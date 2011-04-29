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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

/**
 * This class can be used for nice swt text widget validation. A small image decorates the textfield
 * and a message will pop up.
 * 
 * @author pkl
 * 
 */
public class TextBoxValidator implements ModifyListener {

    private final ControlDecoration decoration;
    private final int fieldLength;
    private final String msg;
    private String decorationType = FieldDecorationRegistry.DEC_ERROR;

    /**
     * constructor for the {@link TextBoxValidator}.
     * 
     * @param text
     *            , text-field widget which should become decorate.
     * @param msg
     *            , the popup msg.
     * @param fieldLength
     *            , the allowed minimum length.
     */
    public TextBoxValidator(final Text text, final String msg, final int fieldLength) {
        this.fieldLength = fieldLength;
        Image errorImg = FieldDecorationRegistry.getDefault().getFieldDecoration(decorationType)
                .getImage();
        decoration = new ControlDecoration(text, SWT.LEFT | SWT.TOP);
        decoration.setImage(errorImg);
        decoration.hide();
        this.msg = msg;
    }

    /**
     * @see ModifyListener.
     * @param e
     *            ModifyEvent
     */
    public void modifyText(final ModifyEvent e) {
        String textContent = ((Text) e.getSource()).getText();
        if (textContent.length() < fieldLength) {
            decoration.show();
            decoration.showHoverText(msg);
        } else {
            decoration.hide();
            decoration.hideHover();
        }
    }

    /**
     * Set the decoration-type. Here you can specify, what decoration should be used.
     * 
     * @param decorationType
     *            String
     */
    public void setDecorationType(final String decorationType) {
        this.decorationType = decorationType;
    }

    /**
     * getter for decoration-type.
     * 
     * @return String of current decorationType.
     */
    public String getDecorationType() {
        return decorationType;
    }

}
