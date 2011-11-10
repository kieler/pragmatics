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

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * An abstract base class for KLighD model transformations.<br>
 * Provides a {@code transform} method with a simpler signature.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public abstract class AbstractTransformation<S, T> implements ITransformation<S, T> {

    /** the current transformation context. */
    private TransformationContext<S, T> context = null;

    /**
     * {@inheritDoc}
     */
    public T transform(final S model, final TransformationContext<S, T> transformationContext) {
        context = transformationContext;
        T targetModel = transform(transformationContext.getSourceModel());
        context = null;
        return targetModel;
    }

    /**
     * Performs the actual transformation from an object of type {@code S} to a model of type
     * {@code T}.
     * 
     * @param model
     *            the source model
     * @return the target model
     */
    public abstract T transform(final S model);

    /**
     * Returns the current transformation context this transformation is executing in.
     * 
     * @return the transformation context while executing the transform method; null else
     */
    public TransformationContext<S, T> getTransformationContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<S, T> transformationContext) {
        context = transformationContext;
        Object sourceElement = getSourceElement(element);
        context = null;
        return sourceElement;
    }
    
    /**
     * Returns the element in the source model which is represented by the given element in the
     * target model.
     * 
     * @param element
     *            the element in the target model
     * @return the element in the source model or null if the element could not be found
     */
    public Object getSourceElement(final Object element) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<S, T> transformationContext) {
        context = transformationContext;
        Object targetElement = getTargetElement(element);
        context = null;
        return targetElement;
    }
    
    /**
     * Returns the element in the target model which represents the given element in the source
     * model.
     * 
     * @param element
     *            the element in the source model
     * @return the element in the target model or null if the element could not be found
     */
    public Object getTargetElement(final Object element) {
        return null;
    }

    /** the name of the {@code transform} method. */
    private static final String TRANSFORM_METHOD_NAME = "transform";

    /** whether it has been tried to infer the classes. */
    private boolean triedToInferClasses = false;
    /** the infered source model class. */
    private Class<?> sourceModelClass = null;
    /** the infered target model class. */
    private Class<?> targetModelClass = null;

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return sourceModelClass;
    }
    
    /**
     * Setter for the sourceModelClass property.
     * @param theSourceClass the class of the source models.
     */
    protected void setSourceClass(final Class<?> theSourceClass) {
        this.sourceModelClass = theSourceClass;
    }
    
    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return targetModelClass;
    }  
    
    /**
     * Setter for the sourceModelClass property.
     * @param theTargetClass the class of the source models.
     */
    protected void setTargetClass(final Class<?> theTargetClass) {
        this.targetModelClass = theTargetClass;
    }   
    
    /**
     * Setter for the triedToInferClasses flag.
     */
    protected void setTriedToInferClass() {
        this.triedToInferClasses = true;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        return true;
    }

    /**
     * Tries to infer the class of the source and target model by analyzing the transform method.
     */
    protected void inferSourceAndTargetModelClass() {
        triedToInferClasses = true;
        // try to find a method with one parameter which returns non-void
        // takes the first matching method if the parameter is not Object
        Method transformMethod = null;
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.getName().equals(TRANSFORM_METHOD_NAME)
                    && method.getParameterTypes().length == 1
                    && !method.getReturnType().equals(Void.TYPE)) {
                transformMethod = method;
                // keep searching if the parameter is of type Object
                // this is necessary to skip the method with type Object that is always present when
                // dealing with generic typed methods
                if (!method.getParameterTypes()[0].equals(new Object().getClass())) {
                    break;
                }
            }
        }
        // infer the types if a matching method has been found
        if (transformMethod != null) {
            sourceModelClass = transformMethod.getParameterTypes()[0];
            targetModelClass = transformMethod.getReturnType();
        }
    }

}
