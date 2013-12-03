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
import de.cau.cs.kieler.kiml.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.NodeCountAnalysis;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutConfig;

/**
 * Smart layout rule for orthogonalization layout.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class OrthogonalRule implements ISmartRule {
    
    /** weight of the planarity measure versus the number of nodes with degree > 4. */
    private static final double PLANARITY_WEIGHT = 0.7;
    /** exponent for adaptation of value spread. */
    private static final double EXPONENT = 0.5;
    /** identifier of the planarity test analysis. */
    private static final String PLANARITY_ID = "de.cau.cs.kieler.klay.planarity";
    /** the penalty factor for missing graph features. */
    private static final double FEATURE_PENALTY = 0.7;

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        int nodeCount = metaLayout.analyze(NodeCountAnalysis.ID);
        if (nodeCount > 0) {
            int crossingEdges = metaLayout.analyze(PLANARITY_ID);
            int degreeG4Nodes = metaLayout.analyze(DegreeGreaterFourAnalysis.ID);
            double fp = SmartLayoutConfig.missingFeaturesFromType(metaLayout,
                    LayoutTypeData.TYPE_ORTHOGONAL);
            
            double planarityMeasure;
            if (crossingEdges == 0) {
                planarityMeasure = 1.0;
            } else {
                int edgeCount = metaLayout.analyze(EdgeCountAnalysis.ID);
                planarityMeasure = Math.min(Math.pow(
                        (double) edgeCount / (nodeCount * (crossingEdges + 1)), EXPONENT), 1.0);
            }
            double degreeMeasure = 1 - (double) degreeG4Nodes / nodeCount;
            return (planarityMeasure * PLANARITY_WEIGHT + degreeMeasure * (1 - PLANARITY_WEIGHT))
                    * Math.pow(FEATURE_PENALTY, fp);
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        LayoutAlgorithmData bestAlgo = SmartLayoutConfig.mostFeasibleAlgorithm(metaLayout,
                LayoutTypeData.TYPE_ORTHOGONAL);
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, bestAlgo.getId());
    }

}
