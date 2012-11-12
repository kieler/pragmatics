/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
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

import org.eclipse.emf.common.util.EList;
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

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

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
    public static final float DEF_BORDER_SPACING = 15.0f;

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
    private Map<KNode, String> node2IdMap = new LinkedHashMap<KNode, String>();
    /** map of edge ids to KEdges. */
    private Map<String, KEdge> id2EdgeMap = new LinkedHashMap<String, KEdge>();

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
    public void requestLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor,
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
            throw new WrappedException(exception, "Failed to communicate with the OGDF process.");
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
    private DocumentRoot transformGraph(final KNode parentNode,
            final IKielerProgressMonitor progressMonitor) {
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
        for (KNode node : parentNode.getChildren()) {
            String id = generateId(node);
            NodeType ogmlNode = factory.createNodeType();
            ogmlNode.setId(id);
            
            // create node label for storage of node id
            LabelType label = factory.createLabelType();
            label.setId("l" + id);
            label.setContent(id);
            ogmlNode.getLabel().add(label);
            
            // set layout information
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            NodeLayoutType ogmlNodeLayout = factory.createNodeLayoutType();
            ogmlNodeLayout.setIdRef(id);
            LocationType location = factory.createLocationType();
            location.setX(nodeLayout.getXpos() + nodeLayout.getWidth() / 2);
            location.setY(nodeLayout.getYpos() + nodeLayout.getHeight() / 2);
            ogmlNodeLayout.setLocation(location);
            KimlUtil.resizeNode(node);
            ShapeType1 shape = factory.createShapeType1();
            shape.setWidth(BigInteger.valueOf(Math.round(nodeLayout.getWidth())));
            shape.setHeight(BigInteger.valueOf(Math.round(nodeLayout.getHeight())));
            ogmlNodeLayout.setShape(shape);
            styles.getNodeStyle().add(ogmlNodeLayout);
            
            // add the node
            structure.getNode().add(ogmlNode);
        }
        
        // transform edges
        boolean processLabels = parentNode.getData(KShapeLayout.class).getProperty(
                AlgorithmSetup.PLACE_LABELS);
        for (KNode sourceNode : parentNode.getChildren()) {
            for (KEdge edge : sourceNode.getOutgoingEdges()) {
                KNode targetNode = edge.getTarget();
                
                // ignore cross-hierarchy edges and self-loops
                if (targetNode.getParent() == parentNode && sourceNode != targetNode) {
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
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
                        for (KLabel label : edge.getLabels()) {
                            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                            EdgeLabelPlacement placement =
                                    labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT);
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
                                    "(" + labelLayout.getWidth() + "," + labelLayout.getHeight() + ")");
                        }
                    }
                    
                    // detect an uml-graph
                    de.cau.cs.kieler.kiml.options.EdgeType edgeType =
                            edgeLayout.getProperty(LayoutOptions.EDGE_TYPE);
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
    private String generateId(final KNode node) {
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
    private String generateId(final KEdge edge) {
        String id = "e" + edgeIdCounter++;
        id2EdgeMap.put(id, edge);
        return id;
    }

    /**
     * Transforms an ogdf point to a KPoint.
     * 
     * @param x
     *            the x coordinate of the ogdf point
     * @param y
     *            the y coordinate of the ogdf point
     * @param offsetX
     *            the x offset
     * @param offsetY
     *            the y offset
     * @return the point as KPoint
     */
    private KPoint toKPoint(final float x, final float y, final float offsetX, final float offsetY) {
        KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        kpoint.setPos(x + offsetX, y + offsetY);
        return kpoint;
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
    private void toKShape(final KShapeLayout layout, final float x, final float y,
            final float width, final float height) {
        layout.setXpos(x - width / 2);
        layout.setYpos(y - height / 2);
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
    private void applyLayout(final KNode parentNode,
            final Map<String, KVectorChain> layoutInformation,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Apply layout", 1);
        
        // get the border spacing
        KShapeLayout parentNodeLayout = parentNode.getData(KShapeLayout.class);
        float borderSpacing = parentNodeLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEF_BORDER_SPACING;
        }
        
        // calculate offsets and parent size
        float boundingBoxWidth = Float.NaN;
        float boundingBoxHeight = Float.NaN;
        float offsetX = borderSpacing;
        float offsetY = borderSpacing;
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
        for (Map.Entry<KNode, String> entry : node2IdMap.entrySet()) {
            KShapeLayout nodeLayout = entry.getKey().getData(KShapeLayout.class);
            KVectorChain ogdfNodeLayout = layoutInformation.get(entry.getValue());
            if (ogdfNodeLayout != null && ogdfNodeLayout.size() == 2 && !ogdfNodeLayout.isNaN()) {
                KVector location = ogdfNodeLayout.getFirst();
                KVector shape = ogdfNodeLayout.getLast();
                toKShape(nodeLayout, (float) location.x + offsetX, (float) location.y + offsetY,
                        (float) shape.x, (float) shape.y);
            }
        }
        
        // apply edge layout
        boolean processLabels = parentNodeLayout.getProperty(AlgorithmSetup.PLACE_LABELS);
        for (Map.Entry<String, KEdge> entry : id2EdgeMap.entrySet()) {
            KEdge kedge = entry.getValue();
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            EList<KPoint> kbends = edgeLayout.getBendPoints();
            kbends.clear();
            KVectorChain ogdfEdgeLayout = layoutInformation.get(entry.getKey());
            if (ogdfEdgeLayout != null && ogdfEdgeLayout.size() >= 2 && !ogdfEdgeLayout.isNaN()) {
                Iterator<KVector> bendIt = ogdfEdgeLayout.iterator();
                KVector sourceBend = bendIt.next();
                
                // set the source point
                KPoint sourcePoint =
                        toKPoint((float) sourceBend.x, (float) sourceBend.y, offsetX, offsetY);
                edgeLayout.setSourcePoint(sourcePoint);
                if (kedge.getSourcePort() != null) {
                    KShapeLayout portLayout = kedge.getSourcePort().getData(KShapeLayout.class);
                    KShapeLayout sourceLayout = kedge.getSource().getData(KShapeLayout.class);
                    portLayout.setXpos(sourcePoint.getX() - sourceLayout.getXpos()
                            - portLayout.getWidth() / 2);
                    portLayout.setYpos(sourcePoint.getY() - sourceLayout.getYpos()
                            - portLayout.getHeight() / 2);
                }
                
                // set the bend points
                KVector lastBend = sourceBend;
                while (bendIt.hasNext()) {
                    KVector bend = bendIt.next();
                    if (Math.abs(bend.x - lastBend.x) + Math.abs(bend.y - lastBend.y)
                            >= MIN_POINT_DIST) { 
                        if (bendIt.hasNext()) {
                            kbends.add(toKPoint((float) bend.x, (float) bend.y, offsetX, offsetY));
                        }
                    } else if (!bendIt.hasNext()) {
                        kbends.remove(kbends.size() - 1);
                    }
                    lastBend = bend;
                }
                
                // set the target point
                KPoint targetPoint =
                        toKPoint((float) lastBend.x, (float) lastBend.y, offsetX, offsetY);
                edgeLayout.setTargetPoint(targetPoint);
                if (kedge.getTargetPort() != null) {
                    KShapeLayout portLayout = kedge.getTargetPort().getData(KShapeLayout.class);
                    KShapeLayout targetLayout = kedge.getTarget().getData(KShapeLayout.class);
                    portLayout.setXpos(targetPoint.getX() - targetLayout.getXpos()
                            - portLayout.getWidth() / 2);
                    portLayout.setYpos(targetPoint.getY() - targetLayout.getYpos()
                            - portLayout.getHeight() / 2);
                }
            }
            
            // set label layout
            if (processLabels) {
                boolean makeMult1 = false, makeMult2 = false;
                for (KLabel label : kedge.getLabels()) {
                    KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                    EdgeLabelPlacement placement =
                            labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT);
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
                            && !ogdfLabelLayout.isNaN()) {
                        KVector labelPos = ogdfLabelLayout.getFirst();
                        toKShape(labelLayout, (float) labelPos.x + offsetX, (float) labelPos.y
                                + offsetY, labelLayout.getWidth(), labelLayout.getHeight());
                    }
                }
            }
        }
        
        // get the insets
        KInsets insets = parentNodeLayout.getInsets();
        // set the width/height of the graph
        if (!(Double.isNaN(boundingBoxWidth) || Double.isNaN(boundingBoxHeight))) {
            float width = boundingBoxWidth + 2 * borderSpacing + insets.getLeft() + insets.getRight();
            float height = boundingBoxHeight + 2 * borderSpacing + insets.getTop() + insets.getBottom();
            KimlUtil.resizeNode(parentNode, width, height, false);
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
            final IKielerProgressMonitor progressMonitor, final OgdfServer ogdfServer)
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
            final IKielerProgressMonitor progressMonitor) {
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
