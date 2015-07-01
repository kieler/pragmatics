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
 */
package net.ogdf.bin;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.ogdf.bin.preferences.OgdfPreferenceStoreAccess;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;

/**
 * A utility class to handle the communication with the OGDF server.
 * 
 * @author mri
 */
public class OgdfServer {
    
    /**
     * Available cleanup modes.
     */
    public static enum Cleanup {
        /** normal cleanup. */
        NORMAL,
        /** read error output and stop the OGDF process and the watcher thread. */
        ERROR,
        /** stop the OGDF process and the watcher thread. */
        STOP;
    }

    /** the information indicating an UML graph. */
    public static final String INFO_UML_GRAPH = "umlGraph";

    /** the edge type suffix. */
    public static final String EDGE_TYPE_SUFFIX = "type";
    /** the association edge type. */
    public static final int EDGE_TYPE_ASSOCIATION = 0;
    /** the dependency edge type. */
    public static final int EDGE_TYPE_DEPENDENCY = 1;
    /** the generalization edge type. */
    public static final int EDGE_TYPE_GENERALIZATION = 2;

    /** the edge label suffix. */
    public static final String EDGE_LABEL_SUFFIX = "label";
    /** the first end label type. */
    public static final int LABEL_TYPE_END1 = 0;
    /** the first multiplicity label type. */
    public static final int LABEL_TYPE_MULT1 = 1;
    /** the name label type. */
    public static final int LABEL_TYPE_NAME = 2;
    /** the second end label type. */
    public static final int LABEL_TYPE_END2 = 3;
    /** the second multiplicity label type. */
    public static final int LABEL_TYPE_MULT2 = 4;

