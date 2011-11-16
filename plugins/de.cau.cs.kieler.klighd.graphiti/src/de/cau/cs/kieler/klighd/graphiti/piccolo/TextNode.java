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

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;

import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * A specialized Piccolo text node preserving a reference to a source model element.
 * 
 * @author chsch
 */
public class TextNode extends PSWTText implements IPictogramNode {

    private static final long serialVersionUID = 1L;

    /** The related source model element. */
    private GraphicsAlgorithmContainer text = null;
    
    /**
     * Constructor.
     * @param theText the related source model text element.
     */
    public TextNode(final GraphicsAlgorithmContainer theText) {
        this.text = theText;
    }
    
    /**
     * {@inheritDoc}
     */
    public GraphicsAlgorithmContainer getPictogramElement() {
        return this.text;
    }

}
