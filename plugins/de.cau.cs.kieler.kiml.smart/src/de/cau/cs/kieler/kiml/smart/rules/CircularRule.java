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
import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;

/**
 * Smart layout rule for circular layout type.
 *
 * @author msp
 */
public class CircularRule implements ISmartRule {

    /**
     * {@inheritDoc}
     */
    public double suitability(final MetaLayout metaLayout) {
        double density = metaLayout.analyze(BiconnectedComponentDensityAnalysis.ID);
        
        return 1 - density;
    }

    /**
     * {@inheritDoc}
     */
    public void applyMetaLayout(final MetaLayout metaLayout) {
        metaLayout.getConfig().put(LayoutOptions.ALGORITHM, LayoutTypeData.TYPE_CIRCLE);
    }

}
