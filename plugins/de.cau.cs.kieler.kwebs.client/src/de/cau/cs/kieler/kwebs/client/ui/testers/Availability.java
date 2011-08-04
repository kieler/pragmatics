/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.ui.testers;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfigs.ServerConfigError;

/**
 * Utility class for testing availability of a server configuration with UI support.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public final class Availability {

    /**
     * Tests whether a given server configuration can be reached.
     * 
     * @param shell
     *            the parent shell for the dialog
     * @param serverConfig
     *            the server configuration to be tested
     */
    public static void checkAvailability(final Shell shell, final ServerConfig serverConfig) {  
        MessageBox box = new MessageBox(shell);
        ServerConfigError why = ServerConfigs.getInstance().isValidServerConfig(serverConfig); 
        if (why != ServerConfigError.ERROR_OK) {
            box.setText("Server Configuration invalid");
            String message = "The selected server configuration is invalid."
                             + " An availability test can not be performed."
                             + " The reason why this server configuration is not valid is:\n\n";
            if (why == ServerConfigError.ERROR_NOSERVERCONFIG) {
                message += "The server configuration object is not valid (is null).";
            } else if (why == ServerConfigError.ERROR_INVALIDNAME) {
                message += "The name of the server configuration is empty.";
            } else if (why == ServerConfigError.ERROR_INVALIDADDRESS) {
                message += "The address is empty or not correct.";
            } else if (why == ServerConfigError.ERROR_NOPROTOCOL) {
                message += "The address does not specify a protocol.";
            } else if (why == ServerConfigError.ERROR_PROTOCOLNOTSUPPORTED) {
                message += "The protocol is not supported."
                           + "\n\nPlease make sure that the appropriate plug-in for the protocol"
                           + " is available in your KIELER installation.";
            } else if (why == ServerConfigError.ERROR_NOTRUSTSTORE) {
                message += "You have specified a server configuration which is using a HTTPS based"
                           + " connection but the trust store for establishing the connection is"
                           + " missing.";
            } else if (why == ServerConfigError.ERROR_NOTRUSTSTOREPASS) {
                message += "You have specified a server configuration which is using a HTTPS based"
                            + " connection but the password for the trust store is missing.";
            }
            box.setMessage(message);
            box.open();
            return;
        }  
        ILayoutServiceClient client = LayoutServiceClients.getInstance().
            createClientForServerConfig(serverConfig);
        try {
            client.connect();
            box.setText("Server Configuration is reachable");
            box.setMessage("The server configuration you selected is reachable.");
        } catch (Exception e) {
            box.setText("Server Configuration is not reachable");
            box.setMessage("The server configuration you selected is not reachable at the moment.");
        }   
        box.open();
    }
    
    /**
     * Private constructor for utility class.
     */
    private Availability() {
    }

}
