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
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The FMMM layouter from the OGDF library.
 * 
 * @author mri
 */
public class FMMMLayouter extends OgdfLayouter {

    /** the quality vs speed option. */
    static final String QUALITY_VS_SPEED =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.qualityVsSpeed";
    /** the unit edge length option. */
    private static final String UNIT_EDGE_LENGTH =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.unitEdgeLength";
    /** the new initial placement option. */
    private static final String NEW_INITIAL_PLACEMENT =
            "de.cau.cs.kieler.kiml.ogdf.option.newInitialPlacement";
    /** the default value for quality vs speed. */
    private static final int DEF_QUALITY_VS_SPEED = Ogdf.BEAUTIFUL_AND_FAST;
    /** the default value for unit edge length. */
    private static final float DEF_UNIT_EDGE_LENGTH = 50.0f;
    /** the default value for new initial placement. */
    private static final boolean DEF_NEW_INITIAL_PLACEMENT = false;
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
    @Override
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = KimlUtil.getShapeLayout(layoutNode);

        // get quality vs speed
        QualityVsSpeed qualityVsSpeed =
                LayoutOptions.getEnum(parentLayout, QualityVsSpeed.class);
        int qvs;
        switch (qualityVsSpeed) {
        case GORGEOUSANDEFFICIENT:
            qvs = Ogdf.GORGEOUS_AND_EFFICIENT;
            break;
        case NICEANDINCREDIBLESPEED:
            qvs = Ogdf.NICE_AND_INCREDIBLE_SPEED;
            break;
        case BEAUTIFULANDFAST:
        default:
            qvs = Ogdf.BEAUTIFUL_AND_FAST;
            break;
        }
        // get the unit edge length
        float unitEdgeLength =
                LayoutOptions.getFloat(parentLayout, UNIT_EDGE_LENGTH);
        if (Float.isNaN(unitEdgeLength)) {
            unitEdgeLength = DEF_UNIT_EDGE_LENGTH;
        }
        // get new initial placement
        boolean newInitialPlacement =
                LayoutOptions.getBoolean(parentLayout, NEW_INITIAL_PLACEMENT);

        Ogdf.createFMMMLayouter(unitEdgeLength, newInitialPlacement, qvs);
        
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
        if (optionId.equals(QUALITY_VS_SPEED)) {
            return DEF_QUALITY_VS_SPEED;
        } else if (optionId.equals(UNIT_EDGE_LENGTH)) {
            return DEF_UNIT_EDGE_LENGTH;
        } else if (optionId.equals(NEW_INITIAL_PLACEMENT)) {
            return DEF_NEW_INITIAL_PLACEMENT;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING_ID)) {
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
