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

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
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
import de.cau.cs.kieler.kiml.options.SizeConstraint;

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
     * Creates a KNode, initializes some attributes, and returns it.
     * 
     * @return an initialized KNode
     */
    public static KNode createInitializedNode() {
        KNode layoutNode = KGraphFactory.eINSTANCE.createKNode();
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
        KShapeLayout labelLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
        port.getData().add(labelLayout);
        return port;
    }

    /**
     * Creates a KLabel, initializes some attributes, and returns it.
     * 
     * @param element a labeled graph element
     * @return an initialized KLabel
     */
    public static KLabel createInitializedLabel(final KLabeledGraphElement element) {
        KLabel label = KGraphFactory.eINSTANCE.createKLabel();
        KShapeLayout labelLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
        label.getData().add(labelLayout);
        label.setText("");
        label.setParent(element);
        return label;
    }
    
    /**
     * Create a unique identifier for the given graph element. Note that this identifier
     * is not necessarily universally unique, since it uses the hash code, which
     * usually covers only the range of heap space addresses.
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
     * Returns a sorted list of the ports of the given node. This requires port
     * sides to be already calculated.
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
                KShapeLayout port2Layout = port2.getData(KShapeLayout.class);
                PortSide port2Side = port2Layout.getProperty(LayoutOptions.PORT_SIDE);
                int result = 0;
                switch (port1Side) {
                case NORTH:
                    if (port2Side == PortSide.NORTH) {
                        result = Float.compare(port1Layout.getXpos(), port2Layout.getXpos());
                    } else {
                        result = -1;
                    }
                    break;
                case EAST:
                    if (port2Side == PortSide.NORTH) {
                        result = 1;
                    } else if (port2Side == PortSide.EAST) {
                        result = Float.compare(port1Layout.getYpos(), port2Layout.getYpos());
                    } else {
                        result = -1;
                    }
                    break;
                case SOUTH:
                    if (port2Side == PortSide.NORTH || port2Side == PortSide.EAST) {
                        result = 1;
                    } else if (port2Side == PortSide.SOUTH) {
                        result = Float.compare(port2Layout.getXpos(), port1Layout.getXpos());
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

    /** minimal size of a node. */
    private static final float MIN_NODE_SIZE = 20.0f;

    /**
     * Sets the size of a given node, depending on the minimal size, the number of ports
     * on each side, the insets, and the label.
     * 
     * @param node the node that shall be resized
     * @return a vector holding the width and height resizing ratio, or {@code null} if the size
     *     constraint is set to {@code FIXED}
     */
    public static KVector resizeNode(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        SizeConstraint sizeConstraint = nodeLayout.getProperty(LayoutOptions.SIZE_CONSTRAINT);
        if (sizeConstraint == SizeConstraint.FIXED) {
            return null;
        }
        
        float newWidth = 0, newHeight = 0;

        if (sizeConstraint.arePortsConsidered()) {
            PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            float minNorth = 2, minEast = 2, minSouth = 2, minWest = 2;
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
                        minNorth += portLayout.getWidth() + 2;
                        break;
                    case EAST:
                        minEast += portLayout.getHeight() + 2;
                        break;
                    case SOUTH:
                        minSouth += portLayout.getWidth() + 2;
                        break;
                    case WEST:
                        minWest += portLayout.getHeight() + 2;
                        break;
                    }
                }
            }
            
            newWidth = Math.max(minNorth, minSouth);
            newHeight = Math.max(minEast, minWest);
        }

        if (sizeConstraint.isDefSizeConsidered()) {
            newWidth = Math.max(newWidth, MIN_NODE_SIZE);
            newHeight = Math.max(newHeight, MIN_NODE_SIZE);
        }
        
        return resizeNode(node, newWidth, newHeight, true);
    }
    
    /**
     * Resize a node to the given width and height, adjusting port and label positions if needed.
     * 
     * @param node a node
     * @param newWidth the new width to set
     * @param newHeight the new height to set
     * @param movePorts whether port positions shall be adjusted
     * @return a vector holding the width and height resizing ratio
     */
    public static KVector resizeNode(final KNode node, final float newWidth, final float newHeight,
            final boolean movePorts) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        if (nodeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            // don't resize nodes that aren't laid out
            return null;
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
        
        // update label positions
        for (KLabel label : node.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            float midx = labelLayout.getXpos() + labelLayout.getWidth() / 2;
            float midy = labelLayout.getYpos() + labelLayout.getHeight() / 2;
            float widthPercent = midx / (float) oldSize.x;
            float heightPercent = midy / (float) oldSize.y;
            if (widthPercent + heightPercent >= 1) {
                if (widthPercent - heightPercent > 0 && midy >= 0) {
                    // label is on the right
                    labelLayout.setXpos(labelLayout.getXpos() + widthDiff);
                    labelLayout.setYpos(labelLayout.getYpos() + heightDiff * heightPercent);
                } else if (widthPercent - heightPercent < 0 && midx >= 0) {
                    // label is on the bottom
                    labelLayout.setXpos(labelLayout.getXpos() + widthDiff * widthPercent);
                    labelLayout.setYpos(labelLayout.getYpos() + heightDiff);
                }
            }
        }
        
        // set fixed size option for the node: now the size is assumed to stay as determined here
        nodeLayout.setProperty(LayoutOptions.SIZE_CONSTRAINT, SizeConstraint.FIXED);
        
        return new KVector(widthRatio, heightRatio);
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
     * Set a layout option using a serialized key / value pair.
     * 
     * @param graphData the graph data instance to modify
     * @param id the layout option identifier
     * @param value the value for the layout option
     */
    public static void setOption(final KGraphData graphData, final String id,
            final String value) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        LayoutOptionData<?> optionData = dataService.getOptionData(id);
        if (optionData != null) {
            Object obj = optionData.parseValue(value);
            if (obj != null) {
                graphData.setProperty(optionData, obj);
            }
        }
    }

    /**
     * Persists all KGraphData elements of a KNode graph.
     *
     * @param graph
     *            the root element of the graph to persist elements of.
     */
    public static void persistDataElements(final KNode graph) {
        TreeIterator<EObject> iterator = graph.eAllContents();
        EObject eObject = null;
        while (iterator.hasNext()) {
            eObject = iterator.next();
            if (eObject instanceof KGraphData) {
                ((KGraphData) eObject).makePersistent();
            }
        }
    }

}
