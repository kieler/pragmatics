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
package de.cau.cs.kieler.kiml.graphviz.dot.transform;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
import de.cau.cs.kieler.core.math.KVectorChain;
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
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Subgraph;
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
 * This class implements a transformation from the KGraph metamodel to the Dot metamodel.
 * Furthermore it contains functionality to apply layout information attached to a Dot model to a
 * KGraph model.
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

  /** small default value for minimal spacing. */
    public static final float DEF_SPACING_SMALL = 16.0f;
    /** large default value for minimal spacing. */
    public static final float DEF_SPACING_LARGE = 40.0f;
    /** extra-large default value for minimal spacing. */
    public static final float DEF_SPACING_XLARGE = 60.0f;
    /** dots per inch specification, needed by Graphviz for some values. */
    public static final float DPI = 72.0f;

    /** default multiplier for font sizes. */
    private static final double FONT_SIZE_MULT = 1.4;
    /** set of delimiters used to parse attribute values. */
    private static final String ATTRIBUTE_DELIM = "\", \t\n\r";

    /** maps each identifier of a graph element to the instance of the element. */
    private HashMap<String, KGraphElement> graphElementMap = new HashMap<String, KGraphElement>();
    /** indicates whether splines are used. */
    private boolean useSplines;

    /** the KGraph instance. */
    private KNode kgraph;

    /**
     * Constructs a KGraphDotTransformation for a given KGraph instance.
     * 
     * @param parent the KGraph instance
     */
    public KGraphDotTransformation(final KNode parent) {
        kgraph = parent;
    }

    /**
     * Transforms the KGraph instance to a Dot instance using the given command.
     * 
     * @param command the command
     * @param monitor the progress monitor
     * @return the Dot instance
     */
    public GraphvizModel transform(final Command command, final IKielerProgressMonitor monitor) {
        monitor.begin("Create Dot model", 1);
        GraphvizModel graphvizModel = DotFactory.eINSTANCE.createGraphvizModel();
        Graph graph = DotFactory.eINSTANCE.createGraph();
        graph.setType(GraphType.DIGRAPH);
        graphvizModel.getGraphs().add(graph);
        boolean layoutHierarchy = kgraph.getData(KShapeLayout.class)
                .getProperty(LayoutOptions.LAYOUT_HIERARCHY)
                && (command == Command.DOT || command == Command.FDP);
        transformNodes(kgraph, graph.getStatements(), command, layoutHierarchy, new KVector());
        transformEdges(kgraph, graph.getStatements(), layoutHierarchy);
        monitor.done();
        return graphvizModel;
    }

    /**
     * Applies the layout information attached to the given Dot instance to the KGraph instance
     * using the mapping created by a previous call to {@code transform}. Has to be called after a
     * call to {@code transform}.
     * 
     * @param model the Dot instance
     * @param monitor the progress monitor
     */
    public void applyLayout(final GraphvizModel model, final IKielerProgressMonitor monitor) {
        monitor.begin("Transfer layout result", 1);
        float borderSpacing = kgraph.getData(KShapeLayout.class)
                .getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEF_SPACING_SMALL / 2;
        }
        Graph graph = model.getGraphs().get(0);
        
        // process nodes and subgraphs
        KVector baseOffset = new KVector();
        applyLayout(kgraph, graph.getStatements(), baseOffset, borderSpacing);
        
        // finally process the edges
        LinkedList<Statement> statements = new LinkedList<Statement>(graph.getStatements());
        KVector edgeOffset = baseOffset.translate(borderSpacing, borderSpacing);
        while (!statements.isEmpty()) {
            Statement statement = statements.removeFirst();
            if (statement instanceof EdgeStatement) {
                applyEdgeLayout((EdgeStatement) statement, edgeOffset, borderSpacing);
            } else if (statement instanceof Subgraph) {
                statements.addAll(((Subgraph) statement).getStatements());
            }
        }

        monitor.done();
    }

    /**
     * Transform the child nodes of the given parent node.
     * 
     * @param parent a parent node
     * @param statements the list to which new statements are added
     * @param command the processed Graphviz command
     * @param hierarchy whether hierarchy mode is active
     * @param offset offset of the parent node in the whole graph
     */
    private void transformNodes(final KNode parent, final List<Statement> statements,
            final Command command, final boolean hierarchy, final KVector offset) {
        // set attributes for the whole graph
        KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
        setGraphAttributes(statements, command, parentLayout);

        // get interactive layout option
        boolean interactive = parentLayout.getProperty(LayoutOptions.INTERACTIVE);

        // create nodes and subgraphs
        for (KNode childNode : parent.getChildren()) {
            KShapeLayout nodeLayout = childNode.getData(KShapeLayout.class);
            NodeStatement nodeStatement = DotFactory.eINSTANCE.createNodeStatement();
            List<Attribute> attributes = nodeStatement.getAttributes();
            String nodeID;
            // if hierarchy mode is active, create a subgraph, else a regular node
            if (hierarchy && !childNode.getChildren().isEmpty()) {
                String clusterNodeID = getNodeID(childNode, NodeType.CLUSTER);
                graphElementMap.put(clusterNodeID, childNode);
                Subgraph subgraph = DotFactory.eINSTANCE.createSubgraph();
                subgraph.setName(clusterNodeID);
                statements.add(subgraph);
                // transform child nodes recursively
                double subgraphx = nodeLayout.getXpos() + nodeLayout.getInsets().getLeft();
                double subgraphy = nodeLayout.getYpos() + nodeLayout.getInsets().getTop();
                transformNodes(childNode, subgraph.getStatements(), command, true,
                        new KVector(offset).translate(subgraphx, subgraphy));
                // create a dummy node for compound edges
                nodeID = getNodeID(childNode, NodeType.DUMMY);
                attributes.add(createAttribute(Attributes.STYLE, "invis"));
                attributes.add(createAttribute(Attributes.WIDTH, 0));
                attributes.add(createAttribute(Attributes.HEIGHT, 0));
                subgraph.getStatements().add(nodeStatement);
            } else {
                nodeID = getNodeID(childNode, NodeType.NODE);
                graphElementMap.put(nodeID, childNode);
                // set width and height
                if (!nodeLayout.getProperty(LayoutOptions.FIXED_SIZE)) {
                    KimlUtil.resizeNode(childNode);
                }
                attributes.add(createAttribute(Attributes.WIDTH, nodeLayout.getWidth() / DPI));
                attributes.add(createAttribute(Attributes.HEIGHT, nodeLayout.getHeight() / DPI));
                // add node position if interactive layout is chosen
                if (interactive) {
                    double xpos = (nodeLayout.getXpos() - offset.x) / DPI;
                    double ypos = (parentLayout.getHeight() - nodeLayout.getYpos() - offset.y) / DPI;
                    String posString = "\"" + Double.toString(xpos)
                            + "," + Double.toString(ypos) + "\"";
                    attributes.add(createAttribute(Attributes.POS, posString));
                }
                // set node shape
                statements.add(nodeStatement);
            }
            Node node = DotFactory.eINSTANCE.createNode();
            node.setName(nodeID);
            nodeStatement.setNode(node);
        }
    }

    /**
     * Transform the edges of the given parent node.
     * 
     * @param parent a parent node
     * @param statements the list to which new statements are added
     * @param hierarchy whether hierarchy mode is active
     */
    private void transformEdges(final KNode parent, final List<Statement> statements,
            final boolean hierarchy) {
        KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
        Direction layoutDirection = parentLayout.getProperty(LayoutOptions.DIRECTION);
        boolean vertical = layoutDirection == Direction.DOWN || layoutDirection == Direction.UP;
        LinkedList<KNode> nodes = new LinkedList<KNode>(parent.getChildren());
        while (!nodes.isEmpty()) {
            KNode source = nodes.removeFirst();
            for (KEdge edge : source.getOutgoingEdges()) {
                KNode target = edge.getTarget();
                // cross-hierarchy edges are considered only if hierarchy mode is active
                if (source.getParent() == target.getParent()
                        || hierarchy && isInsideGraph(target)) {
                    EdgeStatement edgeStatement = DotFactory.eINSTANCE.createEdgeStatement();
                    List<Attribute> attributes = edgeStatement.getAttributes();
                    // set source node or cluster
                    Node sourceNode = DotFactory.eINSTANCE.createNode();
                    if (hierarchy && !source.getChildren().isEmpty()) {
                        sourceNode.setName(getNodeID(source, NodeType.DUMMY));
                        attributes.add(createAttribute(Attributes.LTAIL,
                                getNodeID(source, NodeType.CLUSTER)));
                    } else {
                        sourceNode.setName(getNodeID(source, NodeType.NODE));
                    }
                    edgeStatement.setSourceNode(sourceNode);
                    // set target node or cluster
                    EdgeTarget edgeTarget = DotFactory.eINSTANCE.createEdgeTarget();
                    Node targetNode = DotFactory.eINSTANCE.createNode();
                    if (hierarchy && !target.getChildren().isEmpty()) {
                        targetNode.setName(getNodeID(target, NodeType.DUMMY));
                        attributes.add(createAttribute(Attributes.LHEAD, getNodeID(target,
                                NodeType.CLUSTER)));
                    } else {
                        targetNode.setName(getNodeID(target, NodeType.NODE));
                    }
                    edgeTarget.setTargetnode(targetNode);
                    edgeStatement.getEdgeTargets().add(edgeTarget);

                    // add edge labels at head, tail, and middle position
                    setEdgeLabels(edge, attributes, vertical);
                    // add comment with edge identifier
                    String edgeID = getEdgeID(edge);
                    attributes.add(createAttribute(Attributes.COMMENT, "\"" + edgeID + "\""));
                    graphElementMap.put(edgeID, edge);
                    statements.add(edgeStatement);
                } else {
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
            if (hierarchy) {
                nodes.addAll(source.getChildren());
            }
        }
    }
    
    /**
     * Determines whether the given node is contained in the currently processed layout graph.
     * 
     * @param node a node
     * @return true if the node is in the layout graph
     */
    private boolean isInsideGraph(final KNode node) {
        KNode n = node;
        do {
            if (n == kgraph) {
                return true;
            }
            n = n.getParent();
        } while (n != null);
        return false;
    }
    
    /** base factor for setting {@link Attributes#SIMPLEX_LIMIT} from the iterations limit. */
    private static final float NSLIMIT_BASE = 100.0f;

    /**
     * Sets attributes for the whole graph.
     * 
     * @param statements the statement list for adding attributes
     * @param command a layouter command
     * @param parentLayout the layout data for the parent node
     */
    private void setGraphAttributes(final List<Statement> statements, final Command command,
            final KGraphData parentLayout) {
        AttributeStatement graphAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
        graphAttrStatement.setType(AttributeType.GRAPH);
        List<Attribute> graphAttrs = graphAttrStatement.getAttributes();
        statements.add(graphAttrStatement);
        
        // set general node attributes
        AttributeStatement nodeAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
        nodeAttrStatement.setType(AttributeType.NODE);
        List<Attribute> nodeAttrs = nodeAttrStatement.getAttributes();
        statements.add(nodeAttrStatement);
        nodeAttrs.add(createAttribute(Attributes.SHAPE, "box"));
        nodeAttrs.add(createAttribute(Attributes.FIXEDSIZE, "true"));
        
        // set general edge attributes
        AttributeStatement edgeAttrStatement = DotFactory.eINSTANCE.createAttributeStatement();
        edgeAttrStatement.setType(AttributeType.EDGE);
        List<Attribute> edgeAttrs = edgeAttrStatement.getAttributes();
        statements.add(edgeAttrStatement);
        edgeAttrs.add(createAttribute(Attributes.EDGEDIR, "none"));
        
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
        
        switch (command) {
        case DOT:
            graphAttrs.add(createAttribute(Attributes.RANKSEP, minSpacing / DPI));
            graphAttrs.add(createAttribute(Attributes.NODESEP, minSpacing / DPI));
            // set layout direction
            switch (parentLayout.getProperty(LayoutOptions.DIRECTION)) {
            case DOWN:
                graphAttrs.add(createAttribute(Attributes.RANKDIR, "TB"));
                break;
            case UP:
                graphAttrs.add(createAttribute(Attributes.RANKDIR, "BT"));
                break;
            case LEFT:
                graphAttrs.add(createAttribute(Attributes.RANKDIR, "RL"));
                break;
            default:
                graphAttrs.add(createAttribute(Attributes.RANKDIR, "LR"));
                break;
            }
            // set aspect ratio
            float aspectRatio = parentLayout.getProperty(LayoutOptions.ASPECT_RATIO);
            if (aspectRatio > 0) {
                graphAttrs.add(createAttribute(Attributes.ASPECT, "\"" + aspectRatio + ",1\""));
            }
            // set iterations limit
            float iterationsLimit = parentLayout.getProperty(Attributes.ITER_LIMIT_PROP);
            if (iterationsLimit > 0) {
                graphAttrs.add(createAttribute(Attributes.CROSSMIN_LIMIT, iterationsLimit));
                if (iterationsLimit < 1) {
                    float simplexLimit = iterationsLimit * NSLIMIT_BASE;
                    graphAttrs.add(createAttribute(Attributes.SIMPLEX_LIMIT, simplexLimit));
                }
            }
            // enable compound mode
            if (parentLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
                graphAttrs.add(createAttribute(Attributes.COMPOUND, "true"));
            }
            break;
            
        case TWOPI:
            graphAttrs.add(createAttribute(Attributes.RANKSEP, minSpacing / DPI));
            break;
            
        case CIRCO:
            graphAttrs.add(createAttribute(Attributes.MINDIST, minSpacing / DPI));
            break;
            
        case NEATO:
            edgeAttrs.add(createAttribute(Attributes.EDGELEN, minSpacing / DPI));
            // configure initial placement of nodes
            Integer seed = parentLayout.getProperty(LayoutOptions.RANDOM_SEED);
            if (seed == null) {
                seed = 1;
            } else if (seed == 0) {
                seed = -1;
            } else if (seed < 0) {
                seed = -seed;
            }
            graphAttrs.add(createAttribute(Attributes.START, "random" + seed));
            // set epsilon value
            float epsilon = parentLayout.getProperty(Attributes.EPSILON_PROP);
            if (epsilon > 0) {
                graphAttrs.add(createAttribute(Attributes.EPSILON, epsilon));
            }
            // set distance model
            NeatoModel model = parentLayout.getProperty(Attributes.NEATO_MODEL_PROP);
            if (model != NeatoModel.SHORTPATH) {
                graphAttrs.add(createAttribute(Attributes.NEATO_MODEL, model.literal()));
            }
            break;
            
        case FDP:
            graphAttrs.add(createAttribute(Attributes.SPRING_CONSTANT, minSpacing / DPI));
            break;
        }
        
        if (command == Command.NEATO || command == Command.FDP) {
            // set maximum number of iterations
            int maxiter = parentLayout.getProperty(Attributes.MAXITER_PROP);
            if (maxiter > 0) {
                graphAttrs.add(createAttribute(Attributes.MAXITER, maxiter));
            }
        }

        if (command != Command.DOT) {
            // enable or disable node overlap avoidance
            OverlapMode mode = parentLayout.getProperty(Attributes.OVERLAP_PROP);
            if (mode != OverlapMode.NONE) {
                graphAttrs.add(createAttribute(Attributes.OVERLAP, mode.literal()));
                graphAttrs.add(createAttribute(Attributes.SEP, minSpacing / 2));
            }
            // enable or disable connected component packing
            Boolean pack = parentLayout.getProperty(LayoutOptions.SEPARATE_CC);
            if (command == Command.TWOPI || pack != null && pack.booleanValue()) {
                graphAttrs.add(createAttribute(Attributes.PACK, (int) minSpacing));
            }
        }
        
        // configure edge routing
        EdgeRouting edgeRouting = parentLayout.getProperty(LayoutOptions.EDGE_ROUTING);
        String splineMode;
        switch (edgeRouting) {
        case POLYLINE:
            splineMode = "polyline";
            break;
        case ORTHOGONAL:
            splineMode = "ortho";
            break;
        default:
            splineMode = "spline";
            useSplines = true;
        }
        graphAttrs.add(createAttribute(Attributes.SPLINES, splineMode));
        
        // enable edge concentration
        if (parentLayout.getProperty(Attributes.CONCENTRATE_PROP)) {
            graphAttrs.add(createAttribute(Attributes.CONCENTRATE, "true"));
        }
    }

    /**
     * Create an attribute with given name and value for the Dot graph.
     * 
     * @param name name of the attribute
     * @param value value of the attribute
     * @return instance of a Dot attribute
     */
    public static Attribute createAttribute(final String name, final String value) {
        Attribute attribute = DotFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }
    
    /**
     * Create an attribute with given name and integer value for the Dot graph.
     * 
     * @param name name of the attribute
     * @param value value of the attribute
     * @return instance of a Dot attribute
     */
    public static Attribute createAttribute(final String name, final int value) {
        Attribute attribute = DotFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setValue("\"" + value + "\"");
        return attribute;
    }

    /**
     * Create an attribute with given name and float value for the Dot graph.
     * 
     * @param name name of the attribute
     * @param value value of the attribute
     * @return instance of a Dot attribute
     */
    public static Attribute createAttribute(final String name, final float value) {
        Attribute attribute = DotFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setValue("\"" + value + "\"");
        return attribute;
    }

    /** default label angle value. */
    private static final float DEF_LABEL_ANGLE = -25.0f;

    /**
     * Set edge labels for the given edge.
     * 
     * @param kedge edge whose labels shall be set
     * @param attributes edge attribute list to which the labels are added
     * @param isVertical indicates whether vertical layout direction is active
     */
    private static void setEdgeLabels(final KEdge kedge, final List<Attribute> attributes,
            final boolean isVertical) {
        if (kedge.getLabels().isEmpty()) {
            return;
        }
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        // as Graphviz only supports positioning of one label per label placement, all labels
        // are stacked to one big label as workaround
        StringBuilder midLabel = new StringBuilder(), headLabel = new StringBuilder(),
                tailLabel = new StringBuilder();
        String fontName = null;
        int fontSize = 0;
        boolean isCenterFontName = false, isCenterFontSize = false;
        for (KLabel label : kedge.getLabels()) {
            StringBuilder buffer = midLabel;
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            EdgeLabelPlacement placement = labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT);
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
        if (midLabel.length() > 0) {
            float labelSpacing = edgeLayout.getProperty(LayoutOptions.LABEL_SPACING);
            int charsToAdd = (labelSpacing < 1 ? 1 : (int) labelSpacing) - 1;
            for (int i = 0; i < charsToAdd; i++) {
                midLabel.append(isVertical ? "O" : "\nO");
            }
            attributes.add(createAttribute(Attributes.LABEL, createString(midLabel.toString())));
        }
        // set head label
        if (headLabel.length() > 0) {
            attributes.add(createAttribute(Attributes.HEADLABEL, createString(headLabel.toString())));
        }
        // set tail label
        if (tailLabel.length() > 0) {
            attributes.add(createAttribute(Attributes.TAILLABEL, createString(tailLabel.toString())));
        }
        // set font name
        if (fontName != null && fontName.length() > 0) {
            attributes.add(createAttribute(Attributes.FONTNAME, "\"" + fontName + "\""));
        }
        // set font size
        if (fontSize > 0) {
            attributes.add(createAttribute(Attributes.FONTSIZE, fontSize));
        }
        // set label distance
        float distance = edgeLayout.getProperty(Attributes.LABEL_DISTANCE_PROP);
        if (distance >= 0.0f) {
            attributes.add(createAttribute(Attributes.LABELDISTANCE, distance));
            if (distance > 1.0f) {
                float labelAngle = DEF_LABEL_ANGLE / distance;
                attributes.add(createAttribute(Attributes.LABELANGLE, labelAngle));
            }
        }
    }

    /** first character that is not replaced by underscore. */
    private static final char MIN_OUT_CHAR = 32;
    /** last character that is not replaced by underscore. */
    private static final char MAX_OUT_CHAR = 126;

    /**
     * Creates a properly parseable string by adding the escape character '\\' wherever needed and
     * replacing illegal characters.
     * 
     * @param label a label string from the KGraph structure
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

    /** node types used for identifier generation. */
    private enum NodeType {
        NODE, CLUSTER, DUMMY;
    }
    
    /**
     * Creates a unique identifier for the given node.
     * 
     * @param node node for which an identifier shall be created
     * @param cluster whether a cluster id should be created
     * @return a unique string used to identify the node
     */
    private String getNodeID(final KNode node, final NodeType type) {
        switch (type) {
        case NODE:
            return "node" + node.hashCode();
        case CLUSTER:
            return "cluster" + node.hashCode();
        case DUMMY:
            return "dummy" + node.hashCode();
        }
        return null;
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
     * Applies layout information from a Graphviz model to the original graph.
     * Note that GraphViz uses cubic B-Splines for edge routing, some generalization of Bezier curves.
     * Edge offsets are given separately, since on some platforms edges seem to have a different
     * reference point than nodes.
     * 
     * @param parentNode parent node of the original graph
     * @param statements list of statements to process
     * @param baseOffset offset to be added to edge and subgraph coordinates
     * @param borderSpacing additional border spacing
     */
    private void applyLayout(final KNode parentNode, final List<Statement> statements,
            final KVector baseOffset, final float borderSpacing) {
        // process attributes: determine bounding box of the parent node
        float spacing = borderSpacing;
        KVector nodeOffset = new KVector();
        attr_loop: for (Statement statement : statements) {
            if (statement instanceof AttributeStatement) {
                AttributeStatement attributeStatement = (AttributeStatement) statement;
                if (attributeStatement.getType() == AttributeType.GRAPH) {
                    for (Attribute attribute : attributeStatement.getAttributes()) {
                        if (attribute.getName().equals(Attributes.BOUNDINGBOX)) {
                            try {
                                StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                                        ATTRIBUTE_DELIM);
                                double leftx = Double.parseDouble(tokenizer.nextToken());
                                double bottomy = Double.parseDouble(tokenizer.nextToken());
                                double rightx = Double.parseDouble(tokenizer.nextToken());
                                double topy = Double.parseDouble(tokenizer.nextToken());
                                // set parent node attributes
                                KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
                                KInsets insets = parentLayout.getInsets();
                                float width = (float) (rightx - leftx) + insets.getLeft()
                                        + insets.getRight();
                                float height = (float) (bottomy - topy) + insets.getTop()
                                        + insets.getBottom();
                                if (parentNode == kgraph) {
                                    width += 2 * spacing;
                                    height += 2 * spacing;
                                    baseOffset.translate(-leftx, -topy);
                                } else {
                                    parentLayout.setXpos((float) (baseOffset.x + leftx
                                            - insets.getLeft() + spacing));
                                    parentLayout.setYpos((float) (baseOffset.y + topy
                                            - insets.getTop() + spacing));
                                    spacing = 0;
                                    nodeOffset.x = -(baseOffset.x + leftx);
                                    nodeOffset.y = -(baseOffset.y + topy);
                                }
                                KimlUtil.resizeNode(parentNode, width, height, false);
                                parentLayout.setProperty(LayoutOptions.FIXED_SIZE, true);
                                break attr_loop;
                            } catch (NumberFormatException exception) {
                                // ignore exception
                            } catch (NoSuchElementException exception) {
                                // ignore exception
                            }
                        }
                    }
                }
            }
        }
        
        // process nodes and subgraphs to collect all offset data
        for (Statement statement : statements) {
            if (statement instanceof NodeStatement) {
                applyNodeLayout((NodeStatement) statement, nodeOffset, spacing);
            } else if (statement instanceof Subgraph) {
                Subgraph subgraph = (Subgraph) statement;
                KNode knode = (KNode) graphElementMap.get(subgraph.getName());
                applyLayout(knode, subgraph.getStatements(), baseOffset, spacing);
                KShapeLayout subGraphLayout = knode.getData(KShapeLayout.class);
                subGraphLayout.setXpos(subGraphLayout.getXpos() + (float) nodeOffset.x);
                subGraphLayout.setYpos(subGraphLayout.getYpos() + (float) nodeOffset.y);
            }
        }
    }
    
    /**
     * Set the position of a node.
     * 
     * @param nodeStatement a node statement
     * @param offset the offset to be added to node coordinates
     * @param spacing additional border spacing
     */
    private void applyNodeLayout(final NodeStatement nodeStatement, final KVector offset,
            final float spacing) {
        KNode knode = (KNode) graphElementMap.get(nodeStatement.getNode().getName());
        if (knode == null) {
            return;
        }
        KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
        float xpos = 0.0f, ypos = 0.0f, width = 0.0f, height = 0.0f;
        for (Attribute attribute : nodeStatement.getAttributes()) {
            try {
                if (attribute.getName().equals(Attributes.POS)) {
                    KVector pos = new KVector();
                    pos.parse((attribute.getValue()));
                    xpos = (float) (pos.x + offset.x + spacing);
                    ypos = (float) (pos.y + offset.y + spacing);
                } else if (attribute.getName().equals(Attributes.WIDTH)) {
                    StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                            ATTRIBUTE_DELIM);
                    width = Float.parseFloat(tokenizer.nextToken()) * DPI;
                } else if (attribute.getName().equals(Attributes.HEIGHT)) {
                    StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(),
                            ATTRIBUTE_DELIM);
                    height = Float.parseFloat(tokenizer.nextToken()) * DPI;
                }
            } catch (NumberFormatException exception) {
                // ignore exception
            } catch (IllegalArgumentException exception) {
                // ignore exception
            } catch (NoSuchElementException exception) {
                // ignore exception
            }
        }
        nodeLayout.setXpos(xpos - width / 2);
        nodeLayout.setYpos(ypos - height / 2);
    }
    
    /**
     * Sets the bend points of an edge.
     * 
     * @param edgeStatement an edge statement
     * @param edgeOffset offset to be added to edge coordinates
     * @param spacing additional border spacing
     */
    private void applyEdgeLayout(final EdgeStatement edgeStatement, final KVector edgeOffset,
            final float spacing) {
        Map<String, String> attributeMap = createAttributeMap(edgeStatement.getAttributes());
        KEdge kedge = (KEdge) graphElementMap.get(attributeMap.get(Attributes.COMMENT));
        if (kedge == null) {
            return;
        }
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        List<KPoint> edgePoints = edgeLayout.getBendPoints();
        edgePoints.clear();
        String posString = attributeMap.get(Attributes.POS);
        if (posString == null) {
            posString = "";
        }
        
        KNode referenceNode = kedge.getSource();
        if (!KimlUtil.isDescendant(kedge.getTarget(), referenceNode)) {
            referenceNode = referenceNode.getParent();
        }
        KVector reference = new KVector();
        while (referenceNode != null && referenceNode != kgraph) {
            KShapeLayout nodeLayout = referenceNode.getData(KShapeLayout.class);
            reference.x += nodeLayout.getXpos() + nodeLayout.getInsets().getLeft();
            reference.y += nodeLayout.getYpos() + nodeLayout.getInsets().getTop();
            referenceNode = referenceNode.getParent();
        }
        KVector offset = edgeOffset.differenceCreate(reference);

        // parse the list of spline control coordinates
        List<KVectorChain> splines = new LinkedList<KVectorChain>();
        Pair<KVector, KVector> endpoints = parseSplinePoints(posString, splines, offset);

        KVector sourcePoint = endpoints.getFirst();
        KVector targetPoint = endpoints.getSecond();
        if (!splines.isEmpty()) {
            KLayoutDataFactory layoutDataFactory = KLayoutDataFactory.eINSTANCE;
            // the first point in the list is the start point, if no arrowhead is given
            if (sourcePoint == null) {
                List<KVector> points = splines.get(0);
                if (!points.isEmpty()) {
                    sourcePoint = points.remove(0);
                } else {
                    KShapeLayout sourceLayout = kedge.getSource().getData(KShapeLayout.class);
                    sourcePoint = new KVector();
                    sourcePoint.x = sourceLayout.getXpos() + sourceLayout.getWidth() / 2;
                    sourcePoint.y = sourceLayout.getYpos() + sourceLayout.getHeight() / 2;
                }
            }
            // the last point in the list is the end point, if no arrowhead is given
            if (targetPoint == null) {
                List<KVector> points = splines.get(splines.size() - 1);
                if (!points.isEmpty()) {
                    targetPoint = points.remove(points.size() - 1);
                } else {
                    KShapeLayout targetLayout = kedge.getTarget().getData(KShapeLayout.class);
                    targetPoint = new KVector();
                    targetPoint.x = targetLayout.getXpos() + targetLayout.getWidth() / 2;
                    targetPoint.y = targetLayout.getYpos() + targetLayout.getHeight() / 2;
                }
            }
            // add all other control points to the edge
            for (KVectorChain points : splines) {
                for (KVector point : points) {
                    KPoint controlPoint = layoutDataFactory.createKPoint();
                    controlPoint.applyVector(point);
                    edgePoints.add(controlPoint);
                }
            }
            edgeLayout.getSourcePoint().applyVector(sourcePoint);
            edgeLayout.getTargetPoint().applyVector(targetPoint);
        }
        if (useSplines) {
            edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
        }

        // process the edge labels
        String labelPos = attributeMap.get(Attributes.LABELPOS);
        if (labelPos != null) {
            applyEdgeLabelPos(kedge, labelPos, EdgeLabelPlacement.CENTER, offset);
        }
        labelPos = attributeMap.get(Attributes.HEADLP);
        if (labelPos != null) {
            applyEdgeLabelPos(kedge, labelPos, EdgeLabelPlacement.HEAD, offset);
        }
        labelPos = attributeMap.get(Attributes.TAILLP);
        if (labelPos != null) {
            applyEdgeLabelPos(kedge, labelPos, EdgeLabelPlacement.TAIL, offset);
        }
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
            final EdgeLabelPlacement placement, final KVector offset) {
        float combinedWidth = 0.0f, combinedHeight = 0.0f;
        for (KLabel label : kedge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            if (labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT) == placement) {
                combinedWidth = Math.max(combinedWidth, labelLayout.getWidth());
                combinedHeight += labelLayout.getHeight();
            }
        }
        try {
            KVector pos = new KVector();
            pos.parse(posString);
            float xpos = (float) (pos.x - combinedWidth / 2 + offset.x);
            float ypos = (float) (pos.y - combinedHeight / 2 + offset.y);
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
        } catch (IllegalArgumentException exception) {
            // ignore exception
        }
    }

    /**
     * Converts a list of attributes to a map of attribute names to their values.
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
     * @param offset offset to add to coordinates
     * @return the source and the target point, if specified by the position string
     */
    private static Pair<KVector, KVector> parseSplinePoints(final String posString,
            final List<KVectorChain> splines, final KVector offset) {
        KVector sourcePoint = null, targetPoint = null;
        StringTokenizer splinesTokenizer = new StringTokenizer(posString, "\";");
        while (splinesTokenizer.hasMoreTokens()) {
            KVectorChain pointList = new KVectorChain();
            StringTokenizer posTokenizer = new StringTokenizer(splinesTokenizer.nextToken(), " \t");
            while (posTokenizer.hasMoreTokens()) {
                String token = posTokenizer.nextToken();
                try {
                    if (token.startsWith("s")) {
                        if (sourcePoint == null) {
                            sourcePoint = new KVector();
                            int commaIndex = token.indexOf(',');
                            sourcePoint.parse(token.substring(commaIndex + 1));
                            sourcePoint.add(offset);
                        }
                    } else if (token.startsWith("e")) {
                        if (targetPoint == null) {
                            targetPoint = new KVector();
                            int commaIndex = token.indexOf(',');
                            targetPoint.parse(token.substring(commaIndex + 1));
                            targetPoint.add(offset);
                        }
                    } else {
                        KVector point = new KVector();
                        point.parse(token);
                        pointList.add(point.add(offset));
                    }
                } catch (IllegalArgumentException exception) {
                    // ignore exception
                }
            }
            splines.add(pointList);
        }
        return new Pair<KVector, KVector>(sourcePoint, targetPoint);
    }
    
}
