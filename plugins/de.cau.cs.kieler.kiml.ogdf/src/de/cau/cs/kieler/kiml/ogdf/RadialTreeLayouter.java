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
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The radial tree layouter from the OGDF library.
 * 
 * @author mri
 */
public class RadialTreeLayouter extends OgdfLayouter {

    /** 'levelDistance' property. */
    private static final IProperty<Float> SPACING = new Property<Float>(LayoutOptions.SPACING, 50.0f);
    /** factor for 'ccDistance' property. */
    private static final IProperty<Float> CC_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 1.0f);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Constructs a RadialTreeLayouter.
     */
    public RadialTreeLayouter() {
        super("RADIAL_TREE");
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // levelDistance
        float levelDistance = parentLayout.getProperty(SPACING);
        addOption(OgdfServer.OPTION_LEVEL_DISTANCE, levelDistance);
        // ccDistance
        float ccDistanceFactor = parentLayout.getProperty(CC_DISTANCE);
        addOption(OgdfServer.OPTION_CC_DISTANCE, levelDistance * ccDistanceFactor);
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
