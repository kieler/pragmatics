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

import java.util.Stack;

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
    /** the stack of model transformations invoked to obtain the model. */
    private Stack<IModelTransformation<?, ?>> transformations;

    /**
     * Constructs a view context.
     * 
     * @param viewerProvider
     *            the viewer provider for this context
     * @param model
     *            the model for this context
     * @param transformations
     *            the transformations invoked to obtain the model
     */
    public ViewContext(final IViewerProvider viewerProvider, final Object model,
            final Stack<IModelTransformation<?, ?>> transformations) {
        this.viewerProvider = viewerProvider;
        this.model = model;
        this.transformations = transformations;
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

    /**
     * Returns the object in the source model that translates to the given object in the context's
     * model by using the transformations invoked to obtain that model.
     * 
     * @param object
     *            an object in the context's model
     * @return the object in the source model or null if the link could not be made
     */
    public Object getSourceObject(final Object object) {
        Object source = object;
        for (IModelTransformation<?, ?> transformation : transformations) {
            if (source == null) {
                return null;
            }
            source = transformation.getSourceObject(source);
        }
        return source;
    }

}
