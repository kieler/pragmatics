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
package de.cau.cs.kieler.kiml.graphviz.layouter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.antlr.IAntlrParser;
import org.eclipse.xtext.parsetree.SyntaxError;
import org.eclipse.xtext.parsetree.reconstr.SerializerUtil;

import com.google.inject.Injector;

import de.cau.cs.kieler.kiml.graphviz.DotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.*;
import de.cau.cs.kieler.kiml.graphviz.layouter.Activator;
import de.cau.cs.kieler.kiml.graphviz.layouter.preferences.GraphvizPreferencePage;
import de.cau.cs.kieler.kiml.layout.klayoutdata.*;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.*;
import de.cau.cs.kieler.core.util.ForkedOutputStream;
import de.cau.cs.kieler.core.util.ForwardingInputStream;

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
    
    /** Internal class for storage of points and dimensions. */
    private static class Point {
        float x, y;
        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

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
    
    /** if true, debug output is enabled, which writes dot files to the home folder */
    private final static boolean ENABLE_DEBUG = false;
    /**
     * Dots per inch specification. GraphViz uses inch for some internal sizes
     * (e.g. width, height) but not for all. Hence finding the right DPI is
     * crucial for the layout. Setting the DPI attribute for a graph within
     * GraphViz seems to have no effect.
     */
    private final static float DPI = 72.0f;
    /** default value for minimal spacing */
    private final static float DEF_MIN_SPACING = 16.0f;
    /** set of delimiters used to parse attribute values */
    private final static String ATTRIBUTE_DELIM = "\", \t\n\r\\";

    /** maps each identifier of a graph element to the instance of the element */
    private HashMap<String, KGraphElement> graphElementMap = new HashMap<String, KGraphElement>();
    
    private Injector injector = null;
    /** minimal spacing between elements */
    private float minSpacing;
    /** base file name for debug output */
    private String debugFileName;

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
        debugFileName = Integer.toString(parentNode.hashCode());
        // return if there is nothing in this node
        if (parentNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }
        
        // retrieve layout options
        KLayoutData parentData = KimlLayoutUtil.getShapeLayout(parentNode);
        minSpacing = LayoutOptions.getMinSpacing(parentData);
        if (Float.isNaN(minSpacing))
            minSpacing = DEF_MIN_SPACING;

        // create the Google injector for the Dot language
        if (injector == null) {
            injector = new DotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        
        // start the graphviz process
        Process graphvizProcess = startProcess(dotExecutable,
                GraphvizLayouter.COMMAND_PARAMETER + command);

        // translate the KGraph to Graphviz and write to the process
        GraphvizModel graphvizModel = createDotGraph(parentNode);
        OutputStream dotOutput = graphvizProcess.getOutputStream();
        writeDotGraph(graphvizModel, dotOutput);
        try {
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
            dotOutput.close();
        }
        catch (IOException exception) {
            throw new KielerException("Failed to write to the Graphviz process.", exception);
        }
        progressMonitor.worked(5);

        // wait for the graphviz process to terminate
        // TODO here should be some timeout...
        
        try {
            // FIXME: waitFor() sometimes does not terminate, which indicates
        	// that the dot process has not terminated. 
           	graphvizProcess.waitFor();
        } catch (InterruptedException exception) {}
        progressMonitor.worked(10);
        
        // read graphviz output and apply layout information to the KGraph
        retrieveLayoutResult(parentNode, graphvizProcess.getInputStream(),
                graphvizProcess.getErrorStream());
        
        // actually we know it has already finished, but destroy process anyway to be sure
        graphvizProcess.destroy();

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
     * @return an instance of the graphviz process, or null if the executable
     *     does not exist
     */
    private Process startProcess(String dotExecutable, String argument) throws KielerException {
        File exec = new File(dotExecutable);
        if (!exec.exists()) {
            PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(null,
                        GraphvizPreferencePage.ID, null, null);
            preferenceDialog.open();
        }

        String[] exp = { dotExecutable, argument };
        try {
            Process process = Runtime.getRuntime().exec(exp);
            return process;
        } catch (IOException exception) {
            throw new KielerException("Unable to start Graphviz process.", exception);
        }
    }

    /**
     * Maps a {@code KNode} to the internal Graphviz data structure used by the
     * Dot parser.
     * 
     * @param parent a {@code KNode} with the graph to layout
     * @return an instance of a graphviz model that corresponds to the input graph
     */
    private GraphvizModel createDotGraph(KNode parent) {
        GraphvizModel graphvizModel = DotFactory.eINSTANCE.createGraphvizModel();
        Graph graph = DotFactory.eINSTANCE.createGraph();
        graph.setType(GraphType.DIGRAPH);
        graphvizModel.getGraphs().add(graph);

        /* Use NumberFormat to format the number into a String to workaround
         * different possible locales of machines on that Graphviz could run,
         * which could result in different number formats, e.g. 0.33 on English
         * local, 0,33 on German.
         */
        NumberFormat localFormat = NumberFormat.getNumberInstance();
        
        // create nodes
        for (KNode childNode : parent.getChildren()) {
            NodeStatement nodeStatement = DotFactory.eINSTANCE.createNodeStatement();
            Node node = DotFactory.eINSTANCE.createNode();
            String nodeID = getNodeID(childNode);
            graphElementMap.put(nodeID, childNode);
            node.setName(nodeID);
            nodeStatement.setNode(node);

            AttributeList attributes = DotFactory.eINSTANCE.createAttributeList();
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(childNode);
            // set label
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_LABEL,
                    "\"" + childNode.getLabel().getText() + "\""));
            // set width and height
            String width = localFormat.format(shapeLayout.getWidth() / DPI);
            String height = localFormat.format(shapeLayout.getHeight() / DPI);
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_WIDTH, width));
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_HEIGHT, height));
            // set shape
            // TODO customize the node shape
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_SHAPE, "box"));
            
            nodeStatement.setAttributes(attributes);
            graph.getStatements().add(nodeStatement);
        }

        // create edges
        for (KNode childNode : parent.getChildren()) {
            for (KEdge outgoingEdge : childNode.getOutgoingEdges()) {
                // consider only edges on the same hierarchy level
                if (childNode.getParent() == outgoingEdge.getTarget().getParent()) {
                    EdgeStatement edgeStatement = DotFactory.eINSTANCE.createEdgeStatement();
                    Node sourceNode = DotFactory.eINSTANCE.createNode();
                    sourceNode.setName(getNodeID(outgoingEdge.getSource()));
                    edgeStatement.setSourceNode(sourceNode);
                    EdgeTarget edgeTarget = DotFactory.eINSTANCE.createEdgeTarget();
                    Node targetNode = DotFactory.eINSTANCE.createNode();
                    targetNode.setName(getNodeID(outgoingEdge.getTarget()));
                    edgeTarget.setTargetnode(targetNode);
                    edgeStatement.getEdgeTargets().add(edgeTarget);
                    
                    AttributeList attributes = DotFactory.eINSTANCE.createAttributeList();
                    // set head and tail arrow
                    attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_ARROWHEAD,
                            "none"));
                    attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_ARROWTAIL,
                            "none"));
                    // set labels
                    for (KLabel label : outgoingEdge.getLabels()) {
                        KLayoutData layoutData = KimlLayoutUtil.getShapeLayout(label);
                        EdgeLabelPlacement labelPlacement = LayoutOptions
                                .getEdgeLabelPlacement(layoutData);
                        switch (labelPlacement) {
                        case CENTER:
                            attributes.getEntries().add(createAttribute(
                                    GraphvizAPI.ATTR_LABEL, "\"" + label.getText() + "\""));
                            break;
                        case HEAD:
                            attributes.getEntries().add(createAttribute(
                                    GraphvizAPI.ATTR_HEADLABEL, "\"" + label.getText() + "\""));
                            break;
                        case TAIL:
                            attributes.getEntries().add(createAttribute(
                                    GraphvizAPI.ATTR_TAILLABEL, "\"" + label.getText() + "\""));
                            break;
                        }
                    }
                    // add comment with edge identifier
                    String edgeID = getEdgeID(outgoingEdge);
                    attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_COMMENT,
                            "\"" + edgeID + "\""));
                    graphElementMap.put(edgeID, outgoingEdge);
                    
                    edgeStatement.setAttributes(attributes);
                    graph.getStatements().add(edgeStatement);
                }
            }
        }
        
        // set graph attributes
        AttributeStatement attributeStatement = DotFactory.eINSTANCE.createAttributeStatement();
        attributeStatement.setType(AttributeType.GRAPH);
        AttributeList attributes = DotFactory.eINSTANCE.createAttributeList();
        KLayoutData parentLayout = KimlLayoutUtil.getShapeLayout(parent);
        if (LayoutOptions.getLayoutDirection(parentLayout) == LayoutDirection.VERTICAL)
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "BT"));
        else
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "LR"));
        attributeStatement.setAttributes(attributes);
        graph.getStatements().add(attributeStatement);
        
        return graphvizModel;
    }
    
    /**
     * Creates an attribute with given name and value for the Dot graph.
     * 
     * @param name name of the attribute
     * @param value value of the attribute
     * @return instance of a Dot attribute
     */
    private static Attribute createAttribute(String name, String value) {
        Attribute attribute = DotFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }

    /**
     * Writes a serialized version of the Graphviz model to the given output
     * stream. 
     * 
     * @param graphvizModel Graphviz model to serialize
     * @param outputStream output stream to which the model is written
     * @throws KielerException if writing to the output stream fails
     */
    private void writeDotGraph(GraphvizModel graphvizModel, OutputStream outputStream)
            throws KielerException {
        // enable debug output if needed
        FileOutputStream debugStream = null;
        if (ENABLE_DEBUG) {
            try {
                String path = System.getProperty("user.home");
                if (path.endsWith(File.separator))
                    path += "tmp";
                else
                    path += File.separator + "tmp";
                debugStream = new FileOutputStream(new File(
                        path + File.separator + debugFileName + "-in.dot"));
                outputStream = new ForkedOutputStream(outputStream, debugStream);
            }
            catch (Exception exception) {
                System.out.println("GraphvizLayouter: Could not initialize debug output: "
                        + exception.getMessage());
            }
        }
        
        SerializerUtil serializerUtil = injector.getInstance(SerializerUtil.class);
        try {
            serializerUtil.serialize(graphvizModel, outputStream, null, false);
            outputStream.flush();
        }
        catch (IOException exception) {
            throw new KielerException("Failed to serialize the Dot graph.", exception);
        }
        finally {
            try {
                if (debugStream != null)
                    debugStream.close();
            } catch (IOException exception) {}
        }
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
    private void retrieveLayoutResult(KNode parentNode, InputStream inputStream,
            InputStream errorStream) throws KielerException {
        // read and check error stream
        try {
            StringBuffer error = new StringBuffer();
            int c;
            while ((c = errorStream.read()) >= 0) {
                 error.append((char)c);
            }
            // take this branch only if graphviz really indicated an error
            if (error.length() > 0)
                throw new KielerException("Graphviz error: " + error.toString());
        } catch (IOException exception) {}

        // enable debug output if needed
        FileOutputStream debugStream = null;
        if (ENABLE_DEBUG) {
            try {
                String path = System.getProperty("user.home");
                if (path.endsWith(File.separator))
                    path += "tmp";
                else
                    path += File.separator + "tmp";
                debugStream = new FileOutputStream(new File(path + File.separator
                        + debugFileName + "-out.dot"));
                inputStream = new ForwardingInputStream(inputStream, debugStream);
            }
            catch (Exception exception) {
                System.out.println("GraphvizLayouter: Could not initialize debug output: "
                        + exception.getMessage());
            }
        }
        
        // parse the output stream of the dot process
        IAntlrParser dotParser = injector.getInstance(IAntlrParser.class);
        IParseResult parseResult = dotParser.parse(inputStream);
        if (debugStream != null) {
            try {
                debugStream.close();
            }
            catch (IOException exception) {}
        }
        if (!parseResult.getParseErrors().isEmpty()) {
            StringBuffer errorString = new StringBuffer("Errors in Graphviz output:");
            for (SyntaxError syntaxError : parseResult.getParseErrors()) {
                errorString.append("\n" + syntaxError.getNode().getLine()
                + ": " + syntaxError.getMessage());
            }
            throw new KielerException(errorString.toString());
        }
        GraphvizModel graphvizModel = (GraphvizModel)parseResult.getRootASTElement();
        if (graphvizModel.getGraphs().isEmpty())
            throw new KielerException("No output from the Graphviz process.");
        
        Graph graph = graphvizModel.getGraphs().get(0);
        NumberFormat localFormat = NumberFormat.getNumberInstance();
        Point boundingBox = null;
        for (Statement statement : graph.getStatements()) {
            
            if (statement instanceof NodeStatement) {
                // process a node
                NodeStatement nodeStatement = (NodeStatement)statement;
                KNode knode = (KNode)graphElementMap.get(nodeStatement.getNode().getName());
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
                AttributeList attributes = nodeStatement.getAttributes();
                String position = null;
                for (Attribute attribute : attributes.getEntries()) {
                    try {
                        if (attribute.getName().equals(GraphvizAPI.ATTR_POS))
                            position = attribute.getValue();
                        else if (attribute.getName().equals(GraphvizAPI.ATTR_WIDTH))
                            nodeLayout.setWidth(localFormat.parse(attribute.getValue())
                                    .floatValue() * DPI);
                        else if (attribute.getName().equals(GraphvizAPI.ATTR_HEIGHT))
                            nodeLayout.setHeight(localFormat.parse(attribute.getValue())
                                    .floatValue() * DPI);
                    }
                    catch (ParseException exception) {}
                }
                if (position != null) {
                    try {
                        StringTokenizer tokenizer = new StringTokenizer(position, ATTRIBUTE_DELIM);
                        float xpos = localFormat.parse(tokenizer.nextToken()).floatValue();
                        float ypos = localFormat.parse(tokenizer.nextToken()).floatValue();
                        nodeLayout.setXpos(xpos - nodeLayout.getWidth() / 2 + minSpacing);
                        nodeLayout.setYpos(ypos - nodeLayout.getHeight() / 2 + minSpacing);
                    }
                    catch (ParseException exception) {}
                }
            }
            
            else if (statement instanceof EdgeStatement) {
                // process an edge
                EdgeStatement edgeStatement = (EdgeStatement)statement;
                Map<String, String> attributeMap = createAttributeMap(
                        edgeStatement.getAttributes());
                KEdge kedge = (KEdge)graphElementMap.get(attributeMap.get(
                        GraphvizAPI.ATTR_COMMENT));
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
                String posString = attributeMap.get(GraphvizAPI.ATTR_POS);
                ArrayList<Float> posList = new ArrayList<Float>();
                StringTokenizer posTokenizer = new StringTokenizer(posString, ATTRIBUTE_DELIM);
                while (posTokenizer.hasMoreTokens()) {
                    try {
                        float pos = localFormat.parse(posTokenizer.nextToken()).floatValue();
                        posList.add(Float.valueOf(pos));
                    }
                    catch (ParseException exception) {}
                }

                /* GraphViz uses cubic B-Splines, some generalization of Bezier
                 * curves. The B-Splines are converted here to a polyline that
                 * is understood by Eclipse. See Eclipse bugs 234808 and 168307
                 * on addressing Bezier curves in Eclipse.
                 */

                // first two points in list denote the start point
                KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                sourcePoint.setX(posList.get(0) + minSpacing);
                sourcePoint.setY(posList.get(1) + minSpacing);
                edgeLayout.setSourcePoint(sourcePoint);

                for (int i = 0; i < posList.size() - 7; i += 6) {
                    // convert the bezier representation to a polyline
                    bezierToPolyline(edgeLayout, new Point(posList.get(i + 0), posList.get(i + 1)),
                            new Point(posList.get(i + 2), posList.get(i + 3)),
                            new Point(posList.get(i + 4), posList.get(i + 5)),
                            new Point(posList.get(i + 6), posList.get(i + 7)));
                }
                // need to remove the last grid point, as this is the same as
                // the target point below
                edgeLayout.getBendPoints().remove(edgeLayout.getBendPoints().size() - 1);

                // last two points in the GraphViz list denote the end point
                KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                targetPoint.setX(posList.get(posList.size() - 2) + minSpacing);
                targetPoint.setY(posList.get(posList.size() - 1) + minSpacing);
                edgeLayout.setTargetPoint(targetPoint);

                // process the edge labels
                for (KLabel label : kedge.getLabels()) {
                    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(label);
                    EdgeLabelPlacement labelPlacement = LayoutOptions
                            .getEdgeLabelPlacement(shapeLayout);
                    String labelLoc = "0 0";
                    switch (labelPlacement) {
                    case HEAD:
                        labelLoc = attributeMap.get(GraphvizAPI.ATTR_HEADLP);
                        break;
                    case CENTER:
                        labelLoc = attributeMap.get(GraphvizAPI.ATTR_LP);
                        break;
                    case TAIL:
                        labelLoc = attributeMap.get(GraphvizAPI.ATTR_TAILLP);
                        break;
                    }
                    try {
                        StringTokenizer tokenizer = new StringTokenizer(labelLoc, ATTRIBUTE_DELIM);
                        shapeLayout.setXpos(localFormat.parse(tokenizer.nextToken()).floatValue()
                                + minSpacing);
                        shapeLayout.setYpos(localFormat.parse(tokenizer.nextToken()).floatValue()
                                + minSpacing);
                    }
                    catch (ParseException exception) {}
                }
            }
            
            else if (statement instanceof AttributeStatement) {
                // process an attribute
                AttributeStatement attributeStatement = (AttributeStatement)statement;
                if (attributeStatement.getType() == AttributeType.GRAPH) {
                    AttributeList attributes = attributeStatement.getAttributes();
                    for (Attribute attribute : attributes.getEntries()) {
                        if (attribute.getName().equals(GraphvizAPI.ATTR_BOUNDINGBOX)) {
                            try {
                                StringTokenizer tokenizer = new StringTokenizer(
                                        attribute.getValue(), ATTRIBUTE_DELIM);
                                float xoffset = localFormat.parse(tokenizer.nextToken()).floatValue();
                                float yoffset = localFormat.parse(tokenizer.nextToken()).floatValue();
                                float width = localFormat.parse(tokenizer.nextToken()).floatValue();
                                float height = localFormat.parse(tokenizer.nextToken()).floatValue();
                                boundingBox = new Point(width - xoffset, height - yoffset);
                            }
                            catch (ParseException exception) {}
                        }
                    }
                }
            }
        }
        
        // set parent node attributes
        if (boundingBox != null) {
            KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
            KInsets insets = LayoutOptions.getInsets(parentLayout);
            parentLayout.setWidth(boundingBox.x + 2 * minSpacing
                    + insets.getLeft() + insets.getRight());
            parentLayout.setHeight(boundingBox.y + 2 * minSpacing
                    + insets.getTop() + insets.getBottom());
        }
    }
    
    /**
     * Converts a list of attributes to a map of attribute names to their values.
     * 
     * @param attributes attribute list
     * @return a hash map that contains all given attributes
     */
    private static Map<String, String> createAttributeMap(AttributeList attributes) {
        Map<String, String> attributeMap = new HashMap<String, String>(
                attributes.getEntries().size());
        for (Attribute attribute : attributes.getEntries()) {
            attributeMap.put(attribute.getName(), attribute.getValue());
        }
        return attributeMap;
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
    
}
