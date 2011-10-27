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
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Multimap;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * Detects and resolves violated constraints.
 * 
 * @author msp
 * @author cds
 * @author ima
 */

public interface IConstraintResolver {

    /**
     * Finds and handles violated constraints.
     * 
     * @param nodeGroups
     *            the array of single-node vertices sorted by their barycenter values.
     * @param layerIndex
     *            the layer index.
     * @param random
     *            the random number generator.
     * @param singleNodeNodeGroups
     *            a map of single-node NodeGroups for each layer.
     * @param layoutUnits
     *            a map associating layout units with their respective members.
     */
    void processConstraints(final List<NodeGroup> nodeGroups, final int layerIndex,
            final Random random, final Map<LNode, NodeGroup>[] singleNodeNodeGroups,
            final Multimap<LNode, LNode> layoutUnits);

}
