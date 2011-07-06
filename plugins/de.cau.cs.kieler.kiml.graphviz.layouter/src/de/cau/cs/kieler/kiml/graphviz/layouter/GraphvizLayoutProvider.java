/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter;

import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.alg.InstancePool;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.KGraphDotTransformation.Command;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Layout provider for the Graphviz layout tool. The actual interface to
 * Graphviz is implemented in {@link GraphvizLayouter}.
 * <p>
 * The actual Graphviz layout that is applied is determined by the parameter
 * passed in the {@link #initialize(String)} method. If no parameter is passed,
 * the Dot layouter is applied as default.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class GraphvizLayoutProvider extends AbstractLayoutProvider {

    /** actual Graphviz layouter used to do the layout. */
    private GraphvizLayouter graphvizLayouter = new GraphvizLayouter();
    /** command passed to the layouter. */
    private Command command = Command.INVALID;
    /** the Graphviz process pool. */
    private InstancePool<GraphvizTool> graphvizPool;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final String parameter) {
        command = Command.valueOf(parameter);
        graphvizPool = new InstancePool<GraphvizTool>(new IFactory<GraphvizTool>() {
            public GraphvizTool create() {
                return new GraphvizTool();
            }
            public void destroy(final GraphvizTool tool) {
                tool.endProcess();
            }
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        if (graphvizPool != null) {
            graphvizPool.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        assert graphvizPool != null;
        GraphvizTool tool = graphvizPool.fetch();
        graphvizLayouter.layout(layoutNode, progressMonitor, command, tool);
        graphvizPool.release(tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsHierarchy(final KNode layoutNode) {
        // add dummy inter-level edges for a better hierarchy support
        // TODO implement proper support for nested graphs
        if (layoutNode.getParent() == null) {
            KimlUtil.addDummyEdgesForInterlevelConnections(layoutNode);
        }
        return false;
    }

}
