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

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * @author dag
 *
 */
public class OneDCompactor implements ILayoutProcessor {

    private Map<Rectangle2D, LGraphElement> boxMap = Maps.newHashMap();
    private List<Node> boxes = Lists.newArrayList();

    // private DebugFrame debugFrame = new DebugFrame(); // maybe not static to get 1 frame per
    // connected component

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("1 D compacting", 1);

        // drawDebugView(layeredGraph.getSize());

        // ulf
        boxes.clear();

        // 1) Derive all vertical edges of either nodes or edge segments
        // for (LNode n : layeredGraph.getLayerlessNodes()) {
        for (Layer l : layeredGraph) {
            for (LNode n : l) {

                Rectangle2D.Double rect =
                        new Rectangle2D.Double(n.getPosition().x, n.getPosition().y, n.getSize().x,
                                n.getSize().y);

                Node node = new Node(n, rect);
                boxes.add(node);

                for (LEdge e : n.getOutgoingEdges()) {
                    // FIXME consider start and end
                    Iterator<KVector> bends = e.getBendPoints().iterator();
                    if (bends.hasNext()) {
                        KVector start = bends.next();
                        while (bends.hasNext()) {
                            KVector current = bends.next();

                            if (Math.abs(start.x - current.x) < 0.001) {
                                Rectangle2D.Double rect2 =
                                        new Rectangle2D.Double(start.x,
                                                Math.min(start.y, current.y), start.x + 1,
                                                Math.abs(start.y - current.y));
                                node = new Node(e, rect);// rect2?
                                // boxes.add(node);
                            }

                            start = current;
                        }
                    }
                }
            }
        }

        // 2) Sort them
        Collections.sort(boxes, new Comparator<Node>() {
            /**
             * {@inheritDoc}
             */
            public int compare(Node o1, Node o2) {
                if (o1.getMinX() < o2.getMinX()) {
                    return -1;
                } else if (Math.abs(o1.getMinX() - o2.getMinX()) < 0.001) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        System.out.println(boxes.size());
        System.out.println(boxes);
        new DebugFrame(layeredGraph.getSize());

        // 3) generate constraints
        for (Node box1 : boxes) {
            for (Node box2 : boxes) {
                if (box1 != box2) {

                    double minDist = 20;

                    if (box1.getMinX() < box2.getMinX()) {

                        // Edge edge = new Edge(box1.getWidth() + minDist, box1, box2);
                        // Edges have to point "backwards"
                        Edge edge = new Edge(box1.getWidth() + minDist, box2, box1);

                        List<LNode> tgts = Lists.newArrayList();
                        for (LEdge ee : ((LNode) box1.graphElement).getOutgoingEdges()) {
                            tgts.add(ee.getTarget().getNode());
                        }
                        if (tgts.contains(box2.graphElement)) {
                            System.out.println("adding edge " + box1.graphElement + " "
                                    + box2.graphElement + " " + (box1.getWidth() + minDist));
                            box2.outgoingEdges.add(edge);
                        }
                    } else {

                        // constraint is generated anyway as we iterate over V x V
                        // Edge edge = new Edge(box2.getWidth() + minDist, box2, box1);
                        // box2.outgoingEdges.add(edge);
                    }
                }
            }
        }

        // 4) longest path layering ...
        for (Node n : boxes) {
            visit(n);
        }

        int max = 0;
        for (Node n : boxes) {
            max = Math.max(n.position, max);
        }

        for (Node n : boxes) {
            System.out.println(n.graphElement + " " + (n.position));
        }

        progressMonitor.done();

    }

    private int visit(final Node n) {
        if (n.position >= 0) {
            // already visited
            return n.position;
        } else {
            int maxId = 0;
            for (Edge e : n.outgoingEdges) {
                // no selfloops
                if (!e.tgt.equals(n) && e.tgt != n && e.tgt.graphElement != n.graphElement) {
                    int targetId = visit(e.tgt);
                    maxId = Math.max(maxId, targetId + (int) e.weight);
                }
            }
            n.position = maxId;
            return maxId;
        }
    }

    private static class Node extends Rectangle2D.Double {

        int position = -1;

        LGraphElement graphElement;
        List<Edge> outgoingEdges = Lists.newLinkedList();

        public Node(LGraphElement graphElement, Rectangle2D.Double bounds) {
            this.graphElement = graphElement;
            setRect(bounds);
        }

    }

    private static class Edge {

        public Edge(double weight, Node src, Node tgt) {
            super();
            this.weight = weight;
            this.src = src;
            this.tgt = tgt;
        }

        double weight;
        Node src;
        Node tgt;
    }

    private void drawDebugView(final Graphics g, final int width, final int height) {

        double xMin = 0, xMax = 0, yMin = 0, yMax = 0;

        for (Node box : boxes) {
            if (box.getMinX() < xMin)
                xMin = box.getMinX();
            if (box.getMaxX() > xMax)
                xMax = box.getMaxX();
            if (box.getMinY() < yMin)
                yMin = box.getMinY();
            if (box.getMaxY() > yMax)
                yMax = box.getMaxY();
        }
        
        double f = width / (xMax - xMin);
        //TODO height
        double xOffset = -xMin, yOffset = -yMin;
        
        System.out.println(width+"x"+height+" f:"+f+" xOffset:"+xOffset+" yOffset:"+yOffset);

        for (Node node : boxes) {
            g.drawRect((int) Math.round(node.x * f + xOffset), (int) Math.round(node.y * f + yOffset),
                    (int) Math.round(node.width * f), (int) Math.round(node.height * f));
        }

    }

    private class DebugFrame extends JFrame {

        public DebugFrame(KVector size) {
            super("Debug Frame");

            setContentPane(new DrawPane());

            setSize((int) Math.round(size.x) + 300, (int) Math.round(size.y) + 200); // FIXME

            setVisible(true);
        }

        private class DrawPane extends JPanel {
            public void paintComponent(Graphics g) {
                drawDebugView(g, this.getWidth(), this.getHeight());
            }
        }

    }
}
