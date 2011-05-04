/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.properties;

/**
 * Definition of node types used in the layered approach.
 * 
 * @author msp
 * @author cds
 */
public enum NodeType {
    /** a node representing an external port. */
    EXTERNAL_PORT,
    /** a normal node is created from a node of the original graph. */
    NORMAL,
    /** a dummy node created to split a long edge. */
    LONG_EDGE,
    /** a dummy node created to cope with ports at the northern or southern side. */
    NORTH_SOUTH_PORT;
}