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
package de.cau.cs.kieler.klay.planar.p3compact;

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
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.flownetwork.SuccessiveShortestPathFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * A compaction algorithm that minimizes the length of horizontal and vertical edge segments
 * separately. It only works on simple orthogonal representations, i.e. orthogonal representation
 * where every face is represented as a rectangle. General orthogonal representations have to
 * reduced to a simple one prior to performing this algorithm.
 * 
 * @author ocl
 */
public class TidyRectangleCompactor extends AbstractAlgorithm implements ILayoutPhase {

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<PGraphElement> NETWORKTOGRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private PGraph graph;

    /** The orthogonal representation of the graph. */
    private OrthogonalRepresentation orthogonal;

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(PGraph graph) {
        IntermediateProcessingStrategy strategy = new IntermediateProcessingStrategy();
        strategy.addLayoutProcessor(IntermediateProcessingStrategy.AFTER_PHASE_4,
                IntermediateLayoutProcessor.DUMMYNODE_REMOVING_PROCESSOR);
        return strategy;
    }

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

        // Create networks and solve
        Pair<PGraph, PGraph> networks = this.createFlowNetworks();
        IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        solver.findFlow(networks.getFirst());
        solver.findFlow(networks.getSecond());
    }

    /**
     * {@inheritDoc}
     */
    public void compact(final PGraph g, final OrthogonalRepresentation o) {
        this.graph = g;
        this.orthogonal = o;

        // Create networks and solve
        Pair<PGraph, PGraph> networks = this.createFlowNetworks();
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
    private Pair<PGraph, PGraph> createFlowNetworks() {
        IGraphFactory factory = new PGraphFactory();
        PGraph vertical = factory.createEmptyGraph();
        PGraph horizontal = factory.createEmptyGraph();
        Map<PFace, PNode> verticalMap = new HashMap<PFace, PNode>();
        Map<PFace, PNode> horizontalMap = new HashMap<PFace, PNode>();

        // Create nodes for every graph face
        for (PFace face : this.graph.getFaces()) {
            PNode newnode;
            newnode = vertical.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            verticalMap.put(face, newnode);
            newnode = horizontal.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            horizontalMap.put(face, newnode);
        }

        // Create arcs for vertical or horizontal edges
        // Traverse graph with DFS
        Set<PEdge> visited = new HashSet<PEdge>(this.graph.getEdgeCount() * 2);
        Stack<PEdge> edges = new Stack<PEdge>();
        Stack<Pair<Boolean, Boolean>> direction = new Stack<Pair<Boolean, Boolean>>();
        for (PEdge edge : this.graph.getEdges()) {
            if (!visited.contains(edge)) {
                edges.push(edge);
                direction.push(new Pair<Boolean, Boolean>(false, false));
                while (!edges.isEmpty()) {
                    // TODO create arcs
                    // PEdge current = edges.pop();
                    // Pair<Boolean, Boolean> dir = direction.pop();

                    List<PNode> list = new LinkedList<PNode>();
                    list.add(edge.getSource());
                    list.add(edge.getTarget());
                    for (PNode n : list) {
                        for (Pair<PEdge, OrthogonalAngle> pair : this.orthogonal.getAngles(n)) {
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

        return new Pair<PGraph, PGraph>(vertical, horizontal);
    }

}
