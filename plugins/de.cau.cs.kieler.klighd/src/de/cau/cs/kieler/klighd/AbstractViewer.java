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
package de.cau.cs.kieler.klighd;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * An abstract base class for viewers which provides an implementation for the handling of
 * listeners and an empty implementation for advanced functionality.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the model this viewer accepts
 */
public abstract class AbstractViewer<T> implements IViewer<T> {

    /** the listeners registered on this viewer. */
    private Set<IViewerEventListener> listeners = new LinkedHashSet<IViewerEventListener>();

    /**
     * {@inheritDoc}
     */
    public void highlight(final Object diagramElement, final long duration) {
        // do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    public void addEventListener(final IViewerEventListener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeEventListener(final IViewerEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies the registered listeners about the occurrence of an event.
     * 
     * @param event
     *            the viewer event
     */
    protected void notifyListeners(final IViewerEvent event) {
        for (IViewerEventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

}
