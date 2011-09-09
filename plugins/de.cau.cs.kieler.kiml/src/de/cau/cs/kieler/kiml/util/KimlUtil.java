/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;

/**
 * Utility methods for KGraphs and layout data.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class KimlUtil {

    /**
     * Hidden constructor to avoid instantiation.
     */
    private KimlUtil() {
    }

    /**
     * Comparator class used to sort ports according to their ranks.
     */
    public static class PortComparator implements Comparator<KPort>, Serializable {
        /** the serial version UID. */
        private static final long serialVersionUID = 7489650936528433087L;
        /** indicates whether to treat ranks in forward direction. */
        private boolean forward;
        /** horizontal or vertical layout direction. */
        private Direction layoutDirection;

        /**
         * Creates a port comparator for the given setting.
         * 
         * @param theforward indicates whether to treat ranks in forward
         *            direction
         * @param thelayoutDirection horizontal or vertical layout direction
         */
        public PortComparator(final boolean theforward, final Direction thelayoutDirection) {
            this.forward = theforward;
            this.layoutDirection = thelayoutDirection;
        }

        /**
         * {@inheritDoc}
         */
        public int compare(final KPort port1, final KPort port2) {
            KShapeLayout layout1 = port1.getData(KShapeLayout.class);
            KShapeLayout layout2 = port2.getData(KShapeLayout.class);
            int rank1 = layout1.getProperty(LayoutOptions.PORT_RANK);
            int rank2 = layout2.getProperty(LayoutOptions.PORT_RANK);
            PortSide side1 = layout1.getProperty(LayoutOptions.PORT_SIDE);
            PortSide side2 = layout2.getProperty(LayoutOptions.PORT_SIDE);
            if (side1 == side2) {
                return layoutDirection == Direction.DOWN && !forward
                        || layoutDirection == Direction.RIGHT && forward ? rank1 - rank2
                        : rank2 - rank1;
            } else if (layoutDirection == Direction.DOWN) {
                if (forward) {
                    return side1 == PortSide.NORTH || side1 == PortSide.EAST
                            && (side2 == PortSide.SOUTH || side2 == PortSide.WEST)
                            || side1 == PortSide.SOUTH && side2 == PortSide.WEST ? 1 : -1;
                } else {
                    return side1 == PortSide.SOUTH || side1 == PortSide.EAST
                            && (side2 == PortSide.NORTH || side2 == PortSide.WEST)
                            || side1 == PortSide.NORTH && side2 == PortSide.WEST ? 1 : -1;
                }
            } else {
                if (forward) {
                    return side1 == PortSide.WEST || side1 == PortSide.SOUTH
                            && (side2 == PortSide.EAST || side2 == PortSide.NORTH)
                            || side1 == PortSide.EAST && side2 == PortSide.NORTH ? 1 : -1;
                } else {
                    return side1 == PortSide.EAST || side1 == PortSide.SOUTH
                            && (side2 == PortSide.WEST || side2 == PortSide.NORTH)
                            || side1 == PortSide.WEST && side2 == PortSide.NORTH ? 1 : -1;
                }
            }
        }
    }

    /**
     * Creates a KNode, initializes some attributes, and returns it.
     * 
     * @return an initialized KNode
     */
    public static KNode createInitializedNode() {
        KNode layoutNode = KGraphFactory.eINSTANCE.createKNode();
        KLabel nodeLabel = createInitializedLabel(layoutNode);
        layoutNode.setLabel(nodeLabel);
        KShapeLayout layout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
        layout.setInsets(KLayoutDataFactory.eINSTANCE.createKInsets());
        layoutNode.getData().add(layout);
        return layoutNode;
    }

    /**
     * Creates a KEdge, initializes some attributes, and returns it.
     * 
     * @return an initialized KEdge
     */
    public static KEdge createInitializedEdge() {
        KEdge edge = KGraphFactory.eINSTANCE.createKEdge();
        KEdgeLayout edgeLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
        edgeLayout.setSourcePoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        edgeLayout.setTargetPoint(KLayoutDataFactory.eINSTANCE.createKPoint());
        edge.getData().add(edgeLayout);
        return edge;
    }

    /**
     * Creates a KPort, initializes some attributes, and returns it.
     * 
     * @return an initialized KPort
     */
    public static KPort createInitializedPort() {
        KPort port = KGraphFactory.eINSTANCE.createKPort();
        KLabel portLabel = createInitializedLabel(port);
        port.setLabel(portLabel);
        KShapeLayout labelLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
        port.getData().add(labelLayout);
        return port;
    }

    /**
     * Creates a KLabel, initializes some attributes, and returns it.
     * 
     * @param parent the parent graph element
     * @return an initialized KLabel
     */
    public static KLabel createInitializedLabel(final KGraphElement parent) {
        KLabel label = KGraphFactory.eINSTANCE.createKLabel();
        KShapeLayout labelLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
        label.getData().add(labelLayout);
        label.setText("");
        label.setParent(parent);
        return label;
    }
    
    /**
     * Create a unique identifier for the given graph element.
     * 
     * @param element a graph element
     */
    public static void createIdentifier(final KGraphElement element) {
        KIdentifier identifier = element.getData(KIdentifier.class);
        if (identifier == null) {
            identifier = KLayoutDataFactory.eINSTANCE.createKIdentifier();
            element.getData().add(identifier);
        }
        identifier.setId(Integer.toString(element.hashCode()));
    }

    /**
     * Determines the port side for the given port from its relative position at
     * its corresponding node.
     * 
     * @param port port to analyze
     * @param direction the overall layout direction
     * @return port placement
     */
    public static PortSide calcPortSide(final KPort port, final Direction direction) {
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        
        // check direction-dependent criterion
        KShapeLayout nodeLayout = port.getNode().getData(KShapeLayout.class);
        float xpos = portLayout.getXpos(), ypos = portLayout.getYpos();
        float nodeWidth = nodeLayout.getWidth(), nodeHeight = nodeLayout.getHeight();
        switch (direction) {
        case LEFT:
        case RIGHT:
            if (xpos < 0) {
                return PortSide.WEST;
            } else if (xpos + portLayout.getWidth() > nodeWidth) {
                return PortSide.EAST;
            }
            break;
        case UP:
        case DOWN:
            if (ypos < 0) {
                return PortSide.NORTH;
            } else if (ypos + portLayout.getHeight() > nodeHeight) {
                return PortSide.SOUTH;
            }
        }
        
        // check general criterion
        float widthPercent = (xpos + portLayout.getWidth() / 2) / nodeWidth;
        float heightPercent = (ypos + portLayout.getHeight() / 2) / nodeHeight;
        if (widthPercent + heightPercent <= 1
                && widthPercent - heightPercent <= 0) {
            // port is on the left
            return PortSide.WEST;
        } else if (widthPercent + heightPercent >= 1
                && widthPercent - heightPercent >= 0) {
            // port is on the right
            return PortSide.EAST;
        } else if (heightPercent < 1.0f / 2) {
            // port is on the top
            return PortSide.NORTH;
        } else {
            // port is on the bottom
            return PortSide.SOUTH;
        }
    }
    
    /**
     * Calculate the offset for a port, that is the amount by which it is moved outside of the node.
     * An offset value of 0 means the port has no intersection with the node and touches the outside
     * border of the node.
     * 
     * @param port a port
     * @param side the side on the node for the given port
     * @return the offset on the side
     */
    public static float calcPortOffset(final KPort port, final PortSide side) {
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        KShapeLayout nodeLayout = port.getNode().getData(KShapeLayout.class);
        switch (side) {
        case NORTH:
            return -(portLayout.getYpos() + portLayout.getHeight());
        case EAST:
            return portLayout.getXpos() - nodeLayout.getWidth();
        case SOUTH:
            return portLayout.getYpos() - nodeLayout.getHeight();
        case WEST:
            return -(portLayout.getXpos() + portLayout.getWidth());
        }
        return 0;
    }
    
    /**
     * Returns a sorted list of the ports of the given node.
     * 
     * @param node a node
     * @return sorted list of ports
     */
    public static KPort[] getSortedPorts(final KNode node) {
        KPort[] ports = node.getPorts().toArray(new KPort[node.getPorts().size()]);
        Arrays.sort(ports, new Comparator<KPort>() {
            public int compare(final KPort port1, final KPort port2) {
                KShapeLayout port1Layout = port1.getData(KShapeLayout.class);
                PortSide port1Side = port1Layout.getProperty(LayoutOptions.PORT_SIDE);
                int port1Rank = port1Layout.getProperty(LayoutOptions.PORT_RANK);
                KShapeLayout port2Layout = port2.getData(KShapeLayout.class);
                PortSide port2Side = port2Layout.getProperty(LayoutOptions.PORT_SIDE);
                int port2Rank = port2Layout.getProperty(LayoutOptions.PORT_RANK);
                int result = 0;
                switch (port1Side) {
                case NORTH:
                    if (port2Side == PortSide.NORTH) {
                        result = Float.compare(port1Layout.getXpos(), port2Layout.getXpos());
                        if (result == 0) {
                            result = port1Rank > port2Rank ? 1 : (port1Rank < port2Rank ? -1 : 0);
                        }
                    } else {
                        result = -1;
                    }
                    break;
                case EAST:
                    if (port2Side == PortSide.NORTH) {
                        result = 1;
                    } else if (port2Side == PortSide.EAST) {
                        result = Float.compare(port1Layout.getYpos(), port2Layout.getYpos());
                        if (result == 0) {
                            result = port1Rank > port2Rank ? 1 : (port1Rank < port2Rank ? -1 : 0);
                        }
                    } else {
                        result = -1;
                    }
                    break;
                case SOUTH:
                    if (port2Side == PortSide.NORTH || port2Side == PortSide.EAST) {
                        result = 1;
                    } else if (port2Side == PortSide.SOUTH) {
                        result = Float.compare(port2Layout.getXpos(), port1Layout.getXpos());
                        if (result == 0) {
                            result = port1Rank > port2Rank ? 1 : (port1Rank < port2Rank ? -1 : 0);
                        }
                    } else {
                        result = -1;
                    }
                    break;
                case WEST:
                    if (port2Side == PortSide.NORTH || port2Side == PortSide.EAST
                            || port2Side == PortSide.SOUTH) {
                        result = 1;
                    } else if (port2Side == PortSide.WEST) {
                        result = Float.compare(port2Layout.getYpos(), port1Layout.getYpos());
                        if (result == 0) {
                            result = port1Rank > port2Rank ? 1 : (port1Rank < port2Rank ? -1 : 0);
                        }
                    } else {
                        result = -1;
                    }
                    break;
                }
                return result;
            }
        });
        
        return ports;
    }

    /**
     * Sets port ranks for all ports of the given node according to their
     * relative positions.
     * 
     * @param node node for which port ranks shall be set
     */
    public static void calcPortRanks(final KNode node) {
        // sort the ports according to their positions
        KPort[] ports = getSortedPorts(node);
        // assign ranks according to the new order
        for (int i = 0; i < ports.length; i++) {
            ports[i].getData(KShapeLayout.class).setProperty(LayoutOptions.PORT_RANK, i);
        }
    }

    /**
     * Fills all missing data for the ports of the given node, such as port
     * sides and port ranks.
     * 
     * @param node node for which port data shall be created
     * @param direction layout direction
     */
    public static void fillPortInfo(final KNode node, final Direction direction) {
        KGraphData layoutData = node.getData(KShapeLayout.class);
        PortConstraints portConstraints = layoutData.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (portConstraints == PortConstraints.FREE) {
            // set port sides according to layout direction
            switch (direction) {
            case DOWN:
                for (KPort port : node.getPorts()) {
                    port.getData(KShapeLayout.class).setProperty(
                            LayoutOptions.PORT_SIDE, calcFlow(port) < 0
                            ? PortSide.NORTH : PortSide.SOUTH);
                }
                break;
            case UP:
                for (KPort port : node.getPorts()) {
                    port.getData(KShapeLayout.class).setProperty(
                            LayoutOptions.PORT_SIDE, calcFlow(port) < 0
                            ? PortSide.SOUTH : PortSide.NORTH);
                }
                break;
            case LEFT:
                for (KPort port : node.getPorts()) {
                    port.getData(KShapeLayout.class).setProperty(
                            LayoutOptions.PORT_SIDE, calcFlow(port) < 0
                            ? PortSide.EAST : PortSide.WEST);
                }
                break;
            default:
                for (KPort port : node.getPorts()) {
                    port.getData(KShapeLayout.class).setProperty(LayoutOptions.PORT_SIDE,
                            calcFlow(port) < 0
                            ? PortSide.WEST : PortSide.EAST);
                }
                break;
            }
            layoutData.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        } else if (portConstraints != PortConstraints.UNDEFINED) {
            // set port sides and ranks according to relative position
            boolean ranksUndefined = false;
            for (KPort port : node.getPorts()) {
                KGraphData portLayout = port.getData(KShapeLayout.class);
                if (portLayout.getProperty(LayoutOptions.PORT_RANK) < 0) {
                    ranksUndefined = true;
                }
                if (portLayout.getProperty(LayoutOptions.PORT_SIDE) == PortSide.UNDEFINED) {
                    portLayout.setProperty(LayoutOptions.PORT_SIDE, calcPortSide(port, direction));
                }
            }
            if (ranksUndefined) {
                calcPortRanks(node);
            }
        }
    }

    /**
     * Determines positions of a sorted set of points by placing them with equal
     * distances.
     * 
     * @param points list of points
     * @param minPos minimal position for placing
     * @param maxPos maximal position for placing
     * @param offset offset to be added to positions
     * @param vertical if true, the vertical position is processed, else the
     *            horizontal position is processed
     * @param forward if true, ports are placed from the minimum to the maximum
     *            position
     */
    public static void placePoints(final List<KPoint> points, final float minPos,
            final float maxPos, final float offset, final boolean vertical,
            final boolean forward) {
        float dist = (maxPos - minPos) / (points.size() + 1);
        float pos;
        if (forward) {
            pos = minPos + offset;
        } else {
            pos = maxPos + offset;
            dist = -dist;
        }

        if (vertical) {
            for (KPoint point : points) {
                pos += dist;
                point.setY(pos);
            }
        } else {
            for (KPoint point : points) {
                pos += dist;
                point.setX(pos);
            }
        }
    }

    /** minimal size of a node. */
    private static final float MIN_NODE_SIZE = 16.0f;
    /** minimal distance of two ports on each side of a node. */
    private static final float MIN_PORT_DISTANCE = 8.0f;

    /**
     * Sets the size of a given node, depending on the minimal size, the number of ports
     * on each side, the insets, and the label.
     * 
     * @param node the node that shall be resized
     */
    public static void resizeNode(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        float minNorth = MIN_PORT_DISTANCE, minEast = MIN_PORT_DISTANCE,
                minSouth = MIN_PORT_DISTANCE, minWest = MIN_PORT_DISTANCE;
        Direction direction = node.getParent() == null
                ? nodeLayout.getProperty(LayoutOptions.DIRECTION)
                : node.getParent().getData(KShapeLayout.class).getProperty(LayoutOptions.DIRECTION);
        for (KPort port : node.getPorts()) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            PortSide portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE);
            if (portSide == PortSide.UNDEFINED) {
                portSide = calcPortSide(port, direction);
                portLayout.setProperty(LayoutOptions.PORT_SIDE, portSide);
            }
            if (portConstraints == PortConstraints.FIXED_POS) {
                switch (portSide) {
                case NORTH:
                    minNorth = Math.max(minNorth, portLayout.getXpos()
                            + portLayout.getWidth());
                    break;
                case EAST:
                    minEast = Math.max(minEast, portLayout.getYpos()
                            + portLayout.getHeight());
                    break;
                case SOUTH:
                    minSouth = Math.max(minSouth, portLayout.getXpos()
                            + portLayout.getWidth());
                    break;
                case WEST:
                    minWest = Math.max(minWest, portLayout.getYpos()
                            + portLayout.getHeight());
                    break;
                }
            } else {
                switch (portSide) {
                case NORTH:
                    minNorth += MIN_PORT_DISTANCE + portLayout.getWidth();
                    break;
                case EAST:
                    minEast += MIN_PORT_DISTANCE + portLayout.getHeight();
                    break;
                case SOUTH:
                    minSouth += MIN_PORT_DISTANCE + portLayout.getWidth();
                    break;
                case WEST:
                    minWest += MIN_PORT_DISTANCE + portLayout.getHeight();
                    break;
                }
            }
        }
        minNorth += MIN_PORT_DISTANCE;
        minEast += MIN_PORT_DISTANCE;
        minSouth += MIN_PORT_DISTANCE;
        minWest += MIN_PORT_DISTANCE;

        float newWidth = KielerMath.maxf(MIN_NODE_SIZE, minNorth, minSouth);
        float newHeight = KielerMath.maxf(MIN_NODE_SIZE, minEast, minWest);
        
        resizeNode(node, newWidth, newHeight, true);
    }
    
    /**
     * Resize a node to the given width and height, adjusting port and label positions if needed.
     * 
     * @param node a node
     * @param newWidth the new width to set
     * @param newHeight the new height to set
     * @param movePorts whether port positions shall be adjusted
     */
    public static void resizeNode(final KNode node, final float newWidth, final float newHeight,
            final boolean movePorts) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        if (nodeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            // don't resize nodes that aren't laid out
            return;
        }
        KVector oldSize = new KVector(nodeLayout.getWidth(), nodeLayout.getHeight());
        KVector newSize = new KVector(
                Math.max(newWidth, nodeLayout.getProperty(LayoutOptions.MIN_WIDTH)),
                Math.max(newHeight, nodeLayout.getProperty(LayoutOptions.MIN_HEIGHT)));
        
        float widthRatio = (float) (newSize.x / oldSize.x);
        float heightRatio = (float) (newSize.y / oldSize.y);
        float widthDiff = (float) (newSize.x - oldSize.x);
        float heightDiff = (float) (newSize.y - oldSize.y);

        // update port positions
        if (movePorts) {
            Direction direction = node.getParent() == null
                    ? nodeLayout.getProperty(LayoutOptions.DIRECTION)
                    : node.getParent().getData(KShapeLayout.class).getProperty(LayoutOptions.DIRECTION);
            boolean fixedPorts = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS)
                    == PortConstraints.FIXED_POS;
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                PortSide portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE);
                if (portSide == PortSide.UNDEFINED) {
                    portSide = calcPortSide(port, direction);
                    portLayout.setProperty(LayoutOptions.PORT_SIDE, portSide);
                }
                switch (portSide) {
                case NORTH:
                    if (!fixedPorts) {
                        portLayout.setXpos(portLayout.getXpos() * widthRatio);
                    }
                    break;
                case EAST:
                    portLayout.setXpos(portLayout.getXpos() + widthDiff);
                    if (!fixedPorts) {
                        portLayout.setYpos(portLayout.getYpos() * heightRatio);
                    }
                    break;
                case SOUTH:
                    if (!fixedPorts) {
                        portLayout.setXpos(portLayout.getXpos() * widthRatio);
                    }
                    portLayout.setYpos(portLayout.getYpos() + heightDiff);
                    break;
                case WEST:
                    if (!fixedPorts) {
                        portLayout.setYpos(portLayout.getYpos() * heightRatio);
                    }
                    break;
                }
            }
        }
        
        // resize the node AFTER ports have been placed, since calcPortSide needs the old size
        nodeLayout.setSize((float) newSize.x, (float) newSize.y);
        
        // update label position
        KShapeLayout labelLayout = node.getLabel().getData(KShapeLayout.class);
        float midx = labelLayout.getXpos() + labelLayout.getWidth() / 2;
        float midy = labelLayout.getYpos() + labelLayout.getHeight() / 2;
        float widthPercent = midx / (float) oldSize.x;
        float heightPercent = midy / (float) oldSize.y;
        if (widthPercent + heightPercent >= 1) {
            if (widthPercent - heightPercent > 0 && midy >= 0) {
                // label is on the right
                labelLayout.setXpos(labelLayout.getXpos() + widthDiff);
                labelLayout.setYpos(labelLayout.getYpos() + heightDiff * heightPercent);
                // enable layout application for the node label
                labelLayout.setProperty(LayoutOptions.NO_LAYOUT, false);
            } else if (widthPercent - heightPercent < 0 && midx >= 0) {
                // label is on the bottom
                labelLayout.setXpos(labelLayout.getXpos() + widthDiff * widthPercent);
                labelLayout.setYpos(labelLayout.getYpos() + heightDiff);
                // enable layout application for the node label
                labelLayout.setProperty(LayoutOptions.NO_LAYOUT, false);
            }
        }
        
        // set fixed size option for the node: now the size is assumed to stay as determined here
        nodeLayout.setProperty(LayoutOptions.FIXED_SIZE, true);
    }

    /**
     * Determines the flow of the given port, that is the difference between the
     * number of outgoing edges and the number of incoming edges. Edges that
     * connect to descendant nodes are counted in their reverse direction.
     * 
     * @param port port for which the flow shall be calculated
     * @return difference between number of outgoing and incoming edges
     */
    public static int calcFlow(final KPort port) {
        int flow = 0;
        for (KEdge edge : port.getEdges()) {
            KPort sourcePort = edge.getSourcePort();
            KPort targetPort = edge.getTargetPort();
            KNode otherNode = null;
            if (sourcePort == port) {
                otherNode = edge.getTarget();
            } else if (targetPort == port) {
                otherNode = edge.getSource();
            }
            if (otherNode != null) {
                if (isDescendant(otherNode, port.getNode())) {
                    if (sourcePort == port) {
                        flow--;
                    }
                    if (targetPort == port) {
                        flow++;
                    }
                } else {
                    if (sourcePort == port) {
                        flow++;
                    }
                    if (targetPort == port) {
                        flow--;
                    }
                }
            }
        }
        return flow;
    }

    /**
     * Determines whether the given child node is a descendant of the parent
     * node.
     * 
     * @param child a child node
     * @param parent a parent node
     * @return true if {@code child} is a direct or indirect child of {@code
     *         parent}
     */
    public static boolean isDescendant(final KNode child, final KNode parent) {
        KNode current = child;
        while (current.getParent() != null) {
            current = current.getParent();
            if (current == parent) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Converts the given relative point to an absolute location. The insets of the parent node
     * are included in this calculation.
     * 
     * @param point a relative point
     * @param parent the parent node to which the point is relative to
     */
    public static void toAbsolute(final KVector point, final KNode parent) {
        KNode node = parent;
        while (node != null) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KInsets insets = nodeLayout.getInsets();
            point.translate(nodeLayout.getXpos() + insets.getLeft(),
                    nodeLayout.getYpos() + insets.getTop());
            node = node.getParent();
        }
    }
    
    /**
     * Converts the given absolute point to a relative location. The insets of the parent node
     * are included in this calculation.
     * 
     * @param point an absolute point
     * @param parent the parent node to which the point shall be made relative to
     */
    public static void toRelative(final KVector point, final KNode parent) {
        KNode node = parent;
        while (node != null) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KInsets insets = nodeLayout.getInsets();
            point.translate(-nodeLayout.getXpos() - insets.getLeft(),
                        -nodeLayout.getYpos() - insets.getTop());
            node = node.getParent();
        }
    }
    
    /**
     * Translates the contents of the given node by an offset.
     * 
     * @param parent parent node whose children shall be translated
     * @param xoffset x coordinate offset
     * @param yoffset y coordinate offset
     */
    public static void translate(final KNode parent, final float xoffset, final float yoffset) {
        for (KNode node : parent.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            nodeLayout.setXpos(nodeLayout.getXpos() + xoffset);
            nodeLayout.setYpos(nodeLayout.getYpos() + yoffset);
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                translate(edgeLayout.getSourcePoint(), xoffset, yoffset);
                for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                    translate(bendPoint, xoffset, yoffset);
                }
                translate(edgeLayout.getTargetPoint(), xoffset, yoffset);
                for (KLabel edgeLabel : edge.getLabels()) {
                    KShapeLayout labelLayout = edgeLabel.getData(KShapeLayout.class);
                    labelLayout.setXpos(labelLayout.getXpos() + xoffset);
                    labelLayout.setYpos(labelLayout.getYpos() + yoffset);
                }
            }
        }
    }
    
    /**
     * Translates the given point by an offset.
     * 
     * @param point point that shall be translated
     * @param xoffset x coordinate offset
     * @param yoffset y coordinate offset
     */
    public static void translate(final KPoint point, final float xoffset, final float yoffset) {
        point.setX(point.getX() + xoffset);
        point.setY(point.getY() + yoffset);
    }
    
    /**
     * Recursively search parents of the source and target node to find the most common parent. Then
     * add a dummy edge between the corresponding children within this common parent. Also consider
     * that hierarchy levels of parent and target might be asymmetric.
     * TODO this currently only works for graphs without ports... extend to work with ports
     * 
     * @author haf
     * @param parentNode the parent node
     */
    public static void addDummyEdgesForInterlevelConnections(final KNode parentNode) {
        for (KNode child : parentNode.getChildren()) {
            List<KEdge> edges = new ArrayList<KEdge>(child.getOutgoingEdges());
            for (KEdge edge : edges) {
                KNode source = edge.getSource();
                KNode target = edge.getTarget();
                // look for inter-level connections
                if (source.getParent() != target.getParent()
                        && edge.getSourcePort() == null && edge.getTargetPort() == null) {
                    KNode sourceParent = source;
                    KNode targetParent = target;
                    // successively create list of ancestors for target and source
                    // whenever a new ancestor is contained by the other list, we found a common
                    // ancestor
                    List<KNode> sourceAncestors = new ArrayList<KNode>();
                    List<KNode> targetAncestors = new ArrayList<KNode>();
                    sourceAncestors.add(source);
                    targetAncestors.add(target);
                    KNode newSource = null;
                    KNode newTarget = null;

                    do {
                        if (sourceParent != null) {
                            newSource = sourceParent;
                            sourceParent = sourceParent.getParent();
                        }
                        if (targetParent != null) {
                            newTarget = targetParent;
                            targetParent = targetParent.getParent();
                        }
                        if (sourceParent != null) {
                            sourceAncestors.add(sourceParent);
                        }
                        if (targetParent != null) {
                            targetAncestors.add(targetParent);
                        }
                        if (sourceAncestors.contains(targetParent)) {
                            // the list was build in order of hierarchy levels
                            // hence, the index denotes this level
                            int index = sourceAncestors.indexOf(targetParent);
                            // the new source is the corresponding child of the common parent,
                            // i.e. one less index
                            newSource = sourceAncestors.get(index - 1);
                            break;
                        }
                        if (targetAncestors.contains(sourceParent)) {
                            // the list was build in order of hierarchy levels
                            // hence, the index denotes this level
                            int index = targetAncestors.indexOf(sourceParent);
                            // the new source is the corresponding child of the common parent,
                            // i.e. one less index
                            newTarget = targetAncestors.get(index - 1);
                            break;
                        }
                    } while (!(sourceParent == null && targetParent == null));

                    KEdge dummyEdge = KimlUtil.createInitializedEdge();
                    dummyEdge.setSource(newSource);
                    dummyEdge.setTarget(newTarget);
                }
            }
            addDummyEdgesForInterlevelConnections(child);
        }
    }
    
    /**
     * Excludes the content of the given node from automatic layout.
     * 
     * @param node a layout node
     */
    public static void excludeContent(final KNode node) {
        for (KNode child : node.getChildren()) {
            child.getData(KShapeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
            for (KPort port : child.getPorts()) {
                port.getData(KShapeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
            }
            for (KEdge edge : child.getOutgoingEdges()) {
                edge.getData(KEdgeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
                for (KLabel label : edge.getLabels()) {
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
            excludeContent(child);
        }
    }

}
