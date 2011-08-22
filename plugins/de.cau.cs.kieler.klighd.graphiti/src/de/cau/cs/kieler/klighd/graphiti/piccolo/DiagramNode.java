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

import org.eclipse.graphiti.mm.pictograms.Diagram;

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
public class DiagramNode extends AbstractParentNode implements IGraphParent {

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
     * Returns the Pictogram diagram represented by this node.
     * 
     * @return the Pictogram diagram
     */
    public Diagram getPictogramDiagram() {
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
            camera.translateView(bounds.getX() - transform.getTranslateX(), bounds.getY()
                    - transform.getTranslateY());
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

    private void findCamera() {
        // the first child of the root should be the camera
        PRoot root = getRoot();
        if (root != null && root.getChildrenReference().size() > 0) {
            PNode cameraCandidate = root.getChild(0);
            if (cameraCandidate instanceof PCamera) {
                camera = (PCamera) cameraCandidate;
            }
        }
    }
}
