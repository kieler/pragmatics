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

import net.ogdf.lib.LayoutModule;
import net.ogdf.lib.OrthoDir;
import net.ogdf.lib.OrthoLayout;
import net.ogdf.lib.PlanarizationLayout;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfLayouter {

    /** layout option identifier for the page ratio. */
    public static final String OPT_PAGE_RATIO = "de.cau.cs.kieler.kiml.ogdf.option.pageRatio";
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
    protected LayoutModule prepareLayouter(final KNode layoutNode) {
        // create the layouter
        PlanarizationLayout layout = new PlanarizationLayout();
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        // set page ratio
        float pageRatio = LayoutOptions.getFloat(parentLayout, OPT_PAGE_RATIO);
        if (Float.isNaN(pageRatio)) {
            pageRatio = DEF_PAGE_RATIO;
        }
        layout.pageRatio(pageRatio);

        // set clique preprocessing
        layout.preprocessCliques(true);

        // set the planar layouter
        OrthoLayout orthoLayout = new OrthoLayout();

        // set separation
        float minSpacing = LayoutOptions.getFloat(parentLayout, LayoutOptions.MIN_SPACING);
        if (Float.isNaN(minSpacing)) {
            minSpacing = DEF_MINSPACING;
        }
        orthoLayout.separation(minSpacing);

        // set layout direction
        LayoutDirection direction = LayoutOptions.getEnum(parentLayout, LayoutDirection.class);
        switch (direction) {
        case DOWN:
            orthoLayout.preferedDir(OrthoDir.odNorth);
            break;
        case LEFT:
            orthoLayout.preferedDir(OrthoDir.odWest);
            break;
        case RIGHT:
            orthoLayout.preferedDir(OrthoDir.odEast);
            break;
        default:
            orthoLayout.preferedDir(OrthoDir.odSouth);
            break;
        }

        layout.setPlanarLayouter(orthoLayout);
        
        // perform pre-processing
        loopRouter.preProcess(layoutNode);

        return layout;
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
        if (optionId.equals(LayoutOptions.LAYOUT_DIRECTION)) {
            return LayoutDirection.UP;
        } else if (optionId.equals(LayoutOptions.MIN_SPACING)) {
            return DEF_MINSPACING;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(LayoutOptions.LABEL_SPACING)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_PAGE_RATIO)) {
            return DEF_PAGE_RATIO;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
    
}
