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
package de.cau.cs.kieler.keg.diagram.custom;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.Request;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * A request to update the hypernode structure of the graph.
 *
 * @author msp
 */
public class HypernodesRequest extends Request {

    /** the request type used to update hypernodes. */
    public static final String REQ_UPDATE_HYPERNODES = "apply layout";
    
    /** the root of the layout graph. */
    private KNode rootLayoutNode;
    /** map of hyperedges to the representing hypernodes in the original graph. */
    private Map<Set<KNode>, List<KNode>> hyperedgeMap;
    
    /**
     * Creates a request to apply layout.
     * 
     * @param theRootNode the root of the layout graph
     * @param thehyperedgeMap map of hyperedges to the representing hypernodes in the original graph
     */
    public HypernodesRequest(final KNode theRootNode,
            final Map<Set<KNode>, List<KNode>> thehyperedgeMap) {
        super(REQ_UPDATE_HYPERNODES);
        this.rootLayoutNode = theRootNode;
        this.hyperedgeMap = thehyperedgeMap;
    }
    
    /**
     * Returns the root layout node.
     * 
     * @return the root layout node
     */
    public KNode getRootNode() {
        return rootLayoutNode;
    }
    
    /**
     * Returns the map of hyperedges to the representing hypernodes in the original graph.
     * 
     * @return the hyperedge map
     */
    public Map<Set<KNode>, List<KNode>> getHyperedgeMap() {
        return hyperedgeMap;
    }
    
}
