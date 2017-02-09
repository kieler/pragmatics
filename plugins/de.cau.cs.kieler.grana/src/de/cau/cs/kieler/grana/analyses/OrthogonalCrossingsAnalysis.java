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

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.AnalysisFailed.Type;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Counts orthogonal crossings. This is done by:
 * <ol>
 * <li>collecting all vertical and horizontal segments of each edge once</li>
 * <li>merging all overlapping segments to one</li>
 * <li>counting intersections while ignoring those with a junction point on them</li>
 * </ol>
 * 
 * @author alan
 */
public class OrthogonalCrossingsAnalysis implements IAnalysis {

    private static final double EPSILON = 1e-4;
    private final Set<KVector> junctionPoints;
    private final List<Line2D> segments;

    /**
     * Identifier of the orthogonal crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.orthogonalCrossings";

    /**
     * Creates analyzer.
     */
    public OrthogonalCrossingsAnalysis() {
        segments = Lists.newArrayList();
        junctionPoints = new TreeSet<KVector>(vectorCompare());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final KNode parent, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Orthogonal crossings analysis", 1);
        
        junctionPoints.clear();
        segments.clear();
        try {
            int crossings = countCrossings(parent);
            progressMonitor.done();
            return new Object[] { crossings };
        } catch (UnsupportedOperationException e) {
            return new AnalysisFailed(Type.Failed, e);
        }
    }

    public int countCrossings(final KNode parent) { 
        collectSegmentsAndJunctionPoints(parent.getChildren(), new KVector(0, 0));
        mergeOverlappingSegments();
        return count();
    }

    private void collectSegmentsAndJunctionPoints(final List<KNode> nodes,
            final KVector offset) {
        for (KNode node : nodes) {
            addJunctionPointsOnEdgesConnectedTo(node, new KVector(offset));
            addSegments(offset, node);
            if (hasChildren(node)) {
                KVector childrenOffset = copyPositionOf(node).add(offset);
                collectSegmentsAndJunctionPoints(node.getChildren(), new KVector(childrenOffset));
            }
        }
    }

    private void addSegments(final KVector offset, final KNode node) {
        for (KEdge edge : node.getOutgoingEdges()) {
            if (!edge.getData(KEdgeLayout.class).getProperty(CoreOptions.NO_LAYOUT)) {
                KVector edgeOffset = targetsChildOf(edge, node) ? copyPositionOf(node).add(offset) : offset;
                addSegmentsOf(edge, edgeOffset);
            }
        }
    }

    private boolean targetsChildOf(final KEdge e, final KNode n) {
        return n.getChildren().contains(e.getTarget());
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

    private int count() {
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

    private void addSegmentsOf(final KEdge edge, final KVector offset) {
        KVectorChain chain = new KVectorChain(edge.getData(KEdgeLayout.class).createVectorChain());
        KVectorChain copy = new KVectorChain();
        for (KVector kVector : chain) {
            copy.add(new KVector(kVector));
        }
        copy.offset(offset);
        Iterator<KVector> iterator = copy.iterator();
        KVector start = iterator.next();
        while (iterator.hasNext()) {
            KVector end = iterator.next();
            Line2D line = new Line2D.Double(start.x, start.y, end.x, end.y);
            if (start.x != end.x && start.y != end.y) {
                throw new UnsupportedOperationException("Not orthogonal");
            }
            segments.add(line);
            start = end;
        }
    }

    private void addJunctionPointsOnEdgesConnectedTo(final KNode node, final KVector parentOffset) {
        for (KEdge edge : node.getOutgoingEdges()) {
            // We must copy the junctions, otherwise we change the offset in the graph we are
            // analyzing.
            KVector offset = targetsChildOf(edge, node) ? copyPositionOf(node).add(parentOffset)
                    : parentOffset;
            KVectorChain junctions =
                    edge.getData(KEdgeLayout.class).getProperty(CoreOptions.JUNCTION_POINTS);
            List<KVector> offsettedJunctions = new ArrayList<>();
            junctions.forEach(v -> offsettedJunctions.add(new KVector(v).add(offset)));
            junctionPoints.addAll(offsettedJunctions);
        }
    }

    private void merge(final Line2D lineOne, final Line2D lineTwo) {
        double x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        if (isHorizontal(lineOne)) {
            Double[] xCoords = new Double[] { lineOne.getX1(), lineOne.getX2(), lineTwo.getX1(),
                    lineTwo.getX2() };
            Arrays.sort(xCoords);
            x1 = xCoords[0];
            x2 = xCoords[xCoords.length - 1];
            y1 = lineOne.getY1();
            y2 = lineOne.getY1();
        } else {
            Double[] yCoords = new Double[] { lineOne.getY1(), lineOne.getY2(), lineTwo.getY1(),
                    lineTwo.getY2() };
            Arrays.sort(yCoords);
            y1 = yCoords[0];
            y2 = yCoords[yCoords.length - 1];
            x1 = lineOne.getX1();
            x2 = lineOne.getX1();
        }
        lineOne.setLine(x1, y1, x2, y2);
    }

    private boolean crossingIsVisible(final Line2D firstLine, final Line2D secondLine) {
        return areNotParallel(firstLine, secondLine) && doNotOnlyTouchAtEnds(firstLine, secondLine)
        // && intersect(firstLine, secondLine)
                && firstLine.intersectsLine(secondLine)
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

    private boolean intersectionHasNoJunctionPoint(final Line2D firstLine,
            final Line2D secondLine) {
        KVector intersection = intersectionOf(firstLine, secondLine);
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

    private Comparator<KVector> vectorCompare() {
        return (v1, v2) -> {
            if (doubleEquals(v1.x, v2.x) && doubleEquals(v1.y, v2.y)) {
                return 0;
            } else if (doubleEquals(v1.x, v2.x)) {
                return Doubles.compare(v1.y, v2.y);
            } else {
                return Doubles.compare(v1.x, v2.x);
            }
        };
    }

    private boolean canBeMerged(final Line2D line1, final Line2D line2) {
        return line1.intersectsLine(line2) && !areNotParallel(line1, line2);
    }

    private boolean hasChildren(final KNode node) {
        return !node.getChildren().isEmpty();
    }

    private KVector copyPositionOf(final KNode parent) {
        return new KVector(parent.getData(KShapeLayout.class).createVector());
    }

}
