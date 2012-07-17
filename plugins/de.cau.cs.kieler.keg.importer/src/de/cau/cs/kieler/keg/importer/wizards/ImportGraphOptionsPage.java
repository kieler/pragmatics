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
package de.cau.cs.kieler.keg.importer.wizards;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.ImporterOption;
import de.cau.cs.kieler.keg.importer.KEGImporterPlugin;

/**
 * The wizard page from which to select the source graph type and the options for the import.
 * 
 * @author mri
 */
public class ImportGraphOptionsPage extends WizardPage {

    /** the preference key for the selected importer. */
    private static final String PREFERENCE_IMPORTER = "importWizard.importer"; //$NON-NLS-1$
    /** the preference key for the create diagrams option. */
    private static final String PREFERENCE_CREATE_DIAGRAMS = "importWizard.createDiagrams"; //$NON-NLS-1$
    /** the preference key for the open diagrams option. */
    private static final String PREFERENCE_OPEN_DIAGRAMS = "importWizard.openDiagrams"; //$NON-NLS-1$

    /** the preference store. */
    private IPreferenceStore preferenceStore = null;
    /** the create diagrams button. */
    private Button createDiagramsButton;
    /** the open diagrams button. */
    private Button openDiagramsButton;
    /** the file format combo. */
    private Combo fileFormatCombo = null;
    /** the import options scrolled composite. */
    private ScrolledComposite scrolledComposite = null;
    /** the import options composite. */
    private Composite optionsComposite = null;
    /** the mapping of options on the composites. */
    private Map<AbstractImporter, LinkedList<Control>> optionControls =
            new HashMap<AbstractImporter, LinkedList<Control>>();
    /** the mapping of options on the input elements. */
    private Map<ImporterOption<?>, Object> optionInputs = new HashMap<ImporterOption<?>, Object>();
    /** the last selected importer. */
    private AbstractImporter lastImporter = null;
    /** the selected options. */
    private MapPropertyHolder options = null;

    /**
     * Constructs the ImportGraphOptionsPage.
     */
    public ImportGraphOptionsPage() {
        super("importGraphWizardPage"); //$NON-NLS-1$
        setTitle(Messages.ImportGraphOptionsPage_title);
        setDescription(Messages.ImportGraphOptionsPage_message);
        preferenceStore = KEGImporterPlugin.getDefault().getPreferenceStore();
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createImportMetaOptions(composite);
        createImportType(composite);
        createImportOptions(composite);
        setControl(composite);
    }

