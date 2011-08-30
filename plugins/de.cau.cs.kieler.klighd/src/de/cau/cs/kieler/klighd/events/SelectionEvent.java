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
package de.cau.cs.kieler.klighd.events;

import java.util.Collection;

import de.cau.cs.kieler.klighd.IViewer;

/**
 * A viewer event representing a selection or an unselection.
 * 
 * @author mri
 */
public class SelectionEvent extends AbstractViewerEvent {

    /** the selected diagram objects. */
    private Collection<?> diagramObjects;
    /** whether the event represents a selection or an unselection. */
    private boolean selected;

    /**
     * Constructs a selection event.
     * 
     * @param viewer
     *            the source viewer
     * @param diagramObjects
     *            the selected diagram objects
     * @param selected
     *            true if the event represent a selection; false if the event represents an
     *            unselection
     */
    public SelectionEvent(final IViewer<?> viewer, final Collection<?> diagramObjects,
            final boolean selected) {
        super(viewer);
        this.diagramObjects = diagramObjects;
        this.selected = selected;
    }

    /**
     * Returns the selected diagram objects.
     * 
     * @return the selected diagram objects
     */
    public Collection<?> getDiagramObjects() {
        return diagramObjects;
    }

    /**
     * Returns whether this event represents a selection or an unselection.
     * 
     * @return true if this event represents a selection; false if it represents an unselection
     */
    public boolean isSelection() {
        return selected;
    }

}
