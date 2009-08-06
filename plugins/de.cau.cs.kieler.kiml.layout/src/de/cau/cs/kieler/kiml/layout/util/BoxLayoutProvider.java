/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.util.alg.BoxPlacer;
import de.cau.cs.kieler.kiml.layout.util.alg.BoxSorter;

/**
 * A layout algorithm that does not take edges into account, but
 * treats all nodes as isolated boxes.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BoxLayoutProvider extends AbstractLayoutProvider {

    /** default value for spacing between boxes */
    private final static float DEFAULT_SPACING = 15.0f;
    
    /** the algorithm used to sort boxes */
    private BoxSorter boxSorter = new BoxSorter();
    /** the algorithm used to place boxes */
    private BoxPlacer boxPlacer = new BoxPlacer();
    
    /* (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider#doLayout(de.cau.cs.kieler.core.kgraph.KNode, de.cau.cs.kieler.kiml.layout.services.IKielerProgressMonitor)
     */
    public void doLayout(KNode layoutNode,
            IKielerProgressMonitor progressMonitor) throws KielerException {
        progressMonitor.begin("Box layout", 20);
        // set option for minimal spacing
        float spacing = LayoutOptions.getMinSpacing(
                KimlLayoutUtil.getShapeLayout(layoutNode));
        if (Float.isNaN(spacing))
            spacing = DEFAULT_SPACING;
        
        // sort boxes according to priority and size
        boxSorter.reset(progressMonitor.subTask(10));
        List<KNode> sortedBoxes = boxSorter.sort(layoutNode);
        // place boxes on the plane
        boxPlacer.reset(progressMonitor.subTask(10));
        boxPlacer.placeBoxes(sortedBoxes, layoutNode, spacing);
        
        progressMonitor.done();
    }
    
}
