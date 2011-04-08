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
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

/**
 * The abstract base class for random graph pages providing comfort functionality.
 * 
 * @author mri
 */
public abstract class AbstractRandomGraphPage extends WizardPage {

    /**
     * Constructs an AbstractRandomGraphPage.
     * 
     * @param pageName
     *            the page name
     */
    protected AbstractRandomGraphPage(final String pageName) {
        super(pageName);
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
    protected void addHelp(final Control control, final String help) {
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
