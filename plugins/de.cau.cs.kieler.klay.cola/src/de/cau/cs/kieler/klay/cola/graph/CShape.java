/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.graph;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Insets;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * A shape that has a position, a size and defines margins and insets.
 * 
 * Margins are spacings outside the bounding box of the shape, insets (aka padding) on the
 * inside of the shape's bounding box.
 * 
 * @author uru
 */
public abstract class CShape extends CGraphElement {

    private static final long serialVersionUID = 7543591108386925637L;

    /** the current position of the element. */
    private final KVector pos = new KVector();
    /** the size of the element. */
    private final KVector size = new KVector();

    /** the margin area around this node. */
    private final Margins margins = new Margins();
    /** the insets inside this node, usually reserved for port and label placement. */
    private final Insets insets = new Insets();

    /**
     * Constructs a new shaped element.
     * 
     * @param graph
     *            the containing graph
     */
    public CShape(final CGraph graph) {
        super(graph);
    }

    /**
     * @return the current position of this element.
     */
    public KVector getPos() {
        return pos;
    }

    /**
     * @return the current size of this element.
     */
    public KVector getSize() {
        return size;
    }

    /**
     * @return the margins
     */
    public Margins getMargins() {
        return margins;
    }

    /**
     * @return the insets
     */
    public Insets getInsets() {
        return insets;
    }
}
