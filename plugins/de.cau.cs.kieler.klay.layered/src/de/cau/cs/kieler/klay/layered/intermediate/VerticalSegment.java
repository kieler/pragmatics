/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.klay.layered.graph.LEdge;

/**
 * @author dag
 *
 */
final class VerticalSegment {
    CNode parentNode;
    double relativePositionX;
    KVector bend1, bend2;
    KVectorChain junctionPoints = new KVectorChain();
    double x, y1, y2;
    //testing zero spacing
    LEdge lEdge;

    VerticalSegment(final KVector bend1, final KVector bend2) {
        this.bend1 = bend1;
        this.bend2 = bend2;

        if (bend1.y < bend2.y) {
            x = bend1.x;
            y1 = bend1.y;
            y2 = bend2.y;
        } else {
            x = bend2.x;
            y1 = bend2.y;
            y2 = bend1.y;
        }
    }

    boolean intersects(final VerticalSegment o) {
        return Comp.eq(this.x, o.x) && Comp.ge(this.y2, o.y1) && Comp.le(this.y1, o.y2);
    }

    @Override
    public String toString() {
        return "\nb1: " + bend1 + " \tb2: " + bend2 + " \t" + junctionPoints;
    }
}
