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
package de.cau.cs.kieler.klay.layered;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer.LinearSegment;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator.HyperNode;

/**
 * A utility class for debug output of KLay Layered.
 * Currently it delegates to both {@link JsonDebugUtil} and {@link DotDebugUtil}.
 * 
 * @author csp
 */
public final class DebugUtil {

    private DebugUtil() {
    };
    
    /**
     * Output a representation of the given graph in dot and JSON format.
     * 
     * @param lgraph
     *            the layered graph
     * @param slotIndex
     *            the slot before whose execution the graph is written.
     * @param name
     *            the name the slot before whose execution the graph is written.
     * @see {@link DotDebugUtil#writeDebugGraph(LGraph, int)}
     * @see {@link JsonDebugUtil#writeDebugGraph(LGraph, int)}
     */
    public static void writeDebugGraph(final LGraph lgraph, final int slotIndex, final String name) {
        // GWTExcludeStart
        DotDebugUtil.writeDebugGraph(lgraph, slotIndex, name);
        JsonDebugUtil.writeDebugGraph(lgraph, slotIndex, name);
        // GWTExcludeEnd
    }

    /**
     * Writes a debug graph for the given linear segments and their dependencies.
     * 
     * @param layeredGraph
     *            the layered graph.
     * @param segmentList
     *            the list of linear segments.
     * @param outgoingList
     *            the list of successors for each linear segment.
     * @see {@link DotDebugUtil#writeDebugGraph(LGraph, List, List)}
     * @see {@link JsonDebugUtil#writeDebugGraph(LGraph, List, List)}
     */
    public static void writeDebugGraph(final LGraph layeredGraph,
            final List<LinearSegment> segmentList, final List<List<LinearSegment>> outgoingList) {
        // GWTExcludeStart
        DotDebugUtil.writeDebugGraph(layeredGraph, segmentList, outgoingList);
        JsonDebugUtil.writeDebugGraph(layeredGraph, segmentList, outgoingList);
        // GWTExcludeEnd
    }

    /**
     * Writes a debug graph for the given list of hypernodes.
     * 
     * @param layeredGraph
     *            the layered graph
     * @param layerIndex
     *            the currently processed layer's index
     * @param hypernodes
     *            a list of hypernodes
     * @param debugPrefix
     *            prefix of debug output files
     * @param label
     *            a label to append to the output files
     * @see {@link DotDebugUtil#writeDebugGraph(LGraph, int, List, String, String)}
     * @see {@link JsonDebugUtil#writeDebugGraph(LGraph, int, List, String, String)}
     */
    public static void writeDebugGraph(final LGraph layeredGraph, final int layerIndex,
            final List<HyperNode> hypernodes, final String debugPrefix, final String label) {
        // GWTExcludeStart
        DotDebugUtil.writeDebugGraph(layeredGraph, layerIndex, hypernodes, debugPrefix, label);
        JsonDebugUtil.writeDebugGraph(layeredGraph, layerIndex, hypernodes, debugPrefix, label);
        // GWTExcludeEnd
    }

}
