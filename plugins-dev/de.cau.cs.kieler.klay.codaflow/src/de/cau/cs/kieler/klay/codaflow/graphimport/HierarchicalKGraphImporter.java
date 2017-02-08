/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.graphimport;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adaptagrams.Box;
import org.adaptagrams.Cluster;
import org.adaptagrams.Rectangle;
import org.adaptagrams.RectangularCluster;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;
import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.adaptagrams.properties.CoLaProperties;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.codaflow.properties.CodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.properties.ColaPredicates;
import de.cau.cs.kieler.klay.codaflow.properties.InternalCodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.util.CodaflowUtil;

/**
 * Imports a {@link CGraph} from a KGraph. As opposed to the {@link KGraphImporter} 
 * the whole hierarchy of the KGraph is imported at once.
 *
 * <h3>Limitations</h3>
 * - Edges that start/end at ports of compound nodes are not handled properly.
 * 
 * @author uru
 */
public class HierarchicalKGraphImporter implements IGraphImporter<KNode, CGraph> {

    private Map<KNode, CNode> nodeMap = Maps.newHashMap();
    private Map<KEdge, CEdge> edgeMap = Maps.newHashMap();
    private Map<KPort, CPort> portMap = Maps.newHashMap();
    /** Maps compound nodes to the cluster by which they are represented. */
    private BiMap<KNode, Cluster> clusterMap = HashBiMap.create();

    private CGraph graph;
    private boolean portDummies = false;
    private boolean repositionHierarchicalPorts = false;

    /**
     * {@inheritDoc}
     */
    public CGraph importGraph(final KNode rootNode) {

        // create a new adaptagrams graph
        graph = new CGraph();
        graph.copyProperties(rootNode.getData(KLayoutData.class));
        graph.setProperty(CGraphProperties.ORIGIN, rootNode);

        portDummies = graph.getProperty(CodaflowProperties.PORT_DUMMIES);
        repositionHierarchicalPorts =
                graph.getProperty(CodaflowProperties.REPOSITION_HIERARCHICAL_PORTS);

        // transform the nodes
        recImportNodes(rootNode, null);

        // transform edges
        recImportEdges(rootNode);

        // after all graph elements were created we can initialize the cola elements of the graph
        graph.init();

        return graph;
    }

