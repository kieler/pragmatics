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

import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ServerConfigData;
import de.cau.cs.kieler.kwebs.client.ServerConfigs;

/**
 * Dialog for adding a new provider.
 *
 * @author swe
 */
public class NewServerConfigDialog extends AbstractServerConfigDialog {

    /**
     * Creates a dialog for adding a new server configuration.
     * 
     * @param shell
     *            the parent shell
     */
    public NewServerConfigDialog(final Shell shell) {
        super(shell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Add Server Configuration");
    }

    /**
     * {@inheritDoc}
     */
    protected void handleServerConfigUpdate(final ServerConfigData updatedServerConfig) {
        ServerConfigs.getInstance().addServerConfig(updatedServerConfig);        
    }

    /**
     * {@inheritDoc}
     */
    protected boolean warningOnDouble(final ServerConfigData theserverConfig) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    protected boolean warningOnInvalid(final ServerConfigData theserverConfig) {
        return true;
    }
    
}
