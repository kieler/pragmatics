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

import net.ogdf.bin.OgdfServer;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ogdf.options.QualityVsSpeed;

/**
 * The FMMM layouter from the OGDF library.
 * 
 * @author mri
 */
public class FMMMLayouter extends OgdfLayouter {

    /** 'quality vs speed' property. */
    private static final IProperty<QualityVsSpeed> QUALITY_VS_SPEED = new Property<QualityVsSpeed>(
            "de.cau.cs.kieler.kiml.ogdf.option.qualityVsSpeed", QualityVsSpeed.BEAUTIFULANDFAST);

    /** 'new initial placement' property. */
    private static final IProperty<Boolean> NEW_INITIAL_PLACEMENT = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.newInitialPlacement", false);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Constructs a FMMMLayouter.
     */
    public FMMMLayouter() {
        super("FMMM");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // qualityVsSpeed
        QualityVsSpeed qualityVsSpeed = parentLayout.getProperty(QUALITY_VS_SPEED);
        int qvs;
        switch (qualityVsSpeed) {
        case GORGEOUSANDEFFICIENT:
            qvs = OgdfServer.GORGEOUS_AND_EFFICIENT;
            break;
        case NICEANDINCREDIBLESPEED:
            qvs = OgdfServer.NICE_AND_INCREDIBLE_SPEED;
            break;
        case BEAUTIFULANDFAST:
        default:
            qvs = OgdfServer.BEAUTIFUL_AND_FAST;
            break;
        }
        addOption(OgdfServer.OPTION_QUALITY_VS_SPEED, qvs);
        // newInitialPlacement
        boolean newInitialPlacement = parentLayout.getProperty(NEW_INITIAL_PLACEMENT);
        addOption(OgdfServer.OPTION_NEW_INITIAL_PLACEMENT, newInitialPlacement);
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }

    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.exclude();
    }

}
