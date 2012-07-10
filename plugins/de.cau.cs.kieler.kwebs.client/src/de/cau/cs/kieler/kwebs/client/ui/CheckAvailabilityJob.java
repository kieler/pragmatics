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

package de.cau.cs.kieler.kwebs.client.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfigData;

/**
 * This job tests layout service availability and displays a status dialog afterwards.
 *
 * @author swe
 */
public class CheckAvailabilityJob extends AbstractServerBasedJob {

    /**
     * Creates a Job for testing server availability.
     * 
     * @param theparentShell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to be tested
     */
    public CheckAvailabilityJob(final Shell theparentShell, final ServerConfigData theserverConfig) {
        super("Check Layout Server Availability", theparentShell, theserverConfig);
    }
    
    /**
     * Runs the job and checks for service availability. Display a message box with the result
     * of the test.
     * 
     * @param monitor
     *             the progress monitor used
     * @return the status
     */
    protected IStatus run(final IProgressMonitor monitor) {
        super.checkAvailability();
        return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    protected void available(final ILayoutServiceClient client, final String message) {
        processMessage("Layout Server is available", message);
    }

    /**
     * {@inheritDoc}
     */
    protected void unavailable(final ILayoutServiceClient client, final String message) {
        processMessage("Layout Server is not available", message);
    }
    
}
