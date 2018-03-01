/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.provider;

import org.eclipse.elk.alg.common.nodespacing.NodeDimensionCalculation;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.adapters.ElkGraphAdapters;
import org.eclipse.elk.core.util.adapters.ElkGraphAdapters.ElkGraphAdapter;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.adaptagrams.avoid.LibavoidGraph;

/**
 * A layout provider for KIML that performs layout using the Libavoid connector routing library. See
 * http://www.adaptagrams.org/documentation/ for further information on the library.
 * 
 * @author uru
 */
public class SWIGLibavoidLayoutProvider extends AbstractLayoutProvider {

    private static final int SUBTASK_WORK = 1;
    private static final int LAYOUT_WORK = SUBTASK_WORK + SUBTASK_WORK + SUBTASK_WORK
            + SUBTASK_WORK;

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout(final ElkNode parentNode, final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Libavoid Layout", LAYOUT_WORK);
        // if the graph is empty there is no need to layout
        if (parentNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }

        LibavoidGraph graph = new LibavoidGraph(parentNode);

        // calculate node margins
        ElkGraphAdapter adapter = ElkGraphAdapters.adapt(parentNode);

        
        NodeDimensionCalculation.sortPortLists(adapter);
        NodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        NodeDimensionCalculation.calculateNodeMargins(adapter);

        // transform the kgraph to libavoid structures
        graph.transformGraph();

        // run the router
        graph.process();

        progressMonitor.done();
    }

}
