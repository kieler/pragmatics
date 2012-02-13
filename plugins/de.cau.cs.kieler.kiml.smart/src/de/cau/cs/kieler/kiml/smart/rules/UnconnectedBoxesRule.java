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
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.util.BoxLayoutProvider;

/**
 * Smart layout rule for unconnected boxes.
 *
 * @author msp
 */
public class UnconnectedBoxesRule implements ISmartRule {

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        int edgeCount = metaLayout.analyze(EdgeCountAnalysis.ID);
        
        System.out.println("Unconnected Boxes Rule: " + edgeCount + " edges");
        return edgeCount == 0 ? 1 : 0;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, BoxLayoutProvider.ID);
    }

}
