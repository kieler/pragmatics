/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.export.handlers;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * @author wah
 * 
 */
public class GraphFileHandler {

    private File sourceFile;
    private String targetFormat;
    private IPath targetDirectory;

    private IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();

    /** the last exception thrown inside the UI thread. */
    private Throwable lastException;

    /** the message for a missing eclipse editor. */
    private static final String MESSAGE_NO_EDITOR = "The diagram file could not be opened in an eclipse editor.";

    /** the message for an unsupported diagram editor. */
    private static final String MESSAGE_NO_MANAGER = "The editor for the diagram file is not supported by KIML.";

    /** the 'layout before analysis' option. */
    private boolean layoutBeforeAnalysis;
    /** the last created kgraph instance. */
    private KNode graph;

    /**
     * @param sourceFile
     * @param targetFormat
     * @param targetDirectory
     */
    public GraphFileHandler(final File sourceFile, final String targetFormat,
            final IPath targetDirectory) {
        super();
        this.sourceFile = sourceFile;
        this.targetFormat = targetFormat;
        this.targetDirectory = targetDirectory;
    }

    /**
     * @return the workspace sourceFile
     */
    public File getAbsoluteSourceFile() {
        return sourceFile;
    }

    /**
     * @return the Workspace sourceIPath
     */
    public IPath getAbsoluteSourceIPath() {
        return new Path(sourceFile.toString());
    }

    /**
     * @param sourceFile
     *            the sourceFile to set
     */
    public void setSourceFile(final File sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * @return the targetFormat
     */
    public String getTargetFormat() {
        return targetFormat;
    }

    /**
     * @param targetFormat
     *            the targetFormat to set
     */
    public void setTargetFormat(final String targetFormat) {
        this.targetFormat = targetFormat;
    }

    /**
     * @return the targetDirectory
     */
    public IPath getWorkspaceTargetDirectory() {
        return targetDirectory;
    }

    /**
     * @return the Absolute targetDirectory
     */
    public IPath getAbsoluteTargetDirectory() {
        return workspacePath.append(targetDirectory);
    }

    /**
     * @param targetDirectory
     *            the targetDirectory to set
     */
    public void setDirectory(IPath targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    /**
     * @return the Workspace targetIPath
     */
    public IPath getWorkspaceTargetIPath() {
        // get the last dot position
        int dotPos = this.sourceFile.getName().lastIndexOf(".");
        // replace the file extension with the new one
        return this.targetDirectory.append(this.sourceFile.getName().substring(0, dotPos)
                .concat(".").concat(this.getTargetFormat()));
    }

    /**
     * @return the absolute targetIPath
     */
    public IPath getAbsoluteTargetIPath() {
        return workspacePath.append(getWorkspaceTargetIPath());
    }

    /**
     * @return the workspace targetFile
     */
    public File getWorkspaceTargetFile() {
        return this.getWorkspaceTargetIPath().toFile();
    }

    /**
     * @return the absolute targetFile
     */
    public File getAbsoluteTargetFile() {
        return workspacePath.append(this.getWorkspaceTargetIPath()).toFile();
    }

    /**
     * {@inheritDoc}
     */
    public KNode getKGraph() {

        // get the diagram file
        final IFile diagramFile = (IFile) this.getAbsoluteSourceFile();
        lastException = null;
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage();
                // remember the initially opened editors
                Set<IEditorPart> initialEditors = new HashSet<IEditorPart>();
                for (IEditorReference editorReference : page.getEditorReferences()) {
                    IEditorPart editor = editorReference.getEditor(false);
                    if (editor != null) {
                        initialEditors.add(editor);
                    }
                }
                // open the diagram file in an editor
                IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                    // FIXME throw a more specific exception
                    throw new RuntimeException(MESSAGE_NO_EDITOR);
                }
                IEditorPart editorPart;
                try {
                    editorPart = IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    lastException = e;
                    return;
                }
                // get the layout manager for the editor
                IDiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance()
                        .getManager(editorPart, null);
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
                if (!initialEditors.contains(editorPart)) {
                    page.closeEditor(editorPart, false);
                }
            }
        }, true);
        // throw any exceptions that occurred inside the UI thread
        if (lastException != null) {
            throw new RuntimeException(lastException);
        }
        return graph;
    }

    private <T> String performExport(final KNode graph, final ITransformationHandler<T> transHandler) {
        TransformationData<KNode, T> transData = new TransformationData<KNode, T>();
        transData.setSourceGraph(graph);
        IGraphTransformer<KNode, T> transformer = transHandler.getExporter();
        transformer.transform(transData);
        return transHandler.serialize(transData.getTargetGraphs().get(0));
    }

}
