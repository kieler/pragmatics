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
package de.cau.cs.kieler.klay.layered.solver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.io.CharStreams;
import com.google.common.primitives.Floats;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.p2layers.MinizincMode;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * @author uru
 */
public class MiniZincLayeringModel extends
        AbstractMiniZincModel<Object, Pair<Integer, List<Integer>>> {

    /**
     * @param graph
     *            the graph to be layered.
     */
    public MiniZincLayeringModel(final LGraph graph) {
        super(graph);
    }

    /**
     * {@inheritDoc}
     */
    public String serializeSourceModel(final Object source) {
        float[][] adj = (float[][]) source;

        StringBuffer sb = new StringBuffer();
        // edges as adjacency matrix
        sb.append("N = " + layeredGraph.getLayerlessNodes().size() + ";\n");

        // strongly connected components
        sb.append("e = [| ");
        for (int i = 0; i < adj.length; i++) {
            float[] row = adj[i];
            sb.append(Joiner.on(", ").join(Floats.asList(row)));
            if (i != adj.length - 1) {
                sb.append(",\n     | ");
            }
        }
        sb.append(" |];\n");

        // weights
        sb.append("\nW_length = " + layeredGraph.getProperty(Properties.EDGE_LENGTH_WEIGHT)
                + ";\nW_reverse = " + layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT)
                + ";\n");

        MinizincMode mode = layeredGraph.getProperty(Properties.MINIZINC_MODE);
        boolean staticDummyWeight = mode != MinizincMode.BETWEENNESS_BOTH;
        boolean staticReverseWeight = mode == MinizincMode.STATIC;
        sb.append("static_w_length = " + staticDummyWeight + ";\nstatic_w_reverse = "
                + staticReverseWeight + ";\n");

        int maximalLayers = layeredGraph.getProperty(Properties.MAXIMAL_LAYERS);
        sb.append("L_max_param = " + maximalLayers + ";\n");

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public Pair<Integer, List<Integer>> parseResult(final InputStream is) throws IOException {

        final List<String> lines = CharStreams.readLines(new InputStreamReader(is));

        int nodeCount = layeredGraph.getLayerlessNodes().size();
        Integer[] assignLayers = new Integer[nodeCount];
        int minLayer = Integer.MAX_VALUE;
        int maxLayer = Integer.MIN_VALUE;

        for (String line : lines) {
            String[] chunks = line.trim().split(" ");
            if (chunks.length > 1) {
                // FIXME sometimes scip returns 0.99999999999, so we round here
                int id = Math.round(Float.valueOf(chunks[0]));
                int layer = Math.round(Float.valueOf(chunks[1]));

                // minizinc starts with 1 instead of 0
                assignLayers[id - 1] = layer - 1;
                minLayer = Math.min(minLayer, layer - 1);
                maxLayer = Math.max(maxLayer, layer - 1);
            } else {
                System.out.println(Arrays.toString(chunks));
            }
        }
        System.out.println(Arrays.toString(assignLayers));
        int numberOfLayers = maxLayer - minLayer + 1;
        // shift all layers such that the first layer is 0
        for (int i = 0; i < assignLayers.length; i++) {
            assignLayers[i] -= minLayer;
        }

        // returns an arraylist
        return Pair.of(numberOfLayers, Arrays.asList(assignLayers));
    }
}
