/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.libavoid;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.adaptagrams.libavoid.LibavoidServer;
import org.adaptagrams.libavoid.LibavoidServer.Cleanup;
import org.adaptagrams.libavoid.LibavoidServerException;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.libavoid.options.LibavoidOptions;

/**
 * Performs the actual communication with the libabvoid-server. The graph to layout is send to the
 * server using a textual format. The server then sends back the layouted information.
 * 
 * Protocol: 
 *    - All nodes are passed together with a continuously increasing id starting by 1. 
 *      (1 2 3 4 ...) 
 *    - The same goes for the edges.
 *    - Port's ids start at 5, leaving the ids [1,..,4] as special cases for internal 
 *      handling of libavoid  
 *    - The edge routing option has to be passed first!
 *      The information is required to initialize the libavoid router properly 
 *      before the router can be configured with additional options.
 * 
 * @author uru
 */
public class LibavoidServerCommunicator {

    private static final boolean DEBUG = false;

    /** the separator used to separate chunks of data sent to the libavoid-server process. */
    private static final String CHUNK_KEYWORD = "[CHUNK]\n";

    // Maps holding the nodes and edges of the current graph.
    private BiMap<Integer, ElkNode> nodeIdMap = HashBiMap.create();
    private BiMap<Integer, ElkPort> portIdMap = HashBiMap.create();
    private BiMap<Integer, ElkEdge> edgeIdMap = HashBiMap.create();

    // Internal data.
    private static final int PORT_ID_START = 5;
    private static final int NODE_ID_START = 5;
    // reserved for compound node's boundaries
    private static final int NODE_COMPOUND_NORTH = 1;
    private static final int NODE_COMPOUND_EAST = 2;
    private static final int NODE_COMPOUND_SOUTH = 3;
    private static final int NODE_COMPOUND_WEST = 4;
    /** size, either width or height, of the surrounding rectangles of compound nodes. */
    private static final int SURROUNDING_RECT_SIZE = 10;

    private int nodeIdCounter = NODE_ID_START;
    private int portIdCounter = PORT_ID_START;
    private int edgeIdCounter = 1;
    private static final int SUBTASK_WORK = 1;
    private static final int LAYOUT_WORK = SUBTASK_WORK + SUBTASK_WORK + SUBTASK_WORK
            + SUBTASK_WORK;

    /** String buffer holding the textual graph. */
    private StringBuffer sb = new StringBuffer();

    /** The direction of the current drawing. */
    private Direction currentDirection = Direction.UNDEFINED;

    /**
     * Resets the communicator, i.e., clearing the maps to remember current nodes and the textual
     * representation of the graph.
     */
    private void reset() {
        nodeIdCounter = NODE_ID_START;
        nodeIdMap.clear();
        portIdCounter = PORT_ID_START;
        portIdMap.clear();
        edgeIdCounter = 1;
        edgeIdMap.clear();
        sb = new StringBuffer();
    }

