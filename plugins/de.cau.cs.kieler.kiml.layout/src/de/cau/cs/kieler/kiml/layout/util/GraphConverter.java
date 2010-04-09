/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout.util;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;

/**
 * Class that converts a KGraph into a slim graph.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class GraphConverter extends AbstractAlgorithm {

    /** map of layout nodes and ports to KIELER nodes. */
    private Map<Object, KSlimNode> nodeMap = new HashMap<Object, KSlimNode>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        nodeMap.clear();
    }

    /**
     * Converts a given Ecore graph into a slim graph. Each node contains a
     * reference to the corresponding layout node, and each edge contains a
     * reference to the corresponding layout edge.
     * 
     * @param parentNode parent node to be converted
     * @param includePorts if true, the external ports of the parent node will
     *            also be added as nodes
     * @return a graph which consists of the child nodes
     */
    public KSlimGraph convertGraph(final KNode parentNode, final boolean includePorts) {
        getMonitor().begin("Graph conversion", 1);
        KSlimGraph slimGraph = new KSlimGraph();

        // convert nodes
        for (KNode child : parentNode.getChildren()) {
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(child);
            KSlimNode newNode = new KSlimNode(slimGraph, child);
            newNode.setXpos(nodeLayout.getXpos());
            newNode.setYpos(nodeLayout.getYpos());
            nodeMap.put(child, newNode);
        }

        // convert edges
        for (KNode child : parentNode.getChildren()) {
            for (KEdge layoutEdge : child.getOutgoingEdges()) {
                KNode targetNode = layoutEdge.getTarget();
                if (targetNode.getParent() == child.getParent()) {
                    KSlimEdge newEdge = new KSlimEdge(slimGraph, nodeMap.get(child), nodeMap
                            .get(targetNode), layoutEdge);
                    newEdge.connectNodes();
                }
            }
        }

        if (includePorts) {
            // convert external ports
            for (KPort port : parentNode.getPorts()) {
                KSlimNode newNode = new KSlimNode(slimGraph, port);
                nodeMap.put(port, newNode);
            }

            // convert edges to external ports
            for (KPort port : parentNode.getPorts()) {
                for (KEdge layoutEdge : port.getEdges()) {
                    KNode source = layoutEdge.getSource();
                    KNode target = layoutEdge.getTarget();
                    if (layoutEdge.getSourcePort() == port && target.getParent() == parentNode) {
                        KSlimEdge newEdge = new KSlimEdge(slimGraph, nodeMap.get(port), nodeMap
                                .get(target), layoutEdge);
                        newEdge.connectNodes();
                    } else if (layoutEdge.getTargetPort() == port && source.getParent() == parentNode) {
                        KSlimEdge newEdge = new KSlimEdge(slimGraph, nodeMap.get(source), nodeMap
                                .get(port), layoutEdge);
                        newEdge.connectNodes();
                    }
                }
            }
        }

        getMonitor().done();
        return slimGraph;
    }

}
