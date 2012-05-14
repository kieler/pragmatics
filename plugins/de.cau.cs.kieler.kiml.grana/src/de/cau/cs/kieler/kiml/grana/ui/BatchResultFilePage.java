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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import de.cau.cs.kieler.kiml.grana.GranaPlugin;

/**
 * This page lets the user select a file for the results of the batch.
 * 
 * @author mri
 */
public class BatchResultFilePage extends WizardNewFileCreationPage {

    /** the name of the file selection page. */
    private static final String PAGE_NAME = "ResultFilePage";
    /** the description of this page. */
    private static final String MESSAGE_DESCRIPTION =
            "Select a file for the batch results";
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK =
            "Create a new result file for the batch results.";

    /** the preference store key for the result path. */
    private static final String PREFERENCE_PATH = "grana.batch.resultPath";

    /**
     * Constructs a BatchResultFilePage.
     */
    protected BatchResultFilePage() {
        super(PAGE_NAME, new StructuredSelection());
        setTitle(MESSAGE_DESCRIPTION);
        setDescription(MESSAGE_OK);
        setAllowExistingResources(true);
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
        if (preferenceStore.contains(PREFERENCE_PATH)) {
            String pathString = preferenceStore.getString(PREFERENCE_PATH);
            IPath path = new Path(pathString);
            if (!path.lastSegment().isEmpty()) {
                setFileName(path.lastSegment());
            }
            setContainerFullPath(path.removeLastSegments(1));
        }
    }

    /**
     * Saves the preferences for this page.
     */
    protected void savePreferences() {
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(PREFERENCE_PATH, getResultFile().toString());
    }

    /**
     * Returns the selected result file path.
     * 
     * @return the file path
     */
    public IPath getResultFile() {
        return getContainerFullPath().append(getFileName());
    }
}
