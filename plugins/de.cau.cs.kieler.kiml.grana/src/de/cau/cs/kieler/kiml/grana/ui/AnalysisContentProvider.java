/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisCategory;

/**
 * The content provider for the analysis selection dialog.
 * 
 * @author mri
 */
public class AnalysisContentProvider implements ITreeContentProvider {

    /** maps analyses on their parents. */
    private Map<AbstractInfoAnalysis, AnalysisCategory> parentMap =
        new HashMap<AbstractInfoAnalysis, AnalysisCategory>();

    /**
     * Constructs the content provider.
     * 
     * @param analysisCategories
     *            the analysis categories
     */
    public AnalysisContentProvider(
            final Collection<AnalysisCategory> analysisCategories) {
        super();
        // map analyses on their parents
        for (AnalysisCategory category : analysisCategories) {
            for (AbstractInfoAnalysis analysis : category.getAnalyses()) {
                parentMap.put(analysis, category);
            }
        }
    }

    /**
     * Returns the map that maps the analyses on their parents.
     * 
     * @return the map
     */
    public Map<AbstractInfoAnalysis, AnalysisCategory> getParentMap() {
        return parentMap;
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getChildren(final Object element) {
        if (element instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) element;
            return collection.toArray();
        }
        if (element instanceof AnalysisCategory) {
            AnalysisCategory category = (AnalysisCategory) element;
            return category.getAnalyses().toArray();
        }
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(final Object element) {
        if (element instanceof AbstractInfoAnalysis) {
            return parentMap.get(element);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasChildren(final Object element) {
        if (element instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) element;
            return collection.size() > 0;
        }
        if (element instanceof AnalysisCategory) {
            AnalysisCategory category = (AnalysisCategory) element;
            return category.getAnalyses().size() > 0;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object element) {
        return getChildren(element);
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to do here
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput) {
    }

}
