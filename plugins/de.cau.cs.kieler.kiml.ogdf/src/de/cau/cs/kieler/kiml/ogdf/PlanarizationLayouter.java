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
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

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

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // get page ratio
        float pageRatio = parentLayout.getProperty(LayoutOptions.ASPECT_RATIO);
        if (pageRatio <= 0) {
            pageRatio = DEF_PAGE_RATIO;
        }
        // get separation
        float minSpacing = parentLayout.getProperty(LayoutOptions.SPACING);
        if (minSpacing < 0) {
            minSpacing = DEF_MINSPACING;
        }
        // get layout direction
        Direction direction = parentLayout.getProperty(LayoutOptions.DIRECTION);
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
        loopRouter.exclude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDefaults(final IPropertyHolder defaultsHolder) {
        super.initDefaults(defaultsHolder);
        defaultsHolder.setProperty(LayoutOptions.DIRECTION, Direction.UP);
        defaultsHolder.setProperty(LayoutOptions.SPACING, DEF_MINSPACING);
        defaultsHolder.setProperty(LayoutOptions.ASPECT_RATIO, DEF_PAGE_RATIO);
    }
}
