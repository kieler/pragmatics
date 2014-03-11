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
package de.cau.cs.kieler.klay.cola;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * @author uru
 * 
 */
public final class ColaProperties {

    /** A scalar modifier of ideal edge lengths. */
    public static final IProperty<Float> IDEAL_EDGE_LENGTHS = new Property<Float>(
            "de.cau.cs.kieler.klay.cola.idealEdgeLengths", 100f, 1f);
    
    /**
     * Utility class.
     */
    private ColaProperties() {
    }
}
