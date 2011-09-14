/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Abstract client implementation supporting HTTPS configuration.
 * 
 * @kieler.rating 2011-09-04 red
 *
 * @author swe
 *
 */
public abstract class HttpBasedLayoutServiceClient extends AbstractLayoutServiceClient {

    /**
     * Protected constructor.
     *
     */
    protected HttpBasedLayoutServiceClient() {
    }
    
    /**
     * Creates a new instance. Only to be used by sub classes.
     * 
     * @param theserverConfig
     *            the {@link ServerConfig} of the layout service to be used
     */
    protected HttpBasedLayoutServiceClient(final ServerConfig theserverConfig) {
        super(theserverConfig);
    }

    /** Remember the default SSL socket factory. */
    private SSLSocketFactory sslSocketFactory;

    /**
     * Initializes the SSL context needed for communication with a HTTPS based layout provider.
     * 
     * @param truststore
     *            path to the trust store file
     * @param truststorePass
     *            password for the trust store file
     * @throws Exception
     */
    protected synchronized void initSSL(final String truststore, final String truststorePass) 
        throws Exception {
        if (sslSocketFactory != null) {
            throw new IllegalAccessException("SSL already initialized, call releaseSSL first");
        }
        // Create a trust manager factory
        TrustManagerFactory trustManagerFactory
            = TrustManagerFactory.getInstance(
                  TrustManagerFactory.getDefaultAlgorithm()
              );
        // Load the clients trust store
        KeyStore keyStore
            = KeyStore.getInstance("JKS");
        keyStore.load(
            new FileInputStream(truststore),
            truststorePass.toCharArray()
        );
        // Initialize the trust manager factory with the loaded trust store.
        // Here the trusted certificate from the trust store is imported. 
        trustManagerFactory.init(keyStore);
        // Initialize a SSL context with a trust manager which trusts the
        // imported certificate
        SSLContext sslContext = SSLContext.getInstance("TLS");            
        sslContext.init(
            null,
                trustManagerFactory.getTrustManagers(),
                    new SecureRandom()
        );
        // Exchange the default SSL socket factory with our own and
        // remember system default factory
        sslSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }
    
    /**
     * Sets the SSL configuration to system defaults.
     */
    protected synchronized void releaseSSL() {
        if (sslSocketFactory != null) {
            // Set system SSL socket factory back to default
            HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
            sslSocketFactory = null;
        }
    }
    
}
