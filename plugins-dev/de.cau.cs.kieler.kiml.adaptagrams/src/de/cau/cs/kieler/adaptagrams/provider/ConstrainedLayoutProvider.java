/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.provider;

import org.adaptagrams.ColaEdge;
import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.SeparationConstraint;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.adaptagrams.properties.CoLaProperties;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Entry class for KIML to perform basic constrained layout. In terms of constraints only a
 * direction constrained is possible for this provider.
 * 
 * From the adaptagrams docu:
 * <p>
 * <em>
 *   This method is based on a non-linear gradient projection technique.
 *   Conceptually it's similar to a force directed method like Fruchterman-Reingold—but using a more
 *   principled goal function and optimisation techniques.
 * </em>
 * </p>
 * 
 * @author uru
 */
public class ConstrainedLayoutProvider extends AbstractLayoutProvider {

    // DO NOT map the class instances, as the cpp side might return new instances ..
    private BiMap<KNode, Long> nodeIndexMap = HashBiMap.create();
    private BiMap<KEdge, Long> edgeIndexMap = HashBiMap.create();

    private RectanglePtrs nodes;
    private ColaEdges edges;

    private CompoundConstraintPtrs constraints;

    private float spacing;
    private float borderSpacing;
    private Direction direction;
    private boolean considerPrevious;

    /**
     * Main entry point of the layour provider.
     * 
     * {@inheritDoc}
     */
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {

        // handle some properties
        KLayoutData rootLayout = parentNode.getData(KLayoutData.class);

        // should we consider previous node positions?
        considerPrevious = rootLayout.getProperty(CoLaProperties.CONSIDER_PREVIOUS_POSITIONS);

        // spacing
        spacing = rootLayout.getProperty(LayoutOptions.SPACING);
        Rectangle.setXBorder(spacing);
        Rectangle.setYBorder(spacing);

        // further options
        double idealEdgeLength = rootLayout.getProperty(CoLaProperties.IDEAL_EDGE_LENGTHS).doubleValue();
        boolean avoidOverlaps = rootLayout.getProperty(CoLaProperties.AVOID_OVERLAPS);
        borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);

        // transform to cola representation
        transformGraph(parentNode);

        // create constraints
        constraints = new CompoundConstraintPtrs();
        direction = rootLayout.getProperty(LayoutOptions.DIRECTION);
        if (direction != Direction.UNDEFINED) {
            addDirectionConstraints(parentNode);
        }

        // execute layout algorithm
        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(nodes, edges, idealEdgeLength, avoidOverlaps);

        algo.setConstraints(constraints);

        algo.makeFeasible();

        algo.run();

        // apply the calculated layout back
        applyLayout(parentNode);

        // cleanup c++ objects
        algo.freeAssociatedObjects();
    }

    /**
     * Transforms the KGraph into adaptagrams object.
     */
    private void transformGraph(final KNode root) {
        nodeIndexMap.clear();
        edgeIndexMap.clear();

        // Nodes
        nodes = new RectanglePtrs();
        long index = 0;
        for (KNode n : root.getChildren()) {
            KShapeLayout layout = n.getData(KShapeLayout.class);

            // x X y Y meaning x width y height
            Rectangle r = null;
            if (considerPrevious) {
                r =
                        new Rectangle(layout.getXpos(), layout.getXpos() + layout.getWidth(),
                                layout.getYpos(), layout.getYpos() + layout.getHeight());
            } else {
                // constrained layout considers previous positions, to make it independent from
                // any weird layout stuff used before we run it, use 0 as initial positions for all
                // rects
                r = new Rectangle(0, 0 + layout.getWidth(), 0, 0 + layout.getHeight());
            }

            nodes.add(r);
            nodeIndexMap.put(n, index++);
        }

        // Edges are index pairs to the rectangle array
        edges = new ColaEdges();
        index = 0;
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                // ignore cross-hierarchy edges and self-loops
                if (e.getTarget().getParent() == root && !e.getSource().equals(e.getTarget())) {

                    long src = nodeIndexMap.get(e.getSource());
                    long tgt = nodeIndexMap.get(e.getTarget());
                    ColaEdge cE = new ColaEdge(src, tgt);

                    edges.add(cE);
                    edgeIndexMap.put(e, index++);
                }
            }
        }
    }

    /**
     * Apply the calculated layout back to the KGraph.
     */
    private void applyLayout(final KNode root) {

        // calculate the offset from border spacing and node distribution
        double minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY =
                Float.MIN_VALUE;

        // find the minimal and maximal positions of the contained nodes
        for (int i = 0; i < nodes.size(); i++) {
            Rectangle r = nodes.get(i);
            minX = Math.min(minX, r.getMinX());
            minY = Math.min(minY, r.getMinY());
            maxX = Math.max(maxX, r.getMaxX());
            maxY = Math.max(maxY, r.getMaxY());
        }
        KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

        // Nodes
        for (int i = 0; i < nodes.size(); i++) {
            Rectangle r = nodes.get(i);
            KNode n = nodeIndexMap.inverse().get((long) i);

            KShapeLayout layout = n.getData(KShapeLayout.class);
            layout.setXpos((float) (r.getMinX() + offset.x));
            layout.setYpos((float) (r.getMinY() + offset.y));
        }

        // Edges, no routing done -> clear the bend points
        for (KNode n : root.getChildren()) {
            for (KEdge kedge : n.getOutgoingEdges()) {
                KEdgeLayout layout = kedge.getData(KEdgeLayout.class);
                layout.getBendPoints().clear();

                // TODO handle this more sophisticatedly
                // set some positions for the edge
                KShapeLayout srcLayout = n.getData(KShapeLayout.class);
                KShapeLayout tgtLayout = kedge.getTarget().getData(KShapeLayout.class);

                layout.getSourcePoint().setPos(srcLayout.getXpos() + srcLayout.getWidth() / 2,
                        srcLayout.getYpos() + srcLayout.getHeight() / 2);
                layout.getTargetPoint().setPos(tgtLayout.getXpos() + tgtLayout.getWidth() / 2,
                        tgtLayout.getYpos() + tgtLayout.getHeight() / 2);
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

    /**
     * Add direction constraints.
     */
    private void addDirectionConstraints(final KNode root) {

        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                // only handle edges between nodes of the same parent
                if (!e.getSource().getParent().equals(e.getTarget().getParent())) {
                    continue;
                }
                
                long src = nodeIndexMap.get(e.getSource());
                long tgt = nodeIndexMap.get(e.getTarget());

                KShapeLayout srcLayout = e.getSource().getData(KShapeLayout.class);
                KShapeLayout tgtLayout = e.getTarget().getData(KShapeLayout.class);

                int dim = Dim.XDIM;
                float separation = 2 * spacing;
                boolean swap = false;

                switch (direction) {
                case RIGHT:
                    separation += srcLayout.getWidth();
                    break;
                case LEFT:
                    separation += tgtLayout.getWidth();
                    swap = true;
                    break;
                case DOWN:
                    separation += srcLayout.getHeight();
                    dim = Dim.YDIM;
                    break;
                case UP:
                    separation += tgtLayout.getHeight();
                    dim = Dim.YDIM;
                    swap = true;
                    break;
                default:
                    // nothing
                }

                if (swap) {
                    long tmp = src;
                    src = tgt;
                    tgt = tmp;
                }

                // create the constraint
                SeparationConstraint sc = new SeparationConstraint(dim, src, tgt, separation);
                constraints.add(sc);
            }
        }
    }
}
