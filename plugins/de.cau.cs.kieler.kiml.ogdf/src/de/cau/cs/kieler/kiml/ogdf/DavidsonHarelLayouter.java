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
import de.cau.cs.kieler.core.properties.IPropertyHolder;
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

    /** the costs option identifier. */
    private static final String COSTS_ID = "de.cau.cs.kieler.kiml.ogdf.option.davidsonHarel.costs";
    /** costs property. */
    private static final IProperty<Costs> COSTS = new Property<Costs>(COSTS_ID, Costs.STANDARD);
    
    /** the speed option identifier. */
    private static final String SPEED_ID = "de.cau.cs.kieler.kiml.ogdf.option.davidsonHarel.speed";
    /** speed property. */
    private static final IProperty<Speed> SPEED = new Property<Speed>(SPEED_ID, Speed.MEDIUM);
    
    /** default value for desired edge length. */
    private static final float DEF_DESIRED_EDGE_LENGTH = 0.0f;

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // get the minimum spacing
        float desiredEdgeLength = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (desiredEdgeLength <= 0) {
            desiredEdgeLength = DEF_DESIRED_EDGE_LENGTH;
        }
        // get costs
        Costs costs = parentLayout.getProperty(COSTS);
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
        Speed speed = parentLayout.getProperty(SPEED);
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
    @Override
    public void initDefaults(final IPropertyHolder defaultsHolder) {
        super.initDefaults(defaultsHolder);
        defaultsHolder.setProperty(LayoutOptions.OBJ_SPACING, DEF_DESIRED_EDGE_LENGTH);
    }
    
}
