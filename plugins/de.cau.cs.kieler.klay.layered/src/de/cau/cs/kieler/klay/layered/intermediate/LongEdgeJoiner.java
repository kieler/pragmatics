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

import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Removes dummy nodes due to edge splitting. (dummy nodes that have the node
 * type {@link de.cau.cs.kieler.klay.layered.properties.NodeType#LONG_EDGE})
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; nodes are placed; edges are routed.</dd>
 *   <dt>Postcondition:</dt><dd>there are no dummy nodes of type
 *     {@link de.cau.cs.kieler.klay.layered.properties.NodeType#LONG_EDGE}.</dd>
 *   <dt>Slots:</dt><dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link HierarchicalPortOrthogonalEdgeRouter}</dd>
 * </dl>
 *
 * @author cds
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class LongEdgeJoiner extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        getMonitor().begin("Edge joining", 1);
        
        // Iterate through the layers
        for (Layer layer : layeredGraph) {
            // Get a list iterator for the layer's nodes (since we might be
            // removing dummy nodes from it)
            ListIterator<LNode> nodeIterator = layer.getNodes().listIterator();
            
            while (nodeIterator.hasNext()) {
                LNode node = nodeIterator.next();
                
                // Check if it's a dummy edge we're looking for
                if (node.getProperty(Properties.NODE_TYPE).equals(NodeType.LONG_EDGE)) {
                    // Get the input and output port (of which we assume to have only one,
                    // on the western side and on the eastern side, respectively)
                    List<LEdge> inputPortEdges =
                        node.getPorts(PortSide.WEST).iterator().next().getIncomingEdges();
                    List<LEdge> outputPortEdges =
                        node.getPorts(PortSide.EAST).iterator().next().getOutgoingEdges();
                    int edgeCount = inputPortEdges.size();
                    
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
        
        getMonitor().done();
    }

}
