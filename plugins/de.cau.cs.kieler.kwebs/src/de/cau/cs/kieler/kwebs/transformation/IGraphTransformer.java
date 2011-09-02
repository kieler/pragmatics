/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.transformation;

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * This interface defines an infrastructure for serialization and deserialization of models 
 * in order to transfer them between a client and the layout web service. The client uses concrete
 * implementations of this interface in order to serialize and deserialize the model to do layout on.
 * The server uses an extension point mechanism to register the supported transformations. Depending on 
 * the format identifier supported by the client when he does a layout request the server acquires a 
 * compatible transformer to derive the model instance from the serialized model and the the graph 
 * structure of the model, represented by a {@code KGraph} instance. After calculating the layout, the 
 * initially derived model instance is applied with the calculated layout before it is being serialized
 * and sent back to the client. 
 *            
 * @kieler.rating  2011-05-04 red
 * @author  swe
 * @param <T> type of created graph objects
 */
public interface IGraphTransformer<T> {

    /**
     * Create a graph instance from serial representation. The returned object may actually
     * contain multiple graphs.
     *
     * @param serializedGraph
     *            the serialized graph
     * @return the graph instance
     */
    T deserialize(String serializedGraph);

    /**
     * Serializes the given graph instance in the format supported 
     * by this transformer.
     *
     * @param graph the graph instance
     *
     * @return serialization of the given graph
     */
    String serialize(T graph);

    /**
     * Returns KGraph representations of the structure of the given graph instance which
     * can be used to calculate the layout of the given graph instance.
     * 
     * @param graph
     *            the graph instance
     * @return KGraph representation of the graph structure
     */
    List<KNode> deriveLayout(T graph);
    
    /**
     * Applies the calculated layouts to the given graph instance.
     * 
     * @param graph
     *            the graph instance
     * @param layout
     *            the calculated layouts
     */
    void applyLayout(T graph, List<KNode> layout);
    
    /**
     * Returns the format supported by this transformer. Formats are defined
     * in {@link Formats}
     *
     * @return the supported format
     */
    String getSupportedFormat();

}
