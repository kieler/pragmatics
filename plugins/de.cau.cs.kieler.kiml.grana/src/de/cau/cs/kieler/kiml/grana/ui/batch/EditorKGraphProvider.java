/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
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
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Maybe;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The KGraph provider that retrieves a KGraph by opening a diagram file in an
 * editor and using KIML to build the graph structure.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 * 
 * TODO is this class really unused?
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
            final IElkProgressMonitor monitor) {
        monitor.begin("Retrieving KGraph from " + parameter.toString(), 1);
        
        // get the diagram file
        final IFile diagramFile =
                ResourcesPlugin.getWorkspace().getRoot().getFile(parameter);
        final Maybe<Throwable> wrappedException = new Maybe<Throwable>();
        final Maybe<org.eclipse.elk.graph.KNode> graph = new Maybe<>();
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
                IDiagramLayoutConnector layoutManager = 
                		LayoutConnectorsService.getInstance().getConnector(editorPart, null);
                if (layoutManager == null) {
                    if (!initialEditors.contains(editorPart)) {
                        page.closeEditor(editorPart, false);
                    }
                    wrappedException.set(new RuntimeException(MESSAGE_NO_MANAGER));
                    return;
                }
                
                // build the graph
                LayoutMapping mapping = layoutManager.buildLayoutGraph(editorPart, null);
                graph.set(mapping.getLayoutGraph());
                
                // layout if the option is set
                if (layoutBeforeAnalysis) {
                    IStatus status = new DiagramLayoutEngine().layout(mapping, monitor.subTask(1));
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
        
        // FIXME elkMigrate convert graph
        // graph.get()
        KNode transformedNode = null;
        return transformedNode;
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
