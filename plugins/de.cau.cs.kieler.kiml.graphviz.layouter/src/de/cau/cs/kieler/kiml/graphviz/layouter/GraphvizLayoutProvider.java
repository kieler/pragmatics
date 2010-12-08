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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.KGraphDotTransformation;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutDirection;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
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
    private GraphvizLayouter graphvizLayouter = new GraphvizLayouter();;
    /** command string passed to the layouter. */
    private String command = null;

    /**
     * Initialize default options for the layout provider.
     */
    public GraphvizLayoutProvider() {
        setProperty(LayoutOptions.LAYOUT_DIRECTION, LayoutDirection.RIGHT);
        setProperty(LayoutOptions.BORDER_SPACING,
                KGraphDotTransformation.DEF_MIN_SPACING / 2);
        setProperty(LayoutOptions.OBJ_SPACING,
                KGraphDotTransformation.DEF_MIN_SPACING);
        setProperty(LayoutOptions.FIXED_SIZE, false);
        setProperty(LayoutOptions.RANDOM_SEED, 1);
        setProperty(LayoutOptions.LABEL_SPACING, 1.0f);
        setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
        setProperty(KGraphDotTransformation.LABEL_DISTANCE, 1.0f);
    }

    /**
     * Initializes the Graphviz layout provider with the given parameter string
     * as command.
     * 
     * @param parameter
     *            parameter string that is interpreted as Graphviz command
     */
    public void initialize(final String parameter) {
        this.command = parameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        if (command == null) {
            command = KGraphDotTransformation.DOT_COMMAND;
        }
        graphvizLayouter.layout(layoutNode, progressMonitor, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsHierarchy(final KNode layoutNode) {
        // add dummy inter-level edges for a better hierarchy support
        if (layoutNode.getParent() == null) {
            KimlUtil.addDummyEdgesForInterlevelConnections(layoutNode);
        }
        return false;
    }

}
