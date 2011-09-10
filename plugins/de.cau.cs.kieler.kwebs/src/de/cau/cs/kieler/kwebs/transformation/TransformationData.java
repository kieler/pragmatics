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
package de.cau.cs.kieler.kwebs.transformation;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * A transformation data object that can be arbitrarily extended using the properties mechanism.
 *
 * @param <T> type of graph that is held by this transformation data
 * @author msp
 */
public class TransformationData<T> extends MapPropertyHolder {

    /** the original graph read from a serialized form. */
    private T sourceGraph;
    /** the layout graphs that are derived from a transformation. */
    private List<KNode> layoutGraphs = new LinkedList<KNode>();
    
    /**
     * Set the original graph read from a serialized form.
     * 
     * @param thesourceGraph the source graph
     */
    public void setSourceGraph(final T thesourceGraph) {
        this.sourceGraph = thesourceGraph;
    }
    
    /**
     * Returns the original graph read from a serialized form.
     * 
     * @return the source graph
     */
    public T getSourceGraph() {
        return sourceGraph;
    }
    
    /**
     * Returns the layout graphs that were derived from a transformation.
     * 
     * @return the layout graphs
     */
    public List<KNode> getLayoutGraphs() {
        return layoutGraphs;
    }

}
