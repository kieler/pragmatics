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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Counts orthogonal crossings. This is done by:
 * <ol>
 *   <li>collecting all vertical and horizontal segments of each edge once</li>
 *   <li>merging all overlapping segments to one</li>
 *   <li>counting intersections while ignoring those with a junction point on them</li>
 * </ol>
 * 
 * @author alan
 */
public class OrthogonalCrossingsAnalysis implements IAnalysis {

    /**
     * Identifier of the orthogonal crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.orthogonalCrossings";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Orthogonal crossings analysis", 1);

        int crossings = countCrossings(parentNode);

        progressMonitor.done();
        return new Object[] { crossings };
    }

    private int countCrossings(final KNode parentNode) {
        List<KNode> nodes = Lists.newArrayList(parentNode);

        boolean shouldAnalyzeHierarchy =
                parentNode.getData(KShapeLayout.class).getProperty(
                        AnalysisOptions.ANALYZE_HIERARCHY);

        int crossings = 0;
        while (!nodes.isEmpty()) {
            KNode node = nodes.remove(0);
            crossings += new SingleHierarchyLevelOrthogonalCrossCounter(node).count();
            if (shouldAnalyzeHierarchy) {
                nodes.addAll(node.getChildren());
            }
        }
        return crossings;
    }

    /**
     * Given parent node counts crossings in all children.
     * 
     * @author alan
     */
    private static class SingleHierarchyLevelOrthogonalCrossCounter {
        private static final double EPSILON = 1e-4;
        private final Set<KVector> junctionPoints;
        private final List<Line2D> segments;
        private final KNode parentNode;

        public SingleHierarchyLevelOrthogonalCrossCounter(final KNode n) {
            parentNode = n;
            List<KNode> nodes = new ArrayList<KNode>(parentNode.getChildren());
            segments = Lists.newArrayList();
            junctionPoints = Sets.newHashSet();

            for (KNode node : nodes) {
                collectSegmentsOfEdgesConnectedTo(node);
                addJunctionPointsOnEdgesConnectedTo(node);
            }

            mergeOverlappingSegments();
        }

        private void collectSegmentsOfEdgesConnectedTo(final KNode node) {
            // Visit all incoming edges ignoring those in other hierarchy level
            for (KEdge edge : node.getIncomingEdges()) {
                if (notFromChildOf(edge, node)) {
                    addSegmentsOf(edge);
                }
            }
            // All edges between nodes inside of the parent node have been visited, except for those
            // going to ports of the parent node.
            for (KEdge edge : node.getOutgoingEdges()) {
                if (edgeGoesToParentOutPort(edge)) {
                    addSegmentsOf(edge);
                }
            }
        }

        private boolean edgeGoesToParentOutPort(final KEdge e) {
            return e.getTarget() == parentNode;
        }

        private boolean notFromChildOf(final KEdge e, final KNode n) {
            return !n.getChildren().contains(e.getSource());
        }

        private void addSegmentsOf(final KEdge edge) {
            KVectorChain chain = edge.getData(KEdgeLayout.class).createVectorChain();
            Iterator<KVector> iterator = chain.iterator();
            KVector start = iterator.next();
            while (iterator.hasNext()) {
                KVector end = iterator.next();
                Line2D line = new Line2D.Double(start.x, start.y, end.x, end.y);
                segments.add(line);
                start = end;
            }
        }

        private void addJunctionPointsOnEdgesConnectedTo(final KNode node) {
            for (KEdge edge : node.getIncomingEdges()) {
                junctionPoints.addAll(edge.getData(KEdgeLayout.class).getProperty(
                        LayoutOptions.JUNCTION_POINTS));
            }
            for (KEdge edge : node.getOutgoingEdges()) {
                junctionPoints.addAll(edge.getData(KEdgeLayout.class).getProperty(
                        LayoutOptions.JUNCTION_POINTS));
            }
        }

        private void mergeOverlappingSegments() {
            // Repeatedly iterate over all segments until all overlapping segments have been merged.
            // This is necessary because the order in segments can be arbitrary, so we might miss
            // a possible merge.
            boolean hasMerged;
            do {
                hasMerged = false;
                for (int i = 0; i < segments.size(); i++) {
                    for (int j = i + 1; j < segments.size(); j++) {
                        Line2D lineOne = segments.get(i);
                        Line2D lineTwo = segments.get(j);
                        if (canBeMerged(lineOne, lineTwo)) {
                            merge(lineOne, lineTwo);
                            // Concurrent modification on purpose, size() is evaluated on each
                            // iteration
                            segments.remove(j);
                            hasMerged = true;
                        }
                    }
                }
            } while (hasMerged);
        }

