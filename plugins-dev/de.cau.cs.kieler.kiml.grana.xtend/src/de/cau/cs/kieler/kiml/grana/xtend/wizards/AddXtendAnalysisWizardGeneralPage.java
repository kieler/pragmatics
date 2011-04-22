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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.ui.providers.ConditionalWorkbenchContentProvider;

/**
 * The general page for the AddXtendAnalysisWizard.<br>
 * On this page the user selects the xtend file and chooses a name for the
 * analysis.
 * 
 * @author mri
 */
public class AddXtendAnalysisWizardGeneralPage extends WizardPage {

    /** the message for asking the user to specify a file. */
    private static final String MESSAGE_SPECIFY_FILE =
            "Please specify a xtend file to select an extension from";
    /** the message for asking the user to specify an analysis name. */
    private static final String MESSAGE_SPECIFY_ANALYSIS_NAME =
            "Please specify a name for the analysis.";
    /** the message for telling the user that the selected file does not exist. */
    private static final String MESSAGE_NO_SUCH_FILE = "File does not exist: ";
    /** the message for telling the user that the extension is wrong. */
    private static final String MESSAGE_WRONG_EXT =
            "File extension does not match 'ext'";
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK =
            "Add an extension from the xtend file as analysis";

    /** the file selection text. */
    private Text fileText;
    /** the analysis name text. */
    private Text analysisNameText;
    /** the analysis description text. */
    private Text analysisDescriptionText;
    
    /** the initial file path. */
    private String initialFile;

    /**
     * The constructor.
     * 
     * @param theFile
     *            the source file
     */
    public AddXtendAnalysisWizardGeneralPage(final IFile theFile) {
        super("generalAddXtendWizardPage");
        if (theFile != null) {
            initialFile = theFile.getFullPath().toString();
        }
        setTitle("General");
        setDescription(MESSAGE_SPECIFY_FILE);
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        Composite general = createGeneral(parent);
        setControl(general);
        setPageComplete(false);
        if (initialFile.length() > 0) {
            fileText.setText(initialFile);
            analysisNameText.setFocus();
        } else {
            fileText.setFocus();
        }
    }

    /** the number of columns. */
    private static final int NUM_COLUMNS = 3;
    /** the vertical spacing. */
    private static final int VERTICAL_SPACING = 9;

    /**
     * Creates the composite that contains the file and name text.
     * 
     * @param parent
     *            the parent composite
     * @return the created composite
     */
    private Composite createGeneral(final Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = NUM_COLUMNS;
        layout.verticalSpacing = VERTICAL_SPACING;
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
        label = new Label(container, SWT.NULL);
        label.setText("&Analysis name:");
        analysisNameText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        analysisNameText.setLayoutData(gd);
        analysisNameText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        new Composite(container, SWT.NONE);
        label = new Label(container, SWT.NULL);
        label.setText("&Analysis description:");
        analysisDescriptionText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        analysisDescriptionText.setLayoutData(gd);
        analysisDescriptionText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        new Composite(container, SWT.NONE);
        return container;
    }

    /**
     * Handles a change of the file selection or analyis name text.
     */
    private void dialogChanged() {
        // check file
        String fileName = fileText.getText().trim();
        if (fileName.length() > 0) {
            IPath path = new Path(fileName);
            if (!ResourcesPlugin.getWorkspace().getRoot().exists(path)) {
                setErrorMessage(MESSAGE_NO_SUCH_FILE + fileName);
                setPageComplete(false);
                return;
            } else {
                try {
                    IFile theFile =
                            ResourcesPlugin.getWorkspace().getRoot()
                                    .getFile(path);
                    if (theFile.getFileExtension() == null
                            || !theFile.getFileExtension().equals("ext")) {
                        setErrorMessage(MESSAGE_WRONG_EXT);
                        setPageComplete(false);
                        return;
                    }
                } catch (Exception exception) {
                    // path specifies folder
                    setErrorMessage(MESSAGE_NO_SUCH_FILE);
                    setPageComplete(false);
                    return;
                }
            }
        } else {
            setErrorMessage(null);
            setMessage(MESSAGE_SPECIFY_FILE);
            setPageComplete(false);
            return;
        }
        // check analysis name
        String analysisName = analysisNameText.getText().trim();
        if (analysisName.length() == 0) {
            setErrorMessage(null);
            setMessage(MESSAGE_SPECIFY_ANALYSIS_NAME);
            setPageComplete(false);
            return;
        }
        // input ok
        setErrorMessage(null);
        setMessage(MESSAGE_OK);
        setPageComplete(true);
    }

    /**
     * Handles the file selection browse.
     */
    private void handleBrowse() {
        ElementTreeSelectionDialog dialog =
                new ElementTreeSelectionDialog(getContainer().getShell(),
                        new WorkbenchLabelProvider(),
                        new ConditionalWorkbenchContentProvider(
                                new ICondition<Object>() {
                                    public boolean evaluate(final Object object) {
                                        if (object instanceof IFile) {
                                            IFile theFile = (IFile) object;
                                            String ext =
                                                    theFile.getFileExtension();
                                            if (ext != null) {
                                                return ext.equals("ext");
                                            }
                                        }
                                        return true;
                                    }
                                }));
        dialog.setTitle("Select Xtend file");
        dialog.setAllowMultiple(false);
        dialog.setValidator(new ISelectionStatusValidator() {
            public IStatus validate(final Object[] selection) {
                if (selection.length == 0) {
                    return new Status(Status.OK,
                            "de.cau.cs.kieler.kiml.grana.xtend", "");
                } else {
                    if (selection[0] instanceof IFile) {
                        return new Status(Status.OK,
                                "de.cau.cs.kieler.kiml.grana.xtend", "");
                    } else {
                        return new Status(Status.ERROR,
                                "de.cau.cs.kieler.kiml.grana.xtend",
                                "No file selected.");
                    }
                }
            }
        });
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        int code = dialog.open();
        if (code == Dialog.OK) {
            Object result = dialog.getResult()[0];
            if (result instanceof IFile) {
                IFile theFile = (IFile) result;
                fileText.setText(theFile.getFullPath().toString());
            }
            analysisNameText.setFocus();
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

    /**
     * Returns the analysis name.
     * 
     * @return the analysis name
     */
    public String getAnalysisName() {
        return analysisNameText.getText().trim();
    }
}
