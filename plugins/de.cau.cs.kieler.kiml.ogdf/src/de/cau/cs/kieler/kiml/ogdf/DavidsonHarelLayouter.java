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

import net.ogdf.bin.OgdfServerAPI;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ogdf.options.Costs;
import de.cau.cs.kieler.kiml.ogdf.options.Speed;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The Davidson-Harel layout algorithm from the OGDF library.
 * 
 * @author mri
 */
public class DavidsonHarelLayouter extends OgdfLayouter {

    /** default value for spacing. */
    public static final float DEF_SPACING = 30.0f;

    /** 'spacing' property. */
    private static final IProperty<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING);
    /** the costs option identifier. */
    private static final String COSTS_ID = "de.cau.cs.kieler.kiml.ogdf.option.costs";
    /** costs property. */
    private static final IProperty<Costs> COSTS = new Property<Costs>(COSTS_ID, Costs.STANDARD);

    /** the speed option identifier. */
    private static final String SPEED_ID = "de.cau.cs.kieler.kiml.ogdf.option.speed";
    /** speed property. */
    private static final IProperty<Speed> SPEED = new Property<Speed>(SPEED_ID, Speed.MEDIUM);

    /**
     * Constructs a DavidsonHarelLayouter.
     */
    public DavidsonHarelLayouter() {
        super("DAVIDSON_HAREL");
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // desiredEdgeLength
        float desiredEdgeLength = parentLayout.getProperty(SPACING);
        addOption(OgdfServerAPI.OPTION_EDGE_LENGTH, desiredEdgeLength);
        // costs
        Costs costs = parentLayout.getProperty(COSTS);
        int theCosts;
        switch (costs) {
        case REPULSE:
            theCosts = OgdfServerAPI.COSTS_REPULSE;
            break;
        case PLANAR:
            theCosts = OgdfServerAPI.COSTS_PLANAR;
            break;
        case STANDARD:
        default:
            theCosts = OgdfServerAPI.COSTS_STANDARD;
            break;
        }
        addOption(OgdfServerAPI.OPTION_COSTS, theCosts);
        // speed
        Speed speed = parentLayout.getProperty(SPEED);
        int theSpeed;
        switch (speed) {
        case FAST:
            theSpeed = OgdfServerAPI.SPEED_FAST;
            break;
        case HQ:
            theSpeed = OgdfServerAPI.SPEED_HQ;
            break;
        case MEDIUM:
        default:
            theSpeed = OgdfServerAPI.SPEED_MEDIUM;
            break;
        }
        addOption(OgdfServerAPI.OPTION_SPEED, theSpeed);
    }

}
