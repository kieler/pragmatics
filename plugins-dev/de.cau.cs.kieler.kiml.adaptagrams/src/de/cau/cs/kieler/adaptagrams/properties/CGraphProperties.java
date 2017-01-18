/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.properties;

import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

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
    public static final IProperty<ElkGraphElement> ORIGIN = new Property<ElkGraphElement>(
            "cgraph.origin");

    /**
     * .
     */
    public static final IProperty<Boolean> INCLUDE_SPACING_IN_MARGIN = new Property<Boolean>(
            "cgraph.marginIncSpacing", false);

    /**
     * Utility class.
     */
    private CGraphProperties() {
    }

}
