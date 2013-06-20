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
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public class NeighbourProcessor implements ILayoutProcessor {

    HashMap<Key, TNode> globalMap = new HashMap<Key, TNode>();
    int numberOfNodes;

    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor set neighbours", 1);
        
        TNode root = null;
        
        globalMap.clear();

        numberOfNodes = tGraph.getNodes().isEmpty() ? 1 : tGraph.getNodes().size();

        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }

        setNeighbours(root.getChildren(), progressMonitor.subTask(1.0f));
        
        progressMonitor.done();

    }

    private void setNeighbours(final LinkedList<TNode> currentLevel,
            IKielerProgressMonitor progressMonitor) {

        if (!currentLevel.isEmpty()) {

            LinkedList<TNode> nextLevel = new LinkedList<TNode>();

            TNode lN = null;

            for (TNode tNode : currentLevel) {
                nextLevel.addAll(tNode.getChildren());
                if (lN != null) {
                    lN.setRightNeighbour(tNode);
                }
                tNode.setLeftNeighbour(lN);
                lN = tNode;
            }

            setNeighbours(nextLevel, progressMonitor.subTask(nextLevel.size() / numberOfNodes));
        }

    }
}
