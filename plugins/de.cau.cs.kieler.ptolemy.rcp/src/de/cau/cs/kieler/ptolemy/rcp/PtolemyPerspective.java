/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

import com.google.common.collect.ImmutableSet;

/**
 * @author uru
 * 
 */
@SuppressWarnings("restriction")
public class PtolemyPerspective implements IPerspectiveFactory {

    /** a list with all accepted menu contributions. */
    final ImmutableSet<String> acceptedMenuContribs = ImmutableSet
            .of("org.eclipse.ui.openLocalFile");

    /**
     * {@inheritDoc}
     */
    @Override
    public void createInitialLayout(IPageLayout layout) {

        // hide the toolbar and the perspective area
        WorkbenchWindow activeWorkbenchWindow =
                (WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        activeWorkbenchWindow.setCoolBarVisible(false);
        activeWorkbenchWindow.setPerspectiveBarVisible(false);

        // hide ALL menus that we do not accpet
        MenuManager mm = activeWorkbenchWindow.getMenuManager();
        for (IContributionItem item : mm.getItems()) {
            if (item instanceof MenuManager) {
                for (IContributionItem innerItem : ((MenuManager) item).getItems()) {
                    if (!acceptedMenuContribs.contains(innerItem.getId())) {
                        innerItem.setVisible(false);
                    }
                    // System.out.println(innerItem.getId());
                }
            }

        }

    }
}