    /**
     * Recursively transforms {@link KNode}s to either {@link CNode}s or {@link Cluster}s. 
     */
    private void recImportNodes(final KNode parentNode, final Cluster parentCluster) {

        for (KNode node : parentNode.getChildren()) {

            if (node.getIncomingEdges().isEmpty() && node.getOutgoingEdges().isEmpty()) {
                continue;
            }

            // if this is an atomic node, ie it has no children
            if (node.getChildren().isEmpty()) {
                // create an adaptagrams node
                CNode cnode = new CNode(graph);
                // dimensions
                CodaflowUtil.setPosAndSizeAbsolute(cnode, node, node.getParent());
                // properties
                cnode.copyProperties(node.getData(KLayoutData.class));
                cnode.setProperty(CGraphProperties.ORIGIN, node);
                // remember it
                nodeMap.put(node, cnode);
                graph.getChildren().add(cnode);
                cnode.init();

                // add to the cluster
                // note that top level nodes are not added to any cluster
                if (parentCluster != null) {
                    parentCluster.addChildNode(cnode.cIndex);
                }

                if (portDummies) {
                    // create ports
                    for (KPort p : node.getPorts()) {
                        CPort port = new CPort(graph, cnode);
                        CodaflowUtil.setPosAndSizeAbsolute(port, p, p.getNode());
                        port.copyProperties(p.getData(KLayoutData.class));
                        port.setProperty(CGraphProperties.ORIGIN, p);
                        portMap.put(p, port);
                        cnode.getPorts().add(port);
                        port.init();
                        PortSide ps = p.getData(KLayoutData.class).getProperty(LayoutOptions.PORT_SIDE);
                        port.side = ps;

                        // calculate a useful side
                        if (ps == PortSide.UNDEFINED) {
                            port.side = KimlUtil.calcPortSide(p,
                                            graph.getProperty(LayoutOptions.DIRECTION)); // FIXME
                        }

                        // add an edge from the port to the node's center
                        port.withCenterEdge();

                        // put the dummy port in the same cluster as the node
                        if (parentCluster != null) {
                            parentCluster.addChildNode(port.cIndex);
                        }
                    }
                }

            } else {
                // this is a compound node, create a cluster for it
                RectangularCluster compoundCluster = new RectangularCluster();
                KShapeLayout compoundLayout = node.getData(KShapeLayout.class); 
                KVector absolute = KimlUtil.toAbsolute(compoundLayout.createVector(), parentNode);
                compoundCluster.setBounds(new Rectangle(absolute.x, absolute.x
                        + compoundLayout.getWidth(), absolute.y, absolute.y
                        + compoundLayout.getHeight()));

                // the top level nodes are not cumulated within a cluster
                if (parentCluster == null) {
                    graph.rootCluster.addChildCluster(compoundCluster);
                } else {
                    parentCluster.addChildCluster(compoundCluster);
                }
                clusterMap.put(node, compoundCluster);
                
                // set spacing and border spacing
                float spacing = 0;
                if (node.getParent() != null) {
                    spacing = node.getParent().getData(KShapeLayout.class)
                                    .getProperty(CoLaProperties.SPACING);
                    compoundCluster.setMargin(spacing);
                }

                KInsets kInsets = compoundLayout.getPadding();
                float borderSpacing =
                        node.getData(KShapeLayout.class).getProperty(CodaflowProperties.BORDER_SPACING);
                float padding = spacing / 2f + Math.max(borderSpacing, 0);
                // Box as Padding -> left, right, top, bottom
                // recursively convert the children
                compoundCluster.setPadding(new Box(padding + kInsets.getLeft(), padding
                        + kInsets.getRight(), kInsets.getTop(), kInsets.getBottom()));

                recImportNodes(node, compoundCluster);
            }
        }

    }

    /**
     * through ports are replaced by possibly multiple {@link CEdge} between atomic {@link KNode}s.
     * Recursively transforms {@link KEdge}s to {@link CEdge}. Edges that cross hierarchy boundaries
     */
    private void recImportEdges(final KNode parentNode) {

        for (KNode node : parentNode.getChildren()) {

            // we start a atomic nodes
            if (node.getChildren().isEmpty()) {
                for (KEdge edge : node.getOutgoingEdges()) {
                    
                    // for every edge 
                    List<KEdge> edgeChain = Lists.newLinkedList();
                    List<Pair<KPort, KVector>> checkpoints = Lists.newLinkedList();

                    introduceEdge(edge, edge.getSource(), edge, edge.getTarget(), edgeChain,
                            checkpoints, false);
                }
            } else {

                // recursively import edges of this compound node
                recImportEdges(node);
            }
        }
    }

