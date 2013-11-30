/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.service.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.service.LayoutMapping;

/**
 * The KGraph provider that retrieves a KGraph by opening a diagram file in an
 * editor and using KIML to build the graph structure.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class EditorKGraphProvider implements IKGraphProvider<IPath> {

    /** the message for an unsupported diagram editor. */
    private static final String MESSAGE_NO_MANAGER =
            "The editor for the diagram file is not supported by KIML.";
    /** the message for a missing eclipse editor. */
    private static final String MESSAGE_NO_EDITOR =
            "The diagram file could not be opened in an eclipse editor.";

    /** the 'layout before analysis' option. */
    private boolean layoutBeforeAnalysis;

    /**
     * {@inheritDoc}
     */
    public KNode getKGraph(final IPath parameter,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Retrieving KGraph from " + parameter.toString(), 1);
        
        // get the diagram file
        final IFile diagramFile =
                ResourcesPlugin.getWorkspace().getRoot().getFile(parameter);
        final Maybe<Throwable> wrappedException = new Maybe<Throwable>();
        final Maybe<KNode> graph = new Maybe<KNode>();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                                .getActivePage();
                // remember the initially opened editors
                Set<IEditorPart> initialEditors = new HashSet<IEditorPart>();
                for (IEditorReference editorReference : page
                        .getEditorReferences()) {
                    IEditorPart editor = editorReference.getEditor(false);
                    if (editor != null) {
                        initialEditors.add(editor);
                    }
                }
                
                // open the diagram file in an editor
                IEditorDescriptor editorDescriptor =
                        IDE.getDefaultEditor(diagramFile);
                if (editorDescriptor == null
                        || editorDescriptor.isOpenExternal()) {
                    throw new RuntimeException(MESSAGE_NO_EDITOR);
                }
                IEditorPart editorPart;
                try {
                    editorPart =
                            IDE.openEditor(page, diagramFile,
                                    editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    wrappedException.set(e);
                    return;
                }
                
                // get the layout manager for the editor
                IDiagramLayoutManager<?> layoutManager =
                        LayoutManagersService.getInstance().getManager(
                                editorPart, null);
                if (layoutManager == null) {
                    if (!initialEditors.contains(editorPart)) {
                        page.closeEditor(editorPart, false);
                    }
                    wrappedException.set(new RuntimeException(MESSAGE_NO_MANAGER));
                    return;
                }
                
                // build the graph
                LayoutMapping<?> mapping = layoutManager.buildLayoutGraph(editorPart, null);
                graph.set(mapping.getLayoutGraph());
                
                // layout if the option is set
                if (layoutBeforeAnalysis) {
                    DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
                    IStatus status = layoutEngine.layout(mapping, monitor.subTask(1));
                    if (!status.isOK()) {
                        if (!initialEditors.contains(editorPart)) {
                            page.closeEditor(editorPart, false);
                        }
                        wrappedException.set(status.getException());
                        return;
                    }
                }
                if (!initialEditors.contains(editorPart)) {
                    page.closeEditor(editorPart, false);
                }
            }
        });
        
        monitor.done();
        // throw any exceptions that occurred inside the UI thread
        if (wrappedException.get() instanceof RuntimeException) {
            throw (RuntimeException) wrappedException.get();
        } else if (wrappedException.get() != null) {
            throw new RuntimeException(wrappedException.get());
        }
        return graph.get();
    }

    /**
     * Sets the option which specifies whether layout should be performed before
     * the KGraph is returned.
     * 
     * @param layoutBeforeAnalysisOption
     *            true if layout should be performed
     */
    public void setLayoutBeforeAnalysis(final boolean layoutBeforeAnalysisOption) {
        layoutBeforeAnalysis = layoutBeforeAnalysisOption;
    }
    
}
