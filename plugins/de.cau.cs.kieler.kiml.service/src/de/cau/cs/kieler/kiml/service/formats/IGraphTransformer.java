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

package de.cau.cs.kieler.kiml.service.formats;

/**
 * Interface for graph transformations.
 *
 * @param <S> source graph type
 * @param <T> target graph type
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public interface IGraphTransformer<S, T> {
    
    /**
     * Transform the source graph structure into one ore more instances of the target
     * graph type.
     * 
     * @param data the transformation data instance that holds the source graph
     *     and is enriched with the new target graphs
     */
    void transform(TransformationData<S, T> data);
    
    /**
     * Apply the layout of the target graphs to the original source graph instance. This
     * may only be used on target graphs that were created by the same transformation class.
     * 
     * @param data the transformation data instance
     */
    void transferLayout(TransformationData<S, T> data);

}
