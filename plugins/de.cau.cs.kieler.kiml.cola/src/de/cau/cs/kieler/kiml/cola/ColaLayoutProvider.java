/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.cola;

import java.util.Arrays;

import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Rectangle;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.cola.graph.CGraph;
import de.cau.cs.kieler.kiml.cola.graph.CNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * 
 * @author uru
 */
public class ColaLayoutProvider extends AbstractLayoutProvider {

    private float spacing;
    private float borderSpacing;
    
    private CGraph graph;

    /**
     * Main entry point of the layout provider.
     * 
     * {@inheritDoc}
     */
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {

        // handle some properties
        KLayoutData rootLayout = parentNode.getData(KLayoutData.class);

        // spacing
        spacing = rootLayout.getProperty(LayoutOptions.SPACING);
        Rectangle.setXBorder(spacing);
        Rectangle.setYBorder(spacing);

        borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);

        // create constraints
        // if (rootLayout.getProperty(ColaProperties.DIRECTION_CONSTRAINTS)) {
        // addDirectionConstraints(parentNode);
        // }
        // if (rootLayout.getProperty(ColaProperties.PORT_CONSTRAINTS)) {
        // // addPortConstraints(parentNode);
        // constraintsFixedSide(parentNode);
        // // constraintsFixedOrder(parentNode);
        // }

        // execute layout algorithm

        graph = new CGraph(parentNode);

        new DirectionConstraintProcessor().process(graph);
        new NonUniformEdgeLengthProcessor().process(graph);

        System.out.println(Arrays.toString(graph.idealEdgeLengths));

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 1, false,
                        graph.getIdealEdgeLengths());
        algo.setConstraints(graph.getConstraints());

        // run some w/o overlap
        algo.makeFeasible();

        for (int i = 0; i < 10; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out" + i + ".svg");

            System.out.println(i);
        }

        // do some with overlap
        algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 1, true,
                        graph.getIdealEdgeLengths());
        algo.setConstraints(graph.getConstraints());

        algo.makeFeasible();

        for (int i = 0; i < 10; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out99overlap" + i + ".svg");

            System.out.println(i);
        }

        /*
         * End
         */

        // apply the calculated layout back to the kgrap
        applyLayout(parentNode);

        // cleanup c++ objects
        algo.freeAssociatedObjects();
    }

    private void applyLayout(final KNode root) {

        // calculate the offset from border spacing and node distribution
        double minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY =
                Float.MIN_VALUE;

        // find the minimal and maximal positions of the contained nodes
        for (int i = 0; i < graph.nodes.size(); i++) {
            Rectangle r = graph.nodes.get(i);
            minX = Math.min(minX, r.getMinX());
            minY = Math.min(minY, r.getMinY());
            maxX = Math.max(maxX, r.getMaxX());
            maxY = Math.max(maxY, r.getMaxY());
        }
        KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

        /*
         * Nodes
         */
        for (CNode n : graph.getChildren()) {
            Rectangle r = n.rect;

            KShapeLayout layout = n.origin.getData(KShapeLayout.class);
            layout.setXpos((float) (r.getMinX() + offset.x));
            layout.setYpos((float) (r.getMinY() + offset.y));
        }

        /*
         * Edges, no routing done -> clear the bend points
         */
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                KEdgeLayout layout = e.getData(KEdgeLayout.class);
                layout.getBendPoints().clear();

            }
        }

        // resize the parent node
        KInsets insets = root.getData(KShapeLayout.class).getInsets();
        float width =
                (float) (maxX - minX) + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        float height =
                (float) (maxY - minY) + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(root, width, height, false, true);
    }


    /*-------------------------------
     * Old stuff
     */

