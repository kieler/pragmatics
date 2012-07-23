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
package de.cau.cs.kieler.keg.importer.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * The import graph wizard page which lets the user select whether to import from the workspace or
 * the file system.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class ImportGraphSourcesPage extends WizardPage {

    /** whether to import from workspace or from file system. */
    private boolean importFromWorkspace;

    /**
     * Constructs an ImportGraphSourcesPage.
     */
    public ImportGraphSourcesPage() {
        super("importGraphSourcesPage"); //$NON-NLS-1$
        setTitle(Messages.ImportGraphSourcesPage_title);
        setMessage(Messages.ImportGraphSourcesPage_message);
        importFromWorkspace = true;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createSources(composite);
        setControl(composite);
    }

    // CHECKSTYLEOFF MagicNumber
    private void createSources(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(gridData);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.verticalSpacing = 9;
        composite.setLayout(layout);
        // add the source selection
        Label sourceText = new Label(composite, SWT.NULL);
        sourceText.setText(Messages.ImportGraphSourcesPage_import_message);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        sourceText.setLayoutData(gridData);
        // add file system button
        Button fileSystemSource = new Button(composite, SWT.RADIO | SWT.LEFT);
        fileSystemSource.setText(Messages.ImportGraphSourcesPage_file_system_caption);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalIndent = 20;
        fileSystemSource.setLayoutData(gridData);
        fileSystemSource.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                importFromWorkspace = false;
                getWizard().getContainer().updateButtons();
            }
        });
        fileSystemSource.setSelection(!importFromWorkspace);
        // add workspace button
        Button workspaceSource = new Button(composite, SWT.RADIO | SWT.LEFT);
        workspaceSource.setText(Messages.ImportGraphSourcesPage_workspace_caption);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalIndent = 20;
        workspaceSource.setLayoutData(gridData);
        workspaceSource.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                importFromWorkspace = true;
                getWizard().getContainer().updateButtons();
            }
        });
        workspaceSource.setSelection(importFromWorkspace);
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Returns whether to import from the workspace or the file system.
     * 
     * @return true to import from the workspace; false to import from the file system
     */
    public boolean getImportFromWorkspace() {
        return importFromWorkspace;
    }

    /**
     * An adapter class for the SelectionListener.
     */
    private abstract static class SelectionListenerAdapter implements SelectionListener {

        public void widgetDefaultSelected(final SelectionEvent e) {
            // do nothing
        }
    }
}
