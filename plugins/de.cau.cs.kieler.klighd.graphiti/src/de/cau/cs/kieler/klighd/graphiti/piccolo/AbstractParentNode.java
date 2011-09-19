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
import java.util.List;

import de.cau.cs.kieler.klighd.piccolo.graph.IGraphNode;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphParent;
import de.cau.cs.kieler.klighd.piccolo.graph.Insets;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;

/**
 * An abstract Piccolo node which serves as a base for graph parents.
 * 
 * @author mri
 */
public abstract class AbstractParentNode extends PChildRepresentedNode implements IGraphParent {

    private static final long serialVersionUID = 7602115961857794444L;

    /** the list of child nodes. */
    private List<IGraphNode> childNodes = new ArrayList<IGraphNode>();

    /**
     * Adds the given shape as child.
     * 
     * @param child
     *            the child shape
     */
    public void addShape(final ShapeNode child) {
        addChild(child);
        childNodes.add(child);
    }

    // Implementation of the ...kligh.piccolo.graph interfaces

    /**
     * {@inheritDoc}
     */
    public List<IGraphNode> getChildren() {
        return childNodes;
    }

    /**
     * {@inheritDoc}
     */
    public Insets getInsets() {
        return new Insets();
    }

}
