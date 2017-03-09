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
package de.cau.cs.kieler.grana.analyses;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import com.google.common.collect.ImmutableSet;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;
import de.cau.cs.kieler.grana.analyses.LayersAnalysis.Layer;

/**
 * @author uru
 */
public class InlayerWhitespaceAnalysis implements IAnalysis {

    /**
     * Identifier of this analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.inlayerWhitespace";

    private LayersAnalysis layerAnalysis;
    
    private static final Set<Direction> LEFT_TO_RIGHT = ImmutableSet.of(Direction.UNDEFINED,
            Direction.LEFT, Direction.RIGHT);
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {

        // check that the precondition is met
        progressMonitor.begin("Inlayer whitespace analysis", 1);

        layerAnalysis = context.getAnalysisInstance(LayersAnalysis.class);

        // perform analysis
        float inLayerWhitespace = analyzeHierarchy(parentNode);
    
        progressMonitor.done();
        
        return inLayerWhitespace;
    }
    
    private float analyzeHierarchy(final ElkNode parent) {
        
        List<Layer> layers;
        final Direction direction = parent.getProperty(CoreOptions.DIRECTION);
        if (LEFT_TO_RIGHT.contains(direction)) {
            layers = layerAnalysis.getAllVerticalLayers().get(parent);
        } else {
            layers = layerAnalysis.getAllHorizontalLayers().get(parent);
        }

        float whitespace = 0f;
        
        // Determine whitespace between the nodes in every layer
        for (Layer l : layers) {
            List<ElkNode> nodes = l.nodes;
            if (nodes.size() <= 1) {
                continue;
            }
            // the returned list is not necessarily sorted, so sort it 
            // based on the nodes' positions in proper direction
            Collections.sort(nodes, new Comparator<ElkNode>() {
                public int compare(final ElkNode o1, final ElkNode o2) {
                    if (LEFT_TO_RIGHT.contains(direction)) {
                        return Double.compare(o1.getY(), o2.getY());
                    } else {
                        return Double.compare(o1.getX(), o2.getX());
                    }
                }
            });
            
            
            Iterator<ElkNode> it = nodes.iterator();
            float sumDiffs = 0; // sum of differences between consecutive node's positions
            float sumHeight = 0; // sum of node heights (despite the last node's height)
            ElkNode last = it.next();
            if (LEFT_TO_RIGHT.contains(direction)) {
                sumHeight += last.getHeight();
            } else {
                sumHeight += last.getWidth();
            }
            
            while (it.hasNext()) {
                ElkNode current = it.next();
                if (LEFT_TO_RIGHT.contains(direction)) {
                    sumDiffs += current.getY() - last.getY();
                    if (it.hasNext()) {
                        sumHeight += last.getHeight();
                    }
                } else {
                    sumDiffs += current.getX() - last.getX();
                    if (it.hasNext()) {
                        sumHeight += last.getWidth();
                    }
                }   
                
                last = current;
            }
            
            whitespace += sumDiffs - sumHeight;
        }
        
        // recurse children
        for (ElkNode child : parent.getChildren()) {
            if (!child.getChildren().isEmpty()) {
                whitespace += analyzeHierarchy(child);
            }
        }
        
        return whitespace;
    }

}
