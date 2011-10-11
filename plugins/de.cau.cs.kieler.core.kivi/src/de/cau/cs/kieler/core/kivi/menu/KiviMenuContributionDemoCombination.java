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
package de.cau.cs.kieler.core.kivi.menu;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.KiViPlugin;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService.LocationScheme;

/**
 * A simple demo Combination for using Buttons in KIELER View Management (KiVi).
 * 
 * @author haf
 */
public class KiviMenuContributionDemoCombination extends AbstractCombination {

    // we identify buttons only by an ID
    private final String[] buttonIds = { "de.cau.cs.kieler.core.kivi.demo.button1",
            "de.cau.cs.kieler.core.kivi.demo.button2" };

    /**
     * Define buttons in the constructor of the combination.
     */
    public KiviMenuContributionDemoCombination() {
        // define a very simple button for testing with only a few required data
        KiviMenuContributionService.INSTANCE.addToolbarButton(this, buttonIds[0], "SimpleButton");

        // define a button with all fancy stuff like icon, tooltip and visibility
        String tooltip = "Demo Button for KiVi Button API."
                + "Deactivate combination in KiVi preferences to remove this.";
        ImageDescriptor icon = KiViPlugin.imageDescriptorFromPlugin(KiViPlugin.PLUGIN_ID,
                "icons/producer.png");
        // a button can be made visible only for specific editors (given by Editor IDs)
        // or by a visibility expression defined with the eclipse core expressions framework. just
        // the same way as you would do for standard items in the org.eclipse.ui.menu ext. point.
        // here we register this button only for the standard text editor
        String visibleIn = "org.eclipse.ui.DefaultTextEditor";
        KiviMenuContributionService.INSTANCE.addToolbarButton(this, buttonIds[1], "Demo Button 2",
                tooltip, icon, SWT.PUSH, LocationScheme.MENU_POPUP_TOOLBAR, null, null, null, visibleIn);
    }

    /**
     * Execute this combination.
     * 
     * @param button the button state
     */
    public void execute(final ButtonState button) {
        // look which button was pressed last
        if (button.getButtonId().equals(buttonIds[0])) {
            // for some demo effect, change the enablement (-> grays some button out)
            this.schedule(new MenuItemEnableStateEffect(buttonIds[0], false));
            this.schedule(new MenuItemEnableStateEffect(buttonIds[1], true));
        } else if (button.getButtonId().equals(buttonIds[1])) {
            this.schedule(new MenuItemEnableStateEffect(buttonIds[0], true));
            this.schedule(new MenuItemEnableStateEffect(buttonIds[1], false));
        }
        // make sure you don't do anything if any other button was pressed.
    }

}
