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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/* 
 * 
 * 
 * changes in:
 * IntermediateProcessorStrategy
 * GraphConfigurator
 * Properties
 */

/**
 * @author dag
 *
 */
public class OneDCompactor implements ILayoutProcessor {

    private List<Pair<LGraphElement, Rectangle2D.Double>> boxes =
            new ArrayList<Pair<LGraphElement, Rectangle2D.Double>>();

    private JTabbedPane tabpane;

    private JFrame frm;

    private String firstConnectedComponent = null;

    private List<String> connectedComponents = new ArrayList<String>();

    private List<Pair<LGraph, DebugFrame>> panels = new ArrayList<Pair<LGraph, DebugFrame>>();

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        // redraw debug view
        if (firstConnectedComponent == null
                || firstConnectedComponent.equals(layeredGraph.toString())) { // FIXME compare

            if (frm != null) {
                frm.dispose();
            }

            firstConnectedComponent = layeredGraph.toString();

            tabpane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

            frm = new JFrame("Debug View");

            frm.setSize(1200, 700);

            frm.add(tabpane);

            frm.setVisible(true);
        }

        progressMonitor.begin("1 D compacting", 1);
        // +++++++++++++++++++
        boxes.clear();

        // collecting positions of graph elements
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                // add all nodes of type NORMAL because dummy nodes shouldn't have any size but
                // sometimes are 1 high.
                // FIXME dummy nodes can't be drawn now
                if (node.getNodeType().equals(NodeType.NORMAL)) {
                    Rectangle2D.Double r =
                            new Rectangle2D.Double(node.getPosition().x, node.getPosition().y,
                                    node.getSize().x, node.getSize().y);

                    boxes.add(new Pair<LGraphElement, Rectangle2D.Double>(node, r));
                }

