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

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.grana.analyses.CompoundEdgeAnalysis;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;

/**
 * Smart layout rule for hierarchical graphs with hierarchy crossing edges.
 *
 * @author msp
 */
public class HierarchyRule implements ISmartRule {
    
    /** identifier of the KLay Layered algorithm. */
    private static final String KLAY_LAYERED_ID = "de.cau.cs.kieler.klay.layered";

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        int compoundEdgeCount = metaLayout.analyze(CompoundEdgeAnalysis.ID, 0);
        
        return compoundEdgeCount > 0 ? 1 : 0;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, KLAY_LAYERED_ID);
        metaLayout.getConfig().put(LayoutOptions.LAYOUT_HIERARCHY, true);
    }

}
