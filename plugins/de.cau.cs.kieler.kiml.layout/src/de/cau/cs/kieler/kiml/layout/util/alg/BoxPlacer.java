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
package de.cau.cs.kieler.kiml.layout.util.alg;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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

    /** maximal factor by which a row may be broader than the maximal row width */
    private static final float MAX_BROADEN = 1.1f;
    
    // width and height of the parent node
    private float parentWidth, parentHeight;
    
    /**
     * Place the boxes of the given sorted list according to
     * their order in the list.
     * 
     * @param sortedBoxes sorted list of boxes
     * @param parentNode parent node
     * @param spacing minimal spacing between elements
     * @param expandNodes if true, the nodes are expanded to fill their parent
     */
    public void placeBoxes(List<KNode> sortedBoxes,
            KNode parentNode, float spacing, boolean expandNodes) {
        getMonitor().begin("Box placement", 1);
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        
        // do place the boxes
        placeBoxes(sortedBoxes, spacing, LayoutOptions.getMinWidth(parentLayout),
                LayoutOptions.getMinHeight(parentLayout), expandNodes);
        
        // adjust parent size
        KInsets insets = LayoutOptions.getInsets(parentLayout);
        parentLayout.setWidth(insets.getLeft() + parentWidth + insets.getRight());
        parentLayout.setHeight(insets.getTop() + parentHeight + insets.getBottom());
        
        getMonitor().done();
    }
    
    /**
     * Place the boxes of the given sorted list according to
     * their order in the list.
     * 
     * @param sortedBoxes sorted list of boxes
     * @param minSpacing minimal spacing between elements
     * @param minTotalWidth minimal width of the parent node
     * @param minTotalHeight minimal height of the parent node
     * @param expandNodes if true, the nodes are expanded to fill their parent
     */
    private void placeBoxes(List<KNode> sortedBoxes, float minSpacing,
            float minTotalWidth, float minTotalHeight, boolean expandNodes) {
        // determine the maximal row width by the maximal box width and the total area
        float maxRowWidth = 0.0f;
        float totalArea = 0.0f;
        for (KNode box : sortedBoxes) {
            KShapeLayout boxLayout = KimlLayoutUtil.getShapeLayout(box);
            if (!LayoutOptions.isFixedSize(boxLayout))
            	KimlLayoutUtil.resizeNode(box);
            maxRowWidth = Math.max(maxRowWidth, boxLayout.getWidth());
            totalArea += boxLayout.getWidth() * boxLayout.getHeight();
        }
        maxRowWidth = Math.max(maxRowWidth, (float)Math.sqrt(totalArea)) * MAX_BROADEN;
        
        // place nodes iteratively into rows
        float xpos = minSpacing, ypos = minSpacing,
            highestBox = 0.0f, broadestRow = 2 * minSpacing;
        LinkedList<Integer> rowIndices = new LinkedList<Integer>();
        rowIndices.add(Integer.valueOf(0));
        LinkedList<Float> rowHeights = new LinkedList<Float>();
        ListIterator<KNode> boxIter = sortedBoxes.listIterator();
        while (boxIter.hasNext()) {
            KShapeLayout boxLayout = KimlLayoutUtil.getShapeLayout(boxIter.next());
            float width = boxLayout.getWidth();
            float height = boxLayout.getHeight();
            if (xpos + width > maxRowWidth) {
                // place box into the next row
                if (expandNodes) {
                    rowHeights.addLast(Float.valueOf(highestBox));
                    rowIndices.addLast(Integer.valueOf(boxIter.previousIndex()));
                }
                xpos = minSpacing;
                ypos += highestBox + minSpacing;
                highestBox = 0.0f;
                broadestRow = Math.max(broadestRow, 2 * minSpacing + width);
            }
            boxLayout.setXpos(xpos);
            boxLayout.setYpos(ypos);
            xpos += width + minSpacing;
            broadestRow = Math.max(broadestRow, xpos);
            highestBox = Math.max(highestBox, height);
        }
        broadestRow = Math.max(broadestRow, minTotalWidth);
        float totalHeight = ypos + highestBox + minSpacing;
        if (totalHeight < minTotalHeight) {
            highestBox += minTotalHeight - totalHeight;
            totalHeight = minTotalHeight;
        }
        
        // expand nodes if required
        if (expandNodes) {
            xpos = minSpacing;
            boxIter = sortedBoxes.listIterator();
            rowIndices.addLast(Integer.valueOf(sortedBoxes.size()));
            ListIterator<Integer> rowIndexIter = rowIndices.listIterator();
            int nextRowIndex = rowIndexIter.next();
            rowHeights.addLast(Float.valueOf(highestBox));
            ListIterator<Float> rowHeightIter = rowHeights.listIterator();
            float rowHeight = 0.0f;
            while (boxIter.hasNext()) {
                if (boxIter.nextIndex() == nextRowIndex) {
                    xpos = minSpacing;
                    rowHeight = rowHeightIter.next();
                    nextRowIndex = rowIndexIter.next();
                }
                KShapeLayout boxLayout = KimlLayoutUtil.getShapeLayout(boxIter.next());
                boxLayout.setHeight(rowHeight);
                if (boxIter.nextIndex() == nextRowIndex)
                    boxLayout.setWidth(broadestRow - xpos - minSpacing);
                xpos += boxLayout.getWidth() + minSpacing;
            }
        }
        
        // set parent size
        parentWidth = broadestRow;
        parentHeight = totalHeight;
    }
    
}
