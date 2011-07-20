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

import de.cau.cs.kieler.kwebs.client.Clients;
import de.cau.cs.kieler.kwebs.util.Uris;

/**
 * This utility class provides convenient access to the list of
 * service providers configurable by the user.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public class Providers {

    /** The singleton instance. */
    private static final Providers INSTANCE
        = new Providers();

    /** The list of available providers. */
    private Vector<Provider> providerList
        = new Vector<Provider>();

    /** The optional storage manager. */
    private static IProvidersStorageManager storageManager;

    /** Whether the provider list has been changed. */
    private boolean isDirty
        = false;

    /**
     * Protected constructor.
     */
    protected Providers() {
    }

    /**
     * Get the singleton instance.
     * @return the singleton instance
     */
    public static Providers getInstance() {
        return INSTANCE;
    }

    /**
     * Registers a storage manager.
     *
     * @param manager
     *            the storage manager to be registered
     */
    public static void registerStorageManager(final IProvidersStorageManager manager) {
        storageManager = manager;
    }

    /**
     * Unregisters the storage manager.
     */
    public static void unregisterStorageManager() {
        storageManager = null;
    }

    /**
     * Clears the provider list.
     */
    public static synchronized void clear() {
        if (INSTANCE.providerList.size() > 0) {
            INSTANCE.providerList.clear();
            INSTANCE.isDirty = true;
        }
    }

    /**
     * This method uses a registered storage manager to read a provider list.
     */
    public static synchronized void read() {
        if (storageManager != null) {
            clear();
            storageManager.readProviders();
            setDirty(false);
        }
    }

    /**
     * This method uses a registered storage manager to store a provider list.
     */
    public static synchronized void store() {
        if (storageManager != null) {
            storageManager.storeProviders(INSTANCE.providerList); //FIXME clone it!
            setDirty(false);
        }
    }

    /**
     * Checks whether a given name is a valid provider name.
     *
     * @param name the provider name to be tested
     * @return whether the name is valid or not
     */
    public static boolean isValidName(final String name) {
        return (name != null && name.length() > 0);
    }

    /**
     *
     * @param provider
     * @return
     */
    public static boolean isValidProvider(final Provider provider) {
        return provider != null
               &&
               isValidName(provider.getName())
               &&
               Uris.isValidURI(provider.getAddress())
               &&
               Clients.isProviderSupported(provider)
               &&
               (
                   !Uris.isHttpsURI(provider.getAddress())
                   ||
                   (
                       provider.getTruststore() != null
                       &&
                       provider.getTruststorePass() != null
                   )
               );
    }

    /**
     *
     * @param name
     *            the name of the provider
     * @param address
     *            the address of the layout service
     * @return
     */
    public static Provider createProvider(final String name,
        final String address) {
        return INSTANCE.new Provider(name, address, null, null);
    }

    /**
     *
     *
     * @param name
     *            the name of the provider
     * @param address
     *            the address of the layout service
     * @param truststore
     *            path to the truststore when using https
     * @param truststorepass
     *            password for the truststore
     * @return
     */
    public static Provider createProvider(final String name, final String address,
        final String truststore, final String truststorepass) {
        Provider provider = null;
        if (name != null && name.length() > 0
            && address != null && address.length() > 0) {
            provider = INSTANCE.new Provider(name, address, truststore, truststorepass);
        }
        return provider;
    }

    /**
     *
     * @param name
     * @param adress
     */
    public static synchronized void addProvider(final Provider provider) {
        if (!INSTANCE.providerList.contains(provider)) {
            INSTANCE.providerList.add(provider);
            INSTANCE.isDirty = true;
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public static synchronized void removeProvider(final Provider provider) {
        if (INSTANCE.providerList.contains(provider)) {
            INSTANCE.providerList.remove(provider);
            INSTANCE.isDirty = true;
        }
    }

    /**
     *
     * @param name
     * @param address
     * @return
     */
    public static synchronized Provider findProvider(final String name,
        final String address) {
        Provider provider = null;
        Provider test = INSTANCE.new Provider(name, address);
        Provider tmp = null;
        for (int position = 0; position < INSTANCE.providerList.size();
            position++) {
            tmp = INSTANCE.providerList.get(position);
            if (tmp.equals(test)) {
                provider = tmp;
                break;
            }
        }
        return provider;
    }

    /**
     *
     * @param provider
     * @return
     */
    public static synchronized int getIndexByProvider(final Provider provider) {
        for (int position = 0; position < INSTANCE.providerList.size();
            position++) {
            if (INSTANCE.providerList.get(position) == provider) {
                return position;
            }
        }
        return -1;
    }

    /**
     *
     * @param index
     * @return
     */
    public static synchronized Provider getProviderByIndex(final int index) {
        Provider provider = null;
        if (index >= 0 && index < INSTANCE.providerList.size()) {
            provider = INSTANCE.providerList.get(index);
        }
        return provider;
    }

    /**
     * Returns whether the list of providers or any of the contained providers
     * have been changed.
     *
     * @return whether the list of providers or any of the contained providers
     *         have been changed
     */
    public static synchronized boolean isDirty() {
        boolean result = INSTANCE.isDirty;
        for (Provider provider : INSTANCE.providerList) {
            result |= provider.isDirty;
        }
        return result;
    }

    /**
     * Sets the dirty state of this provider list.
     *
     * @param theisDirty
     *            the new dirty state
     */
    public static synchronized void setDirty(final boolean theisDirty) {
        INSTANCE.isDirty = theisDirty;
    }

    /**
     * Returns an object array consisting of the providers.
     *
     * @return an object array consisting of the providers
     */
    public static synchronized Object[] toObjectArray() {
        return INSTANCE.providerList.toArray();
    }

    /////

    /**
     * This class represents a layout provider.
     */
    public final class Provider {

        /** The name of the provider. */
        private String name;

        /** The address of the layout service. */
        private String address;

        /** Path to the truststore when using https. */
        private String truststore;

        /** Password for the truststore. */
        private String truststorePass;

        /** Whether this provides has been changed. */
        private boolean isDirty
            = false;

        /**
         * Constructs a new provider.
         *
         * @param thename
         *           the name of the provider
         * @param theaddress
         *           the address of the layout service
         */
        private Provider(final String thename, final String theaddress) {
            this(thename, theaddress, null, null);
        }

        /**
         * Constructs a new provider.
         *
         * @param thename
         *           the name of the provider
         * @param theaddress
         *           the address of the layout service
         * @param thetruststore
         *           path to the truststore when using https
         * @param thetruststorePass
         *           password for the truststore
         */
        private Provider(final String thename, final String theaddress,
            final String thetruststore, final String thetruststorePass) {
            name = thename;
            address = theaddress;
            truststore = thetruststore;
            // JSSE requires the separator to be '/', even on windows
            if (truststore != null) {
                truststore = truststore.replace("\\", "/");
            }
            truststorePass = thetruststorePass;
        }

        /**
         * Returns the name of this provider.
         * 
         * @return the name of this provider
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name of this provider.
         * 
         * @param thename
         *            the name of this provider
         */
        public void setName(final String thename) {
            name = thename;
            isDirty = true;
        }

        /**
         * Returns the address where the service is located.
         * 
         * @return the address where the service is located
         */
        public String getAddress() {
            return address;
        }

        /**
         * Sets the address where the service is located.
         * 
         * @param theaddress
         *            the address where the service is located
         */
        public void setAddress(final String theaddress) {
            address = theaddress;
            isDirty = true;
        }

        /**
         * Returns the path to the trust store required to access a https based service.
         * 
         * @return the path to the trust store
         */
        public String getTruststore() {
            return truststore;
        }

        /**
         * Sets the path to the trust store required to access a https based service.
         * 
         * @param thetruststore
         *            the path to the trust store
         */
        public void setTruststore(final String thetruststore) {
            truststore = thetruststore;
            // JSSE requires the separator to be '/', even on windows
            if (truststore != null) {
                truststore = truststore.replace("\\", "/"); 
            }                                                                          
            isDirty = true;
        }

        /**
         * Returns the password required to access the https trust store associated
         * with this provider.
         * 
         * @return the password for the trust store
         */
        public String getTruststorePass() {
            return truststorePass;
        }

        /**
         * Sets the password required to access the https trust store associated
         * with this provider.
         * 
         * @param thetruststorePass
         *            the password for the trust store
         */
        public void setTruststorePass(final String thetruststorePass) {
            truststorePass = thetruststorePass;
            isDirty = true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            String result = name + ", " + address;
            if (truststore != null) {
                result += ", " + truststore;
            }
/* Just for testing purposes            
            if (truststorePass != null) {
                result += ", " + truststorePass;
            }
*/            
            return result;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Provider)) {
                return false;
            }
            Provider provider = (Provider) obj;
            if (!name.equals(provider.name)) {
                return false;
            }
            if (!address.equals(provider.address)) {
                return false;
            }
            if (truststore == null) {
                return (provider.truststore == null);
            }
            if (!truststore.equals(provider.truststore)) {
                return false;
            }
            if (truststorePass == null) {
                return (provider.truststorePass == null);
            }
            if (!truststorePass.equals(provider.truststorePass)) {
                return false;
            }
            return true;
        }

        /**
         *
         */
        @Override
        public int hashCode() {
            //FIXME correct hashcode calculation
            return (name + address + truststore).hashCode();
        }

    }

}
