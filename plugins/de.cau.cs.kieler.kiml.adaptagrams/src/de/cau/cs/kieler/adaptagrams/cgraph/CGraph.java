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
package de.cau.cs.kieler.adaptagrams.cgraph;

import java.util.Arrays;
import java.util.List;

import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.Doubles;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.RootCluster;

import com.google.common.collect.Lists;


/**
 * The {@link CGraph} wraps c++ adaptagrams elements to achieve a 
 * convenient usage from java. 
 * 
 * <h3>Usage Remarks</h3>
 * <ul>
 *      <li>
 *          As opposed to a KGraph, a {@link CGraph} is flat. Hierarchy is created by  
 *          using {@link org.adaptagrams.Cluster Cluster}s.
 *          Coordinates for all nodes, ports, and edges are in a common coordinate system.
 *      </li>
 *      <li>
 *          After an element has been created the {@link #init()} method must be called. 
 *      </li>
 *      <li>
 *          The {@link CGraphElement#cIndex} and {@link CPort#cEdgeIndex} identify adaptagrams
 *          elements and are to be used when creating constraints. Do <b>not</b> use the 
 *          {@link CGraphElement#id} in this case.
 *          See {@link CGraphElement}.
 *      </li>
 *      <li>
 *          Use the {@link de.cau.cs.kieler.adaptagrams.layouter.KConstrainedFDLayouter 
 *          KConstrainedFDLayouter} to setup the actual layouter.
 *      </li>
 * </ul>
 * 
 * @author uru
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

    /*
     * Index counters for the node and edge collections
     */
    protected int nodeIndex = 0;
    protected int edgeIndex = 0;

    /*
     * CGraph collections for convenient use
     */
    private List<CNode> children;
    private List<CPort> externalPorts;
    
    private boolean initialized = false;
    
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
        // the clusters of the graph
        rootCluster = new RootCluster();

        // contained children and external ports
        children = Lists.newArrayList();
        externalPorts = Lists.newArrayList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        // after all edges have been added we can create the array of proper size
        idealEdgeLengths = new double[edgeIndex];
        
        initialized = true;
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
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

    // --------------------------------------------------------------------------------
    // Adaptagrams Accessors
    // --------------------------------------------------------------------------------

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
    public Doubles getIdealEdgeLengths() {

        // copy the calculated values to a swig wrapper
        Doubles arr = new Doubles(idealEdgeLengths.length);
        for (int i = 0; i < idealEdgeLengths.length; ++i) {
            arr.set(i, idealEdgeLengths[i]);
        }

        return arr;
    }

    /**
     * @return the last node index. Note that this index has not been used so far, ie
     *         List(nodes).size == lastNodeIndex.
     */
    public int getLastNodeIndex() {
        return nodeIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CGraph {");
        if (nodes != null) {
            sb.append("\n\tNodes: ").append(nodes);
        }
        if (edges != null) {
            sb.append("\n\tEdges: ").append(edges);
        }
        if (constraints != null) {
            sb.append("\n\tConstraints: ").append(constraints);
        }
        if (rootCluster != null) {
            sb.append("\n\tClusters: ").append(rootCluster);
        }
        if (idealEdgeLengths != null) {
            sb.append("\n\tIdealEdgeLengths: ").append(Arrays.toString(idealEdgeLengths));
        }
        sb.append("\n}");

        return sb.toString();
    }
}
