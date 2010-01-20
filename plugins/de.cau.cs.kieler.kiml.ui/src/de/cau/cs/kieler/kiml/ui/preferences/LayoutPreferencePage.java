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
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Preference page for general KIML preferences.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** array of layout provider identifiers. */
    private String[] providerIds;
    /** array of diagram type identifiers. */
    private String[] diagramTypes;
    /** priority data matrix: rows represent layout providers, columns represent diagram types. */
    private int[][] priorityData;
    /** the provider class that manages content of the priority table. */
    private PriorityTableProvider priorityTableProvider;
    /** list of diagram type option entries. */
    private List<OptionsTableProvider.DataEntry> diagramTypeEntries;

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
        createPrioritiesGroup(composite);
        createDiagramTypeGroup(composite);
        composite.setLayout(new FillLayout(SWT.VERTICAL));
        return composite;
    }
    
    /** fixed height of the priorities table. */
    private static final int PRIORITIES_TABLE_HEIGHT = 180;
    
    /**
     * Creates the group that holds the priorities table.
     * 
     * @param parent the parent control
     * @return a group with the priorities table
     */
    private Group createPrioritiesGroup(final Composite parent) {
        Group prioritiesGroup = new Group(parent, SWT.NONE);
        prioritiesGroup.setText(Messages.getString("kiml.ui.2")); //$NON-NLS-1$
        LayoutServices layoutServices = LayoutServices.getInstance();

        Collection<LayoutProviderData> layoutProviderData = layoutServices.getLayoutProviderData();
        int layoutProviderCount = layoutProviderData.size();
        List<Pair<String, String>> diagramTypeMapping = layoutServices.getDiagramTypes();
        diagramTypes = new String[diagramTypeMapping.size()];
        int i = 0;
        for (Pair<String, String> entry : diagramTypeMapping) {
            diagramTypes[i++] = entry.getFirst();
        }
        if (layoutProviderCount == 0 || diagramTypes.length == 0) {
            // return an empty group if there is nothing to display
            return prioritiesGroup;
        }
        providerIds = new String[layoutProviderCount];
        priorityData = new int[layoutProviderCount][diagramTypes.length];
        PriorityTableProvider.DataEntry[] tableEntries
                = new PriorityTableProvider.DataEntry[layoutProviderCount];
        String[] layouterNames = new String[layoutProviderCount];
        i = 0;
        for (LayoutProviderData providerData : layoutProviderData) {
            providerIds[i] = providerData.getId();
            tableEntries[i] = new PriorityTableProvider.DataEntry();
            tableEntries[i].setLayouterIndex(i);
            tableEntries[i].setPriorities(priorityData[i]);
            layouterNames[i] = providerData.getName();
            for (int j = 0; j < diagramTypes.length; j++) {
                priorityData[i][j] = providerData.getSupportedPriority(diagramTypes[j]);
            }
            i++;
        }

        // construct the priorities table
        Label tableHeaderLabel = new Label(prioritiesGroup, SWT.WRAP);
        tableHeaderLabel.setText(Messages.getString("kiml.ui.3")); //$NON-NLS-1$
        GridData labelLayoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
        tableHeaderLabel.setLayoutData(labelLayoutData);
        Table prioritiesTable = new Table(prioritiesGroup, SWT.BORDER);
        TableColumn[] columns = new TableColumn[diagramTypes.length + 1];
        columns[0] = new TableColumn(prioritiesTable, SWT.NONE);
        columns[0].setText(Messages.getString("kiml.ui.4")); //$NON-NLS-1$
        for (int j = 0; j < diagramTypes.length; j++) {
            String diagramTypeName = layoutServices.getDiagramTypeName(diagramTypes[j]);
            if (diagramTypeName == null) {
                diagramTypeName = diagramTypes[j];
            }
            columns[j + 1] = new TableColumn(prioritiesTable, SWT.NONE);
            columns[j + 1].setText(getAbbrev(diagramTypeName));
            columns[j + 1].setToolTipText(diagramTypeName);
        }
        prioritiesTable.setHeaderVisible(true);
        TableViewer priorityTableViewer = new TableViewer(prioritiesTable);
        String[] columnProperties = new String[diagramTypes.length + 1];
        CellEditor[] cellEditors = new CellEditor[diagramTypes.length + 1];
        columnProperties[0] = PriorityTableProvider.LAYOUTERS_PROPERTY;
        for (int j = 0; j < diagramTypes.length; j++) {
            columnProperties[j + 1] = Integer.toString(j);
            cellEditors[j + 1] = new TextCellEditor(prioritiesTable);
        }
        priorityTableViewer.setColumnProperties(columnProperties);
        priorityTableProvider = new PriorityTableProvider(priorityTableViewer,
                priorityData, layouterNames);
        priorityTableViewer.setContentProvider(priorityTableProvider);
        priorityTableViewer.setLabelProvider(priorityTableProvider);
        priorityTableViewer.setCellEditors(cellEditors);
        priorityTableViewer.setCellModifier(priorityTableProvider);
        priorityTableViewer.setInput(tableEntries);
        for (TableColumn column : columns) {
            column.pack();
        }
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
        prioritiesTable.setLayoutData(tableLayoutData);
        prioritiesTable.pack();
        labelLayoutData.widthHint = prioritiesTable.getSize().x;
        tableLayoutData.heightHint = PRIORITIES_TABLE_HEIGHT;

        prioritiesGroup.setLayout(new GridLayout(1, false));
        return prioritiesGroup;
    }
    
    /** fixed height of the options tables. */
    private static final int OPTIONS_TABLE_HEIGHT = 120;
    
    /**
     * Creates the group that holds the diagram type options table.
     * 
     * @param parent the parent control
     * @return a group with the diagram type options table
     */
    private Group createDiagramTypeGroup(final Composite parent) {
        Group diagramTypeGroup = new Group(parent, SWT.NONE);
        diagramTypeGroup.setText(Messages.getString("kiml.ui.17")); //$NON-NLS-1$
        IPreferenceStore preferenceStore = getPreferenceStore();
        LayoutServices layoutServices = LayoutServices.getInstance();

        List<Pair<String, String>> diagramTypeList = layoutServices.getDiagramTypes();
        Collection<LayoutOptionData> layoutOptionData = layoutServices.getLayoutOptionData();
        diagramTypeEntries = new LinkedList<OptionsTableProvider.DataEntry>();
        for (Pair<String, String> diagramType : diagramTypeList) {
            for (LayoutOptionData data : layoutOptionData) {
                String preference = EclipseLayoutServices.getPreferenceName(
                        diagramType.getFirst(), data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        diagramTypeEntries.add(new OptionsTableProvider.DataEntry(
                                diagramType.getSecond(), data, value));
                    }
                }
            }
        }
        
        // construct the diagram type options table
        Table diagramTypeTable = new Table(diagramTypeGroup, SWT.BORDER);
        TableColumn column1 = new TableColumn(diagramTypeTable, SWT.NONE);
        column1.setText(Messages.getString("kiml.ui.18")); //$NON-NLS-1$
        TableColumn column2 = new TableColumn(diagramTypeTable, SWT.NONE);
        column2.setText(Messages.getString("kiml.ui.19")); //$NON-NLS-1$
        TableColumn column3 = new TableColumn(diagramTypeTable, SWT.NONE);
        column3.setText(Messages.getString("kiml.ui.20")); //$NON-NLS-1$
        diagramTypeTable.setHeaderVisible(true);
        final TableViewer diagramTypeTableViewer = new TableViewer(diagramTypeTable);
        OptionsTableProvider optionsTableProvider = new OptionsTableProvider();
        diagramTypeTableViewer.setContentProvider(optionsTableProvider);
        diagramTypeTableViewer.setLabelProvider(optionsTableProvider);
        diagramTypeTableViewer.setInput(diagramTypeEntries);
        column1.pack();
        column2.pack();
        column3.pack();
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
        diagramTypeTable.setLayoutData(tableLayoutData);
        diagramTypeTable.pack();
        tableLayoutData.heightHint = OPTIONS_TABLE_HEIGHT;
        
        // add buttons to edit the diagram type options
        final Maybe<Integer> selectionIndex = new Maybe<Integer>();
        Composite composite = new Composite(diagramTypeGroup, SWT.NONE);
        final Button editButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        editButton.setText(Messages.getString("kiml.ui.21")); //$NON-NLS-1$
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                showEditDialog(selectionIndex.get());
                diagramTypeTableViewer.refresh();
            }
        });
        final Button removeButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        removeButton.setText(Messages.getString("kiml.ui.22")); //$NON-NLS-1$
        removeButton.setEnabled(false);
        removeButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                diagramTypeEntries.remove(selectionIndex.get());
                diagramTypeTableViewer.setInput(diagramTypeEntries);
                if (diagramTypeEntries.size() == 0) {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
            }
        });
        diagramTypeTable.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                selectionIndex.set(Integer.valueOf(e.detail));
                editButton.setEnabled(true);
                removeButton.setEnabled(true);
            }
        });
        composite.setLayout(new FillLayout(SWT.VERTICAL));
        GridData compositeLayoutData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        composite.setLayoutData(compositeLayoutData);

        diagramTypeGroup.setLayout(new GridLayout(2, false));
        return diagramTypeGroup;
    }

    /** maximal length of displayed diagram type names. */
    private static final int MAX_DIAGTYPE_LENGTH = 7;
    
    /**
     * Creates an abbreviation for the given diagram type.
     * 
     * @param diagramType a diagram type name
     * @return an abbreviation for the diagram type
     */
    private String getAbbrev(final String diagramType) {
        if (diagramType.length() <= MAX_DIAGTYPE_LENGTH) {
            return diagramType;
        } else {
            StringTokenizer tokenizer = new StringTokenizer(diagramType);
            int lastDotIndex = diagramType.lastIndexOf('.');
            if (tokenizer.countTokens() == 1 && lastDotIndex >= 0
                    && lastDotIndex < diagramType.length() - 1) {
                return diagramType.substring(lastDotIndex + 1);
            } else {
                StringBuilder abbrev = new StringBuilder();
                while (tokenizer.hasMoreTokens()) {
                    abbrev.append(tokenizer.nextToken().charAt(0));
                }
                return abbrev.toString();
            }
        }
    }
    
    private void showEditDialog(final int selectionIndex) {
        System.out.println("hu");
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
        if (priorityData != null) {
            EclipseLayoutServices.readSupportPriorities(priorityData, providerIds, diagramTypes);
            priorityTableProvider.refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        if (priorityData != null) {
            for (int i = 0; i < providerIds.length; i++) {
                LayoutProviderData providerData = LayoutServices.getInstance().getLayoutProviderData(
                        providerIds[i]);
                for (int j = 0; j < diagramTypes.length; j++) {
                    int oldPriority = providerData.getSupportedPriority(diagramTypes[j]);
                    int newPriority = priorityData[i][j];
                    if (oldPriority != newPriority) {
                        providerData.setDiagramSupport(diagramTypes[j], newPriority);
                        String preference = EclipseLayoutServices.getPreferenceName(
                                providerData.getId(), diagramTypes[j]);
                        getPreferenceStore().setValue(preference, newPriority);
                    }
                }
            }
            LayoutViewPart.refreshLayoutView();
        }
        return true;
    }

}
