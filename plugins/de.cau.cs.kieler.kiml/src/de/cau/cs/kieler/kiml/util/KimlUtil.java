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

import java.util.EnumSet;
import java.util.Iterator;

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
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
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
 * @kieler.design proposed by msp
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class KimlUtil {
    
    /**
     * Default minimal width for nodes.
     */
    public static final float DEFAULT_MIN_WIDTH = 20.0f;
    
    /**
     * Default minimal height for nodes.
     */
    public static final float DEFAULT_MIN_HEIGHT = 20.0f;
    

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
     * Ensures that each element contained in the given graph is attributed correctly for
     * usage in KIML.
     * 
     * @param graph the parent node of a graph 
     */
    public static void validate(final KNode graph) {
        KLayoutDataFactory layoutFactory = KLayoutDataFactory.eINSTANCE;
        Iterator<EObject> contentIter = graph.eAllContents();
        while (contentIter.hasNext()) {
            EObject element = contentIter.next();
            // Make sure nodes are OK
            if (element instanceof KNode) {
                KNode node = (KNode) element;
                KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
                if (nodeLayout == null) {
                    nodeLayout = layoutFactory.createKShapeLayout();                   
                    node.getData().add(nodeLayout);
                } 
                if (nodeLayout.getInsets() == null) {
                    nodeLayout.setInsets(layoutFactory.createKInsets());
                }
            // Make sure ports are OK           
            } else if (element instanceof KPort) {
                KPort port = (KPort) element;
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                if (portLayout == null) {
                    port.getData().add(layoutFactory.createKShapeLayout());
                }
            // Make sure labels are OK
            } else if (element instanceof KLabel) {
                KLabel label = (KLabel) element;
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                if (labelLayout == null) {
                    label.getData().add(layoutFactory.createKShapeLayout());
                }
                if (label.getText() == null) {
                    label.setText("");
                }
            // Make sure edges are OK
            } else if (element instanceof KEdge) {
                KEdge edge = (KEdge) element;
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                if (edgeLayout == null) {
                    edgeLayout = layoutFactory.createKEdgeLayout();
                    edge.getData().add(edgeLayout);
                }
                if (edgeLayout.getSourcePoint() == null) {
                    edgeLayout.setSourcePoint(layoutFactory.createKPoint());
                }
                if (edgeLayout.getTargetPoint() == null) {
                    edgeLayout.setTargetPoint(layoutFactory.createKPoint());
                }
                // ports and edges are not opposite, so check whether they are connected properly
                KPort sourcePort = edge.getSourcePort();
                if (sourcePort != null) {
                    if (!sourcePort.getEdges().contains(edge)) {
                        sourcePort.getEdges().add(edge);
                    }
                }
                KPort targetPort = edge.getTargetPort();
                if (targetPort != null) {
                    if (!targetPort.getEdges().contains(edge)) {
                        targetPort.getEdges().add(edge);
                    }
                }
            }
        }
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
     * @return the port side relative to its containing node
     */
    public static PortSide calcPortSide(final KPort port, final Direction direction) {
        KShapeLayout portLayout = port.getData(KShapeLayout.class);

        // if the node has zero size, we cannot decide anything
        KShapeLayout nodeLayout = port.getNode().getData(KShapeLayout.class);
        float nodeWidth = nodeLayout.getWidth(), nodeHeight = nodeLayout.getHeight();
        if (nodeWidth <= 0 && nodeHeight <= 0) {
            return PortSide.UNDEFINED;
        }

        // check direction-dependent criterion
        float xpos = portLayout.getXpos(), ypos = portLayout.getYpos();
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
     * Sets the size of a given node, depending on the minimal size, the number of ports
     * on each side, the insets, and the label.
     * 
     * @param node the node that shall be resized
     * @return a vector holding the width and height resizing ratio, or {@code null} if the size
     *     constraint is set to {@code FIXED}
     */
    public static KVector resizeNode(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        EnumSet<SizeConstraint> sizeConstraint = nodeLayout.getProperty(LayoutOptions.SIZE_CONSTRAINT);
        if (sizeConstraint.isEmpty()) {
            return null;
        }
        
        float newWidth = 0, newHeight = 0;

        if (sizeConstraint.contains(SizeConstraint.PORTS)) {
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
        EnumSet<SizeConstraint> sizeConstraint = nodeLayout.getProperty(LayoutOptions.SIZE_CONSTRAINT);
        
        KVector oldSize = new KVector(nodeLayout.getWidth(), nodeLayout.getHeight());
        KVector newSize;
        
        // Calculate the new size
        if (sizeConstraint.contains(SizeConstraint.MINIMUM_SIZE)) {
            float minWidth = nodeLayout.getProperty(LayoutOptions.MIN_WIDTH);
            float minHeight = nodeLayout.getProperty(LayoutOptions.MIN_HEIGHT);
            
            // If minimum width or height are not set, maybe default to default values
            if (sizeConstraint.contains(SizeConstraint.DEFAULT_MINIMUM_SIZE)) {
                if (minWidth <= 0) {
                    minWidth = DEFAULT_MIN_WIDTH;
                }
                
                if (minHeight <= 0) {
                    minHeight = DEFAULT_MIN_HEIGHT;
                }
            }
            
            newSize = new KVector(Math.max(newWidth, minWidth), Math.max(newHeight, minHeight));
        } else {
            newSize = new KVector(newWidth, newHeight);
        }
        
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
        nodeLayout.setProperty(LayoutOptions.SIZE_CONSTRAINT, EnumSet.noneOf(SizeConstraint.class));
        
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
     * Persists all KGraphData elements of a KGraph by serializing the contained properties into
     * {@link de.cau.cs.kieler.core.kgraph.PersistentEntry} tuples.
     *
     * @param graph the root element of the graph to persist elements of.
     */
    public static void persistDataElements(final KNode graph) {
        TreeIterator<EObject> iterator = graph.eAllContents();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eObject instanceof KGraphData) {
                ((KGraphData) eObject).makePersistent();
            }
        }
    }

    /**
     * Loads all {@link de.cau.cs.kieler.core.properties.IProperty} of KGraphData elements of a
     * KGraph by deserializing {@link PersistentEntry} tuples.
     * Values are parsed using layout option data obtained from the {@link LayoutDataService}.
     * Options that cannot be resolved immediately (e.g. because the extension points have not
     * been read yet) are stored as {@link LayoutOptionProxy}.
     * 
     * @param graph the root element of the graph to load elements of.
     */
    public static void loadDataElements(final KNode graph) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        TreeIterator<EObject> iterator = graph.eAllContents();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eObject instanceof KGraphData) {
                KGraphData kgraphData = (KGraphData) eObject;
                for (PersistentEntry persistentEntry : kgraphData.getPersistentEntries()) {
                    String key = persistentEntry.getKey();
                    String value = persistentEntry.getValue();
                    if (key != null && value != null) {
                        LayoutOptionData<?> layoutOptionData = null;
                        
                        // try to get the layout option from the data service.
                        layoutOptionData = dataService.getOptionData(key);
                        
                        // if we have a valid layout option, parse its value.
                        if (layoutOptionData != null) {
                            Object layoutOptionValue = layoutOptionData.parseValue(value);
                            if (layoutOptionValue != null) {
                                kgraphData.setProperty(layoutOptionData, layoutOptionValue);
                            }
                        } else {
                            // the layout option could not be resolved, so create a proxy
                            LayoutOptionProxy.setProxyValue(kgraphData, key, value);
                        }
                    }
                }
            }
        }
    }
    
}
