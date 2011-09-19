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
    /** the list of model transformations invoked to obtain the model. */
    private List<IModelTransformation<?, ?>> transformations;
    /** the reveresed list of model transformations invoked to obtain the model. */
    private List<IModelTransformation<?, ?>> transformationsRev;
    /** id of the source file. */
    private String fileId;

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
            final List<IModelTransformation<?, ?>> transformations) {
        this.viewerProvider = viewerProvider;
        this.model = model;
        this.transformations = transformations;
        for (IModelTransformation<?, ?> trans : transformations) {
            trans.setViewContext(this);
        }
        this.transformationsRev = new LinkedList<IModelTransformation<?, ?>>(transformations);
        Collections.reverse(transformationsRev);
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
     * Returns the element in the source model that translates to the given object in the context's
     * model by using the transformations invoked to obtain that model.
     * 
     * @param object
     *            the object in the context's model
     * @return the element in the source model or null if the link could not be made
     */
    public Object getSourceElement(final Object object) {
        Object source = object;
        for (IModelTransformation<?, ?> transformation : transformationsRev) {
            if (source == null) {
                return null;
            }
            source = transformation.getSourceElement(source);
        }
        return source;
    }

    /**
     * Returns the element in the context's model that derives from the given element in the source
     * model by using the transformations invoked to obtain the context's model.
     * 
     * @param object
     *            the element in the source model
     * @return the element in the context's model or null if the link could not be made
     */
    public Object getTargetElement(final Object object) {
        Object target = object;
        for (IModelTransformation<?, ?> transformation : transformations) {
            if (target == null) {
                return null;
            }
            target = transformation.getTargetElement(target);
        }
        return target;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return this.fileId;
    }
}
