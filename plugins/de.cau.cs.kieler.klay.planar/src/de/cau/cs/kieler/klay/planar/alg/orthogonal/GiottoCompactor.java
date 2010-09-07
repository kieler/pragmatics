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
import de.cau.cs.kieler.klay.planar.alg.orthogonal.IOrthogonalizer.IOrthogonalRepresentation;
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

        // TODO Decompose faces into rectangles
        // TODO Create flow network for vertical metrics
        // TODO Create flow network for horizontal metrics
        // TODO Solve flow networks
        // TODO Assign coordinates based on flow

        // TODO Auto-generated method stub
    }

    /**
     * Create the flow network base upon the graph.
     * 
     * @return the flow network
     */
    private IGraph createFlowNetwork() {
        IGraphFactory factory = new PGraphFactory();
        IGraph network = factory.createEmptyGraph();
        HashMap<IGraphElement, INode> map = new HashMap<IGraphElement, INode>();

        // Create a node for every graph face
        for (IFace face : this.graph.getFaces()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            map.put(face, newnode);
        }

        return network;
    }

}
