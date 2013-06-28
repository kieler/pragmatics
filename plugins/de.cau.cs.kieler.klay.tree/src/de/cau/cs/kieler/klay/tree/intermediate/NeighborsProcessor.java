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
package de.cau.cs.kieler.klay.tree.intermediate;

import java.util.HashMap;
import java.util.Iterator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.Key;

/**
 * A processor which determine the neighbors and siblings for all nodes in the graph. A neighbor is
 * the current node's nearest node, at the same level. A siblings is a neighbor with the same
 * parent.
 * 
 * @author sor
 * @author sgu
 */
public class NeighborsProcessor implements ILayoutProcessor {

    HashMap<Key, TNode> globalMap = new HashMap<Key, TNode>();
    int numberOfNodes;

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor set neighbors", 1f);

        TNode root = null;
        // clear map if processor is reused
        globalMap.clear();

        // save number of nodes for progress computation
        numberOfNodes = tGraph.getNodes().isEmpty() ? 1 : tGraph.getNodes().size();

        // find the root of the component
        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }

        // TODO assert root is not null

        // start with the root and level down by dsf
        setNeighbors(root.getChildren(), progressMonitor);

        progressMonitor.done();

    }

    /**
     * Set the neighbors of each node in the current level and for their children. A neighbor is the
     * current node's nearest node, at the same level. A siblings is a neighbor with the same
     * parent.
     * 
     * @param currentlevel
     *            the list of TNode at the same level, for which the neighbors and siblings should
     *            be determined
     * @param progressMonitor
     */
    private void setNeighbors(final Iterable<TNode> currentlevel,
            IKielerProgressMonitor progressMonitor) {

        // only do something in filled levels
        if (!Iterables.isEmpty(currentlevel)) {
            // create subtask for recursive descent
            IKielerProgressMonitor sT = progressMonitor.subTask(Iterables.size(currentlevel)
                    / numberOfNodes);

            sT.begin("Set neighbors in level", 1f);

            // build empty iterator
            Iterable<TNode> nextLevel = new Iterable<TNode>() {

                public Iterator<TNode> iterator() {
                    return Iterators.emptyIterator();
                }
            };

            TNode lN = null;

            // the left neighbor is the previous processed node
            // the right neighbor of the left neighbor is the current node
            for (TNode cN : currentlevel) {
                // append the children of the current node to the next level
                nextLevel = Iterables.concat(nextLevel, cN.getChildren());
                if (lN != null) {
                    lN.setProperty(Properties.RIGHTNEIGHBOR, cN);
                    cN.setProperty(Properties.LEFTNEIGHBOR, lN);
                    if (cN.getParent() == lN.getParent()) {
                        lN.setProperty(Properties.RIGHTSIBLING, cN);
                        cN.setProperty(Properties.LEFTSIBLING, lN);
                    }
                }

                lN = cN;
            }

            // add amount of work units to the whole task
            sT.done();

            // determine neighbors by bfs and for the whole graph
            setNeighbors(nextLevel, progressMonitor);
        }

    }
}
