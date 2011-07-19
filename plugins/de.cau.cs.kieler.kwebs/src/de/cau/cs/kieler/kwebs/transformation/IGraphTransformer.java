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
 * .
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public interface IGraphTransformer {

    /**
     * Create KGraph from serial representation. The form of serialization
     * and the underlying meta model is defined by {@code format}.
     *
     * @param serializedGraph
     *            the serialized graph
     * @return KNode
     *            the graph as KGraph
     */
    KNode deserialize(final String serializedGraph);

    /**
     * Serializes the given graph in the format supported by this transformer.
     *
     * @param graph the given graph
     *
     * @return String
     *             serialization of the given graph
     */
    String serialize(final KNode graph);

    /**
     * Returns the format supported by this transformer. Formats are defined
     * in {@link Formats}
     *
     * @return String
     *             the supported format
     */
    String getSupportedFormat();

}
