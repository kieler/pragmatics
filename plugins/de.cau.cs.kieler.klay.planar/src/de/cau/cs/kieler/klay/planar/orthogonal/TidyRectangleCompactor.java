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
package de.cau.cs.kieler.klay.planar.orthogonal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.flownetwork.SuccessiveShortestPathFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphElement;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;
import de.cau.cs.kieler.klay.planar.orthogonal.OrthogonalRepresentation.OrthogonalAngle;

/**
 * A compaction algorithm that minimizes the length of horizontal and vertical edge segments
 * separately. It only works on simple orthogonal representations, i.e. orthogonal representation
 * where every face is represented as a rectangle. General orthogonal representations have to
 * reduced to a simple one prior to performing this algorithm.
 * 
 * @author ocl
 */
public class TidyRectangleCompactor extends AbstractAlgorithm implements ICompactor {

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<IGraphElement> NETWORKTOGRAPH = new Property<IGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private IGraph graph;

    /** The orthogonal representation of the graph. */
    private OrthogonalRepresentation orthogonal;

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void compact(final IGraph g, final OrthogonalRepresentation o) {
        this.graph = g;
        this.orthogonal = o;

        // Create networks and solve
        Pair<IGraph, IGraph> networks = this.createFlowNetworks();
        IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        solver.findFlow(networks.getFirst());
        solver.findFlow(networks.getSecond());

        // TODO Assign coordinates based on flow
    }

    /**
     * Create the flow networks for vertical and horizontal metrics.
     * 
     * @return the flow network
     */
    private Pair<IGraph, IGraph> createFlowNetworks() {
        IGraphFactory factory = new PGraphFactory();
        IGraph vertical = factory.createEmptyGraph();
        IGraph horizontal = factory.createEmptyGraph();
        Map<IFace, INode> verticalMap = new HashMap<IFace, INode>();
        Map<IFace, INode> horizontalMap = new HashMap<IFace, INode>();

        // Create nodes for every graph face
        for (IFace face : this.graph.getFaces()) {
            INode newnode;
            newnode = vertical.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            verticalMap.put(face, newnode);
            newnode = horizontal.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            horizontalMap.put(face, newnode);
        }

        // Create arcs for vertical or horizontal edges
        // Traverse graph with DFS
        Set<IEdge> visited = new HashSet<IEdge>(this.graph.getEdgeCount() * 2);
        Stack<IEdge> edges = new Stack<IEdge>();
        Stack<Pair<Boolean, Boolean>> direction = new Stack<Pair<Boolean, Boolean>>();
        for (IEdge edge : this.graph.getEdges()) {
            if (!visited.contains(edge)) {
                edges.push(edge);
                direction.push(new Pair<Boolean, Boolean>(false, false));
                while (!edges.isEmpty()) {
                    IEdge current = edges.pop();
                    Pair<Boolean, Boolean> dir = direction.pop();
                    // TODO create arcs

                    List<INode> list = new LinkedList<INode>();
                    list.add(edge.getSource());
                    list.add(edge.getTarget());
                    for (INode n : list) {
                        for (Pair<IEdge, OrthogonalAngle> pair : this.orthogonal.getAngles(n)) {
                            if (!visited.contains(pair.getFirst())) {
                                edges.push(pair.getFirst());
                                // TODO determine directions
                                direction.push(new Pair<Boolean, Boolean>(false, false));
                            }
                        }
                    }
                }
            }
        }

        return new Pair<IGraph, IGraph>(vertical, horizontal);
    }
}