    private void introduceEdge(final KEdge edge, final KNode startNode, final KEdge startEdge,
            final KNode currentTarget, final List<KEdge> edgeChain, 
            final List<Pair<KPort, KVector>> checkpoints, final boolean hyperedge) {

        edgeChain.add(edge);

        // if the target or the target port's node is atomic, finish the edge
        if (edge.getTarget().getChildren().isEmpty()) {
            CNode src = nodeMap.get(startNode);
            CNode tgt = nodeMap.get(edge.getTarget());
            CPort srcPort = portMap.get(startEdge.getSourcePort());
            CPort tgtPort = portMap.get(edge.getTargetPort());

            CEdge cedge = new CEdge(graph, src, srcPort, tgt, tgtPort);
            edgeMap.put(edge, cedge);
            cedge.copyProperties(edge.getData(KLayoutData.class));
            cedge.setProperty(CGraphProperties.ORIGIN, edge);
            cedge.init();

            // spans the edge hierarchy levels?
            if (!startNode.getParent().equals(currentTarget.getParent())) {
                cedge.crossHierarchy = true;
                cedge.setProperty(InternalCodaflowProperties.EDGE_CHAIN, edgeChain);
                cedge.setProperty(InternalCodaflowProperties.EDGE_CHECKPOINTS, checkpoints);
            }
            
            // hyperedge?
            if (hyperedge) {
                cedge.setProperty(InternalCodaflowProperties.HIERARCHICAL_HYPEREDGE, true);
            }

            src.getOutgoingEdges().add(cedge);
            if (srcPort != null) {
                srcPort.getOutgoingEdges().add(cedge);
            }
            tgt.getIncomingEdges().add(cedge);
            if (tgtPort != null) {
                tgtPort.getIncomingEdges().add(cedge);
            }

        }

        // if the target is a hierarchical port, follow that port!
        if (edge.getTargetPort() != null) {

            // and add the port's position to a list of "checkpoints"
            KShapeLayout portLayout = edge.getTargetPort().getData(KShapeLayout.class);
            KVector cp = portLayout.createVector();
            // make sure the checkpoint lies _on_ the node boundary
            switch (portLayout.getProperty(LayoutOptions.PORT_SIDE)) {
                case NORTH:
                    cp.add(portLayout.getWidth() / 2f, portLayout.getHeight());
                    break;
                case EAST:
                    cp.add(0, portLayout.getHeight() / 2f);
                    break;
                case SOUTH:
                    cp.add(portLayout.getWidth() / 2f, 0);
                    break;
                case WEST:
                    cp.add(portLayout.getWidth(), portLayout.getHeight() / 2f);
                    break;
            }
            // convert it to a global position
            cp = KimlUtil.toAbsolute(cp, edge.getTarget());
            
            // FIXME why calc the position here?
            checkpoints.add(Pair.of(edge.getTargetPort(), cp));
            
            // if more than 3 edges connect to the same port, we consider
            // the 'overall' edge to be an hyperedge
            boolean isHyperedge = edge.getTargetPort().getEdges().size() > 2;
            
            for (KEdge portEdge : getOutgoingEdges(edge.getTargetPort())) {
                // make sure to copy the lists, as we might follow multiple edges
                introduceEdge(portEdge, startNode, startEdge, portEdge.getTarget(),
                        Lists.newArrayList(edgeChain), Lists.newArrayList(checkpoints), 
                        isHyperedge);
            }

        }

    }

    private Iterable<KEdge> getOutgoingEdges(final KPort port) {
        return Iterables.filter(port.getEdges(), new Predicate<KEdge>() {
            public boolean apply(final KEdge edge) {
                return edge.getSource().equals(port.getNode());
            }
        });
    }

    
    /*
     * ------------------------ Layout Application -------------------------------------------
     */
    
