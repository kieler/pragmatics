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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * An analysis for the number of horizontal and vertical layers. Also analyzes 
 * the number of dummy nodes introduced by a layer-based layout, i.e. the number 
 * of edge segments that completely span a layer in the specified direction. 
 * Returns a triple of integers.
 * 
 * Note that measuring maximal nodes per layer does not really make 
 * sense for hierarchical graphs.
 * 
 * @author msp
 * @author uru
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class LayersAnalysis implements IAnalysis {

    /** a utility class to mark the start and end position of a layer. */
    private static final class Layer {
        private float start;
        private float end;
        /** number of nodes in this layer. */
        public List<KNode> nodes = Lists.newArrayList(); // SUPPRESS CHECKSTYLE NEXT 3 VisibilityModifier
        /** number of dummies for edges spanning this layer. */
        public int dummies = 0;

        private Layer(final float thestart, final float theend) {
            this.start = thestart;
            this.end = theend;
        }
    }

    /**
     * Insert a new segment into the given list of layers.
     * 
     * @param layers
     *            a list of layers
     * @param start
     *            the start position of the new segment
     * @param end
     *            the end position of the new segment
     */
    private static void insert(final List<Layer> layers, final float start,
            final float end, final KNode node) {
        Layer insertLayer = null;
        Iterator<Layer> layerIter = layers.iterator();
        while (layerIter.hasNext()) {
            Layer currentLayer = layerIter.next();
            if (start <= currentLayer.end && end >= currentLayer.start) {
                if (insertLayer == null) {
                    // expand the current layer and insert the node into it
                    insertLayer = currentLayer;
                    insertLayer.start = Math.min(insertLayer.start, start);
                    insertLayer.end = Math.max(insertLayer.end, end);
                } else {
                    // merge two partly created layers
                    layerIter.remove();
                    insertLayer.start =
                            Math.min(insertLayer.start, currentLayer.start);
                    insertLayer.end =
                            Math.max(insertLayer.end, currentLayer.end);
                    insertLayer.nodes.addAll(currentLayer.nodes);
                }
            }
        }
        if (insertLayer == null) {
            Layer newLayer = new Layer(start, end);
            layers.add(newLayer);
            insertLayer = newLayer;
        }
        
        insertLayer.nodes.add(node);
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Layers Analysis", 1);

        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        int[] count = countLayers(parentNode, hierarchy);

        progressMonitor.done();
        // SUPPRESS CHECKSTYLE NEXT 1 MagicNumber
        return new Object[] { count[0], count[1], count[2], count[3] , count[4]};
    }
    
    /**
     * Count the number of layers in the given graph and its nested subgraphs.
     * 
     * @param parentNode the parent node
     * @param hierarchy whether to process hierarchy recursively
     * @return the number of horizontal / vertical layers, respectively
     */
    public int[] countLayers(final KNode parentNode, final boolean hierarchy) {
        // analyze horizontal layers
        List<Layer> horizontalLayers = new LinkedList<Layer>();
        for (KNode node : parentNode.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            float start = nodeLayout.getYpos();
            float end = start + nodeLayout.getHeight();
            insert(horizontalLayers, start, end, node);
        }

        // analyze vertical layers
        List<Layer> verticalLayers = new LinkedList<Layer>();
        for (KNode node : parentNode.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            float start = nodeLayout.getXpos();
            float end = start + nodeLayout.getWidth();
            insert(verticalLayers, start, end, node);
        }
        
        // analyze the number of dummy nodes (only valid for a layer-based layout)
        int dummyCount = 0;
        Direction dir = parentNode.getData(KLayoutData.class).getProperty(LayoutOptions.DIRECTION);
        if (dir == Direction.LEFT || dir == Direction.RIGHT 
                || dir == Direction.UNDEFINED) { // default direction is kindof left-to-right
            for (KNode node : parentNode.getChildren()) {
                for (KEdge e : node.getOutgoingEdges()) {
                    KEdgeLayout el = e.getData(KEdgeLayout.class);
                    // edges be 'against' the main flow
                    float start = Math.min(el.getSourcePoint().getX(), el.getTargetPoint().getX());
                    float end = Math.max(el.getSourcePoint().getX(), el.getTargetPoint().getX());

                    for (Layer layer : verticalLayers) {
                        if (start < layer.start && end > layer.end) {
                            dummyCount++;
                            layer.dummies++;
                        }
                    }
                }
            }
        } else {
            for (KNode node : parentNode.getChildren()) {
                for (KEdge e : node.getOutgoingEdges()) {
                    KEdgeLayout el = e.getData(KEdgeLayout.class);
                    // edges be 'against' the main flow
                    float start = Math.min(el.getSourcePoint().getY(), el.getTargetPoint().getY());
                    float end = Math.max(el.getSourcePoint().getY(), el.getTargetPoint().getY());

                    for (Layer layer : horizontalLayers) {
                        if (start < layer.start && end > layer.end) {
                            dummyCount++;
                            layer.dummies++;
                        }
                    }
                }
            }
        }
        
        // analyze maximal number of nodes over all layers (only valid for a layer-based layout)
        int maxNodesPerLayer = Integer.MIN_VALUE;
        int maxNodesPerLayerWDummies = Integer.MIN_VALUE;
        if (dir == Direction.LEFT || dir == Direction.RIGHT 
                || dir == Direction.UNDEFINED) { // default direction is kindof left-to-right
            for (Layer l : verticalLayers) {
                maxNodesPerLayer = Math.max(maxNodesPerLayer, l.nodes.size());
                maxNodesPerLayerWDummies =
                        Math.max(maxNodesPerLayerWDummies, l.nodes.size() + l.dummies);
            }
        } else {
            for (Layer l : horizontalLayers) {
                maxNodesPerLayer = Math.max(maxNodesPerLayer, l.nodes.size());
                maxNodesPerLayerWDummies =
                        Math.max(maxNodesPerLayerWDummies, l.nodes.size() + l.dummies);
            }
        }
        // count the number of layers in the nested subgraphs
        int[] count =
                new int[] { horizontalLayers.size(), verticalLayers.size(), dummyCount,
                        maxNodesPerLayer, maxNodesPerLayerWDummies };
        if (hierarchy) {
            for (KNode child : parentNode.getChildren()) {
                if (!child.getChildren().isEmpty()) {
                    int[] childResult = countLayers(child, true);
                    count[0] += childResult[0];
                    count[1] += childResult[1];
                    count[2] += childResult[2];
                    // 3, 4 do not make sense here
                }
            }
        }
        return count;
    }

}
