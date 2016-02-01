/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.graph.transform;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortLabelPlacement;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphUtil;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Implements the graph layout application aspect of {@link KGraphTransformer}.
 * 
 * @author cds
 */
class KGraphLayoutTransferrer {

    /**
     * Applies the layout information contained in the given LGraph to the KGraph elements it was
     * created from. All source KGraph elements are expected to be accessible through their LGraph
     * counterparts through the {@link InternalProperties#ORIGIN} property.
     * 
     * @param lgraph the LGraph whose layout information to apply.
     */
    public void applyLayout(final LGraph lgraph) {
        Object graphOrigin = lgraph.getProperty(InternalProperties.ORIGIN);
        if (!(graphOrigin instanceof KNode)) {
            return;
        }
        
        // The KNode that represents this graph in the original KGraph
        KNode parentKNode = (KNode) graphOrigin;
        
        // The LNode that represents this graph in the upper hierarchy level, if any
        LNode parentLNode = (LNode) lgraph.getProperty(InternalProperties.PARENT_LNODE);
        
        // Get the offset to be added to all coordinates
        KVector offset = new KVector(lgraph.getOffset());
        
        // Adjust offset (and with it the positions), if requested
        KShapeLayout parentLayout = parentKNode.getData(KShapeLayout.class);
        LInsets lInsets = lgraph.getInsets();
        KInsets kInsets = parentLayout.getInsets();
        
        // We may need to apply increased top/left insets
        final EnumSet<SizeOptions> sizeOptions = parentLayout.getProperty(LayoutOptions.SIZE_OPTIONS);
        KVector additionalInsets = new KVector();
        if (sizeOptions.contains(SizeOptions.APPLY_ADDITIONAL_INSETS)) {
            additionalInsets.x = lInsets.left - kInsets.getLeft();
            additionalInsets.y = lInsets.top - kInsets.getTop();
            offset.x += additionalInsets.x;
            offset.y += additionalInsets.y;
        }
        
        // Set node insets, if requested
        if (sizeOptions.contains(SizeOptions.COMPUTE_INSETS)) {
            kInsets.setBottom((float) lInsets.bottom);
            kInsets.setTop((float) lInsets.top);
            kInsets.setLeft((float) lInsets.left);
            kInsets.setRight((float) lInsets.right);
        }

        // Along the way, we collect the list of edges to be processed later
        List<LEdge> edgeList = Lists.newArrayList();

        // Process the nodes
        for (LNode lnode : lgraph.getLayerlessNodes()) {
            Object origin = lnode.getProperty(InternalProperties.ORIGIN);

            if (origin instanceof KNode) {
                applyNodeLayout(lnode, offset);
            } else if (origin instanceof KPort && parentLNode == null) {
                // It's an external port. Set its position if it hasn't already been done before
                KPort kport = (KPort) origin;
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                KVector portPosition = LGraphUtil.getExternalPortPosition(lgraph, lnode,
                        portLayout.getWidth(), portLayout.getHeight());
                portLayout.applyVector(portPosition);
            }

            // Collect edges, except if they go into a nested subgraph (those edges need to be
            // processed during one of the recursive calls so that any additional offsets are applied
            // correctly)
            for (LPort port : lnode.getPorts()) {
                port.getOutgoingEdges().stream()
                    .filter(edge -> !LGraphUtil.isDescendant(edge.getTarget().getNode(), lnode))
                    .forEach(edge -> edgeList.add(edge));
            }
        }
        
        // Collect edges that go from the current graph's representing LNode down into its descendants
        if (parentLNode != null) {
            for (LPort port : parentLNode.getPorts()) {
                port.getOutgoingEdges().stream()
                    .filter(edge -> LGraphUtil.isDescendant(edge.getTarget().getNode(), parentLNode))
                    .forEach(edge -> edgeList.add(edge));
            }
        }

        // Iterate through all edges
        EdgeRouting routing = parentLayout.getProperty(InternalProperties.EDGE_ROUTING);
        for (LEdge ledge : edgeList) {
            applyEdgeLayout(ledge, routing, offset, additionalInsets);
        }

        // Setup the parent node
        applyParentNodeLayout(lgraph);
        
        // Process nested subgraphs
        for (LNode lnode : lgraph.getLayerlessNodes()) {
            LGraph nestedGraph = lnode.getProperty(InternalProperties.NESTED_LGRAPH);
            if (nestedGraph != null) {
                applyLayout(nestedGraph);
            }
        }
    }

