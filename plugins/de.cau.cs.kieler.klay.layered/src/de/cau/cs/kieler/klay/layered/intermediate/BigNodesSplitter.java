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
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * This processor splits bignode, i.e. nodes that are wider than most of the diagram's nodes. The
 * processor is executed after crossing minimization restricting its power to a small number of
 * scenarios, as described below. However, since the crossing minimization is finished it can
 * guarantee not to introduce any node-edge crossings. This would not be possible otherwise.
 * 
 * 
 * <h2>BigNode With Incoming Long Edge</h2>
 * <p>
 *      We replace as many long edge dummies in front of the big node as possible. The long edge dummies
 *      are only allowed to have exactly one incoming and one outgoing edge, otherwise we leave them as
 *      they are. Depending on the number of dummies we could replace we distribute the original size of
 *      the bignode among the newly created bignode dummies. Special care has to be taken with relation
 *      to the port's positions.
 * </p>
 * 
 * 
 * <h2>BigNode With Outgoing Long Edge</h2>
 * <p>
 *      We replace as many long edge dummies following the big node as possible. All dummies are 
 *      only allowed to have exactly on incoming and outgoing edge. Depending on the number of 
 *      replaced dummies we adapt the width of each replaced dummy to be a fraction of the
 *      original bignode's width. 
 * </p>
 * 
 * 
 * <h2>BigNode Without Outgoing Edge</h2>
 * <p>
 *      We add new bignode dummies in the consecutive layers of the original bignode. While doing this,
 *      we assure that the dummies are placed at a position within a consecutive layer such that
 *      no node-edge crossing is introduced. If this is not possible, we retain the original bignode 
 *      without splitting it. The original number of layers is not increased, i.e. a bignode in the
 *      right-most layer will never be split. 
 * </p>
 * 
 * <h3>Remarks</h3>
 * <p>
 *      Care has to be taken with nodes of the type {@link NodeType#NORTH_SOUTH_PORT}. These are not
 *      connected by edges but are kept edge crossing free by constraints. Hence we are not allowed to
 *      place bignode dummies between such a node and its corresponding normal node.
 * </p>
 * 
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>The crossing minimization phase is finished.</dd>
 *     <dd>LongEdge dummies are merged where possible.</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>Bignodes are split where possible.</dd>
 *   <dt>Slots:</dt>
 *     <dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>After {@link HyperedgeDummyMerger}</dd>
 *     <dd>Before {@link LabelAndNodeSizeProcessor}</dd>
 *     <dd>Care about the results of the {@link InLayerConstraintProcessor}.</dd>
 * </dl>
 * 
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
                bigNodes.add(new BigNode(node, parts));
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
        }
        
        // we don't support self-loops
        for (LEdge edge : node.node.getOutgoingEdges()) {
            if (edge.getSource().getNode().equals(edge.getTarget().getNode())) {
                return false;
            }
        }

        // edges that are connected to the left side of the node
        Iterable<LEdge> westwardEdges;
        // likewise edges connected to the right side of the node
        Iterable<LEdge> eastwardEdges;

        if (node.node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
            List<Iterable<LEdge>> tmp = Lists.newArrayList();
            for (LPort p : node.node.getPorts(PortSide.WEST)) {
                tmp.add(p.getConnectedEdges());
            }
            westwardEdges = Iterables.concat(tmp);

            tmp = Lists.newArrayList();
            for (LPort p : node.node.getPorts(PortSide.EAST)) {
                tmp.add(p.getConnectedEdges());
            }
            eastwardEdges = Iterables.concat(tmp);
        } else {
            // ports are free, thus ports are moved to appropriate sides
            westwardEdges = node.node.getIncomingEdges();
            eastwardEdges = node.node.getOutgoingEdges();
        }
        
        // the node has to be connected
        if (Iterables.isEmpty(node.node.getOutgoingEdges())
                && Iterables.isEmpty(node.node.getIncomingEdges())) {
            return false;
        }

        // or no outgoing edge
        if (Iterables.isEmpty(node.node.getOutgoingEdges())) {
            node.type = BigNodeType.NO_OUTGOING;
            return true;
        }

        // either exactly one incoming edge that originates from a long edge dummy
        if (Iterables.size(westwardEdges) == 1) {
            LNode source = Iterables.get(westwardEdges, 0).getSource().getNode();
            if (source.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
            // and the long edge dummy does not represent a self loop
                    && !source.getProperty(InternalProperties.LONG_EDGE_SOURCE).getNode()
                            .equals(node.node)) {
                node.type = BigNodeType.INC_LONG_EDGE;
                return true;
            }
        }

        // on outgoing edge analog
        if (Iterables.size(eastwardEdges) == 1) {
            LNode target = Iterables.get(eastwardEdges, 0).getTarget().getNode();
            if (target.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                    && !target.getProperty(InternalProperties.LONG_EDGE_TARGET).getNode()
                            .equals(node.node)) {
                node.type = BigNodeType.OUT_LONG_EDGE;
                return true;
            }
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
        public BigNodeType type = BigNodeType.INVALID; // SUPPRESS CHECKSTYLE VisibilityModifier

        /** The dummy nodes created for this big node (include the node itself at index 0). */
        private ArrayList<LNode> dummies = Lists.newArrayList();

        /**
         * Creates a new big node.
         */
        public BigNode(final LNode node, final int chunks) {
            this.node = node;
            this.chunks = chunks;
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
            node.setProperty(InternalProperties.BIG_NODE_ORIGINAL_SIZE, (float) node.getSize().x);
            node.setProperty(InternalProperties.BIG_NODE_INITIAL, true);

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
                
                processIncLongEdge(node, node.getSize().x);
                
            }

        }

        /*------------------------------------------------------------------------------------------
         *                      Big Node with an incoming Long Edge. 
         *------------------------------------------------------------------------------------------
         */

        private void processIncLongEdge(final LNode bignode, final double originalWidth) {

            // remember all nodes we create to adapt the size later on
            List<LNode> chainOfNodes = Lists.newLinkedList();
            chainOfNodes.add(bignode);

            // create the dummies
            LNode start = bignode;
            do {
                start = swapIncLongEdgeDummy(start);
                if (start != null) {
                    chainOfNodes.add(start);
                }
            } while (start != null);

            // assign a width to the nodes of the big node chain, care about spacing
            double newWidth =
                    (originalWidth - (chainOfNodes.size() - 1) * spacing)
                            / (double) chainOfNodes.size();
            for (LNode d : chainOfNodes) {
                d.getSize().x = newWidth;
            }
        }
        
        private LNode swapIncLongEdgeDummy(final LNode start) {
            
            // we require exactly one incoming edge
            if (Iterables.size(start.getIncomingEdges()) != 1
                    || Iterables.get(start.getIncomingEdges(), 0).getSource().getNode()
                            .getProperty(Properties.NODE_TYPE) != NodeType.LONG_EDGE) {
                return null;
            }
            
            // get the dummy
            LEdge incEdge = Iterables.get(start.getIncomingEdges(), 0);
            LNode longEdgeDummy = incEdge.getSource().getNode();
            
            // the longedge dummy becomes the new initial bignode
            longEdgeDummy.setProperty(Properties.LONG_EDGE_SOURCE, null);
            longEdgeDummy.setProperty(Properties.LONG_EDGE_TARGET, null);
            
            longEdgeDummy.setProperty(InternalProperties.BIG_NODE_ORIGINAL_SIZE,
                    (float) start.getSize().x);
            longEdgeDummy.setProperty(InternalProperties.BIG_NODE_INITIAL, true);
            longEdgeDummy.setProperty(Properties.NODE_TYPE, NodeType.NORMAL);
            longEdgeDummy.setProperty(Properties.ORIGIN, start.getProperty(Properties.ORIGIN));
            
            // adapt height
            longEdgeDummy.getSize().y = start.getSize().y;

            // the EAST ports can stay where they are, we have to adjust WEST ports here

            // adapt the origin
            // Remark: we allow the big node to have an arbitrary amount of
            // ports on the EAST side, however, only one of them is allowed
            // to have an outgoing edge
            Object origin = incEdge.getTarget().getProperty(Properties.ORIGIN);
            LPort outPort = null;
            for (LPort p : longEdgeDummy.getPorts(PortSide.WEST)) {
                if (!p.getIncomingEdges().isEmpty()) {
                    p.setProperty(Properties.ORIGIN, origin);

                    LPort tgt = incEdge.getTarget();
                    p.getSize().x = tgt.getSize().x;
                    p.getSize().y = tgt.getSize().y;
                    p.getAnchor().x = tgt.getAnchor().x;
                    p.getAnchor().y = tgt.getAnchor().y;
                    
                    // copy the labels of the port
                    p.getLabels().addAll(tgt.getLabels());
                    tgt.getLabels().clear();

                    outPort = p;
                    break;
                }
            }
            incEdge.getTarget().setProperty(Properties.ORIGIN, null);

            // if the big node has multiple EAST ports, reassemble this on the former long edge
            // dummy
            if (Iterables.size(start.getPorts(PortSide.WEST)) > 1) {
                // the port list is sorted here!
                for (LPort p : Lists.newLinkedList(start.getPorts(PortSide.WEST))) {
                    if (p.getIncomingEdges().isEmpty()) {

                        LPort newPort = new LPort(layeredGraph);
                        newPort.setSide(PortSide.WEST);
                        newPort.getSize().x = p.getSize().x;
                        newPort.getSize().y = p.getSize().y;
                        newPort.setNode(longEdgeDummy);
                        newPort.setProperty(Properties.ORIGIN, p.getProperty(Properties.ORIGIN));

                        p.setNode(null);
                    } else {
                        // re-add the original port to retain order
                        outPort.setNode(longEdgeDummy);
                    }
                }
            }
            
            // the original big node becomes a dummy
            start.setProperty(Properties.ORIGIN, null);
            start.setProperty(InternalProperties.BIG_NODE_INITIAL, false);
            start.setProperty(Properties.NODE_TYPE, NodeType.BIG_NODE);
            
            longEdgeDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                    start.getProperty(LayoutOptions.PORT_CONSTRAINTS));
            longEdgeDummy.setProperty(LayoutOptions.NODE_LABEL_PLACEMENT,
                    start.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT));
            
            
            return longEdgeDummy;
        }
        
        /*------------------------------------------------------------------------------------------
         *                      Big Node with an outgoing Long Edge. 
         *------------------------------------------------------------------------------------------
         */

        private void processOutLongEdge(final LNode bignode, final double originalWidth) {

            // remember all nodes we create to adapt the size lateron
            List<LNode> chainOfNodes = Lists.newLinkedList();
            chainOfNodes.add(bignode);

            // create dummies
            LNode start = bignode;
            do {
                start = replaceOutLongEdgeDummy(start);
                if (start != null) {
                    chainOfNodes.add(start);
                }
            } while (start != null);

            // assign a width to the nodes of the big node chain, care about spacing
            double newWidth =
                    (originalWidth - (chainOfNodes.size() - 1) * spacing)
                            / (double) chainOfNodes.size();
            for (LNode d : chainOfNodes) {
                d.getSize().x = newWidth;
            }

        }

        private LNode replaceOutLongEdgeDummy(final LNode start) {

            if (Iterables.size(start.getOutgoingEdges()) != 1
                    || Iterables.get(start.getOutgoingEdges(), 0).getTarget().getNode()
                            .getProperty(Properties.NODE_TYPE) != NodeType.LONG_EDGE) {
                return null;
            }

            // get the dummy
            LEdge outEdge = Iterables.get(start.getOutgoingEdges(), 0);
            LNode longEdgeDummy = outEdge.getTarget().getNode();

            // tell it to be a big node dummy now
            longEdgeDummy.setProperty(Properties.LONG_EDGE_SOURCE, null);
            longEdgeDummy.setProperty(Properties.LONG_EDGE_TARGET, null);
            longEdgeDummy.setProperty(Properties.NODE_TYPE, NodeType.BIG_NODE);

            // copy some properties
            longEdgeDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                    start.getProperty(LayoutOptions.PORT_CONSTRAINTS));
            longEdgeDummy.setProperty(LayoutOptions.NODE_LABEL_PLACEMENT,
                    start.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT));

            // adapt the origin
            // Remark: we allow the big node to have an arbitrary amount of
            // ports on the EAST side, however, only one of them is allowed
            // to have an outgoing edge
            Object origin = outEdge.getSource().getProperty(Properties.ORIGIN);
            LPort outPort = null;
            for (LPort p : longEdgeDummy.getPorts(PortSide.EAST)) {
                if (!p.getOutgoingEdges().isEmpty()) {
                    p.setProperty(Properties.ORIGIN, origin);

                    LPort src = outEdge.getSource();
                    p.getSize().x = src.getSize().x;
                    p.getSize().y = src.getSize().y;
                    p.getAnchor().x = src.getAnchor().x;
                    p.getAnchor().y = src.getAnchor().y;
                    
                    // copy the labels of the port
                    p.getLabels().addAll(src.getLabels());
                    src.getLabels().clear();

                    outPort = p;
                    break;
                }
            }
            outEdge.getSource().setProperty(Properties.ORIGIN, null);

            // if the big node has multiple EAST ports, reassemble this on the former long edge
            // dummy
            if (!Iterables.isEmpty(start.getPorts(PortSide.EAST))) {
                // the port list is sorted here!
                for (LPort p : Lists.newArrayList(start.getPorts(PortSide.EAST))) {
                    if (p.getOutgoingEdges().isEmpty()) {

                        LPort newPort = new LPort(layeredGraph);
                        newPort.setSide(PortSide.EAST);
                        newPort.getSize().x = p.getSize().x;
                        newPort.getSize().y = p.getSize().y;
                        newPort.setNode(longEdgeDummy);
                        newPort.setProperty(Properties.ORIGIN, p.getProperty(Properties.ORIGIN));

                        p.setNode(null);
                    } else {
                        // re-add the original port to retain order
                        outPort.setNode(longEdgeDummy);
                    }
                }
            }

            // adjust height
            longEdgeDummy.getSize().y = start.getSize().y;

            dummies.add(longEdgeDummy);

            return longEdgeDummy;
        }
        
        /*------------------------------------------------------------------------------------------
         *                      Big Node without outgoing edges. 
         *------------------------------------------------------------------------------------------
         */

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

            // copy variables to make them mutable
            LNode start = bignode;          
            int currentLayer = startLayerIndex;
            
            // check if we can split the big node without introducing node edge overlaps
            int inLayerPos = -1;
            Layer currentLayerLayer = layeredGraph.getLayers().get(currentLayer);
            for (int i = 0; i <  currentLayerLayer.getNodes().size(); ++i) {
                LNode n = currentLayerLayer.getNodes().get(i);
                if (n.equals(start)) {
                    inLayerPos = i;
                    break;
                }
            }

            List<Integer> inLayerPositions =
                    canCreateEndDummies(inLayerPos, currentLayer + 1, maxLayer, chunks, true);
            if (inLayerPositions == null) {
                // no valid positioning could be found
                return;
            }

            // create at most 'chunks' nodes
            int tmpChunks = chunks;
            int i = 0;
            while (start != null && tmpChunks > 1 && currentLayer < maxLayer - 1) {
                // create the dummy
                LNode dummy = introduceDummyNode(start, 0);

                // get layer
                Layer dummyLayer = layeredGraph.getLayers().get(currentLayer + 1);

                int upperStrokeMax = inLayerPositions.get(i++);
                dummy.setLayer(Math.min(upperStrokeMax, dummyLayer.getNodes().size()), dummyLayer);

                if (start != null) {
                    chainOfNodes.add(start);
                }

                start = dummy;
                tmpChunks--;
                // each chunk implicitly covers one spacing as well
                currentLayer++;
            }

            // assign a width to the nodes of the big node chain, care about spacing
            double newWidth =
                    (originalWidth - (chainOfNodes.size() - 1) * spacing)
                            / (double) chainOfNodes.size();
            for (LNode d : chainOfNodes) {
                d.getSize().x = newWidth;
            }
        }

        /**
         * Checks if we can create enough valid dummy nodes without introducing node edge crossings.
         * 
         * @param inLayerPos
         *            the vertical position within the current layer we wanna check
         * @param layerIndex
         *            the current layer we wanna check
         * @param maxLayer
         *            last layer of the diagram
         * @param remainingChunks
         *            the number of dummy nodes we still have to create
         * @param initial
         *            is this the first layer we check
         * @return either a list containing in layer positions for the dummy nodes to be created or
         *         null if no valid positioning could be found.
         */
        private List<Integer> canCreateEndDummies(final int inLayerPos, final int layerIndex,
                final int maxLayer, final int remainingChunks, final boolean initial) {

            // current layer
            Layer layer = layeredGraph.getLayers().get(layerIndex - 1);

            Set<LNode> upper = Sets.newHashSet();
            Set<LNode> lower = Sets.newHashSet();

            for (int i = 0; i < layer.getNodes().size(); ++i) {
                LNode n = layer.getNodes().get(i);
                
                if (i < inLayerPos) {
                    upper.add(n);
                } else if (i > inLayerPos) {
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

            // System.out.println("\n" + node);
            // System.out.println("Upper: " + upper);
            // System.out.println("Lower: " + lower);
            // System.out.println("UpperStroke: " + upperStroke);
            // System.out.println("LowerStroke: " + lowerStroke);
            
            // find min and max
            Layer rightLayer = layeredGraph.getLayers().get(layerIndex);
            int maxUprime = Integer.MIN_VALUE;
            int minLprime = Integer.MAX_VALUE;
            for (int i = 0; i < rightLayer.getNodes().size(); i++) {
                LNode n = rightLayer.getNodes().get(i);
                if (upperStroke.contains(n)) {
                   maxUprime = Math.max(maxUprime, i); 
                } else if (lowerStroke.contains(n)) {
                    minLprime = Math.min(minLprime, i);
                }
            }

            // it's ok if the intersection is empty
            //if (Sets.intersection(upperStroke, lowerStroke).isEmpty()) {
            if (maxUprime < minLprime) {

                // we also require
                // no inlayer edges spanning from upper to lower
                for (LNode n : upperStroke) {
                    for (LEdge e : n.getOutgoingEdges()) {
                        if (n.getLayer().getIndex() == e.getTarget().getNode().getLayer()
                                .getIndex()) {
                            return null;
                        }
                    }
                    for (LEdge e : n.getIncomingEdges()) {
                        if (n.getLayer().getIndex() == e.getSource().getNode().getLayer()
                                .getIndex()) {
                            return null;
                        }
                    }
                }

                // or lower to upper
                for (LNode n : lowerStroke) {
                    for (LEdge e : n.getOutgoingEdges()) {
                        if (n.getLayer().getIndex() == e.getTarget().getNode().getLayer()
                                .getIndex()) {
                            System.out.println("Inlayer Edge out");
                            return null;
                        }
                    }
                    for (LEdge e : n.getIncomingEdges()) {
                        if (n.getLayer().getIndex() == e.getSource().getNode().getLayer()
                                .getIndex()) {
                            System.out.println("Inlayer Edge in");
                            return null;
                        }
                    }
                }

                // get layer
                Layer dummyLayer = layeredGraph.getLayers().get(layerIndex);
                int upperStrokeMax = -1; // -1, to differ between '0 pos' and 'not found'
                

                if (upper.isEmpty()) {
                    upperStrokeMax = 0;
                } else if (lower.isEmpty()) {
                    // if we only have an upper set, place it at the bottom of the dummy layer
                    upperStrokeMax = dummyLayer.getNodes().size();
                } else {
                    // find the proper position
                    upperStrokeMax = maxUprime + 1;
                }
                
                // we also require
                // that no node groups are broken ...
                // FIXME this is too restrictive!
                // It would be better if an interleaving exists.
                for (LNode n : layer.getNodes()) {
                    if (n.getProperty(Properties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT) {
                        return null;
                    }
                }
                
                // return our current knowledge
                if (remainingChunks == 1) {
                    // we created all chunks we need
                    return Lists.newArrayList(upperStrokeMax);
                } else if (layerIndex == maxLayer - 1) {
                    // we reached the end of the diagram
                    return Lists.newArrayList(upperStrokeMax);
                } else {
                    List<Integer> rec =
                            canCreateEndDummies(upperStrokeMax, layerIndex + 1, maxLayer,
                                    remainingChunks - 1, false);
                    if (rec != null) {
                        rec.add(upperStrokeMax);
                    }
                    return rec;
                }

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
            
            
            // move any EAST ports of the current src node
            List<LPort> eastPorts = Lists.newArrayList(src.getPorts(PortSide.EAST));
            for (LPort p : eastPorts) {
                p.setNode(dummy);
            }
            

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
