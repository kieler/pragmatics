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

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.TransformationContext;


/**
 * Transformation proxy realizes the re-initialization of stateful model transformations.
 * This is needed for Xtend2 based transformation leveraging "create extensions".
 * 
 * @param <S> type of the input models
 * @param <T> type of the created models
 *   
 * @author chsch
 */
public class ReinitializingTransformationProxy<S, T> extends AbstractTransformation<S, T> {

    private Injector injector = Guice.createInjector();
    private Class<AbstractTransformation<S, T>> transformationClass = null;
    private ITransformation<S, T> transformationDelegate = null;
    

    /**
     * 
     * @param clazz the transformation class
     */
    public ReinitializingTransformationProxy(final Class<AbstractTransformation<S, T>> clazz) {
        this.transformationClass = clazz;
    }
    
    
    /**
     * {@inheritDoc}<br>
     * Delegates the 'delegate' object.
     */
    public T transform(final S model, final TransformationContext<S, T> transformationContext) {
        this.transformationDelegate = this.injector.getInstance(this.transformationClass); 
        return this.transformationDelegate.transform(model, transformationContext);
    }
    
    
    /**
     * @param model the model
     * @return null
     */
    public T transform(final S model) {
        return null;
    }
    
    
    /**
     * Getter for the delegate attribute.
     * @return the delegate
     */
    protected ITransformation<S, T> getDelegate() {
        return this.transformationDelegate;
    }
    
    
    /**
     * 
     */
    protected void inferSourceAndTargetModelClass() {
        this.setTriedToInferClass();
        AbstractTransformation<S, T> delegate = injector.getInstance(this.transformationClass);        
        if (delegate != null) {
            delegate.inferSourceAndTargetModelClass();
            this.setSourceClass(delegate.getSourceClass());
            this.setTargetClass(delegate.getTargetClass());
        }
    }


}
