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

package de.cau.cs.kieler.kwebs.client.kiml;

import java.net.URI;
import java.util.Vector;

import de.cau.cs.kieler.kwebs.client.ServerConfig;

/**
 * This utility class provides convenient access to the list of
 * server configuration configurable by the user.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public final class ServerConfigs {

    /** The singleton.  */
    private static final ServerConfigs INSTANCE
        = new ServerConfigs();

    /** The list of available server configuration. */
    private Vector<ServerConfig> serverConfigList
        = new Vector<ServerConfig>();

    /** The optional storage manager. */
    private static IServerConfigsStorageManager storageManager;

    /** Whether the server configuration list has been changed. */
    private boolean isDirty
        = false;

    /**
     * Private constructor.
     */
    private ServerConfigs() {
    }

    /**
     * Get the singleton instance.
     *
     * @return the singleton instance
     */
    public static ServerConfigs getInstance() {
        return INSTANCE;
    }

    /**
     * Registers a storage manager.
     *
     * @param manager
     *            the storage manager to be registered
     */
    public void registerStorageManager(final IServerConfigsStorageManager manager) {
        storageManager = manager;
    }

    /**
     * Unregisters the storage manager.
     */
    public void unregisterStorageManager() {
        storageManager = null;
    }

    /**
     * Clears the server configuration list.
     */
    public synchronized void clear() {
        if (serverConfigList.size() > 0) {
            serverConfigList.clear();
            isDirty = true;
        }
    }

    /**
     * This method uses a registered storage manager to read a server configuration list.
     */
    public synchronized void read() {
        if (storageManager != null) {
            clear();
            storageManager.readServerConfigs();
            setDirty(false);
        }
    }

    /**
     * This method uses a registered storage manager to store a server configuration list.
     */
    public synchronized void store() {
        if (storageManager != null) {
            storageManager.storeServerConfigs(serverConfigList); //FIXME clone it!
            setDirty(false);
        }
    }

    /**
     * Error constants for the validity test on a server configuration.
     *
     * @author swe
     *
     */
    public enum ServerConfigError {
        /** Error constant for a valid server configuration. */
        ERROR_OK,
        /** Error constant for an invalid server configuration name. */
        ERROR_NOSERVERCONFIG,
        /** Error constant for an invalid server configuration name. */
        ERROR_INVALIDNAME,
        /** Error constant for an invalid server configuration address. */
        ERROR_INVALIDADDRESS,
        /** Error constant for no trust store given on HTTPS based server configuration. */
        ERROR_NOTRUSTSTORE,
        /** Error constant for no trust store password given on HTTPS based server configuration. */
        ERROR_NOTRUSTSTOREPASS,
        /** Error constant for no protocol defined in server configuration address. */
        ERROR_NOPROTOCOL,
        /** Error constant for an unsupported protocol in server configuration address. */
        ERROR_PROTOCOLNOTSUPPORTED
    }

    /**
     * Checks whether the given server configuration is valid, e.g. name and URI is valid
     * and if the URI is HTTPS based whether the trust store parameters are valid.
     *
     * @param serverConfig
     *            the server configuration to be tested
     * @return whether the server configuration is valid
     */
    public ServerConfigError isValidServerConfig(final ServerConfig serverConfig) {
        if (serverConfig == null) {
            return ServerConfigError.ERROR_NOSERVERCONFIG;
        }
        String name = serverConfig.getName();
        if (name == null || name.length() == 0) {
            return ServerConfigError.ERROR_INVALIDNAME;
        }
        URI address = serverConfig.getAddress();
        if (address == null) {
            return ServerConfigError.ERROR_INVALIDADDRESS;
        }
        String scheme = address.getScheme();
        if (scheme == null) {
            return ServerConfigError.ERROR_NOPROTOCOL;
        }
        if (!LayoutServiceClients.getInstance().isServerConfigSupported(serverConfig)) {
            return ServerConfigError.ERROR_PROTOCOLNOTSUPPORTED;
        }
        if (scheme.toLowerCase().startsWith("https:")) {
            String truststore = serverConfig.getTruststore();
            if (truststore == null || truststore.length() == 0) {
                return ServerConfigError.ERROR_NOTRUSTSTORE;
            }
            String truststorePass = serverConfig.getTruststorePass();
            if (truststorePass == null || truststorePass.length() == 0) {
                return ServerConfigError.ERROR_NOTRUSTSTOREPASS;
            }
        }
        return ServerConfigError.ERROR_OK;
    }

    /**
     * Creates a new server configuration.
     *
     * @param name
     *            the name of the server configuration
     * @param address
     *            the address of the layout service
     * @return the newly created server configuration or {@code null} if the provided data is not valid
     */
    public ServerConfig createServerConfig(final String name, final URI address) {
        return createServerConfig(name, address, null, null, false, false, false);
    }

    /**
     * Creates a new server configuration.
     *
     * @param name
     *            the name of the server configuration
     * @param address
     *            the address of the layout service
     * @param truststore
     *            path to the trust store when using HTTPS
     * @param truststorepass
     *            password for the trust store
     * @return the newly created server configuration or {@code null} if the provided data is not valid
     */
    public ServerConfig createServerConfig(final String name, final URI address,
        final String truststore, final String truststorepass) {
        return createServerConfig(name, address, truststore, truststorepass, false, false, false);
    }

    /**
     * Creates a new server configuration.
     *
     * @param name
     *            the name of the server configuration
     * @param address
     *            the address of the layout service
     * @param truststore
     *            path to the trust store when using HTTPS
     * @param truststorepass
     *            password for the trust store
     * @param isFixed
     *            whether this server configuration can be altered or deleted from the 
     *            server configuration list
     * @param isActive
     *           whether this server configuration shall be the currently active 
     *           server configuration for doing remote layout
     * @param isStandard
     *           whether this server configuration shall be the standard server configuration for
     *           doing remote layout
     * @return the newly created server configuration or {@code null} if the provided data is not valid
     */
    public ServerConfig createServerConfig(final String name, final URI address,
        final String truststore, final String truststorepass, final boolean isFixed,
        final boolean isActive, final boolean isStandard) {
        ServerConfig serverConfig = null;
        if (name != null && name.length() > 0 && address != null) {
            serverConfig = new ServerConfig(
                name, address, truststore, truststorepass, isFixed, isActive, isStandard
            );
        }
        return serverConfig;
    }

    /**
     * Adds a server configuration to the server configuration list.
     *
     * @param serverConfig
     *            the server configuration to be added
     */
    public synchronized void addServerConfig(final ServerConfig serverConfig) {
        //if (!providerList.contains(provider)) {
            serverConfigList.add(serverConfig);
            isDirty = true;
        //}
    }

    /**
     * Removes a server configuration from the server configuration list.
     *
     * @param serverConfig
     *            the server configuration to be removed
     */
    public synchronized void removeServerConfig(final ServerConfig serverConfig) {
        if (!serverConfig.isFixed()) {
            if (serverConfigList.contains(serverConfig)) {
                serverConfigList.remove(serverConfig);
                isDirty = true;
            }
        }
    }

    /**
     * Returns a server configuration equal to the given server configuration.
     *
     * @param serverConfig
     *            the server configuration to be compared
     * @return a server configuration equal to the given server configuration
     *         or {@code null} if no equal server configuration is in the server 
     *         configuration list
     */
    public synchronized ServerConfig findServerConfig(final ServerConfig serverConfig) {
        for (int position = 0; position < serverConfigList.size(); position++) {
            ServerConfig tmpProvider = serverConfigList.get(position);
            if (tmpProvider.equals(serverConfig)) {
                return tmpProvider;
            }
        }
        return null;
    }

    /**
     * Returns whether a given server configuration is already contained in the 
     * server configuration list.
     *
     * @param serverConfig
     *            the server configuration to be searched for
     * @return whether a given server configuration is already contained in the
     *         server configuration list
     */
    public synchronized boolean containsServerConfig(final ServerConfig serverConfig) {
        for (int position = 0; position < serverConfigList.size(); position++) {
            if (serverConfigList.get(position).equals(serverConfig)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of a given server configuration in the server configuration list.
     *
     * @param serverConfig
     *            the server configuration to determine the index for
     * @return the index or {@code -1} if the server configuration is not contained 
     *         in the server configuration list
     */
    public synchronized int getIndexByServerConfig(final ServerConfig serverConfig) {
        for (int position = 0; position < serverConfigList.size(); position++) {
            if (serverConfigList.get(position) == serverConfig) {
                return position;
            }
        }
        return -1;
    }

    /**
     * Returns a server configuration from the server configuration list by the given index.
     *
     * @param index
     *            the index of the server configuration
     * @return the server configuration or {@code null} if the index is invalid
     */
    public synchronized ServerConfig getServerConfigByIndex(final int index) {
        ServerConfig serverConfig = null;
        if (index >= 0 && index < serverConfigList.size()) {
            serverConfig = serverConfigList.get(index);
        }
        return serverConfig;
    }

    /**
     * Returns the currently active server configuration.
     *
     * @return the currently active server configuration or {@code null} if no 
     *         server configuration is currently active
     */
    public synchronized ServerConfig getActiveServerConfig() {
        ServerConfig activeServerConfig = null;
        for (ServerConfig serverConfig : serverConfigList) {
            if (serverConfig.isActive()) {
                activeServerConfig = serverConfig;
                break;
            }
        }
        return activeServerConfig;
    }

    /**
     * Sets the currently active server configuration.
     *
     * @param activeServerConfig
     *            the server configuration which shall be the actively used server configuration
     *            for remote layout
     */
    public synchronized void setActiveServerConfig(final ServerConfig activeServerConfig) {
        if (activeServerConfig != null) {
            if (activeServerConfig.isActive()) {
                return;
            }
            for (ServerConfig serverConfig : serverConfigList) {
                serverConfig.setActive(false);
                if (serverConfig == activeServerConfig) {
                    serverConfig.setActive(true);
                    serverConfig.setDirty(true);
                }
            }
            isDirty = true;
        }
    }

    /**
     * Returns the standard server configuration.
     *
     * @return the standard server configuration or {@code null} if no server configuration 
     *         is currently defined as standard
     */
    public synchronized ServerConfig getStandardServerConfig() {
        ServerConfig standardServerConfig = null;
        for (ServerConfig serverConfig : serverConfigList) {
            if (serverConfig.isStandard()) {
                standardServerConfig = serverConfig;
                break;
            }
        }
        return standardServerConfig;
    }

    /**
     * Sets the standard server configuration.
     *
     * @param standardServerConfig
     *            the server configuration which shall be the standard server configuration 
     *            for remote layout
     */
    public synchronized void setStandardServerConfig(final ServerConfig standardServerConfig) {
        if (standardServerConfig != null) {
            if (standardServerConfig.isStandard()) {
                return;
            }
            for (ServerConfig serverConfig : serverConfigList) {
                serverConfig.setStandard(false);
                if (serverConfig == standardServerConfig) {
                    serverConfig.setStandard(true);
                    serverConfig.setDirty(true);
                }
            }
            isDirty = true;
        }
    }

    /**
     * Returns whether the list of server configuration or any of the contained server configuration
     * have been changed.
     *
     * @return whether the list of server configuration or any of the contained server configuration
     *         have been changed
     */
    public synchronized boolean isDirty() {
        boolean result = isDirty;
        for (ServerConfig serverConfig : serverConfigList) {
            result |= serverConfig.isDirty();
        }
        return result;
    }

    /**
     * Sets the dirty state of this server configuration list.
     *
     * @param theisDirty
     *            the new dirty state
     */
    public synchronized void setDirty(final boolean theisDirty) {
        isDirty = theisDirty;
    }

    /**
     * Returns an object array consisting of the server configuration.
     *
     * @return an object array consisting of the server configuration
     */
    public synchronized Object[] toObjectArray() {
        return serverConfigList.toArray();
    }

}
