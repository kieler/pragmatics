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
package de.cau.cs.kieler.klay.layered.p2layers;

import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.ILayoutPhaseFactory;

/**
 * Enumeration of and factory for the different available layering strategies.
 * 
 * @author pdo
 * @author cds
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating yellow 2012-11-13 review KI-33 by grh, akoc
 */
public enum LayeringStrategy implements ILayoutPhaseFactory {

    /**
     * All nodes will be layered with minimal edge length by using the network-simplex-algorithm.
     */
    NETWORK_SIMPLEX,
    /**
     * All nodes will be layered according to the longest path to any sink.
     */
    LONGEST_PATH,
    /**
     * Nodes are put into layers according to their relative position. The actual positions as given
     * in the input diagram are considered here. This means that if the user moves a node, that
     * movement is reflected in the layering of the graph.
     */
    INTERACTIVE,
    /**
     * Nodes are put into Layers according to the average out-degree and their rank.
     * Could be similar to LONGEST_PATH
     * Taken from " In Search for Efficient Heuristics for Minimum-Width Graph
     * Layering with Consideration of Dummy Nodes"
     * wrtten by Nikolas S. Nikolov, Alexandre Tarassov, and Jürgen Branke

     */
    STRETCH_WIDTH,

/** Implementation of the heuristic MinWidth for solving the NP-hard minimum-width layering
     * problem with consideration of dummy nodes.
     */
    MIN_WIDTH;

    /**
     * {@inheritDoc}
     */
    public ILayoutPhase create() {
        switch (this) {
        case NETWORK_SIMPLEX:
            return new NetworkSimplexLayerer();

        case LONGEST_PATH:
            return new LongestPathLayerer();

        case INTERACTIVE:
            return new InteractiveLayerer();


        case STRETCH_WIDTH:
            return new StretchWidthLayerer();
          

        case MIN_WIDTH:
            return new MinWidthLayerer();


        default:
            throw new IllegalArgumentException("No implementation is available for the layerer "
                    + this.toString());
        }
    }

}
