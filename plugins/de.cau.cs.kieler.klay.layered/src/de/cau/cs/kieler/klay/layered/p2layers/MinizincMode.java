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

/**
 * Mode. 
 * 
 * @author uru
 */
public enum MinizincMode {
    /** Both, dummy and reverse weights are set statically. */
    STATIC, 
    /** The reverse weight is reduced for edges part of sccs. */
    STATIC_SCC, 
    /** An edge betweenness value is calculated and used as weight for reversed edges. */
    BETWEENNESS, 
    /** The edge betweenness value is used for dummy and reverse weights. */
    BETWEENNESS_BOTH
}
