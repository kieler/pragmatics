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
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.kiml.adaptagrams.properties.ColaOptions;


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
    private BiMap<ElkNode, Long> nodeIndexMap = HashBiMap.create();
    private BiMap<ElkEdge, Long> edgeIndexMap = HashBiMap.create();

    private RectanglePtrs nodes;
    private ColaEdges edges;

    private CompoundConstraintPtrs constraints;

    private double spacing;
    private Direction direction;
    private boolean considerPrevious;

    /**
     * Main entry point of the layour provider.
     * 
     * {@inheritDoc}
     */
    public void layout(final ElkNode parentNode, final IElkProgressMonitor progressMonitor) {

        // should we consider previous node positions?
        considerPrevious = parentNode.getProperty(ColaOptions.CONSIDER_PREVIOUS_POSITIONS);

        // spacing
        spacing = parentNode.getProperty(ColaOptions.SPACING_NODE_NODE);
        Rectangle.setXBorder(spacing);
        Rectangle.setYBorder(spacing);

        // further options
        double idealEdgeLength = parentNode.getProperty(ColaOptions.IDEAL_EDGE_LENGTH);
        boolean avoidOverlaps = parentNode.getProperty(ColaOptions.AVOID_OVERLAPS);

        // transform to cola representation
        transformGraph(parentNode);

        // create constraints
        constraints = new CompoundConstraintPtrs();
        direction = parentNode.getProperty(ColaOptions.DIRECTION);
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
    private void transformGraph(final ElkNode root) {
        nodeIndexMap.clear();
        edgeIndexMap.clear();

        // Nodes
        nodes = new RectanglePtrs();
        long index = 0;
        for (ElkNode n : root.getChildren()) {
            // x X y Y meaning x width y height
            Rectangle r = null;
            if (considerPrevious) {
                r = new Rectangle(n.getX(), n.getX() + n.getWidth(),
                                  n.getY(), n.getY() + n.getHeight());
            } else {
                // constrained layout considers previous positions, to make it independent from
                // any weird layout stuff used before we run it, use 0 as initial positions for all
                // rects
                r = new Rectangle(0, 0 + n.getWidth(), 0, 0 + n.getHeight());
            }

            nodes.add(r);
            nodeIndexMap.put(n, index++);
        }

        // Edges are index pairs to the rectangle array
        edges = new ColaEdges();
        index = 0;
        for (ElkNode n : root.getChildren()) {
            for (ElkEdge e : ElkGraphUtil.allOutgoingEdges(n)) {
                // ignore cross-hierarchy edges and self-loops
                if (!e.isHierarchical() && !e.isSelfloop()) {

                    ElkNode source = ElkGraphUtil.connectableShapeToNode(e.getSources().get(0));
                    ElkNode target = ElkGraphUtil.connectableShapeToNode(e.getTargets().get(0));
                    long src = nodeIndexMap.get(source);
                    long tgt = nodeIndexMap.get(target);
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
    private void applyLayout(final ElkNode root) {

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
        
        ElkPadding padding = root.getProperty(ColaOptions.PADDING);
        KVector offset = new KVector(padding.left - minX, padding.top - minY);

        // Nodes
        for (int i = 0; i < nodes.size(); i++) {
            Rectangle r = nodes.get(i);
            ElkNode n = nodeIndexMap.inverse().get((long) i);

            n.setX((float) (r.getMinX() + offset.x));
            n.setY((float) (r.getMinY() + offset.y));
        }

        // Edges, no routing done -> clear the bend points
        for (ElkNode src : root.getChildren()) {
            
            for (ElkEdge elkEdge : ElkGraphUtil.allOutgoingEdges(src)) {
               
                ElkNode tgt = ElkGraphUtil.connectableShapeToNode(elkEdge.getTargets().get(0));
                ElkEdgeSection edgeSection = ElkGraphUtil.firstEdgeSection(elkEdge, true, true);
                
                // TODO handle this more sophisticatedly
                edgeSection.setStartLocation(src.getX() + src.getWidth() / 2, src.getY() + src.getHeight() / 2);
                edgeSection.setEndLocation(tgt.getX() + tgt.getWidth() / 2, tgt.getY() + tgt.getHeight() / 2);
            }
        }

        // resize the parent node
        double width = (maxX - minX) + padding.getHorizontal();
        double height = (maxY - minY) + padding.getVertical();
        ElkUtil.resizeNode(root, width, height, false, true);
    }

    /**
     * Add direction constraints.
     */
    private void addDirectionConstraints(final ElkNode root) {

        for (ElkNode srcNode : root.getChildren()) {
            for (ElkEdge e : ElkGraphUtil.allOutgoingEdges(srcNode)) {

                // only handle edges between nodes of the same parent
                if (e.isHierarchical()) {
                    continue;
                }
                
                ElkNode tgtNode = ElkGraphUtil.connectableShapeToNode(e.getTargets().get(0));
                long src = nodeIndexMap.get(srcNode);
                long tgt = nodeIndexMap.get(tgtNode);

                int dim = Dim.XDIM;
                double separation = 2 * spacing;
                boolean swap = false;

                switch (direction) {
                case RIGHT:
                    separation += srcNode.getWidth();
                    break;
                case LEFT:
                    separation += tgtNode.getWidth();
                    swap = true;
                    break;
                case DOWN:
                    separation += srcNode.getHeight();
                    dim = Dim.YDIM;
                    break;
                case UP:
                    separation += tgtNode.getHeight();
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
