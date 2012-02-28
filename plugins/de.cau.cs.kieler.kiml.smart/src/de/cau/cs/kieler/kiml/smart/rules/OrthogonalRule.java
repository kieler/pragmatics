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

import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.grana.analyses.NodeCountAnalysis;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;

/**
 * Smart layout rule for orthogonalization layout.
 *
 * @author msp
 */
public class OrthogonalRule implements ISmartRule {
    
    /** identifier of the planarity test analysis. */
    private static final String PLANARITY_ID = "de.cau.cs.kieler.klay.planarity";

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        int crossingEdges = metaLayout.analyze(PLANARITY_ID);
        int nodeCount = metaLayout.analyze(NodeCountAnalysis.ID);
        
        if (nodeCount > 0) {
            return 1 - (double) Math.min(crossingEdges, nodeCount) / nodeCount;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, LayoutTypeData.TYPE_ORTHOGONAL);
    }

}
