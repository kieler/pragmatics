/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;

import de.menges.topologie.Topologie.Model;

/**
 * A provider of semantic elements for rail diagrams.
 *
 * @author msp
 */
public class SemanticProvider {
    
    /** the diagram type provider for the current diagram. */
    private IDiagramTypeProvider diagramTypeProvider;
    
    /**
     * Creates a semantic provider for rail diagrams.
     * 
     * @param thediagramTypeProvider the diagram type provider
     */
    public SemanticProvider(final IDiagramTypeProvider thediagramTypeProvider) {
        this.diagramTypeProvider = thediagramTypeProvider;
    }
    
    /**
     * Fetch the the domain model entity for the given container.
     * @param containerShape a container shape
     * @return the domain model entity of the container
     */
    public final Model fetchModel(final ContainerShape containerShape) {
        Object obj = diagramTypeProvider.getFeatureProvider()
                .getBusinessObjectForPictogramElement(containerShape);
        if (obj instanceof Model) {
            return (Model) obj;
        } else if (containerShape instanceof Diagram) {
            return linkModel((Diagram) containerShape);
        }
        throw new IllegalStateException("The given container is not linked to an entity.");
    }

    /**
     * Create a new link to the model.
     * 
     * @param diagram the diagram
     * @return the top level entity
     */
    private Model linkModel(final Diagram diagram) {
        ResourceSet resourceSet = diagramTypeProvider.getDiagramEditor().getResourceSet();
        for (Resource resource : resourceSet.getResources()) {
            for (EObject object : resource.getContents()) {
                if (object instanceof Model) {
                    Model model = (Model) object;
                    PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
                    link.setPictogramElement(diagram);
                    link.getBusinessObjects().add(model);
                    return model;
                }
            }
        }
        throw new IllegalStateException("No resource with a topology model was found.");
    }

}
