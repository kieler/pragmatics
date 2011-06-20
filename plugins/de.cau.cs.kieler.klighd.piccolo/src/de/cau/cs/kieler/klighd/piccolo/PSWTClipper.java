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

import java.util.List;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo node that clips it children at it boundry. Does not have a visual representation other
 * than it's children.
 * 
 * @author mri
 */
public class PSWTClipper extends PNode {

    private static final long serialVersionUID = -2651702857927623226L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        if (getVisible() && fullIntersects(paintContext.getLocalClip())) {
            paintContext.pushTransform(getTransformReference(false));
            paintContext.pushTransparency(getTransparency());
            
            paintContext.pushClip(getBoundsReference());
            
            @SuppressWarnings("unchecked")
            List<PNode> children = getChildrenReference();
            for (PNode child : children) {
                child.fullPaint(paintContext);
            }
            
            paintContext.popClip(getBoundsReference());

            paintContext.popTransparency(getTransparency());
            paintContext.popTransform(getTransformReference(false));
        }
    }

}
