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
 * The Davidson-Harel layout algorithm from the OGDF library.
 * 
 * @author mri
 */
public class DavidsonHarelLayouter extends OgdfLayouter {

    /** the desired edge length option. */
    private static final String DESIRED_EDGE_LENGTH =
            "de.cau.cs.kieler.kiml.ogdf.option.davidsonHarel.desiredEdgeLength";
    /** the costs option. */
    static final String COSTS =
            "de.cau.cs.kieler.kiml.ogdf.option.davidsonHarel.costs";
    /** the speed option. */
    static final String SPEED =
            "de.cau.cs.kieler.kiml.ogdf.option.davidsonHarel.speed";
    /** default value for desired edge length. */
    private static final float DEF_DESIRED_EDGE_LENGTH = 0.0f;
    /** default value for costs. */
    private static final int DEF_COSTS = Ogdf.COSTS_STANDARD;
    /** default value for speed. */
    private static final int DEF_SPEED = Ogdf.SPEED_MEDIUM;
    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15;
    /** default value for label edge distance. */
    private static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    private static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {

        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        // get the minimum spacing and layer distance
        float desiredEdgeLength =
                LayoutOptions.getFloat(parentLayout, DESIRED_EDGE_LENGTH);
        if (Float.isNaN(desiredEdgeLength)) {
            desiredEdgeLength = DEF_DESIRED_EDGE_LENGTH;
        }
        // get costs
        Costs costs =
                LayoutOptions.getEnum(parentLayout, Costs.class);
        int theCosts;
        switch (costs) {
        case REPULSE:
            theCosts = Ogdf.COSTS_REPULSE;
            break;
        case PLANAR:
            theCosts = Ogdf.COSTS_PLANAR;
            break;
        case STANDARD:
        default:
            theCosts = Ogdf.COSTS_STANDARD;
            break;
        }
        // get speed
        Speed speed =
                LayoutOptions.getEnum(parentLayout, Speed.class);
        int theSpeed;
        switch (speed) {
        case FAST:
            theSpeed = Ogdf.SPEED_FAST;
            break;
        case HQ:
            theSpeed = Ogdf.SPEED_HQ;
            break;
        case MEDIUM:
        default:
            theSpeed = Ogdf.SPEED_MEDIUM;
            break;
        }
        
        Ogdf.createDavidsonHarelLayouter(theCosts, theSpeed, desiredEdgeLength);
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(DESIRED_EDGE_LENGTH)) {
            return DEF_DESIRED_EDGE_LENGTH;
        } else if (optionId.equals(COSTS)) {
            return DEF_COSTS;
        } else if (optionId.equals(SPEED)) {
            return DEF_SPEED;
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
