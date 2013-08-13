/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighdning;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * @author uru
 * 
 */
public class BrowseServerApplication implements IApplication {

    private String rootFolder = null;

    private int port = 8081;

    /** Object to synchronize and wait on for termination request. */
    private final Object termSync = new Object();

    /** Whether the server shall terminate. */
    private boolean termRequested = false;

    /**
     * {@inheritDoc}
     */
    public Object start(IApplicationContext context) throws Exception {

        // parse arguments
        String[] args = Platform.getCommandLineArgs();

        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-rootFolder")) {
                i++;
                File f = new File(args[i]);
                if (f.exists()) {
                    rootFolder = f.getAbsolutePath();
                } else {
                    throw new IllegalArgumentException("Cannot locate specified root folder " + f
                            + ".");
                }
            } else if (args[i].equals("-port")) {
                i++;
                try {
                    port = Integer.valueOf(args[i]);
                } catch (NumberFormatException ex) {
                    // silent
                }
            }
            i++;
        }

        if (rootFolder == null) {
            throw new IllegalArgumentException("No -rootFolder specified.");
        }

        new BrowsingSVGServer(rootFolder, port);

        synchronized (termSync) {
            while (!termRequested) {
                try {
                    termSync.wait();
                } catch (InterruptedException e) {
                    // Nothing to do, simply wait on synchronization
                    // object again.
                }
            }
        }

        return IApplication.EXIT_OK;
    }

    /**
     * {@inheritDoc}
     */
    public final void stop() {
        shutdownServer();
    }

    /**
     * Shuts down the server.
     */
    public final synchronized void shutdownServer() {
        System.out.println("Shutting down the server.");
        // Notify the termination sync loop
        termRequested = true;
        synchronized (termSync) {
            termSync.notify();
        }
    }

}
