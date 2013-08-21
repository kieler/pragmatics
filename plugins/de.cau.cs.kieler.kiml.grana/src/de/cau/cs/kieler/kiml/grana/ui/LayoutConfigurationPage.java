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
package de.cau.cs.kieler.kiml.grana.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.AlgorithmSelectionDialog;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutOptionValidator;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutDataService;

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
    private final List<Pair<LayoutOptionData<?>, Object>> optionEntries = Lists.newLinkedList();
    
    /**
     * Constructs a LayoutConfigurationPage.
     */
    public LayoutConfigurationPage() {
        super(PAGE_NAME);
    }

    /** fixed height of the options table. */
    private static final int OPTIONS_TABLE_HEIGHT = 300;
    
    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        setTitle(MESSAGE_DESCRIPTION);
        
        // construct the options table
        final Table table = new Table(parent, SWT.BORDER);
        final TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setText("Option");
        final TableColumn column4 = new TableColumn(table, SWT.NONE);
        column4.setText("Value");
        table.setHeaderVisible(true);
        final TableViewer tableViewer = new TableViewer(table);
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        tableViewer.setLabelProvider(new OptionsLabelProvider());
        column3.pack();
        column4.pack();
        GridData tableLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        table.setLayoutData(tableLayoutData);
        table.pack();
        tableLayoutData.heightHint = OPTIONS_TABLE_HEIGHT;
        
        // add button to add new options
        Composite composite = new Composite(parent, SWT.NONE);
        final Button newButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        newButton.setText("New...");
        
        // add button to edit the options
        final Button editButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        editButton.setText("Edit...");
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                Pair<LayoutOptionData<?>, Object> entry = optionEntries.get(table.getSelectionIndex());
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
                optionEntries.remove(table.getSelectionIndex());
                if (optionEntries.isEmpty()) {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
                tableViewer.refresh();
            }
        });
        
        // react on selection changes of the options table
        table.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                if (!optionEntries.isEmpty() && event.item != null) {
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
                Pair<LayoutOptionData<?>, Object> entry = optionEntries.get(table.getSelectionIndex());
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
                    if (optionEntries.isEmpty()) {
                        editButton.setEnabled(false);
                        removeButton.setEnabled(false);
                    }
                    tableViewer.refresh();
                }
            }
        });
        newButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                LayoutOptionData<?> optionData = showBrowseOptionDialog();
                if (optionData != null) {
                    // look for an existing entry with the same option
                    boolean alreadyInTable = false;
                    int index = 0;
                    for (Pair<LayoutOptionData<?>, Object> entry : optionEntries) {
                        if (optionData.equals(entry.getFirst())) {
                            alreadyInTable = true;
                            break;
                        }
                        index++;
                    }
                    if (alreadyInTable) {
                        tableViewer.setSelection(new StructuredSelection(index));
                    } else {
                        Object value = optionData.getDefault();
                        if (value == null) {
                            value = optionData.getDefaultDefault();
                        }
                        optionEntries.add(new Pair<LayoutOptionData<?>, Object>(optionData, value));
                        tableViewer.refresh();
                        tableViewer.setSelection(new StructuredSelection(optionEntries.size() - 1));
                        column3.pack();
                        column4.pack();
                    }
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
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
        
        parent.setLayout(new GridLayout(2, false));
    }
    
    /**
     * Return a layout configurator for the selected layout options.
     * 
     * @return a layout configurator
     */
    public ILayoutConfig getConfig() {
        return new BatchLayoutConfig();
    }
    
    /**
     * Shows an input dialog to edit the given option table entry.
     * 
     * @param shell the current shell
     * @param entry an option table entry
     */
    private void showEditDialog(final Shell shell, final Pair<LayoutOptionData<?>, Object> entry) {
        LayoutOptionData<?> optionData = entry.getFirst();
        if (entry.getSecond() != null) {
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
                    case REMOTE_ENUM:
                    case ENUM:
                    case ENUMSET:
                    case REMOTE_ENUMSET:
                        entry.setSecond(optionData.parseValue(result.toUpperCase()));
                    default:
                        entry.setSecond(optionData.parseValue(result));
                    }
                }
            }
        }
    }
    
    /**
     * Open a dialog to browse layout options.
     * 
     * @return the selected layout option
     */
    private LayoutOptionData<?> showBrowseOptionDialog() {
        ListDialog dialog = new ListDialog(this.getShell());
        dialog.setTitle("Select Layout Option");
        dialog.setContentProvider(ArrayContentProvider.getInstance());
        dialog.setLabelProvider(new OptionsLabelProvider());
        Collection<LayoutOptionData<?>> data = EclipseLayoutDataService
                .getInstance().getOptionData();
        ArrayList<LayoutOptionData<?>> inputList = new ArrayList<LayoutOptionData<?>>(data.size());      
        for (LayoutOptionData<?> optionData : data) {
            // layout options without target definition are not shown to the user
            if (!optionData.getTargets().isEmpty()) {
                inputList.add(optionData);
            }
        }
        Collections.sort(inputList);
        dialog.setInput(inputList);
        if (dialog.open() == ListDialog.OK) {
            Object[] result = dialog.getResult();
            if (result != null && result.length > 0) {
                return (LayoutOptionData<?>) result[0];
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
        @Override
        public Image getImage(final Object element) {
            if (element instanceof LayoutOptionData) {
                LayoutOptionData<?> optionData = (LayoutOptionData<?>) element;
                return getColumnImage(new Pair<LayoutOptionData<?>, Object>(optionData,
                        optionData.getDefaultDefault()), COL_VALUE);
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public Image getColumnImage(final Object element, final int columnIndex) {
            if (element instanceof Pair && columnIndex == COL_VALUE) {
                @SuppressWarnings("unchecked")
                Pair<LayoutOptionData<?>, Object> entry = (Pair<LayoutOptionData<?>, Object>) element;
                KimlUiPlugin.Images images = KimlUiPlugin.getDefault().getImages();
                switch (entry.getFirst().getType()) {
                case STRING:
                    return images.getPropText();
                case BOOLEAN:
                    if ((Boolean) entry.getSecond()) {
                        return images.getPropTrue();
                    } else {
                        return images.getPropFalse();
                    }
                case REMOTE_ENUM:
                case REMOTE_ENUMSET:
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
            }
            return null;
        }
        
    }
    
    /**
     * Layout configurator that uses the selected layout options.
     */
    private class BatchLayoutConfig implements ILayoutConfig {

        /** the fixed priority of the light layout configurator. */
        public static final int PRIORITY = 50;
        
        /**
         * {@inheritDoc}
         */
        public int getPriority() {
            return PRIORITY;
        }

        /**
         * {@inheritDoc}
         */
        public void enrich(final LayoutContext context) {
            // nothing to enrich
        }

        /**
         * {@inheritDoc}
         */
        public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
            for (Pair<LayoutOptionData<?>, Object> entry : optionEntries) {
                if (optionData.equals(entry.getFirst())) {
                    return entry.getSecond();
                }
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public void transferValues(final KLayoutData graphData, final LayoutContext context) {
            KGraphElement graphElement = context.getProperty(LayoutContext.GRAPH_ELEM);
            for (Pair<LayoutOptionData<?>, Object> entry : optionEntries) {
                if (matchesTargetType(entry.getFirst(), graphElement)) {
                    graphData.setProperty(entry.getFirst(), entry.getSecond());
                }
            }
        }
        
        /**
         * Check whether the given diagram part matches the target type of the layout option.
         * 
         * @param optionData a layout option data instance
         * @param graphElement a graph element
         * @return true if the layout option can be applied to the graph element
         */
        private boolean matchesTargetType(final LayoutOptionData<?> optionData,
                final KGraphElement graphElement) {
            Set<LayoutOptionData.Target> optionTargets = optionData.getTargets();
            if (graphElement instanceof KNode) {
                if (optionTargets.contains(LayoutOptionData.Target.NODES)
                        || !((KNode) graphElement).getChildren().isEmpty()
                        && optionTargets.contains(LayoutOptionData.Target.PARENTS)) {
                    return true;
                }
            } else if (graphElement instanceof KEdge) {
                if (optionTargets.contains(LayoutOptionData.Target.EDGES)) {
                    return true;
                }
            } else if (graphElement instanceof KPort) {
                if (optionTargets.contains(LayoutOptionData.Target.PORTS)) {
                    return true;
                }
            } else if (graphElement instanceof KLabel) {
                if (optionTargets.contains(LayoutOptionData.Target.LABELS)) {
                    return true;
                }
            }
            return false;
        }

    }
    
}
