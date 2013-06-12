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

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.grana.analyses.DirectedCycleAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.LongestPathAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.NodeCountAnalysis;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutConfig;

/**
 * Smart layout rule for layered layout type.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class LayeredRule implements ISmartRule {
    
    /** value at which to split the result for cyclic and acyclic graphs. */
    private static final double SPLIT_VALUE = 0.85;
    /** exponent for adaptation of value spread. */
    private static final double EXPONENT = 0.55;
    /** the penalty factor for missing graph features. */
    private static final double FEATURE_PENALTY = 0.7;

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        int nodeCount = metaLayout.analyze(NodeCountAnalysis.ID);
        if (nodeCount > 0) {
            int cycleCount = metaLayout.analyze(DirectedCycleAnalysis.ID);
            double fp = SmartLayoutConfig.missingFeaturesFromType(metaLayout,
                    LayoutTypeData.TYPE_LAYERED);
            
            if (cycleCount == 0) {
                int longestPath = (Integer) metaLayout.analyze(LongestPathAnalysis.ID) + 1;
                double lpMeasure;
                if (longestPath >= nodeCount / longestPath) {
                    lpMeasure = (double) nodeCount / (longestPath * longestPath);
                } else {
                    lpMeasure = (double) (longestPath * longestPath) / nodeCount;
                }
                return (lpMeasure * (1 - SPLIT_VALUE) + SPLIT_VALUE)
                        * Math.pow(FEATURE_PENALTY, fp);
                
            } else {
                int edgeCount = metaLayout.analyze(EdgeCountAnalysis.ID);
                double cyclesMeasure = Math.min(Math.pow(
                        (double) edgeCount / (nodeCount * cycleCount), EXPONENT), 1.0);
                return cyclesMeasure * SPLIT_VALUE
                        * Math.pow(FEATURE_PENALTY, fp);
            }
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        LayoutAlgorithmData bestAlgo = SmartLayoutConfig.mostFeasibleAlgorithm(metaLayout,
                LayoutTypeData.TYPE_LAYERED);
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, bestAlgo.getId());
    }

}
