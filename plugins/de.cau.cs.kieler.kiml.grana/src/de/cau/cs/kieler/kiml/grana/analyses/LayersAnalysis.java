/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
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

import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.LayoutOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * An analysis for the number of horizontal and vertical layers. Also analyzes 
 * the number of dummy nodes introduced by a layer-based layout, i.e. the number 
 * of edge segments that completely span a layer in the specified direction.
 * Additionally, the maximum number of nodes (with dummies) per layer (for layer-based layout)
 * and the maximum edge density between any pair of layers is determined.
 * Returns six integers.
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

    /** Id of this analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.layers";
    
    // SUPPRESS CHECKSTYLE NEXT 6 Javadoc
    public static final int INDEX_HORIZONTAL = 0;
    public static final int INDEX_VERTICAL = 1;
    public static final int INDEX_DUMMIES = 2;
    public static final int INDEX_MAX_NODES_PER_LAYER = 3;
    public static final int INDEX_MAX_NODES_PER_LAYER_DUMMIES = 4;
    public static final int INDEX_EDGE_DENSITY = 5;
    
    // we store the determined layers in order for other 
    // analyses to access them
    private Map<KNode, List<Layer>> allVerticalLayers = Maps.newHashMap();
    private Map<KNode, List<Layer>> allHorizontalLayers = Maps.newHashMap();
    
    
    /** a utility class to mark the start and end position of a layer. */
    public static final class Layer {
        /** smalles coordinate of any node in the layer. */
        public float start; // SUPPRESS CHECKSTYLE NEXT 6 VisibilityModifier
        /** largest coordinate of any node in the layer. */
        public float end;
        /** number of nodes in this layer. */
        public List<KNode> nodes = Lists.newArrayList(); 
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
            final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Layers Analysis", 1);

        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        int[] count = countLayers(parentNode, hierarchy);

        progressMonitor.done();
        // SUPPRESS CHECKSTYLE NEXT 1 MagicNumber
        return new Object[] { 
                count[INDEX_HORIZONTAL], 
                count[INDEX_VERTICAL], 
                count[INDEX_DUMMIES],
                count[INDEX_MAX_NODES_PER_LAYER],
                count[INDEX_MAX_NODES_PER_LAYER_DUMMIES],
                count[INDEX_EDGE_DENSITY]
        };
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
        
        // store the layer information
        allVerticalLayers.put(parentNode, verticalLayers);
        allHorizontalLayers.put(parentNode, horizontalLayers);
        
        // analyze the number of dummy nodes (only valid for a layer-based layout)
        int dummyCount = 0;
        Direction dir = parentNode.getData(KLayoutData.class).getProperty(LayoutOptions.DIRECTION);
        
        // collect the edges we want to check
        final List<KEdge> edges = Lists.newArrayList();
        for (KNode node : parentNode.getChildren()) {
            edges.addAll(node.getOutgoingEdges());
        }
        // edges of the parent's external ports
        for (KEdge e : parentNode.getOutgoingEdges()) {
            if (KimlUtil.isDescendant(e.getTarget(), parentNode)) {
                edges.add(e);
            }
        }

        
        if (dir == Direction.LEFT || dir == Direction.RIGHT 
                || dir == Direction.UNDEFINED) { // default direction is kindof left-to-right
            for (KEdge e : edges) {
                KEdgeLayout el = e.getData(KEdgeLayout.class);
                // edges may be 'against' the main flow
                float start = Math.min(el.getSourcePoint().getX(), el.getTargetPoint().getX());
                float end = Math.max(el.getSourcePoint().getX(), el.getTargetPoint().getX());
                // cope with inverted ports that might "start" within a layer
                for (KPoint bend : el.getBendPoints()) {
                    start = Math.min(start, bend.getX());
                    end = Math.max(end, bend.getX());
                }

                for (Layer layer : verticalLayers) {
                    if (start < layer.start && end > layer.end) {
                        dummyCount++;
                        layer.dummies++;
                    }
                }
            }
        } else {
            for (KEdge e : edges) {
                KEdgeLayout el = e.getData(KEdgeLayout.class);
                // edges may be 'against' the main flow
                float start = Math.min(el.getSourcePoint().getY(), el.getTargetPoint().getY());
                float end = Math.max(el.getSourcePoint().getY(), el.getTargetPoint().getY());
                // cope with inverted ports that might "start" within a layer
                for (KPoint bend : el.getBendPoints()) {
                    start = Math.min(start, bend.getY());
                    end = Math.max(end, bend.getY());
                }

                for (Layer layer : horizontalLayers) {
                    if (start < layer.start && end > layer.end) {
                        dummyCount++;
                        layer.dummies++;
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
        
        // analyze the edge density between adjacent layers
        // for the i-th and i+1-th layers (in flow direction) the edge 
        // density can be determined by counting the number of 
        // outgoing edges with a target in a layer j > i plus the number of 
        // incoming edges with a source in a layer j' > i plus the number of
        // dummy nodes in layer i
        int maxEdgeDensity = Integer.MIN_VALUE;
        if (dir == Direction.LEFT || dir == Direction.RIGHT
                || dir == Direction.UNDEFINED) { // default direction is kindof left-to-right
            for (Layer l : verticalLayers) {
                int layerOutEdges = l.dummies;
                for (KNode n : l.nodes) {
                    // out edges targeting higher layer
                    for (KEdge e : n.getOutgoingEdges()) {
                        KEdgeLayout el = e.getData(KEdgeLayout.class);
                        if (el.getSourcePoint().getX() < el.getTargetPoint().getX()) {
                            layerOutEdges++;
                        }
                    }
                    // in edges coming from higher layer
                    for (KEdge e : n.getIncomingEdges()) {
                        KEdgeLayout el = e.getData(KEdgeLayout.class);
                        if (el.getSourcePoint().getX() > el.getTargetPoint().getX()) {
                            layerOutEdges++;
                        }
                    }
                }
                maxEdgeDensity = Math.max(maxEdgeDensity, layerOutEdges);
            }
        } else {
            for (Layer l : horizontalLayers) {
                int layerOutEdges = l.dummies;
                for (KNode n : l.nodes) {
                    // out edges targeting higher layer
                    for (KEdge e : n.getOutgoingEdges()) {
                        KEdgeLayout el = e.getData(KEdgeLayout.class);
                        if (el.getSourcePoint().getY() < el.getTargetPoint().getY()) {
                            layerOutEdges++;
                        }
                    }
                    // in edges coming from higher layer
                    for (KEdge e : n.getIncomingEdges()) {
                        KEdgeLayout el = e.getData(KEdgeLayout.class);
                        if (el.getSourcePoint().getY() > el.getTargetPoint().getY()) {
                            layerOutEdges++;
                        }
                    }
                }
                maxEdgeDensity = Math.max(maxEdgeDensity, layerOutEdges);
            }
        }
        
        // count the number of layers in the nested subgraphs
        int[] count =
                new int[] { horizontalLayers.size(), verticalLayers.size(), dummyCount,
                        maxNodesPerLayer, maxNodesPerLayerWDummies, maxEdgeDensity };

        if (hierarchy) {
            for (KNode child : parentNode.getChildren()) {
                if (!child.getChildren().isEmpty()) {
                    int[] childResult = countLayers(child, true);
                    count[INDEX_HORIZONTAL] += childResult[INDEX_HORIZONTAL];
                    count[INDEX_VERTICAL] += childResult[INDEX_VERTICAL];
                    count[INDEX_DUMMIES] += childResult[INDEX_DUMMIES];
                    count[INDEX_MAX_NODES_PER_LAYER] = Math.max(
                            childResult[INDEX_MAX_NODES_PER_LAYER],
                            count[INDEX_MAX_NODES_PER_LAYER]);
                    count[INDEX_MAX_NODES_PER_LAYER_DUMMIES] = Math.max(
                            childResult[INDEX_MAX_NODES_PER_LAYER_DUMMIES],
                            count[INDEX_MAX_NODES_PER_LAYER_DUMMIES]);
                    count[INDEX_EDGE_DENSITY] = Math.max(
                            childResult[INDEX_EDGE_DENSITY],
                            count[INDEX_EDGE_DENSITY]);
                }
            }
        }
        return count;
    }

    
    /**
     * @return the allHorizontalLayers
     */
    public Map<KNode, List<Layer>> getAllHorizontalLayers() {
        return allHorizontalLayers;
    }
    
    /**
     * @return the allVerticalLayers
     */
    public Map<KNode, List<Layer>> getAllVerticalLayers() {
        return allVerticalLayers;
    }
}
