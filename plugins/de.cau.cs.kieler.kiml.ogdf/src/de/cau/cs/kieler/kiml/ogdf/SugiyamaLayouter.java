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

import net.ogdf.lib.FastHierarchyLayout;
import net.ogdf.lib.LayoutModule;
import net.ogdf.lib.SugiyamaLayout;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * The Sugiyama layouter from the OGDF library.
 * 
 * @author mri
 */
public class SugiyamaLayouter extends OgdfLayouter {

    /** layout option identifier for layer distance. */
    public static final String OPT_LAYER_DISTANCE = "de.cau.cs.kieler.kiml.ogdf.option.layerDistance";
    /** layout option identifier for the number of fails. */
    public static final String OPT_FAILS = "de.cau.cs.kieler.kiml.ogdf.option.fails";
    /** layout option identifier for the number of runs. */
    public static final String OPT_RUNS = "de.cau.cs.kieler.kiml.ogdf.option.runs";
    /** layout option identifier for the number of transpose. */
    public static final String OPT_TRANSPOSE = "de.cau.cs.kieler.kiml.ogdf.option.transpose";
    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 8;
    /** default value for minimum spacing. */
    public static final float DEF_MIN_SPACING = 16.0f;
    /** default value for layer distance. */
    public static final float DEF_LAYER_DISTANCE = 16.0f;
    /** default value for label edge distance. */
    public static final float DEF_LABEL_EDGE_DISTANCE = 15.0f;
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;
    /** default value for layer distance. */
    public static final int DEF_FAILS = 4;
    /** default value for layer distance. */
    public static final int DEF_RUNS = 15;
    /** default value for layer distance. */
    public static final boolean DEF_TRANSPOSE = true;

    /**
     * {@inheritDoc}
     */
    public LayoutModule prepareLayouter(final KNode layoutNode) {
        // create the layouter
        SugiyamaLayout layout = new SugiyamaLayout();

        // set options
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        // set number of fails
        int fails = LayoutOptions.getInt(parentLayout, OPT_FAILS);
        layout.fails(fails);

        // set number of runs
        int runs = LayoutOptions.getInt(parentLayout, OPT_RUNS);
        layout.runs(runs);

        // enable/disable transpose
        boolean transpose = LayoutOptions.getBoolean(parentLayout, OPT_TRANSPOSE);
        layout.transpose(transpose);

        FastHierarchyLayout hierarchyLayout = new FastHierarchyLayout();

        // set the minimum spacing
        float minSpacing = LayoutOptions.getFloat(parentLayout,
                LayoutOptions.MIN_SPACING);
        if (Float.isNaN(minSpacing)) {
            minSpacing = DEF_MIN_SPACING;
        }
        hierarchyLayout.nodeDistance(minSpacing);

        // set the layer distance
        float layerDistance = LayoutOptions.getFloat(parentLayout,
                OPT_LAYER_DISTANCE);
        if (Float.isNaN(layerDistance)) {
            layerDistance = DEF_LAYER_DISTANCE;
        }
        hierarchyLayout.layerDistance(layerDistance);

        // set the hierarchy layout module
        layout.setLayout(hierarchyLayout);

        return layout;
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(LayoutOptions.MIN_SPACING)) {
            return DEF_MIN_SPACING;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_EDGE_DISTANCE;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else if (optionId.equals(OPT_LAYER_DISTANCE)) {
            return DEF_LAYER_DISTANCE;
        } else if (optionId.equals(OPT_FAILS)) {
            return DEF_FAILS;
        } else if (optionId.equals(OPT_RUNS)) {
            return DEF_RUNS;
        } else if (optionId.equals(OPT_TRANSPOSE)) {
            return DEF_TRANSPOSE;
        } else {
            return null;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isUmlGraph(final KNode layoutNode) {
        return false;
    }
    
}
