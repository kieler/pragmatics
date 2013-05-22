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
package de.cau.cs.kieler.klay.tree.graph;

import java.util.HashMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public class TGraphBuilder {
    
    /**
     * Create graph instance that is empty
     * @return empty TGraph
     */
    public static TGraph createEmptyGraph() {
        return new TGraph();
    }
    
    /**
     * Create a graph from a {@code KGraph} instance.
     * 
     * @param kgraph
     *            a {@code KNode} to base the graph upon
     * @return a graph corresponding to {@code KGraph}
     */
    public static TGraph createTGraphFromKGraph(final KNode kgraph) {
        int idCount = 0;
        TGraph tgraph = new TGraph();
        HashMap<KNode, TNode> map = new HashMap<KNode, TNode>(kgraph.getChildren().size() * 2);
        tgraph.setProperty(Properties.ORIGIN, kgraph);
        tgraph.copyProperties(kgraph.getData(KShapeLayout.class));
        
        for (KNode knode : kgraph.getChildren()) { 
            String label = "";
            if (knode.getLabels().isEmpty()) {
                label = knode.getLabels().get(0).getText();
            }
            TNode tnode = null;
            idCount = idCount++;
            tnode.setProperty(Properties.ORIGIN, kgraph);
            tnode = tgraph.addNode(tnode, label, idCount);
            map.put(knode, tnode);
        }
        for (KNode knode : kgraph.getChildren()) {
            for (KEdge edge : knode.getOutgoingEdges()) {
                TNode source = map.get(edge.getSource());
                TNode target = map.get(edge.getTarget());
                TEdge tedge = null;
                tedge = tgraph.addEdge(source, target);
                // TEdge tedge = new TEdge(source, target);
                tedge.setProperty(Properties.ORIGIN, kgraph);
                for (KLabel klabel : edge.getLabels()) {
                    TLabel newLabel = new TLabel(tedge, klabel.getText());
                    newLabel.setProperty(Properties.ORIGIN, klabel);
                    tgraph.getLabels().add(newLabel);
                }
            }
        }
        return tgraph;
    }

}
