/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.components;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.layered.compaction.components.IComponent;
import de.cau.cs.kieler.klay.layered.compaction.components.IConnectedComponents;
import de.cau.cs.kieler.klay.layered.compaction.components.IExternalEdge;
import de.cau.cs.kieler.klay.layered.compaction.components.OneDimensionalComponentsCompaction;
import de.cau.cs.kieler.klay.layered.compaction.recthull.Point;
import de.cau.cs.kieler.klay.layered.compaction.recthull.RectilinearConvexHull;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphUtil;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LShape;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Contains implementations of the {@link IConnectedComponents} and {@link IComponent} interfaces
 * for {@link LGraph}s. As a consequence allows to compact a set of components using one dimensional
 * compaction.
 * 
 * @author uru
 * 
 * @see OneDimensionalComponentsCompaction
 */
public class ComponentsCompactor {

    /** Instance of the compactor we use. */
    private OneDimensionalComponentsCompaction<LNode, LEdge> compactor;
    /** The size of the graph before compaction. */
    private KVector originalGraphSize;
    
    /** The offset after compaction has been applied. */
    private KVector yetAnotherOffset;
    /** The graph size after compaciton. */
    private KVector compactedGraphSize;
    
    /** Epsilon for double comparisons. */
    private static final double EPSILON = 0.0001;

    // ------------------------------------------------------------------------------------------------
    //                                          public API
    // ------------------------------------------------------------------------------------------------
    
    /**
     * @param graphs
     *            the components to be compacted
     * @param originalGraphsSize
     *            the size of the overall graph as it is currently
     * @param spacing
     *            the desired spacing to be preserved between any pair of components
     */
    public void compact(final List<LGraph> graphs, final KVector originalGraphsSize,
            final double spacing) {

        this.originalGraphSize = originalGraphsSize;
        
        // from the lgraphs, create connected components
        IConnectedComponents<LNode, LEdge> ccs = new InternalConnectedComponents(graphs);
        
        // for every component we create an element in the compactor
        compactor = OneDimensionalComponentsCompaction.init(ccs, spacing);
        
        // execute compaction
        compactor.compact(new BasicProgressMonitor());

        yetAnotherOffset = new KVector();
        compactedGraphSize = compactor.getGraphSize();
        
        // apply the positions
        for (IComponent<LNode, LEdge> cc : ccs.getComponents()) {
            
            // retrieve the common offset for the currently handled connected component
            KVector offset = compactor.getOffset(cc);

            // move it
            LGraphUtil.offsetGraph(((InternalComponent) cc).graph, offset.x, offset.y);

            // adjust positions of external ports
            for (LNode n : cc.getNodes()) {
                
                if (n.getType() == NodeType.EXTERNAL_PORT) {

                    KVector newPos = getExternalPortPosition(n.getPosition(), 
                            n.getProperty(InternalProperties.EXT_PORT_SIDE));
                    
                    n.getPosition().reset().add(newPos);
                }
            }
        }
        
        // external edges contribute to the graph's size ... however, only certain segments do.
        for (IComponent<LNode, LEdge> cc : ccs.getComponents()) {
            for (LEdge e : cc.getExternalEdges()) {
                KVectorChain vc = new KVectorChain(e.getBendPoints());
                vc.add(0, e.getSource().getAbsoluteAnchor());
                vc.add(e.getTarget().getAbsoluteAnchor());
                
                KVector last = null;
                for (KVector v : vc) {
                    if (last == null) {
                        last = v;
                        continue;
                    }
                    if (DoubleMath.fuzzyEquals(last.x, v.x, EPSILON)) {
                        yetAnotherOffset.x = Math.min(yetAnotherOffset.x, last.x);
                        compactedGraphSize.x = Math.max(compactedGraphSize.x, last.x);
                    } else if (DoubleMath.fuzzyEquals(last.y, v.y, EPSILON)) {
                        yetAnotherOffset.y = Math.min(yetAnotherOffset.y, last.y);
                        compactedGraphSize.y = Math.max(compactedGraphSize.y, last.y);
                    }
                    last = v;
                }
            }
        }
        
        yetAnotherOffset.negate();
        compactedGraphSize.add(yetAnotherOffset);
    }
    
