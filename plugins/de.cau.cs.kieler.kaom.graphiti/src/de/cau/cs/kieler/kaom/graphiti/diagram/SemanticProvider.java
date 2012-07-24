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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;

import de.cau.cs.kieler.kaom.Entity;

/**
 * A provider of semantic elements for KAOM diagrams.
 *
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class SemanticProvider {
   
    /** the diagram type provider for the current diagram. */
    private IDiagramTypeProvider diagramTypeProvider;
    
    /**
     * Creates a semantic provider for KAOM diagrams.
     * 
     * @param thediagramTypeProvider the diagram type provider
     */
    public SemanticProvider(final IDiagramTypeProvider thediagramTypeProvider) {
        this.diagramTypeProvider = thediagramTypeProvider;
    }
    
    /**
     * Fetch the the domain model entity for the given container.
     * 
     * @param containerShape a container shape
     * @return the domain model entity of the container
     */
    public Entity fetchEntity(final ContainerShape containerShape) {
        Object obj = diagramTypeProvider.getFeatureProvider()
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
        ResourceSet resourceSet = diagramTypeProvider.getDiagramEditor().getResourceSet();
        for (Resource resource : resourceSet.getResources()) {
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

}
