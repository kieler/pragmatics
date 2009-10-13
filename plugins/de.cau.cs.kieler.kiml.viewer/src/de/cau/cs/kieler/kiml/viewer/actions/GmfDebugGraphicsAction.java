/******************************************************************************
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
 * See the file LICENSE.txt or see http://www.eclipse.org/legal/ for the
 * license text.
 */
package de.cau.cs.kieler.kiml.viewer.actions;

import org.eclipse.jface.action.Action;

import de.cau.cs.kieler.kiml.viewer.KimlViewerPlugin;
import de.cau.cs.kieler.kiml.viewer.Messages;
import de.cau.cs.kieler.kiml.viewer.views.LayoutGraphView;

/**
 * Action to trigger the debug view to overlay the GMF Editor canvas with a view
 * of the actually layouted graph elements.
 * 
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public class GmfDebugGraphicsAction extends Action {

    /** Icon for the Action */
    private static final String ICON_PATH = "icons/overlay.gif";
    
    LayoutGraphView view;
    
    /**
     * @param layoutGraphView
     */
    public GmfDebugGraphicsAction(LayoutGraphView layoutGraphView) {
        view = layoutGraphView;
        
        setText(Messages.getString("Overlay"));
        setToolTipText(Messages.getString("Debug Layout: Overlay GMF Editor with layouted view."));
        setImageDescriptor(KimlViewerPlugin.imageDescriptorFromPlugin(
                KimlViewerPlugin.PLUGIN_ID, ICON_PATH));
    }

  
    @Override
    public void run() {
        super.run();
        view.getTransparentShell().run();
        
    }
    
}
