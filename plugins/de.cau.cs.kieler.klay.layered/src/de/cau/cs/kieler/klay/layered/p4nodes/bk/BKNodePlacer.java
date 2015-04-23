/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes.bk;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.HDirection;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.NeighborComparator;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.BKAlignedLayout.VDirection;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.ICompacter.CompactionStrategy;
import de.cau.cs.kieler.klay.layered.properties.FixedAlignment;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * This algorithm is an implementation for solving the node placement problem
 * which is posed in phase 4 of the KLay Layered algorithm. Inspired by:
 * <ul>
 *   <li> Ulrik Brandes and Boris K&ouml;pf, Fast and simple horizontal coordinate assignment.
 *     In <i>Proceedings of the 9th International Symposium on Graph Drawing (GD'01)</i>,
 *     LNCS vol. 2265, pp. 33-36, Springer, 2002. </li>
 * </ul>
 * 
 * <p>The original algorithm was extended to be able to cope with ports, node sizes, and node margins,
 * and was made more stable in general. The algorithm is structured in five steps, which include two new
 * steps which were not included in the original algorithm by Brandes and Koepf. The middle three steps
 * are executed four times, traversing the graph in all combinations of TOP or BOTTOM and LEFT or
 * RIGHT.</p>
 * 
 * <p>In KLay Layered we have the general idea of layouting from left to right and
 * transforming in the desired direction later. We decided to translate the terminology of the original
 * algorithm which thinks of a layout from top to bottom. When placing coordinates, we have to differ
 * from the original algorithm, since node placement in KLay Layered has to assign y-coordinates and not
 * x-coordinates.</p>
 * 
 * <p>The variable naming in this code is mostly chosen for an iteration direction within our
 * left to right convention. Arrows describe iteration direction.
 * 
 * LEFT                  RIGHT
 * <----------           ------->
 * 
 * UP    ^              DOWN |
 *       |                   |
 *       |                   v
 * </p>   
 *      
 * <h4>The algorithm:</h4>
 * 
 * <p>The first step checks the graphs' edges and marks short edges which cross long edges (called
 * type 1 conflict). The algorithm indents to draw long edges as straight as possible, thus trying to
 * solve the marked conflicts in a way which keep the long edge straight.</p>
 * 
 * <p>============ UP, DOWN x LEFT, RIGHT ============</p>
 * 
 * <p>The second step traverses the graph in the given directions and tries to group connected nodes
 * into (horizontal) blocks. These blocks, respectively the contained nodes, will be drawn straight when
 * the algorithm is finished. Here, type 1 conflicts are resolved, so that the dummy nodes of a long
 * edge share the same block if possible, such that the long edge is drawn straightly.</p>
 * 
 * <p>The third step contains the addition of node size and port positions to the original algorithm.
 * Each block is investigated from top to bottom. Nodes are moved inside the blocks, such that the port
 * of the edge going to the next node is on the same level as that next node. Furthermore, the size of
 * the block is calculated, regarding node sizes and new space needed due to node movement.</p>
 * 
 * <p>In the fourth step, actual y-coordinates are determined. The blocks are placed, start block and
 * direction determined by the directions of the current iteration. It is tried to place the blocks as
 * compact as possible by grouping blocks.</p>
 *  
 * <p>======================= END =======================</p>
 * 
 * <p>The action of the last step depends on a layout option. If "Less Edge Bends" is set to true, one
 * of the four calculated layouts is selected and applied, choosing the layout which uses the least
 * space. If it is false, a balanced layout is chosen by calculating a median layout of all four
 * layouts.</p>
 * 
 * <p>In rare cases, it is possible that one or more layouts is not correct, e.g. having nodes which
 * overlap each other or violating the layer ordering constraint. If the algorithm detects that, the
 * respective layout is discarded and another one is chosen.</p>
 * 
 * <dl>
 *   <dt>Preconditions:</dt>
 *     <dd>The graph has a proper layering with optimized nodes ordering</dd>
 *     <dd>Ports are properly arranged</dd>
 *   <dt>Postconditions:</dt>
 *     <dd>Each node is assigned a vertical coordinate such that no two nodes overlap</dd>
 *     <dd>The size of each layer is set according to the area occupied by its nodes</dd>
 *     <dd>The height of the graph is set to the maximal layer height</dd>
 * </dl>
 * 
 * @author jjc
 * @author uru
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating yellow 2012-08-10 chsch grh KI-19
 */
