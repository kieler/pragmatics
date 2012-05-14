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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

import de.cau.cs.kieler.kiml.grana.GranaPlugin;

/**
 * This page displays the workspace contents and lets the user select diagram
 * files.
 * 
 * @author mri
 */
public class BatchFileSelectionPage extends WizardExportResourcesPage {

    /** the name of the file selection page. */
    private static final String PAGE_NAME = "FileSelectionPage";
    /** the description of this page. */
    private static final String MESSAGE_DESCRIPTION =
            "Select all diagram files for the batch analysis";
    /** the message for the 'layout before analysis' option. */
    private static final String MESSAGE_LAYOUT_BEFORE_ANALYSIS =
            "Layout diagrams before analysis";
    /** the error message for 'no file selected'. */
    private static final String MESSAGE_NO_FILE_SELECTED =
            "At least one diagram file has to be selected.";

    /** the preference store key for the 'layout before analysis' option. */
    private static final String PREFERENCE_LAYOUT_BEFORE_ANALYSIS =
            "grana.batch.layoutBeforeAnalysis";

    /** the list of selected files. */
    private List<IPath> selectedFiles = new LinkedList<IPath>();

    /** the layout before analysis checkbox. */
    private Button layoutBeforeAnalysisCheckbox;

    /**
     * Constructs a BatchFileSelectionPage with initial selection.
     * 
     * @param selection
     *            the selection
     */
    protected BatchFileSelectionPage(final IStructuredSelection selection) {
        super(PAGE_NAME, selection);
    }

    /**
     * {@inheritDoc}
     */
    public void handleEvent(final Event event) {
        updateSelectedFiles();
        checkError();
    }

    /**
     * Updates the list of selected files.
     */
    private void updateSelectedFiles() {
        selectedFiles.clear();
        @SuppressWarnings("rawtypes")
        List selection = super.getSelectedResources();
        for (Object obj : selection) {
            if (obj instanceof IResource) {
                IResource resource = (IResource) obj;
                IPath path = resource.getFullPath();
                selectedFiles.add(path);
            }
        }
    }

    /**
     * Checks the current status of the page and sets an appropriate error
     * message.
     * 
     * @return whether the page is error free
     */
    private boolean checkError() {
        if (selectedFiles.size() > 0) {
            setErrorMessage(null);
            return true;
        } else {
            setErrorMessage(MESSAGE_NO_FILE_SELECTED);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDestinationGroup(final Composite parent) {
        setTitle(MESSAGE_DESCRIPTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createOptionsGroupButtons(final Group parent) {
        layoutBeforeAnalysisCheckbox = new Button(parent, SWT.CHECK | SWT.LEFT);
        layoutBeforeAnalysisCheckbox.setText(MESSAGE_LAYOUT_BEFORE_ANALYSIS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFlipToNextPage() {
        updateSelectedFiles();
        return checkError();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPageComplete() {
        updateSelectedFiles();
        return checkError();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);
        loadPreferences();
    }

    /**
     * Loads the preferences for this page.
     */
    protected void loadPreferences() {
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(PREFERENCE_LAYOUT_BEFORE_ANALYSIS, true);
        boolean selected =
                preferenceStore.getBoolean(PREFERENCE_LAYOUT_BEFORE_ANALYSIS);
        layoutBeforeAnalysisCheckbox.setSelection(selected);
    }

    /**
     * Saves the preferences for this page.
     */
    protected void savePreferences() {
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(PREFERENCE_LAYOUT_BEFORE_ANALYSIS,
                layoutBeforeAnalysisCheckbox.getSelection());
    }

    /**
     * Returns the list of selected files.
     * 
     * @return the list of selected files
     */
    public List<IPath> getSelectedFiles() {
        updateSelectedFiles();
        return selectedFiles;
    }

    /**
     * Returns the value of the 'layout before analysis' option.
     * 
     * @return true if 'layout before analysis' has been activated
     */
    public boolean getLayoutBeforeAnalysis() {
        return layoutBeforeAnalysisCheckbox.getSelection();
    }
}
