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
package de.cau.cs.kieler.kiml.ui.views;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.ListSelectionDialog;

import de.cau.cs.kieler.kiml.ui.AlgorithmSelectionDialog;

/**
 * A cell editor that opens a dialog to select a subset from a given number of options.
 *
 * @author cds
 */
public class MultipleOptionsCellEditor extends DialogCellEditor {
    /**
     * The items contained in the enumeration. Used to populate the selection dialog.
     */
    private String[] items;
    
    
    /**
     * Creates a layouter hint cell editor.
     * 
     * @param parent the parent composite
     * @param items array of items to select from.
     */
    public MultipleOptionsCellEditor(final Composite parent, final String[] items) {
        super(parent);
        
        this.items = items;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite cell) {
        Control label = super.createContents(cell);
        label.addMouseListener(new MouseAdapter() {
            public void mouseDoubleClick(final MouseEvent e) {
                Object newValue = openDialogBox(cell);
                if (newValue != null) {
                    markDirty();
                    doSetValue(newValue);
                    fireApplyEditorValue();
                }
                // set focus on the layout view in order to be able to respond to key bindings
                LayoutViewPart layoutView = LayoutViewPart.findView();
                if (layoutView != null) {
                    layoutView.setFocus();
                }
            }
        });
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateContents(final Object value) {
        if (value instanceof String[]) {
            String[] strArray = (String[]) value;
            
            if (strArray.length == 0) {
                super.updateContents("");
            } else {
                // Build textual representation
                StringBuilder text = new StringBuilder();
                for (String item : strArray) {
                    text.append(", " + item);
                }
                
                // Drop the leading ", " and update the label
                super.updateContents(text.substring(2));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object openDialogBox(final Control cellEditorWindow) {
        ListSelectionDialog dialog = new ListSelectionDialog(
                cellEditorWindow.getShell(),
                items,
                new ArrayContentProvider(),
                new LabelProvider(),
                null);
        dialog.setInitialSelections((String[]) getValue());
        
        if (dialog.open() == AlgorithmSelectionDialog.OK) {
            Object[] result = dialog.getResult();
            String[] stringResult = new String[result.length];
            
            for (int i = 0; i < result.length; i++) {
                stringResult[i] = (String) result[i];
            }
            
            return stringResult;
        } else {
            return new String[0];
        }
    }

}