    private void createImportMetaOptions(final Composite parent) {
        Composite composite = createComposite(parent, 1);
        // open diagrams after import option
        // add option for creating diagram files for the graphs
        createDiagramsButton = new Button(composite, SWT.CHECK);
        createDiagramsButton
                .setText(Messages.ImportGraphOptionsPage_create_diagrams_message);
        boolean createDiagrams = preferenceStore.getBoolean(PREFERENCE_CREATE_DIAGRAMS);
        createDiagramsButton.setSelection(createDiagrams);
        // add option for creating diagram files for the graphs
        openDiagramsButton = new Button(composite, SWT.CHECK);
        openDiagramsButton.setText(Messages.ImportGraphOptionsPage_open_diagrams_message);
        boolean openDiagrams = preferenceStore.getBoolean(PREFERENCE_OPEN_DIAGRAMS);
        openDiagramsButton.setSelection(openDiagrams);
        openDiagramsButton.setEnabled(createDiagrams);
        createDiagramsButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                if (createDiagramsButton.getSelection()) {
                    openDiagramsButton.setEnabled(true);
                } else {
                    openDiagramsButton.setSelection(false);
                    openDiagramsButton.setEnabled(false);
                }
            }
        });
    }

    private static final int EXPORT_TYPE_COMBO_WIDTH_HINT = 210;

    private void createImportType(final Composite parent) {
        Composite composite = createComposite(parent, 2);
        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.ImportGraphOptionsPage_file_format_caption);
        fileFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        String[] importerNames = ImportManager.getInstance().getImporterNames();
        if (importerNames.length > 0) {
            fileFormatCombo.setItems(importerNames);
            if (!findImporter()) {
                // load exporter from preference store
                String importer = preferenceStore.getString(PREFERENCE_IMPORTER);
                if (ImportManager.getInstance().getImporterByName(importer) != null) {
                    fileFormatCombo.setText(importer);
                } else {
                    fileFormatCombo.setText(importerNames[0]);
                }
            }
        } else {
            fileFormatCombo.setEnabled(false);
            setPageComplete(false);
            setErrorMessage(Messages.ImportGraphOptionsPage_no_importer_error);
        }
        fileFormatCombo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                updateOptions();
            }
        });
        GridData gridData = new GridData(SWT.NONE);
        gridData.widthHint = EXPORT_TYPE_COMBO_WIDTH_HINT;
        fileFormatCombo.setLayoutData(gridData);
    }

    private boolean findImporter() {
        ImportGraphWizard wizard = (ImportGraphWizard) getWizard();
        String extension = wizard.getCommonSelectedExtension();
        AbstractImporter importer = ImportManager.getInstance().getImporterByExtension(extension);
        if (importer == null) {
            return false;
        }
        fileFormatCombo.setText(importer.getName());
        return true;
    }

    private static final int EXPORT_OPTIONS_COMPOSITE_MARGIN = 5;

    private void createImportOptions(final Composite parent) {
        scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setExpandHorizontal(true);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        scrolledComposite.setLayoutData(data);
        optionsComposite =
                createComposite(scrolledComposite, 2, EXPORT_OPTIONS_COMPOSITE_MARGIN,
                        EXPORT_OPTIONS_COMPOSITE_MARGIN);
        for (AbstractImporter importer : ImportManager.getInstance().getImporter()) {
            LinkedList<Control> controls = new LinkedList<Control>();
            optionControls.put(importer, controls);
            for (ImporterOption<?> option : importer.getOptions()) {
                createImportOption(optionsComposite, option, controls);
            }
        }
        scrolledComposite.setContent(optionsComposite);
        updateOptions();
    }

    @SuppressWarnings("unchecked")
    private void createImportOption(final Composite parent, final ImporterOption<?> option,
            final LinkedList<Control> controls) {
        Object value = option.getDefault();
        if (value instanceof Enum<?>) {
            createEnumImportOption(parent, option, controls);
        } else if (value instanceof Boolean) {
            createBooleanImportOption(parent, (ImporterOption<Boolean>) option, controls);
        } else if (value instanceof Integer) {
            createNumberImportOption(parent, option, controls);
        } else if (value instanceof Float) {
            createNumberImportOption(parent, option, controls);
        } else if (value instanceof Double) {
            createNumberImportOption(parent, option, controls);
        } else if (value instanceof String) {
            createStringImportOption(parent, (ImporterOption<String>) option, controls);
        }
    }

    private void createEnumImportOption(final Composite parent, final ImporterOption<?> option,
            final LinkedList<Control> controls) {
        Object[] constants = option.getDefault().getClass().getEnumConstants();
        // keep track of all created buttons
        LinkedList<Button> buttons = new LinkedList<Button>();
        // description label
        Label label = new Label(parent, SWT.NONE);
        label.setText(option.getDescription() + ":"); //$NON-NLS-1$
        GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        label.setLayoutData(gridData);
        controls.add(label);
        setControlVisibility(label, false);
        // choices composite
        Composite choices = new Composite(parent, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.wrap = true;
        choices.setLayout(rowLayout);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        choices.setLayoutData(gridData);
        controls.add(choices);
        setControlVisibility(choices, false);
        // load from preference store
        String lastConstantString = preferenceStore.getString(option.getId());
        boolean found = false;
        // choices radio buttons
        for (Object constant : constants) {
            Button radio = new Button(choices, SWT.RADIO | SWT.LEFT);
            radio.setText(constant.toString());
            buttons.add(radio);
            // set last selection from preference store
            if (constant.toString().equals(lastConstantString)) {
                radio.setSelection(true);
                found = true;
            }
        }
        // set default selection
        if (!found) {
            for (Button button : buttons) {
                if (button.getText().equals(option.getDefault().toString())) {
                    button.setSelection(true);
                    break;
                }
            }
        }
        optionInputs.put(option, buttons);
    }

    private void createBooleanImportOption(final Composite parent,
            final ImporterOption<Boolean> option, final LinkedList<Control> controls) {
        // choice checkbox
        Button checkbox = new Button(parent, SWT.CHECK | SWT.LEFT);
        checkbox.setText(option.getDescription());
        // load from preference store
        if (preferenceStore.contains(option.getId())) {
            checkbox.setSelection(preferenceStore.getBoolean(option.getId()));
        } else {
            checkbox.setSelection(option.getDefault());
        }
        GridData gridData = new GridData(SWT.LEFT, SWT.NONE, true, false);
        gridData.horizontalSpan = 2;
        checkbox.setLayoutData(gridData);
        controls.add(checkbox);
        setControlVisibility(checkbox, false);
        optionInputs.put(option, checkbox);
    }

    private static final int OPTION_NUMBER_TEXT_WIDTH = 120;

    private void createNumberImportOption(final Composite parent, final ImporterOption<?> option,
            final LinkedList<Control> controls) {
        // description label
        Label label = new Label(parent, SWT.NONE);
        label.setText(option.getDescription() + ":"); //$NON-NLS-1$
        GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        label.setLayoutData(gridData);
        controls.add(label);
        setControlVisibility(label, false);
        // input text
        Text input = new Text(parent, SWT.BORDER);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = OPTION_NUMBER_TEXT_WIDTH;
        input.setLayoutData(gridData);
        // load from preference store
        if (preferenceStore.contains(option.getId())) {
            input.setText(preferenceStore.getString(option.getId()));
        } else {
            input.setText(option.getDefault().toString());
        }
        controls.add(input);
        setControlVisibility(input, false);
        optionInputs.put(option, input);
    }

    private static final int OPTION_STRING_TEXT_WIDTH = 220;

    private void createStringImportOption(final Composite parent,
            final ImporterOption<String> option, final LinkedList<Control> controls) {
        // description label
        Label label = new Label(parent, SWT.NONE);
        label.setText(option.getDescription() + ":"); //$NON-NLS-1$
        GridData gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        label.setLayoutData(gridData);
        controls.add(label);
        setControlVisibility(label, false);
        // input text
        Text input = new Text(parent, SWT.BORDER);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = OPTION_STRING_TEXT_WIDTH;
        input.setLayoutData(gridData);
        // load from preference store
        if (preferenceStore.contains(option.getId())) {
            input.setText(preferenceStore.getString(option.getId()));
        } else {
            input.setText(option.getDefault().toString());
        }
        controls.add(input);
        setControlVisibility(input, false);
        optionInputs.put(option, input);
    }

    private Composite createComposite(final Composite parent, final int columns) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = columns;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        gridLayout.makeColumnsEqualWidth = false;
        GridData data = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(data);
        composite.setLayout(gridLayout);
        return composite;
    }

    private Composite createComposite(final Composite parent, final int columns,
            final int marginWidth, final int marginHeight) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = columns;
        gridLayout.marginHeight = marginHeight;
        gridLayout.marginWidth = marginWidth;
        gridLayout.makeColumnsEqualWidth = false;
        GridData data = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(data);
        composite.setLayout(gridLayout);
        return composite;
    }

    private void updateOptions() {
        if (lastImporter != null) {
            List<Control> controls = optionControls.get(lastImporter);
            if (controls != null) {
                for (Control control : controls) {
                    setControlVisibility(control, false);
                }
            }
        }
        String exporterName = fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex());
        AbstractImporter exporter = ImportManager.getInstance().getImporterByName(exporterName);
        List<Control> controls = optionControls.get(exporter);
        if (controls != null) {
            for (Control control : controls) {
                setControlVisibility(control, true);
            }
        }
        optionsComposite.layout();
        scrolledComposite.setMinHeight(optionsComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        lastImporter = exporter;
    }

    // this method assumes that the given control has grid layout data attached
    private void setControlVisibility(final Control control, final boolean visible) {
        control.setVisible(visible);
        GridData data = (GridData) control.getLayoutData();
        data.exclude = !visible;
        control.setLayoutData(data);
    }

    /**
     * Returns the selected importer.
     * 
     * @return the selected importer
     */
    public AbstractImporter getImporter() {
        return lastImporter;
    }

    /**
     * Returns whether to create diagram files for the created graphs.
     * 
     * @return true if diagram files have to be created for the graphs; false else
     */
    public boolean getCreateDiagramFiles() {
        return createDiagramsButton.getSelection();
    }

    /**
     * Returns whether to open the created diagram files.
     * 
     * @return true if the diagram files have to be opened; false else
     */
    public boolean getOpenDiagramFiles() {
        return openDiagramsButton.getSelection();
    }

    /**
     * Returns the selected options.
     * 
     * @return the selected options or null if the dialog has not successfully finished
     */
    public IPropertyHolder getOptions() {
        // create import configuration
        if (options == null) {
            options = new MapPropertyHolder();
            // attach options
            for (ImporterOption<?> option : lastImporter.getOptions()) {
                attachImportOption(option);
            }
        }
        return options;
    }

    /**
     * Saves the pages preferences.
     */
    public void savePreferences() {
        preferenceStore.setValue(PREFERENCE_IMPORTER,
                fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex()));
        preferenceStore.setValue(PREFERENCE_CREATE_DIAGRAMS, createDiagramsButton.getSelection());
        preferenceStore.setValue(PREFERENCE_OPEN_DIAGRAMS, openDiagramsButton.getSelection());
    }

    @SuppressWarnings("unchecked")
    private void attachImportOption(final ImporterOption<?> option) {
        Object value = option.getDefault();
        if (value instanceof Enum<?>) {
            attachEnumImportOption(option);
        } else if (value instanceof Boolean) {
            attachBooleanImportOption((ImporterOption<Boolean>) option);
        } else if (value instanceof Integer) {
            attachIntegerImportOption((ImporterOption<Integer>) option);
        } else if (value instanceof Float) {
            attachFloatImportOption((ImporterOption<Float>) option);
        } else if (value instanceof Double) {
            attachDoubleImportOption((ImporterOption<Double>) option);
        } else if (value instanceof String) {
            attachStringImportOption((ImporterOption<String>) option);
        }
    }

    @SuppressWarnings("unchecked")
    private void attachEnumImportOption(final ImporterOption<?> option) {
        List<Button> buttons = (List<Button>) optionInputs.get(option);
        String choice = null;
        for (Button radio : buttons) {
            if (radio.getSelection()) {
                choice = radio.getText();
                // only one selection is possible
                break;
            }
        }
        // should always be true
        if (choice != null) {
            for (Object constant : option.getDefault().getClass().getEnumConstants()) {
                if (constant.toString().equals(choice)) {
                    if (!constant.equals(option.getDefault())) {
                        options.setProperty(option, constant);
                        preferenceStore.setValue(option.getId(),
                                constant.toString());
                    } else {
                        preferenceStore.setToDefault(option.getId());
                    }
                    break;
                }
            }
        }
    }

    private void attachBooleanImportOption(final ImporterOption<Boolean> option) {
        Button checkbox = (Button) optionInputs.get(option);
        Boolean value = checkbox.getSelection();
        if (value != option.getDefault()) {
            options.setProperty(option, value);
            preferenceStore.setValue(option.getId(), value.toString());
        } else {
            preferenceStore.setToDefault(option.getId());
        }
    }

    private void attachIntegerImportOption(final ImporterOption<Integer> option) {
        Text input = (Text) optionInputs.get(option);
        try {
            Integer value = Integer.parseInt(input.getText());
            if (value != option.getDefault()) {
                options.setProperty(option, value);
                preferenceStore.setValue(option.getId(), value.toString());
            } else {
                preferenceStore.setToDefault(option.getId());
            }
        } catch (NumberFormatException e) {
            // if the text does not contain an integer don't set the option so
            // the default value is taken
        }
    }

    private void attachFloatImportOption(final ImporterOption<Float> option) {
        Text input = (Text) optionInputs.get(option);
        try {
            Float value = Float.parseFloat(input.getText());
            if (value != option.getDefault()) {
                options.setProperty(option, value);
                preferenceStore.setValue(option.getId(), value.toString());
            } else {
                preferenceStore.setToDefault(option.getId());
            }
        } catch (NumberFormatException e) {
            // if the text does not contain a float don't set the option so
            // the default value is taken
        }
    }

    private void attachDoubleImportOption(final ImporterOption<Double> option) {
        Text input = (Text) optionInputs.get(option);
        try {
            Double value = Double.parseDouble(input.getText());
            if (value != option.getDefault()) {
                options.setProperty(option, value);
                preferenceStore.setValue(option.getId(), value.toString());
            } else {
                preferenceStore.setToDefault(option.getId());
            }
        } catch (NumberFormatException e) {
            // if the text does not contain a double don't set the option so
            // the default value is taken
        }
    }

    private void attachStringImportOption(final ImporterOption<String> option) {
        Text input = (Text) optionInputs.get(option);
        String value = input.getText();
        if (value != option.getDefault()) {
            options.setProperty(option, value);
            preferenceStore.setValue(option.getId(), value);
        } else {
            preferenceStore.setToDefault(option.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        findImporter();
        updateOptions();
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
