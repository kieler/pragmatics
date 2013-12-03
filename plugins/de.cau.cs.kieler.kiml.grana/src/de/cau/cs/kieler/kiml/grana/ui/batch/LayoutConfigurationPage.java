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
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.ListDialog;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.AlgorithmSelectionDialog;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutOptionValidator;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * This page allows to configure the layout that is applied to each graph.
 * 
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class LayoutConfigurationPage extends WizardPage {

    /** the name of the file selection page. */
    private static final String PAGE_NAME = "LayoutConfigurationPage";
    /** the description of this page. */
    private static final String MESSAGE_DESCRIPTION =
            "Specify layout options that will be applied to each graph";
    /** the "Option" column. */
    private static final int COL_OPTION = 0;
    /** the "Value" column. */
    private static final int COL_VALUE = 1;
    
    /** list of layout option entries. */
    private final List<Pair<LayoutOptionData<Object>, Object>> optionEntries = Lists.newLinkedList();
    
    /**
     * Constructs a LayoutConfigurationPage.
     * 
     * @param initialEntries the initial layout configuration
     */
    public LayoutConfigurationPage(final List<Pair<LayoutOptionData<Object>, Object>> initialEntries) {
        super(PAGE_NAME);
        optionEntries.addAll(initialEntries);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        setTitle(MESSAGE_DESCRIPTION);
        Composite parentComposite = new Composite(parent, SWT.NONE);
        
        // construct the options table
        final Table table = new Table(parentComposite, SWT.BORDER);
        final TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setText("Option");
        final TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setText("Value");
        table.setHeaderVisible(true);
        final TableViewer tableViewer = new TableViewer(table);
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        tableViewer.setLabelProvider(new OptionsLabelProvider());
        tableViewer.setInput(optionEntries);
        column1.pack();
        column2.pack();
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(tableLayoutData);
        
        // add button to add new options
        Composite buttonComposite = new Composite(parentComposite, SWT.NONE);
        final Button newButton = new Button(buttonComposite, SWT.PUSH | SWT.CENTER);
        newButton.setText("New...");
        
        // add button to edit the options
        final Button editButton = new Button(buttonComposite, SWT.PUSH | SWT.CENTER);
        editButton.setText("Edit...");
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                Pair<LayoutOptionData<Object>, Object> entry = optionEntries.get(
                        table.getSelectionIndex());
                if (entry != null) {
                    showEditDialog(parent.getShell(), entry);
                    tableViewer.refresh();
                }
            }
        });
        
        // add button to remove an option
        final Button removeButton = new Button(buttonComposite, SWT.PUSH | SWT.CENTER);
        removeButton.setText(Messages.getString("kiml.ui.22")); //$NON-NLS-1$
        removeButton.setEnabled(false);
        removeButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                optionEntries.remove(table.getSelectionIndex());
                tableViewer.setInput(optionEntries);
                int index = table.getSelectionIndex();
                if (index < 0 || index >= optionEntries.size()) {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
            }
        });
        
        // react on selection changes of the options table
        table.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                int index = table.getSelectionIndex();
                if (index >= 0 && index < optionEntries.size()) {
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
                Pair<LayoutOptionData<Object>, Object> entry = optionEntries.get(
                        table.getSelectionIndex());
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
                    optionEntries.remove(table.getSelectionIndex());
                    tableViewer.setInput(optionEntries);
                    int index = table.getSelectionIndex();
                    if (index < 0 || index >= optionEntries.size()) {
                        editButton.setEnabled(false);
                        removeButton.setEnabled(false);
                    }
                }
            }
        });
        newButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                LayoutOptionData<Object> optionData = showBrowseOptionDialog();
                if (optionData != null) {
                    // look for an existing entry with the same option
                    Pair<LayoutOptionData<Object>, Object> entry = null;
                    for (Pair<LayoutOptionData<Object>, Object> oe : optionEntries) {
                        if (optionData.equals(oe.getFirst())) {
                            entry = oe;
                            break;
                        }
                    }
                    if (entry == null) {
                        Object value = optionData.getDefault();
                        if (value == null) {
                            value = optionData.getDefaultDefault();
                            if (value == null) {
                                value = optionData.parseValue("");
                            }
                        }
                        if (value != null) {
                            entry = new Pair<LayoutOptionData<Object>, Object>(optionData, value);
                            optionEntries.add(entry);
                            tableViewer.setInput(optionEntries);
                            column1.pack();
                            column2.pack();
                        }
                    }
                    if (entry != null) {
                        tableViewer.setSelection(new StructuredSelection(entry));
                        editButton.setEnabled(true);
                        removeButton.setEnabled(true);
                    }
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
        buttonComposite.setLayout(compositeLayout);
        GridData compositeLayoutData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        buttonComposite.setLayoutData(compositeLayoutData);
        
        parentComposite.setLayout(new GridLayout(2, false));
        setControl(parentComposite);
    }
    
    /**
     * Return a layout configurator for the selected layout options.
     * 
     * @return a layout configurator
     */
    public VolatileLayoutConfig getConfig() {
        VolatileLayoutConfig config = new VolatileLayoutConfig();
        for (Pair<LayoutOptionData<Object>, Object> pair : optionEntries) {
            config.setValue(pair.getFirst(), pair.getSecond());
        }
        return config;
    }
    
    /**
     * Shows an input dialog to edit the given option table entry.
     * 
     * @param shell the current shell
     * @param entry an option table entry
     */
    private void showEditDialog(final Shell shell, final Pair<LayoutOptionData<Object>, Object> entry) {
        LayoutOptionData<?> optionData = entry.getFirst();
        if (optionData.equals(LayoutOptions.ALGORITHM)) {
            // show a selection dialog for a layouter hint
            AlgorithmSelectionDialog dialog = new AlgorithmSelectionDialog(shell, null);
            if (dialog.open() == AlgorithmSelectionDialog.OK) {
                String result = dialog.getSelectedHint();
                if (result != null) {
                    entry.setSecond(result);
                }
            }
        } else {
            // show an input dialog for some other option
            String value = entry.getSecond().toString();
            InputDialog dialog = new InputDialog(shell, "Edit Option",
                    "Enter a new value for the layout option:", value,
                    new LayoutOptionValidator(optionData));
            if (dialog.open() == InputDialog.OK) {
                String result = dialog.getValue().trim();
                switch (optionData.getType()) {
                case ENUM:
                case ENUMSET:
                    entry.setSecond(optionData.parseValue(result.toUpperCase()));
                    break;
                default:
                    entry.setSecond(optionData.parseValue(result));
                }
            }
        }
    }
    
    /**
     * Open a dialog to browse layout options.
     * 
     * @return the selected layout option
     */
    @SuppressWarnings("unchecked")
    private LayoutOptionData<Object> showBrowseOptionDialog() {
        ListDialog dialog = new ListDialog(this.getShell());
        dialog.setTitle("Select Layout Option");
        dialog.setContentProvider(ArrayContentProvider.getInstance());
        dialog.setLabelProvider(new OptionsLabelProvider());
        Collection<LayoutOptionData<?>> data = LayoutDataService.getInstance().getOptionData();
        ArrayList<LayoutOptionData<?>> inputList = new ArrayList<LayoutOptionData<?>>(data.size());      
        for (LayoutOptionData<?> optionData : data) {
            // layout options without target definition are not shown to the user
            if (!optionData.getTargets().isEmpty()) {
                inputList.add(optionData);
            }
        }
        Collections.sort(inputList, new Comparator<LayoutOptionData<?>>() {
            public int compare(final LayoutOptionData<?> lod1, final LayoutOptionData<?> lod2) {
                return lod1.getName().compareTo(lod2.getName());
            }
        });
        dialog.setInput(inputList);
        if (dialog.open() == ListDialog.OK) {
            Object[] result = dialog.getResult();
            if (result != null && result.length > 0) {
                return (LayoutOptionData<Object>) result[0];
            }
        }
        return null;
    }
    
    /**
     * Label provider for the options table.
     */
    private class OptionsLabelProvider extends LabelProvider implements ITableLabelProvider {

        /**
         * {@inheritDoc}
         */
        public Image getColumnImage(final Object element, final int columnIndex) {
            LayoutOptionData<?> optionData = null;
            Object value = null;
            if (element instanceof Pair && columnIndex == COL_VALUE) {
                @SuppressWarnings("unchecked")
                Pair<LayoutOptionData<?>, Object> entry = (Pair<LayoutOptionData<?>, Object>) element;
                optionData = entry.getFirst();
                value = entry.getSecond();
            } else if (element instanceof LayoutOptionData) {
                optionData = (LayoutOptionData<?>) element;
                value = optionData.getDefaultDefault();
            }
            if (optionData != null) {
                KimlUiPlugin.Images images = KimlUiPlugin.getDefault().getImages();
                switch (optionData.getType()) {
                case STRING:
                    return images.getPropText();
                case BOOLEAN:
                    if (value == null || (Boolean) value) {
                        return images.getPropTrue();
                    } else {
                        return images.getPropFalse();
                    }
                case ENUM:
                case ENUMSET:
                    return images.getPropChoice();
                case INT:
                    return images.getPropInt();
                case FLOAT:
                    return images.getPropFloat();
                }
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public String getColumnText(final Object element, final int columnIndex) {
            if (element instanceof Pair) {
                @SuppressWarnings("unchecked")
                Pair<LayoutOptionData<?>, Object> entry = (Pair<LayoutOptionData<?>, Object>) element;
                switch (columnIndex) {
                case COL_OPTION:
                    return entry.getFirst().getName();
                case COL_VALUE:
                    if (entry.getFirst().getType() == LayoutOptionData.Type.ENUM
                            && entry.getSecond() instanceof Integer) {
                        return entry.getFirst().getEnumValue((Integer) entry.getSecond()).toString();
                    } else {
                        return entry.getSecond().toString();
                    }
                }
            } else if (element instanceof LayoutOptionData) {
                LayoutOptionData<?> optionData = (LayoutOptionData<?>) element;
                return optionData.getName();
            }
            return null;
        }
        
    }
    
}
