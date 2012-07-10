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
package de.cau.cs.kieler.kiml.grana.batch;

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
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * The KGraph provider that retrieves a KGraph by opening a diagram file in an
 * editor and using KIML to build the graph structure.
 * 
 * @author mri
 */
public class DiagramKGraphProvider implements IKGraphProvider<IPath> {

    /** the message for an unsupported diagram editor. */
    private static final String MESSAGE_NO_MANAGER =
            "The editor for the diagram file is not supported by KIML.";
    /** the message for a missing eclipse editor. */
    private static final String MESSAGE_NO_EDITOR =
            "The diagram file could not be opened in an eclipse editor.";

    /** the 'layout before analysis' option. */
    private boolean layoutBeforeAnalysis;
    /** the last exception thrown inside the UI thread. */
    private Throwable lastException;
    /** the last created kgraph instance. */
    private KNode graph;

    /**
     * {@inheritDoc}
     */
    public KNode getKGraph(final IPath parameter,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Retrieving KGraph from " + parameter.toString(), 1);
        // get the diagram file
        final IFile diagramFile =
                ResourcesPlugin.getWorkspace().getRoot().getFile(parameter);
        lastException = null;
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
                    // FIXME throw a more specific exception
                    throw new RuntimeException(MESSAGE_NO_EDITOR);
                }
                IEditorPart editorPart;
                try {
                    editorPart =
                            IDE.openEditor(page, diagramFile,
                                    editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    lastException = e;
                    return;
                }
                // get the layout manager for the editor
                IDiagramLayoutManager<?> layoutManager =
                        EclipseLayoutInfoService.getInstance().getManager(
                                editorPart, null);
                if (layoutManager == null) {
                    if (!initialEditors.contains(editorPart)) {
                        page.closeEditor(editorPart, false);
                    }
                    // FIXME throw a more specific exception
                    lastException = new RuntimeException(MESSAGE_NO_MANAGER);
                    return;
                }
                // build the graph
                LayoutMapping<?> mapping = layoutManager.buildLayoutGraph(editorPart, null);
                graph = mapping.getLayoutGraph();
                // layout if the option is set
                if (layoutBeforeAnalysis) {
                    DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
                    IStatus status = layoutEngine.layout(mapping, monitor.subTask(1));
                    if (!status.isOK()) {
                        if (!initialEditors.contains(editorPart)) {
                            page.closeEditor(editorPart, false);
                        }
                        lastException = status.getException();
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
        if (lastException != null) {
            throw new RuntimeException(lastException);
        }
        return graph;
    }

    /**
     * Sets the option which specifies whether layout should be performed before
     * the KGraph is built.
     * 
     * @param layoutBeforeAnalysisOption
     *            true if layout should be performed before the kgraph
     *            generation
     */
    public void setLayoutBeforeAnalysis(final boolean layoutBeforeAnalysisOption) {
        layoutBeforeAnalysis = layoutBeforeAnalysisOption;
    }

    /**
     * Returns the option which specifies whether layout should be performed
     * before the KGraph is built.
     * 
     * @return true if layout should be performed before the kgraph generation
     */
    public boolean getLayoutBeforeAnalysis() {
        return layoutBeforeAnalysis;
    }
}
