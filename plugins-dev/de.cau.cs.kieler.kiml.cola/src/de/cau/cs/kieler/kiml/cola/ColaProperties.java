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
package de.cau.cs.kieler.kiml.cola;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * @author uru
 * 
 */
public final class ColaProperties {

    /**
     * A scalar modifier of ideal edge lengths.
     */
    public static final IProperty<Float> IDEAL_EDGE_LENGTHS = new Property<Float>(
            "de.cau.cs.kieler.kiml.cola.idealEdgeLengths", 100f, 1f);

    /**
     * Direction of the desired layout.
     */
    public static final IProperty<Boolean> AVOID_OVERLAPS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.cola.avoidOverlaps", true);

    /**
     * Whether to adapt port positions after a layout run.
     */
    public static final IProperty<Boolean> ADAPT_PORT_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.cola.adaptPortPositions", false);

    /*
     * Experimental
     */

    /**
     * Direction of the desired layout.
     */
    public static final IProperty<Boolean> DIRECTION_CONSTRAINTS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.cola.direction", true);

    /**
     * Whether to apply port constraints.
     */
    public static final IProperty<Boolean> PORT_CONSTRAINTS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.cola.port", true);

    /**
     * Utility class.
     */
    private ColaProperties() {
    }
}
