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

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerEvent;

/**
 * The abstract base class for viewer events.
 * 
 * @author mri
 */
public abstract class AbstractViewerEvent implements IViewerEvent {

    /** the source viewer. */
    private IViewer<?> viewer;

    /**
     * Constructs an abstract viewer event.
     * 
     * @param viewer
     *            the source viewer
     */
    public AbstractViewerEvent(final IViewer<?> viewer) {
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     */
    public IViewer<?> getSource() {
        return viewer;
    }

}
