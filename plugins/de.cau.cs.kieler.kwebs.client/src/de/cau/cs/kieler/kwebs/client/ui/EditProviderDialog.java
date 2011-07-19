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

import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;

/**
 * Dialog for editing an existing provider.
 *
 * @kieler.rating 2011-05-14 red
 * @author swe
 */
public class EditProviderDialog extends AbstractProviderDialog {

    /** The provider to be edited. */
    private Provider provider;

    /**
     * Creates a new dialog for editing a provider.
     * 
     * @param shell
     *            the parent shell
     * @param theprovider
     *            the provider to be edited
     */
    public EditProviderDialog(final Shell shell, final Provider theprovider) {
        super(shell);
        provider = theprovider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Edit Provider");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Control createDialogArea(final Composite parent) {
        Control result = super.createDialogArea(parent);
        providerName.setText(provider.getName());
        serviceAddress.setText(provider.getAddress());
        String truststoreValue = provider.getTruststore();
        if (truststoreValue != null) {
            truststore.setText(truststoreValue);
        }
        String truststorePassValue = provider.getTruststorePass();
        if (truststorePassValue != null) {
            truststorePass.setText(truststorePassValue);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean close() {
        return super.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void buttonPressed(final int buttonId) {
        if (buttonId == ACTION_OK) {
            Provider tmp = Providers.createProvider(getName(),
                getAddress(), getTruststore(), getTruststorePass()
            );
            if (!tmp.equals(provider) && Providers.isValidProvider(tmp)) {
                provider.setName(getName());
                provider.setAddress(getAddress());
                provider.setTruststore(getTruststore());
                provider.setTruststorePass(getTruststorePass());
            }
        }
        super.buttonPressed(buttonId);
    }
}
