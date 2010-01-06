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
package de.cau.cs.kieler.kiml.layout;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * Performs layout in a graph with hierarchy by executing a layout provider on
 * each level of the hierarchy. This is done recursively from the leafs to the
 * root of the nodes in the graph, using size information from lower levels in
 * the levels above.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author ars
 */
public class RecursiveLayouterEngine {

    /** the last used layout provider. */
    private AbstractLayoutProvider lastLayoutProvider;

    /**
     * Performs recursive layout on the given layout graph.
     * 
     * @param layoutGraph instance of a layout graph
     * @param progressMonitor monitor to which progress of the layout algorithms
     *            is reported
     * @throws KielerException if a layout algorithm fails
     */
    public void layout(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        lastLayoutProvider = null;
        int nodeCount = countNodes(layoutGraph);
        progressMonitor.begin("Recursive graph layout", nodeCount);
        layoutRecursively(layoutGraph, progressMonitor);
        progressMonitor.done();
    }

    /**
     * Recursive function to enable layout of hierarchy. The leafs are laid out
     * first to use their layout information in the levels above.
     * 
     * @param layoutNode the node with children to be laid out
     * @param progressMonitor monitor used to keep track of progress
     * @throws KielerException if one of the layout providers fails
     */
    private void layoutRecursively(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        if (!layoutNode.getChildren().isEmpty()) {
            AbstractLayoutProvider layoutProvider = LayoutServices.getInstance()
                    .getLayoutProvider(layoutNode);
            // if the layout provider supports hierarchy, it is expected to layout the children
            if (!layoutProvider.supportsHierarchy(layoutNode)) {
                for (KNode child : layoutNode.getChildren()) {
                    layoutRecursively(child, progressMonitor);
                }
            }

            // perform layout on the current hierarchy level
            lastLayoutProvider = layoutProvider;
            layoutProvider.doLayout(layoutNode, progressMonitor.subTask(
                    layoutNode.getChildren().size()));

            // check the new size of the parent node
            KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
            float minWidth = LayoutOptions.getMinWidth(parentLayout);
            if (parentLayout.getWidth() < minWidth) {
                parentLayout.setWidth(minWidth);
            }
            float minHeight = LayoutOptions.getMinHeight(parentLayout);
            if (parentLayout.getHeight() < minHeight) {
                parentLayout.setHeight(minHeight);
            }
            LayoutOptions.setFixedSize(parentLayout, true);
        }
    }

    /**
     * Determines the total number of layout nodes in the given layout graph.
     * 
     * @param layoutNode parent layout node to examine
     * @return total number of child layout nodes
     */
    private int countNodes(final KNode layoutNode) {
        int count = layoutNode.getChildren().size();
        for (KNode childNode : layoutNode.getChildren()) {
            if (!childNode.getChildren().isEmpty()) {
                count += countNodes(childNode);
            }
        }
        return count;
    }

    /**
     * Returns the last layout provider that was used by the layouter engine.
     * This can be used to check the source of error if an exception is caught
     * during layout.
     * 
     * @return the last used layout provider, or {@code null} if there is none
     */
    public AbstractLayoutProvider getLastLayoutProvider() {
        return lastLayoutProvider;
    }

}
