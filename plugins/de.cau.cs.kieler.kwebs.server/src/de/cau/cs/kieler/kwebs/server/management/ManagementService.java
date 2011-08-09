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

package de.cau.cs.kieler.kwebs.server.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.publishing.NotPublishedException;
import de.cau.cs.kieler.kwebs.server.publishing.ServicePublisher;

/**
 * Management for the server. Connections to the management server can only be made from localhost.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class ManagementService implements UncaughtExceptionHandler {

    /** Default port for the management service. */
    public static final int DEFAULT_MANAGEMENTPORT
        = 23456;

    /** The singleton instance. */
    private static final ManagementService INSTANCE 
        = new ManagementService();
    
    /** The management server. */
    private RequestListener requestListener 
        = new RequestListener();
    
    private Thread requestThread;
    
    /**
     * Private constructor.
     *
     */
    private ManagementService() {
        requestThread = new Thread(requestListener);
        requestThread.setUncaughtExceptionHandler(this);
    }

    /**
     * Starts the management server on the given port.
     * 
     * @param port
     *            the port this management server listens on
     */
    public static synchronized void startManagement(final int port) {
        INSTANCE.requestListener.setPort(port);
        try {
            INSTANCE.requestThread.start();
        } catch (Exception e) {
            throw new NotPublishedException(e);
        }
    }
    
    /**
     * Stops the management server. 
     */
    public static synchronized void stopManagement() {
        INSTANCE.requestListener.stop();
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
                serverSocket = new ServerSocket(
                    port, DEFAULT_BACKLOG, InetAddress.getByName("localhost")
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
        
        /**
         * Handles the management session based on the accepted socket.
         * 
         * @param socket
         *            the socket this management session is connected to
         */
        public void handle(final Socket socket) {
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );
                PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream())
                );
                Logger.log(Severity.ALWAYS, "Handling management request");
                String request = reader.readLine();
                String response = null;
                String acknowledgement = ManagementCommands.RESPONSE_ACK;
                Logger.log(Severity.ALWAYS, "Management request received: " + request);
                // Execute the response to the management request
                if (request.equals(ManagementCommands.COMMAND_ALIVE)) {
                    // Nothing to do
                } else if (request.equals(ManagementCommands.COMMAND_PUBLISH)) {
                    if (ServicePublisher.getInstance().isPublished()) {
                        response = "Cannot publish service, service is already published";
                        acknowledgement = ManagementCommands.RESPONSE_NACK;
                    } else {
                        try {
                            ServicePublisher.getInstance().publish();
                            response = "Service has been published";
                        } catch (Exception e) {
                            response = "Error while publishing service\n"
                                       + e.getMessage();
                            acknowledgement = ManagementCommands.RESPONSE_NACK;
                        }
                    }
                } else if (request.equals(ManagementCommands.COMMAND_UNPUBLISH)) {
                    if (!ServicePublisher.getInstance().isPublished()) {
                        response = "Cannot unpublish service, service is not published";
                        acknowledgement = ManagementCommands.RESPONSE_NACK;
                    } else {
                        try {
                            ServicePublisher.getInstance().unpublish();
                            response = "Service has been unpublished";
                        } catch (Exception e) {
                            response = "Error while unpublishing service\n"
                                       + e.getMessage();
                            acknowledgement = ManagementCommands.RESPONSE_NACK;
                        }
                    }
                } else if (request.equals(ManagementCommands.COMMAND_PUBLISHED)) {
                    if (ServicePublisher.getInstance().isPublished()) {
                        response = "Service is in published state";
                    } else {
                        response = "Service is in unpublished state";
                    }
                } else if (request.equals(ManagementCommands.COMMAND_SHUTDOWN)) {
                    response = "Shutting down server";
                } else if (request.equals(ManagementCommands.COMMAND_RESTART)) {
                    response = "Restarting server";
                }
                // Send the result of the execution to the management request
                if (response != null) {
                    writer.println(response);
                }
                writer.println(acknowledgement);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                Logger.log(Severity.WARNING, 
                    "Error while processing management request: " + e.getMessage(), 
                e);
            }
        }
        
    }

    /**
     * {@inheritDoc}
     */
    public void uncaughtException(final Thread t, final Throwable e) {
        Logger.log(Severity.FAILURE, "Error in management service: " + e.getMessage(), e);
    }
    
}
