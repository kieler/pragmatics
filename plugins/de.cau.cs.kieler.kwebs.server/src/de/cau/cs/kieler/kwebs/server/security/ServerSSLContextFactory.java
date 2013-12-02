/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.security;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.util.Resources;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * A factory that creates the server wide necessary SSL context depending
 * on the properties configured in {@link Configuration}.
 * 
 * @author swe
 *
 */
public final class ServerSSLContextFactory implements IFactory<SSLContext> {
    
    /** */
    public static final ServerSSLContextFactory INSTANCE
        = new ServerSSLContextFactory();
    
    //////////
    
    /**
     * 
     */
    private ServerSSLContextFactory() {
    }

    //////////
    
    /**
     * 
     * {@inheritDoc}
     */
    public SSLContext create() {
        
        SSLContext sslContext = null;

        try {
            
            sslContext = SSLContext.getInstance("TLS");
            
            KeyManagerFactory keyManagerFactory
                = KeyManagerFactory.getInstance(
                      KeyManagerFactory.getDefaultAlgorithm()
                  );
            
            KeyStore keyStore
                = KeyStore.getInstance("JKS");
            
            TrustManagerFactory trustManagerFactory
                = TrustManagerFactory.getInstance(
                      TrustManagerFactory.getDefaultAlgorithm()
                  );
            
            String keystoreFile = Configuration.INSTANCE.
                getConfigProperty(Configuration.HTTPSKEYSTORE_JKS_PATH);
            
            String keystorePass = Configuration.INSTANCE.
                getConfigProperty(Configuration.HTTPSKEYSTORE_JKS_PASS);
            
            byte[] keystoreData = Resources.readFileOrPluginResourceAsByteArray(
                Application.PLUGIN_ID, keystoreFile
            );
            
            keyStore.load(
                new ByteArrayInputStream(keystoreData),
                keystorePass.toCharArray()
            );
            
            keyManagerFactory.init(
                keyStore, keystorePass.toCharArray()
            );
            
            trustManagerFactory.init(keyStore);
            
            sslContext.init(
                keyManagerFactory.getKeyManagers(),
                    trustManagerFactory.getTrustManagers(),
                        new SecureRandom()
            );

        } catch (Exception e) {
            
            Logger.log(
                Severity.CRITICAL, 
                "Server side SSL context could not be initialized: " + e.getMessage(), 
                e
            );
            
            sslContext = null;
            
        }
        
        return sslContext;
        
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void destroy(final SSLContext sslContext) {
        // Nothing to do
    }

}
