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
package de.cau.cs.kieler.klay.planar.alg.orthogonal;

import java.util.HashMap;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.alg.flownetwork.SuccessiveShortestPathFlowSolver;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.IPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphElement;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * TODO .
 * 
 * @author ocl
 */
public class GiottoCompactor extends AbstractAlgorithm implements ICompactor {

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<IGraphElement> NETWORKTOGRAPH = new Property<IGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private IGraph graph;

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void compact(final IGraph g, final IOrthogonalRepresentation orthogonal) {
        this.graph = g;

        // Decompose faces into rectangles

        // Remove rectangular 'ears' from list

        // TODO

        // Solve flow networks
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
        HashMap<IFace, INode> verticalMap = new HashMap<IFace, INode>();
        HashMap<IFace, INode> horizontalMap = new HashMap<IFace, INode>();

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
        for (IEdge edge : this.graph.getEdges()) {
            IEdge newedge;
            newedge = null; // TODO
            // newedge.setProperty(IFlowNetworkSolver.LOWERBOUND, 1);
            newedge.setProperty(IPathFinder.PATHCOST, 1);
            // TODO capacity, supply/demand
        }

        return new Pair<IGraph, IGraph>(vertical, horizontal);
    }

}
