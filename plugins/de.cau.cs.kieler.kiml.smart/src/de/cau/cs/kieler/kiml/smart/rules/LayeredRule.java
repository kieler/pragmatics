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
import de.cau.cs.kieler.kiml.service.grana.analyses.CycleAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.NodeCountAnalysis;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;

/**
 * Smart layout rule for layered layout type.
 *
 * @author msp
 */
public class LayeredRule implements ISmartRule {
    
    private static final double MAX_CYCLE_RATE = 0.3;

    /**
     * {@inheritDoc}
     */
    public boolean isSuitable(final MetaLayout metaLayout) {
        int nodeCount = (Integer) metaLayout.analyze(NodeCountAnalysis.ID);
        int cycleCount = (Integer) metaLayout.analyze(CycleAnalysis.ID);
        
        System.out.println("Layered Rule: " + nodeCount + " nodes, " + cycleCount
                + " cycles, cycle rate = " + ((double) cycleCount / nodeCount));
        return (double) cycleCount / nodeCount <= MAX_CYCLE_RATE;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, LayoutTypeData.TYPE_LAYERED);
    }

}
