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
package de.cau.cs.kieler.kiml.grana.xtend.wizards;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.kiml.service.AnalysisServices;
import de.cau.cs.kieler.kiml.service.grana.AbstractInfoAnalysis;

/**
 * The dependencies page for the AddXtendAnalysisWizard.<br>
 * On this page the user selects the dependencies of the analysis.
 * 
 * @author mri
 */
public class AddXtendAnalysisWizardDependenciesPage extends WizardPage {

    /** image for the UP button. **/
    private static final Image UP_ARROW = AbstractUIPlugin
            .imageDescriptorFromPlugin("de.cau.cs.kieler.kiml.grana.xtend",
                    "icons/up.png").createImage();
    /** image for the DOWN button. **/
    private static final Image DOWN_ARROW = AbstractUIPlugin
            .imageDescriptorFromPlugin("de.cau.cs.kieler.kiml.grana.xtend",
                    "icons/down.png").createImage();

    /** the analyses table. */
    private Table analysesTable;
    /** the analyses table viewer. */
    private TableViewer analysesTableViewer;
    /** the dependencies table. */
    private Table dependenciesTable;
    /** the dependencies table viewer. */
    private TableViewer dependenciesTableViewer;

    /**
     * The constructor.
     */
    public AddXtendAnalysisWizardDependenciesPage() {
        super("dependenciesAddXtendWizardPage");
        setTitle("Dependencies");
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        Composite container = createAnalysisTables(parent);
        setControl(container);
        analysesTableViewer.setInput(AnalysisServices.getInstance()
                .getAnalyses());
        setPageComplete(true);
    }

    /** the number of columns of the grid. */
    private static final int NUM_COLUMNS = 2;
    /** the table height. */
    private static final int TABLE_HEIGHT = 100;
    /** the number of buttons in the button container. */
    private static final int NUM_BUTTONS = 4;
    /** the button width. */
    private static final int BUTTON_WIDTH = 100;
    /** the width of the name column. */
    private static final int NAME_COLUMN_WIDTH = 200;

    /**
     * Creates the two analysis tables and the required buttons.
     * 
     * @param parent
     *            the parent composite
     * @return the created composite
     */
    public Composite createAnalysisTables(final Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = NUM_COLUMNS;
        // analyses table
        analysesTable = new Table(container, SWT.BORDER);
        TableColumn column11 = new TableColumn(analysesTable, SWT.NONE);
        column11.setText("Name");
        TableColumn column12 = new TableColumn(analysesTable, SWT.NONE);
        column12.setText("Description");
        analysesTable.setHeaderVisible(true);
        analysesTableViewer = new TableViewer(analysesTable);
        AnalysisTableProvider analysesTableProvider =
                new AnalysisTableProvider();
        analysesTableViewer.setLabelProvider(analysesTableProvider);
        analysesTableViewer.setContentProvider(analysesTableProvider);
        column11.setWidth(NAME_COLUMN_WIDTH);
        column12.pack();
        GridData layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        analysesTable.setLayoutData(layoutData);
        layoutData.heightHint = TABLE_HEIGHT;
        analysesTable.pack();
        analysesTable.addMouseListener(new MouseListener() {
            public void mouseUp(final MouseEvent e) {
            }

            public void mouseDown(final MouseEvent e) {
            }

            public void mouseDoubleClick(final MouseEvent e) {
                for (TableItem item : analysesTable.getSelection()) {
                    dependenciesTableViewer.add(item.getData());
                    analysesTableViewer.remove(item.getData());
                }
            }
        });
        new Composite(container, SWT.NONE);
        // button container
        Composite buttonContainer = new Composite(container, SWT.NONE);
        buttonContainer.setLayout(new GridLayout(NUM_BUTTONS, true));
        Button addButton = new Button(buttonContainer, SWT.PUSH);
        addButton.setText("Add");
        layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        addButton.setLayoutData(layoutData);
        layoutData.widthHint = BUTTON_WIDTH;
        addButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                for (TableItem item : analysesTable.getSelection()) {
                    dependenciesTableViewer.add(item.getData());
                    analysesTableViewer.remove(item.getData());
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        Button addAllButton = new Button(buttonContainer, SWT.PUSH);
        addAllButton.setText("Add All");
        layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        addAllButton.setLayoutData(layoutData);
        layoutData.widthHint = BUTTON_WIDTH;
        addAllButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                for (TableItem item : analysesTable.getItems()) {
                    dependenciesTableViewer.add(item.getData());
                    analysesTableViewer.remove(item.getData());
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        Button removeButton = new Button(buttonContainer, SWT.PUSH);
        removeButton.setText("Remove");
        layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        removeButton.setLayoutData(layoutData);
        layoutData.widthHint = BUTTON_WIDTH;
        removeButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                for (TableItem item : dependenciesTable.getSelection()) {
                    analysesTableViewer.add(item.getData());
                    dependenciesTableViewer.remove(item.getData());
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        Button removeAllButton = new Button(buttonContainer, SWT.PUSH);
        removeAllButton.setText("Remove All");
        layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        removeAllButton.setLayoutData(layoutData);
        layoutData.widthHint = BUTTON_WIDTH;
        removeAllButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent e) {
                for (TableItem item : dependenciesTable.getItems()) {
                    analysesTableViewer.add(item.getData());
                    dependenciesTableViewer.remove(item.getData());
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        new Composite(container, SWT.NONE);
        // dependencies table
        dependenciesTable = new Table(container, SWT.BORDER);
        TableColumn column21 = new TableColumn(dependenciesTable, SWT.NONE);
        column21.setText("Name");
        TableColumn column22 = new TableColumn(dependenciesTable, SWT.NONE);
        column22.setText("Description");
        dependenciesTable.setHeaderVisible(true);
        dependenciesTableViewer = new TableViewer(dependenciesTable);
        AnalysisTableProvider dependenciesTableProvider =
                new AnalysisTableProvider();
        dependenciesTableViewer.setLabelProvider(dependenciesTableProvider);
        dependenciesTableViewer.setContentProvider(dependenciesTableProvider);
        column21.setWidth(NAME_COLUMN_WIDTH);
        column22.pack();
        layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        dependenciesTable.setLayoutData(layoutData);
        layoutData.heightHint = TABLE_HEIGHT;
        dependenciesTable.pack();
        dependenciesTable.addMouseListener(new MouseListener() {
            public void mouseUp(final MouseEvent e) {
            }

            public void mouseDown(final MouseEvent e) {
            }

            public void mouseDoubleClick(final MouseEvent e) {
                for (TableItem item : dependenciesTable.getSelection()) {
                    analysesTableViewer.add(item.getData());
                    dependenciesTableViewer.remove(item.getData());
                }
            }
        });
        // up and down button
        Composite upDownContainer = new Composite(container, SWT.NONE);
        upDownContainer.setLayout(new GridLayout(1, false));
        Button up = new Button(upDownContainer, SWT.PUSH);
        up.setImage(UP_ARROW);
        Button down = new Button(upDownContainer, SWT.PUSH);
        down.setImage(DOWN_ARROW);
        return container;
    }

    /**
     * Returns the ids of the selected dependencies.
     * 
     * @return the selected dependencies
     */
    public List<String> getDependencies() {
        LinkedList<String> dependencies = new LinkedList<String>();
        for (TableItem item : dependenciesTable.getItems()) {
            Object obj = item.getData();
            if (obj instanceof AbstractInfoAnalysis) {
                AbstractInfoAnalysis analysis = (AbstractInfoAnalysis) obj;
                dependencies.add(analysis.getId());
            }
        }
        return dependencies;
    }
}
