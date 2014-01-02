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

import java.util.Iterator;
import java.util.List;

import org.adaptagrams.ColaEdge;
import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.SeparationConstraint;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
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

    private RectanglePtrs nodes;
    private ColaEdges edges;

    private CompoundConstraintPtrs constraints;

    private float spacing;

    /**
     * Main entry point of the layour provider.
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

        // transform to cola representation
        transformGraph(parentNode);

        // create constraints
        constraints = new CompoundConstraintPtrs();
        if (rootLayout.getProperty(ColaProperties.DIRECTION_CONSTRAINTS)) {
            addDirectionConstraints(parentNode);
        }
        if (rootLayout.getProperty(ColaProperties.PORT_CONSTRAINTS)) {
            addPortConstraints(parentNode);
        }

        // execute layout algorithm
        ConstrainedFDLayout algo = new ConstrainedFDLayout(nodes, edges, 100, true);

        algo.setConstraints(constraints);

        algo.makeFeasible();

        algo.run();

        // apply the calculated layout back
        applyLayout(parentNode);

        // adapt size of root node
        adaptRootNodeSize(parentNode);

        // cleanup c++ objects
        algo.freeAssociatedObjects();
    }

    private void transformGraph(final KNode root) {
        nodeIndexMap.clear();

        /*
         * Nodes
         */
        nodes = new RectanglePtrs();
        long index = 0;
        for (KNode n : root.getChildren()) {
            KShapeLayout layout = n.getData(KShapeLayout.class);
            // x X y Y meaning x width y height
            // Rectangle r =
            // new Rectangle(layout.getXpos(), layout.getXpos() + layout.getWidth(),
            // layout.getYpos(), layout.getYpos() + layout.getHeight());

            // constrained layout considers previous positions, to make it independent from
            // any weird layout stuff used before we run it, use 0 as initial positions for all
            // rects
            Rectangle r = new Rectangle(0, 0 + layout.getWidth(), 0, 0 + layout.getHeight());

            nodes.add(r);
            nodeIndexMap.put(n, index++);
        }

        /*
         * Edges
         */
        // edges are index pairs to the rectangle array
        edges = new ColaEdges();
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {
                long src = nodeIndexMap.get(e.getSource());
                long tgt = nodeIndexMap.get(e.getTarget());
                ColaEdge cE = new ColaEdge(src, tgt);
                edges.add(cE);

            }
        }
    }

    private void applyLayout(final KNode root) {

        /*
         * Nodes
         */
        for (int i = 0; i < nodes.size(); i++) {
            Rectangle r = nodes.get(i);
            KNode n = nodeIndexMap.inverse().get((long) i);

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

        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                long src = nodeIndexMap.get(e.getSource());
                long tgt = nodeIndexMap.get(e.getTarget());

                KShapeLayout srcLayout = e.getSource().getData(KShapeLayout.class);

                // TODO
                // does it refer to the minX pos of each node?
                // in that case we have to add the size of the node to the min separation
                SeparationConstraint sc =
                        new SeparationConstraint(Dim.XDIM, src, tgt, srcLayout.getWidth() + 10
                                + spacing);
                constraints.add(sc);
            }
        }
    }

    private void addPortConstraints(final KNode root) {

        for (KNode n : root.getChildren()) {
            List<KPort> ports = n.getPorts();

            Iterable<KPort> westPorts =
                    Iterables.filter(ports, new PortSidePredicate(PortSide.WEST));

            // System.out.println(westPorts);
            Iterator<KPort> it = westPorts.iterator();
            KPort prev = null;
            while (it.hasNext()) {
                KPort curr = it.next();
                if (prev != null) {

                    int separation = 0;
                    if (prev.getData(KShapeLayout.class).getYpos() < curr.getData(
                            KShapeLayout.class).getYpos()) {
                        // prev should be above curr
                        separation = 20;
                    } else {
                        separation = -20;
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
                            SeparationConstraint sc =
                                    new SeparationConstraint(Dim.YDIM, n1, n2, separation, false);
                            constraints.add(sc);

                        }
                    }

                }
                prev = curr;
            }
        }
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
