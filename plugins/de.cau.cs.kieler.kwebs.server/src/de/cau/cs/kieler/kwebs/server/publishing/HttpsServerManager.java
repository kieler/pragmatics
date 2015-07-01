/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.publishing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.security.ServerSSLContextFactory;

/**
 * Manager for publishing a service object over HTTPS.
 * Concurrent safety has to be provided by using instance.
 *
 * @kieler.design 2011-08-25 reviewed by ckru, msp, mri
 * @author swe
 */
final class HttpsServerManager extends HttpServerManager {

    /** Default host for HTTPS connections. */
    private static final String HTTPS_DEFAULTHOST = "0.0.0.0";

    /** Default port for HTTPS connections. */
    private static final int HTTPS_DEFAULTPORT = 9443;

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
     * @throws IllegalStateException if no server SSL context is available
     */
    protected synchronized void createServer() throws NoSuchAlgorithmException,
        KeyStoreException, CertificateException, IOException,
        UnrecoverableKeyException, KeyManagementException, URISyntaxException {
        
        if (server != null) {
            throw new AlreadyPublishedException();
        }
        
        SSLContext sslContext = ServerSSLContextFactory.INSTANCE.create();
        
        // The factory could not provide a SSL context.
        if (sslContext == null) {
            throw new IllegalStateException(
                "No server SSL context available."
            ); 
        }
        
        HttpsConfigurator httpsConfigurator = new HttpsConfigurator(sslContext);
        
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
            Integer.parseInt(Configuration.INSTANCE.getConfigProperty(Configuration.SERVER_BACKLOG))
        );
        
        ((HttpsServer) server).setHttpsConfigurator(httpsConfigurator);
        
    }

}