    /**
     * Applies layout information computed for the given node.
     * 
     * @param lnode
     *            the node that has the layout information.
     * @param offset
     *            offset to add to coordinates.
     */
    private void applyNodeLayout(final LNode lnode, final KVector offset) {
        final KNode knode = (KNode) lnode.getProperty(InternalProperties.ORIGIN);
        
        // Set the node position
        KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
        nodeLayout.setXpos((float) (lnode.getPosition().x + offset.x));
        nodeLayout.setYpos((float) (lnode.getPosition().y + offset.y));
        
        // Set the node size, if necessary
        if (!nodeLayout.getProperty(LayoutOptions.SIZE_CONSTRAINT).isEmpty()
                || lnode.getProperty(InternalProperties.NESTED_LGRAPH) != null) {
            nodeLayout.setWidth((float) lnode.getSize().x);
            nodeLayout.setHeight((float) lnode.getSize().y);
        }

        // Set port positions
        for (LPort lport : lnode.getPorts()) {
            Object origin = lport.getProperty(InternalProperties.ORIGIN);
            if (origin instanceof KPort) {
                KPort kport = (KPort) origin;
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                portLayout.applyVector(lport.getPosition());
                portLayout.setProperty(LayoutOptions.PORT_SIDE, lport.getSide());
            }
        }
        
        // Set node label positions, if they were not fixed
        // (that is at least one of the node or the label has a node label placement set)
        final boolean nodeHasLabelPlacement =
                !lnode.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT).isEmpty();
        
