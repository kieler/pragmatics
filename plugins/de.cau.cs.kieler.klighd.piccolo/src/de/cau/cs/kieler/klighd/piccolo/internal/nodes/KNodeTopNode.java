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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;

/**
 * The Piccolo node for representing the top-level {@code KNode}.
 * 
 * @author mri
 */
public class KNodeTopNode extends PEmptyNode implements INode {

    private static final long serialVersionUID = 8395163186723344696L;

    /** the encapsulated {@code KNode}. */
    private transient KNode node;

    /** the Piccolo node representing the child area. */
    private KChildAreaNode childArea;

    /**
     * Constructs a Piccolo node for representing the top-level {@code KNode}.
     * 
     * @param node
     *            the KNode
     */
    public KNodeTopNode(final KNode node) {
        this.node = node;
        childArea = new KChildAreaNode(this);
        childArea.setPickable(false);
        childArea.setClip(false);
        addChild(childArea);
        setPickable(true);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getGraphElement() {
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(
            final AbstractKGERenderingController<KNode, ? extends IGraphElement<KNode>> controller) {
        String s = "KLighD: Invalid access occured: invoking setRenderingController()"
                + "is not allowed for KNodeTopNodes!";
        throw new UnsupportedOperationException(s);
    }

    /**
     * {@inheritDoc}
     */
    public AbstractKGERenderingController<KNode, KNodeNode> getRenderingController() {
        String s = "KLighD: Invalid access occured: calling getRenderingController()"
                        + "is not allowed for KNodeTopNodes!";
        throw new UnsupportedOperationException(s);
    }
    
    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildArea() {
        return childArea;
    }

}
