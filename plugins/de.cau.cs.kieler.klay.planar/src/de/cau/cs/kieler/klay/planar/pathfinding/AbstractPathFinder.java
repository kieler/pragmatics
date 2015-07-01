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
package de.cau.cs.kieler.klay.planar.pathfinding;

import java.util.List;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * Algorithm to find a path between two nodes in a graph.
 * 
 * @author ocl
 * @kieler.rating proposed yellow by pkl
 */
public abstract class AbstractPathFinder implements IPathFinder {

    /**
     * {@inheritDoc}
     */
    public List<PEdge> findPath(final PNode source, final PNode target) {
        return this.findPath(source, target, new Predicate<Pair<PNode, PEdge>>() {
            public boolean apply(final Pair<PNode, PEdge> object) {
                PNode node = object.getFirst();
                PEdge edge = object.getSecond();
                return !edge.isDirected() || (node == edge.getTarget());
            }
        });
    }

}
