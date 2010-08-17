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
package de.cau.cs.kieler.graphs.diagram.custom;

import java.util.LinkedList;
import java.util.List;

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
    
    /** list of old hypernodes in the graph. */
    private List<KNode> oldHypernodes = new LinkedList<KNode>();
    /** the root of the layout graph. */
    private KNode rootLayoutNode;
    
    /**
     * Creates a request to apply layout.
     * 
     * @param theRootNode the root of the layout graph
     */
    public HypernodesRequest(final KNode theRootNode) {
        super(REQ_UPDATE_HYPERNODES);
        this.rootLayoutNode = theRootNode;
    }
    
    /**
     * Returns the list of old hypernodes in the graph, which is initially empty.
     * 
     * @return the list of old hypernodes
     */
    public List<KNode> getOldHypernodes() {
        return oldHypernodes;
    }
    
    /**
     * Returns the root layout node.
     * 
     * @return the root layout node
     */
    public KNode getRootNode() {
        return rootLayoutNode;
    }
    
}
