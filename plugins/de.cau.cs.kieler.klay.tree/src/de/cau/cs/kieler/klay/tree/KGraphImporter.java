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

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * Manages the transformation of KGraphs to TGraphs.
 * 
 * @author sor
 * @author sgu
 */
public class KGraphImporter implements IGraphImporter<KNode> {

    // /////////////////////////////////////////////////////////////////////////////
    // Transformation KGraph -> TGraph

    /**
     * {@inheritDoc}
     */
    public TGraph importGraph(final KNode kgraph) {
        TGraph tGraph = new TGraph();
        tGraph.setProperty(Properties.ORIGIN, kgraph);

        // copy the properties of the KGraph to the t-graph
        KShapeLayout sourceShapeLayout = kgraph.getData(KShapeLayout.class);
        tGraph.copyProperties(sourceShapeLayout);
        tGraph.checkProperties(Properties.SPACING, Properties.ASPECT_RATIO);

        // keep a list of created nodes in the t-graph
        Map<KNode, TNode> elemMap = new HashMap<KNode, TNode>();

        // transform nodes and edges
        transformNodes(kgraph, tGraph, elemMap);
        transformEdges(kgraph, tGraph, elemMap);

        return tGraph;
    }

    /**
     * Transforms the nodes and ports defined by the given layout node.
     * 
     * @param parentNode
     *            the layout node whose edges to transform.
     * @param tGraph
     *            the t-graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code TGraph} elements.
     */
    private void transformNodes(final KNode parentNode, final TGraph tGraph,
            final Map<KNode, TNode> elemMap) {
        int index = 0;

        for (KNode knode : parentNode.getChildren()) {
            // TODO implement or remove the following
            // add a new node to the t-graph, copying its size
            // KShapeLayout tMap.getNodeMap().put(tMap.getNodeMap().size(), value) =
            // knode.getData(KShapeLayout.class);
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);

            // copy label
            String label = "";
            if (!knode.getLabels().isEmpty()) {
                label = knode.getLabels().get(0).getText();
            }

            // create new tNode
            TNode newNode = new TNode(index++, tGraph, label);
            newNode.setProperty(Properties.ORIGIN, knode);
            newNode.getPosition().x = nodeLayout.getXpos() + nodeLayout.getWidth() / 2;
            newNode.getPosition().y = nodeLayout.getYpos() + nodeLayout.getHeight() / 2;
            newNode.getSize().x = Math.max(nodeLayout.getWidth(), 1);
            newNode.getSize().y = Math.max(nodeLayout.getHeight(), 1);
            tGraph.getNodes().add(newNode);

            // keep the corresponding tNode of each kNode for edge transformation
            elemMap.put(knode, newNode);

            // set properties of the new node
            newNode.copyProperties(nodeLayout);
        }
    }

    /**
     * Transforms the edges defined by the given layout node.
     * 
     * @param parentNode
     *            the layout node whose edges to transform.
     * @param tGraph
     *            the t-graph.
     * @param elemMap
     *            the element map that maps the original {@code KGraph} elements to the transformed
     *            {@code TGraph} elements.
     */
    private void transformEdges(final KNode parentNode, final TGraph tGraph,
            final Map<KNode, TNode> elemMap) {
        for (KNode knode : parentNode.getChildren()) {
            for (KEdge kedge : knode.getOutgoingEdges()) {
                // exclude edges that pass hierarchy bounds as well as self-loops
                if (kedge.getTarget().getParent() == parentNode && knode != kedge.getTarget()) {
                    KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);

                    // find the corresponding source and target tNode of edge
                    TNode source = elemMap.get(knode);
                    TNode target = elemMap.get(kedge.getTarget());

                    if (source != null && target != null) {
                        // create a edge and add edge to tGraph
                        TEdge newEdge = new TEdge(source, target);
                        newEdge.setProperty(Properties.ORIGIN, kedge);

                        // TODO transform the edge's labels

                        // set properties of the new edge
                        newEdge.copyProperties(edgeLayout);
                        newEdge.checkProperties(Properties.LABEL_SPACING);

                        // update tNode accordingly
                        source.getOutgoingEdges().add(newEdge);
                        target.getInComingEdges().add(newEdge);

                        tGraph.getEdges().add(newEdge);
                    }
                }
            }
        }
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Apply Layout Results

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TGraph tGraph) {
        // get the corresponding kGraph
        KNode kgraph = (KNode) tGraph.getProperty(Properties.ORIGIN);

        // determine the border spacing, which influences the offset
        KShapeLayout graphLayout = kgraph.getData(KShapeLayout.class);

        // check border spacing and update if necessary
        float borderSpacing = tGraph.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = Properties.SPACING.getDefault();
            tGraph.setProperty(LayoutOptions.BORDER_SPACING, borderSpacing);
        }

        // calculate the offset from border spacing and node distribution
        double minXPos = Integer.MAX_VALUE, minYPos = Integer.MAX_VALUE, maxXPos = Integer.MIN_VALUE, maxYPos = Integer.MIN_VALUE;
        for (TNode tNode : tGraph.getNodes()) {
            KVector pos = tNode.getPosition();
            KVector size = tNode.getSize();
            minXPos = Math.min(minXPos, pos.x - size.x / 2);
            minYPos = Math.min(minYPos, pos.y - size.y / 2);
            maxXPos = Math.max(maxXPos, pos.x + size.x / 2);
            maxYPos = Math.max(maxYPos, pos.y + size.y / 2);
        }
        KVector offset = new KVector(borderSpacing - minXPos, borderSpacing - minYPos);

        // process the nodes
        for (TNode tNode : tGraph.getNodes()) {
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

        // process the edges
        for (TEdge tEdge : tGraph.getEdges()) {
            KEdge kedge = (KEdge) tEdge.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = tEdge.getBendPoints();

            // add the source port and target port positions to the vector chain
            bendPoints.addFirst(tEdge.getSource().getPosition());
            bendPoints.addLast(tEdge.getTarget().getPosition());

            edgeLayout.getBendPoints().clear();
            edgeLayout.applyVectorChain(bendPoints);
        }

        // set up the graph
        KInsets insets = graphLayout.getInsets();
        float width = (float) (maxXPos - minXPos) + 2 * borderSpacing + insets.getLeft()
                + insets.getRight();
        float height = (float) (maxYPos - minYPos) + 2 * borderSpacing + insets.getTop()
                + insets.getBottom();
        //KimlUtil.resizeNode(kgraph, width, height, false, false);
        KimlUtil.resizeNode(kgraph, width, height, false);
    }

}
