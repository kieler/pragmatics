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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public class ArrangeNodes implements ILayoutProcessor {

    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        // TODO implement actual processors

        progressMonitor.begin("Processor arrange node", 1);

        List<TNode> roots = new ArrayList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                roots.add(tNode);
        }
         int maxDepth = 0;
         
         for (TNode tRoot : roots) {
             int depth = tRoot.getProperty(Properties.DEPTH);
             if (maxDepth < depth) {
                 maxDepth = depth;
            }
        }
         
         HashMap<Integer, List<TNode>> hm = new HashMap<Integer, List<TNode>>(); 
         
         ArrayList<TNode> currentLevel = new ArrayList<TNode>();
         ArrayList<TNode> nextLevel = new ArrayList<TNode>();
         
         for (TNode tNode : tGraph.getNodes()) {
             int level = tNode.getProperty(Properties.LEVEL);
            if (level == (maxDepth - 1)) {
                hm.put(tNode.getProperty(Properties.FAN), tNode.getChildren());
            }
        }
        
    }

}
