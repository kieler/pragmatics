/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.config;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.test.config.BasicLayoutConfigurator;

/**
 * Basic configurator that uses {@link EdgeRouting#ORTHOGONAL}.
 * 
 * @author uru
 * 
 */
public class OrthogonalEdgeRoutingLayoutConfigurator extends BasicLayoutConfigurator {

    /**
     * Configure orthogonal edge routing.
     */
    public OrthogonalEdgeRoutingLayoutConfigurator() {
        super("");
    }

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(final KNode root) {
        root.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_ROUTING,
                EdgeRouting.ORTHOGONAL);
    }

}
