/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import io.typefox.sprotty.api.Dimension
import io.typefox.sprotty.api.Point
import io.typefox.sprotty.api.SModelElement
import java.util.ArrayList
import java.util.List
import org.eclipse.elk.core.util.Pair

/**
 * A helper class containing static methods for mapping of KGraph and SGraph bounds and layouts
 * @author stu114054
 *
 */
class KGraphMappingUtil {    
    /**
     * Map the layout of each KGraph element in the map to their corresponding SGraph elements
     */
    static def mapLayout(ArrayList<Pair<KGraphElement, SModelElement>> mapping) {
        mapping.forEach[pair |
            var KGraphElement kGraphElement = pair.first
            var SModelElement sModelElement = pair.second
            
            // Layout data looks different for different KGraph Element Types
            if (kGraphElement instanceof KNode && sModelElement instanceof SKNode) {
                mapLayout(kGraphElement as KNode, sModelElement as SKNode)
            } else if (kGraphElement instanceof KEdge && sModelElement instanceof SKEdge) {
                mapLayout(kGraphElement as KEdge, sModelElement as SKEdge)
            } else if (kGraphElement instanceof KPort && sModelElement instanceof SKPort) {
                mapLayout(kGraphElement as KPort, sModelElement as SKPort)
            } else if (kGraphElement instanceof KLabel && sModelElement instanceof SKLabel) {
                mapLayout(kGraphElement as KLabel, sModelElement as SKLabel)
            } else {
                throw new IllegalArgumentException("The KGraph and SGraph classes do not map to each other: " + kGraphElement.class + ", " + sModelElement.class)
            }
        ]
    }
    
    private static def mapLayout(KNode knode, SKNode sknode) {
        sknode.position = new Point(knode.xpos, knode.ypos)
        sknode.size = new Dimension(knode.width, knode.height)
    }
    
    private static def mapLayout(KEdge kedge, SKEdge skedge) {
        // copy all routing points
        var List<Point> routingPoints = new ArrayList<Point>
        var sourcePoint = kedge.sourcePoint
        var targetPoint = kedge.targetPoint
        routingPoints.add(new Point(sourcePoint.x, sourcePoint.y))
        for (bendPoint : kedge.bendPoints) {
            routingPoints.add(new Point(bendPoint.x, bendPoint.y))
        }
        routingPoints.add(new Point(targetPoint.x, targetPoint.y))
        skedge.routingPoints = routingPoints
    }
    
    private static def mapLayout(KLabel klabel, SKLabel sklabel) {
        sklabel.position = new Point(klabel.xpos, klabel.ypos)
        sklabel.size = new Dimension(klabel.width, klabel.height)
        //sklabel.alignment = new Point(klabel.)? // TODO alignments? insets?
    }
    
    private static def mapLayout(KPort kport, SKPort skport) {
        skport.position = new Point(kport.xpos, kport.ypos)
        skport.size = new Dimension(kport.width, kport.height)
    }
    
    
    /**
     * Maps the bounds and alignments of an SGraph element to its corresponding KGraph
     * TODO probably deprecated
     */
    static def mapBounds(ArrayList<Pair<KGraphElement, SModelElement>> mapping) {
        mapping.forEach[pair | 
            var KGraphElement kGraphElement = pair.first
            var SModelElement sModelElement = pair.second
            if (sModelElement instanceof SKNode) {
                if (kGraphElement instanceof KNode) {
                    kGraphElement.height = sModelElement.size.height as float
                    kGraphElement.width  = sModelElement.size.width  as float
                    // TODO also map the alignments
                } else {
                    throw new IllegalArgumentException("The KGraph and SGraph classes do not map to each other: " + kGraphElement.class + ", " + sModelElement.class)
                }
            }
        ]
    }
}