/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import net.ogdf.lib.Ogdf;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The upward-planarization layout algorithm from the OGDF library.
 * 
 * @author mri
 */
public class UpwardPlanarizationLayouter extends OgdfLayouter {

    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15;
    /** default value for minimum spacing. */
    private static final float DEF_MIN_SPACING = 16.0f;
    /** default value for label edge distance. */
    private static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    private static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;
    
    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();
    
    /**
     * Returns whether the graph defined by the given parent node is connected.
     * 
     * @param node
     *            the parent node of the graph
     * @return whether the graph is connected
     */
    private boolean isConnected(final KNode node) {
        // empty graphs are connected
        if (node.getChildren().size() == 0) {
            return true;
        }
        // mark all nodes connected to a random node
        Queue<KNode> checkNodes = new LinkedList<KNode>();
        Set<KNode> marker = new HashSet<KNode>();
        checkNodes.addAll(node.getChildren());
        Queue<KNode> nodes = new LinkedList<KNode>();
        nodes.add(checkNodes.remove());
        while (!nodes.isEmpty()) {
            KNode currentNode = nodes.remove();
            if (!marker.contains(currentNode)) {
                marker.add(currentNode);
                for (KEdge edge : currentNode.getOutgoingEdges()) {
                    nodes.add(edge.getTarget());
                }
                for (KEdge edge : currentNode.getIncomingEdges()) {
                    nodes.add(edge.getSource());
                }
            }
        }
        // if there is still a not marked node the graph is not connected 
        for (KNode currentNode : checkNodes) {
            if (!marker.contains(currentNode)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        
        // the layouter crashes on not connected graphs
        if (!isConnected(layoutNode)) {
            throw new RuntimeException("Layouter does not support not-connected graphs.");
        }
        
        KShapeLayout parentLayout = KimlUtil.getShapeLayout(layoutNode);
        
        // get the minimum spacing and layer distance
        float minSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (minSpacing < 0) {
            minSpacing = DEF_MIN_SPACING;
        }
        
        Ogdf.createUpwardPlanarizationLayouter(minSpacing, minSpacing);
        
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.exclude();
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(LayoutOptions.OBJ_SPACING_ID)) {
            return DEF_MIN_SPACING;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING_ID)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(LABEL_EDGE_DIST_ID)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(LABEL_MARGIN_DIST_ID)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
}
