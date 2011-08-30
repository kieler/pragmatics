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

/**
 * This is an abstract job with the support for testing the servers availability.
 *  
 * @kieler.rating 2011-05-14
 * 
 * @author swe
 */
public abstract class AbstractServerBasedJob extends Job {

    /** The parent shell. */
    private Shell parentShell;
    
    /** The server configuration to be used. */
    private ServerConfig serverConfig;
    
    /**
     * Creates a Job.
     *
     * @param jobName
     *            the name of the job
     * @param theparentShell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to be tested
     */
    protected AbstractServerBasedJob(final String jobName, final Shell theparentShell, 
        final ServerConfig theserverConfig) {
        super(jobName);
        parentShell = theparentShell;
        serverConfig = theserverConfig;
    }
    
    /**
     * Returns the shell used for this job.
     * 
     * @return the shell used for this job
     */
    protected Shell getShell() {
        return parentShell;
    }
    
    /**
     * Returns the server configuration used for this job.
     * 
     * @return the server configuration used for this job
     */
    protected ServerConfig getServerConfig() {
        return serverConfig;
    }
    
    /**
     * Runs the job and checks for service availability. Display a message box with the result
     * of the test.
     * 
     * @param monitor
     *             the progress monitor used
     * @return whether the service is available
     */
    protected boolean checkAvailability() {
        ServerConfigError why = ServerConfigs.getInstance().
            isValidServerConfig(serverConfig);
        ILayoutServiceClient client = null;
        boolean available = false;
        String message = null;
        if (why == ServerConfigError.ERROR_OK) {
            client = LayoutServiceClients.getInstance().
                createClientForServerConfig(serverConfig);
            try {
                client.connect();
                available = true;
                message = "The layout service you selected is reachable.";
                available(client, message);
            } catch (Exception e) { e.printStackTrace();
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
                unavailable(client, message);
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
            unavailable(client, message);
        }
        return available;
    }
    
    /**
     * Can be used by sub classes to display errors occurred during execution.
     * 
     * @param throwable
     *            the throwable occurred
     */
    protected void processError(final Throwable throwable) {
        String message = throwable.getMessage();
        if (message == null) {
            message = "An undefined error occurred.";
        }        
        processMessage("An Error occurred.", message);                
    }

    /**
     * Can be used by sub classes to display messages during execution.
     * 
     * @param title
     *            the title of the message
     * @param message
     *            the message to display
     */
    protected void processMessage(final String title, final String message) {
        Display.getDefault().syncExec(
                new Runnable() {
                    public void run() {
                        MessageBox box = new MessageBox(getShell(), SWT.OK);
                        box.setText(title);
                        box.setMessage(message);
                        box.open();
                    }
                }
            );        
    }
    
    /**
     * Called by this job if the server is available.
     * 
     * @param client
     *            the layout service client used
     * @param message
     *            detailed message
     */
    protected abstract void available(final ILayoutServiceClient client, final String message);

    /**
     * Called by this job if the server is not available. The client is not safe to use, it may have
     * been disconnected. Sub classes shall not try to reconnect to the service with it.
     * 
     * @param client
     *            the layout service client used
     * @param message
     *            detailed message
     */
    protected abstract void unavailable(final ILayoutServiceClient client, final String message);
    
}
