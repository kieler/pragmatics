/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.libavoid;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.adaptagrams.cola.libavoid.LibavoidServer;
import org.adaptagrams.cola.libavoid.LibavoidServer.Cleanup;
import org.adaptagrams.cola.libavoid.LibavoidServerException;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;

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
 * 
 * 
 * 
 * @author uru
 */
public class LibavoidServerCommunicator {

    private static final boolean DEBUG = false;

    /** the separator used to separate chunks of data sent to the libavoid-server process. */
    private static final String CHUNK_KEYWORD = "[CHUNK]\n";

    // Maps holding the nodes and edges of the current graph.
    private BiMap<Integer, KNode> nodeIdMap = HashBiMap.create();
    private BiMap<Integer, KPort> portIdMap = HashBiMap.create();
    private BiMap<Integer, KEdge> edgeIdMap = HashBiMap.create();

    // Internal data.
    private static final int PORT_ID_START = 5;
    private int nodeIdCounter = 1;
    private int portIdCounter = PORT_ID_START;
    private int edgeIdCounter = 1;
    private static final int SUBTASK_WORK = 1;
    private static final int LAYOUT_WORK = SUBTASK_WORK + SUBTASK_WORK + SUBTASK_WORK
            + SUBTASK_WORK;

    /** String buffer holding the textual graph. */
    private StringBuffer sb = new StringBuffer();

