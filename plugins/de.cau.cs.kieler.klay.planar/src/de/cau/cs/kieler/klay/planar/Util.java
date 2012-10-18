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
package de.cau.cs.kieler.klay.planar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * provides some methods to debug the graph.
 * 
 * @author pkl
 */
public final class Util {

    /**
     * Private constructor.
     */
    private Util() {
        // This space intentionally left blank
    }

    // ======================== Debug Files ==========================================
    /**
     * Returns the path for debug output graphs.
     * 
     * @return the path for debug output graphs, without trailing separator.
     */
    public static String getDebugOutputPath() {
        String path = System.getProperty("user.home");
        if (path.endsWith(File.separator)) {
            path += "tmp" + File.separator + "klay";
        } else {
            path += File.separator + "tmp" + File.separator + "klay";
        }

        return path;
    }

    /**
     * Returns the beginning of the file name used for debug output graphs while layouting the given
     * layered graph. This will look something like {@code "143293-"}.
     * 
     * @param graph
     *            the graph to return the base debug file name for.
     * @return the base debug file name for the given graph.
     */
    public static String getDebugOutputFileBaseName(final PGraph graph) {
        return Integer.toString(graph.hashCode() & ((1 << (Integer.SIZE / 2)) - 1)) + "-";
    }

    /**
     * Removes the old temp directory.
     */
    public static void clearTmpDir() {
        String path = Util.getDebugOutputPath();
        for (File innerFile : new File(path).listFiles()) {
            try {
                innerFile.delete();
            } catch (SecurityException e) {
                // nothing to do.
            }
        }

    }

    /**
     * Creates a writer for the given graph. The file name to be written to is assembled from the
     * graph's hash code and the slot index. Writes the graph in a dot output file.
     * 
     * @param graph
     *            the stored graph
     * 
     * @param slotIndex
     *            the slot before whose execution the graph is written
     * 
     * @param directed
     *            if the output graph should be stored as directed graph
     * 
     */
    public static void storeGraph(final PGraph graph, final int slotIndex, final boolean directed) {
        try {
            String path = Util.getDebugOutputPath();
            new File(path).mkdirs();

            String debugFileName = Util.getDebugOutputFileBaseName(graph) + "fulldebug-slot"
                    + String.format("%1$02d", slotIndex);

            graph.writeDotGraph(new FileWriter(new File(path + File.separator + debugFileName
                    + ".dot")), directed);
        } catch (IOException e) {
            // nothing to do
        } catch (SecurityException se) {
            // nothing to do
        }
    }

    // ======================== General Algorithms==========================================

    /**
     * computes a breadth-first-search. Starting with the source node, moving along the edges and
     * put every found node to the result.
     * 
     * @param graph
     *            the graph on which the bfs should be done
     * @param startNode
     *            the root node of the bfs
     * @return a sorted listed from source to the furthermost apart node.
     */
    public static List<PNode> bfsNodes(final PGraph graph, final PNode startNode) {
        List<PNode> result = new LinkedList<PNode>();

        // need to save the already visited nodes.
        List<PNode> visited = new LinkedList<PNode>();

        Queue<PNode> queue = new LinkedList<PNode>();
        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            PNode node = queue.remove();
            result.add(node);
            for (PNode child : node.adjacentNodes()) {
                if (!visited.contains(child)) {
                    queue.add(child);
                    visited.add(child);
                }
            }

        }
        return result;
    }

}
