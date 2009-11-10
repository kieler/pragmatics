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
    private final List<KSlimNode> nodes = new LinkedList<KSlimNode>();
    /** list of edges in this TSM graph. */
    private final List<KSlimEdge> edges = new LinkedList<KSlimEdge>();
    /** list of internal faces in this TSM graph. */
    private final List<KSlimFace> faces = new LinkedList<KSlimFace>();
    /** the external face of this TSM graph. */
    private KSlimFace externalFace = new KSlimFace(this, false);

    /** total width of the graph. */
    private float width;
    /** total height of the graph. */
    private float height;

    /** next available identifier for nodes. */
    private int nextNodeId = 0;
    /** next available identifier for edges. */
    private int nextEdgeId = 0;
    /** next available identifier for faces. */
    private int nextFaceId = 0;
    
    /**
     * Returns the next node identifier and increases it by one.
     * 
     * @return the next node id
     */
    int nextNodeId() {
        return nextNodeId++;
    }
    
    /**
     * Returns the next edge identifier and increases it by one.
     * 
     * @return the next edge id
     */
    int nextEdgeId() {
        return nextEdgeId++;
    }
    
    /**
     * Returns the next face identifier and increases it by one.
     * 
     * @return the next face id
     */
    int nextFaceId() {
        return nextFaceId++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SlimGraph(" + getNodes().size() + " nodes, " + getEdges().size()
                + " edges, " + (getFaces().size() + 1) + " faces)";
    }

    /**
     * Returns the nodes.
     *
     * @return the nodes
     */
    public List<KSlimNode> getNodes() {
        return nodes;
    }

    /**
     * Returns the edges.
     *
     * @return the edges
     */
    public List<KSlimEdge> getEdges() {
        return edges;
    }

    /**
     * Returns the faces.
     *
     * @return the faces
     */
    public List<KSlimFace> getFaces() {
        return faces;
    }

    /**
     * Sets the externalFace.
     *
     * @param theexternalFace the externalFace to set
     */
    public void setExternalFace(final KSlimFace theexternalFace) {
        this.externalFace = theexternalFace;
    }

    /**
     * Returns the externalFace.
     *
     * @return the externalFace
     */
    public KSlimFace getExternalFace() {
        return externalFace;
    }

    /**
     * Sets the width.
     *
     * @param thewidth the width to set
     */
    public void setWidth(final float thewidth) {
        this.width = thewidth;
    }

    /**
     * Returns the width.
     *
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets the height.
     *
     * @param theheight the height to set
     */
    public void setHeight(final float theheight) {
        this.height = theheight;
    }

    /**
     * Returns the height.
     *
     * @return the height
     */
    public float getHeight() {
        return height;
    }

}
