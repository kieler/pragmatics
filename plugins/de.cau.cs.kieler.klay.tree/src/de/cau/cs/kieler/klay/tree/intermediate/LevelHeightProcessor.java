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
package de.cau.cs.kieler.klay.tree.intermediate;

import java.util.Iterator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * A processor which determines the height for each level by setting it to the height of the tallest
 * node of the level.
 * 
 * @author sor
 * @author sgu
 */
public class LevelHeightProcessor implements ILayoutProcessor {

    /** number of nodes in the graph. */
    private int numberOfNodes;

    /**
     * {@inheritDoc}
     */
    public void process(final TGraph tGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor determine the height for each level", 1f);

        /** save number of nodes for progress computation */
        numberOfNodes = tGraph.getNodes().isEmpty() ? 1 : tGraph.getNodes().size();

        TNode root = null;
        /** find the root of the component */
        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }

        /** start with the root and level down by dsf */
        if (root != null) {            
            setNeighbors(root.getChildren(), progressMonitor);
        }

        progressMonitor.done();

    }

    /**
     * Set the height property for each node in the current level and for their children. The height
     * is the height of the tallest node in the level.
     * 
     * @param currentLevel
     *            the list of TNode at the same level, for which the neighbors and siblings should
     *            be determined
     * @param progressMonitor
     *            the current progress monitor
     */
    private void setNeighbors(final Iterable<TNode> currentLevel,
            final IKielerProgressMonitor progressMonitor) {
        /** only do something in filled levels */
        if (!Iterables.isEmpty(currentLevel)) {
            /** create subtask for recursive descent */
            IKielerProgressMonitor sT = progressMonitor.subTask(Iterables.size(currentLevel)
                    / numberOfNodes);

            sT.begin("Set neighbors in level", 1f);

            /** build empty iterator */
            Iterable<TNode> nextLevel = new Iterable<TNode>() {

                public Iterator<TNode> iterator() {
                    return Iterators.emptyIterator();
                }
            };

            double height = 0d;

            for (TNode cN : currentLevel) {
                /** append the children of the current node to the next level */
                nextLevel = Iterables.concat(nextLevel, cN.getChildren());
                /** check if the node is the tallest node so far */
                if (height < cN.getSize().y) {
                    height = cN.getSize().y;
                }
            }
            for (TNode cN : currentLevel) {
                /** set the level height for the node */
                cN.setProperty(Properties.LEVELHEIGHT, height);
            }

            /** add amount of work units to the whole task */
            sT.done();

            /** determine neighbors by bfs and for the whole graph */
            setNeighbors(nextLevel, progressMonitor);
        }

    }
}
