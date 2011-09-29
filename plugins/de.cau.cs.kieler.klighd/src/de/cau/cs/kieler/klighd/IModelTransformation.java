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
     * @param params
     *            special parameter for the transformation
     * @return the target model
     */
    T transform(final S model, Object... params);

    /**
     * Returns the element in the source model which is represented by the given element in the
     * target model.
     * 
     * @param object
     *            the element in the target model
     * @return the element in the source model or null if the element could not be found
     */
    Object getSourceElement(final Object object);

    /**
     * Returns the element in the target model which represents the given element in the source
     * model.
     * 
     * @param object
     *            the element in the source model
     * @return the element in the target model or null if the element could not be found
     */
    Object getTargetElement(final Object object);

    /**
     * Returns whether the given model is a valid source for the transformation.
     * 
     * @param model
     *            the model
     * @return true if this transformation supports the given model as a source; false else
     */
    boolean supports(final Object model);

    /**
     * Can be used to set the dependency between transformation and viewContext.
     * 
     * @param viewContext
     *            the view context
     */
    void setViewContext(final ViewContext viewContext);

    /**
     * the file id helps to identify a source-, graphical model mapping for a special file. 
     * @param fileId
     */
    void setFileId(String fileId);

}
