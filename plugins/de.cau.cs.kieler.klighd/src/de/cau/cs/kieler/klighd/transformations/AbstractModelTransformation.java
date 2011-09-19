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
package de.cau.cs.kieler.klighd.transformations;

import java.lang.reflect.Method;

import de.cau.cs.kieler.klighd.IModelTransformation;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * The abstract base class for KLighD model transformations.<br>
 * Provides base implementations for the mapping methods {@code getSourceElement} and
 * {@code getTargetElement}. Uses Java reflections to implement the {@code supports} method
 * depending on the type of the parameter to {@code transform}.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public abstract class AbstractModelTransformation<S, T> implements IModelTransformation<S, T> {

    /** the name of the 'transform' method. */
    private static final String TRANSFORM_METHOD_NAME = "transform"; 
    
    /** the cached class of the first parameter to the transform method. */
    private Class<?> firstParamClass = null;
    
    /**
     * {@inheritDoc}
     */
    public T transform(final S model, final Object... params) {
        return transform(model);
    }
    
    /**
     * Performs the actual transformation from an object of type {@code S} to a model of type
     * {@code T} without any special environment parameters.
     * 
     * @param model
     *            the source model
     * @return the target model
     */
    public T transform(final S model) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object object) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object object) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        // search for the class of the first parameter to the transform method if necessary
        if (firstParamClass == null) {
            for (Method method : getClass().getMethods()) {
                if (method.getName().equals(TRANSFORM_METHOD_NAME)
                        && method.getParameterTypes().length > 0) {
                    firstParamClass = method.getParameterTypes()[0];
                    break;
                }
            }
        }
        // check if the given object is an instance of the parameters class
        if (firstParamClass != null) {
            return firstParamClass.isInstance(model);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void setViewContext(final ViewContext viewContext) {
        // do nothing   
    }

}
