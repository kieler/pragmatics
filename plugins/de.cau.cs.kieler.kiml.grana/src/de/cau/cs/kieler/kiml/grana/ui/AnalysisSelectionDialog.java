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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisCategory;

/**
 * The dialog that shows a selection of graph analyses to the user to select
 * from.
 * 
 * @author mri
 */
public class AnalysisSelectionDialog extends CheckedTreeSelectionDialog {

    /** the dialogs title. */
    private static final String TITLE = "Select analyses";
    /** the information message. */
    private static final String MESSAGE_INFO = "Select the analyses to perform";
    /** the no-selection error message. */
    private static final String MESSAGE_NO_SELECTION =
            "At least one analysis has to be selected.";
    /** the selected analyses. */
    private List<AbstractInfoAnalysis> result;

    /** maps analyses on their parents. */
    private Map<AbstractInfoAnalysis, AnalysisCategory> parentMap;

    /**
     * Constructs the dialog.
     * 
     * @param parent
     *            the parent shell
     * 
     * @param analysisCategories
     *            the categories to display
     */
    public AnalysisSelectionDialog(final Shell parent,
            final Collection<AnalysisCategory> analysisCategories) {
        super(parent, new AnalysisLabelProvider(), new AnalysisContentProvider(
                analysisCategories));
        // set general options
        setTitle(TITLE);
        setMessage(MESSAGE_INFO);
        // set the tree input
        setInput(analysisCategories);
        // set a custom selection validator
        setValidator(new ISelectionStatusValidator() {
            public IStatus validate(final Object[] selection) {
                if (selection.length > 0) {
                    return new Status(IStatus.OK, PlatformUI.PLUGIN_ID,
                            IStatus.OK, "", null);
                } else {
                    return new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID,
                            IStatus.OK, MESSAGE_NO_SELECTION, null);
                }
            }
        });
    }

    /**
     * Creates the dialog.
     */
    public void create() {
        super.create();
        // get the analysis parent mapping
        AnalysisContentProvider contentProvider =
                (AnalysisContentProvider) getTreeViewer().getContentProvider();
        parentMap = contentProvider.getParentMap();
        // add a check state listener
        getTreeViewer().addCheckStateListener(
                new CheckStateListener(getTreeViewer()));
        // correct initial category selection
        for (AnalysisCategory category : parentMap.values()) {
            boolean allChecked = true;
            boolean oneChecked = false;
            for (AbstractInfoAnalysis infoAnalysis : category.getAnalyses()) {
                if (!getTreeViewer().getChecked(infoAnalysis)) {
                    allChecked = false;
                } else {
                    oneChecked = true;
                }
            }
            if (allChecked) {
                getTreeViewer().setChecked(category, true);
            }
            if (oneChecked) {
                getTreeViewer().setExpandedState(category, true);
            }
        }
    }

    /**
     * Returns the selected analyses.
     * 
     * @return the analyses
     */
    public List<AbstractInfoAnalysis> getAnalyses() {
        return result;
    }

    /**
     * {@inheritDoc}
     */
    protected void computeResult() {
        result = new LinkedList<AbstractInfoAnalysis>();
        Object[] checkedElements = getTreeViewer().getCheckedElements();
        // add only analyses to the result
        for (Object obj : checkedElements) {
            if (obj instanceof AbstractInfoAnalysis) {
                result.add((AbstractInfoAnalysis) obj);
            }
        }
        setResult(result);
    }

    /**
     * A check state listener for providing selection consistency.
     */
    private class CheckStateListener implements ICheckStateListener {

        private CheckboxTreeViewer treeViewer;

        /**
         * Constructs a check state listener.
         * 
         * @param checkboxTreeViewer
         *            the tree viewer this listener is registered on
         */
        public CheckStateListener(final CheckboxTreeViewer checkboxTreeViewer) {
            treeViewer = checkboxTreeViewer;
        }

        /**
         * {@inheritDoc}
         */
        public void checkStateChanged(final CheckStateChangedEvent event) {
            Object obj = event.getElement();
            if (obj instanceof AnalysisCategory) {
                // the check state of a category was changed
                AnalysisCategory category = (AnalysisCategory) obj;
                for (AbstractInfoAnalysis analysis : category.getAnalyses()) {
                    treeViewer.setChecked(analysis, event.getChecked());
                }
                updateOKStatus();
                treeViewer.setExpandedState(category, true);
            } else if (obj instanceof AbstractInfoAnalysis) {
                // the check state of an analysis was changed
                AbstractInfoAnalysis analysis = (AbstractInfoAnalysis) obj;
                AnalysisCategory category = parentMap.get(analysis);
                if (category != null) {
                    if (event.getChecked()) {
                        for (AbstractInfoAnalysis infoAnalysis : category
                                .getAnalyses()) {
                            if (!treeViewer.getChecked(infoAnalysis)) {
                                return;
                            }
                        }
                        treeViewer.setChecked(category, true);
                    } else {
                        treeViewer.setChecked(category, false);
                    }
                }
            }
        }

    }
}