    /**
     * {@inheritDoc}
     */
    public void applyLayout(final CGraph constrainedGraph) {

        double borderSpacing = graph.getProperty(CodaflowProperties.BORDER_SPACING).doubleValue();

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
        // also consider the clusters here
        for (Cluster cluster : clusterMap.values()) {
            RectangularCluster c = (RectangularCluster) cluster;
            minX = Math.min(minX, c.getBounds().getMinX());
            minY = Math.min(minY, c.getBounds().getMinY());
            maxX = Math.max(maxX, c.getBounds().getMaxX());
            maxY = Math.max(maxY, c.getBounds().getMaxY());
        }

        // the global offset by which the whole diagram is shifted
        final KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

        /*
         * Clusters
         */
        for (Entry<KNode, Cluster> clusterEnty : clusterMap.entrySet()) {
            KNode knode = clusterEnty.getKey();
            KShapeLayout layout = knode.getData(KShapeLayout.class);
            RectangularCluster c = (RectangularCluster) clusterEnty.getValue();
            
            // clusters in clusters have to be offset properly
            KVector relative = relativeToCluster(c, offset);

            layout.setXpos((float) (relative.x));
            layout.setYpos((float) (relative.y));
            layout.setWidth((float) (c.getBounds().width()));
            layout.setHeight((float) (c.getBounds().height()));
            
            // 
            layout.setProperty(LayoutOptions.SIZE_CONSTRAINT, SizeConstraint.fixed());
        }

        /*
         * Nodes
         */
        for (CNode n : graph.getChildren()) {
            KShapeLayout layout =
                    n.getProperty(CGraphProperties.ORIGIN).getData(KShapeLayout.class);

            // if the node is contained in a cluster we have to offset it
            KVector relative = relativeToCluster(n, offset);
            layout.setXpos((float) relative.x);
            layout.setYpos((float) relative.y);

            // ports
            if (!n.getProperty(LayoutOptions.PORT_CONSTRAINTS).isPosFixed()) {
                for (CPort p : n.getPorts()) {
                    if (p.cEdgeIndex != -1) {
                        KShapeLayout portLayout =
                                p.getProperty(CGraphProperties.ORIGIN)
                                        .getData(KShapeLayout.class);
                        // ports are relative to the parent in KGraph
                        KVector relativePort = p.getRelativePos();
                        portLayout.setXpos((float) relativePort.x);
                        portLayout.setYpos((float) relativePort.y);
                    }
                }
            }
        }

        // should we reposition hierarchical ports?
        if (repositionHierarchicalPorts) {
            Set<KPort> repositionedPorts = Sets.newHashSet();

            // PASS 1 - ACA aligned hierarchy crossing edges
            for (CNode n : constrainedGraph.getChildren()) {
                for (CPort p : n.getPorts()) {
                    final Iterable<CEdge> alignedEdges =
                            Iterables.filter(p.getIncomingEdges(), Predicates.and(
                                    ColaPredicates.PREDICATE_HIERARCHICAL_EDGE,
                                    ColaPredicates.PREDICATE_ACA_ALIGNED_EDGE));
                    for (CEdge e : alignedEdges) {
                        repositionHierarchicalPorts(e, offset, repositionedPorts);
                    }
                }
            }

            // PASS 2 - All the order hierarchy crossing edges
            for (CNode n : constrainedGraph.getChildren()) {
                for (CPort p : n.getPorts()) {
                    final Iterable<CEdge> alignedEdges =
                            Iterables.filter(p.getIncomingEdges(),
                                    Predicates.and(ColaPredicates.PREDICATE_HIERARCHICAL_EDGE,
                                    Predicates.not(ColaPredicates.PREDICATE_ACA_ALIGNED_EDGE)));
                    for (CEdge e : alignedEdges) {
                        repositionHierarchicalPorts(e, offset, repositionedPorts);
                    }
                }
            }
        }
        
        // transfer layout to the edges 
        for (CEdge e : edgeMap.values()) {

            if (e.crossHierarchy) {
                applyLayoutToCrossHierarchyEdge(e, offset);
            } else {
                applyLayoutToEdge(e, offset);
            }
        }

        // resize the parent node
        KNode root = (KNode) graph.getProperty(CGraphProperties.ORIGIN);
        KInsets insets = root.getData(KShapeLayout.class).getPadding();
        double width = (maxX - minX) + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        double height = (maxY - minY) + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(root, (float) width, (float) height, false, true);
    }
    
    
    
    /*
     * ------------------------ Layout Application (Edges) --------------------------------------
     */
    
    /**
     * If {@link CEdge#bendpoints} contains bendpoints we write these back to the original
     * {@link KEdge}. Otherwise a straight line is assigned between the attachement points of the
     * edge.
     */
    private void applyLayoutToEdge(final CEdge e, final KVector offset) {

        KEdge edge = (KEdge) e.getProperty(CGraphProperties.ORIGIN);
        KEdgeLayout layout = edge.getData(KEdgeLayout.class);

        // if bendpoints were specified, use these
        if (!e.bendpoints.isEmpty()) {
            KVectorChain chain = new KVectorChain(e.bendpoints);

            // note, that we have to consider hierarchy as well
            // is the source node contained in a cluster?
            KNode parent = ((KNode) e.getSource().getProperty(CGraphProperties.ORIGIN)).getParent();
            Cluster c = clusterMap.get(parent);
            KVector clusterPos = new KVector();
            if (c != null) {
                clusterPos = new KVector(c.getBounds().getMinX(), c.getBounds().getMinY());
                clusterPos.negate();
            } else {
                clusterPos.add(offset);
            }

            chain.offset(clusterPos);
            layout.applyVectorChain(chain);
            
        } else {
            // convert to relative coordinates (note that both are relative to the source's
            // parent)
            KVector relativeSrcPoint =
                    KimlUtil.toRelative(e.getSourcePoint(), edge.getSource().getParent());
            KVector relativeTgtPoint =
                    KimlUtil.toRelative(e.getTargetPoint(), edge.getSource().getParent());

            // apply new positions
            layout.getSourcePoint().applyVector(relativeSrcPoint.clone().add(offset));
            layout.getTargetPoint().applyVector(relativeTgtPoint.clone().add(offset));
        }
    }
    
