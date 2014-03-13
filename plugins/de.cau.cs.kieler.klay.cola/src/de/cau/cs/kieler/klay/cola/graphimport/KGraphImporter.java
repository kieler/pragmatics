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
package de.cau.cs.kieler.klay.cola.graphimport;

import java.util.Map;

import org.adaptagrams.AlignmentConstraint;
import org.adaptagrams.Cluster;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;
import de.cau.cs.kieler.klay.cola.graph.CShape;

/**
 * @author uru
 */
public class KGraphImporter implements IGraphImporter<KNode> {

    /**
     * The original object from which a graph element was created.
     */
    public static final IProperty<KGraphElement> ORIGIN = new Property<KGraphElement>(
            "kgraph.origin");

    /**
     * 
     */
    private Map<KNode, CNode> knodeMap = Maps.newHashMap();
    private Map<KPort, CPort> kportMap = Maps.newHashMap();

    private Map<KNode, Cluster> clusterMap = Maps.newHashMap();

    /**
     * {@inheritDoc}
     */
    public CGraph importGraph(final KNode root) {

        CGraph graph = new CGraph();
        graph.copyProperties(root.getData(KLayoutData.class));
        graph.setProperty(ORIGIN, root);

        // TODO copy all the properties
        // copy the properties from the KGraph element to the CGraph element
        // KLayoutData layoutData = element.getData(KLayoutData.class);
        // if (layoutData != null) {
        // copyProperties(layoutData);
        // }

        // put the root in the pool!
        knodeMap.put(root, null);

        // if (hierarchy && !root.getChildren().isEmpty()) {
        // Cluster cluster = new RectangularCluster();
        // rootCluster.addChildCluster(cluster);
        // clusterMap.put(root, cluster);
        // System.out.println("Creating cluster");
        // }

        for (KNode n : root.getChildren()) {

            // ignore unconnected nodes
            if (n.getIncomingEdges().isEmpty() && n.getOutgoingEdges().isEmpty()) {
                continue;
            }

            // create the node
            CNode cnode = new CNode(graph);
            // dimensions
            setPosAndSize(cnode, n.getData(KShapeLayout.class));
            // properties
            cnode.copyProperties(n.getData(KLayoutData.class));
            cnode.setProperty(ORIGIN, n);
            // remember it
            knodeMap.put(n, cnode);
            graph.getChildren().add(cnode);
            cnode.setParent(graph);
            cnode.init();

            // create ports
            for (KPort p : n.getPorts()) {
                CPort port = new CPort(graph, cnode);
                setPosAndSize(port, p.getData(KShapeLayout.class));
                port.copyProperties(p.getData(KLayoutData.class));
                port.setProperty(ORIGIN, p);
                kportMap.put(p, port);
                cnode.getPorts().add(port);
                port.init();
                // add an edge from the port to the node's center
                port.withCenterEdge();
            }

            // if (hierarchy) {
            // // add to cluster
            // Cluster parentCluster = clusterMap.get(root);
            // parentCluster.addChildNode(cnode.cIndex);
            // System.out.println("Adding to cluster");
            // }

            // }
        }

        // if (hierarchy) {
        //
        // // first do the children
        // for (KNode n : root.getChildren()) {
        // transformGraph(n, hierarchy);
        // }
        //
        // // then the edges
        //
        // for (KEdge e : root.getOutgoingEdges()) {
        //
        // // ignore hierarchy edges
        // // if (KimlUtil.isDescendant(e.getTarget(), e.getSource())) {
        // // continue;
        // // }
        //
        // CNode srcNode = knodeMap.get(e.getSource());
        // CNode tgtNode = knodeMap.get(e.getTarget());
        // CPort srcPort = kportMap.get(e.getSourcePort());
        // CPort tgtPort = kportMap.get(e.getTargetPort());
        //
        // if ((srcNode != null || srcPort != null) && (tgtNode != null || tgtPort != null)) {
        // CEdge edge = new CEdge(this, e, srcNode, srcPort, tgtNode, tgtPort);
        // }
        // }
        //
        // } else {

        /*
         * external port dummies
         */
        for (KPort p : root.getPorts()) {
            CPort port = new CPort(graph, null);
            setPosAndSize(port, p.getData(KShapeLayout.class));
            port.copyProperties(p.getData(KLayoutData.class));
            port.setProperty(ORIGIN, p);
            kportMap.put(p, port);
            graph.getExternalPorts().add(port);
            port.init();
            port.asExternalDummy();
        }

        // align external ports
        AlignmentConstraint acLeft = new AlignmentConstraint(Dim.XDIM);
        graph.constraints.add(acLeft);
        AlignmentConstraint acRight = new AlignmentConstraint(Dim.XDIM);
        graph.constraints.add(acRight);
        for (CPort p : graph.getExternalPorts()) {
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

                CEdge edge = new CEdge(graph, srcNode, srcPort, tgtNode, tgtPort);
                edge.copyProperties(e.getData(KLayoutData.class));
                edge.setProperty(ORIGIN, e);
                edge.init();

                // register the edge (if it is no edge to an external port dummy
                if (e.getTarget().getParent() == e.getSource().getParent()) {
                    srcNode.getOutgoingEdges().add(edge);
                    if (srcPort != null) {
                        srcPort.getOutgoingEdges().add(edge);
                    }
                    tgtNode.getIncomingEdges().add(edge);
                    if (tgtPort != null) {
                        tgtPort.getIncomingEdges().add(edge);
                    }
                }

            }
        }
        // }
        
