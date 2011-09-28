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
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;


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
    // General Utility
    
    /**
     * Center the given point on one side of a boundary.
     * 
     * @param point a point to change
     * @param boundary the boundary to use for centering
     * @param side the side of the boundary
     */
    public static void centerPoint(final KVector point, final KVector boundary,
            final PortSide side) {
        switch (side) {
        case NORTH:
            point.x = boundary.x / 2;
            point.y = 0;
            break;
        case EAST:
            point.x = boundary.x;
            point.y = boundary.y / 2;
            break;
        case SOUTH:
            point.x = boundary.x / 2;
            point.y = boundary.y;
            break;
        case WEST:
            point.x = 0;
            point.y = boundary.y / 2;
            break;
        }
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
}
