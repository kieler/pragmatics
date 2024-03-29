/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.formats;

import org.eclipse.elk.graph.ElkNode;

/**
 * Interface for handlers of graph format transformations.
 *            
 * @param <T> type of handled graphs
 * @author msp
 */
public interface IGraphFormatHandler<T> {

    /**
     * Create a graph instance from a serial representation. The created object is stored as
     * source graph in the given transformation data instance and may actually contain multiple graphs.
     * Afterwards the graph transformer obtained with {@link #getImporter()} can be used to
     * transform the deserialized graph into one or more KGraphs.
     *
     * @param serializedGraph the serialized graph
     * @param transData transformation data for graph import
     */
    void deserialize(String serializedGraph, TransformationData<T, ElkNode> transData);

    /**
     * Serialize the target graphs contained in the given transformation data instance using the
     * format supported by this transformer. Such target graphs can be created using the
     * graph transformer obtained with {@link #getExporter()}.
     *
     * @param transData transformation data for graph export
     * @return serialization of the given graph
     */
    String serialize(TransformationData<ElkNode, T> transData);
    
    /**
     * Return a graph transformer for importing graphs into the KGraph format. Such a transformer
     * can be applied to transformation data instances that have been processed using
     * {@link #deserialize(String, TransformationData)}.
     * 
     * @return a KGraph importer
     */
    IGraphTransformer<T, ElkNode> getImporter();
    
    /**
     * Return a graph transformer for exporting graphs from the KGraph format. Such a transformer
     * can be applied to transformation data instances that are processed using
     * {@code #serialize(TransformationData)} after exporting has finished.
     * 
     * @return a KGraph exporter
     */
    IGraphTransformer<ElkNode, T> getExporter();

}
