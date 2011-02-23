/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * Contribute zoom actions to the action bar.
 * 
 * @author soh
 */
public class RailEditorActionBarContributor extends EditorActionBarContributor {

    @Override
    public final void contributeToToolBar(final IToolBarManager manager) {
        super.contributeToToolBar(manager);

        String[] zoomStrings =
                new String[] {ZoomManager.FIT_ALL, ZoomManager.FIT_HEIGHT,
                        ZoomManager.FIT_WIDTH };
        manager.add(new ZoomComboContributionItem(getPage(), zoomStrings));

    }
}
