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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * A view context contains a viewer provider and a model that is accepted by the viewer provider.
 * 
 * @author mri
 */
public class ViewContext {

    /** the viewer provider. */
    private IViewerProvider viewerProvider;
    /** the model. */
    private Object model;

    /**
     * Constructs a ViewContext.
     * 
     * @param viewerProvider
     *            the viewer provider for this context
     * @param model
     *            the model for this context
     */
    public ViewContext(final IViewerProvider viewerProvider, final Object model) {
        this.viewerProvider = viewerProvider;
        this.model = model;
    }

    /**
     * Returns a viewer for the context's model attached to the given composite. The model is set as
     * input for viewer in this method.
     * 
     * @param parent
     *            the parent composite
     * @return the viewer
     * @see IViewerProvider
     */
    public Viewer createViewer(final Composite parent) {
        if (viewerProvider != null) {
            Viewer viewer = viewerProvider.createViewer(parent);
            viewer.setInput(model);
            return viewer;
        }
        return null;
    }

    /**
     * Returns the context's viewer provider.
     * 
     * @return the viewer provider
     */
    public IViewerProvider getViewerProvider() {
        return viewerProvider;
    }

    /**
     * Returns the context's model.
     * 
     * @return the model
     */
    public Object getModel() {
        return model;
    }

}
