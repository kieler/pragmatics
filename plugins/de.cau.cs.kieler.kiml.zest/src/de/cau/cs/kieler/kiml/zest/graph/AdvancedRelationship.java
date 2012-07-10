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
package de.cau.cs.kieler.kiml.zest.graph;

import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.exampleStructures.SimpleRelationship;

/**
 * Implementation of the LayoutRelationship interface in the Zest layout
 * package.
 * 
 * @author msp
 */
public class AdvancedRelationship extends SimpleRelationship {

    private Object realObject;

    /**
     * Initializes the relationship.
     * 
     * @param therealObject reference to the corresponding edge in the layout graph
     * @param sourceEntity source entity
     * @param destinationEntity target entity
     */
    public AdvancedRelationship(final Object therealObject, final LayoutEntity sourceEntity,
            final LayoutEntity destinationEntity) {
        super(sourceEntity, destinationEntity, false);
        this.realObject = therealObject;
    }

    /**
     * Returns the reference to the corresponding edge in the layout graph.
     * 
     * @return edge from the layout graph
     */
    public Object getRealObject() {
        return realObject;
    }

}
