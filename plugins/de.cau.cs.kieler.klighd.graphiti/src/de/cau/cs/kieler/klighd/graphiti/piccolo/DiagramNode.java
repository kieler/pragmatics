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

import java.util.List;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.klighd.piccolo.graph.IGraphParent;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A Piccolo node wrapping a Pictogram diagram.
 * 
 * @author mri
 */
public class DiagramNode extends AbstractParentNode implements IPictogramNode, IGraphParent {

    private static final long serialVersionUID = 6594473069619851592L;

    /** the Pictogram diagram represented by this node. */
    private Diagram diagram;
    /** the Piccolo camera for this diagram node. */
    private PCamera camera = null;

    /**
     * Constructs a diagram node.
     * 
     * @param diagram
     *            the Pictogram diagram represented by this node
     */
    public DiagramNode(final Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * {@inheritDoc}
     */
    public PictogramElement getPictogramElement() {
        return diagram;
    }

    /**
     * {@inheritDoc}
     */
    public void setRelativeBounds(final PBounds bounds) {
        if (camera == null) {
            findCamera();
        }
        // transform the viewport
        if (camera != null) {
            PAffineTransform transform = camera.getViewTransformReference();
            double scale = transform.getScale();
            double deltaX = (bounds.getX() - transform.getTranslateX()) / scale;
            double deltaY = (bounds.getY() - transform.getTranslateY()) / scale;
            camera.translateView(deltaX, deltaY);
        }
    }

    /**
     * {@inheritDoc}
     */
    public PBounds getRelativeBounds() {
        if (camera == null) {
            findCamera();
        }
        if (camera != null) {
            // get the transform of the viewport
            PAffineTransform transform = camera.getViewTransformReference();
            PBounds diagramBounds = getFullBounds();
            PBounds bounds = new PBounds(transform.getTranslateX(), transform.getTranslateY(),
                    diagramBounds.width, diagramBounds.height);

            return bounds;
        }
        return new PBounds(0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Tries to find the camera for this node.
     */
    @SuppressWarnings("unchecked")
    private void findCamera() {
        // the first child of the root should be the camera
        PRoot root = getRoot();
        if (root != null) {
            for (PNode child : (List<PNode>) root.getChildrenReference()) {
                if (child instanceof PCamera) {
                    camera = (PCamera) child;
                    break;
                }
            }
        }
    }
    
}
