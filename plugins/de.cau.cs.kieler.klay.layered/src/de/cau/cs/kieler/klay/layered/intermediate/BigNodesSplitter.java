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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * TODO.
 * 
 * Precondition: The crossing minimization phase is finished.
 * 
 * Applicability:
 * 
 * Same slot dependency:
 * 
 * AFTER : HyperedgeDummyMerger has to be finished TODO check if we break the
 * InLayerConstraintProcessor as we are executed afterwards. BEFORE : LabelAndNodeSizeCalculator
 * 
 * We have to take care that we only split nodes if no node edge crossing would be introduce. This
 * involves checking for each big node for interleaving edges
 * 
 * 
 *  * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>The crossing minimization phase is finished.</dd>
 *     <dd>LongEdge dummies are merged where possible.</dd>
 *   <dt>Postcondition:</dt>
 *      <dd>Bignodes are split where possible.</dd>
 *   <dt>Slots:</dt>
 *     <dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>After {@link HyperedgeDummyMerger}</dd>
 *     <dd>Before {@link LabelAndNodeSizeProcessor}</dd>
 * </dl>
 * 
 * 
 * FIXME ports in post processing ....
 * 
 * @see BigNodesPostProcessor
 * 
 * @author uru
 */
public class BigNodesSplitter implements ILayoutProcessor {

    /**
     * Describes the type of a certain bignode. 
     */
    private enum BigNodeType {
        NO_OUTGOING, INC_LONG_EDGE, INVALID, OUT_LONG_EDGE
    }

    /**
     * Minimal width into which nodes are split, the smaller this value, the more big node dummy
     * nodes are possibly introduced.
     */
    private static final double MIN_WIDTH = 50;
    /** The current graph. */
    private LGraph layeredGraph;
    /** Used to assign ids to newly created dummy nodes. */
    private int dummyID = 0;
    /** Currently used node spacing. */
    private double spacing = 0;
    /** Current layout direction. */
    private Direction direction = Direction.UNDEFINED;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph theLayeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Big nodes pre-processing", 1);

        this.layeredGraph = theLayeredGraph;
        // List<LNode> nodes = layeredGraph.getLayerlessNodes();
        List<LNode> nodes = Lists.newLinkedList();
        for (Layer l : theLayeredGraph.getLayers()) {
            nodes.addAll(l.getNodes());
        }

        // re-index nodes
        int counter = 0;
        for (LNode node : nodes) {
            node.id = counter++;
        }

        // the object spacing in the drawn graph
        spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        direction = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        // the ID for the most recently created dummy node
        dummyID = nodes.size();

        // Compute width of narrowest node
        double minWidth = Float.MAX_VALUE;
        for (LNode node : nodes) {
            // ignore all dummy nodes
            if ((node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL)
                    && (node.getSize().x < minWidth)) {
                minWidth = node.getSize().x;
            }
        }

        // assure a capped minimal width
        minWidth = Math.max(MIN_WIDTH, minWidth);

        // collect all nodes that are considered "big"
        List<BigNode> bigNodes = Lists.newLinkedList();
        double threshold = (minWidth + spacing);
        for (LNode node : nodes) {
            if ((node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL)
                    && (node.getSize().x > threshold)) {
                // when splitting, consider that we can use the spacing area
                // we try to find a node width that considers the spacing
                // for every dummy node to be created despite the last one
                int parts = 1;
                double chunkWidth = node.getSize().x;
                while (chunkWidth > minWidth) {
                    parts++;
                    chunkWidth = (node.getSize().x - (parts - 1) * spacing) / (double) parts;
                }

                // new
                bigNodes.add(new BigNode(node, parts, chunkWidth));
            }
        }

        // handle each big node
        for (BigNode node : bigNodes) {
            // is this big node ok?
            if (isProcessorApplicable(node)) {
                node.process();
            }
        }

