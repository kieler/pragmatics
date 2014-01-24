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
package de.cau.cs.kieler.kiml.util;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory.GraphAdapter;

/**
 * 
 * Entry points to apply several methods for node dimension calculation, including positioning of
 * labels, ports, etc.
 * 
 * @author uru
 */
public final class KimlNodeDimensionCalculation {

    /**
     * .
     */
    private KimlNodeDimensionCalculation() {
    }

    public static <T> void calculateLabelAndNodeSizes(final T graph,
            final GraphAdapterFactory<T> fac) {
        GraphAdapter<?> graphAdapter = fac.getGraphAdapter(graph);

        LabelAndNodeSizeProcessor processor = new LabelAndNodeSizeProcessor();
        processor.process(graphAdapter);
    }

    public static void calculateLabelAndNodeSizes(final KNode root) {
        KGraphAdapterFactory fac = new KGraphAdapterFactory();
        calculateLabelAndNodeSizes(root, fac);
    }

    public static <T> void calculateNodeMargins(final T graph, final GraphAdapterFactory<T> fac) {
        GraphAdapter<?> graphAdapter = fac.getGraphAdapter(graph);

        NodeMarginCalculator calcu = new NodeMarginCalculator();
        calcu.processNodeMargin(graphAdapter);
    }

    public static void calculateNodeMargins(final KNode root) {
        KGraphAdapterFactory fac = new KGraphAdapterFactory();
        calculateNodeMargins(root, fac);
    }

}
