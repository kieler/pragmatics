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

/**
 * This utility class resembles a server configuration as the user can edit
 * it on the preference page for remote layout.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public final class ServerConfig {

    //CHECKSTYLEOFF VisibilityModifier
    
    /** The name of the server configuration. */
    String name;

    /** The address of the layout service. */
    URI address;

    /** Path to the trust store when using HTTPS. */
    String truststore;

    /** Password for the trust store. */
    String truststorePass;

    /** Whether this server configuration may be changed or deleted. */
    boolean isFixed
        = false;

    /** Whether this server configuration has been changed. */
    boolean isDirty
        = false;

    /**
     *  Whether this server configuration is the currently selected server configuration 
     *  for doing remote layout.
     */
    boolean isActive
        = false;

    /** 
     *  Whether this server configuration is the standard server configuration for 
     *  doing remote layout. 
     */
    boolean isStandard
        = false;
    
    //CHECKSTYLEON VisibilityModifier
    
    /**
     * Constructs a new server configuration.
     *
     * @param thename
     *           the name of the server configuration
     * @param theaddress
     *           the address of the layout service
     */
    ServerConfig(final String thename, final URI theaddress) {
        this(thename, theaddress, null, null, false, false, false);
    }

    /**
     * Constructs a new server configuration.
     *
     * @param thename
     *           the name of the server configuration
     * @param theaddress
     *           the address of the layout service
     * @param thetruststore
     *           path to the trust store when using HTTPS
     * @param thetruststorePass
     *           password for the trust store
     */
    ServerConfig(final String thename, final URI theaddress,
        final String thetruststore, final String thetruststorePass) {
        this(thename, theaddress, thetruststore, thetruststorePass, false, false, false);
    }
    
    /**
     * Constructs a new server configuration.
     *
     * @param thename
     *           the name of the server configuration
     * @param theaddress
     *           the address of the layout service
     * @param thetruststore
     *           path to the trust store when using HTTPS
     * @param thetruststorePass
     *           password for the trust store
     * @param theisFixed
     *           whether this server configuration can be altered or deleted from the 
     *           server configuration list
     * @param theisActive
     *           whether this server configuration shall be the currently active 
     *           server configuration for doing remote layout
     * @param theisStandard
     *           whether this server configuration shall be the standard 
     *           server configuration for doing remote layout
     */
    ServerConfig(final String thename, final URI theaddress,
        final String thetruststore, final String thetruststorePass, final boolean theisFixed,
        final boolean theisActive, final boolean theisStandard) {
        name = thename;
        address = theaddress;
        truststore = thetruststore;
        // JSSE requires the separator to be '/', even on windows
        if (truststore != null) {
            truststore = truststore.replace("\\", "/");
        }
        truststorePass = thetruststorePass;
        isFixed = theisFixed;
        isActive = theisActive;
        isStandard = theisStandard;
    }
    
    /**
     * Returns whether this server configuration can be changed or deleted from the server
     * configuration list.
     * 
     * @return whether this server configuration can be changed or deleted from the server 
     *         configuration list
     */
    public boolean isFixed() {
        return isFixed;
    }

    /**
     * Returns whether this server configuration shall be the currently active server configuration 
     * for doing remote layout.
     * 
     * @return whether this server configuration shall be the currently active server configuration 
     *         for doing remote layout
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns whether this server configuration shall be the standard server configuration for 
     * doing remote layout.
     * 
     * @return whether this server configuration shall be the standard server configuration for 
     *         doing remote layout
     */
    public boolean isStandard() {
        return isStandard;
    }
    
    /**
     * Returns the name of this server configuration.
     * 
     * @return the name of this server configuration
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this server configuration.
     * 
     * @param thename
     *            the name of this server configuration
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
    public URI getAddress() {
        return address;
    }

    /**
     * Sets the address where the service is located.
     * 
     * @param theaddress
     *            the address where the service is located
     */
    public void setAddress(final URI theaddress) {
        address = theaddress;
        isDirty = true;
    }

    /**
     * Returns the path to the trust store required to access a HTTPS based service.
     * 
     * @return the path to the trust store
     */
    public String getTruststore() {
        return truststore;
    }

    /**
     * Sets the path to the trust store required to access a HTTPS based service.
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
     * Returns the password required to access the HTTPS trust store associated
     * with this server configuration.
     * 
     * @return the password for the trust store
     */
    public String getTruststorePass() {
        return truststorePass;
    }

    /**
     * Sets the password required to access the HTTPS trust store associated
     * with this server configuration.
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
        if (!(obj instanceof ServerConfig)) {
            return false;
        }
        ServerConfig serverConfig = (ServerConfig) obj;
        if (!name.equals(serverConfig.name)) {
            return false;
        }
        if (!address.equals(serverConfig.address)) {
            return false;
        }
        if (truststore == null) {
            if (serverConfig.truststore != null) {
                return false;
            }
        }
        if (truststore != null) {
            if (!truststore.equals(serverConfig.truststore)) {
                return false;
            }
        }
        if (truststorePass == null) {
            if (serverConfig.truststorePass != null) {
                return false;
            }
        }
        if (truststorePass != null) {
            if (!truststorePass.equals(serverConfig.truststorePass)) {
                return false;
            }
        }
        if (isFixed != serverConfig.isFixed) {
            return false;
        }
        if (isStandard != serverConfig.isStandard) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        //FIXME correct hash code calculation
        return (name + address + truststore + truststorePass + isFixed + isStandard).hashCode();
    }

}
