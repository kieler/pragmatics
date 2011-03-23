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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;

/**
 * A utility class to handle the communication with the OGDF server.
 * 
 * @author mri
 */
public final class OgdfServerAPI {

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

    /** the option for arranging connected components. */
    public static final String OPTION_ARRANGE_CC = "arrangeCCs";
    /** the option for the attraction formula. */
    public static final String OPTION_ATTRACTION_FORMULA = "attractionFormula";
    /** the option for the connected component distance. */
    public static final String OPTION_CC_DISTANCE = "ccDistance";
    /** the option for association edge costs. */
    public static final String OPTION_COST_ASSOC = "costAssoc";
    /** the option for the generalization edge costs. */
    public static final String OPTION_COST_GEN = "costGen";
    /** the option for costs. */
    public static final String OPTION_COSTS = "costs";
    /** the option for the desired edge length. */
    public static final String OPTION_DESIRED_LENGTH = "desiredLength";
    /** the option for the edge length. */
    public static final String OPTION_EDGE_LENGTH = "edgeLength";
    /** the option for the number of fail runs. */
    public static final String OPTION_FAILS = "fails";
    /** the option for the fineness. */
    public static final String OPTION_FINENESS = "fineness";
    /** the option for the gravitational constant. */
    public static final String OPTION_GRAVITATIONAL_CONSTANT = "gravitationalConstant";
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
    /** the option for the random seed. */
    public static final String OPTION_RANDOM_SEED = "randomSeed";
    /** the option for the rotation angle. */
    public static final String OPTION_ROTATION_ANGLE = "rotationAngle";
    /** the option for the rotation sensitivity. */
    public static final String OPTION_ROTATION_SENSITIVITY = "rotationSensitivity";
    /** the option for the number of runs. */
    public static final String OPTION_RUNS = "runs";
    /** the option for the separation. */
    public static final String OPTION_SEPARATION = "separation";
    /** the option for the sibling distance. */
    public static final String OPTION_SIBLING_DISTANCE = "siblingDistance";
    /** the option for the speed option set. */
    public static final String OPTION_SPEED = "speed";
    /** the option for the subtree distance. */
    public static final String OPTION_SUBTREE_DISTANCE = "subtreeDistance";
    /** the option for the transposition. */
    public static final String OPTION_TRANSPOSE = "transpose";
    /** the option for the tree distance. */
    public static final String OPTION_TREE_DISTANCE = "treeDistance";

    /** the 'north' direction. */
    public static final int DIRECTION_NORTH = 0;
    /** the 'south' direction. */
    public static final int DIRECTION_SOUTH = 1;
    /** the 'west' direction. */
    public static final int DIRECTION_WEST = 2;
    /** the 'east' direction. */
    public static final int DIRECTION_EAST = 3;

    /** the 'gorgeous-and-efficient' qvs. */
    public static final int GORGEOUS_AND_EFFICIENT = 0;
    /** the 'beautiful-and-fast' qvs. */
    public static final int BEAUTIFUL_AND_FAST = 1;
    /** the 'nice-and-incredible-speed' qvs. */
    public static final int NICE_AND_INCREDIBLE_SPEED = 2;

    /** the 'standard' costs. */
    public static final int COSTS_STANDARD = 0;
    /** the 'repulse' costs. */
    public static final int COSTS_REPULSE = 1;
    /** the 'planar' costs. */
    public static final int COSTS_PLANAR = 2;

    /** the 'fast' speed. */
    public static final int SPEED_FAST = 0;
    /** the 'medium' speed. */
    public static final int SPEED_MEDIUM = 1;
    /** the 'hq' speed. */
    public static final int SPEED_HQ = 2;

    /** the 'top-to-bottom' orientation. */
    public static final int ORIENTATION_TOP_TO_BOTTOM = 0;
    /** the 'bottom-to-top' orientation. */
    public static final int ORIENTATION_BOTTOM_TO_TOP = 1;
    /** the 'left-to-right' orientation. */
    public static final int ORIENTATION_LEFT_TO_RIGHT = 2;
    /** the 'right-to-left' orientation. */
    public static final int ORIENTATION_RIGHT_TO_LEFT = 3;

