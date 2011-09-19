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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.klighd.piccolo.graph.IGraphEdge;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphNode;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphPort;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A Piccolo node wrapping a Pictogram shape.
 * 
 * @author mri
 */
public class ShapeNode extends AbstractParentNode implements IPictogramNode, IGraphNode {

    private static final long serialVersionUID = 6280554909111287283L;

    /** the Pictogram shape represented by this node. */
    private Shape shape;
    /** a mapping between Pictogram anchors and Piccolo anchor nodes. */
    private Map<PictogramElement, AnchorNode> anchorMap =
            new LinkedHashMap<PictogramElement, AnchorNode>();
    /** the list of ports. */
    private List<IGraphPort> ports = new ArrayList<IGraphPort>();

    /**
     * Constructs a ShapeNode.
     * 
     * @param shape
     *            the Pictogram shape
     */
    public ShapeNode(final Shape shape) {
        this.shape = shape;
    }

    /**
     * {@inheritDoc}
     */
    public PictogramElement getPictogramElement() {
        return shape;
    }

    /**
     * Adds the given anchor node to the shape node.
     * 
     * @param anchorNode
     *            the anchor node
     */
    public void addAnchor(final AnchorNode anchorNode) {
        addChild(anchorNode);
        anchorMap.put(anchorNode.getPictogramElement(), anchorNode);
        // if the anchor has a visual representation consider it a port
        if (anchorNode.getRepresentationNode() != null && anchorNode.getPickable()) {
            ports.add(anchorNode);
        }
    }

    /**
     * Returns a collection containing the anchor nodes attached to this shape node.
     * 
     * @return the anchor nodes attached to this shape node
     */
    public Collection<AnchorNode> getAnchorNodes() {
        return anchorMap.values();
    }

    /**
     * Returns the Piccolo anchor node for the given Pictogram anchor.
     * 
     * @param anchor
     *            the Pictogram anchor
     * @return the Piccolo anchor node
     */
    public AnchorNode getAnchorNode(final Anchor anchor) {
        return anchorMap.get(anchor);
    }

    // Implementation of the ...kligh.piccolo.graph interfaces

    // /**
    // * {@inheritDoc}
    // */
    // public Insets getInsets() {
    // // TODO return real insets here
    // return new Insets();
    // }

    /**
     * {@inheritDoc}
     */
    public void setRelativeBounds(final PBounds bounds) {
        PAffineTransform transform = getTransformReference(true);
        translate(bounds.getX() - transform.getTranslateX(),
                bounds.getY() - transform.getTranslateY());
        // TODO resize the shape
    }

    /**
     * {@inheritDoc}
     */
    public PBounds getRelativeBounds() {
        PAffineTransform transform = getTransformReference(true);
        PBounds bounds =
                getRepresentationNode() != null ? getRepresentationNode().getBounds() : getBounds();
        PBounds relativeBounds = new PBounds();
        relativeBounds.setRect(transform.getTranslateX(), transform.getTranslateY(),
                bounds.getWidth(), bounds.getHeight());
        return relativeBounds;
    }

    /**
     * {@inheritDoc}
     */
    public List<IGraphPort> getPorts() {
        return ports;
    }

    /**
     * {@inheritDoc}
     */
    public List<IGraphEdge> getOutgoingEdges() {
        List<IGraphEdge> outgoingEdges = new ArrayList<IGraphEdge>(ports.size());
        for (IGraphPort port : anchorMap.values()) {
            for (IGraphEdge edge : port.getOutgoingEdges()) {
                outgoingEdges.add(edge);
            }
        }
        return outgoingEdges;
    }

}
