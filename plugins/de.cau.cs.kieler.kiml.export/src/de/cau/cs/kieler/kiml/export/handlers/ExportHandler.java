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

import java.io.OutputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExportPlugin;
import de.cau.cs.kieler.kiml.export.ExportUtil;
import de.cau.cs.kieler.kiml.export.Messages;
import de.cau.cs.kieler.kiml.export.ui.ExportDialog;
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
                LayoutMapping<?> mapping = 
                        EclipseLayoutInfoService.getInstance().getManager(editorPart, null)
                                .buildLayoutGraph(editorPart, null);
                KNode graph = mapping.getLayoutGraph();
                // get the selected configuration
                AbstractExporter exporter = exportDialog.getExporter();
                String filePath = exportDialog.getExportFile();
                boolean isWorkspacePath = exportDialog.isExportWorkspacePath();
                MapPropertyHolder options = exportDialog.getOptions();
                // open the export file
                OutputStream stream = ExportUtil.createOutputStream(filePath, isWorkspacePath);
                // perform the export
                IKielerProgressMonitor monitor =
                        new KielerProgressMonitor(new NullProgressMonitor());
                exporter.doExport(graph, stream, options, monitor);
                stream.close();
            } catch (Throwable exception) {
                Status myStatus =
                        new Status(IStatus.WARNING, ExportPlugin.PLUGIN_ID,
                                Messages.ExportHandler_export_failed_error, exception);
                StatusManager.getManager().handle(myStatus,
                        StatusManager.BLOCK | StatusManager.SHOW);
            }
        }
        return null;
    }
}