    /**
     * Resets the communicator, i.e., clearing the maps to remember current nodes and the textual
     * representation of the graph.
     */
    private void reset() {
        nodeIdCounter = 1;
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
    public void requestLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor,
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
            // clean up the Libavoid server process
            lvServer.cleanup(Cleanup.NORMAL);

        } catch (IOException exception) {
            lvServer.cleanup(Cleanup.ERROR);
            throw new WrappedException(exception,
                    "Failed to communicate with the Libavoid process.");
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
    private void applyLayout(final KNode parentNode,
            final Map<String, KVectorChain> layoutInformation,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Apply layout", SUBTASK_WORK);

        // Libavoid only routes edges, hence we only have to apply the new edge information
        for (Entry<String, KVectorChain> entry : layoutInformation.entrySet()) {

            // assure we have enough points
            LinkedList<KVector> points = new LinkedList<KVector>(entry.getValue());
            if (points.size() < 2) {
                throw new IllegalStateException(
                        "An edge retrieved from Libavoid has less than 2 points.");
            }

            // get the corresponding edge
            int edgeId = Integer.valueOf(entry.getKey().split(" ")[1]);
            KEdge e = edgeIdMap.get(edgeId);
            if (e == null) {
                throw new IllegalStateException("A problem within the edge mapping occured."
                        + "Could not determine edge for id " + edgeId + ".");
            }
            KEdgeLayout edgeLayout = e.getData(KEdgeLayout.class);
            edgeLayout.getBendPoints().clear();

            // transfer libavoid's results to the edges
            edgeLayout.setSourcePoint(toKPoint(points.pollFirst()));
            while (points.size() > 1) {
                KVector head = points.pollFirst();
                edgeLayout.getBendPoints().add(toKPoint(head));
            }
            edgeLayout.setTargetPoint(toKPoint(points.pollFirst()));

        }

        progressMonitor.done();
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
            final IKielerProgressMonitor progressMonitor) {
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
    private void writeTextGraph(final KNode root, final OutputStream stream) {

        // first send the options
        transformOptions(root);

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
            throw new WrappedException(e,
                    "Could not write to the outputstream of the libavoid server.");
        }
    }

    private void transformOptions(final KNode node) {

        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        
        /*
         * General Properties 
         */
        Direction direction = nodeLayout.getProperty(LayoutOptions.DIRECTION);
        addOption(LayoutOptions.DIRECTION.getId(), direction);
        
        EdgeRouting edgeRouting = nodeLayout.getProperty(LayoutOptions.EDGE_ROUTING);
        addOption(LayoutOptions.EDGE_ROUTING.getId(), edgeRouting);

        /*
         * Penalties
         */
        float segmentPenalty = nodeLayout.getProperty(LibavoidRouterSetup.SEGMENT_PENALTY);
        addPenalty(LibavoidRouterSetup.SEGMENT_PENALTY.getId(), segmentPenalty);

        float anglePenalty = nodeLayout.getProperty(LibavoidRouterSetup.ANGLE_PENALTY);
        addPenalty(LibavoidRouterSetup.ANGLE_PENALTY.getId(), anglePenalty);

        float crossingPenalty = nodeLayout.getProperty(LibavoidRouterSetup.CROSSING_PENALTY);
        addPenalty(LibavoidRouterSetup.CROSSING_PENALTY.getId(), crossingPenalty);

        float clusterCrossingPenalty =
                nodeLayout.getProperty(LibavoidRouterSetup.CLUSTER_CROSSING_PENALTY);
        addPenalty(LibavoidRouterSetup.CLUSTER_CROSSING_PENALTY.getId(), clusterCrossingPenalty);

        float fixedSharedPathPenalty =
                nodeLayout.getProperty(LibavoidRouterSetup.FIXED_SHARED_PATH_PENALTY);
        addPenalty(LibavoidRouterSetup.FIXED_SHARED_PATH_PENALTY.getId(), fixedSharedPathPenalty);

        float portDirectionPenalty =
                nodeLayout.getProperty(LibavoidRouterSetup.PORT_DIRECTION_PENALTY);
        addPenalty(LibavoidRouterSetup.PORT_DIRECTION_PENALTY.getId(), portDirectionPenalty);

        float shapeBufferDistance =
                nodeLayout.getProperty(LibavoidRouterSetup.SHAPE_BUFFER_DISTANCE);
        addPenalty(LibavoidRouterSetup.SHAPE_BUFFER_DISTANCE.getId(), shapeBufferDistance);

        float idealNudgingDistance =
                nodeLayout.getProperty(LibavoidRouterSetup.IDEAL_NUDGING_DISTANCE);
        addPenalty(LibavoidRouterSetup.IDEAL_NUDGING_DISTANCE.getId(), idealNudgingDistance);

        /*
         * Routing options
         */
        boolean nudgeOrthogonalSegmentsConnectedToShapes =
                nodeLayout.getProperty(LibavoidRouterSetup.NUDGE_ORTHOGONAL_SEGMENTS);
        addRoutingOption(LibavoidRouterSetup.NUDGE_ORTHOGONAL_SEGMENTS.getId(),
                nudgeOrthogonalSegmentsConnectedToShapes);

        boolean improveHyperedgeRoutesMovingJunctions =
                nodeLayout.getProperty(LibavoidRouterSetup.IMPROVE_HYPEREDGES);
        addRoutingOption(LibavoidRouterSetup.IMPROVE_HYPEREDGES.getId(),
                improveHyperedgeRoutesMovingJunctions);

        boolean penaliseOrthogonalSharedPathsAtConnEnds =
                nodeLayout.getProperty(LibavoidRouterSetup.PENALISE_ORTH_SHATE_PATHS);
        addRoutingOption(LibavoidRouterSetup.PENALISE_ORTH_SHATE_PATHS.getId(),
                penaliseOrthogonalSharedPathsAtConnEnds);

        boolean nudgeOrthogonalTouchingColinearSegments =
                nodeLayout.getProperty(LibavoidRouterSetup.NUDGE_ORTHOGONAL_COLINEAR_SEGMENTS);
        addRoutingOption(LibavoidRouterSetup.NUDGE_ORTHOGONAL_COLINEAR_SEGMENTS.getId(),
                nudgeOrthogonalTouchingColinearSegments);

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

    private void transformGraph(final KNode root) {

        sb.append("GRAPH");
        sb.append("\n");

        // nodes
        for (KNode node : root.getChildren()) {
            transformNode(node);
        }

        // edges
        for (KNode node : root.getChildren()) {
            for (KEdge edge : node.getOutgoingEdges()) {
                transformEdge(edge);
            }
        }

        sb.append("GRAPHEND");
        sb.append("\n");
    }

    private void transformNode(final KNode node) {
        // assign an id
        nodeIdMap.put(nodeIdCounter, node);
        
        // get information about port-less incoming and outgoing edges
        int portLessIncomingEdges =
                Iterables.size(Iterables.filter(node.getIncomingEdges(), new Predicate<KEdge>() {
                    public boolean apply(final KEdge edge) {
                        return edge.getTargetPort() == null;
                    }
                }));
        int portLessOutgoingEdges =
                Iterables.size(Iterables.filter(node.getOutgoingEdges(), new Predicate<KEdge>() {
                    public boolean apply(final KEdge edge) {
                        return edge.getSourcePort() == null;
                    }
                }));

        // convert the bounds
        KShapeLayout shape = node.getData(KShapeLayout.class);
        // format:
        // id topleft bottomright portLessIncomingEdges portLessOutgoingEdges
        sb.append("NODE " + nodeIdCounter + " " + shape.getXpos() + " " + shape.getYpos() + " "
                + (shape.getXpos() + shape.getWidth()) + " "
                + (shape.getYpos() + shape.getHeight()) + " " + portLessIncomingEdges + " "
                + portLessOutgoingEdges);
        sb.append("\n");

        // transfer port constraints
        PortConstraints pc = shape.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        sb.append("NODEOPTION " + nodeIdCounter + " " + pc);
        sb.append("\n");

        // transfer all ports
        for (KPort port : node.getPorts()) {
            portIdMap.put(portIdCounter, port);

            // gather information
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            PortSide side = portLayout.getProperty(LayoutOptions.PORT_SIDE);

            // get center point of port
            float centerX = portLayout.getXpos() + portLayout.getWidth() / 2;
            float centerY = portLayout.getYpos() + portLayout.getHeight() / 2;

            // format: portId nodeId portSide centerX centerYs
            sb.append("PORT " + portIdCounter + " " + nodeIdCounter + " " + side.toString() + " "
                    + centerX + " " + centerY);
            sb.append("\n");

            portIdCounter++;
        }

        nodeIdCounter++;
    }

    private void transformEdge(final KEdge edge) {
        // assign an id
        edgeIdMap.put(edgeIdCounter, edge);

        // convert the edge
        int srcId = nodeIdMap.inverse().get(edge.getSource());
        int tgtId = nodeIdMap.inverse().get(edge.getTarget());

        Integer srcPort = portIdMap.inverse().get(edge.getSourcePort());
        Integer tgtPort = portIdMap.inverse().get(edge.getTargetPort());

        // determine the type of the edge, ie, if it involves ports
        String edgeType = "EDGE";
        if (srcPort != null && tgtPort != null) {
            edgeType = "PEDGEP";
        } else if (srcPort != null) {
            edgeType = "PEDGE";
        } else if (tgtPort != null) {
            edgeType = "EDGEP";
        }

        // format: edgeId srcId tgtId srcPort tgtPort
        sb.append(edgeType + " " + edgeIdCounter + " " + srcId + " " + tgtId + " " + srcPort + " "
                + tgtPort);
        sb.append("\n");

        edgeIdCounter++;
    }

    /*
     * Convenient methods.
     */

    private KPoint toKPoint(final KVector v) {
        KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        kpoint.setPos((float) v.x, (float) v.y);
        return kpoint;
    }

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
}
