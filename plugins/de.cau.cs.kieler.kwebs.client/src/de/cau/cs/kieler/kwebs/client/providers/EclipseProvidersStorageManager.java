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

package de.cau.cs.kieler.kwebs.client.providers;

import java.util.Vector;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kwebs.client.preferences.Preferences;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;

/**
 * This utility class provides convenient access to the list of
 * service providers configurable by the user.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public final class EclipseProvidersStorageManager implements IProvidersStorageManager {

    /** The preference store used to store the providers. */
    private IPreferenceStore preferenceStore
        = Preferences.getPreferenceStore();

    /** Preference store prefix of the name of a provider.*/
    private static final String NAME_PREFIX
        = Preferences.PREFID_LAYOUT_PROVIDER_NAMEPREFIX;

    /** Preference store prefix of the address of a provider.*/
    private static final String ADDRESS_PREFIX
        = Preferences.PREFID_LAYOUT_PROVIDER_ADDRESSPREFIX;

    /** Preference store prefix of the trust store of a provider.*/
    private static final String TRUSTSTORE_PREFIX
        = Preferences.PREFID_LAYOUT_PROVIDER_TRUSTSTOREPREFIX;

    /** Preference store prefix of the password for the trust store of a provider.*/
    private static final String TRUSTSTOREPASS_PREFIX
        = Preferences.PREFID_LAYOUT_PROVIDER_TRUSTSTOREPASSPREFIX;

    /**
     * {@inheritDoc}
     */
    public synchronized void readProviders() {
        int count = preferenceStore.getInt(
            Preferences.PREFID_LAYOUT_PROVIDER_COUNT
        );
        String name = null;
        String address = null;
        String truststore = null;
        String truststorePass = null;
        String currNamePrefix = null;
        String currAddressPrefix = null;
        String currTruststorePrefix = null;
        String currTruststorePassPrefix = null;
        for (int position = 0; position < count; position++) {
            currNamePrefix = NAME_PREFIX + "." + position;
            currAddressPrefix = ADDRESS_PREFIX + "." + position;
            currTruststorePrefix = TRUSTSTORE_PREFIX + "." + position;
            currTruststorePassPrefix = TRUSTSTOREPASS_PREFIX + "." + position;
            if (!preferenceStore.contains(currNamePrefix)
                || !preferenceStore.contains(currAddressPrefix)) {
                break;
            }
            name = preferenceStore.getString(currNamePrefix);
            address = preferenceStore.getString(currAddressPrefix);
            truststore = preferenceStore.getString(currTruststorePrefix);
            truststorePass = preferenceStore.getString(currTruststorePassPrefix);
            Providers.addProvider(Providers.createProvider(
                name, address, truststore, truststorePass
            ));
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void storeProviders(final Vector<Provider> providerList) {
        int count = providerList.size();
        preferenceStore.setValue(Preferences.PREFID_LAYOUT_PROVIDER_COUNT, count);
        Provider provider = null;
        for (int position = 0; position < count; position++) {
            provider = providerList.get(position);
            preferenceStore.setValue(
                NAME_PREFIX + "." + position,
                    provider.getName()
            );
            preferenceStore.setValue(
                ADDRESS_PREFIX + "." + position,
                    provider.getAddress()
            );
            if (provider.getTruststore() != null) {
                preferenceStore.setValue(
                    TRUSTSTORE_PREFIX + "." + position,
                        provider.getTruststore()
                );
            }
            if (provider.getTruststorePass() != null) {
                preferenceStore.setValue(
                    TRUSTSTOREPASS_PREFIX + "." + position,
                        provider.getTruststorePass()
                );
            }
        }
    }

}
