/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.cola.graph;

import org.adaptagrams.ColaEdge;

import de.cau.cs.kieler.core.kgraph.KEdge;

/**
 * @author uru
 * 
 */
public class CEdge {

    public final int cIndex;

    public final CNode src;
    public final CNode tgt;
    public final CPort srcPort;
    public final CPort tgtPort;

    public final ColaEdge edge;

    /**
     * 
     */
    public CEdge(final CGraph graph, final KEdge origin, final CNode src, final CPort srcPort,
            final CNode tgt, final CPort tgtPort) {

        this.src = src;
        this.tgt = tgt;
        this.srcPort = srcPort;
        this.tgtPort = tgtPort;

        int srcIndex;
        if (srcPort != null) {
            srcIndex = srcPort.cIndex;
        } else {
            srcIndex = src.cIndex;
        }

        int tgtIndex;
        if (tgtPort != null) {
            tgtIndex = tgtPort.cIndex;
        } else {
            tgtIndex = tgt.cIndex;
        }

        // create the cola representation
        edge = new ColaEdge(srcIndex, tgtIndex);
        cIndex = graph.edgeIndex++;

        // add to the graph
        graph.edges.add(edge);
    }
    
    /**
     * @return the src
     */
    public CNode getSrc() {
        return src;
    }
    
    /**
     * @return the tgt
     */
    public CNode getTgt() {
        return tgt;
    }

}
