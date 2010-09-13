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
package de.cau.cs.kieler.klay.planar.alg.planarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.INode.NodeType;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * Inserts an edge in a planar graph by building the dual graph. In this graph shortest path is
 * determined. To get the shortest path we test diffrent start- and targetfaces. The algorithm
 * follow this path with the faces in the given graph and splits up every edge to cross with a new
 * node. Then these nodes get connected and build a path which presents the given insertion edge. So
 * the graph is still planar. A special case is, when a hyperedge is crossed. In this case the new
 * edge is layed through the hypernode of this hyperedge. Then the hypernode is splitted up in 2
 * hypernodes. Now we have the edge between these 2 new hypernodes. This edge is now crossed as
 * usual, by adding a new node and connect the new edge to this.
 * 
 * @author cku
 * 
 */
public class EdgeInsertionPlanarization extends AbstractAlgorithm implements IPlanarizer {

    /**
     * A property assigning a cost to an edge. This property is used when computing a shortest path
     * in a graph.
     */
    private Property<Integer> PATHCOST = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.pathcost", 1);

    /**
     * Inserts a list of given pairs of nodes (that presemt edges) into a given planar embedding of
     * a graph.
     * 
     * {@inheritDoc}
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    public void planarize(final IGraph graph, final List<IEdge> edges) {

        for (IEdge insertingEdge : edges) {

            IGraph dualGraph = new PGraphFactory().createDualGraph(graph);
            LinkedList<INode> dualPath = new LinkedList<INode>();

            INode source = insertingEdge.getSource();
            INode target = insertingEdge.getTarget();

            INode dualStartNode = null;
            INode dualTargetNode = null;

            IFace targetFace = null;

            HashSet<IFace> sourceFaces = findSurroundingFaces(source);
            HashSet<IFace> targetFaces = findSurroundingFaces(target);

            ArrayList<IFace> facePath = new ArrayList<IFace>();
            ArrayList<IFace> shortestFacePath = new ArrayList<IFace>();
            LinkedList<INode> shortestDualPath = new LinkedList<INode>();

            int[] parent = new int[graph.getFaceCount()];

            // check all start nodes for BFS in dual graph
            for (IFace sourceFace : sourceFaces) {
                facePath.clear();

                dualStartNode = (INode) sourceFace.getProperty(IGraphFactory.TODUALGRAPH);

                // run BFS for all possible target faces
                for (IFace itargetFace : targetFaces) {

                    dualTargetNode = (INode) itargetFace.getProperty(IGraphFactory.TODUALGRAPH);

                    parent = bfs(dualStartNode, dualTargetNode, dualGraph);

                    // the path through the dual graph
                    dualPath = findPath(dualTargetNode.getID(), parent, dualGraph);

                    if (shortestDualPath.size() > dualPath.size() || shortestDualPath.isEmpty()) {
                        shortestDualPath.clear();
                        shortestDualPath.addAll(0, dualPath);
                        targetFace = itargetFace;
                    }
                }

                assert sourceFace.getID() != targetFace.getID() : "nodes lie at the same face";

                // same path with faces in the normal graph
                facePath.add(sourceFace);

                for (INode node : shortestDualPath) {
                    IFace face = dualNodeToFace(node, graph);
                    if (face.getID() != sourceFace.getID()) {
                        facePath.add(face);
                    }

                }
                if (!facePath.contains(targetFace)) {
                    facePath.add(targetFace);
                }

                // we found a better startface with shorter path
                if (shortestFacePath.size() > facePath.size() || shortestFacePath.isEmpty()) {
                    shortestFacePath.clear();
                    shortestFacePath.addAll(0, facePath);
                }

                // path can't be shorter then 1, search finished
                if (shortestFacePath.size() == 1) {
                    break;
                }
            }
            System.out.println("Der Face Path:");
            System.out.println(shortestFacePath.toString());

            // find the borders to cross
            LinkedList<IEdge> crossingBorders = new LinkedList<IEdge>();
            int faceCounter = 0;
            while (faceCounter < shortestFacePath.size() - 1) {
                IFace face1 = shortestFacePath.get(faceCounter);
                IFace face2 = shortestFacePath.get(faceCounter + 1);
                IEdge crossingEdge = findBorderEdge(face1, face2);
                crossingBorders.add(crossingEdge);
                faceCounter++;

            }

            // the node path with new nodes for the crossing edges
            ArrayList<INode> path = new ArrayList<INode>();
            path.add(source);
            for (IEdge crossingEdge : crossingBorders) {

                // crossing a hyperedge
                if (crossingEdge.getSource().getType() == NodeType.HYPER) {
                    path.add(crossingEdge.getSource());
                }

                if (crossingEdge.getTarget().getType() == NodeType.HYPER) {
                    path.add(crossingEdge.getTarget());
                }

                // crossing a normal edge
                else {
                    INode newNode = graph.addNode(crossingEdge);
                    path.add(newNode);
                }
            }
            path.add(target);

            // connect the node path
            int pathNodeCounter = 0;
            while (pathNodeCounter < path.size() - 1) {

                // split up the crossed hypernodes
                // TODO check this!!!
                if (path.get(pathNodeCounter).getType() == NodeType.HYPER) {

                    INode oldHyperNode = path.get(pathNodeCounter);

                    // create new hypernode and connect this with the old one
                    INode newHyperNode = graph.addNode();
                    IEdge newHyperEdge = graph.addEdge(oldHyperNode, newHyperNode);

                    // bring edge in right order
                    reinsertEdges(newHyperEdge, oldHyperNode);

                    // move half of the edges to new hypernode
                    for (int i = 0; i < oldHyperNode.getAdjacentEdgeCount() / 2; i++) {
                        // FIXME
                    }

                    // split up the new edge
                    INode midNode = graph.addNode(newHyperEdge);

                    // connect the new node
                    IEdge newEdge = graph.addEdge(midNode, path.get(pathNodeCounter + 1));

                    // bring new edges at new node in right order
                    reinsertEdges(newEdge, midNode);
                    pathNodeCounter++;
                }

                // connecting new normal nodes
                else {

                    IEdge newEdge = graph.addEdge(path.get(pathNodeCounter),
                            path.get(pathNodeCounter + 1));

                    // bring new edges in right order
                    reinsertEdges(newEdge, newEdge.getSource());
                    reinsertEdges(newEdge, newEdge.getTarget());

                    pathNodeCounter++;
                }
            }
        }
    }

    /**
     * finds all faces, that contact the node.
     * 
     * @param node
     *            , the given node
     * @return faces, all faces that contact the node
     */
    private HashSet<IFace> findSurroundingFaces(final INode node) {
        HashSet<IFace> faces = new HashSet<IFace>();
        for (IEdge edge : node.adjacentEdges()) {
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
    private void reinsertEdges(final IEdge edge, final INode node) {

        IEdge preNewEdge = findFirstFaceEdge(edge, node);
        List<IEdge> edges = new LinkedList<IEdge>();
        for (IEdge e : node.adjacentEdges()) {
            edges.add(e);
        }
        int index = 0;
        for (IEdge iedge : edges) {
            if (iedge == preNewEdge) {
                // move the given edge in right position
                edge.move(node, node);
                // move the rest of the edges after the given edge
                if (index < edges.size() - 1) {
                    for (IEdge iEdge : edges.subList(index + 1, edges.size() - 1)) {
                        iEdge.move(node, node);

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
    private IEdge findFirstFaceEdge(final IEdge iedge, final INode node) {
        // node is target of edge
        IFace face = iedge.getRightFace();
        // node is source of edge
        if (iedge.getSource() == node) {
            face = iedge.getLeftFace();
        }
        for (IEdge edge : node.adjacentEdges()) {
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
    private IEdge findBorderEdge(final IFace face1, final IFace face2) {
        for (IEdge edge1 : face1.adjacentEdges()) {
            for (IEdge edge2 : face2.adjacentEdges()) {
                if (edge1.getID() == edge2.getID()) {
                    return edge1;
                }
            }
        }
        return null;
    }

    /**
     * finds the suiting face in the graph with a given node from the dual graph.
     * 
     * @param node
     *            , the given node
     * @param graph
     *            , the given graph
     * @return face, the searched face
     */
    private IFace dualNodeToFace(final INode node, final IGraph graph) {
        for (IFace face : graph.getFaces()) {
            if (node.getID() == face.getID()) {
                return face;
            }
        }
        return null;
    }

    /**
     * builds a path from the target node to the source node.
     * 
     * @param sourceIndex
     *            , the index of the source node
     * @param targetIndex
     *            , the index of the target node
     * @param parent
     *            , the array of parents
     * @param graph
     *            , the given graph
     * 
     * @return path, a list of nodes from source to target
     */
    private LinkedList<INode> findPath(final int targetIndex, final int[] parent, final IGraph graph) {

        LinkedList<INode> path = new LinkedList<INode>();
        int nextID = parent[targetIndex];

        // -1 is the source index
        do {
            INode nextNode = findNode(nextID, graph.getNodes());
            path.add(nextNode);
            nextID = parent[nextNode.getID()];
        } while (nextID != -1);

        return path;
    }

    /**
     * finds a node by his id in a given LinkedList.
     * 
     * @param index
     *            , the given index
     * @param iterable
     *            , the given list
     * @return node, the searched node
     */
    private INode findNode(final int nodeID, final Iterable<INode> iterable) {
        for (INode node : iterable) {
            if (node.getID() == nodeID) {
                return node;
            }

        }
        return null;
    }

    /**
     * searches all distances and parents from the root in the given graph.
     * 
     * @param root
     *            , the start node
     * @param graph
     *            , the given graph
     * @return parent , array with the parents from root to all nodes
     */
    private int[] bfs(final INode root, final INode target, final IGraph graph) {
        int size = graph.getNodeCount();
        int rootID = root.getID();
        int targetID = target.getID();

        // initialize arrays
        int[] parent = new int[size];
        int[] distance = new int[size];
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            parent[i] = -1;
            distance[i] = -1;
            visited[i] = false;
        }

        parent[rootID] = -1;
        visited[rootID] = true;
        distance[rootID] = 0;

        // start BFS
        LinkedList<INode> queue = new LinkedList<INode>();
        queue.add(root);

        while (!queue.isEmpty()) {

            INode currentNode = queue.poll();
            int currentID = currentNode.getID();

            // BFS can stop if we reached the target
            if (currentID == targetID) {
                return parent;
            }

            // find all neighbors and put them in an array
            INode[] neighbors = new INode[currentNode.getAdjacentEdgeCount()];
            int neighborCounter = 0;

            for (INode neighborNode : currentNode.adjacentNodes()) {
                neighbors[neighborCounter] = neighborNode;
                neighborCounter++;
            }

            // find the neighbors and get parent and distance
            for (int i = 0; i < neighbors.length; i++) {
                INode neighbor = neighbors[i];
                int neighborIndex = neighbor.getID();
                if (!visited[neighborIndex]) {
                    parent[neighborIndex] = currentID;
                    distance[neighborIndex] = distance[currentID] + 1;
                    visited[neighborIndex] = true;
                    queue.add(neighbor);
                }
                visited[currentID] = true;
            }
        }
        return parent;
    }

    /**
     * a dijkstra algorithm which finds the shortest path in a graph, where edges are undirected
     * have a weight.
     * 
     * @param root
     *            , the start node
     * @param graph
     *            , the given graph
     * @return parent , array with the parents from root to all nodes
     */
    @SuppressWarnings("unused")
    private int[] dijkstra(final INode root, final IGraph graph) {

        int size = graph.getNodeCount();
        int rootID = root.getID();

        // initialize arrays
        int[] parent = new int[size];
        int[] distance = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = -1;
            distance[i] = -1;
        }

        parent[rootID] = -1;
        distance[rootID] = 0;

        // get all nodes of the given graph and put them in a hash set
        LinkedHashSet<INode> nodes = new LinkedHashSet<INode>();
        for (INode iNode : graph.getNodes()) {
            nodes.add(iNode);
        }
        INode currentNode = root;
        int currentID = currentNode.getID();

        while (!nodes.isEmpty()) {

            // find the node with smalles weight in nodes
            int minEdgeWeight = Integer.MAX_VALUE;
            for (INode neighborNode : currentNode.adjacentNodes()) {
                IEdge connectingEdge = currentNode.getEdge(neighborNode);
                int edgeWeight = connectingEdge.getProperty(PATHCOST);
                if (edgeWeight < minEdgeWeight) {
                    minEdgeWeight = edgeWeight;
                }
            }

            nodes.remove(currentNode);

            for (INode neighborNode : currentNode.adjacentNodes()) {
                int neighborID = neighborNode.getID();
                if (nodes.contains(neighborNode)) {

                    // distance between current and neighbor
                    int wayLenght = currentNode.getEdge(neighborNode).getProperty(PATHCOST);
                    int alternative = distance[currentID] + wayLenght;

                    // check if there is a shorter path
                    if (alternative < distance[neighborID]) {
                        distance[neighborID] = alternative;
                        parent[neighborID] = currentID;
                    }

                }
            }
        }

        return parent;
    }

    /**
     * merges hypernodes to get a point-based approach from a tree-based approach.
     * 
     * @param graph
     *            , the given graph
     */
    @SuppressWarnings("unused")
    private void mergeHyperNodes(final IGraph graph) {
        for (INode node : graph.getNodes()) {
            if (node.getType() == NodeType.HYPER) {
                for (INode inode : node.adjacentNodes()) {
                    if (inode.getType() == NodeType.HYPER) {

                        // remove edges which connect hypernodes
                        graph.removeEdge(node.getEdge(inode));

                        // connect all edges with 1 hypernode
                        for (IEdge edge : inode.adjacentEdges()) {
                            edge.move(inode, node);
                        }

                        // remove all unneeded hypernodes
                        graph.removeNode(inode);

                    }
                }
            }
        }
    }

}