//    private void constraintsFixedOrder(final KNode root) {
//
//        for (KNode n : root.getChildren()) {
//
//            List<KPort> ports = n.getPorts();
//
//            for (PortSide ps : PortSide.values()) {
//                if (ps == PortSide.UNDEFINED) {
//                    continue;
//                }
//
//                int dim = (ps == PortSide.EAST || ps == PortSide.WEST) ? Dim.YDIM : Dim.XDIM;
//                int separation = 20;
//
//                Iterable<KPort> sidePorts = Iterables.filter(ports, new PortSidePredicate(ps));
//
//                // System.out.println(westPorts);
//                Iterator<KPort> it = sidePorts.iterator();
//                KPort prev = null;
//                while (it.hasNext()) {
//                    KPort curr = it.next();
//                    if (prev != null) {
//
//                        boolean swap = false;
//                        if (ps == PortSide.EAST || ps == PortSide.WEST) {
//                            if (prev.getData(KShapeLayout.class).getYpos() < curr.getData(
//                                    KShapeLayout.class).getYpos()) {
//                                // prev should be above curr
//                                separation +=
//                                        prev.getNode().getData(KShapeLayout.class).getHeight();
//                            } else {
//                                swap = true;
//                                separation +=
//                                        curr.getNode().getData(KShapeLayout.class).getHeight();
//                            }
//                        } else {
//                            if (prev.getData(KShapeLayout.class).getXpos() < curr.getData(
//                                    KShapeLayout.class).getXpos()) {
//                                // prev should be above curr
//                            } else {
//                                swap = true;
//                            }
//                        }
//
//                        for (KEdge prevEdge : prev.getEdges()) {
//                            KNode prevNode = prevEdge.getSource();
//                            if (prevNode.equals(prev.getNode())) {
//                                prevNode = prevEdge.getTarget();
//                            }
//
//                            for (KEdge curEdge : curr.getEdges()) {
//                                KNode curNode = curEdge.getSource();
//                                if (curNode.equals(curr.getNode())) {
//                                    curNode = curEdge.getTarget();
//                                }
//
//                                System.out.println(prevNode + " " + curNode);
//
//                                long n1 = nodeIndexMap.get(prevNode);
//                                long n2 = nodeIndexMap.get(curNode);
//
//                                if (swap) {
//                                    long tmp = n1;
//                                    n1 = n2;
//                                    n2 = tmp;
//                                }
//
//                                SeparationConstraint sc =
//                                        new SeparationConstraint(dim, n1, n2, separation, false);
//                                constraints.add(sc);
//
//                            }
//                        }
//
//                    }
//                    prev = curr;
//                }
//            }
//        }
//    }
//
//    /**
//     * TODO only valid for LEFT to RIGHT layout.
//     * 
//     * TODO it should be ok if one side has no port or no port constraints!
//     */
//    private void constraintsFixedSide(final KNode root) {
//
//        System.out.println("applying ports " + System.currentTimeMillis());
//
//        for (KNode n : root.getChildren()) {
//            for (KEdge e : n.getOutgoingEdges()) {
//
//                if (e.getSourcePort() == null || e.getTargetPort() == null) {
//                    // if one end doesnt have a port we do not handle it
//                    continue;
//                }
//
//                KNode src = e.getSource();
//                PortSide srcSide =
//                        e.getSourcePort().getData(KLayoutData.class)
//                                .getProperty(LayoutOptions.PORT_SIDE);
//                KShapeLayout srcLayout = src.getData(KShapeLayout.class);
//
//                KNode tgt = e.getTarget();
//                PortSide tgtSide =
//                        e.getTargetPort().getData(KLayoutData.class)
//                                .getProperty(LayoutOptions.PORT_SIDE);
//                KShapeLayout tgtLayout = tgt.getData(KShapeLayout.class);
//
//                // if one of the two nodes has fixed side we add a separation constraint
//                if (srcLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()
//                        || tgtLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
//
//                    // handled cases:
//                    // WEST - EAST
//                    // NOTH - SOUTH
//
//                    boolean validCase = true;
//                    boolean swap = false;
//                    int separationDim = Dim.XDIM;
//
//                    // TODO do this properly, 2*20 is for ports on both sides
//                    float separation = spacing + 2 * 40;
//
//                    if (srcSide == PortSide.EAST && tgtSide == PortSide.WEST) {
//                        // System.out.println(src + " " + tgt + " " + srcLayout.getWidth() + " "
//                        // + srcLayout.getInsets());
//                        // System.out.println("Case 1");
//                        separationDim = Dim.XDIM;
//                        separation += srcLayout.getWidth();
//                    } else if (srcSide == PortSide.WEST && tgtSide == PortSide.EAST) {
//                        // System.out.println("Case 2");
//                        // inverted port situation
//                        separationDim = Dim.XDIM;
//                        separation += srcLayout.getWidth() + tgtLayout.getWidth();
//                    } else if (srcSide == PortSide.SOUTH && tgtSide == PortSide.NORTH) {
//                        // System.out.println("Case 3");
//                        separationDim = Dim.YDIM;
//                        separation += srcLayout.getHeight();
//                    } else if (srcSide == PortSide.NORTH && tgtSide == PortSide.SOUTH) {
//                        // System.out.println("Case 4");
//                        separationDim = Dim.YDIM;
//                        separation += tgtLayout.getHeight();
//                        swap = true;
//                    }
//
//                    else if (tgtSide == PortSide.SOUTH && srcSide == PortSide.EAST) {
//                        // TODO
//                        System.out.println("Case 5");
//                        separationDim = Dim.YDIM;
//                        separation += srcLayout.getHeight();
//                        swap = true;
//                    }
//
//                    else {
//                        validCase = false;
//                    }
//
//                    if (validCase) {
//                        long n1 = nodeIndexMap.get(src);
//                        long n2 = nodeIndexMap.get(tgt);
//                        if (swap) {
//                            long tmp = n1;
//                            n1 = n2;
//                            n2 = tmp;
//                        }
//                        SeparationConstraint sc =
//                                new SeparationConstraint(separationDim, n1, n2, separation, false);
//                        constraints.add(sc);
//                    }
//
//                }
//
//            }
//        }
//
//    }

