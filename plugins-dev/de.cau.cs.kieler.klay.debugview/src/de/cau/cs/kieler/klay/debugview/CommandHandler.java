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
package de.cau.cs.kieler.klay.debugview;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

/**
 * Handler for the commands defined by this plug-in.
 * 
 * @author cds
 */
public class CommandHandler extends AbstractHandler {
    
    /** Command ID of the Open command. */
    private static final String ID_OPEN = "de.cau.cs.kieler.klay.debugview.open"; //$NON-NLS-1$
    

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        if (event.getCommand().getId().equals(ID_OPEN)) {
            DebugWindow window = new DebugWindow(
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
            window.open();
        }
        
        return null;
    }

}
