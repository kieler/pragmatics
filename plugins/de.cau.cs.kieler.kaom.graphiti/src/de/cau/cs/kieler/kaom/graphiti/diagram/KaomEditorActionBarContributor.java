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
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * Contributes actions to the eclipse toolbar.
 * 
 * @author soh
 * @kieler.ignore (excluded from review process)
 */
public class KaomEditorActionBarContributor extends EditorActionBarContributor {

    @Override
    public void contributeToToolBar(final IToolBarManager manager) {
        super.contributeToToolBar(manager);

        String[] zoomStrings = new String[] { ZoomManager.FIT_ALL,
                ZoomManager.FIT_HEIGHT, ZoomManager.FIT_WIDTH };
        manager.add(new ZoomComboContributionItem(getPage(), zoomStrings));

    }
}
