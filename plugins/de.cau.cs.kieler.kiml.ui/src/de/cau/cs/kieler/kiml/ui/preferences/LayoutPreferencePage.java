/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.preferences;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutConfigService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.ExtensionLayoutConfigService;
import de.cau.cs.kieler.kiml.service.KimlServicePlugin;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutHandler;
import de.cau.cs.kieler.kiml.ui.LayoutOptionValidator;
import de.cau.cs.kieler.kiml.ui.AlgorithmSelectionDialog;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Preference page for general KIML preferences.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class LayoutPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** checkbox for animation. */
    private Button animationCheckBox;
    /** checkbox for zoom-to-fit. */
    private Button zoomCheckBox;
    /** checkbox for progress dialog. */
    private Button progressCheckBox;
    /** checkbox for edge routing style. */
    private Button obliqueCheckBox;
    /** checkbox for debug graph output. */
    private Button debugCheckBox;
    /** list of layout option entries. */
    private List<OptionsTableProvider.DataEntry> optionEntries;
    /** table viewer to refresh after changes to the option table data. */
    private TableViewer optionTableViewer;

    /**
     * Creates the layout preference page.
     */
    public LayoutPreferencePage() {
        super();
    }
    
    
    /** vertical spacing between out group boxes. */
    private static final int VERTICAL_LAYOUT_SPACING = 10;

    /**
     * {@inheritDoc}
     */
    protected Control createContents(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout compositeLayout = new GridLayout(1, false);
        compositeLayout.verticalSpacing = VERTICAL_LAYOUT_SPACING;
        composite.setLayout(compositeLayout);
        
        Group generalGroup = createGeneralGroup(composite);
        generalGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        
        Group optionsGroup = createOptionsGroup(composite);
        optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        return composite;
    }
    
    /** margin width for layouts. */
    private static final int MARGIN_WIDTH = 10;
    /** margin height for layouts. */
    private static final int MARGIN_HEIGHT = 5;
    
    /**
     * Creates the group for general options.
     * 
     * @param parent the parent control
     * @return a group with general options
     */
    private Group createGeneralGroup(final Composite parent) {
        IPreferenceStore mainPrefStore = getPreferenceStore();
        IPreferenceStore servicePrefStore = KimlServicePlugin.getDefault().getPreferenceStore();
        Group generalGroup = new Group(parent, SWT.NONE);
        generalGroup.setText(Messages.getString("kiml.ui.35")); //$NON-NLS-1$
        
        // add checkbox for animation
        animationCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        animationCheckBox.setText(Messages.getString("kiml.ui.64")); //$NON-NLS-1$
        animationCheckBox.setToolTipText(Messages.getString("kiml.ui.67")); //$NON-NLS-1$
        animationCheckBox.setSelection(mainPrefStore.getBoolean(LayoutHandler.PREF_ANIMATION));
        
        // add checkbox for zoom-to-fit
        zoomCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        zoomCheckBox.setText(Messages.getString("kiml.ui.65")); //$NON-NLS-1$
        zoomCheckBox.setToolTipText(Messages.getString("kiml.ui.68")); //$NON-NLS-1$
        zoomCheckBox.setSelection(mainPrefStore.getBoolean(LayoutHandler.PREF_ZOOM));
        
        // add checkbox for progress dialog
        progressCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        progressCheckBox.setText(Messages.getString("kiml.ui.66")); //$NON-NLS-1$
        progressCheckBox.setToolTipText(Messages.getString("kiml.ui.69")); //$NON-NLS-1$
        progressCheckBox.setSelection(mainPrefStore.getBoolean(LayoutHandler.PREF_PROGRESS));
        
        // add checkbox for oblique routing
        obliqueCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        obliqueCheckBox.setText(Messages.getString("kiml.ui.36")); //$NON-NLS-1$
        obliqueCheckBox.setToolTipText(Messages.getString("kiml.ui.70")); //$NON-NLS-1$
        obliqueCheckBox.setSelection(servicePrefStore.getBoolean(
                LayoutManagersService.PREF_OBLIQUE_ROUTE));
        
        // add checkbox for debug graph output
        debugCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        debugCheckBox.setText(Messages.getString("kiml.ui.71")); //$NON-NLS-1$
        debugCheckBox.setToolTipText(Messages.getString("kiml.ui.72")); //$NON-NLS-1$
        debugCheckBox.setSelection(servicePrefStore.getBoolean(
                DiagramLayoutEngine.PREF_DEBUG_OUTPUT));
        
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.marginWidth = MARGIN_WIDTH;
        layout.marginHeight = MARGIN_HEIGHT;
        generalGroup.setLayout(layout);
        return generalGroup;
    }
    
    /** fixed height of the options table. */
    private static final int OPTIONS_TABLE_HEIGHT = 300;
    
    /**
     * Creates the group that holds the diagram element options table.
     * 
     * @param parent the parent control
     * @return a group with the diagram element options table
     */
    private Group createOptionsGroup(final Composite parent) {
        IPreferenceStore servicePrefStore = KimlServicePlugin.getDefault().getPreferenceStore();
        Group elementGroup = new Group(parent, SWT.NONE);
        elementGroup.setText(Messages.getString("kiml.ui.28")); //$NON-NLS-1$
        LayoutMetaDataService dataService = LayoutMetaDataService.getInstance();
        Collection<LayoutOptionData> layoutOptionData = dataService.getOptionData();
        optionEntries = new LinkedList<OptionsTableProvider.DataEntry>();

        // add options for edit parts and domain model elements
        Set<String> elements = ((ExtensionLayoutConfigService) LayoutConfigService.getInstance())
                .getRegisteredElements();
        for (String element : elements) {
            for (LayoutOptionData data : layoutOptionData) {
                String preference = ExtensionLayoutConfigService.getPreferenceName(
                        element, data.getId());
                if (servicePrefStore.contains(preference)) {
                    Object value = data.parseValue(servicePrefStore.getString(preference));
                    if (value != null) {
                        int dotIndex = element.lastIndexOf('.');
                        String partName = element.substring(dotIndex + 1);
                        ElementType type = partName.endsWith("EditPart")
                                ? ElementType.EDIT_PART : ElementType.MODEL_ELEM;
                        if (partName.endsWith("Impl")) {
                            partName = partName.substring(0, partName.length() - "Impl".length());
                        }
                        optionEntries.add(new OptionsTableProvider.DataEntry(
                                partName, element, type, data, value));
                    }
                }
            }
        }
        
        // add options for diagram types
        List<Pair<String, String>> diagramTypeList = LayoutConfigService.getInstance()
                .getDiagramTypes();
        for (Pair<String, String> diagramType : diagramTypeList) {
            for (LayoutOptionData data : layoutOptionData) {
                String preference = ExtensionLayoutConfigService.getPreferenceName(
                        diagramType.getFirst(), data.getId());
                if (servicePrefStore.contains(preference)) {
                    Object value = data.parseValue(servicePrefStore.getString(preference));
                    if (value != null) {
                        optionEntries.add(new OptionsTableProvider.DataEntry(
                                diagramType.getSecond(), diagramType.getFirst(),
                                ElementType.DIAG_TYPE, data, value));
                    }
                }
            }
        }
        
        // create the table and actions to edit layout option values
        addOptionTable(elementGroup, optionEntries);
        
        elementGroup.setLayout(new GridLayout(2, false));
        return elementGroup;
    }
    
    /**
     * Adds a table to display options and buttons to edit the options.
     * 
     * @param parent the parent to which controls are added
     * @param entries the list of table entries
     */
    private void addOptionTable(final Composite parent,
            final List<OptionsTableProvider.DataEntry> entries) {
        
        // construct the options table
        final Table table = new Table(parent, SWT.BORDER);
        final TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setText(Messages.getString("kiml.ui.29")); //$NON-NLS-1$
        final TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setText(Messages.getString("kiml.ui.9")); //$NON-NLS-1$
        final TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setText(Messages.getString("kiml.ui.19")); //$NON-NLS-1$
        final TableColumn column4 = new TableColumn(table, SWT.NONE);
        column4.setText(Messages.getString("kiml.ui.20")); //$NON-NLS-1$
        table.setHeaderVisible(true);
        final TableViewer tableViewer = new TableViewer(table);
        OptionsTableProvider optionsTableProvider = new OptionsTableProvider();
        tableViewer.setContentProvider(optionsTableProvider);
        tableViewer.setLabelProvider(optionsTableProvider);
        tableViewer.setInput(entries);
        optionTableViewer = tableViewer;
        column1.pack();
        column2.pack();
        column3.pack();
        column4.pack();
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        table.setLayoutData(tableLayoutData);
        table.pack();
        tableLayoutData.heightHint = OPTIONS_TABLE_HEIGHT;
        
        // add button to add new options
        Composite composite = new Composite(parent, SWT.NONE);
        final Button newButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        newButton.setText(Messages.getString("kiml.ui.41")); //$NON-NLS-1$
        
        // add button to edit the options
        final Button editButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        editButton.setText(Messages.getString("kiml.ui.21")); //$NON-NLS-1$
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                OptionsTableProvider.DataEntry entry = getEntry(entries, table.getSelectionIndex());
                if (entry != null) {
                    showEditDialog(parent.getShell(), entry);
                    tableViewer.refresh();
                }
            }
        });
        
        // add button to remove an option
        final Button removeButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        removeButton.setText(Messages.getString("kiml.ui.22")); //$NON-NLS-1$
        removeButton.setEnabled(false);
        removeButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                int selectionIndex = table.getSelectionIndex();
                OptionsTableProvider.DataEntry entry = getEntry(entries, selectionIndex);
                if (entry != null) {
                    entry.setValue(null);
                    tableViewer.refresh();
                    int count = 0;
                    for (OptionsTableProvider.DataEntry en : entries) {
                        if (en.getValue() != null) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        editButton.setEnabled(false);
                        removeButton.setEnabled(false);
                    }
                }
            }
        });
        
        // react on selection changes of the options table
        table.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                if (!entries.isEmpty() && event.item != null) {
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
                } else {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                // Duplicate code from edit button handler
                OptionsTableProvider.DataEntry entry = getEntry(entries, table.getSelectionIndex());
                if (entry != null) {
                    showEditDialog(parent.getShell(), entry);
                    tableViewer.refresh();
                }
            }
        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.character == SWT.DEL) {
                    // Duplicate code from remove button handler
                    int selectionIndex = table.getSelectionIndex();
                    OptionsTableProvider.DataEntry entry = getEntry(entries, selectionIndex);
                    if (entry != null) {
                        entry.setValue(null);
                        tableViewer.refresh();
                        int count = 0;
                        for (OptionsTableProvider.DataEntry en : entries) {
                            if (en.getValue() != null) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            editButton.setEnabled(false);
                            removeButton.setEnabled(false);
                        }
                    }
                }
            }
        });
        newButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                int newIndex = showNewDialog(parent.getShell(), entries);
                if (newIndex >= 0) {
                    tableViewer.refresh();
                    tableViewer.setSelection(new StructuredSelection(entries.get(newIndex)));
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
                    column1.pack();
                    column2.pack();
                    column3.pack();
                    column4.pack();
                }
            }
        });

        // Make sure the buttons have an appropriate minimum size
        setButtonLayoutData(newButton);
        setButtonLayoutData(editButton);
        setButtonLayoutData(removeButton);
        
        GridLayout compositeLayout = new GridLayout(1, false);
        compositeLayout.verticalSpacing = LayoutConstants.getSpacing().y;
        compositeLayout.marginHeight = 0;
        compositeLayout.marginWidth = 0;
        composite.setLayout(compositeLayout);
        GridData compositeLayoutData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        composite.setLayoutData(compositeLayoutData);
    }

    /**
     * Fetches the entry with given index of a list of data entry, bypassing elements whose
     * value was set to {@code null}.
     * 
     * @param entries list of data entries 
     * @param index index of the entry to look up
     * @return the entry at the given index
     */
    private OptionsTableProvider.DataEntry getEntry(final List<OptionsTableProvider.DataEntry> entries,
            final int index) {
        ListIterator<OptionsTableProvider.DataEntry> entryIter = entries.listIterator();
        int i = 0;
        while (entryIter.hasNext()) {
            OptionsTableProvider.DataEntry entry = entryIter.next();
            if (entry.getValue() != null) {
                if (i == index) {
                    return entry;
                }
                i++;
            }
        }
        return null;
    }
    
    /**
     * Shows an input dialog to edit the given option table entry.
     * 
     * @param shell the current shell
     * @param entry an option table entry
     */
    private void showEditDialog(final Shell shell, final OptionsTableProvider.DataEntry entry) {
        LayoutOptionData optionData = entry.getOptionData();
        if (entry.getValue() != null) {
            if (optionData.equals(LayoutOptions.ALGORITHM)) {
                // show a selection dialog for a layouter hint
                AlgorithmSelectionDialog dialog = new AlgorithmSelectionDialog(shell, null);
                if (dialog.open() == AlgorithmSelectionDialog.OK) {
                    String result = dialog.getSelectedHint();
                    if (result != null) {
                        entry.setValue(result);
                    }
                }
            } else {
                // show an input dialog for some other option
                String value = entry.getValue().toString();
                InputDialog dialog = new InputDialog(shell, Messages.getString("kiml.ui.23"),
                        Messages.getString("kiml.ui.24"), value, new LayoutOptionValidator(optionData));
                if (dialog.open() == InputDialog.OK) {
                    String result = dialog.getValue().trim();
                    switch (optionData.getType()) {
                    case ENUM:
                    case ENUMSET:
                        entry.setValue(optionData.parseValue(result.toUpperCase()));
                        break;
                    default:
                        entry.setValue(optionData.parseValue(result));
                    }
                }
            }
        }
    }
    
    /**
     * Shows an input dialog to add a new layout option to the list.
     * 
     * @param shell the current shell
     * @param entries the list of table entries
     * @return the table index to put focus on, or -1 if the focus should not be changed
     */
    private int showNewDialog(final Shell shell, final List<OptionsTableProvider.DataEntry> entries) {
        NewOptionDialog dialog = new NewOptionDialog(shell);
        if (dialog.open() == NewOptionDialog.OK) {
            OptionsTableProvider.DataEntry newEntry = dialog.createDataEntry();
            if (newEntry == null) {
                MessageDialog.openError(shell, Messages.getString("kiml.ui.51"),
                        Messages.getString("kiml.ui.52"));
            } else {
                // look for an existing entry with same identifiers
                int oldIndex = 0;
                OptionsTableProvider.DataEntry oldEntry = null;
                for (OptionsTableProvider.DataEntry e : entries) {
                    if (e.getValue() != null) {
                        if (e.equals(newEntry)) {
                            oldEntry = e;
                            break;
                        }
                        oldIndex++;
                    }
                }
                if (oldEntry != null) {
                    return oldIndex;
                } else {
                    entries.add(newEntry);
                    return entries.size() - 1;
                }
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(KimlUiPlugin.getDefault().getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performDefaults() {
        super.performDefaults();
        IPreferenceStore mainPrefStore = getPreferenceStore();
        IPreferenceStore servicePrefStore = KimlServicePlugin.getDefault().getPreferenceStore();
        
        // set default values for the general options
        animationCheckBox.setSelection(mainPrefStore.getDefaultBoolean(LayoutHandler.PREF_ANIMATION));
        zoomCheckBox.setSelection(mainPrefStore.getDefaultBoolean(LayoutHandler.PREF_ZOOM));
        progressCheckBox.setSelection(mainPrefStore.getDefaultBoolean(LayoutHandler.PREF_PROGRESS));
        obliqueCheckBox.setSelection(servicePrefStore.getDefaultBoolean(
                LayoutManagersService.PREF_OBLIQUE_ROUTE));
        debugCheckBox.setSelection(servicePrefStore.getDefaultBoolean(
                DiagramLayoutEngine.PREF_DEBUG_OUTPUT));
        
        // clear the layout options table
        for (OptionsTableProvider.DataEntry entry : optionEntries) {
            entry.setValue(null);
        }
        optionTableViewer.refresh();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        LayoutConfigService configService = LayoutConfigService.getInstance();
        IPreferenceStore mainPrefStore = getPreferenceStore();
        IPreferenceStore servicePrefStore = KimlServicePlugin.getDefault().getPreferenceStore();
        
        // set new values for the general options
        mainPrefStore.setValue(LayoutHandler.PREF_ANIMATION, animationCheckBox.getSelection());
        mainPrefStore.setValue(LayoutHandler.PREF_ZOOM, zoomCheckBox.getSelection());
        mainPrefStore.setValue(LayoutHandler.PREF_PROGRESS, progressCheckBox.getSelection());
        servicePrefStore.setValue(LayoutManagersService.PREF_OBLIQUE_ROUTE,
                obliqueCheckBox.getSelection());
        servicePrefStore.setValue(DiagramLayoutEngine.PREF_DEBUG_OUTPUT, debugCheckBox.getSelection());
        
        // store data for the diagram element and diagram type options
        for (OptionsTableProvider.DataEntry entry : optionEntries) {
            Object oldValue = configService.getOptionValue(entry.getElementId(),
                    entry.getOptionData().getId());
            Object newValue = entry.getValue();
            if (oldValue == null && newValue != null
                    || oldValue != null && !oldValue.equals(newValue)) {
                String preference = ExtensionLayoutConfigService.getPreferenceName(
                        entry.getElementId(), entry.getOptionData().getId());
                if (newValue == null) {
                    configService.removeOptionValue(entry.getElementId(),
                            entry.getOptionData().getId());
                    servicePrefStore.setToDefault(preference);
                    ((ExtensionLayoutConfigService) configService).getRegisteredElements().remove(
                            entry.getElementId());
                } else {
                    configService.addOptionValue(entry.getElementId(),
                            entry.getOptionData().getId(), newValue);
                    servicePrefStore.setValue(preference, newValue.toString());
                    if (entry.getType() != ElementType.DIAG_TYPE) {
                        ((ExtensionLayoutConfigService) configService).getRegisteredElements().add(
                                entry.getElementId());
                    }
                }
            }
        }
        
        LayoutViewPart layoutView = LayoutViewPart.findView();
        if (layoutView != null) {
            layoutView.refresh();
        }
        return true;
    }

}
