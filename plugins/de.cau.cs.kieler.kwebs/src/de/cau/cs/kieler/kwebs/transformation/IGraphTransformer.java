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
 * This interface defines an infrastructre for de-/serialization of graphs in order to transfer
 * them between a client and the layout web service. The client uses concrete implementations of
 * this interface in order to de-/serialize the graph to be layouted. The server uses an extension
 * point mechanism to register the supported transformations.
 * 
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public interface IGraphTransformer {

    /**
     * Create a {@code KGraph} from serial representation.
     *
     * @param serializedGraph
     *            the serialized graph
     * @return the graph as KGraph
     */
    KNode deserialize(final String serializedGraph);

    /**
     * Serializes the given {@code KGraph} in the format supported by this transformer.
     *
     * @param graph the given graph
     *
     * @return serialization of the given graph
     */
    String serialize(final KNode graph);

    /**
     * Returns the format supported by this transformer. Formats are defined
     * in {@link Formats}
     *
     * @return the supported format
     */
    String getSupportedFormat();

}
