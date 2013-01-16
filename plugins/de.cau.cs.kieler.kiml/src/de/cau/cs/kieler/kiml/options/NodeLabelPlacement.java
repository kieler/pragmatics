/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.options;

import java.util.EnumSet;

/**
 * Options for controlling how node labels are placed by layout algorithms. The corresponding layout
 * option will usually accept an {@link EnumSet} over this enumeration, theoretically allowing
 * arbitrary and even contradictory subsets of options to be set. Note that you are restricted to
 * the following combinations if you want your choice to make sense:
 * <ul>
 *   <li>Exactly one of the {@link #INSIDE} and {@link #OUTSIDE} options.</li>
 *   <li>Exactly one of the {@link #H_LEFT}, {@link #H_CENTER}, and {@link #H_RIGHT} options.</li>
 *   <li>Exactly one of the {@link #V_TOP}, {@link #V_CENTER}, and {@link #V_BOTTOM} options.</li>
 * </ul>
 * 
 * <p><i>Note:</i> Layout algorithms may only support a subset of these options.</p>
 *
 * @author msp
 * @author cds
 */
public enum NodeLabelPlacement {
    
    /**
     * Horizontal left placement.
     */
    H_LEFT,
    
    /**
     * Horizontal center placement.
     */
    H_CENTER,
    
    /**
     * Horizontal right placement.
     */
    H_RIGHT,
    
    /**
     * Vertical top placement.
     */
    V_TOP,
    
    /**
     * Vertical center placement.
     */
    V_CENTER,
    
    /**
     * Vertical bottom placement.
     */
    V_BOTTOM,
    
    /**
     * Place node labels on the inside of the node. This should usually be combined with size
     * constraints to ensure the node is big enough to accomodate its labels.
     */
    INSIDE,
    
    /**
     * Place node labels on the outside of the node.
     */
    OUTSIDE;
    
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static NodeLabelPlacement valueOf(final int i) {
        return values()[i];
    }
    

    /**
     * Returns an empty enum set over this enumeration, which prevents the layout algorithm from
     * changing the label's coordinates.
     * 
     * @return set over this enumeration representing fixed node label placement constraints.
     */
    public static EnumSet<NodeLabelPlacement> fixed() {
        return EnumSet.noneOf(NodeLabelPlacement.class);
    }
    
    /**
     * Returns a node label placement to place the node label inside the node, centered at its top.
     * 
     * @return node lable placement for inside top center placement.
     */
    public static EnumSet<NodeLabelPlacement> insideTopCenter() {
        return EnumSet.of(INSIDE, V_TOP, H_CENTER);
    }
    
    /**
     * Returns a node label placement to place the node label outside the node, centered below it.
     * 
     * @return node lable placement for outside bottom center placement.
     */
    public static EnumSet<NodeLabelPlacement> outsideBottomCenter() {
        return EnumSet.of(OUTSIDE, V_BOTTOM, H_CENTER);
    }
}
