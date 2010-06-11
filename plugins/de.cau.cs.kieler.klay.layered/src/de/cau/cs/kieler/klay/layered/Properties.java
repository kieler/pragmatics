/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import de.cau.cs.kieler.core.util.Property;

/**
 * Container for priority definitions.
 *
 * @author msp
 */
public final class Properties {
    
    /** Definition of node types used in the layered approach. */
    public enum NodeType {
        /** a normal node is created from a node of the original graph. */
        NORMAL,
        /** a dummy node created to split a long edge. */
        LONG_EDGE;
    }    

    /** priority of elements. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(
            "priority", 0);
    /** node type. */
    public static final Property<NodeType> NODE_TYPE = new Property<NodeType>(
            "nodeType", NodeType.NORMAL);
    
    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
