/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
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
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;

/**
 * StretchWidth algorithm described in In Search for Efficient Heuristics for Minimum-Width Graph
 * Layering with Consideration of Dummy Nodes written by Nikolas S. Nikolov, Alexandre Tarassov, and
 * Jürgen Branke 
 * 
 * Precondition: the graph has no cycles, but might contain self-loops
 * Postcondition: all nodes have been assigned a layer such that edges 
 * connect only nodes from layers with increasing indices
 * 
 * 
 * @author mad
 */
public class StretchWidthLayerer implements ILayoutPhase {
    // Indicates the width of the currently built layer
    private double widthCurrent = 0;
    // Estimated width of the next layer
    private int widthUp = 0;
    // Indicates the width of the layering
    // Will be increased dynamically increased if the layering fails for lower values
    // The Pseudo codes says initialize with 0 but I think you can start with 1 or maybe
    // higher/average
    // out-degree
    // I choose, later, the broadest normalized node as the algorithm fails until it can be placed
    private double maxWidth = 0;
    // The graph the layering is done for
    private LGraph currentGraph;
    // Factor which determines the bounding of the incoming edges with widthUp and maxWidth
    private double upperLayerInfluence;
    // Sorted list of layerless nodes
    private List<LNode> sortedLayerlessNodes;
    // Set of nodes placed in the current layer
    private Set<LNode> alreadyPlacedNodes = Sets.newHashSet();
    // Set of nodes in all layers except the current
    private Set<LNode> alreadyPlacedInOtherLayers = Sets.newHashSet();
    // List of layerless nodes to be used in one layering approach, will be sorted after
    // initialization
    private List<LNode> tempLayerlessNodes;
    // List of sets of successors for every node
    // can be accessed with successors.get(node.id)
    private List<Set<LNode>> successors;
    // Array of out-degrees for every node
    // access with outDegree[node.id]
    private int[] outDegree;
    // Array of in-degrees for every nodeminWidthNP
    // access with inDegree[node.id]
    private int[] inDegree;
    // Selected node to be placed
    private LNode selectedNode;
    // minimum node size to normalize the other sizes
    private double minimumNodeSize;
    // maximum node size to normalize the other sizes
    private double maximumNodeSize;
    //normalized Size of every node
    // access with normSize[node.id]
    private double[] normSize;
    

    /**
     * {@inheritDoc}
     */

    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return IntermediateProcessingConfiguration
                .createEmpty()
                .addBeforePhase1(
                        IntermediateProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER)
                .addBeforePhase3(IntermediateProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR);
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("StretchWidth", 1);

        // set graph
        currentGraph = layeredGraph;
        // reset variables
        widthCurrent = 0;
        widthUp = 0;
      
          
       
        // Sort the nodes at beginning, since the rank will not change
        // It has to be computed first
        computeSortedNodes();
        // Compute a list with the successors of every node
        // Can't be swapped with the computation of the out- and in-degrees
        computeSuccessors();
        // Compute two arrays of out- and in-degree for every node
        computeDegrees();
        //compute minimum node size
        minMaxNodeSize();
        // compute normalized size of every node
        computeNormalizedSize();
        
        maxWidth = maximumNodeSize / minimumNodeSize;
        
        // To combine dummy nodes and real node size, you could also add the average normalized node size
        // to this criteria and adjust the condition go up at widthUp
        upperLayerInfluence = getAverageOutDegree();
        
        // Layer currently worked on
        Layer currentLayer = new Layer(currentGraph);
        
        // add first layer to the graph 
        currentGraph.getLayers().add(currentLayer);
        
        // Copy the sorted layerless nodes so we don't overwrite it in the reset case
        tempLayerlessNodes = Lists.newArrayList(sortedLayerlessNodes);
        // Variable to compute the difference of u and z

