/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * A processor that is able to split an input graph into connected components and to pack those
 * components after layout.
 *
 * @author msp
 */
public class ComponentsProcessor extends AbstractAlgorithm {

    /**
     * Split the given graph into its connected components.
     * 
     * @param graph an input graph with layerless nodes
     * @return a list of components that can be processed one by one
     */
    public List<LayeredGraph> split(final LayeredGraph graph) {
        return Lists.newArrayList(graph);
    }
    
    /**
     * Pack the given components into a single graph.
     * 
     * @param components a list of components
     * @return a single graph that contains all components
     */
    public LayeredGraph pack(final List<LayeredGraph> components) {
        return components.get(0);
    }
    
}
