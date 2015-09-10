/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.service.filter;

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.service.Statistics;

/**
 * 
 * @author swe
 */
public class LayoutFilterData {

    //////////
    
    /** */
    private List<KNode> graphs;

    /** */
    private List<GraphLayoutOption> options;
    
    /** */
    private Statistics statistics;
    
    //////////
    
    /**
     * 
     * @param graphs
     * @param options
     */
    public LayoutFilterData(final List<KNode> graphs, final List<GraphLayoutOption> options) {
        this.graphs = graphs;
        this.options = options;
    }
    
    //////////

    /**
     * 
     * @return
     */
    public List<KNode> getGraphs() {
        return graphs;
    }

    /**
     * 
     * @param graphs
     */
    public void setGraphs(final List<KNode> graphs) {
        this.graphs = graphs;
    }

    /**
     * 
     * @return
     */
    public List<GraphLayoutOption> getOptions() {
        return options;
    }

    /**
     * 
     * @param options
     */
    public void setOptions(final List<GraphLayoutOption> options) {
        this.options = options;
    }

    /**
     * 
     * @return
     */
    public Statistics getStatistics() {
        return statistics;
    }
    
    /**
     * 
     * @param statistics
     */
    public void setStatistics(final Statistics statistics) {
        this.statistics = statistics;
    }
    
}