        while (!tempLayerlessNodes.isEmpty()) {
            // Select a node to be placed
            selectedNode = selectNode();
            // The pseudo-code computes u\z but since u /alreadyPlacedNodes will be cleared here
            // It's enough to check if alreadyPlacedNodes is empty
            // if it is empty it would indicate an empty layer
            if (selectedNode == null || (conditionGoUp() && !alreadyPlacedNodes.isEmpty())) {
                // go to the next layer //
                currentLayer = new Layer(currentGraph);
                currentGraph.getLayers().add(currentLayer);
                // union of z and u in z
                alreadyPlacedInOtherLayers.addAll(alreadyPlacedNodes);
                alreadyPlacedNodes.clear();
                // change width
                widthCurrent = widthUp;
                widthUp = 0;
            } else {
                if (conditionGoUp()) {
                    // reset layering //
                    // clear the placed layers
                    currentGraph.getLayers().clear();
                    // create the new first layer;
                    currentLayer = new Layer(currentGraph);
                    currentGraph.getLayers().add(currentLayer);
                    // reset variables
                    widthCurrent = 0;
                    widthUp = 0;
                    alreadyPlacedNodes.clear();
                    alreadyPlacedInOtherLayers.clear();
                    // increase maxWidth
                    maxWidth++;
                    // reset layerless nodes
                    tempLayerlessNodes = Lists.newArrayList(sortedLayerlessNodes);

                } else {
                    // add node to current layer //
                    selectedNode.setLayer(currentLayer);
                    tempLayerlessNodes.remove(selectedNode);
                    alreadyPlacedNodes.add(selectedNode);
                    // compute new widthCurrent and widthUp
                    widthCurrent = widthCurrent - outDegree[selectedNode.id] + normSize[selectedNode.id];
                    widthUp = widthUp + inDegree[selectedNode.id];
                }
            }
        }
     

     // Layering done, delete original layerlessNodes
        layeredGraph.getLayerlessNodes().clear();
        // Algorithm is Bottom-Up -> reverse Layers
        Collections.reverse(layeredGraph.getLayers());
        
        progressMonitor.done();
    }

    
    /**
     * Checks the effects of the hypothetical
     * placement of the selected node and if the algorithm should
     * rather go up, then placing the node.
     *     
     * @return true, if the algorithm should go to the next layer
     */
    private Boolean conditionGoUp() {
               return ((widthCurrent - outDegree[selectedNode.id] + normSize[selectedNode.id]) 
                       > maxWidth 
                    || ((widthUp + inDegree[selectedNode.id]) > (maxWidth * upperLayerInfluence)));  
          }


    /**
     * Selects a node from the sorted list of layerless nodes. The selection is done according to
     * the rank of the node and only if all of its successors are already in z (the set of layered
     * nodes already placed in the other layers).
     * 
     * @return node to be placed in the current layer
     */
    private LNode selectNode() {
        for (LNode node : tempLayerlessNodes) {
            // If all successors of node are in the alreadyPlacedInOtherLayers set, choose this
            // node, since the list is sorted by rank
            if (alreadyPlacedInOtherLayers.containsAll(successors.get(node.id))) {
                return node;
            }

        }
        // If no candidate was found return null
        return null;
    }

    /**
     * Sorts a list of nodes after its rank. The rank is defined as max(d⁺(v),max(d⁺(u):(u,v)∈ E)).
     * Since the id-field is reused later on, this function should be executed first
     */
    private void computeSortedNodes() {
        List<LNode> unsortedNodes = currentGraph.getLayerlessNodes();
        sortedLayerlessNodes = Lists.newArrayList(unsortedNodes);
        // the id-field is reused later on, be careful
        for (LNode node : unsortedNodes) {
            node.id = getRank(node);
        }
        // sorting the list by the node-id /rank
        Collections.sort(sortedLayerlessNodes, new Comparator<LNode>() {
            public int compare(final LNode o1, final LNode o2) {
                // descending sort
                if (o1.id < o2.id) {
                    return 1;
                } else if (o1.id > o2.id) {
                    return -1;
                }
                return 0;
            }
        });

    }

    /**
     * Computes the rank of a node. The rank is defined as max(d⁺(v),max(d⁺(u):(u,v)∈ E)).
     * 
     * @param node to compute the rank for
     * @return rank of the node
     */
    private Integer getRank(final LNode node) {
        int max = getOutDegree(node);
        int temp;
        LNode pre;

        // compute max of predecessors out-degree and out-degree of the current node
        for (LEdge preEdge : node.getIncomingEdges()) {
            pre = preEdge.getSource().getNode();
            temp = getOutDegree(pre);
            max = Math.max(max, temp);
        }

        return max;
    }

    /**
     * Computes the successors of every node and saves them in a list of sets of nodes. This list is
     * used in selectNode. Needs to be executed after computeSortedNodes.
     */
    private void computeSuccessors() {
        int i = 0;
        successors = Lists.newArrayList();
        Set<LNode> currSucc = Sets.newHashSet();

        for (LNode node : sortedLayerlessNodes) {
            node.id = i;
            for (LEdge edge : node.getOutgoingEdges()) {
                currSucc.add(edge.getTarget().getNode());
            }
            // remove current node from accSucc to deal with self-loops
            currSucc.remove(node);
            successors.add(Sets.newHashSet(currSucc));
            currSucc.clear();
            i++;

        }

    }

    
    
    /**
     * Computes the in- and out-degree of every node. the values are used to determine widthUp and
     * widthCurrent. Needs to be executed after computeSuccessors()
     */
    private void computeDegrees() {
        inDegree = new int[sortedLayerlessNodes.size()];
        outDegree = new int[sortedLayerlessNodes.size()];

        for (LNode node : sortedLayerlessNodes) {
            inDegree[node.id] = getInDegree(node);
            outDegree[node.id] = getOutDegree(node);
        }

    }
    
    
    /**
     * Computes the minimum and the maximum node size.
     * Needs to be computed after computeSuccessors().
     */
    private void minMaxNodeSize() {
                
        Direction dir = currentGraph.getProperty(LayoutOptions.DIRECTION);
        double size;
        
        if (dir == Direction.DOWN || dir == Direction.UP) {
            minimumNodeSize = sortedLayerlessNodes.get(0).getSize().x;
            maximumNodeSize = sortedLayerlessNodes.get(0).getSize().x;
            for (LNode node : sortedLayerlessNodes) {
                size = node.getSize().x;
                if (minimumNodeSize > size) {
                    minimumNodeSize = size;
                }
                if(maximumNodeSize < size){
                    maximumNodeSize = size;
                }
            }           
        } else {
            minimumNodeSize = sortedLayerlessNodes.get(0).getSize().y;
            for (LNode node : sortedLayerlessNodes) {
                size = node.getSize().y;
                if (minimumNodeSize > size) {
                    minimumNodeSize = size;
                }
                if(maximumNodeSize < size){
                    maximumNodeSize = size;
                }
            }
        }
    }
    
    
    /**
     * Computes the average normalized node size. 
     * @return average normalized node size
     */
 @SuppressWarnings("unused")
