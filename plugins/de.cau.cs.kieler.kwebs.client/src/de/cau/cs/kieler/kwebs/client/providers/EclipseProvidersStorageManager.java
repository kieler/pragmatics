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
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Extensions;

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

    /** Preference store prefix of the password for the trust store of a provider.*/
    private static final String FIXED_PREFIX
        = Preferences.PREFID_LAYOUT_PROVIDER_FIXEDPREFIX;

    /** */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.client.configuration";

    /** */
    private static final String ELEMENT_DEFAULTPROVIDER
        = "defaultProvider";

    /** */
    private static final String ATTRIBUTE_NAME
        = "name";

    /** */
    private static final String ATTRIBUTE_ADDRESS
        = "address";

    /** Default name of the KIELER layout server is configured via extension. */
    private static final String DEFAULT_NAME
        = Extensions.get(
            EXTENSIONPOINT_ID, ELEMENT_DEFAULTPROVIDER, ATTRIBUTE_NAME
        );

    /** Default address of the KIELER layout server is configured via extension. */
    private static final String DEFAULT_ADDRESS 
        = Extensions.get(
            EXTENSIONPOINT_ID, ELEMENT_DEFAULTPROVIDER, ATTRIBUTE_ADDRESS
        );

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
        boolean fixed = false;
        String currNamePrefix = null;
        String currAddressPrefix = null;
        String currTruststorePrefix = null;
        String currTruststorePassPrefix = null;
        String currFixedPrefix = null;
        Provider tmpProvider = null;
        for (int position = 0; position < count; position++) {
            currNamePrefix = NAME_PREFIX + "." + position;
            currAddressPrefix = ADDRESS_PREFIX + "." + position;
            currTruststorePrefix = TRUSTSTORE_PREFIX + "." + position;
            currTruststorePassPrefix = TRUSTSTOREPASS_PREFIX + "." + position;
            currFixedPrefix = FIXED_PREFIX + "." + position;
            if (!preferenceStore.contains(currNamePrefix)
                || !preferenceStore.contains(currAddressPrefix)) {
                break;
            }
            name = preferenceStore.getString(currNamePrefix);
            address = preferenceStore.getString(currAddressPrefix);
            truststore = preferenceStore.getString(currTruststorePrefix);
            // Dont use default value "" but null in order to
            // be compatible with the provider model
            if (truststore.length() == 0) {
                truststore = null;
            }
            // Dont use default value "" but null in order to
            // be compatible with the provider model
            truststorePass = preferenceStore.getString(currTruststorePassPrefix);
            if (truststorePass.length() == 0) {
                truststorePass = null;
            }
            fixed = preferenceStore.getBoolean(currFixedPrefix);
            tmpProvider = Providers.createProvider(
                name, address, truststore, truststorePass, fixed
            );
            Providers.addProvider(tmpProvider);
            Logger.log(Severity.DEBUG, "Read provider from store: " + tmpProvider);
        }
        // Check if KIELER default layout provider is in the provider list.
        // If not, insert it.
        Provider defaultProvider = Providers.createProvider(
            DEFAULT_NAME, DEFAULT_ADDRESS, null, null, true
        );
        if (!Providers.containsProvider(defaultProvider)) {
            Logger.log(Severity.DEBUG, "Adding default provider to the provider list");
            Providers.addProvider(defaultProvider);
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void storeProviders(final Vector<Provider> providerList) {
        int count = providerList.size();
        preferenceStore.setValue(Preferences.PREFID_LAYOUT_PROVIDER_COUNT, count);
        Provider tmpProvider = null;
        for (int position = 0; position < count; position++) {
            tmpProvider = providerList.get(position);
            Logger.log(Severity.DEBUG, "Storing provider: " + tmpProvider);
            preferenceStore.setValue(
                NAME_PREFIX + "." + position,
                    tmpProvider.getName()
            );
            preferenceStore.setValue(
                ADDRESS_PREFIX + "." + position,
                    tmpProvider.getAddress()
            );
            if (tmpProvider.getTruststore() != null) {
                preferenceStore.setValue(
                    TRUSTSTORE_PREFIX + "." + position,
                        tmpProvider.getTruststore()
                );
            }
            if (tmpProvider.getTruststorePass() != null) {
                preferenceStore.setValue(
                    TRUSTSTOREPASS_PREFIX + "." + position,
                        tmpProvider.getTruststorePass()
                );
            }
            preferenceStore.setValue(
                FIXED_PREFIX + "." + position, 
                    tmpProvider.isFixed()
            );
        }
    }

}
