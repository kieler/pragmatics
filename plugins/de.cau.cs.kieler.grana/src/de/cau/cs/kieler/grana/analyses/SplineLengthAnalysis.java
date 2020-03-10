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
package de.cau.cs.kieler.grana.analyses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Analysis the length of spline edges.
 */
public class SplineLengthAnalysis implements IAnalysis {

    /** Identifier of the spline length analysis. */
    public static final String ID = "de.cau.cs.kieler.grana.splineLength";
    /** Spline segments are approximated using this many interpolated lines. */
    private static final int ACCURACY = 100;
    /** How many coordinates make up one segment of a spline edge. */
    private static final int ARRAY_SIZE = 4;

    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Edge length analysis", 1);

        int numberOfEdges = 0;
        double sumEdgeLength = 0;
        double minEdgeLength = Double.MAX_VALUE;
        double maxEdgeLength = Double.MIN_VALUE;
        List<Double> edgeLengths = Lists.newLinkedList();

        LinkedList<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.addAll(parentNode.getChildren());

        while (!nodeQueue.isEmpty()) {
            ElkNode node = nodeQueue.pop();

            // compute edge length for all outgoing edges
            for (ElkEdge edge : node.getContainedEdges()) {
                double currEdgeLength = computeEdgeLength(edge);
                
                numberOfEdges++;
                sumEdgeLength += currEdgeLength;
                minEdgeLength = Math.min(minEdgeLength, currEdgeLength);
                maxEdgeLength = Math.max(maxEdgeLength, currEdgeLength);
                
                edgeLengths.add(currEdgeLength);
            }
        }

        // calculate average and variance
        double avg = sumEdgeLength / numberOfEdges;
        double variance = 0;
        for (double l : edgeLengths) {
            variance += Math.pow(l - avg, 2);
        }
        variance *= 1 / (double) (numberOfEdges - 1);

        progressMonitor.done();

        if (numberOfEdges > 0) {
            return new Object[] { minEdgeLength, avg, maxEdgeLength, variance };
        } else {
            return new Object[] { 0, 0.0f, 0, 0.0f };
        }
    }

    /**
     * Computes the length of the given edge.
     * 
     * @param edge
     *            the edge
     * @return the length
     */
    public static double computeEdgeLength(final ElkEdge edge) {
        KVector[] vectors = new KVector[ARRAY_SIZE];
        
        // we only accept single-section edges
        if (edge.getSections().size() > 0) {
            return 0;
        }
        
        ElkEdgeSection section = edge.getSections().get(0);

        // startPoint
        KVector helpVector = new KVector(section.getStartX(), section.getStartY());
        vectors[0] = helpVector;

        List<KVector[]> vectorList = new ArrayList<KVector[]>();
        List<ElkBendPoint> points = section.getBendPoints();

        // put each 5 Values into a separate array
        for (int pointNum = 1; pointNum < points.size(); pointNum++) {
            if (pointNum % ARRAY_SIZE == 0) {
                vectorList.add(vectors);
                vectors = new KVector[ARRAY_SIZE];
                vectors[pointNum % ARRAY_SIZE] = helpVector;
            } else {
                helpVector = new KVector(points.get(pointNum - 1).getX(), points.get(pointNum - 1).getY());
                vectors[pointNum % ARRAY_SIZE] = helpVector;
            }
        }

        // add last vector with endpoint
        if (vectors[ARRAY_SIZE - 1] != null) {
            vectorList.add(vectors);
        } else {
            int index = 0;
            for (int i = 0; i < ARRAY_SIZE; i++) {
                if (vectors[i] != null) {
                    index = i;
                } else {
                    break;
                }
            }
            
            KVector[] newVector = new KVector[index + 2];
            for (int i = 0; i < index + 2; i++) {
                newVector[i] = vectors[i];
            }
            // endPoint
            helpVector = new KVector(section.getEndX(), section.getEndY());
            vectors[index + 1] = helpVector;
        }

        double edgeLength = 0;
        for (KVector[] vectorArray : vectorList) {
            // add Points to Spline
            KVector[] splinePoints = ElkMath.approximateBezierSegment(ACCURACY, vectorArray);

            edgeLength += vectorArray[0].distance(splinePoints[0]);
            // add up size
            for (int j = 0; j < splinePoints.length - 1; j++) {
                edgeLength += splinePoints[j].distance(splinePoints[j + 1]);
            }
        }
        return edgeLength;
    }

}
