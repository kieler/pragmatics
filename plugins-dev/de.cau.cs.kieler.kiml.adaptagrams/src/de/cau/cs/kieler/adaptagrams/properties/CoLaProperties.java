/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.properties;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Layout options of the {@link org.adaptagrams.ConstrainedFDLayout ConstrainedFDLayout}.
 * 
 * @author uru
 */
public final class CoLaProperties {

    /** A scalar modifier of ideal edge lengths. */
    public static final IProperty<Float> IDEAL_EDGE_LENGTHS = new Property<Float>(
            "de.cau.cs.kieler.cola.idealEdgeLength", 100f, 1f);

    /** Direction of the desired layout. */
    public static final IProperty<Boolean> AVOID_OVERLAPS = new Property<Boolean>(
            "de.cau.cs.kieler.cola.avoidOverlaps", true);

    /** Whether to retain previous node positions during the layout run. */
    public static final IProperty<Boolean> CONSIDER_PREVIOUS_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.cola.considerPreviousPositions", false);

    /** Spacing between nodes. */
    public static final Property<Float> SPACING = new Property<Float>(
            LayoutOptions.SPACING, 18.0f, 0.0f);
    
    /** Whether to adapt port positions after a layout run. */
    // public static final IProperty<Boolean> ADAPT_PORT_POSITIONS = new Property<Boolean>(
    // "de.cau.cs.kieler.cola.adaptPortPositions", false);

    /*
     * Experimental
     */

    /**
     * Direction of the desired layout.
     */
    public static final IProperty<Boolean> DIRECTION_CONSTRAINTS = new Property<Boolean>(
            "de.cau.cs.kieler.cola.useDirection", true);

    /**
     * Utility class.
     */
    private CoLaProperties() {
    }
}
