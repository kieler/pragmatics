/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.graphviz.layouter.preferences.GraphvizPreferencePage;

/**
 * Defines constants used in the Graphviz Dot language and static methods to access Graphviz via a
 * separate process.
 * 
 * @author <a href="mailto:tkl@informatik.uni-kiel.de">Tobias Kloss</a>
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public final class GraphvizAPI {

    /** Style of arrowhead on the head node of an edge. */
    public static final String ATTR_ARROWHEAD = "arrowhead";
    /** Style of arrowhead on the tail node of an edge. */
    public static final String ATTR_ARROWTAIL = "arrowtail";
    /** Bounding box. */
    public static final String ATTR_BOUNDINGBOX = "bb";
    /** Comment. */
    public static final String ATTR_COMMENT = "comment";
    /** Set edge type for drawing arrowheads. */
    public static final String ATTR_EDGEDIR = "dir";
    /** This specifies the expected number of pixels per inch on a display device. */
    public static final String ATTR_DPI = "dpi";
    /**
     * If true, the node size is specified by the values of the width and height attributes only and
     * is not expanded to contain the text label.
     */
    public static final String ATTR_FIXEDSIZE = "fixedsize";
    /** Font used for text. */
    public static final String ATTR_FONTNAME = "fontname";
    /** Font size, in points, used for text. */
    public static final String ATTR_FONTSIZE = "fontsize";
    /** Text label to be placed near head of edge. */
    public static final String ATTR_HEADLABEL = "headlabel";
    /** Head label position, in points. */
    public static final String ATTR_HEADLP = "head_lp";
    /** Height of node, in inches. */
    public static final String ATTR_HEIGHT = "height";
    /** Text label attached to objects. */
    public static final String ATTR_LABEL = "label";
    /**
     * This, along with labeldistance, determine where the headlabel (taillabel) are placed with
     * respect to the head (tail) in polar coordinates.
     */
    public static final String ATTR_LABELANGLE = "labelangle";
    /**
     * Multiplicative scaling factor adjusting the distance that the headlabel(taillabel) is from
     * the head(tail) node.
     */
    public static final String ATTR_LABELDISTANCE = "labeldistance";
    /** Font used for headlabel and taillabel. */
    public static final String ATTR_LABELFONTNAME = "labelfontname";
    /** Font size, in points, used for headlabel and taillabel. */
    public static final String ATTR_LABELFONTSIZE = "labelfontsize";
    /** Justification for cluster labels. */
    public static final String ATTR_LABELJUST = "labeljust";
    /** Top/bottom placement of graph and cluster labels. */
    public static final String ATTR_LABELLOC = "labelloc";
    /** Preferred edge length, in inches (fdp, neato only). */
    public static final String ATTR_EDGELEN = "len";
    /** Label position, in points. */
    public static final String ATTR_LABELPOS = "lp";
    /** Determines if and how node overlaps should be removed (not dot). */
    public static final String ATTR_OVERLAP = "overlap";
    /**
     * The pad attribute specifies how much, in inches, to extend the drawing area around the
     * minimal area needed to draw the graph.
     */
    public static final String ATTR_PAD = "pad";
    /** Position of node, or spline control points. */
    public static final String ATTR_POS = "pos";
    /** For graphs, this sets x and y margins of canvas, in inches. */
    public static final String ATTR_MARGIN = "margin";
    /** Specifies the minimum separation between all nodes (circo only). */
    public static final String ATTR_MINDIST = "mindist";
    /** Sets direction of graph layout (dot only). */
    public static final String ATTR_RANKDIR = "rankdir";
    /**
     * In dot, this gives the desired rank separation, in inches. In twopi, specifies radial
     * separation of concentric circles. (twopi, dot only)
     */
    public static final String ATTR_RANKSEP = "ranksep";
    /** If 90, set drawing orientation to landscape. */
    public static final String ATTR_ROTATE = "rotate";
    /** Set the shape of a node. */
    public static final String ATTR_SHAPE = "shape";
    /**
     * Controls how, and if, edges are represented. If true, edges are drawn as splines routed
     * around nodes; if false, edges are drawn as line segments.
     */
    public static final String ATTR_SPLINES = "splines";
    /** Parameter used to determine the initial layout of nodes (fdp, neato only). */
    public static final String ATTR_START = "start";
    /** Text label to be placed near tail of edge. */
    public static final String ATTR_TAILLABEL = "taillabel";
    /** Tail label position, in points. */
    public static final String ATTR_TAILLP = "tail_lp";
    /** Weight of edge. */
    public static final String ATTR_WEIGHT = "weight";
    /** Width of node, in inches. */
    public static final String ATTR_WIDTH = "width";

    /** preference constant for Graphviz executable. */
    public static final String PREF_GRAPHVIZ_EXECUTABLE = "graphviz.executable";
    /** preference constant for timeout. */
    public static final String PREF_TIMEOUT = "graphviz.timeout";
    /** default timeout for waiting for Graphviz to give some output. */
    public static final int PROCESS_DEF_TIMEOUT = 10000;
    /** minimal timeout for waiting for Graphviz to give some output. */
    public static final int PROCESS_MIN_TIMEOUT = 200;

    /** argument used to specify the command. */
    private static final String ARG_COMMAND = "-K";
    /** argument to suppress warnings of the executable. */
    private static final String ARG_NOWARNINGS = "-q";
    /** argument to invert the Y axis to conform with SWT. */
    private static final String ARG_INVERTYAXIS = "-y";
    /** default locations of the dot executable. */
    private static final String[] DEFAULT_LOCS = {
            "/opt/local/bin/",
            "/usr/local/bin/",
            "/usr/bin/",
            "/bin/" };
    /**
     * number of milliseconds to wait if no input is available from the Graphviz process.
     */
    private static final int PROCESS_INPUT_WAIT = 20;
    /**
     * maximal number of characters that is read from the Graphviz error output.
     */
    private static final int MAX_ERROR_OUTPUT = 512;

    /**
     * Hidden constructor to avoid instantiation.
     */
    private GraphvizAPI() {
    }

    /** the process instance that is used for multiple layout runs. */
    private static Process graphvizProcess = null;
    
    /**
     * Starts a new Graphviz process with the given command. If a process instance was already
     * created, that instance is returned.
     * 
     * @param command the graphviz command to use
     * @return an instance of the graphviz process
     * @throws KielerException if creating the process fails
     */
    public static synchronized Process startProcess(final String command) throws KielerException {
        if (graphvizProcess == null) {
            IPreferenceStore preferenceStore = GraphvizLayouterPlugin.getDefault().getPreferenceStore();
            String dotExecutable = preferenceStore.getString(PREF_GRAPHVIZ_EXECUTABLE);
            if (!new File(dotExecutable).exists()) {
                boolean foundExec = false;
                for (String location : DEFAULT_LOCS) {
                    dotExecutable = location + "dot";
                    if (new File(dotExecutable).exists()) {
                        foundExec = true;
                        break;
                    }
                }
                if (!foundExec) {
                    PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(null,
                            GraphvizPreferencePage.ID, null, null);
                    preferenceDialog.open();
                    // fetch the executable string again after the user has entered a new path
                    dotExecutable = preferenceStore.getString(PREF_GRAPHVIZ_EXECUTABLE);
                }
            }
    
            try {
                graphvizProcess = Runtime.getRuntime().exec(
                                new String[] {dotExecutable, ARG_NOWARNINGS, ARG_INVERTYAXIS,
                                        ARG_COMMAND + command});
            } catch (IOException exception) {
                throw new KielerException("Failed to start Graphviz process."
                        + " Please check your Graphviz installation.", exception);
            }
        }
        return graphvizProcess;
    }
    
    /**
     * Closes the currently cached process instance so a new one is created for the next
     * layout run.
     */
    public static void endProcess() {
        if (graphvizProcess != null) {
            try {
                graphvizProcess.getOutputStream().close();
                graphvizProcess.getInputStream().close();
            } catch (IOException exception) {
                // ignore exception
            }
            graphvizProcess.destroy();
            graphvizProcess = null;
        }
    }

    /**
     * Waits until there is some input from the given input stream, with a customizable timeout.
     * 
     * @param inputStream input stream from which input is expected
     * @param errorStream error stream that is queried if there is no input
     * @param monitor monitor to which progress is reported
     * @throws KielerException if the timeout is exceeded while waiting
     */
    public static void waitForInput(final InputStream inputStream, final InputStream errorStream,
            final IKielerProgressMonitor monitor) throws KielerException {
        monitor.begin("Wait for Graphviz", 1);
        IPreferenceStore preferenceStore = GraphvizLayouterPlugin.getDefault().getPreferenceStore();
        int timeout = preferenceStore.getInt(PREF_TIMEOUT);
        if (timeout < PROCESS_MIN_TIMEOUT) {
            timeout = PROCESS_DEF_TIMEOUT;
        }
        try {
            // wait until there is input from Graphviz
            long startTime = System.currentTimeMillis();
            try {
                while (inputStream.available() == 0
                        && System.currentTimeMillis() - startTime < timeout) {
                    Thread.sleep(PROCESS_INPUT_WAIT);
                }
            } catch (InterruptedException exception) {
                // ignore exception
            }
            // read and check error stream if there is still no input from Graphviz
            if (inputStream.available() == 0) {
                StringBuilder error = new StringBuilder();
                while (error.length() < MAX_ERROR_OUTPUT && errorStream.available() > 0) {
                    error.append((char) errorStream.read());
                }
                endProcess();
                if (error.length() > 0) {
                    throw new KielerException("Graphviz error: " + error.toString());
                } else {
                    throw new KielerException("Timeout exceeded while waiting for Graphviz output.");
                }
            } else {
                // read the error stream anyway - if error stream is not empty,
                // process may not terminate
                while (errorStream.available() > 0) {
                    if (GraphvizLayouter.ENABLE_DEBUG) {
                        System.err.print(errorStream.read());
                    } else {
                        errorStream.read();
                    }
                }
            }
        } catch (IOException exception) {
            endProcess();
            throw new KielerException("Unable to read Graphviz output.", exception);
        } finally {
            monitor.done();
        }
    }

}
