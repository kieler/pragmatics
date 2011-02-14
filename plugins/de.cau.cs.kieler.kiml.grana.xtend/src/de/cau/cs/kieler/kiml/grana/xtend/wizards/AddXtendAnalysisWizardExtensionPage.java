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

import java.net.MalformedURLException;

import org.eclipse.internal.xtend.xtend.parser.ParseException;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.cau.cs.kieler.core.model.xtend.transformation.AbstractTransformation;
import de.cau.cs.kieler.kiml.grana.xtend.XtendAnalysesManager;

/**
 * The extension page for the AddXtendAnalysisWizard.<br>
 * On this page the user selects the extension the analysis is representing.
 * 
 * @author mri
 */
public class AddXtendAnalysisWizardExtensionPage extends WizardPage {

    /** the message which tells the user that the file could not be resolved. */
    private static final String MESSAGE_MALFORMED_URI =
            "The file cannot be resolved. Check the previous page";
    /** the message which asks the user to choose an extension. */
    private static final String MESSAGE_CHOOSE_EXTENSION =
            "Please choose the extension the analysis is going to represent";
    private static final String MESSAGE_PARSE_FAILED =
            "The file couldn't be parsed\nError: ";

    /** the page that preceedes this page. */
    private AddXtendAnalysisWizardGeneralPage generalPage;
    /** the extension table. */
    private Table table;
    /** the table viewer of the extension table. */
    private TableViewer tableViewer;

    /**
     * The constructor.
     * 
     * @param general
     *            the page that preceedes this page
     */
    public AddXtendAnalysisWizardExtensionPage(
            final AddXtendAnalysisWizardGeneralPage general) {
        super("extensionAddXtendWizardPage");
        generalPage = general;
        setTitle("Extension");
        setDescription(MESSAGE_CHOOSE_EXTENSION);
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        Composite extension = createExtensionTable(container);
        extension.setLayoutData(new GridData(GridData.FILL_BOTH));
        setControl(container);
        dialogChanged();
    }

    /** fixed width for the extension column. */
    private static final int COLUMN_EXTENSION_WIDTH = 200;

    /**
     * Creates the table showing the extensions to select from.
     * 
     * @param parent
     *            the parent composite
     * @return the created table
     */
    private Table createExtensionTable(final Composite parent) {
        table = new Table(parent, SWT.BORDER);
        TableColumn columnExtension = new TableColumn(table, SWT.NONE);
        columnExtension.setText("Extension");
        TableColumn columnParameters = new TableColumn(table, SWT.NONE);
        columnParameters.setText("Parameters");
        table.setHeaderVisible(true);
        tableViewer = new TableViewer(table);
        XtendExtensionTableProvider tableProvider =
                new XtendExtensionTableProvider();
        tableViewer.setLabelProvider(tableProvider);
        tableViewer.setContentProvider(tableProvider);
        tableViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {
                    public void selectionChanged(
                            final SelectionChangedEvent event) {
                        dialogChanged();
                    }
                });
        columnExtension.setWidth(COLUMN_EXTENSION_WIDTH);
        columnParameters.pack();
        GridData tableLayoutData =
                new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        table.setLayoutData(tableLayoutData);
        table.pack();
        return table;
    }

    /**
     * Handles a change of the extension selection.
     */
    private void dialogChanged() {
        if (table.getSelectionCount() > 0) {
            setPageComplete(true);
        } else {
            setPageComplete(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(final boolean visible) {
        if (visible) {
            try {
                tableViewer.setInput(XtendAnalysesManager.getInstance()
                        .getXtendAnalysisCandidates(generalPage.getFileName()));
                setErrorMessage(null);
            } catch (ParseException e) {
                setErrorMessage(MESSAGE_PARSE_FAILED + e.getMessage());
            } catch (MalformedURLException e) {
                // shouldn't happen
                setErrorMessage(MESSAGE_MALFORMED_URI);
            }
            setPageComplete(false);
        }
        super.setVisible(visible);
    }

    /**
     * Returns the name of the selected extension.
     * 
     * @return the name of the selected extension
     */
    public String getExtension() {
        if (table.getSelection().length > 0) {
            Object obj = table.getSelection()[0].getData();
            if (obj instanceof AbstractTransformation) {
                AbstractTransformation transformation =
                        (AbstractTransformation) obj;
                return transformation.getTransformation();
            }
        }
        return "";
    }
}
