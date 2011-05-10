/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kex.ui.wizards.exporting;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Control;

/**
 * This class can be used for nice swt text widget validation. A small image decorates the textfield
 * and a message will pop up.
 * 
 * @author pkl
 * 
 */
public class TextBoxValidator implements ModifyListener {

    private final ControlDecoration decoration;
    private final String msg;
    private String decorationType = FieldDecorationRegistry.DEC_ERROR;

    /**
     * You can use that field for adding boolean flag to your control widget. All you have to do is
     * to use the control with {@link Control.setData(String key)}. When creating a new
     * {@link TextBoxValidator} object a first check will be triggered and depending on that flag
     * the decoration will show or not.
     * 
     */
    public static final String WANTS_COMPLETE = "wantsComplete";

    /**
     * When creating a new {@link TextBoxValidator} object the data Object of the control will check
     * and depending on that flag the decoration will show or not. Note: if WANTS_COMPLETE is not
     * set it will be ignored and not shown as initial.
     * 
     * @param control
     *            , control-field widget which should become decorate.
     * @param msg
     *            , the popup msg.
     */
    public TextBoxValidator(final Control control, final String msg) {
        this.msg = msg;
        decoration = new ControlDecoration(control, SWT.LEFT | SWT.TOP);
        decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(decorationType)
                .getImage());
        if (control.getData(WANTS_COMPLETE) instanceof Boolean) {
            if ((Boolean) control.getData(WANTS_COMPLETE)) {
                decoration.hide();
            } else {
                decoration.show();
            }
        }
        // used to show hover when focus is set otherwise hide hover.
        control.addFocusListener(new FocusListener() {

            public void focusLost(final FocusEvent e) {
                decoration.hideHover();
            }

            public void focusGained(final FocusEvent e) {
                if (check(e)) {
                    decoration.showHoverText(msg);
                }
            }
        });
    }

    /**
     * @see ModifyListener.
     * @param e
     *            ModifyEvent
     */
    public final void modifyText(final ModifyEvent e) {
        if (check(e)) {
            decoration.show();
            decoration.showHoverText(msg);
        } else {
            decoration.hide();
            decoration.hideHover();
        }
    }

    /**
     * Has to override for custom validation. This check will be used to show decoration or not.
     * 
     * @param e
     *            the triggered typed-event
     * 
     * @return true, if decoration should be shown otherwise return false.
     */
    public boolean check(final TypedEvent e) {
        return true;
    }

    /**
     * Set the decoration-type. Here you can specify, what decoration should be used.
     * 
     * @param decorationType
     *            String
     */
    public final void setDecorationType(final String decorationType) {
        this.decorationType = decorationType;
        decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(decorationType)
                .getImage());
    }

    /**
     * getter for decoration-type.
     * 
     * @return String of current decorationType.
     */
    public final String getDecorationType() {
        return decorationType;
    }

}
