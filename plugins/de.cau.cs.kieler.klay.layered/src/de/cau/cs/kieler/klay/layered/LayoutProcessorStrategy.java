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
package de.cau.cs.kieler.klay.layered;

import de.cau.cs.kieler.klay.layered.intermediate.BigNodesProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.CommentPostprocessor;
import de.cau.cs.kieler.klay.layered.intermediate.CommentPreprocessor;
import de.cau.cs.kieler.klay.layered.intermediate.CompoundCycleProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.CompoundDummyEdgeRemover;
import de.cau.cs.kieler.klay.layered.intermediate.CompoundGraphRestorer;
import de.cau.cs.kieler.klay.layered.intermediate.CompoundSideProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.EdgeAndLayerConstraintEdgeReverser;
import de.cau.cs.kieler.klay.layered.intermediate.EndLabelProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.GraphTransformer;
import de.cau.cs.kieler.klay.layered.intermediate.HierarchicalPortConstraintProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.HierarchicalPortDummySizeProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.HierarchicalPortOrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.intermediate.HierarchicalPortPositionProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.HyperedgeDummyMerger;
import de.cau.cs.kieler.klay.layered.intermediate.HypernodesProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.InLayerConstraintProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.InvertedPortProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.LabelDummyInserter;
import de.cau.cs.kieler.klay.layered.intermediate.LabelDummyRemover;
import de.cau.cs.kieler.klay.layered.intermediate.LabelDummySwitcher;
import de.cau.cs.kieler.klay.layered.intermediate.LayerConstraintProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeJoiner;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeSplitter;
import de.cau.cs.kieler.klay.layered.intermediate.NodeMarginCalculator;
import de.cau.cs.kieler.klay.layered.intermediate.NorthSouthPortPostprocessor;
import de.cau.cs.kieler.klay.layered.intermediate.NorthSouthPortPreprocessor;
import de.cau.cs.kieler.klay.layered.intermediate.PortListSorter;
import de.cau.cs.kieler.klay.layered.intermediate.PortPositionProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.PortSideProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer;
import de.cau.cs.kieler.klay.layered.intermediate.SelfLoopProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.SubgraphOrderingProcessor;

