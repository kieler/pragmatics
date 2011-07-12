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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.StructuredSelection;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutOptionValidator;
import de.cau.cs.kieler.kiml.ui.LayouterHintDialog;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Preference page for general KIML preferences.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** checkbox for edge routing style. */
    private Button obliqueCheckBox;
    /** list of layout option entries. */
    private List<OptionsTableProvider.DataEntry> optionEntries;
    /** table viewer to refresh after changes to the option table data. */
    private TableViewer optionTableViewer;

    /**
     * Creates the layout preference page.
     */
    public LayoutPreferencePage() {
        super();
        setDescription(Messages.getString("kiml.ui.0")); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    protected Control createContents(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        Group generalGroup = createGeneralGroup(composite);
        generalGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        Group optionsGroup = createOptionsGroup(composite);
        optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        GridLayout compositeLayout = new GridLayout(1, false);
        composite.setLayout(compositeLayout);
        return composite;
    }
    
    /** margin width for layouts. */
    private static final int MARGIN_WIDTH = 10;
    
    /**
     * Creates the group for general options.
     * 
     * @param parent the parent control
     * @return a group with general options
     */
    private Group createGeneralGroup(final Composite parent) {
        Group generalGroup = new Group(parent, SWT.NONE);
        generalGroup.setText(Messages.getString("kiml.ui.35")); //$NON-NLS-1$
        
        // add checkbox for oblique routing
        obliqueCheckBox = new Button(generalGroup, SWT.CHECK | SWT.LEFT);
        obliqueCheckBox.setText(Messages.getString("kiml.ui.36")); //$NON-NLS-1$
        obliqueCheckBox.setSelection(getPreferenceStore().getBoolean(
                EclipseLayoutInfoService.PREF_OBLIQUE_ROUTE));
        
        FillLayout layout = new FillLayout();
        layout.marginWidth = MARGIN_WIDTH;
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
        Group elementGroup = new Group(parent, SWT.NONE);
        elementGroup.setText(Messages.getString("kiml.ui.28")); //$NON-NLS-1$
        IPreferenceStore preferenceStore = getPreferenceStore();
        LayoutDataService dataService = LayoutDataService.getInstance();
        Collection<LayoutOptionData<?>> layoutOptionData = dataService.getOptionData();
        optionEntries = new LinkedList<OptionsTableProvider.DataEntry>();

        // add options for edit parts and domain model elements
        Set<String> elements = EclipseLayoutInfoService.getInstance().getRegisteredElements();
        for (String element : elements) {
            for (LayoutOptionData<?> data : layoutOptionData) {
                String preference = EclipseLayoutInfoService.getPreferenceName(element, data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
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
        List<Pair<String, String>> diagramTypeList = EclipseLayoutInfoService.getInstance()
                .getDiagramTypes();
        for (Pair<String, String> diagramType : diagramTypeList) {
            for (LayoutOptionData<?> data : layoutOptionData) {
                String preference = EclipseLayoutInfoService.getPreferenceName(
                        diagramType.getFirst(), data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
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
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
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
                OptionsTableProvider.DataEntry entry = getEntry(entries, table.getSelectionIndex());
                if (entry != null) {
                    entry.setValue(null);
                    tableViewer.refresh();
                    int count = 0;
                    for (OptionsTableProvider.DataEntry e : entries) {
                        if (e.getValue() != null) {
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
            public void widgetSelected(final SelectionEvent event) {
                if (!entries.isEmpty() && event.item != null) {
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
                } else {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
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
        
        composite.setLayout(new FillLayout(SWT.VERTICAL));
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
        LayoutOptionData<?> optionData = entry.getOptionData();
        if (entry.getValue() != null) {
            if (optionData.getId().equals(LayoutOptions.ALGORITHM_ID)) {
                // show a selection dialog for a layouter hint
                LayouterHintDialog dialog = new LayouterHintDialog(shell, null);
                if (dialog.open() == LayouterHintDialog.OK) {
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
        // set default values for the general options
        obliqueCheckBox.setSelection(getPreferenceStore().getDefaultBoolean(
                EclipseLayoutInfoService.PREF_OBLIQUE_ROUTE));
        
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
        EclipseLayoutInfoService infoService = EclipseLayoutInfoService.getInstance();
        // set new values for the general options
        getPreferenceStore().setValue(EclipseLayoutInfoService.PREF_OBLIQUE_ROUTE,
                obliqueCheckBox.getSelection());
        
        // store data for the diagram element and diagram type options
        for (OptionsTableProvider.DataEntry entry : optionEntries) {
            Object oldValue = infoService.getOptionValue(entry.getElementId(),
                    entry.getOptionData().getId());
            Object newValue = entry.getValue();
            if (oldValue == null && newValue != null || !oldValue.equals(newValue)) {
                String preference = EclipseLayoutInfoService.getPreferenceName(
                        entry.getElementId(), entry.getOptionData().getId());
                if (newValue == null) {
                    infoService.removeOptionValue(entry.getElementId(),
                            entry.getOptionData().getId());
                    getPreferenceStore().setToDefault(preference);
                    infoService.getRegisteredElements().remove(entry.getElementId());
                } else {
                    infoService.addOptionValue(entry.getElementId(),
                            entry.getOptionData().getId(), newValue);
                    getPreferenceStore().setValue(preference, newValue.toString());
                    if (entry.getType() != ElementType.DIAG_TYPE) {
                        infoService.getRegisteredElements().add(entry.getElementId());
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
