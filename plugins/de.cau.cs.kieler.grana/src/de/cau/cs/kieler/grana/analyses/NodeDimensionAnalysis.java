/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.grana.analyses;

/**
 * The {@link NodeSizeAnalysis} only returns results on the size of the nodes. 
 * The analysis reports results for the width and height of nodes. 
 */
public class NodeDimensionAnalysis extends NodeSizeAnalysis {

    // CONSTANTS
    /**
     * ID of this analysis.
     */
    public static final String ANALYSIS_ID = "de.cau.cs.kieler.grana.nodeSize";
    
    public static final int INDEX_MIN_W = 0;
    public static final int INDEX_AVG_W = 1;
    public static final int INDEX_MAX_W = 2;
    
    public static final int INDEX_MIN_H = 3;
    public static final int INDEX_AVG_H = 4;
    public static final int INDEX_MAX_H = 5;

    public static final int INDEX_NODES = 6;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Object returnResult(NodeSizeAnalysisState state) {
        return new Object[]{
                // width
                state.minWidth,
                state.sumWidth / state.nodes,
                state.maxWidth,
                // height
                state.minHeight,
                state.sumHeight / state.nodes,
                state.maxHeight,
                // cnt
                state.nodes
        };
    }
    
}
