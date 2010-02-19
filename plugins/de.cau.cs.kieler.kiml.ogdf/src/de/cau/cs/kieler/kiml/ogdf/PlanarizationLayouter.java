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

import net.ogdf.lib.FastPlanarSubgraph;
import net.ogdf.lib.OrthoDir;
import net.ogdf.lib.OrthoLayout;
import net.ogdf.lib.PlanarizationLayout;
import net.ogdf.lib.UMLLayoutModule;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfUMLLayouter {

    /** layout option identifier for the page ratio. */
    public static final String OPT_PAGE_RATIO = "de.cau.cs.kieler.kiml.ogdf.option.pageRatio";

    /** layout option identifier for clique preprocessing. */
    public static final String OPT_PRE_CLIQUES = "de.cau.cs.kieler.kiml.ogdf.option.preCliques";

    /** layout option identifier for the minimum clique size. */
    public static final String OPT_MIN_CLIQUE_SIZE = "de.cau.cs.kieler.kiml.ogdf.option.minCliqueSize";

    /** layout option identifier for the number of runs. */
    public static final String OPT_RUNS = "de.cau.cs.kieler.kiml.ogdf.option.runs";

    /** layout option identifier for separation distance. */
    public static final String OPT_SEPARATION = "de.cau.cs.kieler.kiml.ogdf.option.separation";

    /** layout option identifier for overhang. */
    public static final String OPT_OVERHANG = "de.cau.cs.kieler.kiml.ogdf.option.cOverhang";

    /** layout option identifier for margin. */
    public static final String OPT_MARGIN = "de.cau.cs.kieler.kiml.ogdf.option.margin";

    /** layout option identifier for cost assoc. */
    public static final String OPT_COST_ASSOC = "de.cau.cs.kieler.kiml.ogdf.option.costAssoc";

    /** layout option identifier for cost gen. */
    public static final String OPT_COST_GEN = "de.cau.cs.kieler.kiml.ogdf.option.costGen";

    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.0f;

    /** default value for clique preprocessing. */
    public static final boolean DEF_PRE_CLIQUES = true;

    /** default value for minimum clique size. */
    public static final int DEF_MIN_CLIQUE_SIZE = 10;

    /** default value for number of runs. */
    public static final int DEF_RUNS = 0;

    /** default value for separation distance. */
    public static final float DEF_SEPARATION = 40.0f;

    /** default value for overhang. */
    public static final float DEF_OVERHANG = 0.2f;

    /** default value for margin. */
    public static final float DEF_MARGIN = 40.0f;

    /** default value for direction. */
    public static final LayoutDirection DEF_DIRECTION = LayoutDirection.UP;

    /** default value for cost assoc. */
    public static final int DEF_COST_ASSOC = 1;

    /** default value for cost gen. */
    public static final int DEF_COST_GEN = 4;

    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 8;

    /** default value for label edge distance. */
    public static final float DEF_LABEL_EDGE_DISTANCE = 15.0f;

    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /**
     * {@inheritDoc}
     */
    protected UMLLayoutModule prepareLayouter(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        // create the layouter
        PlanarizationLayout layout = new PlanarizationLayout();

        // set options
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        // set page ratio
        float pageRatio = LayoutOptions.getFloat(parentLayout, OPT_PAGE_RATIO);
        if (Float.isNaN(pageRatio)) {
            pageRatio = DEF_PAGE_RATIO;
        }
        layout.pageRatio(pageRatio);

        // set clique preprocessing
        boolean preClique = LayoutOptions.getBoolean(parentLayout,
                OPT_PRE_CLIQUES);
        layout.preprocessCliques(preClique);

        // set minimum clique size
        int minCliqueSize = LayoutOptions.getInt(parentLayout,
                OPT_MIN_CLIQUE_SIZE);
        if (minCliqueSize == Integer.MAX_VALUE
                || minCliqueSize == Integer.MIN_VALUE) {
            minCliqueSize = DEF_MIN_CLIQUE_SIZE;
        }
        layout.minCliqueSize(minCliqueSize);

        FastPlanarSubgraph fps = new FastPlanarSubgraph();

        // set number of runs
        int runs = LayoutOptions.getInt(parentLayout, OPT_RUNS);
        if (runs == Integer.MAX_VALUE || runs == Integer.MIN_VALUE) {
            runs = DEF_RUNS;
        }
        fps.runs(runs);

        // set subgraph module
        layout.setSubgraph(fps);

        OrthoLayout ol = new OrthoLayout();

        // set separation
        float separation = LayoutOptions.getFloat(parentLayout, OPT_SEPARATION);
        if (Float.isNaN(separation)) {
            separation = DEF_SEPARATION;
        }
        ol.separation(separation);

        // set overhang
        float overhang = LayoutOptions.getFloat(parentLayout, OPT_OVERHANG);
        if (Float.isNaN(overhang)) {
            overhang = DEF_OVERHANG;
        }
        ol.cOverhang(overhang);

        // set margin
        float margin = LayoutOptions.getFloat(parentLayout, OPT_MARGIN);
        if (Float.isNaN(margin)) {
            margin = DEF_MARGIN;
        }
        ol.margin(margin);

        // set cost assoc
        int costAssoc = LayoutOptions.getInt(parentLayout, OPT_COST_ASSOC);
        if (costAssoc == Integer.MAX_VALUE || costAssoc == Integer.MIN_VALUE) {
            costAssoc = DEF_COST_ASSOC;
        }
        ol.costAssoc(costAssoc);

        // set cost gen
        int costGen = LayoutOptions.getInt(parentLayout, OPT_COST_GEN);
        if (costGen == Integer.MAX_VALUE || costGen == Integer.MIN_VALUE) {
            costGen = DEF_COST_GEN;
        }
        ol.costGen(costGen);

        // set layout direction
        LayoutDirection direction = LayoutOptions.getEnum(parentLayout,
                LayoutDirection.class);
        OrthoDir orthoDirection;
        switch (direction) {
        case DOWN:
            orthoDirection = OrthoDir.odNorth;
            break;
        case LEFT:
            orthoDirection = OrthoDir.odWest;
            break;
        case RIGHT:
            orthoDirection = OrthoDir.odEast;
            break;
        case UP:
            orthoDirection = OrthoDir.odSouth;
            break;
        case UNDEFINED:
        default:
            orthoDirection = OrthoDir.odUndefined;
            break;
        }
        ol.preferedDir(orthoDirection);

        // set the planar layouter
        layout.setPlanarLayouter(ol);

        return layout;
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(LayoutOptions.LAYOUT_DIRECTION)) {
            return DEF_DIRECTION;
        } else if (optionId.equals(OPT_PAGE_RATIO)) {
            return DEF_PAGE_RATIO;
        } else if (optionId.equals(OPT_PRE_CLIQUES)) {
            return DEF_PRE_CLIQUES;
        } else if (optionId.equals(OPT_MIN_CLIQUE_SIZE)) {
            return DEF_MIN_CLIQUE_SIZE;
        } else if (optionId.equals(OPT_RUNS)) {
            return DEF_RUNS;
        } else if (optionId.equals(OPT_SEPARATION)) {
            return DEF_SEPARATION;
        } else if (optionId.equals(OPT_OVERHANG)) {
            return DEF_OVERHANG;
        } else if (optionId.equals(OPT_MARGIN)) {
            return DEF_MARGIN;
        } else if (optionId.equals(OPT_COST_ASSOC)) {
            return DEF_COST_ASSOC;
        } else if (optionId.equals(OPT_COST_GEN)) {
            return DEF_COST_GEN;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_EDGE_DISTANCE;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
}
