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

import org.eclipse.swt.widgets.Control;

/**
 * The interface for viewers on unrestricted models.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the model this viewer accepts
 */
public interface IViewer<T> {

    /**
     * Returns the control used by this viewer.
     * 
     * @return the control
     */
    Control getControl();

    /**
     * Sets the input model for this viewer.
     * 
     * @param model
     *            the input model
     */
    void setModel(final T model);

    /**
     * Highlights the given diagram element for the specified duration.
     * 
     * @param diagramElement
     *            the diagram element
     * @param duration
     *            the duration
     */
    void highlight(final Object diagramElement, final long duration);

    /**
     * Adds an event listener to the viewer.
     * 
     * @param listener
     *            the event listener
     */
    void addEventListener(final IViewerEventListener listener);

    /**
     * Removes an event listener from the viewer.
     * 
     * @param listener
     *            the event listener
     */
    void removeEventListener(final IViewerEventListener listener);

}
