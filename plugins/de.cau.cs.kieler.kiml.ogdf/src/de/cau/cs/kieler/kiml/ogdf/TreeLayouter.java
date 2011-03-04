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

import net.ogdf.bin.OgdfServerAPI;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The tree layouter from the OGDF library.
 * 
 * @author mri
 */
public class TreeLayouter extends OgdfLayouter {

    /** 'direction' property. */
    private static final IProperty<Direction> DIRECTION = new Property<Direction>(
            LayoutOptions.DIRECTION, Direction.UP);
    /** 'edgeRouting' property. */
    private static final IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
            LayoutOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
    /** the 'siblingDistance' option identifier. */
    private static final String SIBLING_DISTANCE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.siblingDistance";
    /** 'siblingDistance' property. */
    private static final IProperty<Float> SIBLING_DISTANCE = new Property<Float>(
            SIBLING_DISTANCE_ID, 20.0f);
    /** the 'subtreeDistance' option identifier. */
    private static final String SUBTREE_DISTANCE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.subtreeDistance";
    /** 'subtreeDistance' property. */
    private static final IProperty<Float> SUBTREE_DISTANCE = new Property<Float>(
            SUBTREE_DISTANCE_ID, 20.0f);
    /** the 'levelDistance' option identifier. */
    private static final String LEVEL_DISTANCE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.levelDistance";
    /** 'levelDistance' property. */
    private static final IProperty<Float> LEVEL_DISTANCE = new Property<Float>(LEVEL_DISTANCE_ID,
            50.0f);
    /** the 'treeDistance' option identifier. */
    private static final String TREE_DISTANCE_ID = "de.cau.cs.kieler.kiml.ogdf.option.treeDistance";
    /** 'treeDistance' property. */
    private static final IProperty<Float> TREE_DISTANCE = new Property<Float>(TREE_DISTANCE_ID,
            50.0f);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Constructs a TreeLayouter.
     */
    public TreeLayouter() {
        super("TREE");
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // direction
        Direction direction = parentLayout.getProperty(DIRECTION);
        int orientation = OgdfServerAPI.ORIENTATION_BOTTOM_TO_TOP;
        switch (direction) {
        case LEFT:
            orientation = OgdfServerAPI.ORIENTATION_RIGHT_TO_LEFT;
            break;
        case RIGHT:
            orientation = OgdfServerAPI.ORIENTATION_LEFT_TO_RIGHT;
            break;
        case UP:
            orientation = OgdfServerAPI.ORIENTATION_TOP_TO_BOTTOM;
            break;
        }
        addOption(OgdfServerAPI.OPTION_ORIENTATION, orientation);
        // edgeRouting
        EdgeRouting edgeRouting = parentLayout.getProperty(EDGE_ROUTING);
        boolean orthogonal = edgeRouting == EdgeRouting.ORTHOGONAL;
        addOption(OgdfServerAPI.OPTION_ORTHOGONAL, orthogonal);
        // siblingDistance
        float siblingDistance = parentLayout.getProperty(SIBLING_DISTANCE);
        addOption(OgdfServerAPI.OPTION_SIBLING_DISTANCE, siblingDistance);
        // subtreeDistance
        float subtreeDistance = parentLayout.getProperty(SUBTREE_DISTANCE);
        addOption(OgdfServerAPI.OPTION_SUBTREE_DISTANCE, subtreeDistance);
        // levelDistance
        float levelDistance = parentLayout.getProperty(LEVEL_DISTANCE);
        addOption(OgdfServerAPI.OPTION_LEVEL_DISTANCE, levelDistance);
        // treeDistance
        float treeDistance = parentLayout.getProperty(TREE_DISTANCE);
        addOption(OgdfServerAPI.OPTION_TREE_DISTANCE, treeDistance);
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }

    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.exclude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDefaults(final IPropertyHolder defaultsHolder) {
        super.initDefaults(defaultsHolder);
        defaultsHolder.setProperty(LayoutOptions.DIRECTION, Direction.DOWN);
        defaultsHolder.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
    }

}
