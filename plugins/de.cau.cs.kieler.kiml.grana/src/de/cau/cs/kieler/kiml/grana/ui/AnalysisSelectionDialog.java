/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kiml.grana.AnalysisCategory;
import de.cau.cs.kieler.kiml.grana.AnalysisData;

/**
 * The dialog that shows a selection of graph analyses to the user to select from.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class AnalysisSelectionDialog extends Dialog {

    /** the dialogs title. */
    private static final String TITLE = "Select analyses";
    /** the width of the dialog area. */
    private static final int WIDTH = 400;
    /** the height of the dialog area. */
    private static final int HEIGHT = 400;

    /** the selected analyses. */
    private List<AnalysisData> result = null;
    /** the analyses categories. */
    private List<AnalysisCategory> categories;
    /** the initially selected analyses. */
    private List<AnalysisData> initialAnalyses;
    /** the analysis selection viewer. */
    private AnalysisSelectionViewer analysisSelectionViewer;

    /**
     * Constructs the dialog.
     * 
     * @param parent
     *            the parent shell
     * @param analysisCategories
     *            the categories to display
     * @param selectedAnalyses
     *            the currently selectedAnalyses
     */
    public AnalysisSelectionDialog(final Shell parent,
            final List<AnalysisCategory> analysisCategories,
            final List<AnalysisData> selectedAnalyses) {
        super(parent);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        categories = analysisCategories;
        initialAnalyses = selectedAnalyses;
    }

    /**
     * Returns the selected analyses. Only valid if the user confirmed the dialog instead of canceling.
     * 
     * @return the analyses
     */
    public List<AnalysisData> getAnalyses() {
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        analysisSelectionViewer = new AnalysisSelectionViewer(parent, categories,
                initialAnalyses);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.widthHint = WIDTH;
        data.heightHint = HEIGHT;
        analysisSelectionViewer.setLayoutData(data);
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(TITLE);
    }

    @Override
    protected void okPressed() {
        // Remember the selection in the viewer before closing the dialog
        result = analysisSelectionViewer.getSelectedAnalyses();
        
        super.okPressed();
    }
    
}
