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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.ui.LayouterHintDialog;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A cell editor that opens a dialog to select a layout algorithm or type.
 *
 * @kieler.rating 2011-01-24 proposed yellow msp
 * @author msp
 */
public class LayouterHintCellEditor extends CellEditor {

    /** the label for displaying the current selection. */
    private Label label;
    /** the currently set layouter hint identifier. */
    private String value;
    
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
    protected Control createControl(final Composite parent) {
        label = new Label(parent, SWT.LEFT);
        label.setFont(parent.getFont());
        label.setBackground(parent.getBackground());
        label.addMouseListener(new MouseListener() {
            public void mouseDoubleClick(final MouseEvent e) {
            }
            public void mouseDown(final MouseEvent e) {
            }
            public void mouseUp(final MouseEvent e) {
                showDialog();
            }
        });
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doSetFocus() {
        showDialog();
    }
    
    /**
     * Open a dialog for selection of a layout algorithm and set the new value.
     */
    private void showDialog() {
        LayouterHintDialog dialog = new LayouterHintDialog(Display.getDefault().getActiveShell(),
                value);
        if (dialog.open() == LayouterHintDialog.OK) {
            doSetValue(dialog.getSelectedHint());
            valueChanged(true, true);
            fireApplyEditorValue();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object doGetValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doSetValue(final Object thevalue) {
        if (thevalue instanceof String) {
            this.value = (String) thevalue;
            String newText;
            LayoutServices layoutServices = LayoutServices.getInstance();
            LayoutTypeData layoutType = layoutServices.getTypeData(value);
            if (layoutType != null) {
                newText = layoutType.toString();
            } else {
                LayoutAlgorithmData layouterData = layoutServices.getAlgorithmData(value);
                if (layouterData != null) {
                    newText = layouterData.toString();
                } else {
                    newText = Messages.getString("kiml.ui.8");
                }
            }
            label.setText(newText);
        }
    }

}
