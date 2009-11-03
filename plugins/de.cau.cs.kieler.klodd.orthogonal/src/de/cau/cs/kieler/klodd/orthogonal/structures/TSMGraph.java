/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.structures;

import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;

/**
 * The graph structure used internally for the topology-shape-metrics approach.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class TSMGraph extends KSlimGraph {

    /**
     * Applies all layout information to the contained layout graph objects.
     * 
     * @param offsetX offset to add on X axis
     * @param offsetY offset to add on Y axis
     */
    public void applyLayout(final float offsetX, final float offsetY) {

        // apply node layout
        for (KSlimNode node : nodes) {
            ((TSMNode) node).applyLayout(offsetX, offsetY);
        }

        // apply edge layout
        for (KSlimEdge edge : edges) {
            edge.rank = 0;
        }
        for (KSlimEdge edge : edges) {
            if (edge.rank == 0) {
                ((TSMEdge) edge).applyLayout(offsetX, offsetY);
            }
        }
    }

}
