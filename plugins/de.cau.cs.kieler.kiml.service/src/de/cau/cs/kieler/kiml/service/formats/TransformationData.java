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

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * A transformation data object that can be arbitrarily extended using the properties mechanism.
 *
 * @param <S> source graph type
 * @param <T> target graph type
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class TransformationData<S, T> extends MapPropertyHolder {
    
    /** the serial version UID. */
    private static final long serialVersionUID = 1216251172929233162L;
    
    /** the original source graph. */
    private S sourceGraph;
    /** the transformed target graphs. */
    private final List<T> layoutGraphs = new LinkedList<T>();
    /** the log messages. */
    private final List<String> logMessages = new LinkedList<String>();
    
    /**
     * Set the original source graph.
     * 
     * @param thesourceGraph the source graph
     */
    public void setSourceGraph(final S thesourceGraph) {
        this.sourceGraph = thesourceGraph;
    }
    
    /**
     * Returns the original source graph.
     * 
     * @return the source graph
     */
    public S getSourceGraph() {
        return sourceGraph;
    }
    
    /**
     * Returns the transformed target graphs.
     * 
     * @return the target graphs
     */
    public List<T> getTargetGraphs() {
        return layoutGraphs;
    }
    
    /**
     * Report a log message.
     * 
     * @param message a user friendly message
     */
    public void log(final String message) {
        logMessages.add(message);
    }
    
    /**
     * Returns the reported log messages.
     * 
     * @return the log messages
     */
    public Iterable<String> getMessages() {
        return logMessages;
    }

}
