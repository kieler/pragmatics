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
package de.cau.cs.kieler.kiml.ui.views;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * A command handler for displaying the layout view.
 *
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class ShowLayoutViewHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow workbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
        if (workbenchWindow != null) {
            try {
                workbenchWindow.getActivePage().showView(LayoutViewPart.VIEW_ID);
            } catch (PartInitException exception) {
                IStatus status = new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID,
                        "Could not open Layout View.", exception);
                StatusManager.getManager().handle(status, StatusManager.SHOW);
            }
        }
        return null;
    }

}
