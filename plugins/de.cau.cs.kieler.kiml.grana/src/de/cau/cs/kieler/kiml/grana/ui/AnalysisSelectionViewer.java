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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.core.ui.util.TreeViewerCheckStateHandler;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.service.grana.AnalysisCategory;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * A component which displays analysis categories in a given collection and lets the user select any
 * number of analyses specifed in these categories.
 * 
 * @author mri
 */
public class AnalysisSelectionViewer extends Composite implements ISelectionChangedListener {

    /** the checkbox tree viewer. */
    private CheckboxTreeViewer treeViewer;
    /** the registered selection listeners. */
    private List<ISelectionListener> listeners = new LinkedList<ISelectionListener>();

    /**
     * The constructor for an AnalysisSelectionViewer.
     * 
     * @param parent
     *            the parent composite
     * @param theCategories
     *            the categories
     * @param selectedAnalyses
     *            the pre-selected analyses
     */
    public AnalysisSelectionViewer(final Composite parent,
            final List<AnalysisCategory> theCategories, final List<AnalysisData> selectedAnalyses) {
        super(parent, SWT.NONE);
        // create the tree viewer
        treeViewer = new CheckboxTreeViewer(this);
        treeViewer.setLabelProvider(new AnalysisLabelProvider());
        treeViewer.setContentProvider(new AnalysisContentProvider(theCategories));
        treeViewer.setInput(theCategories);

        TreeViewerCheckStateHandler checkStateManager = new TreeViewerCheckStateHandler(treeViewer);
        checkStateManager.checkElements(selectedAnalyses);

        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        treeViewer.getTree().setLayoutData(data);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        this.setLayout(gridLayout);
        treeViewer.addSelectionChangedListener(this);
        // correct initial category selection
        for (AnalysisCategory category : theCategories) {
            if (category.getAnalyses().size() > 0) {
                boolean allChecked = true;
                boolean oneChecked = false;
                for (AnalysisData infoAnalysis : category.getAnalyses()) {
                    if (!treeViewer.getChecked(infoAnalysis)) {
                        allChecked = false;
                    } else {
                        oneChecked = true;
                    }
                }
                if (allChecked) {
                    treeViewer.setChecked(category, true);
                }
                if (oneChecked) {
                    treeViewer.setExpandedState(category, true);
                }
            }
        }
    }

    /**
     * Returns the selected analyses.
     * 
     * @return the selected analyses
     */
    public List<AnalysisData> getSelectedAnalyses() {
        List<AnalysisData> analyses = new LinkedList<AnalysisData>();
        Object[] checkedElements = treeViewer.getCheckedElements();
        // add only analyses to the result
        for (Object obj : checkedElements) {
            if (obj instanceof AnalysisData) {
                analyses.add((AnalysisData) obj);
            }
        }
        return analyses;
    }

    /**
     * Adds a selection listener.
     * 
     * @param listener
     *            the selection listener
     */
    public void addSelectionListener(final ISelectionListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a selection listener.
     * 
     * @param listener
     *            the selection listener
     */
    public void removeSelectionListener(final ISelectionListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all listeners.
     */
    private void notifyListeners() {
        for (ISelectionListener listener : listeners) {
            listener.selectionChanged(getSelectedAnalyses());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final SelectionChangedEvent event) {
        notifyListeners();
    }

    /**
     * The label provider for the analysis selection dialog.
     */
    private class AnalysisLabelProvider extends LabelProvider {

        /**
         * {@inheritDoc}
         */
        public String getText(final Object element) {

            if (element instanceof AnalysisData) {
                AnalysisData analysis = (AnalysisData) element;
                if (analysis.getComponents().size() > 0) {
                    String label = analysis.getName() + " (";
                    boolean first = true;
                    for (Pair<String, String> component : analysis.getComponents()) {
                        if (first) {
                            first = false;
                        } else {
                            label += ", ";
                        }
                        label += component.getFirst();
                    }
                    label += ")";
                    return label;
                } else {
                    return analysis.getName();
                }
            } else if (element instanceof AnalysisCategory) {
                AnalysisCategory category = (AnalysisCategory) element;
                return category.getName();
            }

            return super.getText(element);
        }
    }

    /**
     * The content provider for the analysis selection dialog.
     */
    private class AnalysisContentProvider implements ITreeContentProvider {

        /** maps analyses on their parents. */
        private Map<AnalysisData, AnalysisCategory> parentMap
                = new HashMap<AnalysisData, AnalysisCategory>();

        /**
         * Constructs the content provider.
         * 
         * @param analysisCategories
         *            the analysis categories
         */
        public AnalysisContentProvider(final Collection<AnalysisCategory> analysisCategories) {
            super();
            // map analyses on their parents
            for (AnalysisCategory category : analysisCategories) {
                for (AnalysisData analysis : category.getAnalyses()) {
                    parentMap.put(analysis, category);
                }
            }
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
            if (element instanceof AnalysisData) {
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
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        }
    }
}
