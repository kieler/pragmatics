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
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The Sugiyama layouter from the OGDF library.
 * 
 * @author mri
 */
public class SugiyamaLayouter extends OgdfLayouter {

    /** default value for minimum spacing. */
    private static final float DEF_MIN_SPACING = 16.0f;

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * {@inheritDoc}
     */
    public void prepareLayouter(final KNode layoutNode) {
        
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        
        // get the minimum spacing and layer distance
        float minSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (minSpacing < 0) {
            minSpacing = DEF_MIN_SPACING;
        }
        
        Ogdf.createSugiyamaLayouter(minSpacing, minSpacing);

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
        defaultsHolder.setProperty(LayoutOptions.OBJ_SPACING, DEF_MIN_SPACING);
    }
}
