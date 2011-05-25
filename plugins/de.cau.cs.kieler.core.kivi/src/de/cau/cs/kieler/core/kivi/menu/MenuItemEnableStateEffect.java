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

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.internal.KiviContributionItem;

/**
 * A KIELER View Management Effect to change the enable state of a menu entry. This 
 * can be used for example to switch a button from enabled to disabled state, changing
 * its color from normal to gray and disabling any functionality of that button.
 * 
 * The menu item is identified by the String ID of the corresponding Handler that is
 * registered for that item. Only items created in KiVi are supported. General Eclipse
 * menu contributions must be handled using the Command framework of Eclipse.
 * 
 * If the id is not registered, the effect will do nothing.
 * 
 * @author haf
 */
public class MenuItemEnableStateEffect extends AbstractEffect {

    private String id;
    private boolean enable;
    
    /**
     * Create an effect for menu item state.
     * 
     * @param menuItemID the menu item identifier
     * @param enabledState the enablement state
     */
    public MenuItemEnableStateEffect(final String menuItemID, final boolean enabledState) {
        id = menuItemID;
        enable = enabledState;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        KiviContributionItem.setEnabledState(id, enable);
    }

}
