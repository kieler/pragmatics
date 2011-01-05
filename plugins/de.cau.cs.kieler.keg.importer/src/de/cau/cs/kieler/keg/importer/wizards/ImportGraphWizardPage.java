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

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.keg.KEGPlugin;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.ImporterOption;
import de.cau.cs.kieler.keg.importer.KEGImporterPlugin;

/**
 * The wizard page from which to select the graph file and the options to
 * import.
 * 
 * @author mri
 */
public class ImportGraphWizardPage extends WizardPage {

    /** the page title. */
    private static final String TITLE = "Import Graph";
    /** the message for asking the user to specify a file. */
    private static final String MESSAGE_SPECIFY_FILE =
            "Please specify a file to import.";
    /** the message that is displayed while the input is correct. */
    private static final String MESSAGE_OK =
            "Imports a graph from various file formats to a KEG file.";
    /** the error message for a wrong workspace selection. */
    private static final String ERROR_NO_FILE_SELECTED = "No file selected.";
    /** the error message for a not existing file. */
    private static final String ERROR_NO_SUCH_FILE = "File does not exist.";

    /** the preference key for the file path. */
    private static final String PREFERENCE_FILE_PATH = "exportDialog.filePath";
    /** the preference key for the workspace path. */
    private static final String PREFERENCE_WORKSPACE_PATH =
            "exportDialog.workspacePath";
    /** the preference key for the selected importer. */
    private static final String PREFERENCE_IMPORTER = "exportDialog.exporter";

    /** the preference store. */
    private IPreferenceStore preferenceStore = null;
    /** the file selection text. */
    private Text fileText;
    /** the workspace path checkbox. */
    private Button workspacePathCheckbox = null;
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
    private Map<ImporterOption<?>, Object> optionInputs =
            new HashMap<ImporterOption<?>, Object>();
    /** the last selected importer. */
    private AbstractImporter lastImporter = null;
    /** the selected options. */
    private MapPropertyHolder options = null;
    /** the preselected workspace file. */
    private String workspaceFile = null;

