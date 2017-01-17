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

import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kwebs.server.alg.Message;
import de.cau.cs.kieler.kwebs.server.alg.Message.Severity;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.util.Graphs;

/**
 * 
 * @author swe
 */
public class GraphConstraints extends LayoutFilter {

    //////////
    
    /** Default value for maximum number of graphs transmitted in a single request. */
    private static final int MAXNUMBER_GRAPHS = 5;

    /** Default value for maximum number of elements a single graph may contain. */
    private static final int MAXNUMBER_ELEMENTS = 5000;

    /**
     * Value for maximum number of graphs transmitted in a single request
     * initially set to default value.
     */
    private int maxGraphs = Configuration.INSTANCE.getConfigPropertyAsInteger(
        Configuration.MAXNUMBER_GRAPHS, MAXNUMBER_GRAPHS
    );

    /** Whether to test on number of transmitted graphs. */
    private boolean testMaxGraphs = Configuration.INSTANCE.getConfigPropertyAsBoolean(
        Configuration.TESTMAXNUMBER_GRAPHS, true
    );

    /**
     * Value for maximum number of elements a single graph may contain
     * initially set to default value.
     */
    private int maxElements = Configuration.INSTANCE.getConfigPropertyAsInteger(
        Configuration.MAXELEMENTS_GRAPHS, MAXNUMBER_ELEMENTS
    );

    /** Whether to test on number elements contained in the transmitted graphs. */
    private boolean testMaxElements = Configuration.INSTANCE.getConfigPropertyAsBoolean(
        Configuration.TESTMAXELEMENTS_GRAPHS, true
    );

    //////////
    
    /**
     * {@inheritDoc}
     */
    public boolean apply(final LayoutFilterData data, final List<Message> messages) {
        if (testMaxGraphs && data.getGraphs().size() > maxGraphs) {
            messages.add(new Message(
                this, 
                Severity.ERROR, 
                "Too many graphs in request, maximum number is " + maxGraphs
            ));
            return false;
        }
        if (testMaxElements) {
            for (ElkNode layout : data.getGraphs()) {
                if (Graphs.countElements(layout) > maxElements) {
                    messages.add(new Message(
                        this, 
                        Severity.ERROR, 
                        "Too many elements in graph, maximum number is " + maxElements
                    ));
                    return false;
                }
            }
        }
        
        return true;
        
    }

}
