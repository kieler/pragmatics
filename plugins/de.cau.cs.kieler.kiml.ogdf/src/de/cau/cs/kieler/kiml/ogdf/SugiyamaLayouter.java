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
 * The Sugiyama layouter from the OGDF library.
 * 
 * @author mri
 */
public class SugiyamaLayouter extends OgdfLayouter {
    
    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.3f;
    /** default value for spacing. */
    public static final float DEF_SPACING = 16.0f;

    /** 'aspectRatio' property. */
    private static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, DEF_PAGE_RATIO);
    /** 'spacing' property. */
    private static final IProperty<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING);
    /** the 'fails' option identifier. */
    private static final String FAILS_ID = "de.cau.cs.kieler.kiml.ogdf.option.fails";
    /** 'fails' property. */
    private static final IProperty<Integer> FAILS = new Property<Integer>(FAILS_ID, 4);
    /** the 'runs' option identifier. */
    private static final String RUNS_ID = "de.cau.cs.kieler.kiml.ogdf.option.runs";
    /** 'runs' property. */
    private static final IProperty<Integer> RUNS = new Property<Integer>(RUNS_ID, 15);
    /** the 'transpose' option identifier. */
    private static final String TRANSPOSE_ID = "de.cau.cs.kieler.kiml.ogdf.option.transpose";
    /** 'transpose' property. */
    private static final IProperty<Boolean> TRANSPOSE = new Property<Boolean>(TRANSPOSE_ID, true);
    /** the 'minDistCC' option identifier. */
    private static final String MIN_DIST_CC_ID = "de.cau.cs.kieler.kiml.ogdf.option.minDistCC";
    /** 'minDistCC' property. */
    private static final IProperty<Float> MIN_DIST_CC = new Property<Float>(MIN_DIST_CC_ID, 20.0f);
    /** the 'layerDistance' option identifier. */
    private static final String LAYER_DISTANCE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistLevel";
    /** 'layerDistance' property. */
    private static final IProperty<Float> LAYER_DISTANCE = new Property<Float>(LAYER_DISTANCE_ID,
            16.0f);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Contructs a SugiyamaLayouter.
     */
    public SugiyamaLayouter() {
        super("SUGIYAMA");
    }

    /**
     * {@inheritDoc}
     */
    public void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // pageRatio
        float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
        addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
        // minSpacing
        float minSpacing = parentLayout.getProperty(SPACING);
        addOption(OgdfServer.OPTION_NODE_DISTANCE, minSpacing);
        // fails
        int fails = parentLayout.getProperty(FAILS);
        addOption(OgdfServer.OPTION_FAILS, fails);
        // runs
        int runs = parentLayout.getProperty(RUNS);
        addOption(OgdfServer.OPTION_RUNS, runs);
        // transpose
        boolean transpose = parentLayout.getProperty(TRANSPOSE);
        addOption(OgdfServer.OPTION_TRANSPOSE, transpose);
        // arrangeCCs
        Boolean arrangeCCs = parentLayout.getProperty(LayoutOptions.SEPARATE_CC);
        addOption(OgdfServer.OPTION_ARRANGE_CC, arrangeCCs != null && arrangeCCs.booleanValue());
        // minDistCC
        float minDistCC = parentLayout.getProperty(MIN_DIST_CC);
        addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCC);
        // layerDistance
        float layerDistance = parentLayout.getProperty(LAYER_DISTANCE);
        addOption(OgdfServer.OPTION_LAYER_DISTANCE, layerDistance);
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
