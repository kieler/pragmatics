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
 * Interface for layerer modules.
 *
 * @author msp
 */
public interface ILayerer extends IAlgorithm {

    /**
     * Create a set of layers and assign a layer to each of the given nodes.
     * 
     * @param nodes a collection of nodes
     * @param layeredGraph an initially empty layered graph which is filled with layers
     */
    void layer(Collection<LNode> nodes, LayeredGraph layeredGraph);
    
}
