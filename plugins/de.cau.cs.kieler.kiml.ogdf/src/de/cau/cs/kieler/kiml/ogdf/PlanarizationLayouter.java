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

import net.ogdf.bin.OgdfServer;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
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
    private static final IProperty<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING, 1.0f);
    /** 'direction' property. */
    private static final IProperty<Direction> DIRECTION = new Property<Direction>(
            LayoutOptions.DIRECTION, Direction.UP);
    /** the 'preprocessCliques' option identifier. */
    private static final String PREPROCESS_CLIQUES_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.preprocessCliques";
    /** 'preprocessCliques' property. */
    private static final IProperty<Boolean> PREPROCESS_CLIQUES = new Property<Boolean>(
            PREPROCESS_CLIQUES_ID, false);
    /** the 'minCliqueSize' option identifier. */
    private static final String MIN_CLIQUE_SIZE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.minCliqueSize";
    /** 'minCliqueSize' property. */
    private static final IProperty<Integer> MIN_CLIQUE_SIZE = new Property<Integer>(
            MIN_CLIQUE_SIZE_ID, 10);
    /** the 'costAssoc' option identifier. */
    private static final String COST_ASSOC_ID = "de.cau.cs.kieler.kiml.ogdf.option.costAssoc";
    /** 'costAssoc' property. */
    private static final IProperty<Integer> COST_ASSOC = new Property<Integer>(COST_ASSOC_ID, 1);
    /** the 'costGen' option identifier. */
    private static final String COST_GEN_ID = "de.cau.cs.kieler.kiml.ogdf.option.costGen";
    /** 'costGen' property. */
    private static final IProperty<Integer> COST_GEN = new Property<Integer>(COST_GEN_ID, 4);

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
    }

}
