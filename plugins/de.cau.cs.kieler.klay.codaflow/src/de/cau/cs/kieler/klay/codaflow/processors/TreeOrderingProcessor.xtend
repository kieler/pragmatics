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
import de.cau.cs.kieler.klay.codaflow.util.CodaflowUtil

/**
 * Removes crossings by introducing ordering constraints on nodes u and v 
 * where at least one node is part of a tree.
 * 
 * At the moment this is done in a very naive fashion where 
 * several crossings might be missed when multiple edges
 * are attached to the same port.
 * 
 * @author uru
 */
class TreeOrderingProcessor implements ILayoutProcessor {
    
    var spacing = 0f;
    
    override process(CGraph graph, IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Tree Ordering", 1)
        
        CodaflowUtil.markTrees(graph);
        spacing = graph.getProperty(CoLaProperties.SPACING)        

        for (n : graph.children) {
            val eastPorts = n.getPorts(PortSide.EAST).sortBy[it.pos.y].iterator
            processSide(eastPorts, [e | e.pos.y], [e | e.rectSizeRaw.y / 2f])
            
            val westPorts = n.getPorts(PortSide.WEST).sortBy[it.pos.y].iterator 
            processSide(westPorts, [e | e.pos.y], [e | e.rectSizeRaw.y / 2f])
            
            val northPorts = n.getPorts(PortSide.NORTH).sortBy[it.pos.x].iterator
            processSide(northPorts, [e | e.pos.x], [e | e.rectSizeRaw.x / 2f])
            
            val southPorts = n.getPorts(PortSide.SOUTH).sortBy[it.pos.x].iterator
            processSide(southPorts, [e | e.pos.x], [e | e.rectSizeRaw.x / 2f])
        }
        
        progressMonitor.done();
    }
    
    /**
     * Checks for a list of ports p0...pn of the same side, if simple 
     * crossings are present. For example if pi.target.y > pi+1.target.y.
     * In such a case, a separation constraint is introduced iff
     * either pi.target or pi+1.target is part of a tree. 
     * 
     * @param ports
     *      the ports of one side for which to check for simple crossings.
     * @param posFun
     *      a function returning the position of a node relevant for the 
     *      current ordering (ie for EAST ports the y coordinates are relevant).
     * @param sizeFun
     *      a function returning the size a node contributes to an introduced
     *      separation constraint (ie for EAST ports half the width). 
     */
    private def processSide(Iterator<CPort> ports, (CNode) => Double posFun, (CNode) => Double sizeFun) {
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
            val max = p.getMaximalNode(posFun)
            if (max != null) { // the port must have connected edges
            
                // FIXME also check that a crossing can be introduced at all
                if (top.key > max.key) {
                    val maxNode = max.value
                    val topNode = top.value
                    val tree = maxNode.getProperty(InternalCodaflowProperties.PART_OF_TREE) 
                                || topNode.getProperty(InternalCodaflowProperties.PART_OF_TREE)
                    if (tree) {
                       val g = sizeFun.apply(maxNode) + sizeFun.apply(topNode) + spacing
                       val sc = new SeparationConstraint(Dim.YDIM, topNode.cIndex, maxNode.cIndex, g)
                       topNode.graph.constraints.add(sc) 
                    }
                }
                top = max
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