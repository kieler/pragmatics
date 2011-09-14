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

package de.cau.cs.kieler.kwebs.server.publishing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Resources;

/**
 * Manager for publishing a service object over HTTPS.
 * Concurrent safety has to be provided by using instance.
 * 
 * @kieler.rating  2011-08-25 proposed yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author swe
 *
 */
final class HttpsServerManager extends HttpServerManager {

    /** Default host for HTTPS connections. */
    private static final String HTTPS_DEFAULTHOST
        = "0.0.0.0";

    /** Default port for HTTPS connections. */
    private static final int HTTPS_DEFAULTPORT
        = 8443;
    
    /**
     * Creates the {@code HttpsServer} instance configured to listen on the host and port specified by
     * the property {@code Configuration.HTTPS_ADDRESS}.
     * 
     * @throws NoSuchAlgorithmException 
     * @throws KeyStoreException 
     * @throws IOException 
     * @throws CertificateException 
     * @throws UnrecoverableKeyException 
     * @throws KeyManagementException 
     * @throws URISyntaxException 
     */
    protected synchronized void createServer() throws NoSuchAlgorithmException, 
        KeyStoreException, CertificateException, IOException, 
        UnrecoverableKeyException, KeyManagementException, URISyntaxException {
        if (server != null) {
            throw new AlreadyPublishedException();
        }
        SSLContext sslContext
            = SSLContext.getInstance("TLS");
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
        String keystoreFile = config.getConfigProperty(Configuration.HTTPSKEYSTORE_JKS_PATH);
        String keystorePass = config.getConfigProperty(Configuration.HTTPSKEYSTORE_JKS_PASS);
        byte[] keystoreData = Resources.readFileOrPluginResourceAsByteArray(
            Application.PLUGIN_ID, keystoreFile
        );  
        keyStore.load(
            //new FileInputStream(keystoreFile),
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
        HttpsConfigurator httpsConfigurator
            = new HttpsConfigurator(sslContext);
        //URI address = new URI(config.getConfigProperty(Configuration.HTTPS_ADDRESS));
        String host = address.getHost();
        if (host == null) {
            Logger.log(Severity.WARNING, 
                "The host you specified for the HTTPS server is invalid."
                + " Using default host " + HTTPS_DEFAULTHOST + "."
            );
            host = HTTPS_DEFAULTHOST;
        }
        int port = address.getPort();
        if (port == -1) {
            Logger.log(Severity.WARNING, 
                "The port you specified for the HTTPS server is invalid."
                + " Using default port" + HTTPS_DEFAULTPORT + "."
            );
            port = HTTPS_DEFAULTPORT;
        }
        server = HttpsServer.create(
            new InetSocketAddress(host, port),
            Integer.parseInt(config.getConfigProperty(Configuration.SERVER_BACKLOG))
        );
        ((HttpsServer) server).setHttpsConfigurator(httpsConfigurator);
    }

}
