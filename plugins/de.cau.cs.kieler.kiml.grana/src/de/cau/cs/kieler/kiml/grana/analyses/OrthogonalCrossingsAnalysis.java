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

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
 * @author alan
 *
 */
public class OrthogonalCrossingsAnalysis implements IAnalysis {
    /** tolerance for double equality. */
    private static final double TOLERANCE = 1e-4;

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
            crossings += getCrossingsForChildrenOf(node);
            if (shouldAnalyzeHierarchy) {
                nodes.addAll(node.getChildren());
            }
        }
        return crossings;
    }

    private int getCrossingsForChildrenOf(final KNode parentNode) {

        List<KNode> nodes = new ArrayList<KNode>(parentNode.getChildren());
        List<Line2D> lines = Lists.newArrayList();
        Set<KVector> junctionPoints = Sets.newHashSet();
        for (KNode n : nodes) {
            lines.addAll(collectLines(n, parentNode));
            junctionPoints.addAll(getJunctionPoints(n));
        }

        mergeOverlappingLines(lines);

        return countCrossings(lines, junctionPoints);
    }

    private Set<KVector> getJunctionPoints(final KNode node) {
        Set<KVector> junctionPoints = Sets.newHashSet();
        for (KEdge edge : node.getIncomingEdges()) {
            junctionPoints.addAll(edge.getData(KEdgeLayout.class).getProperty(
                    LayoutOptions.JUNCTION_POINTS));
        }
        for (KEdge edge : node.getOutgoingEdges()) {
            junctionPoints.addAll(edge.getData(KEdgeLayout.class).getProperty(
                    LayoutOptions.JUNCTION_POINTS));
        }
        return junctionPoints;
    }

    private List<Line2D> collectLines(final KNode node, final KNode parentNode) {
        ArrayList<Line2D> lines = Lists.newArrayList();

        for (KEdge edge : node.getIncomingEdges()) {
            if (!node.getChildren().contains(edge.getSource())) {
                lines.addAll(getLines(edge));
            }
        }
        for (KEdge edge : node.getOutgoingEdges()) {
            if (edge.getTarget() == parentNode) {
                lines.addAll(getLines(edge));
            }
        }
        return lines;
    }

    private Collection<? extends Line2D> getLines(final KEdge edge) {
        ArrayList<Line2D> lines = Lists.newArrayList();
        KVectorChain chain = edge.getData(KEdgeLayout.class).createVectorChain();
        Iterator<KVector> iterator = chain.iterator();
        KVector start = iterator.next();
        while (iterator.hasNext()) {
            KVector end = iterator.next();
            Line2D line = new Line2D.Double(start.x, start.y, end.x, end.y);
            lines.add(line);
            start = end;
        }
        return lines;
    }

    private void mergeOverlappingLines(final List<Line2D> lines) {
        // Repeatedly iterate over all lines until all overlapping lines have been merged.
        // This is necessary because the order in lines can be arbitrary, so we might miss
        // a possible merge.
        boolean hasMerged;
        do {
            hasMerged = false;
            for (int i = 0; i < lines.size(); i++) {
                for (int j = i + 1; j < lines.size(); j++) {
                    Line2D lineOne = lines.get(i);
                    Line2D lineTwo = lines.get(j);
                    if (canBeMerged(lineOne, lineTwo)) {
                        merge(lineOne, lineTwo);
                        // Concurrent modification on purpose, size() is evaluated on each iteration
                        lines.remove(j);
                        hasMerged = true;
                    }
                }
            }
        } while (hasMerged);
    }

    private void merge(final Line2D lineOne, final Line2D lineTwo) {
        double x1 = 0;
        double x2 = 0;
        double y1 = 0;
        double y2 = 0;
        if (isHorizontal(lineOne)) {
            Double[] xCoords =
                    new Double[] { lineOne.getX1(), lineOne.getX2(), lineTwo.getX1(),
                            lineTwo.getX2() };
            Arrays.sort(xCoords);
            x1 = xCoords[0];
            x2 = xCoords[3];
            y1 = lineOne.getY1();
            y2 = lineOne.getY1();
        } else {
            Double[] yCoords =
                    new Double[] { lineOne.getY1(), lineOne.getY2(), lineTwo.getY1(),
                            lineTwo.getY2() };
            Arrays.sort(yCoords);
            y1 = yCoords[0];
            y2 = yCoords[3];
            x1 = lineOne.getX1();
            x2 = lineOne.getX1();
        }
        lineOne.setLine(x1, y1, x2, y2);
    }

    private int countCrossings(final List<Line2D> lines, final Set<KVector> junctionPoints) {
        int crossings = 0;
        for (int i = 0; i < lines.size(); i++) {
            Line2D firstLine = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Line2D secondLine = lines.get(j);
                if (cross(firstLine, secondLine, junctionPoints)) {
                    crossings++;
                }
            }
        }
        return crossings;
    }

    private boolean cross(final Line2D firstLine, final Line2D secondLine,
            final Set<KVector> junctionPoints) {
        return !areParallel(firstLine, secondLine) && !linesTouchAtEnds(firstLine, secondLine)
                && intersect(firstLine, secondLine)
                && !intersectionHasJunctionPoint(firstLine, secondLine, junctionPoints);
    }

    private boolean intersect(final Line2D firstLine, final Line2D secondLine) {
        Line2D verticalLine = isVertical(firstLine) ? firstLine : secondLine;
        Line2D horizontalLine = isHorizontal(firstLine) ? firstLine : secondLine;
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

    private boolean intersectionHasJunctionPoint(final Line2D firstLine, final Line2D secondLine,
            final Set<KVector> junctionPoints) {
        KVector intersection = intersectionOf(firstLine, secondLine);
        return junctionPoints.contains(intersection);
    }

    private KVector intersectionOf(final Line2D firstLine, final Line2D secondLine) {
        assert !areParallel(firstLine, secondLine);
        Line2D verticalLine = isVertical(firstLine) ? firstLine : secondLine;
        Line2D horizontalLine = isHorizontal(firstLine) ? firstLine : secondLine;
        return new KVector(verticalLine.getX1(), horizontalLine.getY1());
    }

    private boolean linesTouchAtEnds(final Line2D firstLine, final Line2D secondLine) {
        Line2D verticalLine = isVertical(firstLine) ? firstLine : secondLine;
        Line2D horizontalLine = isHorizontal(firstLine) ? firstLine : secondLine;
        return doubleEquals(horizontalLine.getX1(), verticalLine.getX1())
                || doubleEquals(horizontalLine.getX2(), verticalLine.getX1())
                || doubleEquals(horizontalLine.getY1(), verticalLine.getY1())
                || doubleEquals(horizontalLine.getY1(), verticalLine.getY2());
    }

    private boolean isVertical(final Line2D line) {
        return doubleEquals(line.getX1(), line.getX2());
    }

    private boolean isHorizontal(final Line2D line) {
        return doubleEquals(line.getY1(), line.getY2());
    }

    private boolean doubleEquals(final double y1, final double y2) {
        return Math.abs(y1 - y2) < TOLERANCE;
    }

    private boolean canBeMerged(final Line2D line1, final Line2D line2) {
        return line1.intersectsLine(line2) && areParallel(line1, line2);
    }

    private boolean areParallel(final Line2D firstLine, final Line2D secondLine) {
        return isVertical(firstLine) && isVertical(secondLine) || isHorizontal(firstLine)
                && isHorizontal(secondLine);
    }

}
