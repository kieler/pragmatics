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
import java.util.Map;
import java.util.Map.Entry;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.FillStrings;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public class FanProcessor implements ILayoutProcessor {

    Map<String, Integer> gloFanMap = new HashMap<String, Integer>();

    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        // set the fan for every node
        // TODO multiple parents of a node

        TNode root = null;
        
        gloFanMap.clear();

        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }
        root.setProperty(Properties.ID, "0");

        // mark the roots for fan calculation
        for (TNode tChild : root.getChildren()) {
            tChild.setProperty(Properties.ID, "0");
            // check if the ID was set already by another relation
            if (tChild.getProperty(Properties.ID) != null) {
                tChild.setProperty(Properties.MULTI, true);
            }
            // TODO add implementation for multiple inheritance
            // the provisional stringId is the Id of the parent
            tChild.setProperty(Properties.ID, "0");
        }

        calculateFan(root.getChildren());

        // set the fan for all nodes
        for (TNode tNode : tGraph.getNodes()) {
            String key = tNode.getProperty(Properties.ID);
            int count = gloFanMap.get(key) != null ? gloFanMap.get(key) : 1;
            tNode.setProperty(Properties.FAN, count);
        }

    }

    /**
     * Calculates a fan for the nodes in the list currentLevel including their descendants.
     * 
     * @param currentLevel
     *            the list of TNode for which the fan should be calculated.
     */
    private void calculateFan(final LinkedList<TNode> currentLevel) {
        if (!currentLevel.isEmpty()) {

            // the children of the current level are the next level
            LinkedList<TNode> nextLevel = new LinkedList<TNode>();

            // currentLevel is not empty so stringId will be set
            String id = null;
            String pId = null;

            // the size of the which the stringId will be extended for this level
            int digits = (int) (Math.floor(Math.log10(currentLevel.size() - 1)) + 1);

            // set final stringId for all nodes in this level
            // and set the provisional stringId for their children
            int index = 0;
            for (TNode tNode : currentLevel) {
                // the final stringId is the stringId of the parent and the extension
                if (pId != tNode.getProperty(Properties.ID)) {
                    pId = tNode.getProperty(Properties.ID);
                    index = 0;
                }
                id = pId + FillStrings.formatRight(String.valueOf(index++), digits);
                tNode.setProperty(Properties.ID, id);
                for (TNode tChild : tNode.getChildren()) {
                    nextLevel.add(tChild);
                    // check if the ID was set already by another relation
                    if (tChild.getProperty(Properties.ID) != null) {
                        tChild.setProperty(Properties.MULTI, true);
                    }
                    // TODO add implementation for multiple inheritance
                    // the provisional stringId is the Id of the parent
                    tChild.setProperty(Properties.ID, id);
                }
            }

            // holds the occurences of descendants in this level
            Map<String, Integer> locFanMap = new HashMap<String, Integer>();

            // calculated occurences of descendants in this level
            for (int i = 0; i < id.length() - digits; i++) {
                for (TNode tNode : currentLevel) {
                    String key = tNode.getProperty(Properties.ID).substring(0, i);
                    int blockSize = locFanMap.get(key) != null ? locFanMap.get(key) + 1 : 1;
                    locFanMap.put(key, blockSize);
                }
            }

            // update the gloFanMap with the values from locFanMap
            // will only increase the values
            for (Entry<String, Integer> locEntry : locFanMap.entrySet()) {
                Integer gloValue = gloFanMap.get(locEntry.getKey());
                if ((gloValue == null) || (gloValue < locEntry.getValue())) {
                    gloFanMap.put(locEntry.getKey(), locEntry.getValue());
                }
            }

            // calculated the occurences in the deeper levels and add them to the global gloFanMap
            calculateFan(nextLevel);

        }
    }

}
