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
package de.cau.cs.kieler.klay.planar.p1planar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.AbstractPathFinder;
import de.cau.cs.kieler.klay.planar.pathfinding.DijkstraPathFinder;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Inserts an edge in a planar graph by building the dual graph. How the dual graph is build,
 * depends on the given graph. In this graph shortest path is determined. To get the shortest path
 * we test different start- and target faces. The algorithm follow this path with the faces in the
 * given graph and splits up every edge to cross with a new node. Then these nodes get connected and
 * build a path which presents the given insertion edge. So the graph is still planar. This edge is
 * now crossed as usual, by adding a new node and connect the new edge to this.
 * 
 * @author cku
 * @author pkl
 * 
 */
public class EdgeInsertionPlanarization extends AbstractAlgorithm implements ILayoutPhase {

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final PGraph graph) {
        return null;
    }

    /**
     * builds a path of nodes, depending on the edges to cross.
     * 
     * @param crossingBorders
     *            , list of borders that are crossed by the new edge
     * @param source
     *            , the source of the new edge
     * @param target
     *            , the target of the new egde
     * @param graph
     *            , the given graph
     * @return
     */
    private ArrayList<PNode> buildPathFromBorders(final LinkedList<PEdge> crossingBorders,
            final PNode source, final PNode target, final PGraph graph) {
        ArrayList<PNode> path = new ArrayList<PNode>();
        path.add(source);
        for (PEdge crossingEdge : crossingBorders) {
            // crossing a normal edge
            PNode newNode = graph.addNode(crossingEdge).getFirst();
            newNode.setProperty(Properties.PlANAR_DUMMY_NODE, true);
            path.add(newNode);
        }
        path.add(target);
        return path;
    }

    /**
     * finds the borders to cross, from a given path of faces.
     * 
     * @param shortestFacePath
     *            , the path of faces through the graph
     * @return
     */
    private LinkedList<PEdge> findCrossingBorders(final LinkedList<PFace> shortestFacePath) {
        LinkedList<PEdge> crossingBorders = new LinkedList<PEdge>();
        int faceCounter = 0;
        while (faceCounter < shortestFacePath.size() - 1) {
            PFace face1 = shortestFacePath.get(faceCounter);
            PFace face2 = shortestFacePath.get(faceCounter + 1);
            // find the edge between face1 and fac2
            PEdge crossingEdge = findBorderEdge(face1, face2);
            crossingBorders.add(crossingEdge);
            faceCounter++;

        }
        return crossingBorders;
    }

    /**
     * builds a face path in the real graph, from a given edge path in the dual graph.
     * 
     * @param dualEdgePath
     *            , a path of edges through the dual graph
     * @return shortestFacePath, a path of faces
     */
    private LinkedList<PFace> getShortestFacePath(final List<PEdge> dualEdgePath) {
        LinkedList<PFace> shortestFacePath = new LinkedList<PFace>();

        // same path with faces in the normal graph
        for (PEdge PEdge : dualEdgePath) {
            PFace firstFace = (PFace) PEdge.getSource().getProperty(Properties.TODUALGRAPH);
            PFace secondFace = (PFace) PEdge.getTarget().getProperty(Properties.TODUALGRAPH);

            // add first face to the path
            if (!shortestFacePath.contains(firstFace) && firstFace != null) {
                shortestFacePath.add(firstFace);
            }
            // add second face to the path
            if (!shortestFacePath.contains(secondFace) && secondFace != null) {
                shortestFacePath.add(secondFace);
            }
        }
        return shortestFacePath;
    }

    /**
     * returns all pre edges at the source nodes of a path.
     * 
     * @param nodePath
     *            , the path of nodes
     * @param facePath
     *            , the path of faces adjacent to the nodes
     * @return sourcePreEdges, all pre edges at the source nodes
     */
    private List<PEdge> getSourcePreEdges(final ArrayList<PNode> nodePath,
            final LinkedList<PFace> facePath) {

        LinkedList<PEdge> sourcePreEdges = new LinkedList<PEdge>();
        int i = 0;
        while (i < nodePath.size() - 1) {

            PNode src = nodePath.get(i);
            PEdge edge = null;

            PFace face = facePath.get(i);
            for (PEdge e : face.adjacentEdges()) {
                if ((e.getSource() == src) && (e.getRightFace() == face)) {
                    edge = e;
                    break;
                } else if ((e.getTarget() == src) && (e.getLeftFace() == face)) {
                    edge = e;
                    break;
                }
            }
            sourcePreEdges.add(edge);
            i++;
        }
        return sourcePreEdges;

    }

    /**
     * returns all pre edges at the target nodes of a path.
     * 
     * @param nodePath
     *            , the path of nodes
     * @param facePath
     *            , the path of faces adjacent to the nodes
     * @return targetPreEdges, all pre edges at the target nodes
     */
    private List<PEdge> getTargetPreEdges(final ArrayList<PNode> nodePath,
            final LinkedList<PFace> facePath) {

        LinkedList<PEdge> targetPreEdges = new LinkedList<PEdge>();
        int i = 0;
        while (i < nodePath.size() - 1) {

            PNode dst = nodePath.get(i + 1);
            PEdge edge = null;

            PFace face = facePath.get(i);
            for (PEdge e : face.adjacentEdges()) {
                if ((e.getSource() == dst) && (e.getRightFace() == face)) {
                    edge = e;
                    break;
                } else if ((e.getTarget() == dst) && (e.getLeftFace() == face)) {
                    edge = e;
                    break;
                }
            }
            targetPreEdges.add(edge);
            i++;
        }
        return targetPreEdges;

    }

    /**
     * Find the next edge on a node.
     * 
     * @param node
     *            , the node containing the edges
     * @param edge
     *            , the current edge
     * @return the next edge
     */
    private PEdge getNextClockwiseEdge(final PNode node, final PEdge edge) {
        Iterator<PEdge> iter = node.adjacentEdges().iterator();
        PEdge current = null;
        while (iter.hasNext() && current != edge) {
            current = iter.next();
        }
        if (iter.hasNext()) {
            // Get the next edge on the node
            return iter.next();
        } else {
            // Reached the last node, get the first
            return node.adjacentEdges().iterator().next();
        }
    }

    /**
     * finds all faces, that contact the node.
     * 
     * @param node
     *            , the given node
     * @return faces, all faces that contact the node
     */
    private HashSet<PFace> findSurroundingFaces(final PNode node) {
        HashSet<PFace> faces = new HashSet<PFace>();
        for (PEdge edge : node.adjacentEdges()) {
            faces.add(edge.getLeftFace());
            faces.add(edge.getRightFace());

        }
        return faces;
    }

    /**
     * brings the edges of a node in the right order for an embedding.
     * 
     * @param edge
     *            , the new edge
     * @param node
     *            , the given node
     */
    private void reinsertEdges(final PEdge edge, final PEdge preEdge, final PNode node) {

        List<PEdge> edges = new LinkedList<PEdge>();
        for (PEdge e : node.adjacentEdges()) {
            edges.add(e);
        }
        int index = 0;
        for (PEdge pedge : edges) {
            if (pedge == preEdge) {
                // move the given edge in right position
                edge.move(node, node);
                // move the rest of the edges after the given edge
                if (index < edges.size() - 1) {
                    for (PEdge pedge2 : edges.subList(index + 1, edges.size() - 1)) {
                        pedge2.move(node, node);
                    }
                    break;
                }
            }
            index++;
        }
    }

    /**
     * returns the first clockwise edge of a face from a given node.
     * 
     * @param face
     *            , the given face
     * @param node
     *            , the given node
     * @return edge, the clockwise first edge at this node
     */
    private PEdge findFirstFaceEdge(final PEdge pEdge, final PNode node) {
        // node is target of edge
        PFace face = pEdge.getRightFace();
        // node is source of edge
        if (pEdge.getSource() == node) {
            face = pEdge.getLeftFace();
        }
        for (PEdge edge : node.adjacentEdges()) {
            if (edge.getRightFace() == face || edge.getLeftFace() == face) {
                return edge;
            }
        }
        return null;
    }

    /**
     * finds the borderEdge between 2 faces.
     * 
     * @param face1
     *            , the first face
     * @param face2
     *            , the second face
     * @return edge1, the borderEdge
     */
    private PEdge findBorderEdge(final PFace face1, final PFace face2) {
        for (PEdge edge1 : face1.adjacentEdges()) {
            for (PEdge edge2 : face2.adjacentEdges()) {
                if (edge1.id == edge2.id) {
                    return edge1;
                }
            }
        }
        return null;
    }

    /**
     * Inserts a list of given pairs of nodes (that present edges) into a given planar embedding of
     * a graph.
     * 
     * Takes a planar graph and adds some more edges to the graph. These missing edges would make
     * the graph non-planar, so this algorithm adds them and inserts dummy nodes on the edge
     * crossings, the so graph stays planar.
     * 
     * @param graph
     *            the planar subgraph containing a list of the edges to be inserted into the graph
     * 
     */
    public void process(final PGraph graph) {
        List<PEdge> edges = graph.getProperty(Properties.INSERTABLE_EDGES);
        if (edges != null) {

            // initialize path finder
            AbstractPathFinder dijkstra = new DijkstraPathFinder();

            for (PEdge insertingEdge : edges) {

                // get the dual graph of the given graph
                PGraph dualGraph = new PGraphFactory().createDualGraph(graph);

                // insert the original nodes in the dual graph
                PNode dualStartNode = dualGraph.addNode();
                PNode dualTargetNode = dualGraph.addNode();

                PNode source = insertingEdge.getSource();
                PNode target = insertingEdge.getTarget();

                // find the faces around source and target
                HashSet<PFace> sourceFaces = findSurroundingFaces(source);
                HashSet<PFace> targetFaces = findSurroundingFaces(target);

                // connect the start node with the start faces for 0 cost
                for (PFace sFace : sourceFaces) {
                    PNode newDualNode = (PNode) sFace.getProperty(Properties.TODUALGRAPH);
                    PEdge newDualEdge = dualGraph.addEdge(dualStartNode, newDualNode, true);
                    newDualEdge.setProperty(IPathFinder.PATHCOST, 0);
                }

                // connect the target node with the target faces for 0 cost
                for (PFace tFace : targetFaces) {
                    PNode newDualNode = (PNode) tFace.getProperty(Properties.TODUALGRAPH);
                    PEdge newDualEdge = dualGraph.addEdge(newDualNode, dualTargetNode, true);
                    newDualEdge.setProperty(IPathFinder.PATHCOST, 0);
                }

                // find the shortest path through dual graph via dijkstra
                List<PEdge> dualEdgePath = dijkstra.findPath(dualStartNode, dualTargetNode);

                // get a path of faces in the original graph
                LinkedList<PFace> shortestFacePath = getShortestFacePath(dualEdgePath);

                // find the borders to cross
                LinkedList<PEdge> crossingBorders = findCrossingBorders(shortestFacePath);

                // the node path with new nodes for the crossing edges
                ArrayList<PNode> path = buildPathFromBorders(crossingBorders, source, target, graph);

                // connect the node path
                int pathNodeCounter = 0;

                List<PEdge> sourcePreEdges = getSourcePreEdges(path, shortestFacePath);
                List<PEdge> targetPreEdges = getTargetPreEdges(path, shortestFacePath);

                while (pathNodeCounter < path.size() - 1) {
                    // connecting new normal nodes
                    PNode src = path.get(pathNodeCounter);
                    PNode dst = path.get(pathNodeCounter + 1);

                    PEdge newEdge = graph.addEdge(src, dst);

                    // bring new edges in right order
                    reinsertEdges(newEdge, sourcePreEdges.get(pathNodeCounter), src);
                    reinsertEdges(newEdge, targetPreEdges.get(pathNodeCounter), dst);
                    pathNodeCounter++;
                }
            }
        }
    }

}
