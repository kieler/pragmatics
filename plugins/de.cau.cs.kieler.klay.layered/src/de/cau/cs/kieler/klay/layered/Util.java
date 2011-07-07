/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import java.io.File;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.graph.Insets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;


/**
 * Contains utility methods used throughout KLay Layered.
 * 
 * @author cds
 */
public final class Util {
    
    /**
     * Private constructor.
     */
    private Util() {
        // This space intentionally left blank
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Debug Files
    
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
     * Returns the beginning of the file name used for debug output graphs while layouting the
     * given layered graph. This will look something like {@code "143293-"}.
     * 
     * @param graph the graph to return the base debug file name for.
     * @return the base debug file name for the given graph.
     */
    public static String getDebugOutputFileBaseName(final LayeredGraph graph) {
        return Integer.toString(graph.hashCode() & ((1 << (Integer.SIZE / 2)) - 1)) + "-";
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Coordinate Conversions
    
    /**
     * Converts the position of the given node from coordinates relative to the hierarchical node
     * border to coordinates relative to that node's content area. The content area is the
     * hierarchical node minus insets minus border spacing minus offset.
     * 
     * @param node the node whose coordinates to convert.
     * @param graph the layered graph.
     * @param horizontal if {@code true}, the x coordinate will be translated.
     * @param vertical if {@code true}, the y coordinate will be translated.
     */
    public static void borderToContentAreaCoordinates(final LNode node, final LayeredGraph graph,
            final boolean horizontal, final boolean vertical) {
        
        Insets.Double insets = graph.getInsets();
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        KVector offset = graph.getOffset();
        
        KVector pos = node.getPosition();
        
        if (horizontal) {
            pos.x = pos.x - insets.left - borderSpacing - offset.x;
        }
        
        if (vertical) {
            pos.y = pos.y - insets.top - borderSpacing - offset.y;
        }
    }
}
