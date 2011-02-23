/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Split the long edges of the layered graph to obtain a proper layering.
 * For each edge that connects two nodes that are more than one layer apart
 * from each other, create a dummy node to split the edge. The resulting layering
 * is <i>proper</i>, i.e. all edges connect only nodes from subsequent layers.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>the graph is properly layered; that is, each edge
 *     connects nodes in neighbouring layers.</dd>
 * </dl>
 *
 * @author msp
 */
public class EdgeSplitter extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        LPort targetPort = edge.getTarget();
                        int targetIndex = targetPort.getNode().getLayer().getIndex();
                        if (targetIndex != layerIter.nextIndex()) {
                            Layer nextLayer = layerIter.next();
                            LNode dummyNode = new LNode();
                            dummyNode.setProperty(Properties.ORIGIN, edge);
                            dummyNode.setProperty(Properties.NODE_TYPE,
                                    Properties.NodeType.LONG_EDGE);
                            dummyNode.setLayer(nextLayer);
                            LPort dummyInput = new LPort(PortType.INPUT);
                            dummyInput.setNode(dummyNode);
                            LPort dummyOutput = new LPort(PortType.OUTPUT);
                            dummyOutput.setNode(dummyNode);
                            edge.setTarget(dummyInput);
                            LEdge dummyEdge = new LEdge();
                            dummyEdge.copyProperties(edge);
                            dummyEdge.setSource(dummyOutput);
                            dummyEdge.setTarget(targetPort);
                            layerIter.previous();
                        }
                    }
                }
            }
        }
    }

}
