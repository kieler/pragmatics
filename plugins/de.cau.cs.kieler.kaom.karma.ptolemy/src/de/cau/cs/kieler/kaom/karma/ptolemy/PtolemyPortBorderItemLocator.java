package de.cau.cs.kieler.kaom.karma.ptolemy;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

import de.cau.cs.kieler.kaom.Port;

public class PtolemyPortBorderItemLocator extends BorderItemLocator {

    private int numberOfPorts;

    private int index;

    /**
     * Creates a label locator.
     * 
     * @param parent
     *            the parent figure
     * @param side
     *            the side
     * @param portsOfSide
     *            a list of the ports of the same side
     * @param thisPort
     *            the port that uses this locator
     */
    public PtolemyPortBorderItemLocator(final IFigure parent, final int side,
            final List<Port> portsOfSide, final Port thisPort) {
        super(parent, side);
        numberOfPorts = portsOfSide.size();
        index = portsOfSide.indexOf(thisPort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void relocate(final IFigure borderItem) {
        Rectangle validLocation = getValidLocation(null, borderItem);
        borderItem.setBounds(validLocation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
        Rectangle location = proposedLocation == null ? new Rectangle() : new Rectangle(
                proposedLocation);
        if (location.getSize().isEmpty()) {
            location.setSize(borderItem.getPreferredSize());
        }
        if (location.x == 0 && location.y == 0) {
            locate(location, borderItem);
        } else {
            int side = findClosestSideOfParent(proposedLocation, getParentBorder());
            Point newTopLeft = locateOnBorder(location.getTopLeft(), side, 0, borderItem);
            location.setLocation(newTopLeft);
        }
        return location;
    }

    /**
     * Find a suitable location for the given border item.
     * 
     * @param location
     *            the rectangle where the new location is written to
     * @param borderItem
     *            a border item
     */
    private void locate(final Rectangle location, IFigure borderItem) {
        Rectangle parentBorder = getParentBorder();
        Dimension offset = getBorderItemOffset();
        switch (getPreferredSideOfParent()) {
        case PositionConstants.EAST:
            location.x = parentBorder.x + parentBorder.width - offset.width - 1;
            location.y = parentBorder.y + (parentBorder.height - location.height) / 2;
            distributePortsVerticalOffset(location);
            break;
        case PositionConstants.WEST:
            location.x = parentBorder.x - location.width + offset.width + 3;
            location.y = parentBorder.y + (parentBorder.height - location.height) / 2;
            distributePortsVerticalOffset(location);
            break;
        case PositionConstants.NORTH:
            location.x = parentBorder.x + (parentBorder.width - location.width) / 2;
            location.y = parentBorder.y - location.height + offset.height;
            distributePortsHorizontalOffset(location);
            break;
        default:
            location.x = parentBorder.x + (parentBorder.width - location.width) / 2;
            location.y = parentBorder.y + parentBorder.height - offset.height;
            distributePortsHorizontalOffset(location);
        }

        /*
         * IFigure figure = this.getConflictingBorderItemFigure(location.getTopLeft(), borderItem);
         * if (figure != null) { //figure.getBounds().y += 3; location.y +=10; }
         */
    }
    
    private void distributePortsVerticalOffset(Rectangle location) {
        if (numberOfPorts == 1) {
        } else if ((numberOfPorts % 2) == 1) {
            if (this.index == (numberOfPorts + 1) / 2) {

            } else if (this.index < numberOfPorts / 2) {
                location.y -= ((location.height / 2 + 4) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.y += (location.height / 2 + 5) + (location.height / 2 + 5)
                        * (index - numberOfPorts / 2);
            }
        } else {

        if (this.index < numberOfPorts / 2) {
            location.y -= ((location.height / 2 + 4) * ((numberOfPorts / 2) - index));
        } else if (index >= numberOfPorts / 2) {
            location.y += (location.height / 2 + 5) + (location.height / 2 + 5)
                    * (index - numberOfPorts / 2);
        }
        }
    }
    
    private void distributePortsHorizontalOffset(Rectangle location) {
        if (numberOfPorts == 1) {
        } else if ((numberOfPorts % 2) == 1) {
            if (this.index == (numberOfPorts + 1) / 2) {

            } else if (this.index < numberOfPorts / 2) {
                location.x -= ((location.width / 2 + 4) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.x += (location.width / 2 + 5) + (location.width / 2 + 5)
                        * (index - numberOfPorts / 2);
            }
        } else {

            if (this.index < numberOfPorts / 2) {
                location.x -= ((location.width / 2 + 4) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.x += (location.width / 2 + 5) + (location.width / 2 + 5)
                        * (index - numberOfPorts / 2);
            }
        }
    }

}
