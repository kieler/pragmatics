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
package de.cau.cs.kieler.adaptagrams.properties;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * @author uru
 */
public final class CGraphProperties {

    /*--------------------------------------------------------------------------------------------
     *                          Internal Use only
     */

    /**
     * The original object from which a graph element was created.
     */
    public static final IProperty<KGraphElement> ORIGIN =
            new Property<KGraphElement>("cgraph.origin");

    // FIXME shouldnt be a property here!
    public static final IProperty<Boolean> CONSIDER_PREVIOUS_POSITION =
            new Property<Boolean>("de.cau.cs.kieler.klay.cola.considerPreviousPositions", false);
    /**
     * Utility class.
     */
    private CGraphProperties() {
    }

}
