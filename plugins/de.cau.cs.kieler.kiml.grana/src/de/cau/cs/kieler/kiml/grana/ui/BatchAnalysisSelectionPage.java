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

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * This page displays the available analyses and lets the user select any number
 * of them for the batch run.
 * 
 * @author mri
 */
public class BatchAnalysisSelectionPage extends WizardPage implements
        ISelectionListener {

    /** the name of the file selection page. */
    private static final String PAGE_NAME = "AnalysisSelectionPage";
    /** the description of this page. */
    private static final String MESSAGE_DESCRIPTION =
            "Select the analyses for the batch";
    /** the error message for 'no analysis selected'. */
    private static final String MESSAGE_NO_ANALYSIS_SELECTED =
            "At least one analysis has to be selected.";

    /** the analysis selection viewer. */
    private AnalysisSelectionViewer viewer;
    /** the initally selected analyses. */
    private List<AnalysisData> initialAnalyses;

    /**
     * Constructs a BatchAnalysisSelectionPage.
     * 
     * @param selectedAnalyses
     *            the selected analyses
     */
    public BatchAnalysisSelectionPage(
            final List<AnalysisData> selectedAnalyses) {
        super(PAGE_NAME);
        initialAnalyses = selectedAnalyses;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        setTitle(MESSAGE_DESCRIPTION);
        viewer =
                new AnalysisSelectionViewer(
                        parent,
                        AnalysisService.getInstance().getCategories(),
                        initialAnalyses);
        setControl(viewer);
        selectionChanged(getAnalyses());
        viewer.addSelectionListener(this);
    }

    /**
     * Returns the selected analyses.
     * 
     * @return the selected analyses
     */
    public List<AnalysisData> getAnalyses() {
        return viewer.getSelectedAnalyses();
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(
            final List<AnalysisData> selectedAnalyses) {
        if (selectedAnalyses.size() > 0) {
            setErrorMessage(null);
        } else {
            setErrorMessage(MESSAGE_NO_ANALYSIS_SELECTED);
        }
        setPageComplete(selectedAnalyses.size() > 0);
    }
}
