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
package de.cau.cs.kieler.klay.rail.options;

/**
 *
 * Enum class holding the possible node types of a railway element for the
 * layout.
 *
 * @author jjc
 *
 */
public enum NodeType {

    /** A node with only one port, end of a modeled railway. */
    BREACH_OR_CLOSE,
    /** A node with three ports, a switch with a left direction. */
    SWITCH_LEFT,
    /** A node with three ports, a switch with a right direction. */
    SWITCH_RIGHT;

}
