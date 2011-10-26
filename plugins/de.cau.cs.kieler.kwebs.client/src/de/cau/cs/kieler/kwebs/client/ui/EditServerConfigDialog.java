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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ServerConfigData;

/**
 * Dialog for editing an existing server configuration.
 *
 * @kieler.rating 2011-05-14 red
 * @author swe
 */
public class EditServerConfigDialog extends AbstractServerConfigDialog {

    /** The server configuration to be edited. */
    private ServerConfigData serverConfig;

    /**
     * Creates a new dialog for editing a server configuration.
     * 
     * @param shell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to be edited
     */
    public EditServerConfigDialog(final Shell shell, final ServerConfigData theserverConfig) {
        super(shell);
        serverConfig = theserverConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Edit Server Configuration");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Control createDialogArea(final Composite parent) {
        Control result = super.createDialogArea(parent);
        serverConfigName.setText(serverConfig.getName());
        serverConfigAddress.setText(serverConfig.getAddress().toString());
        String truststoreValue = serverConfig.getTruststore();
        if (truststoreValue != null) {
            truststore.setText(truststoreValue);
        }
        String truststorePassValue = serverConfig.getTruststorePass();
        if (truststorePassValue != null) {
            truststorePass.setText(truststorePassValue);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    protected void handleServerConfigUpdate(final ServerConfigData updatedServerConfig) {
        if (!updatedServerConfig.equals(serverConfig)) {
            serverConfig.setName(updatedServerConfig.getName());
            serverConfig.setAddress(updatedServerConfig.getAddress());
            serverConfig.setTruststore(updatedServerConfig.getTruststore());
            serverConfig.setTruststorePass(updatedServerConfig.getTruststorePass());
        }
    }

    /**
     * {@inheritDoc}
     */
    protected boolean warningOnDouble(final ServerConfigData theserverConfig) {
        return (theserverConfig != serverConfig);
    }

    /**
     * {@inheritDoc}
     */
    protected boolean warningOnInvalid(final ServerConfigData theserverConfig) {
        return true;
    }
    
}
