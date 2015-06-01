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
import de.cau.cs.kieler.core.math.KielerMath;

/**
 * @author uru
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
     * Returns the docking point at the source node, or port if existent (relative to the source node).
     * 
     * Note, if neither a src node nor a src port are specified a (0, 0)
     * vector is returned.
     * 
     * @return the source docking point
     */
    public KVector getSourcePoint() {
        return getClippedAnchorVector(src, srcPort, tgt, tgtPort);
    }

    /**
     * Returns the docking point at the target node, or port if existent.
     * 
     * Note, if neither a tgt node nor a tgt port are specified a (0, 0)
     * vector is returned.
     * 
     * @return the target docking point
     */
    public KVector getTargetPoint() {
        return getClippedAnchorVector(tgt, tgtPort, src, srcPort);
    }

    /**
     * All positions have to be in a common coordinate system.
     * Either parameter may be null. 
     * 
     * @return a vector pointing to either the center of the {@code srcNode} or the {@srcNodePort}
     *         (the port is prioritized).
     */
    private KVector getClippedAnchorVector(final CNode srcNode, final CPort srcNodePort,
            final CNode tgtNode, final CPort tgtNodePort) {
        KVector sV = null;
        KVector srcSize = null;
        if (srcNode != null) {
            sV = srcNode.getCenter();
            srcSize = srcNode.getSize();
        }
        if (srcNodePort != null) {
            sV = srcNodePort.getCenter();
            srcSize = srcNodePort.getSize();
        }
        KVector tV = null; 
        if (tgtNode != null) {
            tV = tgtNode.getCenter();
        }
        if (tgtNodePort != null) {
            tV = tgtNodePort.getCenter();
        }
        
        if (sV == null && tV == null) {
           return new KVector(); 
        }

        KVector v = tV.clone().sub(sV);
        KielerMath.clipVector(v, srcSize.x, srcSize.y);
        
        return v.add(sV);
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
