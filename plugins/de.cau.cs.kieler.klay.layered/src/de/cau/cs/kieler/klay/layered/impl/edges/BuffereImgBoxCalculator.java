/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.impl.edges;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IBoxCalculator;

/**
 * this has been fun for testing, but has been overruled by ObjectBoxCalculator
 * 
 * @author car
 * @deprecated
 */
public class BuffereImgBoxCalculator extends AbstractAlgorithm implements IBoxCalculator {

    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;

    public BufferedImage buffImage;

    // contains nodes from which long edges are starting
    LinkedList<LEdge> longEdges = new LinkedList<LEdge>();

    // normal edges
    LinkedList<LEdge> shortEdges = new LinkedList<LEdge>();

    LinkedList<LNode> normalNodes = new LinkedList<LNode>();

    LinkedList<LNode> dummyNodes = new LinkedList<LNode>();

    private LayeredGraph layeredGraph;

    private static final int EDGE_PRECISION = 4;

    private static final int MIN_BOX_HEIGHT = 10;

    static JFrame debugframe = new JFrame("Graphical Debug");
    static PreviewPanel previewPanel = null;

    /** the DebugCanvas to use for debug-drawings **/
    private DebugCanvas debugCanvas;

    /**
     * {@inheritDoc}
     */
    public boolean addEdge(final BezierSpline spline) {

        for (BezierCurve curve : spline.getCurves()) {
            KVector start = curve.start;

            // the 2 defines how many points are caught ... the more .. the more precise
            // there's always the start point missing in pts
            int n = EDGE_PRECISION - 1;
            KVector[] pts = KielerMath.calcBezierPoints(curve.asVectorList(), n);
            buffImage.getGraphics().drawLine((int) start.x, (int) start.y, (int) pts[0].x,
                    (int) pts[0].y);
            for (int i = 0; i < n - 1; i++) {
                buffImage.getGraphics().drawLine((int) pts[i].x, (int) pts[i].y,
                        (int) pts[i + 1].x, (int) pts[i + 1].y);
            }

        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addEdge(final LEdge edge) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public LinkedList<Rectangle2D.Double> getBoxes(final LEdge edge) {

        LPort currentTarget = edge.getTarget();
        LPort currentSource = edge.getSource();

        LinkedList<Rectangle2D.Double> globBarray = new LinkedList<Rectangle2D.Double>();

        if (debugCanvas != null) {
            debugCanvas.clear();
        }

        System.out.println((currentSource.getNode().getPos().x + currentSource.getPos().x) + "/"
                + (currentSource.getNode().getPos().y + currentSource.getPos().y) + " -> "
                + (currentTarget.getNode().getPos().x + currentTarget.getPos().x) + "/"
                + (currentTarget.getNode().getPos().y + currentTarget.getPos().y));

        // System.out.println(currentTarget.getNode().getType());

        int defaultboxwidth = (int) spacing / 3;
        float reachedx = (float) currentSource.getPos().x + currentSource.getNode().getPos().x;
        int boxes = 0;

        // wander along the edge
        do {

            float targetx = (float) currentTarget.getPos().x + currentTarget.getNode().getPos().x;

            Rectangle2D.Double newBox = null, prevBox = null;

            while (reachedx < targetx - 1) {

                // a good starting point for the boxes is right ON the direct line between source
                // and target, this is basically the line-equation for this direct path
                float starty = (currentSource.getPos().y + currentSource.getNode().getPos().y)
                        + (((currentTarget.getPos().y + currentTarget.getNode().getPos().y) - (currentSource
                                .getPos().y + currentSource.getNode().getPos().y)) * (reachedx
                                + defaultboxwidth - (currentSource.getPos().x + currentSource
                                .getNode().getPos().x)))
                        / ((currentTarget.getPos().x + currentTarget.getNode().getPos().x) - (currentSource
                                .getPos().x + currentSource.getNode().getPos().x));

                newBox = new Rectangle2D.Double(reachedx, Math.max(0, starty - MIN_BOX_HEIGHT / 2),
                        defaultboxwidth, MIN_BOX_HEIGHT);

                // show initial position
                if (debugCanvas != null) {
                    debugCanvas.drawRectangle((float) newBox.x, (float) newBox.y,
                            (float) newBox.width, (float) newBox.height, DebugCanvas.Color.ORANGE);
                }

                // the first AND LAST box has to cover the complete first node
                if (boxes == 0 || reachedx + defaultboxwidth > targetx) {
                    if (newBox.y > currentSource.getNode().getPos().y) {
                        double diff = newBox.y - currentSource.getNode().getPos().y;
                        newBox.y -= diff;
                        newBox.height += diff;
                    }
                    newBox.height = Math.max(newBox.height, currentSource.getNode().getSize().y);
                }
                // test workaround
                // if (boxes > 15) {
                // newBox.y += 5;
                // }

                // ensure that the box is intersecting the previous box
                if (prevBox != null) {
                    // the old box is "below" the new Box
                    if (prevBox.y > newBox.y + newBox.height) {
                        newBox.height = prevBox.y - newBox.y + MIN_BOX_HEIGHT;
                    }

                    // or "over" the new box
                    if (prevBox.y + prevBox.height < newBox.y) {
                        double diff = newBox.y - (prevBox.y + prevBox.height);
                        newBox.y -= diff;
                        newBox.height += diff;
                    }
                }

                // insert some space between boxes and actual nodes
                int boxSpace = 0;

                // enlarge boxes as long as there is space
                while (isFreeLine(buffImage, newBox.x, Math.max(0, newBox.y - boxSpace),
                        newBox.width)
                        && newBox.y > 0) {
                    newBox.y--;
                    newBox.height++;
                }
                while (isFreeLine(buffImage, newBox.x, newBox.y + newBox.height, newBox.width)
                        && newBox.y + newBox.height < buffImage.getHeight()) {
                    newBox.height++;
                }

                // enlarge boxes partly over edges that already have been drawn
                // TODO: this is not really working
                double left = newBox.x;
                double right = newBox.x + newBox.width;
                if (!isFreeLine(buffImage, left, newBox.y + newBox.height, 1)) {
                    while (isFreeLine(buffImage, right, newBox.y + newBox.height, 1)
                            && newBox.y + newBox.height < buffImage.getHeight()) {
                        newBox.height++;
                    }
                }
                if (!isFreeLine(buffImage, right - 1, newBox.y + newBox.height, 1)) {
                    while (isFreeLine(buffImage, left, newBox.y + newBox.height, 1)
                            && newBox.y + newBox.height < buffImage.getHeight()) {
                        newBox.height++;
                    }
                }

                // add the new box
                globBarray.add(newBox);
                boxes++;
                prevBox = newBox;
                reachedx += newBox.width;

                // and draw the new box
                if (debugCanvas != null) {
                    debugCanvas.drawRectangle((float) newBox.x, (float) newBox.y,
                            (float) newBox.width, (float) newBox.height, DebugCanvas.Color.BLUE);
                }

            }

            if (debugCanvas != null) {
                // line from source->target
                debugCanvas.drawLine(
                        (currentSource.getNode().getPos().x + currentSource.getPos().x),
                        (currentSource.getNode().getPos().y + currentSource.getPos().y),
                        (currentTarget.getNode().getPos().x + currentTarget.getPos().x),
                        (currentTarget.getNode().getPos().y + currentTarget.getPos().y),
                        DebugCanvas.Color.RED);
                // draw dummy nodes
                if (currentTarget.getNode().getProperty(Properties.NODE_TYPE) == Properties.NodeType.LONG_EDGE) {
                    debugCanvas.drawFilledCircle(
                            (currentTarget.getNode().getPos().x + currentTarget.getPos().x),
                            (currentTarget.getNode().getPos().y + currentTarget.getPos().y), 10,
                            DebugCanvas.Color.CYAN);
                }
            }

            // find next target
            currentSource = currentTarget;
            for (LPort iterPort : currentTarget.getNode().getPorts(PortType.OUTPUT)) {
                for (LEdge iterEdge : iterPort.getEdges()) {
                    // if (iterEdge.getOrigin() != null) {
                    if (iterEdge.getOrigin() != null) {
                        currentTarget = iterEdge.getTarget();
                        break;
                    }
                }
                break;
            }

        } while (currentSource.getNode().getProperty(Properties.NODE_TYPE) == Properties.NodeType.LONG_EDGE);

        previewPanel.updateBarrray(globBarray);
        return globBarray;
    }

    /**
     * {@inheritDoc}
     */
    public LinkedList<Line2D.Double> getLines(final LinkedList<Rectangle2D.Double> boxes) {
        // LinkedList<Line2D.Float> larray = new Line2D.Float[boxes.length];
        LinkedList<Line2D.Double> larray = new LinkedList<Line2D.Double>();

        if (boxes.size() == 0) {
            return larray;
        }

        // first and last element are null
        larray.add(null);

        // remember first box, then iterate over all boxes and write the corresponding lines
        Rectangle2D.Double oldBox = boxes.getFirst();
        for (Rectangle2D.Double box : boxes) {
            if (!box.equals(boxes.getFirst())) {
                if (oldBox != null && box != null) {
                    // since intersection to those objects threw strange results once, try it this
                    // way
                    if (oldBox.x + oldBox.width == box.x) {
                        double matchx = box.x;
                        // max start
                        double intersectfrom = Math.max(box.y, oldBox.y);
                        // min end
                        double intersecttill = Math.min(box.y + box.height, oldBox.y
                                + oldBox.height);
                        if (intersectfrom < intersecttill) {
                            larray.add(new Line2D.Double(matchx, intersectfrom, matchx,
                                    intersecttill));
                            // System.out.println("intersect " + larray[i].getP1() + "/" +
                            // larray[i].getP2());
                        } else {
                            // if no itersection --> add a null !
                            larray.add(null);
                        }
                    }
                }
                oldBox = box;
            }

        }
        previewPanel.updateLarrray(larray);

        return larray;
    }

    /**
     * {@inheritDoc}
     */
    public void initialize(LayeredGraph graph, DebugCanvas debugCanvas) {
        this.debugCanvas = debugCanvas;
        initialize(graph);
    }

    /**
     * {@inheritDoc}
     */
    public void initialize(final LayeredGraph lg) {
        this.layeredGraph = lg;

        // Debug Frame init
        if (!debugframe.isVisible()) {
            debugframe.setSize((int) layeredGraph.getSize().x, (int) layeredGraph.getSize().y);
            previewPanel = new PreviewPanel();
            debugframe.add(previewPanel, -1);
            debugframe.setLocationRelativeTo(null);
            debugframe.pack();
            debugframe.setVisible(true);
            debugframe.toFront();
        }
        previewPanel.updateBarrray(null);
        previewPanel.updateLarrray(null);

        // contains nodes from which long edges are starting
        longEdges = new LinkedList<LEdge>();

        // normal edges
        shortEdges = new LinkedList<LEdge>();

        normalNodes = new LinkedList<LNode>();

        dummyNodes = new LinkedList<LNode>();

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.LONG_EDGE) {
                    normalNodes.add(node);
                    for (LPort port : node.getPorts(PortType.OUTPUT)) {
                        for (LEdge edge : port.getEdges()) {
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) == Properties.NodeType.LONG_EDGE) {
                                longEdges.add(edge);
                            } else {
                                shortEdges.add(edge);
                            }
                        }
                    }
                } else {
                    dummyNodes.add(node);
                }
            }
        }

        buffImage = new BufferedImage((int) layeredGraph.getSize().x,
                (int) layeredGraph.getSize().y, BufferedImage.TYPE_INT_RGB);

        int additionalNodeSpace = 10;
        for (LNode node : normalNodes) {
            buffImage.getGraphics().fillRect((int) node.getPos().x, (int) node.getPos().y,
                    (int) node.getSize().x, (int) node.getSize().y + additionalNodeSpace);
        }

        for (LEdge edge : shortEdges) {
            buffImage.getGraphics().drawLine(
                    (int) (edge.getSource().getNode().getPos().x + edge.getSource().getPos().x),
                    (int) (edge.getSource().getNode().getPos().y + edge.getSource().getPos().y),
                    (int) (edge.getTarget().getNode().getPos().x + edge.getTarget().getPos().x),
                    (int) (edge.getTarget().getNode().getPos().y + edge.getTarget().getPos().y));
        }

    }

    private static boolean isFreeLine(final BufferedImage img, final double x, final double y,
            final double width) {

        /*
         * if (img.getHeight() < y || y < 0 || x < 0 || x + width < img.getWidth()) { return false;
         * }
         */
        if (img == null) {
            return false;
        }
        for (int xp = (int) x; xp < x + width; xp++) {

            // RGB value where nothing drawn is -16777216
            if (img.getRGB(Math.min((int) xp, img.getWidth() - 1), Math.min((int) y, img
                    .getHeight() - 1)) != -16777216) {
                return false;
            }
        }

        return true;
    }

    /**
     * debug for graphics
     * 
     * @author car
     * @author uru
     * 
     */
    public class PreviewPanel extends JPanel {
        LinkedList<Rectangle2D.Double> globBarray;
        LinkedList<Line2D.Double> globLarray;

        public PreviewPanel() {
            super();
        }

        public void updateBarrray(final LinkedList<Rectangle2D.Double> newGlobBarray) {
            this.globBarray = newGlobBarray;
            refresh();
        }

        public void updateLarrray(final LinkedList<Line2D.Double> newGlobLarray) {
            this.globLarray = newGlobLarray;
            refresh(); // good place for breakpoint
        }

        private void refresh() {
            revalidate();
            repaint();
            this.setPreferredSize(new Dimension((int) layeredGraph.getSize().x, (int) layeredGraph
                    .getSize().y));
            ((JFrame) getParent().getParent().getParent().getParent()).pack();
            int i = 1; // just for breakpoint
            int j = i * 2;
        }

        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g; // we need a Graphics2D context
            g2.drawImage(buffImage, null, 0, 0);

            g.setColor(Color.RED);
            if (layeredGraph != null) {
                for (Layer layer : layeredGraph.getLayers()) {
                    for (LNode node : layer.getNodes()) {
                        g.fillOval((int) node.getPos().x, (int) node.getPos().y, (int) node
                                .getSize().x, (int) node.getSize().y);
                    }
                }
            }

            if (dummyNodes != null) {
                for (LNode node : dummyNodes) {
                    g.setColor(Color.cyan);
                    g.fillOval((int) node.getPos().x - 5, (int) node.getPos().y - 5, 10, 10);
                }
            }

            if (globBarray != null) {
                for (Rectangle2D.Double box : globBarray) {
                    if (box != null) {
                        g.setColor(Color.GREEN);
                        g.drawRect((int) box.x, (int) box.y, (int) box.width, (int) box.height);
                    }
                }
            }
            if (globLarray != null) {
                for (Line2D.Double line : globLarray) {
                    if (line != null) {
                        g.setColor(new Color(100, 100, 255));
                        g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(),
                                (int) line.getY2());
                    }
                }
            }

        }
    }

}
