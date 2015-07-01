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
package de.cau.cs.kieler.klay.codaflow.test

import org.junit.Test
import java.awt.geom.Line2D
import de.cau.cs.kieler.klay.codaflow.util.CodaflowUtil

import static org.junit.Assert.*

/**
 * Tests for the {@link CodaflowUtil} class.
 * 
 * @author uru 
 */
class CodaflowUtilTests {
    
    val EPSILON = 0.00001d
    
    @Test
    def testLineIntersection() {
        
        // intersecting lines
        var l1 = line2(1 -> 1, 4 -> 4)
        var l2 = line2(1 -> 4, 4 -> 1)
        var p = CodaflowUtil.getLineIntersectionPoint(l1, l2)
        assertEquals(2.5, p.x, EPSILON)
        assertEquals(2.5, p.y, EPSILON)
        
        // parallel lines
        l1 = line2(1 -> 1, 4 -> 4)
        l2 = line2(2 -> 1, 5 -> 4)
        p = CodaflowUtil.getLineIntersectionPoint(l1, l2)
        assertNull(p)

        // on the same infinite line but not touching
        l1 = line2(1 -> 1, 4 -> 4)
        l2 = line2(5 -> 5, 7 -> 7)
        p = CodaflowUtil.getLineIntersectionPoint(l1, l2)
        assertNull(p)
        
        // share a segment
        l1 = line2(1 -> 1, 4 -> 4)
        l2 = line2(3 -> 3, 6 -> 6)
        p = CodaflowUtil.getLineIntersectionPoint(l1, l2)
        // we expect the center of the shared path
        assertEquals(3.5, p.x, EPSILON)
        assertEquals(3.5, p.y, EPSILON)
        
        // other way around
        p = CodaflowUtil.getLineIntersectionPoint(l2, l1)
        // we expect the center of the shared path
        assertEquals(3.5, p.x, EPSILON)
        assertEquals(3.5, p.y, EPSILON)
        
    }
        
    private def line2(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return new Line2D.Double(p1.key, p1.value, p2.key, p2.value)
    }
}