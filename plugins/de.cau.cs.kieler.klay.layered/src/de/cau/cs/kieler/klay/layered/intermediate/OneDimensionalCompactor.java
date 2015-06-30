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

import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
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
    
    private static final double TOLERANCE = 0.0001; //TODO ????????

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Compacting horizontally", 1);

        double objSpacing = layeredGraph.getProperty(InternalProperties.SPACING);
        double edgeSpacing =
                layeredGraph.getProperty(InternalProperties.SPACING)
                        * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);

        List<CNode> nodes = Lists.newArrayList();
        List<VerticalSegment> verticalSegments = Lists.newArrayList();

        // 1. collecting positions of graph elements 
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {

                // add all nodes of type NORMAL because dummy nodes shouldn't have any size or
                // spacings
                // at this point there shouldn't even be any
                if (node.getNodeType().equals(NodeType.NORMAL)) {
                    // hitbox excluding object spacing
                    Rectangle r =
                            new Rectangle(node.getPosition().x, node.getPosition().y,
                                    node.getSize().x, node.getSize().y);
                    nodes.add(new CNode(node, r));
                }

                // add vertical edge segments
                for (LEdge edge : node.getOutgoingEdges()) {

                    Iterator<KVector> bends = edge.getBendPoints().iterator();

                    // infer vertical segments from positions of bendpoints
                    if (bends.hasNext()) {
                        KVector bend1 = bends.next();

                        while (bends.hasNext()) {
                            KVector bend2 = bends.next();
                            // FIXME use just tolerance
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
                                // TODO test if all JPs found
                                // missing the ones on n/s segments because they're not on same x
                            }

                            bend1 = bend2;
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
                        return Double.compare(o1.y, o2.y);
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
                    c = new CNode(verticalSegment);
                } else {
                    // FIXME assuming a junction point is on one edge only, no duplicates
                    c.addSegment(verticalSegment);
                }

                last = verticalSegment;
            }
            nodes.add(c);
        }

        // 3. infer constraints from hitbox intersections 
        for (CNode node1 : nodes) {
            for (CNode node2 : nodes) {

                // determine if object or edge spacing should be used
                // also retrieving margins around nodes
                double spacing = edgeSpacing;
                LInsets margin1 = new LInsets(0, 0, 0, 0);
                if (node1.isNode) {
                    margin1 = node1.lNode.getMargin();
                    spacing = objSpacing;
                }
                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (node2.isNode) {
                    margin2 = node2.lNode.getMargin();
                    spacing = objSpacing;
                }

                // add constraint if node2 is to the right of node1 and could collide in x direction
                if (node1 != node2
                        && node2.hitbox.x > node1.hitbox.x // not >= neither Comp.gt to avoid
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

        // 4. calculating node positions 

        // starting with nodes with outDegree == 0
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : nodes) {
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

                // calculating leftmost position according to constraints
                inc.startX =
                        Math.max(inc.startX, node.startX + node.hitbox.width + margin1.right
                                + margin2.left + spacing);
                inc.outDegree--;

                // set startNodes for the next iteration
                if (inc.outDegree == 0) {
                    startNodes.add(inc);
                }
            }
        }

        // 5. positioning nodes 
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

        progressMonitor.done();
    }

    /**
     * Internal representation of a node in the constraint graph specifying a hitbox for the
     * {@link LGraphElement}.
     */
    private final class CNode {
        private boolean isNode;
        private LNode lNode;// TODO LNode or just position? hmm need margins
        private Rectangle hitbox;
        // specify particular vertical edge segments and affected junction points
        private KVectorChain bends;
        private KVectorChain juctionPoints;
        // representation of constraints
        private List<CNode> incoming = Lists.newArrayList();
        private int outDegree = 0;
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
            this.hitbox = new Rectangle(vSeg.x, vSeg.y, 0, vSeg.h);
        }

        private void addSegment(final VerticalSegment vSeg) {
            this.bends.addAll(vSeg.bend1, vSeg.bend2);
            this.juctionPoints.addAll(vSeg.junctionPoints);
            this.hitbox.setRect(vSeg.x, Math.min(this.hitbox.y, vSeg.y), 0,
                    Math.max(this.hitbox.height, vSeg.h));
        }

    }

    // TODO -- comparable
    private final class VerticalSegment /*implements Comparable<VerticalSegment> */{
        private KVector bend1, bend2;
        private KVectorChain junctionPoints = new KVectorChain();
        private double x, y, h;

        private VerticalSegment(final KVector bend1, final KVector bend2) {
            this.bend1 = bend1;
            this.bend2 = bend2;

            if (bend1.y < bend2.y) {
                x = bend1.x;
                y = bend1.y;
                h = bend2.y - y;
            } else {
                x = bend2.x;
                y = bend2.y;
                h = bend1.y - y;
            }
        }

        private boolean intersects(VerticalSegment o) {
            return Comp.eq(this.x, o.x) && Comp.ge(this.y, o.y) && Comp.le(this.y, o.y + o.h);
        }

//        /**
//         * {@inheritDoc}
//         */
//        public int compareTo(VerticalSegment o) {
//            return Double.compare(this.bend1.x, o.bend1.x);
//        }

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