    /**
     * Requests a layout from the libavoid server.
     * 
     * @param layoutNode
     *            the root node of the graph to layout.
     * @param progressMonitor
     *            the monitor
     * @param lvServer
     *            an instance of the libavoid server.
     */
    public void requestLayout(final ElkNode layoutNode, final IElkProgressMonitor progressMonitor,
            final LibavoidServer lvServer) {
        progressMonitor.begin("Libavoid Layout", LAYOUT_WORK);
        // if the graph is empty there is no need to layout
        if (layoutNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }

        // start the libavoid server process, or retrieve the previously used process
        lvServer.initialize();

        try {
            // retrieve the libavoid server input
            OutputStream outputStream = lvServer.input();
            // write the graph to the process
            writeTextGraph(layoutNode, outputStream);
            // flush the stream
            outputStream.flush();

            // read the layout information
            Map<String, KVectorChain> layoutInformation =
                    readLayoutInformation(lvServer, progressMonitor.subTask(1));

            // apply the layout back to the KGraph
            applyLayout(layoutNode, layoutInformation, progressMonitor.subTask(1));
            // calculate junction points
            calculateJunctionPoints(layoutNode);
            // clean up the Libavoid server process
            lvServer.cleanup(Cleanup.NORMAL);

        } catch (IOException exception) {
            lvServer.cleanup(Cleanup.ERROR);
            throw new WrappedException("Failed to communicate with the Libavoid process.", exception);
        } finally {
            progressMonitor.done();
            reset();
        }
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
        progressMonitor.begin("Apply layout", SUBTASK_WORK);

        // Libavoid only routes edges, hence we only have to apply the new edge information
        for (Entry<String, KVectorChain> entry : layoutInformation.entrySet()) {

            // assure we have enough points
            KVectorChain points = entry.getValue();
            if (points.size() < 2) {
                throw new IllegalStateException(
                        "An edge retrieved from Libavoid has less than 2 points.");
            }

            // get the corresponding edge
            int edgeId = Integer.valueOf(entry.getKey().split(" ")[1]);
            ElkEdge e = edgeIdMap.get(edgeId);
            if (e == null) {
                throw new IllegalStateException("A problem within the edge mapping occured."
                        + "Could not determine edge for id " + edgeId + ".");
            }
            
            // clean bend points
            ElkEdgeSection edgeSection = ElkGraphUtil.firstEdgeSection(e, true, true);
            ElkUtil.applyVectorChain(points, edgeSection);
        }

        progressMonitor.done();
    }
    
