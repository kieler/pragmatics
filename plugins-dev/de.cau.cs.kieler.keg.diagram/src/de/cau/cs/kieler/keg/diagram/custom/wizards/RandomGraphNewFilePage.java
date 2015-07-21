/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;

/**
 * The wizard page from which to select the KEG file to the graph is generated into.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class RandomGraphNewFilePage extends WizardNewFileCreationPage {

    /** the preference key for the number of graphs. */
    private static final String PREFERENCE_NUMBER_OF_GRAPHS
            = "randomWizard.numberOfGraphs"; //$NON-NLS-1$
    /** the preference key for the automatic creation of diagram files. */
    private static final String PREFERENCE_DIAGRAM_FILES
            = "randomWizard.diagramFiles"; //$NON-NLS-1$
    /** the preference key for the automatic opening of diagram files. */
    private static final String PREFERENCE_OPEN_DIAGRAM_FILES
            = "randomWizard.openDiagramFiles"; //$NON-NLS-1$
    /** the preference key for the filename. */
    private static final String PREFERENCE_FILENAME
            = "randomWizard.filename"; //$NON-NLS-1$
    /** the preference key for the time-based randomization seed. */
    private static final String PREFERENCE_TIME_BASED_SEED
            = "randomWizard.timeBasedSeed"; //$NON-NLS-1$
    /** the preference key for the randomization seed value. */
    private static final String PREFERENCE_RANDOM_SEED
            = "randomWizard.randomSeedValue"; //$NON-NLS-1$

    /** the number of graphs to be created. */
    private int numberOfGraphs;
    /** whether to automatically create diagram files. */
    private boolean diagramFiles;
    /** whether to automatically open the diagrams in an editor. */
    private boolean openDiagramFiles;
    /** whether to use a time-based randomization seed. */
    private boolean timeBasedSeed;
    /** fixed value for randomization seed. */
    private int randomSeed;

    /**
     * Constructs the new file wizard page.
     * 
     * @param selection
     *            the selection the wizard is called on
     */
    public RandomGraphNewFilePage(final IStructuredSelection selection) {
        super("randomGraphNewFilePage", selection); //$NON-NLS-1$
        setTitle(Messages.RandomGraphNewFilePage_title);
        setDescription(Messages.RandomGraphNewFilePage_description);
        setFileExtension("keg"); //$NON-NLS-1$
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
        // group box
        Group group = new Group(parent, SWT.NONE);
        group.setText(Messages.RandomGraphNewFilePage_options_group_caption);
        
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        group.setLayoutData(gridData);
        
        GridLayout layout = new GridLayout(2, false);
        group.setLayout(layout);
        
        // add option for a number of graphs to be created
        Label label = new Label(group, SWT.NONE);
        label.setText(Messages.RandomGraphNewFilePage_number_of_graphs_caption);
        
        final Spinner graphsSpinner = new Spinner(group, SWT.BORDER | SWT.SINGLE);
        graphsSpinner.setToolTipText(Messages.RandomGraphNewFilePage_number_of_graphs_help);
        graphsSpinner.setValues(numberOfGraphs, 1, Integer.MAX_VALUE, 0, 1, 10);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        graphsSpinner.setLayoutData(gridData);
        
        // add option for creating diagram files for the graphs
        final Button diagramButton = new Button(group, SWT.CHECK);
        diagramButton.setText(Messages.RandomGraphNewFilePage_create_diagrams_caption);
        diagramButton.setToolTipText(Messages.RandomGraphNewFilePage_create_diagrams_help);
        diagramButton.setSelection(diagramFiles);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        gridData.verticalIndent = 10;
        diagramButton.setLayoutData(gridData);
        
        // add option for creating diagram files for the graphs
        final Button openButton = new Button(group, SWT.CHECK);
        openButton.setText(Messages.RandomGraphNewFilePage_open_diagrams_caption);
        openButton.setToolTipText(Messages.RandomGraphNewFilePage_open_diagrams_help);
        openButton.setSelection(openDiagramFiles);
        openButton.setEnabled(diagramFiles);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        gridData.horizontalIndent = 30;
        openButton.setLayoutData(gridData);
        
        // add option for time-based randomization seed
        final Button timeSeedButton = new Button(group, SWT.CHECK);
        timeSeedButton.setText(Messages.RandomGraphNewFilePage_time_seed_caption);
        timeSeedButton.setToolTipText(Messages.RandomGraphNewFilePage_time_seed_help);
        timeSeedButton.setSelection(timeBasedSeed);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        gridData.verticalIndent = 10;
        timeSeedButton.setLayoutData(gridData);
        
        // add option for random number generator seed
        label = new Label(group, SWT.NONE);
        label.setText(Messages.RandomGraphNewFilePage_random_seed_caption);
        
        final Spinner seedSpinner = new Spinner(group, SWT.BORDER | SWT.SINGLE);
        seedSpinner.setToolTipText(Messages.RandomGraphNewFilePage_random_seed_help);
        seedSpinner.setValues(randomSeed, 0, Integer.MAX_VALUE, 0, 1, 10);
        seedSpinner.setEnabled(!timeBasedSeed);
        
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        seedSpinner.setLayoutData(gridData);
        
        // create the advanced options and hide them
        Composite advanced = new Composite(group, SWT.NONE);
        advanced.setVisible(false);
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.exclude = true;
        advanced.setLayoutData(gridData);
        layout = new GridLayout();
        advanced.setLayout(layout);
        super.createAdvancedControls(advanced);
        
        // event listeners
        graphsSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfGraphs = graphsSpinner.getSelection();
            }
        });
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
        timeSeedButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                timeBasedSeed = timeSeedButton.getSelection();
                seedSpinner.setEnabled(!timeBasedSeed);
            }
        });
        seedSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                randomSeed = seedSpinner.getSelection();
            }
        });
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = GraphsDiagramEditorPlugin.getInstance().getPreferenceStore();
        preferenceStore.setValue(PREFERENCE_NUMBER_OF_GRAPHS, numberOfGraphs);
        preferenceStore.setValue(PREFERENCE_DIAGRAM_FILES, diagramFiles);
        preferenceStore.setValue(PREFERENCE_OPEN_DIAGRAM_FILES, openDiagramFiles);
        preferenceStore.setValue(PREFERENCE_TIME_BASED_SEED, timeBasedSeed);
        preferenceStore.setValue(PREFERENCE_RANDOM_SEED, randomSeed);
        preferenceStore.setValue(PREFERENCE_FILENAME, getFileName());
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = GraphsDiagramEditorPlugin.getInstance().getPreferenceStore();
        numberOfGraphs = preferenceStore.getInt(PREFERENCE_NUMBER_OF_GRAPHS);
        diagramFiles = preferenceStore.getBoolean(PREFERENCE_DIAGRAM_FILES);
        openDiagramFiles = preferenceStore.getBoolean(PREFERENCE_OPEN_DIAGRAM_FILES);
        timeBasedSeed = preferenceStore.getBoolean(PREFERENCE_TIME_BASED_SEED);
        randomSeed = preferenceStore.getInt(PREFERENCE_RANDOM_SEED);
        setFileName(preferenceStore.getString(PREFERENCE_FILENAME));
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = GraphsDiagramEditorPlugin.getInstance().getPreferenceStore();
        preferenceStore.setDefault(PREFERENCE_NUMBER_OF_GRAPHS, 1);
        preferenceStore.setDefault(PREFERENCE_DIAGRAM_FILES, false);
        preferenceStore.setDefault(PREFERENCE_OPEN_DIAGRAM_FILES, false);
        preferenceStore.setDefault(PREFERENCE_TIME_BASED_SEED, true);
        preferenceStore.setDefault(PREFERENCE_RANDOM_SEED, 0);
        preferenceStore.setDefault(PREFERENCE_FILENAME, "random.keg"); //$NON-NLS-1$
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
     * Returns whether a time-based randomization seed shall be used.
     * 
     * @return true if a time-based randomization seed shall be used
     */
    public boolean getTimeBasedSeed() {
        return timeBasedSeed;
    }
    
    /**
     * Returns the randomization seed value.
     * 
     * @return the randomization seed
     */
    public int getRandomSeedValue() {
        return randomSeed;
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
