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
package de.cau.cs.kieler.klighd.piccolo;

import edu.umd.cs.piccolo.PNode;

/**
 * The interface for nodes which are visually represented by a child node.
 * 
 * @author mri
 */
public interface IChildRepresentedNode {

    /**
     * Sets the child node representing this node.
     * 
     * @param representationNode
     *            the representation node
     */
    void setRepresentationNode(final PNode representationNode);

    /**
     * Returns the child node representing this node.
     * 
     * @return the representation node
     */
    PNode getRepresentationNode();

}
