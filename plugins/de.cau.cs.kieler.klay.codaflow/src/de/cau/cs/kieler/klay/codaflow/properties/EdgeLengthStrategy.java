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
package de.cau.cs.kieler.klay.codaflow.properties;

/**
 * @author uru
 */
public enum EdgeLengthStrategy {

    /**
     * Ideal edge lengths of <b>all</b> edges are specified based on the value of
     * {@link de.cau.cs.kieler.klay.cola.properties.ColaProperties#IDEAL_EDGE_LENGTHS
     * ColaProperties#IDEAL_EDGE_LENGTHS}.
     */
    UNIFORM,

    /**
     * For each edge its ideal length is determined by the degree of its source and target node.
     * Edges connecting nodes with high degree will thus get assigned a longer ideal edge length.
     */
    CONNECTIVITY,

    /**
     * The same strategy is used as for {@link EdgeLengthStrategy#CONNECTIVITY}, additionally all
     * calculated edge lengths are limited to be smaller than the specified value of
     * {@link de.cau.cs.kieler.kiml.options.LayoutOptions#SPACING LayoutOptions#SPACING}.
     * 
     * If edge lengths l_orig are calculated that span the interval [min, max] and let the spacing
     * be s, then for each edge e, its ideal edge length l_new is calculated by l_new = l_orig *
     * (spacing / max).
     */
    CONNECTIVITY_CAPPED,
    
    /**
     * TODO document.
     */
    SYMMDIFF
}
