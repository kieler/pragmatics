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
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Definition of attraction formula option for the GEM layouter.
 * 
 * @author mri
 */
public enum AttractionFormula {
    /** the Fruchterman-Reingold method. */
    FRUCHTERMAN_REINGOLD,
    /** the GEM method. */
    GEM;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static AttractionFormula valueOf(final int i) {
        return values()[i];
    }
}
