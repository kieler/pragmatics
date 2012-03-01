/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.components;

import java.util.Collection;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Takes a list of layered graphs and combines them into a single graph, placing them according to some
 * algorithm.
 * 
 * @author cds
 * @author msp
 */
abstract class GraphPlacer {
    
    /**
     * Computes a proper placement for the given graphs and combines them into a single graph.
     * 
     * @param components the graphs to be combined.
     * @return a single graph containing the components.
     */
    public abstract LayeredGraph combine(final List<LayeredGraph> components);
    

    /**
     * Move the source graphs into the destination graph using a specified offset.
     * 
     * @param destGraph the destination graph.
     * @param sourceGraph the source graphs.
     * @param offsetx x coordinate offset.
     * @param offsety y coordinate offset.
     */
    protected void moveGraphs(final LayeredGraph destGraph, final Collection<LayeredGraph> sourceGraphs,
            final double offsetx, final double offsety) {
        
        for (LayeredGraph sourceGraph : sourceGraphs) {
            moveGraph(destGraph, sourceGraph, offsetx, offsety);
        }
    }

    /**
     * Move the source graph into the destination graph using a specified offset. This method should
     * only be called once per graph, since it also applies the graph's set offset in addition to the
     * one given in the methods of this argument.
     * 
     * @param destGraph the destination graph.
     * @param sourceGraph the source graph.
     * @param offsetx x coordinate offset.
     * @param offsety y coordinate offset.
     */
    protected void moveGraph(final LayeredGraph destGraph, final LayeredGraph sourceGraph,
            final double offsetx, final double offsety) {
        
        KVector graphOffset = sourceGraph.getOffset().translate(offsetx, offsety);
        
        for (Layer layer : sourceGraph) {
            for (LNode node : layer) {
                node.getPosition().add(graphOffset);
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        edge.getBendPoints().translate(graphOffset);
                        for (LLabel label : edge.getLabels()) {
                            label.getPosition().add(graphOffset);
                        }
                    }
                }
                destGraph.getLayerlessNodes().add(node);
            }
        }
    }
    
    /**
     * Offsets the given graphs by a given offset without moving their nodes to another graph.
     * 
     * @param graph the graph to offset.
     * @param offsetx x coordinate offset.
     * @param offsety y coordinate offset.
     */
    protected void offsetGraphs(final Collection<LayeredGraph> graphs, final double offsetx,
            final double offsety) {
        
        for (LayeredGraph graph : graphs) {
            offsetGraph(graph, offsetx, offsety);
        }
    }
    
    /**
     * Offsets the given graph by a given offset without moving its nodes to another graph. This
     * method can be called as many times as possible on a given graph: it does not take the graph's
     * offset into account.
     * 
     * @param graph the graph to offset.
     * @param offsetx x coordinate offset.
     * @param offsety y coordinate offset.
     */
    protected void offsetGraph(final LayeredGraph graph, final double offsetx, final double offsety) {
        KVector graphOffset = new KVector(offsetx, offsety);
        
        for (Layer layer : graph) {
            for (LNode node : layer) {
                node.getPosition().add(graphOffset);
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        edge.getBendPoints().translate(graphOffset);
                        for (LLabel label : edge.getLabels()) {
                            label.getPosition().add(graphOffset);
                        }
                    }
                }
            }
        }
    }

}
