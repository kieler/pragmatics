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
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;

import de.cau.cs.kieler.core.model.graphiti.ui.AbstractReInitGraphitiDiagramCommand;
import de.cau.cs.kieler.kaom.Entity;

/**
 * @author soh
 */
public class ReInitKaomDiagramCommand extends
        AbstractReInitGraphitiDiagramCommand {

    /**
     * Creates a new ReInitKaomDiagramCommand.java.
     * 
     */
    public ReInitKaomDiagramCommand() {
        super(KaomDiagramEditor.DIAGRAM_TYPE, 0, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditorId() {
        return KaomDiagramEditor.EDITOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void linkModelToDiagram(final EObject modelRoot,
            final Diagram diagram, final Resource diagramResource) {
        PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
        link.setPictogramElement(diagram);
        link.getBusinessObjects().add(modelRoot);
        diagramResource.getContents().add(diagram);
        for (EObject eObj : modelRoot.eContents()) {
            // WTB: pattern matching...
            if (eObj instanceof Entity) {
                linkModelToDiagram((Entity) eObj, diagramResource);
            }
        }
    }

    private void linkModelToDiagram(final Entity obj,
            final Resource diagramResource) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDiagramExtension() {
        return KaomDiagramEditor.DIAGRAM_FILE_EXTENSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getModelExtension() {
        return KaomDiagramEditor.MODEL_FILE_EXTENSION;
    }

}
