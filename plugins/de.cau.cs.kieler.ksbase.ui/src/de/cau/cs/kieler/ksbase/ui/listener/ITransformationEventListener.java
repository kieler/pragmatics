/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.listener;

/**
 * Interface for a transformation listener. This listener has to be registered
 * in the {@link de.cau.cs.kieler.ksbase.ui.TransformationUIManager} and is
 * notified after each transformation.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public interface ITransformationEventListener {

    /**
     * Notifies the listener that a transformation has
     * been executed.
     * @param args List of parameters for the listener.
     */
    void transformationExecuted(Object[] args);
}
