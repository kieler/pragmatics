/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml;


import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.GraphFeature;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Performs layout on a graph with hierarchy by executing a layout algorithm on each level of the
 * hierarchy. This is done recursively from the leafs to the root of the nodes in the graph, using
 * size information from lower levels in the levels above.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating yellow 2012-08-10 review KI-23 by cds, sgu
 * @author ars
 * @author msp
 */
public class RecursiveGraphLayoutEngine implements IGraphLayoutEngine {

    /**
     * Performs recursive layout on the given layout graph.
     * 
     * @param layoutGraph top-level node of the graph to be laid out
     * @param progressMonitor monitor to which progress of the layout algorithms is reported
     */
    public void layout(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor) {
        int nodeCount = countNodesRecursively(layoutGraph, true);
        progressMonitor.begin("Recursive Graph Layout", nodeCount);
        
        // perform recursive layout of the whole substructure of the given node
        layoutRecursively(layoutGraph, progressMonitor);
        
        progressMonitor.done();
    }

    /**
     * Recursive function to enable layout of hierarchy. The leafs are laid out first to use their
     * layout information in the levels above.
     * 
     * <p>This method returns self loops routed inside the given layout node. Those will have
     * coordinates relative to the node's top left corner, which is incorrect. Once the node's
     * final coordinates in its container are determined, any inside self loops will have to be offset
     * by the node's position.</p>
     * 
     * @param layoutNode the node with children to be laid out
     * @param progressMonitor monitor used to keep track of progress
     * @return list of self loops routed inside the node.
     */
    private List<KEdge> layoutRecursively(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        
        if (progressMonitor.isCanceled()) {
            return Collections.emptyList();
        }
        
        final KShapeLayout layoutNodeShapeLayout = layoutNode.getData(KShapeLayout.class);
        
        // Check if the node should be laid out at all
        if (layoutNodeShapeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            return Collections.emptyList();
        }
        
        // We have to process the node if it has children...
        final boolean hasChildren = !layoutNode.getChildren().isEmpty();
        
        // ...or if inside self loop processing is enabled and it actually has inside self loops
        final List<KEdge> insideSelfLoops = gatherInsideSelfLoops(layoutNode);
        final boolean hasInsideSelfLoops = !insideSelfLoops.isEmpty();
        
        if (hasChildren || hasInsideSelfLoops) {
            // this node has children and is thus a compound node;
            // fetch the layout algorithm that should be used to compute a layout for its content
            final LayoutAlgorithmData algorithmData = getAlgorithm(layoutNode);
            final boolean supportsHierarchy = algorithmData.supportsFeature(GraphFeature.COMPOUND);
            final boolean supportsClusters = algorithmData.supportsFeature(GraphFeature.CLUSTERS);
            final boolean supportsInsideSelfLoops = algorithmData.supportsFeature(
                    GraphFeature.INSIDE_SELF_LOOPS);
            
            // If the node contains inside self loops, but no regular children and if the layout
            // algorithm doesn't actually support inside self loops, we cancel
            if (!hasChildren && hasInsideSelfLoops && !supportsInsideSelfLoops) {
                return Collections.emptyList();
            }
            
            // We collect inside self loops of children and post-process them later
            List<KEdge> childrenInsideSelfLoops = Lists.newArrayList();
            
            // if the layout provider supports hierarchy, it is expected to layout the node's compound
            // node children as well
            int nodeCount;
            if (layoutNodeShapeLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)
                    && (supportsHierarchy || supportsClusters)) {
                
                // the layout algorithm will compute a layout for all levels of hierarchy under the
                // current one
                nodeCount = countNodesRecursively(layoutNode, false);
            } else {
                // layout each compound node contained in this node separately
                nodeCount = layoutNode.getChildren().size();
                for (KNode child : layoutNode.getChildren()) {
                    childrenInsideSelfLoops.addAll(layoutRecursively(child, progressMonitor));
                    
                    // apply the LayoutOptions.SCALE_FACTOR if present
                    KimlUtil.applyConfiguredNodeScaling(child);
                }
            }

            if (progressMonitor.isCanceled()) {
                return Collections.emptyList();
            }
            
            // Before running layout on our node, we need to exclude any inside self loops of children
            // from being laid out again
            for (final KEdge selfLoop : childrenInsideSelfLoops) {
                KEdgeLayout edgeLayout = selfLoop.getData(KEdgeLayout.class);
                edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            }

            // get an instance of the layout provider
            AbstractLayoutProvider layoutProvider = algorithmData.getInstancePool().fetch();
            try {
                // perform layout on the current hierarchy level
                layoutProvider.doLayout(layoutNode, progressMonitor.subTask(nodeCount));
                algorithmData.getInstancePool().release(layoutProvider);
            } catch (RuntimeException exception) {
                // the layout provider has failed - destroy it slowly and painfully
                layoutProvider.dispose();
                throw exception;
            }
            
            // Post-process the inner self loops we collected
            postProcessInsideSelfLoops(childrenInsideSelfLoops);
            
            // Return our own inside self loops to be processed later
            if (hasInsideSelfLoops && supportsInsideSelfLoops) {
                return insideSelfLoops;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Returns the most appropriate layout provider for the given node.
     * 
     * @param layoutNode node for which a layout provider is requested
     * @return a layout provider instance that fits the layout hints for the given node
     */
    private LayoutAlgorithmData getAlgorithm(final KNode layoutNode) {
        KShapeLayout nodeLayout = layoutNode.getData(KShapeLayout.class);
        String layoutHint = nodeLayout.getProperty(LayoutOptions.ALGORITHM);
        String diagramType = nodeLayout.getProperty(LayoutOptions.DIAGRAM_TYPE);
        LayoutAlgorithmData algorithmData = DefaultLayoutConfig.getLayouterData(
                layoutHint, diagramType);
        if (algorithmData == null) {
            throw new IllegalStateException("No registered layout algorithm is available.");
        }
        return algorithmData;
    }

    /**
     * Determines the total number of layout nodes in the given layout graph.
     * 
     * @param layoutNode parent layout node to examine
     * @param countAncestors if true, the nodes on the ancestors path are also counted
     * @return total number of child layout nodes
     */
    private int countNodesRecursively(final KNode layoutNode, final boolean countAncestors) {
        // count the content of the given node
        int count = layoutNode.getChildren().size();
        for (KNode childNode : layoutNode.getChildren()) {
            if (!childNode.getChildren().isEmpty()) {
                count += countNodesRecursively(childNode, false);
            }
        }
        // count the ancestors path
        if (countAncestors) {
            KNode parent = layoutNode.getParent();
            while (parent != null) {
                count += parent.getChildren().size();
                parent = parent.getParent();
            }
        }
        return count;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    // Inside Self Loops
    
    /**
     * Returns a list of self loops of the given node that should be routed inside that node instead
     * of around it. For the node to even be considered for inside self loop processing, its
     * {@link LayoutOptions#SELF_LOOP_INSIDE} property must be set to {@code true}. The returned
     * list will then consist of those of its outgoing edges that are self loops and that have that
     * property set to {@code true} as well.
     * 
     * @param node
     *            the node whose inside self loops to return.
     * @return possibly empty list of inside self loops.
     */
    private List<KEdge> gatherInsideSelfLoops(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        
        if (nodeLayout.getProperty(LayoutOptions.SELF_LOOP_INSIDE)) {
            List<KEdge> insideSelfLoops = Lists.newArrayListWithCapacity(node.getOutgoingEdges().size());
            
            for (KEdge edge : node.getOutgoingEdges()) {
                if (edge.getTarget() == node) {
                    final KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    if (edgeLayout.getProperty(LayoutOptions.SELF_LOOP_INSIDE)) {
                        insideSelfLoops.add(edge);
                    }
                }
            }
            
            return insideSelfLoops;
        } else {
            return Collections.emptyList();
        }
    }
    
    /**
     * Post-processes self loops routed inside by offsetting their coordinates by the coordinates of
     * their parent node. The post processing is necessary since the self loop coordinates are
     * relative to their parent node's upper left corner since, at that point, the parent node's
     * final coordinates are not determined yet.
     * 
     * @param insideSelfLoops
     *            list of inside self loops to post-process.
     */
    private void postProcessInsideSelfLoops(final List<KEdge> insideSelfLoops) {
        for (final KEdge selfLoop : insideSelfLoops) {
            final KNode node = selfLoop.getSource();
            final KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            final KEdgeLayout edgeLayout = selfLoop.getData(KEdgeLayout.class);
            
            final float xOffset = nodeLayout.getXpos();
            final float yOffset = nodeLayout.getYpos();
            
            // Offset the edge coordinates by the node's position
            applyOffset(edgeLayout.getSourcePoint(), xOffset, yOffset);
            applyOffset(edgeLayout.getTargetPoint(), xOffset, yOffset);
            
            for (final KPoint bend : edgeLayout.getBendPoints()) {
                applyOffset(bend, xOffset, yOffset);
            }
        }
    }
    
    /**
     * Offsets a point by the given amount.
     * 
     * @param point
     *            point to offset.
     * @param xOffset
     *            horizontal offset.
     * @param yOffset
     *            vertical offset.
     */
    private void applyOffset(final KPoint point, final float xOffset, final float yOffset) {
        point.setX(point.getX() + xOffset);
        point.setY(point.getY() + yOffset);
    }

}
