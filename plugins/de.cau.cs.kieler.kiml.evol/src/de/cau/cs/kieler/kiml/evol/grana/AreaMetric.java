package de.cau.cs.kieler.kiml.evol.grana;

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * Measures the area extent of the given graph layout.
 *
 * Does not care for hierarchy. The returned Object is a float value within the
 * range of 0.0 to 1.0, where a higher value means more area.
 *
 * @author bdu
 *
 */
public class AreaMetric implements IAnalysis {
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Area metric analysis", 1);
        final Float result;
        float xmin = Float.MAX_VALUE;
        float ymin = Float.MAX_VALUE;
        float xmax = 0.0f;
        float ymax = 0.0f;
        for (final KNode node : parentNode.getChildren()) {
            final KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(node);
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

        Assert.isTrue((xdim >= 1.0f) && (ydim >= 1.0f), "Very small dimension.");

        final double area = xdim * ydim;

        // normalize
        if (area < 1.0) {
            result = 0.0f;
        } else {
            result = (float) (1.0f - (1.0f / Math.pow(area, 0.08)));
        }
        progressMonitor.done();
        return result;
    }
}
