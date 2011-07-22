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
     * 
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
     * Checks whether the given provider is valid, e.g. name and uri is valid
     * and if the uri is https based whether the trust store parameters are valid.
     * 
     * @param provider
     *            the provider to be tested
     * @return whether the provider is valid
     */
    public static boolean isValidProvider(final Provider provider) {
        return provider != null
               &&
               (provider.name != null && provider.name.length() > 0)
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
     * Creates a new provider.
     * 
     * @param name
     *            the name of the provider
     * @param address
     *            the address of the layout service
     * @return the newly created provider
     */
    public static Provider createProvider(final String name,
        final String address) {
        return createProvider(name, address, null, null, false);
    }

    /**
     * Creates a new provider.
     *
     * @param name
     *            the name of the provider
     * @param address
     *            the address of the layout service
     * @param truststore
     *            path to the truststore when using https
     * @param truststorepass
     *            password for the truststore
     * @return the newly created provider
     */
    public static Provider createProvider(final String name, final String address,
        final String truststore, final String truststorepass) {
        return createProvider(name, address, truststore, truststorepass, false);
    }
    
    /**
     * Creates a new provider.
     *
     * @param name
     *            the name of the provider
     * @param address
     *            the address of the layout service
     * @param truststore
     *            path to the truststore when using https
     * @param truststorepass
     *            password for the truststore
     * @param isFixed
     *            whether this provider can be altered or deleted from the provider list
     * @return the newly created provider
     */
    public static Provider createProvider(final String name, final String address,
        final String truststore, final String truststorepass, final boolean isFixed) {
        Provider provider = null;
        if (name != null && name.length() > 0
            && address != null && address.length() > 0) {
            provider = INSTANCE.new Provider(name, address, truststore, truststorepass, isFixed);
        }
        return provider;
    }

    /**
     * Adds a provider to the provider list.
     * 
     * @param provider
     *            the provider to be added
     */
    public static synchronized void addProvider(final Provider provider) {
        //if (!INSTANCE.providerList.contains(provider)) {
            INSTANCE.providerList.add(provider);
            INSTANCE.isDirty = true;
        //}
    }

    /**
     * Removes a provider from the provider list.
     * 
     * @param provider
     *            the provider to be removed
     */
    public static synchronized void removeProvider(final Provider provider) {
        if (!provider.isFixed) {
            if (INSTANCE.providerList.contains(provider)) {
                INSTANCE.providerList.remove(provider);
                INSTANCE.isDirty = true;
            }
        }
    }

    /**
     * Returns a provider equal to the given provider.
     * 
     * @param provider
     *            the provider to be compared
     * @return a provider equal to the given provider or {@code null} if no equal
     *         provider is in the provider list
     */
    public static synchronized Provider findProvider(final Provider provider) {
        for (int position = 0; position < INSTANCE.providerList.size(); position++) {
            Provider tmpProvider = INSTANCE.providerList.get(position); 
            if (tmpProvider.equals(provider)) {
                return tmpProvider;
            }
        }
        return null;
    }

    /**
     * Returns whether a given provider is already contained in the provider list.
     * 
     * @param provider
     *            the provider to be searched for
     * @return whether a given provider is already contained in the provider list
     */
    public static synchronized boolean containsProvider(final Provider provider) {
        for (int position = 0; position < INSTANCE.providerList.size(); position++) {
            if (INSTANCE.providerList.get(position).equals(provider)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of a given provider in the provider list.
     * 
     * @param provider
     *            the provider to determine the index for
     * @return the index or {@code -1} if the provider is not contained in the provider list
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
     * Returns a provider from the provider list by the given index.
     * 
     * @param index
     *            the index of the provider
     * @return the provider or {@code null} if the index is invalid
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

        /** Path to the trust store when using https. */
        private String truststore;

        /** Password for the trust store. */
        private String truststorePass;

        /** Whether this provider may be changed or deleted. */
        private boolean isFixed
            = false;

        /** Whether this provider has been changed. */
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
            this(thename, theaddress, null, null, false);
        }

        /**
         * Constructs a new provider.
         *
         * @param thename
         *           the name of the provider
         * @param theaddress
         *           the address of the layout service
         * @param thetruststore
         *           path to the trust store when using https
         * @param thetruststorePass
         *           password for the trust store
         */
        private Provider(final String thename, final String theaddress,
            final String thetruststore, final String thetruststorePass) {
            this(thename, theaddress, thetruststore, thetruststorePass, false);
        }
        
        /**
         * Constructs a new provider.
         *
         * @param thename
         *           the name of the provider
         * @param theaddress
         *           the address of the layout service
         * @param thetruststore
         *           path to the trust store when using https
         * @param thetruststorePass
         *           password for the trust store
         * @param theisFixed
         *           whether this provider can be altered or deleted from the provider list
         */
        private Provider(final String thename, final String theaddress,
            final String thetruststore, final String thetruststorePass, final boolean theisFixed) {
            name = thename;
            address = theaddress;
            truststore = thetruststore;
            // JSSE requires the separator to be '/', even on windows
            if (truststore != null) {
                truststore = truststore.replace("\\", "/");
            }
            truststorePass = thetruststorePass;
            isFixed = theisFixed;
        }
        
        /**
         * Returns whether this provider can be changed or deleted from the provider list.
         * 
         * @return whether this provider can be changed or deleted from the provider list
         */
        public boolean isFixed() {
            return isFixed;
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
                if (provider.truststore != null) {
                    return false;
                }
            }
            if (truststore != null) {
                if (!truststore.equals(provider.truststore)) {
                    return false;
                }
            }
            if (truststorePass == null) {
                if (provider.truststorePass != null) {
                    return false;
                }
            }
            if (truststorePass != null) {
                if (!truststorePass.equals(provider.truststorePass)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            //FIXME correct hash code calculation
            return (name + address + truststore + truststorePass).hashCode();
        }

    }

}
