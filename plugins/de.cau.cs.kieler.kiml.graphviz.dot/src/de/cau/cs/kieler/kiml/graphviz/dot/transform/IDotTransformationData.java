/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.graphviz.dot.transform;

import java.util.List;

import de.cau.cs.kieler.core.properties.IPropertyHolder;

/**
 * Interface around the layout format service's {@code TransformationData} class. This is required to
 * eliminate the dependency on the format service.
 * 
 * @param <S> source graph type
 * @param <T> target graph type
 * @author cds
 */
public interface IDotTransformationData<S, T> extends IPropertyHolder {
    
    /**
     * Set the original source graph.
     * 
     * @param srcGraph the source graph
     */
    void setSourceGraph(S srcGraph);
    
    /**
     * Returns the original source graph.
     * 
     * @return the source graph
     */
    S getSourceGraph();
    
    /**
     * Returns the transformed target graphs.
     * 
     * @return the target graphs
     */
    List<T> getTargetGraphs();
    
    /**
     * Report a log message.
     * 
     * @param msg a user friendly message
     */
    void log(String msg);
    
    /**
     * Returns the reported log messages.
     * 
     * @return the log messages
     */
    Iterable<String> getMessages();
}
