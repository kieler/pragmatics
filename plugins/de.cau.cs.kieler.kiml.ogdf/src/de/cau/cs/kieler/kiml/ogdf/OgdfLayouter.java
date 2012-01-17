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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import net.ogdf.bin.OgdfServer;
import net.ogdf.bin.OgdfServer.Aborter;
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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.NonBlockingInputStream;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The base wrapper class for all OGDF layouters.
 * 
 * @author mri
 */
public abstract class OgdfLayouter {

    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 15;

    /** layout option identifier for label edge distance. */
    public static final String LABEL_EDGE_DIST_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.labelEdgeDistance";
    /** default value for label edge distance. */
    public static final float DEF_LABEL_EDGE_DIST = 15.0f;
    /** label edge distance property. */
    public static final IProperty<Float> LABEL_EDGE_DIST = new Property<Float>(LABEL_EDGE_DIST_ID,
            DEF_LABEL_EDGE_DIST);

    /** layout option identifier for label margin distance. */
    public static final String LABEL_MARGIN_DIST_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.labelMarginDistance";
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DIST = 15.0f;
    /** label margin distance property. */
    public static final IProperty<Float> LABEL_MARGIN_DIST = new Property<Float>(
            LABEL_MARGIN_DIST_ID, DEF_LABEL_MARGIN_DIST);

    /** the ogdf server option for the layouter. */
    private static final String OGDF_OPTION_LAYOUTER = "layouter";

    /** the input format for the ogdf server. */
    public static final String INPUT_FORMAT = "OGML";
    /** the separator used to separate chunks of data sent to the ogdf-server process. */
    private static final String CHUNK_KEYWORD = "[CHUNK]\n";

    /** the resource set used for serialization of the graph. */
    private ResourceSet resourceSet;

    /** the name of the layouter. */
    private String name;
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
     * Contructs an OgdfLayouter.
     * 
     * @param theName
     *            the name of the layouter as used in the ogdf server
     */
    public OgdfLayouter(final String theName) {
        name = theName;
    }

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

    /** the debug canvas, which can be used to debug the OGDF wrapper. */
    @SuppressWarnings("unused")
    private IDebugCanvas debugCanvas;

    /**
     * Set the debug canvas.
     * 
     * @param thecanvas
     *            the debug canvas
     */
    public void setDebugCanvas(final IDebugCanvas thecanvas) {
        this.debugCanvas = thecanvas;
    }

    private static final int SMALL_TASK_WORK = 1;
    private static final int PROCESS_WORK = 5;
    private static final int LAYOUT_WORK = SMALL_TASK_WORK + SMALL_TASK_WORK + SMALL_TASK_WORK
            + PROCESS_WORK + SMALL_TASK_WORK;