        graph.init();

        return graph;
    };

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final CGraph graph) {

        double borderSpacing = graph.getProperty(LayoutOptions.BORDER_SPACING);

        // calculate the offset from border spacing and node distribution
        double minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY =
                Float.MIN_VALUE;

        // find the minimal and maximal positions of the contained nodes
        for (CNode n : graph.getChildren()) {
            Rectangle r = n.rect;
            minX = Math.min(minX, r.getMinX());
            minY = Math.min(minY, r.getMinY());
            maxX = Math.max(maxX, r.getMaxX());
            maxY = Math.max(maxY, r.getMaxY());
        }
        KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

        /*
         * Nodes
         */
        for (CNode n : graph.getChildren()) {
            Rectangle r = n.rect;

            KShapeLayout layout = n.getProperty(ORIGIN).getData(KShapeLayout.class);
            layout.setXpos((float) (r.getMinX() + offset.x));
            layout.setYpos((float) (r.getMinY() + offset.y));

            // ports
            for (CPort p : n.getPorts()) {
                if (p.cEdgeIndex != -1) {
                    KShapeLayout portLayout = p.getProperty(ORIGIN).getData(KShapeLayout.class);
                    // ports are relative to the parent in KGraph
                    portLayout.setXpos((float) p.getActualXPos());
                    portLayout.setYpos((float) p.getActualYPos());
                }
            }
        }

        /*
         * External Ports
         */
        for (CPort p : graph.getExternalPorts()) {
            Rectangle r = p.rect;

            KShapeLayout layout = p.getProperty(ORIGIN).getData(KShapeLayout.class);
            layout.setXpos((float) (r.getMinX() + offset.x));
            layout.setYpos((float) (r.getMinY() + offset.y));
        }

        /*
         * Edges, no routing done -> clear the bend points
         */
        KNode root = (KNode) graph.getProperty(ORIGIN);
        for (KNode n : root.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {

                KEdgeLayout layout = e.getData(KEdgeLayout.class);
                layout.getBendPoints().clear();

            }
        }

        // resize the parent node
        KInsets insets = root.getData(KShapeLayout.class).getInsets();
        double width = (maxX - minX) + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        double height = (maxY - minY) + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(root, (float) width, (float) height, false, true);

    }

    private void setPosAndSize(CShape c, KShapeLayout k) {
        c.getPos().x = k.getXpos();
        c.getPos().y = k.getYpos();
        c.getSize().x = k.getWidth();
        c.getSize().y = k.getHeight();
    }

}
