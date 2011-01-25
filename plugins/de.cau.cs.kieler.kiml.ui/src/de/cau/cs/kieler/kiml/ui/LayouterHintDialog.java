/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ILayoutData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;

/**
 * A dialog to browse and select layout providers or layout types.
 *
 * @kieler.rating 2011-01-24 proposed yellow msp
 * @author msp
 */
public class LayouterHintDialog extends Dialog {

    /** the current layouter hint as selected by the user. */
    private String layouterHint;
    /** the label for displaying the name of the current hint. */
    private Label displayNameLabel;
    /** the label for displaying the description of the current hint. */
    private Label descriptionLabel;
    /** the tree viewer for layout algorithm selection. */
    private TreeViewer treeViewer;
    
    /**
     * Creates a layout hint dialog.
     * 
     * @param parentShell the parent shell
     * @param currentHint the currently active layouter hint
     */
    public LayouterHintDialog(final Shell parentShell, final String currentHint) {
        super(parentShell);
        this.layouterHint = currentHint;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.getString("kiml.ui.58")); //$NON-NLS-1$
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
        Object element = selection.getFirstElement();
        if (element instanceof ILayoutData) {
            layouterHint = ((ILayoutData) element).getId();
        }
        return super.close();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        ((GridLayout) composite.getLayout()).numColumns = 2;
        createSelectionTree(composite);
        createDescriptionArea(composite);
        return composite;
    }
    
    /**
     * Create the dialog area that displays the selection tree and filter text.
     * 
     * @param parent the parent composite
     * @return the control for the selection area
     */
    private Control createSelectionTree(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        
        // create filter text
        final Text filterText = new Text(composite, SWT.BORDER);
        filterText.setText(Messages.getString("kiml.ui.59"));
        filterText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        filterText.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
        
        // create tree viewer
        LayoutServices layoutServices = LayoutServices.getInstance();
        treeViewer = new TreeViewer(composite, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
        final LayouterHintProvider provider = new LayouterHintProvider();
        treeViewer.setContentProvider(provider);
        treeViewer.setLabelProvider(new LabelProvider());
        treeViewer.setSorter(new ViewerSorter());
        treeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        treeViewer.setInput(layoutServices);
        treeViewer.expandAll();
        LayoutProviderData providerData = layoutServices.getLayoutProviderData(layouterHint);
        if (providerData != null) {
            treeViewer.setSelection(new StructuredSelection(providerData));
        }
        treeViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(final DoubleClickEvent event) {
                okPressed();
            }
        });
        
        // set up a filter on the tree viewer using the filter text
        final Maybe<Boolean> filterChanged = new Maybe<Boolean>(Boolean.FALSE);
        filterText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                if (!filterChanged.get()) {
                    filterChanged.set(Boolean.TRUE);
                    filterText.setForeground(null);
                    int pos = filterText.getCaretPosition();
                    String newText = filterText.getText(pos - 1, pos - 1);
                    filterText.setText(newText);
                    filterText.setSelection(pos);
                } else {
                    provider.updateFilter(filterText.getText());
                    treeViewer.refresh();
                    treeViewer.expandAll();
                }
            }
        });
        treeViewer.addFilter(new ViewerFilter() {
            public boolean select(final Viewer viewer, final Object parentElement,
                    final Object element) {
                return provider.applyFilter(element);
            }
        });
        
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return composite;
    }
    
    /** width of the description area. */
    private static final int DESCRIPTION_WIDTH = 300;
    
    /**
     * Create the dialog area that displays the description of a layout algorithm.
     * 
     * @param parent the parent composite
     * @return the control for the description area
     */
    private Control createDescriptionArea(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        
        displayNameLabel = new Label(composite, SWT.NONE);
        FontDescriptor fontDescriptor = FontDescriptor.createFrom(parent.getFont());
        fontDescriptor = fontDescriptor.increaseHeight(2).setStyle(SWT.BOLD);
        displayNameLabel.setFont(fontDescriptor.createFont(parent.getDisplay()));
        displayNameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        descriptionLabel = new Label(composite, SWT.WRAP);
        GridData descriptionLayoutData = new GridData(SWT.FILL, SWT.TOP, false, true);
        descriptionLayoutData.widthHint = DESCRIPTION_WIDTH;
        descriptionLabel.setLayoutData(descriptionLayoutData);
        
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return composite;
    }
    
    /**
     * The layouter hint that was selected by the user.
     * 
     * @return the selected layouter hint
     */
    public String getSelectedHint() {
        return layouterHint;
    }

}
