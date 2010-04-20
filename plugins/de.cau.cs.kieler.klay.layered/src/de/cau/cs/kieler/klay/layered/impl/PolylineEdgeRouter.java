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
package de.cau.cs.kieler.klay.layered.impl;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;

/**
 * Edge router module that draws edges with non-orthogonal line segments.
 *
 * @author msp
 */
public class PolylineEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {
    
    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;

    /**
     * {@inheritDoc}
     */
    public void setSpacing(final float theSpacing) {
        this.spacing = theSpacing;
    }
    
    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        // set horizontal positioning for each layer
        float xpos = 0.0f;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPos().x = xpos;
            }
            xpos += layer.getSize().x + spacing;
        }
    }

}