    /**
     *  
     */
    private void applyLayoutToCrossHierarchyEdge(final CEdge e, final KVector offset) {
        
        // if some edge routing assigned sub routes
        List<KVectorChain> subRoutes = e.getProperty(InternalCodaflowProperties.EDGE_SUB_ROUTES);
        List<KEdge> edgeChain = e.getProperty(InternalCodaflowProperties.EDGE_CHAIN);
        
        if (!subRoutes.isEmpty()) {
            Iterator<KVectorChain> subRoutesIt = subRoutes.iterator();
            Iterator<KEdge> edgeChainIt = edgeChain.iterator();

            boolean first = true;
            
            while (subRoutesIt.hasNext() && edgeChainIt.hasNext()) {
                final KVectorChain vc = subRoutesIt.next();
                final KEdge edge = edgeChainIt.next();
                
                // move the hierarchical target ports (not the last port connecting 
                // to an atomic node)
                if (edgeChainIt.hasNext() && edge.getTargetPort() != null) {
                    KShapeLayout portLayout = edge.getTargetPort().getData(KShapeLayout.class);
                    KVector portPos = KimlUtil.toRelative(vc.getLast().clone(), edge.getTarget());
                    switch (portLayout.getProperty(LayoutOptions.PORT_SIDE)) {
                    case NORTH:
                        portPos.add(-portLayout.getWidth() / 2f, -portLayout.getHeight());
                        break;
                    case EAST: 
                        portPos.add(0, -portLayout.getHeight() / 2f);
                        break;
                    case SOUTH:
                        portPos.add(-portLayout.getWidth() / 2f, 0);
                        break;
                    case WEST:
                        portPos.add(-portLayout.getWidth(), -portLayout.getHeight() / 2f);
                    }
                    portLayout.applyVector(portPos.add(offset));
                }
                
                // take care of possible margins on the "start" node
                if (first && edge.getSourcePort() != null) {
                    KShapeLayout portLayout = edge.getSourcePort().getData(KShapeLayout.class);
                    KVector portCenter =
                            KimlUtil.toAbsolute(portLayout.createVector(), edge.getSource()).add(
                                    portLayout.getWidth() / 2f, portLayout.getHeight() / 2f);
                    KVector initialOffset = vc.getFirst().clone().sub(portCenter);
                    vc.getFirst().sub(initialOffset.add(offset));
                }
                first = false;
                
                KVector globalOffset = new KVector();
                // current coordinates are in global, translate them to relative
                if (KimlUtil.isDescendant(edge.getTarget(), edge.getSource())) {
                    // relative to the source
                    globalOffset = KimlUtil.toRelative(globalOffset, edge.getSource());
                } else if (edge.getSource().getParent().getParent() != null) {
                    // relative to the source's parent
                    globalOffset =
                            KimlUtil.toRelative(globalOffset, edge.getSource().getParent());
                }

                vc.offset(globalOffset);
                vc.offset(offset);
                
                // apply back to the original edge layout
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                edgeLayout.applyVectorChain(vc);
            }

        } else {
            
            // assign a straight line from source to target for each part of the edge chain
            List<KEdge> chain = e.getProperty(InternalCodaflowProperties.EDGE_CHAIN);
            if (chain != null) {
                for (KEdge kedge : chain) {
                    KEdgeLayout el = kedge.getData(KEdgeLayout.class);
                    el.getBendPoints().clear();

                    // get the docking positions
                    KVector srcPoint =
                            getClippedAnchorVector(kedge.getSource(),
                                    kedge.getSourcePort(), kedge.getTarget(),
                                    kedge.getTargetPort());
                    KVector tgtPoint =
                            getClippedAnchorVector(kedge.getTarget(),
                                    kedge.getTargetPort(), kedge.getSource(),
                                    kedge.getSourcePort());

                    // convert to relative coordinates
                    KVector relativeSrcPoint;
                    KVector relativeTgtPoint;
                    // if the target node is a child of the source node, the source node is
                    // the reference for the edge's coordinates
                    if (KimlUtil.isDescendant(kedge.getTarget(), kedge.getSource())) {
                        relativeSrcPoint = KimlUtil.toRelative(srcPoint, kedge.getSource());
                        relativeTgtPoint = KimlUtil.toRelative(tgtPoint, kedge.getSource());
                    } else {
                        // otherwise the source's parent node
                        relativeSrcPoint =
                                KimlUtil.toRelative(srcPoint, kedge.getSource().getParent());
                        relativeTgtPoint =
                                KimlUtil.toRelative(tgtPoint, kedge.getSource().getParent());
                    }

                    // apply new positions
                    // note that offsets have already been assigned to the nodes and ports
                    el.getSourcePoint().applyVector(relativeSrcPoint.clone());
                    el.getTargetPoint().applyVector(relativeTgtPoint.clone());
                }
            }
        }
    }
    
    
    /*
     * ------------------------ Convenience -------------------------------------------
     */

