/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.ElkShape;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Maps;

import net.ogdf.bin.OgdfServer;
import net.ogdf.bin.OgdfServer.Cleanup;
import net.ogdf.bin.OgdfServerException;
import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.EdgeType;
import net.ogdf.ogml.GraphType;
import net.ogdf.ogml.LabelType;
import net.ogdf.ogml.LayoutType;
import net.ogdf.ogml.LocationType;
import net.ogdf.ogml.NodeLayoutType;
import net.ogdf.ogml.NodeType;
import net.ogdf.ogml.OgmlFactory;
import net.ogdf.ogml.OgmlType;
import net.ogdf.ogml.ShapeType1;
import net.ogdf.ogml.SourceTargetType;
import net.ogdf.ogml.StructureType;
import net.ogdf.ogml.StylesType;
import net.ogdf.ogml.impl.OgmlFactoryImpl;
import net.ogdf.ogml.util.OgmlResourceFactoryImpl;

/**
 * Communication with the OGDF server process using the OGML format.
 * 
 * @author mri
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class OgmlServerCommunicator {

    /** default value for border spacing. */
    public static final double DEF_BORDER_SPACING = 15.0;

    /** the input format for the ogdf server. */
    private static final String INPUT_FORMAT = "OGML";
    /** the separator used to separate chunks of data sent to the ogdf-server process. */
    private static final String CHUNK_KEYWORD = "[CHUNK]\n";
    /** the minimal distance of bend points. */
    private static final double MIN_POINT_DIST = 2.0;

    /** the current id for the generation of node ids. */
    private int nodeIdCounter = 0;
    /** the current id for the generation of edge ids. */
    private int edgeIdCounter = 0;
    /** map of KNodes to ids. */
    private Map<ElkNode, String> node2IdMap = new LinkedHashMap<>();
    /** map of edge ids to KEdges. */
    private Map<String, ElkEdge> id2EdgeMap = new LinkedHashMap<>();

    /** the buffer for serialized ogdf options. */
    private List<String> optionBuffer = new LinkedList<String>();
    /** the buffer for additional serialized information about the graph. */
    private List<String> infoBuffer = new LinkedList<String>();

    /**
     * Resets all temporary counters, buffers and maps.
     */
    private void reset() {
        // reset id generators
        nodeIdCounter = 0;
        edgeIdCounter = 0;
        // reset mappings
        node2IdMap.clear();
        id2EdgeMap.clear();
        // reset buffers
        optionBuffer.clear();
        infoBuffer.clear();
    }

    private static final int SUBTASK_WORK = 1;
    private static final int LAYOUT_WORK = SUBTASK_WORK + SUBTASK_WORK + SUBTASK_WORK + SUBTASK_WORK;

    /**
     * Send a layout request to the OGDF server.
     * 
     * @param layoutNode
     *            the node representing the graph
     * @param progressMonitor
     *            the progress monitor
     * @param ogdfServer
     *            the OGDF server process interface
     */
    public void requestLayout(final ElkNode layoutNode, final IElkProgressMonitor progressMonitor,
            final OgdfServer ogdfServer) {
        
        progressMonitor.begin("OGDF Layout", LAYOUT_WORK);
        // if the graph is empty there is no need to layout
        if (layoutNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }

        // start the OGDF server process, or retrieve the previously used process
        ogdfServer.initialize(INPUT_FORMAT);

        // transform the graph
        DocumentRoot root = transformGraph(layoutNode, progressMonitor.subTask(SUBTASK_WORK));

        try {
            // retrieve the OGDF server input
            OutputStream outputStream = ogdfServer.input();
            // write the graph to the process
            writeOgmlGraph(root, outputStream, progressMonitor.subTask(SUBTASK_WORK), ogdfServer);
            // flush the stream
            outputStream.flush();
        
            // read the layout information
            Map<String, KVectorChain> layoutInformation = readLayoutInformation(ogdfServer,
                    progressMonitor.subTask(SUBTASK_WORK));
            // apply the layout back to the KGraph
            applyLayout(layoutNode, layoutInformation, progressMonitor.subTask(SUBTASK_WORK));
            // clean up the OGDF server process
            ogdfServer.cleanup(Cleanup.NORMAL);

        } catch (IOException exception) {
            ogdfServer.cleanup(Cleanup.ERROR);
            throw new WrappedException("Failed to communicate with the OGDF process.", exception);
        } finally {
            progressMonitor.done();
            reset();
        }
    }

    /**
     * Adds an option for the next layout.
     * 
     * @param key
     *            the option key
     * @param value
     *            the value
     */
    public void addOption(final String key, final Object value) {
        optionBuffer.add(key + "=" + value.toString());
    }

    /**
     * Adds additional information for the next layout.
     * 
     * @param key
     *            the option key
     * @param value
     *            the value
     */
    private void addInformation(final String key, final Object value) {
        infoBuffer.add(key + "=" + value.toString());
    }

    /**
     * Transforms the given layout graph into an OGML graph (ignores hierarchy).
     * 
     * @param parentNode
     *            the parent node of the layout graph
     * @param progressMonitor
     *            the progress monitor
     * @return an OGML graph
     */
    private DocumentRoot transformGraph(final ElkNode parentNode,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Transform KGraph to OGML", 1);
        boolean umlGraph = false;
        
        // create the graph
        OgmlFactory factory = OgmlFactoryImpl.eINSTANCE;
        DocumentRoot root = factory.createDocumentRoot();
        OgmlType ogml = factory.createOgmlType();
        root.setOgml(ogml);
        GraphType graph = factory.createGraphType();
        ogml.setGraph(graph);
        StructureType structure = factory.createStructureType();
        graph.setStructure(structure);
        LayoutType layout = factory.createLayoutType();
        graph.setLayout(layout);
        StylesType styles = factory.createStylesType();
        layout.setStyles(styles);
        
        // transform nodes (only top level)
        for (ElkNode node : parentNode.getChildren()) {
            String id = generateId(node);
            NodeType ogmlNode = factory.createNodeType();
            ogmlNode.setId(id);
            
            // create node label for storage of node id
            LabelType label = factory.createLabelType();
            label.setId("l" + id);
            label.setContent(id);
            ogmlNode.getLabel().add(label);
            
            // set layout information
            NodeLayoutType ogmlNodeLayout = factory.createNodeLayoutType();
            ogmlNodeLayout.setIdRef(id);
            LocationType location = factory.createLocationType();
            location.setX(node.getX() + node.getWidth() / 2);
            location.setY(node.getY() + node.getHeight() / 2);
            ogmlNodeLayout.setLocation(location);
            ElkUtil.resizeNode(node);
            ShapeType1 shape = factory.createShapeType1();
            shape.setWidth(BigInteger.valueOf(Math.round(node.getWidth())));
            shape.setHeight(BigInteger.valueOf(Math.round(node.getHeight())));
            ogmlNodeLayout.setShape(shape);
            styles.getNodeStyle().add(ogmlNodeLayout);
            
            // add the node
            structure.getNode().add(ogmlNode);
        }
        
        // transform edges
        boolean processLabels = parentNode.getProperty(AlgorithmSetup.PLACE_LABELS);
        for (ElkNode sourceNode : parentNode.getChildren()) {
            for (ElkEdge edge : sourceNode.getOutgoingEdges()) {
                ElkNode targetNode = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
                
                // ignore cross-hierarchy edges and self-loops
                if (!edge.isHierarchical() && !edge.isSelfloop()) {
                    String id = generateId(edge);
                    EdgeType ogmlEdge = factory.createEdgeType();
                    ogmlEdge.setId(id);
                    SourceTargetType ogmlSource = factory.createSourceTargetType();
                    SourceTargetType ogmlTarget = factory.createSourceTargetType();
                    ogmlSource.setIdRef(node2IdMap.get(sourceNode));
                    ogmlTarget.setIdRef(node2IdMap.get(targetNode));
                    ogmlEdge.getSource().add(ogmlSource);
                    ogmlEdge.getTarget().add(ogmlTarget);
                    
                    // create edge label for storage of edge id
                    LabelType ogdfLabel = factory.createLabelType();
                    ogdfLabel.setId("l" + id);
                    ogdfLabel.setContent(id);
                    ogmlEdge.getLabel().add(ogdfLabel);
                    
                    // store labels as additional information
                    if (processLabels) {
                        boolean makeMult1 = false, makeMult2 = false;
                        for (ElkLabel label : edge.getLabels()) {
                            EdgeLabelPlacement placement =
                                    label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
                            int labelType = OgdfServer.LABEL_TYPE_NAME;
                            switch (placement) {
                            case HEAD:
                                if (makeMult2) {
                                    labelType = OgdfServer.LABEL_TYPE_MULT2;
                                } else {
                                    labelType = OgdfServer.LABEL_TYPE_END2;
                                }
                                makeMult2 = !makeMult2;
                                break;
                            case TAIL:
                                if (makeMult1) {
                                    labelType = OgdfServer.LABEL_TYPE_MULT1;
                                } else {
                                    labelType = OgdfServer.LABEL_TYPE_END1;
                                }
                                makeMult1 = !makeMult1;
                                break;
                            }
                            addInformation(id + OgdfServer.EDGE_LABEL_SUFFIX + labelType,
                                    "(" + label.getWidth() + "," + label.getHeight() + ")");
                        }
                    }
                    
                    // detect an uml-graph
                    org.eclipse.elk.core.options.EdgeType edgeType =
                            edge.getProperty(CoreOptions.EDGE_TYPE);
                    switch (edgeType) {
                    case ASSOCIATION:
                        umlGraph = true;
                        addInformation(id + OgdfServer.EDGE_TYPE_SUFFIX,
                                OgdfServer.EDGE_TYPE_ASSOCIATION);
                        break;
                    case DEPENDENCY:
                        umlGraph = true;
                        addInformation(id + OgdfServer.EDGE_TYPE_SUFFIX,
                                OgdfServer.EDGE_TYPE_DEPENDENCY);
                        break;
                    case GENERALIZATION:
                        umlGraph = true;
                        addInformation(id + OgdfServer.EDGE_TYPE_SUFFIX,
                                OgdfServer.EDGE_TYPE_GENERALIZATION);
                        break;
                    }
                    
                    // add the edge
                    structure.getEdge().add(ogmlEdge);
                }
            }
        }
        
        addInformation(OgdfServer.INFO_UML_GRAPH, umlGraph);
        progressMonitor.done();
        return root;
    }

    /**
     * Generates an identifer for the given node and creates the mapping.
     * 
     * @param node
     *            the node
     * @return the identifier
     */
    private String generateId(final ElkNode node) {
        String id = "n" + nodeIdCounter++;
        node2IdMap.put(node, id);
        return id;
    }

    /**
     * Generates an identifier for the given edge and creates the mapping.
     * 
     * @param edge
     *            the edge
     * @return the identifier
     */
    private String generateId(final ElkEdge edge) {
        String id = "e" + edgeIdCounter++;
        id2EdgeMap.put(id, edge);
        return id;
    }

    /**
     * Assigns OGDF geometry to a KShape layout.
     * 
     * @param layout
     *            the shape layout
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    private void toKShape(final ElkShape layout, final double x, final double y,
            final double width, final double height) {
        
        layout.setX(x - width / 2);
        layout.setY(y - height / 2);
    }

    /**
     * Applies the layout information back to the original graph.
     * 
     * @param parentNode
     *            the parent node of the layout graph
     * @param layoutInformation
     *            the layout information
     * @param progressMonitor
     *            the progress monitor
     */
    private void applyLayout(final ElkNode parentNode,
            final Map<String, KVectorChain> layoutInformation,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Apply layout", 1);
        
        // get the border spacing
        double borderSpacing = parentNode.getProperty(CoreOptions.SPACING_BORDER);
        if (borderSpacing < 0) {
            borderSpacing = DEF_BORDER_SPACING;
        }
        
        // dem insets!
        ElkPadding elkPadding = parentNode.getProperty(CoreOptions.PADDING);
        
        // calculate offsets and parent size
        float boundingBoxWidth = Float.NaN;
        float boundingBoxHeight = Float.NaN;
        
        double offsetX = borderSpacing + elkPadding.getLeft();
        double offsetY = borderSpacing + elkPadding.getTop();
        
        KVectorChain boundingBox = layoutInformation.get("graph");
        if (boundingBox != null && boundingBox.size() == 2) {
            KVector bbShape = boundingBox.getLast();
            boundingBoxWidth = (float) bbShape.x;
            boundingBoxHeight = (float) bbShape.y;
            KVector bbLocation = boundingBox.getFirst();
            if (!Double.isNaN(bbLocation.x)) {
                offsetX -= (float) bbLocation.x;
            }
            if (!Double.isNaN(bbLocation.y)) {
                offsetY -= (float) bbLocation.y;
            }
        }

        // apply node layout
        for (Map.Entry<ElkNode, String> entry : node2IdMap.entrySet()) {
            ElkNode node = entry.getKey();
            KVectorChain ogdfNodeLayout = layoutInformation.get(entry.getValue());
            if (ogdfNodeLayout != null && ogdfNodeLayout.size() == 2 && !ogdfNodeLayout.hasNaN()) {
                KVector location = ogdfNodeLayout.getFirst();
                KVector shape = ogdfNodeLayout.getLast();
                toKShape(node, location.x + offsetX, location.y + offsetY, shape.x, shape.y);
            }
        }
        
        // apply edge layout
        boolean processLabels = parentNode.getProperty(AlgorithmSetup.PLACE_LABELS);
        boolean adaptPortPositions = parentNode.getProperty(AlgorithmSetup.ADAPT_PORT_POSITIONS); 
        for (Map.Entry<String, ElkEdge> entry : id2EdgeMap.entrySet()) {
            ElkEdge elkedge = entry.getValue();
            
            // Intialize the edge's edge section
            ElkEdgeSection elkedgeSection = ElkGraphUtil.firstEdgeSection(elkedge, true, true);
            KVectorChain ogdfEdgeLayout = layoutInformation.get(entry.getKey());
            
            if (ogdfEdgeLayout != null && ogdfEdgeLayout.size() >= 2 && !ogdfEdgeLayout.hasNaN()) {
                Iterator<KVector> bendIt = ogdfEdgeLayout.iterator();
                KVector sourceBend = bendIt.next();
                
                // set the source point
                elkedgeSection.setStartLocation(sourceBend.x + offsetX, sourceBend.y + offsetY);
                
                if (adaptPortPositions && elkedge.getSources().get(0) instanceof ElkPort) {
                    ElkPort sourcePort = (ElkPort) elkedge.getSources().get(0);
                    ElkNode sourceNode = sourcePort.getParent();
                    
                    sourcePort.setX(elkedgeSection.getStartX() - sourceNode.getX()
                            - sourcePort.getWidth() / 2);
                    sourcePort.setY(elkedgeSection.getStartY() - sourceNode.getY()
                            - sourcePort.getHeight() / 2);
                }
                
                // set the bend points
                KVector lastBend = sourceBend;
                while (bendIt.hasNext()) {
                    KVector bend = bendIt.next();
                    if (Math.abs(bend.x - lastBend.x) + Math.abs(bend.y - lastBend.y)
                            >= MIN_POINT_DIST) {
                        
                        if (bendIt.hasNext()) {
                            ElkGraphUtil.createBendPoint(elkedgeSection,
                                    bend.x + offsetX, bend.y + offsetY);
                        }
                    } else if (!bendIt.hasNext() && elkedgeSection.getBendPoints().size() > 0) {
                        // the last bend point 
                        elkedgeSection.getBendPoints().remove(
                                elkedgeSection.getBendPoints().size() - 1);
                    }
                    lastBend = bend;
                }
                
                // set the target point
                elkedgeSection.setEndLocation(lastBend.x + offsetX, lastBend.y + offsetY);
                
                if (adaptPortPositions && elkedge.getTargets().get(0) instanceof ElkPort) {
                    ElkPort targetPort = (ElkPort) elkedge.getTargets().get(0);
                    ElkNode targetNode = targetPort.getParent();
                    
                    targetPort.setX(elkedgeSection.getEndX() - targetNode.getX()
                            - targetPort.getWidth() / 2);
                    targetPort.setY(elkedgeSection.getEndY() - targetNode.getY()
                            - targetPort.getHeight() / 2);
                }
            }
            
            // set label layout
            if (processLabels) {
                boolean makeMult1 = false, makeMult2 = false;
                for (ElkLabel label : elkedge.getLabels()) {
                    EdgeLabelPlacement placement =
                            label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
                    int labelType = OgdfServer.LABEL_TYPE_NAME;
                    
                    switch (placement) {
                    case HEAD:
                        if (makeMult2) {
                            labelType = OgdfServer.LABEL_TYPE_MULT2;
                        } else {
                            labelType = OgdfServer.LABEL_TYPE_END2;
                        }
                        makeMult2 = !makeMult2;
                        break;
                    case TAIL:
                        if (makeMult1) {
                            labelType = OgdfServer.LABEL_TYPE_MULT1;
                        } else {
                            labelType = OgdfServer.LABEL_TYPE_END1;
                        }
                        makeMult1 = !makeMult1;
                        break;
                    }
                    
                    KVectorChain ogdfLabelLayout = layoutInformation.get(entry.getKey()
                            + OgdfServer.EDGE_LABEL_SUFFIX + labelType);
                    
                    if (ogdfLabelLayout != null && ogdfLabelLayout.size() == 1
                            && !ogdfLabelLayout.hasNaN()) {
                        
                        KVector labelPos = ogdfLabelLayout.getFirst();
                        toKShape(label, labelPos.x + offsetX, labelPos.y + offsetY,
                                label.getWidth(), label.getHeight());
                    }
                }
            }
        }
        
        // set the width/height of the graph
        if (!(Double.isNaN(boundingBoxWidth) || Double.isNaN(boundingBoxHeight))) {
            double width = boundingBoxWidth + 2 * borderSpacing
                    + elkPadding.getLeft() + elkPadding.getRight();
            double height = boundingBoxHeight + 2 * borderSpacing
                    + elkPadding.getTop() + elkPadding.getBottom();
            
            ElkUtil.resizeNode(parentNode, width, height, false, true);
        }
        
        progressMonitor.done();
    }

    /**
     * Writes the given OGML graph to the output stream.
     * 
     * @param root
     *            the graph root
     * @param outputStream
     *            the output stream
     * @param progressMonitor
     *            the progress monitor
     * @param ogdfServer
     *            the OGDF server process interface
     * @throws IOException if writing to the process fails
     */
    private void writeOgmlGraph(final DocumentRoot root, final OutputStream outputStream,
            final IElkProgressMonitor progressMonitor, final OgdfServer ogdfServer)
            throws IOException {
        progressMonitor.begin("Serialize OGML graph", 1);
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("ogml", new OgmlResourceFactoryImpl());
        
        Resource resource = resourceSet.createResource(URI.createURI("output.ogml"));
        resource.getContents().add(root);
        
        // write OGML graph to the process
        resource.save(outputStream, null);
        
        // write the buffers to the process
        writeBuffers(outputStream);
        
        progressMonitor.done();
    }

    /**
     * Writes the specified options and additional information to the output stream.
     * 
     * @param outputStream
     *            the output stream
     */
    private void writeBuffers(final OutputStream outputStream) {
        PrintStream printStream = new PrintStream(outputStream);
        printStream.print(CHUNK_KEYWORD);
        // write options
        for (String optionString : optionBuffer) {
            printStream.println(optionString);
        }
        printStream.print(CHUNK_KEYWORD);
        // write additional information
        for (String infoString : infoBuffer) {
            printStream.println(infoString);
        }
        printStream.print(CHUNK_KEYWORD);
        printStream.flush();
    }
    
    /**
     * Read layout information from the OGDF server process.
     * 
     * @param ogdfServer
     *            the OGDF server process interface
     * @param progressMonitor
     *            the progress monitor
     * @return a map of layout information
     */
    private Map<String, KVectorChain> readLayoutInformation(final OgdfServer ogdfServer,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Read output from OGDF", 1);
        Map<String, String> outputData = ogdfServer.readOutputData();
        if (outputData == null) {
            ogdfServer.cleanup(Cleanup.ERROR);
            throw new OgdfServerException("No output from the OGDF process."
                    + " Try increasing the timeout value in the preferences"
                    + " (KIELER / Layout / OGDF).");
        }
        Map<String, KVectorChain> layoutInformation = Maps.newHashMapWithExpectedSize(
                outputData.size());
        for (Map.Entry<String, String> entry : outputData.entrySet()) {
            KVectorChain pointList = new KVectorChain();
            StringTokenizer tokenizer = new StringTokenizer(entry.getValue(), ",() \t");
            while (tokenizer.countTokens() >= 2) {
                double x = parseDouble(tokenizer.nextToken());
                double y = parseDouble(tokenizer.nextToken());
                pointList.add(new KVector(x, y));
            }
            layoutInformation.put(entry.getKey(), pointList);
        }
        progressMonitor.done();
        return layoutInformation;
    }
    
    /**
     * Parse a double value ignoring illegal string values.
     * 
     * @param string a string value
     * @return the corresponding double, or NaN if the string is illegal
     */
    private static double parseDouble(final String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            // the vector chain could not be parsed - return NaN
            return Double.NaN;
        }
    }
    
}
