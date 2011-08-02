/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.dot.transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphvizDotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Attribute;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.AttributeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.AttributeType;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.DotFactory;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.EdgeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.EdgeTarget;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Graph;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphType;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Node;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.NodeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Statement;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * This class implements a transformation from the KGraph metamodel to the Dot
 * metamodel. Furthermore it contains functionality to apply layout information
 * attached to a Dot model to a KGraph model.
 * 
 * @author mri
 */
public class KGraphDotTransformation {
    
    /** the injector for creation of resources. */
    private static Injector injector;
    
    /**
     * Return a new instance of a resource set for parsing and serializing.
     * 
     * @return a resource set
     */
    public XtextResourceSet createResourceSet() {
        if (injector == null) {
            injector = new GraphvizDotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector.getInstance(XtextResourceSet.class);
    }

    /** definition of Graphviz commands. */
    public enum Command {
        /** invalid command. */
        INVALID,
        /** command for Dot layout. */
        DOT,
        /** command for Neato layout. */
        NEATO,
        /** command for Twopi layout. */
        TWOPI,
        /** command for Fdp layout. */
        FDP,
        /** command for Circo layout. */
        CIRCO;
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return super.toString().toLowerCase(Locale.ENGLISH);
        }
    }
    
    /** layout option identifier for label distance. */
    public static final String LABEL_DISTANCE_ID =
            "de.cau.cs.kieler.graphviz.labelDistance";
    /** label distance property. */
    public static final IProperty<Float> LABEL_DISTANCE = new Property<Float>(
            LABEL_DISTANCE_ID, 1.0f);
    
    /** small default value for minimal spacing. */
    public static final float DEF_SPACING_SMALL = 16.0f;
    /** large default value for minimal spacing. */
    public static final float DEF_SPACING_LARGE = 40.0f;
    /** extra-large default value for minimal spacing. */
    public static final float DEF_SPACING_XLARGE = 60.0f;
    
    /** Bounding box. */
    private static final String ATTR_BOUNDINGBOX = "bb";
    /** Comment. */
    private static final String ATTR_COMMENT = "comment";
    /** Set edge type for drawing arrowheads. */
    private static final String ATTR_EDGEDIR = "dir";
    /** Preferred edge length, in inches (fdp, neato only). */
    private static final String ATTR_EDGELEN = "len";
    /**
     * If true, the node size is specified by the values of the width and height
     * attributes only and is not expanded to contain the text label.
     */
    private static final String ATTR_FIXEDSIZE = "fixedsize";
    /** Font used for text. */
    private static final String ATTR_FONTNAME = "fontname";
    /** Font size, in points, used for text. */
    private static final String ATTR_FONTSIZE = "fontsize";
    /** Text label to be placed near head of edge. */
    private static final String ATTR_HEADLABEL = "headlabel";
    /** Head label position, in points. */
    private static final String ATTR_HEADLP = "head_lp";
    /** Height of node, in inches. */
    private static final String ATTR_HEIGHT = "height";
    /** Text label attached to objects. */
    private static final String ATTR_LABEL = "label";
    /**
     * This, along with labeldistance, determine where the headlabel (taillabel)
     * are placed with respect to the head (tail) in polar coordinates.
     */
    private static final String ATTR_LABELANGLE = "labelangle";
    /**
     * Multiplicative scaling factor adjusting the distance that the
     * headlabel(taillabel) is from the head(tail) node.
     */
    private static final String ATTR_LABELDISTANCE = "labeldistance";
    /** Label position, in points. */
    private static final String ATTR_LABELPOS = "lp";
    /** Determines if and how node overlaps should be removed (not dot). */
    private static final String ATTR_OVERLAP = "overlap";
    /** Position of node, or spline control points. */
    private static final String ATTR_POS = "pos";
    /** Specifies the minimum separation between all nodes (circo only). */
    private static final String ATTR_MINDIST = "mindist";
    /** Sets direction of graph layout (dot only). */
    private static final String ATTR_RANKDIR = "rankdir";
    /**
     * In dot, this gives the desired rank separation, in inches. In twopi,
     * specifies radial separation of concentric circles. (twopi, dot only)
     */
    private static final String ATTR_RANKSEP = "ranksep";
    /** Set the shape of a node. */
    private static final String ATTR_SHAPE = "shape";
    /**
     * Controls how, and if, edges are represented. If true, edges are drawn as
     * splines routed around nodes; if false, edges are drawn as line segments.
     */
    private static final String ATTR_SPLINES = "splines";
    /**
     * Parameter used to determine the initial layout of nodes (fdp, neato
     * only).
     */
    private static final String ATTR_START = "start";
    /** Text label to be placed near tail of edge. */
    private static final String ATTR_TAILLABEL = "taillabel";
    /** Tail label position, in points. */
    private static final String ATTR_TAILLP = "tail_lp";
    /** Width of node, in inches. */
    private static final String ATTR_WIDTH = "width";

