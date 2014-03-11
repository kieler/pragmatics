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

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.cola.graph.CGraph;
import de.cau.cs.kieler.kiml.cola.graph.CNode;
import de.cau.cs.kieler.kiml.cola.graph.CPort;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;

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

        System.out.println("Called layout "
                + parentNode.getData(KShapeLayout.class)
                        .getProperty(LayoutOptions.LAYOUT_HIERARCHY));

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

        // calculate margins
        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.calculateNodeMargins(adapter);

        // execute layout algorithm
        graph = new CGraph(parentNode);

        BasicProgressMonitor bpm = new BasicProgressMonitor();
        new DirectionConstraintProcessor().process(graph, bpm);
        new PortConstraintProcessor().process(graph, bpm);
        new NonUniformEdgeLengthProcessor().process(graph, bpm);

        System.out.println(parentNode);
        System.out.println(Arrays.toString(graph.idealEdgeLengths));

        // for the moment fix the issue where the edgelengths do not allow 0
        for (int i = 0; i < graph.idealEdgeLengths.length; ++i) {
            if (graph.idealEdgeLengths[i] == 0) {
                graph.idealEdgeLengths[i] = borderSpacing;
            }
        }

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 50, false,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());

        // run some w/o overlap
        algo.makeFeasible();

        int runs = 10;
        
        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out" + i + ".svg");

            // System.out.println(i);
        }

        // do some with overlap
        algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 1, true,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());

        algo.makeFeasible();

        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out99overlap" + i + ".svg");

            // System.out.println(i);
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
        for (CNode n : graph.getChildren()) {
            Rectangle r = n.rect;
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

            // ports
            for (CPort p : n.getPorts()) {
                if (p.cEdgeIndex != -1) {
                    KShapeLayout portLayout = p.origin.getData(KShapeLayout.class);
                    // ports are relative to the parent in KGraph
                    portLayout.setXpos((float) p.getActualXPos());
                    portLayout.setYpos((float) p.getActualYPos());
                }
            }
        }

        /*
         * External Ports
         */
        for (CPort p : graph.getExternalPorts()) {
            Rectangle r = p.rect;

            KShapeLayout layout = p.origin.getData(KShapeLayout.class);
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

}
