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
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;

/**
 * Crossing minimization module that performs one or more sweeps over the layers
 * while applying a two-layer crossing minimization heuristic on each pair of layers.
 * Inspired by
 * <ul>
 *   <li>Michael Forster. A Fast and Simple Heuristic for Constrained Two-Level
 *     Crossing Reduction. In <i>Graph Drawing</i>, volume 3383 of LNCS,
 *     pp. 206-216. Springer, 2005.</li>
 * </ul>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering, i.e. all
 *     long edges have been splitted; all nodes have at least fixed port
 *     sides.</dd>
 *   <dt>Postcondition:</dt><dd>the order of nodes in each layer and the order
 *     of ports in each node are optimized to yield as few edge crossings
 *     as possible</dd>
 * </dl>
 *
 * @author msp
 */
public class LayerSweepCrossingMinimizer extends AbstractAlgorithm implements ILayoutPhase {
    
    /** intermediate processing strategy. */
    private static final IntermediateProcessingStrategy INTERMEDIATE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(
                // Before Phase 1
                null,
                // Before Phase 2
                null,
                // Before Phase 3
                EnumSet.of(
                        IntermediateLayoutProcessor.LONG_EDGE_SPLITTER,
                        IntermediateLayoutProcessor.PORT_SIDE_AND_ORDER_PROCESSOR),
                // Before Phase 4
                null,
                // Before Phase 5
                null,
                // After Phase 5
                EnumSet.of(IntermediateLayoutProcessor.LONG_EDGE_JOINER));
    
    /** barycenter values for ports. */
    private float[] portBarycenter;
    /** port position array. */
    private float[] portPos;
    /** barycenter values for nodes. */
    private float[] nodeBarycenter;
    /** the random number generator. */
    private Random random;
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy() {
        return INTERMEDIATE_PROCESSING_STRATEGY;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer sweep crossing minimization", 1);
        
        // Find the number of layers. If there's only one, no crossing minimization
        // is necessary.
        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            getMonitor().done();
            return;
        }
        
        // Remember the best, current and previous sweep; they basically save the node oder
        // per layer for the different sweeps of the algorithm
        LNode[][] bestSweep = new LNode[layerCount][];
        LNode[][] curSweep = new LNode[layerCount][];
        LNode[][] prevSweep = new LNode[layerCount][];
        int bestSweepCrossings = Integer.MAX_VALUE;
        int curSweepCrossings = Integer.MAX_VALUE;
        int prevSweepCrossings = Integer.MAX_VALUE;
        
        int nodeCount = 0;
        int portCount = 0;

        // Iterate through the layers, initializing port and node IDs and collecting
        // the nodes into the current sweep
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            
            int layerIndex = layerIter.previousIndex();
            int layerNodeCount = layer.getNodes().size();
            
            // Initialize this layer's node arrays in the different sweeps
            bestSweep[layerIndex] = new LNode[layerNodeCount];
            prevSweep[layerIndex] = new LNode[layerNodeCount];
            curSweep[layerIndex] = new LNode[layerNodeCount];
            
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();
                