    /** default multiplier for font sizes. */
    private static final double FONT_SIZE_MULT = 1.4;

    /** dots per inch specification, needed by Graphviz for some values. */
    private static final float DPI = 72.0f;
    /** set of delimiters used to parse attribute values. */
    private static final String ATTRIBUTE_DELIM = "\", \t\n\r";

    /** maps each identifier of a graph element to the instance of the element. */
    private HashMap<String, KGraphElement> graphElementMap =
            new HashMap<String, KGraphElement>();
    /** offset value to be added to all coordinates. */
    private float offset;
    /** indicates whether splines are used. */
    private boolean useSplines;

    /** the KGraph instance. */
    private KNode kgraph;

    /**
     * Constructs a KGraphDotTransformation for a given KGraph instance.
     * 
     * @param parent
     *            the KGraph instance
     */
    public KGraphDotTransformation(final KNode parent) {
        kgraph = parent;
    }

    /**
     * Transforms the KGraph instance to a Dot instance using the given command.
     * 
     * @param command
     *            the command
     * @param monitor
     *            the progress monitor
     * @return the Dot instance
     */
    public GraphvizModel transform(final Command command,
            final IKielerProgressMonitor monitor) {
        return createDotGraph(kgraph, command, monitor);
    }

    /**
     * Applies the layout information attached to the given Dot instance to the
     * KGraph instance using the mapping created by a previous call to
     * {@code transform}. Has to be called after a call to {@code transform}.
     * 
     * @param model
     *            the Dot instance
     * @param monitor
     *            the progress monitor
     */
    public void applyLayout(final GraphvizModel model,
            final IKielerProgressMonitor monitor) {
        retrieveLayoutResult(kgraph, model, monitor);
    }

