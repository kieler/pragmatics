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
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.util.ColorConstant;

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
    public static final String DIAGRAM_FILE_EXTENSION = "kaog";
    /** file extension for KAOM model files. */
    public static final String MODEL_FILE_EXTENSION = "kaom";
    
    /**
     * Fetch the the domain model entity for the given container.
     * 
     * @param containerShape a container shape
     * @return the domain model entity of the container
     */
    public Entity fetchEntity(final ContainerShape containerShape) {
        Object obj = getDiagramTypeProvider().getFeatureProvider()
                .getBusinessObjectForPictogramElement(containerShape);
        if (obj instanceof Entity) {
            return (Entity) obj;
        } else if (containerShape instanceof Diagram) {
            return linkTopEntity((Diagram) containerShape);
        }
        throw new IllegalStateException("The given container is not linked to an entity.");
    }
    
    /**
     * Create a new link to the top level entity of the diagram.
     * 
     * @param diagram the diagram
     * @return the top level entity
     */
    private Entity linkTopEntity(final Diagram diagram) {
        for (Resource resource : getResourceSet().getResources()) {
            for (EObject object : resource.getContents()) {
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
    
    /**
     * Fetch the style with given identifier.
     * 
     * @param diagram the diagram for which the style shall be fetched
     * @param id the style identifier
     * @return a style instance, or {@code null} if the id is unknown
     */
    public Style fetchStyle(final Diagram diagram, final String id) {
        Style style = null;
        for (Style diagramStyle : diagram.getStyles()) {
            if (id.equals(diagramStyle.getId())) {
                 style = diagramStyle;
                 break;
            }
        }
        if (style == null) {
            style = createStyle(diagram, id);
        }
        return style;
    }
    
    /** the default style id for KAOM diagrams. */
    public static final String DEFAULT_STYLE = "default";
    
    /**
     * Create the style with given identifier.
     * 
     * @param diagram the diagram where the style shall be created
     * @param id the style identifier
     * @return a new style instance, or {@code null} if the id is unknown
     */
    private Style createStyle(final Diagram diagram, final String id) {
        if (DEFAULT_STYLE.equals(id)) {
            IGaService gaService = Graphiti.getGaService();
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram, ColorConstant.BLACK));
            style.setBackground(gaService.manageColor(diagram, ColorConstant.WHITE));
            return style;
        }
        return null;
    }

}
