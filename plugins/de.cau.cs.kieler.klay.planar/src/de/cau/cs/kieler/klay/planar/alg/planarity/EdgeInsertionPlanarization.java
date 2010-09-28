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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.AbstractPathFinder;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.DijkstraPathFinder;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.IPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.INode.NodeType;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * Inserts an edge in a planar graph by building the dual graph. How the dual graph is build,
 * depends on the given graph. If this graph is a hyper graph the dual graph contains more edges,
 * which depends on the hypernode. In this graph shortest path is determined. To get the shortest
 * path we test diffrent start- and targetfaces. The algorithm follow this path with the faces in
 * the given graph and splits up every edge to cross with a new node. Then these nodes get connected
 * and build a path which presents the given insertion edge. So the graph is still planar. A special
 * case is, when a hyperedge is crossed. In this case the new edge is layed through the hypernode of
 * this hyperedge. Then the hypernode is splitted up in 2 hypernodes. Now we have the edge between
 * these 2 new hypernodes. This edge is now crossed as usual, by adding a new node and connect the
 * new edge to this.
 * 
 * @author cku
 * 
 */
public class EdgeInsertionPlanarization extends AbstractAlgorithm implements IPlanarizer {
    /**
     * Inserts a list of given pairs of nodes (that presemt edges) into a given planar embedding of
     * a graph.
     * 
     * {@inheritDoc}
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    public void planarize(final IGraph graph, final List<IEdge> edges) {
        if (edges == null) {
            System.out.println("No new Edges");
        } else {
            // bring graph in point-based standard
            mergeHyperNodes(graph);
            // initialize path finder
            AbstractPathFinder dijkstra = new DijkstraPathFinder();

            for (IEdge insertingEdge : edges) {

                System.out.println("Subgraph");
                System.out.println(graph);
                System.out.println("Neue Kanten\n\n");
                System.out.println(edges.toString());

                // get the dual graph of the given graph
                IGraph dualGraph = new PGraphFactory().createDualGraph(graph);

                // insert the original nodes in the dual graph
                INode dualStartNode = dualGraph.addNode();
                INode dualTargetNode = dualGraph.addNode();

                INode source = insertingEdge.getSource();
                INode target = insertingEdge.getTarget();

                // find the faces around source and target
                HashSet<IFace> sourceFaces = findSurroundingFaces(source);
                HashSet<IFace> targetFaces = findSurroundingFaces(target);

                // connect the start node with the start faces for 0 cost
                for (IFace sFace : sourceFaces) {
                    INode newDualNode = (INode) sFace.getProperty(IGraphFactory.TODUALGRAPH);
                    IEdge newDualEdge = dualGraph.addEdge(dualStartNode, newDualNode, true);
                    newDualEdge.setProperty(IPathFinder.PATHCOST, 0);
                }

                // connect the target node with the target faces for 0 cost
                for (IFace tFace : targetFaces) {
                    INode newDualNode = (INode) tFace.getProperty(IGraphFactory.TODUALGRAPH);
                    IEdge newDualEdge = dualGraph.addEdge(newDualNode, dualTargetNode, true);
                    newDualEdge.setProperty(IPathFinder.PATHCOST, 0);
                }

                // add edges for faces laying at the same hypernode in dualgraph
                extendDualGraph(graph, dualGraph);

                // find the shortest path through dual graph via dijkstra
                List<IEdge> dualEdgePath = dijkstra.findPath(dualStartNode, dualTargetNode);

                // get a path of faces in the original graph
                LinkedList<IFace> shortestFacePath = getShortestFacePath(dualEdgePath);

                System.out.println("Der Face Path:");
                System.out.println(shortestFacePath.toString());

                // find the borders to cross
                LinkedList<IEdge> crossingBorders = findCrossingBorders(shortestFacePath);

                // the node path with new nodes for the crossing edges
                ArrayList<INode> path = buildPathFromBorders(crossingBorders, source, target, graph);

                // connect the node path
                int pathNodeCounter = 0;
                while (pathNodeCounter < path.size() - 1) {

                    // split up the crossed hypernodes
                    if (path.get(pathNodeCounter).getType() == NodeType.HYPER) {
                        splitUpHypernodes(path, graph, pathNodeCounter);
                    }

                    // connecting new normal nodes
                    else {

                        List<IEdge> sourcePreEdges = getSourcePreEdges(path, shortestFacePath);
                        List<IEdge> targetPreEdges = getTargetPreEdges(path, shortestFacePath);

                        INode src = path.get(pathNodeCounter);
                        INode dst = path.get(pathNodeCounter + 1);

                        IEdge newEdge = graph.addEdge(src, dst);

                        // bring new edges in right order
                        reinsertEdges(newEdge, sourcePreEdges.get(pathNodeCounter), src);
                        reinsertEdges(newEdge, targetPreEdges.get(pathNodeCounter), dst);
                        pathNodeCounter++;
                    }
                }
            }
        }
    }

    /**
     * add edges for faces laying at the same hypernode in dualgraph.
     * 
     * @param graph
     *            , the given graph
     * @param dualGraph
     *            , the dual graph of the given graph + 2 nodes
     */
    private void extendDualGraph(IGraph graph, IGraph dualGraph) {
        for (INode node : graph.getNodes()) {
            if (node.getType() == NodeType.HYPER) {
                LinkedHashSet<INode> dualNodes = new LinkedHashSet<INode>();
                IFace face = null;
                for (IEdge edge : node.adjacentEdges()) {
                    if (node == edge.getSource()) {
                        face = edge.getRightFace();
                    } else {
                        face = edge.getLeftFace();
                    }
                    INode dualNode = (INode) face.getProperty(IGraphFactory.TODUALGRAPH);
                    dualNodes.add(dualNode);
                }
                for (INode iNode : dualNodes) {
                    for (INode iNode2 : dualNodes) {
                        if (iNode != iNode2) {
                            dualGraph.addEdge(iNode, iNode2, false);
                        }
                    }
                }
            }
        }

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
    private ArrayList<INode> buildPathFromBorders(LinkedList<IEdge> crossingBorders, INode source,
            INode target, IGraph graph) {
        ArrayList<INode> path = new ArrayList<INode>();
        path.add(source);
        for (IEdge crossingEdge : crossingBorders) {

            // crossing a edge has hypernode as source
            if (crossingEdge.getSource().getType() == NodeType.HYPER) {
                path.add(crossingEdge.getSource());
            }
            // crossing a edge has hypernode as target
            if (crossingEdge.getTarget().getType() == NodeType.HYPER) {
                path.add(crossingEdge.getTarget());
            }

            // crossing a normal edge
            else {
                INode newNode = graph.addNode(crossingEdge).getFirst();
                path.add(newNode);
            }
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
    private LinkedList<IEdge> findCrossingBorders(LinkedList<IFace> shortestFacePath) {
        LinkedList<IEdge> crossingBorders = new LinkedList<IEdge>();
        int faceCounter = 0;
        while (faceCounter < shortestFacePath.size() - 1) {
            IFace face1 = shortestFacePath.get(faceCounter);
            IFace face2 = shortestFacePath.get(faceCounter + 1);
            // find the edge between face1 and fac2
            IEdge crossingEdge = findBorderEdge(face1, face2);
            crossingBorders.add(crossingEdge);
            faceCounter++;

        }
        return crossingBorders;
    }

    /**
     * splits a hypernode in 2 hypernodes and inserts a new node on the egde between the hypernodes.
     * 
     * @param path
     *            , a path of nodes for the new edge
     * @param graph
     *            , the given graph
     * @param pathNodeCounter
     *            , the position on the path
     */
    private void splitUpHypernodes(ArrayList<INode> path, IGraph graph, int pathNodeCounter) {

        INode oldHyperNode = path.get(pathNodeCounter);

        // create new hypernode and connect this with the old one
        INode newHyperNode = graph.addNode();
        IEdge newHyperEdge = graph.addEdge(oldHyperNode, newHyperNode);

        // bring edge in right order
        IEdge preEdgeA = this.findFirstFaceEdge(newHyperEdge, oldHyperNode);
        reinsertEdges(newHyperEdge, preEdgeA, oldHyperNode);

        // move half of the edges to new hypernode
        IEdge next = getNextClockwiseEdge(oldHyperNode, newHyperEdge);
        for (int x = 0; x < oldHyperNode.getAdjacentEdgeCount() / 2; x++) {
            next.move(oldHyperNode, newHyperNode);
            next = getNextClockwiseEdge(oldHyperNode, next);
        }

        // split up the new edge
        INode midNode = graph.addNode(newHyperEdge).getFirst();

        // connect the new node
        IEdge newEdge = graph.addEdge(midNode, path.get(pathNodeCounter + 1));

        // bring new edges at new node in right order
        IEdge preEdgeB = this.findFirstFaceEdge(newEdge, midNode);
        reinsertEdges(newEdge, preEdgeB, midNode);
        pathNodeCounter++;

    }

    /**
     * builds a face path in the real graph, from a given edge path in the dual graph
     * 
     * @param dualEdgePath
     *            , a path of edges through the dual graph
     * @return shortestFacePath, a path of faces
     */
    private LinkedList<IFace> getShortestFacePath(List<IEdge> dualEdgePath) {
        LinkedList<IFace> shortestFacePath = new LinkedList<IFace>();

        // same path with faces in the normal graph
        for (IEdge iEdge : dualEdgePath) {
            IFace firstFace = (IFace) iEdge.getSource().getProperty(IGraphFactory.TODUALGRAPH);
            IFace secondFace = (IFace) iEdge.getTarget().getProperty(IGraphFactory.TODUALGRAPH);

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
     * returns all pre edges at the source nodes of a path
     * 
     * @param nodePath
     *            , the path of nodes
     * @param facePath
     *            , the path of faces adjacent to the nodes
     * @return sourcePreEdges, all pre edges at the source nodes
     */
    private List<IEdge> getSourcePreEdges(ArrayList<INode> nodePath, LinkedList<IFace> facePath) {
        LinkedList<IEdge> sourcePreEdges = new LinkedList<IEdge>();
        int i = 0;
        while (i < nodePath.size() - 1) {

            INode src = nodePath.get(i);
            IEdge edge = null;

            IFace face = facePath.get(i);
            for (IEdge e : face.adjacentEdges()) {
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
     * returns all pre edges at the target nodes of a path
     * 
     * @param nodePath
     *            , the path of nodes
     * @param facePath
     *            , the path of faces adjacent to the nodes
     * @return targetPreEdges, all pre edges at the target nodes
     */
    private List<IEdge> getTargetPreEdges(ArrayList<INode> nodePath, LinkedList<IFace> facePath) {
        LinkedList<IEdge> targetPreEdges = new LinkedList<IEdge>();
        int i = 0;
        while (i < nodePath.size() - 1) {

            INode dst = nodePath.get(i + 1);
            IEdge edge = null;

            IFace face = facePath.get(i);
            for (IEdge e : face.adjacentEdges()) {
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
    private IEdge getNextClockwiseEdge(final INode node, final IEdge edge) {
        Iterator<IEdge> iter = node.adjacentEdges().iterator();
        IEdge current = null;
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
    private void reinsertEdges(final IEdge edge, final IEdge preEdge, final INode node) {

        List<IEdge> edges = new LinkedList<IEdge>();
        for (IEdge e : node.adjacentEdges()) {
            edges.add(e);
        }
        int index = 0;
        for (IEdge iedge : edges) {
            if (iedge == preEdge) {
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
     * merges hypernodes to get a point-based approach from a tree-based approach.
     * 
     * @param graph
     *            , the given graph
     */
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
