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
 * The Spring Embedder algorithm by Fruchterman and Reingold.
 * 
 * @author mri
 */
public class SpringEmbedderFRLayouter extends OgdfLayouter {

    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.3f;

    /** 'aspectRatio' property. */
    private static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, DEF_PAGE_RATIO);
    /** 'iterations' property. */
    private static final IProperty<Integer> ITERATIONS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.iterations", 400);
    /** 'fineness' property. */
    private static final IProperty<Float> FINENESS = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.fineness", 0.51f);
    /** 'noise' property. */
    private static final IProperty<Boolean> NOISE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.ogdf.option.noise", true);
    /** 'minDistCC' property. */
    private static final IProperty<Float> MIN_DIST_CC = new Property<Float>(
            LayoutOptions.SPACING, 20.0f);

    /**
     * Constructs a SpringEmbedderFRLayouter.
     */
    public SpringEmbedderFRLayouter() {
        super("FRUCHTERMAN_REINGOLD");
    }
    
    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // pageRatio
        float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
        addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
        // iterations
        int iterations = parentLayout.getProperty(ITERATIONS);
        addOption(OgdfServer.OPTION_ITERATIONS, iterations);
        // fineness
        float fineness = parentLayout.getProperty(FINENESS);
        addOption(OgdfServer.OPTION_FINENESS, fineness);
        // noise
        boolean noise = parentLayout.getProperty(NOISE);
        addOption(OgdfServer.OPTION_NOISE, noise);
        // minDistCC
        float minDistCC = parentLayout.getProperty(MIN_DIST_CC);
        addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCC);
    }

}
