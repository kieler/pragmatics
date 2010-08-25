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
package de.cau.cs.kieler.klay.layered.modules;

import java.util.Collection;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Interface for big node splitter modules. Its purpose is to split wide nodes into several smaller
 * nodes, so that layerer can handle them properly.
 * 
 * @author pdo
 */
public interface IBigNodeHandler extends IAlgorithm {

    /**
     * Split wide nodes of the input collection into several smaller nodes. All incoming edges of
     * each original node should be connected to the first resulting node. All outgoing edges should
     * be connected to the last created node.
     * 
     * @param nodes
     *            a {@code Collection} containing all nodes of the graph
     * @param layeredGraph
     *            the layered Graph
     */
    void handleBigNodes(Collection<LNode> nodes, LayeredGraph layeredGraph);

    /**
     * This method removes all edges previously added to the graph to fixate big nodes in their
     * segments. This removal is necessary, since that edges are not part of the original graph, but
     * will reduce the quality of the result of the crossing minimization phase.
     */
    void removeFixationEdges();
}
