/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.smart.rules;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutConfig;

/**
 * Smart layout rule for circular layout type.
 *
 * @author msp
 */
public class CircularRule implements ISmartRule {
    
    /** the penalty factor for missing graph features. */
    private static final double FEATURE_PENALTY = 0.6;

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        double density = metaLayout.analyze(BiconnectedComponentDensityAnalysis.ID);
        int missingFeatures = SmartLayoutConfig.missingFeaturesFromType(metaLayout,
                LayoutTypeData.TYPE_CIRCLE);
        
        return (1 - density) * KielerMath.pow(FEATURE_PENALTY, missingFeatures);
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        LayoutAlgorithmData bestAlgo = SmartLayoutConfig.mostFeasibleAlgorithm(metaLayout,
                LayoutTypeData.TYPE_CIRCLE);
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, bestAlgo.getId());
    }

}