private double avereageNodeSize() {        
     double sum = 0;
     for (LNode node : sortedLayerlessNodes) {
        sum = sum + normSize[node.id];
     }
     return sum / sortedLayerlessNodes.size();
    }
    
    /**
     * Computes the size of every node according to the layering direction 
     * and normalizes it by the minimum node size.
     * Needs to be computed after computeSuccessors() and minimumNodeSize().
     */
    private void computeNormalizedSize() {
        
        //choose which size is the width
        
        Direction dir = currentGraph.getProperty(LayoutOptions.DIRECTION);
        normSize = new double[sortedLayerlessNodes.size()];
        if (dir == Direction.DOWN || dir == Direction.UP) {
            for (LNode node : sortedLayerlessNodes) {
                normSize[node.id] = node.getSize().x / minimumNodeSize;
            }           
        } else {
            for (LNode node : sortedLayerlessNodes) {
                normSize[node.id] = node.getSize().y / minimumNodeSize;
            }
            
        }
        
    }
    
    

    /**
     * Computes the out-degree of a node.
     * 
     * @param node to compute the out-degree for
     * @return out-degree of the node
     */
    private Integer getOutDegree(final LNode node) {
        int i = 0;
        for (@SuppressWarnings("unused")
        LEdge edge : node.getOutgoingEdges()) {
            i++;
        }
        return i;
    }

    /**
     * Computes the in-degree of a node.
     * 
     * @param node to compute the in-degree for
     * @return in-degree of the node
     */
    private Integer getInDegree(final LNode node) {
        int i = 0;
        for (@SuppressWarnings("unused")
        LEdge edge : node.getIncomingEdges()) {
            i++;
        }
        return i;
    }

    /**
     * Computes the average out-degree of the graph should be computed before changing the
     * layerlessNodes list.
     * 
     * @return average out-degree of the Graph
     */
    private float getAverageOutDegree() {
        float allOut = 0;
        for (LNode node : currentGraph.getLayerlessNodes()) {
            allOut += getOutDegree(node);

        }

        return allOut / currentGraph.getLayerlessNodes().size();
    }
}
