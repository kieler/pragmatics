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

/**
 * This processor applies additional compaction to an already routed graph and can be
 * executed after {@link OrthogonalEdgeRouter}. Therefore nodes and vertical segments of edges are
 * repositioned in the specified direction where the position is minimal considering the
 * desired spacing between elements.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>The edges are routed orthogonally</dd>
 * <dt>Postcondition:</dt>
 * <dd>Nodes and edges are positioned compact without colliding.</dd>
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
    /** the layered graph. */
    private LGraph layeredGraph;
    /** the list of {@link CNode}s modeling the constraints in this graph. */
    private List<CNode> cNodes = Lists.newArrayList();
    /** groups of elements that are supposed to stay in the configuration they are. */
    private List<CGroup> cGroups = Lists.newArrayList();
    /** compacting in this direction. */
    private Direction direction = Direction.UNDEFINED;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Compacting", 1);
        
        this.layeredGraph = layeredGraph;
        
        // compacting left, locking all nodes that don't have any edge pointing to the right,
        // then compacting right to shorten unnecessary long edges
        changeDirection(Direction.LEFT).compact().changeDirection(Direction.RIGHT).compact().done();
        
        progressMonitor.done();
    }
    
    /**
     * Compacts the graph, reverses the transformation of hitboxes and writes the
     * new positions to the graph elements.
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor compact() {
        // if no direction was specified readNodes() has to reload the CNodes
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
    
    /**
     * Changes the direction for compaction by transforming the hitboxes.
     * @param dir
     *          the new direction
     * @return
     *          this instance of {@link OneDimensionalCompactor}
     */
    public OneDimensionalCompactor changeDirection(final Direction dir) {
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
         * U,D is only permitted if !layeredGraph.hasEdges 
         * (but this cannot happen in an intermediate processor)
         */
        
        // prohibits vertical compaction if the graph has edges, because vertical and horizontal
        // segments would interchange
        if (dir == Direction.UP || dir == Direction.DOWN) {
            boolean hasEdges = layeredGraph.getLayers().stream()
                    .flatMap(l -> l.getNodes().stream())
                    .anyMatch(n -> !Iterables.isEmpty(n.getConnectedEdges()));

            if (hasEdges) {
                System.out.println("Vertical compaction is not permitted on graphs containing edges.");
                return null;
            }
        }
        
        // executes required transformations
        switch (direction) {
        case UNDEFINED:
            switch (dir) {
            case LEFT:
                readNodes(); getConstraints(); groupCNodes();
                break;
                
            case RIGHT:
                readNodes(); mirrorHitboxes(); getConstraints(); groupCNodes();
                break;
                
            case UP:
                readNodes(); transposeHitboxes(); getConstraints(); groupCNodes();
                break;
                
            case DOWN:
                readNodes(); transposeHitboxes(); mirrorHitboxes(); getConstraints(); groupCNodes();
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
                transposeHitboxes(); getConstraints(); groupCNodes();
                break;
                
            case DOWN:
                transposeHitboxes(); mirrorHitboxes(); getConstraints(); groupCNodes();
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
                mirrorHitboxes(); transposeHitboxes(); getConstraints(); groupCNodes();
                break;
                
            case DOWN:
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); getConstraints(); groupCNodes();
                break;

            default:
                break;
            }
            break;
            
        case UP:
            switch (dir) {
            case LEFT:
                mirrorHitboxes(); transposeHitboxes(); mirrorHitboxes(); getConstraints(); groupCNodes();
                break;
                
            case RIGHT:
                mirrorHitboxes(); transposeHitboxes(); getConstraints(); groupCNodes();
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
                transposeHitboxes(); mirrorHitboxes(); getConstraints(); groupCNodes();
                break;
                
            case RIGHT:
                transposeHitboxes(); getConstraints(); groupCNodes();
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
    
    /**
     * Updates graph properties offset and size and clears lists.
     */
    public void done() {
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
    
    /**
     * Mirrors hitboxes horizontally.
     */
    private void mirrorHitboxes() {
        for (CNode cNode : cNodes) {
            cNode.hitbox.x = -cNode.hitbox.x - cNode.hitbox.width;
            if (cNode.parentNode != null) {
                cNode.cGroupOffset = -cNode.cGroupOffset + cNode.parentNode.hitbox.width;
            }
        }
    }
    
    /**
     * Transposes hitboxes.
     */
    private void transposeHitboxes() {
        double tmp;
        for (CNode cNode : cNodes) {
            tmp = cNode.hitbox.x;
            cNode.hitbox.x = cNode.hitbox.y;
            cNode.hitbox.y = tmp;
            tmp = cNode.hitbox.width;
            cNode.hitbox.width = cNode.hitbox.height;
            cNode.hitbox.height = tmp;
        }
    }
    
    /**
     * For debugging. Writes hitboxes to svg.
     * @param name
     *          filename
     */
    @SuppressWarnings("unused")
    private void drawHitboxes(final String name) {
        // update graph properties to determine viewBox
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

        // drawing hitboxes to svg
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(name));

            out.println("<svg viewBox=\"" + (-layeredGraph.getOffset().x) + " "
                    + (-layeredGraph.getOffset().y) + " " + layeredGraph.getSize().x + " "
                    + layeredGraph.getSize().y + "\">");

            for (CNode cNode : cNodes) {
                if (cNode.getClass().equals(CLNode.class)) {
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
     * Collects the positions and dimensions of nodes and vertical segments in the layered graph
     * and writes them to the {@link CNode}s.
     */
    private void readNodes() {
        List<VerticalSegment> verticalSegments = Lists.newArrayList();
        // resetting to avoid problems if this is called repeatedly
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

        // 2. combining intersecting segments in CLEdges to process them as one
        if (!verticalSegments.isEmpty()) {
            // sorting the segments by position in ascending order
            Collections.sort(verticalSegments, new Comparator<VerticalSegment>() {
                /**
                 * {@inheritDoc}
                 */
                public int compare(final VerticalSegment o1, final VerticalSegment o2) {
                    int d = Double.compare(o1.x, o2.x);
                    if (d == 0) {
                        return Double.compare(o1.y1, o2.y1);
                    }
                    return d;
                }
            });

            // merging intersecting segments in the same CLEdge
            VerticalSegment last = verticalSegments.get(0);
            CLEdge c = new CLEdge(last, layeredGraph);

            for (int i = 1; i < verticalSegments.size(); i++) {
                
                VerticalSegment verticalSegment = verticalSegments.get(i);
                
                if (verticalSegment.intersects(last)) {
                    c.addSegment(verticalSegment);
                } else {
                    cNodes.add(c);
                    c = new CLEdge(verticalSegment, layeredGraph);
                }

                last = verticalSegment;
            }
            cNodes.add(c);
        }

        verticalSegments.clear();
    }
    
    /**
     * Groups nodes with their connected north/south segments to keep them at
     * the correct position to each other.
     */
    private void groupCNodes() {
        // resetting groups from previous compaction
        cGroups.clear();
        
        // creating groups for independent CNodes
        for (CNode cNode : cNodes) {
            if (cNode.parentNode == null) {
                cGroups.add(new CGroup(cNode));
            }
        }
        
        // adding CNodes of north/south segments to the same group as their parent nodes
        for (CNode cNode : cNodes) {
            if (cNode.parentNode != null) {
                cNode.parentNode.cGroup.addCNode(cNode);
            }
        }
        
        // setting the outDegree (number of CNodes this group is constrained by) of CGroups
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

    /**
     * Locks the position of CNodes before a second compaction in opposite direction
     * if the CNode has no edges connected to a node in that direction.
     */
    private void lockCNodes() {
        // maps CNodes to LNodes
        Map<LNode, CNode> nodeMap = Maps.newHashMap();
        for (CNode cNode : cNodes) {
            nodeMap.put(cNode.getLNode(), cNode);
        }

        for (CNode cNode : cNodes) {
            
            cNode.reposition = false; //cNode.getLNode() == null;//TODO except for edges dammit
            
            for (LNode connectedNode : cNode.getConnectedNodes()) {
                if (nodeMap.get(connectedNode).getPosition() < cNode.getPosition()) {
                    cNode.reposition = true;
                    break;
                }
            }
        }
    }

    /**
     * Creates a constraint between CNodes a and b if a could cast a shadow on b
     * considering margins and spacing.
     * @param cmp
     *          a function comparing the position of two CNodes (usually >)
     */
    private void getConstraints() {
        // resetting constraintsinitially
        for (CNode cNode : cNodes) {
            cNode.incoming.clear();
            cNode.outDegree = 0;
        }
        
        //  inferring constraints from hitbox intersections
        for (CNode cNode1 : cNodes) {
            for (CNode cNode2 : cNodes) {
                double spacing = cNode1.getSpacing(cNode2);

                // add constraint if node2 is to the right of node1 and could collide if 
                // movedhorizontally
                // exclude parentNodes because they don't constrain their north/south segments
                if (cNode1 != cNode2 && cNode1 != cNode2.parentNode
                        // shouldn't use >= nor Comp.gt to avoid simultaneous constraints a->b and b->a
                        && cNode2.hitbox.x > cNode1.hitbox.x
                        
                        && Comp.gt(cNode2.hitbox.y + cNode2.hitbox.height + spacing, cNode1.hitbox.y)
                        
                        && Comp.lt(cNode2.hitbox.y, cNode1.hitbox.y
                                   + cNode1.hitbox.height + spacing)) {

                    cNode1.incoming.add(cNode2);
                    cNode2.outDegree++;
                }
            }
        }

    }

    /**
     * If the graph is compacted twice in opposing directions, the constraints can be reversed to
     * avoid recalculating them.
     */
    private void reverseConstraints() {
        // maps CNodes to temporary lists of incoming constraints
        Map<CNode, List<CNode>> incMap = Maps.newHashMap();
        for (CNode cNode : cNodes) {
            incMap.put(cNode, Lists.newArrayList());
        }
        
        // resetting fields of CNodes and reversing constraints
        for (CNode cNode : cNodes) {
            cNode.startPos = Double.NEGATIVE_INFINITY;
            cNode.outDegree = 0;
            for (CNode inc : cNode.incoming) {
                cNode.outDegree++;
                incMap.get(inc).add(cNode);
            }
        }
        
        // write back
        for (CNode cNode : cNodes) {
            cNode.incoming.clear();
            cNode.incoming = incMap.get(cNode);
        }
    }

    /**
     * Compacting CGroups and the CNodes inside.
     */
    private void compactCGroups() {
        // queues CGroups whose outgoing constraints are processed
        Queue<CGroup> sinks = Lists.newLinkedList();
        
        // finding and compacting CGroups that are sinks
        for (CGroup group : cGroups) {
            if (group.isInnerCompactable()) {
                group.compactInnerCNodes();
                sinks.add(group);
            }
        }
        
        // propagating the positions of sinks to set new starting positions for constrained CNodes
        // and CGroups
        while (!sinks.isEmpty()) {
            
            CGroup group = sinks.poll();
            List<CGroup> compactables = group.propagate();
            
            // compacting CGroups that became sinks in this iteration
            for (CGroup g : compactables) {
                g.compactInnerCNodes();
                sinks.add(g);
            }
            compactables.clear();
        }
        
        // setting hitbox positions to new starting positions
        for (CNode cNode : cNodes) {
            cNode.setPosition();
        }
    }

    /**
     * Sets the positions of graph elements to the compacted position.
     */
    private void setNodePositions() {
        for (CNode cNode : cNodes) {
            cNode.setElementPosition();
        }
    }

}
