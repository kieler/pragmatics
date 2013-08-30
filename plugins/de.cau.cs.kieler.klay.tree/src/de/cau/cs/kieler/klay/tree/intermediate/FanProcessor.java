/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.intermediate;

import java.util.Formatter;
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

/**
 * A processor that computes the maximal fan out for each node in the given graph. The maximal fan out of
 * a node is the maximal number of descendants it has got in one level.
 * 
 * @author sor
 * @author sgu
 */
public class FanProcessor implements ILayoutProcessor {

    /** this map temporarily contains the maximal fan of each node in the given graph. */
    private Map<String, Integer> gloFanMap = new HashMap<String, Integer>();

    /** this map temporarily contains the number of descendants of each node in the given graph. */
    private Map<String, Integer> gloDescMap = new HashMap<String, Integer>();

    /**
     * {@inheritDoc}
     */
    public void process(final TGraph tGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor compute fanout", 1);

        /** clear map if processor is reused */
        gloFanMap.clear();
        gloDescMap.clear();

        TNode root = null;
        /** find the root of the component */
        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }

        LinkedList<TNode> rootLevel = new LinkedList<TNode>();
        rootLevel.add(root);

        calculateFan(rootLevel);

        /** set the fan and descendants for all nodes */
        for (TNode tNode : tGraph.getNodes()) {
            String key = tNode.getProperty(Properties.ID);
            int fan = gloFanMap.get(key) != null ? gloFanMap.get(key) : 0;
            tNode.setProperty(Properties.FAN, fan);
            int desc = 1 + (gloDescMap.get(key) != null ? gloDescMap.get(key) : 0);
            tNode.setProperty(Properties.DESCENDANTS, desc);
        }

        progressMonitor.done();
    }

    /**
     * Calculates a fan for the nodes in the list currentLevel including their descendants.
     * 
     * @param currentLevel
     *            the list of TNode for which the fan should be calculated.
     */
    private void calculateFan(final LinkedList<TNode> currentLevel) {
        if (!currentLevel.isEmpty()) {

            /** the children of the current level are the next level */
            LinkedList<TNode> nextLevel = new LinkedList<TNode>();

            /** currentLevel is not empty so stringId will be set */
            String id = null;
            String pId = null;

            /** the size of the which the stringId will be extended for this level */
            int digits = (int) (Math.floor(Math.log10(currentLevel.size())) + 1);

            /**
             * set final stringId for all nodes in this level and set the provisional stringId for
             * their children
             */
            int index = 0;
            for (TNode tNode : currentLevel) {
                /** the final stringId is the stringId of the parent and the extension */
                if (pId != tNode.getProperty(Properties.ID)) {
                    pId = tNode.getProperty(Properties.ID);
                    index = 0;
                }
                if (pId != null) {
                    id = pId + formatRight(index++, digits);
                } else {
                    id = formatRight(index++, digits);
                }
                tNode.setProperty(Properties.ID, id);
                for (TNode tChild : tNode.getChildren()) {
                    nextLevel.add(tChild);
                    /** the provisional stringId is the Id of the parent */
                    tChild.setProperty(Properties.ID, id);
                }
            }

            /** holds the occurences of descendants in this level */
            Map<String, Integer> locFanMap = new HashMap<String, Integer>();

            /** calculated occurences of descendants in this level */
            for (int i = 0; i < id.length() - digits; i++) {
                for (TNode tNode : currentLevel) {
                    String key = tNode.getProperty(Properties.ID).substring(0, i + 1);
                    int blockSize = locFanMap.get(key) != null ? locFanMap.get(key) + 1 : 1;
                    locFanMap.put(key, blockSize);
                }
            }

            /** update the gloFanMap with the values from locFanMap will only increase the values */
            for (Entry<String, Integer> locEntry : locFanMap.entrySet()) {
                Integer gloValue = gloDescMap.get(locEntry.getKey()) != null ? gloDescMap
                        .get(locEntry.getKey()) : 0;
                gloDescMap.put(locEntry.getKey(), locEntry.getValue() + gloValue);
                gloValue = gloFanMap.get(locEntry.getKey());
                if (gloValue == null || gloValue < locEntry.getValue()) {
                    gloFanMap.put(locEntry.getKey(), locEntry.getValue());
                }
            }

            /** calculate the occurrences in the deeper levels and add them to the global gloFanMap */
            calculateFan(nextLevel);
        }
    }
    
    /**
     * Create a string representation of the given value, padded with 0s.
     * 
     * @param value a value
     * @param len the min. number of characters
     * @return a string representation
     */
    public static String formatRight(final int value, final int len) {
        Formatter formatter = new Formatter();
        String result = formatter.format("%1$" + len + "d", value).toString();
        formatter.close();
        return result;
    }
    
}
