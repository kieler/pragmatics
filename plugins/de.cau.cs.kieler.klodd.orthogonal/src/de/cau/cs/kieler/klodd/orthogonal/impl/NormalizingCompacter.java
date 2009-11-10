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
package de.cau.cs.kieler.klodd.orthogonal.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimFace;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.orthogonal.impl.DualGraphBuilder.ExternalFaceDetector;
import de.cau.cs.kieler.klodd.orthogonal.modules.ICompacter;
import de.cau.cs.kieler.klodd.orthogonal.structures.TSMEdge;
import de.cau.cs.kieler.klodd.orthogonal.structures.TSMNode;

/**
 * Compacter implementation that normalizes the graph and executes another
 * compacter for normalized orthogonal representations.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class NormalizingCompacter extends AbstractAlgorithm implements ICompacter {

    /** the embedded compacter for normalized orthogonal representations. */
    private ICompacter normalizedCompacter;
    /** mapping of nodes or edge bends to their first normalization node. */
    private Map<Object, TSMNode> startNodeMap = new HashMap<Object, TSMNode>();
    /** mapping of nodes to their last normalization node (lower right corner). */
    private Map<Object, TSMNode> endNodeMap = new HashMap<Object, TSMNode>();
    /** minimal distance between elements. */
    private float minDist;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        startNodeMap.clear();
        endNodeMap.clear();
    }

    /**
     * Creates a normalizing compacter based on the given compacter, which
     * should work on a normalized orthogonal representation.
     * 
     * @param thenormalizedCompacter compacter that expects a normalized orthogonal
     *            representation
     */
    public NormalizingCompacter(final ICompacter thenormalizedCompacter) {
        this.normalizedCompacter = thenormalizedCompacter;
    }

    /**
     * {@inheritDoc}
     */
    public void compact(final KSlimGraph graph, final float theminDist) {
        getMonitor().begin("Normalizing compaction", 1);

        this.minDist = theminDist;
        // create a normalized version of the input graph
        KSlimGraph normalizedGraph = createNormalizedGraph(graph);
        // build dual graph for the normalized graph
        buildDualGraph(normalizedGraph);
        // execute the embedded compacter
        normalizedCompacter.reset(getMonitor().subTask(1));
        normalizedCompacter.compact(normalizedGraph, theminDist);
        // transform abstract metrics to concrete metrics
        transformMetrics(graph, (int) normalizedGraph.getWidth(), (int) normalizedGraph.getHeight());

        getMonitor().done();
    }

    /**
     * Descriptor class for ports.
     */
    private static class PortDescriptor {
        /** list of dummy nodes that were created for the related port. */
        List<TSMNode> nodes = new LinkedList<TSMNode>();
        /** number of edges going left. */
        int leftEdges = 0;
        /** number of edges without bends. */
        int straightEdges = 0;
        /** number of edges going right. */
        int rightEdges = 0;
    }

    /**
     * Creates a normalized version of the input graph.
     * 
     * @param inputGraph graph with orthogonal representation
     * @return new graph with normalized orthogonal representation
     */
    private KSlimGraph createNormalizedGraph(final KSlimGraph inputGraph) {
        KSlimGraph normalizedGraph = new KSlimGraph();
        Map<Object, PortDescriptor> portMap = new HashMap<Object, PortDescriptor>();
        int[] sourceRank = new int[inputGraph.getEdges().size()];
        int[] targetRank = new int[inputGraph.getEdges().size()];

        // process the nodes of the graph
        for (KSlimNode node : inputGraph.getNodes()) {
            TSMNode tsmNode = (TSMNode) node;
            if (tsmNode.type == TSMNode.Type.LAYOUT) {
                KSlimNode.Side currentSide = KSlimNode.Side.UNDEFINED;
                TSMNode currentNode = null;
                KSlimNode.Side startingSide = KSlimNode.Side.UNDEFINED;
                boolean changedSide = false;
                KSlimNode startingNode = null;
                int edgeRank = 0;
                for (KSlimNode.IncEntry edgeEntry : node.getIncidence()) {
                    KEdge layoutEdge = (KEdge) edgeEntry.getEdge().getObject();
                    KPort port = (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT ? layoutEdge
                            .getSourcePort() : layoutEdge.getTargetPort());
                    PortDescriptor portDescriptor = portMap.get(port);

                    // create dummy nodes for ports and corners
                    if (portDescriptor == null) {
                        edgeRank = 0;
                        KSlimNode.Side newSide = edgeEntry.side();
                        TSMNode newNode = new TSMNode(normalizedGraph, TSMNode.Type.NORM_PORT, port);
                        if (startingSide == KSlimNode.Side.UNDEFINED) {
                            startingSide = newSide;
                            startingNode = newNode;
                        } else {
                            if (currentSide != newSide) {
                                currentNode = addCornerNodes(normalizedGraph, currentNode,
                                        (TSMNode) node, currentSide, newSide);
                                changedSide = true;
                            }
                            KSlimEdge newEdge = new TSMEdge(normalizedGraph, currentNode, newNode);
                            newEdge.connectNodes(newSide.right(), newSide.left());
                        }
                        portDescriptor = new PortDescriptor();
                        portDescriptor.nodes.add(newNode);
                        portMap.put(port, portDescriptor);
                        currentNode = newNode;
                        currentSide = newSide;
                    }

                    // count the number of edges going left and right
                    List<KSlimEdge.Bend> bends = edgeEntry.getEdge().getBends();
                    if (bends.isEmpty()) {
                        portDescriptor.straightEdges++;
                    } else {
                        if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT
                                && bends.get(0).getType() == KSlimEdge.Bend.Type.LEFT
                                || edgeEntry.getType() == KSlimNode.IncEntry.Type.IN
                                && bends.get(bends.size() - 1).getType() == KSlimEdge.Bend.Type.RIGHT) {
                            portDescriptor.leftEdges++;
                        } else {
                            portDescriptor.rightEdges++;
                        }
                    }

                    // determine the port rank of the current edge
                    if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT) {
                        sourceRank[edgeEntry.getEdge().getId()] = edgeRank;
                    } else {
                        targetRank[edgeEntry.getEdge().getId()] = edgeRank;
                    }
                    edgeRank++;
                }

                // add remaining corners
                if (currentSide == KSlimNode.Side.UNDEFINED) {
                    currentSide = KSlimNode.Side.NORTH;
                    startingSide = currentSide;
                }
                if (startingSide != currentSide || !changedSide) {
                    currentNode = addCornerNodes(normalizedGraph, currentNode, (TSMNode) node,
                            currentSide, startingSide);
                }
                if (startingNode != null) {
                    KSlimEdge newEdge = new TSMEdge(normalizedGraph, currentNode, startingNode);
                    newEdge.connectNodes(startingSide.right(), startingSide.left());
                }
            } else if (tsmNode.type == TSMNode.Type.CROSSING) {
                // handle dummy nodes of edge crossings
                TSMNode dummyNode = new TSMNode(normalizedGraph, TSMNode.Type.CROSSING, node);
                PortDescriptor portDescriptor = new PortDescriptor();
                portDescriptor.nodes.add(dummyNode);
                portMap.put(node, portDescriptor);
            } else {
                assert false;
            }
        }

        // process the edges of the graph
        for (KSlimEdge edge : inputGraph.getEdges()) {
            TSMEdge tsmEdge = (TSMEdge) edge;
            KEdge layoutEdge = (KEdge) tsmEdge.getObject();
            // determine attachment point at source
            TSMNode currentNode = null, targetNode = null;
            boolean sourceBendConsumed = false, targetBendConsumed = false;
            if (((TSMNode) edge.getSource()).type == TSMNode.Type.LAYOUT) {
                PortDescriptor portDescriptor = portMap.get(layoutEdge.getSourcePort());
                Pair<TSMNode, Boolean> endpointResult = getEndpointNode(normalizedGraph, edge,
                        portDescriptor, sourceRank[edge.getId()], true);
                currentNode = (TSMNode) endpointResult.getFirst();
                sourceBendConsumed = endpointResult.getSecond();
            } else if (((TSMNode) edge.getSource()).type == TSMNode.Type.CROSSING) {
                PortDescriptor portDescriptor = portMap.get(edge.getSource());
                currentNode = (TSMNode) portDescriptor.nodes.get(0);
            }
            // register the new node
            if (sourceBendConsumed) {
                startNodeMap.put(edge.getBends().get(0), currentNode);
            }

            // determine attachment point at target
            if (((TSMNode) edge.getTarget()).type == TSMNode.Type.LAYOUT) {
                PortDescriptor portDescriptor = portMap.get(layoutEdge.getTargetPort());
                Pair<TSMNode, Boolean> endpointResult = getEndpointNode(normalizedGraph, edge,
                        portDescriptor, targetRank[edge.getId()], false);
                targetNode = endpointResult.getFirst();
                targetBendConsumed = endpointResult.getSecond();
            } else if (((TSMNode) edge.getTarget()).type == TSMNode.Type.CROSSING) {
                PortDescriptor portDescriptor = portMap.get(edge.getTarget());
                targetNode = portDescriptor.nodes.get(0);
            }
            // register the new node
            if (targetBendConsumed) {
                startNodeMap.put(edge.getBends().get(edge.getBends().size() - 1), targetNode);
            }

            // create dummy nodes for remaining bends
            KSlimNode.Side currentSide = (sourceBendConsumed
                    ? (edge.getBends().get(0).getType() == KSlimEdge.Bend.Type.LEFT
                            ? edge.getSourceSide().left() : edge.getSourceSide().right())
                    : edge.getSourceSide());
            ListIterator<KSlimEdge.Bend> bendIter = edge.getBends().listIterator();
            if (sourceBendConsumed) {
                bendIter.next();
            }
            int targetIndex = targetBendConsumed ? edge.getBends().size() - 1 : edge.getBends().size();
            while (bendIter.nextIndex() < targetIndex) {
                // add the new node for normalization
                KSlimEdge.Bend bend = bendIter.next();
                TSMNode newNode = new TSMNode(normalizedGraph, TSMNode.Type.BEND, edge);
                KSlimEdge newEdge = new TSMEdge(normalizedGraph, currentNode, newNode, layoutEdge);
                newEdge.connectNodes(currentSide, currentSide.opposed());

                // register the new node
                startNodeMap.put(bend, newNode);

                currentNode = newNode;
                currentSide = (bend.getType() == KSlimEdge.Bend.Type.LEFT ? currentSide.left() : currentSide
                        .right());
            }
            KSlimEdge newEdge = new TSMEdge(normalizedGraph, currentNode, targetNode, layoutEdge);
            newEdge.connectNodes(currentSide, currentSide.opposed());
        }

        return normalizedGraph;
    }

    /**
     * Adds dummy nodes for all corners between the start side and the end side.
     * 
     * @param graph graph to which new nodes are added
     * @param firstNode node to which the first new node is connected, or null
     *            if no normalization node was built for the given node
     * @param origNode the original node for which dummy nodes are created
     * @param startSide start side of the original node
     * @param endSide end side of the original node
     * @return the last newly created node
     */
    private TSMNode addCornerNodes(final KSlimGraph graph, final TSMNode firstNode,
            final TSMNode origNode, final KSlimNode.Side startSide, final KSlimNode.Side endSide) {
        KSlimNode.Side currentSide = startSide;
        TSMNode newNode, startNode = null, currentNode = firstNode;
        do {
            // add the new node and a new edge
            TSMNode.Type cornerType;
            switch (currentSide) {
            case NORTH:
                cornerType = TSMNode.Type.NORM_NE;
                break;
            case EAST:
                cornerType = TSMNode.Type.NORM_SE;
                break;
            case SOUTH:
                cornerType = TSMNode.Type.NORM_SW;
                break;
            case WEST:
                cornerType = TSMNode.Type.NORM_NW;
                break;
            default:
                throw new IllegalStateException("Illegal value: " + currentSide);
            }
            newNode = new TSMNode(graph, cornerType, origNode);
            if (currentNode == null) {
                startNode = currentNode;
            } else {
                KSlimEdge newEdge = new TSMEdge(graph, currentNode, newNode);
                newEdge.connectNodes(currentSide.right(), currentSide.left());
            }

            // register the new normalization node
            switch (cornerType) {
            case NORM_NW:
                startNodeMap.put(origNode, newNode);
                break;
            case NORM_SE:
                endNodeMap.put(origNode, newNode);
                break;
            }

            currentNode = newNode;
            currentSide = currentSide.right();
        } while (currentSide != endSide);
        if (startNode != null) {
            KSlimEdge newEdge = new TSMEdge(graph, currentNode, startNode);
            newEdge.connectNodes(currentSide.left(), currentSide.opposed());
        }
        return currentNode;
    }

    /**
     * Determines a node to attach the endpoint of an edge.
     * 
     * @param graph the graph to which new nodes and edges are added
     * @param edge the edge to be attached.
     * @param portDescriptor port descriptor for the endpoint
     * @param rank rank of the given edge inside its port
     * @param source if true, the source endpoint is attached, else the target
     *            endpoint is attached
     * @return a node that can be used to attach the rest of the edge, and a
     *         boolean value showing whether the edge bend at the endpoint was
     *         consumed
     */
    private Pair<TSMNode, Boolean> getEndpointNode(final KSlimGraph graph, final KSlimEdge edge,
            final PortDescriptor portDescriptor, final int rank, final boolean source) {
        int nodeIndex;
        boolean bendConsumed = false;
        // TODO try to achieve a better merging of edges of the same port
        if (edge.getBends().isEmpty()) {
            nodeIndex = Math.max(portDescriptor.leftEdges, portDescriptor.rightEdges);
        } else if (source && edge.getBends().get(0).getType() == KSlimEdge.Bend.Type.LEFT
                || !source && edge.getBends().get(edge.getBends().size() - 1).getType() == KSlimEdge.Bend.Type.RIGHT) {
            nodeIndex = rank + 1;
        } else {
            nodeIndex = portDescriptor.leftEdges + portDescriptor.straightEdges
                    + portDescriptor.rightEdges - rank;
        }

        if (edge.getBends().size() == 1
                && nodeIndex == Math.max(portDescriptor.leftEdges, portDescriptor.rightEdges)
                && portDescriptor.leftEdges != portDescriptor.rightEdges) {
            nodeIndex--;
        } else if (!edge.getBends().isEmpty()) {
            bendConsumed = true;
        }

        // add new nodes if needed
        for (int i = portDescriptor.nodes.size() - 1; i < nodeIndex; i++) {
            TSMNode newNode = new TSMNode(graph, TSMNode.Type.BEND, edge);
            KSlimEdge newEdge = new TSMEdge(graph, portDescriptor.nodes.get(i), newNode,
                    (KEdge) edge.getObject());
            KSlimNode.Side newSide = source ? edge.getSourceSide() : edge.getTargetSide();
            newEdge.connectNodes(newSide, newSide.opposed());
            portDescriptor.nodes.add(newNode);
        }
        return new Pair<TSMNode, Boolean>(portDescriptor.nodes.get(nodeIndex),
                Boolean.valueOf(bendConsumed));
    }

    /**
     * Builds the dual graph of the given normalized graph using a dual graph
     * builder algorithm.
     * 
     * @param normalizedGraph normalized graph to process
     */
    private void buildDualGraph(final KSlimGraph normalizedGraph) {
        DualGraphBuilder dualGraphBuilder = new DualGraphBuilder();
        dualGraphBuilder.buildDual(normalizedGraph, new ExternalFaceDetector() {
            public boolean isExternal(final List<KSlimFace.BorderEntry> border) {
                int cornerSum = 0;
                KSlimFace.BorderEntry currentEntry = border.get(border.size() - 1);
                for (KSlimFace.BorderEntry nextEntry : border) {
                    KSlimNode.Side side1 = currentEntry.secondSide();
                    KSlimNode.Side side2 = nextEntry.firstSide();
                    if (side1 == side2.left()) {
                        cornerSum--;
                    } else if (side1 == side2.right()) {
                        cornerSum++;
                    } else if (side1 == side2) {
                        cornerSum -= 2;
                    }
                    currentEntry = nextEntry;
                }
                return cornerSum == -4;
            }
        });
    }

    /**
     * Transforms the abstract metrics of the normalized graph to concrete
     * metrics of the original graph.
     * 
     * @param origGraph the original graph
     */
    private void transformMetrics(final KSlimGraph origGraph, final int abstrWidth,
            final int abstrHeight) {
        // create pool of nodes and edge bends
        List<Object> compactables = new LinkedList<Object>();
        compactables.addAll(origGraph.getNodes());
        for (KSlimEdge edge : origGraph.getEdges()) {
            compactables.addAll(edge.getBends());
        }

        // transform horizontal positions
        origGraph.setWidth(transformMetrics(compactables, origGraph, true, abstrWidth));
        // transform vertical positions
        origGraph.setHeight(transformMetrics(compactables, origGraph, false, abstrHeight));

        // set proper coordinates for the first and the last bend of each edge
        for (KSlimEdge edge : origGraph.getEdges()) {
            int bendsCount = edge.getBends().size();
            if (bendsCount > 0) {
                KEdge layoutEdge = (KEdge) edge.getObject();
                // set source bend
                KSlimEdge.Bend sourceBend = edge.getBends().get(0);
                if (edge.getSourceSide() == KSlimNode.Side.NORTH || edge.getSourceSide() == KSlimNode.Side.SOUTH) {
                    sourceBend.setXpos(edge.getSource().getXpos());
                    if (((TSMNode) edge.getSource()).type == TSMNode.Type.LAYOUT
                            && layoutEdge.getSourcePort() != null) {
                        KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(layoutEdge
                                .getSourcePort());
                        sourceBend.setXpos(sourceBend.getXpos() + (portLayout.getXpos() + portLayout.getWidth() / 2));
                    }
                } else {
                    sourceBend.setYpos(edge.getSource().getYpos());
                    if (((TSMNode) edge.getSource()).type == TSMNode.Type.LAYOUT
                            && layoutEdge.getSourcePort() != null) {
                        KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(layoutEdge
                                .getSourcePort());
                        sourceBend.setYpos(sourceBend.getYpos() + (portLayout.getYpos() + portLayout.getHeight() / 2));
                    }
                }

                // set target bend
                KSlimEdge.Bend targetBend = edge.getBends().get(bendsCount - 1);
                if (edge.getTargetSide() == KSlimNode.Side.EAST || edge.getTargetSide() == KSlimNode.Side.WEST) {
                    targetBend.setYpos(edge.getTarget().getYpos());
                    if (((TSMNode) edge.getTarget()).type == TSMNode.Type.LAYOUT
                            && layoutEdge.getTargetPort() != null) {
                        KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(layoutEdge
                                .getTargetPort());
                        targetBend.setYpos(targetBend.getYpos() + (portLayout.getYpos() + portLayout.getHeight() / 2));
                    }
                } else {
                    targetBend.setXpos(edge.getTarget().getXpos());
                    if (((TSMNode) edge.getTarget()).type == TSMNode.Type.LAYOUT
                            && layoutEdge.getTargetPort() != null) {
                        KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(layoutEdge
                                .getTargetPort());
                        targetBend.setXpos(targetBend.getXpos() + (portLayout.getXpos() + portLayout.getWidth() / 2));
                    }
                }
            }
        }
    }

    /**
     * Transforms horizontal or vertical metrics for a given list of compactable
     * elements.
     * 
     * @param compactables list of compactable elements, i.e. nodes or edge
     *            bends
     * @param graph the original graph
     * @param horizontal indicates whether horizontal or vertical compaction is
     *            performed
     * @return the total width or height of the graph
     */
    private float transformMetrics(final List<Object> compactables, final KSlimGraph graph,
            final boolean horizontal, final int abstrSize) {
        // sort compactable elements by their abstract position
        Collections.sort(compactables, new Comparator<Object>() {
            public int compare(final Object o1, final Object o2) {
                int pos1 = getPos(o1, true);
                int pos2 = getPos(o2, true);
                if (pos1 == pos2) {
                    int lastpos1 = getPos(o1, false);
                    int lastpos2 = getPos(o2, false);
                    return lastpos2 - lastpos1;
                } else {
                    return pos1 - pos2;
                }
            }

            /*
             * Returns the abstract position of a node or edge bend.
             * 
             * @param obj node or an edge bend transformed into a normalized
             * node
             * 
             * @param first if true, the first position of a node is returned,
             * which is the position of the upper left corner
             * 
             * @return abstract position, or -1 if the last position of a bend
             * is requested
             */
            int getPos(final Object obj, final boolean first) {
                if (first) {
                    return horizontal ? startNodeMap.get(obj).abstrXpos
                            : startNodeMap.get(obj).abstrYpos;
                }  else if (obj instanceof KSlimNode) {
                    return horizontal ? endNodeMap.get(obj).abstrXpos : endNodeMap.get(obj).abstrYpos;
                } else {
                    return -1;
                }
            }
        });

        // initialize concrete positions array
        float[] concrPos = new float[abstrSize];
        for (int i = 1; i < abstrSize; i++) {
            concrPos[i] = -1;
        }

        // set concrete positions for all compactable elements
        float maxPos = 0.0f;
        int lastPos = 0;
        for (Object obj : compactables) {
            int abstrPos = horizontal ? startNodeMap.get(obj).abstrXpos
                    : startNodeMap.get(obj).abstrYpos;
            if (abstrPos > lastPos) {
                int lastWrittenPos = abstrPos - 1;
                while (concrPos[lastWrittenPos] < 0) {
                    lastWrittenPos--;
                }
                if (concrPos[abstrPos] < concrPos[lastWrittenPos] + minDist) {
                    concrPos[abstrPos] = concrPos[lastWrittenPos] + minDist;
                    maxPos = Math.max(maxPos, concrPos[abstrPos]);
                }
                lastPos = abstrPos;
            }
            if (obj instanceof TSMNode) {
                TSMNode node = (TSMNode) obj;
                if (node.type == TSMNode.Type.LAYOUT) {
                    int endPos = horizontal ? endNodeMap.get(obj).abstrXpos
                            : endNodeMap.get(obj).abstrYpos;
                    KNode layoutNode = (KNode) node.getObject();
                    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
                    float size = horizontal ? nodeLayout.getWidth() : nodeLayout.getHeight();
                    assert endPos - abstrPos > 0;
                    float stepSize = size / (endPos - abstrPos);
                    float currentStep = concrPos[abstrPos];
                    for (int i = abstrPos + 1; i <= endPos; i++) {
                        currentStep += stepSize;
                        concrPos[i] = Math.max(concrPos[i], currentStep);
                    }
                    maxPos = Math.max(maxPos, concrPos[endPos]);
                }
                if (horizontal) {
                    node.setXpos(concrPos[abstrPos]);
                } else {
                    node.setYpos(concrPos[abstrPos]);
                }
            } else if (obj instanceof KSlimEdge.Bend) {
                KSlimEdge.Bend edgeBend = (KSlimEdge.Bend) obj;
                if (horizontal) {
                    edgeBend.setXpos(concrPos[abstrPos]);
                } else {
                    edgeBend.setYpos(concrPos[abstrPos]);
                }
            }
        }
        return maxPos + minDist;
    }

}
