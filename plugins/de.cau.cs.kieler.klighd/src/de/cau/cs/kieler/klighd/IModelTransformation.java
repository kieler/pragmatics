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

/**
 * An interface for a model-to-model transformation between two unrestricted models.
 * 
 * @author mri
 * 
 * @param <S>
 *            the type of the source model
 * @param <T>
 *            the type of the target model
 */
public interface IModelTransformation<S, T> {

    /**
     * Performs the actual transformation from an object of type {@code S} to a model of type
     * {@code T}.
     * 
     * @param model
     *            the source model
     * @return the target model
     */
    T transform(final S model);

    /**
     * Returns the object in the source model which is represented by the given object in the target
     * model.
     * 
     * @param object
     *            the object in the target model
     * @return the object in the source model
     */
    Object getSourceObject(final Object object);

    /**
     * Returns the object in the target model which represents the given object in the source model.
     * 
     * @param object
     *            the object in the source model
     * @return the object in the target model
     */
    Object getTargetObject(final Object object);

    /**
     * Returns whether the given model is a valid source for the transformation.
     * 
     * @param model
     *            the model
     * @return true if this transformation supports the given model as a source; false else
     */
    boolean supports(final Object model);

}
