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

import java.net.URI;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kwebs.client.activator.Activator;
import de.cau.cs.kieler.kwebs.client.preferences.Preferences;

/**
 * This utility class provides convenient access to the list of
 * server configuration configurable by the user.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public final class EclipseServerConfigsStorageManager implements IServerConfigsStorageManager {

    /** The preference store used to store the server configuration. */
    private IPreferenceStore preferenceStore
        = Preferences.getPreferenceStore();

    /** Preference store prefix of the name of a server configuration.*/
    private static final String NAME_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_NAMEPREFIX;

    /** Preference store prefix of the address of a server configuration.*/
    private static final String ADDRESS_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_ADDRESSPREFIX;

    /** Preference store prefix of the trust store of a server configuration.*/
    private static final String TRUSTSTORE_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_TRUSTSTOREPREFIX;

    /** Preference store prefix of the password for the trust store of a server configuration.*/
    private static final String TRUSTSTOREPASS_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_TRUSTSTOREPASSPREFIX;

    /** Preference store prefix for the fixed property of a server configuration.*/
    private static final String FIXED_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_FIXEDPREFIX;

    /** Preference store prefix for the active property of a server configuration.*/
    private static final String ACTIVE_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_ACTIVEPREFIX;

    /** Preference store prefix for the standard property of a server configuration.*/
    private static final String STANDARD_PREFIX
        = Preferences.PREFID_LAYOUT_SERVERCONFIG_STANDARDPREFIX;

    /** ID of the extension point used to configure default server configuration. */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.client.configuration";

    /** Name of the 'defaultServerConfig' element. */
    private static final String ELEMENT_DEFAULTSERVERCONFIG
        = "defaultServerConfig";

    /** Name of the 'name' attribute. */
    private static final String ATTRIBUTE_NAME
        = "name";

    /** Name of the 'address' attribute. */
    private static final String ATTRIBUTE_ADDRESS
        = "address";

    /** Name of the 'isStandard' attribute. */
    private static final String ATTRIBUTE_ISSTANDARD
        = "isStandard";

    /**
     * {@inheritDoc}
     */
    public synchronized void readServerConfigs() {
        int count = preferenceStore.getInt(
            Preferences.PREFID_LAYOUT_SERVERCONFIG_COUNT
        );
        String name = null;
        String address = null;
        String truststore = null;
        String truststorePass = null;
        boolean fixed = false;
        boolean active = false;
        boolean standard = false;
        String currNamePrefix = null;
        String currAddressPrefix = null;
        String currTruststorePrefix = null;
        String currTruststorePassPrefix = null;
        String currFixedPrefix = null;
        String currActivePrefix = null;
        String currStandardPrefix = null;
        ServerConfig tmpServerConfig = null;
        for (int position = 0; position < count; position++) {
            currNamePrefix = NAME_PREFIX + "." + position;
            currAddressPrefix = ADDRESS_PREFIX + "." + position;
            currTruststorePrefix = TRUSTSTORE_PREFIX + "." + position;
            currTruststorePassPrefix = TRUSTSTOREPASS_PREFIX + "." + position;
            currFixedPrefix = FIXED_PREFIX + "." + position;
            currActivePrefix = ACTIVE_PREFIX + "." + position;
            currStandardPrefix = STANDARD_PREFIX + "." + position;
            if (!preferenceStore.contains(currNamePrefix)
                || !preferenceStore.contains(currAddressPrefix)) {
                break;
            }
            name = preferenceStore.getString(currNamePrefix);
            address = preferenceStore.getString(currAddressPrefix);
            truststore = preferenceStore.getString(currTruststorePrefix);
            // Do not use default value "" but null in order to
            // be compatible with the server configuration model
            if (truststore.length() == 0) {
                truststore = null;
            }
            // Do not use default value "" but null in order to
            // be compatible with the server configuration model
            truststorePass = preferenceStore.getString(currTruststorePassPrefix);
            if (truststorePass.length() == 0) {
                truststorePass = null;
            }
            fixed = preferenceStore.getBoolean(currFixedPrefix);
            active = preferenceStore.getBoolean(currActivePrefix);
            standard = preferenceStore.getBoolean(currStandardPrefix);
            URI uri = null;
            try {
                uri = new URI(address);
            } catch (Exception e) {
                StatusManager.getManager().handle(
                    new Status(
                        IStatus.WARNING, 
                        Activator.PLUGIN_ID, 
                        "Could not create URI from address: " + address, 
                        e
                    )
                );
            }
            tmpServerConfig = ServerConfigs.getInstance().createServerConfig(
                name, uri, truststore, truststorePass, fixed, active, standard
            );
            ServerConfigs.getInstance().addServerConfig(tmpServerConfig);
        }
        // Check if KIELER default layout server configurations are in the server configuration list.
        // If not, insert them.
        IConfigurationElement[] extensions = Platform.getExtensionRegistry().
            getConfigurationElementsFor(EXTENSIONPOINT_ID);
        for (IConfigurationElement element : extensions) {
            if (element.getName().equals(ELEMENT_DEFAULTSERVERCONFIG)) {
                String serverConfigName = element.getAttribute(ATTRIBUTE_NAME);
                String serverConfigAddress = element.getAttribute(ATTRIBUTE_ADDRESS);
                URI uri = null;                
                try {
                    uri = new URI(serverConfigAddress); 
                } catch (Exception e) {
                    // Ignore here, null URI will be handled in call to createServerConfig()
                }
                boolean isStandard = Boolean.parseBoolean(element.getAttribute(ATTRIBUTE_ISSTANDARD));
                ServerConfig defaultServerConfig = ServerConfigs.getInstance().createServerConfig(
                    serverConfigName, uri, null, null, true, false, isStandard
                );
                if (defaultServerConfig != null) {
                    if (!ServerConfigs.getInstance().containsServerConfig(defaultServerConfig)) {
                        ServerConfigs.getInstance().addServerConfig(defaultServerConfig);
                    }
                }
            }
        }
        // If the user has not selected an active server configuration yet, set the standard 
        // server configuration as active server configuration.
        if (ServerConfigs.getInstance().getActiveServerConfig() == null) {
            ServerConfigs.getInstance().setActiveServerConfig(
                ServerConfigs.getInstance().getStandardServerConfig()
            );
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void storeServerConfigs(final Vector<ServerConfig> serverConfigList) {
        int count = serverConfigList.size();
        preferenceStore.setValue(Preferences.PREFID_LAYOUT_SERVERCONFIG_COUNT, count);
        ServerConfig tmpServerConfig = null;
        for (int position = 0; position < count; position++) {
            tmpServerConfig = serverConfigList.get(position);
            preferenceStore.setValue(
                NAME_PREFIX + "." + position,
                tmpServerConfig.getName()
            );
            preferenceStore.setValue(
                ADDRESS_PREFIX + "." + position,
                tmpServerConfig.getAddress().toString()
            );
            if (tmpServerConfig.getTruststore() != null) {
                preferenceStore.setValue(
                    TRUSTSTORE_PREFIX + "." + position,
                    tmpServerConfig.getTruststore()
                );
            }
            if (tmpServerConfig.getTruststorePass() != null) {
                preferenceStore.setValue(
                    TRUSTSTOREPASS_PREFIX + "." + position,
                    tmpServerConfig.getTruststorePass()
                );
            }
            preferenceStore.setValue(
                FIXED_PREFIX + "." + position, 
                tmpServerConfig.isFixed()
            );
            preferenceStore.setValue(
                ACTIVE_PREFIX + "." + position, 
                tmpServerConfig.isActive()
            );
            preferenceStore.setValue(
                STANDARD_PREFIX + "." + position, 
                tmpServerConfig.isStandard()
            );
        }
    }

}
