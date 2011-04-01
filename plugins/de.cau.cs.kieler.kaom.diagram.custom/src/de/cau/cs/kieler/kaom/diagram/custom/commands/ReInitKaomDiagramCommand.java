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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.model.ui.AbstractReInitDiagramCommand;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kaom.diagram.custom.AnnotationDisplayer;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditor;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorUtil;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;
import de.cau.cs.kieler.kaom.diagram.part.Messages;

/**
 * A command that reinitializes a diagram file from a given kixs file.
 * 
 * @author soh
 * @kieler.rating 2010-06-14 proposed yellow
 */
public class ReInitKaomDiagramCommand extends AbstractReInitDiagramCommand {

    /** File extension for diagram files. */
    public static final String DIAGRAM_EXTENSION = "kaod";
    /** File extension for XMI model files. */
    public static final String MODEL_EXTENSION = "kaom";
    /** File extension for Xtext model files. */
    public static final String TEXT_EXTENSION = "kaot";
    
    /** parameter for initialization source. */
    private static final String SOURCE_PARAM = "de.cau.cs.kieler.kaom.ui.reinitDiagram.source";

    /** the currently set source file extension. */
    private String sourceExtension = MODEL_EXTENSION;
    
    /**
     * Sets the source file extension to the given format. This must be either
     * {@link #MODEL_EXTENSION} or {@link #TEXT_EXTENSION}.
     * 
     * @param extension the source file extension
     */
    public void setSourceExtension(final String extension) {
        if (TEXT_EXTENSION.equals(extension)) {
            this.sourceExtension = TEXT_EXTENSION;
        } else {
            this.sourceExtension = MODEL_EXTENSION;
        } 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        setSourceExtension(event.getParameter(SOURCE_PARAM));
        return super.execute(event);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IEditorPart createNewDiagram(final EObject diagramRoot,
            final TransactionalEditingDomain editingDomain,
            final IFile diagramFile, final IProgressMonitor monitor) {
        final Maybe<IEditorPart> result = new Maybe<IEditorPart>(null);
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
        final URI diagramModelURI =
                URI.createPlatformResourceURI(diagramFile.getFullPath()
                        .toString(), true);
        ResourceSet resourceSet = editingDomain.getResourceSet();
        final Resource diagramResource =
                resourceSet.createResource(diagramModelURI);
        final AbstractTransactionalCommand command =
                new AbstractTransactionalCommand(editingDomain,
                        Messages.KaomNewDiagramFileWizard_InitDiagramCommand,
                        affectedFiles) {

                    @Override
                    protected CommandResult doExecuteWithResult(
                            final IProgressMonitor monitor,
                            final IAdaptable info) throws ExecutionException {
                        int diagramVID =
                                KaomVisualIDRegistry
                                        .getDiagramVisualID(diagramRoot);
                        if (diagramVID != EntityEditPart.VISUAL_ID) {
                            String msg =
                                    Messages.KaomNewDiagramFileWizard_IncorrectRootError;
                            return CommandResult.newErrorCommandResult(msg);
                        }
                        Diagram diagram =
                                ViewService
                                        .createDiagram(
                                                diagramRoot,
                                                EntityEditPart.MODEL_ID,
                                                KaomDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                        diagramResource.getContents().add(diagram);
                        return CommandResult.newOKCommandResult();
                    }
                };

        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

            public void run() {
                try {
                    OperationHistoryFactory.getOperationHistory().execute(
                            command, new NullProgressMonitor(), null);
                    diagramResource.save(KaomDiagramEditorUtil.getSaveOptions());

                    IEditorPart editor =
                            openDiagram(diagramResource, KaomDiagramEditor.ID);
                    result.set(editor);
                } catch (ExecutionException e) {
                    KaomDiagramEditorPlugin.getInstance().logError(
                            "Unable to create model and diagram", e); //$NON-NLS-1$
                } catch (IOException ex) {
                    KaomDiagramEditorPlugin
                            .getInstance()
                            .logError(
                                    "Save operation failed for: " + diagramModelURI, ex); //$NON-NLS-1$
                } catch (PartInitException ex) {
                    KaomDiagramEditorPlugin.getInstance().logError(
                            "Unable to open editor", ex); //$NON-NLS-1$
                }
            }
        });

        // display some annotations
        IEditorPart editorPart = result.get();
        AnnotationDisplayer.displayAnnotations(editorPart);
        return editorPart;
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
        return sourceExtension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TransactionalEditingDomain createEditingDomain() {
        return GMFEditingDomainFactory.INSTANCE.createEditingDomain();
    }
}
