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
package de.cau.cs.kieler.klay.layered.p2layers;

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
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.solver.AbstractCPLEXModel;

/**
 * @author uru
 * 
 */
public class CPLEXLayeringModel extends AbstractCPLEXModel<Object, Pair<Integer, List<Integer>>> {

    /**
     * @param graph the layered graph
     */
    public CPLEXLayeringModel(final LGraph graph) {
        super(graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModel() {
        return "D:/Uni/ma/minizinc-layering/second/cplex/Layering2/Layering.mod";
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
        sb.append("e = [[ ");
        for (int i = 0; i < adj.length; i++) {
            float[] row = adj[i];
            sb.append(Joiner.on(", ").join(Floats.asList(row)));
            if (i != adj.length - 1) {
                sb.append("],\n     [ ");
            }
        }
        sb.append(" ]];\n");
        
        // TODO
        // weights
        sb.append("\nw_length = " + layeredGraph.getProperty(Properties.EDGE_LENGTH_WEIGHT)
                + ";\nw_reverse = " + layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT)
                + ";\n");
//        
//        MinizincMode mode = layeredGraph.getProperty(Properties.MINIZINC_MODE);
//        boolean staticDummyWeight = mode != MinizincMode.BETWEENNESS_BOTH;
//        boolean staticReverseWeight = mode == MinizincMode.STATIC;
//        sb.append("static_w_length = " + staticDummyWeight + ";\nstatic_w_reverse = "
//                + staticReverseWeight + ";\n");
//
//        int maximalLayers = layeredGraph.getProperty(Properties.MAXIMAL_LAYERS);
//        sb.append("L_max_param = " + maximalLayers + ";\n");
        
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public Pair<Integer, List<Integer>> parseResult(final InputStream is) throws IOException {
        
        final List<String> lines =
                CharStreams.readLines(new InputStreamReader(is));
        
        int nodeCount = layeredGraph.getLayerlessNodes().size();
        Integer[] assignLayers = new Integer[nodeCount];
        int minLayer = Integer.MAX_VALUE;
        int maxLayer = Integer.MIN_VALUE;
        System.out.println("Lines: " + lines);
        for (String line : lines) {
            if (!line.startsWith("Result")) {
                continue;
            }
            System.out.println("Line " + line);
            
            String values = line.substring(line.indexOf("[") + 1, line.lastIndexOf("]"));
            System.out.println("values: " + values);
            String[] chunks = values.trim().split(" ");
            
            for (int i = 0; i < chunks.length; i++) {
             // FIXME sometimes scip returns 0.99999999999, so we round here
                int layer = Math.round(Float.valueOf(chunks[i]));
                assignLayers[i] = layer;
                minLayer = Math.min(minLayer, layer);
                maxLayer = Math.max(maxLayer, layer);
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
