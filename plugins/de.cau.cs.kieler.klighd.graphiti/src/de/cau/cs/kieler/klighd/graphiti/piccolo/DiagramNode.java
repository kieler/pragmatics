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
        //TODO set bounding box here
    }

    /**
     * {@inheritDoc}
     */
    public PBounds getRelativeBounds() {
        //TODO return the real BB here
        return getFullBounds();
    }

}
