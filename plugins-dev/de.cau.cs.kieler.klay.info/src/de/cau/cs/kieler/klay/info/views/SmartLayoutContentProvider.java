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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.kiml.smart.ISmartRule;
import de.cau.cs.kieler.kiml.smart.MetaLayout;

/**
 * Content provider for the smart layout view.
 *
 * @author msp
 */
public class SmartLayoutContentProvider implements ITreeContentProvider {
    
    /**
     * Data class for smart layout rule results.
     */
    public static class ResultData {
        private ISmartRule smartRule;
        private double suitability;
        private MetaLayout metaLayout;
        
        /**
         * Returns the smart rule.
         * 
         * @return the smart rule
         */
        public ISmartRule getSmartRule() {
            return smartRule;
        }
        
        /**
         * Returns the suitability value determined by the smart rule.
         * 
         * @return the suitability value
         */
        public double getSuitability() {
            return suitability;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof List<?>) {
            return ((List<?>) inputElement).toArray();
        }
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasChildren(final Object element) {
        return element instanceof MetaLayout;
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof MetaLayout) {
            MetaLayout metaLayout = (MetaLayout) parentElement;
            Collection<Map.Entry<ISmartRule, Double>> results = metaLayout.getResults().entrySet();
            ResultData[] dataArray = new ResultData[results.size()];
            int i = 0;
            for (Map.Entry<ISmartRule, Double> entry : results) {
                ResultData data = new ResultData();
                data.metaLayout = metaLayout;
                data.smartRule = entry.getKey();
                data.suitability = entry.getValue();
                dataArray[i++] = data;
            }
            Arrays.sort(dataArray, new Comparator<ResultData>() {
                public int compare(final ResultData d1, final ResultData d2) {
                    // reverse the result to achieve descending values
                    return Double.compare(d2.suitability, d1.suitability);
                }
            });
            return dataArray;
        }
        return new ResultData[0];
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(final Object element) {
        if (element instanceof ResultData) {
            return ((ResultData) element).metaLayout;
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
