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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.antlr.IAntlrParser;
import org.eclipse.xtext.parsetree.SyntaxError;
import org.eclipse.xtext.parsetree.reconstr.SerializerUtil;

import com.google.inject.Injector;

import de.cau.cs.kieler.kiml.graphviz.DotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.Attribute;
import de.cau.cs.kieler.kiml.graphviz.dot.AttributeList;
import de.cau.cs.kieler.kiml.graphviz.dot.AttributeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.AttributeType;
import de.cau.cs.kieler.kiml.graphviz.dot.DotFactory;
import de.cau.cs.kieler.kiml.graphviz.dot.EdgeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.EdgeTarget;
import de.cau.cs.kieler.kiml.graphviz.dot.Graph;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphType;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.Node;
import de.cau.cs.kieler.kiml.graphviz.dot.NodeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.Statement;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.ForkedOutputStream;
import de.cau.cs.kieler.core.util.ForwardingInputStream;
import de.cau.cs.kieler.core.util.KielerMath;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.core.util.KielerMath.Point;;

/**
 * Layouter that calls Graphviz through a child process to perform layout. The
 * graph structure and layout information is passed through a textual format
 * called Dot, see the <a href="http://www.graphviz.org/doc/info/lang.html"> Dot
 * language specification</a>. Serialization and parsing of this textual format
 * is done using <a href="http://www.eclipse.org/modeling/tmf/">Xtext</a>.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GraphvizLayouter {

    /** layout option identifier for spline points factor. */
    public static final String OPT_SPLINE_POINTS = "de.cau.cs.kieler.kiml.graphviz.options.splinePoints";
    /** command for Dot layout. */
    public static final String DOT_COMMAND = "dot";
    /** command for Neato layout. */
    public static final String NEATO_COMMAND = "neato";
    /** command for Twopi layout. */
    public static final String TWOPI_COMMAND = "twopi";
    /** command for Fdp layout. */
    public static final String FDP_COMMAND = "fdp";
    /** command for Circo layout. */
    public static final String CIRCO_COMMAND = "circo";
    /** default value for minimal spacing. */
    public static final float DEF_MIN_SPACING = 16.0f;
    /** if true, debug output is enabled, which writes dot files to the home folder. */
    public static final boolean ENABLE_DEBUG = false;
    
    /** dots per inch specification, needed by Graphviz for some values. */
    private static final float DPI = 72.0f;
    /** set of delimiters used to parse attribute values. */
    private static final String ATTRIBUTE_DELIM = "\", \t\n\r";
    /** multiplier for font sizes. */
    private static final double FONT_MULTIPLIER = 1.4;

    /** maps each identifier of a graph element to the instance of the element. */
    private HashMap<String, KGraphElement> graphElementMap = new HashMap<String, KGraphElement>();
    /** the Google injector used to create the Xtext serializer and parser. */
    private Injector injector = null;
    /** offset value to be added to all coordinates. */
    private float offset;
    /** base file name for debug output. */
    private String debugFileName;

    /** amount of work for small tasks. */
    private static final int SMALL_TASK = 5;
    /** amount of work for large tasks. */
    private static final int LARGE_TASK = 10;
    
    /**
     * Performs the actual work of the layout process. Translates the KNode into
     * a structure GraphViz understands, calls the desired GraphViz layouter and
     * annotates the KLayoutGraph with the position and size information
     * provided by GraphViz.
     * 
     * @param parentNode the node to process
     * @param progressMonitor a monitor to which progress is reported
     * @param command Graphviz command to use, determines the layout algorithm
     * @throws KielerException if Graphviz layout fails
     */
    public void layout(final KNode parentNode, final IKielerProgressMonitor progressMonitor,
            final String command) throws KielerException {
        // check the command
        if (!DOT_COMMAND.equals(command) && !NEATO_COMMAND.equals(command)
                && !TWOPI_COMMAND.equals(command) && !FDP_COMMAND.equals(command)
                && !CIRCO_COMMAND.equals(command)) {
            throw new KielerException("Invalid Graphviz command set for this layout provider.");
        }

        progressMonitor.begin("Graphviz layout (" + command + ")",
                SMALL_TASK + SMALL_TASK + LARGE_TASK + LARGE_TASK + LARGE_TASK + SMALL_TASK);
        graphElementMap.clear();
        if (ENABLE_DEBUG) {
            debugFileName = Integer.toString(parentNode.hashCode());
        }
        // return if there is nothing in this node
        if (parentNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }

        // create the Google injector for the Dot language
        if (injector == null) {
            injector = new DotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }

        // start the graphviz process
        Process graphvizProcess = GraphvizAPI.startProcess(command,
                progressMonitor.subTask(SMALL_TASK));

        try {
            // translate the KGraph to Graphviz and write to the process
            GraphvizModel graphvizInput = createDotGraph(parentNode, command,
                    progressMonitor.subTask(SMALL_TASK));
            writeDotGraph(graphvizInput, new BufferedOutputStream(
                    graphvizProcess.getOutputStream()), progressMonitor.subTask(LARGE_TASK));

            // wait for Graphviz to give some output
            GraphvizAPI.waitForInput(graphvizProcess.getInputStream(),
                    graphvizProcess.getErrorStream(), progressMonitor.subTask(LARGE_TASK));

            // read graphviz output and apply layout information to the KGraph
            GraphvizModel graphvizOutput = readDotGraph(new BufferedInputStream(
                    graphvizProcess.getInputStream()), progressMonitor.subTask(LARGE_TASK));
            retrieveLayoutResult(parentNode, graphvizOutput,
                    progressMonitor.subTask(SMALL_TASK));
        } finally {
            // destroy the process to release resources
            graphvizProcess.destroy();
            progressMonitor.done();
        }
    }

    /**
     * Maps a {@code KNode} to the internal Graphviz data structure used by the
     * Dot parser.
     * 
     * @param parent a {@code KNode} with the graph to layout
     * @param command the command string identifying the layout method
     * @param monitor progress monitor
     * @return an instance of a graphviz model that corresponds to the input
     *         graph
     */
    private GraphvizModel createDotGraph(final KNode parent, final String command,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Create Dot model", 1);
        GraphvizModel graphvizModel = DotFactory.eINSTANCE.createGraphvizModel();
        Graph graph = DotFactory.eINSTANCE.createGraph();
        graph.setType(GraphType.DIGRAPH);
        graphvizModel.getGraphs().add(graph);

        // set attributes for the whole graph
        AttributeStatement graphAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
        graphAttrStatement.setType(AttributeType.GRAPH);
        AttributeList graphAttrs = DotFactory.eINSTANCE.createAttributeList();
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parent);
        // set minimal spacing
        float minSpacing = LayoutOptions.getMinSpacing(parentLayout);
        if (Float.isNaN(minSpacing)) {
            minSpacing = DEF_MIN_SPACING;
        }
        String spacingVal = Float.toString(minSpacing / DPI);
        if (command.equals(DOT_COMMAND) || command.equals(TWOPI_COMMAND)) {
            graphAttrs.getEntries().add(
                    createAttribute(GraphvizAPI.ATTR_RANKSEP, Float.toString(2 * minSpacing / DPI)));
        }
        if (command.equals(CIRCO_COMMAND)) {
            graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_MINDIST, spacingVal));
        } else if (command.equals(NEATO_COMMAND) || command.equals(FDP_COMMAND)) {
            AttributeStatement edgeAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
            edgeAttrStatement.setType(AttributeType.EDGE);
            AttributeList edgeAttrs = DotFactory.eINSTANCE.createAttributeList();
            edgeAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_EDGELEN, spacingVal));
            edgeAttrStatement.setAttributes(edgeAttrs);
            graph.getStatements().add(edgeAttrStatement);
        }
        // set offset to border
        offset = LayoutOptions.getBorderSpacing(parentLayout);
        if (Float.isNaN(offset)) {
            offset = DEF_MIN_SPACING / 2;
        }
        // set layout direction
        if (command.equals(DOT_COMMAND)) {
            switch (LayoutOptions.getLayoutDirection(parentLayout)) {
            case DOWN:
                graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "TB"));
                break;
            case UP:
                graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "BT"));
                break;
            case LEFT:
                graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "RL"));
                break;
            default:
                graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "LR"));
                break;
            }
        }
        // enable node overlap avoidance
        if (!command.equals(DOT_COMMAND)) {
            graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_OVERLAP, "false"));
        }
        // enable drawing of splines
        graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_SPLINES, "true"));
        // configure initial placement of nodes
        if (command.equals(NEATO_COMMAND) || command.equals(FDP_COMMAND)) {
            int seed = LayoutOptions.getRandomSeed(parentLayout);
            graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_START, "random" + seed));
        }
        graphAttrStatement.setAttributes(graphAttrs);
        graph.getStatements().add(graphAttrStatement);
        // get interactive layout option
        boolean interactive = LayoutOptions.isInteractive(parentLayout);

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
            // set label - removed as it is currently not needed for layout
            // KLabel label = childNode.getLabel();
            // attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_LABEL,
            // createString(label.getText())));
            // set width and height
            if (!LayoutOptions.isFixedSize(shapeLayout)) {
                KimlLayoutUtil.resizeNode(childNode);
            }
            String width = Float.toString(shapeLayout.getWidth() / DPI);
            String height = Float.toString(shapeLayout.getHeight() / DPI);
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_WIDTH, width));
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_HEIGHT, height));
            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_FIXEDSIZE, "true"));
            // add node position if interactive layout is chosen
            if (interactive) {
                float xpos = (shapeLayout.getXpos() - offset) / DPI;
                float ypos = (parentLayout.getHeight() - shapeLayout.getYpos() - offset) / DPI;
                String posString = "\"" + Float.toString(xpos) + "," + Float.toString(ypos) + "\"";
                attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_POS, posString));
            }
            // set node shape
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
                    // disable drawing arrows for the edges
                    attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_EDGEDIR, "none"));
                    // as Graphviz only supports positioning of a single label, all labels
                    // are stacked to one big label as workaround
                    StringBuffer unifiedLabel = new StringBuffer();
                    for (KLabel label : outgoingEdge.getLabels()) {
                        if (unifiedLabel.length() > 0) {
                            unifiedLabel.append("\\n");
                        }
                        unifiedLabel.append(label.getText());
                    }
                    if (unifiedLabel.length() > 0) {
                        attributes.getEntries().add(
                                createAttribute(GraphvizAPI.ATTR_LABEL, createString(unifiedLabel
                                        .toString())));
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(outgoingEdge
                                .getLabels().get(0));
                        String fontName = LayoutOptions.getFontName(labelLayout);
                        if (fontName != null) {
                            attributes.getEntries().add(
                                    createAttribute(GraphvizAPI.ATTR_FONTNAME, createString(fontName)));
                            // increase the font size to let Graphviz prepare
                            // more room for the label
                            int fontSize = (int) (LayoutOptions.getFontSize(labelLayout)
                                    * FONT_MULTIPLIER);
                            attributes.getEntries().add(
                                    createAttribute(GraphvizAPI.ATTR_FONTSIZE, Integer
                                            .toString(fontSize)));
                        }
                    }
                    // add comment with edge identifier
                    String edgeID = getEdgeID(outgoingEdge);
                    attributes.getEntries().add(
                            createAttribute(GraphvizAPI.ATTR_COMMENT, "\"" + edgeID + "\""));
                    graphElementMap.put(edgeID, outgoingEdge);

                    edgeStatement.setAttributes(attributes);
                    graph.getStatements().add(edgeStatement);
                }
            }
        }

        monitor.done();
        return graphvizModel;
    }

    /**
     * Creates an attribute with given name and value for the Dot graph.
     * 
     * @param name name of the attribute
     * @param value value of the attribute
     * @return instance of a Dot attribute
     */
    private static Attribute createAttribute(final String name, final String value) {
        Attribute attribute = DotFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }

    /** first character that is not replaced by underscore. */
    private static final char MIN_OUT_CHAR = 32;
    /** last character that is not replaced by underscore. */
    private static final char MAX_OUT_CHAR = 126;
    
    /**
     * Creates a properly parsable string by adding the escape character '\\'
     * wherever needed and replacing illegal characters.
     * 
     * @param label a label string from the KGraph structure
     * @return string to be used in the Graphviz model
     */
    private static String createString(final String label) {
        StringBuffer escapeBuffer = new StringBuffer(label.length() + 2);
        // prefix the label with an underscore to prevent it from being equal to
        // a keyword
        escapeBuffer.append("\"_");
        for (int i = 0; i < label.length(); i++) {
            char c = label.charAt(i);
            if (c == '\"' || c == '\\' || c > MAX_OUT_CHAR) {
                escapeBuffer.append('_');
            } else if (c == '\n') {
                escapeBuffer.append("\\n");
            } else if (c >= MIN_OUT_CHAR) {
                escapeBuffer.append(c);
            }
        }
        escapeBuffer.append('\"');
        return escapeBuffer.toString();
    }

    /**
     * Writes a serialized version of the Graphviz model to the given output
     * stream. The output stream is closed after writing.
     * 
     * @param graphvizModel Graphviz model to serialize
     * @param processStream output stream to which the model is written
     * @param monitor a monitor to which progress is reported
     * @throws KielerException if writing to the output stream fails
     */
    private void writeDotGraph(final GraphvizModel graphvizModel, final OutputStream processStream,
            final IKielerProgressMonitor monitor) throws KielerException {
        monitor.begin("Serialize model", 1);
        OutputStream outputStream = processStream;
        // enable debug output if needed
        FileOutputStream debugStream = null;
        if (ENABLE_DEBUG) {
            try {
                String path = System.getProperty("user.home");
                if (path.endsWith(File.separator)) {
                    path += "tmp";
                } else {
                    path += File.separator + "tmp";
                }
                debugStream = new FileOutputStream(new File(path + File.separator + debugFileName
                        + "-in.dot"));
                outputStream = new ForkedOutputStream(processStream, debugStream);
            } catch (Exception exception) {
                System.out.println("GraphvizLayouter: Could not initialize debug output: "
                        + exception.getMessage());
            }
        }

        SerializerUtil serializerUtil = injector.getInstance(SerializerUtil.class);
        try {
            serializerUtil.serialize(graphvizModel, outputStream, null, false);
            outputStream.flush();
        } catch (IOException exception) {
            throw new KielerException("Failed to serialize the Dot graph.", exception);
        } finally {
            try {
                outputStream.close();
            } catch (IOException exception) {
                // ignore exception
            }
        }
        monitor.done();
    }

    /**
     * Creates a unique identifier for the given node.
     * 
     * @param node node for which an identifier shall be created
     * @return a unique string used to identify the node
     */
    private String getNodeID(final KNode node) {
        return "node" + node.hashCode();
    }

    /**
     * Creates a unique identifier for the given edge.
     * 
     * @param edge edge for which an identifier shall be created
     * @return a unique string used to identify the edge
     */
    private String getEdgeID(final KEdge edge) {
        return "edge" + edge.hashCode();
    }

    /**
     * Reads and parses a serialized Graphviz model.
     * 
     * @param inputStream input stream from which the model is read
     * @param monitor a monitor to which progress is reported
     * @return an instance of the parsed graphviz model
     * @throws KielerException if reading from the input stream fails, or the
     *             parser fails to parse the model
     */
    private GraphvizModel readDotGraph(final InputStream processStream,
            final IKielerProgressMonitor monitor)
            throws KielerException {
        monitor.begin("Parse output", 1);
        InputStream inputStream = processStream;
        // enable debug output if needed
        FileOutputStream debugStream = null;
        if (ENABLE_DEBUG) {
            try {
                String path = System.getProperty("user.home");
                if (path.endsWith(File.separator)) {
                    path += "tmp";
                } else {
                    path += File.separator + "tmp";
                }
                debugStream = new FileOutputStream(new File(path + File.separator + debugFileName
                        + "-out.dot"));
                inputStream = new ForwardingInputStream(processStream, debugStream);
            } catch (Exception exception) {
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
            } catch (IOException exception) {
                // ignore exception
            }
        }
        if (!parseResult.getParseErrors().isEmpty()) {
            StringBuffer errorString = new StringBuffer("Errors in Graphviz output:");
            for (SyntaxError syntaxError : parseResult.getParseErrors()) {
                errorString.append("\n" + syntaxError.getNode().getLine() + ": "
                        + syntaxError.getMessage());
            }
            throw new KielerException(errorString.toString());
        }
        GraphvizModel graphvizModel = (GraphvizModel) parseResult.getRootASTElement();
        if (graphvizModel.getGraphs().isEmpty()) {
            throw new KielerException("No output from the Graphviz process.");
        }

        monitor.done();
        return graphvizModel;
    }

    /**
     * Applies layout information from a Graphviz model to the original graph.
     * <p>
     * GraphViz uses cubic B-Splines, some generalization of Bezier curves. The
     * B-Splines are converted here to a polyline that is understood by Eclipse.
     * See Eclipse bugs 234808 and 168307 on addressing Bezier curves in
     * Eclipse.
     * 
     * @param parentNode parent node of the original graph
     * @param graphvizModel graphviz model with layout information
     * @param monitor progress monitor
     */
    private void retrieveLayoutResult(final KNode parentNode, final GraphvizModel graphvizModel,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Transfer layout result", 1);
        Graph graph = graphvizModel.getGraphs().get(0);
        Point boundingBox = null;
        float edgeOffsetx = offset, edgeOffsety = offset;
        for (Statement statement : graph.getStatements()) {

            if (statement instanceof NodeStatement) {
                // process a node
                NodeStatement nodeStatement = (NodeStatement) statement;
                KNode knode = (KNode) graphElementMap.get(nodeStatement.getNode().getName());
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
                AttributeList attributes = nodeStatement.getAttributes();
                float xpos = 0.0f, ypos = 0.0f, width = 0.0f, height = 0.0f;
                for (Attribute attribute : attributes.getEntries()) {
                    try {
                        if (attribute.getName().equals(GraphvizAPI.ATTR_POS)) {
                            StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                    ATTRIBUTE_DELIM);
                            xpos = Float.parseFloat(tokenizer.nextToken()) + offset;
                            ypos = Float.parseFloat(tokenizer.nextToken()) + offset;
                        } else if (attribute.getName().equals(GraphvizAPI.ATTR_WIDTH)) {
                            StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                    ATTRIBUTE_DELIM);
                            width = Float.parseFloat(tokenizer.nextToken()) * DPI;
                        } else if (attribute.getName().equals(GraphvizAPI.ATTR_HEIGHT)) {
                            StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                    ATTRIBUTE_DELIM);
                            height = Float.parseFloat(tokenizer.nextToken()) * DPI;
                        }
                    } catch (NumberFormatException exception) {
                        // ignore exception
                    }
                }
                nodeLayout.setXpos(xpos - width / 2);
                nodeLayout.setYpos(ypos - height / 2);
                
            } else if (statement instanceof EdgeStatement) {
                // process an edge
                EdgeStatement edgeStatement = (EdgeStatement) statement;
                Map<String, String> attributeMap = createAttributeMap(edgeStatement.getAttributes());
                KEdge kedge = (KEdge) graphElementMap.get(attributeMap.get(GraphvizAPI.ATTR_COMMENT));
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
                edgeLayout.getBendPoints().clear();
                String posString = attributeMap.get(GraphvizAPI.ATTR_POS);
                
                // parse the list of spline control coordinates
                List<List<Point>> splines = new LinkedList<List<Point>>();
                Pair<KPoint, KPoint> endpoints = parseSplinePoints(posString, splines,
                        edgeOffsetx, edgeOffsety);

                // the first point in the list is the start point, if no arrowhead is given
                KPoint sourcePoint = endpoints.getFirst();
                if (sourcePoint == null) {
                    sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    Point firstPoint = splines.get(0).get(0);
                    sourcePoint.setX((float)firstPoint.x);
                    sourcePoint.setY((float)firstPoint.y);
                }
                edgeLayout.setSourcePoint(sourcePoint);

                // convert the B-spline representation to a polyline
                splineToPolyline(edgeLayout, splines);

                // the last point in the list is the end point, if no arrowhead is given
                KPoint targetPoint = endpoints.getSecond();
                if (targetPoint == null) {
                    targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    List<Point> points = splines.get(splines.size() - 1);
                    Point lastPoint = points.get(points.size() - 1);
                    targetPoint.setX((float)lastPoint.x);
                    targetPoint.setY((float)lastPoint.y);
                }
                edgeLayout.setTargetPoint(targetPoint);

                // process the edge labels
                String labelPos = attributeMap.get(GraphvizAPI.ATTR_LABELPOS);
                if (labelPos != null) {
                    float combinedWidth = 0.0f, combinedHeight = 0.0f;
                    for (KLabel label : kedge.getLabels()) {
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                        combinedWidth = Math.max(combinedWidth, labelLayout.getWidth());
                        combinedHeight += labelLayout.getHeight();
                    }
                    try {
                        StringTokenizer tokenizer = new StringTokenizer(labelPos, ATTRIBUTE_DELIM);
                        float xpos = Float.parseFloat(tokenizer.nextToken()) - combinedWidth / 2
                                + edgeOffsetx;
                        float ypos = Float.parseFloat(tokenizer.nextToken()) - combinedHeight / 2
                                + edgeOffsety;
                        for (KLabel label : kedge.getLabels()) {
                            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                            labelLayout.setXpos(xpos);
                            labelLayout.setYpos(ypos);
                            ypos += labelLayout.getHeight();
                        }
                    } catch (NumberFormatException exception) {
                        // ignore exception
                    }
                }
                
            } else if (statement instanceof AttributeStatement) {
                // process an attribute
                AttributeStatement attributeStatement = (AttributeStatement) statement;
                if (attributeStatement.getType() == AttributeType.GRAPH) {
                    AttributeList attributes = attributeStatement.getAttributes();
                    for (Attribute attribute : attributes.getEntries()) {
                        if (attribute.getName().equals(GraphvizAPI.ATTR_BOUNDINGBOX)) {
                            try {
                                StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                        ATTRIBUTE_DELIM);
                                float leftx = Float.parseFloat(tokenizer.nextToken());
                                float bottomy = Float.parseFloat(tokenizer.nextToken());
                                float rightx = Float.parseFloat(tokenizer.nextToken());
                                float topy = Float.parseFloat(tokenizer.nextToken());
                                boundingBox = new Point(rightx - leftx, bottomy - topy);
                                // on some platforms the edges have an offset, but the nodes don't
                                //  -- maybe a Graphviz bug?
                                edgeOffsetx -= leftx;
                                edgeOffsety -= topy;
                            } catch (NumberFormatException exception) {
                                // ignore exception
                            }
                        }
                    }
                }
            }
        }

        // set parent node attributes
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        if (boundingBox != null) {
            KInsets insets = LayoutOptions.getInsets(parentLayout);
            parentLayout.setWidth((float)boundingBox.x + insets.getLeft()
                    + insets.getRight() + 2 * offset);
            parentLayout.setHeight((float)boundingBox.y + insets.getTop()
                    + insets.getBottom() + 2 * offset);
        }
        monitor.done();
    }

    /**
     * Converts a list of attributes to a map of attribute names to their
     * values.
     * 
     * @param attributes attribute list
     * @return a hash map that contains all given attributes
     */
    private static Map<String, String> createAttributeMap(final AttributeList attributes) {
        Map<String, String> attributeMap = new HashMap<String, String>(attributes.getEntries().size());
        for (Attribute attribute : attributes.getEntries()) {
            attributeMap.put(attribute.getName(), attribute.getValue());
        }
        return attributeMap;
    }
    
    /**
     * Puts the points of a position string into a list of splines.
     * 
     * @param posString string with spline points
     * @param splines list of splines
     * @param offsetx offset in x coordinate
     * @param offsety offset in y coordinate
     * @return the source and the target point, if specified by the position string
     */
    private static Pair<KPoint, KPoint> parseSplinePoints(final String posString,
            final List<List<Point>> splines, final float offsetx, final float offsety) {
        KPoint sourcePoint = null, targetPoint = null;
        StringTokenizer splinesTokenizer = new StringTokenizer(posString, ";");
        while (splinesTokenizer.hasMoreTokens()) {
            ArrayList<Point> pointList = new ArrayList<Point>(SPLINE_POINTS);
            StringTokenizer posTokenizer = new StringTokenizer(
                    splinesTokenizer.nextToken(), ATTRIBUTE_DELIM);
            boolean isXcoord = true, isStartp = false, isEndp = false;
            Point point = null;
            while (posTokenizer.hasMoreTokens()) {
                String token = posTokenizer.nextToken();
                if (token.startsWith("s")) {
                    if (sourcePoint != null && posTokenizer.countTokens() >= 2) {
                        posTokenizer.nextToken();
                        posTokenizer.nextToken();
                        continue;
                    }
                    sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    isStartp = true;
                } else if (token.startsWith("e")) {
                    if (targetPoint != null && posTokenizer.countTokens() >= 2) {
                        posTokenizer.nextToken();
                        posTokenizer.nextToken();
                        continue;
                    }
                    targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    isEndp = true;
                } else {
                    try {
                        int backslashIndex = token.indexOf('\\');
                        if (backslashIndex >= 0) {
                            token = token.substring(0, backslashIndex) + posTokenizer.nextToken();
                        }
                        float pos = Float.parseFloat(token)
                                + (isXcoord ? offsetx : offsety);
                        if (isStartp) {
                            if (isXcoord) {
                                sourcePoint.setX(pos);
                            } else {
                                sourcePoint.setY(pos);
                                isStartp = false;
                            }
                        } else if (isEndp) {
                            if (isXcoord) {
                                targetPoint.setX(pos);
                            } else {
                                targetPoint.setY(pos);
                                isEndp = false;
                            }
                        } else {
                            if (isXcoord) {
                                point = new Point(pos, 0);
                            } else {
                                point.y = pos;
                                pointList.add(point);
                            }
                        }
                        isXcoord = !isXcoord;
                    } catch (NumberFormatException exception) {
                        // ignore exception
                    }
                }
            }
            splines.add(pointList);
        }
        return new Pair<KPoint, KPoint>(sourcePoint, targetPoint);
    }

    /** fixed number of spline points. TODO add support for arbitrary number of spline points */
    private static final int SPLINE_POINTS = 4;
    
    /**
     * Transforms a Graphviz B-spline curve to a set of bend points.
     * 
     * @param edgeLayout layout to which the bend points are added
     * @param splines list of splines, where each spline is a list of control points
     */
    private void splineToPolyline(final KEdgeLayout edgeLayout, final List<List<Point>> splines) {
        List<KPoint> bendPoints = edgeLayout.getBendPoints();
        // get layout option for spline points factor
        float splinePointsFact = 1.0f;
        KFloatOption splinePointsOption = (KFloatOption) edgeLayout.getOption(OPT_SPLINE_POINTS);
        if (splinePointsOption != null) {
            splinePointsFact = splinePointsOption.getValue();
        }
        ListIterator<List<Point>> splinesIter = splines.listIterator();
        while (splinesIter.hasNext()) {
            List<Point> points = splinesIter.next();
            Point[] bezierPoints = KielerMath.calcBezierPoints(points,
                    Math.round(splinePointsFact * (points.size() - 1)));
            int pointsToAdd = (splinesIter.nextIndex() == splines.size()
                    ? bezierPoints.length - 1 : bezierPoints.length);
            for (int i = 0; i < pointsToAdd; i++) {
                KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                kpoint.setX((float)bezierPoints[i].x);
                kpoint.setY((float)bezierPoints[i].y);
                bendPoints.add(kpoint);
            }
        }
    }

}
