/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.batch.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.elk.core.GraphIssue;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.LayoutOptionValidator;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.ui.AlgorithmSelectionDialog;
import org.eclipse.elk.core.ui.ElkUiPlugin;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.ImageRegistry;
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
    private final List<Pair<LayoutOptionData, Object>> optionEntries = Lists.newLinkedList();
    
    /**
     * Constructs a LayoutConfigurationPage.
     * 
     * @param initialEntries the initial layout configuration
     */
    public LayoutConfigurationPage(final List<Pair<LayoutOptionData, Object>> initialEntries) {
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
                Pair<LayoutOptionData, Object> entry = optionEntries.get(
                        table.getSelectionIndex());
                if (entry != null) {
                    showEditDialog(parent.getShell(), entry);
                    tableViewer.refresh();
                }
            }
        });
        
        // add button to remove an option
        final Button removeButton = new Button(buttonComposite, SWT.PUSH | SWT.CENTER);
        removeButton.setText("Remove"); //$NON-NLS-1$
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
                Pair<LayoutOptionData, Object> entry = optionEntries.get(
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
                LayoutOptionData optionData = showBrowseOptionDialog();
                if (optionData != null) {
                    // look for an existing entry with the same option
                    Pair<LayoutOptionData, Object> entry = null;
                    for (Pair<LayoutOptionData, Object> oe : optionEntries) {
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
                            entry = new Pair<LayoutOptionData, Object>(optionData, value);
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
    public LayoutConfigurator getConfig() {
        LayoutConfigurator config = new LayoutConfigurator();
        for (Pair<LayoutOptionData, Object> pair : optionEntries) {
            config.configure(ElkGraphElement.class).setProperty(pair.getFirst(), pair.getSecond());
        }
        return config;
    }
    
    /**
     * Shows an input dialog to edit the given option table entry.
     * 
     * @param shell the current shell
     * @param entry an option table entry
     */
    private void showEditDialog(final Shell shell, final Pair<LayoutOptionData, Object> entry) {
        LayoutOptionData optionData = entry.getFirst();
        if (optionData.equals(CoreOptions.ALGORITHM)) {
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
                    new StringLayoutOptionValidator(optionData));
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
    private LayoutOptionData showBrowseOptionDialog() {
        ListDialog dialog = new ListDialog(this.getShell());
        dialog.setTitle("Select Layout Option");
        dialog.setContentProvider(ArrayContentProvider.getInstance());
        dialog.setLabelProvider(new OptionsLabelProvider());
        Collection<LayoutOptionData> data = LayoutMetaDataService.getInstance().getOptionData();
        ArrayList<LayoutOptionData> inputList = new ArrayList<LayoutOptionData>(data.size());      
        for (LayoutOptionData optionData : data) {
            // layout options without target definition are not shown to the user
            if (!optionData.getTargets().isEmpty()) {
                inputList.add(optionData);
            }
        }
        Collections.sort(inputList, new Comparator<LayoutOptionData>() {
            public int compare(final LayoutOptionData lod1, final LayoutOptionData lod2) {
                return lod1.getName().compareTo(lod2.getName());
            }
        });
        dialog.setInput(inputList);
        if (dialog.open() == ListDialog.OK) {
            Object[] result = dialog.getResult();
            if (result != null && result.length > 0) {
                return (LayoutOptionData) result[0];
            }
        }
        return null;
    }
    
    
    /**
     * An input validator that checks if a given String can be parsed into a valid option value for a given layout
     * option.
     */
    private static final class StringLayoutOptionValidator implements IInputValidator {
        /** the layout option data to validate for. */
        private LayoutOptionData optionData;
        /** validator used to validate layout options after parsing. */
        private LayoutOptionValidator optionValidator = new LayoutOptionValidator();
        
        
        /**
         * Creates a layout option validator for a layout option data.
         * 
         * @param theoptionData layout option data
         */
        private StringLayoutOptionValidator(final LayoutOptionData theoptionData) {
            this.optionData = theoptionData;
        }
        
        
        /**
         * {@inheritDoc}
         */
        public String isValid(final String newText) {
            // Try to parse the string value
            Object parsedValue = optionData.parseValue(newText);
            
            // If the value couldn't be parsed, raise an error message depending on which data format the layout
            // option expects
            if (parsedValue == null) {
                switch (optionData.getType()) {
                case BOOLEAN:
                    return "Expected true or false.";
                case ENUM:
                case ENUMSET:
                    return getChoicesMessage();
                case INT:
                    return "Expected integer number.";
                case FLOAT:
                    return "Expected real number.";
                case OBJECT:
                    // We allow null objects if the input string is either null or "null"
                    if (newText != null && !newText.equals("null")) {
                        return "Error parsing data object.";
                    }
                }
            } else {
                // Let our validator validate the value to be validated
                List<GraphIssue> issues = optionValidator.checkProperty(optionData, parsedValue, null);
                if (!issues.isEmpty()) {
                    return issues.get(0).toString();
                }
            }
            
            // Fallback
            return null;
        }
        
        /**
         * Creates an error message for available choices.
         * 
         * @param choices the available choices
         * @return an error message
         */
        private String getChoicesMessage() {
            StringBuilder messageBuilder = new StringBuilder("Invalid value. Valid values are ");
            String[] choices = optionData.getChoices();
            for (int i = 0; i < choices.length; i++) {
                if (i == 0) {
                    messageBuilder.append(" " + choices[i]);
                } else {
                    messageBuilder.append(", " + choices[i]);
                }
            }
            return messageBuilder.toString();
        }

    }
    
    /**
     * Label provider for the options table.
     */
    private class OptionsLabelProvider extends LabelProvider implements ITableLabelProvider {

        /**
         * {@inheritDoc}
         */
        public Image getColumnImage(final Object element, final int columnIndex) {
            LayoutOptionData optionData = null;
            Object value = null;
            if (element instanceof Pair && columnIndex == COL_VALUE) {
                @SuppressWarnings("unchecked")
                Pair<LayoutOptionData, Object> entry = (Pair<LayoutOptionData, Object>) element;
                optionData = entry.getFirst();
                value = entry.getSecond();
            } else if (element instanceof LayoutOptionData) {
                optionData = (LayoutOptionData) element;
                value = optionData.getDefaultDefault();
            }
            if (optionData != null) {
                ImageRegistry imageRegistry = ElkUiPlugin.getInstance().getImageRegistry();
                switch (optionData.getType()) {
                case STRING:
                    return imageRegistry.get(ElkUiPlugin.IMG_TEXT);
                case BOOLEAN:
                    if (value == null || (Boolean) value) {
                        return imageRegistry.get(ElkUiPlugin.IMG_TRUE);
                    } else {
                        return imageRegistry.get(ElkUiPlugin.IMG_FALSE);
                    }
                case ENUM:
                case ENUMSET:
                    return imageRegistry.get(ElkUiPlugin.IMG_CHOICE);
                case INT:
                    return imageRegistry.get(ElkUiPlugin.IMG_INT);
                case FLOAT:
                    return imageRegistry.get(ElkUiPlugin.IMG_FLOAT);
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
                Pair<LayoutOptionData, Object> entry = (Pair<LayoutOptionData, Object>) element;
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
                LayoutOptionData optionData = (LayoutOptionData) element;
                return optionData.getName();
            }
            return null;
        }
        
    }
    
}