    /**
     * Note that the positions are transferred to a absolute coordinates and the returned
     * vector is in absolute coordinates as well.
     * 
     * @return a vector pointing to either the center of the {@code srcNode} or the {@srcNodePort}
     *         (the port is prioritized).
     */
    private KVector getClippedAnchorVector(final KNode srcNode, final KPort srcNodePort,
            final KNode tgtNode, final KPort tgtNodePort) {
        KVector sV = null;
        KVector srcSize = null;
        KShapeLayout sl = srcNode.getData(KShapeLayout.class);
        sV = KimlUtil.toAbsolute(getCenter(srcNode), srcNode.getParent());
        srcSize = new KVector(sl.getWidth(), sl.getHeight());

        if (srcNodePort != null) {
            KShapeLayout slp = srcNodePort.getData(KShapeLayout.class);
            // the port's position is relative to the node
            sV =
                    KimlUtil.toAbsolute(sl.createVector(), srcNode.getParent()).add(
                            getCenter(srcNodePort));
            srcSize = new KVector(slp.getWidth(), slp.getHeight());
        }
        KVector tV = new KVector();
        tV = KimlUtil.toAbsolute(getCenter(tgtNode), tgtNode.getParent());
        if (tgtNodePort != null) {
            // the port's position is relative to the node
            tV =
                    KimlUtil.toAbsolute(tgtNode.getData(KShapeLayout.class).createVector(),
                            tgtNode.getParent()).add(getCenter(tgtNodePort));
        }
        
        if (sV == null && tV == null) {
           return new KVector(); 
        }

        KVector v = tV.clone().sub(sV);
        KielerMath.clipVector(v, srcSize.x, srcSize.y);
        
        return v.add(sV);
    }
    
    private KVector getCenter(final KGraphElement kge) {
        KShapeLayout sl = kge.getData(KShapeLayout.class);
        return sl.createVector().add(sl.getWidth() / 2f, sl.getHeight() / 2f);
    }
    
    /**
     * Every node's position is in global coordinates. We translate this back to the position
     * relative to the parent node.
     */
    private KVector relativeToCluster(final CNode node, final KVector offset) {

        KVector nodePos = node.getPos();
        KNode parent = ((KNode) node.getProperty(CGraphProperties.ORIGIN)).getParent();
        Cluster c = clusterMap.get(parent);
        if (c == null) {
            // no offset required, we are on the root level
            return nodePos.clone().add(offset);
        } else {
            // if it's a nested cluster we have to offset that
            KVector clusterPos = new KVector(c.getBounds().getMinX(), c.getBounds().getMinY());
            return nodePos.clone().sub(clusterPos);
        }
    }

