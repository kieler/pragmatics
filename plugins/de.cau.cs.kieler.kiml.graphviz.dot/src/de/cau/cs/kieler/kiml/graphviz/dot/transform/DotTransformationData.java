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

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * Pretty much a copy of the format service's {@code TransformationData} class. This class is necessary
 * to keep GraphViz Dot itself independent from the formats service.
 * 
 * @param <S> source graph type.
 * @param <T> target graph type.
 * @author cds
 */
public final class DotTransformationData<S, T> extends MapPropertyHolder
        implements IDotTransformationData<S, T> {

    /** Serialization ID. */
    private static final long serialVersionUID = 2334583523009191492L;
    
    /** the original source graph. */
    private S sourceGraph;
    /** the transformed target graphs. */
    private final List<T> layoutGraphs = new LinkedList<T>();
    /** the log messages. */
    private final List<String> logMessages = new LinkedList<String>();
    

    @Override
    public void setSourceGraph(final S srcGraph) {
        this.sourceGraph = srcGraph;
    }

    @Override
    public S getSourceGraph() {
        return sourceGraph;
    }

    @Override
    public List<T> getTargetGraphs() {
        return layoutGraphs;
    }

    @Override
    public void log(final String msg) {
        logMessages.add(msg);
    }

    @Override
    public Iterable<String> getMessages() {
        return logMessages;
    }
}
