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
import java.util.Collections;
import java.util.Set;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.TransformationOption;

/**
 * An abstract base class for KLighD model transformations.<br>
 * <br>
 * Some hints for the use with Xtend2:<br>
 * If your custom view synthesis transformation is written in Xtend2 and leverages <b>create
 * extensions</b> or <b>dependency injection</b> with Google Guice, please note the hint in
 * {@link ReinitializingTransformationProxy} on registering such transformations.<br>
 * <br>
 * If a transformation implementation incorporates helper transformations containing shared or
 * outsourced parts and the outsourced ones need to have access to the main transformation instance,
 * e.g. for accessing the transformation context, this can be realized by means of Guice, too. The
 * helper transformation implementation must declare an injected field of type
 * AbstractTransformation&lt;?, ?&gt;, the main transformation must be annotated with &#64;Singleton.
 * This way the helper classes are provided with the current instance of the main transformation. <br>
 * <br>
 * Furthermore, transformations may leverage other ones, e.g. for realizing composed views. This can
 * be achieved by simply declaring an injected field ore extension and calling the related
 * {@link #transform(Object, TransformationContext)} method. If multiple instance of such a delegate
 * transformation are needed (e.g. due to the use of create extensions) a field of type
 * {@link com.google.inject.Provider Provider&lt;yourTransformationClass&gt;} can be declared. Each
 * time calling {@link com.google.inject.Provider#get() get()} on this provider a new instance will
 * be obtained as long as the provided class is <b>not</b> declared as singleton (via &#64;Singleton).
 * 
 * @author mri, chsch
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public abstract class AbstractTransformation<S, T> implements ITransformation<S, T> {
    
    private TransformationContext<S, T> currentContext = null;
    
    /**
     * Initializes the transformation run.
     * Currently, just keeps the context to be used
     * (allowing to neglect it in the concrete transformation methods).
     * 
     * @param transformationContext the context to be used during the current run
     */
    protected void use(final TransformationContext<S, T> transformationContext) {        
        this.currentContext = transformationContext;
        this.currentContext.clear();
    }
    
    /**
     * Getter.
     * 
     * @return the currently used transformation context or <code>null</code> if no one is set.
     */
    protected TransformationContext<S, T> getUsedContext() {
        return this.currentContext;
    }

    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link TransformationOption} option.
     */
    public Object getOptionValue(final TransformationOption option) {
        return this.getUsedContext().getOptionValue(option);
    }
    
    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link TransformationOption} option.
     */
    public boolean getOptionBooleanValue(final TransformationOption option) {
        Object result = this.getUsedContext().getOptionValue(option);
        if (result instanceof Boolean) {
            return (Boolean) result; 
        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Boolean valued transformation option "
                    + option.getName() + " expecting a Boolean value.");
        }
    }
    
    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link TransformationOption} option.
     */
    public int getOptionIntValue(final TransformationOption option) {
        Object result = this.getUsedContext().getOptionValue(option);
        if (result instanceof Integer) {
            return (Integer) result; 
        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Integer valued transformation option "
                    + option.getName() + " expecting a int value.");
        }
    }
    
    /**
     * Convenience getter.
     * 
     * @param option the option to evaluate the configuration state / the configured value.
     * @return the configured value of {@link TransformationOption} option.
     */
    public float getOptionFloatValue(final TransformationOption option) {
        Object result = this.getUsedContext().getOptionValue(option);
        if (result instanceof Float) {
            return (Float) result; 
        } else {
            throw new IllegalArgumentException("KLighD transformation option handling: "
                    + "The transformation " + this
                    + " attempted to evaluate the non-Float valued transformation option "
                    + option.getName() + " expecting a float value.");
        }
    }
    
    /**
     * Method to put a pair of source target into the lookup table.<br>
     * Name, Parameter ordering, and return value (the target) are optimized for
     * calling in Xtend2 based transformations in a fluent interface fashion, like
     * "model.createShape().putToLookUpWith(model);"<br>
     * <br>
     * Usage requires to perform 'use(TransformationContext)' at the beginning of
     * {@link ITransformation#transform(Object, TransformationContext)}.
     * 
     * @param <D> the type of the target element which is implicitly determined 
     * @param derived the derived element
     * @param source the model element
     * @return the image element
     */
    public <D> D putToLookUpWith(final D derived, final Object source) {
        if (this.currentContext == null) {
            throw new IllegalStateException("KLighD transformation "
                    + this.getClass().getCanonicalName()
                    + " uses 'putToLookUp(...) and probably does not invoke"
                    + "'use(TransformationContxt)' at the beginning of its 'transform()' method");
        }
        this.currentContext.addSourceTargetPair(source, derived);
        return derived;
    }

    
    /** whether it has been tried to infer the classes. */
    private boolean triedToInferClasses = false;
    /** the inferred source model class. */
    private Class<?> sourceModelClass = null;
    /** the inferred target model class. */
    private Class<?> targetModelClass = null;

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        if (!triedToInferClass()) {
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
        if (!triedToInferClass()) {
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
     * Getter for the triedToInferClasses flag.
     * 
     * @return true if source and target classes have been tried to infer
     */
    protected boolean triedToInferClass() {
        return this.triedToInferClasses;
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
     * {@inheritDoc}
     * 
     * Concrete transformations are supposed to override this method in order to register dedicated
     * {@link TransformationOption TransformationOptions}. 
     */
    public Set<TransformationOption> getTransformationOptions() {
        return Collections.emptySet();
    }

    /** the name of the {@code transform} method. */
    protected static final String TRANSFORM_METHOD_NAME = "transform";

    /**
     * Tries to infer the class of the source and target model by analyzing the transform method.
     */
    protected void inferSourceAndTargetModelClass() {
        triedToInferClasses = true;
        // try to find a method with two parameters which returns non-void
        // takes the first matching method with parameter 0 != Object
        Method transformMethod = null;
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.getName().equals(TRANSFORM_METHOD_NAME)
                    && method.getParameterTypes().length == 2
                    && !method.getReturnType().equals(Void.TYPE)
                    && !method.getReturnType().equals(Object.class)) {
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
