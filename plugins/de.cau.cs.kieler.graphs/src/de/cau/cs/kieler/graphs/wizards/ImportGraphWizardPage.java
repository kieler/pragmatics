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
package de.cau.cs.kieler.graphs.wizards;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The wizard page from which to select the graph file to import.
 * 
 * @author mri
 */
public class ImportGraphWizardPage extends WizardPage {

    /** the message for asking the user to specify a file. */
    private static final String MESSAGE_SPECIFY_FILE =
            "Please specify a file to import.";
    /** the message for telling the user that the selected file does not exist. */
    private static final String MESSAGE_NO_SUCH_FILE = "File does not exist: ";
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK =
            "Imports a graph from various file formats to a graphs file.";

    /** the file selection text. */
    private Text fileText;

    /**
     * Constructs the import wizard page.
     */
    public ImportGraphWizardPage() {
        super("importGraphWizardPage");
        setTitle("Import Graph");
        setDescription(MESSAGE_SPECIFY_FILE);
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        Label label = new Label(container, SWT.NULL);
        label.setText("&From file:");

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setText("Browse...");
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                handleBrowse();
            }
        });
        setControl(container);
        setPageComplete(false);
    }

    /**
     * Handles a change of the file selection text.
     */
    private void dialogChanged() {
        String fileName = fileText.getText().trim();
        if (fileName.length() > 0) {
            File file = new File(fileName);
            if (!file.exists() || file.isDirectory()) {
                setErrorMessage(MESSAGE_NO_SUCH_FILE + fileName);
                setPageComplete(false);
            } else {
                setErrorMessage(null);
                setMessage(MESSAGE_OK);
                setPageComplete(true);
            }
        } else {
            setErrorMessage(null);
            setMessage(MESSAGE_SPECIFY_FILE);
            setPageComplete(false);
        }
    }

    /**
     * Handles the file selection browse.
     */
    private void handleBrowse() {
        FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.OPEN);
        dialog.setFilterExtensions(new String[] { "*.graphml" });
        String file = dialog.open();
        if (file != null) {
            fileText.setText(file);
        }
    }

    /**
     * Returns the selected file name.
     * 
     * @return the file name
     */
    public String getFileName() {
        return fileText.getText();
    }
}
