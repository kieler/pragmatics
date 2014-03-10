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

import org.adaptagrams.AlignmentConstraint;
import org.adaptagrams.ColaEdges;
import org.adaptagrams.CompoundConstraintPtrs;
import org.adaptagrams.Dim;
import org.adaptagrams.RectanglePtrs;
import org.adaptagrams.SWIGTYPE_p_double;
import org.adaptagrams.adaptagrams;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * @author uru
 * 
 */
public class CGraph extends CGraphElement<KNode> {

    private static final long serialVersionUID = -5599764636700647978L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    /**
     * 
     */
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
    private List<CPort> externalPorts;

    /**
     * 
     */
    public CGraph(final KNode graph) {
        super(null, graph);

        origin = graph;

        // a list of rectangles
        nodes = new RectanglePtrs();
        // a list of pairs of array indexes to the nodes array
        edges = new ColaEdges();
        // a list of constraints
        constraints = new CompoundConstraintPtrs();

        children = new ArrayList<CNode>(graph.getChildren().size());
        externalPorts = Lists.newArrayList();

        transformGraph(graph);

        idealEdgeLengths = new double[edgeIndex];
    }

    private void transformGraph(final KNode root) {

        // put the root in the pool!
        knodeMap.put(root, null);

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

                CPort port = new CPort(this, p, cnode).withCenterEdge();
                kportMap.put(p, port);
                cnode.ports.add(port);

            }

            // keep the order!
            // for (CPort p1 : cnode.getPorts()) {
            // for (CPort p2 : cnode.getPorts()) {
            //
            // // not for the same port, not for ports on different sides
            // if (p1.equals(p2) || p1.side != p2.side) {
            // continue;
            // }
            //
            // System.out.println("SPATEN");
            //
            // int dim;
            // double sep;
            // if (p1.side == PortSide.NORTH || p1.side == PortSide.SOUTH) {
            // dim = Dim.XDIM;
            // sep =
            // p1.origin.getData(KShapeLayout.class).getXpos()
            // - p2.origin.getData(KShapeLayout.class).getXpos();
            // } else {
            // dim = Dim.YDIM;
            // sep =
            // p1.origin.getData(KShapeLayout.class).getYpos()
            // - p2.origin.getData(KShapeLayout.class).getYpos();
            // }
            // System.out.println(dim + " " + sep);
            // SeparationConstraint sc =
            // new SeparationConstraint(dim, p1.cIndex, p2.cIndex, -sep+20, false);
            // constraints.add(sc);
            // }
            // }

        }

        /*
         * external port dummies
         */
        for (KPort p : root.getPorts()) {

            CPort port = new CPort(this, p, null).asExternalDummy();
            kportMap.put(p, port);
            externalPorts.add(port);
        }

        // align external ports
        AlignmentConstraint acLeft = new AlignmentConstraint(Dim.XDIM);
        constraints.add(acLeft);
        AlignmentConstraint acRight = new AlignmentConstraint(Dim.XDIM);
        constraints.add(acRight);
        for (CPort p : externalPorts) {
            if (p.side == PortSide.WEST) {
                acLeft.addShape(p.cIndex, 0);
            } else {
                acRight.addShape(p.cIndex, 0);
            }
        }

        /*
         * Edges
         */
        for (KNode n : root.getChildren()) {

            for (KEdge e : n.getOutgoingEdges()) {

                // ignore hierarchy edges
                if (KimlUtil.isDescendant(e.getTarget(), e.getSource())) {
                    continue;
                }

                CNode srcNode = knodeMap.get(e.getSource());
                CNode tgtNode = knodeMap.get(e.getTarget());
                CPort srcPort = kportMap.get(e.getSourcePort());
                CPort tgtPort = kportMap.get(e.getTargetPort());

                CEdge edge = new CEdge(this, e, srcNode, srcPort, tgtNode, tgtPort);

                // register the edge (if it is no edge to an external port dummy
                if (e.getTarget().getParent() == e.getSource().getParent()) {
                    srcNode.outgoingEdges.add(edge);
                    if (srcPort != null) {
                        srcPort.outgoingEdges.add(edge);
                    }
                    tgtNode.incomingEdges.add(edge);
                    if (tgtPort != null) {
                        tgtPort.incomingEdges.add(edge);
                    }
                }

            }
        }

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