    /** the 'Fruchterman-Reingold' attraction formula. */
    public static final int ATTRACTION_FORMULA_FR = 1;
    /** the 'GEM' attraction formula. */
    public static final int ATTRACTION_FORMULA_GEM = 2;

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
    public static final String EXECUTABLE_PATH_OSX32 = EXECUTABLE_PATH_BIN + "/osx32/ogdf-server";
    /** the relative path for the osx64 executable. */
    public static final String EXECUTABLE_PATH_OSX64 = EXECUTABLE_PATH_BIN + "/osx64/ogdf-server";
    /** the relative path for the solaris executable. */
    public static final String EXECUTABLE_PATH_SOLARIS = EXECUTABLE_PATH_BIN
            + "/solaris/ogdf-server";

    /** preference constant for timeout. */
    public static final String PREF_TIMEOUT = "ogdf.timeout";
    /** default timeout for waiting for the server to give some output. */
    public static final int PROCESS_DEF_TIMEOUT = 5000;
    /** minimal timeout for waiting for the server to give some output. */
    public static final int PROCESS_MIN_TIMEOUT = 200;

    /** starting wait time for polling input from the server process. */
    private static final int MIN_INPUT_WAIT = 4;
    /** maximal wait time for polling input from the server process. */
    private static final int MAX_INPUT_WAIT = 300;

    /** the ogdf server executable. */
    private static String executable = null;
    /** the ogdf server process. */
    private static Process process = null;

    /**
     * A private constructor to make the class not instantiable.
     */
    private OgdfServerAPI() {
        // do nothing
    }

    /**
     * A helper enumeration for identifying the operating system.
     */
    private enum OS {
        LINUX32, LINUX64, WIN32, WIN64, OSX32, OSX64, SOLARIS, UNKNOWN
    }

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
     * Finds the ogdf server executable.
     * 
     * @throws IOException
     *             when the executable could not be located
     */
    private static void findExecutable() throws IOException {
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
            throw new RuntimeException("Unsupported operating system.");
        }
        URL url = FileLocator.find(bundle, path, null);
        executable = FileLocator.resolve(url).getFile();
        // set the file permissions if necessary
        switch (os) {
        case LINUX32:
        case LINUX64:
        case OSX32:
        case OSX64:
        case SOLARIS:
            File executableFile = new File(executable);
            executableFile.setExecutable(true);
            break;
        }
    }

    /**
     * Starts a new ogdf server process or returns an existing one.
     * 
     * @param inputFormat
     *            the graph input format for the ogdf server
     * @return an instance of the ogdf server process
     */
    public static synchronized Process startProcess(final String inputFormat) {
        if (process == null) {
            try {
                if (executable == null) {
                    findExecutable();
                }
                process = Runtime.getRuntime().exec(new String[] { executable, inputFormat });
            } catch (IOException exception) {
                throw new RuntimeException("Failed to start ogdf server process.", exception);
            }
        }
        return process;
    }

    /**
     * Closes the currently cached process instance so a new one is created for the next start
     * process call.
     */
    public static void endProcess() {
        try {
            process.getOutputStream().close();
            process.getInputStream().close();
        } catch (IOException exception) {
            // ignore exception
        }
        process.destroy();
        process = null;
    }

    /**
     * Waits until there is some input from the given input stream, with a customizable timeout.
     * 
     * @param inputStream
     *            input stream from which input is expected
     * @param monitor
     *            monitor to which progress is reported
     * @return returns whether input arrived
     */
    public static boolean waitForInput(final InputStream inputStream,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Wait for the ogdf server", 1);
        IPreferenceStore preferenceStore = OgdfPlugin.getDefault().getPreferenceStore();
        int timeout = preferenceStore.getInt(PREF_TIMEOUT);
        if (timeout < PROCESS_MIN_TIMEOUT) {
            timeout = PROCESS_DEF_TIMEOUT;
        }
        try {
            // wait until there is input from the server
            long startTime = System.currentTimeMillis();
            try {
                long sleepTime = MIN_INPUT_WAIT;
                while (inputStream.available() == 0
                        && System.currentTimeMillis() - startTime < timeout
                        && !monitor.isCanceled()) {
                    Thread.sleep(sleepTime);
                    // increase sleep time after each step
                    if (sleepTime < MAX_INPUT_WAIT) {
                        sleepTime += MIN_INPUT_WAIT;
                    }
                }
            } catch (InterruptedException exception) {
                // ignore exception
            }
            if (inputStream.available() == 0) {
                endProcess();
                return false;
            }
            return true;
        } catch (IOException exception) {
            endProcess();
            throw new RuntimeException("Unable to read ogdf server output.", exception);
        } finally {
            monitor.done();
        }
    }
}
