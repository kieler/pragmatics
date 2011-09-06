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
package de.cau.cs.kieler.klighd.triggers;

import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * An abstract base class for KLighD trigger states containing information about the context in
 * which the trigger for this trigger state fired.
 * 
 * @author mri
 */
public abstract class AbstractKlighdTriggerState extends AbstractTriggerState {

    /** the id of the view associated with this selection. */
    private String viewId = null;
    /** the view context in which the selection occurred. */
    private ViewContext viewContext = null;
    /** the viewer in which the selection occurred. */
    private IViewer<?> viewer = null;

    /**
     * Constructs an abstract KLighD trigger state from no context.
     */
    public AbstractKlighdTriggerState() {
        // do nothing
    }
    
    /**
     * Constructs an abstract KLighD trigger state connected with a view, viewer and a view context
     * in which the trigger that lead to this trigger state fired.
     * 
     * @param viewId
     *            the id of the view
     * @param viewContext
     *            the view context
     * @param viewer
     *            the viewer
     */
    public AbstractKlighdTriggerState(final String viewId, final ViewContext viewContext,
            final IViewer<?> viewer) {
        this.viewId = viewId;
        this.viewContext = viewContext;
        this.viewer = viewer;
    }

    /**
     * Returns the id of the view associated with this selection.
     * 
     * @return the id of view
     */
    public String getViewId() {
        return viewId;
    }

    /**
     * Returns the view context in which the selection occurred.
     * 
     * @return the view context or null if no selection occurred so far
     */
    public ViewContext getViewContext() {
        return viewContext;
    }

    /**
     * Returns the viewer in which the selection occurred.
     * 
     * @return the viewer or null if no selection occurred so far
     */
    public IViewer<?> getViewer() {
        return viewer;
    }

}
