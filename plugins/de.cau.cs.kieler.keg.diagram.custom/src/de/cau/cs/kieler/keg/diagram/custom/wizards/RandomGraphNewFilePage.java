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
package de.cau.cs.kieler.keg.diagram.custom.wizards;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import de.cau.cs.kieler.keg.diagram.custom.KEGDiagramPlugin;

/**
 * The wizard page from which to select the KEG file to the graph is generated into.
 * 
 * @author mri
 */
public class RandomGraphNewFilePage extends WizardNewFileCreationPage {

    /** the page title. */
    private static final String TITLE = "KEG File";
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK = "Create a new KEG file to generate into (*.keg).";

    /** the label for the number of graphs. */
    private static final String LABEL_NUMBER_OF_GRAPHS = "&Number Of Graphs:";
    /** the label for the diagram files option. */
    private static final String LABEL_DIAGRAM_FILES =
            "Automatically create diagram files (*.kegdi) for the KEG graphs";
    /** the label for the open diagram files option. */
    private static final String LABEL_OPEN_DIAGRAM_FILES =
            "Automatically open the diagram files in editors";

    /** the extension for the file to create. */
    private static final String NEW_FILE_EXTENSION = "keg";

    /** the preference key for the number of graphs. */
    private static final String PREFERENCE_NUMBER_OF_GRAPHS = "randomWizard.numberOfGraphs";
    /** the preference key for the automatic creation of diagram files. */
    private static final String PREFERENCE_DIAGRAM_FILES = "randomWizard.diagramFiles";
    /** the preference key for the automatic opening of diagram files. */
    private static final String PREFERENCE_OPEN_DIAGRAM_FILES = "randomWizard.openDiagramFiles";
    /** the preference key for the filename. */
    private static final String PREFERENCE_FILENAME = "randomWizard.filename";

    /** the number of graphs to be created. */
    private int numberOfGraphs;
    /** whether to automatically create diagram files. */
    private boolean diagramFiles;
    /** whether to automatically open the diagrams in an editor. */
    private boolean openDiagramFiles;

    /**
     * Constructs the new file wizard page.
     * 
     * @param selection
     *            the selection the wizard is called on
     */
    public RandomGraphNewFilePage(final IStructuredSelection selection) {
        super("randomGraphNewFilePage", selection);
        setTitle(TITLE);
        setDescription(MESSAGE_OK);
        setFileExtension(NEW_FILE_EXTENSION);
        setAllowExistingResources(true);
        setDefaultPreferences();
        loadPreferences();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // CHECKSTYLEOFF MagicNumber
    protected void createAdvancedControls(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(gridData);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 9;
        composite.setLayout(layout);
        // add option for a number of graphs to be created
        Label label = new Label(composite, SWT.NULL);
        label.setText(LABEL_NUMBER_OF_GRAPHS);
        final Spinner graphsSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        graphsSpinner.setValues(numberOfGraphs, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        graphsSpinner.setLayoutData(gridData);
        graphsSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfGraphs = Integer.parseInt(graphsSpinner.getText());
            }
        });
        // add option for creating diagram files for the graphs
        final Button diagramButton = new Button(composite, SWT.CHECK);
        diagramButton.setText(LABEL_DIAGRAM_FILES);
        diagramButton.setSelection(diagramFiles);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        diagramButton.setLayoutData(gridData);
        // add option for creating diagram files for the graphs
        final Button openButton = new Button(composite, SWT.CHECK);
        openButton.setText(LABEL_OPEN_DIAGRAM_FILES);
        openButton.setSelection(openDiagramFiles);
        openButton.setEnabled(diagramFiles);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        openButton.setLayoutData(gridData);
        openButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                openDiagramFiles = openButton.getSelection();
            }
        });
        diagramButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                diagramFiles = diagramButton.getSelection();
                if (diagramFiles) {
                    openButton.setEnabled(true);
                } else {
                    openButton.setSelection(false);
                    openButton.setEnabled(false);
                    openDiagramFiles = false;
                }
            }
        });
        // create the advanced options and hide them
        Composite advanced = new Composite(composite, SWT.NULL);
        advanced.setVisible(false);
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.exclude = true;
        advanced.setLayoutData(gridData);
        layout = new GridLayout();
        advanced.setLayout(layout);
        super.createAdvancedControls(advanced);
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(PREFERENCE_NUMBER_OF_GRAPHS, numberOfGraphs);
        preferenceStore.setValue(PREFERENCE_DIAGRAM_FILES, diagramFiles);
        preferenceStore.setValue(PREFERENCE_OPEN_DIAGRAM_FILES, openDiagramFiles);
        preferenceStore.setValue(PREFERENCE_FILENAME, getFileName());
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        numberOfGraphs = preferenceStore.getInt(PREFERENCE_NUMBER_OF_GRAPHS);
        diagramFiles = preferenceStore.getBoolean(PREFERENCE_DIAGRAM_FILES);
        openDiagramFiles = preferenceStore.getBoolean(PREFERENCE_OPEN_DIAGRAM_FILES);
        setFileName(preferenceStore.getString(PREFERENCE_FILENAME));
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(PREFERENCE_NUMBER_OF_GRAPHS, 1);
        preferenceStore.setDefault(PREFERENCE_DIAGRAM_FILES, false);
        preferenceStore.setDefault(PREFERENCE_OPEN_DIAGRAM_FILES, false);
        preferenceStore.setDefault(PREFERENCE_FILENAME, "random.keg");
    }

    /**
     * Returns the number of graphs to be created.
     * 
     * @return the number of graphs
     */
    public int getNumberOfGraphs() {
        return numberOfGraphs;
    }

    /**
     * Returns whether to create diagram files for the created graphs.
     * 
     * @return true if diagram files have to be created for the graphs; false else
     */
    public boolean getCreateDiagramFiles() {
        return diagramFiles;
    }

    /**
     * Returns whether to open the created diagram files.
     * 
     * @return true if the diagram files have to be opened; false else
     */
    public boolean getOpenDiagramFiles() {
        return openDiagramFiles;
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
