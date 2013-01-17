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
package de.cau.cs.kieler.papyrus.sequence.sorter;

/**
 * Definition of available lifeline sorting strategies for the sequence diagram layouter.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 */
public enum LifelineSortingStrategy {

    /** Sort the lifelines according to their x-coordinates. */
    INTERACTIVE,
    /** Sort the lifelines according to the layers of the associated messages. */
    LAYER_BASED,
    /**
     * Sort the lifelines according to McAllisters solution for the linear arrangement problem that
     * minimizes the total length of messages.
     */
    SHORT_MESSAGES;

    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static LifelineSortingStrategy valueOf(final int i) {
        return values()[i];
    }
}
