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
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.util.Pair;
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
    /** data matrix: rows represent layout providers, columns represent diagram types. */
    private int[][] data;
    /** the provider class that manages content of the priority table. */
    private PriorityTableProvider tableProvider;

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
        return createPrioritiesGroup(parent);
    }
    
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
        data = new int[layoutProviderCount][diagramTypes.length];
        PriorityTableProvider.DataEntry[] tableEntries
                = new PriorityTableProvider.DataEntry[layoutProviderCount];
        String[] layouterNames = new String[layoutProviderCount];
        i = 0;
        for (LayoutProviderData providerData : layoutProviderData) {
            providerIds[i] = providerData.getId();
            tableEntries[i] = new PriorityTableProvider.DataEntry();
            tableEntries[i].setLayouterIndex(i);
            tableEntries[i].setPriorities(data[i]);
            layouterNames[i] = providerData.getName();
            for (int j = 0; j < diagramTypes.length; j++) {
                data[i][j] = providerData.getSupportedPriority(diagramTypes[j]);
            }
            i++;
        }

        // construct the priorities table
        Label tableHeaderLabel = new Label(prioritiesGroup, SWT.WRAP);
        tableHeaderLabel.setText(Messages.getString("kiml.ui.3")); //$NON-NLS-1$
        GridData labelLayoutData = new GridData(SWT.LEFT, SWT.FILL, false, false);
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
        tableProvider = new PriorityTableProvider(priorityTableViewer, data, layouterNames);
        priorityTableViewer.setContentProvider(tableProvider);
        priorityTableViewer.setLabelProvider(tableProvider);
        priorityTableViewer.setCellEditors(cellEditors);
        priorityTableViewer.setCellModifier(tableProvider);
        priorityTableViewer.setInput(tableEntries);
        for (TableColumn column : columns) {
            column.pack();
        }
        prioritiesTable.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        prioritiesTable.pack();
        labelLayoutData.widthHint = prioritiesTable.getSize().x;

        prioritiesGroup.setLayout(new GridLayout(1, false));
        return prioritiesGroup;
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
        if (data != null) {
            EclipseLayoutServices.readSupportPriorities(data, providerIds, diagramTypes);
            tableProvider.refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        if (data != null) {
            for (int i = 0; i < providerIds.length; i++) {
                LayoutProviderData providerData = LayoutServices.getInstance().getLayoutProviderData(
                        providerIds[i]);
                for (int j = 0; j < diagramTypes.length; j++) {
                    int oldPriority = providerData.getSupportedPriority(diagramTypes[j]);
                    int newPriority = data[i][j];
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
