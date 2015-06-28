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

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
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

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        // hypernodes testing
        final double TOLERANCE = 0.01;
        List<VerticalSegment> verticalSegments = Lists.newArrayList();
        List<HyperNode> hyperNodes = layeredGraph.getProperty(InternalProperties.HYPERNODES);
        for (HyperNode hyperNode : hyperNodes) {
            if (Math.abs(hyperNode.end - hyperNode.start) > TOLERANCE) {
                VerticalSegment vSeg = new VerticalSegment();
//                for (LPort port : hyperNode.ports) {
//                    if (port.getNode().getNodeType().equals(NodeType.NORMAL)) {
//                        System.out.println(port.getOutgoingEdges());
//                    }
//                }
                for (LNode node : hyperNode.connectedNodes) {
                    if (!node.getNodeType().equals(NodeType.NORMAL)) {
                        System.out.println(node.getProperty(InternalProperties.ORIGIN));
                        System.out.println(node.getNodeType());
                    }
                }
            }
        }

        progressMonitor.begin("one dimensional compacting", 1);

        double objSpacing = layeredGraph.getProperty(InternalProperties.SPACING);
        double edgeSpacing =
                layeredGraph.getProperty(InternalProperties.SPACING)
                        * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);

        List<CNode> nodes = Lists.newArrayList();

        /* collecting positions of graph elements */
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
                    // System.out.println(edge + " JPs: "
                    // + edge.getProperty(LayoutOptions.JUNCTION_POINTS));
                    //
                    // Iterator<KVector> bends = edge.getBendPoints().iterator();
                    //
                    // while (bends.hasNext()) {
                    // // infer vertical segments from order of bendpoints
                    // KVector bend1 = bends.next();
                    // KVector bend2 = bends.next();
                    // double x, y, h;
                    // if (bend1.y < bend2.y) {
                    // x = bend1.x;
                    // y = bend1.y;
                    // h = bend2.y - y;
                    // } else {
                    // x = bend2.x;
                    // y = bend2.y;
                    // h = bend1.y - y;
                    // }
                    //
                    // Rectangle rEdge = new Rectangle(x, y, 0, h);
                    // CNode cn = new CNode(edge, rEdge);
                    // cn.bend1 = bend1;
                    // cn.bend2 = bend2;
                    // nodes.add(cn);
                    // }
                }
            }
        }

        /* infer constraints from hitbox intersections */
        for (CNode node1 : nodes) {
            for (CNode node2 : nodes) {

                // determine if object or edge spacing should be used
                // also retrieving margins around nodes
                double spacing = edgeSpacing;
                LInsets margin1 = new LInsets(0, 0, 0, 0);
                if (node1.elem.getClass() == LNode.class) {
                    margin1 = ((LNode) node1.elem).getMargin();
                    spacing = objSpacing;
                }
                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (node2.elem.getClass() == LNode.class) {
                    margin2 = ((LNode) node2.elem).getMargin();
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

        /* calculating node positions */

        // starting with nodes with outDegree == 0
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : nodes) {
            if (node.outDegree == 0) {
                startNodes.add(node);
                if (node.elem.getClass() == LNode.class) {
                    node.startX = ((LNode) node.elem).getPosition().x;
                } else if (node.elem.getClass() == LEdge.class) {
                    node.startX = node.bend1.x;
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
                if (node.elem.getClass() == LNode.class) {
                    margin1 = ((LNode) node.elem).getMargin();
                    spacing = objSpacing;
                }
                LInsets margin2 = new LInsets(0, 0, 0, 0);
                if (inc.elem.getClass() == LNode.class) {
                    margin2 = ((LNode) inc.elem).getMargin();
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

        /* positioning nodes */
        for (CNode node : nodes) {

            if (node.elem.getClass() == LNode.class) {
                LNode n = (LNode) node.elem;
                n.getPosition().x = node.startX;

            } else if (node.elem.getClass() == LEdge.class) {
                node.bend1.x = node.startX;
                node.bend2.x = node.startX;

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
        private LGraphElement elem;
        private Rectangle hitbox;
        // specify particular vertical edge segment
        private KVector bend1, bend2;
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
        private CNode(final LGraphElement elem, final Rectangle hitbox) {
            this.elem = elem;
            this.hitbox = hitbox;
        }

    }

    private final class VerticalSegment {
        private List<Pair<LEdge, Pair<KVector, KVector>>> bends = Lists.newArrayList();
        private KVectorChain junctionPoints = new KVectorChain();
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
