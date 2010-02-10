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
package de.cau.cs.kieler.kiml.layout.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.KielerMath;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.options.PortSide;

/**
 * Utility methods for KGraphs and layout data.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class KimlLayoutUtil {

    /**
     * Hidden constructor to avoid instantiation.
     */
    private KimlLayoutUtil() {
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
        private LayoutDirection layoutDirection;

        /**
         * Creates a port comparator for the given setting.
         * 
         * @param theforward indicates whether to treat ranks in forward
         *            direction
         * @param thelayoutDirection horizontal or vertical layout direction
         */
        public PortComparator(final boolean theforward, final LayoutDirection thelayoutDirection) {
            this.forward = theforward;
            this.layoutDirection = thelayoutDirection;
        }

        /**
         * {@inheritDoc}
         */
        public int compare(final KPort port1, final KPort port2) {
            KShapeLayout layout1 = getShapeLayout(port1);
            KShapeLayout layout2 = getShapeLayout(port2);
            int rank1 = LayoutOptions.getInt(layout1, LayoutOptions.PORT_RANK);
            int rank2 = LayoutOptions.getInt(layout2, LayoutOptions.PORT_RANK);
            PortSide side1 = LayoutOptions.getEnum(layout1, PortSide.class);
            PortSide side2 = LayoutOptions.getEnum(layout2, PortSide.class);
            if (side1 == side2) {
                return layoutDirection == LayoutDirection.DOWN && !forward
                        || layoutDirection == LayoutDirection.RIGHT && forward ? rank1 - rank2
                        : rank2 - rank1;
            } else if (layoutDirection == LayoutDirection.DOWN) {
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
     * Returns shape layout data for a given graph element. If there is no
     * registered shape layout for the element, a new shape layout is created
     * and registered.
     * 
     * @param graphElement the graph element
     * @return related shape layout data
     */
    public static KShapeLayout getShapeLayout(final KGraphElement graphElement) {
        KShapeLayout layoutData = (KShapeLayout) graphElement.getData(KLayoutDataPackage.eINSTANCE
                .getKShapeLayout());
        if (layoutData == null) {
            layoutData = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
            graphElement.getData().add(layoutData);
        }
        return layoutData;
    }

    /**
     * Returns edge layout data for a given graph element. If there is no
     * registered edge layout for the element, a new edge layout is created and
     * registered.
     * 
     * @param graphElement the graph element
     * @return related edge layout data
     */
    public static KEdgeLayout getEdgeLayout(final KGraphElement graphElement) {
        KEdgeLayout layoutData = (KEdgeLayout) graphElement.getData(KLayoutDataPackage.eINSTANCE
                .getKEdgeLayout());
        if (layoutData == null) {
            layoutData = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
            graphElement.getData().add(layoutData);
        }
        return layoutData;
    }
    
    /**
     * Returns the value of the given {@link KOption} as an {@code Object}.
     * 
     * @param koption the {@code KOption} for which the value shall be retrieved
     * @param optionData the layout option data related with the option
     * @return the current value of the option
     */
    public static Object getValue(final KOption koption, final LayoutOptionData optionData) {
        switch (optionData.getType()) {
        case STRING:
            return ((KStringOption) koption).getValue();
        case BOOLEAN:
            return Boolean.valueOf(((KBooleanOption) koption).isValue());
        case ENUM:
        case INT:
            return Integer.valueOf(((KIntOption) koption).getValue());
        case FLOAT:
            return Float.valueOf(((KFloatOption) koption).getValue());
        default:
            return null;
        }
    }
    
    /**
     * Sets the value of the given {@link KOption}.
     * 
     * @param koption the {@code KOption} for which the value shall be set
     * @param optionData the layout option data related with the option
     * @param value the new value of the option
     */
    public static void setValue(final KOption koption, final LayoutOptionData optionData,
            final Object value) {
        switch (optionData.getType()) {
        case STRING:
            ((KStringOption) koption).setValue((String) value);
            break;
        case BOOLEAN:
            ((KBooleanOption) koption).setValue(((Boolean) value).booleanValue());
            break;
        case ENUM:
            if (value instanceof Enum<?>) {
                ((KIntOption) koption).setValue(((Enum<?>) value).ordinal());
                break;
            }
        case INT:
            ((KIntOption) koption).setValue(((Integer) value).intValue());
            break;
        case FLOAT:
            ((KFloatOption) koption).setValue(((Float) value).floatValue());
            break;
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
     * Determines the port side for the given port from its relative position at
     * its corresponding node.
     * 
     * @param port port to analyze
     * @return port placement
     */
    public static PortSide calcPortSide(final KPort port) {
        KShapeLayout nodeLayout = getShapeLayout(port.getNode());
        KShapeLayout portLayout = getShapeLayout(port);
        // determine port placement from port position
        float nodeWidth = nodeLayout.getWidth();
        float nodeHeight = nodeLayout.getHeight();
        if (nodeWidth > 0) {
            float relx = (portLayout.getXpos() + portLayout.getWidth() / 2) - (nodeWidth / 2);
            float rely = (portLayout.getYpos() + portLayout.getHeight() / 2) - (nodeHeight / 2);
    
            if (relx != 0) {
                float nodeRatio = Math.abs(nodeHeight / nodeWidth);
                float portRatio = Math.abs(rely / relx);
                if (portRatio < nodeRatio) {
                    if (relx > 0) {
                        return PortSide.EAST;
                    } else if (relx < 0) {
                        return PortSide.WEST;
                    }
                } else if (portRatio > nodeRatio) {
                    if (rely > 0) {
                        return PortSide.SOUTH;
                    } else if (rely < 0) {
                        return PortSide.NORTH;
                    }
                }
            } else if (rely > 0) {
                return PortSide.SOUTH;
            } else if (rely < 0) {
                return PortSide.NORTH;
            }
        }

        // determine port placement from the incident edges
        LayoutDirection layoutDirection = LayoutOptions.getEnum(getShapeLayout(
                port.getNode().getParent()), LayoutDirection.class);
        int flow = calcFlow(port);
        switch (layoutDirection) {
        case DOWN:
            if (flow > 0) {
                return PortSide.SOUTH;
            }
            if (flow < 0) {
                return PortSide.NORTH;
            }
            break;
        case UP:
            if (flow > 0) {
                return PortSide.NORTH;
            }
            if (flow < 0) {
                return PortSide.SOUTH;
            }
            break;            
        case LEFT:
            if (flow > 0) {
                return PortSide.WEST;
            }
            if (flow < 0) {
                return PortSide.EAST;
            }
            break;
        case RIGHT:
            if (flow > 0) {
                return PortSide.EAST;
            }
            if (flow < 0) {
                return PortSide.WEST;
            }
            break;
        }
        return PortSide.UNDEFINED;
    }

    /**
     * Sets port ranks for all ports of the given node according to their
     * relative positions.
     * 
     * @param node node for which port ranks shall be set
     */
    public static void calcPortRanks(final KNode node) {
        // sort the ports according to their positions
        KPort[] ports = node.getPorts().toArray(new KPort[0]);
        Arrays.sort(ports, new Comparator<KPort>() {
            public int compare(final KPort port1, final KPort port2) {
                KShapeLayout port1Layout = getShapeLayout(port1);
                PortSide port1Side = LayoutOptions.getEnum(port1Layout, PortSide.class);
                int port1Rank = LayoutOptions.getInt(port1Layout, LayoutOptions.PORT_RANK);
                KShapeLayout port2Layout = getShapeLayout(port2);
                PortSide port2Side = LayoutOptions.getEnum(port2Layout, PortSide.class);
                int port2Rank = LayoutOptions.getInt(port2Layout, LayoutOptions.PORT_RANK);
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

        // assign ranks according to the new order
        for (int i = 0; i < ports.length; i++) {
            LayoutOptions.setInt(getShapeLayout(ports[i]), LayoutOptions.PORT_RANK, i);
        }
    }

    /**
     * Fills all missing data for the ports of the given node, such as port
     * sides and port ranks.
     * 
     * @param node node for which port data shall be created
     * @param layoutDirection layout direction
     */
    public static void fillPortInfo(final KNode node, final LayoutDirection layoutDirection) {
        KLayoutData layoutData = getShapeLayout(node);
        PortConstraints portConstraints = LayoutOptions.getEnum(layoutData, PortConstraints.class);
        if (portConstraints == PortConstraints.FREE_PORTS) {
            // set port sides according to layout direction
            switch (layoutDirection) {
            case DOWN:
                for (KPort port : node.getPorts()) {
                    LayoutOptions.setEnum(getShapeLayout(port), calcFlow(port) < 0
                            ? PortSide.NORTH : PortSide.SOUTH);
                }
                break;
            case UP:
                for (KPort port : node.getPorts()) {
                    LayoutOptions.setEnum(getShapeLayout(port), calcFlow(port) < 0
                            ? PortSide.SOUTH : PortSide.NORTH);
                }
                break;
            case LEFT:
                for (KPort port : node.getPorts()) {
                    LayoutOptions.setEnum(getShapeLayout(port), calcFlow(port) < 0
                            ? PortSide.EAST : PortSide.WEST);
                }
                break;
            default:
                for (KPort port : node.getPorts()) {
                    LayoutOptions.setEnum(getShapeLayout(port), calcFlow(port) < 0
                            ? PortSide.WEST : PortSide.EAST);
                }
                break;
            }
            LayoutOptions.setEnum(layoutData, PortConstraints.FIXED_SIDE);
        } else if (portConstraints != PortConstraints.UNDEFINED) {
            // set port sides and ranks according to relative position
            boolean ranksUndefined = false;
            for (KPort port : node.getPorts()) {
                KLayoutData portLayout = getShapeLayout(port);
                if (LayoutOptions.getInt(portLayout, LayoutOptions.PORT_RANK) < 0) {
                    ranksUndefined = true;
                }
                if (LayoutOptions.getEnum(portLayout, PortSide.class) == PortSide.UNDEFINED) {
                    LayoutOptions.setEnum(portLayout, calcPortSide(port));
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
    private static final float MIN_NODE_SIZE = 7.0f;
    /** minimal distance of two ports on each side of a node. */
    private static final float MIN_PORT_DISTANCE = 4.0f;
    /** maximal aspect ratio of node sizes. */
    private static final float MAX_SIZE_RATIO = 3.0f;

    /**
     * Sets the size of a given node, depending on the minimal size, the number of ports
     * on each side, the insets, and the label.
     * 
     * @param node the node that shall be resized
     */
    public static void resizeNode(final KNode node) {
        KShapeLayout nodeLayout = getShapeLayout(node);
        float oldWidth = nodeLayout.getWidth();
        float oldHeight = nodeLayout.getHeight();
        float newWidth = LayoutOptions.getFloat(nodeLayout, LayoutOptions.MIN_WIDTH);
        float newHeight = LayoutOptions.getFloat(nodeLayout, LayoutOptions.MIN_HEIGHT);
        if (newWidth < MIN_NODE_SIZE || newHeight < MIN_NODE_SIZE) {
            newWidth = newWidth < MIN_NODE_SIZE ? MIN_NODE_SIZE : newWidth;
            newHeight = newHeight < MIN_NODE_SIZE ? MIN_NODE_SIZE : newHeight;
            KShapeLayout labelLayout = getShapeLayout(node.getLabel());
            newWidth = Math.max(newWidth, labelLayout.getWidth());
            newHeight = Math.max(newHeight, labelLayout.getHeight());
    
            float minNorth = MIN_PORT_DISTANCE, minEast = MIN_PORT_DISTANCE,
                    minSouth = MIN_PORT_DISTANCE, minWest = MIN_PORT_DISTANCE;
            for (KPort port : node.getPorts()) {
                switch (LayoutOptions.getEnum(getShapeLayout(port), PortSide.class)) {
                case NORTH:
                    minNorth += MIN_PORT_DISTANCE;
                    break;
                case EAST:
                    minEast += MIN_PORT_DISTANCE;
                    break;
                case SOUTH:
                    minSouth += MIN_PORT_DISTANCE;
                    break;
                case WEST:
                    minWest += MIN_PORT_DISTANCE;
                    break;
                }
            }
    
            newWidth = KielerMath.maxf(newWidth, minNorth, minSouth);
            newHeight = KielerMath.maxf(newHeight, minEast, minWest);
            if (newHeight < newWidth / MAX_SIZE_RATIO) {
                newHeight = newWidth / MAX_SIZE_RATIO;
            } else if (newWidth < newHeight / MAX_SIZE_RATIO) {
                newWidth = newHeight / MAX_SIZE_RATIO;
            }
        }
        nodeLayout.setWidth(newWidth);
        nodeLayout.setHeight(newHeight);

        // update port positions
        for (KPort port : node.getPorts()) {
            KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
            switch (LayoutOptions.getEnum(portLayout, PortSide.class)) {
            case EAST:
                portLayout.setXpos(portLayout.getXpos() + newWidth - oldWidth);
                break;
            case SOUTH:
                portLayout.setYpos(portLayout.getYpos() + newHeight - oldHeight);
            }
        }
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
            if (sourcePort != null && targetPort != null) {
                KPort otherPort = sourcePort;
                if (otherPort == port) {
                    otherPort = targetPort;
                }
                if (isDescendant(otherPort.getNode(), port.getNode())) {
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
     * Translates the contents of the given node by an offset.
     * 
     * @param parent parent node whose children shall be translated
     * @param xoffset x coordinate offset
     * @param yoffset y coordinate offset
     */
    public static void translate(final KNode parent, final float xoffset, final float yoffset) {
        for (KNode node : parent.getChildren()) {
            KShapeLayout nodeLayout = getShapeLayout(node);
            nodeLayout.setXpos(nodeLayout.getXpos() + xoffset);
            nodeLayout.setYpos(nodeLayout.getYpos() + yoffset);
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = getEdgeLayout(edge);
                translate(edgeLayout.getSourcePoint(), xoffset, yoffset);
                for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                    translate(bendPoint, xoffset, yoffset);
                }
                translate(edgeLayout.getTargetPoint(), xoffset, yoffset);
                for (KLabel edgeLabel : edge.getLabels()) {
                    KShapeLayout labelLayout = getShapeLayout(edgeLabel);
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

}
