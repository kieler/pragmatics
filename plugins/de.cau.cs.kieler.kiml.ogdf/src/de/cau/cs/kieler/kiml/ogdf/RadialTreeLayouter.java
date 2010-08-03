/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import net.ogdf.lib.Ogdf;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * The radial tree layouter from the OGDF library.
 * 
 * @author mri
 */
public class RadialTreeLayouter extends OgdfLayouter {

    /** the minimum level distance option. */
    private static final String MIN_DIST_LEVEL =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel";
    /** the minimum connected component distance option. */
    private static final String MIN_DIST_CC =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC";
    /** default value for the minimum level distance. */
    private static final float DEF_MIN_DIST_LEVEL = 50.0f;
    /** default value for minimum connected component distance. */
    private static final float DEF_MIN_DIST_CC = 50.0f;
    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15;
    /** default value for label edge distance. */
    private static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    private static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();
    
    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {

        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        // get the minimum level distance
        float minDistLevel =
                LayoutOptions.getFloat(parentLayout, MIN_DIST_LEVEL);
        if (Float.isNaN(minDistLevel)) {
            minDistLevel = DEF_MIN_DIST_LEVEL;
        }
        // get the minimum connected component distance
        float minDistCC = LayoutOptions.getFloat(parentLayout, MIN_DIST_CC);
        if (Float.isNaN(minDistCC)) {
            minDistCC = DEF_MIN_DIST_CC;
        }
        
        Ogdf.createRadialTreeLayouter(minDistLevel, minDistCC);
        
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.postProcess();
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(MIN_DIST_LEVEL)) {
            return DEF_MIN_DIST_LEVEL;
        } else if (optionId.equals(MIN_DIST_CC)) {
            return DEF_MIN_DIST_CC;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
}
