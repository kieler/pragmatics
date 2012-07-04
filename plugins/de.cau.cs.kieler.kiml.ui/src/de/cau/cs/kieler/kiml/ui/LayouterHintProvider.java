/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;

/**
 * Content provider for displaying layout algorithms sorted by layout type.
 *
 * @author msp
 */
public class LayouterHintProvider implements ITreeContentProvider {

    /** the layout services used for this provider. */
    private LayoutDataService layoutServices;
    /** the filter map that stores visibility information. */
    private final Map<Object, Boolean> filterMap = new HashMap<Object, Boolean>();
    /** the current filter value. */
    private String filterValue;
    /** the current best filter match. */
    private String bestFilterMatch;
    
    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof LayoutDataService) {
            layoutServices = (LayoutDataService) inputElement;
        }
        return layoutServices.getTypeData().toArray();
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof LayoutTypeData) {
            LayoutTypeData typeData = (LayoutTypeData) parentElement;
            return typeData.getLayouters().toArray();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(final Object element) {
        if (element instanceof LayoutAlgorithmData) {
            LayoutAlgorithmData layouterData = (LayoutAlgorithmData) element;
            return layoutServices.getTypeData(layouterData.getType());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasChildren(final Object element) {
        if (element instanceof LayoutTypeData) {
            return ((LayoutTypeData) element).getLayouters().size() > 0;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }
    
    /**
     * Update the filter value for this provider.
     * 
     * @param filter the new filter value
     */
    public void updateFilter(final String filter) {
        this.filterValue = filter;
        if (filterValue != null) {
            filterValue = filterValue.toLowerCase();
        }
        filterMap.clear();
        bestFilterMatch = null;
    }
    
    /**
     * Apply the current filter to the given element.
     * 
     * @param element an element from the content
     * @return true if the filter admits the element
     */
    public boolean applyFilter(final Object element) {
        Boolean result = filterMap.get(element);
        if (result == null) {
            if (filterValue != null && filterValue.length() > 0) {
                if (element instanceof LayoutTypeData) {
                    LayoutTypeData typeData = (LayoutTypeData) element;
                    result = typeData.getName().toLowerCase().contains(filterValue);
                    if (result) {
                        for (LayoutAlgorithmData layouterData : typeData.getLayouters()) {
                            filterMap.put(layouterData, Boolean.TRUE);
                        }
                        if (bestFilterMatch == null) {
                            bestFilterMatch = typeData.getId();
                        } else {
                            bestFilterMatch = "";
                        }
                    } else {
                        boolean hasFilteredChild = false;
                        for (LayoutAlgorithmData layouterData : typeData.getLayouters()) {
                            hasFilteredChild |= applyFilter(layouterData);
                        }
                        result = hasFilteredChild;
                    }
                } else if (element instanceof LayoutAlgorithmData) {
                    LayoutAlgorithmData layouterData = (LayoutAlgorithmData) element;
                    if (layouterData.getName().toLowerCase().contains(filterValue)) {
                        result = Boolean.TRUE;
                        if (bestFilterMatch == null) {
                            bestFilterMatch = layouterData.getId();
                        } else {
                            bestFilterMatch = "";
                        }
                    } else {
                        String category = LayoutDataService.getInstance().getCategoryName(
                                layouterData.getCategory());
                        result = category != null && category.toLowerCase().contains(filterValue);
                    }
                }
            } else {
                result = Boolean.TRUE;
            }
            filterMap.put(element, result);
        }
        return result;
    }
    
    /**
     * Returns the best match of the currently active filter.
     * 
     * @return the best filter match
     */
    public String getBestFilterMatch() {
        return bestFilterMatch;
    }

}
