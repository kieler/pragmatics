/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p5edges;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

//CHECKSTYLEOFF JavadocStyle

/**
 * Interface for label placement modules.
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     assigned node and port positions</dd>
 *   <dt>Postcondition:</dt><dd>the position of every label is set</dd>
 * </dl>
 * @author jjc
 *
 */
public interface ILabelPlacer extends IAlgorithm {

    /**
     * Calculates label positions for given graph and stores them in it.
     * 
     * @param thelayeredGraph The respective graph
     */
    void placeLabels(LayeredGraph thelayeredGraph);
    
    /**
     * Gives the size of the longest label in the given layer.
     * You should only consider those of source ports.
     * 
     * @param thelayer The layer to check 
     * @return The longest LLabel
     */
    LLabel longestLabel(Layer thelayer);
    
}
