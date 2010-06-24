/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.grana;

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * A layout metric that computes the vertical compactness (flatness) of the
 * given graph layout.
 * 
 * Does not care for hierarchy. The returned Object is a double value within the
 * range of 0.0 to 1.0, where a higher value means more vertical compactness.
 * 
 * @author bdu
 * 
 */
public class FlatnessMetric implements IAnalysis {
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Flatness metric analysis", 1);
        final Float result;
        float xmin = Float.MAX_VALUE;
        float ymin = Float.MAX_VALUE;
        float xmax = 0.0f;
        float ymax = 0.0f;
        for (KNode node : parentNode.getChildren()) {
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(node);
            final float xpos = nodeLayout.getXpos();
            final float ypos = nodeLayout.getYpos();
            Assert.isTrue(xpos >= 0.0f && ypos >= 0.0f, "negative node positions");
            if (xpos > xmax) {
                xmax = xpos;
            }
            if (ypos > ymax) {
                ymax = ypos;
            }
            if (xpos < xmin) {
                xmin = xpos;
            }
            if (ypos < ymin) {
                ymin = ypos;
            }
        }
        final float xdim = xmax - xmin;
        final float ydim = ymax - ymin;
        final boolean isXdimZero = (xdim == 0.0f);
        final boolean isYdimZero = (ydim == 0.0f);
        if (isXdimZero && isYdimZero) {
            // XXX this should happen rarely, consider returning NaN?
            result = 0.5f;
        } else {
            final float heightToWidthRatio = (isXdimZero ? Float.POSITIVE_INFINITY : ydim / xdim);
            final float widthToHeightRatio = (isYdimZero ? Float.POSITIVE_INFINITY : xdim / ydim);
            if (widthToHeightRatio < 1.0) {
                // narrow
                result = widthToHeightRatio / 2;
            } else {
                // wide
                result = 1.0f - (heightToWidthRatio / 2);
            }
        }
        progressMonitor.done();
        return result;
    }
}