        monitor.done();
    }

    /**
     * Only handle nodes with {@link PortConstraints} <= {@link PortConstraints#FIXED_ORDER}, or for
     * greater port constraints we demand that the node has no NORTH and SOUTH ports.
     * 
     * Also, we do not support self-loops at the moment.
     * 
     * @param node
     * @return true if we can apply big nodes processing
     */
    private boolean isProcessorApplicable(final BigNode node) {

        if (node.node.getProperty(LayoutOptions.PORT_CONSTRAINTS) == PortConstraints.FIXED_RATIO
                || node.node.getProperty(LayoutOptions.PORT_CONSTRAINTS) == PortConstraints.FIXED_POS) {
            for (LPort port : node.node.getPorts()) {
                if (port.getSide() == PortSide.NORTH || port.getSide() == PortSide.SOUTH) {
                    return false;
                }
            }
            // we don't support self-loops
            for (LEdge edge : node.node.getOutgoingEdges()) {
                if (edge.getSource().getNode().equals(edge.getTarget().getNode())) {
                    return false;
                }
            }
        }

        Iterable<LEdge> incomingEdges = node.node.getIncomingEdges();
        Iterable<LEdge> outgoingEdges = node.node.getOutgoingEdges();

        // the node has to be connected
        if (Iterables.isEmpty(node.node.getOutgoingEdges())
                && Iterables.isEmpty(node.node.getIncomingEdges())) {
            return false;
        }

        // or no outgoing edge
        if (Iterables.isEmpty(node.node.getOutgoingEdges())) {
            System.out.println("No outgoing");
            node.type = BigNodeType.NO_OUTGOING;
            return true;
        }

        // either exactly one incoming edge that originates from a long edge dummy
        if (Iterables.size(incomingEdges) == 1
                && Iterables.get(incomingEdges, 0).getSource().getNode()
                        .getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            System.out.println("Incoming long edge");
            node.type = BigNodeType.INC_LONG_EDGE;
            return true;
        }

        // on outgoing edge analog
        if (Iterables.size(outgoingEdges) == 1
                && Iterables.get(outgoingEdges, 0).getTarget().getNode()
                        .getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            System.out.println("Outgoing long edge");
            node.type = BigNodeType.OUT_LONG_EDGE;
            return true;
        }

        return false;
    }

    /**
     * Internal representation of a big node.
     * 
     * @author uru
     */
    private class BigNode {

        private LNode node;
        private int chunks;
        private double minWidth;
        public BigNodeType type = BigNodeType.INVALID; // SUPPRESS CHECKSTYLE VisibilityModifier

        /** The dummy nodes created for this big node (include the node itself at index 0). */
        private ArrayList<LNode> dummies = Lists.newArrayList();

        /**
         * Creates a new big node.
         */
        public BigNode(final LNode node, final int chunks, final double minWidth) {
            this.node = node;
            this.chunks = chunks;
            this.minWidth = minWidth;
        }

        /**
         * Main entry point for big node processing.
         * 
         * - splits the big node into consecutive dummy nodes - handles labels
         * 
         */
        public void process() {

            // remember east ports
            LinkedList<LPort> eastPorts = new LinkedList<LPort>();
            for (LPort port : node.getPorts()) {
                if (port.getSide() == PortSide.EAST) {
                    eastPorts.add(port);
                }
            }

            // if ports are free to be moved on the sides, we have to move all outgoing edges as
            // well as these will be assigned to the east side later
            if (direction == Direction.RIGHT
                    && !node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                for (LEdge e : node.getOutgoingEdges()) {
                    eastPorts.add(e.getSource());
                }
            }

            // remember original size to restore it later
            node.setProperty(Properties.BIG_NODE_ORIGINAL_SIZE, (float) node.getSize().x);
            node.setProperty(Properties.BIG_NODE_INITIAL, true);

            // we consider the first node as dummy as well, even though we do not mark it
            dummies.add(node);

            /*
             * Handle depending on the big node type
             */
            if (type == BigNodeType.NO_OUTGOING) {

                processNoOutgoingEdge(node, node.getLayer().getIndex(), node.getSize().x);

            } else if (type == BigNodeType.OUT_LONG_EDGE) {

                processOutLongEdge(node, node.getSize().x);
                
            } else if (type == BigNodeType.INC_LONG_EDGE) {
                
                processIncLongEdge(node);
                
            }

        }

        private void processNoOutgoingEdge(final LNode bignode, final int startLayerIndex,
                final double originalWidth) {

            int maxLayer = layeredGraph.getLayers().size();
            if (startLayerIndex >= maxLayer - 1) {
                // there are no more layers, we cannot create dummies
                return;
            }

            // remember all nodes we create to adapt the size lateron
            List<LNode> chainOfNodes = Lists.newLinkedList();
            chainOfNodes.add(bignode);

            LNode start = bignode;
            // create first dummy
            start = createEndDummies(start, minWidth, startLayerIndex + 1);
            if (start != null) {
                chainOfNodes.add(start);
            }
            int currentLayer = startLayerIndex + 1;

            // create at most 'chunks' nodes
            int tmpChunks = chunks;
            while (start != null && tmpChunks > 1 && currentLayer < maxLayer - 1) {
                // create it and add dummy to the graph
                start = createEndDummies(start, 0, currentLayer + 1);
                if (start != null) {
                    chainOfNodes.add(start);
                }
                tmpChunks--;
                // each chunk implicitly covers one spacing as well
                currentLayer++;
            }

            // we have to adapt the size depending on the number of dummies we were able to
            // create
            // TODO we should consider spacing here
            double newWidth = originalWidth / (double) chainOfNodes.size();
            for (LNode d : chainOfNodes) {
                d.getSize().x = newWidth;
            }
        }

        private void processOutLongEdge(final LNode bignode, final double originalWidth) {

            // remember all nodes we create to adapt the size lateron
            List<LNode> chainOfNodes = Lists.newLinkedList();
            chainOfNodes.add(bignode);

            LNode start = bignode;

            do {
                start = replaceOutLongEdgeDummy(start, minWidth);
                if (start != null) {
                    chainOfNodes.add(start);
                }
            } while (start != null);

            // TODO consider spacing
            double newWidth = originalWidth / (double) chainOfNodes.size();
            for (LNode d : dummies) {
                d.getSize().x = newWidth;
            }

            // TODO we have to handle the east port properly
            // 
            
            // add the east ports to the final dummy
            // for (LPort port : eastPorts) {
            // node.getPorts().remove(port);
            // port.setNode(prevDummy);
            // }
        }

        private void processIncLongEdge(final LNode start) {

            // TODO
            // swap the big node and the long edge dummy as long as possible
            
        }

        private LNode replaceOutLongEdgeDummy(final LNode start, final double width) {

            if (Iterables.size(start.getOutgoingEdges()) != 1
                    || Iterables.get(start.getOutgoingEdges(), 0).getTarget().getNode()
                            .getProperty(Properties.NODE_TYPE) != NodeType.LONG_EDGE) {
                return null;
            }

            // get the dummy
            LNode longEdgeDummy = Iterables.get(start.getOutgoingEdges(), 0).getTarget().getNode();

            // tell it to be a big node dummy now
            longEdgeDummy.setProperty(Properties.NODE_TYPE, NodeType.BIG_NODE);

            // copy some properties
            longEdgeDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                    start.getProperty(LayoutOptions.PORT_CONSTRAINTS));
            longEdgeDummy.setProperty(LayoutOptions.NODE_LABEL_PLACEMENT,
                    start.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT));

            // adjust size
            longEdgeDummy.getSize().x = width;
            longEdgeDummy.getSize().y = start.getSize().y;

            dummies.add(longEdgeDummy);

            return longEdgeDummy;
        }

        /**
         * Method is used for big nodes that have no outgoing edge.
         * 
         * It is checked if we can split the node further without introducing a node/edge crossing.
         * If this is the case, a new dummy is created and placed in the consecutive layer at a
         * proper place.
         * 
         * 
         * @param start
         * @param width
         * @param layerIndex
         *            the layer into which the dummy should be placed.
         * @param prevInlayerNode
         * 
         * @return a dummy node if one was created, null else
         */
        private LNode createEndDummies(final LNode start, final double width, final int layerIndex) {

            // current layer
            Layer layer = layeredGraph.getLayers().get(layerIndex - 1);

            int startPos = -1;
            Set<LNode> upper = Sets.newHashSet();
            Set<LNode> lower = Sets.newHashSet();

            for (int i = 0; i < layer.getNodes().size(); ++i) {
                LNode n = layer.getNodes().get(i);
                if (n.equals(start)) {
                    startPos = i;
                    continue;
                }

                if (startPos == -1) {
                    upper.add(n);
                } else {
                    lower.add(n);
                }
            }

            Set<LNode> upperStroke = Sets.newHashSet();
            Set<LNode> lowerStroke = Sets.newHashSet();
            for (LNode n : upper) {
                for (LEdge e : n.getOutgoingEdges()) {
                    // no inlayer edges
                    if (n.getLayer().getIndex() != e.getTarget().getNode().getLayer().getIndex()) {
                        upperStroke.add(e.getTarget().getNode());
                    }
                }
            }
            for (LNode n : lower) {
                for (LEdge e : n.getOutgoingEdges()) {
                    // no inlayer edge
                    if (n.getLayer().getIndex() != e.getTarget().getNode().getLayer().getIndex()) {
                        lowerStroke.add(e.getTarget().getNode());
                    }
                }
            }

            System.out.println("\n" + node);
            System.out.println("Upper: " + upper);
            System.out.println("Lower: " + lower);
            System.out.println("UpperStroke: " + upperStroke);
            System.out.println("LowerStroke: " + lowerStroke);

            // it's ok if the intersection is empty
            if (Sets.intersection(upperStroke, lowerStroke).isEmpty()) {

                // we also require
                // no inlayer edges spanning from upper to lower
                for (LNode n : upperStroke) {
                    for (LEdge e : n.getOutgoingEdges()) {
                        // if (lowerStroke.contains(e.getTarget().getNode())) {
                        // return null;
                        // }
                        if (n.getLayer().getIndex() == e.getTarget().getNode().getLayer()
                                .getIndex()) {
                            return null;
                        }
                    }
                    for (LEdge e : n.getIncomingEdges()) {
                        // if (lowerStroke.contains(e.getSource().getNode())) {
                        // return null;
                        // }

                        if (n.getLayer().getIndex() == e.getSource().getNode().getLayer()
                                .getIndex()) {
                            return null;
                        }
                    }
                }

                // create the dummy
                LNode dummy = introduceDummyNode(start, width);

                // get layer
                Layer dummyLayer = layeredGraph.getLayers().get(layerIndex);

                // TODO really true? cant it be that the upper is completely located below the lower
                // position is the maximum of the upperStroke set plus one
                int upperStrokeMax = 0;
                for (LNode n : upperStroke) {
                    upperStrokeMax = Math.max(upperStrokeMax, dummyLayer.getNodes().indexOf(n));
                }

                // dummyLayer.getNodes().add(upperStrokeMax + 1, dummy);
                dummy.setLayer(upperStrokeMax + 1, dummyLayer);

                return dummy;
            }

            return null;
        }

        /**
         * Creates a new dummy node of the specified width.
         * 
         * @return the created dummy.
         */
        private LNode introduceDummyNode(final LNode src, final double width) {
            // create new dummy node
            LNode dummy = new LNode(layeredGraph);
            dummy.setProperty(Properties.NODE_TYPE, NodeType.BIG_NODE);

            // copy some properties
            dummy.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                    src.getProperty(LayoutOptions.PORT_CONSTRAINTS));
            dummy.setProperty(LayoutOptions.NODE_LABEL_PLACEMENT,
                    src.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT));

            dummy.id = dummyID++;

            // remember
            dummies.add(dummy);

            // set same height as original
            dummy.getSize().y = src.getSize().y;
            // the first n-1 nodes (initial+dummies) are assigned a width of 'minWidth'
            // while the last node (right most) is assigned the remaining
            // width of the bignode, i.e.
            // overallWidth - (n-1) * minWidth
            dummy.getSize().x = width;

            // add ports to connect it with the previous node
            LPort outPort = new LPort(layeredGraph);
            outPort.setSide(PortSide.EAST);
            outPort.setNode(src);
            // assign reasonable positions to the port in case of FIXES_POS
            outPort.getPosition().x = dummy.getSize().x;
            outPort.getPosition().y = dummy.getSize().y / 2;

            LPort inPort = new LPort(layeredGraph);
            inPort.setSide(PortSide.WEST);
            inPort.setNode(dummy);
            inPort.getPosition().y = dummy.getSize().y / 2;
            inPort.getPosition().x = -inPort.getSize().x;

            // add edge to connect it with the previous node
            LEdge edge = new LEdge(layeredGraph);
            edge.setSource(outPort);
            edge.setTarget(inPort);

            return dummy;
        }

    }

}