    /**
     * Layouts the given graph.
     * 
     * @param layoutNode
     *            the node representing the graph
     * @param progressMonitor
     *            the progress monitor
     * @param ogdfServer
     *            the OGDF server process interface
     */
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor,
            final OgdfServer ogdfServer) {
        progressMonitor.begin("OGDF Layout", LAYOUT_WORK);
        // if the graph is empty there is no need to layout
        if (layoutNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }
        addOption(OGDF_OPTION_LAYOUTER, name);

        // create the resource set used for serialization of the graph
        if (resourceSet == null) {
            resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                    .put("ogml", new OgmlResourceFactoryImpl());
        }
        // start the ogdf server process, or retrieve the previously used process
        Process process = ogdfServer.startProcess(INPUT_FORMAT);

        // set the random number generator seed
        setRandomSeed(layoutNode);
        // prepare the algorithm for use and pre-process the graph
        prepareLayouter(layoutNode);
        // prepare the label layout
        prepareLabelLayout(layoutNode);
        // transform the graph
        DocumentRoot root = transformGraph(layoutNode, progressMonitor.subTask(SMALL_TASK_WORK));

        // receive the output stream and make it buffered
        OutputStream outputStream = new BufferedOutputStream(process.getOutputStream());
        // write the graph to the process
        writeOgmlGraph(root, outputStream, progressMonitor.subTask(SMALL_TASK_WORK), ogdfServer);
        // write the buffers to the process
        writeBuffers(outputStream, progressMonitor.subTask(SMALL_TASK_WORK));
        try {
            // flush the stream
            outputStream.flush();
            // wait for the process to finish the layout and send the layout information
            final IKielerProgressMonitor subMon = progressMonitor.subTask(PROCESS_WORK);
            subMon.begin("Wait OGDF Reply", 1);
            boolean dataok = ogdfServer.waitForInput(process.getInputStream(), new Aborter() {
                public boolean shouldAbort() {
                    return subMon.isCanceled();
                }
            });
            if (subMon.isCanceled()) {
                return;
            }
            if (!dataok) {
                // FIXME throw a more specific exception
                throw new RuntimeException("A timeout occured while waiting for the OGDF process."
                        + " Try increasing the timeout value in the preferences"
                        + " (KIELER / Layout / OGDF).");
            }
            subMon.done();

            // read the layout information
            Map<String, KVectorChain> layoutInformation =
                    readLayoutInformation(new BufferedInputStream(process.getInputStream()),
                            progressMonitor.subTask(SMALL_TASK_WORK), ogdfServer);
            // apply the layout back to the KGraph
            applyLayout(layoutNode, layoutInformation);
            // perform post-processing
            postProcess(layoutNode);

        } catch (IOException e) {
            throw new WrappedException(e, "Could not flush stdout.");
        } finally {
            progressMonitor.done();
            reset();
        }
    }

    /**
     * Set the random number generator seed.
     * 
     * @param node
     *            parent node from which the seed option is taken
     */
    private void setRandomSeed(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        Integer seed = nodeLayout.getProperty(LayoutOptions.RANDOM_SEED);
        if (seed == null) {
            addOption(OgdfServer.OPTION_RANDOM_SEED, 1);
        } else if (seed == 0) {
            addOption(OgdfServer.OPTION_RANDOM_SEED, (int) System.currentTimeMillis());
        } else {
            addOption(OgdfServer.OPTION_RANDOM_SEED, seed);
        }
    }

    /**
     * Sets the layout specific options and modules depending on the options defined in the node.
     * 
     * @param layoutNode
     *            the parent node
     */
    protected abstract void prepareLayouter(final KNode layoutNode);

    /**
     * Performs post-processing on the given node. The default implementation does nothing.
     * 
     * @param layoutNode
     *            the parent node
     */
    protected void postProcess(final KNode layoutNode) {
    }

    /**
     * Adds an option for the next layout.
     * 
     * @param key
     *            the option key
     * @param value
     *            the value
     */
    protected void addOption(final String key, final Object value) {
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
    protected void addInformation(final String key, final Object value) {
        infoBuffer.add(key + "=" + value.toString());
    }

    /**
     * Prepare the label layout.
     * 
     * @param layoutNode
     *            the parent layout node
     */
    protected void prepareLabelLayout(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // edgeDistance
        float edgeDistance = parentLayout.getProperty(LABEL_EDGE_DIST);
        if (edgeDistance < 0) {
            edgeDistance = DEF_LABEL_EDGE_DIST;
        }
        addOption(OgdfServer.OPTION_LABEL_EDGE_DISTANCE, edgeDistance);
        // marginDistance
        float marginDistance = parentLayout.getProperty(LABEL_MARGIN_DIST);
        if (marginDistance < 0) {
            marginDistance = DEF_LABEL_MARGIN_DIST;
        }
        addOption(OgdfServer.OPTION_LABEL_MARGIN_DISTANCE, marginDistance);
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
            // ignore ports and labels
            KimlUtil.excludePorts(node);
            KimlUtil.excludeLabels(node);
        }
        // transform edges
        for (KNode sourceNode : parentNode.getChildren()) {
            for (KEdge edge : sourceNode.getOutgoingEdges()) {
                KNode targetNode = edge.getTarget();
                // ignore cross-hierarchy edges
                if (targetNode.getParent() == parentNode) {
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
                        addInformation(id + OgdfServer.EDGE_LABEL_SUFFIX + labelType, "("
                                + labelLayout.getWidth() + "," + labelLayout.getHeight() + ")");
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
     */
    protected void applyLayout(final KNode parentNode,
            final Map<String, KVectorChain> layoutInformation) {
        // get the parent node layout
        KShapeLayout parentNodeLayout = parentNode.getData(KShapeLayout.class);
        KVectorChain boundingBox = layoutInformation.get("graph");
        if (boundingBox == null || boundingBox.size() != 2) {
            // FIXME throw a more specific exception
            throw new RuntimeException("Malformed layout data received from ogdf server.");
        }
        KVector bbLocation = boundingBox.getFirst();
        KVector bbShape = boundingBox.getLast();
        float boundingBoxX = (float) bbLocation.x;
        float boundingBoxY = (float) bbLocation.y;
        float boundingBoxWidth = (float) bbShape.x;
        float boundingBoxHeight = (float) bbShape.y;
        // get the border spacing
        float borderSpacing = parentNodeLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEF_BORDER_SPACING;
        }
        // calculate offsets
        float offsetX = (float) -boundingBoxX + borderSpacing;
        float offsetY = (float) -boundingBoxY + borderSpacing;
        // apply node layout
        for (Map.Entry<KNode, String> entry : node2IdMap.entrySet()) {
            KShapeLayout nodeLayout = entry.getKey().getData(KShapeLayout.class);
            KVectorChain ogdfNodeLayout = layoutInformation.get(entry.getValue());
            if (ogdfNodeLayout != null && ogdfNodeLayout.size() == 2) {
                KVector location = ogdfNodeLayout.getFirst();
                KVector shape = ogdfNodeLayout.getLast();
                toKShape(nodeLayout, (float) location.x + offsetX, (float) location.y + offsetY,
                        (float) shape.x, (float) shape.y);
            }
        }
        // apply edge layout
        for (Map.Entry<String, KEdge> entry : id2EdgeMap.entrySet()) {
            KEdge kedge = entry.getValue();
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            EList<KPoint> kbends = edgeLayout.getBendPoints();
            kbends.clear();
            KVectorChain ogdfEdgeLayout = layoutInformation.get(entry.getKey());
            if (ogdfEdgeLayout != null && ogdfEdgeLayout.size() >= 2) {
                Iterator<KVector> bendIt = ogdfEdgeLayout.iterator();
                KVector sourceBend = bendIt.next();
                // set the source point
                KPoint sourcePoint =
                        toKPoint((float) sourceBend.x, (float) sourceBend.y, offsetX, offsetY);
                edgeLayout.setSourcePoint(sourcePoint);
                // set the bend points
                while (bendIt.hasNext()) {
                    KVector bend = bendIt.next();
                    KPoint point = toKPoint((float) bend.x, (float) bend.y, offsetX, offsetY);
                    if (bendIt.hasNext()) {
                        kbends.add(point);
                    } else {
                        // set the target point
                        edgeLayout.setTargetPoint(point);
                    }
                }
            }
            // set label layout
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
                KVectorChain ogdfLabelLayout =
                        layoutInformation.get(entry.getKey() + OgdfServer.EDGE_LABEL_SUFFIX
                                + labelType);
                if (ogdfLabelLayout != null && ogdfLabelLayout.size() > 0) {
                    KVector labelPos = ogdfLabelLayout.getFirst();
                    toKShape(labelLayout, (float) labelPos.x + offsetX, (float) labelPos.y
                            + offsetY, labelLayout.getWidth(), labelLayout.getHeight());
                    labelLayout.setProperty(LayoutOptions.NO_LAYOUT, false);
                }
            }
        }
        // get the insets
        KInsets insets = parentNodeLayout.getInsets();
        // set the width/height of the graph
        float width = boundingBoxWidth + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        float height = boundingBoxHeight + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(parentNode, width, height, false);
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
     */
    private void writeOgmlGraph(final DocumentRoot root, final OutputStream outputStream,
            final IKielerProgressMonitor progressMonitor, final OgdfServer ogdfServer) {
        progressMonitor.begin("Serialize OGML graph", 1);
        try {
            Resource resource = resourceSet.createResource(URI.createURI("http:///My.ogml"));
            resource.getContents().add(root);
            // write to the stream
            resource.save(outputStream, null);
            outputStream.flush();
        } catch (IOException exception) {
            ogdfServer.endProcess();
            throw new WrappedException(exception, "Failed to serialize the OGML graph.");
        }
        progressMonitor.done();
    }

    /**
     * Writes the specified options and additional information to the output stream.
     * 
     * @param outputStream
     *            the output stream
     * @param progressMonitor
     *            the progress monitor
     */
    private void writeBuffers(final OutputStream outputStream,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Write options", 1);
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
        progressMonitor.done();
    }

    /**
     * An enumeration for keeping track of the current parser state.
     */
    private enum ParseState {
        TYPE, DATA, ERROR
    }

    /**
     * Reads the layout information from the input stream.
     * 
     * @param inputStream
     *            the input stream
     * @param progressMonitor
     *            the progress monitor
     * @param ogdfServer
     *            the OGDF server process interface
     */
    private Map<String, KVectorChain> readLayoutInformation(final InputStream inputStream,
            final IKielerProgressMonitor progressMonitor, final OgdfServer ogdfServer) {
        progressMonitor.begin("Read layout information", 1);
        Map<String, KVectorChain> layoutInformation = new HashMap<String, KVectorChain>();
        String error = "";
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(
                            new NonBlockingInputStream(inputStream)));
            ParseState state = ParseState.TYPE;
            boolean parseMore = true;
            while (parseMore) {
                String line = reader.readLine();
                if (line == null) {
                    ogdfServer.endProcess();
                    // FIXME throw a more specific exception
                    throw new RuntimeException("Failed to read answer from ogdf server process.");
                }
                switch (state) {
                case TYPE:
                    if (line.equals("LAYOUT")) {
                        state = ParseState.DATA;
                    } else if (line.equals("ERROR")) {
                        state = ParseState.ERROR;
                    }
                    break;
                case DATA:
                    if (line.equals("DONE")) {
                        parseMore = false;
                    } else {
                        // try parsing the line
                        try {
                            String[] tokens = line.split("=");
                            if (tokens.length == 2 && tokens[0].length() > 0) {
                                KVectorChain pointList = new KVectorChain();
                                pointList.parse(tokens[1]);
                                layoutInformation.put(tokens[0], pointList);
                            }
                        } catch (IllegalArgumentException e) {
                            // do nothing
                        }
                    }
                    break;
                case ERROR:
                    if (line.equals("DONE")) {
                        throw new RuntimeException(error);
                    } else {
                        if (error.length() > 0) {
                            error += "\n";
                        }
                        error += line;
                    }
                    break;
                }
            }
        } catch (IOException exception) {
            ogdfServer.endProcess();
            throw new RuntimeException(
                    "Failed to read layout information from ogdf server process.", exception);
        } finally {
            progressMonitor.done();
        }
        return layoutInformation;
    }
}
