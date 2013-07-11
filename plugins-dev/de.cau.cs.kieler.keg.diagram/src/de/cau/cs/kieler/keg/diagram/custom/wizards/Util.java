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
package de.cau.cs.kieler.keg.diagram.custom.wizards;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

/**
 * Utility functionality for the random graph wizard.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public final class Util {

    /**
     * A private constructor to prevent instantiation.
     */
    private Util() {
        // do nothing
    }

    /**
     * Adds a help text to a control by adding an exclamation mark at the top left of the control
     * and a tooltip.
     * 
     * @param control
     *            the control
     * @param help
     *            the help text
     */
    // CHECKSTYLEOFF MagicNumber
    public static void addHelp(final Control control, final String help) {
        ControlDecoration dec = new ControlDecoration(control, SWT.TOP | SWT.RIGHT);
        FieldDecoration errorFieldIndicator =
                FieldDecorationRegistry.getDefault().getFieldDecoration(
                        FieldDecorationRegistry.DEC_INFORMATION);
        dec.setImage(errorFieldIndicator.getImage());
        dec.setDescriptionText(help);
        dec.setMarginWidth(5);
        control.setToolTipText(help);
    }
    // CHECKSTYLEON MagicNumber
}
