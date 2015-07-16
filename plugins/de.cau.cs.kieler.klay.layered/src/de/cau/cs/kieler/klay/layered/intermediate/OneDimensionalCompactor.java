/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator.HyperNode;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * This processor applies additional horizontal compaction to an already routed graph and can be
 * executed after {@link OrthogonalEdgeRouter}. Therefore nodes and vertical segments of edges are
 * repositioned in only the horizontal direction where the position is leftmost considering the
 * desired spacing between elements.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>The edges are routed orthogonally with at most two vertical segments per edge.</dd>
 * <dd>Edges do not connect to north or south ports.</dd>
 * <dd>The graph does not contain {@link HyperNode}s.</dd>
 * <dt>Postcondition:</dt>
 * <dd>Nodes and edges are positioned leftmost without colliding.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 5.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>After {@link LabelDummyRemover}</dd>
 * <dd>Before {@link ReversedEdgeRestorer}</dd>
 * </dl>
 * 
 * @author dag
 */
public class OneDimensionalCompactor implements ILayoutProcessor {

    private static final double TOLERANCE = 0.0001; // TODO ????????

    private List<CNode> nodes = Lists.newArrayList();

    private double objSpacing, edgeSpacing;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Compacting horizontally", 1);

        objSpacing = (double) layeredGraph.getProperty(InternalProperties.SPACING);
        edgeSpacing =
                layeredGraph.getProperty(InternalProperties.SPACING)
                        * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);

        // 1. 2.
        readNodes(layeredGraph);

        // TODO test
        for (CNode cNode : nodes) {
            if (cNode.isNode && cNode.lNode.getName() != null
                    && cNode.lNode.getName().contains("lock")) { // TODO null if nested stuff??
                cNode.reposition = false;
            }
        }

        // 3. 4.
        getConstraints((a, b) -> a > b);

        for (CNode node : nodes) {
            System.out.println(node + "    " + node.outDegree + "  " + node.incoming
                    + node.pendingNSSegments);
        }

        compactLeft();
        setNodePositions(nodes);
        lockNodes();
        // nodes.clear();
        // readNodes(layeredGraph); // needed for resetting outDegree and incoming
        // getConstraints((a, b) -> a < b);
        reverseConstraints();

        // for (CNode node : nodes) {
        // System.out.println(node + "    " + node.outDegree + "  " + node.incoming);
        // }

        // compactRight();
        // setNodePositions(nodes);

        nodes.clear();
        progressMonitor.done();
    }

    /**
     * TODO
     * 
     * @param layeredGraph
     * @return
     */
    private void readNodes(final LGraph layeredGraph) {
        List<VerticalSegment> verticalSegments = Lists.newArrayList();

        // 1. collecting positions of graph elements
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                CNode cNode = null;
                // add all nodes of type NORMAL because dummy nodes shouldn't have any size or
                // spacings TODO?
                // at this point there shouldn't even be any dummy nodes?
                if (node.getNodeType().equals(NodeType.NORMAL)) {
                    // hitbox excluding object spacing
                    Rectangle r =
                            new Rectangle(node.getPosition().x, node.getPosition().y,
                                    node.getSize().x, node.getSize().y);
                    cNode = new CNode(node, r);
                    nodes.add(cNode);
                } else { // FIXME obsolete
                    System.out.println("WARNING! found a node with NodeType other than normal");
                }

                // add vertical edge segments
                for (LEdge edge : node.getOutgoingEdges()) {

                    Iterator<KVector> bends = edge.getBendPoints().iterator();

                    // infer vertical segments from positions of bendpoints
                    if (bends.hasNext()) {
                        KVector bend1 = bends.next();

                        // get segment of source n/s port
                        if (edge.getSource().getSide() == PortSide.NORTH
                                || edge.getSource().getSide() == PortSide.SOUTH) {
                            // TODO capsulate
                            VerticalSegment vSeg =
                                    new VerticalSegment(bend1, edge.getSource().getAbsoluteAnchor());
                            KVectorChain inJPs = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                            for (KVector jp : inJPs) {
                                if (Comp.eq(jp.x, bend1.x)) {
                                    vSeg.junctionPoints.add(jp);
                                }
                            }
                            vSeg.parentNode = cNode;
                            vSeg.relativePositionX = vSeg.x - cNode.hitbox.x;
                            verticalSegments.add(vSeg);
                        }

                        // get regular segments
                        while (bends.hasNext()) {
                            KVector bend2 = bends.next();
                            if (!Comp.eq(bend1.y, bend2.y)) {
                                VerticalSegment vSeg = new VerticalSegment(bend1, bend2);
                                // add JPs with same x
                                KVectorChain inJPs =
                                        edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                                for (KVector jp : inJPs) {
                                    if (Comp.eq(jp.x, bend1.x)) {
                                        vSeg.junctionPoints.add(jp);
                                    }
                                }
                                verticalSegments.add(vSeg);
                            }

                            bend1 = bend2;
                        }
                    }
                }

                // same for incoming edges to get those NSSegments
                // TODO kinda shitty
                for (LEdge edge : node.getIncomingEdges()) {
                    Iterator<KVector> bends = edge.getBendPoints().iterator();
                    if (bends.hasNext()) {
                        KVector bend1 = bends.next();
                        while (bends.hasNext()) { // TODO put inside next condition
                            bend1 = bends.next();
                        }
                        // get segment of target n/s port
                        if (edge.getTarget().getSide() == PortSide.NORTH
                                || edge.getTarget().getSide() == PortSide.SOUTH) {
                            VerticalSegment vSeg =
                                    new VerticalSegment(bend1, edge.getTarget().getAbsoluteAnchor());
                            KVectorChain inJPs = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                            for (KVector jp : inJPs) {
                                if (Comp.eq(jp.x, bend1.x)) {
                                    vSeg.junctionPoints.add(jp);
                                }
                            }
                            vSeg.parentNode = cNode;
                            vSeg.relativePositionX = vSeg.x - cNode.hitbox.x;
                            verticalSegments.add(vSeg);
                        }
                    }
                }
            }
        }

        // 2. combining intersecting segments to process them as one
        if (!verticalSegments.isEmpty()) {
            // FIXME this part is the least efficient
            Collections.sort(verticalSegments, new Comparator<VerticalSegment>() {
                /**
                 * {@inheritDoc}
                 */
                public int compare(VerticalSegment o1, VerticalSegment o2) {
                    int d = Double.compare(o1.x, o2.x);
                    if (d == 0) {
                        return Double.compare(o1.y1, o2.y1);
                    }
                    return d;
                }
            });

            VerticalSegment last = verticalSegments.get(0);
            CNode c = new CNode(last);

            for (int i = 1; i < verticalSegments.size(); i++) {
                VerticalSegment verticalSegment = verticalSegments.get(i);
                if (!verticalSegment.intersects(last)) {
                    nodes.add(c);
                    // if c is NSSegment
                    if (c.parentNode != null) {
                        // parentNode null if not ns because that is set in 1.
                        if (c.parentNode.connectedNSSegments == null) {
                            c.parentNode.connectedNSSegments = Sets.newHashSet();
                        }
                        c.parentNode.pendingNSSegments++;
                        c.parentNode.connectedNSSegments.add(c);
                    }
                    // System.out.println(c.hitbox.x + " : " + c.hitbox.y + " : " + c.hitbox.height
                    // + "\n\tBPs: " + c.bends + "\n\tJPs: " + c.juctionPoints);
                    c = new CNode(verticalSegment); // FIXME if nssegs intersect they belong to same
                                                    // parent so this is faulty for intersectin
                                                    // segments of different parents
                } else {
                    // FIXME assuming a junction point is on one edge only, no duplicates
                    c.addSegment(verticalSegment);
                }

                last = verticalSegment;
            }
            nodes.add(c);
            // System.out.println(c.hitbox.x + " : " + c.hitbox.y + " : " + c.hitbox.height
            // + "\n\tBPs: " + c.bends + "\n\tJPs: " + c.juctionPoints);
        }

        verticalSegments.clear();
    }

    // TODO why do nssegs not merge if this function is used??
    // private void addNewVSegment(List<VerticalSegment> verticalSegments, LEdge edge, KVector
    // bend1,
    // KVector bend2, boolean regular) {
    // if (regular || edge.getTarget().getSide() == PortSide.NORTH
    // || edge.getTarget().getSide() == PortSide.SOUTH) {
    // VerticalSegment vSeg = new VerticalSegment(bend1, bend2);
    // KVectorChain inJPs = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
    // for (KVector jp : inJPs) {
    // if (Comp.eq(jp.x, bend1.x)) {
    // vSeg.junctionPoints.add(jp);
    // }
    // }
    // verticalSegments.add(vSeg);
    // }
    // }

    private void lockNodes() {
        // TODO left/right lock nodes
        for (CNode node : nodes) {
            if (node.isNode) {
                boolean b = false;
                for (LEdge edge : node.lNode.getIncomingEdges()) {
                    if (edge.getSource().getNode().getPosition().x > node.lNode.getPosition().x) {
                        b = true;
                        break;
                    }
                }
                for (LEdge edge : node.lNode.getOutgoingEdges()) {
                    if (edge.getTarget().getNode().getPosition().x > node.lNode.getPosition().x) {
                        b = true;
                        break;
                    }
                }
                node.reposition = b;
            }
        }
    }

    private void getConstraints(final BiFunction<Double, Double, Boolean> cmp) { // FIXME overhead??
        // 3. infer constraints from hitbox intersections
        for (CNode node1 : nodes) {

            double spacing = edgeSpacing;
            LInsets margin1 = new LInsets(0, 0, 0, 0);
            if (node1.isNode) {
                margin1 = node1.lNode.getMargin();
                spacing = objSpacing;
            }

            for (CNode node2 : nodes) {

                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (node2.isNode) {
                    margin2 = node2.lNode.getMargin();
                    spacing = objSpacing;
                }

                // add constraint if node2 is to the right/left of node1 and could collide in x
                // direction
                // exclude parentNode
                if (node1 != node2 && node1 != node2.parentNode
                        && cmp.apply(node2.hitbox.x, node1.hitbox.x) // not >= neither Comp.gt to
                                                                     // avoid
                        // simultaneous constraints
                        // a->b and b->a
                        && Comp.gt(node2.hitbox.y + node2.hitbox.height + margin2.bottom + spacing,
                                node1.hitbox.y - margin1.top)
                        && Comp.lt(node2.hitbox.y - margin2.top, node1.hitbox.y
                                + node1.hitbox.height + margin1.bottom + spacing)) {

                    node1.incoming.add(node2);
                    node2.outDegree++;
                }
            }
        }
    }

    private void reverseConstraints() {
        for (CNode node : nodes) {
            // TODO if CNodes are reversed again the (reversed flag)?(tmpIncoming) has to be reset
            // TODO reset ns stuff
            node.startX = Double.POSITIVE_INFINITY;
            node.outDegree = 0;
            for (CNode inc : node.incoming) {
                node.outDegree++;
                inc.tmpIncoming.add(node);
            }

        }
        for (CNode node : nodes) {
            node.incoming = node.tmpIncoming;
        }
    }

    /**
     * TODO
     */
    private void compactLeft() {

        // 4. calculating node positions

        // starting with nodes with outDegree == 0
        Queue<CNode> startNodes = Lists.newLinkedList();
        Queue<CNode> pendingNodes = Lists.newLinkedList();
        for (CNode node : nodes) {
            if (node.outDegree == 0) {
                if (node.pendingNSSegments == 0) {
                    startNodes.add(node);
                    if (node.reposition) {
                        node.startX = 0;
                    } else {
                        if (node.isNode) {
                            node.startX = node.lNode.getPosition().x;
                        } else {
                            node.startX = node.bends.getFirst().x;
                        }
                    }
                    // handle NSSegments
                    if (node.parentNode != null) {
                        node.parentNode.pendingNSSegments--;
                        System.out.println(node + " is child of " + node.parentNode);
                    }
                    // handle NS parents
                    if (node.connectedNSSegments != null) { // should not be empty
                        // set parent startX
                        for (CNode ns : node.connectedNSSegments) {
                            // TODO specific to compactLeft
                            node.startX = Math.max(node.startX, ns.startX - ns.relativePositionX);
                            System.out.println("reset startX of " + node + ": " + node.startX);
                        }
                        // set connectedNSSegments startX
                        for (CNode ns : node.connectedNSSegments) {
                            // TODO specific to compactLeft
                            ns.startX = node.startX + ns.relativePositionX;
                        }
                    }
                } else {
                    pendingNodes.add(node);
                }
            }
        }

        // deriving leftmost positions from constraints
        while (!startNodes.isEmpty() || !pendingNodes.isEmpty()) {

            CNode node = startNodes.poll();
            if (node != null) {
                for (CNode inc : node.incoming) {
                    // determine if object or edge spacing should be used
                    // also retrieving margins around nodes
                    double spacing = edgeSpacing;
                    LInsets margin1 = new LInsets(0, 0, 0, 0);
                    if (node.isNode) {
                        margin1 = node.lNode.getMargin();
                        spacing = objSpacing;
                    }
                    LInsets margin2 = new LInsets(0, 0, 0, 0);
                    if (inc.isNode) {
                        margin2 = inc.lNode.getMargin();
                        spacing = objSpacing;
                    }

                    double currentX;
                    if (inc.isNode) {
                        currentX = inc.lNode.getPosition().x;
                    } else {
                        currentX = inc.bends.getFirst().x;
                    }

                    double newStartX =
                            Math.max(inc.startX, node.startX + node.hitbox.width + margin1.right
                                    + margin2.left + spacing);

                    inc.outDegree--;

                    if (inc.reposition || newStartX > currentX) { // since spacing often is not
                                                                  // sufficient enable widening
                                                                  // (probably not needed if nothing
                                                                  // was
                                                                  // locked before)
                        inc.startX = newStartX;
                    } else {
                        inc.startX = currentX;
                        inc.outDegree = 0; // set 0 to prevent unnecessary loops if node is locked
                                           // and
                                           // spacing is correct
                    }

                    // set startNodes for the next iteration
                    if (inc.outDegree == 0) {
                        if (inc.pendingNSSegments == 0) { // TODO maybe do this only below ? this
                                                          // seems duplicate
                            startNodes.add(inc);
                            // handle NSSegments
                            if (inc.parentNode != null) {
                                inc.parentNode.pendingNSSegments--;
                                System.out.println(inc + " is child of " + inc.parentNode);
                            }
                            // handle NS parents
                            if (inc.connectedNSSegments != null) { // should not be empty id not
                                                                   // null
                                // set parent startX
                                for (CNode ns : inc.connectedNSSegments) {
                                    // TODO specific to compactLeft
                                    inc.startX =
                                            Math.max(inc.startX, ns.startX - ns.relativePositionX);
                                    System.out
                                            .println("reset startX of " + inc + ": " + inc.startX);
                                }
                                // set connectedNSSegments startX
                                for (CNode ns : inc.connectedNSSegments) {
                                    // TODO specific to compactLeft
                                    ns.startX = inc.startX + ns.relativePositionX;
                                }
                            }
                        } else {
                            pendingNodes.add(inc);
                        }
                    }
                }
            }

            node = pendingNodes.poll();
            if (node != null) {
                if (node.pendingNSSegments == 0) {
                    startNodes.add(node);
                    // handle NSSegments
                    if (node.parentNode != null) {
                        node.parentNode.pendingNSSegments--;
                        System.out.println(node + " is child of " + node.parentNode);
                    }
                    // handle NS parents
                    if (node.connectedNSSegments != null) { // should not be empty id not null
                        // set parent startX
                        for (CNode ns : node.connectedNSSegments) {
                            // TODO specific to compactLeft
                            node.startX = Math.max(node.startX, ns.startX - ns.relativePositionX);
                            System.out.println("reset startX of " + node + ": " + node.startX);
                        }
                        // set connectedNSSegments startX
                        for (CNode ns : node.connectedNSSegments) {
                            // TODO specific to compactLeft
                            ns.startX = node.startX + ns.relativePositionX;
                        }
                    }
                } else {
                    pendingNodes.add(node);
                }
            }
        }
    }

    /**
     * TODO
     */
    private void compactRight() { // FIXME they differ in only 2 places!!

        // 4. calculating node positions

        // starting with nodes with outDegree == 0
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : nodes) {
            // TODO -- init
            node.startX = Double.POSITIVE_INFINITY;

            if (node.outDegree == 0) {
                startNodes.add(node);
                if (node.isNode) {
                    node.startX = node.lNode.getPosition().x;
                } else {
                    node.startX = node.bends.getFirst().x;
                }

            }
        }

        // deriving leftmost positions from constraints
        while (!startNodes.isEmpty()) {

            CNode node = startNodes.poll();
            for (CNode inc : node.incoming) {
                // determine if object or edge spacing should be used
                // also retrieving margins around nodes
                double spacing = edgeSpacing;
                LInsets margin1 = new LInsets(0, 0, 0, 0);
                if (node.isNode) {
                    margin1 = node.lNode.getMargin();
                    spacing = objSpacing;
                }
                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (inc.isNode) {
                    margin2 = inc.lNode.getMargin();
                    spacing = objSpacing;
                }

                // calculating rightmost position according to constraints
                // +++++ TODO really do this multiple times??
                double newStartX =
                        Math.min(inc.startX, node.startX - margin1.left - margin2.right - spacing
                                - inc.hitbox.width);
                double currentX;
                if (inc.isNode) { // TODO necessary to check that??
                    currentX = inc.lNode.getPosition().x;
                } else {
                    currentX = inc.bends.getFirst().x;
                }
                if (inc.reposition || newStartX < currentX) {
                    inc.startX = newStartX;
                } else {
                    inc.startX = currentX;
                }

                inc.outDegree--; // FIXME set 0 to prevent unnecessary loops

                // set startNodes for the next iteration
                if (inc.outDegree == 0) {
                    startNodes.add(inc);
                }
            }
        }
    }

    private void setNodePositions(List<CNode> nodes) {
        for (CNode node : nodes) {

            if (node.isNode) {
                LNode n = node.lNode;
                n.getPosition().x = node.startX;

            } else {
                for (KVector b : node.bends) {
                    b.x = node.startX;
                }
                for (KVector j : node.juctionPoints) {
                    j.x = node.startX;
                }
            }
        }
    }

    /**
     * Internal representation of a node in the constraint graph specifying a hitbox for the
     * {@link LGraphElement}.
     */
    private final class CNode {
        private boolean isNode;
        // used for reversing constraints
        private Set<CNode> tmpIncoming = Sets.newHashSet();
        // flags a node to be repositioned
        private boolean reposition = true;
        private LNode lNode;// TODO LNode or just position? hmm need margins
        private Rectangle hitbox;
        // specify particular vertical edge segments and affected junction points
        private KVectorChain bends;
        private KVectorChain juctionPoints;
        // representation of constraints
        private Set<CNode> incoming = Sets.newHashSet();// Lists.newArrayList();
        private int outDegree = 0;
        // TODO could there be multiple parents?
        private CNode parentNode = null;
        private double relativePositionX;
        private int pendingNSSegments = 0;
        private Set<CNode> connectedNSSegments = null;
        private double startX = Double.NEGATIVE_INFINITY;

        /**
         * Creates new constraint node
         * 
         * @param elem
         *            the graph element
         * @param hitbox
         *            the constraints are inferred from this box
         */
        private CNode(final LNode lNode, final Rectangle hitbox) {
            this.lNode = lNode;
            this.hitbox = hitbox;
            this.isNode = true;
        }

        private CNode(final VerticalSegment vSeg) {
            this.isNode = false;
            this.bends = new KVectorChain(vSeg.bend1, vSeg.bend2);
            this.juctionPoints = new KVectorChain(vSeg.junctionPoints);
            this.hitbox = new Rectangle(vSeg.x, vSeg.y1, 0, vSeg.y2 - vSeg.y1);
            this.parentNode = vSeg.parentNode;
            this.relativePositionX = vSeg.relativePositionX;
        }

        private void addSegment(final VerticalSegment vSeg) { // TODO check this!!
            this.bends.addAll(vSeg.bend1, vSeg.bend2);
            this.juctionPoints.addAll(vSeg.junctionPoints);
            double newY1 = Math.min(this.hitbox.y, vSeg.y1);
            double newY2 = Math.max(this.hitbox.y + this.hitbox.height, vSeg.y2);
            this.hitbox.setRect(vSeg.x, newY1, 0, newY2 - newY1);
            // TODO necessary to merge ns stuff?
        }

        // TODO private boolean intersects(CNode o)

        // TODO test
        @Override
        public String toString() {
            if (this.isNode) {
                return this.lNode.getName();
            } else {
                return "seg(" + this.hitbox.x + ", " + this.hitbox.y + ")";
            }
        }

    }

    // TODO -- comparable
    private final class VerticalSegment /* implements Comparable<VerticalSegment> */{
        private CNode parentNode;
        private double relativePositionX;
        private KVector bend1, bend2;
        private KVectorChain junctionPoints = new KVectorChain();
        private double x, y1, y2;

        private VerticalSegment(final KVector bend1, final KVector bend2) {
            this.bend1 = bend1;
            this.bend2 = bend2;

            if (bend1.y < bend2.y) {
                x = bend1.x;
                y1 = bend1.y;
                y2 = bend2.y;
            } else {
                x = bend2.x;
                y1 = bend2.y;
                y2 = bend1.y;
            }
        }

        private boolean intersects(VerticalSegment o) {
            return Comp.eq(this.x, o.x) && Comp.ge(this.y2, o.y1) && Comp.le(this.y1, o.y2); // FIXME
                                                                                             // correct?
        }

        // /**
        // * {@inheritDoc}
        // */
        // public int compareTo(VerticalSegment o) {
        // return Double.compare(this.bend1.x, o.bend1.x);
        // }

        @Override
        public String toString() {
            return "\nb1: " + bend1 + " \tb2: " + bend2 + " \t" + junctionPoints;
        }
    }

    /**
     * Internal class for tolerance affected double comparisons.
     */
    private static final class Comp {

        private static final double TOLERANCE = 0.0001;

        private static boolean eq(final double d1, final double d2) {
            return Math.abs(d1 - d2) <= TOLERANCE;
        }

        private static boolean gt(final double d1, final double d2) {
            return d1 - d2 > TOLERANCE;
        }

        private static boolean lt(final double d1, final double d2) {
            return d2 - d1 > TOLERANCE;
        }

        private static boolean ge(final double d1, final double d2) {
            return d1 - d2 >= -TOLERANCE;
        }

        private static boolean le(final double d1, final double d2) {
            return d2 - d1 >= -TOLERANCE;
        }
    }

}
