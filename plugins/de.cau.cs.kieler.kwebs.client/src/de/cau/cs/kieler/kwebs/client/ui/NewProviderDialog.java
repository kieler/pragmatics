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

import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;

/**
 * Dialog for adding a new provider.
 *
 * @kieler.rating 2011-05-14 red
 * @author swe
 */
public class NewProviderDialog extends AbstractProviderDialog {

    /**
     * Creates a dialog for adding a new provider.
     * 
     * @param shell
     *            the parent shell
     */
    public NewProviderDialog(final Shell shell) {
        super(shell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Add Provider");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void buttonPressed(final int buttonId) {
        if (buttonId == ACTION_OK) {
            Provider tmp = Providers.createProvider(
                getName(), getAddress(), getTruststore(), getTruststorePass()
            );
            if (Providers.isValidProvider(tmp)) {
                Providers.addProvider(tmp);
            }
        }
        super.buttonPressed(buttonId);
    }
    
}
