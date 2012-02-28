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
            LayoutOptions.DIRECTION, Direction.RIGHT);
    /** 'edgeRouting' property. */
    private static final IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
            LayoutOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
    /** 'siblingDistance' property. */
    private static final IProperty<Float> SIBLING_DISTANCE = new Property<Float>(
            LayoutOptions.SPACING, 20.0f);
    /** factor for 'levelDistance' property. */
    private static final IProperty<Float> LEVEL_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel", 1.0f);
    /** factor for 'subtreeDistance' property. */
    private static final IProperty<Float> SUBTREE_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.subtreeDistance", 1.0f);
    /** factor for 'treeDistance' property. */
    private static final IProperty<Float> TREE_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0f);

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
        int orientation;
        switch (direction) {
        case LEFT:
            orientation = OgdfServer.ORIENTATION_RIGHT_TO_LEFT;
            break;
        case UP:
            orientation = OgdfServer.ORIENTATION_TOP_TO_BOTTOM;
            break;
        case DOWN:
            orientation = OgdfServer.ORIENTATION_BOTTOM_TO_TOP;
            break;
        default:
            orientation = OgdfServer.ORIENTATION_LEFT_TO_RIGHT;
        }
        addOption(OgdfServer.OPTION_ORIENTATION, orientation);
        // edgeRouting
        EdgeRouting edgeRouting = parentLayout.getProperty(EDGE_ROUTING);
        boolean orthogonal = edgeRouting == EdgeRouting.ORTHOGONAL;
        addOption(OgdfServer.OPTION_ORTHOGONAL, orthogonal);
        // siblingDistance
        float siblingDistance = parentLayout.getProperty(SIBLING_DISTANCE);
        addOption(OgdfServer.OPTION_SIBLING_DISTANCE, siblingDistance);
        // levelDistance
        float levelDistanceFactor = parentLayout.getProperty(LEVEL_DISTANCE);
        addOption(OgdfServer.OPTION_LEVEL_DISTANCE, siblingDistance * levelDistanceFactor);
        // subtreeDistance
        float subtreeDistanceFactor = parentLayout.getProperty(SUBTREE_DISTANCE);
        addOption(OgdfServer.OPTION_SUBTREE_DISTANCE, siblingDistance * subtreeDistanceFactor);
        // treeDistance
        float treeDistanceFactor = parentLayout.getProperty(TREE_DISTANCE);
        addOption(OgdfServer.OPTION_TREE_DISTANCE, siblingDistance * treeDistanceFactor);
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }

    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.exclude();
    }

}
