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
package de.cau.cs.kieler.kiml.layout.util;

import java.util.List;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.alg.BoxPlacer;
import de.cau.cs.kieler.kiml.layout.util.alg.BoxSorter;

/**
 * A layout algorithm that does not take edges into account, but treats all
 * nodes as isolated boxes.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class BoxLayoutProvider extends AbstractLayoutProvider {

    /** default value for spacing between boxes. */
    private static final float DEFAULT_SPACING = 15.0f;

    /** the algorithm used to sort boxes. */
    private BoxSorter boxSorter = new BoxSorter();
    /** the algorithm used to place boxes. */
    private BoxPlacer boxPlacer = new BoxPlacer();

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Box layout", 2);
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        // set option for minimal spacing
        float objSpacing = LayoutOptions.getMinSpacing(parentLayout);
        if (Float.isNaN(objSpacing)) {
            objSpacing = DEFAULT_SPACING;
        }
        // set option for border spacing
        float borderSpacing = LayoutOptions.getBorderSpacing(parentLayout);
        if (Float.isNaN(borderSpacing)) {
            borderSpacing = DEFAULT_SPACING;
        }
        // set expand nodes option
        boolean expandNodes = LayoutOptions.isExpandNodes(parentLayout);

        // sort boxes according to priority and size
        boxSorter.reset(progressMonitor.subTask(1));
        List<KNode> sortedBoxes = boxSorter.sort(layoutNode);
        // place boxes on the plane
        boxPlacer.reset(progressMonitor.subTask(1));
        boxPlacer.placeBoxes(sortedBoxes, layoutNode, objSpacing, borderSpacing, expandNodes);

        progressMonitor.done();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (LayoutOptions.MIN_SPACING.equals(optionId)) {
            return DEFAULT_SPACING;
        } else if (LayoutOptions.BORDER_SPACING.equals(optionId)) {
            return DEFAULT_SPACING;
        } else if (LayoutOptions.PRIORITY.equals(optionId)) {
            return 0;
        } else if (LayoutOptions.EXPAND_NODES.equals(optionId)) {
            return false;
        } else {
            return null;
        }
    }

}
