/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd.xtext;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService.LocationScheme;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;

/**
 * A combination for initializing/refreshing of KLighD views of Xtext-based models.
 * 
 * @author chsch
 */
public class VisualizeSelectedModelElementCombination extends AbstractCombination {

    /**
     * Constructor.
     */
    public VisualizeSelectedModelElementCombination() {
        KiviMenuContributionService.INSTANCE.addToolbarButton(this,
                "de.cau.cs.kieler.visualizeModelElement", "SimpleButton", "Test", null, SWT.PUSH,
                LocationScheme.POPUP, null, "de.menges.logic.Logic");
    }
    
    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param button
     *            A {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     */
    public void execute(final ButtonState button) {
        // look which button was pressed last
        if (button.getButtonId().equals("de.cau.cs.kieler.visualizeModelElement")) {
            Display.getDefault().asyncExec(new Runnable() {
                
                public void run() {
                    MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Huhu",
                            "Boohh");
                    
                }
            });
        }
    }
}
