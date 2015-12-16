/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.comments;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * An {@link IBoundsProvider} that simply returns the bounds defined in the node's shape layout.
 * 
 * @author cds
 */
public class ShapeLayoutBoundsProvider implements IBoundsProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double boundsFor(final KNode node) {
        KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        
        if (shapeLayout == null) {
            return null;
        } else {
            return new Rectangle2D.Double(
                    shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());
        }
    }

}
