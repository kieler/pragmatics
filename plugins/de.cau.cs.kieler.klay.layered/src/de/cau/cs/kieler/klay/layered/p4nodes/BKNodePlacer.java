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
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * @author jjc
 */
public class BKNodePlacer extends AbstractAlgorithm implements ILayoutPhase {

    private List<LEdge> markedEdges;

    private HashMap<LNode, LNode> root;

    private HashMap<LNode, LNode> align;

    private HashMap<LNode, LNode> sink;

    private HashMap<LNode, Double> shift;

    private HashMap<LNode, Double> x;
    
    private float normalSpacing;

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("BK node placement", 1);
        markedEdges = new LinkedList<LEdge>();
        root = new HashMap<LNode, LNode>();
        align = new HashMap<LNode, LNode>();
        sink = new HashMap<LNode, LNode>();
        shift = new HashMap<LNode, Double>();
        x = new HashMap<LNode, Double>();
        
        normalSpacing  = layeredGraph.getProperty(Properties.OBJ_SPACING);

        // directions?!?
        markConflicts(layeredGraph);

        verticalAlignment(layeredGraph);

        horizontalCompaction(layeredGraph);
        
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                System.out.println("Set position of " + node.toString() + " to " + x.get(node));
                node.getPosition().y = x.get(node);
            }
        }

        getMonitor().done();
    }

    private void markConflicts(final LayeredGraph layeredGraph) {
        for (int i = 1; i < (layeredGraph.getLayers().size() - 1); i++) {
            int k0 = 0;
            int l = 1;
            for (int l1 = 1; l1 < layerSize(layeredGraph, i + 1); l1++) {
                if (l1 == layerSize(layeredGraph, i + 1)
                        || incidentToInnerSegment(nodeByPosition(layeredGraph, i + 1, l1), -1)) {
                    int k1 = layerSize(layeredGraph, i);
                    if (incidentToInnerSegment(nodeByPosition(layeredGraph, i + 1, l1), -1)) {
                        k1 = firstUpperNeighbor(nodeByPosition(layeredGraph, i + 1, l1)).getIndex();
                    }
                    while (l <= l1) {
                        for (LNode node : allUpperNeighbors(nodeByPosition(layeredGraph, i + 1, l))) {
                            int k = node.getIndex();
                            if (k < k0 || k > k1) {
                                markedEdges.add(getEdge(node,
                                        nodeByPosition(layeredGraph, i + 1, l)));
                            }
                        }
                        l++;
                    }
                    k0 = k1;
                }
            }
        }
    }

    private void verticalAlignment(final LayeredGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                root.put(node, node);
                align.put(node, node);
            }
        }
        for (int i = 0; i < layeredGraph.getLayers().size(); i++) {
            int r = 0;
            for (int k = 0; k < layeredGraph.getLayers().get(i).getNodes().size(); k++) {
                LNode vik = nodeByPosition(layeredGraph, i, k);
                List<LNode> neighbors = allUpperNeighbors(vik);
                if (neighbors.size() > 0) {
                    int d = neighbors.size();
                    for (int m = (int) Math.floor(((d + 1.0) / 2.0)); m <= (int) Math
                            .ceil(((d + 1.0) / 2.0)); m++) {
                        if (align.get(vik).equals(vik)) {
                            LNode um = neighbors.get(m - 1);
                            if (!markedEdges.contains(getEdge(um, vik)) && r < um.getIndex()) {
                                align.put(um, vik);
                                root.put(vik, root.get(um));
                                align.put(vik, root.get(vik));
                                r = um.getIndex();
                            }
                        }
                    }
                }
            }
        }
    }

    private void horizontalCompaction(final LayeredGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                sink.put(node, node);
                shift.put(node, Double.POSITIVE_INFINITY);
            }
        }
        
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                if (root.get(v).equals(v)) {
                    placeBlock(v);
                }
            }
        }
        
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                x.put(v, x.get(root.get(v)));
                if (shift.get(sink.get(root.get(v))) < Double.POSITIVE_INFINITY) {
                    x.put(v, x.get(v) + shift.get(sink.get(root.get(v))));
                }
            }
        }

    }

    private void placeBlock(final LNode v) {
        if (!x.containsKey(v)) {
            x.put(v, 0.0);
            LNode w = v;
            do {
                if (w.getIndex() > 0) {
                    LNode u = root.get(w.getLayer().getNodes().get(w.getIndex() - 1));
                    placeBlock(u);
                    if (sink.get(v).equals(v)) {
                        sink.put(v, u);
                    }
                    if (!sink.get(v).equals(u)) {
                        //TODO replace 0 with minimum separation constraint
                        shift.put(sink.get(u),
                                Math.min(shift.get(sink.get(u)), x.get(u) - x.get(v) - normalSpacing));
                    } else {
                        x.put(v, Math.max(x.get(v), x.get(u) + normalSpacing));
                    }
                }
                w = align.get(w);
            } while (w != v);
        }
    }

    private int layerSize(final LayeredGraph layeredGraph, final int layer) {
        return layeredGraph.getLayers().get(layer).getNodes().size();
    }

    private LNode nodeByPosition(final LayeredGraph layeredGraph, final int layer,
            final int position) {
        return layeredGraph.getLayers().get(layer).getNodes().get(position);
    }

    private boolean incidentToInnerSegment(final LNode node, final int layerOffset) {
        if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            for (LEdge edge : node.getIncomingEdges()) {
                if (edge.getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        && edge.getSource().getNode().getLayer().getIndex() == node.getLayer()
                                .getIndex() + layerOffset) {
                    return true;
                }
            }
            for (LEdge edge : node.getOutgoingEdges()) {
                if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        && edge.getTarget().getNode().getLayer().getIndex() == node.getLayer()
                                .getIndex() + layerOffset) {
                    return true;
                }
            }
        }
        return false;
    }

    private LNode firstUpperNeighbor(final LNode node) {
        return node.getIncomingEdges().iterator().next().getSource().getNode();
    }

    private List<LNode> allUpperNeighbors(final LNode node) {
        List<LNode> result = new LinkedList<LNode>();
        for (LEdge edge : node.getIncomingEdges()) {
            result.add(edge.getSource().getNode());
        }
        Collections.sort(result, new NeighborComparator());
        return result;
    }

    private LEdge getEdge(final LNode source, final LNode target) {
        for (LEdge edge : source.getConnectedEdges()) {
            if (edge.getTarget().getNode().equals(target)
                    || edge.getSource().getNode().equals(target)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        // TODO Auto-generated method stub
        return null;
    }

    class NeighborComparator implements Comparator<LNode> {

        /**
         * {@inheritDoc}
         */
        public int compare(final LNode o1, final LNode o2) {
            int result = 0;
            if (o1.getIndex() < o2.getIndex()) {
                result = -1;
            } else if (o1.getIndex() > o2.getIndex()) {
                result = 1;
            }
            return result;
        }

    }

}