//    private List<Pair<KNode, KNode>> createPairsOfNodes(final KNode root) {
//
//        // create appropriate data structures
//        List<KNode> children = Lists.newArrayList(root.getChildren());
//        List<Pair<KNode, KNode>> pairs = Lists.newLinkedList();
//
//        // create all pairs of nodes -> (n ncr 2) elements
//        for (int i = 0; i < children.size(); i++) {
//            KNode fst = children.get(i);
//            for (int j = i + 1; j < children.size(); j++) {
//                KNode snd = children.get(j);
//                Pair<KNode, KNode> nodePair = Pair.of(fst, snd);
//                pairs.add(nodePair);
//            }
//        }
//
//        return pairs;
//    }

//    private List<Cluster> separateUnconnectedComponents(final KNode root) {
//
//        Cluster connected = new ConvexCluster();
//        Cluster unconnected = new ConvexCluster();
//
//        for (KNode n : root.getChildren()) {
//            long id = nodeIndexMap.get(n);
//            if (Iterables.isEmpty(n.getIncomingEdges()) && Iterables.isEmpty(n.getOutgoingEdges())) {
//                // unconnected
//                unconnected.addChildNode(id);
//            } else {
//                // connected
//                connected.addChildNode(id);
//            }
//        }
//
//        return Lists.newArrayList(connected, unconnected);
//    }

    /**
     * .
     */
//    private static class PortSidePredicate implements Predicate<KPort> {
//        private PortSide side;
//
//        public PortSidePredicate(final PortSide side) {
//            this.side = side;
//        }
//
//        public boolean apply(final KPort p) {
//            KShapeLayout layout = p.getData(KShapeLayout.class);
//            return layout.getProperty(LayoutOptions.PORT_SIDE) == side;
//        }
//    }

}