    /**
     * Constructs the import wizard page.
     */
    public ImportGraphWizardPage() {
        super("importGraphWizardPage");
        setTitle(TITLE);
        setDescription(MESSAGE_SPECIFY_FILE);
        preferenceStore = KEGImporterPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Constructs the import wizard page with a preselected workspace file.
     * 
     * @param theWorkspaceFile
     *            the workspace file
     */
    public ImportGraphWizardPage(final String theWorkspaceFile) {
        super("importGraphWizardPage");
        setTitle(TITLE);
        setDescription(MESSAGE_SPECIFY_FILE);
        preferenceStore = KEGImporterPlugin.getDefault().getPreferenceStore();
        workspaceFile = theWorkspaceFile;
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createFileGroup(composite);
        createImportTypeGroup(composite);
        createImportOptionsGroup(composite);
        setControl(composite);
        validateFileText();
    }

    private static final int FILE_GROUP_COLUMNS = 3;
    private static final int FILE_TEXT_WIDTH_HINT = 300;
    private static final int BROWSE_WIDTH_HINT = 150;

    private void createFileGroup(final Composite parent) {
        Composite composite = createComposite(parent, FILE_GROUP_COLUMNS);
        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText("&File:");
        // file path text
        fileText = new Text(composite, SWT.BORDER);
        // load path from preference store
        if (workspaceFile == null) {
            String path = preferenceStore.getString(PREFERENCE_FILE_PATH);
            fileText.setText(path);
        } else {
            fileText.setText(workspaceFile);
        }
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                validateFileText();
            }
        });
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.widthHint = FILE_TEXT_WIDTH_HINT;
        fileText.setLayoutData(gridData);
        // browse workspace button
        Button button = new Button(composite, SWT.PUSH);
        button.setText("&Workspace...");
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                handleWorkspaceBrowse();
            }
        });
        // is workspace path checkbox
        workspacePathCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        workspacePathCheckbox.setText("Is a workspace &path");
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        gridData.horizontalSpan = 2;
        workspacePathCheckbox.setLayoutData(gridData);
        if (workspaceFile == null) {
            // load option from preference store
            boolean workspacePath =
                    preferenceStore.getBoolean(PREFERENCE_WORKSPACE_PATH);

            workspacePathCheckbox.setSelection(workspacePath);
        } else {
            workspacePathCheckbox.setSelection(true);
        }
        workspacePathCheckbox.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                validateFileText();
            }
        });
        // browse file system button
        button = new Button(composite, SWT.PUSH);
        button.setText("File &system...");
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gridData.widthHint = BROWSE_WIDTH_HINT;
        button.setLayoutData(gridData);
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                handleFileSystemBrowse();
            }
        });
    }

    private static final int EXPORT_TYPE_COMBO_WIDTH_HINT = 210;

    private void createImportTypeGroup(final Composite parent) {
        Composite composite = createComposite(parent, 2);
        // label
        Label label = new Label(composite, SWT.NONE);
        label.setText("File F&ormat:");
        fileFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        String[] importerNames = ImportManager.getInstance().getImporterNames();
        if (importerNames.length > 0) {
            fileFormatCombo.setItems(importerNames);
            if (workspaceFile == null) {
                // load exporter from preference store
                String importer =
                        preferenceStore.getString(PREFERENCE_IMPORTER);
                if (ImportManager.getInstance().getImporterByName(importer) != null) {
                    fileFormatCombo.setText(importer);
                } else {
                    fileFormatCombo.setText(importerNames[0]);
                }
            } else {
                findImporter(false);
            }
        } else {
            fileFormatCombo.setEnabled(false);
        }
        fileFormatCombo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                updateOptions();
                validateFileText();
            }
        });
        GridData gridData = new GridData(SWT.NONE);
        gridData.widthHint = EXPORT_TYPE_COMBO_WIDTH_HINT;
        fileFormatCombo.setLayoutData(gridData);
    }

    private static final int EXPORT_OPTIONS_COMPOSITE_MARGIN = 5;

    private void createImportOptionsGroup(final Composite parent) {
        scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setExpandHorizontal(true);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        scrolledComposite.setLayoutData(data);
        optionsComposite =
                createComposite(scrolledComposite, 2,
                        EXPORT_OPTIONS_COMPOSITE_MARGIN,
                        EXPORT_OPTIONS_COMPOSITE_MARGIN);
        for (AbstractImporter importer : ImportManager.getInstance()
                .getImporter()) {
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
    private void createImportOption(final Composite parent,
            final ImporterOption<?> option, final LinkedList<Control> controls) {
        Object value = option.getDefault();
        if (value instanceof Enum<?>) {
            createEnumImportOption(parent, option, controls);
        } else if (value instanceof Boolean) {
            createBooleanImportOption(parent, (ImporterOption<Boolean>) option,
                    controls);
        } else if (value instanceof Integer) {
            createNumberImportOption(parent, option, controls);
        } else if (value instanceof Float) {
            createNumberImportOption(parent, option, controls);
        } else if (value instanceof Double) {
            createNumberImportOption(parent, option, controls);
        } else if (value instanceof String) {
            createStringImportOption(parent, (ImporterOption<String>) option,
                    controls);
        }
    }

    private void createEnumImportOption(final Composite parent,
            final ImporterOption<?> option, final LinkedList<Control> controls) {
        Object[] constants = option.getDefault().getClass().getEnumConstants();
        // keep track of all created buttons
        LinkedList<Button> buttons = new LinkedList<Button>();
        // description label
        Label label = new Label(parent, SWT.NONE);
        label.setText(option.getDescription() + ":");
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
        String lastConstantString =
                preferenceStore.getString(option.getIdentifier().toString());
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
            final ImporterOption<Boolean> option,
            final LinkedList<Control> controls) {
        // choice checkbox
        Button checkbox = new Button(parent, SWT.CHECK | SWT.LEFT);
        checkbox.setText(option.getDescription());
        // load from preference store
        if (preferenceStore.contains(option.getIdentifier().toString())) {
            checkbox.setSelection(preferenceStore.getBoolean(option
                    .getIdentifier().toString()));
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

    private void createNumberImportOption(final Composite parent,
            final ImporterOption<?> option, final LinkedList<Control> controls) {
        // description label
        Label label = new Label(parent, SWT.NONE);
        label.setText(option.getDescription() + ":");
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
        if (preferenceStore.contains(option.getIdentifier().toString())) {
            input.setText(preferenceStore.getString(option.getIdentifier()
                    .toString()));
        } else {
            input.setText(option.getDefault().toString());
        }
        controls.add(input);
        setControlVisibility(input, false);
        optionInputs.put(option, input);
    }

    private static final int OPTION_STRING_TEXT_WIDTH = 220;

    private void createStringImportOption(final Composite parent,
            final ImporterOption<String> option,
            final LinkedList<Control> controls) {
        // description label
        Label label = new Label(parent, SWT.NONE);
        label.setText(option.getDescription() + ":");
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
        if (preferenceStore.contains(option.getIdentifier().toString())) {
            input.setText(preferenceStore.getString(option.getIdentifier()
                    .toString()));
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

    private Composite createComposite(final Composite parent,
            final int columns, final int marginWidth, final int marginHeight) {
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

    private void validateFileText() {
        IPath filePath = new Path(fileText.getText());
        if (workspacePathCheckbox.getSelection()) {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IResource resource = root.findMember(filePath);
            if (resource == null || !resource.exists()) {
                // file does not exist
                setErrorMessage(ERROR_NO_SUCH_FILE);
                setPageComplete(false);
                return;
            }
            if (resource instanceof IContainer) {
                // file path exists but describes a folder
                setErrorMessage(ERROR_NO_FILE_SELECTED);
                setPageComplete(false);
                return;
            }
        } else {
            File file = new File(filePath.toString());
            if (!file.exists()) {
                // file does not exist
                setErrorMessage(ERROR_NO_SUCH_FILE);
                setPageComplete(false);
                return;
            }
            if (file.isDirectory()) {
                // file path exists but describes a folder
                setErrorMessage(ERROR_NO_FILE_SELECTED);
                setPageComplete(false);
                return;
            }
        }
        setErrorMessage(null);
        setMessage(MESSAGE_OK);
        setPageComplete(true);
    }

    private void handleFileSystemBrowse() {
        FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
        // open the dialog
        String selectedFile = fileDialog.open();
        // dialog has not been canceled
        if (selectedFile != null) {
            workspacePathCheckbox.setSelection(false);
            fileText.setText(selectedFile);
            findImporter(true);
            validateFileText();
        }
    }

    private void handleWorkspaceBrowse() {
        ElementTreeSelectionDialog fileSelectionDialog =
                new ElementTreeSelectionDialog(getShell(),
                        new WorkbenchLabelProvider(),
                        new WorkbenchContentProvider());
        fileSelectionDialog.setAllowMultiple(false);
        fileSelectionDialog.setValidator(new ISelectionStatusValidator() {
            public IStatus validate(final Object[] selection) {
                if (selection.length > 0 && !(selection[0] instanceof IFile)) {
                    return new Status(IStatus.ERROR, KEGPlugin.PLUGIN_ID,
                            ERROR_NO_FILE_SELECTED);
                }
                return new Status(IStatus.OK, KEGPlugin.PLUGIN_ID, "");
            }
        });
        fileSelectionDialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        if (fileSelectionDialog.open() == Dialog.OK) {
            Object selectedObject = fileSelectionDialog.getFirstResult();
            if (selectedObject != null && selectedObject instanceof IFile) {
                IFile file = (IFile) selectedObject;
                fileText.setText(file.getFullPath().toOSString());
                workspacePathCheckbox.setSelection(true);
                findImporter(true);
                validateFileText();
            }
        }
    }

    private void findImporter(final boolean update) {
        IPath path = new Path(fileText.getText());
        AbstractImporter importer =
                ImportManager.getInstance().getImporterByExtension(
                        path.getFileExtension());
        if (importer != null) {
            if (!fileFormatCombo.getText().equals(importer.getName())) {
                fileFormatCombo.setText(importer.getName());
                if (update) {
                    updateOptions();
                }
            }
        }
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
        String exporterName =
                fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex());
        AbstractImporter exporter =
                ImportManager.getInstance().getImporterByName(exporterName);
        List<Control> controls = optionControls.get(exporter);
        if (controls != null) {
            for (Control control : controls) {
                setControlVisibility(control, true);
            }
        }
        optionsComposite.layout();
        scrolledComposite.setMinHeight(optionsComposite.computeSize(
                SWT.DEFAULT, SWT.DEFAULT).y);
        lastImporter = exporter;
    }

    // this method assumes that the given control has grid layout data attached
    private void setControlVisibility(final Control control,
            final boolean visible) {
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
     * Returns the selected import file path.
     * 
     * @return the selected path or null if the dialog has not successfully
     *         finished
     */
    public String getImportFile() {
        return fileText.getText();
    }

    /**
     * Returns whether the selected import file path is relative to the
     * workspace.
     * 
     * @return true if the selected import file path is relative to the
     *         workspace
     */
    public boolean isImportWorkspacePath() {
        return workspacePathCheckbox.getSelection();
    }

    /**
     * Returns the selected options.
     * 
     * @return the selected options or null if the dialog has not successfully
     *         finished
     */
    public MapPropertyHolder getOptions() {
        // create import configuration
        if (options == null) {
            options = new MapPropertyHolder() {
            };
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
        preferenceStore.setValue(PREFERENCE_FILE_PATH, fileText.getText());
        preferenceStore.setValue(PREFERENCE_WORKSPACE_PATH,
                workspacePathCheckbox.getSelection());
        preferenceStore.setValue(PREFERENCE_IMPORTER,
                fileFormatCombo.getItem(fileFormatCombo.getSelectionIndex()));
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
            for (Object constant : option.getDefault().getClass()
                    .getEnumConstants()) {
                if (constant.toString().equals(choice)) {
                    if (!constant.equals(option.getDefault())) {
                        options.setProperty(option, constant);
                        preferenceStore.setValue(option.getIdentifier()
                                .toString(), constant.toString());
                    } else {
                        preferenceStore.setToDefault(option.getIdentifier()
                                .toString());
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
            preferenceStore.setValue(option.getIdentifier().toString(),
                    value.toString());
        } else {
            preferenceStore.setToDefault(option.getIdentifier().toString());
        }
    }

    private void attachIntegerImportOption(final ImporterOption<Integer> option) {
        Text input = (Text) optionInputs.get(option);
        try {
            Integer value = Integer.parseInt(input.getText());
            if (value != option.getDefault()) {
                options.setProperty(option, value);
                preferenceStore.setValue(option.getIdentifier().toString(),
                        value.toString());
            } else {
                preferenceStore.setToDefault(option.getIdentifier().toString());
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
                preferenceStore.setValue(option.getIdentifier().toString(),
                        value.toString());
            } else {
                preferenceStore.setToDefault(option.getIdentifier().toString());
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
                preferenceStore.setValue(option.getIdentifier().toString(),
                        value.toString());
            } else {
                preferenceStore.setToDefault(option.getIdentifier().toString());
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
            preferenceStore.setValue(option.getIdentifier().toString(), value);
        } else {
            preferenceStore.setToDefault(option.getIdentifier().toString());
        }
    }
}
