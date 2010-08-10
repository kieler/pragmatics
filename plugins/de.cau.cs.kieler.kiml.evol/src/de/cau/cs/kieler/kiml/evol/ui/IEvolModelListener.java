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

/**
 * Interface for listeners of the evolution model.
 *
 * @author bdu
 *
 */
public interface IEvolModelListener {
    
    /**
     * Notifies that a model change has been performed.
     *
     * @param source
     *            the model
     * @param cause
     *            a string explaining the cause
     */
    void afterChange(EvolModel source, String cause);


}
