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

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Interface for handlers of graph format transformations.
 *            
 * @param <T> type of handled graphs
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public interface ITransformationHandler<T> {

    /**
     * Create a graph instance from a serial representation. The created object is stored as
     * source graph in the given transformation data instance and may actually contain multiple graphs.
     * Afterwards the graph transformer obtained with {@link #getImporter()} can be used to
     * transform the deserialized graph into one or more KGraphs.
     *
     * @param serializedGraph the serialized graph
     * @param transData transformation data for graph import
     */
    void deserialize(String serializedGraph, TransformationData<T, KNode> transData);

    /**
     * Serialize the target graphs contained in the given transformation data instance using the
     * format supported by this transformer. Such target graphs can be created using the
     * graph transformer obtained with {@link #getExporter()}.
     *
     * @param transData transformation data for graph export
     * @return serialization of the given graph
     */
    String serialize(TransformationData<KNode, T> transData);
    
    /**
     * Return a graph transformer for importing graphs into the KGraph format. Such a transformer
     * can be applied to transformation data instances that have been processed using
     * {@link #deserialize(String, TransformationData)}.
     * 
     * @return a KGraph importer
     */
    IGraphTransformer<T, KNode> getImporter();
    
    /**
     * Return a graph transformer for exporting graphs from the KGraph format. Such a transformer
     * can be applied to transformation data instances that are processed using
     * {@code #serialize(TransformationData)} after exporting has finished.
     * 
     * @return a KGraph exporter
     */
    IGraphTransformer<KNode, T> getExporter();

}