                // add vertical edge segments
                for (LEdge edge : node.getOutgoingEdges()) {
                    if (edge.getBendPoints().iterator().hasNext()) {

                        Iterator<KVector> bends = edge.getBendPoints().iterator();
                        KVector bend1 = bends.next();
                        KVector bend2 = bends.next();
                        // reliable?
                        double x, y, h;
                        if (bend1.y < bend2.y) {
                            x = bend1.x;
                            y = bend1.y;
                            h = bend2.y - y;
                        } else {
                            x = bend2.x;
                            y = bend2.y;
                            h = bend1.y - y;
                        }

                        Rectangle2D.Double rEdge = new Rectangle2D.Double(x, y, 0, h);

                        boxes.add(new Pair<LGraphElement, Rectangle2D.Double>(edge, rEdge));
                    }
                }
            }
        }

        drawDebugView(boxes, (int) Math.round(layeredGraph.getSize().x),
                (int) Math.round(layeredGraph.getSize().y), layeredGraph);

        // resize boxes to include spacing
        double objSpacing = layeredGraph.getProperty(InternalProperties.SPACING);
        double edgeSpacing =
                layeredGraph.getProperty(InternalProperties.SPACING)
                        * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        for (Pair<LGraphElement, Rectangle2D.Double> box : boxes) {

            double spacing = edgeSpacing;
            if (box.getFirst().getClass() == LNode.class) {
                spacing = objSpacing;
            }

            box.getSecond().x -= spacing / 2;
            box.getSecond().y -= spacing / 2;
            box.getSecond().width += spacing;
            box.getSecond().height += spacing;
        }

        /*
         * // constraint stuff // TODO minX > .. and hitbox collision // constraint only if edge
         * present?? CNode constraintGraph = new CNode(null, 0); for (Pair<LGraphElement,
         * Rectangle2D.Double> box1 : boxes) {
         * 
         * CNode cn = new CNode(box1, 0); constraintGraph.children.add(cn);
         * 
         * for (Pair<LGraphElement, Rectangle2D.Double> box2 : boxes) { // intersects? if (box1 !=
         * box2 && box1.getSecond().x + box1.getSecond().width <= box2.getSecond().x &&
         * box2.getSecond().y + box2.getSecond().height >= box1.getSecond().y && box2.getSecond().y
         * <= box1.getSecond().y + box1.getSecond().height) {
         * 
         * cn.children.add(new CNode(box2, Math.abs(box2.getSecond().x - (box1.getSecond().x +
         * box1.getSecond().width)))); // System.out.println("constraint: " +
         * box1.getFirst().toString() + " --> " // + box2.getFirst().toString()); } } }
         * 
         * // find maximal possible distance to move nodes for (CNode cn1 :
         * constraintGraph.children) { for (CNode cn2 : cn1.children) { if (cn1.distance == 0 ||
         * cn1.distance > cn2.distance) { cn1.distance = cn2.distance; } }
         * System.out.println(cn1.box.getFirst().toString() + " move right: " + cn1.distance); }
         */
        // sorting
        Collections.sort(boxes, new Comparator<Pair<LGraphElement, Rectangle2D.Double>>() {

            public int compare(Pair<LGraphElement, Rectangle2D.Double> o1,
                    Pair<LGraphElement, Rectangle2D.Double> o2) {
                if (o1.getSecond().x < o2.getSecond().x) {
                    return -1;
                }
                if (o1.getSecond().x == o2.getSecond().x) { // <0.01 ??
                    return 0;
                }
                return 0;
            }

        });

        // move boxes
        for (Pair<LGraphElement, Rectangle2D.Double> box1 : boxes) {

            Iterator<Pair<LGraphElement, Rectangle2D.Double>> boxIterator = boxes.iterator();

            double minDist = 0;

            // test for intersections with boxes left of box1
            while (boxIterator.hasNext()) {

                Pair<LGraphElement, Rectangle2D.Double> box2 = boxIterator.next();

//                if (box1 == box2) { // would not consider repositioned boxes
//                    break;
//                }

                // TODO what if there's no node left of box1 but still space to move
                double dist = box1.getSecond().x - box2.getSecond().x - box2.getSecond().width;
                // test > 0 to include repositioned boxes
                if (dist > 0 && (dist < minDist || minDist == 0)
                        && box2.getSecond().y + box2.getSecond().height >= box1.getSecond().y
                        && box2.getSecond().y <= box1.getSecond().y + box1.getSecond().height) {

                    minDist = dist;
                }
            }

            // move box/node
            box1.getSecond().x -= minDist;
            
            System.out.println(box1.getFirst().toString() +" : "+minDist);
            
            if (box1.getFirst().getClass() == LNode.class) {
                
                LNode node = (LNode)box1.getFirst();
                node.getPosition().x -= minDist;
            } else if (box1.getFirst().getClass() == LEdge.class) {
                
                // FIXME can't really refer to those bendpoints if more than 2
                LEdge edge = (LEdge)box1.getFirst();
                Iterator<KVector> bends = edge.getBendPoints().iterator();
                KVector bend1 = bends.next();
                KVector bend2 = bends.next();
                bend1.x -= minDist;
                bend2.x -= minDist;
            }
        }
        
        drawDebugView(boxes, (int) Math.round(layeredGraph.getSize().x),
                (int) Math.round(layeredGraph.getSize().y), layeredGraph);

        progressMonitor.done();

    }

    // private class CNode {
    // public Pair<LGraphElement, Rectangle2D.Double> box =
    // new Pair<LGraphElement, Rectangle2D.Double>();
    // public List<CNode> children = new ArrayList<CNode>();
    // public double distance;
    //
    // public CNode(Pair<LGraphElement, Rectangle2D.Double> box, double distance) {
    // this.box = box;
    // this.distance = distance;
    // }
    // }

    private void drawDebugView(final List<Pair<LGraphElement, Rectangle2D.Double>> boxes,
            final int width, final int height, final LGraph layeredGraph) {

        // double xMin = 0, xMax = 0, yMin = 0, yMax = 0;

        List<Rectangle> regularNodes = new ArrayList<Rectangle>();
        List<Rectangle> longEdgeNodes = new ArrayList<Rectangle>();
        List<Rectangle> vertEdgeSeg = new ArrayList<Rectangle>();

        // for (Pair<LGraphElement, Rectangle2D.Double> box : boxes) {
        // if (box.getSecond().getMinX() < xMin) {
        // xMin = box.getSecond().getMinX();
        // }
        // if (box.getSecond().getMaxX() > xMax) {
        // xMax = box.getSecond().getMaxX();
        // }
        // if (box.getSecond().getMinY() < yMin) {
        // yMin = box.getSecond().getMinY();
        // }
        // if (box.getSecond().getMaxY() > yMax) {
        // yMax = box.getSecond().getMaxY();
        // }
        // }
        //
        // System.out.println(xMin + " " + xMax + " - " + yMin + " " + yMax + " ^= "
        // + layeredGraph.getSize().x + " " + layeredGraph.getSize().y + " offset: "
        // + layeredGraph.getOffset().x + " " + layeredGraph.getOffset().y);
        //
        // xMax = layeredGraph.getSize().x;
        // yMax = layeredGraph.getSize().y;
        //
        // double f = width / (xMax - xMin); // TODO simplify; but seems that yMin might be offset
        //
        // double xOffset = -xMin, yOffset = -yMin;

        double f = width / layeredGraph.getSize().x;
        f = 1;
        double xOffset = layeredGraph.getOffset().x;
        double yOffset = layeredGraph.getOffset().y;

        for (Pair<LGraphElement, Rectangle2D.Double> box : boxes) {

            if (box.getFirst().getClass() == LNode.class) {

                LNode node = (LNode) box.getFirst();

                if (node.getNodeType().equals(NodeType.NORMAL)) {

                    regularNodes.add(new Rectangle((int) Math.round((box.getSecond().x + xOffset)
                            * f), (int) Math.round((box.getSecond().y + yOffset) * f), (int) Math
                            .round(box.getSecond().width * f),
                            (int) Math.round(box.getSecond().height * f)));
                }

                if (node.getNodeType().equals(NodeType.LONG_EDGE)) {
                    // FIXME some dummy nodes have height 1
                    longEdgeNodes
                            .add(new Rectangle(
                                    (int) Math.round((box.getSecond().x + xOffset) * f) - 1,
                                    (int) Math.round((box.getSecond().y + yOffset) * f) - 1, 2, 2));
                }
            } else { // probably an edge

                vertEdgeSeg.add(new Rectangle((int) Math.round((box.getSecond().x + xOffset) * f),
                        (int) Math.round((box.getSecond().y + yOffset) * f), (int) Math.round(box
                                .getSecond().width * f), (int) Math.round(box.getSecond().height
                                * f)));
            }
        }

        // removing already drawn graphs do be redrawn
//        for (Pair<LGraph, OneDCompactor.DebugFrame> panel : panels) {
//            if (panel.getFirst() == layeredGraph) { // FIXME doesn't work this way
//                tabpane.remove(panel.getSecond());
//                // remove panel
//            }
//        }
        // FIXME avoid concurrent modification exception if removing panel

        DebugFrame dFrame =
                new DebugFrame(width, height, layeredGraph, regularNodes, longEdgeNodes,
                        vertEdgeSeg);

        panels.add(new Pair<LGraph, OneDCompactor.DebugFrame>(layeredGraph, dFrame));
        tabpane.addTab("graph component", dFrame);

    }

    private class DebugFrame extends JPanel {

        private List<Rectangle> regularNodes = new ArrayList<Rectangle>();
        private List<Rectangle> longEdgeNodes = new ArrayList<Rectangle>();
        private List<Rectangle> vertEdgeSeg = new ArrayList<Rectangle>();

        private int BORDER_SPACING, OBJECT_SPACING, EDGE_SPACING;

        public DebugFrame(final int width, final int height, final LGraph layeredGraph,
                final List<Rectangle> regularNodes, final List<Rectangle> longEdgeNodes,
                final List<Rectangle> vertEdgeSeg) {

            // super("Debug Frame");

            this.setBackground(Color.white);
            this.setOpaque(true);

            this.regularNodes = regularNodes;
            this.longEdgeNodes = longEdgeNodes;
            this.vertEdgeSeg = vertEdgeSeg;

            this.BORDER_SPACING =
                    (int) Math.round(layeredGraph.getProperty(InternalProperties.BORDER_SPACING));
            this.OBJECT_SPACING =
                    (int) Math.round(layeredGraph.getProperty(InternalProperties.SPACING));
            this.EDGE_SPACING =
                    (int) Math.round(layeredGraph.getProperty(InternalProperties.SPACING)
                            * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR));

            // setContentPane(new DrawPane());

            setSize(width + 2 * BORDER_SPACING, height + 2 * BORDER_SPACING);

            setVisible(true);
        }

        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.setColor(Color.decode("#008800"));
            for (Rectangle rectangle : regularNodes) {
                g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y + BORDER_SPACING,
                        rectangle.width + OBJECT_SPACING, rectangle.height + OBJECT_SPACING);
            }

            g.setColor(Color.RED);
            for (Rectangle rectangle : vertEdgeSeg) {
                g.drawRect(rectangle.x + BORDER_SPACING - EDGE_SPACING, rectangle.y
                        + BORDER_SPACING - EDGE_SPACING, rectangle.width + 2 * EDGE_SPACING,
                        rectangle.height + 2 * EDGE_SPACING);
            }

            g.setColor(Color.black);
            for (Rectangle rectangle : regularNodes) {
                g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y + BORDER_SPACING,
                        rectangle.width, rectangle.height);
            }

            g.setColor(Color.red);
            for (Rectangle rectangle : longEdgeNodes) {
                g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y + BORDER_SPACING,
                        rectangle.width, rectangle.height);
            }

            g.setColor(Color.blue);
            for (Rectangle rectangle : vertEdgeSeg) {
                g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y + BORDER_SPACING,
                        rectangle.width, rectangle.height);
            }

        }

    }
}
