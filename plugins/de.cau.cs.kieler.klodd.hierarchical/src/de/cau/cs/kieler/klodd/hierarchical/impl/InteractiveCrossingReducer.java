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
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.Collections;
import java.util.Comparator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.modules.ICrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;

/**
 *
 * @author msp
 */
public class InteractiveCrossingReducer extends AbstractAlgorithm implements ICrossingReducer {

    /**
     * {@inheritDoc}
     */
    public void reduceCrossings(final LayeredGraph layeredGraph) {
        getMonitor().begin("Interactive crossing reduction", 1);

        final LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        for (Layer layer : layeredGraph.getLayers()) {
            // calculate lengthwise layer position
            float lengthSum = 0.0f;
            int count = 0;
            for (LayerElement element : layer.getElements()) {
                if (element.getKNode() != null) {
                    count++;
                    if (layoutDirection == LayoutDirection.DOWN) {
                        lengthSum += element.getKNode().getYpos();
                    } else {
                        lengthSum += element.getKNode().getXpos();
                    }
                }
            }
            final float lengthPos = lengthSum / count;
            
            // sort all elements by their relative positions
            Collections.sort(layer.getElements(), new Comparator<LayerElement>() {
                public int compare(final LayerElement elem1, final LayerElement elem2) {
                    return Float.compare(getCompareValue(elem1, layoutDirection, lengthPos),
                            getCompareValue(elem2, layoutDirection, lengthPos));
                }
            });

            // calculate concrete rank values
            layer.calcElemRanks();
        }
        
        getMonitor().done();
    }
    
    private float getCompareValue(final LayerElement element,
            final LayoutDirection layoutDirection, final float lengthPos) {
        if (element.getKNode() != null) {
            // process a node or external port
            if (layoutDirection == LayoutDirection.DOWN) {
                return element.getKNode().getXpos();
            } else {
                return element.getKNode().getYpos();
            }
        } else {
            // process an edge
            KEdge edge = (KEdge) element.getElemObj();
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
            if (layoutDirection == LayoutDirection.DOWN) {
                float currVal = edgeLayout.getSourcePoint().getX();
                for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                    if (bendPoint.getY() > lengthPos) {
                        break;
                    }
                    currVal = bendPoint.getX();
                }
                return currVal;
            } else {
                float currVal = edgeLayout.getSourcePoint().getY();
                for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                    if (bendPoint.getX() > lengthPos) {
                        break;
                    }
                    currVal = bendPoint.getY();
                }
                return currVal;
            }
        }
    }

}
