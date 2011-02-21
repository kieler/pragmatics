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

import net.ogdf.lib.Ogdf;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The tree layouter from the OGDF library.
 * 
 * @author mri
 */
public class TreeLayouter extends OgdfLayouter {

    /** default value for the minimum level distance. */
    private static final float DEF_MIN_DIST = 22.0f;

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();
    
    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {

        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // get the general minimum distance
        float minDist = parentLayout.getProperty(LayoutOptions.SPACING);
        if (minDist < 0) {
            minDist = DEF_MIN_DIST;
        }
        // get orthogonal option
        EdgeRouting edgeRouting = parentLayout.getProperty(LayoutOptions.EDGE_ROUTING);
        boolean orthogonal = edgeRouting == EdgeRouting.ORTHOGONAL;
        // get orientation
        Direction direction = parentLayout.getProperty(LayoutOptions.DIRECTION);
        int orientation = Ogdf.ORIENTATION_TOP_TO_BOTTOM;
        switch (direction) {
        case LEFT:
            orientation = Ogdf.ORIENTATION_RIGHT_TO_LEFT;
            break;
        case RIGHT:
            orientation = Ogdf.ORIENTATION_LEFT_TO_RIGHT;
            break;
        case UP:
            orientation = Ogdf.ORIENTATION_BOTTOM_TO_TOP;
            break;
        }
        
        Ogdf.createTreeLayouter(minDist, minDist, 2 * minDist,
                2 * minDist, orthogonal, orientation);
        
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
        defaultsHolder.setProperty(LayoutOptions.SPACING, DEF_MIN_DIST);
        defaultsHolder.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
        defaultsHolder.setProperty(LayoutOptions.DIRECTION, Direction.DOWN);
    }

}
