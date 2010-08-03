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
 * The tree layouter from the OGDF library.
 * 
 * @author mri
 */
public class TreeLayouter extends OgdfLayouter {

    /** the minimum level distance option. */
    private static final String MIN_DIST_LEVEL =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel";
    /** the minimum sibling distance option. */
    private static final String MIN_DIST_SIBLING =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistSibling";
    /** the minimum subtree distance option. */
    private static final String MIN_DIST_SUBTREE =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistSubtree";
    /** the minimum tree distance option. */
    private static final String MIN_DIST_TREE =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistTree";
    /** the orthogonal option. */
    private static final String ORTHOGONAL =
            "de.cau.cs.kieler.kiml.ogdf.option.orthogonal";
    /** the orientation option. */
    static final String ORIENTATION =
            "de.cau.cs.kieler.kiml.ogdf.option.orientation";
    /** default value for the minimum level distance. */
    private static final float DEF_MIN_DIST_LEVEL = 50.0f;
    /** default value for the minimum sibling distance. */
    private static final float DEF_MIN_DIST_SIBLING = 20.0f;
    /** default value for the minimum subtree distance. */
    private static final float DEF_MIN_DIST_SUBTREE = 20.0f;
    /** default value for the minimum tree distance. */
    private static final float DEF_MIN_DIST_TREE = 50.0f;
    /** default value for the orthogonal. */
    private static final boolean DEF_ORTHOGONAL = false;
    /** default value for the orientation. */
    private static final int DEF_ORIENTATION = Ogdf.ORIENTATION_TOP_TO_BOTTOM;
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
        // get the minimum sibling distance
        float minDistSibling =
                LayoutOptions.getFloat(parentLayout, MIN_DIST_SIBLING);
        if (Float.isNaN(minDistSibling)) {
            minDistSibling = DEF_MIN_DIST_SIBLING;
        }
        // get the minimum subtree distance
        float minDistSubtree =
                LayoutOptions.getFloat(parentLayout, MIN_DIST_SUBTREE);
        if (Float.isNaN(minDistSubtree)) {
            minDistSubtree = DEF_MIN_DIST_SUBTREE;
        }
        // get the minimum subtree distance
        float minDistTree = LayoutOptions.getFloat(parentLayout, MIN_DIST_TREE);
        if (Float.isNaN(minDistTree)) {
            minDistTree = DEF_MIN_DIST_TREE;
        }
        // get orthogonal option
        boolean orthogonal = LayoutOptions.getBoolean(parentLayout, ORTHOGONAL);
        // get orientation
        Orientation orientation =
                LayoutOptions.getEnum(parentLayout, Orientation.class);
        int theOrientation;
        switch (orientation) {
        case BOTTOM_TO_TOP:
            theOrientation = Ogdf.ORIENTATION_BOTTOM_TO_TOP;
            break;
        case LEFT_TO_RIGHT:
            theOrientation = Ogdf.ORIENTATION_LEFT_TO_RIGHT;
            break;
        case RIGHT_TO_LEFT:
            theOrientation = Ogdf.ORIENTATION_RIGHT_TO_LEFT;
            break;
        case TOP_TO_BOTTOM:
        default:
            theOrientation = Ogdf.ORIENTATION_TOP_TO_BOTTOM;
            break;
        }

        Ogdf.createTreeLayouter(minDistSibling, minDistSubtree, minDistLevel,
                minDistTree, orthogonal, theOrientation);
        
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
        } else if (optionId.equals(MIN_DIST_SIBLING)) {
            return DEF_MIN_DIST_SIBLING;
        } else if (optionId.equals(MIN_DIST_SUBTREE)) {
            return DEF_MIN_DIST_SUBTREE;
        } else if (optionId.equals(MIN_DIST_TREE)) {
            return DEF_MIN_DIST_TREE;
        } else if (optionId.equals(ORTHOGONAL)) {
            return DEF_ORTHOGONAL;
        } else if (optionId.equals(ORIENTATION)) {
            return DEF_ORIENTATION;
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
