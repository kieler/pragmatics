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

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * This interface defines an infrastructure for serialization and deserialization of graphs 
 * in order to transfer them between a client and the layout web service. The client uses concrete
 * implementations of this interface in order to serialize and deserialize the graph to do layout on.
 * The server uses an extension point mechanism to register the supported transformations. Depending on 
 * the format identifier supported by the client when he does a layout request the server acquires a 
 * compatible transformer to derive a {@code KGraph} representation of the graph to do layout on. After
 * calculation of the layout on the derived {@code KGraph} the server applies the layout back to the 
 * original graph instance.
 *            
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public interface IGraphTransformer {

    /**
     * Create a graph instance from serial representation.
     *
     * @param serializedGraph
     *            the serialized graph
     * @return the graph instance
     */
    Object deserialize(String serializedGraph);

    /**
     * Serializes the given graph instance in the format supported 
     * by this transformer.
     *
     * @param graph the graph instance
     *
     * @return serialization of the given graph
     */
    String serialize(Object graph);

    /**
     * Returns a KGraph representation of the structure of the given graph instance which
     * can be used to calculate the layout of the given graph instance.
     * 
     * @param graph
     *            the graph instance
     * @return KGraph representation of the graph structure
     */
    KNode deriveLayout(Object graph);
    
    /**
     * Applies the calculated layout to the given graph instance.
     * 
     * @param graph
     *            the graph instance
     * @param layout
     *            the calculated layout
     */
    void applyLayout(Object graph, KNode layout);
    
    /**
     * Returns the format supported by this transformer. Formats are defined
     * in {@link Formats}
     *
     * @return the supported format
     */
    String getSupportedFormat();

}
