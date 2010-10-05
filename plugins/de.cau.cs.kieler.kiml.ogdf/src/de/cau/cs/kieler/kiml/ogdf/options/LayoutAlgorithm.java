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
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Definition of available layout algorithms.
 * 
 * @author mri
 */
public enum LayoutAlgorithm {
    
    /** Sugiyama's layout algorithm. */
    SUGIYAMA,
    /** The planarization layout algorithm. */
    UMLPLANARIZATION,
    /** The FMMM layout algorithm. */
    FMMM,
    /** The Davidson-Harel layout algorithm. */
    DAVIDSON_HAREL,
    /** The Fruchterman-Reingold algorithm. */
    FRUCHTERMAN_REINGOLD,
    /** The circular layout algorithm. */
    CIRCULAR,
    /** The tree layout algorithm. */
    TREE,
    /** The radial tree layout algorithm. */
    RADIAL_TREE,
    /** The upward-planarization layout algorithm. */
    UPWARD_PLANARIZATION;
    
}
