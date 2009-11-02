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
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;

/**
 * Layout provider for the Graphviz layout tool. The actual interface to
 * Graphviz is implemented in {@link GraphvizLayouter}.
 * <p>
 * The actual Graphviz layout that is applied is determined by the parameter
 * passed in the {@link #initialize(String)} method. If no parameter is passed,
 * the Dot layouter is applied as default.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GraphvizLayoutProvider extends AbstractLayoutProvider {

    /** actual Graphviz layouter used to do the layout. */
    private GraphvizLayouter graphvizLayouter = new GraphvizLayouter();;
    /** command string passed to the layouter. */
    private String command = null;

    /**
     * Initializes the Graphviz layout provider with the given parameter string
     * as command.
     * 
     * @param parameter parameter string that is interpreted as Graphviz command
     */
    public void initialize(final String parameter) {
        this.command = parameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        if (command == null) {
            command = GraphvizLayouter.DOT_COMMAND;
        }
        graphvizLayouter.layout(layoutNode, progressMonitor, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (LayoutOptions.LAYOUT_DIRECTION.equals(optionId)) {
            return LayoutDirection.HORIZONTAL;
        } else if (LayoutOptions.MIN_SPACING.equals(optionId)) {
            return GraphvizLayouter.DEF_MIN_SPACING;
        } else if (LayoutOptions.FIXED_SIZE.equals(optionId)) {
            return false;
        } else {
            return null;
        }
    }

}
