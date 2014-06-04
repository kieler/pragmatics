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
package de.cau.cs.kieler.klay.gwt.client.layout;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphUtil;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Copied from {@link de.cau.cs.kieler.kiml.util.FixedLayoutProvider} and altered
 * to directly work with LGraphs.
 * 
 * A layout provider that sets fixed positions for all elements. These positions are taken
 * from the {@link LayoutOptions#POSITION} and {@link LayoutOptions#BEND_POINTS} options.
 * Elements that have no position option attached just stay where they are.
 * This is useful for at least two things:
 * <ul>
 *   <li>Fix the layout of a part of the diagram so it won't be affected by automatic layout.</li>
 *   <li>Apply a layout imported from somewhere else, e.g. the original layout that was
 *     manually created in another modeling tool.</li>
 * </ul>
 *
 * @author msp
 * @author uru
 */
public class FixedLayoutProvider {

    /** the layout provider id. */
    public static final String ID = "de.cau.cs.kieler.fixed";
    
    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15.0f;
    
    /**
     * Perform the layout.
     * 
     * @param lGraph
     *            the graph to layout
     * @param progressMonitor
     *            a progress monitor
     */
    public void doLayout(final LGraph lGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Fixed Layout", 1);
        EdgeRouting edgeRouting = lGraph.getProperty(LayoutOptions.EDGE_ROUTING);
        double maxx = 0, maxy = 0;
        
        for (LNode node : lGraph.getLayerlessNodes()) {
            // set the fixed position of the node, or leave it as it is
            KVector pos = node.getProperty(LayoutOptions.POSITION);
            if (pos != null) {
                node.getPosition().x = pos.x;
                node.getPosition().y = pos.y;
                // set the fixed size of the node
                // TODO Think about whether this makes sense with the new size constraint options.
                if (node.getProperty(LayoutOptions.SIZE_CONSTRAINT).contains(
                        SizeConstraint.MINIMUM_SIZE)) {
                    
                    float width = node.getProperty(LayoutOptions.MIN_WIDTH);
                    float height = node.getProperty(LayoutOptions.MIN_HEIGHT);
                    if (width > 0 && height > 0) {
                        LGraphUtil.resizeNode(node, new KVector(width, height), true, true);
                    }
                }
            }
            maxx = Math.max(maxx, node.getPosition().x + node.getSize().x);
            maxy = Math.max(maxy, node.getPosition().y + node.getSize().y);
            
            // set the fixed position of the node labels, or leave them as they are
            for (LLabel label : node.getLabels()) {
                pos = label.getProperty(LayoutOptions.POSITION);
                if (pos != null) {
                    label.getPosition().x = pos.x;
                    label.getPosition().y = pos.y;
                }
                maxx = Math.max(maxx, node.getPosition().x + label.getPosition().x
                        + label.getSize().x);
                maxy = Math.max(maxy, node.getPosition().y + label.getPosition().y
                        + label.getSize().y);
            }
                
            // set the fixed position of the ports, or leave them as they are
            for (LPort port : node.getPorts()) {
                pos = port.getProperty(LayoutOptions.POSITION);
                if (pos != null) {
                    port.getPosition().x = pos.x;
                    port.getPosition().y = pos.y;
                }
                double portx = node.getPosition().x + port.getPosition().x;
                double porty = node.getPosition().y + port.getPosition().y;
                maxx = Math.max(maxx, portx + port.getSize().x);
                maxy = Math.max(maxy, porty + port.getSize().y);
                
                // set the fixed position of the port labels, or leave them as they are
                for (LLabel label : port.getLabels()) {
                    pos = label.getProperty(LayoutOptions.POSITION);
                    if (pos != null) {
                        label.getPosition().x = pos.x;
                        label.getPosition().y = pos.y;
                    }
                    maxx = Math.max(maxx, portx + label.getPosition().x + label.getSize().x);
                    maxy = Math.max(maxy, porty + label.getPosition().y + label.getSize().y);
                }
            }
            
            // set fixed routing for the connected edges, or leave them as they are
            for (LEdge edge : node.getOutgoingEdges()) {
                KVector maxv = processEdge(edge, edgeRouting);
                maxx = Math.max(maxx, (float) maxv.x);
                maxy = Math.max(maxy, (float) maxv.y);
            }
            for (LEdge edge : node.getIncomingEdges()) {
                // if (edge.getSource().getParent() != layoutNode) {
                if (edge.getSource().getNode().getProperty(InternalProperties.NESTED_LGRAPH) != lGraph) {
                    KVector maxv = processEdge(edge, edgeRouting);
                    maxx = Math.max(maxx, (float) maxv.x);
                    maxy = Math.max(maxy, (float) maxv.y);
                }
            }
        }
        
        // set size of the parent node
        // border spacing and insets are included in #getActualSize
        
        // LGraphUtil.resizeNode(lGraph, new KVector(newWidth, newHeight), true, true);
        lGraph.getSize().x = maxx;
        lGraph.getSize().y = maxy;
        lGraph.setProperty(LayoutOptions.SIZE_CONSTRAINT, SizeConstraint.fixed());
        
        progressMonitor.done();
    }
    
    /**
     * Process an edge and its labels.
     * 
     * @param edge an edge
     * @param edgeRouting the global edge routing setting
     */
    private KVector processEdge(final LEdge edge, final EdgeRouting edgeRouting) {
        // boolean sameHierarchy = edge.getSource().getParent() == edge.getTarget().getParent();
        boolean sameHierarchy =
                edge.getSource().getNode().getProperty(InternalProperties.NESTED_LGRAPH) == edge
                        .getTarget().getNode().getProperty(InternalProperties.NESTED_LGRAPH);
        KVector maxv = new KVector();
        KVectorChain bendPoints = edge.getProperty(LayoutOptions.BEND_POINTS);
        // we need at least two bend points, since the source point and target point must be included
        if (bendPoints != null && bendPoints.size() >= 2) {
            edge.getBendPoints().clear();
            int i = 0;
            for (KVector v : bendPoints) {
                // source and target are specified relative to the parent node, 
                // thus we have to subtract node and port positions to get the anchor
                if (i == 0) {
                    KVector actual =
                            v.clone().sub(edge.getSource().getPosition())
                                    .sub(edge.getSource().getNode().getPosition());
                    edge.getSource().getAnchor().x = actual.x;
                    edge.getSource().getAnchor().y = actual.y;
                } else if (i == bendPoints.size() - 1) {
                    KVector actual =
                            v.clone().sub(edge.getTarget().getPosition())
                                    .sub(edge.getTarget().getNode().getPosition());
                    edge.getTarget().getAnchor().x = actual.x;
                    edge.getTarget().getAnchor().y = actual.y;
                } else {
                    edge.getBendPoints().add(v);
                }
                i++;
            }
        }
        
        // determine maximal coordinates
        if (sameHierarchy) {
            for (KVector point : edge.getBendPoints()) {
                maxv.x = Math.max(maxv.x, point.x);
                maxv.y = Math.max(maxv.y, point.y);
            }
        }
        
        // set the fixed position of the edge labels, or leave them as they are
        for (LLabel label : edge.getLabels()) {
            KVector pos = label.getProperty(LayoutOptions.POSITION);
            if (pos != null) {
                label.getPosition().x = pos.x;
                label.getPosition().y = pos.y;
            }
            if (sameHierarchy) {
                maxv.x = Math.max(maxv.x, label.getPosition().x + label.getSize().x);
                maxv.y = Math.max(maxv.y, label.getPosition().y + label.getSize().y);
            }
        }
        
        // TODO not yet supported
        // if orthogonal routing is selected, determine the junction points
        // Note: if the edge coordinates are not modified, the junction points are also ignored
        // if (edgeRouting == EdgeRouting.ORTHOGONAL) {
        // KVectorChain junctionPoints = LGraphUtil.determineJunctionPoints(edge);
        // if (junctionPoints.isEmpty()) {
        // edge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
        // } else {
        // edge.setProperty(LayoutOptions.JUNCTION_POINTS, junctionPoints);
        // }
        // }
        
        return maxv;
    }

}
