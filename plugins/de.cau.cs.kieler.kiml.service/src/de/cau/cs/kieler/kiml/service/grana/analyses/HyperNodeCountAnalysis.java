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

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A graph analysis that counts the number of hyper nodes. Returns a single-component
 * result {@code (int hyperNodes)}.
 * 
 * @author cds
 */
public class HyperNodeCountAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Hyper Nodes Analysis", 1);
        
        int hyperNodes = 0;
        
        // Iterate through the graph using a queue of nodes discovered
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        
        while (nodeQueue.size() > 0) {
            KNode node = nodeQueue.remove(0);
            
            // Check if this is a hyper node
            KShapeLayout layoutData = node.getData(KShapeLayout.class);
            if (layoutData != null) {
                Boolean hyperHyper = layoutData.getProperty(LayoutOptions.HYPERNODE);
                if (hyperHyper != null && hyperHyper.booleanValue()) {
                    hyperNodes++;
                }
            }
            
            nodeQueue.addAll(node.getChildren());
        }
        
        progressMonitor.done();
        return hyperNodes;
    }
}
