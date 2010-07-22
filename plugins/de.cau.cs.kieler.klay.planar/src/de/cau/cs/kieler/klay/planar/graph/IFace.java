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
package de.cau.cs.kieler.klay.planar.graph;

/**
 * A face in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author cku
 * @author ocl
 */
public interface IFace extends IGraphElement {

    /**
     * Get the {@code PNode}s which describe this face. Returns an {@code Iterable} object to gain
     * access to all {@code INode}s that lay on the border of this face.
     * 
     * @return the surrounding nodes
     */
    Iterable<INode> getNodes();

    /**
     * Get the {@code PEgde}s surrounding this edge. Returns at {@code Iterable} object to gain
     * access to all {@code IEdge}s that define the border of this edge.
     * 
     * @return the surrounding edges
     */
    Iterable<IEdge> getEdges();

}
