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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

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
import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;

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
    
    /** Internal class for storage of points. */
    private static class Point {
        float x, y;
        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

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
    
    /**
     * Dots per inch specification. GraphViz uses inch for some internal sizes
     * (e.g. width, height) but not for all. Hence finding the right DPI is
     * crucial for the layout. Setting the DPI attribute for a graph within
     * GraphViz seems to have no effect.
     */
    private final static float DPI = 72.0f;
    /** default value for minimal spacing */
    private final static float DEF_MIN_SPACING = 16.0f;

    /** the dimension of bounding box from the last read graph */
    private Dimension boundingBox;
    /** the stdin of the dot process */
    private PrintWriter dotInput;
    /** the stdout of the dot process */
    private BufferedReader dotOutput;
    /** the stderr of the dot process */
    private BufferedReader errorStream;
    /** maps each identifier of a graph element to the instance of the element */
    private HashMap<String, KGraphElement> graphElementMap = new HashMap<String, KGraphElement>();
    /** minimal spacing between elements */
    private float minSpacing;

    /**
     * Performs the actual work of the layout process. Translates the KNode into
     * a structure GraphViz understands, calls the desired GraphViz layouter and
     * annotates the KLayoutGraph with the position and size information
     * provided by GraphViz.
     * 
     * @param parentNode the node to process
     * @param progressMonitor a monitor to which progress is reported
     */
    public void layout(KNode parentNode, IKielerProgressMonitor progressMonitor,
            String command) throws KielerException {
        // check the command
        if (!DOT_COMMAND.equals(command) && !NEATO_COMMAND.equals(command)
                && !TWOPI_COMMAND.equals(command) && !FDP_COMMAND.equals(command)
                && !CIRCO_COMMAND.equals(command))
            throw new KielerException("Invalid Graphviz command set for this layout provider.");
        
        progressMonitor.begin("Graphviz layout (" + command + ")", 20);
        IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
        String dotExecutable = preferenceStore.getString(PREF_GRAPHVIZ_EXECUTABLE);
        graphElementMap.clear();
        boundingBox = new Dimension();
        // return if there is nothing in this node
        if (parentNode.getChildren().isEmpty()) {
            return;
        }
        
        // retrieve layout options
        KLayoutData parentData = KimlLayoutUtil.getShapeLayout(parentNode);
        minSpacing = LayoutOptions.getMinSpacing(parentData);
        if (Float.isNaN(minSpacing))
            minSpacing = DEF_MIN_SPACING;

        // start the dot process
        Process graphvizProcess = startProcess(dotExecutable,
                GraphvizLayouter.COMMAND_PARAMETER + command);

        // translate the KGraph to Graphviz and write to the process
        createGraph(parentNode);
        dotInput.flush();
        /* the dot process will immediately answer to graph strings
         * in the input stream, but the process will stay open and
         * responsive to new inputs, until the input stream is closed.
         * Hence we close it and will start the process again for the next 
         * operation.
         * TODO: instead of closing the process and starting a new one, 
         * reusing the same process could be more efficient. This would
         * require additional exception handling in order to make it
         * stable and fault tolerant.
         */
        dotInput.close();
        progressMonitor.worked(5);

        PrintWriter debugOutWriter = null;
        FileWriter debugFileWriter = null;

        // check whether debug output should be performed
        if (preferenceStore.getBoolean(PREF_GRAPHVIZ_ENABLE_DEBUG_OUTPUT)) {
            String outputName = getNodeID(parentNode);
            String outputDir = preferenceStore.getString(PREF_GRAPHVIZ_DEBUG_DIR);
            if (outputDir.length() == 0) {
                outputDir = System.getProperty("user.home");
            }
            try {
                if (outputDir.endsWith(File.separator))
                    debugFileWriter = new FileWriter(outputDir + outputName);
                else
                    debugFileWriter = new FileWriter(outputDir + File.separator + outputName);
                debugOutWriter = new PrintWriter(debugFileWriter);
            } catch (IOException exception) {
                throw new KielerException("Failed to write to debug output.", exception);
            }
        }
        // wait for the graphviz process to terminate
        // TODO here should be some timeout...
        
        try {
            // FIXME: waitFor() sometimes does not terminate, which indicates
        	// that the dot process has not terminated. 
           	graphvizProcess.waitFor();
        } catch (InterruptedException exception) {}
        progressMonitor.worked(10);
        
        // read graphviz output and apply layout information to the KGraph
        retrieveLayoutResult(debugOutWriter);
        setTopNodeAttributes(parentNode);
        
        // actually we know it has already finished, but destroy process anyway to be sure
        graphvizProcess.destroy();

        if (debugFileWriter != null)
            try {
                debugFileWriter.close();
            } catch (IOException e) {}
        if (debugOutWriter != null)
            debugOutWriter.close();
        progressMonitor.done();
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
     * @return returns true, if the dot process could be started
     */
    private Process startProcess(String dotExecutable, String argument) throws KielerException {
        File exec = new File(dotExecutable);
        if (!exec.exists()) {
            //String message = "Error with the external GraphViz layouter library.";
            //Status myStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, ge);
            //StatusManager.getManager().handle(myStatus, StatusManager.BLOCK);
            PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(null,
                        GraphvizPreferencePage.ID, null, null);
            preferenceDialog.open();
            // throw new KielerException("Invalid path set for Graphviz executable.");
        }

        String[] exp = { dotExecutable, argument };
        try {
            Process process = Runtime.getRuntime().exec(exp);
            dotOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            dotInput = new PrintWriter(process.getOutputStream());
            return process;
        } catch (IOException exception) {
            throw new KielerException("Unable to start Graphviz process.", exception);
        }
    }

    /**
     * Maps a {@code KNode} to the internal Graphviz data structure used by the Graphviz
     * layouter.
     * 
     * @param parent a {@code KNode} with the graph to layout
     */
    private void createGraph(KNode parent) {
        dotInput.println("digraph \"KIELER\" {");

        // create nodes
        for (KNode childNode : parent.getChildren()) {
            Map<String, String> nodeAttributes = new LinkedHashMap<String, String>();
            String label = childNode.getLabel().getText();
            /*
             * Use NumberFormat to format the number into a String to workaround
             * different possible locales of machines on that Graphviz could run
             * (could result in different number formats, e.g. 0.33 on English
             * local, 0,33 on German)
             */
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(childNode);
            NumberFormat usFormat = NumberFormat.getInstance(Locale.US);
            String width = usFormat.format(shapeLayout.getWidth() / DPI);
            String height = usFormat.format(shapeLayout.getHeight() / DPI);
            nodeAttributes.put(GraphvizAPI.ATTR_LABEL, label);
            nodeAttributes.put(GraphvizAPI.ATTR_HEIGHT, height);
            nodeAttributes.put(GraphvizAPI.ATTR_WIDTH, width);
            // TODO customize the node shape
            nodeAttributes.put(GraphvizAPI.ATTR_SHAPE, "box");

            createNode(childNode, nodeAttributes);
        }

        // create edges
        for (KNode childNode : parent.getChildren()) {
            for (KEdge outgoingEdge : childNode.getOutgoingEdges()) {
                Map<String, String> edgeAttributes = new LinkedHashMap<String, String>();
                edgeAttributes.put(GraphvizAPI.ATTR_ARROWHEAD, "none");
                edgeAttributes.put(GraphvizAPI.ATTR_ARROWTAIL, "none");

                for (KLabel label : outgoingEdge.getLabels()) {
                    KLayoutData layoutData = KimlLayoutUtil.getShapeLayout(label);
                    EdgeLabelPlacement labelPlacement = LayoutOptions
                            .getEdgeLabelPlacement(layoutData);
                    switch (labelPlacement) {
                    case CENTER:
                        edgeAttributes.put(GraphvizAPI.ATTR_LABEL, label.getText());
                        break;
                    case HEAD:
                        edgeAttributes.put(GraphvizAPI.ATTR_HEADLABEL, label.getText());
                        break;
                    case TAIL:
                        edgeAttributes.put(GraphvizAPI.ATTR_TAILLABEL, label.getText());
                        break;
                    }
                }
                createEdge(outgoingEdge, edgeAttributes);
            }
        }
        
        // set graph attributes
        Map<String, String> graphAttributes = new LinkedHashMap<String, String>();
        KLayoutData parentLayout = KimlLayoutUtil.getShapeLayout(parent);
        if (LayoutOptions.getLayoutDirection(parentLayout) == LayoutDirection.VERTICAL)
            graphAttributes.put(GraphvizAPI.ATTR_RANKDIR, "BT");
        else
            graphAttributes.put(GraphvizAPI.ATTR_RANKDIR, "LR");
        setGraphAttributes(graphAttributes);
        dotInput.println("}");
    }
    

    /**
     * Adds the given node to the Graphviz graph.
     * 
     * @param node a node to add
     * @param attributes map of attributes to set for the node
     */
    private void createNode(KNode node, Map<String, String> attributes) {
        Iterator<String> iter = attributes.keySet().iterator();
        String nodeID = getNodeID(node);
        dotInput.print("\"" + nodeID + "\" [");

        while (iter.hasNext()) {
            String key = iter.next();
            dotInput.print(key + "=\"" + attributes.get(key) + "\"");
            if (iter.hasNext()) {
                dotInput.print(", ");
            }
        }

        dotInput.println("];");
        graphElementMap.put(nodeID, node);
    }

    /**
     * Adds the given edge to the Graphviz graph.
     * 
     * @param edge an edge to add
     * @param attributes map of attributes to set for the edge
     */
    private void createEdge(KEdge edge, Map<String, String> attributes) {
        Iterator<String> iter = attributes.keySet().iterator();
        dotInput.print("\"" + this.getNodeID(edge.getSource()) + "\" -> \""
                + this.getNodeID(edge.getTarget()) + "\" [");
        
        while (iter.hasNext()) {
            String key = (String) iter.next();
            dotInput.print(key + "=\"" + ((String) attributes.get(key)).replaceAll("\n", "\\\\n")
                    + "\"");
            dotInput.print(", ");
        }
        
        dotInput.println("comment=\"" + getEdgeID(edge) + "\"];");
        graphElementMap.put(getEdgeID(edge), edge);
    }
    
    /**
     * Sets attributes for the whole graph.
     * 
     * @param attributes map of attributes to set for the graph
     */
    private void setGraphAttributes(Map<String, String> attributes) {
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
        shapeLayout.setWidth((bb.width + 2 * minSpacing) + left + right);
        shapeLayout.setHeight((bb.height + 2 * minSpacing) + top + bottom);
    }

    /**
     * Creates a unique identifier for the given node.
     * 
     * @param node node for which an identifier shall be created
     * @return a unique string used to identify the node
     */
    private String getNodeID(KNode node) {
        return "node" + node.hashCode();
    }

    /**
     * Creates a unique identifier for the given edge.
     * 
     * @param edge edge for which an identifier shall be created
     * @return a unique string used to identify the edge
     */
    private String getEdgeID(KEdge edge) {
        return "edge" + edge.hashCode();
    }

    /**
     * Reads the layout result from the Graphviz output stream and applies it
     * to the original graph.
     * 
     * @param debugWriter a print writer used to output the received Graphviz result
     * @throws KielerException if an error occurs while reading, or if Graphviz reports
     *     an error
     */
    private void retrieveLayoutResult(PrintWriter debugWriter) throws KielerException {
        // read error stream
        try {
            StringBuffer error = new StringBuffer();
            
            while (errorStream.ready()) {
                 error.append(errorStream.readLine() + "\n");
            }
            // here make sure to correctly check error condition. Take this branch only if
            // graphviz really indicated an error
            if (error.length() > 0)
                throw new KielerException("Graphviz error: " + error.toString());
        } catch (IOException exception) {}

        // read output stream
        try {
            // FIXME: should check if dot output is ready somewhere. This would
            // require to wait until the dot process has finished. This was not
            // implemented yet, because process.waitFor() does not terminate...
            if (!dotOutput.ready())
                throw new KielerException("Graphviz output stream is empty.");
            boolean endOfGraph = false;
            while (!endOfGraph) {
                String line;
                boolean endOfLine;

                line = "";
                endOfLine = false;
                while (!endOfLine) {
                    String read = dotOutput.readLine();
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
                    retrieveNodeLayout(line);
                } else if (line.matches(".*bb=.*")) {
                    retrieveBoundingBox(line);
                } else if (line.matches(".* \\[.*")
                        && !(line.matches(".*graph \\[.*") || line.matches(".*node \\[.*"))) {
                    retrieveNodeLayout(line);
                } else if (line.startsWith("}")) {
                    endOfGraph = true;
                }
            }
        } catch (IOException exception) {
            throw new KielerException("Error while reading dot output stream.", exception);
        }
    }

    /**
     * Reads bounding box information from an input line.
     * 
     * @param line an input line
     */
    private void retrieveBoundingBox(String line) {
        StringTokenizer st = new StringTokenizer(line, "=,\"");

        st.nextToken(); // graph [bb
        st.nextToken(); // 0
        st.nextToken(); // 0
        boundingBox.width = Integer.parseInt(st.nextToken());
        boundingBox.height = Integer.parseInt(st.nextToken());
    }

    /**
     * Reads node layout from an input line.
     * 
     * @param line an input line
     */
    private void retrieveNodeLayout(String line) {
        StringTokenizer st = new StringTokenizer(line);
        Map<String, String> attributes;
        String nodeString = st.nextToken("[\t").trim();
        KNode node = (KNode) graphElementMap.get(nodeString);
        String attributeString = st.nextToken("[]");
        attributes = readAttributes(attributeString);
        String posString = attributes.get(GraphvizAPI.ATTR_POS);
        String heightString = attributes.get(GraphvizAPI.ATTR_HEIGHT);
        String widthString = attributes.get(GraphvizAPI.ATTR_WIDTH);
        KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(node);
        List<Float> position = string2Floats(posString);
        // use NumberFormat for parsing
        NumberFormat usFormat = NumberFormat.getInstance(Locale.US);
        try {
            shapeLayout.setHeight(usFormat.parse(heightString).floatValue() * DPI);
            shapeLayout.setWidth(usFormat.parse(widthString).floatValue() * DPI);
        }
        catch (ParseException exception) {}
        // in GraphViz position is the center of the node
        // in draw2D it's the upper left corner
        KPoint location = graphviz2KPoint(position.get(0), position.get(1),
                shapeLayout.getWidth(), shapeLayout.getHeight());
        shapeLayout.setXpos(location.getX());
        shapeLayout.setYpos(location.getY());
    }

    /**
     * Reads edge layout from an input line.
     * 
     * @param line an input line
     */
    private void retrieveEdgeLayout(String line) {
        // edge found
        Map<String, String> attributes;
        KEdge edge;
        attributes = readAttributes(line.substring(line.indexOf('[') + 1, line.lastIndexOf(']')));
        edge = (KEdge) graphElementMap.get(attributes.get("comment"));
        String posString = (String) attributes.get("pos");
        List<Float> floatList = string2Floats(posString);
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
        KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        sourcePoint.setX(floatList.get(0) + minSpacing);
        sourcePoint.setY(floatList.get(1) + minSpacing);
        edgeLayout.setSourcePoint(sourcePoint);

        for (int i = 0; i < floatList.size() - 7; i += 6) {
            /* convert the bezier representation to a poly line */
            bezierToPolyline(edgeLayout, new Point(floatList.get(i + 0), floatList.get(i + 1)),
                    new Point(floatList.get(i + 2), floatList.get(i + 3)), new Point(floatList
                            .get(i + 4), floatList.get(i + 5)), new Point(floatList.get(i + 6),
                            floatList.get(i + 7)));
        }
        /*
         * need to remove the last grid point, as this is the same as
         * the target point below
         */
        edgeLayout.getBendPoints().remove(edgeLayout.getBendPoints().size() - 1);

        /* last two points in the GraphViz list denote the end point */
        KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        targetPoint.setX(floatList.get(floatList.size() - 2) + minSpacing);
        targetPoint.setY(floatList.get(floatList.size() - 1) + minSpacing);
        edgeLayout.setTargetPoint(targetPoint);

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

            List<Float> labelFloats = string2Floats(labelLoc);
            KPoint labelLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
            if (labelFloats.size() == 2) {
                /* small adjust of size, and therefore of position */
                shapeLayout.setHeight(shapeLayout.getHeight() + 7);
                labelLocation = graphviz2KPoint(labelFloats.get(0).intValue(), labelFloats.get(1)
                        .intValue(), shapeLayout.getWidth(), shapeLayout.getHeight());
            }
            shapeLayout.setXpos(labelLocation.getX());
            shapeLayout.setYpos(labelLocation.getY());
        }
    }
    
    /**
     * Transforms Graphviz coordinates to coordinates in the layout graph.
     * The size of the provided item is used to adjust the resulting location.
     * Padding is also used.
     * 
     * @param x GraphViz x coordinate
     * @param y GraphViz y coordinate
     * @param size size of the item; required because GraphViz coordinates are at
     *     the center of any item and KPoint coordinates are at the upper left corner.
     * @return the location of the item in terms of the upper left corner
     */
    private KPoint graphviz2KPoint(float x, float y, float width, float height) {
        KPoint newLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
        newLocation.setX(x - (width / 2) + minSpacing);
        newLocation.setY(y - (height / 2) + minSpacing);
        return newLocation;
    }

    /**
     * Converts a string containing a list of numbers into a list of Float
     * objects. Used for converting GraphViz position Strings (e.g. list of
     * bendpoints) into a real list.
     * 
     * @param floatStringList a comma separated string with numbers
     * @return a list holding all the provided floats of the string
     */
    private List<Float> string2Floats(String floatStringList) {
        ArrayList<Float> floatList = new ArrayList<Float>();
        if (floatStringList != null) {
            // char \s is any whitespace char
            String[] tokens = floatStringList.split(",|\\s");
            for (int i = 0; i < tokens.length; i++) {
                try {
                    floatList.add(new Float(tokens[i]));
                } catch (Exception e) {
                }
            }
        }
        return floatList;
    }
    
    /**
     * Transforms a Graphviz Bezier curve to a set of bend points.
     * 
     * @param layout layout to which the bend points are added 
     * @param p0 first Bezier point
     * @param p1 second Bezier point
     * @param p2 third Bezier point
     * @param p3 fourth Bezier point
     */
    private void bezierToPolyline(KEdgeLayout layout, Point p0, Point p1, Point p2, Point p3) {
        List<KPoint> bendPoints = layout.getBendPoints();
        bendPoints.clear();
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
            KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            bendPoint.setX(x + minSpacing);
            bendPoint.setY(y + minSpacing);
            bendPoints.add(bendPoint);
        }
    }
    
    /**
     * Parses the given attribute string.
     * 
     * @param line line to be parsed
     * @return a map of attributes to values
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
    
}
