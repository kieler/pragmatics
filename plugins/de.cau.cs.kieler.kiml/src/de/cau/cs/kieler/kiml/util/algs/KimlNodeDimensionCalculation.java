/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.util.algs;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphAdapter;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;

/**
 * 
 * Entry points to apply several methods for node dimension calculation, including positioning of
 * labels, ports, etc.
 * 
 * @author uru
 */
public final class KimlNodeDimensionCalculation {

    /**
     * Private constructor - utility class.
     */
    private KimlNodeDimensionCalculation() {
    }

    /**
     * Calculates label sizes and node sizes also considering ports.
     * 
     * @see LabelAndNodeSizeProcessor
     * 
     * @param adapter
     *            an instance of an adapter for the passed graph's type.
     * @param <T>
     *            the graphs type, e.g. {@link KNode}
     */
    public static <T> void calculateLabelAndNodeSizes(final GraphAdapter<T> adapter) {
        LabelAndNodeSizeProcessor processor = new LabelAndNodeSizeProcessor();
        processor.process(adapter);
    }

    /**
     * Calculates label sizes and node sizes also considering ports for a KGraph.
     * 
     * @see LabelAndNodeSizeProcessor
     * 
     * @param graph
     *            the graph for which to calculate the sizes.
     */
    public static void calculateLabelAndNodeSizes(final KNode graph) {
        KGraphAdapter kga = new KGraphAdapter(graph);
        calculateLabelAndNodeSizes(kga);
    }

    /**
     * Calculates node margins for the nodes of the passed graph.
     * 
     * @param adapter
     *            an instance of an adapter for the passed graph's type.
     * @param <T>
     *            the graphs type, e.g. {@link KNode}
     */
    public static <T> void calculateNodeMargins(final GraphAdapter<T> adapter) {
        NodeMarginCalculator calcu = new NodeMarginCalculator();
        calcu.processNodeMargin(adapter);
    }

    /**
     * Calculates node margins for the nodes of the passed KGraph.
     * 
     * @param root
     *            the graph.
     */
    public static void calculateNodeMargins(final KNode root) {
        KGraphAdapter kga = new KGraphAdapter(root);
        calculateNodeMargins(kga);
    }

}
