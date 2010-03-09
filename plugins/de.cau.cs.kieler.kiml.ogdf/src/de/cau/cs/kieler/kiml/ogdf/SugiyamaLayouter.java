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

    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 15;
    /** default value for minimum spacing. */
    public static final float DEF_MIN_SPACING = 16.0f;
    /** default value for label edge distance. */
    public static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;
    /** default value for layer distance. */
    public static final int DEF_FAILS = 4;
    /** default value for layer distance. */
    public static final int DEF_RUNS = 15;
    /** default value for layer distance. */
    public static final boolean DEF_TRANSPOSE = true;

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();
    
    /**
     * {@inheritDoc}
     */
    public LayoutModule prepareLayouter(final KNode layoutNode) {
        // create the layouter
        SugiyamaLayout sugiLayout = new SugiyamaLayout();
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        FastHierarchyLayout hierarchyLayout = new FastHierarchyLayout();

        // set the minimum spacing and layer distance
        float minSpacing = LayoutOptions.getFloat(parentLayout,
                LayoutOptions.MIN_SPACING);
        if (Float.isNaN(minSpacing)) {
            minSpacing = DEF_MIN_SPACING;
        }
        hierarchyLayout.nodeDistance(minSpacing);
        hierarchyLayout.layerDistance(minSpacing);

        // set the hierarchy layout module
        sugiLayout.setLayout(hierarchyLayout);
        
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);

        return sugiLayout;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.postProcess();
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
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
    
}
