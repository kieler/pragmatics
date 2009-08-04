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
package de.cau.cs.kieler.kiml.layout.util.alg;

import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * Placing algorithm for boxes. Edges of the graph are not considered.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BoxPlacer extends AbstractAlgorithm {

    /** maximal factor by which the layout may be broader than the broadest box */
    private static final float MAX_BROADEN = 1.2f;
    
    // width and height of the parent node
    private float parentWidth, parentHeight;
    
    /**
     * Place the boxes of the given sorted list according to
     * their order in the list.
     * 
     * @param sortedBoxes sorted list of boxes
     * @param parentNode parent node
     * @param spacing minimal spacing between elements
     */
    public void placeBoxes(List<KNode> sortedBoxes,
            KNode parentNode, float spacing) {
        getMonitor().begin("Box placement", 1);
        
        // do place the boxes
        placeBoxes(sortedBoxes, spacing);
        
        // adjust parent size
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        KInsets insets = LayoutOptions.getInsets(parentLayout);
        parentLayout.setWidth(insets.getLeft() + parentWidth + insets.getRight());
        parentLayout.setHeight(insets.getTop() + parentHeight + insets.getBottom());
        LayoutOptions.setFixedSize(parentLayout, true);
        
        getMonitor().done();
    }
    
    /**
     * Place the boxes of the given sorted list according to
     * their order in the list.
     * 
     * @param sortedBoxes sorted list of boxes
     * @param minSpacing minimal spacing between elements
     */
    private void placeBoxes(List<KNode> sortedBoxes, float minSpacing) {
        parentWidth = 0.0f;
        // determine the maximal width
        float maxWidth = 0.0f;
        for (KNode box : sortedBoxes) {
            KShapeLayout boxLayout = KimlLayoutUtil.getShapeLayout(box);
            if (!LayoutOptions.isFixedSize(boxLayout))
            	KimlLayoutUtil.resizeNode(box);
            if (boxLayout.getWidth() > maxWidth)
                maxWidth = boxLayout.getWidth();
        }
        
        // place nodes iteratively
        // TODO make this placement more intelligent
        float xpos = minSpacing, ypos = minSpacing, nextYpos = ypos;
        for (KNode box : sortedBoxes) {
            KShapeLayout boxLayout = KimlLayoutUtil.getShapeLayout(box);
            float width = boxLayout.getWidth();
            float height = boxLayout.getHeight();
            if (xpos + width > maxWidth * MAX_BROADEN) {
                // place box into the next row
                xpos = minSpacing;
                ypos = nextYpos + minSpacing;
            }
            boxLayout.setXpos(xpos);
            boxLayout.setYpos(ypos);
            xpos += width + minSpacing;
            nextYpos = Math.max(nextYpos, ypos + height);
            parentWidth = Math.max(parentWidth, xpos);
        }
        parentHeight = nextYpos + minSpacing;
    }
    
}
