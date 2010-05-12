package de.cau.cs.kieler.graphs.custom;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

/**
 * @author msp
 */
public class NodeLayout extends AbstractHintLayout {

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
            int xpos = (area.width - labelSize.width) / 2;
            label.setBounds(new Rectangle(area.x + xpos, area.y, labelSize.width, labelSize.height));
        } else {
            labelSize = new Dimension();
        }
        if (compartment != null) {
            compartment.setBounds(new Rectangle(area.x + 1, area.y + labelSize.height + 1,
                    area.width - 2, area.height - labelSize.height - 2));
        }
    }

    private static final int MIN_WIDTH = 20;
    private static final int MIN_HEIGHT = 20;

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
        Dimension size = new Dimension(MIN_WIDTH, MIN_HEIGHT);
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
