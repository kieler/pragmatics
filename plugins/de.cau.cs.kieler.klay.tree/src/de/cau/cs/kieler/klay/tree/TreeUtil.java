/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree;

import java.util.Iterator;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klay.tree.graph.TNode;

/**
 * Utility class for KLay Tree.
 * 
 * @author sgu
 */
public final class TreeUtil {

    /**
     * Hidden constructor to avoid instantiation.
     */
    private TreeUtil() {
    }
    
    /**
     * This method returns the leftmost node at the deepest level. This is implemented using a
     * postorder walk of the subtree under given level.
     * 
     * @param currentlevel
     *            a list of nodes at level one
     * @return the leftmost node at the deepest level.
     */
    public static TNode getLeftMost(final Iterable<TNode> currentlevel) {
        return getLeftMost(currentlevel, -1);
    }

    /**
     * This method returns the leftmost node at the given level. This is implemented using a
     * postorder walk of the subtree under given level, depth levels down. Depth here refers to the
     * level below where the leftmost descendant is being found.
     * 
     * If given level is negative it returns the leftmost node at the deepest level.
     * 
     * @param currentlevel
     *            a list of nodes at level one
     * @param depth
     *            the depth to search for
     * @return the leftmost descendant at depth levels down
     */
    public static TNode getLeftMost(final Iterable<TNode> currentlevel, final int depth) {
        if (0 < Iterables.size(currentlevel)) {
            int d = depth;

            // the leftmost descendant at depth levels down
            if (1 < d) {
                d--;
                // build empty iterator
                Iterable<TNode> nextLevel = new Iterable<TNode>() {

                    public Iterator<TNode> iterator() {
                        return Iterators.emptyIterator();
                    }
                };

                for (TNode cN : currentlevel) {
                    // append the children of the current node to the next level
                    nextLevel = Iterables.concat(nextLevel, cN.getChildren());
                }
                return getLeftMost(nextLevel, d);
            }

            // the leftmost node at the deepest level
            if (d < 0) {
                // build empty iterator
                Iterable<TNode> nextLevel = new Iterable<TNode>() {

                    public Iterator<TNode> iterator() {
                        return Iterators.emptyIterator();
                    }
                };

                for (TNode cN : currentlevel) {
                    // append the children of the current node to the next level
                    nextLevel = Iterables.concat(nextLevel, cN.getChildren());
                }

                //
                if (0 < Iterables.size(nextLevel)) {
                    return getLeftMost(nextLevel, d);
                }
            }
        }
        // return the leftmost node at the current level
        return Iterables.getFirst(currentlevel, null);
    }

}
