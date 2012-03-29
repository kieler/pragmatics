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
package de.cau.cs.kieler.klay.info.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutConfig;
import de.cau.cs.kieler.klay.info.views.SmartLayoutContentProvider.ResultData;

/**
 * A comparator for the smart layout view.
 *
 * @author msp
 */
public class SmartLayoutComparator extends ViewerComparator {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Viewer viewer, final Object e1, final Object e2) {
        if (e1 instanceof MetaLayout && e2 instanceof MetaLayout) {
            MetaLayout m1 = (MetaLayout) e1, m2 = (MetaLayout) e2;
            return (int) (m2.getTimestamp() - m1.getTimestamp());
        }
        
        if (e1 instanceof ResultData && e2 instanceof ResultData) {
            ResultData d1 = (ResultData) e1, d2 = (ResultData) e2;
            // add priority bias values
            double value1 = d1.getSuitability() + d1.getSmartRuleData().getPriority()
                    * SmartLayoutConfig.PRIORITY_BIAS;
            double value2 = d2.getSuitability() + d2.getSmartRuleData().getPriority()
                    * SmartLayoutConfig.PRIORITY_BIAS;
            // reverse the result to achieve descending values
            return Double.compare(value2, value1);
        }
        
        return super.compare(viewer, e1, e2);
    }

}
