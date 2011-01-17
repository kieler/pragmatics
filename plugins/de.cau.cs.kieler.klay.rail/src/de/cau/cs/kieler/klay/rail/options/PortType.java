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
* Enum class holding the possible port types of a railway element's ports for the
* layout.
*
* @author jjc
*
*/
public enum PortType {
    
    /** The peak of a switch. */
    STUMP,
    /** The port of a switch with a straight line to the peak. */
    STRAIGHT,
    /** The branch of a switch. */
    BRANCH;

}
