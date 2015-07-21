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

package de.cau.cs.kieler.kwebs.server.management;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import de.cau.cs.kieler.kwebs.server.management.command.client.IManagementExchange;

/**
 *
 * @author swe
 *
 */
public class ManagementClient {

    //////////

    /** */
    private int port
        = ManagementService.DEFAULT_MANAGEMENTPORT;

    /** The socket which connects to the management service of the layout server. */
    private Socket socket;

    /** */
    private InputStream socketIn;

    /** */
    private OutputStream socketOut;

    /** */
    private String truststoreLocation
        = null;

    /** */
    private String truststorePassword
        = null;

    //////////

    /**
     *
     */
    public ManagementClient() {
        this(ManagementService.DEFAULT_MANAGEMENTPORT, null, null);
    }

    /**
     * Creates a new management client to manage the layout server running on localhost.
     *
     * @param port
     *            the port the management server listens for management requests
     * @param truststoreLocation
     *            the location of the trust store for client authentication.
     * @param truststorePassword
     *            the password of the trust store for client authentication.
     */
    public ManagementClient(final int port, final String truststoreLocation,
        final String truststorePassword) {
        this.port               = port;
        this.truststoreLocation = truststoreLocation;
        this.truststorePassword = truststorePassword;
    }

    //////////

    /**
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     *
     * @param port
     */
    public void setPort(final int port) {
        this.port = port;
    }

    /**
     *
     * @return
     */
    public String getTruststoreLocation() {
        return truststoreLocation;
    }

    /**
     *
     * @param truststoreLocation
     */
    public void setTruststoreLocation(final String truststoreLocation) {
        this.truststoreLocation = truststoreLocation;
    }

    /**
     *
     * @return
     */
    public String getTruststorePassword() {
        return truststorePassword;
    }

    /**
     *
     * @param truststorePassword
     */
    public void setTruststorePassword(final String truststorePassword) {
        this.truststorePassword = truststorePassword;
    }

    //////////

    /**
     * Returns whether the layout server is running or not.
     *
     * @return whether the layout server is running or not
     * @throws IOException when a communication error occurs
     * @throws ClassNotFoundException
     * @throws CertificateException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public IManagementExchange execute(final IManagementExchange exchange) throws IOException,
        ClassNotFoundException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
        CertificateException {

        initSocket();

        ObjectOutputStream objectOut = new ObjectOutputStream(socketOut);

        objectOut.writeObject(exchange);
        objectOut.flush();

        ObjectInputStream objectIn = new ObjectInputStream(socketIn);
        Object            object   = objectIn.readObject();

        objectOut.close();
        objectIn.close();

        if (!(object instanceof IManagementExchange)) {
            throw new IllegalArgumentException(
                "Can only handle cmd instances, not objects of type " + object.getClass()
            );
        }

        return (IManagementExchange) object;

    }

    //////////

    /**
     * @throws IOException
     * @throws CertificateException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     *
     */
    private void initSocket() throws KeyManagementException, NoSuchAlgorithmException,
        KeyStoreException, CertificateException, IOException {
        if (truststoreLocation == null) {
            throw new IllegalStateException(
                "No truststore has been set for client authentication."
            );
        }
        if (truststorePassword == null) {
            throw new IllegalStateException(
                "No password has been set for client authentication."
            );
        }
        socket = createSocket(
            truststoreLocation,
            truststorePassword,
            InetAddress.getByName("localhost"),
            port
        );
        socketIn  = socket.getInputStream();
        socketOut = socket.getOutputStream();
    }

    /**
     *
     * @param truststoreFile
     * @param truststorePass
     * @param address
     * @param port
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws CertificateException
     * @throws FileNotFoundException
     * @throws IOException
     * @throws KeyManagementException
     */
    private Socket createSocket(final String truststoreFile, final String truststorePass,
        final InetAddress address, final int thePort) throws NoSuchAlgorithmException, KeyStoreException,
        CertificateException, IOException, KeyManagementException {
        // Create a trust manager factory
        TrustManagerFactory trustManagerFactory
            = TrustManagerFactory.getInstance(
                  TrustManagerFactory.getDefaultAlgorithm()
              );
        // Load the clients trust store
        KeyStore keyStore
            = KeyStore.getInstance("JKS");
        keyStore.load(
            new FileInputStream("./" + truststoreFile),
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
        return sslContext.getSocketFactory().createSocket(address, thePort);
    }

}
