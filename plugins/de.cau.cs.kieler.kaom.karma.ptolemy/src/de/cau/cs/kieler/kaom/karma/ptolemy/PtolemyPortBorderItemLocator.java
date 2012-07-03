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

package de.cau.cs.kieler.kaom.karma.ptolemy;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

import de.cau.cs.kieler.kaom.Port;

/**
 * BorderItemLocator for determining the correct locations of ports in a kaom ptolemy diagram.
 * 
 * @author ckru
 * 
 */
public class PtolemyPortBorderItemLocator extends BorderItemLocator {
    
    /**
     * Number of all the ports on the same side as this one to help 
     * determine the distribution of ports.
     */
    private int numberOfPorts;

    /**
     * The index of the port in the ports of this side. This helps to determine
     * the order of ports and its distribution on the side.
     */
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
        //get a valid location and set it. No user constraint support here.
        Rectangle validLocation = getValidLocation(null, borderItem);
        borderItem.setBounds(validLocation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
        // if there's no proposed location make a new location object else use that.
        Rectangle location = proposedLocation == null ? new Rectangle() : new Rectangle(
                proposedLocation);
        //if no constraint is given use preferred size
        if (location.getSize().isEmpty()) {
            location.setSize(borderItem.getPreferredSize());
        }
        //no user constraint so locate a fitting location
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
    private void locate(final Rectangle location, final IFigure borderItem) {
        Rectangle parentBorder = getParentBorder();
        Dimension offset = getBorderItemOffset();
        switch (getPreferredSideOfParent()) {
        case PositionConstants.EAST:
            location.x = parentBorder.x + parentBorder.width - offset.width - 1;
            location.y = parentBorder.y + (parentBorder.height - location.height) / 2;
            distributePortsVertical(location);
            break;
        case PositionConstants.WEST:
            //The additional offset has to be here for it to be displayed correctly.
            // SUPPRESS CHECKSTYLE NEXT Magic Number
            location.x = parentBorder.x - location.width + offset.width + 3;
            location.y = parentBorder.y + (parentBorder.height - location.height) / 2;
            distributePortsVertical(location);
            break;
        case PositionConstants.NORTH:
            location.x = parentBorder.x + (parentBorder.width - location.width) / 2;
            location.y = parentBorder.y - location.height + offset.height;
            distributePortsHorizontal(location);
            break;
        default:
            location.x = parentBorder.x + (parentBorder.width - location.width) / 2;
            location.y = parentBorder.y + parentBorder.height - offset.height;
            distributePortsHorizontal(location);
        }
    }

    //some offsets to distribute the ports on one side the way ptolemy does
    private static final int OFFSET_UP = 4;
    private static final int OFFSET_DOWN = 5;

    /**
     * Shift the port position according to the number of ports so that all ports are distributed
     * evenly on one side.
     * 
     * @param location
     *            the location without offset
     */
    private void distributePortsVertical(final Rectangle location) {
        // It is actually important to explicitly do nothing in this case
        // SUPPRESS CHECKSTYLE NEXT Empty Block
        if (numberOfPorts == 1) {
            // do nothing, if there is only one port it is already in the middle where it belongs
        } else if ((numberOfPorts % 2) == 1) {
            // It is actually important to explicitly do nothing in this case
            // SUPPRESS CHECKSTYLE NEXT Empty Block
            if (this.index == (numberOfPorts + 1) / 2) {
                // do nothing, the port in the middle of an odd number of ports is already where it
                // belongs
            } else if (this.index < numberOfPorts / 2) {
                location.y -= ((location.height / 2 + OFFSET_UP) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.y += (location.height / 2 + OFFSET_DOWN)
                        + (location.height / 2 + OFFSET_DOWN) * (index - numberOfPorts / 2);
            }
        } else {
            if (this.index < numberOfPorts / 2) {
                location.y -= ((location.height / 2 + OFFSET_UP) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.y += (location.height / 2 + OFFSET_DOWN)
                        + (location.height / 2 + OFFSET_DOWN) * (index - numberOfPorts / 2);
            }
        }
    }

    /**
     * Shift the port position according to the number of ports so that all ports are distributed
     * evenly on one side.
     * 
     * @param location
     *            the location without offset
     */
    private void distributePortsHorizontal(final Rectangle location) {
        // It is actually important to explicitly do nothing in this case
        // SUPPRESS CHECKSTYLE NEXT Empty Block
        if (numberOfPorts == 1) {
            // do nothing, if there is only one port it is already in the middle where it belongs
        } else if ((numberOfPorts % 2) == 1) {
            // It is actually important to explicitly do nothing in this case
            // SUPPRESS CHECKSTYLE NEXT Empty Block
            if (this.index == (numberOfPorts + 1) / 2) {
                // do nothing, the port in the middle of an odd number of ports is already where it
                // belongs
            } else if (this.index < numberOfPorts / 2) {
                location.x -= ((location.width / 2 + OFFSET_UP) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.x += (location.width / 2 + OFFSET_DOWN)
                        + (location.width / 2 + OFFSET_DOWN) * (index - numberOfPorts / 2);
            }
        } else {
            if (this.index < numberOfPorts / 2) {
                location.x -= ((location.width / 2 + OFFSET_UP) * ((numberOfPorts / 2) - index));
            } else if (index >= numberOfPorts / 2) {
                location.x += (location.width / 2 + OFFSET_DOWN)
                        + (location.width / 2 + OFFSET_DOWN) * (index - numberOfPorts / 2);
            }
        }
    }

}
