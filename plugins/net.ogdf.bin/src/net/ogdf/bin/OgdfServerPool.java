/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package net.ogdf.bin;

import java.util.LinkedList;

import net.ogdf.bin.OgdfServer.Cleanup;

/**
 * A pool for OGDF server process instances.
 *
 * @author msp
 */
public final class OgdfServerPool {
    
    /** the singleton instance of the server pool. */
    public static final OgdfServerPool INSTANCE = new OgdfServerPool();
    
    /**
     * Hide constructor to avoid instantiation from outside.
     */
    private OgdfServerPool() {
    }
    
    /** the list of currently available servers. */
    private LinkedList<OgdfServer> servers = new LinkedList<OgdfServer>();
    
    /**
     * Fetch an OGDF server process from the pool, creating one if necessary.
     * 
     * @return an OGDF server process
     */
    public OgdfServer fetch() {
        synchronized (servers) {
            if (servers.isEmpty()) {
                return new OgdfServer();
            }
            return servers.removeFirst();
        }
    }
    
    /**
     * Release a previously created server process into the pool. Only instances that
     * are still usable may be released. Process instances that lead to errors must be closed
     * without releasing them.
     * 
     * @param server an OGDf server process
     */
    public void release(final OgdfServer server) {
        synchronized (servers) {
            servers.add(server);
        }
    }
    
    /**
     * Dispose all created server instances.
     */
    public void dispose() {
        synchronized (servers) {
            for (OgdfServer server : servers) {
                server.cleanup(Cleanup.STOP);
            }
            servers.clear();
        }
    }

}
