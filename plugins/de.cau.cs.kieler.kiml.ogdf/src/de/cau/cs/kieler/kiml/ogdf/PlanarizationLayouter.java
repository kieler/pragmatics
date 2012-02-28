/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import java.util.List;

import net.ogdf.bin.OgdfServer;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfLayouter {

    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.3f;
    /** default value for spacing. */
    public static final float DEF_SPACING = 20.0f;

    /** 'aspectRatio' property. */
    private static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, DEF_PAGE_RATIO);
    /** 'spacing' property. */
    private static final IProperty<Float> SPACING = new Property<Float>(
            LayoutOptions.SPACING, DEF_SPACING, 1.0f);
    /** 'direction' property. */
    private static final IProperty<Direction> DIRECTION = new Property<Direction>(
            LayoutOptions.DIRECTION, Direction.UP);
    /** 'preprocessCliques' property. */
    private static final IProperty<Boolean> PREPROCESS_CLIQUES = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.preprocessCliques", false);
    /** 'minCliqueSize' property. */
    private static final IProperty<Integer> MIN_CLIQUE_SIZE = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.minCliqueSize", 10);
    /** 'costAssoc' property. */
    private static final IProperty<Integer> COST_ASSOC = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.costAssoc", 1);
    /** 'costGen' property. */
    private static final IProperty<Integer> COST_GEN = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.costGen", 4);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Constructs a PlanarizationLayouter.
     */
    public PlanarizationLayouter() {
        super("PLANARIZATION");
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // pageRatio
        float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
        addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
        // minSpacing
        float minSpacing = parentLayout.getProperty(SPACING);
        addOption(OgdfServer.OPTION_SEPARATION, minSpacing);
        // layoutDirection
        Direction direction = parentLayout.getProperty(DIRECTION);
        int layoutDirection;
        switch (direction) {
        case UP:
            layoutDirection = OgdfServer.DIRECTION_SOUTH;
            break;
        case LEFT:
            layoutDirection = OgdfServer.DIRECTION_WEST;
            break;
        case RIGHT:
            layoutDirection = OgdfServer.DIRECTION_EAST;
            break;
        default:
            layoutDirection = OgdfServer.DIRECTION_NORTH;
            break;
        }
        addOption(OgdfServer.OPTION_LAYOUT_DIRECTION, layoutDirection);
        // preprocessCliques
        boolean preprocessCliques = parentLayout.getProperty(PREPROCESS_CLIQUES);
        addOption(OgdfServer.OPTION_PREPROCESS_CLIQUES, preprocessCliques);
        // minCliqueSize
        int minCliqueSize = parentLayout.getProperty(MIN_CLIQUE_SIZE);
        addOption(OgdfServer.OPTION_MIN_CLIQUE_SIZE, minCliqueSize);
        // costAssoc
        int costAssoc = parentLayout.getProperty(COST_ASSOC);
        addOption(OgdfServer.OPTION_COST_ASSOC, costAssoc);
        // costGen
        int costGen = parentLayout.getProperty(COST_GEN);
        addOption(OgdfServer.OPTION_COST_GEN, costGen);
        // perform pre-processing
        loopRouter.preProcess(layoutNode);
    }

    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.exclude();
        removeDuplicateBends(layoutNode);
    }

    private static final float TOLERANCE = 5.0f;
    private static final float TOLERANCE2 = TOLERANCE * TOLERANCE;

    /**
     * Drops bend points which are adjacent to the start or end point when their position is
     * identical or close to identical.
     * 
     * @param layoutNode
     *            the parent node
     */
    private void removeDuplicateBends(final KNode layoutNode) {
        for (KNode node : layoutNode.getChildren()) {
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                KPoint source = edgeLayout.getSourcePoint();
                KPoint target = edgeLayout.getTargetPoint();
                List<KPoint> bends = edgeLayout.getBendPoints();
                // merge with source point
                while (!bends.isEmpty()) {
                    KPoint bend = bends.get(0);
                    if (distance2(source, bend) < TOLERANCE2) {
                        // drop the bend point
                        bends.remove(0);
                    } else {
                        break;
                    }
                }
                // merge with target point
                while (!bends.isEmpty()) {
                    KPoint bend = bends.get(bends.size() - 1);
                    if (distance2(target, bend) < TOLERANCE2) {
                        // drop the bend point
                        bends.remove(bends.size() - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private float distance2(final KPoint p1, final KPoint p2) {
        float dX = p2.getX() - p1.getX();
        float dY = p2.getY() - p1.getY();
        return dX * dX + dY * dY;
    }

}
