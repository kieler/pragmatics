/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * Metric for node separation. Checks whether the distance of each pair of nodes exceeds a
 * certain minimal value.
 *
 * @author msp
 */
public class NodeSeparationMetric implements IAnalysis {
    
    /** the minimal distance between nodes. */
    private static final float MIN_DISTANCE = 7;
    /** the separation for nodes that touch each other. */
    private static final float TOUCH_SEP = 0.4f;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Node separation metric analysis", 1);

        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);

        float result = nodeSeparation(parentNode, hierarchy);
        
        assert result >= 0 && result <= 1;
        progressMonitor.done();
        return result;
    }
    
    /**
     * Calculate the separation as normalized metric between 0 and 1.
     * 
     * @param parentNode the parent node
     * @param hierarchy whether the full hierarchy shall be considered
     * @return the separation metric
     */
    private float nodeSeparation(final KNode parentNode, final boolean hierarchy) {
        if (parentNode.getChildren().size() <= 1) {
            return 1;
        }
        int nodeCount = parentNode.getChildren().size();

        // calculate the distance between each pair of nodes
        float[][] distanceMatrix = new float[nodeCount][nodeCount];
        ListIterator<KNode> nodeIter1 = parentNode.getChildren().listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            KShapeLayout nodeLayout1 = node1.getData(KShapeLayout.class);
            ListIterator<KNode> nodeIter2 = parentNode.getChildren().listIterator(
                    nodeIter1.nextIndex());
            while (nodeIter2.hasNext()) {
                KNode node2 = nodeIter2.next();
                KShapeLayout nodeLayout2 = node2.getData(KShapeLayout.class);
                float distance = normalizedDistance(nodeLayout1, nodeLayout2);
                distanceMatrix[nodeIter1.previousIndex()][nodeIter2.previousIndex()] = distance;
            }
        }

        float separation = 0;
        int childNodeCount = 0;
        nodeIter1 = parentNode.getChildren().listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            
            // determine the minimal distance to any other node
            float minDist = MIN_DISTANCE + 1;
            int i = nodeIter1.previousIndex();
            for (int j = 0; j < nodeCount; j++) {
                if (j > i) {
                    minDist = Math.min(minDist, distanceMatrix[i][j]);
                } else if (j < i) {
                    minDist = Math.min(minDist, distanceMatrix[j][i]);
                }
            }
            
            // calculate the separation for node1
            if (minDist > 1) {
                separation += TOUCH_SEP + (minDist - 1) / MIN_DISTANCE * (1 - TOUCH_SEP);
            } else {
                separation += minDist * TOUCH_SEP;
            }
            
            if (hierarchy) {
                int n = node1.getChildren().size();
                if (n > 0) {
                    separation += n * nodeSeparation(node1, true);
                    childNodeCount += n;
                }
            }
        }
        
        return separation / (nodeCount + childNodeCount);
    }
    
    /**
     * Calculate the normalized distance between the two nodes.
     * 
     * @param nodeLayout1 layout of the first node
     * @param nodeLayout2 layout of the second node
     * @return normalized distance
     */
    private float normalizedDistance(final KShapeLayout nodeLayout1, final KShapeLayout nodeLayout2) {
        float left1 = nodeLayout1.getXpos();
        float top1 = nodeLayout1.getYpos();
        float right1 = left1 + nodeLayout1.getWidth();
        float bottom1 = top1 + nodeLayout1.getHeight();
        float left2 = nodeLayout2.getXpos();
        float top2 = nodeLayout2.getYpos();
        float right2 = left2 + nodeLayout2.getWidth();
        float bottom2 = top2 + nodeLayout2.getHeight();
        
        if (left2 > right1) {
            if (top2 > bottom1) {
                return 1 + (left2 - right1) + (top2 - bottom1); 
            } else if (bottom2 < top1) {
                return 1 + (left2 - right1) + (top1 - bottom2);
            } else {
                return 1 + left2 - right1;
            }
        } else if (right2 < left1) {
            if (top2 > bottom1) {
                return 1 + (left1 - right2) + (top2 - bottom1); 
            } else if (bottom2 < top1) {
                return 1 + (left1 - right2) + (top1 - bottom2);
            } else {
                return 1 + left1 - right2;
            }
        } else {
            if (top2 > bottom1) {
                return 1 + top2 - bottom1; 
            } else if (bottom2 < top1) {
                return 1 + top1 - bottom2;
            } else {
                // the nodes overlap!
                float hwidth1 = nodeLayout1.getWidth() / 2;
                float hheight1 = nodeLayout1.getHeight() / 2;
                float hwidth2 = nodeLayout2.getWidth() / 2;
                float hheight2 = nodeLayout2.getHeight() / 2;
                
                float distance = Math.abs((left2 + hwidth2) - (left1 + hwidth1))
                        + Math.abs((top2 + hheight2) - (top1 + hheight1));
                return distance / (hwidth1 + hwidth2 + hheight1 + hheight2);
            }
        }
    }

}
