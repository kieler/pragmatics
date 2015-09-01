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
import de.cau.cs.kieler.solvers.AbstractCPLEXModel;

/**
 * @author uru
 */
public class CPLEXDLPModel extends AbstractCPLEXModel<Object, Pair<Integer, List<Integer>>> {

    /** CPLEX model be executed. */
    private static final String CPLEX_LAYERING = System.getenv("CPLEX_DLP_LAYERING");
    
    /** The graph to be layered. */
    private LGraph layeredGraph = null;
    
    
    /**
     * @param graph the layered graph
     */
    public CPLEXDLPModel(final LGraph graph) {
        checkIfPathExists(CPLEX_LAYERING, "CPLEX_DLP_LAXERING");
        
        layeredGraph = graph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModel() {
        return CPLEX_LAYERING;
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
        
        int index = 0;
        boolean start = false;
        for (String line : lines) {
            if (line.startsWith("Done")) {
                break;
            }
            if (!start) {
                if (line.startsWith("Result")) {
                    start = true;
                }
                continue;
            }

            String chunk = line.trim();
            // FIXME sometimes scip returns 0.99999999999, so we round here
            int layer = Math.round(Float.valueOf(chunk));
            assignLayers[index++] = layer;
            minLayer = Math.min(minLayer, layer);
            maxLayer = Math.max(maxLayer, layer);
        }
        
        System.out.println("Length: " + index + " " + Arrays.toString(assignLayers));
        int numberOfLayers = maxLayer - minLayer + 1;
        // shift all layers such that the first layer is 0
        for (int i = 0; i < assignLayers.length; i++) {
            assignLayers[i] -= minLayer;
        }
        
        // returns an arraylist
        return Pair.of(numberOfLayers, Arrays.asList(assignLayers));
    }
}
