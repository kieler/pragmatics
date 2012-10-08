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
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Enumeration of available embedder modules for planarization layout.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum EmbedderModule {
    
    /** the simple embedder module. */
    SIMPLE,
    /** the maximal external face embedder module. */
    MAX_FACE,
    /** the maximal external face with layers approach embedder module. */
    MAX_FACE_LAYERS,
    /** the minimum block-nesting depth embedder module. */
    MIN_DEPTH,
    /** the minimum block-nesting depth, maximal external face embedder module. */
    MIN_DEPTH_MAX_FACE,
    /** the minimum block-nesting depth, maximal external face with layers approach embedder module. */
    MIN_DEPTH_MAX_FACE_LAYERS;

}
