/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
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
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum LayoutAlgorithm {
    
    /** Sugiyama's layout algorithm. */
    SUGIYAMA,
    /** The planarization layout algorithm. */
    PLANARIZATION,
    /** The FMMM layout algorithm. */
    FMMM,
    /** The Davidson-Harel layout algorithm. */
    DAVIDSON_HAREL,
    /** The Fruchterman-Reingold algorithm. */
    FRUCHTERMAN_REINGOLD,
    /** The GEM algorithm. */
    GEM,
    /** The circular layout algorithm. */
    CIRCULAR,
    /** The tree layout algorithm. */
    TREE,
    /** The radial tree layout algorithm. */
    RADIAL_TREE,
    /** The upward-planarization layout algorithm. */
    UPWARD_PLANARIZATION,
    /** The fast multipole layout algorithm. */
    FAST_MULTIPOLE,
    /** The fast multipole multilevel layout algorithm. */
    FAST_MULTIPOLE_MULTILEVEL,
    /** The Kamada-Kawai layout algorithm. */
    KAMADA_KAWAI,
    /** The stress majorization layout algorithm. */
    STRESS_MAJORIZATION,
    /** The dominance layout algorithm. */
    DOMINANCE,
    /** The visibility layout algorithm. */
    VISIBILITY,
    /** The Fraysseix-Pach-Pollack layout algorithm. */
    FRAYSSEIX_PACH_POLLACK,
    /** The Schnyder layout algorithm. */
    SCHNYDER,
    /** The canonical order layout algorithm by Kant. */
    CANONICAL_ORDER,
    /** The mixed model layout algorithm by Gutwenger and Mutzel. */
    MIXED_MODEL,
    /** The convex grid layout algorithm by Chrobak and Kant. */
    CONVEX_GRID,
    /** The balloon layout algorithm. */
    BALLOON;
    
}
