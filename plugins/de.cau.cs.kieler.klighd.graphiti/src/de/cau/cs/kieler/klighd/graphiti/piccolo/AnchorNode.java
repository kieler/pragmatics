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

import org.eclipse.graphiti.mm.pictograms.Anchor;

import edu.umd.cs.piccolo.PNode;

/**
 * A Piccolo node wrapping a Pictogram anchor.
 * 
 * @author mri
 */
public class AnchorNode extends PNode {

    private static final long serialVersionUID = 1406179264277421131L;

    /** the Pictogram anchor represented by this node. */
    private Anchor anchor;

    /**
     * Constructs an AnchorNode.
     * 
     * @param anchor
     *            the Pictogram anchor
     */
    public AnchorNode(final Anchor anchor) {
        this.anchor = anchor;
    }

    /**
     * Returns the Pictorgram anchor represented by this node.
     * 
     * @return the Pictogram anchor
     * 
     */
    public Anchor getPictogramAnchor() {
        return anchor;
    }
}
