/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 * Processor that tries to move label dummy nodes into the center of their long edges. This is done
 * by switching the order of long edge dummies and label dummies.
 * 
 * <p>
 * If this is the only thing we did we could end up in situations where multiple edges forming a
 * hyperedge are merged such that it's not clear anymore which edge label belongs to which edge:
 * </p>
 * <pre>
 *       An edge label
 *    -------------------+-----------+-------------------------- - - -
 *                       |           |
 *    -------------------+           +-------------------------- - - -
 *                                       Another edge label
 * </pre>
 * <p>
 * We solve this by making sure that the long edge dummies preceding a label dummy node have their
 * {@link InternalProperties#LONG_EDGE_TARGET} property set to {@code null} (we more or less view the
 * label dummy as the new target of those long edge dummies). The same is true for the
 * {@link InternalProperties#LONG_EDGE_SOURCE} property of long edge dummies succeeding a label dummy.
 * </p>
 * 
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>a properly layered graph</dd>
 *     <dd>center edge labels are represented by center label dummy nodes</dd>
 *     <dd>each label dummy and long edge dummy has exactly one incoming and one outgoing edge</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>center label dummy nodes are the centermost dummies of a long edge</dd>
 *     <dd>the {@link InternalProperties#LONG_EDGE_TARGET} property of long edge dummies preceding center
 *         edge label dummies are set to {@code null} to prevent incorrect hyperedge dummy merging; the
 *         same is true for the {@link InternalProperties#LONG_EDGE_SOURCE} property of long edge dummies
 *         succeeding center edge label dummies.
 *   <dt>Slots:</dt>
 *     <dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>{@link LongEdgeSplitter}</dd>
 * </dl>
 * 
 * @author jjc
 * @author cds
 * @kieler.rating yellow proposed cds
 */
public final class LabelDummySwitcher implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Label dummy switching", 1);
        
        // List of label dummies we encounter and list of label dummies and long edge dummies that need
        // to be swapped
        List<LNode> labelDummies = Lists.newArrayList();
        List<Pair<LNode, LNode>> nodesToSwap = Lists.newArrayList();
        
        // Iterate over the graph and gather all label dummies that need to be swapped
        List<LNode> leftLongEdgeDummies = Lists.newArrayList();
        List<LNode> rightLongEdgeDummies = Lists.newArrayList();
        
        for (Layer layer : layeredGraph) {
            for (LNode node : layer.getNodes()) {
                if (node.getType() == NodeType.LABEL) {
                    labelDummies.add(node);
                    leftLongEdgeDummies.clear();
                    rightLongEdgeDummies.clear();
                    
                    // Gather long edge dummies left of the label dummy
                    LNode source = node;
                    do {
                        source = source.getIncomingEdges().iterator().next().getSource().getNode();
                        if (source.getType() == NodeType.LONG_EDGE) {
                            leftLongEdgeDummies.add(source);
                        }
                    } while (source.getType() == NodeType.LONG_EDGE);
                    
                    // Gather long edge dummies right of the label dummy
                    LNode target = node;
                    do {
                        target = target.getOutgoingEdges().iterator().next().getTarget().getNode();
                        if (target.getType() == NodeType.LONG_EDGE) {
                            rightLongEdgeDummies.add(target);
                        }
                    } while (target.getType() == NodeType.LONG_EDGE);
                    
                    // Check whether the label dummy should be switched
                    int leftSize = leftLongEdgeDummies.size();
                    int rightSize = rightLongEdgeDummies.size();
                    
                    if (leftSize > rightSize + 1) {
                        int pos = (leftSize + rightSize) / 2;
                        nodesToSwap.add(new Pair<LNode, LNode>(node, leftLongEdgeDummies.get(pos)));
                    } else if (rightSize > leftSize + 1) {
                        int pos = (rightSize - leftSize) / 2 - 1;
                        nodesToSwap.add(new Pair<LNode, LNode>(node, rightLongEdgeDummies.get(pos)));
                    }
                }
            }
        }

        // Execute the swapping and reset long edge source / target information
        for (Pair<LNode, LNode> swapPair : nodesToSwap) {
            swapNodes(swapPair.getFirst(), swapPair.getSecond());
        }
        for (LNode labelDummy : labelDummies) {
            updateLongEdgeSourceTargetInfo(labelDummy);
        }
        
        monitor.done();
    }

    /**
     * Swaps the two given dummy nodes.
     * 
     * @param dummy1 the first dummy node.
     * @param dummy2 the second dummy node.
     */
    private void swapNodes(final LNode dummy1, final LNode dummy2) {
        Layer layer1 = dummy1.getLayer();
        Layer layer2 = dummy2.getLayer();
        
        // Detect incoming and outgoing ports of the nodes
        LPort inputPort1 = dummy1.getPorts(PortType.INPUT).iterator().next();
        LPort outputPort1 = dummy1.getPorts(PortType.OUTPUT).iterator().next();
        LPort inputPort2 = dummy2.getPorts(PortType.INPUT).iterator().next();
        LPort outputPort2 = dummy2.getPorts(PortType.OUTPUT).iterator().next();
        
        // Store incoming and outgoing edges
        LEdge[] incomingEdges1 = inputPort1.getIncomingEdges().toArray(new LEdge[1]);
        LEdge[] outgoingEdges1 = outputPort1.getOutgoingEdges().toArray(new LEdge[1]);
        LEdge[] incomingEdges2 = inputPort2.getIncomingEdges().toArray(new LEdge[1]);
        LEdge[] outgoingEdges2 = outputPort2.getOutgoingEdges().toArray(new LEdge[1]);

        // Set values of first node to values from second node
        dummy1.setLayer(layer2);
        for (LEdge edge : incomingEdges2) {
            edge.setTarget(inputPort1);
        }
        for (LEdge edge : outgoingEdges2) {
            edge.setSource(outputPort1);
        }

        // Set values of first node to values from second node
        dummy2.setLayer(layer1);
        for (LEdge edge : incomingEdges1) {
            edge.setTarget(inputPort2);
        }
        for (LEdge edge : outgoingEdges1) {
            edge.setSource(outputPort2);
        }
    }
    
    /**
     * Updates the {@link InternalProperties#LONG_EDGE_SOURCE} and
     * {@link InternalProperties#LONG_EDGE_TARGET} properties of long edge dummy nodes preceding and
     * succeeding the given label dummy node.
     * 
     * @param labelDummy the label dummy node.
     */
    private void updateLongEdgeSourceTargetInfo(final LNode labelDummy) {
        // Set all LONG_EDGE_TARGET properties of the predecessors to null
        doUpdateLongEdgeSourceTargetInfo(
                labelDummy,
                node -> node.getIncomingEdges().iterator().next().getSource().getNode(),
                InternalProperties.LONG_EDGE_TARGET);
        
        // Set all LONG_EDGE_SOURCE properties of the successors to null
        doUpdateLongEdgeSourceTargetInfo(
                labelDummy,
                node -> node.getOutgoingEdges().iterator().next().getTarget().getNode(),
                InternalProperties.LONG_EDGE_SOURCE);
    }
    
    /**
     * Does the actual work of setting the long edge source or target of all nodes before or after
     * the given label dummy node to {@code null}.
     * 
     * @param labelDummy
     *            the label dummy node to start from.
     * @param nextElement
     *            a function that, given a node, returns the node to process next. Use this to
     *            decide whether to target all successors or all predecessors of the label dummy
     *            node.
     * @param longEdgeProperty
     *            the property to be set to {@code null}.
     */
    private void doUpdateLongEdgeSourceTargetInfo(final LNode labelDummy,
            final Function<LNode, LNode> nextElement, final IProperty<LPort> longEdgeProperty) {
        
        LNode longEdgeDummy = nextElement.apply(labelDummy);
        while (longEdgeDummy.getType() == NodeType.LONG_EDGE) {
            longEdgeDummy.setProperty(longEdgeProperty, null);
            longEdgeDummy = nextElement.apply(longEdgeDummy);
        }
    }
    
}
