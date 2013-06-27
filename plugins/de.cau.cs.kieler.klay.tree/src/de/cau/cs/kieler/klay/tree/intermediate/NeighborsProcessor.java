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
import java.util.LinkedList;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.Key;

/**
 * A processor which determine the local neighbors for all nodes in the graph. A local neighbor is a
 * node in the same level and which got the same parent and is located to the right or left of the
 * other node.
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

        progressMonitor.begin("Processor set neighbours", 1);

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

        // start with the root and level down by dsf
        setNeighbours(root.getChildrenCopy(), progressMonitor.subTask(1.0f));

        progressMonitor.done();

    }

    /**
     * Set the neighbors of each node in the current level and for their children.
     * 
     * @param currentLevel
     *            the list of TNode for which the neighbors should be calculated
     * @param progressMonitor
     */
    private void setNeighbours(final LinkedList<TNode> currentLevel,
            IKielerProgressMonitor progressMonitor) {

        if (!currentLevel.isEmpty()) {

            LinkedList<TNode> nextLevel = new LinkedList<TNode>();

            TNode lN = null;

            // the left neighbor is the previous processed node
            // the right neighbor of the left neighbor is the current node
            for (TNode tNode : currentLevel) {
                nextLevel = tNode.getChildrenCopy();
                // determine neighbors by dfs and only in subtrees
                setNeighbours(nextLevel, progressMonitor.subTask(nextLevel.size() / numberOfNodes));
                if (lN != null) {
                    lN.setRightNeighbour(tNode);
                }
                tNode.setLeftNeighbour(lN);
                lN = tNode;
            }
        }

    }
}
