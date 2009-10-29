/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.slimgraph;

import java.util.LinkedList;
import java.util.List;

/**
 * The general-purpose slim graph structure. This structure is specially
 * designed for use in graph algorithms for undirected graphs, because each node
 * has only one incidence list for both the incoming and the outgoing edges.
 * However, the direction of each edge can be obtained by a respective entry in
 * the incidence list of the respective node.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KSlimGraph {

    /** list of nodes in this TSM graph. */
    public final List<KSlimNode> nodes = new LinkedList<KSlimNode>();
    /** list of edges in this TSM graph. */
    public final List<KSlimEdge> edges = new LinkedList<KSlimEdge>();
    /** list of internal faces in this TSM graph. */
    public final List<KSlimFace> faces = new LinkedList<KSlimFace>();
    /** the external face of this TSM graph. */
    public KSlimFace externalFace = new KSlimFace(this, false);

    /** total width of the graph. */
    public float width;
    /** total height of the graph. */
    public float height;

    /** next available identifier for nodes. */
    int nextNodeId = 0;
    /** next available identifier for edges. */
    int nextEdgeId = 0;
    /** next available identifier for faces. */
    int nextFaceId = 0;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SlimGraph(" + nodes.size() + " nodes, " + edges.size()
                + " edges, " + (faces.size() + 1) + " faces)";
    }

}
