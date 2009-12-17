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

import org.eclipse.zest.layouts.exampleStructures.SimpleNode;

/**
 * Implementation of the LayoutEntity interface in the Zest layout package.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class AdvancedEntity extends SimpleNode {

    /**
     * Initializes the entity.
     * 
     * @param realObject reference to the corresponding layout node in the
     *            layout graph
     * @param x current horizontal position
     * @param y current vertical position
     * @param width current width
     * @param height current height
     */
    public AdvancedEntity(final Object realObject, final double x,
            final double y, final double width, final double height) {
        super(realObject, x, y, width, height);
    }

}
