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
package de.cau.cs.kieler.klay.codaflow.processors

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph
import de.cau.cs.kieler.adaptagrams.cgraph.CNode
import de.cau.cs.kieler.adaptagrams.cgraph.CPort
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor
import de.cau.cs.kieler.kiml.options.PortSide
import java.util.Iterator
import org.eclipse.xtext.xbase.lib.Pair
import de.cau.cs.kieler.klay.codaflow.properties.InternalCodaflowProperties
import org.adaptagrams.SeparationConstraint
import org.adaptagrams.Dim
import de.cau.cs.kieler.adaptagrams.properties.CoLaProperties

/**
 * Removes crossings by introducing ordering constraints on nodes u and v 
 * where at least one node is part of a tree.
 * 
 * @author uru
 */
class TreeOrderingProcessor implements ILayoutProcessor {
    
    var spacing = 0f;
    
    override process(CGraph graph, IKielerProgressMonitor progressMonitor) {
        
        spacing = graph.getProperty(CoLaProperties.SPACING)
        println("SPA " + spacing)
        for (n : graph.children) {
        
            val eastPorts = n.getPorts(PortSide.EAST).sortBy[it.pos.y].iterator
            processSide(eastPorts, [e | e.pos.y], [e | e.rectSizeRaw.y / 2f])
            
            val westPorts = n.getPorts(PortSide.WEST).sortBy[it.pos.y].iterator 
            processSide(westPorts, [e | e.pos.y], [e | e.rectSizeRaw.y / 2f])
            
        }
        
        
        
    }
    
    private def processSide(Iterator<CPort> ports, (CNode) => Double posFun, (CNode) => Double centerFun) {
        // skip unconnected ports
        var Pair<Double, CNode> top = null
        while (ports.hasNext && top == null ) {
            top = ports.next.getMaximalNode(posFun)
        }
        
        if (top == null) {
            return;
        }
        
        while (ports.hasNext) {
            val p = ports.next
            val maxNode = p.getMaximalNode(posFun)
            if (maxNode != null) { // the port must have connected edges
                if (top.key > maxNode.key) {
                    if (maxNode.value.getProperty(InternalCodaflowProperties.PART_OF_TREE)) {
                       val g = centerFun.apply(maxNode.value) + centerFun.apply(top.value) + spacing
                       println(g + " " + maxNode.value.rectSizeRaw + " " + top.value.rectSizeRaw)
                       val sc = new SeparationConstraint(Dim.YDIM, top.value.cIndex, maxNode.value.cIndex, g)
                       top.value.graph.constraints.add(sc) 
                    }
                }
                top = maxNode
            }
        }
    }
    
    /**
     * @return the connected node with the maximal position
     */
    private def Pair<Double, CNode> getMaximalNode(CPort port, (CNode) => Double posFun) {
        val connectedNodes = port.connectedNodes
        if (connectedNodes.size == 1) {
            val n = connectedNodes.get(0)
            return posFun.apply(n) -> n
        } else {
            return connectedNodes // for all connected nodes
              .map[posFun.apply(it) -> it]  // get position
              .reduce(e1, e2|if(e1.key > e2.key) return e1 else e2) // find max
        }
    }
    
}