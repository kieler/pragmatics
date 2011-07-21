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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * .
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class ManagementClient {
    
    /** The socket which connects to the management service of the layout server. */
    private Socket socket;
    
    /** The reader used to communicate to the management service of the layout server. */
    private BufferedReader reader;
    
    /** The writer used to communicate to the management service of the layout server. */
    private PrintWriter writer;
    
    /**
     * Creates a new management client to manage the layout server running on localhost.
     * 
     * @param port
     *            the port the management server listens for management requests
     * @throws IOException
     */
    public ManagementClient(final int port) throws IOException {
        socket = new Socket(InetAddress.getByName("localhost"), port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    /**
     * Returns whether the layout server is running or not.
     * 
     * @return whether the layout server is running or not
     * @throws IOException when a communication error occurs
     */
    public boolean alive() throws IOException {
        String tmp = null;
        String received = null;
        writer.println(ManagementCommands.COMMAND_ALIVE);
        writer.flush();
        do {
            received = tmp;
            tmp = reader.readLine();
        } while (tmp != null);
        return (received.equals(ManagementCommands.RESPONSE_ACK));
    }

    /**
     * Shuts down the layout server.
     * 
     * @return whether the layout server was shut down
     * @throws IOException when a communication error occurs
     */
    public boolean shutdown() throws IOException {
        String tmp = null;
        String received = null;
        writer.println(ManagementCommands.COMMAND_SHUTDOWN);
        writer.flush();    
        do {
            received = tmp;
            tmp = reader.readLine();                
        } while (tmp != null);
        return (received.equals(ManagementCommands.RESPONSE_ACK));
    }
    
}