    /**
     * Maps a {@code KNode} to the internal Graphviz data structure used by the
     * Dot parser.
     * 
     * @param parent
     *            a {@code KNode} with the graph to layout
     * @param command
     *            the command string identifying the layout method
     * @param monitor
     *            progress monitor
     * @return an instance of a graphviz model that corresponds to the input
     *         graph
     */
    private GraphvizModel createDotGraph(final KNode parent,
            final Command command, final IKielerProgressMonitor monitor) {
        monitor.begin("Create Dot model", 1);
        GraphvizModel graphvizModel =
                DotFactory.eINSTANCE.createGraphvizModel();
        Graph graph = DotFactory.eINSTANCE.createGraph();
        graph.setType(GraphType.DIGRAPH);
        graphvizModel.getGraphs().add(graph);

        // set attributes for the whole graph
        KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
        setGraphAttributes(graph, command, parentLayout);

        // get interactive layout option
        boolean interactive =
                parentLayout.getProperty(LayoutOptions.INTERACTIVE);

        // create nodes
        for (KNode childNode : parent.getChildren()) {
            NodeStatement nodeStatement =
                    DotFactory.eINSTANCE.createNodeStatement();
            Node node = DotFactory.eINSTANCE.createNode();
            String nodeID = getNodeID(childNode);
            graphElementMap.put(nodeID, childNode);
            node.setName(nodeID);
            nodeStatement.setNode(node);

            List<Attribute> attributes = nodeStatement.getAttributes();
            KShapeLayout shapeLayout = childNode.getData(KShapeLayout.class);
            // set label - removed as it is currently not needed for layout
            /*
             * KLabel label = childNode.getLabel();
             * attributes.getEntries().add(createAttribute
             * (GraphvizAPI.ATTR_LABEL, createString(label.getText())));
             */
            // set width and height
            if (!shapeLayout.getProperty(LayoutOptions.FIXED_SIZE)) {
                KimlUtil.resizeNode(childNode);
            }
            String width = Float.toString(shapeLayout.getWidth() / DPI);
            String height = Float.toString(shapeLayout.getHeight() / DPI);
            attributes.add(createAttribute(ATTR_WIDTH, width));
            attributes.add(createAttribute(ATTR_HEIGHT, height));
            attributes.add(createAttribute(ATTR_FIXEDSIZE, "true"));
            // add node position if interactive layout is chosen
            if (interactive) {
                float xpos = (shapeLayout.getXpos() - offset) / DPI;
                float ypos =
                        (parentLayout.getHeight() - shapeLayout.getYpos() - offset)
                                / DPI;
                String posString =
                        "\"" + Float.toString(xpos) + ","
                                + Float.toString(ypos) + "\"";
                attributes.add(createAttribute(ATTR_POS, posString));
            }
            // set node shape
            // TODO customize the node shape
            attributes.add(createAttribute(ATTR_SHAPE, "box"));

            graph.getStatements().add(nodeStatement);
        }

        // create edges
        Direction layoutDirection =
                parentLayout.getProperty(LayoutOptions.DIRECTION);
        boolean vertical =
                layoutDirection == Direction.DOWN
                        || layoutDirection == Direction.UP;
        for (KNode childNode : parent.getChildren()) {
            for (KEdge outgoingEdge : childNode.getOutgoingEdges()) {
                // consider only edges on the same hierarchy level
                if (childNode.getParent() == outgoingEdge.getTarget()
                        .getParent()) {
                    EdgeStatement edgeStatement =
                            DotFactory.eINSTANCE.createEdgeStatement();
                    Node sourceNode = DotFactory.eINSTANCE.createNode();
                    sourceNode.setName(getNodeID(outgoingEdge.getSource()));
                    edgeStatement.setSourceNode(sourceNode);
                    EdgeTarget edgeTarget =
                            DotFactory.eINSTANCE.createEdgeTarget();
                    Node targetNode = DotFactory.eINSTANCE.createNode();
                    targetNode.setName(getNodeID(outgoingEdge.getTarget()));
                    edgeTarget.setTargetnode(targetNode);
                    edgeStatement.getEdgeTargets().add(edgeTarget);

                    List<Attribute> attributes = edgeStatement.getAttributes();
                    // disable drawing arrows for the edges
                    attributes.add(createAttribute(ATTR_EDGEDIR, "none"));
                    // add edge labels at head, tail, and middle position
                    setEdgeLabels(outgoingEdge, attributes, vertical);
                    // add comment with edge identifier
                    String edgeID = getEdgeID(outgoingEdge);
                    attributes.add(createAttribute(ATTR_COMMENT, "\"" + edgeID
                            + "\""));
                    graphElementMap.put(edgeID, outgoingEdge);

                    graph.getStatements().add(edgeStatement);
                } else {
                    KEdgeLayout edgeLayout =
                            outgoingEdge.getData(KEdgeLayout.class);
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
        }

        monitor.done();
        return graphvizModel;
    }

    /**
     * Sets attributes for the whole graph.
     * 
     * @param graph
     *            a graph for serialization to Graphviz
     * @param command
     *            a layouter command
     * @param parentLayout
     *            the layout data for the parent node
     */
    private void setGraphAttributes(final Graph graph, final Command command,
            final KGraphData parentLayout) {
        AttributeStatement graphAttrStatement =
                DotFactory.eINSTANCE.createAttributeStatement();
        graphAttrStatement.setType(AttributeType.GRAPH);
        List<Attribute> graphAttrs = graphAttrStatement.getAttributes();
        // set minimal spacing
        float minSpacing = parentLayout.getProperty(LayoutOptions.SPACING);
        if (minSpacing < 0) {
            switch (command) {
            case CIRCO:
            case FDP:
            case NEATO:
                minSpacing = DEF_SPACING_LARGE;
                break;
            case TWOPI:
                minSpacing = DEF_SPACING_XLARGE;
                break;
            default:
                minSpacing = DEF_SPACING_SMALL;
            }
        }
        String spacingVal = Float.toString(minSpacing / DPI);
        if (command == Command.DOT || command == Command.TWOPI) {
            graphAttrs.add(createAttribute(ATTR_RANKSEP, spacingVal));
        }
        if (command == Command.CIRCO) {
            graphAttrs.add(createAttribute(ATTR_MINDIST, spacingVal));
        } else if (command == Command.NEATO || command == Command.FDP) {
            AttributeStatement edgeAttrStatement =
                    DotFactory.eINSTANCE.createAttributeStatement();
            edgeAttrStatement.setType(AttributeType.EDGE);
            List<Attribute> edgeAttrs = edgeAttrStatement.getAttributes();
            edgeAttrs.add(createAttribute(ATTR_EDGELEN, spacingVal));
            graph.getStatements().add(edgeAttrStatement);
        }
        // set offset to border
        offset = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (offset < 0) {
            offset = DEF_SPACING_SMALL / 2;
        }
        // set layout direction
        if (command == Command.DOT) {
            switch (parentLayout.getProperty(LayoutOptions.DIRECTION)) {
            case DOWN:
                graphAttrs.add(createAttribute(ATTR_RANKDIR, "TB"));
                break;
            case UP:
                graphAttrs.add(createAttribute(ATTR_RANKDIR, "BT"));
                break;
            case LEFT:
                graphAttrs.add(createAttribute(ATTR_RANKDIR, "RL"));
                break;
            default:
                graphAttrs.add(createAttribute(ATTR_RANKDIR, "LR"));
                break;
            }
        }
        // enable node overlap avoidance
        if (command != Command.DOT) {
            graphAttrs.add(createAttribute(ATTR_OVERLAP, "false"));
        }
        // enable or disable drawing of splines
        EdgeRouting edgeRouting =
                parentLayout.getProperty(LayoutOptions.EDGE_ROUTING);
        useSplines =
                (edgeRouting != EdgeRouting.POLYLINE && edgeRouting != EdgeRouting.ORTHOGONAL);
        graphAttrs.add(createAttribute(ATTR_SPLINES,
                Boolean.toString(useSplines)));
        // configure initial placement of nodes
        if (command == Command.NEATO) {
            Integer seed = parentLayout.getProperty(LayoutOptions.RANDOM_SEED);
            if (seed == null) {
                seed = 1;
            } else if (seed == 0) {
                seed = -1;
            } else if (seed < 0) {
                seed = -seed;
            }
            graphAttrs.add(createAttribute(ATTR_START, "random" + seed));
        }
        graph.getStatements().add(graphAttrStatement);
    }

    /**
     * Creates an attribute with given name and value for the Dot graph.
     * 
     * @param name
     *            name of the attribute
     * @param value
     *            value of the attribute
     * @return instance of a Dot attribute
     */
    private static Attribute createAttribute(final String name,
            final String value) {
        Attribute attribute = DotFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }

    /** default label angle value. */
    private static final float DEF_LABEL_ANGLE = -25.0f;

    /**
     * Set edge labels for the given edge.
     * 
     * @param kedge
     *            edge whose labels shall be set
     * @param attributes
     *            edge attribute list to which the labels are added
     * @param isVertical
     *            indicates whether vertical layout direction is active
     */
    private static void setEdgeLabels(final KEdge kedge,
            final List<Attribute> attributes, final boolean isVertical) {
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        // as Graphviz only supports positioning of one label per label
        // placement, all labels
        // are stacked to one big label as workaround
        StringBuilder midLabel = new StringBuilder(), headLabel =
                new StringBuilder(), tailLabel = new StringBuilder();
        String fontName = null;
        int fontSize = 0;
        boolean isCenterFontName = false, isCenterFontSize = false;
        for (KLabel label : kedge.getLabels()) {
            StringBuilder buffer = midLabel;
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            EdgeLabelPlacement placement =
                    labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT);
            boolean takeFontName = false, takeFontSize = false;
            switch (placement) {
            case CENTER:
                takeFontName = fontName == null || !isCenterFontName;
                isCenterFontName = true;
                takeFontSize = fontSize <= 0 || !isCenterFontSize;
                isCenterFontSize = true;
                break;
            case HEAD:
                takeFontName = fontName == null;
                takeFontSize = fontSize <= 0;
                buffer = headLabel;
                break;
            case TAIL:
                takeFontName = fontName == null;
                takeFontSize = fontSize <= 0;
                buffer = tailLabel;
                break;
            }
            if (buffer.length() > 0) {
                buffer.append("\n");
            }
            buffer.append(label.getText());
            if (takeFontName) {
                fontName = labelLayout.getProperty(LayoutOptions.FONT_NAME);
            }
            if (takeFontSize) {
                fontSize = labelLayout.getProperty(LayoutOptions.FONT_SIZE);
                // increase the font size to let Graphviz prepare more room for
                // the label
                if (fontSize > 0) {
                    fontSize *= FONT_SIZE_MULT;
                }
            }
        }

        // set mid label: if empty, it is filled with a dummy string to avoid
        // edge overlapping
        if (midLabel.length() == 0) {
            midLabel.append(' ');
        } else {
            float labelSpacing =
                    edgeLayout.getProperty(LayoutOptions.LABEL_SPACING);
            int charsToAdd = (labelSpacing < 1 ? 1 : (int) labelSpacing) - 1;
            for (int i = 0; i < charsToAdd; i++) {
                midLabel.append(isVertical ? "O" : "\nO");
            }
        }
        attributes.add(createAttribute(ATTR_LABEL,
                createString(midLabel.toString())));
        // set head label
        if (headLabel.length() > 0) {
            attributes.add(createAttribute(ATTR_HEADLABEL,
                    createString(headLabel.toString())));
        }
        // set tail label
        if (tailLabel.length() > 0) {
            attributes.add(createAttribute(ATTR_TAILLABEL,
                    createString(tailLabel.toString())));
        }
        // set font name
        if (fontName != null && fontName.length() > 0) {
            attributes.add(createAttribute(ATTR_FONTNAME, "\"" + fontName
                    + "\""));
        }
        // set font size
        if (fontSize > 0) {
            attributes.add(createAttribute(ATTR_FONTSIZE,
                    Integer.toString(fontSize)));
        }
        // set label distance
        float distance = edgeLayout.getProperty(LABEL_DISTANCE);
        if (distance >= 0.0f) {
            attributes.add(createAttribute(ATTR_LABELDISTANCE,
                    Float.toString(distance)));
            if (distance > 1.0f) {
                float labelAngle = DEF_LABEL_ANGLE / distance;
                attributes.add(createAttribute(ATTR_LABELANGLE,
                        Float.toString(labelAngle)));
            }
        }
    }

    /** first character that is not replaced by underscore. */
    private static final char MIN_OUT_CHAR = 32;
    /** last character that is not replaced by underscore. */
    private static final char MAX_OUT_CHAR = 126;

    /**
     * Creates a properly parsable string by adding the escape character '\\'
     * wherever needed and replacing illegal characters.
     * 
     * @param label
     *            a label string from the KGraph structure
     * @return string to be used in the Graphviz model
     */
    private static String createString(final String label) {
        StringBuilder escapeBuffer = new StringBuilder(label.length() + 2);
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
     * Creates a unique identifier for the given node.
     * 
     * @param node
     *            node for which an identifier shall be created
     * @return a unique string used to identify the node
     */
    private String getNodeID(final KNode node) {
        return "node" + node.hashCode();
    }

    /**
     * Creates a unique identifier for the given edge.
     * 
     * @param edge
     *            edge for which an identifier shall be created
     * @return a unique string used to identify the edge
     */
    private String getEdgeID(final KEdge edge) {
        return "edge" + edge.hashCode();
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
        KVector boundingBox = null;
        float edgeOffsetx = offset, edgeOffsety = offset;
        for (Statement statement : graph.getStatements()) {

            if (statement instanceof NodeStatement) {
                // process a node
                NodeStatement nodeStatement = (NodeStatement) statement;
                KNode knode = (KNode) graphElementMap.get(nodeStatement.getNode().getName());
                if (knode == null) {
                    continue;
                }
                KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
                float xpos = 0.0f, ypos = 0.0f, width = 0.0f, height = 0.0f;
                for (Attribute attribute : nodeStatement.getAttributes()) {
                    try {
                        if (attribute.getName().equals(ATTR_POS)) {
                            KVector pos = parsePoint(attribute.getValue());
                            xpos = (float) pos.x + offset;
                            ypos = (float) pos.y + offset;
                        } else if (attribute.getName().equals(ATTR_WIDTH)) {
                            StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                    ATTRIBUTE_DELIM);
                            width = Float.parseFloat(tokenizer.nextToken()) * DPI;
                        } else if (attribute.getName().equals(ATTR_HEIGHT)) {
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
                KEdge kedge = (KEdge) graphElementMap.get(attributeMap.get(ATTR_COMMENT));
                if (kedge == null) {
                    continue;
                }
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                List<KPoint> edgePoints = edgeLayout.getBendPoints();
                edgePoints.clear();
                String posString = attributeMap.get(ATTR_POS);
                
                // parse the list of spline control coordinates
                List<List<KVector>> splines = new LinkedList<List<KVector>>();
                Pair<KPoint, KPoint> endpoints = parseSplinePoints(posString, splines,
                        edgeOffsetx, edgeOffsety);

                KPoint sourcePoint = endpoints.getFirst();
                KPoint targetPoint = endpoints.getSecond();
                if (!splines.isEmpty()) {
                    KLayoutDataFactory layoutDataFactory = KLayoutDataFactory.eINSTANCE;
                    // the first point in the list is the start point, if no arrowhead is given
                    if (sourcePoint == null) {
                        sourcePoint = layoutDataFactory.createKPoint();
                        List<KVector> points = splines.get(0);
                        if (!points.isEmpty()) {
                            sourcePoint.applyVector(points.remove(0));
                        } else {
                            KShapeLayout sourceLayout = kedge.getSource().getData(KShapeLayout.class);
                            sourcePoint.setX(sourceLayout.getXpos() + sourceLayout.getWidth() / 2);
                            sourcePoint.setY(sourceLayout.getYpos() + sourceLayout.getHeight() / 2);
                        }
                    }
                    // the last point in the list is the end point, if no arrowhead is given
                    if (targetPoint == null) {
                        targetPoint = layoutDataFactory.createKPoint();
                        List<KVector> points = splines.get(splines.size() - 1);
                        if (!points.isEmpty()) {
                            targetPoint.applyVector(points.remove(points.size() - 1));
                        } else {
                            KShapeLayout targetLayout = kedge.getTarget().getData(KShapeLayout.class);
                            targetPoint.setX(targetLayout.getXpos() + targetLayout.getWidth() / 2);
                            targetPoint.setY(targetLayout.getYpos() + targetLayout.getHeight() / 2);
                        }
                    }
                    // add all other control points to the edge
                    for (List<KVector> points : splines) {
                        for (KVector point : points) {
                            KPoint controlPoint = layoutDataFactory.createKPoint();
                            controlPoint.applyVector(point);
                            edgePoints.add(controlPoint);
                        }
                    }
                }
                edgeLayout.setSourcePoint(sourcePoint);
                edgeLayout.setTargetPoint(targetPoint);
                if (useSplines) {
                    edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
                }

                // process the edge labels
                String labelPos = attributeMap.get(ATTR_LABELPOS);
                if (labelPos != null) {
                    applyEdgeLabelPos(kedge, labelPos, EdgeLabelPlacement.CENTER,
                            edgeOffsetx, edgeOffsety);
                }
                labelPos = attributeMap.get(ATTR_HEADLP);
                if (labelPos != null) {
                    applyEdgeLabelPos(kedge, labelPos, EdgeLabelPlacement.HEAD,
                            edgeOffsetx, edgeOffsety);
                }
                labelPos = attributeMap.get(ATTR_TAILLP);
                if (labelPos != null) {
                    applyEdgeLabelPos(kedge, labelPos, EdgeLabelPlacement.TAIL,
                            offset, offset);
                }
                
            } else if (statement instanceof AttributeStatement) {
                // process an attribute
                AttributeStatement attributeStatement = (AttributeStatement) statement;
                if (attributeStatement.getType() == AttributeType.GRAPH) {
                    for (Attribute attribute : attributeStatement.getAttributes()) {
                        if (attribute.getName().equals(ATTR_BOUNDINGBOX)) {
                            try {
                                StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                        ATTRIBUTE_DELIM);
                                float leftx = Float.parseFloat(tokenizer.nextToken());
                                float bottomy = Float.parseFloat(tokenizer.nextToken());
                                float rightx = Float.parseFloat(tokenizer.nextToken());
                                float topy = Float.parseFloat(tokenizer.nextToken());
                                boundingBox = new KVector(rightx - leftx, bottomy - topy);
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
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        if (boundingBox != null) {
            KInsets insets = parentLayout.getInsets();
            float width = (float) boundingBox.x + insets.getLeft()
                    + insets.getRight() + 2 * offset;
            float height = (float) boundingBox.y + insets.getTop()
                    + insets.getBottom() + 2 * offset;
            KimlUtil.resizeNode(parentNode, width, height, false);
            parentLayout.setProperty(LayoutOptions.FIXED_SIZE, true);
        }
        monitor.done();
    }
    
    /**
     * Applies the edge label positions for the given edge.
     * 
     * @param kedge edge for which labels are processed
     * @param posString string with label position
     * @param placement label placement to choose
     * @param offsetx x offset added to positions
     * @param offsety y offset added to positions
     */
    private void applyEdgeLabelPos(final KEdge kedge, final String posString,
            final EdgeLabelPlacement placement, final float offsetx, final float offsety) {
        float combinedWidth = 0.0f, combinedHeight = 0.0f;
        for (KLabel label : kedge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            if (labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT) == placement) {
                combinedWidth = Math.max(combinedWidth, labelLayout.getWidth());
                combinedHeight += labelLayout.getHeight();
            }
        }
        KVector pos = parsePoint(posString);
        float xpos = (float) pos.x - combinedWidth / 2 + offsetx;
        float ypos = (float) pos.y - combinedHeight / 2 + offsety;
        for (KLabel label : kedge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            if (labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT) == placement) {
                float xoffset = (combinedWidth - labelLayout.getWidth()) / 2;
                labelLayout.setXpos(xpos + xoffset);
                labelLayout.setYpos(ypos);
                labelLayout.setProperty(LayoutOptions.NO_LAYOUT, false);
                ypos += labelLayout.getHeight();
            }
        }
    }

    /**
     * Converts a list of attributes to a map of attribute names to their
     * values.
     * 
     * @param attributes attribute list
     * @return a hash map that contains all given attributes
     */
    private static Map<String, String> createAttributeMap(final List<Attribute> attributes) {
        Map<String, String> attributeMap = new HashMap<String, String>(attributes.size());
        for (Attribute attribute : attributes) {
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
            final List<List<KVector>> splines, final float offsetx, final float offsety) {
        KPoint sourcePoint = null, targetPoint = null;
        StringTokenizer splinesTokenizer = new StringTokenizer(posString, "\";");
        while (splinesTokenizer.hasMoreTokens()) {
            ArrayList<KVector> pointList = new ArrayList<KVector>();
            StringTokenizer posTokenizer = new StringTokenizer(splinesTokenizer.nextToken(), " ");
            while (posTokenizer.hasMoreTokens()) {
                String token = posTokenizer.nextToken();
                if (token.startsWith("s")) {
                    if (sourcePoint == null) {
                        sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                        int commaIndex = token.indexOf(',');
                        KVector point = parsePoint(token.substring(commaIndex + 1));
                        sourcePoint.applyVector(point.translate(offsetx, offsety));
                    }
                } else if (token.startsWith("e")) {
                    if (targetPoint == null) {
                        targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                        int commaIndex = token.indexOf(',');
                        KVector point = parsePoint(token.substring(commaIndex + 1));
                        targetPoint.applyVector(point.translate(offsetx, offsety));
                    }
                } else {
                    KVector point = parsePoint(token);
                    point.x += offsetx;
                    point.y += offsety;
                    pointList.add(point);
                }
            }
            splines.add(pointList);
        }
        return new Pair<KPoint, KPoint>(sourcePoint, targetPoint);
    }
    
    /** default number of characters in new string builders. */
    private static final int DEF_BUILDER_SIZE = 8;
    
    /**
     * Parses a point from a Graphviz string.
     * 
     * @param string string from which the point is parsed
     * @return a point with x and y coordinates
     */
    private static KVector parsePoint(final String string) {
        double x = 0.0f, y = 0.0f;
        StringBuilder xbuilder = null, ybuilder = null;
        boolean commaRead = false;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= '0' && c <= '9' || c == '.' || c == '-') {
                if (commaRead) {
                    if (ybuilder == null) {
                        ybuilder = new StringBuilder(DEF_BUILDER_SIZE);
                    }
                    ybuilder.append(c);
                } else {
                    if (xbuilder == null) {
                        xbuilder = new StringBuilder(DEF_BUILDER_SIZE);
                    }
                    xbuilder.append(c);
                }
            } else if (c == ',') {
                if (commaRead) {
                    break;
                }
                commaRead = true;
            }
        }
        if (xbuilder != null) {
            x = Double.valueOf(xbuilder.toString());
        }
        if (ybuilder != null) {
            y = Double.valueOf(ybuilder.toString());
        }
        return new KVector(x, y);
    }
}