        for (LLabel llabel : lnode.getLabels()) {
            if (nodeHasLabelPlacement
                    || !llabel.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT).isEmpty()) {
                KLabel klabel = (KLabel) llabel.getProperty(InternalProperties.ORIGIN);
                KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                klabelLayout.applyVector(llabel.getPosition());
            }
        }
        
        // Set port label positions, if they were not fixed
        if (lnode.getProperty(LayoutOptions.PORT_LABEL_PLACEMENT) != PortLabelPlacement.FIXED) {
            for (LPort lport : lnode.getPorts()) {
                for (LLabel label : lport.getLabels()) {
                    KLabel klabel = (KLabel) label.getProperty(InternalProperties.ORIGIN);
                    KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                    klabelLayout.setWidth((float) label.getSize().x);
                    klabelLayout.setHeight((float) label.getSize().y);
                    klabelLayout.applyVector(label.getPosition());
                }
            }
        }
    }
    
    /**
     * Applies layout information computed for the given edge.
     * 
     * @param ledge
     *            the edge that has the layout information.
     * @param routing
     *            the kind of routing applied to edges.
     * @param offset
     *            offset to add to coordinates.
     * @param additionalInsets
     *            the additional insets that may have to be taken into account for hierarchical that go
     *            into the bowels of their source node. These are already included in the offset, but
     *            are required separately.
     */
    private void applyEdgeLayout(final LEdge ledge, final EdgeRouting routing, final KVector offset,
            final KVector additionalInsets) {
        
        KEdge kedge = (KEdge) ledge.getProperty(InternalProperties.ORIGIN);
        
        // Only the orthogonal edge routing algorithm supports self-loops. Thus, leave self-loops
        // untouched if another routing algorithm is selected.
        if (kedge == null) {
            return; 
        } else if (ledge.isSelfLoop()
                && routing != EdgeRouting.ORTHOGONAL && routing != EdgeRouting.SPLINES) {
            
            return;
        }
        
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        KVectorChain bendPoints = ledge.getBendPoints();
        KVector edgeOffset = offset;

        // Adapt the offset value and add the source port position to the vector chain
        KVector sourcePoint;
        if (LGraphUtil.isDescendant(ledge.getTarget().getNode(), ledge.getSource().getNode())) {
            // The external port's anchor position, relative to the node's top left corner
            LPort sourcePort = ledge.getSource();
            sourcePoint = KVector.sum(sourcePort.getPosition(), sourcePort.getAnchor());
            
            // The node's insets need to be subtracted since edges going into the node's bowels are
            // relative to the top left corner + insets
            // TODO This line assumes that for a compound node, the insets computed for its LGraph and
            //      for its representing LNode are the same, which doesn't always seem to be the case
            LInsets sourceInsets = sourcePort.getNode().getInsets();
            sourcePoint.add(-sourceInsets.left, -sourceInsets.top);
            
            // The source point will later have the passed offset added to it, which it doesn't actually
            // need, so we subtract it now
            sourcePoint.sub(offset);
            
            // What it does need, however, is any additional insets that may be present, so we
            // explicitly add them here
            sourcePoint.add(additionalInsets);
        } else {
            sourcePoint = ledge.getSource().getAbsoluteAnchor();
        }
        bendPoints.addFirst(sourcePoint);
        
        // Add the target port position to the vector chain, including additional offset
        KVector targetPoint = ledge.getTarget().getAbsoluteAnchor();
        if (ledge.getProperty(InternalProperties.TARGET_OFFSET) != null) {
            targetPoint.add(ledge.getProperty(InternalProperties.TARGET_OFFSET));
        }
        bendPoints.addLast(targetPoint);

        // Translate the bend points by the offset and apply the bend points
        bendPoints.offset(edgeOffset);
        edgeLayout.applyVectorChain(bendPoints);

        // Apply layout to labels
        for (LLabel label : ledge.getLabels()) {
            KLabel klabel = (KLabel) label.getProperty(InternalProperties.ORIGIN);
            KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
            klabelLayout.setWidth((float) label.getSize().x);
            klabelLayout.setHeight((float) label.getSize().y);
            klabelLayout.applyVector(label.getPosition().add(edgeOffset));
        }
        
        // Copy junction points
        KVectorChain junctionPoints = ledge.getProperty(LayoutOptions.JUNCTION_POINTS);
        if (junctionPoints != null) {
            junctionPoints.offset(edgeOffset);
            edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, junctionPoints);
        } else {
            edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, null);
        }

        // Mark the edge with information about its routing
        if (routing == EdgeRouting.SPLINES) {
            // SPLINES means that bend points shall be interpreted as control points for splines
            edgeLayout.setProperty(InternalProperties.EDGE_ROUTING, EdgeRouting.SPLINES);
        } else {
            // null means that bend points shall be interpreted as bend points
            edgeLayout.setProperty(InternalProperties.EDGE_ROUTING, null);
        }
    }

    /**
     * Applies layout information computed for the given graph.
     * 
     * @param lgraph
     *            the edge that has the layout information.
     */
    private void applyParentNodeLayout(final LGraph lgraph) {
        KNode knode = (KNode) lgraph.getProperty(InternalProperties.ORIGIN);
        KShapeLayout knodeLayout = knode.getData(KShapeLayout.class);
        
        KVector actualGraphSize = lgraph.getActualSize();
        knodeLayout.setProperty(LayoutOptions.SIZE_CONSTRAINT, SizeConstraint.fixed());

        if (lgraph.getProperty(InternalProperties.PARENT_LNODE) == null) {
            Set<GraphProperties> graphProps = lgraph.getProperty(InternalProperties.GRAPH_PROPERTIES);
            
            if (graphProps.contains(GraphProperties.EXTERNAL_PORTS)) {
                // Ports have positions assigned, the graph's size is final
                knodeLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
                KimlUtil.resizeNode(
                        knode,
                        (float) actualGraphSize.x,
                        (float) actualGraphSize.y,
                        false,
                        true);
            } else {
                // Ports have not been positioned yet - leave this for next layouter
                KimlUtil.resizeNode(
                        knode,
                        (float) actualGraphSize.x,
                        (float) actualGraphSize.y,
                        true,
                        true);
            }
        }
    }

}
