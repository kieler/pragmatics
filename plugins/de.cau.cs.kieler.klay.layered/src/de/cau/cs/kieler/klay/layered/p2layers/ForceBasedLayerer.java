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
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A layerer that leverages an force-based layout internally to assign layer 
 * indices to the node.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has no cycles</dd>
 *   <dt>Postcondition:</dt><dd>all nodes have been assigned a layer such that
 *     edges connect only nodes from layers with increasing indices</dd>
 * </dl>
 * 
 * @author uru
 */
public class ForceBasedLayerer implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration BASELINE_PROCESSING_CONFIGURATION =
            new IntermediateProcessingConfiguration(
            // Before Phase 1
                    EnumSet.of(LayoutProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER),

                    // Before Phase 2
                    null,

                    // Before Phase 3
                    EnumSet.of(LayoutProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR),

                    // Before Phase 4
                    null,

                    // Before Phase 5
                    null,

                    // After Phase 5
                    null);

    /** additional processor dependencies for handling big nodes. */
    private static final IntermediateProcessingConfiguration BIG_NODES_PROCESSING_ADDITIONS =
            new IntermediateProcessingConfiguration(
            // Before Phase 1
                    null,

                    // Before Phase 2
                    EnumSet.of(LayoutProcessorStrategy.BIG_NODES_PREPROCESSOR),

                    // Before Phase 3
                    EnumSet.of(LayoutProcessorStrategy.BIG_NODES_INTERMEDIATEPROCESSOR),

                    // Before Phase 4
                    null,

                    // Before Phase 5
                    null,

                    // After Phase 5
                    EnumSet.of(LayoutProcessorStrategy.BIG_NODES_POSTPROCESSOR));
    
    private BiMap<LNode, KNode> nodeMap = HashBiMap.create();
    
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        // Basic strategy
        IntermediateProcessingConfiguration strategy =
                new IntermediateProcessingConfiguration(BASELINE_PROCESSING_CONFIGURATION);

        // Additional dependencies
        if (graph.getProperty(Properties.DISTRIBUTE_NODES)) {
            strategy.addAll(BIG_NODES_PROCESSING_ADDITIONS);
        }
        return strategy;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
       
        System.out.println("Starting Force Based " + layeredGraph);
        nodeMap.clear();
        
        performForceLayout(layeredGraph, progressMonitor);
        
        deriveLayering(layeredGraph);
        
        assureProperness(layeredGraph);
        
        // empty the list of unlayered nodes
        layeredGraph.getLayerlessNodes().clear();
        
    }
        
    
    /**
     * TODO problem: is it ok if this might introduce cycles?
     * 
     * @param layeredGraph
     */
    private void assureProperness(final LGraph layeredGraph) {
        for (Layer l : layeredGraph.getLayers()) {
            int sourceIndex = l.getIndex();
            for (LNode n : l.getNodes()) {
                Iterable<LEdge> cpy = Lists.newLinkedList(n.getOutgoingEdges());
                for (LEdge e : cpy) {
                    if (e.getTarget().getNode().getLayer().getIndex() < sourceIndex) {
                        System.out.println("revert");
                        e.reverse(layeredGraph, true);
                    }
                }
            }
        }
    }

    private void deriveLayering(final LGraph layeredGraph) {
        
        // sort nodes by x position
        List<KNode> positionedNodes = Lists.newLinkedList(nodeMap.values());
        Collections.sort(positionedNodes, new Comparator<KNode>() {
            /**
             * {@inheritDoc}
             */
            public int compare(final KNode node1, final KNode node2) {
                KShapeLayout sl1 = node1.getData(KShapeLayout.class);
                KShapeLayout sl2 = node2.getData(KShapeLayout.class);
                return Float.compare(sl1.getXpos(), sl2.getXpos());
            }
        });
        
//        for (KNode kn : positionedNodes) {
//            KShapeLayout sl1 = kn.getData(KShapeLayout.class);
//            System.out.print(nodeMap.inverse().get(kn) + " (" +sl1.getXpos() + ") " );
//        }
        
        Set<KNode> currentLayerSet = Sets.newHashSet();
        // iterate through the sorted list and assign layers
        Layer currentLayer = new Layer(layeredGraph);
        layeredGraph.getLayers().add(currentLayer);
        for (KNode n : positionedNodes) {
            
            // check that no target is contained in the current layer
            boolean ok = true;
            for (KEdge e : n.getOutgoingEdges()) {
                KNode target = e.getTarget();
                if (currentLayerSet.contains(target)) {
                    ok = false;
                    break;
                }
            }
            for (KEdge e : n.getIncomingEdges()) {
                KNode src = e.getSource();
                if (currentLayerSet.contains(src)) {
                    ok = false;
                    break;
                }
            }
            
            // do we have to start a new layer?
            if (!ok) {
               // start a new layer 
                currentLayerSet.clear();
                currentLayer = new Layer(layeredGraph);
                layeredGraph.getLayers().add(currentLayer);
            }
            
            // add the node to the current layer
            LNode lNode = nodeMap.inverse().get(n);
            lNode.setLayer(currentLayer);
            
            currentLayerSet.add(n);
        }
        
    }
    
    
    private KNode performForceLayout(final LGraph layeredGraph,
            final IKielerProgressMonitor progressMonitor) {

//        String algo = "de.cau.cs.kieler.kiml.ogdf.FruchtermanReingold";
//        String algo = "de.cau.cs.kieler.kiml.ogdf.kamadaKawai";
        String algo = "org.adaptagrams.cola.webcola.constraintLayout";
        
        LayoutAlgorithmData algorithmData =
                DefaultLayoutConfig.getLayouterData(algo, 
                        null);
        if (algorithmData == null) {
            throw new IllegalStateException("No registered layout algorithm is available.");
        }

        // layout algo
        AbstractLayoutProvider layoutProvider = algorithmData.getInstancePool().fetch();
        // aux graph
        KNode auxGraph = createAuxiliaryGraph(layeredGraph);

        layoutProvider.doLayout(auxGraph, progressMonitor);
        
        return auxGraph;

    }
    
    private KNode createAuxiliaryGraph(final LGraph layeredGraph) {

        KNode root = KimlUtil.createInitializedNode();
        KShapeLayout layout = root.getData(KShapeLayout.class);
        layout.setWidth((float) layeredGraph.getSize().x);
        layout.setHeight((float) layeredGraph.getSize().y);
        layout.setProperty(LayoutOptions.ANIMATE, false);
        layout.setProperty(LayoutOptions.ZOOM_TO_FIT, false);
        
        // nodes
        for (LNode n : layeredGraph.getLayerlessNodes()) {
            
            // ignore unconnected nodes
            if (Iterables.isEmpty(n.getOutgoingEdges()) 
                    && Iterables.isEmpty(n.getIncomingEdges())) {
                continue;
            }
            
            // create node and add to auxiliary graph
            KNode child = KimlUtil.createInitializedNode();
            root.getChildren().add(child);
            // set dimension
            KShapeLayout clayout = child.getData(KShapeLayout.class);
            clayout.setWidth((float) n.getSize().x);
            clayout.setHeight((float) n.getSize().y);
            // remember
            nodeMap.put(n, child);
        }

        // edges
        for (LNode n : layeredGraph.getLayerlessNodes()) {
            for (LEdge e : n.getOutgoingEdges()) {
                KEdge auxEdge = KimlUtil.createInitializedEdge();
                
                // undo the cycle breaking
                if (!e.getProperty(Properties.REVERSED)) {
                    auxEdge.setSource(nodeMap.get(n));
                    auxEdge.setTarget(nodeMap.get(e.getTarget().getNode()));
                } else {
                    auxEdge.setTarget(nodeMap.get(n));
                    auxEdge.setSource(nodeMap.get(e.getTarget().getNode()));    
                }
            }
        }

        return root;
    }
}
