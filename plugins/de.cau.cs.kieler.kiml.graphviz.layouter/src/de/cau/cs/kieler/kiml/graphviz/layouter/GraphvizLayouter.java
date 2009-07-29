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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.antlr.IAntlrParser;
import org.eclipse.xtext.parsetree.SyntaxError;
import org.eclipse.xtext.parsetree.reconstr.SerializerUtil;

import com.google.inject.Injector;

import de.cau.cs.kieler.kiml.graphviz.DotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.*;
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
 * Layouter that calls Graphviz through a child process to perform layout.
 * The graph structure and layout information is passed through a textual
 * format called Dot, see the <a href="http://www.graphviz.org/doc/info/lang.html">
 * Dot language specification</a>. Serialization and parsing of this textual
 * format is done using <a href="http://www.eclipse.org/modeling/tmf/">Xtext</a>.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
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
    
    /** if true, debug output is enabled, which writes dot files to the home folder */
    private final static boolean ENABLE_DEBUG = false;
    /** number of milliseconds to wait if no input is available from the Graphviz process */
    private final static int PROCESS_INPUT_WAIT = 500;
    /** maximal number of characters that is read from the Graphviz error output */
    private final static int MAX_ERROR_OUTPUT = 512;
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
    
    /** the Google injector used to create the Xtext serializer and parser */
    private Injector injector = null;
    /** offset value to be added to all coordinates */
    private float offset;
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
        
        progressMonitor.begin("Graphviz layout (" + command + ")", 40);
        graphElementMap.clear();
        debugFileName = Integer.toString(parentNode.hashCode());
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
        Process graphvizProcess = GraphvizAPI.startProcess(command);
        progressMonitor.worked(10);

        // translate the KGraph to Graphviz and write to the process
        GraphvizModel graphvizInput = createDotGraph(parentNode, command);
        progressMonitor.worked(5);
        writeDotGraph(graphvizInput, new BufferedOutputStream(
        		graphvizProcess.getOutputStream()));
        progressMonitor.worked(5);

       	Thread.yield();
        progressMonitor.worked(10);
        
        // read graphviz output and apply layout information to the KGraph
        GraphvizModel graphvizOutput = readDotGraph(new BufferedInputStream(
        		graphvizProcess.getInputStream()), graphvizProcess.getErrorStream());
        progressMonitor.worked(5);
        retrieveLayoutResult(parentNode, graphvizOutput);
        
        // destroy the process to release resources
        graphvizProcess.destroy();

        progressMonitor.done();
    }
    
    /**
     * Maps a {@code KNode} to the internal Graphviz data structure used by the
     * Dot parser.
     * 
     * @param parent a {@code KNode} with the graph to layout
     * @param command the command string identifying the layout method
     * @return an instance of a graphviz model that corresponds to the input graph
     */
    private GraphvizModel createDotGraph(KNode parent, String command) {
        GraphvizModel graphvizModel = DotFactory.eINSTANCE.createGraphvizModel();
        Graph graph = DotFactory.eINSTANCE.createGraph();
        graph.setType(GraphType.DIGRAPH);
        graphvizModel.getGraphs().add(graph);

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
            		getLabelValue(childNode.getLabel())));
            if (LayoutOptions.isFixedSize(shapeLayout)) {
	            // set width and height
	            String width = Float.toString(shapeLayout.getWidth() / DPI);
	            String height = Float.toString(shapeLayout.getHeight() / DPI);
	            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_WIDTH, width));
	            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_HEIGHT, height));
	            attributes.getEntries().add(createAttribute(GraphvizAPI.ATTR_FIXEDSIZE, "true"));
            }
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
                                    GraphvizAPI.ATTR_LABEL, getLabelValue(label)));
                            break;
                        case HEAD:
                            attributes.getEntries().add(createAttribute(
                                    GraphvizAPI.ATTR_HEADLABEL, getLabelValue(label)));
                            break;
                        case TAIL:
                            attributes.getEntries().add(createAttribute(
                                    GraphvizAPI.ATTR_TAILLABEL, getLabelValue(label)));
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
        
        // set attributes for the whole graph
        AttributeStatement graphAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
        graphAttrStatement.setType(AttributeType.GRAPH);
        AttributeList graphAttrs = DotFactory.eINSTANCE.createAttributeList();
        KLayoutData parentLayout = KimlLayoutUtil.getShapeLayout(parent);
        // set minimal spacing and offset
        float minSpacing = LayoutOptions.getMinSpacing(parentLayout);
        if (Float.isNaN(minSpacing))
            minSpacing = DEF_MIN_SPACING;
        if (command.equals(DOT_COMMAND) || command.equals(TWOPI_COMMAND))
        	graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKSEP,
        			Float.toString(minSpacing / DPI)));
        if (command.equals(CIRCO_COMMAND))
        	graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_MINDIST,
        			Float.toString(minSpacing / DPI)));
        else if (command.equals(NEATO_COMMAND) || command.equals(FDP_COMMAND)) {
        	AttributeStatement edgeAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
        	edgeAttrStatement.setType(AttributeType.EDGE);
        	AttributeList edgeAttrs = DotFactory.eINSTANCE.createAttributeList();
        	edgeAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_EDGELEN,
        			Float.toString(minSpacing / DPI)));
        	edgeAttrStatement.setAttributes(edgeAttrs);
        	graph.getStatements().add(edgeAttrStatement);
        }
        offset = minSpacing;
        // set layout direction
        if (command.equals(DOT_COMMAND)) {
	        if (LayoutOptions.getLayoutDirection(parentLayout) == LayoutDirection.VERTICAL)
	            graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "BT"));
	        else
	            graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_RANKDIR, "LR"));
        }
        // enable overlap avoidance
        if (!command.equals(DOT_COMMAND))
        	graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_OVERLAP, "false"));
        // configure initial placement of nodes
        if (command.equals(NEATO_COMMAND) || command.equals(FDP_COMMAND))
        	graphAttrs.getEntries().add(createAttribute(GraphvizAPI.ATTR_START, "random1"));
        graphAttrStatement.setAttributes(graphAttrs);
        graph.getStatements().add(graphAttrStatement);
        
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
     * Creates a string used as label value from a given label.
     * 
     * @param label a label from the KGraph structure
     * @return string to be used in the Graphviz model
     */
    private String getLabelValue(KLabel label) {
        String labelValue = label.getText();
        if (labelValue.endsWith("\\"))
        	return "\"" + labelValue + " \"";
        else
        	return "\"" + labelValue + "\"";
    }

    /**
     * Writes a serialized version of the Graphviz model to the given output
     * stream. The output stream is closed after writing.
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
            	outputStream.close();
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
     * Reads and parses a serialized Graphviz model.
     * 
     * @param inputStream input stream from which the model is read
     * @param errorStream error stream that is checked for error messages
     * @return an instance of the parsed graphviz model
     * @throws KielerException if reading from the input stream fails, or an error message
     *     is caught from Graphviz, or the parser fails to parse the model
     */
    private GraphvizModel readDotGraph(InputStream inputStream, InputStream errorStream)
    		throws KielerException{
    	try {
    		// wait for a while if there is no input from Graphviz
    		// FIXME what if Graphviz takes longer to execute? this should be made dynamic
	    	if (inputStream.available() == 0)
	    		Thread.sleep(PROCESS_INPUT_WAIT);
	    	// read and check error stream if there is still no input from Graphv
        	if (inputStream.available() == 0) {
	            StringBuffer error = new StringBuffer();
	            while (error.length() < MAX_ERROR_OUTPUT
	            		&& errorStream.available() > 0) {
	                 error.append((char)errorStream.read());
	            }
	            // take this branch only if graphviz really indicated an error
	            if (error.length() > 0)
	                throw new KielerException("Graphviz error: " + error.toString());
        	}
        } catch (Exception exception) {}

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
        
        return graphvizModel;
    }
    
    /**
     * Applies layout information from a Graphviz model to the original graph.
     * 
     * @param parentNode parent node of the original graph
     * @param graphvizModel graphviz model with layout information
     */
    private void retrieveLayoutResult(KNode parentNode, GraphvizModel graphvizModel) {
        Graph graph = graphvizModel.getGraphs().get(0);
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
                            nodeLayout.setWidth(Float.parseFloat(attribute.getValue()) * DPI);
                        else if (attribute.getName().equals(GraphvizAPI.ATTR_HEIGHT))
                            nodeLayout.setHeight(Float.parseFloat(attribute.getValue()) * DPI);
                    }
                    catch (NumberFormatException exception) {}
                }
                if (position != null) {
                    try {
                        StringTokenizer tokenizer = new StringTokenizer(position, ATTRIBUTE_DELIM);
                        float xpos = Float.parseFloat(tokenizer.nextToken()) + offset;
                        float ypos = Float.parseFloat(tokenizer.nextToken()) + offset;
                        nodeLayout.setXpos(xpos - nodeLayout.getWidth() / 2);
                        nodeLayout.setYpos(ypos - nodeLayout.getHeight() / 2);
                    }
                    catch (NumberFormatException exception) {}
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
                        float pos = Float.parseFloat(posTokenizer.nextToken()) + offset;
                        posList.add(Float.valueOf(pos));
                    }
                    catch (NumberFormatException exception) {}
                }

                /* GraphViz uses cubic B-Splines, some generalization of Bezier
                 * curves. The B-Splines are converted here to a polyline that
                 * is understood by Eclipse. See Eclipse bugs 234808 and 168307
                 * on addressing Bezier curves in Eclipse.
                 */

                // first two points in list denote the start point
                KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                sourcePoint.setX(posList.get(0));
                sourcePoint.setY(posList.get(1));
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
                targetPoint.setX(posList.get(posList.size() - 2));
                targetPoint.setY(posList.get(posList.size() - 1));
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
                        labelLoc = attributeMap.get(GraphvizAPI.ATTR_LABELPOS);
                        break;
                    case TAIL:
                        labelLoc = attributeMap.get(GraphvizAPI.ATTR_TAILLP);
                        break;
                    }
                    try {
                        StringTokenizer tokenizer = new StringTokenizer(labelLoc, ATTRIBUTE_DELIM);
                        shapeLayout.setXpos(Float.parseFloat(tokenizer.nextToken()) + offset);
                        shapeLayout.setYpos(Float.parseFloat(tokenizer.nextToken()) + offset);
                    }
                    catch (NumberFormatException exception) {}
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
                                float xoffset = Float.parseFloat(tokenizer.nextToken());
                                float yoffset = Float.parseFloat(tokenizer.nextToken());
                                float width = Float.parseFloat(tokenizer.nextToken());
                                float height = Float.parseFloat(tokenizer.nextToken());
                                boundingBox = new Point(width - xoffset, height - yoffset);
                            }
                            catch (NumberFormatException exception) {}
                        }
                    }
                }
            }
        }
        
        // set parent node attributes
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        if (boundingBox != null) {
            KInsets insets = LayoutOptions.getInsets(parentLayout);
            parentLayout.setWidth(boundingBox.x + insets.getLeft() + insets.getRight()
            		+ 2 * offset);
            parentLayout.setHeight(boundingBox.y + insets.getTop() + insets.getBottom()
            		+ 2 * offset);
        }
        LayoutOptions.setFixedSize(parentLayout, true);
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
            bendPoint.setX(x);
            bendPoint.setY(y);
            bendPoints.add(bendPoint);
        }
    }
    
}
