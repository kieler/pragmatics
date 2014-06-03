/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighdning.viewer;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.export.KlighdAbstractSVGGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * Wrapps a {@link KNodeNode} to insert an id into the SVG that allows expanding and collapsing of
 * hierarchical nodes.
 * 
 * @author uru
 */
@SuppressWarnings("serial")
public class WrappedKNodeNode extends PNode {

    /** Identifier for the additional text. */
    public static final String ID_TEXT = "de.cau.cs.kieler.id";

    /** The graph node. */
    private KNode node;
    /** The piccolo node that is wrapped. */
    private KNodeNode knodenode;

    /**
     * @param node
     *            the node to be wrapped
     */
    public WrappedKNodeNode(final KNodeNode node) {
        this.knodenode = node;
        this.node = knodenode.getGraphElement();

        // add the wrapped node as child
        addChild(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {

        // if (paintContext.getGraphics() instanceof KlighdSimpleSVGGraphicsImpl {
        if (paintContext.getGraphics() instanceof KlighdAbstractSVGGraphics) {
//            KlighdSimpleSVGGraphicsImpl g =
//                    (KlighdSimpleSVGGraphicsImpl) paintContext.getGraphics();
            
            final KlighdAbstractSVGGraphics g =
                    (KlighdAbstractSVGGraphics) paintContext.getGraphics();

            // draw invisible text
            final int oldAlpha = g.getAlpha();
            g.setAlpha(0);
            g.drawText(ID_TEXT + ":" + node.hashCode());
            g.setAlpha(oldAlpha);

        }

        // perform the super call afterwards, that way the text is drawn immediately before the
        // first element of the wrappe knodenode
        super.fullPaint(paintContext);
    }

    /**
     * @return the knodenode
     */
    public KNodeNode getKnodeNode() {
        return knodenode;
    }
}
