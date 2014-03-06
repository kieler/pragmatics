/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.cola.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.adaptagrams.Rectangle;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * @author uru
 * 
 */
public class CNode {

    public final KNode origin;
    public final int cIndex;

    public final Rectangle rect;

    final protected List<CNode> children;
    final protected List<CEdge> outgoingEdges;
    final protected List<CEdge> incomingEdges;
    final protected List<CPort> ports;

    final Random r = new Random();

    /**
     * .
     */
    public CNode(final CGraph graph, final KNode node) {

        this.origin = node;

        KShapeLayout layout = node.getData(KShapeLayout.class);

        // get margins
        Margins margin = node.getData(KShapeLayout.class).getProperty(LayoutOptions.MARGINS);

        // x X y Y meaning x width y height
        // Rectangle r =
        // new Rectangle(layout.getXpos(), layout.getXpos() + layout.getWidth(),
        // layout.getYpos(), layout.getYpos() + layout.getHeight());

        // constrained layout considers previous positions, to make it independent from
        // any weird layout stuff used before we run it, use 0 as initial positions for all
        // rects
        int rand = 0;// r.nextInt(200);
        int randy = 0;// r.nextInt(200);
        rect =
                new Rectangle(rand - margin.left, rand + layout.getWidth() + margin.right, rand
                        - margin.top, rand + layout.getHeight() + margin.bottom);
        cIndex = graph.nodeIndex++;

        // register in graph
        graph.nodes.add(rect);

        // setup the internal lists
        children = new ArrayList<CNode>(node.getChildren().size());
        outgoingEdges = new ArrayList<CEdge>(node.getOutgoingEdges().size());
        incomingEdges = new ArrayList<CEdge>(node.getIncomingEdges().size());
        ports = new ArrayList<CPort>(node.getPorts().size());
    }

    /**
     * @return the outgoingEdges
     */
    public List<CEdge> getOutgoingEdges() {
        return outgoingEdges;
    }

    /**
     * @return the incomingEdges
     */
    public List<CEdge> getIncomingEdges() {
        return incomingEdges;
    }

    /**
     * @return the ports
     */
    public List<CPort> getPorts() {
        return ports;
    }
}
