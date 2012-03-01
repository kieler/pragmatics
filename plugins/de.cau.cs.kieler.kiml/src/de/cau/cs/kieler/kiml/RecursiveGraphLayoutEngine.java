/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.GraphFeature;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Performs layout in a graph with hierarchy by executing a layout algorithm on
 * each level of the hierarchy. This is done recursively from the leafs to the
 * root of the nodes in the graph, using size information from lower levels in
 * the levels above. If an exception occurs, {@link #getLastLayoutProvider()}
 * can be used to get more information about the algorithm that caused the
 * exception.
 * 
 * @kieler.rating 2011-03-14 yellow
 *     reviewed by cmot, cds
 * @author ars
 * @author msp
 */
public class RecursiveGraphLayoutEngine implements IGraphLayoutEngine {

    /**
     * Performs recursive layout on the given layout graph. Layout is not only performed
     * for the selected node, but also for its ancestors, if there are any.
     * 
     * @param layoutGraph instance of a layout graph
     * @param progressMonitor monitor to which progress of the layout algorithms is reported
     */
    public void layout(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor) {
        int nodeCount = countNodes(layoutGraph, true);
        progressMonitor.begin("Recursive Graph Layout", nodeCount);
        
        // perform recursive layout of the whole substructure of the given node
        layoutRecursively(layoutGraph, progressMonitor);
        
        progressMonitor.done();
    }

    /**
     * Recursive function to enable layout of hierarchy. The leafs are laid out
     * first to use their layout information in the levels above.
     * 
     * @param layoutNode the node with children to be laid out
     * @param progressMonitor monitor used to keep track of progress
     */
    private void layoutRecursively(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        if (!layoutNode.getChildren().isEmpty()
                && !layoutNode.getData(KShapeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
            LayoutAlgorithmData algorithmData = getAlgorithm(layoutNode);
            AbstractLayoutProvider layoutProvider = algorithmData.getProviderPool().fetch();
            // if the layout provider supports hierarchy, it is expected to layout the children
            int nodeCount;
            if (layoutNode.getData(KShapeLayout.class).getProperty(LayoutOptions.LAYOUT_HIERARCHY)
                    && (algorithmData.getSupportedPriority(GraphFeature.COMPOUND)
                            > LayoutAlgorithmData.MIN_PRIORITY
                        || algorithmData.getSupportedPriority(GraphFeature.CLUSTERS)
                            > LayoutAlgorithmData.MIN_PRIORITY)) {
                nodeCount = countNodes(layoutNode, false);
            } else {
                nodeCount = layoutNode.getChildren().size();
                for (KNode child : layoutNode.getChildren()) {
                    layoutRecursively(child, progressMonitor);
                    if (progressMonitor.isCanceled()) {
                        return;
                    }
                }
            }

            // perform layout on the current hierarchy level
            layoutProvider.doLayout(layoutNode, progressMonitor.subTask(nodeCount));
            algorithmData.getProviderPool().release(layoutProvider);
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
    private int countNodes(final KNode layoutNode, final boolean countAncestors) {
        // count the content of the given node
        int count = layoutNode.getChildren().size();
        for (KNode childNode : layoutNode.getChildren()) {
            if (!childNode.getChildren().isEmpty()) {
                count += countNodes(childNode, false);
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

    /**
     * {@inheritDoc}
     */
    public boolean isActive() {
        return true;
    }

}