        private void merge(final Line2D lineOne, final Line2D lineTwo) {
            double x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            if (isHorizontal(lineOne)) {
                Double[] xCoords =
                        new Double[] { lineOne.getX1(), lineOne.getX2(), lineTwo.getX1(),
                                lineTwo.getX2() };
                Arrays.sort(xCoords);
                x1 = xCoords[0];
                x2 = xCoords[xCoords.length - 1];
                y1 = lineOne.getY1();
                y2 = lineOne.getY1();
            } else {
                Double[] yCoords =
                        new Double[] { lineOne.getY1(), lineOne.getY2(), lineTwo.getY1(),
                                lineTwo.getY2() };
                Arrays.sort(yCoords);
                y1 = yCoords[0];
                y2 = yCoords[yCoords.length - 1];
                x1 = lineOne.getX1();
                x2 = lineOne.getX1();
            }
            lineOne.setLine(x1, y1, x2, y2);
        }

        public int count() {
            int crossings = 0;
            for (int i = 0; i < segments.size(); i++) {
                Line2D firstLine = segments.get(i);
                for (int j = i + 1; j < segments.size(); j++) {
                    Line2D secondLine = segments.get(j);
                    if (crossingIsVisible(firstLine, secondLine)) {
                        crossings++;
                    }
                }
            }
            return crossings;
        }

        private boolean crossingIsVisible(final Line2D firstLine, final Line2D secondLine) {
            return areNotParallel(firstLine, secondLine)
                    && doNotOnlyTouchAtEnds(firstLine, secondLine)
                    && intersect(firstLine, secondLine)
                    && intersectionHasNoJunctionPoint(firstLine, secondLine);
        }

        private boolean areNotParallel(final Line2D firstLine, final Line2D secondLine) {
            return isVertical(firstLine) ^ isVertical(secondLine);
        }

        private boolean doNotOnlyTouchAtEnds(final Line2D firstLine, final Line2D secondLine) {
            Line2D verticalLine = isVertical(firstLine) ? firstLine : secondLine;
            Line2D horizontalLine = isVertical(firstLine) ? secondLine : firstLine;

            return !doubleEquals(horizontalLine.getX1(), verticalLine.getX1())
                    && !doubleEquals(horizontalLine.getX2(), verticalLine.getX1())
                    && !doubleEquals(horizontalLine.getY1(), verticalLine.getY1())
                    && !doubleEquals(horizontalLine.getY1(), verticalLine.getY2());
        }

        private boolean intersect(final Line2D firstLine, final Line2D secondLine) {
            Line2D verticalLine = isVertical(firstLine) ? firstLine : secondLine;
            Line2D horizontalLine = isVertical(firstLine) ? secondLine : firstLine;
            double xValue = verticalLine.getX1();
            double yValue = horizontalLine.getY1();

            boolean xIntersects =
                    Math.min(horizontalLine.getX1(), horizontalLine.getX2()) < xValue
                            && Math.max(horizontalLine.getX1(), horizontalLine.getX2()) > xValue;

            boolean yInstersects =
                    Math.min(verticalLine.getY1(), verticalLine.getY2()) < yValue
                            && Math.max(verticalLine.getY1(), verticalLine.getY2()) > yValue;

            return xIntersects && yInstersects;

        }

        private boolean intersectionHasNoJunctionPoint(final Line2D firstLine,
                final Line2D secondLine) {
            KVector intersection = intersectionOf(firstLine, secondLine);
            // TODO Potential Bug: KVector hashSet and equals use == on doubles. Seems to work
            // up to now, though.
            return !junctionPoints.contains(intersection);
        }

        private KVector intersectionOf(final Line2D firstLine, final Line2D secondLine) {
            Line2D verticalLine = isVertical(firstLine) ? firstLine : secondLine;
            Line2D horizontalLine = isHorizontal(firstLine) ? firstLine : secondLine;
            return new KVector(verticalLine.getX1(), horizontalLine.getY1());
        }

        private boolean isVertical(final Line2D line) {
            return doubleEquals(line.getX1(), line.getX2());
        }

        private boolean isHorizontal(final Line2D line) {
            return doubleEquals(line.getY1(), line.getY2());
        }

        private boolean doubleEquals(final double y1, final double y2) {
            return Math.abs(y1 - y2) < EPSILON;
        }

        private boolean canBeMerged(final Line2D line1, final Line2D line2) {
            return line1.intersectsLine(line2) && !areNotParallel(line1, line2);
        }

    }

}
