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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ogdf.options.QualityVsSpeed;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The FMMM layouter from the OGDF library.
 * 
 * @author mri
 */
public class FMMMLayouter extends OgdfLayouter {

    /** the quality vs speed option identifier. */
    private static final String QUALITY_VS_SPEED_ID
            = "de.cau.cs.kieler.kiml.ogdf.option.fmmm.qualityVsSpeed";
    /** quality vs speed property. */
    private static final IProperty<QualityVsSpeed> QUALITY_VS_SPEED = new Property<QualityVsSpeed>(
            QUALITY_VS_SPEED_ID, QualityVsSpeed.BEAUTIFULANDFAST);
    
    /** the new initial placement option identifier. */
    private static final String NEW_INITIAL_PLACEMENT_ID
            = "de.cau.cs.kieler.kiml.ogdf.option.newInitialPlacement";
    /** new initial placement property. */
    private static final IProperty<Boolean> NEW_INITIAL_PLACEMENT = new Property<Boolean>(
            NEW_INITIAL_PLACEMENT_ID, false);
    
    /** the default value for unit edge length. */
    private static final float DEF_UNIT_EDGE_LENGTH = 50.0f;
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
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // get quality vs speed
        QualityVsSpeed qualityVsSpeed = parentLayout.getProperty(QUALITY_VS_SPEED);
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
        float unitEdgeLength = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (unitEdgeLength <= 0) {
            unitEdgeLength = DEF_UNIT_EDGE_LENGTH;
        }
        // get new initial placement
        boolean newInitialPlacement = parentLayout.getProperty(NEW_INITIAL_PLACEMENT);

        Ogdf.createFMMMLayouter(unitEdgeLength, newInitialPlacement, qvs);
        
        // remove self-loops from the graph
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
    public Object getDefault(final String optionId) {
        if (optionId.equals(LayoutOptions.OBJ_SPACING_ID)) {
            return DEF_UNIT_EDGE_LENGTH;
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
