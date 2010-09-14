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
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import de.cau.cs.kieler.kaom.Entity;

/**
 * Diagram editor class for the Graphiti KAOM editor.
 * 
 * @author msp
 */
public class KaomDiagramEditor extends DiagramEditor {

    /** the editor identifier. */
    public static final String EDITOR_ID = "de.cau.cs.kieler.kaom.graphiti.editor";
    /** the diagram type name to store in diagram files. */
    public static final String DIAGRAM_TYPE = "KAOM";
    /** the diagram type identifier. */
    public static final String DIAGRAM_TYPE_ID = "de.cau.cs.kieler.kaom.diagramType";
    /** the diagram type provider identifier. */
    public static final String DIAGRAM_TYPE_PROVIDER_ID = "de.cau.cs.kieler.kaom.diagramTypeProvider";
    /** file extension for Graphiti KAOM diagram files. */
    public static final String DIAGRAM_FILE_EXTENSION = "kaogd";
    /** file extension for KAOM model files. */
    public static final String MODEL_FILE_EXTENSION = "kaom";
    
    /**
     * Create a new link to the top level entity of the diagram.
     * 
     * @param diagram the diagram
     * @return the top level entity
     */
    public Entity findTopEntity(final Diagram diagram) {
        for (Resource resource : getResourceSet().getResources()) {
            if (!resource.getContents().isEmpty()) {
                EObject object = resource.getContents().get(0);
                if (object instanceof Entity) {
                    Entity topEntity = (Entity) object;
                    PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
                    link.setPictogramElement(diagram);
                    link.getBusinessObjects().add(topEntity);
                    return topEntity;
                }
            }
        }
        throw new IllegalStateException("No resource with a top level entity was found.");
    }

}
