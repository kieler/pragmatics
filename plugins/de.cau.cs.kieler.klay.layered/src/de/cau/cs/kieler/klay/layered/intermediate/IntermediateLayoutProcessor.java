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
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;

/**
 * Definition of available intermediate layout processors for the layered layouter.
 * This enumeration also serves as a factory for intermediate layout processors.
 * 
 * @author cds
 * @author ima
 */
public enum IntermediateLayoutProcessor {
    
    /* In this enumeration, intermediate layout processors are listed by the earliest
     * slot in which they can sensibly be used. The order in which they are listed is
     * determined by the dependencies on other processors.
     */
    
    // Before Phase 1
    
    /** Makes sure nodes with layer constraints have only incoming or only outgoing edges. */
    EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER,
    
    // Before Phase 2
    
    /** Splits big nodes into multiple layers to distribute them better and reduce whitespace. */
    BIG_NODES_PROCESSOR,
    
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
   
    
    // Before Phase 4
    
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
    COMPOUND_GRAPH_RESTORER;
    
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static IntermediateLayoutProcessor valueOf(final int i) {
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
            
        case COMPOUND_DUMMY_EDGE_REMOVER:
            return new CompoundDummyEdgeRemover();
            
        case COMPOUND_GRAPH_RESTORER:
            return new CompoundGraphRestorer();
            
        case COMPOUND_SIDE_PROCESSOR:
            return new CompoundSideProcessor();
            
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
        
        case IN_LAYER_CONSTRAINT_PROCESSOR:
            return new InLayerConstraintProcessor();
        
        case LAYER_CONSTRAINT_PROCESSOR:
            return new LayerConstraintProcessor();
        
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
        
        default:
            return null;
        }
    }
}
