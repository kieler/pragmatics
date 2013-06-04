/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A dialog for selecting layout options that are considered in evolutionary meta layout.
 *
 * @author msp
 */
public class OptionSelectionDialog extends Dialog {
    
    /** the table viewer for the layout options. */
    private TableViewer optionViewer;
    /** the list of selected options. */
    private List<LayoutOptionData<?>> selectedOptions;

    /**
     * Creates an option selection dialog where all options are pre-selected.
     * 
     * @param parentShell the parent shell, or <code>null</code> to create a top-level shell
     */
    public OptionSelectionDialog(final Shell parentShell) {
        super(parentShell);
    }
    
    /**
     * Creates an option selection dialog where the given options are pre-selected.
     * 
     * @param parentShell the parent shell, or <code>null</code> to create a top-level shell
     * @param selectedOptions the options to pre-select
     */
    public OptionSelectionDialog(final Shell parentShell,
            final List<LayoutOptionData<?>> selectedOptions) {
        super(parentShell);
        this.selectedOptions = selectedOptions;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Select Layout Options");
    }
    
    /**
     * Returns the selection of layout options.
     * 
     * @return the selection of layout options
     */
    public List<LayoutOptionData<?>> getSelection() {
        return selectedOptions;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        
        // create buttons for changing all selections
        Composite buttonComposite = new Composite(composite, SWT.NONE);
        buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        buttonComposite.setLayout(new FillLayout());
        Button deselectButton = new Button(buttonComposite, SWT.PUSH | SWT.FLAT);
        deselectButton.setText("Deselect All");
        deselectButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                for (TableItem item : optionViewer.getTable().getItems()) {
                    item.setChecked(false);
                }
            }
        });
        Button selectButton = new Button(buttonComposite, SWT.PUSH | SWT.FLAT);
        selectButton.setText("Select All");
        selectButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                for (TableItem item : optionViewer.getTable().getItems()) {
                    item.setChecked(true);
                }
            }
        });
        
        // create the table viewer for layout options
        TableViewer tableViewer = new TableViewer(composite, SWT.CHECK);
        tableViewer.setContentProvider(new OptionsContentProvider());
        tableViewer.addFilter(new EvolOptionsFilter());
        tableViewer.setInput(LayoutDataService.getInstance());
        tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tableViewer.setComparator(new ViewerComparator());
        optionViewer = tableViewer;
        
        // initialize the selection
        if (selectedOptions == null) {
            for (TableItem item : tableViewer.getTable().getItems()) {
                item.setChecked(true);
            }
        } else {
            for (TableItem item : tableViewer.getTable().getItems()) {
                if (selectedOptions.contains(item.getData())) {
                    item.setChecked(true);
                }
            }
        }
        
        return composite;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        selectedOptions = new ArrayList<LayoutOptionData<?>>();
        for (TableItem item : optionViewer.getTable().getItems()) {
            if (item.getChecked()) {
                selectedOptions.add((LayoutOptionData<?>) item.getData());
            }
        }
        return super.close();
    }
    
    /**
     * Content provider that provides a list of all layout options.
     */
    private static class OptionsContentProvider implements IStructuredContentProvider {

        /**
         * {@inheritDoc}
         */
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof LayoutDataService) {
                return ((LayoutDataService) inputElement).getOptionData().toArray();
            }
            return new Object[0];
        }

        /**
         * {@inheritDoc}
         */
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // nothing to do here
        }

        /**
         * {@inheritDoc}
         */
        public void dispose() {
            // nothing to dispose
        }
        
    }
    
    /**
     * Viewer filter that accepts only layout options that can be handled in evolutionary layout.
     */
    private static class EvolOptionsFilter extends ViewerFilter {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            if (element instanceof LayoutOptionData<?>) {
                LayoutOptionData<?> optionData = (LayoutOptionData<?>) element;
                return LayoutOptions.ALGORITHM.equals(optionData)
                        || (optionData.getTargets().contains(LayoutOptionData.Target.PARENTS)
                        && optionData.getVariance() > 0 && typeSupported(optionData.getType()));
            }
            return false;
        }
        
        /**
         * Determine whether the given layout option type is supported by evolutionary layout.
         * 
         * @param type a layout option type
         * @return true if the type is supported
         */
        private boolean typeSupported(final LayoutOptionData.Type type) {
            switch (type) {
            case BOOLEAN:
            case ENUM:
            case INT:
            case FLOAT:
                return true;
            default:
                return false;
            }
        }
        
    }

}
