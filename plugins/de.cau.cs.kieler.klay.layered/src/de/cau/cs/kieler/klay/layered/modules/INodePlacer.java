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

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

//CHECKSTYLEOFF JavadocStyle

/**
 * Interface for node placement modules.
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     optimized nodes ordering</dd>
 *   <dt>Postcondition:</dt><dd>each node is assigned a vertical coordinate
 *     such that no two nodes overlap; the ports of each node are arranged
 *     according to their order; the size of each layer is set according to
 *     the area occupied by contained nodes; the height of the graph is set
 *     to the maximal layer height</dd>
 * </dl>
 *
 * @author msp
 */
public interface INodePlacer extends IAlgorithm {
    
    /**
     * Determine placement and size for all nodes in the given layered graph.
     * 
     * @param layeredGraph a layered graph
     */
    void placeNodes(LayeredGraph layeredGraph);
    
    /**
     * Set the node spacing for placement.
     * 
     * @param spacing the spacing value
     */
    void setSpacing(float spacing);

}
