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

package de.cau.cs.kieler.kwebs.client.kiml.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfig;
import de.cau.cs.kieler.kwebs.client.kiml.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs.ServerConfigError;

/**.
 *
 * @kieler.rating 2011-05-14
 * @author swe
 */
public class CheckAvailabilityJob extends Job {

    /** The parent shell. */
    private Shell parentShell;
    
    /** The server configuration to be tested. */
    private ServerConfig serverConfig;
    
    /**
     * Creates a Job for testing server availability.
     * 
     * @param theparentShell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to be tested
     */
    public CheckAvailabilityJob(final Shell theparentShell, final ServerConfig theserverConfig) {
        super("Check Layout Server Availability");
        parentShell = theparentShell;
        serverConfig = theserverConfig;
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
        ServerConfigError why = ServerConfigs.getInstance().
            isValidServerConfig(serverConfig);
        ILayoutServiceClient client = null;
        String message = null;
        if (why == ServerConfigError.ERROR_OK) {
            client = LayoutServiceClients.getInstance().
                createClientForServerConfig(serverConfig);
            try {
                client.connect();
                message = "The layout service you selected is reachable.";
            } catch (Exception e) {
                String[] errorLines = client.getLastErrorAsStringArray();
                message = "The layout service you selected is not reachable.";                                            
                message += "\n\nThe error occurred was:\n\n"; 
                if (errorLines != null) {                            
                    for (String errorLine : errorLines) {
                        message += errorLine.trim() + "\n";
                    }
                } else {
                    message += "\n\nNo error description available.";
                }    
            } finally {
                client.disconnect();
            }
        } else {
            message = "The selected server configuration is invalid."
                      + " An availability test can not be performed.";                                        
            message += "\n\nThe reason why this server configuration is not valid is:\n\n";
            if (why == ServerConfigError.ERROR_NOSERVERCONFIG) {
                message += "The server configuration is missing a name or an address.";
            } else if (why == ServerConfigError.ERROR_INVALIDNAME) {
                message += "The name of the server configuration is empty.";
            } else if (why == ServerConfigError.ERROR_INVALIDADDRESS) {
                message += "The address is empty or not correct.";
            } else if (why == ServerConfigError.ERROR_NOPROTOCOL) {
                message += "The address does not specify a protocol.";
            } else if (why == ServerConfigError.ERROR_PROTOCOLNOTSUPPORTED) {
                message += "The protocol is not supported."
                        + "\n\nPlease make sure that the appropriate plug-in"
                        + " for the protocol is available in your KIELER installation.";
            } else if (why == ServerConfigError.ERROR_NOTRUSTSTORE) {
                message += "You have specified a server configuration which is using"
                        + " a HTTPS based connection but the trust store for"
                        + " establishing the connection is missing.";
            } else if (why == ServerConfigError.ERROR_NOTRUSTSTOREPASS) {
                message += "You have specified a server configuration which is using"
                        + " a HTTPS based connection but the password for the"
                        + " trust store is missing.";
            } else {
                message += "An unspecified error occured.";
            }
        }
        final String dummy = message;
        Display.getDefault().syncExec(
            new Runnable() {
                public void run() {
                    MessageBox box = new MessageBox(parentShell, SWT.OK);
                    box.setText("Result");
                    box.setMessage(dummy);
                    box.open();
                }
            }
        );
        return Status.OK_STATUS;
    }
    
}
