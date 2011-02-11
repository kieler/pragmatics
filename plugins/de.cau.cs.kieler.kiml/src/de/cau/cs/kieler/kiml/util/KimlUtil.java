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
import java.util.ListIterator;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutDirection;
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
            KShapeLayout layout1 = port1.getData(KShapeLayout.class);
            KShapeLayout layout2 = port2.getData(KShapeLayout.class);
            int rank1 = layout1.getProperty(LayoutOptions.PORT_RANK);
            int rank2 = layout2.getProperty(LayoutOptions.PORT_RANK);
            PortSide side1 = layout1.getProperty(LayoutOptions.PORT_SIDE);
            PortSide side2 = layout2.getProperty(LayoutOptions.PORT_SIDE);
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
        layout.setProperty(LayoutOptions.INSETS, KLayoutDataFactory.eINSTANCE.createKInsets());
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
        KShapeLayout nodeLayout = port.getNode().getData(KShapeLayout.class);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        PortSide portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE);
        if (portSide != PortSide.UNDEFINED) {
            return portSide;
        }
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
        LayoutDirection layoutDirection = port.getNode().getParent().getData(KShapeLayout.class)
                .getProperty(LayoutOptions.LAYOUT_DIRECTION);
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
     * @param layoutDirection layout direction
     */
    public static void fillPortInfo(final KNode node, final LayoutDirection layoutDirection) {
        KGraphData layoutData = node.getData(KShapeLayout.class);
        PortConstraints portConstraints = layoutData.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (portConstraints == PortConstraints.FREE) {
            // set port sides according to layout direction
            switch (layoutDirection) {
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
                    portLayout.setProperty(LayoutOptions.PORT_SIDE, calcPortSide(port));
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
    private static final float MIN_PORT_DISTANCE = 4.0f;

    /**
     * Sets the size of a given node, depending on the minimal size, the number of ports
     * on each side, the insets, and the label.
     * 
     * @param node the node that shall be resized
     */
    public static void resizeNode(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        float minNorth = 2 * MIN_PORT_DISTANCE, minEast = 2 * MIN_PORT_DISTANCE,
                minSouth = 2 * MIN_PORT_DISTANCE, minWest = 2 * MIN_PORT_DISTANCE;
        if (portConstraints == PortConstraints.FIXED_POS) {
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                switch (portLayout.getProperty(LayoutOptions.PORT_SIDE)) {
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
            }
        } else {
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                switch (portLayout.getProperty(LayoutOptions.PORT_SIDE)) {
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

        float newWidth = KielerMath.maxf(nodeLayout.getProperty(LayoutOptions.MIN_WIDTH),
                MIN_NODE_SIZE, minNorth, minSouth);
        float newHeight = KielerMath.maxf(nodeLayout.getProperty(LayoutOptions.MIN_HEIGHT),
                MIN_NODE_SIZE, minEast, minWest);
        float oldWidth = nodeLayout.getWidth();
        float oldHeight = nodeLayout.getHeight();
        nodeLayout.setWidth(newWidth);
        nodeLayout.setHeight(newHeight);

        // update port positions
        for (KPort port : node.getPorts()) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            switch (portLayout.getProperty(LayoutOptions.PORT_SIDE)) {
            case EAST:
                portLayout.setXpos(portLayout.getXpos() + newWidth - oldWidth);
                break;
            case SOUTH:
                portLayout.setYpos(portLayout.getYpos() + newHeight - oldHeight);
                break;
            }
        }
        // update label position
        KShapeLayout labelLayout = node.getLabel().getData(KShapeLayout.class);
        if (labelLayout.getXpos() > oldWidth) {
            labelLayout.setXpos(labelLayout.getXpos() + newWidth - oldWidth);
        } else {
            float oldRelPos = (labelLayout.getXpos() + labelLayout.getWidth() / 2) / oldWidth;
            labelLayout.setXpos(oldRelPos * newWidth - labelLayout.getWidth() / 2);
        }
        if (labelLayout.getYpos() > oldHeight) {
            labelLayout.setYpos(labelLayout.getYpos() + newHeight - oldHeight);
        } else {
            float oldRelPos = (labelLayout.getYpos() + labelLayout.getHeight() / 2) / oldHeight;
            labelLayout.setYpos(oldRelPos * newHeight - labelLayout.getHeight() / 2);
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
     * Converts the given relative point to an absolute location.
     * 
     * @param point a relative point
     * @param parent the parent node to which the point is relative to
     */
    public static void toAbsolute(final KVector point, final KNode parent) {
        KNode node = parent;
        while (node != null) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KInsets insets = nodeLayout.getProperty(LayoutOptions.INSETS);
            point.x += nodeLayout.getXpos() + insets.getLeft();
            point.y += nodeLayout.getYpos() + insets.getTop();
            node = node.getParent();
        }
    }
    
    /**
     * Converts the given absolute point to a relative location.
     * 
     * @param point an absolute point
     * @param parent the parent node to which the point shall be made relative to
     */
    public static void toRelative(final KVector point, final KNode parent) {
        KNode node = parent;
        while (node != null) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KInsets insets = nodeLayout.getProperty(LayoutOptions.INSETS);
            point.x -= nodeLayout.getXpos() + insets.getLeft();
            point.y -= nodeLayout.getYpos() + insets.getTop();
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
     * Create a vector chain from the given edge layout.
     * 
     * @param edgeLayout an edge layout
     * @return a vector chain with source point, bend points, and target point
     */
    public static KVectorChain toVectorChain(final KEdgeLayout edgeLayout) {
        KVectorChain vectorChain = new KVectorChain();
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        vectorChain.add(sourcePoint.getX(), sourcePoint.getY());
        for (KPoint bendPoint : edgeLayout.getBendPoints()) {
            vectorChain.add(bendPoint.getX(), bendPoint.getY());
        }
        KPoint targetPoint = edgeLayout.getTargetPoint();
        vectorChain.add(targetPoint.getX(), targetPoint.getY());        
        return vectorChain;
    }
    
    /**
     * Apply the points of a vector chain to the given edge layout.
     * 
     * @param edgeLayout an edge layout
     * @param vectorChain a vector chain with source point, bend points, and target point
     */
    public static void applyVectorChain(final KEdgeLayout edgeLayout, final KVectorChain vectorChain) {
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KVector firstPoint = vectorChain.getFirst();
        sourcePoint.setX((float) firstPoint.x);
        sourcePoint.setY((float) firstPoint.y);
        
        edgeLayout.getBendPoints().clear();
        ListIterator<KVector> pointIter = vectorChain.listIterator(1);
        while (pointIter.nextIndex() < vectorChain.size() - 1) {
            KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            KVector nextPoint = pointIter.next();
            bendPoint.setX((float) nextPoint.x);
            bendPoint.setY((float) nextPoint.y);
            edgeLayout.getBendPoints().add(bendPoint);
        }
        
        KPoint targetPoint = edgeLayout.getTargetPoint();
        KVector lastPoint = vectorChain.getLast();
        targetPoint.setX((float) lastPoint.x);
        targetPoint.setY((float) lastPoint.y);
    }

}
