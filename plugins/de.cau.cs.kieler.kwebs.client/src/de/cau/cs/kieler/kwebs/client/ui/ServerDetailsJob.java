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

import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfigData;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.transformation.ServiceDataXmiTransformer;

/**
 * This job tests layout service availability and displays the layout servers capabilities
 * afterwards or an error message if the service is not available.
 *
 * @kieler.rating 2011-05-14
 * 
 * @author swe
 */
public class ServerDetailsJob extends AbstractServerBasedJob {

    /**
     * Creates a Job for testing server availability.
     * 
     * @param theparentShell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to be tested
     */
    public ServerDetailsJob(final Shell theparentShell, final ServerConfigData theserverConfig) {
        super("Service Details", theparentShell, theserverConfig);
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
        String serviceDataXMI = null;
        ServiceData serviceData = null;
        try {
            serviceDataXMI = client.getServiceData();
            serviceData = new ServiceDataXmiTransformer().deserialize(serviceDataXMI);
        } catch (Exception e) {
            super.processError(e);
            return;
        }                
        final String html = ServerDetailsPage.generateHtml(serviceData, client);
        //final URI uri = client.getServerConfig().getAddress();
        Display.getDefault().syncExec(
            new Runnable() { public void run() { 
                new BrowserDialog(
                    getShell(), 
                    html,
                    null,//uri.getScheme() + "://" + uri.getAuthority() + "/ServiceData.html",
                    "Server Details",
                    new Rectangle(0, 0, 500, 450)
                ).open(); 
            }; }
        );
        
    }

    /**
     * {@inheritDoc}
     */
    protected void unavailable(final ILayoutServiceClient client, final String message) {
        processMessage("Layout Server is not available", message);
    }
    
}
