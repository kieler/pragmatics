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
import de.cau.cs.kieler.kiml.options.LayoutDirection;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfLayouter {

    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.3f;
    /** default value for minimal spacing. */
    public static final float DEF_MINSPACING = 30.0f;
    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 15.0f;
    /** default value for label edge distance. */
    public static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = KimlUtil.getShapeLayout(layoutNode);
        // get page ratio
        float pageRatio = parentLayout.getProperty(LayoutOptions.ASPECT_RATIO);
        if (pageRatio <= 0) {
            pageRatio = DEF_PAGE_RATIO;
        }
        // get separation
        float minSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (minSpacing < 0) {
            minSpacing = DEF_MINSPACING;
        }
        // get layout direction
        LayoutDirection direction = parentLayout.getProperty(LayoutOptions.LAYOUT_DIRECTION);
        int layoutDirection;
        switch (direction) {
        case UP:
            layoutDirection = Ogdf.DIRECTION_NORTH;
            break;
        case LEFT:
            layoutDirection = Ogdf.DIRECTION_WEST;
            break;
        case RIGHT:
            layoutDirection = Ogdf.DIRECTION_EAST;
            break;
        default:
            layoutDirection = Ogdf.DIRECTION_SOUTH;
            break;
        }
        // create the planarization layouter
        Ogdf.createMixedUpwardPlanarizationLayouter(pageRatio, minSpacing,
                layoutDirection);
        // perform pre-processing
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
        if (optionId.equals(LayoutOptions.LAYOUT_DIRECTION_ID)) {
            return LayoutDirection.UP;
        } else if (optionId.equals(LayoutOptions.OBJ_SPACING_ID)) {
            return DEF_MINSPACING;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING_ID)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(LayoutOptions.ASPECT_RATIO_ID)) {
            return DEF_PAGE_RATIO;
        } else if (optionId.equals(LABEL_EDGE_DIST_ID)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(LABEL_MARGIN_DIST_ID)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING_ID)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(LABEL_EDGE_DIST_ID)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(LABEL_MARGIN_DIST_ID)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
}
