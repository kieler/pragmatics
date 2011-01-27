package de.cau.cs.kieler.rail.editor;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.part.EditorActionBarContributor;

public class RailEditorActionBarContributor extends EditorActionBarContributor {

    @Override
    public void contributeToToolBar(final IToolBarManager manager) {
        super.contributeToToolBar(manager);

        String[] zoomStrings =
                new String[] { ZoomManager.FIT_ALL, ZoomManager.FIT_HEIGHT,
                        ZoomManager.FIT_WIDTH };
        manager.add(new ZoomComboContributionItem(getPage(), zoomStrings));

    }
}
