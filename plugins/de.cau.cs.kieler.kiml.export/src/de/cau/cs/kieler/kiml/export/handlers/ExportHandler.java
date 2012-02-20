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
package de.cau.cs.kieler.kiml.export.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.export.ExportPlugin;
import de.cau.cs.kieler.kiml.export.Messages;
import de.cau.cs.kieler.kiml.export.ui.ExportDialog;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * The handler that is responsible for exporting graphs from graphical diagrams.
 * 
 * @author mri
 */
public class ExportHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        // get the active editor
        IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
        // open the export dialog
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        ExportDialog exportDialog = new ExportDialog(shell);
        int code = exportDialog.open();
        if (code == Dialog.OK) {
            try {
                
                // retrieve a kgraph representation of the diagram
                LayoutMapping<?> mapping = EclipseLayoutInfoService.getInstance()
                        .getManager(editorPart, null).buildLayoutGraph(editorPart, null);
                KNode inputGraph = mapping.getLayoutGraph();
                
                // get the selected configuration
                GraphFormatData formatData = exportDialog.getFormat();
                String filePath = exportDialog.getExportFile();
                boolean isWorkspacePath = exportDialog.isExportWorkspacePath();
                
                // perform the export
                String exportGraph = performExport(inputGraph, formatData.getHandler());
                
                // write the export file
                Writer writer = createWriter(filePath, isWorkspacePath);
                writer.write(exportGraph);
                writer.close();
                
            } catch (Throwable exception) {
                Status myStatus = new Status(IStatus.WARNING, ExportPlugin.PLUGIN_ID,
                        Messages.ExportHandler_export_failed_error, exception);
                StatusManager.getManager().handle(myStatus,
                        StatusManager.BLOCK | StatusManager.SHOW);
            }
        }
        return null;
    }
    
    /**
     * Perform a graph export using the given transformation handler.
     * 
     * @param graph a graph
     * @param transHandler the transformation handler
     * @return a serialized transformed graph
     */
    private <T> String performExport(final KNode graph, final ITransformationHandler<T> transHandler) {
        TransformationData<KNode, T> transData = new TransformationData<KNode, T>();
        transData.setSourceGraph(graph);
        IGraphTransformer<KNode, T> transformer = transHandler.getExporter();
        transformer.transform(transData);
        return transHandler.serialize(transData.getTargetGraphs().get(0));
    }
    
    /**
     * Creates a writer to a file that is located in the workspace or in the file system.
     * 
     * @param path the file path
     * @param isWorkspacePath true if the file path is relative to the workspace
     * @return the new writer
     * @throws IOException thrown when the file could not be opened
     */
    private static Writer createWriter(final String path,
            final boolean isWorkspacePath) throws IOException {
        if (isWorkspacePath) {
            // workspace path
            IPath filePath = new Path(path);
            URI fileURI = URI.createPlatformResourceURI(filePath.toOSString(), true);
            URIConverter uriConverter = new ExtensibleURIConverterImpl();
            OutputStream outputStream = uriConverter.createOutputStream(fileURI);
            return new OutputStreamWriter(outputStream);
        } else {
            // file system path
            File file = new File(path);
            return new FileWriter(file);
        }
    }
    
}
