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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.Key;

/**
 * A processor which set the final coordinates for each node in a given graph. The property XCOOR
 * has to be set before this processor is called.
 * 
 * @author sor
 * @author sgu
 */
public class CoordianteProcessor implements ILayoutProcessor {

    double spacing = 20f;

    HashMap<Key, TNode> globalMap = new HashMap<Key, TNode>();
    int numberOfNodes;

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor set coordinates", 1);

        spacing = tGraph.getProperty(Properties.SPACING);

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
                KVector pos = tNode.getPosition();
                pos.x = tNode.getProperty(Properties.XCOOR);
                pos.y = 0;
            }
        }

        // start with the root and level down by bsf
        setCoordiantes(root.getChildren(), 1, progressMonitor.subTask(1.0f));

        progressMonitor.done();

    }

    /**
     * Set the coordinate for each node in a given level and all underlying levels.
     * 
     * @param currentLevel
     *            the list of TNode for which the neighbors should be calculated
     * @param level
     *            the level index
     * @param progressMonitor
     *            the current progress monitor
     */
    private void setCoordiantes(final LinkedList<TNode> currentLevel, int level,
            IKielerProgressMonitor progressMonitor) {

        // if the level is empty there is nothing to do
        if (!currentLevel.isEmpty()) {

            LinkedList<TNode> nextLevel = new LinkedList<TNode>();
            // the y coordinate is constant the spacing
            // TODO add implementation to take into account the nodes height 
            double y = spacing * level;
            // TODO remove debug
            System.out.println("level: " + level + " spacing: " + spacing + " y: " + y);

            // set the coordinates for each node in the current level
            // and collect the nodes of the next level
            for (TNode tNode : currentLevel) {
                nextLevel.addAll(tNode.getChildren());
                KVector pos = tNode.getPosition();
                pos.x = tNode.getProperty(Properties.XCOOR);
                pos.y = (int) Math.round(y);
                // TODO remove debug
                System.out.println("Set coordiante x: " + pos.x + " y: " + pos.y);
            }

            // go to the next level
            setCoordiantes(nextLevel, ++level,
                    progressMonitor.subTask(nextLevel.size() / numberOfNodes));
        }

    }
}
