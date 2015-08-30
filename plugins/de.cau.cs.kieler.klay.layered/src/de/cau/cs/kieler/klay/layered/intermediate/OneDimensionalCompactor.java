/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;

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
    
    private LGraph layeredGraph;
    private List<CNode> cNodes = Lists.newArrayList();
    private List<CGroup> cGroups = Lists.newArrayList();
    private Direction direction = Direction.UNDEFINED;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Compacting", 1);
        
        this.layeredGraph = layeredGraph;
        
        
        changeDirection(Direction.LEFT).compact().changeDirection(Direction.RIGHT).compact().done();        
        
        progressMonitor.done();
    }
    
    public OneDimensionalCompactor compact() {
        if (direction == Direction.UNDEFINED) {
            changeDirection(Direction.LEFT);
        }
        
        compactCGroups();
        
        /* transform back
         * L: nop
         * R: mirror
         * U: transpose
         * D: mirror, transpose
         */
        switch (direction) {
        case RIGHT:
            mirrorHitboxes();
            break;
            
        case UP:
            transposeHitboxes();
            break;
            
        case DOWN:
            mirrorHitboxes(); transposeHitboxes();
            break;

        default:
            break;
        }
        
        setNodePositions();
        
        return this;
    }
    
    public OneDimensionalCompactor changeDirection(Direction dir) {
        /*
         * *>UNDEFINED: nop
         * UNDEFINED>L: read, getcons, group
         * UNDEFINED>R: read, mirror, getcons, group
         * UNDEFINED>U: read, transpose, getcons, group
         * UNDEFINED>D: read, transpose, mirror, getcons, group
         * L>R|R>L|U>D|D>U: mirror, lock, revcons, group
         * L>D|[R>d]|[U>l]|D>L: transpose, mirror, getcons, group
         * [L>d]|R>D|U>L|[D>l]: mirror, transpose, mirror, getcons, group
         * L>U|[R>u]|[U>r]|D>R: transpose, getcons, group
         * [L>u]|R>U|U>R|[D>r]: mirror, transpose, getcons, group
         * *>*: nop
         * 
         * U,D is only permitted if !layeredGraph.hasEdges (but this cannot happen in an intermediate processor)
         */
        
        if (dir == Direction.UP || dir == Direction.DOWN) {
            boolean hasEdges = layeredGraph.getLayers().stream()
                    .map((l) -> l.getNodes().stream()
                                    .map((n) -> Iterables.isEmpty(n.getConnectedEdges()))
                                    .anyMatch((b) -> b == false)
                                    )
                    .anyMatch((b) -> b == true) ;
            
            if (hasEdges) {
                System.out.println("Vertical compaction is not permitted on graphs containing edges.");
                return null;
            }
        }
        
        switch (direction) {
        case UNDEFINED:
            switch (dir) {
            case LEFT:
                readNodes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case RIGHT:
                readNodes(); mirrorHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case UP:
                readNodes(); transposeHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case DOWN:
                readNodes(); transposeHitboxes(); mirrorHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;

            default:
                break;
            }
            break;
            
        case LEFT:
            switch (dir) {
            case RIGHT:
                mirrorHitboxes(); lockCNodes(); reverseConstraints(); groupCNodes();
                break;
                
            case UP:
                transposeHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case DOWN:
                transposeHitboxes(); mirrorHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;

            default:
                break;
            }
            break;
            
        case RIGHT:
            switch (dir) {
            case LEFT:
                mirrorHitboxes(); lockCNodes(); reverseConstraints(); groupCNodes();
                break;
                
            case UP:
                mirrorHitboxes(); transposeHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case DOWN:
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;

            default:
                break;
            }
            break;
            
        case UP:
            switch (dir) {
            case LEFT:
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); lockCNodes(); reverseConstraints();
                break;
                
            case RIGHT:
                mirrorHitboxes(); transposeHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case DOWN:
                mirrorHitboxes(); lockCNodes(); reverseConstraints(); groupCNodes();
                break;

            default:
                break;
            }
            break;
    
        case DOWN:
            switch (dir) {
            case LEFT:
                transposeHitboxes(); mirrorHitboxes(); lockCNodes(); reverseConstraints();
                break;
                
            case RIGHT:
                transposeHitboxes(); getConstraints((a, b) -> a > b); groupCNodes();
                break;
                
            case UP:
                mirrorHitboxes(); lockCNodes(); reverseConstraints(); groupCNodes();
                break;

            default:
                break;
            }
            break;

        default:
            break;
        }
        
        direction = dir;
        return this;
    }
    
    public void done() {
        //update graph properties and clear lists
        KVector topLeft = new KVector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        KVector bottomRight = new KVector(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (CNode cNode : cNodes) {
            topLeft.x = Math.min(topLeft.x, cNode.hitbox.x);
            topLeft.y = Math.min(topLeft.y, cNode.hitbox.y);
            bottomRight.x = Math.max(bottomRight.x, cNode.hitbox.x + cNode.hitbox.width);
            bottomRight.y = Math.max(bottomRight.y, cNode.hitbox.y + cNode.hitbox.height);
        }
        layeredGraph.getOffset().reset().add(topLeft.clone().negate());
        layeredGraph.getSize().reset().add(bottomRight.clone().sub(topLeft));

        cGroups.clear();
        cNodes.clear();
        
        // resetting direction between calls of process()
        direction = Direction.UNDEFINED;
    }
    
    private void mirrorHitboxes() {
        for (CNode cNode : cNodes) {
            cNode.hitbox.x = - cNode.hitbox.x - cNode.hitbox.width;
            if (cNode.parentNode != null) {
                cNode.cGroupOffset = - cNode.cGroupOffset + cNode.parentNode.hitbox.width;
            }
        }
    }
    private void transposeHitboxes() {
        double tmp;
        for (CNode cNode : cNodes) {
            tmp = cNode.hitbox.x;
            cNode.hitbox.x = cNode.hitbox.y;
            cNode.hitbox.y = tmp;
            tmp = cNode.hitbox.width;
            cNode.hitbox.width = cNode.hitbox.height;
            cNode.hitbox.height = tmp;
            //TODO cGroupOffset cannot be transposed because it is one dimensional
        }
    }
    
    private void drawHitboxes(String name) {
        // update graph properties
        KVector topLeft = new KVector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        KVector bottomRight = new KVector(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (CNode cNode : cNodes) {
            topLeft.x = Math.min(topLeft.x, cNode.hitbox.x);
            topLeft.y = Math.min(topLeft.y, cNode.hitbox.y);
            bottomRight.x = Math.max(bottomRight.x, cNode.hitbox.x + cNode.hitbox.width);
            bottomRight.y = Math.max(bottomRight.y, cNode.hitbox.y + cNode.hitbox.height);
        }
        layeredGraph.getOffset().reset().add(topLeft.clone().negate());
        layeredGraph.getSize().reset().add(bottomRight.clone().sub(topLeft));

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(name));

            out.println("<svg viewBox=\"" + (-layeredGraph.getOffset().x) + " "
                    + (-layeredGraph.getOffset().y) + " " + layeredGraph.getSize().x + " "
                    + layeredGraph.getSize().y + "\">");

            for (CNode cNode : cNodes) {
                if (cNode.isNode) {
                    out.println("<rect x=\"" + cNode.hitbox.x + "\" y=\"" + cNode.hitbox.y
                            + "\" width=\"" + cNode.hitbox.width + "\" height=\""
                            + cNode.hitbox.height + "\" fill=\"white\" stroke=\"black\"/>");
                } else {
                    out.println("<line x1=\"" + cNode.hitbox.x + "\" y1=\"" + cNode.hitbox.y
                            + "\" x2=\"" + (cNode.hitbox.x + cNode.hitbox.width) + "\" y2=\""
                            + (cNode.hitbox.y + cNode.hitbox.height) + "\" stroke=\"blue\"/>");
                }
            }

            out.println("</svg>");

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 
     * @param layeredGraph
     * @return
     */
    private void readNodes() {
        List<VerticalSegment> verticalSegments = Lists.newArrayList();
        cNodes.clear();
        
        
        // 1. collecting positions of graph elements
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                // add all nodes
                CLNode cNode = new CLNode(node, layeredGraph);
                cNodes.add(cNode);

                // add vertical edge segments
                for (LEdge edge : node.getOutgoingEdges()) {

                    Iterator<KVector> bends = edge.getBendPoints().iterator();

                    // infer vertical segments from positions of bendpoints
                    if (bends.hasNext()) {
                        KVector bend1 = bends.next();

                        // get segment of source n/s port
                        if (edge.getSource().getSide() == PortSide.NORTH
                                || edge.getSource().getSide() == PortSide.SOUTH) { 
                            
                            verticalSegments.add(new VerticalSegment(bend1, 
                                    edge.getSource().getAbsoluteAnchor(), cNode, edge));
                        }

                        // get regular segments
                        while (bends.hasNext()) {
                            KVector bend2 = bends.next();
                            if (!Comp.eq(bend1.y, bend2.y)) {
                                verticalSegments.add(new VerticalSegment(bend1, bend2, null, edge));
                            }

                            bend1 = bend2;
                        }
                    }
                }

                // same for incoming edges to get NSSegments on target side
                for (LEdge edge : node.getIncomingEdges()) {
                    if (!edge.getBendPoints().isEmpty()) {

                        // get segment of target n/s port
                        if (edge.getTarget().getSide() == PortSide.NORTH
                                || edge.getTarget().getSide() == PortSide.SOUTH) {
                            KVector bend1 = edge.getBendPoints().getLast();
                            verticalSegments.add(new VerticalSegment(bend1, 
                                    edge.getTarget().getAbsoluteAnchor(), cNode, edge));
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
            CLEdge c = new CLEdge(last, layeredGraph);

            for (int i = 1; i < verticalSegments.size(); i++) {
                
                VerticalSegment verticalSegment = verticalSegments.get(i);
                
                if (!verticalSegment.intersects(last)) {
                    cNodes.add(c);
                    
                    c = new CLEdge(verticalSegment, layeredGraph);
                } else {
                    // assuming a junction point is on one edge only, no duplicates
                    c.addSegment(verticalSegment);
                }

                last = verticalSegment;
            }
            cNodes.add(c);
        }

        verticalSegments.clear();
    }
    

    private void groupCNodes() {
        // reset
        cGroups.clear();
        
        for (CNode cNode : cNodes) {
            if (cNode.parentNode == null) {
                cGroups.add(new CGroup(cNode));
            }
        }
        for (CNode cNode : cNodes) {
            if (cNode.parentNode != null) {
                cNode.parentNode.cGroup.addCNode(cNode);
            }
        }
        
        //set outDegree of CGroups
        for (CGroup group : cGroups) {
            for (CNode cNode : group.cNodes) {
                for (CNode inc : cNode.incoming) {
                    if (group.incomingConstraints.contains(inc)) {
                        inc.cGroup.outDegree++;
                    }
                }
            }
        }
    }

    private void lockCNodes() {
        // TODO use hitbox on both sides and make direction independent (<); could solve collision problems
        for (CNode cNode : cNodes) {
            if (cNode.isNode) {
                boolean b = false;
                for (LEdge edge : ((CLNode)cNode).lNode.getIncomingEdges()) {
                    if (edge.getSource().getNode().getPosition().x < cNode.getPosition()) {
                        b = true;
                        break;
                    }
                }
                for (LEdge edge : ((CLNode)cNode).lNode.getOutgoingEdges()) {
                    if (edge.getTarget().getNode().getPosition().x < cNode.getPosition()) {
                        b = true;
                        break;
                    }
                }
                cNode.reposition = b;
            }
        }
    }

    private void getConstraints(final BiFunction<Double, Double, Boolean> cmp) { // FIXME ?
                                                                                 // bifunction
                                                                                 // obsolete
                                                                                 // (reverseConstraints)
        // reset constraints
        for (CNode cNode : cNodes) {
            cNode.incoming.clear();
            cNode.outDegree = 0;
        }
        // 3. infer constraints from hitbox intersections
        for (CNode cNode1 : cNodes) {
            for (CNode cNode2 : cNodes) {
                double spacing = cNode1.getSpacing(cNode2);

                // add constraint if node2 is to the right/left of node1 and could collide in x
                // direction
                // exclude parentNode
                if (cNode1 != cNode2 && cNode1 != cNode2.parentNode
                        && cmp.apply(cNode2.hitbox.x, cNode1.hitbox.x) // not >= neither Comp.gt to
                                                                     // avoid
                        // simultaneous constraints
                        // a->b and b->a
                        && Comp.gt(cNode2.hitbox.y + cNode2.hitbox.height + spacing, cNode1.hitbox.y)
                        && Comp.lt(cNode2.hitbox.y, cNode1.hitbox.y
                                   + cNode1.hitbox.height + spacing)) {

                    cNode1.incoming.add(cNode2);
                    cNode2.outDegree++;
                }
            }
        }

    }

    private void reverseConstraints() {
        Map<CNode, List<CNode>> incMap = Maps.newHashMap();
        for (CNode cNode : cNodes) {
            incMap.put(cNode, Lists.newArrayList());
        }
        for (CNode cNode : cNodes) {
            cNode.startPos = Double.NEGATIVE_INFINITY;
            cNode.outDegree = 0;
            for (CNode inc : cNode.incoming) {
                cNode.outDegree++;
                incMap.get(inc).add(cNode);
            }

        }
        for (CNode cNode : cNodes) {
            cNode.incoming.clear();
            cNode.incoming = incMap.get(cNode);
        }
    }

    /**
     * 
     */
    private void compactCGroups() {
    
        Queue<CGroup> sinks = Lists.newLinkedList();
        for (CGroup group : cGroups) {
            if (group.isInnerCompactable()) {
                group.compactInnerCNodes();
                sinks.add(group);
            }
        }
        while (!sinks.isEmpty()) {
            CGroup group = sinks.poll();
            List<CGroup> compactables = group.propagate();
            for (CGroup g : compactables) {
                g.compactInnerCNodes();
                sinks.add(g);
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
        
        // set hitboxes
        for (CNode cNode : cNodes) {
            cNode.setPosition();
        }
    }

    private void setNodePositions() {
        for (CNode cNode : cNodes) {
            cNode.setElementPosition();
        }
    }

}
