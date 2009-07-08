/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.layouter.graphviz;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.layouter.graphviz.Activator;
import de.cau.cs.kieler.kiml.layouter.graphviz.preferences.GraphvizPreferencePage;
import de.cau.cs.kieler.kiml.layouter.graphviz.preferences.PreferenceConstants;
import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.MapPrinter;

/**
 * Basic wrapper class employing the GraphViz binary (e.g. dot layout) to do a
 * graphical layout on the passed {@code KNode} data structure. The basic
 * principle is simple:
 * <ol>
 * <li>Read the {@code KNode} data structure and use the {@link GraphvizAPI} to
 * fill a GraphViz internal (native) data structure.</li>
 * <li>Call a GraphViz layout engine (e.g. the dot layouter) on the GraphViz
 * data structure. The data structure will get augmented by positioning
 * attributes.</li>
 * <li>Read the position attributes from the GraphViz data structure and write
 * them back to the KIELER graph data structure.</li>
 * </ol>
 * Supported features are node sizes and positions, tail and mid label
 * positions. Edges in GraphViz are described as B-splines curves. The bezier
 * curves are converted to polylines here to be able to work with a GEF editor.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public class GraphvizLayouter {

    /** preference constant for debug directory */
    public static final String PREF_GRAPHVIZ_DEBUG_DIR = "pref.graphviz.debug.dir";
    /** preference constant for enable debug output */
    public static final String PREF_GRAPHVIZ_ENABLE_DEBUG_OUTPUT = "pref.graphviz.enable.debug.output";
    /** preference constant for graphviz executable */
    public static final String PREF_GRAPHVIZ_EXECUTABLE  = "pref.graphviz.executable";
    
    /** command for Dot layout */
    public final static String DOT_COMMAND = "dot";
    /** command for Neato layout */
    public final static String NEATO_COMMAND = "neato";
    /** command for Twopi layout */
    public final static String TWOPI_COMMAND = "twopi";
    /** command for Fdp layout */
    public final static String FDP_COMMAND = "fdp";
    /** command for Circo layout */
    public final static String CIRCO_COMMAND = "circo";
    /** parameter used to specify the command */
    public final static String COMMAND_PARAMETER = "-K";

    /** the dimension of bounding box from the last read graph */
    private Dimension boundingBox;
    /** the stdin of the dot process */
    private PrintWriter dotInput;
    /** the stdout of the dot process */
    private BufferedReader dotOutput;
    /** the stderr of the dot process */
    private BufferedReader errorStream;
    /** the dot process */
    private Process graphvizProcess;
    /** maps the object id to the object itself */
    private HashMap<String, Object> idToObject = new HashMap<String, Object>();
    /** internal file counter for debug output files */
    private int fileCounter = 0;
    /**
     * Stores an instance of the preferences dialog. This is used to present the
     * preferences at the right tab to the user whenever an error occurred that
     * is likely caused by wrong preferences.
     */
    private PreferenceDialog preferenceDialog;
    /**
     * Dots per inch specification. GraphViz uses inch for some internal sizes
     * (e.g. width, height) but not for all. Hence finding the right DPI is
     * crucial for the layout. Setting the DPI attribute for a graph within
     * GraphViz seems to have no effect.
     */
    private final float dpi = 72.0f;
    /** X padding from the borders. pad attribute of GraphViz has no effect */
    private int prefPadX = 15;
    /** Y padding from the borders. pad attribute of GraphViz has no effect */
    private int prefPadY = 15;
    /** holds the String denoting the desired layouter of the GraphViz suite */
    private String command;
    /** holds the path to the dot executable. Will be read from the preferences */
    private String dotExecutable;
    /** argument passed to the dot executable */
    private String dotArgument = GraphvizLayouter.COMMAND_PARAMETER
            + GraphvizLayouter.DOT_COMMAND;

    /**
     * Creates a new KIML GraphViz layouter with the specified concrete GraphViz
     * layouter (e.g. GraphViz Circo). Loads the preference values.
     * 
     * @param command A String denoting the layouter. Must be one of the command
     *     strings defined in this class
     */
    public GraphvizLayouter(String command) {
        this.command = command;
    }

    /**
     * Updates the preferences for the GraphViz layouter.
     */
    private void updatePreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        dotExecutable = store.getString(PreferenceConstants.PREF_GRAPHVIZ_EXECUTABLE);
    }

    /**
     * Performs the actual work of the layout process. Translates the KNode into
     * a structure GraphViz understands, calls the desired GraphViz layouter and
     * annotates the KLayoutGraph with the position and size information
     * provided by GraphViz.
     * 
     * @param node
     *            the node to process
     */
    public void visit(KNode node, IKielerProgressMonitor progressMonitor) throws KielerException {
        progressMonitor.begin("GraphViz layout", 20);
        updatePreferences();

        try {
            /* return if there is nothing in this node */
            if (node.getChildren().isEmpty()) {
                return;
            }

            /* create fresh internal GraphViz graph */
            updatePreferences();
            idToObject.clear();
            boundingBox = new Dimension();

            // start the dot process
            this.startProcess();

            this.newGraph("KIELER");
            Map<String, String> graphAttributes = new HashMap<String, String>();
            graphAttributes.put(GraphvizAPI.ATTR_SHAPE, "box");

            /* translate the KLayoutGraph to GraphViz */
            mapNodeGroup2Graphviz(node);

            /* check if Emma wants to layout horizontal */
            KLayoutData layoutData = KimlLayoutUtil.getShapeLayout(node);
            if (LayoutOptions.getLayoutDirection(layoutData) == LayoutDirection.VERTICAL) {
                graphAttributes.put("rankdir", "BT");
            } else {
                graphAttributes.put("rankdir", "LR");
            }
            progressMonitor.worked(5);

            // Apply graph attributes
            this.setGraphAttributes(graphAttributes);
            // finalize graph input and flush it to graphviz
            this.flushGraph();

            /* pick up desired layouter */
            dotArgument = GraphvizLayouter.COMMAND_PARAMETER + command;

            PrintWriter debugOut = null;
            FileWriter fw = null;

            /* should Emma debug? */
            if (Activator.getDefault().getPreferenceStore().getBoolean(
                    PreferenceConstants.PREF_GRAPHVIZ_ENABLE_DEBUG_OUTPUT)) {

                String outputName = Integer.toString(node.hashCode());
                String outputDir = Activator.getDefault().getPreferenceStore().getString(
                        PreferenceConstants.PREF_GRAPHVIZ_DEBUG_DIR);
                if (outputDir.equals("")) {
                    outputDir = System.getProperty("user.home");
                }
                try {
                    fw = new FileWriter(outputDir + File.separator + outputName);
                    debugOut = new PrintWriter(fw);
                    fileCounter++;
                } catch (IOException e) {
                    Status myStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                            "Error writing Graphviz Dot file for debugging", e);
                    StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
                }
            }
            // Wait for the graphviz process to terminate. This requires that
            // the
            // input stream has been closed before, otherwise the process will
            // run
            // forever.
            // TODO: here should be some timeout...
            progressMonitor.worked(5);
            try {
                graphvizProcess.waitFor();
            } catch (InterruptedException e1) {/*nothing*/
            }
            progressMonitor.worked(5);
            // read graphviz output and apply layout information to KIELER
            // datastructure
            this.retrieveLayoutInformations(debugOut);
            this.setTopNodeAttributes(node);
            this.killProcess(); // well, actually we know it has already
                                // finished, but destroy process anyway to be
                                // sure

            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {/*nothing*/
                }
            if (debugOut != null)
                debugOut.close();
        } catch (KielerException ge) {
            // TODO move error handling out of the layouter using
            // KielerException
            String message = "Error with the external GraphViz layouter library.";
            Status myStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, ge);
            StatusManager.getManager().handle(myStatus, StatusManager.BLOCK);
            if (preferenceDialog == null || preferenceDialog.getTray() == null) {
                preferenceDialog = PreferencesUtil.createPreferenceDialogOn(null,
                        GraphvizPreferencePage.ID, null, null);
                preferenceDialog.open();
            }
        }

        progressMonitor.done();
    }

    /**
     * Maps a {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KNode KNode}
     * to the internal GraphvizAPI data structure. This is stored in the
     * GraphvizAPI internally.
     * 
     * @param node
     *            A KNode with the graph to lay out, the actual nodes of the
     *            graph are stored as subgroups of the nodeGroup.
     */
    private void mapNodeGroup2Graphviz(KNode node) {
        int i = 0;
        /*
         * First process all nodes to have the pointers for them when creating
         * the edges.
         */
        for (KNode childNode : node.getChildren()) {
            Map<String, String> nodeAttributes = new HashMap<String, String>();
            String label = childNode.getLabel().getText();
            if (label == null)
                label = "";
            /*
             * Use NumberFormat to format the number into a String to workaround
             * different possible locales of machines on that GraphViz could run
             * (could result in different number formats, e.g. 0.33 on English
             * local, 0,33 on German)
             */
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(childNode);
            String height = pixels2GraphVizInches((int) shapeLayout.getHeight());
            String width = pixels2GraphVizInches((int) shapeLayout.getWidth());
            nodeAttributes.put("label", label);
            nodeAttributes.put("height", height);
            nodeAttributes.put("width", width);
            // hardcoded shape value here. Had the case where dot did not handle
            // a global graph attribute of shape resulting in circles
            nodeAttributes.put("shape", "box");

            addNodeToGraph(childNode, nodeAttributes, i);
            i++;
        }

        /*
         * Then create the edges
         */
        for (KNode childNode : node.getChildren()) {
            for (KEdge outgoingEdge : childNode.getOutgoingEdges()) {
                Map<String, String> edgeAttributes = new HashMap<String, String>();
                edgeAttributes.put("arrowhead", "none");
                edgeAttributes.put("arrowtail", "none");

                for (KLabel label : outgoingEdge.getLabels()) {
                    KLayoutData layoutData = KimlLayoutUtil.getShapeLayout(label);
                    EdgeLabelPlacement labelPlacement = LayoutOptions
                            .getEdgeLabelPlacement(layoutData);
                    /*
                     * As graphViz just handles three labels properly (well,
                     * actually just two, the mid and tail label), hard code
                     * it here. First 'normal' label.
                     */
                    if (labelPlacement == EdgeLabelPlacement.CENTER) {
                        String labelString = label.getText();
                        edgeAttributes.put(GraphvizAPI.ATTR_LABEL, labelString);
                    }
                    /*
                     * Give a try for head label.
                     */
                    else if (labelPlacement == EdgeLabelPlacement.HEAD) {
                        String labelString = label.getText();
                        edgeAttributes.put(GraphvizAPI.ATTR_HEADLABEL, labelString);
                    }
                    /*
                     * Give a try for tail label.
                     */
                    else if (labelPlacement == EdgeLabelPlacement.TAIL) {
                        String labelString = label.getText();
                        edgeAttributes.put(GraphvizAPI.ATTR_TAILLABEL, labelString);
                    }
                }
                this.addEdgeToGraph(outgoingEdge, edgeAttributes);
            }
        }
    }

    /**
     * Sets the required size of the top KNode by obtaining the bounding box of
     * the GraphViz Graph, resulting from the positions of the contained sub
     * nodes. If insets are given in the top KNode, they are added to the size.
     * Insets denote the difference between the resulting size of the area the
     * contained elements cover after the layout process and the real size the
     * composite node should have. An example is a compartment with a header.
     * The size (height) of the header has to be added to the desired resulting
     * size of the top KNode.
     * 
     * @param node
     *            the top KNode to set the size of
     */
    private void setTopNodeAttributes(KNode node) {
        Dimension bb = this.boundingBox;
        KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(node);
        float left = 0, right = 0, bottom = 0, top = 0;
        try {
            KInsets insets = LayoutOptions.getInsets(shapeLayout);
            top = insets.getTop();
            bottom = insets.getBottom();
            left = insets.getLeft();
            right = insets.getRight();
        } catch (Exception e) {
            /* no insets available, stay silent */
        }
        shapeLayout.setWidth((bb.width + 2 * prefPadX) + left + right);
        shapeLayout.setHeight((bb.height + 2 * prefPadY) + top + bottom);
    }

    /**
     * Transforms pixel into a GraphViz inch string, taking care of the
     * platform's current locale settings. Under different locales, the height
     * and width values of GraphViz use dots, respective periods.
     * 
     * @param pixels
     *            the pixels to transform
     * @return a localized inch string
     */
    private String pixels2GraphVizInches(int pixels) {
        NumberFormat machineFormat = NumberFormat.getInstance(Locale.US);
        return machineFormat.format(pixels / (float) dpi);
    }

    /**
     * Transforms GraphViz written inches into pixel, using the internal dpi
     * factor and taking care of the platform's current locale settings. Under
     * different locales, the height and width values of GraphViz use dots,
     * respective periods.
     * 
     * @param dotInches
     *            localized inches to transform into pixels
     * @return pixel corresponding to the provided inches
     */
    private int graphVizInches2Pixels(String dotInches) throws ParseException {
        NumberFormat machineFormat = NumberFormat.getInstance(Locale.US);
        return (int) (machineFormat.parse(dotInches).floatValue() * dpi);
    }

    /**
     * Transforms GraphvizCoordinates to
     * {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KPoint KPoint}
     * (KLayoutGraph) coordinates. The size of the provided item is used to
     * adjust the resulting location. Padding is also used.
     * 
     * @param x
     *            GraphViz x coordinate
     * @param y
     *            GraphViz y coordinate
     * @param size
     *            Size of the item. Required because GraphViz coordinates are at
     *            the center of any item and KPoint coordinates are at the upper
     *            left corner.
     * @return KPoint the location of the item in terms of the upper left corner
     */
    private KPoint graphviz2KPoint(int x, int y, float width, float height) {
        KPoint newLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
        newLocation.setX(x - (width / 2) + prefPadX);
        newLocation.setY(y - (height / 2) + prefPadY);
        return newLocation;
    }

    /**
     * Transforms GraphvizCoordinates to
     * {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KPoint KPoint}
     * (KLayoutGraph) coordinates. Padding is also used.
     * 
     * @param location
     *            GraphViz Coordinates
     * @return Draw2D coordinates
     */
    private KPoint graphviz2KPoint(int x, int y) {
        KPoint newLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
        newLocation.setX(x + prefPadX);
        newLocation.setY(y + prefPadY);
        return newLocation;
    }

    private void bezierToPolyline(KEdgeLayout layout, Point p0, Point p1, Point p2, Point p3) {

        /*
         * as the start point is not added below, that means the number of
         * points added to the polyline are the integer below -1
         */
        int numberOfPoints = 4;
        float dt;
        int i;

        dt = (float) (1.0 / (numberOfPoints - 1));
        float ax, bx, cx;
        float ay, by, cy;
        float tSquared, tCubed;

        cx = (float) (3.0 * (p1.x - p0.x));
        bx = (float) (3.0 * (p2.x - p1.x) - cx);
        ax = p3.x - p0.x - cx - bx;

        cy = (float) (3.0 * (p1.y - p0.y));
        by = (float) (3.0 * (p2.y - p1.y) - cy);
        ay = p3.y - p0.y - cy - by;

        for (i = 1; i < numberOfPoints; i++) {
            float t = i * dt;

            tSquared = t * t;
            tCubed = tSquared * t;
            int x = (int) (((ax * tCubed) + (bx * tSquared) + (cx * t)) + p0.x);
            int y = (int) (((ay * tCubed) + (by * tSquared) + (cy * t)) + p0.y);
            // layout.getGridPoints().add(graphviz2KPoint(x, y));
            layout.getBendPoints().add(graphviz2KPoint(x, y));
        }
    }

    /**
     * Converts a string containing a list of integers into a List of Integer
     * objects. Used for converting GraphViz position Strings (e.g. list of
     * bendpoints) into a real list.
     * 
     * @param integerStringList
     *            A comma separated string with integers
     * @return A list holding all the provided Integers of the string
     */
    private List<Integer> string2Ints(String integerStringList) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        if (integerStringList != null) {
            /* \s = any whitespace char */
            String[] tokens = integerStringList.split(",|\\s");
            for (int i = 0; i < tokens.length; i++) {
                try {
                    intList.add(new Integer(tokens[i]));
                } catch (Exception e) {
                    /* nothing */
                }
            }
        }
        return intList;
    }

    /**
     * @see GraphvizLayouterLibrary#newGraph(String)
     */
    protected final void newGraph(final String name) {
        idToObject.clear();
        System.out.println(MapPrinter.toString(idToObject));
        dotInput.println("digraph \"" + name + "\" {");
    }

    /**
     * @see GraphvizLayouter#layoutGraph()
     */
    protected final void flushGraph() {
        dotInput.println("}");
        dotInput.flush();
        /* the dot process will immediately answer to graph strings
         * in the input stream, but the process will stay open and
         * responsive to new inputs, until the input stream is closed.
         * Hence we close it and will start the process againfor the next 
         * operation.
         * TODO: instead of closing the process and starting a new one, 
         * reusing the same process could be more efficient. This would
         * require additional exception handling in order to make it
         * stable and fault tolerant.
         */
        dotInput.close();
    }

    /**
     * @see GraphvizLayouterLibrary#setGraphAttributes(Map)
     */
    protected final void setGraphAttributes(final Map<String, String> attributes) {
        Iterator<String> iter = attributes.keySet().iterator();

        dotInput.print("graph [");

        while (iter.hasNext()) {
            String key = (String) iter.next();

            dotInput.print(key + "=\"" + attributes.get(key) + "\"");
            if (iter.hasNext()) {
                dotInput.print(", ");
            }
        }

        dotInput.println("];");
    }

    /**
     * @see GraphvizLayouterLibrary#addNodeToGraph(Node, Map)
     */
    protected final void addNodeToGraph(final KNode node, final Map<String, String> attributes,
            int number) {
        Iterator<String> iter = attributes.keySet().iterator();

        // String nodeID = "node"+number;//this.getNodeID(node);
        String nodeID = this.getNodeID(node);
        dotInput.print("\"" + nodeID + "\" [");

        while (iter.hasNext()) {
            String key = (String) iter.next();

            dotInput.print(key + "=\"" + attributes.get(key) + "\"");
            if (iter.hasNext()) {
                dotInput.print(", ");
            }
        }

        dotInput.println("];");

        idToObject.put(nodeID, node);
    }

    /**
     * @see GraphvizLayouterLibrary#addEdgeToGraph(Edge, Map)
     */
    protected final void addEdgeToGraph(final KEdge edge, final Map<String, String> attributes) {
        Iterator<String> iter = attributes.keySet().iterator();

        dotInput.print("\"" + this.getNodeID(edge.getSource()) + "\" -> \""
                + this.getNodeID(edge.getTarget()) + "\" [");
        while (iter.hasNext()) {
            String key = (String) iter.next();
            dotInput.print(key + "=\"" + ((String) attributes.get(key)).replaceAll("\n", "\\\\n")
                    + "\"");
            if (iter.hasNext()) {
                dotInput.print(", ");
            }
        }
        dotInput.println(", comment=\"" + getEdgeID(edge) + "\"];");
        idToObject.put(getEdgeID(edge), edge);
    }

    /** Create some temporary ID (hash code) */
    private String getNodeID(KNode node) {
        String nodeID = "node" + node.hashCode();
        return nodeID;
    }

    private String getEdgeID(KEdge edge) {
        String edgeID = "edge" + edge.hashCode();
        return edgeID;
    }

    /**
     * Parses the given attribute string.
     * 
     * @param line
     *            line to be parsed
     * @return a map of attribute <-> value pairs
     */
    private Map<String, String> readAttributes(final String line) {
        HashMap<String, String> map = new HashMap<String, String>();
        String attr = null;
        String value = null;
        int beginIndex = 0;
        int i = 0;
        boolean markedValue;

        while (i < line.length()) {
            attr = null;
            value = null;

            while ((line.charAt(i) == ',') || (line.charAt(i) == ' ')) {
                i++;
            }
            beginIndex = i;

            // looking for attribute
            while (attr == null) {
                if (line.charAt(i) == '=') {
                    attr = line.substring(beginIndex, i);
                    beginIndex = i + 1;
                }
                i++;
            }

            // looking for value
            markedValue = false;
            while (value == null) {
                switch (line.charAt(i)) {
                // case '\\':
                // escape char, ignore next
                // i++;
                // break;
                case '\"':
                    if (markedValue) {
                        value = line.substring(beginIndex, i);
                    } else {
                        beginIndex++;
                        markedValue = true;
                    }
                    break;
                case ',':
                    if (!markedValue) {
                        value = line.substring(beginIndex, i);
                    }
                    break;
                default:
                    // all other chars are element of the attribute
                }

                i++;
                if ((i >= line.length()) && (value == null)) {
                    value = line.substring(beginIndex, i);
                }
            }
            value = value.replaceAll("\\n", "\n");
            map.put(attr, value);
        }

        return map;
    }

    /**
     * @see GraphvizLayouterLibrary#retrieveLayoutInformations(Collection, View)
     */
    protected final void retrieveLayoutInformations(PrintWriter debugWriter) throws KielerException {
        boolean endOfGraph;

        // read error stream
        try {
            while (errorStream.ready()) {
                Status error = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Graphviz: "
                        + errorStream.readLine());
                StatusManager.getManager().handle(error, StatusManager.SHOW);
            }
        } catch (IOException e) {
            throw new KielerException("Graphviz: error while reading dot error stream.", e);
        }

        // read output stream
        try {
            // FIXME: should check if dot output is ready somewhere. This would
            // require to wait until the dot process has finished. This was not
            // implemented yet, because process.waitFor() does not terminate...
            if (!dotOutput.ready())
                throw new KielerException(
                        "Graphviz output stream empty. Most likely incompatible dot binary set.");
            endOfGraph = false;
            while (!endOfGraph) {
                String line;
                boolean endOfLine;

                line = "";
                endOfLine = false;
                while (!endOfLine) {
                    String read = dotOutput.readLine();
                    // StatusManager.getManager().handle(
                    // new Status(IStatus.INFO, Activator.PLUGIN_ID,
                    // "read: "+read, null),
                    // StatusManager.LOG);
                    if (read.endsWith("\\")) {
                        line += read.substring(0, read.length() - 1);
                    } else {
                        line += read;
                        endOfLine = true;
                    }
                }
                if (debugWriter != null)
                    debugWriter.println(line);

                if (line.matches(".*->.*")) {
                    retrieveEdgeLayout(line);
                } else if (line.matches(".*\".*\" \\[.*")) {
                    // node found
                    retrieveNodeLayout(line);
                    // } else if (line.matches(".*graph \\[bb.*")) {
                } else if (line.matches(".*bb=.*")) {
                    // bounding box found
                    retrieveBoundingBox(line);
                } else if (line.matches(".* \\[.*")
                        && !(line.matches(".*graph \\[.*") || line.matches(".*node \\[.*"))) {
                    // node found without "..."
                    retrieveNodeLayout(line);
                } else if (line.startsWith("}")) {
                    endOfGraph = true;
                }
            }
        } catch (IOException e) {
            throw new KielerException("Error while reading dot output stream.", e);
        }
    }

    private void retrieveBoundingBox(String line) {
        StringTokenizer st = new StringTokenizer(line, "=,\"");

        st.nextToken(); // graph [bb
        st.nextToken(); // 0
        st.nextToken(); // 0
        boundingBox.width = Integer.parseInt(st.nextToken());
        boundingBox.height = Integer.parseInt(st.nextToken());
    }

    private void retrieveNodeLayout(String line) {
        try {
            StringTokenizer st = new StringTokenizer(line);
            Map<String, String> attributes;
            // st.nextToken("\""); // throw away leading " signs
            String nodeString = st.nextToken("[\t").trim();
            KNode node = (KNode) idToObject.get(nodeString);
            // st.nextToken("\"["); // " [
            String attributeString = st.nextToken("[]");
            attributes = readAttributes(attributeString);
            String posString = attributes.get(GraphvizAPI.ATTR_POS);
            String heightString = attributes.get(GraphvizAPI.ATTR_HEIGHT);
            String widthString = attributes.get(GraphvizAPI.ATTR_WIDTH);
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(node);
            try {
                List<Integer> position = string2Ints(posString);
                // use NumberFormat for parsing, see respective methods below
                shapeLayout.setHeight(graphVizInches2Pixels(heightString));
                shapeLayout.setWidth(graphVizInches2Pixels(widthString));
                // in GraphViz position is the center of the node
                // in draw2D it's the upper left corner
                KPoint location = graphviz2KPoint(position.get(0).intValue(), position.get(1)
                        .intValue(), shapeLayout.getWidth(), shapeLayout.getHeight());
                shapeLayout.setXpos(location.getX());
                shapeLayout.setYpos(location.getY());

            } catch (Exception exception) {
                // if there are no valid layout informations, use default values
                shapeLayout.setHeight(graphVizInches2Pixels("10"));
                shapeLayout.setWidth(graphVizInches2Pixels("10"));
                shapeLayout.setXpos(0);
                shapeLayout.setYpos(0);
            }
        } catch (Exception e) {
            Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Error while reading node attributes from graphviz:", e);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }
    }

    private void retrieveEdgeLayout(String line) {
        // edge found
        Map<String, String> attributes;
        KEdge edge;
        attributes = readAttributes(line.substring(line.indexOf('[') + 1, line.lastIndexOf(']')));
        try {
            edge = (KEdge) idToObject.get(attributes.get("comment"));
            String posString = (String) attributes.get("pos");
            List<Integer> intList = string2Ints(posString);
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);

            /*
             * ars, 2008-11-15: when setting arrowhead to 'none' then there
             * is no separate end coordinate for the edge given, as assumed
             * before.
             * 
             * ars, 2008-11-21, GraphViz uses cubic B-Splines, some
             * generalization of Bezier Curves. The B-Splines are converted
             * here to a polyline that Eclipse understands
             * 
             * Addressing Bezier curves in eclipse:
             * 
             * @see Eclipse bug: 234808 and 168307
             */

            /* first two points in list denote the start point */
            edgeLayout.setSourcePoint(graphviz2KPoint(intList.get(0), intList.get(1)));

            for (int i = 0; i < intList.size() - 7; i += 6) {
                /* convert the bezier representation to a poly line */
                bezierToPolyline(edgeLayout, new Point(intList.get(i + 0), intList.get(i + 1)),
                        new Point(intList.get(i + 2), intList.get(i + 3)), new Point(intList
                                .get(i + 4), intList.get(i + 5)), new Point(intList.get(i + 6),
                                intList.get(i + 7)));
            }
            /*
             * need to remove the last grid point, as this is the same as
             * the target point below
             */
            edgeLayout.getBendPoints().remove(edgeLayout.getBendPoints().size() - 1);

            /* last two points in the GraphViz list denote the end point */
            edgeLayout.setTargetPoint(graphviz2KPoint(intList.get(intList.size() - 2), intList
                    .get(intList.size() - 1)));

            /* tell all users that we produced some sort of spline */
            // edgeLayout.getOptions().add(new KIntOption(KEdgeType.SPLINE));
            /*
             * Process labels, there is a maximum of three that can be handled
             * by GraphViz. Well, actually two with locations.
             */
            for (KLabel label : edge.getLabels()) {
                KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(label);
                EdgeLabelPlacement labelPlacement = LayoutOptions
                        .getEdgeLabelPlacement(shapeLayout);

                String labelLoc = "0 0";

                // head label
                if (labelPlacement == EdgeLabelPlacement.HEAD) {
                    labelLoc = attributes.get(GraphvizAPI.ATTR_HEADLP);
                }

                /* mid label */
                if (labelPlacement == EdgeLabelPlacement.CENTER) {
                    labelLoc = attributes.get(GraphvizAPI.ATTR_LP);
                }

                /* tail label */
                if (labelPlacement == EdgeLabelPlacement.TAIL) {
                    labelLoc = attributes.get(GraphvizAPI.ATTR_TAILLP);
                }

                List<Integer> labelInts = string2Ints(labelLoc);
                KPoint labelLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
                if (labelInts.size() == 2) {
                    /* small adjust of size, and therefore of position */
                    shapeLayout.setHeight(shapeLayout.getHeight() + 7);
                    labelLocation = graphviz2KPoint(labelInts.get(0).intValue(), labelInts.get(1)
                            .intValue(), shapeLayout.getWidth(), shapeLayout.getHeight());
                }
                shapeLayout.setXpos(labelLocation.getX());
                shapeLayout.setYpos(labelLocation.getY());
            }
        } catch (Exception e) {
            Status myStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Graphviz Edge result reading problem.", e);
            StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
        }
    }

    /**
     * Starts the dot process. Sets an input and output and error stream to and
     * from the dot process. The process itself will only terminate if it gets
     * killed or the input stream gets closed. While it is active it will
     * respond to new graph definitions in its input stream in the dot language
     * and answer with the layouted graph definition in the output stream. Hence
     * the same process could be used multiple times for multiple input graphs.
     * So far this is not done.
     * 
     * @author taken from KIEL classic
     * @return returns true, if the dot process could be started
     */
    private void startProcess() throws KielerException {

        isGraphvizExecutableValid(); // might also throw GraphvizException

        String[] exp = { dotExecutable, dotArgument };
        try {
            graphvizProcess = Runtime.getRuntime().exec(exp);
            dotOutput = new BufferedReader(new InputStreamReader(graphvizProcess.getInputStream()));
            errorStream = new BufferedReader(
                    new InputStreamReader(graphvizProcess.getErrorStream()));
            dotInput = new PrintWriter(graphvizProcess.getOutputStream());
        } catch (IOException e) {
            throw new KielerException("Unable to start Graphviz process.", e);
        }
    }

    /**
     * Closes the dot process. I.e. it should wait for it to terminate or kill
     * it if it does not terminate.
     * 
     * @return true iff process has ended
     */
    private boolean killProcess() {
        // FIXME: should wait a timeout and then destroy the process
        try {
            graphvizProcess.destroy();
            // graphvizProcess.waitFor();
        } catch (Exception e) {
        }
        return true;
    }

    /**
     * Checks whether the GraphvizBinary path is valid or not. Will try to
     * access the file and if it fails will show an error message.
     * 
     * @return true iff dot executable can be read and executed
     * @throws GraphvizException
     *             iff dot can not be executed
     * @author haf
     */
    private boolean isGraphvizExecutableValid() throws KielerException {
        // try{
        if (dotExecutable == null || dotExecutable.equals(""))
            throw new KielerException("Path not set, path empty.");
        File exec = new File(dotExecutable);
        if (exec.exists())
            return true;
        else
            throw new KielerException("File not found.");
        // }catch(IOException e){
        // // FIXME: put UI stuff in external package such that we can use the
        // algorithm also standalone in headless environment
        // String message =
        // "Error executing Graphviz dot binary: "+dotExecutable+" Please set the right path in the preferences!";
        // //Status myStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
        // message, e);
        // //StatusManager.getManager().handle(myStatus, StatusManager.BLOCK);
        // //PreferenceDialog dialog =
        // PreferencesUtil.createPreferenceDialogOn(null,
        // GraphvizPreferencePage.ID, null, null);
        // //dialog.open();
        // throw new KielerException(message, e);
        // }
    }
}
