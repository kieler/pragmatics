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
package de.cau.cs.kieler.kiml.grana.xtend.ui;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kiml.grana.xtend.GranaXtendPlugin;
import de.cau.cs.kieler.kiml.grana.xtend.XtendAnalysesManager;

/**
 * The general preference page for the grana xtend analyses.
 * 
 * @author mri
 */
public class XtendAnalysesPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    /** the description for this preference page. */
    private static final String DESCRIPTION =
            "Preferences for the Xtend Graph Analyzes.";

    /**
     * The constructor.
     */
    public XtendAnalysesPreferencePage() {
        setDescription(DESCRIPTION);
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(GranaXtendPlugin.getDefault().getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     */
    protected Control createContents(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        Group xtendAnalysesGroup = createXtendAnalysesGroup(composite);
        xtendAnalysesGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true,
                false));
        GridLayout compositeLayout = new GridLayout(1, false);
        composite.setLayout(compositeLayout);
        return composite;
    }

    /**
     * Creates the group and list that hold the currently registered xtend
     * analyses.
     * 
     * @param parent
     *            the parent composite
     * @return a group with the xtend analyses table
     */
    private Group createXtendAnalysesGroup(final Composite parent) {
        Group xtendAnalysesGroup = new Group(parent, SWT.NONE);
        xtendAnalysesGroup.setText("Xtend Analyzes");
        addXtendAnalysesTable(xtendAnalysesGroup);
        xtendAnalysesGroup.setLayout(new GridLayout(2, false));
        return xtendAnalysesGroup;
    }
    
    /** fixed height of the xtend analyses table. */
    private static final int XTEND_ANALYSES_TABLE_HEIGHT = 200;

    /**
     * Adds a table to display xtend analyses and buttons to edit the options.
     * 
     * @param parent the parent to which controls are added
     */
    private void addXtendAnalysesTable(final Composite parent) {
        // construct the options table
        final Table table = new Table(parent, SWT.BORDER);
        TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setText("Name");
        TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setText("Extension");
        TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setText("File");
        table.setHeaderVisible(true);
        final TableViewer tableViewer = new TableViewer(table);
        XtendAnalysesTableProvider tableProvider = new XtendAnalysesTableProvider();
        tableViewer.setLabelProvider(tableProvider);
        tableViewer.setContentProvider(tableProvider);
        tableViewer.setInput(XtendAnalysesManager.getInstance().getXtendAnalyses());
        column1.pack();
        column2.pack();
        column3.pack();
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
        table.setLayoutData(tableLayoutData);
        table.pack();
        tableLayoutData.heightHint = XTEND_ANALYSES_TABLE_HEIGHT;
        
        // add buttons
        Composite composite = new Composite(parent, SWT.NONE);
        final Button addButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        addButton.setText("Add...");
        addButton.setEnabled(true);
        addButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
            }
        });
        final Button editButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        editButton.setText("Edit...");
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
            }
        });
        final Button removeButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        removeButton.setText("Remove");
        removeButton.setEnabled(false);
        removeButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
            }
        });
        table.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                if (e.item != null) {
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
                } else {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
            }
        });
        composite.setLayout(new FillLayout(SWT.VERTICAL));
        GridData compositeLayoutData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        composite.setLayoutData(compositeLayoutData);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean performOk() {
        // TODO Auto-generated method stub
        return super.performOk();
    }
}
