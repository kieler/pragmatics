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
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The circular layouter from the OGDF library.
 * 
 * @author mri
 */
public class CircularLayouter extends OgdfLayouter {

    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.3f;
    /** default value for spacing. */
    public static final float DEF_MIN_DIST_CIRCLE = 20.0f;

    /** 'aspectRatio' property. */
    private static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, DEF_PAGE_RATIO);
    /** the 'minDistLevel' option identifier. */
    private static final String MIN_DIST_LEVEL_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel";
    /** 'minDistLevel' property. */
    private static final IProperty<Float> MIN_DIST_LEVEL = new Property<Float>(MIN_DIST_LEVEL_ID,
            20.0f);
    /** the 'minDistSibling' option identifier. */
    private static final String MIN_DIST_SIBLING_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistSibling";
    /** 'minDistSibling' property. */
    private static final IProperty<Float> MIN_DIST_SIBLING = new Property<Float>(
            MIN_DIST_SIBLING_ID, 20.0f);
    /** the 'minDistCC' option identifier. */
    private static final String MIN_DIST_CC_ID = "de.cau.cs.kieler.kiml.ogdf.option.minDistCC";
    /** 'minDistCC' property. */
    private static final IProperty<Float> MIN_DIST_CC = new Property<Float>(MIN_DIST_CC_ID, 20.0f);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Constructs a CircularLayouter.
     */
    public CircularLayouter() {
        super("CIRCULAR");
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // pageRatio
        float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
        addOption(OgdfServerAPI.OPTION_PAGE_RATIO, pageRatio);
        // minDistCircle
        float minDistCircle = parentLayout.getProperty(LayoutOptions.SPACING);
        if (minDistCircle < 0) {
            minDistCircle = DEF_MIN_DIST_CIRCLE;
        }
        addOption(OgdfServerAPI.OPTION_MIN_DIST_CIRCLE, minDistCircle);
        // minDistLevel
        float minDistLevel = parentLayout.getProperty(MIN_DIST_LEVEL);
        addOption(OgdfServerAPI.OPTION_MIN_DIST_LEVEL, minDistLevel);
        // minDistSibling
        float minDistSibling = parentLayout.getProperty(MIN_DIST_SIBLING);
        addOption(OgdfServerAPI.OPTION_MIN_DIST_SIBLING, minDistSibling);
        // minDistCC
        float minDistCC = parentLayout.getProperty(MIN_DIST_CC);
        addOption(OgdfServerAPI.OPTION_MIN_DIST_CC, minDistCC);
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
