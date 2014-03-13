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
package de.cau.cs.kieler.klay.cola.graph;

import java.util.ArrayList;
import java.util.List;

import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.RootCluster;
import org.adaptagrams.SWIGTYPE_p_double;
import org.adaptagrams.adaptagrams;

import com.google.common.collect.Lists;

/**
 * @author uru
 * 
 */
public class CGraph extends CGraphElement {

    private static final long serialVersionUID = -5599764636700647978L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    /*
     * Cola collections
     */
    public final RectanglePtrs nodes;
    public final ColaEdges edges;
    public final CompoundConstraintPtrs constraints;
    public double[] idealEdgeLengths;
    public final RootCluster rootCluster;

    protected int nodeIndex = 0;
    protected int edgeIndex = 0;

    private List<CNode> children;
    private List<CPort> externalPorts;

    /**
     * 
     */
    public CGraph() {
        super(null);

        // a list of rectangles
        nodes = new RectanglePtrs();
        // a list of pairs of array indexes to the nodes array
        edges = new ColaEdges();
        // a list of constraints
        constraints = new CompoundConstraintPtrs();

        rootCluster = new RootCluster();

        children = new ArrayList<CNode>();
        externalPorts = Lists.newArrayList();

        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        
        // 
        idealEdgeLengths = new double[edgeIndex];
    }

    /**
     * @return the children
     */
    public List<CNode> getChildren() {
        return children;
    }

    /**
     * @return the externalPorts
     */
    public List<CPort> getExternalPorts() {
        return externalPorts;
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

    public int getLastNodeIndex() {
        return nodeIndex;
    }
}
