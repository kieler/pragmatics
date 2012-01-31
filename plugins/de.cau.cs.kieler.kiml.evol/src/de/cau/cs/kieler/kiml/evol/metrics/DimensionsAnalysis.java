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
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

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
     *         the horizontal dimension, the second one is the vertical
     *         dimension.
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Dimensions analysis", 1);

        Pair<Float, Float> result;
        try {
            float xmin = Float.MAX_VALUE;
            float ymin = Float.MAX_VALUE;
            float xmax = 0.0f;
            float ymax = 0.0f;
            for (final KNode node : parentNode.getChildren()) {
                KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
                float xpos = nodeLayout.getXpos();
                float ypos = nodeLayout.getYpos();
                assert (xpos >= 0.0f) && (ypos >= 0.0f) : "negative node positions";
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
            float xdim = xmax - xmin;
            float ydim = ymax - ymin;
            final float epsilon = 0.1f;
            assert (xdim >= epsilon) || (ydim >= epsilon) : "Very small dimension.";
            result = new Pair<Float, Float>(xdim, ydim);

        } finally {
            // We must close the monitor.
            progressMonitor.done();
        }

        return result;
    }
}
