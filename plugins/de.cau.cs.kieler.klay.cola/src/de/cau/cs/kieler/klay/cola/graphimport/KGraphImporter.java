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
import org.adaptagrams.RectangularCluster;
import org.adaptagrams.SeparationConstraint;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties;
import de.cau.cs.kieler.klay.cola.util.ColaUtil;

/**
 * Imports a KGraph and converts it into a CGraph (adaptagrams).
 * 
 * This importer only handles one hierarchical level.
 * 
 * <h1>Cross-Hierarchy Edges</h1> 
 * Edges that span hierarchy boundaries, i.e., the target node is
 * either an ancestor or descendent of the source node, are ignored. 
 * Edges can however, cross the boundary of a compound node via a port that 
 * lies on the boundary. From the inside of a compound node these ports
 * are considered to be 'external ports' and treated specially.  
 * 
 * <h1>External Ports</h1> 
 * External ports are represented by adaptagrams rectangles, just as usual 
 * ports. We use either a cluster around the 'regular' nodes (all non-external
 * port nodes) or alignment constraints to keep the external ports on the 
 * outside of the compound node.
 *   
 * 
 * <h1>Known Limitations</h1> 
 *  - Constraints of external ports are only partly implemented (order,
 *    ratio, pos missing)
 * 
 * @author uru
 */
public class KGraphImporter implements IGraphImporter<KNode, CGraph> {

    /*
     * Internal mappings of KGraph elements to Adaptagram elements
     */
    private Map<KNode, CNode> knodeMap = Maps.newHashMap();
    private Map<KPort, CPort> kportMap = Maps.newHashMap();

    
    // --------------------------------------------------------------------------------------
    //                          Graph Transformation
    // --------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    public CGraph importGraph(final KNode root) {

        // setup the graph
        CGraph graph = new CGraph();
        graph.copyProperties(root.getData(KLayoutData.class));
        graph.setProperty(InternalColaProperties.ORIGIN, root);

        // put the root in the pool!
        knodeMap.put(root, null);

        // iterate over all nodes of the root KNode
        for (KNode n : root.getChildren()) {
            transformNode(n, graph, null);
        }

        // handle external ports
        handleExternalPorts(root, graph);
        
        // edges
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
        cnode.setProperty(InternalColaProperties.ORIGIN, n);
        // remember it
        knodeMap.put(n, cnode);
        graph.getChildren().add(cnode);
        cnode.setParent(graph);
        cnode.init();

