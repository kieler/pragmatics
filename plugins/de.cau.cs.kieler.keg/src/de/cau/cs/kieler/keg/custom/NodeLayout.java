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
package de.cau.cs.kieler.keg.custom;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

/**
 * A layout for KEG nodes.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class NodeLayout extends AbstractHintLayout {

    /** the default node width. */
    private static final int DEFAULT_NODE_WIDTH = 40;
    /** the default node height. */
    private static final int DEFAULT_NODE_HEIGHT = 40;

    /** the label height cashed from of the last layout. */
    private int lastLabelHeight = 0;

    /**
     * {@inheritDoc}
     */
    public void layout(final IFigure container) {
        WrappingLabel label = null;
        ResizableCompartmentFigure compartment = null;
        for (Object child : container.getChildren()) {
            if (child instanceof WrappingLabel) {
                label = (WrappingLabel) child;
            } else if (child instanceof ResizableCompartmentFigure) {
                compartment = (ResizableCompartmentFigure) child;
            }
        }
        Rectangle area = container.getClientArea();
        Dimension labelSize;
        if (label != null) {
            labelSize = label.getPreferredSize();
            // this prevents the input text for the label to assume a label height of 0
            int labelHeight = labelSize.height == 0 ? lastLabelHeight : labelSize.height;
            lastLabelHeight = labelSize.height;
            int xpos = (area.width - labelSize.width) / 2;
            int ypos = (Math.min(area.height, DEFAULT_NODE_HEIGHT) - labelHeight) / 2;
            label.setBounds(new Rectangle(area.x + xpos, area.y + ypos, labelSize.width,
                    labelHeight));
        } else {
            labelSize = new Dimension();
        }
        if (compartment != null) {
            compartment.setBounds(new Rectangle(area.x + DEFAULT_NODE_WIDTH / 2, area.y
                    + DEFAULT_NODE_HEIGHT / 2 + labelSize.height / 2 + 1, area.width
                    - DEFAULT_NODE_WIDTH, Math.max(0, area.height - DEFAULT_NODE_HEIGHT
                    - labelSize.height / 2)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension calculatePreferredSize(final IFigure container, final int wHint,
            final int hHint) {
        Insets insets = container.getInsets();
        int wInset = insets.left + insets.right;
        int hInset = insets.top + insets.bottom;
        WrappingLabel label = null;
        ResizableCompartmentFigure compartment = null;
        for (Object child : container.getChildren()) {
            if (child instanceof WrappingLabel) {
                label = (WrappingLabel) child;
            } else if (child instanceof ResizableCompartmentFigure) {
                compartment = (ResizableCompartmentFigure) child;
            }
        }
        Dimension size = new Dimension(DEFAULT_NODE_WIDTH, DEFAULT_NODE_HEIGHT);
        Dimension labelSize;
        if (label != null) {
            labelSize = label.getPreferredSize();
            if (labelSize.width + wInset > size.width) {
                size.width = labelSize.width + wInset;
            }
            if (labelSize.height + hInset > size.height) {
                size.height = labelSize.height + hInset;
            }
        } else {
            labelSize = new Dimension();
        }
        if (compartment != null) {
            Dimension compartmentSize = compartment.getMinimumSize();
            int minWidth = compartmentSize.width + 2;
            if (minWidth + wInset > size.width) {
                size.width = minWidth + wInset;
            }
            int minHeight = labelSize.height + compartmentSize.height + 2;
            if (minHeight + hInset > size.height) {
                size.height = minHeight + hInset;
            }
        }
        return size;
    }

}
