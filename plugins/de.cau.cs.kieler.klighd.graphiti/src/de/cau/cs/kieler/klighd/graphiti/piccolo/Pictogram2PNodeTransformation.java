/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.util.AlgorithmsSwitch;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.ManhattanConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.util.PictogramsSwitch;

import de.cau.cs.kieler.klighd.IModelTransformation;
import de.cau.cs.kieler.klighd.piccolo.PSWTAdvancedPath;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * A transformation from a Graphiti Pictogram model to a list of Piccolo nodes.
 * 
 * @author mri
 */
public class Pictogram2PNodeTransformation implements IModelTransformation<Diagram, List<PNode>> {

    /** the Pictogram color for white. */
    private static final Color WHITE = StylesFactory.eINSTANCE.createColor();
    /** the Pictogram color for black. */
    private static final Color BLACK = StylesFactory.eINSTANCE.createColor();

    // CHECKSTYLEOFF MagicNumber
    static {
        WHITE.setRed(255);
        WHITE.setGreen(255);
        WHITE.setBlue(255);
        BLACK.setRed(0);
        BLACK.setGreen(0);
        BLACK.setBlue(0);
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Constructs a Pictogram2PNodeTransformation.
     */
    public Pictogram2PNodeTransformation() {

    }

    /**
     * Performs the actual transformation from the Pictogram model to the Piccolo node.
     * 
     * @param diagram
     *            the Pictogram diagram
     * @return the PNode
     */
    public List<PNode> transform(final Diagram diagram) {
        List<PNode> layerRoots = new LinkedList<PNode>();
        // use two layers, one for nodes and one for edges
        PNode nodes = new PNode();
        PNode edges = new PNode();
        layerRoots.add(edges);
        layerRoots.add(nodes);
        // determine default colors from the diagram graphics algorithm
        GraphicsAlgorithm ga = diagram.getGraphicsAlgorithm();
        Color fc, bc;
        if (diagram.getGraphicsAlgorithm() != null) {
            fc = getForegroundColor(ga, BLACK);
            bc = getBackgroundColor(ga, WHITE);
        } else {
            fc = BLACK;
            bc = WHITE;
        }
        // transform shapes
        for (Shape shape : diagram.getChildren()) {
            transformShape(nodes, shape, fc, bc);
        }
        // transform connections
        for (Connection connection : diagram.getConnections()) {
            transformConnection(edges, connection);
        }
        return layerRoots;
    }

    /**
     * Transforms a Pictogram shape to a Piccolo node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param shape
     *            the shape
     */
    private void transformShape(final PNode parent, final Shape shape, final Color fc,
            final Color bc) {
        ShapeNode shapeNode = new ShapeNode(shape);
        parent.addChild(shapeNode);
        // determine colors and transform graphics algorithm
        GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
        Color gaFc, gaBc;
        if (ga != null) {
            gaFc = getForegroundColor(ga, fc);
            gaBc = getBackgroundColor(ga, bc);
            PNode gaNode =
                    transformGraphicsAlgorithm(shapeNode, shape.getGraphicsAlgorithm(), gaFc, gaBc);
            // the position of the graphics algorithm determines the position of the shape, which is
            // represented by an individual node, so the translation has to be moved to that node to
            // maintain correct positioning for child shapes
            gaNode.translate(-ga.getX(), -ga.getY());
            shapeNode.translate(ga.getX(), ga.getY());

        } else {
            gaFc = fc;
            gaBc = bc;
        }
        // handle child shapes in case of a container shape
        if (shape instanceof ContainerShape) {
            ContainerShape containerShape = (ContainerShape) shape;
            for (Shape childShape : containerShape.getChildren()) {
                transformShape(shapeNode, childShape, gaFc, gaBc);
            }
        }
//        // handle anchors
//        PBounds bounds = node.getGlobalBounds();
//        for (Anchor anchor : shape.getAnchors()) {
//            if (anchor.getGraphicsAlgorithm() != null) {
//                GraphicsAlgorithm ga = anchor.getGraphicsAlgorithm();
//                anchorPositions.put(anchor,
//                        new java.awt.Point((int) (bounds.x + ga.getX() + ga.getWidth() / 2),
//                                (int) (bounds.y + ga.getY() + ga.getHeight() / 2)));
//            }
//        }
        shapeNode.setVisible(shape.isVisible());
    }

    /**
     * Transforms a Pictogram connection to a Piccolo node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param connection
     *            the connection
     */
    private void transformConnection(final PNode parent, final Connection connection) {
        PNode connectionNode = new PictogramsSwitch<PNode>() {
            public PNode caseFreeFormConnection(final FreeFormConnection ffConnection) {
                return null;
            }

            public PNode caseManhattanConnection(final ManhattanConnection mhConnection) {
                return null;
            }
        }.doSwitch(connection);
        // parent.addChild(connectionNode);
    }

    /**
     * Transforms a Pictogram Graphics Algorithm to a Piccolo node and adds it to the children of
     * the given parent node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param ga
     *            the graphics algorithm
     * @param fc
     *            the default foreground color for this graphics algorithm
     * @param bc
     *            the default background color for this graphics algorithm
     * @return the Piccolo node representing the graphics algorithm
     */
    private PNode transformGraphicsAlgorithm(final PNode parent, final GraphicsAlgorithm ga,
            final Color fc, final Color bc) {
        PNode node = new AlgorithmsSwitch<PNode>() {
            public PNode caseRectangle(final Rectangle object) {
                return transformRectangle(object, fc, bc);
            }

            public PNode caseRoundedRectangle(final RoundedRectangle object) {
                return transformRoundedRectangle(object, fc, bc);
            }

            public PNode caseEllipse(final Ellipse object) {
                return transformEllipse(object, fc, bc);
            }

            public PNode casePolygon(final Polygon object) {
                return transformPolygon(object, fc, bc);
            }

            public PNode casePolyline(final Polyline object) {
                return transformPolyline(object, fc, bc);
            }

            public PNode caseText(final Text object) {
                return transformText(object, fc, bc);
            }

            public PNode caseMultiText(final MultiText object) {
                return transformText(object, fc, bc);
            }

            public PNode caseImage(final Image object) {
                return transformImage(object);
            }
        }.doSwitch(ga);
        // ignore the node if no PNode representation could be found
        if (node != null) {
            node.translate(ga.getX(), ga.getY());
            parent.addChild(node);
            // transform child graphics algorithms
            for (GraphicsAlgorithm childGa : ga.getGraphicsAlgorithmChildren()) {
                Color childFc = getForegroundColor(childGa, fc);
                Color childBc = getBackgroundColor(childGa, bc);
                transformGraphicsAlgorithm(node, childGa, childFc, childBc);
            }
        }
        return node;
    }

    private PNode transformRectangle(final Rectangle r, final Color fc, final Color bc) {
        PSWTAdvancedPath rect = PSWTAdvancedPath.createRectangle(0, 0, r.getWidth(), r.getHeight());
        rect.setLineWidth(r.getLineWidth());
        if (r.getLineVisible()) {
            rect.setStrokeColor(transformColor(fc));
        } else {
            rect.setStrokeColor(null);
        }
        if (r.getFilled()) {
            rect.setPaint(transformColor(bc));
        } else {
            rect.setPaint(null);
        }
        return rect;
    }

    private PNode transformRoundedRectangle(final RoundedRectangle rr, final Color fc,
            final Color bc) {
        PSWTAdvancedPath rrect =
                PSWTAdvancedPath.createRoundRectangle(0, 0, rr.getWidth(), rr.getHeight(),
                        rr.getCornerWidth(), rr.getCornerHeight());
        rrect.setLineWidth(rr.getLineWidth());
        if (rr.getLineVisible()) {
            rrect.setStrokeColor(transformColor(fc));
        } else {
            rrect.setStrokeColor(null);
        }
        if (rr.getFilled()) {
            rrect.setPaint(transformColor(bc));
        } else {
            rrect.setPaint(null);
        }
        return rrect;
    }

    private PNode transformEllipse(final Ellipse e, final Color fc, final Color bc) {
        PSWTAdvancedPath ellipse =
                PSWTAdvancedPath.createEllipse(0, 0, e.getWidth(), e.getHeight());
        ellipse.setLineWidth(e.getLineWidth());
        if (e.getLineVisible()) {
            ellipse.setStrokeColor(transformColor(fc));
        } else {
            ellipse.setStrokeColor(null);
        }
        if (e.getFilled()) {
            ellipse.setPaint(transformColor(bc));
        } else {
            ellipse.setPaint(null);
        }
        return ellipse;
    }

    private PNode transformPolygon(final Polygon p, final Color fc, final Color bc) {
        if (p.getPoints().size() > 0) {
            Point2D[] points = new Point2D[p.getPoints().size()];
            int i = 0;
            for (Point point : p.getPoints()) {
                points[i++] = new java.awt.Point(point.getX(), point.getY());
            }
            PSWTAdvancedPath polygon = PSWTAdvancedPath.createPolygon(points);
            polygon.setLineWidth(p.getLineWidth());
            if (p.getLineVisible()) {
                polygon.setStrokeColor(transformColor(fc));
            } else {
                polygon.setStrokeColor(null);
            }
            if (p.getFilled()) {
                polygon.setPaint(transformColor(bc));
            } else {
                polygon.setPaint(null);
            }
            return polygon;
        }
        return null;
    }

    private PNode transformPolyline(final Polyline p, final Color fc, final Color bc) {
        if (p.getPoints().size() > 0) {
            Point2D[] points = new Point2D[p.getPoints().size()];
            int i = 0;
            for (Point point : p.getPoints()) {
                points[i++] = new java.awt.Point(point.getX(), point.getY());
            }
            PSWTAdvancedPath line = PSWTAdvancedPath.createPolyline(points);
            line.setLineWidth(p.getLineWidth());
            if (p.getLineVisible()) {
                line.setStrokeColor(transformColor(fc));
            } else {
                line.setStrokeColor(null);
            }
            if (p.getFilled()) {
                line.setPaint(transformColor(bc));
            } else {
                line.setPaint(null);
            }
            return line;
        }
        return null;
    }

    private PNode transformText(final AbstractText t, final Color fc, final Color bc) {
        PSWTText text = new PSWTText();
        text.setFont(transformFont(t.getFont()));
        text.setText(t.getValue() != null ? t.getValue() : "");
        text.setTransparent(true);
        text.setWidth(t.getWidth());
        text.setHeight(t.getHeight());
        if (t.getLineVisible()) {
            text.setPenColor(transformColor(fc));
        } else {
            text.setPenColor(null);
        }
        if (t.getFilled()) {
            text.setBackgroundColor(transformColor(bc));
        } else {
            text.setBackgroundColor(null);
        }
        // text.setTranslation(t.getX(), t.getY());
        return text;
    }

    private PNode transformImage(final Image i) {
        // TODO implement this if possible (may not)
        return new PNode();
    }

    private java.awt.Color transformColor(final Color color) {
        return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    private java.awt.Font transformFont(final Font font) {
        int style = 0;
        if (font.isBold()) {
            style |= java.awt.Font.BOLD;
        }
        if (font.isItalic()) {
            style |= java.awt.Font.ITALIC;
        }
        return new java.awt.Font(font.getName(), style, font.getSize());
    }

    private Color getForegroundColor(final GraphicsAlgorithm ga, final Color def) {
        if (ga.getForeground() != null) {
            return ga.getForeground();
        }
        if (ga.getStyle() != null) {
            return getForegroundColor(ga.getStyle(), def);
        }
        return def;
    }

    private Color getForegroundColor(final Style style, final Color def) {
        if (style.getForeground() != null) {
            return style.getForeground();
        }
        if (style.getStyleContainer() != null && style.getStyleContainer() instanceof Style) {
            Style parentStyle = (Style) style.getStyleContainer();
            return getForegroundColor(parentStyle, def);
        }
        return def;
    }

    private Color getBackgroundColor(final GraphicsAlgorithm ga, final Color def) {
        if (ga.getBackground() != null) {
            return ga.getBackground();
        }
        if (ga.getStyle() != null) {
            return getBackgroundColor(ga.getStyle(), def);
        }
        return def;
    }

    private Color getBackgroundColor(final Style style, final Color def) {
        if (style.getBackground() != null) {
            return style.getBackground();
        }
        if (style.getStyleContainer() != null && style.getStyleContainer() instanceof Style) {
            Style parentStyle = (Style) style.getStyleContainer();
            return getBackgroundColor(parentStyle, def);
        }
        return def;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isModelSupported(final Object model) {
        return model instanceof Diagram;
    }

}
