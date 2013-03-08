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
package de.cau.cs.kieler.klay.debugview.views;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.DirectoryDialog;

import de.cau.cs.kieler.klay.debugview.KlayDebugViewPlugin;
import de.cau.cs.kieler.klay.debugview.Messages;

/**
 * 
 * 
 * @author cds
 */
public class OpenFolderAction extends Action {
    /**
     * The action's ID.
     */
    private static final String ACTION_ID = "de.cau.cs.kieler.klay.debugview.openFolder";
    
    /**
     * The debug view this action was created for.
     */
    private DebugView debugView = null;
    
    
    /**
     * Creates a new instance for the given debug view.
     * 
     * @param debugView the debug view that this action is to operate on.
     */
    public OpenFolderAction(final DebugView debugView) {
        this.debugView = debugView;
        
        setId(ACTION_ID);
        setToolTipText(Messages.DebugWindow_Toolbar_BrowseFolder_ToolTip);
        setImageDescriptor(KlayDebugViewPlugin.imageDescriptorFromPlugin(
                KlayDebugViewPlugin.PLUGIN_ID,
                "images/open.png"));
    }
    

    @Override
    public void run() {
        DirectoryDialog dialog = new DirectoryDialog(debugView.getSite().getShell());
        dialog.setMessage(Messages.DebugWindow_PathDialog_Message);
        dialog.setFilterPath(debugView.getCurrentPath());
        
        String newPath = dialog.open();
        if (newPath != null) {
            debugView.setPath(newPath);
        }
    }
}