    /**
     * Calculates and sets the junction points for each edge of the graph.
     * 
     * @param graph
     *            the graph.
     */
    private void calculateJunctionPoints(final ElkNode graph) {
        for (ElkNode n : graph.getChildren()) {
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(n)) {
                KVectorChain junctionPoints = ElkUtil.determineJunctionPoints(edge);
                edge.setProperty(CoreOptions.JUNCTION_POINTS, junctionPoints);
            }
        }
    }

    /**
     * Read layout information from the Libavoid server process.
     * 
     * @param libavoidServer
     *            the Libavoid server process interface
     * @param progressMonitor
     *            the progress monitor
     * @return a map of layout information
     */
    private Map<String, KVectorChain> readLayoutInformation(final LibavoidServer libavoidServer,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Read output from Libavoid", 1);
        Map<String, String> outputData = libavoidServer.readOutputData();
        if (outputData == null) {
            libavoidServer.cleanup(Cleanup.ERROR);
            throw new LibavoidServerException("No output from the Libavoid process."
                    + " Try increasing the timeout value in the preferences"
                    + " (KIELER / Layout / Libavoid).");
        }
        Map<String, KVectorChain> layoutInformation =
                Maps.newHashMapWithExpectedSize(outputData.size());
        for (Map.Entry<String, String> entry : outputData.entrySet()) {
            KVectorChain pointList = new KVectorChain();
            StringTokenizer tokenizer = new StringTokenizer(entry.getValue(), " ");
            // now the coordinates
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
     * Transforms the passed graph to a textual format and writes it to the specified output stream.
     */
    private void writeTextGraph(final ElkNode root, final OutputStream stream) {

        // first send the options
        transformOptions(root);

        if (root.getProperty(CoreOptions.DEBUG_MODE)) {
            sb.append("DEBUG\n");
        }
        
        // transform the graph to a text format
        transformGraph(root);

        // finish with the chunk keyword
        sb.append(CHUNK_KEYWORD);

        if (DEBUG) {
            System.out.println(sb);
        }

        try {
            // write it to the stream
            stream.write(sb.toString().getBytes());
        } catch (IOException e) {
            throw new WrappedException("Could not write to the outputstream of the libavoid server.", e);
        }
    }

    private void transformOptions(final ElkNode node) {

        /*
         * General Properties
         */
        // IMPORTANT: the edge routing option has to be passed first!
        // The information is required to initialize the libavoid router properly
        // before the router can be configured with additional options
        EdgeRouting edgeRouting = node.getProperty(LibavoidOptions.EDGE_ROUTING);
        addOption(LibavoidOptions.EDGE_ROUTING.getId(), edgeRouting);

        Direction direction = node.getProperty(LibavoidOptions.DIRECTION);
        currentDirection = direction;
        addOption(LibavoidOptions.DIRECTION.getId(), direction);

        /*
         * Penalties
         */
        double segmentPenalty = node.getProperty(LibavoidOptions.SEGMENT_PENALTY);
        addPenalty(LibavoidOptions.SEGMENT_PENALTY.getId(), segmentPenalty);

        double anglePenalty = node.getProperty(LibavoidOptions.ANGLE_PENALTY);
        addPenalty(LibavoidOptions.ANGLE_PENALTY.getId(), anglePenalty);

        double crossingPenalty = node.getProperty(LibavoidOptions.CROSSING_PENALTY);
        addPenalty(LibavoidOptions.CROSSING_PENALTY.getId(), crossingPenalty);

        double clusterCrossingPenalty =
                node.getProperty(LibavoidOptions.CLUSTER_CROSSING_PENALTY);
        addPenalty(LibavoidOptions.CLUSTER_CROSSING_PENALTY.getId(), clusterCrossingPenalty);

        double fixedSharedPathPenalty =
                node.getProperty(LibavoidOptions.FIXED_SHARED_PATH_PENALTY);
        addPenalty(LibavoidOptions.FIXED_SHARED_PATH_PENALTY.getId(), fixedSharedPathPenalty);

        double portDirectionPenalty =
                node.getProperty(LibavoidOptions.PORT_DIRECTION_PENALTY);
        addPenalty(LibavoidOptions.PORT_DIRECTION_PENALTY.getId(), portDirectionPenalty);

        double shapeBufferDistance =
                node.getProperty(LibavoidOptions.SHAPE_BUFFER_DISTANCE);
        addPenalty(LibavoidOptions.SHAPE_BUFFER_DISTANCE.getId(), shapeBufferDistance);

        double idealNudgingDistance =
                node.getProperty(LibavoidOptions.IDEAL_NUDGING_DISTANCE);
        addPenalty(LibavoidOptions.IDEAL_NUDGING_DISTANCE.getId(), idealNudgingDistance);

        /*
         * Routing options
         */
        boolean nudgeOrthogonalSegmentsConnectedToShapes =
                node.getProperty(LibavoidOptions.NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES);
        addRoutingOption(LibavoidOptions.NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES.getId(),
                nudgeOrthogonalSegmentsConnectedToShapes);

        boolean improveHyperedgeRoutesMovingJunctions =
                node.getProperty(LibavoidOptions.IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS);
        addRoutingOption(LibavoidOptions.IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS.getId(),
                improveHyperedgeRoutesMovingJunctions);

        boolean penaliseOrthogonalSharedPathsAtConnEnds =
                node.getProperty(LibavoidOptions.PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS);
        addRoutingOption(LibavoidOptions.PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS.getId(),
                penaliseOrthogonalSharedPathsAtConnEnds);

        boolean nudgeOrthogonalTouchingColinearSegments =
                node.getProperty(LibavoidOptions.NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS);
        addRoutingOption(LibavoidOptions.NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS.getId(),
                nudgeOrthogonalTouchingColinearSegments);

        boolean performUnifyingNudgingPreprocessingStep =
                node.getProperty(LibavoidOptions.PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP);
        addRoutingOption(LibavoidOptions.PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP.getId(),
                performUnifyingNudgingPreprocessingStep);

        boolean improveHyperedgeRoutesMovingAddingAndDeletingJunctions = node.getProperty(
                LibavoidOptions.IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS);
        addRoutingOption(
                LibavoidOptions.IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS
                        .getId(),
                improveHyperedgeRoutesMovingAddingAndDeletingJunctions);

    }

    private void addOption(final String key, final Object value) {
        sb.append("OPTION " + key + " " + value.toString());
        sb.append("\n");
    }

    private void addRoutingOption(final String key, final Object value) {
        sb.append("ROUTINGOPTION " + key + " " + value.toString());
        sb.append("\n");
    }

    private void addPenalty(final String key, final Object value) {
        sb.append("PENALTY " + key + " " + value.toString());
        sb.append("\n");
    }

    /**
     * Transform the actual graph.
     * 
     * @param root
     *            of the current graph.
     */
    private void transformGraph(final ElkNode root) {

        sb.append("GRAPH");
        sb.append("\n");

        // add boundaries if this node is a compound node
        if (root.getParent() != null) {
            transformHierarchicalParent(root);
        } else {
            // create 4 dummy nodes, as the libavoid process expects gap-less node
            // ids starting from 1.
            transformHierarchicalParentDummy(root);
        }

        // nodes
        for (ElkNode node : root.getChildren()) {
            transformNode(node);
        }

        // edges
        for (ElkNode node : root.getChildren()) {
            // all edges between nodes within the root node
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (!edge.isHierarchical()) {
                    transformEdge(edge);
                }
            }
        }
        
        // AND, in case of an compound node,
        // all edges between hierarchical ports and nodes within the root node
        for (ElkPort p : root.getPorts()) {
            for (ElkEdge e : ElkGraphUtil.allIncidentEdges(p)) {
                ElkNode src = ElkGraphUtil.connectableShapeToNode(e.getSources().get(0));
                ElkNode tgt = ElkGraphUtil.connectableShapeToNode(e.getTargets().get(0));
                if ((src.getParent().equals(root) || tgt.getParent().equals(root))) {
                    transformEdge(e);
                }
            }
        }

        sb.append("GRAPHEND");
        sb.append("\n");
    }

    /**
     * Create 4 nodes that "surround", hence restrict, the child area. This way it is guaranteed
     * that no edge is routed outsite its compound node.
     */
    private void transformHierarchicalParent(final ElkNode parent) {

        // offset each side by the parent buffer distance to let edges route properly
        double bufferDistance = parent.getProperty(LibavoidOptions.SHAPE_BUFFER_DISTANCE);
        // top
        libavoidNode(parent, NODE_COMPOUND_NORTH, 0, 0 - SURROUNDING_RECT_SIZE - bufferDistance,
                parent.getWidth(), SURROUNDING_RECT_SIZE, 0, 0);
        // right
        libavoidNode(parent, NODE_COMPOUND_EAST, 0 + parent.getWidth() + bufferDistance, 0,
                SURROUNDING_RECT_SIZE, parent.getHeight(), 0, 0);
        // bottom
        libavoidNode(parent, NODE_COMPOUND_SOUTH, 0, 0 + parent.getHeight() + bufferDistance,
                parent.getWidth(), SURROUNDING_RECT_SIZE, 0, 0);
        // left
        libavoidNode(parent, NODE_COMPOUND_WEST, 0 - bufferDistance - SURROUNDING_RECT_SIZE, 0,
                SURROUNDING_RECT_SIZE, parent.getHeight(), 0, 0);

        // convert the ports of the compound node itself
        for (ElkPort port : parent.getPorts()) {
            int nodeId = determineHierarchicalNodeId(port);
            libavoidPort(port, portIdCounter, nodeId, parent);
            portIdCounter++;
        }
    }

    private void transformHierarchicalParentDummy(final ElkNode root) {
        // 4 dummies
        libavoidNode(root, NODE_COMPOUND_NORTH, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_EAST, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_SOUTH, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_WEST, 0, 0, 0, 0, 0, 0);
    }

    // SUPPRESS CHECKSTYLE NEXT 1 ParameterNumber 
    private void libavoidNode(final ElkNode node, final int id, 
            final double xPos, final double yPos,
            final double width, final double height, 
            final int portLessIncomingEdges, final int portLessOutgoingEdges) {

        // put to map
        if (id >= NODE_ID_START) {
            nodeIdMap.put(id, node);
        }

        // format:
        // id topleft bottomright portLessIncomingEdges portLessOutgoingEdges
        sb.append("NODE " + id + " " + xPos + " " + yPos + " " + (xPos + width) + " "
                + (yPos + height) + " " + portLessIncomingEdges + " " + portLessOutgoingEdges);
        sb.append("\n");
    }

    private void libavoidPort(final ElkPort port, final int portId, final int nodeId,
            final ElkNode compoundNode) {

        // put to map
        portIdMap.put(portId, port);

        // gather information
        PortSide side = ElkUtil.calcPortSide(port, currentDirection);

        // get center point of port
        double centerX = port.getX() + port.getWidth() / 2;
        double centerY = port.getY() + port.getHeight() / 2;
        
        // for compound nodes we have to mirror the port sides
        if (compoundNode != null) {
            side = side.opposed();
        }

        // format: portId nodeId portSide centerX centerYs
        sb.append("PORT " + portId + " " + nodeId + " " + side.toString() + " " + centerX + " "
                + centerY);
        sb.append("\n");

    }

    private void transformNode(final ElkNode node) {
        // get information about port-less incoming and outgoing edges
        int portLessIncomingEdges = node.getIncomingEdges().size();
        int portLessOutgoingEdges = node.getOutgoingEdges().size();

        // convert the bounds
        libavoidNode(node, nodeIdCounter, 
                node.getX(), node.getY(), node.getWidth(), node.getHeight(), 
                portLessIncomingEdges, portLessOutgoingEdges);

        // transfer port constraints
        PortConstraints pc = node.getProperty(LibavoidOptions.PORT_CONSTRAINTS);
        sb.append("NODEOPTION " + nodeIdCounter + " " + pc);
        sb.append("\n");

        // transfer all ports
        for (ElkPort port : node.getPorts()) {
            libavoidPort(port, portIdCounter, nodeIdCounter, null);
            portIdCounter++;
        }

        nodeIdCounter++;
    }

    private void transformEdge(final ElkEdge edge) {
        // assign an id
        edgeIdMap.put(edgeIdCounter, edge);

        ElkNode srcNode = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
        ElkNode tgtNode = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
        ElkPort srcPort = ElkGraphUtil.connectableShapeToPort(edge.getSources().get(0));
        ElkPort tgtPort = ElkGraphUtil.connectableShapeToPort(edge.getTargets().get(0));
        
        // convert the edge
        Integer srcId = nodeIdMap.inverse().get(srcNode);
        Integer tgtId = nodeIdMap.inverse().get(tgtNode);

        Integer srcPortId = portIdMap.inverse().get(srcPort);
        Integer tgtPortId = portIdMap.inverse().get(tgtPort);

        // hierarchical port's libavoid nodes are not stored in the mapping
        if (srcPortId != null && srcId == null) {
            srcId = determineHierarchicalNodeId(srcPort);
        }
        if (tgtPortId != null && tgtId == null) {
            tgtId = determineHierarchicalNodeId(tgtPort);
        }

        // determine the type of the edge, ie, if it involves ports
        String edgeType = "EDGE";
        if (srcPortId != null && tgtPortId != null) {
            edgeType = "PEDGEP";
        } else if (srcPortId != null) {
            edgeType = "PEDGE";
        } else if (tgtPortId != null) {
            edgeType = "EDGEP";
        }

        // format: edgeId srcId tgtId srcPort tgtPort
        sb.append(edgeType + " " + edgeIdCounter + " " + srcId + " " + tgtId + " " + srcPortId + " "
                + tgtPortId);
        sb.append("\n");

        edgeIdCounter++;
    }

    /*
     * Convenient methods.
     */

    /**
     * Parse a double value ignoring illegal string values.
     * 
     * @param string
     *            a string value
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

    private int determineHierarchicalNodeId(final ElkPort port) {
        PortSide ps = ElkUtil.calcPortSide(port, currentDirection);
        int nodeId = 0;
        switch (ps) {
        case NORTH:
            nodeId = NODE_COMPOUND_NORTH;
            break;
        case EAST:
            nodeId = NODE_COMPOUND_EAST;
            break;
        case SOUTH:
            nodeId = NODE_COMPOUND_SOUTH;
            break;
        default: // WEST
            nodeId = NODE_COMPOUND_WEST;
            break;
        }
        return nodeId;
    }
}
