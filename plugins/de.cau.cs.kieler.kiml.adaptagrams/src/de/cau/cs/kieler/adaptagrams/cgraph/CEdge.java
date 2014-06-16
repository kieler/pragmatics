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

import java.util.List;

import org.adaptagrams.ColaEdge;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.core.math.KVector;

/**
 * @author uru
 * 
 */
public class CEdge extends CGraphElement {

    private static final long serialVersionUID = 1120071695206100617L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    public final CNode src;
    public final CNode tgt;
    public final CPort srcPort;
    public final CPort tgtPort;

    public ColaEdge edge;

    public boolean crossHierarchy = false;
    
    public List<KVector> bendpoints = Lists.newLinkedList();
    
    /**
     * 
     */
    public CEdge(final CGraph graph, final CNode src, final CPort srcPort, final CNode tgt,
            final CPort tgtPort) {
        super(graph);

        this.src = src;
        this.tgt = tgt;
        this.srcPort = srcPort;
        this.tgtPort = tgtPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {

        // external ports only have a dummy for the port, no parent nodes
        if (src == null) {
            assert srcPort != null;
        }
        if (tgt == null) {
            assert tgtPort != null;
        }

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

        // System.out.println("Initialized " + edge + " " + this);

        cIndex = graph.edgeIndex++;

        // add to the graph
        graph.edges.add(edge);

    }

    /**
     * @return the src
     */
    public CNode getSource() {
        return src;
    }

    /**
     * @return the tgt
     */
    public CNode getTarget() {
        return tgt;
    }

    /**
     * @return the srcPort
     */
    public CPort getSourcePort() {
        return srcPort;
    }

    /**
     * @return the tgtPort
     */
    public CPort getTargetPort() {
        return tgtPort;
    }

    /**
     * Returns the docking point at the source node.
     * 
     * Note, this method cannot be used in conjunction with external ports, it requires the source
     * node to be set.
     * 
     * @return the source docking point
     */
    public KVector getSourcePoint() {
        KVector v = src.getRectPos().clone();
        if (srcPort != null) {
            v.add(srcPort.getRelativePos());
            // point to the port's center
            v.add(srcPort.getSize().scale(0.5f)); // SUPPRESS CHECKSTYLE NEXT 40 MagicNumber
        } else {
            // point to the node's center
            v.add(src.getSize().scale(0.5f));
        }

        return v;
    }

    /**
     * Returns the docking point at the target node.
     * 
     * Note, this method cannot be used in conjunction with external ports, it requires the target
     * node to be set.
     * 
     * @return the target docking point
     */
    public KVector getTargetPoint() {
        KVector v = tgt.getRectPos().clone();
        if (tgtPort != null) {
            v.add(tgtPort.getRelativePos());
            // point to the port's center
            v.add(tgtPort.getSize().scale(0.5f));
        } else {
            // point to the node's center
            v.add(tgt.getSize().scale(0.5f));
        }

        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CEdge { ");
        if (crossHierarchy) {
            sb.append("CrossHierarchy: ").append(src.getProperty(CGraphProperties.ORIGIN))
                    .append("->").append(tgt.getProperty(CGraphProperties.ORIGIN)).append(" ");
        } else {
            Object origin = getProperty(CGraphProperties.ORIGIN);
            if (origin != null) {
                sb.append(origin).append(" ");
            }
        }
        if (edge != null) {
            sb.append(edge).append(" ");
        }

        sb.append(" }");
        return sb.toString();
    }
}
