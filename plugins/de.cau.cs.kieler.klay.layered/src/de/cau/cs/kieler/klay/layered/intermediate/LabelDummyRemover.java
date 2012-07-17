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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 *
 * @author jjc
 */
public class LabelDummyRemover extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Label dummy removal", 1);
        
        for (Layer layer : layeredGraph.getLayers()) {
            // An iterator is necessary for traversing nodes, since
            // dummy nodes might be removed
            ListIterator<LNode> nodeIterator = layer.getNodes().listIterator();
            
            while (nodeIterator.hasNext()) {
                LNode node = nodeIterator.next();
                
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LABEL) {
                    // First, place label on position of dummy node 
                    LLabel label = (LLabel) node.getProperty(Properties.ORIGIN);
                    label.getPosition().x = node.getPosition().x;
                    label.getPosition().y = node.getPosition().y;
                    
                    // We can assume that there are exactly one western and eastern port
                    // on each side of the node
                    List<LEdge> inputPortEdges =
                        node.getPorts(PortSide.WEST).iterator().next().getIncomingEdges();
                    List<LEdge> outputPortEdges =
                        node.getPorts(PortSide.EAST).iterator().next().getOutgoingEdges();
                    int edgeCount = inputPortEdges.size();
                    
                    // TODO rework doc here
                    // The following code assumes that edges with the same indices in the two
                    // lists originate from the same long edge, which is true for the current
                    // implementation of LongEdgeSplitter and HyperedgeDummyMerger
                    while (edgeCount-- > 0) {
                        // Get the two edges
                        LEdge survivingEdge = inputPortEdges.get(0);
                        LEdge droppedEdge = outputPortEdges.get(0);
                        
                        // Do some edgy stuff
                        survivingEdge.setTarget(droppedEdge.getTarget());
                        droppedEdge.setSource(null);
                        droppedEdge.setTarget(null);
                        
                        // Join their bend points
                        KVectorChain survivingBendPoints = survivingEdge.getBendPoints();
                        for (KVector bendPoint : droppedEdge.getBendPoints()) {
                            survivingBendPoints.add(new KVector(bendPoint));
                        }
                    }
                    
                    // Remove the node
                    nodeIterator.remove();
                }
            }
        }

    }

}
