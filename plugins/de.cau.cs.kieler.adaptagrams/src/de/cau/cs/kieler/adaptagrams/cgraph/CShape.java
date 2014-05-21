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
package de.cau.cs.kieler.adaptagrams.cgraph;

import org.adaptagrams.Rectangle;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Insets;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * A shape that has a position, a size and defines margins and insets.
 * 
 * Margins are spacings outside the bounding box of the shape, insets (aka padding) on the inside of
 * the shape's bounding box.
 * 
 * Note that there is a difference between {@link #getPos()} and {@link #getRectPos()}. The latter
 * returns the position of the adaptagrams rectangle an thus the position of this shape after any
 * layout is applied.
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

    // CHECKSTYLEOFF VisibilityModifier
    /** The adaptagrams {@link Rectangle} representing this shape. */
    protected Rectangle rect;

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
     * Note that this position might diverge from the position of {@link #getRectPos()}. This position is
     * mainly used <emph>before</emph> any layout algorithm is applied.
     * 
     * Opposed to KGraph, adaptagram positions are absolute. 
     * 
     * @return the current position of this element.
     * @see #getRectPos()
     */
    public KVector getPos() {
        return pos;
    }

    /**
     * Note that this position might diverge from the one of {@link #getPos()}. The rect's position
     * is mainly used <emph>after</emph> any layout algorithm is applied.
     * 
     * To get the raw position (w/o considering margins) use the {@link #getRectPosRaw()} method.
     * 
     * @return the position of the underlying adaptagrams rectangle. The margin is already
     *         subtracted.
     * @see #getPos()
     */
    public KVector getRectPos() {
        return new KVector(rect.getMinX() + getMargins().left, rect.getMinY() + getMargins().top);
    }
    
    /**
     * @return the position of the underlying adaptagrams rectangle without considering any margins.
     */
    public KVector getRectPosRaw() {
        return new KVector(rect.getMinX(), rect.getMinY());
    }

    /**
     * @return the center position of the underlying adaptagrams rectangle. The margin is already
     *         subtracted.
     */
    public KVector getRectCenter() {
        double centerX =
                rect.getMinX() + getMargins().left
                        + (rect.width() - getMargins().left - getMargins().right) / 2;
        double centerY =
                rect.getMinY() + getMargins().top
                        + (rect.height() - getMargins().top - getMargins().bottom) / 2;
        return new KVector(centerX, centerY);
    }

    /**
     * @return the center position of the underlying adaptagrams rectangle.
     */
    public KVector getRectCenterRaw() {
        double centerX = rect.getMinX() + (rect.width()) / 2;
        double centerY = rect.getMinY() + (rect.height()) / 2;
        return new KVector(centerX, centerY);
    }
    
    /**
     * @return the current size of this element.
     */
    public KVector getSize() {
        return size;
    }
    
    /**
     * Note that the size might diverge from the on of {@link #getSize()}. The rect's size is mainly
     * used <emph>after</emph> any layout algorithm is applied.
     * 
     * To get the raw size (w/o considering margins) use the {@link #getRectSizeRaw()} method.
     * 
     * @return the size of the underlying adaptagrams rectangle. The margin is already subtracted.
     */
    public KVector getRectSize() {
        return new KVector(rect.width() - getMargins().left - getMargins().right, rect.height()
                - getMargins().top - getMargins().bottom);
    }
    
    /**
     * @return the raw size of the underlying adaptagrams rectangle, no margin is considered.
     */
    public KVector getRectSizeRaw() {
        return new KVector(rect.width(), rect.height());
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
