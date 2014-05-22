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
 */
package org.adaptagrams;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator class of this plugin, is responsible to properly load the dynamic libraries of the
 * adaptagrams constrained layout project.
 * 
 * @author uru
 */
public class AdaptagramsPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /**
     * (non-Javadoc).
     * 
     * @param bundleContext 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     * @throws Exception e
     */
    public void start(final BundleContext bundleContext) throws Exception {
        AdaptagramsPlugin.context = bundleContext;

        loadSharedAdaptagramsLibrary();
    }

    /**
     * (non-Javadoc).
     * 
     * @param bundleContext 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     * @throws Exception e
     */
    public void stop(final BundleContext bundleContext) throws Exception {
        AdaptagramsPlugin.context = null;
    }

    /*
     * Loading the adaptagrams dynamic library ...
     */

    /**
     * A helper enumeration for identifying the operating system.
     */
    private enum OS {
        LINUX32, LINUX64, WIN32, WIN64, OSX32, OSX64, UNKNOWN
    }

    /** the path for the library bin directory. */
    public static final String LIBRARY_PATH_BIN = "/lib";
    /** the relative path for the linux32 library. */
    public static final String LIBRARY_PATH_LINUX32 = LIBRARY_PATH_BIN + "/linux32/adaptagrams.so";
    /** the relative path for the linux64 library. */
    public static final String LIBRARY_PATH_LINUX64 = LIBRARY_PATH_BIN + "/linux64/adaptagrams.so";
    /** the relative path for the win32 library. */
    public static final String LIBRARY_PATH_WIN32 = LIBRARY_PATH_BIN + "/win32/adaptagrams.dll";
    /** the relative path for the win64 library. */
    public static final String LIBRARY_PATH_WIN64 = LIBRARY_PATH_BIN + "/win64/adaptagrams.dll";
    /** the relative path for the osx32 library. */
    public static final String LIBRARY_PATH_OSX32 = LIBRARY_PATH_BIN + "/osx32/adaptagrams.dylib";
    /** the relative path for the osx64 library. */
    public static final String LIBRARY_PATH_OSX64 = LIBRARY_PATH_BIN + "/osx64/adaptagrams.dylib";

    /**
     * Detect the operating system from system properties.
     * 
     * @return the operating system
     */
    private static OS detectOS() {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        if (os.contains("linux")) {
            if (arch.contains("64")) {
                return OS.LINUX64;
            } else if (arch.contains("86")) {
                return OS.LINUX32;
            }
        } else if (os.contains("win")) {
            if (arch.contains("64")) {
                return OS.WIN64;
            } else if (arch.contains("86")) {
                return OS.WIN32;
            }
        } else if (os.contains("mac")) {
            if (arch.contains("64")) {
                return OS.OSX64;
            } else if (arch.contains("86")) {
                return OS.OSX32;
            }
        }
        return OS.UNKNOWN;
    }

    /**
     * Resolve the OGDF server library.
     * 
     * @param an
     *            library file
     * @throws IOException
     *             when the library could not be located
     */
    private static File resolvelibrary() throws IOException {
        Bundle bundle = AdaptagramsPlugin.getContext().getBundle();
        IPath path = null;
        OS os = detectOS();
        switch (os) {
        case LINUX32:
            path = new Path(LIBRARY_PATH_LINUX32);
            break;
        case LINUX64:
            path = new Path(LIBRARY_PATH_LINUX64);
            break;
        case WIN32:
            path = new Path(LIBRARY_PATH_WIN32);
            break;
        case WIN64:
            path = new Path(LIBRARY_PATH_WIN64);
            break;
        case OSX32:
            path = new Path(LIBRARY_PATH_OSX32);
            break;
        case OSX64:
            path = new Path(LIBRARY_PATH_OSX64);
            break;
        default:
            throw new RuntimeException("Unsupported operating system.");
        }
        URL url = FileLocator.find(bundle, path, null);
        if (url == null) {
            throw new RuntimeException("Adaptagrams dynamic binary could not be located.");
        }
        File execFile = new File(FileLocator.resolve(url).getFile());

        return execFile;
    }

    /**
     * Loading the actual library into the runtime environment.
     */
    private static void loadSharedAdaptagramsLibrary() {
        try {
            String lib = resolvelibrary().getAbsolutePath();
            System.load(lib);
        } catch (IOException e) {
            throw new RuntimeException("Adaptagrams dynamic binary could not be loaded.");
        }
    }
}
