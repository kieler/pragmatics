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
package de.cau.cs.kieler.kiml.util;

import java.util.List;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.alg.BoxPlacer;
import de.cau.cs.kieler.kiml.util.alg.BoxSorter;

/**
 * A layout algorithm that does not take edges into account, but treats all
 * nodes as isolated boxes.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class BoxLayoutProvider extends AbstractLayoutProvider {
    
    /** the layout provider id. */
    public static final String ID = "de.cau.cs.kieler.kiml.layouter.boxLayout";

    /** default value for spacing between boxes. */
    private static final float DEFAULT_SPACING = 15.0f;

    /** the algorithm used to sort boxes. */
    private BoxSorter boxSorter = new BoxSorter();
    /** the algorithm used to place boxes. */
    private BoxPlacer boxPlacer = new BoxPlacer();

    /**
     * Initialize default options of the layout provider.
     */
    public BoxLayoutProvider() {
        setProperty(LayoutOptions.OBJ_SPACING, DEFAULT_SPACING);
        setProperty(LayoutOptions.BORDER_SPACING, DEFAULT_SPACING);
        setProperty(LayoutOptions.PRIORITY, 0);
        setProperty(LayoutOptions.EXPAND_NODES, false);
        setProperty(LayoutOptions.ASPECT_RATIO, BoxPlacer.DEF_ASPECT_RATIO);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Box layout", 2);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // set option for minimal spacing
        float objSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (objSpacing < 0) {
            objSpacing = DEFAULT_SPACING;
        }
        // set option for border spacing
        float borderSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEFAULT_SPACING;
        }
        // set expand nodes option
        boolean expandNodes = parentLayout.getProperty(LayoutOptions.EXPAND_NODES);

        // sort boxes according to priority and size
        boxSorter.reset(progressMonitor.subTask(1));
        List<KNode> sortedBoxes = boxSorter.sort(layoutNode);
        // place boxes on the plane
        boxPlacer.reset(progressMonitor.subTask(1));
        boxPlacer.placeBoxes(sortedBoxes, layoutNode, objSpacing, borderSpacing, expandNodes);

        progressMonitor.done();
    }

}
