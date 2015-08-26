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
 * <dd>The edges are routed orthogonally</dd>
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

    // TODO arraylists when possible;

    private static final double TOLERANCE = 0.0001; // TODO ????????

    // TODO icg --
    private List<CNode> cNodes = Lists.newArrayList();
    private List<CGroup> cGroups = Lists.newArrayList();

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

        // 3. 4.
        getConstraints((a, b) -> a > b);
//        System.out.println(cNodes);
        groupCNodes();
        System.out.println("\ngroups:\n");
        for (CGroup g : cGroups) {
            System.out.println(g.cNodes);
            System.out.println("\tinc: " + g.incomingConstraints);
        }
        compactCGroups();
        System.out.println("\nstartX after compaction:\n");
        for (CNode node : cNodes) {
            System.out.println(node + "startX: " + node.startX);
        }
        //compactLeft();
        setNodePositions();
        lockCNodes();
        reverseConstraints();
        // compactRight();
        // setNodePositions();

        cGroups.clear();
        cNodes.clear();
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
                // add all nodes
                // hitbox excluding object spacing
                Rectangle r =
                        new Rectangle(node.getPosition().x, node.getPosition().y, node.getSize().x,
                                node.getSize().y);
                cNode = new CNode(node, r, layeredGraph);
                cNodes.add(cNode);
                // TODO icg new cgroup

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
                            vSeg.parentNode = cNode; // TODO icg = cGroup
                            vSeg.relativePositionX = vSeg.x - cNode.hitbox.x;
                            verticalSegments.add(vSeg);
                            vSeg.lEdge = edge;
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
                                vSeg.lEdge = edge;
                            }

                            bend1 = bend2;
                        }
                    }
                }

                // same for incoming edges to get NSSegments on target side
                // TODO kinda shitty
                for (LEdge edge : node.getIncomingEdges()) {
                    if (!edge.getBendPoints().isEmpty()) { // FIXME what if 2 nodes connected by ns
                                                           // seg??

                        // get segment of target n/s port
                        if (edge.getTarget().getSide() == PortSide.NORTH
                                || edge.getTarget().getSide() == PortSide.SOUTH) {
                            KVector bend1 = edge.getBendPoints().getLast();
                            VerticalSegment vSeg =
                                    new VerticalSegment(bend1, edge.getTarget().getAbsoluteAnchor());
                            KVectorChain inJPs = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                            for (KVector jp : inJPs) {
                                if (Comp.eq(jp.x, bend1.x)) {
                                    vSeg.junctionPoints.add(jp);
                                }
                            }
                            vSeg.parentNode = cNode; // TODO icg = cGroup
                            vSeg.relativePositionX = vSeg.x - cNode.hitbox.x;
                            verticalSegments.add(vSeg);
                            vSeg.lEdge = edge;
                        }
                    }
                }
            }
        }

        // 2. combining intersecting segments to process them as one
        if (!verticalSegments.isEmpty()) {
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
            CNode c = new CNode(last, layeredGraph); // TODO icg cg = new CGroup

            for (int i = 1; i < verticalSegments.size(); i++) {
                VerticalSegment verticalSegment = verticalSegments.get(i);
                if (!verticalSegment.intersects(last)) {
                    cNodes.add(c); // TODO icg --?
                    // if c is NSSegment
                    if (c.parentNode != null) { // TODO icg cg.getFirst().parentCGroup or
                                                // c.parentCGroup
                        // parentNode null if not ns because that is set in 1.
                        if (c.parentNode.connectedNSSegments == null) {
                            c.parentNode.connectedNSSegments = Lists.newArrayList();
                        }
                        c.parentNode.pendingNSSegments++;
                        c.parentNode.connectedNSSegments.add(c);
                        // TODO icg c.parentCGroup.addCNode(c)
                    }
                    // System.out.println(c.hitbox.x + " : " + c.hitbox.y + " : " + c.hitbox.height
                    // + "\n\tBPs: " + c.bends + "\n\tJPs: " + c.juctionPoints);
                    c = new CNode(verticalSegment, layeredGraph); // FIXME if nssegs intersect they
                                                                  // belong to same
                    // parent so this is faulty for intersecting
                    // segments of different parents (not happening in layered graph)
                } else {
                    // FIXME assuming a junction point is on one edge only, no duplicates
                    c.addSegment(verticalSegment);
                }

                last = verticalSegment;
            }
            cNodes.add(c);
            // TODO maybe do the merging completely here
            // if c is NSSegment
            if (c.parentNode != null) {
                // parentNode null if not ns because that is set in 1.
                if (c.parentNode.connectedNSSegments == null) {
                    c.parentNode.connectedNSSegments = Lists.newArrayList();
                }
                c.parentNode.pendingNSSegments++;
                c.parentNode.connectedNSSegments.add(c);
            }
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

    private void groupCNodes() {
        for (CNode node : cNodes) {
            if (node.parentNode == null) {
                CGroup group = new CGroup(node);
                if (node.connectedNSSegments != null) {
                    for (CNode nsSegment : node.connectedNSSegments) {
                        group.addCNode(nsSegment);
                    }
                }
                cGroups.add(group);
            }
        }
        
        //set outDegree TODO calc offsets ?? seem to be ok
        for (CGroup group : cGroups) {
            for (CNode node : group.cNodes) {
                for (CNode inc : node.incoming) {
                    if (group.incomingConstraints.contains(inc)) {
                        inc.group.outDegree++;
                    }
                }
            }
        }
        
        //check if incomingConstraints is correct
        for (CGroup group : cGroups) {
            List<CNode> incs = Lists.newArrayList();
            for (CNode n : group.cNodes) {
                incs.addAll(n.incoming);
            }
            System.out.println(group.cNodes+" all incs: "+incs+" g.inc: "+group.incomingConstraints);
            incs.clear();
        }
    }

    private void lockCNodes() { // TODO icg need to rename? lockCGroups
        // TODO left/right lock nodes
        for (CNode node : cNodes) {
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

    private double getSpacing(CNode a, CNode b) {
        if (a.isNode && b.isNode) {
            return objSpacing;
        }
        // TODO testing zero spacing
        if (a.parentNode != null && b.parentNode != null
                && !Sets.intersection(a.lEdges, b.lEdges).isEmpty()) {
            return 0;
        }
        return edgeSpacing;
    }

    private void getConstraints(final BiFunction<Double, Double, Boolean> cmp) { // FIXME overhead??
                                                                                 // bifunction
                                                                                 // obsolete
                                                                                 // (reverseConstraints)
        // 3. infer constraints from hitbox intersections
        for (CNode node1 : cNodes) {
//TODO use node.getMargin
            LInsets margin1 = new LInsets(0, 0, 0, 0);
            if (node1.isNode) {
                margin1 = node1.lNode.getMargin();
            }

            for (CNode node2 : cNodes) {
                double spacing = getSpacing(node1, node2);

                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (node2.isNode) {
                    margin2 = node2.lNode.getMargin();
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
        for (CNode node : cNodes) {
            // TODO if CNodes are reversed again the (reversed flag)?(tmpIncoming) has to be reset
            // TODO reset ns stuff
            node.startX = Double.POSITIVE_INFINITY;
            node.outDegree = 0;
            for (CNode inc : node.incoming) {
                node.outDegree++;
                inc.tmpIncoming.add(node);
            }

        }
        for (CNode node : cNodes) {
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
        for (CNode node : cNodes) {
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
                        // System.out.println(node + " is child of " + node.parentNode);
                    }
                    // handle NS parents
                    if (node.connectedNSSegments != null) { // should not be empty
                        // set parent startX
                        for (CNode ns : node.connectedNSSegments) {
                            // TODO specific to compactLeft
                            node.startX = Math.max(node.startX, ns.startX - ns.relativePositionX);
                            // System.out.println("reset startX of " + node + ": " + node.startX);
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
                    double spacing = getSpacing(node, inc);
                    LInsets margin1 = new LInsets(0, 0, 0, 0);
                    if (node.isNode) {
                        margin1 = node.lNode.getMargin();
                    }
                    LInsets margin2 = new LInsets(0, 0, 0, 0);
                    if (inc.isNode) {
                        margin2 = inc.lNode.getMargin();
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

                    // System.out.println("startX "+inc+"   "+inc.startX);

                    // set startNodes for the next iteration
                    if (inc.outDegree == 0) {
                        if (inc.pendingNSSegments == 0) { // TODO maybe do this only below ? this
                                                          // seems duplicate
                            startNodes.add(inc);
                            // handle NSSegments
                            if (inc.parentNode != null) {
                                inc.parentNode.pendingNSSegments--;
                                // System.out.println(inc + " is child of " + inc.parentNode);
                            }
                            // handle NS parents
                            if (inc.connectedNSSegments != null) { // should not be empty id not
                                                                   // null
                                // set parent startX
                                for (CNode ns : inc.connectedNSSegments) {
                                    // TODO specific to compactLeft
                                    inc.startX =
                                            Math.max(inc.startX, ns.startX - ns.relativePositionX);
                                    // System.out
                                    // .println("reset startX of " + inc + ": " + inc.startX);
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
                        // System.out.println(node + " is child of " + node.parentNode);
                    }
                    // handle NS parents
                    if (node.connectedNSSegments != null) { // should not be empty id not null
                        // set parent startX
                        for (CNode ns : node.connectedNSSegments) {
                            // TODO specific to compactLeft
                            node.startX = Math.max(node.startX, ns.startX - ns.relativePositionX);
                            // System.out.println("reset startX of " + node + ": " + node.startX);
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
    private void compactCGroups() {// TODO rn
    // TODO interface later
        System.out.println("\n initial sinks:\n");
        Queue<CGroup> sinks = Lists.newLinkedList();
        for (CGroup group : cGroups) {
            if (group.isInnerCompactable()) {
                group.compactInnerCNodes();
                sinks.add(group);
                System.out.println("group: "+group.cNodes);
            }
        }
        System.out.println("\nfurther compaction:\n");
        while (!sinks.isEmpty()) {
            CGroup group = sinks.poll();
            List<CGroup> compactables = group.propagate();
            for (CGroup g : compactables) {
                g.compactInnerCNodes();
                sinks.add(g);
                System.out.println("new sink: "+g.cNodes);
            }
            compactables.clear();
        }

        /*
         * 
         * the group.startX has to be initialized on creation
         * 
         * group.outgoingConstraints := not in group group.isInnerCompactable() if outgoing
         * constraints not contained in itself are empty
         * 
         * group.compactInnerCNodes() postcondition: isSink()==true and add to sinks (needs to be
         * done outside) Queue<CNode> startNodes = lists... like before while.. only for incoming
         * inside group ??? remove those from group.incomingConstraints at the end set positions at
         * offsets from minimal startX of group
         * 
         * group.propagate() update startX of incomings (CNodes), decrease outDegree or remove
         * oneself from its outgoing constraints, if one becomes innerCompactable add to return list
         * 
         * ?? CNode.updateStartX() has to know both nodes ++CNode.cGroup
         * 
         * (use updateStartX instead) CNode.propagate could be used in group.propagate and
         * compactInnerCNodes also has to handle in/outgoings of group
         */
    }

    /**
     * TODO
     */
    private void compactRight() { // FIXME they differ in only 2 places!!

        // 4. calculating node positions

        // starting with nodes with outDegree == 0
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : cNodes) {
            // TODO -- init
            node.startX = Double.POSITIVE_INFINITY;

            if (node.outDegree == 0) { // diff inits
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
                double spacing = getSpacing(node, inc);
                LInsets margin1 = new LInsets(0, 0, 0, 0);
                if (node.isNode) {
                    margin1 = node.lNode.getMargin();
                }
                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (inc.isNode) {
                    margin2 = inc.lNode.getMargin();
                }

                // calculating rightmost position according to constraints
                // +++++ TODO really do this multiple times??
                double newStartX =
                        Math.min(inc.startX, node.startX - margin1.left - margin2.right - spacing
                                - inc.hitbox.width); // diff margin spacing calc
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

    private void setNodePositions() { // TODO icg move to cnode or cgroup
        for (CNode node : cNodes) {

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

}
