/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.diagram.custom.commands;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.progress.WorkbenchJob;

import de.cau.cs.kieler.core.ui.commands.AbstractReInitDiagramCommand;
import de.cau.cs.kieler.core.ui.util.EditorUtils;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorUtil;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;
import de.cau.cs.kieler.kaom.diagram.part.Messages;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * A command that reinitializes a diagram file from a given kixs file.
 * 
 * @author soh
 * @kieler.rating 2010-06-14 proposed yellow
 */
public class ReInitKaomDiagramCommand extends AbstractReInitDiagramCommand {

    /** File extension for diagram files. */
    private static final String DIAGRAM_EXTENSION = "kaod";

    /** File extension for model files. */
    private static final String MODEL_EXTENSION = "kaom";

    /** Delay for the auto layout. */
    private static final long AUTO_LAYOUT_DELAY = 1000;

    /**
     * Perform actions after the reinit.
     * 
     * @param path
     *            the file
     * @param partners
     *            the partner files
     */
    @Override
    protected void performPostOperationAction(final IFile path,
            final List<IFile> partners) {
        WorkbenchJob job = new WorkbenchJob("") {

            @Override
            public IStatus runInUIThread(final IProgressMonitor monitor) {
                // perform auto layout
                IEditorPart editor = EditorUtils.getLastActiveEditor();
                EditPart part = null;
                if (editor != null) {
                    EclipseLayoutServices.getInstance().layout(editor, part,
                            false, true);
                }
                return new Status(IStatus.OK,
                        "de.cau.cs.kieler.synccharts.diagram.custom", "Done");
            }
        };

        job.schedule(AUTO_LAYOUT_DELAY);
    }

    /**
     * Create a new diagram file from the given semantics model.
     * 
     * @param diagramRoot
     *            the root element.
     * @param editingDomain
     *            the editing domain.
     * @param diagramFile
     *            the destination file
     * @return true if the creation was successful
     */
    @Override
    public boolean createNewDiagram(final EObject diagramRoot,
            final TransactionalEditingDomain editingDomain,
            final IFile diagramFile) {
        List<IFile> affectedFiles = new LinkedList<IFile>();
        refreshWorkspace();

        // get the destination file
        refreshWorkspace();

        if (!diagramFile.exists()) {
            // create a new file
            byte[] buf = { 0 };
            InputStream stream = new ByteArrayInputStream(buf);
            try {
                diagramFile.create(stream, true, null);
                refreshWorkspace();
                stream.close();
            } catch (CoreException e0) {
                e0.printStackTrace();
            } catch (IOException e0) {
                e0.printStackTrace();
            }
        }

        KaomDiagramEditorUtil.setCharset(diagramFile);
        affectedFiles.add(diagramFile);
        URI diagramModelURI = URI.createPlatformResourceURI(diagramFile
                .getFullPath().toString(), true);
        ResourceSet resourceSet = editingDomain.getResourceSet();
        final Resource diagramResource = resourceSet
                .createResource(diagramModelURI);
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(
                editingDomain,
                Messages.KaomNewDiagramFileWizard_InitDiagramCommand,
                affectedFiles) {

            @Override
            protected CommandResult doExecuteWithResult(
                    final IProgressMonitor monitor, final IAdaptable info)
                    throws ExecutionException {
                int diagramVID = KaomVisualIDRegistry
                        .getDiagramVisualID(diagramRoot);
                if (diagramVID != EntityEditPart.VISUAL_ID) {
                    String msg = Messages.KaomNewDiagramFileWizard_IncorrectRootError;
                    return CommandResult.newErrorCommandResult(msg);
                }
                Diagram diagram = ViewService.createDiagram(diagramRoot,
                        EntityEditPart.MODEL_ID,
                        KaomDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                diagramResource.getContents().add(diagram);
                return CommandResult.newOKCommandResult();
            }
        };
        try {
            OperationHistoryFactory.getOperationHistory().execute(command,
                    new NullProgressMonitor(), null);
            diagramResource.save(KaomDiagramEditorUtil.getSaveOptions());
            KaomDiagramEditorUtil.openDiagram(diagramResource);
        } catch (ExecutionException e) {
            KaomDiagramEditorPlugin.getInstance().logError(
                    "Unable to create model and diagram", e); //$NON-NLS-1$
        } catch (IOException ex) {
            KaomDiagramEditorPlugin.getInstance().logError(
                    "Save operation failed for: " + diagramModelURI, ex); //$NON-NLS-1$
        } catch (PartInitException ex) {
            KaomDiagramEditorPlugin.getInstance().logError(
                    "Unable to open editor", ex); //$NON-NLS-1$
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDiagramExtension() {
        return DIAGRAM_EXTENSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getModelExtension() {
        return MODEL_EXTENSION;
    }
}
