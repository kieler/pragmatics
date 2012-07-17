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
package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * An analysis for the number of horizontal and vertical layers. Returns a pair
 * of integers.
 * 
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class LayersAnalysis implements IAnalysis {

    /** a utility class to mark the start and end position of a layer. */
    private static final class Layer {
        private float start;
        private float end;

        private Layer(final float thestart, final float theend) {
            this.start = thestart;
            this.end = theend;
        }
    }

    /**
     * Insert a new segment into the given list of layers.
     * 
     * @param layers
     *            a list of layers
     * @param start
     *            the start position of the new segment
     * @param end
     *            the end position of the new segment
     */
    private static void insert(final List<Layer> layers, final float start,
            final float end) {
        Layer insertLayer = null;
        Iterator<Layer> layerIter = layers.iterator();
        while (layerIter.hasNext()) {
            Layer currentLayer = layerIter.next();
            if (start <= currentLayer.end && end >= currentLayer.start) {
                if (insertLayer == null) {
                    insertLayer = currentLayer;
                    insertLayer.start = Math.min(insertLayer.start, start);
                    insertLayer.end = Math.max(insertLayer.end, end);
                } else {
                    layerIter.remove();
                    insertLayer.start =
                            Math.min(insertLayer.start, currentLayer.start);
                    insertLayer.end =
                            Math.max(insertLayer.end, currentLayer.end);
                }
            }
        }
        if (insertLayer == null) {
            Layer newLayer = new Layer(start, end);
            layers.add(newLayer);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Layers Analysis", 1);

        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        int[] count = countLayers(parentNode, hierarchy);

        progressMonitor.done();
        return new Object[] { count[0], count[1] };
    }
    
    /**
     * Count the number of layers in the given graph and its nested subgraphs.
     * 
     * @param parentNode the parent node
     * @param hierarchy whether to process hierarchy recursively
     * @return the number of horizontal / vertical layers, respectively
     */
    public int[] countLayers(final KNode parentNode, final boolean hierarchy) {
        // analyze horizontal layers
        List<Layer> horizontalLayers = new LinkedList<Layer>();
        for (KNode node : parentNode.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            float start = nodeLayout.getYpos();
            float end = start + nodeLayout.getHeight();
            insert(horizontalLayers, start, end);
        }

        // analyze vertical layers
        List<Layer> verticalLayers = new LinkedList<Layer>();
        for (KNode node : parentNode.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            float start = nodeLayout.getXpos();
            float end = start + nodeLayout.getWidth();
            insert(verticalLayers, start, end);
        }
        
        // count the number of layers in the nested subgraphs
        int[] count = new int[] { horizontalLayers.size(), verticalLayers.size() };
        if (hierarchy) {
            for (KNode child : parentNode.getChildren()) {
                if (!child.getChildren().isEmpty()) {
                    int[] childResult = countLayers(child, true);
                    count[0] += childResult[0];
                    count[1] += childResult[1];
                }
            }
        }
        return count;
    }

}
