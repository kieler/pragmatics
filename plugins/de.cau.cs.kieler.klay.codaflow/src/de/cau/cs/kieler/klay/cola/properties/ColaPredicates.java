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
package de.cau.cs.kieler.klay.cola.properties;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;

/**
 * @author uru
 *
 */
public final class ColaPredicates {

    /**
     * 
     */
    private ColaPredicates() {
    }
    
    public static Predicate<CEdge> PREDICATE_HIERARCHICAL_EDGE = new Predicate<CEdge>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(final CEdge edge) {
            return edge.crossHierarchy;
        }
    };
    
    public static Predicate<CEdge> PREDICATE_ACA_ALIGNED_EDGE = new Predicate<CEdge>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(final CEdge edge) {
            return edge.getProperty(InternalColaProperties.ACA_EDGE_ALIGNED);
        }
    };
    
}