                curSweep[layerIndex][nodeIter.previousIndex()] = node;
                node.id = nodeCount++;
                
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                }
            }
        }
        
        // Initialize the position and barycenter arrays
        portBarycenter = new float[portCount];
        portPos = new float[portCount];
        nodeBarycenter = new float[nodeCount];
        
        // Fetch the graph's randomizer and determine the requested number of runs
        random = layeredGraph.getProperty(Properties.RANDOM);
        int runCount = layeredGraph.getProperty(Properties.THOROUGHNESS);
        
        // Perform the requested number of runs, each consisting of several sweeps
        // (alternating between forward and backward sweeps)
        for (int run = 0; run < runCount; run++) {
            // Each run is randomly determined to be a forward or a backward run
            boolean forward = random.nextBoolean();
            LNode[] fixedLayer = forward ? curSweep[0] : curSweep[layerCount - 1];
            
            // The fixed layer is randomized
            randomizeLayer(fixedLayer);
            assignPortPos(fixedLayer);
            
            // Reset last and current run crossing counters
            prevSweepCrossings = Integer.MAX_VALUE;
            curSweepCrossings = Integer.MAX_VALUE;
            
            // Do the forward and backward sweeps until the number of crossings
            // increases with respect to the previous sweep
            boolean firstSweep = true;
            do {
                // The formerly current sweep is now the previous sweep
                copySweep(curSweep, prevSweep);
                prevSweepCrossings = curSweepCrossings;
                curSweepCrossings = 0;
                
                if (forward) {
                    // Perform a forward sweep
                    for (int layerIndex = 1; layerIndex < layerCount; layerIndex++) {
                        LNode[] freeLayer = curSweep[layerIndex];
                        curSweepCrossings += minimizeCrossings(
                                fixedLayer, freeLayer, true, !firstSweep);
                        fixedLayer = freeLayer;
                    }
                } else {
                    // Perform a backward sweep
                    for (int layerIndex = layerCount - 2; layerIndex >= 0; layerIndex--) {
                        LNode[] freeLayer = curSweep[layerIndex];
                        curSweepCrossings += minimizeCrossings(
                                fixedLayer, freeLayer, false, !firstSweep);
                        fixedLayer = freeLayer;
                    }
                }
                
                // Switch the sweep direction
                firstSweep = false;
                forward = !forward;
            } while (curSweepCrossings < prevSweepCrossings);
            
            // Compare the current result with the best one
            if (curSweepCrossings < bestSweepCrossings || prevSweepCrossings < bestSweepCrossings) {
                // Restore the previous sweep's ordering if it has become worse
                if (curSweepCrossings < prevSweepCrossings) {
                    copySweep(curSweep, bestSweep);
                    bestSweepCrossings = curSweepCrossings;
                } else {
                    copySweep(prevSweep, bestSweep);
                    bestSweepCrossings = prevSweepCrossings;
                }
            }
        }
        
        // Distribute the ports of all nodes with free port constraints
        distributePorts(bestSweep);
        
        // Apply the ordering to the original layered graph
        layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = bestSweep[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()]);
            }
        }
        
        dispose();
        getMonitor().done();
    }
    
    /**
     * Release all created resources so the GC can reap them.
     */
    private void dispose() {
        this.nodeBarycenter = null;
        this.portBarycenter = null;
        this.portPos = null;
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Port Position Assignment
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately.
     * 
     * @param layer a layer
     */
    private void assignPortPos(final LNode[] layer) {
        int nodeIx = 0;
        for (LNode node : layer) {
            // count the input and output ports
            int inputPorts = 0, outputPorts = 0;
            for (LPort port : node.getPorts()) {
                switch (port.getType()) {
                case INPUT:
                    inputPorts++;
                    break;
                case OUTPUT:
                    outputPorts++;
                    break;
                }
            }
            // set positions for the input ports
            if (inputPorts > 0) {
                assignPortPos(node, nodeIx, PortType.INPUT, inputPorts);
            }
            // set positions for the output ports
            if (outputPorts > 0) {
                assignPortPos(node, nodeIx, PortType.OUTPUT, outputPorts);
            }
            nodeIx++;
        }
    }
    
    /**
     * Assign port positions for the input or output ports of the given node.
     * 
     * @param node a node
     * @param nodeIx the node's index in its layer
     * @param type the type of ports to process
     * @param count the number of ports of that type
     */
    private void assignPortPos(final LNode node, final int nodeIx,
            final PortType type, final int count) {
        if (node.getProperty(Properties.PORT_CONS).isOrderFixed()) {
            float incr = 1.0f / count;
            
            if (type == PortType.INPUT) {
                // Start at the top right port, going counter-clockwise
                List<LPort> portList = getSortedInputPorts(node);
                float pos = nodeIx + 1 - incr;
                
                for (LPort port : portList) {
                    portPos[port.id] = pos;
                    pos -= incr;
                }
                
            } else {
                // Start at the top left port, going clockwise
                float pos = nodeIx;
                
                for (LPort port : node.getPorts(type)) {
                    portPos[port.id] = pos;
                    pos += incr;
                }
            }
        } else {
            for (LPort port : node.getPorts(type)) {
                portPos[port.id] = nodeIx + getPortIncr(type, port.getSide());
            }
        }
    }

    /**
     * Returns a list of input ports, beginning at the top right port of the eastern
     * side, going clockwise.
     * 
     * @param node the node whose input ports to return.
     * @return list of input ports.
     */
    private List<LPort> getSortedInputPorts(final LNode node) {
        List<LPort> northPorts = new LinkedList<LPort>();
        List<LPort> portList = new LinkedList<LPort>();
        
        for (LPort port : node.getPorts(PortType.INPUT)) {
            if (port.getSide() == PortSide.NORTH) {
                northPorts.add(port);
            } else {
                portList.add(port);
            }
        }
        
        // Append the list of northern ports to the other ports
        portList.addAll(northPorts);
        return portList;
    }
    
    private static final float INCR_ONE = 0.3f;
    private static final float INCR_TWO = 0.5f;
    private static final float INCR_THREE = 0.7f;
    private static final float INCR_FOUR = 0.9f;
    
    /**
     * Return an increment value for the position of a port with given type and side.
     * 
     * @param type the port type
     * @param side the port side
     * @return a position increment for the port
     */
    private static float getPortIncr(final PortType type, final PortSide side) {
        switch (type) {
        case INPUT:
            switch (side) {
            case NORTH:
                return INCR_ONE;
            case WEST:
                return INCR_TWO;
            case SOUTH:
                return INCR_THREE;
            case EAST:
                return INCR_FOUR;
            }
            break;
        case OUTPUT:
            switch (side) {
            case NORTH:
                return INCR_ONE;
            case EAST:
                return INCR_TWO;
            case SOUTH:
                return INCR_THREE;
            case WEST:
                return INCR_FOUR;
            }
            break;
        }
        return 0;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Crossing Minimization

    /**
     * Minimize the number of crossings for the edges between the two given layers.
     * Currently the barycenter heuristic is used for this.
     * 
     * @param fixedLayer the fixed layer
     * @param freeLayer the free layer whose nodes are reordered
     * @param forward whether the free layer is after the fixed layer
     * @param preOrdered whether the nodes have been ordered in a previous run
     * @return the new number of crossings between the two layers
     */
    private int minimizeCrossings(final LNode[] fixedLayer, final LNode[] freeLayer,
            final boolean forward, final boolean preOrdered) {
        
        int totalEdges = 0;
        for (LNode node : freeLayer) {
            nodeBarycenter[node.id] = -1;
            float nodeSum = 0;
            int edgeCount = 0;
            for (LPort freePort : node.getPorts(forward ? PortType.INPUT : PortType.OUTPUT)) {
                for (LPort fixedPort : freePort.getConnectedPorts()) {
                    nodeSum += portPos[fixedPort.id];
                }
                edgeCount += freePort.getEdges().size();
            }
            if (edgeCount > 0) {
                nodeBarycenter[node.id] = nodeSum / edgeCount;
                totalEdges += edgeCount;
            }
        }
        sortNodes(freeLayer, preOrdered);
        assignPortPos(freeLayer);
        LNode[] leftLayer = forward ? fixedLayer : freeLayer;
        LNode[] rightLayer = forward ? freeLayer : fixedLayer;
        return countCrossings(leftLayer, rightLayer, totalEdges);
    }
    
    /**
     * Sort the nodes of the given layer. A heuristic tries to find appropriate position values
     * for nodes whose position value is < 0.
     * 
     * @param layer layer whose nodes shall be sorted
     * @param preOrdered whether the nodes have been ordered in a previous run
     */
    private void sortNodes(final LNode[] layer, final boolean preOrdered) {
        // determine placements for nodes with undefined position value
        if (preOrdered) {
            float lastValue = -1;
            for (int i = 0; i < layer.length; i++) {
                int nodeid = layer[i].id;
                float value = nodeBarycenter[nodeid];
                if (value < 0) {
                    float nextValue = lastValue + 1;
                    for (int j = i + 1; j < layer.length; j++) {
                        float x = nodeBarycenter[layer[j].id];
                        if (x >= 0) {
                            nextValue = x;
                            break;
                        }
                    }
                    value = (lastValue + nextValue) / 2;
                    nodeBarycenter[nodeid] = value;
                }
                lastValue = value;
            }
        } else {
            // no previous ordering - determine random placement for new nodes
            float maxBary = 0;
            for (LNode node : layer) {
                maxBary = Math.max(maxBary, nodeBarycenter[node.id]);
            }
            maxBary += 2;
            for (LNode node : layer) {
                if (nodeBarycenter[node.id] < 0) {
                    nodeBarycenter[node.id] = random.nextFloat() * maxBary - 1;
                }
            }
        }

        // sort all nodes by the filtered ranks
        Arrays.sort(layer, new Comparator<LNode>() {
            public int compare(final LNode node1, final LNode node2) {
                return Float.compare(nodeBarycenter[node1.id], nodeBarycenter[node2.id]);
            }
        });
    }
    
    /**
     * Calculate the number of crossings between the two given layers.
     * Taken from W. Barth , M. Juenger, P. Mutzel: <em>Simple and efficient
     * bilayer cross counting</em>, LNCS 2528, pp. 331-360, 2002.
     * 
     * @param leftLayer the left layer
     * @param rightLayer the right layer
     * @param edgeCount the total number of edges in the layer
     * @return the number of edge crossings
     */
    private int countCrossings(final LNode[] leftLayer, final LNode[] rightLayer,
            final int edgeCount) {
        
        // assign index values to the ports of the right layer
        int targetCount = 0;
        Map<LPort, Integer> targetMap = new HashMap<LPort, Integer>();
        for (LNode node : rightLayer) {
            if (node.getProperty(Properties.PORT_CONS).isOrderFixed()) {
                List<LPort> inputPorts = getSortedInputPorts(node);
                ListIterator<LPort> portIter = inputPorts.listIterator(inputPorts.size());
                while (portIter.hasPrevious()) {
                    targetMap.put(portIter.previous(), targetCount++);
                }
            } else {
                for (LPort port : node.getPorts(PortType.INPUT)) {
                    targetMap.put(port, targetCount);
                }
                targetCount++;
            }
        }
        
        // determine the sequence of edge target positions sorted by source and target index
        int[] southSequence = new int[edgeCount];
        int i = 0;
        for (LNode node : leftLayer) {
            if (node.getProperty(Properties.PORT_CONS).isOrderFixed()) {
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    int start = i;
                    for (LEdge edge : port.getEdges()) {
                        int pos = targetMap.get(edge.getTarget());
                        insert(southSequence, start, i++, pos);
                    }
                }
            } else {
                int start = i;
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        int pos = targetMap.get(edge.getTarget());
                        insert(southSequence, start, i++, pos);
                    }
                }
            }
        }
        
        // build the accumulator tree
        int firstIndex = 1;
        while (firstIndex < targetCount) {
            firstIndex *= 2;
        }
        int treeSize = 2 * firstIndex - 1;
        firstIndex -= 1;
        int[] tree = new int[treeSize];
        
        // count the crossings
        int crossCount = 0;
        for (int k = 0; k < edgeCount; k++) {
            int index = southSequence[k] + firstIndex;
            tree[index]++;
            while (index > 0) {
                if (index % 2 > 0) {
                    crossCount += tree[index + 1];
                }
                index = (index - 1) / 2;
                tree[index]++;
            }
        }
        return crossCount;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Port Distribution
    
    /**
     * Distribute the ports of the layered graph depending on the port constraints.
     * 
     * @param layeredGraph a layered graph
     */
    private void distributePorts(final LNode[][] layeredGraph) {
        for (int l = 0; l < layeredGraph.length; l++) {
            if (l + 1 < layeredGraph.length) {
                assignPortPos(layeredGraph[l + 1]);
            }
            LNode[] layer = layeredGraph[l];
            for (int i = 0; i < layer.length; i++) {
                LNode node = layer[i];
                if (!node.getProperty(Properties.PORT_CONS).isOrderFixed()) {
                    // the order of ports on each side is variable, so distribute the ports
                    distributePorts(node);
                    node.setProperty(Properties.PORT_CONS, PortConstraints.FIXED_ORDER);
                    int portCount = 0;
                    for (LPort port : node.getPorts()) {
                        if (port.getType() == PortType.OUTPUT) {
                            portCount++;
                        }
                    }
                    if (portCount > 0) {
                        assignPortPos(node, i, PortType.OUTPUT, portCount);
                    }
                }
            }
        }
    }
    
    /**
     * Distribute the ports of the given node by their sides, connected ports, and input
     * or output type.
     * 
     * @param node node whose ports shall be sorted
     */
    private void distributePorts(final LNode node) {
        // calculate barycenter values for the ports of the node
        for (LPort port : node.getPorts()) {
            float sum = 0;
            for (LPort connectedPort : port.getConnectedPorts()) {
                sum += portPos[connectedPort.id];
            }
            if (port.getEdges().isEmpty()) {
                portBarycenter[port.id] = -1;
            } else {
                portBarycenter[port.id] = sum / port.getEdges().size();
            }
        }
        // sort the ports by considering the side, type, and barycenter values
        node.sortPorts(portBarycenter);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Utility Methods
    
    /**
     * Randomize the order of nodes for the given layer.
     * 
     * @param layer a layer
     */
    private void randomizeLayer(final LNode[] layer) {
        for (int i = 0; i < layer.length; i++) {
            int j = random.nextInt(layer.length);
            if (i != j) {
                LNode temp = layer[j];
                layer[j] = layer[i];
                layer[i] = temp;
            }
        }
    }

    /**
     * Copy the content of the source node array to the target node array.
     * 
     * @param source a layered graph
     * @param dest a node array to copy the graph into
     */
    private static void copySweep(final LNode[][] source, final LNode[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] = source[i][j];
            }
        }
    }
    
    /**
     * Insert a number into a sorted range of an array.
     * 
     * @param array an integer array
     * @param start the start index of the search range (inclusive)
     * @param end the end index of the search range (exclusive)
     * @param n the number to insert
     */
    private static void insert(final int[] array, final int start, final int end, final int n) {
        int insx = binarySearch(array, start, end, n);
        if (insx < 0) {
            insx = -insx - 1;
        }
        for (int j = end - 1; j >= insx; j--) {
            array[j + 1] = array[j];
        }
        array[insx] = n;
    }
    
    /**
     * Searches a range of the specified array of ints for the specified value using the
     * binary search algorithm. The range must be sorted prior to making this call.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key
     */
    private static int binarySearch(final int[] a, final int fromIndex,
            final int toIndex, final int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1); // key not found
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
