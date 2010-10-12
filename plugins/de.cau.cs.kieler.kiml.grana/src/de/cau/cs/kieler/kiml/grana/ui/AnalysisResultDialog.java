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
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.ui.CoreUIPlugin;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;

/**
 * The dialog for presenting the results of a set of analyses.
 * 
 * @author mri
 */
public class AnalysisResultDialog extends Dialog {

    /** the dialogs title. */
    private static final String TITLE = "Analysis results";
    /** the dialogs default width. */
    private static final int DEFAULT_WIDTH = 580;
    /** the dialogs default height. */
    private static final int DEFAULT_HEIGHT = 600;

    /** the html this dialog is displaying. */
    private String html;

    /**
     * Constructs the dialog.
     * 
     * @param parentShell
     *            the parent shell
     * @param analyses
     *            the perfomed analyses
     * @param results
     *            the analysis results to display
     */
    public AnalysisResultDialog(final Shell parentShell,
            final List<AbstractInfoAnalysis> analyses,
            final Map<String, Object> results) {
        super(parentShell);
        // build the html content
        html = HtmlResultGenerator.generate(analyses, results);

        setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.BORDER
                | SWT.APPLICATION_MODAL | SWT.RESIZE);
    }

    /**
     * {@inheritDoc}
     */
    protected Control createDialogArea(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        composite.setLayoutData(data);

        Browser browser;
        try {
            browser = new Browser(composite, SWT.NONE);
            browser.setLayoutData(new GridData(GridData.FILL_BOTH));
            if (html != null) {
                browser.setText(html);
            }
        } catch (SWTError e) {
            IStatus status = new Status(IStatus.ERROR, CoreUIPlugin.PLUGIN_ID,
                    "Could not instantiate Browser.", e);
            StatusManager.getManager().handle(status);
        }
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(TITLE);
    }

    /**
     * {@inheritDoc}
     */
    protected Point getInitialSize() {
        return new Point(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    protected Control createContents(final Composite parent) {
        Composite composite = new Composite(parent, 0);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        applyDialogFont(composite);
        // initialize the dialog units
        initializeDialogUnits(composite);
        // create the dialog area and button bar
        dialogArea = createDialogArea(composite);
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    protected Control getButtonBar() {
        return super.getButtonBar();
    }

    /**
     * Returns whether this dialog is empty.
     * 
     * @return true if this dialog is empty
     */
    public boolean isEmpty() {
        return html == null;
    }
}