public final class BKNodePlacer implements ILayoutPhase {
    
    /** Additional processor dependencies for graphs with hierarchical ports. */
    private static final IntermediateProcessingConfiguration HIERARCHY_PROCESSING_ADDITIONS =
            IntermediateProcessingConfiguration.createEmpty()
            .addBeforePhase5(IntermediateProcessorStrategy.HIERARCHICAL_PORT_POSITION_PROCESSOR);

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        
        if (graph.getProperty(InternalProperties.GRAPH_PROPERTIES).contains(
                GraphProperties.EXTERNAL_PORTS)) {
            return HIERARCHY_PROCESSING_ADDITIONS;
        } else {
            return null;
        }
    }
    
    private LGraph lGraph;
    /** List of edges involved in type 1 conflicts (see above). */
    private final List<LEdge> markedEdges = Lists.newArrayList();

    /** Flag which switches debug output of the algorithm on or off. */
    private boolean debugMode = false;
    /** Whether to produce a balanced layout or not. */
    private boolean produceBalancedLayout = false;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Brandes & Koepf node placement", 1);

        this.lGraph = layeredGraph;
        
        // Determine overall node count for an optimal initialization of maps.
        int nodeCount = 0;
        for (Layer layer : layeredGraph) {
            nodeCount += layer.getNodes().size();
        }

        // Initialize four layouts which result from the two possible directions respectively.
        BKAlignedLayout rightdown =
                new BKAlignedLayout(layeredGraph, nodeCount, VDirection.DOWN, HDirection.RIGHT);
        BKAlignedLayout rightup =
                new BKAlignedLayout(layeredGraph, nodeCount, VDirection.UP, HDirection.RIGHT);
        BKAlignedLayout leftdown =
                new BKAlignedLayout(layeredGraph, nodeCount, VDirection.DOWN, HDirection.LEFT);
        BKAlignedLayout leftup =
                new BKAlignedLayout(layeredGraph, nodeCount, VDirection.UP, HDirection.LEFT);

        // Regard possible other layout options.
        debugMode = layeredGraph.getProperty(Properties.DEBUG_MODE);
        produceBalancedLayout =
                layeredGraph.getProperty(Properties.FIXED_ALIGNMENT) == FixedAlignment.BALANCED;

        // Phase which marks type 1 conflicts, no difference between the directions so only
        // one run is required.
        markConflicts(layeredGraph);

        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        List<BKAlignedLayout> layouts = Lists.newArrayListWithCapacity(4);
        switch (layeredGraph.getProperty(Properties.FIXED_ALIGNMENT)) {
            case LEFTDOWN:
                layouts.add(leftdown);
                break;
            case LEFTUP:
                layouts.add(leftup);
                break;
            case RIGHTDOWN:
                layouts.add(rightdown);
                break;
            case RIGHTUP:
                layouts.add(rightup); 
                break;
            default:
                layouts.add(rightdown);
                layouts.add(rightup);
                layouts.add(leftdown);
                layouts.add(leftup); 
        }
        
        BKAligner aligner = new BKAligner(layeredGraph);
        for (BKAlignedLayout bal : layouts) {
            // Phase which determines the nodes' memberships in blocks. This happens in four different
            // ways, either from processing the nodes from the first layer to the last or vice versa.
            aligner.verticalAlignment(bal, markedEdges);
            
            // Additional phase which is not included in the original Brandes-Koepf Algorithm.
            // It makes sure that the connected ports within a block are aligned to avoid unnecessary
            // bend points. Also, the required size of each block is determined.
            aligner.insideBlockShift(bal);
        }

        ICompacter compacter;
        switch (layeredGraph.getProperty(Properties.COMPACTION_STRATEGY)) {
            case IMPROVE_STRAIGHTNESS:
                compacter = new BKCompacterStraight(layeredGraph);
                break;
            default:
                compacter = new BKCompacter(layeredGraph);
        }
        for (BKAlignedLayout bal : layouts) {
            // This phase determines the y coordinates of the blocks and thus the vertical coordinates
            // of all nodes.
            compacter.horizontalCompaction(bal);
        }

        if (layeredGraph.getProperty(Properties.COMPACTION_STRATEGY) 
                == CompactionStrategy.IMPROVE_STRAIGHTNESS_POSTPROCESS) {
            BKStraightener straightener = new BKStraightener(layeredGraph);
            for (BKAlignedLayout bal : layouts) {
                straightener.improveStraightness(bal);
            }
        }

        // Debug output
        if (debugMode) {
            System.out.println("rightdown size is " + rightdown.layoutSize());
            System.out.println("rightup size is " + rightup.layoutSize());
            System.out.println("leftdown size is " + leftdown.layoutSize());
            System.out.println("leftup size is " + leftup.layoutSize());
        }

        // Choose a layout from the four calculated layouts. Layouts that contain errors are skipped.
        // The layout with the smallest size is selected. If more than one smallest layout exists,
        // the first one of the competing layouts is selected.
        BKAlignedLayout chosenLayout = null;
        BKAlignedLayout balanced = new BKAlignedLayout(layeredGraph, nodeCount, null, null);

        // If layout options chose to use the balanced layout, it is calculated and added here.
        // If it is broken for any reason, one of the four other layouts is selected by the
        // given criteria.
        if (produceBalancedLayout) {
            balanced = createBalancedLayout(layouts, nodeCount);
            chosenLayout = balanced;
        }
        
        // Since it is possible that the balanced layout violates ordering constraints, this cannot
        // simply be an else case to the previous if statement
        if (!produceBalancedLayout || !checkOrderConstraint(layeredGraph, balanced)) {
            chosenLayout = null;
            for (BKAlignedLayout bal : layouts) {
                if (checkOrderConstraint(layeredGraph, bal)) {
                    if (chosenLayout == null || chosenLayout.layoutSize() > bal.layoutSize()) {
                        chosenLayout = bal;
                    }
                }
            }
        }
        
        // If no layout is correct (which should never happen but is not strictly impossible),
        // the lefttop layout is chosen by default.
        if (chosenLayout == null) {
            chosenLayout = layouts.get(0); // there has to be at least one layout in the list
        }
        
        // Apply calculated positions to nodes.
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPosition().y = chosenLayout.y.get(node) + chosenLayout.innerShift.get(node);
            }
        }

        // Debug output
        if (debugMode) {
            System.out.println("Chosen node placement: " + chosenLayout);
            System.out.println("Blocks: " + getBlocks(chosenLayout));
            System.out.println("Classes: " + getClasses(chosenLayout));
            System.out.println("Marked edges: " + markedEdges);
        }

        markedEdges.clear();
        monitor.done();
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Conflict Detection
    
    /** The minimum number of layers we need to have conflicts. */
    private static final int MIN_LAYERS_FOR_CONFLICTS = 3;

    /**
     * This phase of the node placer marks all type 1 and type 2 conflicts.
     * 
     * <p>The conflict types base on the distinction of inner segments and non-inner segments of edges.
     * A inner segment is present if an edge is drawn between two dummy nodes and thus is part of
     * a long edge. A non-inner segment is present if one of the connected nodes is not a dummy
     * node.</p>
     * 
     * <p>Type 0 conflicts occur if two non-inner segments cross each other. Type 1 conflicts happen 
     * when a non-inner segment and a inner segment cross. Type 2 conflicts are present if two
     * inner segments cross.</p>
     * 
     * <p>The markers are later used to solve conflicts in favor of long edges. In case of type 2
     * conflicts, the marker favors the earlier node in layout order.</p>
     * 
     * @param layeredGraph The layered graph to be layouted
     */
    private void markConflicts(final LGraph layeredGraph) {
        int numberOfLayers = layeredGraph.getLayers().size();
        
        // Check if there are enough layers to detect conflicts
        if (numberOfLayers < MIN_LAYERS_FOR_CONFLICTS) {
            return;
        }
        
        // We'll need the number of nodes in the different layers quite often in this method, so save
        // them up front
        int[] layerSize = new int[numberOfLayers];
        int layerIndex = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            layerSize[layerIndex++] = layer.getNodes().size();
        }
        
        // The following call succeeds since there are at least 3 layers in the graph
        Iterator<Layer> layerIterator = layeredGraph.getLayers().listIterator(2);
        for (int i = 1; i < numberOfLayers - 1; i++) {
            // The variable naming here follows the notation of the corresponding paper
            // Normally, underscores are not allowed in local variable names, but since there
            // is no way of properly writing indices beside underscores, Checkstyle will be
            // disabled here and in future methods containing indexed variables
            // CHECKSTYLEOFF Local Variable Names
            Layer currentLayer = layerIterator.next();
            Iterator<LNode> nodeIterator = currentLayer.getNodes().iterator();
            
            int k_0 = 0;
            int l = 0;
            
            for (int l_1 = 0; l_1 < layerSize[i + 1]; l_1++) {
                // In the paper, l and i are indices for the layer and the position in the layer
                LNode v_l_i = nodeIterator.next(); // currentLayer.getNodes().get(l_1);
                
                if (l_1 == ((layerSize[i + 1]) - 1) || incidentToInnerSegment(v_l_i, i + 1, i)) {
                    int k_1 = layerSize[i] - 1;
                    if (incidentToInnerSegment(v_l_i, i + 1, i)) {
                        k_1 = allUpperNeighbors(v_l_i).get(0).getIndex();
                    }
                    
                    while (l <= l_1) {
                        LNode v_l = currentLayer.getNodes().get(l);
                        
                        if (!incidentToInnerSegment(v_l, i + 1, i)) {
                            for (LNode upperNeighbor : allUpperNeighbors(v_l)) {
                                int k = upperNeighbor.getIndex();
                                
                                if (k < k_0 || k > k_1) {
                                    // Marked edge can't return null here, because the upper neighbor
                                    // relationship between v_l and upperNeighbor enforces the existence
                                    // of at least one edge between the two nodes
                                    markedEdges.add(getEdge(upperNeighbor, v_l));
                                }
                            }
                        }
                        
                        l++;
                    }
                    
                    k_0 = k_1;
                }
            }
            // CHECKSTYLEON Local Variable Names
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Layout Balancing

    /**
     * Calculates a balanced layout by determining the median of the four layouts.
     * 
     * <p>First, the layout with the smallest height, meaning the difference between the highest and the
     * lowest y-coordinate placement, is used as a starting point. Then, the median position of each of
     * the four layouts is used to determine the final position.</p>
     * 
     * @param layouts The four calculated layouts
     * @param nodeCount The number of nodes in the graph
     * @return A balanced layout, the median of the four layouts
     */
    private BKAlignedLayout createBalancedLayout(final List<BKAlignedLayout> layouts,
            final int nodeCount) {
        
        final int noOfLayouts = layouts.size();
        BKAlignedLayout balanced = new BKAlignedLayout(lGraph, nodeCount, null, null);
        double[] width = new double[noOfLayouts];
        double[] min = new double[noOfLayouts];
        double[] max = new double[noOfLayouts];
        int minWidthLayout = 0;

        // Find the smallest layout
        for (int i = 0; i < noOfLayouts; i++) {
            min[i] = Integer.MAX_VALUE;
            max[i] = Integer.MIN_VALUE;
        }
        
        for (int i = 0; i < noOfLayouts; i++) {
            BKAlignedLayout current = layouts.get(i);
            for (double y : current.y.values()) {
                if (min[i] > y) {
                    min[i] = y;
                }
                
                if (max[i] < y) {
                    max[i] = y;
                }
            }
            
            width[i] = max[i] - min[i];
            if (width[minWidthLayout] > width[i]) {
                minWidthLayout = i;
            }
        }

        // Find the shift between the smallest and the four layouts
        double[] shift = new double[noOfLayouts];
        for (int i = 0; i < noOfLayouts; i++) {
            if (layouts.get(i).vdir == VDirection.DOWN) {
                shift[i] = min[minWidthLayout] - min[i];
            } else {
                shift[i] = max[minWidthLayout] - max[i];
            }
        }

        // Calculated y-coordinates for a balanced placement
        double[] calculatedYs = new double[noOfLayouts];
        for (LNode node : layouts.get(0).y.keySet()) {
            for (int i = 0; i < noOfLayouts; i++) {
                calculatedYs[i] = layouts.get(i).y.get(node) + shift[i];
            }
            
            Arrays.sort(calculatedYs);
            balanced.y.put(node, (calculatedYs[1] + calculatedYs[2]) / 2.0);
            balanced.innerShift.put(node,
                    layouts.get(minWidthLayout).innerShift.get(node));
        }

        return balanced;
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods

    /**
     * Checks whether the given node is part of a long edge between the two given layers.
     * At this 'layer2' is left, or before, 'layer1'.
     * 
     * @param node Possible long edge node
     * @param layer1 The first layer, the layer of the node
     * @param layer2 The second layer
     * @return True if the node is part of a long edge between the layers, false else
     */
    private boolean incidentToInnerSegment(final LNode node, final int layer1, final int layer2) {
        // consider that big nodes include their respective start and end node.
        if (node.getNodeType() == NodeType.BIG_NODE) {
            // all nodes should be placed straightly
            for (LEdge edge : node.getIncomingEdges()) {
                LNode source = edge.getSource().getNode();
                if ((source.getNodeType() == NodeType.BIG_NODE
                        || source.getProperty(InternalProperties.BIG_NODE_INITIAL))
                        && edge.getSource().getNode().getLayer().getIndex() == layer2
                        && node.getLayer().getIndex() == layer1) {

                    return true;
                }
            }
        }
        
        if (node.getNodeType() == NodeType.LONG_EDGE) {
            for (LEdge edge : node.getIncomingEdges()) {
                NodeType sourceNodeType = edge.getSource().getNode().getNodeType();
                
                // TODO Using layer indices here is not a good idea in terms of performance
                if (sourceNodeType == NodeType.LONG_EDGE
                        && edge.getSource().getNode().getLayer().getIndex() == layer2
                        && node.getLayer().getIndex() == layer1) {
                    
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gives all upper neighbors of a given node. An upper neighbor is a node in a previous layer that
     * has an edge pointing to the given node.
     * 
     * @param node The node which might have neighbors
     * @return A list containing all upper neighbors
     */
    static List<LNode> allUpperNeighbors(final LNode node) {
        List<LNode> result = Lists.newArrayList();
        int maxPriority = 0;
        
        for (LEdge edge : node.getIncomingEdges()) {
            if (edge.getProperty(Properties.PRIORITY) > maxPriority) {
                maxPriority = edge.getProperty(Properties.PRIORITY);
            }
        }
        
        for (LEdge edge : node.getIncomingEdges()) {
            if (node.getLayer() != edge.getSource().getNode().getLayer()
                    && edge.getProperty(Properties.PRIORITY) == maxPriority) {
                result.add(edge.getSource().getNode());
            }
        }
        
        Collections.sort(result, NeighborComparator.INSTANCE);
        return result;
    }

    /**
     * Give all lower neighbors of a given node. A lower neighbor is a node in a following layer that
     * has an edge coming from the given node.
     * 
     * @param node The node which might have neighbors
     * @return A list containing all lower neighbors
     */
    static List<LNode> allLowerNeighbors(final LNode node) {
        List<LNode> result = Lists.newArrayList();
        int maxPriority = 0;
        
        for (LEdge edge : node.getOutgoingEdges()) {
            if (edge.getProperty(Properties.PRIORITY) > maxPriority) {
                maxPriority = edge.getProperty(Properties.PRIORITY);
            }
        }
        
        for (LEdge edge : node.getOutgoingEdges()) {
            if (node.getLayer() != edge.getTarget().getNode().getLayer()
                    && edge.getProperty(Properties.PRIORITY) == maxPriority) {
                
                result.add(edge.getTarget().getNode());
            }
        }
        
        Collections.sort(result, NeighborComparator.INSTANCE);
        return result;
    }

    /**
     * Find an edge between two given nodes.
     * 
     * @param source The source node of the edge
     * @param target The target node of the edge
     * @return The edge between source and target, or null if there is none
     */
    static LEdge getEdge(final LNode source, final LNode target) {
        for (LEdge edge : source.getConnectedEdges()) {
            if (edge.getTarget().getNode().equals(target) || edge.getSource().getNode().equals(target)) {
                return edge;
            }
        }
        
        return null;
    }

    /**
     * Finds all blocks of a given layout.
     * 
     * @param bal The layout of which the blocks shall be found
     * @return The blocks of the given layout
     */
    static Map<LNode, List<LNode>> getBlocks(final BKAlignedLayout bal) {
        Map<LNode, List<LNode>> blocks = Maps.newLinkedHashMap();
        
        for (LNode node : bal.root.keySet()) {
            LNode root = bal.root.get(node);
            List<LNode> blockContents = blocks.get(root);
            
            if (blockContents == null) {
                blockContents = Lists.newArrayList();
                blocks.put(root, blockContents);
            }
            
            blockContents.add(node);
        }
        
        return blocks;
    }

    /**
     * Finds all classes of a given layout.
     * 
     * @param bal The layout whose classes to find
     * @return The classes of the given layout
     */
    static Map<LNode, List<LNode>> getClasses(final BKAlignedLayout bal) {
        Map<LNode, List<LNode>> classes = Maps.newLinkedHashMap();
        
        // We need to enumerate all block roots
        Set<LNode> roots = Sets.newLinkedHashSet(bal.root.values());
        for (LNode root : roots) {
            LNode sink = bal.sink.get(root);
            List<LNode> classContents = classes.get(sink);
            
            if (classContents == null) {
                classContents = Lists.newArrayList();
                classes.put(sink, classContents);
            }
            
            classContents.add(root);
        }
        
        return classes;
    }
        
    /**
     * Checks whether all nodes are placed in the correct order in their layers and do not overlap
     * each other.
     * 
     * @param layeredGraph the containing layered graph.
     * @param bal the layout which shall be checked.
     * @return {@code true} if the order is preserved and no nodes overlap, {@code false} otherwise.
     */
    private boolean checkOrderConstraint(final LGraph layeredGraph, final BKAlignedLayout bal) {
        // Check if the layout contains Y coordinate information
        if (bal.y.isEmpty()) {
            return false;
        }
        
        // Flag indicating whether the layout is feasible or not
        boolean feasible = true;
        
        // Iterate over the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // Current Y position in the layer
            double pos = Double.NEGATIVE_INFINITY;
            
            // We remember the previous node for debug output
            LNode previous = null;
            
            // Iterate through the layer's nodes
            for (LNode node : layer.getNodes()) {
                // For the layout to be correct, both the node's top border and its bottom border must
                // be beyond the current position in the layer
                double top = bal.y.get(node) + bal.innerShift.get(node) - node.getMargin().top;
                double bottom = bal.y.get(node) + bal.innerShift.get(node) + node.getSize().y
                        + node.getMargin().bottom;
                
                if (top > pos && bottom > pos) {
                    previous = node;
                    
                    // Update the position inside the layer
                    pos = bal.y.get(node) + bal.innerShift.get(node) + node.getSize().y
                            + node.getMargin().bottom;
                } else {
                    // We've found an overlap
                    feasible = false;
                    if (debugMode) {
                        System.out.println("bk node placement breaks on " + node
                                + " which should have been after " + previous);
                    }
                    break;
                }
            }
            
            // Don't bother continuing if we've already determined that the layout is infeasible
            if (!feasible) {
                break;
            }
        }
        
        if (debugMode) {
            System.out.println(bal + " is feasible: " + feasible);
        }
        
        return feasible;
    }
    

   
}
