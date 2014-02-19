/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.compound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.UnsupportedGraphException;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.importexport.ImportUtil;
import de.cau.cs.kieler.klay.layered.importexport.KGraphImporter;
import de.cau.cs.kieler.klay.layered.intermediate.PortListSorter;
import de.cau.cs.kieler.klay.layered.intermediate.PortSideProcessor;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 *
 * @author msp
 */
public class CompoundGraphPostprocessor implements ILayoutProcessor {
    
    /** the layout algorithm used for regular layout runs. */
    private KlayLayered klayLayered;
    /** the hash code counter used to create graph elements. */
    private HashCodeCounter hashCodeCounter;
    /** map of generated cross-hierarchy edges. */
    private final Multimap<KEdge, CrossHierarchyEdge> crossHierarchyMap = HashMultimap.create();
    /** map of input layered graphs to output layered graphs of the layer-based algorithm. */
    private final Map<LGraph, LGraph> layeredGraphMap = Maps.newHashMap();
    /** set of external ports that have already been positioned. */
    private final Set<KPort> positionedPorts = Sets.newHashSet();

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph lgraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Compound graph postprocessor", 1);
        
        for (KEdge kedge : crossHierarchyMap.keySet()) {
            List<CrossHierarchyEdge> crossHierarchyEdges = new ArrayList<CrossHierarchyEdge>(
                    crossHierarchyMap.get(kedge));
            // put the cross-hierarchy edges in proper order from source to target
            Collections.sort(crossHierarchyEdges, new Comparator<CrossHierarchyEdge>() {
                public int compare(final CrossHierarchyEdge edge1, final CrossHierarchyEdge edge2) {
                    if (edge1.type == PortType.OUTPUT && edge2.type == PortType.INPUT) {
                        return -1;
                    } else if (edge1.type == PortType.INPUT && edge2.type == PortType.OUTPUT) {
                        return 1;
                    }
                    int level1 = hierarchyLevel(edge1.parentNode, kgraph);
                    int level2 = hierarchyLevel(edge2.parentNode, kgraph);
                    if (edge1.type == PortType.OUTPUT) {
                        // from deeper level to higher level
                        return level2 - level1;
                    } else {
                        // from higher level to deeper level
                        return level1 - level2;
                    }
                }
            });
            
            // determine the reference node for all bend points
            KNode referenceNode = kedge.getSource();
            if (!KimlUtil.isDescendant(kedge.getTarget(), referenceNode)) {
                referenceNode = referenceNode.getParent();
            }

            // check whether there are any junction points
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            edgeLayout.getBendPoints().clear();
            KVectorChain junctionPoints = edgeLayout.getProperty(LayoutOptions.JUNCTION_POINTS);
            if (Iterables.any(crossHierarchyEdges, new Predicate<CrossHierarchyEdge>() {
                public boolean apply(final CrossHierarchyEdge chEdge) {
                    KVectorChain ledgeJPs = chEdge.ledge.getProperty(LayoutOptions.JUNCTION_POINTS);
                    return ledgeJPs != null && !ledgeJPs.isEmpty();
                }
            })) {
                if (junctionPoints == null) {
                    junctionPoints = new KVectorChain();
                    edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, junctionPoints);
                } else {
                    junctionPoints.clear();
                }
            } else if (junctionPoints != null) {
                edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, null);
            }
            
            // apply the computed layouts to the cross-hierarchy edge
            KVector lastPoint = null;
            for (CrossHierarchyEdge chEdge : crossHierarchyEdges) {
                LGraph layeredGraph = layeredGraphMap.get(chEdge.lgraph);
                KVector offset = new KVector(layeredGraph.getOffset());
                KimlUtil.toAbsolute(offset, chEdge.parentNode);
                KimlUtil.toRelative(offset, referenceNode);
                KVectorChain bendPoints = chEdge.ledge.getBendPoints().translate(offset);
                // Note: if an NPE occurs here, that means KLay Layered has replaced the original edge
                KVector sourcePoint = chEdge.ledge.getSource().getAbsoluteAnchor().add(offset);
                KVector targetPoint = chEdge.ledge.getTarget().getAbsoluteAnchor().add(offset);

                if (chEdge.ledge.getSource().getNode().getProperty(Properties.ORIGIN)
                        == kedge.getSource()
                        || chEdge.parentNode == kedge.getSource()) {
                    edgeLayout.getSourcePoint().applyVector(sourcePoint);
                } else if (lastPoint != null) {
                    KVector nextPoint = targetPoint;
                    if (!bendPoints.isEmpty()) {
                        nextPoint = bendPoints.getFirst();
                    }
                    if (Math.abs(lastPoint.x - nextPoint.x) > OrthogonalRoutingGenerator.TOLERANCE
                        && Math.abs(lastPoint.y - nextPoint.y) > OrthogonalRoutingGenerator.TOLERANCE) {
                        // add the source point as bend point to properly connect the hierarchy levels
                        KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                        bendPoint.applyVector(sourcePoint);
                        edgeLayout.getBendPoints().add(bendPoint);
                    }
                }
                
                for (KVector point : bendPoints) {
                    KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    bendPoint.applyVector(point);
                    edgeLayout.getBendPoints().add(bendPoint);
                }
                
                if (chEdge.ledge.getTarget().getNode().getProperty(Properties.ORIGIN)
                        == kedge.getTarget()
                        || chEdge.parentNode == kedge.getTarget()) {
                    edgeLayout.getTargetPoint().applyVector(targetPoint);
                }
                if (bendPoints.isEmpty()) {
                    lastPoint = sourcePoint;
                } else {
                    lastPoint = bendPoints.getLast();
                }
                
                // copy junction points
                KVectorChain ledgeJPs = chEdge.ledge.getProperty(LayoutOptions.JUNCTION_POINTS);
                if (ledgeJPs != null) {
                    junctionPoints.addAll(ledgeJPs.translate(offset));
                }
            }
        }

        monitor.done();
    }
    
    /**
     * Compute the hierarchy level of the given node.
     * 
     * @param node a node
     * @param kgraph the containing hierarchical graph
     * @return the hierarchy level (higher number means the node is nested deeper)
     */
    private static int hierarchyLevel(final KNode node, final KNode kgraph) {
        KNode current = node;
        int level = 0;
        while (current != null) {
            if (current == kgraph) {
                return level;
            }
            current = current.getParent();
            level++;
        }
        // the given node is not an ancestor of the graph node
        throw new IllegalArgumentException();
    }

}