        // create ports
        if (graph.getProperty(ColaProperties.PORT_DUMMIES)) {
            for (KPort p : n.getPorts()) {
                CPort port = new CPort(graph, cnode);
                ColaUtil.setPosAndSize(port, p.getData(KShapeLayout.class));
                // port position is absolute on this hierarchical level
                port.getPos().add(cnode.getPos());
                port.copyProperties(p.getData(KLayoutData.class));
                port.setProperty(InternalColaProperties.ORIGIN, p);
                kportMap.put(p, port);
                cnode.getPorts().add(port);
                port.init();
                port.side = port.getProperty(LayoutOptions.PORT_SIDE);
                // if no side was specified but the constraints are at least FIXED_SIDE,
                // determine the port side based on the current position
                if (port.side == PortSide.UNDEFINED
                        && cnode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                    port.side = KimlUtil.calcPortSide(p, graph.getProperty(LayoutOptions.DIRECTION));
                }

                // add an edge from the port to the node's center
                port.withCenterEdge();
            }
        }

    }

    /**
     * Transforms all edges that originate from and target nodes 
     * that are direct children of the current root node. 
     */
    private void transformEdges(final KNode n, final CGraph graph) {
        
        final KNode parent = n.getParent();
        for (KEdge e : n.getOutgoingEdges()) {
            // only transform edges that connect nodes that are
            // direct children of the parent
            // hence, edges to and from external ports are not 
            // transformed here
            if (e.getSource().getParent().equals(parent)
                    && e.getTarget().getParent().equals(parent)) {
                transformEdge(e, graph);
            }
        }
    }

    /**
     * Transforms either a regular edge or an edge to or from an external port.
     */
    private void transformEdge(final KEdge e, final CGraph graph) {

        CNode srcNode = knodeMap.get(e.getSource());
        CNode tgtNode = knodeMap.get(e.getTarget());
        CPort srcPort = kportMap.get(e.getSourcePort());
        CPort tgtPort = kportMap.get(e.getTargetPort());

        CEdge edge = new CEdge(graph, srcNode, srcPort, tgtNode, tgtPort);
        edge.copyProperties(e.getData(KLayoutData.class));
        edge.setProperty(InternalColaProperties.ORIGIN, e);
        edge.init();

        // register the edge (if it is no edge to an external port dummy)
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
            // add the edge to the list of external edges of the node 
            // that is on this graph layer ie not the parent
            if (srcNode != null) {
                srcNode.getExternalEdges().add(edge);
            } else {
                tgtNode.getExternalEdges().add(edge);
            }
            
            // external edges wanna know their connected edges as well
            if (tgtPort != null) {
                tgtPort.getIncomingEdges().add(edge);
            }
            
            if (srcPort != null) {
                srcPort.getOutgoingEdges().add(edge);
            }
        }
    }

    /**
     * Creates dummy ports for external ports and transforms the connected edges.
     */
    private void handleExternalPorts(final KNode root, final CGraph graph) {
        
        // nothing to do if no external ports exist
        if (root.getPorts().isEmpty()) {
            return;
        }
        
        // iterate through all ports of the parent node
        for (KPort p : root.getPorts()) {
            CPort port = new CPort(graph, null);
            ColaUtil.setPosAndSize(port, p.getData(KShapeLayout.class));
            port.copyProperties(p.getData(KLayoutData.class));
            port.setProperty(InternalColaProperties.ORIGIN, p);
            kportMap.put(p, port);
            graph.getExternalPorts().add(port);
            port.init();
            port.side = port.getProperty(LayoutOptions.PORT_SIDE);
            // if no side was specified but the constraints are at least FIXED_SIDE,
            // determine the port side based on the current position
            if (port.side == PortSide.UNDEFINED
                    && graph.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                port.side = KimlUtil.calcPortSide(p, graph.getProperty(LayoutOptions.DIRECTION)); 
            }
            port.asExternalDummy();

            // transform the edges from and to external ports
            // note that these edges must be ignored during the rest of the import
            for (KEdge e : p.getEdges()) {
                // edges connected to this port and incident to any node that
                // is a direct child of the root
                if (e.getSource().getParent().equals(root)
                        || e.getTarget().getParent().equals(root)) {
                    transformEdge(e, graph);
                }
            }
        }

        
        // we have to make sure that the external ports are fixed at the outside boundary
        // of the hierarchical node
        switch (graph.getProperty(LayoutOptions.PORT_CONSTRAINTS)) {
        
            case UNDEFINED:
            case FREE: {
                // we add all nodes and their dummy ports to a cluster to separate them from the
                // external ports
                Cluster cluster = new RectangularCluster();
                for (CNode n : graph.getChildren()) {
                    cluster.addChildNode(n.cIndex);
                    for (CPort p : n.getPorts()) {
                        cluster.addChildNode(p.cIndex);
                    }
                }
                graph.rootCluster.addChildCluster(cluster);
            }
            
            // FIXME this basically only implements fixed side
            case FIXED_SIDE:
            case FIXED_ORDER:
            case FIXED_RATIO:
            case FIXED_POS:
            {
                // add alignment constraints at a certain port side
                AlignmentConstraint acLeft = null;
                AlignmentConstraint acTop = null;
                AlignmentConstraint acRight = null;
                AlignmentConstraint acBottom = null;
                for (CPort p : graph.getExternalPorts()) {
                    if (p.side == PortSide.WEST) { // WEST
                        if (acLeft == null) {
                            acLeft = new AlignmentConstraint(Dim.XDIM);
                            graph.constraints.add(acLeft);
                            addSeparationToAllRegularNodes(p, graph, Dim.XDIM, true);
                        }
                        acLeft.addShape(p.cIndex, 0);
                    } else if (p.side == PortSide.NORTH) { // NORTH
                        if (acTop == null) {
                            acTop = new AlignmentConstraint(Dim.YDIM);
                            graph.constraints.add(acTop);
                            addSeparationToAllRegularNodes(p, graph, Dim.YDIM, true);
                        }
                        acTop.addShape(p.cIndex, 0);
                    } else if (p.side == PortSide.EAST) { // EAST
                        if (acRight == null) {
                            acRight = new AlignmentConstraint(Dim.XDIM);
                            graph.constraints.add(acRight);
                            addSeparationToAllRegularNodes(p, graph, Dim.XDIM, false);
                        }
                        acRight.addShape(p.cIndex, 0);
                    } else { // SOUTH
                        if (acBottom == null) {
                            acBottom = new AlignmentConstraint(Dim.YDIM);
                            graph.constraints.add(acBottom);
                            addSeparationToAllRegularNodes(p, graph, Dim.YDIM, false);
                        }
                        acBottom.addShape(p.cIndex, 0);
                    }
                }
            }
        }
    }

    /**
     * Note that its enough to create the separations for one dimension for a single port, as the
     * dimension of all external ports is the same.
     */
    private void addSeparationToAllRegularNodes(final CPort p, final CGraph cgraph, final int dim,
            final boolean leftTop) {
        final double borderSpacing = cgraph.getProperty(LayoutOptions.BORDER_SPACING);
        for (CNode n : cgraph.getChildren()) {
            double gap =
                    dim == Dim.XDIM ? (p.getRectSizeRaw().x / 2f + n.getRectSizeRaw().x / 2f) : (p
                            .getRectSizeRaw().y / 2f + n.getRectSizeRaw().y / 2f);
            SeparationConstraint sc;
            if (leftTop) {
                sc = new SeparationConstraint(dim, p.cIndex, n.cIndex, gap + borderSpacing);
            } else {
                sc = new SeparationConstraint(dim, n.cIndex, p.cIndex, gap + borderSpacing);
            }
            cgraph.constraints.add(sc);
        }
    }

    // --------------------------------------------------------------------------------------
    //                          Layout Application
    // --------------------------------------------------------------------------------------

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
            final KVector pos = n.getRectPosRaw();
            final KVector size = n.getRectSizeRaw();
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);
            maxX = Math.max(maxX, pos.x + size.x);
            maxY = Math.max(maxY, pos.y + size.y);
            for (CPort p : n.getPorts()) {
                final KVector ppos = p.getRectPosRaw();
                final KVector psize = p.getRectSizeRaw();
                minX = Math.min(minX, ppos.x);
                minY = Math.min(minY, ppos.y);
                maxX = Math.max(maxX, ppos.x + psize.x);
                maxY = Math.max(maxY, ppos.y + psize.y);
            }
        }
        KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

        // nodes
        for (CNode n : graph.getChildren()) {
            KShapeLayout layout =
                    n.getProperty(InternalColaProperties.ORIGIN).getData(KShapeLayout.class);

            // set new positions
            layout.setXpos((float) (n.getRectPos().x + offset.x));
            layout.setYpos((float) (n.getRectPos().y + offset.y));

            // write back insets
            KInsets insets = layout.getInsets();
            insets.setLeft((float) n.getInsets().left);
            insets.setRight((float) n.getInsets().right);
            insets.setTop((float) n.getInsets().top);
            insets.setBottom((float) n.getInsets().bottom);

            // ports
            // FIXME
            // if (!n.getProperty(LayoutOptions.PORT_CONSTRAINTS).isPosFixed()) {
                for (CPort p : n.getPorts()) {
                    if (p.cEdgeIndex != -1) {
                        KShapeLayout portLayout =
                                p.getProperty(InternalColaProperties.ORIGIN)
                                        .getData(KShapeLayout.class);
                        // ports are relative to the parent in KGraph
                        KVector relative = p.getRelativePos();
                        portLayout.setXpos((float) relative.x);
                        portLayout.setYpos((float) relative.y);
                    }
                }
            // }
        }

        // re-position external ports
        // TODO we always wanna reposition the external ports??
        // if (!graph.getProperty(LayoutOptions.PORT_CONSTRAINTS).isPosFixed()) {
        for (CPort p : graph.getExternalPorts()) {
            KPort kp = (KPort) p.getProperty(InternalColaProperties.ORIGIN);
            KShapeLayout layout =
                    kp.getData(KShapeLayout.class);
            layout.setXpos((float) (p.getRectPos().x + offset.x));
            layout.setYpos((float) (p.getRectPos().y + offset.y));
            
            // reposition the port
            PortSide ps = KimlUtil.calcPortSide(kp  , Direction.RIGHT);
            layout.setProperty(LayoutOptions.PORT_SIDE, ps);
        }
        // }

        // edges, no routing done -> clear the bend points
        // however, we try to give correct positions
        KNode root = (KNode) graph.getProperty(InternalColaProperties.ORIGIN);
        for (CNode n : graph.getChildren()) {
            
            // "usual" edges 
            for (CEdge e : n.getOutgoingEdges()) {
                KEdge edge = (KEdge) e.getProperty(InternalColaProperties.ORIGIN);
                KEdgeLayout layout = edge.getData(KEdgeLayout.class);
                
                layout.getBendPoints().clear();
                layout.getSourcePoint().applyVector(e.getSourcePoint().clone().add(offset));
                layout.getTargetPoint().applyVector(e.getTargetPoint().clone().add(offset));
            }
            
            // edges that connect to external ports, reference point is the root node for both
            // source and target point
            for (CEdge e : n.getExternalEdges()) {
                KEdge edge = (KEdge) e.getProperty(InternalColaProperties.ORIGIN);
                KEdgeLayout layout = edge.getData(KEdgeLayout.class);

                layout.getBendPoints().clear();
                if (e.getSource() != null) {
                    layout.getSourcePoint().applyVector(e.getSourcePoint().clone().add(offset));
                } else {
                    // get the port's position relative to the parent
                    layout.getSourcePoint().applyVector(
                            e.getSourcePort().getRectCenter().clone().add(offset));
                }
                if (e.getTarget() != null) {
                    layout.getTargetPoint().applyVector(e.getTargetPoint().clone().add(offset));
                } else {
                    layout.getTargetPoint().applyVector(
                            e.getTargetPort().getRectCenter().clone().add(offset));
                }
            }
        }
                
        // resize the parent node
        KInsets insets = root.getData(KShapeLayout.class).getInsets();
        double width = (maxX - minX) + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        double height = (maxY - minY) + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(root, (float) width, (float) height, false, true);
    }

}
