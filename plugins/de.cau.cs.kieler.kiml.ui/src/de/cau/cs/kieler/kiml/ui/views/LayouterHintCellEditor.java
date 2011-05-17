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

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.ui.LayouterHintDialog;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A cell editor that opens a dialog to select a layout algorithm or type.
 *
 * @kieler.rating 2011-01-24 proposed yellow msp
 * @author msp
 */
public class LayouterHintCellEditor extends DialogCellEditor {
    
    /**
     * Creates a layouter hint cell editor.
     * 
     * @param parent the parent composite
     */
    public LayouterHintCellEditor(final Composite parent) {
        super(parent);
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
        if (value instanceof String) {
            String newText;
            LayoutDataService layoutServices = LayoutDataService.getInstance();
            LayoutTypeData layoutType = layoutServices.getTypeData((String) value);
            if (layoutType != null) {
                newText = layoutType.toString();
            } else {
                LayoutAlgorithmData layouterData = layoutServices.getAlgorithmData((String) value);
                if (layouterData != null) {
                    newText = layouterData.toString();
                } else {
                    newText = Messages.getString("kiml.ui.8");
                }
            }
            super.updateContents(newText);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object openDialogBox(final Control cellEditorWindow) {
        LayouterHintDialog dialog = new LayouterHintDialog(cellEditorWindow.getShell(),
                (String) getValue());
        if (dialog.open() == LayouterHintDialog.OK) {
            return dialog.getSelectedHint();
        }
        return null;
    }

}
