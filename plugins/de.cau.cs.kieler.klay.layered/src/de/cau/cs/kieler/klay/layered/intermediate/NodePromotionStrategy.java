/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Definitions of strategies for the node promotion heuristic.
 * 
 * @author amf
 */
public enum NodePromotionStrategy {

    /**
     * Node promotion won't be applied to the graph.
     */
    NONE,
    /**
     * Strategy for promotion proposed by Nikolov et al. in their paper to keep the layering at a
     * width that's at worst as wide as the layering of the used layering alforithm.
     */
    NIKOLOV,
    /**
     * Strategy of Nikolov et al. (maintaining width while promoting nodes) but transferred to
     * approximated sizes of original and dummy nodes in pixels.
     */
    NIKOLOV_PIXEL,
    /**
     * This strategy tries a complete run of the node promotion heuristic without any boundaries and
     * checks afterwards if the maximal width has been exceeded. Only if it is exceeded, the result
     * will be dismissed and another run of the node promotion heuristic is started with the
     * strategy of Nikolov et al. (maintaining width while promoting nodes).
     */
    NIKOLOV_IMPROVED,
    /**
     * Operates like NIKOLOV_IMPROVED but with consideration of approximated sizes of the original
     * and dummy nodes.
     */
    NIKOLOV_IMPROVED_PIXEL,
    /**
     * Stops the promotion either if there are no more promotions to make or a certain percentage
     * (given through {@link Properties#NODE_PROMOTION_BOUNDARY}) of dummy nodes has been reduced
     * while promoting. If the percentage is reached there will be no more promotions made even if
     * there are possible promotions left.
     */
    DUMMYNODE_PERCENTAGE,
    /**
     * Stops the promotion either if there are no more promotions to make or a certain number of
     * promotions were executed. The number is calculated by a percentage of
     * {@link Properties#NODE_PROMOTION_BOUNDARY} of the number of all nodes in the graph. If the
     * number is reached there will be no more promotions made even if there are possible promotions
     * left.
     */
    NODECOUNT_PERCENTAGE,
    /** 
     * The node promotion will run until there are no more promotions left to make. 
     */
    NO_BOUNDARY;
}
