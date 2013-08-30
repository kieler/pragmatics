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
package de.cau.cs.kieler.ksbase.ui;

import java.util.LinkedList;

import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;
import de.cau.cs.kieler.ksbase.ui.utils.TransformationUtils;

/**
 * Transformation-UI manager. Handles creation and execution of commands and notify of
 * transformationEvent listeners
 * 
 * @author mim
 */
public final class TransformationUIManager {

    /** Transformation-UI instance. **/
    public static final TransformationUIManager INSTANCE = new TransformationUIManager();

    /**
     * The list of listeners to notify before and after transformation has been executed.
     **/
    private LinkedList<ITransformationEventListener> transformationEventListeners;

    private TransformationUtils transformationUtils;

    /**
     * The default constructor.
     */
    private TransformationUIManager() {
        transformationEventListeners = new LinkedList<ITransformationEventListener>();
        transformationUtils = new TransformationUtils();
        transformationEventListeners.add(transformationUtils);
    }

    /**
     * Adds a listener to the post-transformation transformation listener queue.
     * 
     * @param listener
     *            The listener to add
     */
    public void addTransformationListener(final ITransformationEventListener listener) {
        transformationEventListeners.add(listener);
    }

    /**
     * Removes a listener from the post-transformation listener queue.
     * 
     * @param listener
     *            The listener to remove.
     */
    public void removeTransformationListener(final ITransformationEventListener listener) {
        transformationEventListeners.remove(listener);
    }

    /**
     * Getter for the transformationEventListeners.
     * 
     * @return the transformationEventListeners
     */
    public LinkedList<ITransformationEventListener> getTransformationEventListeners() {
        return this.transformationEventListeners;
    }

}