    /**
     * @return the offset by which each component has to be shifted after compaction such that the
     *         top-left-most point is (0, 0).
     */
    public KVector getOffset() {
        return yetAnotherOffset;
    }
    
    /**
     * @return the new graph size.
     */
    public KVector getGraphSize() {
        return compactedGraphSize;
    }
    
    // ------------------------------------------------------------------------------------------------
    //                                          private API
    // ------------------------------------------------------------------------------------------------
    
    private KVector getExternalPortPosition(final KVector pos, final PortSide ps) {
        switch (ps) {
        case NORTH:
            return new KVector(pos.x, 0);
        case EAST:
            return new KVector(originalGraphSize.x, pos.y);
        case SOUTH:
            return new KVector(pos.x, originalGraphSize.y);
        case WEST:
            return new KVector(0, pos.y);
        }
        return pos.clone();
    }
    
    /**
     * Implementation of the {@link IConnectedComponents} interface. Holds the components 
     * and whether the overall graph contains external edges
     */
    private class InternalConnectedComponents implements IConnectedComponents<LNode, LEdge> {

        private List<IComponent<LNode, LEdge>> components = Lists.newArrayList();
        private boolean containsExternalPorts = false;
        
        public InternalConnectedComponents(final List<LGraph> ccs) {
            // calculate the connected components
            for (LGraph g : ccs) {
                InternalComponent cc = new InternalComponent(g);
                components.add(cc);
                containsExternalPorts |= !cc.getExternalPortSides().isEmpty();
            }
        }
        
        @Override
        public Iterator<IComponent<LNode, LEdge>> iterator() {
            return components.iterator();
        }

        @Override
        public List<IComponent<LNode, LEdge>> getComponents() {
            return components;
        }

        @Override
        public boolean isContainsExternalPorts() {
            return containsExternalPorts;
        }
    }
    
    /**
     * Internal implementation of the {@link IExternalEdge} interface. Contains the {@link LEdge}
     * which represents the external edge alongside which of the two ports is the external port and
     * on which side of the parent node the port is located.
     */
    private final class InternalExternalEdge implements IExternalEdge<LEdge> {

        private LEdge edge;
        private LPort externalPort;
        private PortSide externalPortSide;
        
        public InternalExternalEdge(final LEdge edge) {
            this.edge = edge;
            if (edge.getSource().getNode().getType() == NodeType.EXTERNAL_PORT) {
                externalPort = edge.getSource();
                externalPortSide =
                        edge.getSource().getNode().getProperty(InternalProperties.EXT_PORT_SIDE);
            } else if (edge.getTarget().getNode().getType() == NodeType.EXTERNAL_PORT) {
                externalPort = edge.getTarget();
                externalPortSide =
                        edge.getTarget().getNode().getProperty(InternalProperties.EXT_PORT_SIDE);
            } else {
                throw new IllegalArgumentException("Edge " + edge + " is not an external edge.");
            }
        }
        
        @Override
        public LEdge getEdge() {
            return edge;
        }

        @Override
        public boolean isVertical() {
            return PortSide.SIDES_NORTH_SOUTH.contains(externalPortSide);
        }
    }
    
    /**
     * Basically, this is a wrapper around an {@link LGraph} since the {@link LGraph}
     * represents a connected component, if the user desires to.
     */
    private class InternalComponent implements IComponent<LNode, LEdge> {
        
        private LGraph graph;
        private List<IExternalEdge<LEdge>> externalEdges = Lists.newArrayList();
        
        private List<Rectangle> rectilinearConvexHull;
        private Map<IExternalEdge<LEdge>, List<Rectangle>> externalEdgeHulls;
        
        public InternalComponent(final LGraph graph) {
            this.graph = graph;
            
            for (LNode n : graph.getLayerlessNodes()) {
                for (LEdge e : n.getOutgoingEdges()) {
                    if (isExternalEdge(e)) {
                        InternalExternalEdge iee = new InternalExternalEdge(e);
                        externalEdges.add(iee);
                    }
                }
            }
            
            calculateHulls();
        }
        
