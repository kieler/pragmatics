/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.service.component.annotations.Component;

import de.cau.cs.kieler.klighd.IStatusManager;

/**
 * @author nre
 *
 */
@Component
public class EclipseStatusManager implements IStatusManager {

    /**
     * Handles the status by using the default Eclipse UI status manager.
     */
    @Override
    public void handle(IStatus status) {
        StatusManager.getManager().handle(status);
    }

}
