/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.util;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A layout provider that sets fixed positions for all elements. Elements that have no position
 * option attached just stay where they are.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class FixedLayoutProvider extends AbstractLayoutProvider {

    /** the layout provider id. */
    public static final String ID = "de.cau.cs.kieler.fixed";
    
    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15.0f;
    
    /**
     * Initialize the default values of the fixed layout provider.
     */
    public FixedLayoutProvider() {
        setProperty(LayoutOptions.BORDER_SPACING, DEF_BORDER_SPACING);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Null Layout", 1);
        float maxx = 0, maxy = 0;
        
        for (KNode node : layoutNode.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            // set the fixed position of the node, or leave it as it is
            KVector pos = nodeLayout.getProperty(LayoutOptions.POSITION);
            if (pos == null) {
                nodeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            } else {
                nodeLayout.setXpos((float) pos.x);
                nodeLayout.setYpos((float) pos.y);
                // set the fixed size of the node
                if (!nodeLayout.getProperty(LayoutOptions.FIXED_SIZE)) {
                    float width = nodeLayout.getProperty(LayoutOptions.MIN_WIDTH);
                    if (width > 0) {
                        nodeLayout.setWidth(width);
                    }
                    float height = nodeLayout.getProperty(LayoutOptions.MIN_HEIGHT);
                    if (height > 0) {
                        nodeLayout.setHeight(height);
                    }
                }
            }
            maxx = Math.max(maxx, nodeLayout.getXpos() + nodeLayout.getWidth());
            maxy = Math.max(maxy, nodeLayout.getYpos() + nodeLayout.getHeight());
            
            // set the fixed position of the ports, or leave them as they are
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                pos = portLayout.getProperty(LayoutOptions.POSITION);
                if (pos == null) {
                    portLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                } else {
                    portLayout.setXpos((float) pos.x);
                    portLayout.setYpos((float) pos.y);
                }
            }
            
            // set fixed routing for the connected edges, or leave them as they are
            for (KEdge edge : node.getOutgoingEdges()) {
                processEdge(edge);
            }
            for (KEdge edge : node.getIncomingEdges()) {
                if (edge.getSource().getParent() != layoutNode) {
                    processEdge(edge);
                }
            }
        }
        
        // set size of the parent node
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        float borderSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEF_BORDER_SPACING;
        }
        KInsets insets = parentLayout.getProperty(LayoutOptions.INSETS);
        parentLayout.setWidth(maxx + borderSpacing + insets.getLeft() + insets.getRight());
        parentLayout.setHeight(maxy + borderSpacing + insets.getTop() + insets.getBottom());
        
        progressMonitor.done();
    }
    
    /**
     * Process an edge and its labels.
     * 
     * @param edge an edge
     */
    private void processEdge(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KVectorChain bendPoints = edgeLayout.getProperty(LayoutOptions.BEND_POINTS);
        if (bendPoints == null || bendPoints.size() < 2) {
            edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
        } else {
            KimlUtil.applyVectorChain(edgeLayout, bendPoints);
        }
        
        for (KLabel label : edge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            KVector pos = labelLayout.getProperty(LayoutOptions.POSITION);
            if (pos == null) {
                labelLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            } else {
                labelLayout.setXpos((float) pos.x);
                labelLayout.setYpos((float) pos.y);
            }
        }
    }

}
