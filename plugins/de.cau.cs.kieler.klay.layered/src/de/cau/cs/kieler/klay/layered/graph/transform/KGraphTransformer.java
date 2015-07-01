/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.graph.transform;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * Manages the transformation of KGraphs to LayeredGraphs. Sets the
 * {@link de.cau.cs.kieler.klay.layered.properties.Properties#GRAPH_PROPERTIES GRAPH_PROPERTIES}
 * property on imported graphs.
 * 
 * @author msp
 * @author cds
 * @see KGraphImporter
 * @see KGraphLayoutTransferrer
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating proposed yellow by msp
 */
public class KGraphTransformer implements IGraphTransformer<KNode> {
    
    /**
     * {@inheritDoc}
     */
    public LGraph importGraph(final KNode graph) {
        return new KGraphImporter().importGraph(graph);
    }
    
    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LGraph layeredGraph) {
        new KGraphLayoutTransferrer().applyLayout(layeredGraph);
    }
    
}