    /** the option for selection of the acyclic subgraph module. */
    public static final String OPTION_ACYCLIC_SUBGRAPH_MODULE = "acyclicSubgraphModule";
    /** the option for arranging connected components. */
    public static final String OPTION_ARRANGE_CC = "arrangeCCs";
    /** the option for the attraction formula. */
    public static final String OPTION_ATTRACTION_FORMULA = "attractionFormula";
    /** the option for the base ratio. */
    public static final String OPTION_BASE_RATIO = "baseRatio";
    /** the option for the connected component distance. */
    public static final String OPTION_CC_DISTANCE = "ccDistance";
    /** the option for association edge costs. */
    public static final String OPTION_COST_ASSOC = "costAssoc";
    /** the option for the generalization edge costs. */
    public static final String OPTION_COST_GEN = "costGen";
    /** the option for costs. */
    public static final String OPTION_COSTS = "costs";
    /** the option for selection of the crossing beautifier module. */
    public static final String OPTION_CROSS_BEAUTIF_MODULE = "crossingBeautifierModule";
    /** the option for selection of the crossing minimization module. */
    public static final String OPTION_CROSS_MIN_MODULE = "crossingMinimizationModule";
    /** the option for the desired edge length. */
    public static final String OPTION_DESIRED_LENGTH = "desiredLength";
    /** the option for selection of the edge insertion module. */
    public static final String OPTION_EDGE_INSERTION_MODULE = "edgeInsertionModule";
    /** the option for the edge length. */
    public static final String OPTION_EDGE_LENGTH = "edgeLength";
    /** the option for selection of the embedder module. */
    public static final String OPTION_EMBEDDER_MODULE = "embedderModule";
    /** the option for the number of fail runs. */
    public static final String OPTION_FAILS = "fails";
    /** the option for the fineness. */
    public static final String OPTION_FINENESS = "fineness";
    /** the option for the number of global iterations. */
    public static final String OPTION_GLOBAL_ITERATIONS = "globalIterations";
    /** the option for the gravitational constant. */
    public static final String OPTION_GRAVITATIONAL_CONSTANT = "gravitationalConstant";
    /** the option for the grid distance. */
    public static final String OPTION_GRID_DISTANCE = "gridDistance";
    /** the option for the initial temperature. */
    public static final String OPTION_INITIAL_TEMPERATURE = "initialTemperature";
    /** the option for the number of iterations. */
    public static final String OPTION_ITERATIONS = "iterations";
    /** the option for the label-to-edge distance. */
    public static final String OPTION_LABEL_EDGE_DISTANCE = "labelEdgeDistance";
    /** the option for the label margin. */
    public static final String OPTION_LABEL_MARGIN_DISTANCE = "labelMarginDistance";
    /** the option for the layer distance. */
    public static final String OPTION_LAYER_DISTANCE = "layerDistance";
    /** the option for the layout direction. */
    public static final String OPTION_LAYOUT_DIRECTION = "layoutDirection";
    /** the option for the selection of the layout algorithm. */
    public static final String OPTION_LAYOUTER = "layouter";
    /** the option for the level distance. */
    public static final String OPTION_LEVEL_DISTANCE = "levelDistance";
    /** the option for the number of local iterations. */
    public static final String OPTION_LOCAL_ITERATIONS = "localIterations";
    /** the option for the maximal disturbance. */
    public static final String OPTION_MAXIMAL_DISTURBANCE = "maximalDisturbance";
    /** the option for the minimal clique size. */
    public static final String OPTION_MIN_CLIQUE_SIZE = "minCliqueSize";
    /** the option for the minimal distance between connected components. */
    public static final String OPTION_MIN_DIST_CC = "minDistCC";
    /** the option for the minimal distance between circles. */
    public static final String OPTION_MIN_DIST_CIRCLE = "minDistCircle";
    /** the option for the minimal distance between levels. */
    public static final String OPTION_MIN_DIST_LEVEL = "minDistLevel";
    /** the option for the minimal distance between siblings. */
    public static final String OPTION_MIN_DIST_SIBLING = "minDistSibling";
    /** the option for the minimal temperature. */
    public static final String OPTION_MINIMAL_TEMPERATURE = "minimalTemperature";
    /** the option for the bound for the number of nodes for the multilevel step. */
    public static final String OPTION_MULTILEVEL_UNNAL = "multilevelUntilNumNodesAreLess";
    /** the option for the number of coefficients for expansion. */
    public static final String OPTION_MULTIPOLE_PREC = "multipolePrec";
    /** the option for a new initial node placement. */
    public static final String OPTION_NEW_INITIAL_PLACEMENT = "newInitialPlacement";
    /** the option for the node distance. */
    public static final String OPTION_NODE_DISTANCE = "nodeDistance";
    /** the option for the noise. */
    public static final String OPTION_NOISE = "noise";
    /** the option for the number of rounds. */
    public static final String OPTION_NUMBER_OF_ROUNDS = "numberOfRounds";
    /** the option for the orientation. */
    public static final String OPTION_ORIENTATION = "orientation";
    /** the option for the usage of an orthogonal routing. */
    public static final String OPTION_ORTHOGONAL = "orthogonal";
    /** the option for the oscillation angle. */
    public static final String OPTION_OSCILLATION_ANGLE = "oscillationAngle";
    /** the option for the oscillation sensitivity. */
    public static final String OPTION_OSCILLATION_SENSITIVITY = "oscillationSensitivity";
    /** the option for the page ratio. */
    public static final String OPTION_PAGE_RATIO = "pageRatio";
    /** the option for the preprocessing of cliques. */
    public static final String OPTION_PREPROCESS_CLIQUES = "preprocessCliques";
    /** the option for the quality-vs-speed option set. */
    public static final String OPTION_QUALITY_VS_SPEED = "qualityVsSpeed";
    /** the option for radial constraints. */
    public static final String OPTION_RADIAL = "radial";
    /** the option for randomization of the layout. */
    public static final String OPTION_RANDOMIZE = "randomize";
    /** the option for the random seed. */
    public static final String OPTION_RANDOM_SEED = "randomSeed";
    /** the option for selection of the ranking module. */
    public static final String OPTION_RANKING_MODULE = "rankingModule";
    /** the option for the rotation angle. */
    public static final String OPTION_ROTATION_ANGLE = "rotationAngle";
    /** the option for the rotation sensitivity. */
    public static final String OPTION_ROTATION_SENSITIVITY = "rotationSensitivity";
    /** the option for the number of runs. */
    public static final String OPTION_RUNS = "runs";
    /** the option for scale function factor. */
    public static final String OPTION_SCALE_FUNCTION_FACTOR = "scaleFunctionFactor";
    /** the option for the separation. */
    public static final String OPTION_SEPARATION = "separation";
    /** the option for the sibling distance. */
    public static final String OPTION_SIBLING_DISTANCE = "siblingDistance";
    /** the option for the speed option set. */
    public static final String OPTION_SPEED = "speed";
    /** the option for the stop tolerance. */
    public static final String OPTION_STOP_TOLERANCE = "stopTolerance";
    /** the option for the subtree distance. */
    public static final String OPTION_SUBTREE_DISTANCE = "subtreeDistance";
    /** the option for the transposition. */
    public static final String OPTION_TRANSPOSE = "transpose";
    /** the option for the tree distance. */
    public static final String OPTION_TREE_DISTANCE = "treeDistance";
    /** the option for upward constraints. */
    public static final String OPTION_UPWARD = "upward";
    /** the option for usage of the given layout. */
    public static final String OPTION_USE_LAYOUT = "useLayout";
    /** the option for the width of a ranking. */
    public static final String OPTION_WIDTH = "width";
    
    
    /**
     * A helper enumeration for identifying the operating system.
     */
    private enum OS {
        LINUX32, LINUX64, WIN32, WIN64, OSX32, OSX64, SOLARIS, UNKNOWN
    }

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
        } else if (os.contains("solaris")) {
            return OS.SOLARIS;
        }
        return OS.UNKNOWN;
    }
    
    /**
     * Constructor only has package visibility. Use {@link OgdfServerPool} to create instances.
     */
    OgdfServer() {
    }

    /** the ogdf server executable. */
    private String executable;
    /** the ogdf server process. */
    private Process process;
    /** the watcher thread used to cancel a blocked read operation. */
    private Watchdog watchdog;
    /** the input stream given by the OGDF process. */
    private InputStream ogdfStream;
    /** a temporary file that should be removed after closing the process. */
    private File tempFile;

    /** the path for the executable bin directory. */
    public static final String EXECUTABLE_PATH_BIN = "/ogdf-server/bin";
    /** the relative path for the linux32 executable. */
    public static final String EXECUTABLE_PATH_LINUX32 = EXECUTABLE_PATH_BIN
            + "/linux32/ogdf-server";
    /** the relative path for the linux64 executable. */
    public static final String EXECUTABLE_PATH_LINUX64 = EXECUTABLE_PATH_BIN
            + "/linux64/ogdf-server";
    /** the relative path for the win32 executable. */
    public static final String EXECUTABLE_PATH_WIN32 = EXECUTABLE_PATH_BIN
            + "/win32/ogdf-server.exe";
    /** the relative path for the win64 executable. */
    public static final String EXECUTABLE_PATH_WIN64 = EXECUTABLE_PATH_BIN
            + "/win64/ogdf-server.exe";
    /** the relative path for the osx32 executable. */
    public static final String EXECUTABLE_PATH_OSX32 = EXECUTABLE_PATH_BIN
            + "/osx32/ogdf-server";
    /** the relative path for the osx64 executable. */
    public static final String EXECUTABLE_PATH_OSX64 = EXECUTABLE_PATH_BIN
            + "/osx64/ogdf-server";
    /** the relative path for the solaris executable. */
    public static final String EXECUTABLE_PATH_SOLARIS = EXECUTABLE_PATH_BIN
            + "/solaris/ogdf-server";

    /** the size for file transfer buffers. */
    public static final int BUFFER_SIZE = 512;
    
    /**
     * Resolve the OGDF server executable.
     * 
     * @param an executable file
     * @throws IOException
     *             when the executable could not be located
     */
    private File resolveExecutable() throws IOException {
        Bundle bundle = OgdfPlugin.getDefault().getBundle();
        IPath path = null;
        OS os = detectOS();
        switch (os) {
        case LINUX32:
            path = new Path(EXECUTABLE_PATH_LINUX32);
            break;
        case LINUX64:
            path = new Path(EXECUTABLE_PATH_LINUX64);
            break;
        case WIN32:
            path = new Path(EXECUTABLE_PATH_WIN32);
            break;
        case WIN64:
            path = new Path(EXECUTABLE_PATH_WIN64);
            break;
        case OSX32:
            path = new Path(EXECUTABLE_PATH_OSX32);
            break;
        case OSX64:
            path = new Path(EXECUTABLE_PATH_OSX64);
            break;
        case SOLARIS:
            path = new Path(EXECUTABLE_PATH_SOLARIS);
            break;
        default:
            throw new OgdfServerException("Unsupported operating system.");
        }
        URL url = FileLocator.find(bundle, path, null);
        if (url == null) {
            throw new OgdfServerException("OGDF binary could not be located.");
        }
        File execFile = new File(FileLocator.resolve(url).getFile());
        
        // if the plug-in is in a jar archive, create a temporary file to execute
        if (!execFile.exists()) {
            execFile = File.createTempFile("ogdf-server", ".exe");
            OutputStream dest = new FileOutputStream(execFile);
            InputStream source = url.openStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            do {
                count = source.read(buffer);
                if (count > 0) {
                    dest.write(buffer, 0, count);
                }
            } while (count > 0);
            dest.close();
            tempFile = execFile;
        }
        
        // set the file permissions if necessary
        switch (os) {
        case LINUX32:
        case LINUX64:
        case OSX32:
        case OSX64:
        case SOLARIS:
            if (!execFile.canExecute()) {
                boolean success = execFile.setExecutable(true);
                if (!success) {
                    throw new OgdfServerException("Failed to set executable permission for "
                            + execFile.getPath());
                }
            }
            break;
        }
        return execFile;
    }
    
    /**
     * Initialize the OGDF server instance by starting the OGDF process and the
     * watcher thread as necessary.
     * 
     * @param inputFormat
     *            the graph input format for the ogdf server
     */
    public synchronized void initialize(final String inputFormat) {
        if (watchdog == null) {
            // start the watcher thread for timeout checking
            watchdog = new Watchdog();
            watchdog.setName("OGDF Watchdog");
            watchdog.start();
        }

        if (process == null) {
            try {
                if (executable == null) {
                    executable = resolveExecutable().getPath();
                }
                process = Runtime.getRuntime().exec(new String[] { executable, inputFormat });
            } catch (IOException exception) {
                throw new OgdfServerException("Failed to start ogdf server process.", exception);
            } finally {
                if (process == null) {
                    cleanup(Cleanup.STOP);
                }
            }
        }
    }

    /**
     * Return the stream that is used to give input to OGDF.
     * 
     * @return an output stream for writing to the tool
     */
    public OutputStream input() {
        if (process != null) {
            return new BufferedOutputStream(process.getOutputStream());
        }
        throw new IllegalStateException("OGDF server has not been initialized.");
    }
    
    /**
     * Return the stream for reading the output of the OGDF process.
     * 
     * @return an input stream for reading from the tool
     */
    private InputStream output() {
        if (process != null) {
            synchronized (nextJob) {
                // create an input stream and make it visible to the watcher thread
                ogdfStream = process.getInputStream();
                // wake the watcher, which will then sleep until a timeout occurs
                nextJob.notify();
            }
            return ogdfStream;
        }
        throw new IllegalStateException("OGDF server has not been initialized.");
    }
    
    /**
     * An enumeration for keeping track of the current parser state.
     */
    private enum ParseState {
        TYPE, DATA, ERROR
    }

    /**
     * Read output data from the OGDF server process.
     * 
     * @return key-value map of output data, or {@code null} if the process output was not complete
     */
    public Map<String, String> readOutputData() {
        Map<String, String> data = new HashMap<String, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(output()));
        ParseState state = ParseState.TYPE;
        boolean parseMore = true;
        StringBuilder error = null;
        
        while (parseMore) {
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException exception) {
                // most probably the stream was closed due to a timeout of the watchdog thread
            }
            if (line == null) {
                // the stream is empty although more input is expected
                return null;
            }
            
            switch (state) {
            case TYPE:
                if (line.equals("LAYOUT")) {
                    state = ParseState.DATA;
                } else if (line.equals("ERROR")) {
                    state = ParseState.ERROR;
                    error = new StringBuilder();
                }
                break;
                
            case DATA:
                if (line.equals("DONE")) {
                    parseMore = false;
                } else {
                    String[] tokens = line.split("=");
                    if (tokens.length == 2 && tokens[0].length() > 0) {
                        data.put(tokens[0], tokens[1]);
                    }
                }
                break;
                
            case ERROR:
                if (line.equals("DONE")) {
                    cleanup(Cleanup.STOP);
                    throw new OgdfServerException(error.toString());
                } else {
                    if (error.length() > 0) {
                        error.append('\n');
                    }
                    error.append(line);
                }
                break;
            }
            
        }
        return data;
    }

    /** maximal number of characters to read from error stream. */
    private static final int MAX_ERROR_OUTPUT = 512;
    /** time to wait before checking process errors. */
    private static final int PROC_ERROR_TIME = 500;
    
    /**
     * Clean up, optionally preparing the tool for the next use.
     * 
     * @param c the cleanup option
     */
    public synchronized void cleanup(final Cleanup c) {
        StringBuilder error = null;
        if (process != null) {
            InputStream errorStream = process.getErrorStream();
            try {
                if (c == Cleanup.ERROR) {
                    // wait a bit so the process can either terminate or generate error
                    Thread.sleep(PROC_ERROR_TIME);
                    // read the error stream to display a meaningful error message
                    error = new StringBuilder();
                    int ch;
                    do {
                        ch = errorStream.read();
                        if (ch >= 0) {
                            error.append((char) ch);
                        }
                    } while (error.length() < MAX_ERROR_OUTPUT && ch >= 0);
                    if (error.length() == 0) {
                        // no error message -- check for exit value
                        int exitValue = process.exitValue();
                        if (exitValue != 0) {
                            exitValueError(exitValue, error);
                        }
                    }
                }
                // if error stream is not empty, the process may not terminate
                while (errorStream.available() > 0) {
                    errorStream.read();
                }
            } catch (Exception ex) {
                // ignore exception
            }
            
            // terminate the OGDF process if requested
            if (c == Cleanup.ERROR || c == Cleanup.STOP) {
                try {
                    process.getOutputStream().close();
                    process.getInputStream().close();
                } catch (IOException exception) {
                    // ignore exception
                }
                process.destroy();
                process = null;
                
                if (tempFile != null) {
                    tempFile.delete();
                    tempFile = null;
                }
            }
        }
        
        synchronized (nextJob) {
            // reset the stream to indicate that the job is done
            ogdfStream = null;
            if (watchdog != null) {
                Watchdog myWatchdog = watchdog;
                // if requested, reset the watcher to indicate that it should terminate
                if (c == Cleanup.ERROR || c == Cleanup.STOP) {
                    watchdog = null;
                }
                // wake the watcher if it is still waiting for timeout
                myWatchdog.interrupt();
            }
        }
        
        if (error != null && error.length() > 0) {
            // an error output could be read from OGDF, so display that to the user
            error.insert(0, "OGDF error: ");
            throw new OgdfServerException(error.toString());
        }
    }
    
    /**
     * Generate an error message for the given exit value.
     * 
     * @param exitValue an exit value
     * @param error a string builder for error messages
     */
    private void exitValueError(final int exitValue, final StringBuilder error) {
        error.append("Process terminated with exit value ").append(exitValue);
        // SUPPRESS CHECKSTYLE NEXT 30 MagicNumber
        if (exitValue > 128) {
            switch (exitValue - 128) {
            case 2: // SIGINT
                error.append(" (interrupted)");
                break;
            case 3: // SIGQUIT
                error.append(" (quit)");
                break;
            case 4: // SIGILL
                error.append(" (illegal instruction)");
                break;
            case 6: // SIGABRT
                error.append(" (aborted)");
                break;
            case 8: // SIGFPE
                error.append(" (floating point error)");
                break;
            case 9: // SIGKILL
                error.append(" (killed)");
                break;
            case 11: // SIGSEGV
                error.append(" (segmentation fault)");
                break;
            case 13: // SIGPIPE
                error.append(" (broken pipe)");
                break;
            case 15: // SIGTERM
                error.append(" (terminated)");
                break;
            }
        }
        error.append('.');
    }

    /** preference constant for timeout. */
    public static final String PREF_TIMEOUT = "ogdf.timeout";
    /** default timeout for waiting for the server to give some output. */
    public static final int PROCESS_DEF_TIMEOUT = 10000;
    /** minimal timeout for waiting for the server to give some output. */
    public static final int PROCESS_MIN_TIMEOUT = 200;
    
    /** synchronization object between the main thread and the watcher thread. */
    private Object nextJob = new Object();
    
    /**
     * A watcher thread that takes action when a timeout occurs.
     */
    private class Watchdog extends Thread {
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            do {
                synchronized (nextJob) {
                    // the watcher starts working as soon as a stream is made visible
                    while (ogdfStream == null) {
                        try {
                            // wait for notification by the main thread
                            nextJob.wait();
                        } catch (InterruptedException ex) {
                            // the watchdog thread is interrupted: shutdown is requested
                            if (watchdog != this) {
                                return;
                            }
                        }
                    }
                }
                
                // retrieve the current timeout value
                int timeout = PROCESS_DEF_TIMEOUT;
                timeout = OgdfPreferenceStoreAccess.getUISaveInt(PREF_TIMEOUT);
                if (timeout < PROCESS_MIN_TIMEOUT) {
                    timeout = PROCESS_DEF_TIMEOUT;
                }
                
                boolean interrupted = false;
                try {
                    Thread.sleep(timeout);
                }  catch (InterruptedException ex) {
                    // this means the main thread has done a cleanup before the timeout occurred
                    interrupted = true;
                }
                
                if (!interrupted) {
                    synchronized (nextJob) {
                        // timeout has occurred! kill the process so the main thread will wake
                        Process myProcess = process;
                        if (myProcess != null) {
                            ogdfStream = null;
                            myProcess.destroy();
                        }
                    }
                }
                
            } while (watchdog == this);
        }
        
    }
    
}
