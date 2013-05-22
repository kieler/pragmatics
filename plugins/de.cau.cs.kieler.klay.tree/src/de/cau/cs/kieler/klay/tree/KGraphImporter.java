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
package de.cau.cs.kieler.klay.tree;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * TODO: Document this class.
 * 
 * TODO: convert applyLayout of force graph to tgraph
 * 
 * @author sor
 * @author sgu
 */
public class KGraphImporter implements IGraphImporter<KNode> {
    
    ///////////////////////////////////////////////////////////////////////////////
    // Transformation KGraph -> TGraph

    /**
     * {@inheritDoc}
     */
    public TGraph importGraph(final KNode kgraph) {
        TGraph TGraph = new TGraph();
        TGraph.setProperty(Properties.ORIGIN, kgraph);
        
        // copy the properties of the KGraph to the force graph
        KShapeLayout sourceShapeLayout = kgraph.getData(KShapeLayout.class);
        TGraph.copyProperties(sourceShapeLayout);
        TGraph.checkProperties(Properties.SPACING, Properties.ASPECT_RATIO);
                
        // keep a list of created nodes in the force graph
        Map<KNode, TNode> elemMap = new HashMap<KNode, TNode>();
        
        // transform everything
        transformNodes(kgraph, TGraph, elemMap);
                
        return TGraph;
    }
    
    /**
     * Transforms the nodes and ports defined by the given layout node.
     * 
     * @param parentNode the layout node whose edges to transform.
     * @param tGraph the force graph.
     * @param elemMap the element map that maps the original {@code KGraph} elements to the
     *                transformed {@code TGraph} elements.
     */
    private void transformNodes(final KNode parentNode, final TGraph tGraph,
            final Map<KNode, TNode> elemMap) {
        int index = 0;
        for (KNode knode : parentNode.getChildren()) {
            // add a new node to the force graph, copying its size
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            
            String label = "";
            if (!knode.getLabels().isEmpty()) {
                label = knode.getLabels().get(0).getText();
            }
            TNode newNode = new TNode(0, tGraph, label);
            newNode.id = index++;
            newNode.setProperty(Properties.ORIGIN, knode);
            newNode.getPosition().x = nodeLayout.getXpos() + nodeLayout.getWidth() / 2;
            newNode.getPosition().y = nodeLayout.getYpos() + nodeLayout.getHeight() / 2;
            newNode.getSize().x = Math.max(nodeLayout.getWidth(), 1);
            newNode.getSize().y = Math.max(nodeLayout.getHeight(), 1);
            tGraph.getNodes().add(newNode);
            
            elemMap.put(knode, newNode);
            
            // port constraints cannot be undefined
            PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            if (portConstraints == PortConstraints.UNDEFINED) {
                portConstraints = PortConstraints.FREE;
            }
            
            // TODO consider ports
            
            // set properties of the new node
            newNode.copyProperties(nodeLayout);
        }
    }
       
    
    ///////////////////////////////////////////////////////////////////////////////
    // Apply Layout Results
    


    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TGraph TGraph) {
        KNode kgraph = (KNode) TGraph.getProperty(Properties.ORIGIN);
        
        // determine the border spacing, which influences the offset
        KShapeLayout parentLayout = kgraph.getData(KShapeLayout.class);
        float borderSpacing = TGraph.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = Properties.SPACING.getDefault();
        }
        TGraph.setProperty(LayoutOptions.BORDER_SPACING, borderSpacing);
        
        // calculate the offset from border spacing and node distribution
        double minXPos = Integer.MAX_VALUE, minYPos = Integer.MAX_VALUE,
                maxXPos = Integer.MIN_VALUE, maxYPos = Integer.MIN_VALUE;
        for (TNode tNode : TGraph.getNodes()) {
            KVector pos = tNode.getPosition();
            KVector size = tNode.getSize();
            minXPos = Math.min(minXPos, pos.x - size.x / 2);
            minYPos = Math.min(minYPos, pos.y - size.y / 2);
            maxXPos = Math.max(maxXPos, pos.x + size.x / 2);
            maxYPos = Math.max(maxYPos, pos.y + size.y / 2);
        }
        KVector offset = new KVector(borderSpacing - minXPos, borderSpacing - minYPos);
        
        // process the nodes
        for (TNode tNode : TGraph.getNodes()) {
            Object object = tNode.getProperty(Properties.ORIGIN);
            
            if (object instanceof KNode) {
                // set the node position
                KNode knode = (KNode) object;
                KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
                KVector nodePos = tNode.getPosition().add(offset);
                nodeLayout.setXpos((float) nodePos.x - nodeLayout.getWidth() / 2);
                nodeLayout.setYpos((float) nodePos.y - nodeLayout.getHeight() / 2);
            }
        }
        
        // set up the parent node
        KInsets insets = parentLayout.getInsets();
        float width = (float) (maxXPos - minXPos) + 2 * borderSpacing
                + insets.getLeft() + insets.getRight();
        float height = (float) (maxYPos - minYPos) + 2 * borderSpacing
                + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(kgraph, width, height, false);
    }
    
}
