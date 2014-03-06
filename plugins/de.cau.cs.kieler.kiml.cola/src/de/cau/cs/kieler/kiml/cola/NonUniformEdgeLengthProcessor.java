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
package de.cau.cs.kieler.kiml.cola;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.cola.graph.CEdge;
import de.cau.cs.kieler.kiml.cola.graph.CGraph;
import de.cau.cs.kieler.kiml.cola.graph.CNode;
import de.cau.cs.kieler.kiml.cola.graph.CPort;

/**
 * @author uru
 * 
 */
public class NonUniformEdgeLengthProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {

        double idealEdgeLength = graph.getProperty(ColaProperties.IDEAL_EDGE_LENGTHS);

        System.out.println("Ideal Edge: " + idealEdgeLength);

        // set the ideal edge lengths for the port dummy edges
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                graph.idealEdgeLengths[p.cEdgeIndex] = p.idealDummyEdgeLength + 20;
                // System.out.println("Port: " +p.cEdgeIndex + " " + p.idealDummyEdgeLength);
            }
        }

        Map<CNode, Set<CNode>> neighbours = Maps.newHashMap();
        for (CNode node : graph.getChildren()) {
            Set<CNode> neighbourSet = Sets.newHashSet();
            for (CEdge e : node.getOutgoingEdges()) {
                neighbourSet.add(e.getTarget());
            }
            for (CEdge e : node.getIncomingEdges()) {
                neighbourSet.add(e.getSource());
            }
            neighbours.put(node, neighbourSet);
        }

        // calc link sizes for each edge
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                Set<CNode> srcSet = neighbours.get(e.getSource());
                Set<CNode> tgtSet = neighbours.get(e.getTarget());

                // calc link lengths
                int union = Sets.union(srcSet, tgtSet).size();
                int intersection = Sets.intersection(srcSet, tgtSet).size();

                // symmdifflinks
                double sqrt = Math.sqrt((double) (union - intersection));

                // jaccard
                // double jaccard = 1;
                // if (union > 1 && intersection > 1) {
                // jaccard = 1 / (intersection / (double) union);
                // }

                // System.out.println("Edge: " + e.cIndex + " " + (1 + idealEdgeLength * sqrt));
                graph.idealEdgeLengths[e.cIndex] = 1 + idealEdgeLength * sqrt;
                // edgeLengths[index] = 1 + w * jaccard;
            }
        }
    }

}
