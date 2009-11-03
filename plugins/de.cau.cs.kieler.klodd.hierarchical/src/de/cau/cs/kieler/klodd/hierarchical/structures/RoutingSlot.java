/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.structures;

/**
 * A routing slot is a place in which layer connections can be routed.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class RoutingSlot {

    /** the slot rank determines the lengthwise distance to the preceding layer. */
    public int rank = 0;

    /** crosswise starting and ending position of this slot. */
    public float start, end;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "slot:" + Math.round(start) + "-" + Math.round(end);
    }

}
