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

import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTPath;

/**
 * A transformation from a Graphiti Pictogram model to a Piccolo node.
 * 
 * @author mri
 */
public class Pictogram2PNodeTransformation {

    /** the attribute key for the Pictogram element. */
    private static final String PICTORGRAM_ATTR_KEY = "pictogramElement";

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
    public PNode transform(final Diagram diagram) {
        PNode graph = new PNode();
        // transform shapes
        for (Shape shape : diagram.getChildren()) {
            transformShape(graph, shape);
        }
        // transform connections
        for (Connection connection : diagram.getConnections()) {
            transformGraphicsAlgorithm(graph, connection.getGraphicsAlgorithm());
        }
        return graph;
    }

    /**
     * Transforms a Pictogram shape to a Piccolo node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param shape
     *            the shape
     */
    private void transformShape(final PNode parent, final Shape shape) {
        if (!shape.isVisible()) {
            return;
        }
        PNode node = transformGraphicsAlgorithm(parent, shape.getGraphicsAlgorithm());
        // ignore if no fitting PNode could be created
        if (node != null) {
            // add the Pictogram element as attribute
            node.addAttribute(PICTORGRAM_ATTR_KEY, shape);
            // handle child shapes in case of a container shape
            if (shape instanceof ContainerShape) {
                ContainerShape containerShape = (ContainerShape) shape;
                for (Shape childShape : containerShape.getChildren()) {
                    transformShape(node, childShape);
                }
            }
        }
    }

    /**
     * Transforms a Pictogram Graphics Algorithm to a Piccolo node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param ga
     *            the graphics algorithm
     * @return the created Piccolo node
     */
    private PNode transformGraphicsAlgorithm(final PNode parent, final GraphicsAlgorithm ga) {
        // find fitting Piccolo node
        PNode node = null;
        if (ga instanceof Rectangle) {
            node = PSWTPath.createRectangle(0, 0, ga.getWidth(), ga.getHeight());
        } else if (ga instanceof RoundedRectangle) {
            RoundedRectangle rr = (RoundedRectangle) ga;
            node =
                    PSWTPath.createRoundRectangle(0, 0, ga.getWidth(), ga.getHeight(),
                            rr.getCornerWidth(), rr.getCornerHeight());
        } else if (ga instanceof Ellipse) {
            node = PSWTPath.createEllipse(0, 0, ga.getWidth(), ga.getHeight());
        } else if (ga instanceof Polygon) {
            Polygon polygon = (Polygon) ga;
            if (polygon.getPoints().size() > 0) {
                Point2D[] points = new Point2D[polygon.getPoints().size() + 1];
                int i = 0;
                for (Point point : polygon.getPoints()) {
                    points[i++] = new java.awt.Point(point.getX(), point.getY());
                }
                points[i] = points[0];
                node = PSWTPath.createPolyline(points);
            }
        } else if (ga instanceof Polyline) {
            Polyline polyline = (Polyline) ga;
            if (polyline.getPoints().size() > 0) {
                Point2D[] points = new Point2D[polyline.getPoints().size()];
                int i = 0;
                for (Point point : polyline.getPoints()) {
                    points[i++] = new java.awt.Point(point.getX(), point.getY());
                }
                node = PSWTPath.createPolyline(points);
            }
        } else if (ga instanceof Text) {
            // TODO handle this case
        } else if (ga instanceof MultiText) {
            // TODO handle this case
        } else if (ga instanceof Image) {
            // TODO handle this case
        }
        // transform child graphics algorithms
        if (node != null) {
            node.translate(ga.getX(), ga.getY());
            parent.addChild(node);
            for (GraphicsAlgorithm childGa : ga.getGraphicsAlgorithmChildren()) {
                transformGraphicsAlgorithm(node, childGa);
            }
        }
        return null;
    }

    /**
     * Returns the Pictogram counterpart for the given Piccolo node.
     * 
     * @param node
     *            the Piccolo node
     * @return the Pictogram element
     */
    public PictogramElement getPictogramElementForPNode(final PNode node) {
        Object attribute = node.getAttribute(PICTORGRAM_ATTR_KEY);
        if (attribute instanceof PictogramElement) {
            return (PictogramElement) attribute;
        }
        return null;
    }

}
