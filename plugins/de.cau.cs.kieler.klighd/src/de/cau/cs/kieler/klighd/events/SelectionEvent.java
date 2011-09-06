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
 * A viewer event representing a selection or unselection which contains a list of currently
 * selected diagram elements.
 * 
 * @author mri
 */
public class SelectionEvent extends AbstractViewerEvent {

    /** the selected diagram elements. */
    private Collection<?> diagramElements;

    /**
     * Constructs a selection event.
     * 
     * @param viewer
     *            the source viewer
     * @param diagramElements
     *            the selected diagram elements
     */
    public SelectionEvent(final IViewer<?> viewer, final Collection<?> diagramElements) {
        super(viewer);
        this.diagramElements = diagramElements;
    }

    /**
     * Returns the selected diagram elements.
     * 
     * @return the selected diagram elements
     */
    public Collection<?> getDiagramElements() {
        return diagramElements;
    }

}
