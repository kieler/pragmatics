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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adaptagrams.Cluster;
import org.adaptagrams.ColaEdge;
import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.ConvexCluster;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.RootCluster;
import org.adaptagrams.SWIGTYPE_p_double;
import org.adaptagrams.SeparationConstraint;
import org.adaptagrams.adaptagrams;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.cola.util.ColaUtil;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;

/**
 * 
 * @author uru
 */
public class ColaLayoutProvider extends AbstractLayoutProvider {

    // DO NOT map the class instances, as the cpp side might return new instances ..
    private BiMap<KNode, Long> nodeIndexMap = HashBiMap.create();
    private BiMap<KEdge, Long> edgeIndexMap = HashBiMap.create();
    private BiMap<KPort, Long> portDummyIndexMap = HashBiMap.create();

    private RectanglePtrs nodes;
    private ColaEdges edges;

    private CompoundConstraintPtrs constraints;

    private float spacing;
    
    
    private long edgeIndexOffset = 0;
    
    private double[] edgeLengths ;

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

        constraints = new CompoundConstraintPtrs();

        // transform to cola representation
        transformGraph(parentNode);

        // create constraints
        if (rootLayout.getProperty(ColaProperties.DIRECTION_CONSTRAINTS)) {
            addDirectionConstraints(parentNode);
        }
        if (rootLayout.getProperty(ColaProperties.PORT_CONSTRAINTS)) {
            // addPortConstraints(parentNode);
            constraintsFixedSide(parentNode);
            // constraintsFixedOrder(parentNode);
        }

        double idealEdgeLength = rootLayout.getProperty(ColaProperties.IDEAL_EDGE_LENGTHS);
         double[] nonUniformEdgeLengths = nonUniformLinks(parentNode, (float) idealEdgeLength);
         System.out.println(Arrays.toString(nonUniformEdgeLengths));
        
         SWIGTYPE_p_double arr = adaptagrams.newDoubleArray(nonUniformEdgeLengths.length);
         for (int i = 0; i < nonUniformEdgeLengths.length; ++i) {
         adaptagrams.doubleArraySet(arr, i, nonUniformEdgeLengths[i]);
         // adaptagrams.doubleArraySet(arr, i, 100);
         }

        // FIXME not able to pass a array of edge lengths here due to swig type ..
        // execute layout algorithm
        ConstrainedFDLayout algo = new ConstrainedFDLayout(nodes, edges, 40, false, arr);

        algo.setConstraints(constraints);

        // set clusters
        // RootCluster rc = new RootCluster();
        // for (Cluster c : separateUnconnectedComponents(parentNode)) {
        // rc.addChildCluster(c);
        // }

        algo.makeFeasible();

        algo.run();
        algo.run();
        // algo.run();

        algo.outputInstanceToSVG();

        // apply the calculated layout back
        applyLayout(parentNode);

        // adapt size of root node
        adaptRootNodeSize(parentNode);

        // cleanup c++ objects
        algo.freeAssociatedObjects();
    }

    private void transformGraph(final KNode root) {
        nodeIndexMap.clear();
        edgeIndexMap.clear();
        portDummyIndexMap.clear();

        /*
         * Nodes
         */
        nodes = new RectanglePtrs();

        // edges are index pairs to the rectangle array
        edges = new ColaEdges();
        
        int edgeCount  = 0;
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {
                edgeCount ++;
            }
        }
        int portCount = 0;
        for (KNode n : root.getChildren()) {
            for (KPort p : n.getPorts()) {
                portCount++;
            }
        }
        edgeLengths = new double[portCount + edgeCount];

        long edgeIndex = 0;
        long index = 0;

        for (KNode n : root.getChildren()) {
            
            // ignore unconnected nodes
            if (n.getIncomingEdges().isEmpty() && n.getOutgoingEdges().isEmpty()) {
                continue;
            }
            
            
            KShapeLayout layout = n.getData(KShapeLayout.class);
            // x X y Y meaning x width y height
            // Rectangle r =
            // new Rectangle(layout.getXpos(), layout.getXpos() + layout.getWidth(),
            // layout.getYpos(), layout.getYpos() + layout.getHeight());

            // constrained layout considers previous positions, to make it independent from
            // any weird layout stuff used before we run it, use 0 as initial positions for all
            // rects
            Rectangle r = new Rectangle(0, 0 + layout.getWidth(), 0, 0 + layout.getHeight());
            final long centerIndex = index;
            nodes.add(r);
            nodeIndexMap.put(n, index++);

            // create ports
            for (KPort p : n.getPorts()) {

                KShapeLayout portLayout = p.getData(KShapeLayout.class);

                try {
                    if (n.getLabels().get(0).getText().equals("DiscreteClock")) {
                        if (portLayout.getProperty(LayoutOptions.PORT_SIDE) != PortSide.EAST) {
                            System.out.println("Ignore port");
                            // continue;
                        }

                    }
                } catch (Exception e) {

                }

                Rectangle pr =
                        new Rectangle(0, 0 + portLayout.getWidth(), 0, 0 + portLayout.getHeight());
                nodes.add(pr);
                portDummyIndexMap.put(p, index++);

                // connect by edge
                ColaEdge dummyEdge = new ColaEdge(centerIndex, index - 1);
                edges.add(dummyEdge);
                edgeIndex++;

                // constraints refer to the center of a node
                double halfX = layout.getWidth() / 2f - portLayout.getWidth() / 2f;
                double halfY = layout.getHeight() / 2f - portLayout.getHeight() / 2f;

                // generate sep constrs
                SeparationConstraint scX =
                        new SeparationConstraint(Dim.XDIM, centerIndex, index - 1,
                                portLayout.getXpos() - halfX, true);
                constraints.add(scX);
                SeparationConstraint scY =
                        new SeparationConstraint(Dim.YDIM, centerIndex, index - 1,
                                portLayout.getYpos() - halfY, true);
                constraints.add(scY);
                
                // calculate the fixed distance of the dummy to the center
                KVector center = new KVector(layout.getWidth()/ 2f, layout.getHeight() / 2f);
                KVector portPos = new KVector(portLayout.getXpos() - halfX, portLayout.getYpos() - halfY);
                
                edgeLengths[(int)edgeIndex-1] = KVector.distance(center, portPos) + 5;
                
            }

        }
        
