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

import java.util.Map;

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 *
 * An analysis that measures the horizontal and vertical dimensions of the graph
 * drawing.
 *
 * @author bdu
 *
 */
public class DimensionsAnalysis implements IAnalysis {
    /**
     * {@inheritDoc}
     *
     * @return a {@link Pair} of {@code Double} values, where the first entry is
     *         the x dimension, the second one is the y dimension.
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Dimensions analysis", 1);

        float xmin = Float.MAX_VALUE;
        float ymin = Float.MAX_VALUE;
        float xmax = 0.0f;
        float ymax = 0.0f;
        for (final KNode node : parentNode.getChildren()) {
            final KShapeLayout nodeLayout = KimlUtil.getShapeLayout(node);
            final float xpos = nodeLayout.getXpos();
            final float ypos = nodeLayout.getYpos();
            Assert.isTrue((xpos >= 0.0f) && (ypos >= 0.0f), "negative node positions");
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
        Assert.isTrue((xdim >= 0.1f) || (ydim >= 0.1f), "Very small dimension.");
        final Pair<Float, Float> result = new Pair<Float, Float>(xdim, ydim);
        return result;
    }
}
