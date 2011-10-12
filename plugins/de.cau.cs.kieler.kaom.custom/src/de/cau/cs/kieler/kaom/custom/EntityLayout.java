/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.custom;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A layout manager for entities.
 * 
 * @author msp
 */
public class EntityLayout extends AbstractHintLayout {

    /** the minimal width for entities. */
    public static final int MIN_WIDTH = 1;
    /** the minimal height for entities. */
    public static final int MIN_HEIGHT = 1;
    
    /**
     * user given min height.
     */
    private int fixedMinHeight = -1;
    
    /**
     * user given min width.
     */
    private int fixedMinWidth = -1;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension calculatePreferredSize(final IFigure container, final int wHint,
            final int hHint) {
        Insets insets = container.getInsets();
        if (!container.isVisible()) {
            return new Dimension(insets.getWidth(), insets.getHeight());
        }
        
        List<?> children = container.getChildren();
        Dimension prefSize = calculateChildrenSize(children, wHint, hHint, true);
        // Do a second pass, if necessary
        if (wHint >= 0 && prefSize.width > wHint) {
            prefSize = calculateChildrenSize(children, prefSize.width, hHint, true);
        } else if (hHint >= 0 && prefSize.width > hHint) {
            prefSize = calculateChildrenSize(children, wHint, prefSize.width, true);
        }

        return prefSize.expand(insets.getWidth(), insets.getHeight())
                .union(getBorderPreferredSize(container));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension calculateMinimumSize(final IFigure container, final int wHint, final int hHint) {
        if (fixedMinHeight < 0 || fixedMinWidth < 0) {
            return new Dimension(MIN_WIDTH, MIN_HEIGHT);
        } else {
            return new Dimension(fixedMinWidth, fixedMinHeight);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    public void layout(final IFigure parent) {
        if (!parent.isVisible()) {
            return;
        }
        
        List<?> children = parent.getChildren();
        int numChildren = children.size();
        Rectangle clientArea = parent.getClientArea();
        int x = clientArea.x;
        int y = clientArea.y;
        int availableWidth = clientArea.width;
        int availableHeight = clientArea.height;

        if (numChildren == 1) {
            IFigure child = (IFigure) children.get(0);
            child.setBounds(new Rectangle(x, y, availableWidth, availableHeight));
        } else if (numChildren > 1) {
            Dimension[] prefSizes = new Dimension[numChildren];
            Dimension[] minSizes = new Dimension[numChildren];
            Dimension[] maxSizes = new Dimension[numChildren];
    
            /*
             * Calculate sum of preferred heights of all children(totalHeight). Calculate sum of minimum
             * heights of all children(minHeight). Cache Preferred Sizes and Minimum Sizes of all
             * children.
             * 
             * totalHeight is the sum of the preferred heights of all children totalMinHeight is the sum
             * of the minimum heights of all children prefMinSumHeight is the sum of the difference
             * between all children's preferred heights and minimum heights. (This is used as a ratio to
             * calculate how much each child will shrink).
             */
            IFigure child;
            int totalHeight = 0;
            int totalMinHeight = 0;
            double totalMaxHeight = 0;
    
            for (int i = 0; i < numChildren; i++) {
                child = (IFigure) children.get(i);
    
                prefSizes[i] = child.getPreferredSize(availableWidth, availableHeight);
                minSizes[i] = child.getMinimumSize(availableWidth, availableHeight);
                maxSizes[i] = child.getMaximumSize();
    
                totalHeight += prefSizes[i].height;
                totalMinHeight += minSizes[i].height;
                totalMaxHeight += maxSizes[i].height;
            }
    
            /*
             * The total amount that the children must be shrunk is the sum of the preferred Heights of
             * the children minus Max(the available area and the sum of the minimum heights of the
             * children).
             * 
             * amntShrinkCurrentHeight is the amount each child will shrink respectively
             */
    
            for (int i = 0; i < numChildren; i++) {
                int amntShrinkCurrentHeight = 0;
                int prefHeight = prefSizes[i].height;
                int maxHeight = maxSizes[i].height;
                int prefWidth = prefSizes[i].width;
                int minWidth = minSizes[i].width;
                int maxWidth = maxSizes[i].width;
                Rectangle newBounds = new Rectangle(x, y, prefWidth, prefHeight);
    
                child = (IFigure) children.get(i);
    
                int width = Math.min(prefWidth, maxWidth);
                width = Math.max(minWidth, Math.min(clientArea.width, width));
                newBounds.width = width;
    
                int adjust = clientArea.width - width;
                newBounds.x += adjust;
                if (newBounds.height - amntShrinkCurrentHeight > maxHeight) {
                        amntShrinkCurrentHeight = newBounds.height - maxHeight;
                }
                newBounds.height -= amntShrinkCurrentHeight;
                child.setBounds(newBounds);
                totalHeight -= prefHeight;
                y += newBounds.height;
            }
        }
    }

    /**
     * Calculates either the preferred or minimum children size.
     */
    private Dimension calculateChildrenSize(final List<?> children, final int wHint,
            final int hHint, final boolean preferred) {
        Dimension childSize;
        IFigure child;
        int height = 0, width = 0;
        for (int i = 0; i < children.size(); i++) {
            child = (IFigure) children.get(i);
            childSize = preferred ? child.getPreferredSize(wHint, hHint) : child
                    .getMinimumSize(wHint, hHint);
            height += childSize.height;
            width = Math.max(width, childSize.width);
        }
        return new Dimension(width, height);
    }

    /**
     * Sets a different minimum size than the default.
     * @param width new min width
     * @param height new min height
     */
    public void setFixedMinSize(final int width, final int height) {
        this.fixedMinHeight = height;
        this.fixedMinWidth = width;
    }
    
}
