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

package de.cau.cs.kieler.kwebs.server.management;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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

import de.cau.cs.kieler.kwebs.server.management.command.client.IManagementExchange;
import de.cau.cs.kieler.kwebs.server.management.command.server.IManagementCommand;
import de.cau.cs.kieler.kwebs.server.publishing.NotPublishedException;

/**
 * 
 * Management for the server. Connections to the management server can only be made from localhost.
 *
 * @kieler.rating  2011-05-04 red
 * 
 * @author  swe
 * 
 */
public final class ManagementService  implements UncaughtExceptionHandler {

    //////////
    
    /** The singleton instance. */
    public static final ManagementService INSTANCE 
        = new ManagementService();
    
    //////////
    
    /** Default port for the management service. */
    public static final int DEFAULT_MANAGEMENTPORT
        = 23456;
    
    //////////
    
    /** The management server. */
    private RequestListener requestListener 
        = new RequestListener();
    
    /** */
    private Thread requestThread;
    
    /** */
    private int port
        = DEFAULT_MANAGEMENTPORT;
    
    /** */
    private String keystoreLocation
        = null;

    /** */
    private String keystorePassword
	    = null;

	//////////
	
    /**
     * Private constructor.
     *
     */
    private ManagementService() {
        requestThread = new Thread(requestListener);
        requestThread.setUncaughtExceptionHandler(this);
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
    public String getKeystoreLocation() {
		return keystoreLocation;
	}

    /**
     * 
     * @param keystoreLocation
     */
	public void setKeystoreLocation(final String keystoreLocation) {
		this.keystoreLocation = keystoreLocation;
	}

	/**
	 * 
	 * @return
	 */
	public String getKeystorePassword() {
		return keystorePassword;
	}

	/**
	 * 
	 * @param keystorePassword
	 */
	public void setKeystorePassword(final String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	//////////
	
	/**
     * Starts the management server on the given port.
     */
    public synchronized void startManagement() {
        requestListener.setPort(port);
        try {
            requestThread.start();
        } catch (Exception e) {
            throw new NotPublishedException(e);
        }
    }
    
    /**
     * Stops the management server. 
     */
    public synchronized void stopManagement() {
        requestListener.stop();
    }
    
    /**
     * This class implements the management server and dispatches management session requests to the
     * request handler.
     *  
     * @author swe
     *
     */
    private final class RequestListener implements Runnable {

        /** Timeout for the server socket to listen. */
        private static final int SOCKET_TIMEOUT
            = 50;

        /** Backlog for the server socket. */
        private static final int DEFAULT_BACKLOG
            = 10;
        
        /** The socket to listen for management requests. */
        private ServerSocket serverSocket;
        
        /** The port to listen for management requests. */
        private int port
            = -1;
        
        /** Whether to stop the management loop or not. */
        private boolean stopped;

        /**
         * 
         * @param theport
         */
        public void setPort(final int theport) {
            port = theport;
        }
        
        /**
         * {@inheritDoc}
         */
        public synchronized void run() {
            if (port == -1) {
                throw new ManagementException("Management port not set");
            }
            if (serverSocket != null) {
                throw new ManagementException("Management service already published");
            }
            stopped = false;
            try {
                serverSocket = createSocket(
            		keystoreLocation, 
            		keystorePassword, 
            		InetAddress.getByName("localhost"), 
            		port, 
            		DEFAULT_BACKLOG
                );
                serverSocket.setSoTimeout(SOCKET_TIMEOUT);
                while (!stopped) {
                    //CHECKSTYLEOFF EmptyBlock
                    try {
                        new RequestHandler().handle(serverSocket.accept());
                    } catch (SocketTimeoutException e) {
                    }
                    //CHECKSTYLEON EmptyBlock
                }
                serverSocket.close();
                serverSocket = null;
            } catch (Exception e) {
                throw new ManagementException("Management service could not be published", e);
            }               
        }
        
        /**
         * Stops the management server.
         */
        public void stop() {
            stopped = true;
        }
        
    }
    
    /**
     * This class handles a single management connection.
     * 
     * @author swe
     *
     */
    private final class RequestHandler {        

        /** */
        private InputStream socketIn;
        
        /** */
        private OutputStream socketOut;
        
        /**
         * Handles the management session based on the accepted socket.
         * 
         * @param socket
         *            the socket this management session is connected to
         */
        public void handle(final Socket socket) {
        	ObjectInputStream   objectIn  = null;
        	ObjectOutputStream  objectOut = null;
        	Object              object    = null;
        	IManagementExchange exchange  = null;
        	IManagementCommand  command   = null;
            try {
            	socketIn = socket.getInputStream();
            	objectIn = new ObjectInputStream(socketIn);
            	object   = objectIn.readObject();
            	if (!(object instanceof IManagementExchange)) {
            		throw new IllegalArgumentException(
            		    "Can only handle exchange instances, not objects of type " + object.getClass()
            		);
            	}
            	exchange = (IManagementExchange) object;
            	command  = ManagementCommandFactory.INSTANCE.create(exchange.getCommand());
            	command.initialize(exchange);
            	command.execute();
            } catch (Exception e) {
            	// Override any result the command may have calculated and set. Return
            	// the exception instead.
            	if (exchange != null) {
            		exchange.setResponse(e);
            	}
//                Logger.log(Severity.WARNING, 
//                    "Error while processing management request: " + e.getMessage(), e 
//                );
            } finally {
            	try {
	            	if (exchange != null) {
	            		socketOut = socket.getOutputStream();
	            		objectOut = new ObjectOutputStream(socketOut);
	            		objectOut.writeObject(exchange);
	            	}
	            	if (objectIn != null) {
	            		objectIn.close();
	            	}
	            	if (objectOut != null) {
	            		objectOut.flush();
	            		objectOut.close();
	            	}
	            	if (socketIn != null) {
	            		socketIn.close();
	            	}
	            	if (socketOut != null) {
	            		socketOut.flush();
	            		socketOut.close();
	            	}
            	} catch (Exception e) { 
            		// Nothing more can be done here.
				}
            }
        }
        
    }

    /**
     * {@inheritDoc}
     */
    public void uncaughtException(final Thread t, final Throwable e) {e.printStackTrace();
//        Logger.log(Severity.FAILURE, "Error in management service: " + e.getMessage(), e);
    }
    
    //////////
    
    private ServerSocket createSocket(final String keystoreFile, final String keystorePass,
	    final InetAddress address, final int port, final int backlog) throws NoSuchAlgorithmException,
	    KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, 
	    KeyManagementException, URISyntaxException {
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
	    keyStore.load(
	    	new FileInputStream("./" + keystoreFile),
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
	    return sslContext.getServerSocketFactory().createServerSocket(port, backlog, address);
    }
    
    //////////
    
    /**
     * Main method for testing purposes only.
     * 
     * @param args
     */
    public static void main(final String[] args) {
		ManagementService.INSTANCE.startManagement();
	}
    
}
