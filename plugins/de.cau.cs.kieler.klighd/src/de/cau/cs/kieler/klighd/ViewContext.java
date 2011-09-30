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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * A view context contains a viewer provider and a model that is accepted by the viewer provider.
 * 
 * @author mri
 */
public final class ViewContext extends MapPropertyHolder {

    /** the viewer provider. */
    private IViewerProvider viewerProvider;
    /** the list of transformation contexts in this view context. */
    private List<TransformationContext<?, ?>> transformationContexts;
    /** the reveresed list of transformation contexts. */
    private List<TransformationContext<?, ?>> transformationContextsRev;

    /**
     * Constructs a view context.
     * 
     * @param viewerProvider
     *            the viewer provider for this context
     * @param transformationContexts
     *            the transformation contexts involved
     */
    public ViewContext(final IViewerProvider viewerProvider,
            final List<TransformationContext<?, ?>> transformationContexts) {
        this.viewerProvider = viewerProvider;
        this.transformationContexts = transformationContexts;
        this.transformationContextsRev =
                new LinkedList<TransformationContext<?, ?>>(transformationContexts);
        Collections.reverse(transformationContextsRev);
        // set view context in the transformation contexts
        for (TransformationContext<?, ?> transformationContext : transformationContexts) {
            transformationContext.setViewContext(this);
        }
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
     * Returns the context's target model.
     * 
     * @return the target model
     */
    public Object getModel() {
        return transformationContextsRev.get(0).getTargetModel();
    }

    /**
     * Returns the element in the source model that translates to the given object in the context's
     * model by using the transformations invoked to obtain that model.
     * 
     * @param element
     *            the object in the context's model
     * @return the element in the source model or null if the link could not be made
     */
    public Object getSourceElement(final Object element) {
        Object source = element;
        for (TransformationContext<?, ?> transformationContext : transformationContexts) {
            if (source == null) {
                return null;
            }
            source = transformationContext.getSourceElement(element);
        }
        return source;
    }

    /**
     * Returns the element in the context's model that derives from the given element in the source
     * model by using the transformations invoked to obtain the context's model.
     * 
     * @param element
     *            the element in the source model
     * @return the element in the context's model or null if the link could not be made
     */
    public Object getTargetElement(final Object element) {
        Object target = element;
        for (TransformationContext<?, ?> transformationContext : transformationContexts) {
            if (target == null) {
                return null;
            }
            target = transformationContext.getTargetElement(element);
        }
        return target;
    }

}