    private KVector relativeToCluster(final Cluster c, final KVector offset) {

        KVector clusterPos = new KVector(c.getBounds().getMinX(), c.getBounds().getMinY());

        // retrieve the parent cluster
        KNode node = clusterMap.inverse().get(c);
        Cluster parentCluster = clusterMap.get(node.getParent());

        if (parentCluster == null) {
            return clusterPos.clone().add(offset);
        } else {
            KVector parentClusterPos =
                    new KVector(parentCluster.getBounds().getMinX(), parentCluster.getBounds()
                            .getMinY());
            return clusterPos.clone().sub(parentClusterPos);
        }
    }

    /**
     * 
     * @param e
     * @param offset
     * @param fixedPortSet
     *            a set with ports that must not moved.
     */
    private void repositionHierarchicalPorts(final CEdge e, final KVector offset,
            final Set<KPort> fixedPortSet) {

        List<KEdge> edgeChain = e.getProperty(InternalCodaflowProperties.EDGE_CHAIN);
        // initialize the line connecting the centers of the start and end port
        KVector srcPnt;
        KVector tgtPnt;
        if (e.srcPort == null || e.tgtPort == null) {
            // use the nodes' centers
            srcPnt = e.src.getRectCenterRaw();
            tgtPnt = e.tgt.getRectCenterRaw();
        } else {
            srcPnt = e.srcPort.getRectCenterRaw();
            tgtPnt = e.tgtPort.getRectCenterRaw();
        }

        final Line2D.Double line = new Line2D.Double(srcPnt.x, srcPnt.y, tgtPnt.x, tgtPnt.y);

        // iterate through all edge but the last and
        // reposition the target port of all intermediate ports
        for (KEdge kedge : edgeChain.subList(0, edgeChain.size() - 1)) {

            if (fixedPortSet.contains(kedge.getTargetPort())) {
                continue;
            }
            
            // find the currently surrounding cluster
            Cluster c = clusterMap.get(kedge.getTarget());
            Rectangle bounds = c.getBounds();
            final Rectangle2D rect =
                    new Rectangle2D.Double(bounds.getMinX(), bounds.getMinY(), bounds.width(),
                            bounds.height());

            final Pair<KVector, PortSide> intersection = CodaflowUtil.getIntersectionPoint(line, rect);

            if (intersection != null) {
                KShapeLayout portLayout = kedge.getTargetPort().getData(KShapeLayout.class);

                // the calculated intersection bound lies exactly on the rectangles boundary,
                // hence we have to shift the port into a specific direction according to its size
                // also, the intersection refers to the center position, so add half the dimension
                KVector positionOffset = new KVector();
                switch (intersection.getSecond()) {
                case NORTH:
                    positionOffset.x = portLayout.getWidth() / 2d;
                    positionOffset.y = portLayout.getHeight();
                    break;
                case EAST:
                    positionOffset.x = 0;
                    positionOffset.y = portLayout.getHeight() / 2d;
                    break;
                case SOUTH:
                    positionOffset.x = portLayout.getWidth() / 2d;
                    positionOffset.y = 0;
                    break;
                case WEST:
                    positionOffset.x = portLayout.getWidth();
                    positionOffset.y = portLayout.getHeight() / 2d;
                    break;
                default:
                    // we dont care
                }

                // assign the new side of the port
                portLayout.setProperty(LayoutOptions.PORT_SIDE, intersection.getSecond());

                // determine the new position, relative to the parent node
                KVector clusterPos = new KVector(bounds.getMinX(), bounds.getMinY());
                KVector newPos = new KVector();
                newPos.add(intersection.getFirst()) // intersection point
                        .sub(clusterPos) // relative to the cluster
                        .sub(positionOffset); // proper port position

                portLayout.applyVector(newPos);
                
                // we moved this port, remember to not move it again
                fixedPortSet.add(kedge.getTargetPort());
            }
        }

    }
}

