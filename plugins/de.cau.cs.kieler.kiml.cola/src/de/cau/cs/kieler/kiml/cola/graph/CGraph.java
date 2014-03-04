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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.SWIGTYPE_p_double;
import org.adaptagrams.adaptagrams;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * @author uru
 * 
 */
public class CGraph {

    private Map<KNode, CNode> knodeMap = Maps.newHashMap();
    private Map<KPort, CPort> kportMap = Maps.newHashMap();

    /*
     * Cola collections
     */
    public final RectanglePtrs nodes;
    public final ColaEdges edges;
    public final CompoundConstraintPtrs constraints;
    public final double[] idealEdgeLengths;

    
    protected int nodeIndex = 0;
    protected int edgeIndex = 0;

    public final KNode origin;

    private List<CNode> children;

    /**
     * 
     */
    public CGraph(final KNode graph) {

        origin = graph;

        // a list of rectangles
        nodes = new RectanglePtrs();
        // a list of pairs of array indexes to the nodes array
        edges = new ColaEdges();
        // a list of constraints
        constraints = new CompoundConstraintPtrs();

        children = new ArrayList<CNode>(graph.getChildren().size());

        transformGraph(graph);

        idealEdgeLengths = new double[edgeIndex];
    }

    private void transformGraph(final KNode root) {

        for (KNode n : root.getChildren()) {

            // ignore unconnected nodes
            if (n.getIncomingEdges().isEmpty() && n.getOutgoingEdges().isEmpty()) {
                continue;
            }

            CNode cnode = new CNode(this, n);
            knodeMap.put(n, cnode);
            children.add(cnode);

            // create ports
            for (KPort p : n.getPorts()) {

                CPort port = new CPort(this, p, cnode);
                kportMap.put(p, port);
                cnode.ports.add(port);

            }

        }

        /*
         * Edges
         */
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                CNode srcNode = knodeMap.get(e.getSource());
                CNode tgtNode = knodeMap.get(e.getTarget());
                CPort srcPort = kportMap.get(e.getSourcePort());
                CPort tgtPort = kportMap.get(e.getTargetPort());

                CEdge edge = new CEdge(this, e, srcNode, srcPort, tgtNode, tgtPort);

                srcNode.outgoingEdges.add(edge);
                tgtNode.incomingEdges.add(edge);
            }
        }

        System.out.println("Last edge created: " + edgeIndex);
    }

    /**
     * @return the children
     */
    public List<CNode> getChildren() {
        return children;
    }

    /**
     * @return the nodes
     */
    public RectanglePtrs getNodes() {
        return nodes;
    }

    /**
     * @return the edges
     */
    public ColaEdges getEdges() {
        return edges;
    }

    /**
     * @return the constraints
     */
    public CompoundConstraintPtrs getConstraints() {
        return constraints;
    }

    /**
     * @return the ideal edge lengths.
     */
    public SWIGTYPE_p_double getIdealEdgeLengths() {

        // copy the calculated values to a swig wrapper
        SWIGTYPE_p_double arr = adaptagrams.newDoubleArray(idealEdgeLengths.length);
        for (int i = 0; i < idealEdgeLengths.length; ++i) {
            adaptagrams.doubleArraySet(arr, i, idealEdgeLengths[i]);
        }

        return arr;
    }

    public <T> T getProperty(final IProperty<T> prop) {
        KLayoutData layout = origin.getData(KShapeLayout.class);
        return layout.getProperty(prop);
    }
}
