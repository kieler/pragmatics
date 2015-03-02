/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.LayersAnalysis.Layer;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * @author uru
 */
public class InlayerWhitespaceAnalysis implements IAnalysis {

    /**
     * Identifier of this analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.inlayerWhitespace";

    private LayersAnalysis layerAnalysis;
    
    private static final Set<Direction> LEFT_TO_RIGHT = ImmutableSet.of(Direction.UNDEFINED,
            Direction.LEFT, Direction.RIGHT);
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {

        // check that the precondition is met
        progressMonitor.begin("Inlayer whitespace analysis", 1);

        layerAnalysis = context.getAnalysisInstance(LayersAnalysis.class);

        // perform analysis
        float inLayerWhitespace = analyzeHierarchy(parentNode);
    
        progressMonitor.done();
        
        return inLayerWhitespace;
    }
    
    private float analyzeHierarchy(final KNode parent) {
        
        List<Layer> layers;
        KLayoutData ld = parent.getData(KLayoutData.class);
        final Direction direction = ld.getProperty(LayoutOptions.DIRECTION);
        if (LEFT_TO_RIGHT.contains(direction)) {
            layers = layerAnalysis.getAllVerticalLayers().get(parent);
        } else {
            layers = layerAnalysis.getAllHorizontalLayers().get(parent);
        }

        float whitespace = 0f;
        
        // Determine whitespace between the nodes in every layer
        for (Layer l : layers) {
            List<KNode> nodes = l.nodes;
            if (nodes.size() <= 1) {
                continue;
            }
            // the returned list is not necessarily sorted, so sort it 
            // based on the nodes' positions in proper direction
            Collections.sort(nodes, new Comparator<KNode>() {
                public int compare(final KNode o1, final KNode o2) {
                    KShapeLayout l1 = o1.getData(KShapeLayout.class);
                    KShapeLayout l2 = o2.getData(KShapeLayout.class);
                    if (LEFT_TO_RIGHT.contains(direction)) {
                        return Float.compare(l1.getYpos(), l2.getYpos());
                    } else {
                        return Float.compare(l1.getXpos(), l2.getXpos());
                    }
                }
            });
            
            
            Iterator<KNode> it = nodes.iterator();
            float sumDiffs = 0; // sum of differences between consecutive node's positions
            float sumHeight = 0; // sum of node heights (despite the last node's height)
            KNode last = it.next();
            KShapeLayout lastSl = last.getData(KShapeLayout.class);
            if (LEFT_TO_RIGHT.contains(direction)) {
                sumHeight += lastSl.getHeight();
            } else {
                sumHeight += lastSl.getWidth();
            }
            
            while (it.hasNext()) {
                KNode current = it.next();
                KShapeLayout currentSl = current.getData(KShapeLayout.class);
                if (LEFT_TO_RIGHT.contains(direction)) {
                    sumDiffs += currentSl.getYpos() - lastSl.getYpos();
                    if (it.hasNext()) {
                        sumHeight += lastSl.getHeight();
                    }
                } else {
                    sumDiffs += currentSl.getXpos() - lastSl.getXpos();
                    if (it.hasNext()) {
                        sumHeight += lastSl.getWidth();
                    }
                }   
                
                last = current;
                lastSl = currentSl;
            }
            
            whitespace += sumDiffs - sumHeight;
        }
        
        // recurse children
        for (KNode child : parent.getChildren()) {
            if (!child.getChildren().isEmpty()) {
                whitespace += analyzeHierarchy(child);
            }
        }
        
        return whitespace;
    }

}
