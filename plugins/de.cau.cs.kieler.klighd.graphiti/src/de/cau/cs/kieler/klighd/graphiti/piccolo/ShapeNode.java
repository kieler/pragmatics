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
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import org.eclipse.graphiti.mm.pictograms.Shape;

import edu.umd.cs.piccolo.PNode;

/**
 * A Piccolo node wrapping a Pictogram shape.
 * 
 * @author mri
 */
public class ShapeNode extends PNode {

    private static final long serialVersionUID = 6280554909111287283L;

    /** the Pictogram shape represented by this node. */
    private Shape shape;

    /**
     * Constructs a ShapeNode.
     * 
     * @param shape
     *            the Pictogram shape
     */
    public ShapeNode(final Shape shape) {
        this.shape = shape;
    }

    /**
     * Returns the Pictogram shape represented by this node.
     * 
     * @return the Pictogram shape
     */
    public Shape getShape() {
        return shape;
    }
}