        @Override
        public List<LNode> getNodes() {
            return graph.getLayerlessNodes();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public List<LEdge> getExternalEdges() {
            List<LEdge> edges = Lists.newArrayList();
            for (IExternalEdge<LEdge> ee : externalEdges) {
                edges.add(ee.getEdge());
            }
            return edges;
        }
        
        @Override
        public Set<PortSide> getExternalPortSides() {
            return graph.getProperty(InternalProperties.EXT_PORT_CONNECTIONS);
        }

        @Override
        public List<Rectangle> getHull() {
            return rectilinearConvexHull;
        }

        @Override
        public Map<IExternalEdge<LEdge>, List<Rectangle>> getExternalEdgeHulls() {
            return externalEdgeHulls;
        }
        
        private void calculateHulls() {
            
            // hull of the cc's nodes
            if (rectilinearConvexHull == null) {
                Iterable<Point> cloud = createComponentPointSet(this);
                RectilinearConvexHull rch = RectilinearConvexHull.of(cloud);
                rectilinearConvexHull = rch.splitIntoRectangles();
            }

            // hull of the cc's external edges
            if (externalEdgeHulls == null) {
                externalEdgeHulls = Maps.newLinkedHashMap();
                for (IExternalEdge<LEdge> e : externalEdges) {
                    RectilinearConvexHull rch = 
                            RectilinearConvexHull
                                    .of(createExternalEdgePointSet((InternalExternalEdge) e));
                    externalEdgeHulls.put(e, rch.splitIntoRectangles());
                }
            }
        }
        
        private Iterable<Point> createComponentPointSet(final IComponent<LNode, LEdge> cc) {

            final List<Point> pts = Lists.newArrayList();
            
            for (LNode n : graph.getLayerlessNodes()) {
                
                if (n.getType() == NodeType.EXTERNAL_PORT) {
                    continue;   
                }
                
                addLGraphElementBounds(pts, n, new KVector());

                // add bend points of the edges
                for (LEdge edge : n.getOutgoingEdges()) {
                    if (isExternalEdge(edge)) {
                        continue;
                    }
                    
                    for (KVector bp : edge.getBendPoints()) {
                        KVector absolute = bp;
                        pts.add(new Point(absolute.x, absolute.y));   
                    }
                }
                
                // laaaabel
                for (LLabel label : n.getLabels()) {
                    addLGraphElementBounds(pts, label, n.getPosition());
                }
            }
            
            return pts;
        }
        
        private void addLGraphElementBounds(final List<Point> pts, final LShape element,
                final KVector offset) {
            Margins margins = element.getProperty(LayoutOptions.MARGINS);

            // add bounding box of the node
            pts.add(new Point(element.getPosition().x - margins.left + offset.x, 
                    element.getPosition().y - margins.top + offset.y));
            pts.add(new Point(element.getPosition().x - margins.left + offset.x, 
                    element.getPosition().y + element.getSize().y + margins.bottom + offset.y));
            pts.add(new Point(
                    element.getPosition().x + element.getSize().x + margins.right + offset.x, 
                    element.getPosition().y - margins.top + offset.y));
            pts.add(new Point(
                    element.getPosition().x + element.getSize().x + margins.right + offset.x, 
                    element.getPosition().y + element.getSize().y + margins.bottom + offset.y));
        }
        
        private Iterable<Point> createExternalEdgePointSet(final InternalExternalEdge exEdge) {
  
            LEdge edge = exEdge.getEdge();
            List<Point> pts = Lists.newLinkedList();

            // TODO discuss this ... in klay the external ports do not reach "all the way" yet

            KVector s = edge.getSource().getAbsoluteAnchor();
            if (exEdge.externalPort == edge.getSource()) {
                s = getExternalPortPosition(s, exEdge.externalPortSide);
            }
            Point start = Point.from(s);
            // FIXME the hull algorithm requires at least four points
            pts.add(start);
            pts.add(start);
            
            for (KVector v : edge.getBendPoints()) {
                pts.add(Point.from(v));
                pts.add(Point.from(v));
            }
             
            KVector e = edge.getTarget().getAbsoluteAnchor();
            if (exEdge.externalPort == edge.getTarget()) {
                e = getExternalPortPosition(e, exEdge.externalPortSide);
            }
            Point end = Point.from(e);
            pts.add(end);
            pts.add(end);
            
            return pts;
        }
        
        private boolean isExternalEdge(final LEdge edge) {
            return edge.getSource().getNode().getType() == NodeType.EXTERNAL_PORT
                    || edge.getTarget().getNode().getType() == NodeType.EXTERNAL_PORT;
        }
        
    }
}
