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
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * The Spring Embedder algorithm by Fruchterman and Reingold.
 * 
 * @author mri
 */
public class SpringEmbedderFRLayouter extends OgdfLayouter {

    /** the iterations option. */
    private static final String ITERATIONS =
            "de.cau.cs.kieler.kiml.ogdf.option.iterations";
    /** default value for the number of iterations. */
    private static final int DEF_ITERATIONS = 400;
    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15;
    /** default value for label edge distance. */
    private static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    private static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {

        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        
        // get the number of iterations
        int iterations =
                LayoutOptions.getInt(parentLayout, ITERATIONS);
        if (iterations == 0) {
            iterations = DEF_ITERATIONS;
        }
        
        Ogdf.createSpringEmbedderFRLayouter(iterations);
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(ITERATIONS)) {
            return DEF_ITERATIONS;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }

}
