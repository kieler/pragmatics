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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.ui;

import de.cau.cs.kieler.kiml.evol.EvolModel;

/**
 * Interface for listeners of the evolution model.
 *
 * @author bdu
 *
 */
public interface IEvolModelListener {
    
    /**
     * Indicates the type of a model change.
     * 
     * @author bdu
     * 
     */
    public enum ModelChangeType {
        /**
         * The current position was changed.
         */
        SET_POSITION,

        /**
         * The model was reset.
         */
        RESET,

        /**
         * An evolutionary step was performed.
         */
        EVOLVE,

        /**
         * Auto-rating was performed.
         */
        AUTO_RATING,

        /**
         * Rating of the current individual was changed.
         */
        CURRENT_RATING
    }

    /**
     * Notifies that a model change has been performed.
     *
     * @param source
     *            the model
     * @param cause
     *            the cause of the change
     */
    void afterChange(EvolModel source, ModelChangeType cause);

}