/**
 * Definition of available intermediate layout processors for the layered layouter.
 * This enumeration also serves as a factory for intermediate layout processors.
 * 
 * @author cds
 * @author ima
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum LayoutProcessorStrategy {
    
    /* In this enumeration, intermediate layout processors are listed by the earliest
     * slot in which they can sensibly be used. The order in which they are listed is
     * determined by the dependencies on other processors.
     */
    
    // Before Phase 1
    
    /** Handles cyclic dependencies of compound nodes. */
    COMPOUND_CYCLE_PROCESSOR,
    /** Mirrors the graph to perform a right-to-left drawing. */
    LEFT_DIR_PREPROCESSOR,
    /** Transposes the graph to perform a top-bottom drawing. */
    DOWN_DIR_PREPROCESSOR,
    /** Mirrors and transposes the graph to perform a bottom-up drawing. */
    UP_DIR_PREPROCESSOR,
    /** Removes some comment boxes to place them separately in a post-processor. */
    COMMENT_PREPROCESSOR,
    /** Makes sure nodes with layer constraints have only incoming or only outgoing edges. */
    EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER,
    
    // Before Phase 2
    
    /** Splits big nodes into multiple layers to distribute them better and reduce whitespace. */
    BIG_NODES_PROCESSOR,
    /** Adds dummy nodes in edges where center labels are present. */
    LABEL_DUMMY_INSERTER,
    
    // Before Phase 3
    
    /** Makes sure that layer constraints are taken care of. */
    LAYER_CONSTRAINT_PROCESSOR,
    /** Handles northern and southern hierarchical ports. */
    HIERARCHICAL_PORT_CONSTRAINT_PROCESSOR,
    /** Removes layering constraint dummy edges from compound graphs. */
    COMPOUND_DUMMY_EDGE_REMOVER,
    /** Takes a layered graph and turns it into a properly layered graph. */
    LONG_EDGE_SPLITTER,
    /** Makes sure nodes have at least fixed port sides. */
    PORT_SIDE_PROCESSOR,
    /** Takes a layered graph and inserts dummy nodes for edges connected to inverted ports. */
    INVERTED_PORT_PROCESSOR,
    /** Takes care of self loops. */
    SELF_LOOP_PROCESSOR,
    /** Orders the port lists of nodes with fixed port order. */
    PORT_LIST_SORTER,
    /** Inserts dummy nodes to take care of northern and southern ports. */
    NORTH_SOUTH_PORT_PREPROCESSOR,
    /** Tries to switch the label dummy nodes which the middle most dummy node
     *  of a long edge. */
    LABEL_DUMMY_SWITCHER,
   
    
    // Before Phase 4
    
    /** Makes sure that subgraphs are in same relative order on all levels.*/
    SUBGRAPH_ORDERING_PROCESSOR,
    /** Makes sure that in-layer constraints are handled. */
    IN_LAYER_CONSTRAINT_PROCESSOR,
    /** Merges long edge dummy nodes belonging to the same hyperedge. */
    HYPEREDGE_DUMMY_MERGER,
    /** Sets the positions of ports. */
    PORT_POSITION_PROCESSOR,
    /** Calculates the margins of nodes according to the sizes of ports and labels. */
    NODE_MARGIN_CALCULATOR,
    /** Inserts dummy nodes and edges to achieve free drawing space for compound node borders. */
    COMPOUND_SIDE_PROCESSOR,
    
    // Before Phase 5
    
    /** Adjusts the width of hierarchical port dummy nodes. */
    HIERARCHICAL_PORT_DUMMY_SIZE_PROCESSOR,
    /** Fix coordinates of hierarchical port dummy nodes. */
    HIERARCHICAL_PORT_POSITION_PROCESSOR,
    
    // After Phase 5
    
    /** Reinserts and places comment boxes that have been removed before. */
    COMMENT_POSTPROCESSOR,
    /** Moves hypernodes horizontally for better placement. */
    HYPERNODE_PROCESSOR,
    /** Routes edges incident to hierarchical ports orthogonally. */
    HIERARCHICAL_PORT_ORTHOGONAL_EDGE_ROUTER,
    /** Takes a properly layered graph and removes the dummy nodes due to proper layering. */
    LONG_EDGE_JOINER,
    /** Removes dummy nodes inserted by the north south side preprocessor and routes edges. */
    NORTH_SOUTH_PORT_POSTPROCESSOR,
    /** Takes the reversed edges of a graph and restores their original direction. */
    REVERSED_EDGE_RESTORER,
    /** Removes dummy nodes and -edges from compound graph representation,
     *  positioning of compound nodes. */
    COMPOUND_GRAPH_RESTORER,
    /** Mirrors the graph to perform a right-to-left drawing. */
    LEFT_DIR_POSTPROCESSOR,
    /** Transposes the graph to perform a top-bottom drawing. */
    DOWN_DIR_POSTPROCESSOR,
    /** Mirrors and transposes the graph to perform a bottom-up drawing. */
    UP_DIR_POSTPROCESSOR,
    /** Removes dummy nodes which were introduced for center labels. */
    LABEL_DUMMY_REMOVER,
    /** Place end labels on edges. */
    END_LABEL_PROCESSOR;
    
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static LayoutProcessorStrategy valueOf(final int i) {
        return values()[i];
    }
    
    
    /**
     * Creates an instance of the layout processor described by this instance.
     * 
     * @return the layout processor.
     */
    public ILayoutProcessor create() {
        switch (this) {
        case BIG_NODES_PROCESSOR:
            return new BigNodesProcessor();
            
        case COMMENT_POSTPROCESSOR:
            return new CommentPostprocessor();
            
        case COMMENT_PREPROCESSOR:
            return new CommentPreprocessor();
            
        case COMPOUND_CYCLE_PROCESSOR:
            return new CompoundCycleProcessor();
            
        case COMPOUND_DUMMY_EDGE_REMOVER:
            return new CompoundDummyEdgeRemover();
            
        case COMPOUND_GRAPH_RESTORER:
            return new CompoundGraphRestorer();
            
        case COMPOUND_SIDE_PROCESSOR:
            return new CompoundSideProcessor();
            
        case DOWN_DIR_POSTPROCESSOR:
        case DOWN_DIR_PREPROCESSOR:
            return new GraphTransformer(GraphTransformer.Mode.TRANSPOSE);
            
        case HIERARCHICAL_PORT_CONSTRAINT_PROCESSOR:
            return new HierarchicalPortConstraintProcessor();
        
        case HIERARCHICAL_PORT_DUMMY_SIZE_PROCESSOR:
            return new HierarchicalPortDummySizeProcessor();
            
        case HIERARCHICAL_PORT_ORTHOGONAL_EDGE_ROUTER:
            return new HierarchicalPortOrthogonalEdgeRouter();
        
        case HIERARCHICAL_PORT_POSITION_PROCESSOR:
            return new HierarchicalPortPositionProcessor();
            
        case HYPEREDGE_DUMMY_MERGER:
            return new HyperedgeDummyMerger();
            
        case HYPERNODE_PROCESSOR:
            return new HypernodesProcessor();
        
        case IN_LAYER_CONSTRAINT_PROCESSOR:
            return new InLayerConstraintProcessor();
        
        case LAYER_CONSTRAINT_PROCESSOR:
            return new LayerConstraintProcessor();
            
        case LEFT_DIR_POSTPROCESSOR:
        case LEFT_DIR_PREPROCESSOR:
            return new GraphTransformer(GraphTransformer.Mode.MIRROR);
        
        case EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER:
            return new EdgeAndLayerConstraintEdgeReverser();
            
        case LONG_EDGE_JOINER:
            return new LongEdgeJoiner();
            
        case LONG_EDGE_SPLITTER:
            return new LongEdgeSplitter();
        
        case NODE_MARGIN_CALCULATOR:
            return new NodeMarginCalculator();
        
        case NORTH_SOUTH_PORT_POSTPROCESSOR:
            return new NorthSouthPortPostprocessor();
        
        case NORTH_SOUTH_PORT_PREPROCESSOR:
            return new NorthSouthPortPreprocessor();
        
        case INVERTED_PORT_PROCESSOR:
            return new InvertedPortProcessor();
        
        case PORT_LIST_SORTER:
            return new PortListSorter();
        
        case PORT_POSITION_PROCESSOR:
            return new PortPositionProcessor();
        
        case PORT_SIDE_PROCESSOR:
            return new PortSideProcessor();
        
        case REVERSED_EDGE_RESTORER:
            return new ReversedEdgeRestorer();
        
        case SELF_LOOP_PROCESSOR:
            return new SelfLoopProcessor();
            
        case SUBGRAPH_ORDERING_PROCESSOR:
            return new SubgraphOrderingProcessor();
            
        case UP_DIR_POSTPROCESSOR:
        case UP_DIR_PREPROCESSOR:
            return new GraphTransformer(GraphTransformer.Mode.MIRROR_AND_TRANSPOSE);
            
        case LABEL_DUMMY_INSERTER:
            return new LabelDummyInserter();
            
        case LABEL_DUMMY_REMOVER:
            return new LabelDummyRemover();
            
        case LABEL_DUMMY_SWITCHER:
            return new LabelDummySwitcher();
            
        case END_LABEL_PROCESSOR:
            return new EndLabelProcessor();
        
        default:
            return null;
        }
    }
}
