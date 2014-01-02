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
