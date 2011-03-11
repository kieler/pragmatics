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
package de.cau.cs.kieler.rail.editor;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.model.graphiti.ui.AbstractReInitGraphitiDiagramCommand;
import de.menges.topologie.Topologie.Basegraph.Edge;
import de.menges.topologie.Topologie.SpecializedVertices.Einbruchsknoten;

/**
 * 
 * @author soh
 */
public class ReInitKrailDiagramCommand extends
        AbstractReInitGraphitiDiagramCommand {

    /**
     * Creates a new ReInitKrailDiagramCommand.
     */
    public ReInitKrailDiagramCommand() {
        super(KrailDiagramEditor.DIAGRAM_TYPE, 0, false);
    }

    @Override
	protected final String getEditorId() {
        return KrailDiagramEditor.EDITOR_ID;
    }

    @Override
	protected final boolean isConnection(final EObject eObj) {
        return eObj instanceof Edge;
    }

    @Override
	protected final EObject getConnectionSource(final EObject connection) {
        if (connection instanceof Edge) {
            Edge e = (Edge) connection;
            return e.getFrom();
        }
        return null;
    }

    @Override
	protected final EObject getConnectionTarget(final EObject connection) {
        if (connection instanceof Edge) {
            Edge e = (Edge) connection;
            return e.getDestination();
        }

        return null;
    }

    @SuppressWarnings("restriction")
	@Override
	protected final void performPostOperationAction(final IFile path,
            final List<IFile> partners, final IProgressMonitor monitor,
            final IEditorPart newEditor) {
        super.performPostOperationAction(path, partners, monitor, newEditor);
        if (newEditor instanceof KrailDiagramEditor) {
            KrailDiagramEditor editor = (KrailDiagramEditor) newEditor;
            RootEditPart rep = editor.getGraphicalViewer().getRootEditPart();
            EditPart ep = rep.getContents();
            if (ep instanceof DiagramEditPart) {
                DiagramEditPart dep = (DiagramEditPart) ep;
                PictogramElement pe = dep.getPictogramElement();
                if (pe instanceof Diagram) {
                    final Diagram diag = (Diagram) pe;
                    editor.getEditingDomain()
                            .getCommandStack()
                            .execute(
                                    new RecordingCommand(editor
                                            .getEditingDomain()) {

                                        @Override
                                        protected void doExecute() {
                                            KrailNewWizard
                                            .addLayoutDefaultsToDiagram(diag);

                                            setEntryPoint(diag);

                                        }
                                    });
                }
            }
        }
    }

    /**
     * Each Graph needs one Entry point for the layouter to work. Since the
     * entry point is not yet specified in the model file the first
     * 'Einbruchsknoten' will be selected as entry point.
     * @param diag
     *            the diagram
     */
    private void setEntryPoint(final Diagram diag) {
        List<EObject> contents = diag.eContents();
        PictogramElement result = null;
        for (EObject e : contents) {
            if (e instanceof PictogramElement) {
                PictogramElement pe = (PictogramElement) e;
                PictogramLink link = pe.getLink();
                if (link != null) {
                    List<?> bo = link.getBusinessObjects();
                    if (bo != null && bo.size() > 0) {
                        Object mm = bo.get(0);
                        if (mm instanceof Einbruchsknoten) {
                            result = pe;
                            break;
                        }
                    }
                }
            }
        }
        if (result != null) {
            Property p2 = MmFactory.eINSTANCE.createProperty();
            p2.setKey("layout:de.cau.cs.kieler.klay.rail.entryPoint");
            p2.setValue("true");
            result.getProperties().add(p2);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
	protected final String getDiagramExtension() {
        return KrailDiagramEditor.DIAGRAM_FILE_EXTENSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	protected final String getModelExtension() {
        return KrailDiagramEditor.MODEL_FILE_EXTENSION;
    }

    @Override
	protected final boolean addChildrenRecursively(final EObject eObj) {
        return false;
    }

}