edgeIndexOffset = edgeIndex;
System.out.println("Finished dummie edges at " + edgeIndex);
        /*
         * Edges
         */
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {
                long src;
                if (e.getSourcePort() != null) {
                    src = portDummyIndexMap.get(e.getSourcePort());
                } else {
                    src = nodeIndexMap.get(e.getSource());
                }

                long tgt;
                if (e.getTargetPort() != null) {
                    tgt = portDummyIndexMap.get(e.getTargetPort());
                } else {
                    tgt = nodeIndexMap.get(e.getTarget());
                }

                ColaEdge cE = new ColaEdge(src, tgt);

                edges.add(cE);
                edgeIndexMap.put(e, edgeIndex++);
            }
        }
        
        System.out.println("Last edge created: " + edgeIndex);
    }

    private void applyLayout(final KNode root) {

        /*
         * Nodes
         */
        for (int i = 0; i < nodes.size(); i++) {
            Rectangle r = nodes.get(i);
            KNode n = nodeIndexMap.inverse().get((long) i);

            if (n == null) {
                // this is a dummy
                continue;
            }

            KShapeLayout layout = n.getData(KShapeLayout.class);
            layout.setXpos((float) r.getMinX());
            layout.setYpos((float) r.getMinY());
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
    }

    private void adaptRootNodeSize(final KNode root) {

        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY =
                Float.MIN_VALUE;

        // find the minimal and maximal positions of the contained nodes
        for (KNode n : root.getChildren()) {
            KShapeLayout sl = n.getData(KShapeLayout.class);

            if (sl.getXpos() < minX) {
                minX = sl.getXpos();
            }
            if (sl.getYpos() < minY) {
                minY = sl.getYpos();
            }
            float currMaxX = sl.getXpos() + sl.getWidth();
            if (maxX < currMaxX) {
                maxX = currMaxX;
            }
            float currMaxY = sl.getYpos() + sl.getHeight();
            if (maxY < currMaxY) {
                maxY = currMaxY;
            }
        }

        // adapt the size of the root node
        KShapeLayout rootLayout = root.getData(KShapeLayout.class);
        float borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);

        rootLayout.setPos(minX - borderSpacing, minY - borderSpacing);
        rootLayout.setWidth(maxX - minX + borderSpacing);
        rootLayout.setHeight(maxY - minY + borderSpacing);
    }

    /*
     * Constraints
     */
    private void addDirectionConstraints(final KNode root) {

        Set<Set<KNode>> sccs = ColaUtil.findStronglyConnectedComponents(root);
        Map<KNode, Set<KNode>> nodeSccMap = Maps.newHashMap();
        for (Set<KNode> scc : sccs) {
            for (KNode n : scc) {
                nodeSccMap.put(n, scc);
            }
        }

        // TODO only left to right atm
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                // dont create constraints if the nodes are in the same scc
                if (nodeSccMap.get(e.getSource()).contains(e.getTarget())) {
                    continue;
                }

                long src = nodeIndexMap.get(e.getSource());
                long tgt = nodeIndexMap.get(e.getTarget());

                KShapeLayout srcLayout = e.getSource().getData(KShapeLayout.class);
                KShapeLayout tgtLayout = e.getTarget().getData(KShapeLayout.class);

                // separation has to go from mid to mid
                // TODO consider margin etc
                double widthSeparation =
                        (srcLayout.getWidth() + srcLayout.getInsets().getLeft() + srcLayout
                                .getInsets().getRight())
                                / 2f
                                + (tgtLayout.getWidth() + tgtLayout.getInsets().getLeft() + tgtLayout
                                        .getInsets().getRight()) / 2f;
                SeparationConstraint sc =
                        new SeparationConstraint(Dim.XDIM, src, tgt, widthSeparation + spacing);
                constraints.add(sc);
            }
        }
    }

    private void constraintsFixedOrder(final KNode root) {

        for (KNode n : root.getChildren()) {
            
            List<KPort> ports = n.getPorts();

            for (PortSide ps : PortSide.values()) {
                if (ps == PortSide.UNDEFINED) {
                    continue;
                }

                int dim = (ps == PortSide.EAST || ps == PortSide.WEST) ? Dim.YDIM : Dim.XDIM;
                int separation = 20;

                Iterable<KPort> sidePorts = Iterables.filter(ports, new PortSidePredicate(ps));

                // System.out.println(westPorts);
                Iterator<KPort> it = sidePorts.iterator();
                KPort prev = null;
                while (it.hasNext()) {
                    KPort curr = it.next();
                    if (prev != null) {

                        boolean swap = false;
                        if (ps == PortSide.EAST || ps == PortSide.WEST) {
                            if (prev.getData(KShapeLayout.class).getYpos() < curr.getData(
                                    KShapeLayout.class).getYpos()) {
                                // prev should be above curr
                                separation +=
                                        prev.getNode().getData(KShapeLayout.class).getHeight();
                            } else {
                                swap = true;
                                separation +=
                                        curr.getNode().getData(KShapeLayout.class).getHeight();
                            }
                        } else {
                            if (prev.getData(KShapeLayout.class).getXpos() < curr.getData(
                                    KShapeLayout.class).getXpos()) {
                                // prev should be above curr
                            } else {
                                swap = true;
                            }
                        }

                        for (KEdge prevEdge : prev.getEdges()) {
                            KNode prevNode = prevEdge.getSource();
                            if (prevNode.equals(prev.getNode())) {
                                prevNode = prevEdge.getTarget();
                            }

                            for (KEdge curEdge : curr.getEdges()) {
                                KNode curNode = curEdge.getSource();
                                if (curNode.equals(curr.getNode())) {
                                    curNode = curEdge.getTarget();
                                }

                                System.out.println(prevNode + " " + curNode);

                                long n1 = nodeIndexMap.get(prevNode);
                                long n2 = nodeIndexMap.get(curNode);

                                if (swap) {
                                    long tmp = n1;
                                    n1 = n2;
                                    n2 = tmp;
                                }

                                SeparationConstraint sc =
                                        new SeparationConstraint(dim, n1, n2, separation, false);
                                constraints.add(sc);

                            }
                        }

                    }
                    prev = curr;
                }
            }
        }
    }

    /**
     * TODO only valid for LEFT to RIGHT layout.
     * 
     * TODO it should be ok if one side has no port or no port constraints!
     */
    private void constraintsFixedSide(final KNode root) {

        System.out.println("applying ports " + System.currentTimeMillis());

        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                if (e.getSourcePort() == null || e.getTargetPort() == null) {
                    // if one end doesnt have a port we do not handle it
                    continue;
                }

                KNode src = e.getSource();
                PortSide srcSide =
                        e.getSourcePort().getData(KLayoutData.class)
                                .getProperty(LayoutOptions.PORT_SIDE);
                KShapeLayout srcLayout = src.getData(KShapeLayout.class);

                KNode tgt = e.getTarget();
                PortSide tgtSide =
                        e.getTargetPort().getData(KLayoutData.class)
                                .getProperty(LayoutOptions.PORT_SIDE);
                KShapeLayout tgtLayout = tgt.getData(KShapeLayout.class);

                // if one of the two nodes has fixed side we add a separation constraint
                if (srcLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()
                        || tgtLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {

                    // handled cases:
                    // WEST - EAST
                    // NOTH - SOUTH

                    boolean validCase = true;
                    boolean swap = false;
                    int separationDim = Dim.XDIM;

                    // TODO do this properly, 2*20 is for ports on both sides
                    float separation = spacing + 2 * 40;

                    if (srcSide == PortSide.EAST && tgtSide == PortSide.WEST) {
                        // System.out.println(src + " " + tgt + " " + srcLayout.getWidth() + " "
                        // + srcLayout.getInsets());
                        // System.out.println("Case 1");
                        separationDim = Dim.XDIM;
                        separation += srcLayout.getWidth();
                    } else if (srcSide == PortSide.WEST && tgtSide == PortSide.EAST) {
                        // System.out.println("Case 2");
                        // inverted port situation
                        separationDim = Dim.XDIM;
                        separation += srcLayout.getWidth() + tgtLayout.getWidth();
                    } else if (srcSide == PortSide.SOUTH && tgtSide == PortSide.NORTH) {
                        // System.out.println("Case 3");
                        separationDim = Dim.YDIM;
                        separation += srcLayout.getHeight();
                    } else if (srcSide == PortSide.NORTH && tgtSide == PortSide.SOUTH) {
                        // System.out.println("Case 4");
                        separationDim = Dim.YDIM;
                        separation += tgtLayout.getHeight();
                        swap = true;
                    }

                    else if (tgtSide == PortSide.SOUTH && srcSide == PortSide.EAST) {
                        // TODO
                        System.out.println("Case 5");
                        separationDim = Dim.YDIM;
                        separation += srcLayout.getHeight();
                        swap = true;
                    }

                    else {
                        validCase = false;
                    }

                    if (validCase) {
                        long n1 = nodeIndexMap.get(src);
                        long n2 = nodeIndexMap.get(tgt);
                        if (swap) {
                            long tmp = n1;
                            n1 = n2;
                            n2 = tmp;
                        }
                        SeparationConstraint sc =
                                new SeparationConstraint(separationDim, n1, n2, separation, false);
                        constraints.add(sc);
                    }

                }

            }
        }

    }

    private List<Pair<KNode, KNode>> createPairsOfNodes(final KNode root) {

        // create appropriate data structures
        List<KNode> children = Lists.newArrayList(root.getChildren());
        List<Pair<KNode, KNode>> pairs = Lists.newLinkedList();

        // create all pairs of nodes -> (n ncr 2) elements
        for (int i = 0; i < children.size(); i++) {
            KNode fst = children.get(i);
            for (int j = i + 1; j < children.size(); j++) {
                KNode snd = children.get(j);
                Pair<KNode, KNode> nodePair = Pair.of(fst, snd);
                pairs.add(nodePair);
            }
        }

        return pairs;
    }

    private List<Cluster> separateUnconnectedComponents(final KNode root) {

        Cluster connected = new ConvexCluster();
        Cluster unconnected = new ConvexCluster();

        for (KNode n : root.getChildren()) {
            long id = nodeIndexMap.get(n);
            if (Iterables.isEmpty(n.getIncomingEdges()) && Iterables.isEmpty(n.getOutgoingEdges())) {
                // unconnected
                unconnected.addChildNode(id);
            } else {
                // connected
                connected.addChildNode(id);
            }
        }

        return Lists.newArrayList(connected, unconnected);
    }

    private double[] nonUniformLinks(final KNode root, final float w) {

//        double[] edgeLengths = new double[edgeIndexMap.size()];

        
        // collect neighbour sets for each node
        Map<KNode, Set<KNode>> neighbours = Maps.newHashMap();
        for (KNode node : root.getChildren()) {
            Set<KNode> neighbourSet = Sets.newHashSet();
            for (KEdge e : node.getOutgoingEdges()) {
                neighbourSet.add(e.getTarget());
            }
            for (KEdge e : node.getIncomingEdges()) {
                neighbourSet.add(e.getSource());
            }
            neighbours.put(node, neighbourSet);
        }

        // calc link sizes for each edge
        for (Entry<KEdge, Long> entry : edgeIndexMap.entrySet()) {
            KEdge e = entry.getKey();
            Set<KNode> srcSet = neighbours.get(e.getSource());
            Set<KNode> tgtSet = neighbours.get(e.getTarget());

            // calc link lengths
            int union = Sets.union(srcSet, tgtSet).size();
            int intersection = Sets.intersection(srcSet, tgtSet).size();

            // symmdifflinks
            double sqrt = Math.sqrt((double) (union - intersection));

            // jaccard
            double jaccard = 1;
            if (union > 1 && intersection > 1) {
                jaccard = 1 / (intersection / (double) union);
            }

            int index = entry.getValue().intValue();
            edgeLengths[index] = 1 + w * sqrt;
            // edgeLengths[index] = 1 + w * jaccard;
        }

        return edgeLengths;
    }

    /**
     * .
     */
    private static class PortSidePredicate implements Predicate<KPort> {
        private PortSide side;

        public PortSidePredicate(final PortSide side) {
            this.side = side;
        }

        public boolean apply(final KPort p) {
            KShapeLayout layout = p.getData(KShapeLayout.class);
            return layout.getProperty(LayoutOptions.PORT_SIDE) == side;
        }
    }

}
