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

import java.util.Iterator;
import java.util.Map;

import org.adaptagrams.AlignmentConstraint;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
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
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.util.ColaUtil;

/**
 * @author uru
 */
public class KGraphImporter implements IGraphImporter<KNode> {

    /*
     * Internal mappings of KGraph elements to Adaptagram elements 
     */
    private Map<KNode, CNode> knodeMap = Maps.newHashMap();
    private Map<KPort, CPort> kportMap = Maps.newHashMap();

    private boolean portDummies = false;

    /**
     * {@inheritDoc}
     */
    public CGraph importGraph(final KNode root) {

        // setup the graph
        CGraph graph = new CGraph();
        graph.copyProperties(root.getData(KLayoutData.class));
        graph.setProperty(ColaProperties.ORIGIN, root);

        portDummies = graph.getProperty(ColaProperties.PORT_DUMMIES);

        // put the root in the pool!
        knodeMap.put(root, null);

        // iterate over all nodes of the root KNode
        for (KNode n : root.getChildren()) {
            transformNode(n, graph, null);
        }

        /*
         * external port dummies
         */
        for (KPort p : root.getPorts()) {
            CPort port = new CPort(graph, null);
            ColaUtil.setPosAndSize(port, p.getData(KShapeLayout.class));
            port.copyProperties(p.getData(KLayoutData.class));
            port.setProperty(ColaProperties.ORIGIN, p);
            kportMap.put(p, port);
            graph.getExternalPorts().add(port);
            port.init();
            port.side = KimlUtil.calcPortSide(p, graph.getProperty(LayoutOptions.DIRECTION));
            port.asExternalDummy();

            // transform the edges from and to external ports
            for (KEdge e : p.getEdges()) {
                if (e.getSource().getParent().equals(root)
                        || e.getTarget().getParent().equals(root)) {
                    transformEdge(e, graph);
                }
            }
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
            transformEdges(n, graph);
        }

        // after all graph elements were created we can initialize the cola elements of the graph
        graph.init();

        return graph;
    }

    private void transformNode(final KNode n, final CGraph graph, final KNode parent) {

        // ignore unconnected nodes
        if (n.getIncomingEdges().isEmpty() && n.getOutgoingEdges().isEmpty()) {
            return;
        }

        // create the node
        CNode cnode = new CNode(graph);
        // dimensions
        ColaUtil.setPosAndSize(cnode, n.getData(KShapeLayout.class));
        // properties
        cnode.copyProperties(n.getData(KLayoutData.class));
        cnode.setProperty(ColaProperties.ORIGIN, n);
        // remember it
        knodeMap.put(n, cnode);
        graph.getChildren().add(cnode);
        cnode.setParent(graph);
        cnode.init();

        // create ports
        if (portDummies) {
            for (KPort p : n.getPorts()) {
                CPort port = new CPort(graph, cnode);
                ColaUtil.setPosAndSize(port, p.getData(KShapeLayout.class));
                port.copyProperties(p.getData(KLayoutData.class));
                port.setProperty(ColaProperties.ORIGIN, p);
                kportMap.put(p, port);
                cnode.getPorts().add(port);
                port.init();
                port.side = KimlUtil.calcPortSide(p, 
                        graph.getProperty(LayoutOptions.DIRECTION)); // FIXME

                // add an edge from the port to the node's center
                port.withCenterEdge();
            }
        }

    }

    private void transformEdges(final KNode n, final CGraph graph) {

        for (KEdge e : n.getOutgoingEdges()) {

            // ignore edges that point "into" the node
            if (KimlUtil.isDescendant(e.getTarget(), e.getSource())) {
                continue;
            }

            transformEdge(e, graph);
        }

    }

    private void transformEdge(final KEdge e, final CGraph graph) {

        CNode srcNode = knodeMap.get(e.getSource());
        CNode tgtNode = knodeMap.get(e.getTarget());
        CPort srcPort = kportMap.get(e.getSourcePort());
        CPort tgtPort = kportMap.get(e.getTargetPort());

        CEdge edge = new CEdge(graph, srcNode, srcPort, tgtNode, tgtPort);
        edge.copyProperties(e.getData(KLayoutData.class));
        edge.setProperty(ColaProperties.ORIGIN, e);
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
        } else {
            // add to the list of external edges of the node that is on this graph layer,
            // ie not the parent
            if (srcNode != null) {
                srcNode.getExternalEdges().add(edge);
            } else {
                tgtNode.getExternalEdges().add(edge);
            }
        }
    }

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
            KShapeLayout layout = n.getProperty(ColaProperties.ORIGIN).getData(KShapeLayout.class);

            // set new positions
            layout.setXpos((float) (r.getMinX() + n.getMargins().left + offset.x));
            layout.setYpos((float) (r.getMinY() + n.getMargins().top + offset.y));

            // write back insets
            KInsets insets = layout.getInsets();
            insets.setLeft((float) n.getInsets().left);
            insets.setRight((float) n.getInsets().right);
            insets.setTop((float) n.getInsets().top);
            insets.setBottom((float) n.getInsets().bottom);

            // ports
            for (CPort p : n.getPorts()) {
                if (p.cEdgeIndex != -1) {
                    KShapeLayout portLayout =
                            p.getProperty(ColaProperties.ORIGIN).getData(KShapeLayout.class);
                    // ports are relative to the parent in KGraph
                    // portLayout.setXpos((float) p.getActualXPos());
                    // portLayout.setYpos((float) p.getActualYPos());
                    KVector relative = p.getRelativePos();
                    portLayout.setXpos((float) relative.x);
                    portLayout.setYpos((float) relative.y);
                }
            }
        }

        /*
         * External Ports
         */
        for (CPort p : graph.getExternalPorts()) {
            Rectangle r = p.rect;

            KShapeLayout layout = p.getProperty(ColaProperties.ORIGIN).getData(KShapeLayout.class);
            layout.setXpos((float) (r.getMinX() + offset.x));
            layout.setYpos((float) (r.getMinY() + offset.y));
        }

        /*
         * Edges, no routing done -> clear the bend points
         */
        KNode root = (KNode) graph.getProperty(ColaProperties.ORIGIN);
        Iterator<EObject> allEdges =
                Iterators.filter(root.eAllContents(), new Predicate<EObject>() {
                    public boolean apply(final EObject obj) {
                        return obj instanceof KEdge;
                    }
                });
        while (allEdges.hasNext()) {
            KEdge edge = ((KEdge) allEdges.next());
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            layout.getBendPoints().clear();
            layout.getSourcePoint().setPos(0, 0);
            layout.getTargetPoint().setPos(0, 0);
        }

        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                KEdge edge = (KEdge) e.getProperty(ColaProperties.ORIGIN);
                KEdgeLayout layout = edge.getData(KEdgeLayout.class);
                layout.getSourcePoint().applyVector(e.getSourcePoint().sumCreate(offset));
                layout.getTargetPoint().applyVector(e.getTargetPoint().sumCreate(offset));
            }
        }

        // FIXME atm this is too small! Why are we missing some of the overall size?
        // resize the parent node
        KInsets insets = root.getData(KShapeLayout.class).getInsets();
        double width = (maxX - minX) + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        double height = (maxY - minY) + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(root, (float) width, (float) height, false, true);

    }

}
