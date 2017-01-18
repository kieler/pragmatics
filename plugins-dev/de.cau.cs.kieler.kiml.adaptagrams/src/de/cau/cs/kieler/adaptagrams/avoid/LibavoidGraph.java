/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.avoid;

import java.io.File;
import java.util.Map;

import org.adaptagrams.AvoidBox;
import org.adaptagrams.AvoidPoints;
import org.adaptagrams.AvoidRectangle;
import org.adaptagrams.ConnDirFlag;
import org.adaptagrams.ConnEnd;
import org.adaptagrams.ConnRef;
import org.adaptagrams.ConnType;
import org.adaptagrams.Point;
import org.adaptagrams.Polygon;
import org.adaptagrams.Router;
import org.adaptagrams.RouterFlag;
import org.adaptagrams.RoutingOption;
import org.adaptagrams.ShapeConnectionPin;
import org.adaptagrams.ShapeRef;
import org.adaptagrams.adaptagrams;
import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.adaptagrams.properties.AvoidProperties;
import de.cau.cs.kieler.kiml.adaptagrams.properties.AvoidOptions;

/**
 * @author uru
 * 
 */
public class LibavoidGraph {

    // Internal object
    /** Currently used edge routing. */
    private EdgeRouting edgeRouting;
    /** Currently specified direction. */
    private Direction direction;
    /** The router object used to perform the edge routing. */
    private Router router;
 
    // maps are accessible in extending classes
    // CHECKSTYLEOFF VisibilityModifier NEXT 10 LINES
    // Maps holding the nodes and edges of the current graph.
    /** Maps node to their ids. */
    protected BiMap<Integer, ElkNode> nodeIdMap = HashBiMap.create();
    /** Maps ports to their ids. */
    protected BiMap<Integer, ElkPort> portIdMap = HashBiMap.create();
    /** Maps edges to their ids. */
    protected BiMap<Integer, ElkEdge> edgeIdMap = HashBiMap.create();

    /** Maps ids to the respective shapes. */
    protected Map<Integer, ShapeRef> idShapeRefMap = Maps.newHashMap();
    /** Maps ids to the respective connection refs . */
    protected BiMap<Integer, ConnRef> idConnRefMap = HashBiMap.create();

    private Map<ConnRef, ElkEdge> connRefEdgeMap = Maps.newHashMap();

    /** First id used to reference ports. */
    public static final int PORT_ID_START = 5;
    /** First id used to reference nodes. */
    public static final int NODE_ID_START = 5;
    // reserved for compound node's boundaries
    /** Id of the bounding node at the north. */
    public static final int NODE_COMPOUND_NORTH = 1;
    /** Id of the bounding node at the east. */
    public static final int NODE_COMPOUND_EAST = 2;
    /** Id of the bounding node at the south. */
    public static final int NODE_COMPOUND_SOUTH = 3;
    /** Id of the bounding node at the west. */
    public static final int NODE_COMPOUND_WEST = 4;
    /** size, either width or height, of the surrounding rectangles of compound nodes. */
    public static final int SURROUNDING_RECT_SIZE = 10;

    /*
     * Pin Types
     * 
     * Per definition the ids of passed ports start at 5. Thus, [1..4] are free for arbitrary
     * definition. Remark: the id has to be > 0! Otherwise a c++ assertion fails.
     */
    /** Indicates pins that can be used by an arbitrary endpoint of an edge. */
    private static final int PIN_ARBITRARY = 1;
    /** Indicates pins reserved for incoming edges. */
    private static final int PIN_INCOMING = 2;
    /** Indicates pins reserved for outgoing edges. */
    private static final int PIN_OUTGOING = 3;

    private int nodeIdCounter = NODE_ID_START;
    private int portIdCounter = PORT_ID_START;
    private int edgeIdCounter = 1;

    private ElkNode parentNode;

    /**
     * Initializes a new libavoid router with the properties of the root node.
     * 
     * @param parentNode
     *            the root ElkNode
     */
    public LibavoidGraph(final ElkNode parentNode) {

        this.parentNode = parentNode;

        // initialize
        nodeIdCounter = NODE_ID_START;
        nodeIdMap.clear();
        portIdCounter = PORT_ID_START;
        portIdMap.clear();
        edgeIdCounter = 1;
        edgeIdMap.clear();
        idShapeRefMap.clear();
        idConnRefMap.clear();
        connRefEdgeMap.clear();

        // determine the type of edge routing, we use polyline as default
        // IMPORTANT: the edge routing option has to be passed first!
        // The information is required to initialize the libavoid router properly
        // before the router can be configured with additional options
        edgeRouting = parentNode.getProperty(AvoidOptions.EDGE_ROUTING);
        int rf = RouterFlag.PolyLineRouting;
        if (edgeRouting == EdgeRouting.ORTHOGONAL) {
            rf = RouterFlag.OrthogonalRouting;
        }
        direction = parentNode.getProperty(AvoidOptions.DIRECTION);

        // create the router object
        router = new Router(rf);

        // layout options
        AvoidProperties.transferOptions(router, parentNode);
    }

    /**
     * Transforms the KGraph structures to libavoid structures.
     */
    public void transformGraph() {

        // transform to libavoid object
        transformGraph(parentNode);

    }

    /**
     * @return the router
     */
    public Router getRouter() {
        return router;
    }

    /**
     * Actually run the libavoid router and apply the layout back.
     */
    public void process() {

        File folder =
                new File(System.getProperty("user.home") + File.separatorChar + "tmp"
                        + File.separatorChar + "libavoid");
        folder.mkdirs();
        
        router.setRoutingOption(RoutingOption.nudgeSharedPathsWithCommonEndPoint, false);
        
        // perform the routing
        router.processTransaction();

        // apply layout information back
        applyLayout(parentNode);
        calculateJunctionPoints(parentNode);

        router.outputInstanceToSVG(folder.getAbsolutePath() + File.separator
                + "libavoid-afterroute" + this.hashCode() + ".svg");

        // destroy
        router.delete();
        // make sure it is not used anymore, otherwise it will result in a jvm crash
        router = null;
    }

    /**
     * @return the nodeIdMap
     */
    public BiMap<Integer, ElkNode> getNodeIdMap() {
        return nodeIdMap;
    }

    /*-----------------------------------------------------
     *  Layout Options
     * -----------------------------------------------------
     */

  

    /*-----------------------------------------------------
     *  KGraph -> Libavoid
     * -----------------------------------------------------
     */

    /**
     * Transform the actual graph.
     * 
     * @param root
     *            of the current graph.
     */
    private void transformGraph(final ElkNode root) {

        // add boundaries if this node is a compound node
        if (root.getParent() != null) {
            transformHierarchicalParent(root);
        } else {
            // create 4 dummy nodes, as the libavoid process expects gap-less node
            // ids starting from 1.
            transformHierarchicalParentDummy(root);
        }

        // nodes
        for (ElkNode node : root.getChildren()) {
            transformNode(node);
        }

        // edges
        for (ElkNode node : root.getChildren()) {
            // all edges between nodes within the root node
            for (ElkEdge edge : node.getOutgoingEdges()) {
                if (!edge.isHierarchical()) {
                    transformEdge(edge);
                }
            }
        }
        // AND, in case of a compound node,
        // all edges between hierarchical ports and nodes within the root node
        for (ElkPort p : root.getPorts()) {
            for (ElkEdge e : ElkGraphUtil.allIncidentEdges(p)) {
                ElkNode src = ElkGraphUtil.connectableShapeToNode(e.getSources().get(0));
                ElkNode tgt = ElkGraphUtil.connectableShapeToNode(e.getTargets().get(0));
                if ((src.getParent().equals(root) || tgt.getParent().equals(root))) {
                    transformEdge(e);
                }
            }
        }

    }

    /**
     * Create 4 nodes that "surround", hence restrict, the child area. This way it is guaranteed
     * that no edge is routed outside its compound node.
     */
    private void transformHierarchicalParent(final ElkNode parent) {

        ElkPadding padding = parent.getProperty(AvoidOptions.PADDING);

        // offset each side by the shape buffer distance to let edges route properly
        float bufferDistance = parent.getProperty(AvoidProperties.SHAPE_BUFFER_DISTANCE);
        // top
        libavoidNode(parent, NODE_COMPOUND_NORTH, 0, 0 - SURROUNDING_RECT_SIZE - bufferDistance,
                parent.getWidth(), SURROUNDING_RECT_SIZE + padding.getTop(),
                // edges
                0, 0);
        // right
        libavoidNode(parent, NODE_COMPOUND_EAST,
                0 + parent.getWidth() + bufferDistance - padding.getRight(), 0,
                SURROUNDING_RECT_SIZE, parent.getHeight(), 0, 0);
        // bottom
        libavoidNode(parent, NODE_COMPOUND_SOUTH, 0, 0 + parent.getHeight() + bufferDistance
                - padding.getBottom(), parent.getWidth(), SURROUNDING_RECT_SIZE, 0, 0);
        // left
        libavoidNode(parent, NODE_COMPOUND_WEST, 0 - bufferDistance - SURROUNDING_RECT_SIZE, 0,
                SURROUNDING_RECT_SIZE + padding.getLeft(), parent.getHeight(), 0, 0);

        // convert the ports of the compound node itself
        for (ElkPort port : parent.getPorts()) {
            int nodeId = determineHierarchicalNodeId(port);
            libavoidPort(port, portIdCounter, nodeId, parent);
            portIdCounter++;
            
            // mark these ports as fixed for the next layout run
            // (of a higher hierarchy level)
            port.setProperty(AvoidOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        }
    }

    /**
     * For internal representation only, no semantic meaning.
     */
    private void transformHierarchicalParentDummy(final ElkNode root) {
        // 4 dummies
        libavoidNode(root, NODE_COMPOUND_NORTH, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_EAST, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_SOUTH, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_WEST, 0, 0, 0, 0, 0, 0);
    }

    private void libavoidNode(final ElkNode node, final int id, 
            final double xPos, final double yPos,
            final double width, final double height, 
            final int portLessIncomingEdges, final int portLessOutgoingEdges) {

        // get margins
        ElkMargin margin = node.getProperty(AvoidOptions.MARGINS);

        // defined by top left and bottom right coordinates
        AvoidRectangle rect =
                new AvoidRectangle(new Point(xPos - margin.left, yPos - margin.top), new Point(xPos
                        + width + margin.right, yPos + height + margin.bottom));
        ShapeRef sr = new ShapeRef(router, rect, id);

        // put to map
        if (id >= NODE_ID_START) {
            nodeIdMap.put(id, node);
        }
        idShapeRefMap.put(id, sr);

        // create pins for port-less edges
        if (direction == Direction.UNDEFINED) {

            // create incoming+outgoing pins on each side of the node
            int totalPins = portLessIncomingEdges + portLessOutgoingEdges;
            if (totalPins > 0) {
                double spacing = 1 / (double) (totalPins + 1);

                int[] connDir =
                        { ConnDirFlag.ConnDirUp, ConnDirFlag.ConnDirRight, ConnDirFlag.ConnDirDown,
                                ConnDirFlag.ConnDirLeft };
                int[] xPosPt = { 1, 0, 1, 0 };
                int[] xOffset = { 0, 1, 0, 0 };
                int[] yPosPt = { 0, 1, 0, 1 };
                int[] yOffset = { 0, 0, 1, 0 };

                // create the pins on each side
                // CHECKSTYLEOFF MagicNumber
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < totalPins; j++) {
                        ShapeConnectionPin pin =
                                new ShapeConnectionPin(sr, PIN_ARBITRARY, xPosPt[i]
                                        * ((j + 1) * spacing) + xOffset[i], yPosPt[i]
                                        * ((j + 1) * spacing) + yOffset[i], 0.0, connDir[i]);
                        pin.setExclusive(true);

                        /*
                         * cout << "Pin at " << xPos[i] * ((j+1) * spacing) + xOffset[i] << " " <<
                         * yPos[i] * ((j+1) * spacing) + yOffset[i] << " " << connDir[i] << endl;
                         */
                    }
                }
                // CHECKSTYLEON MagicNumber
            }
        } else {
            // create incoming pins on the "first" side, considering the direction,
            // outgoing edges on the "last" side

            // some setups depending on direction option
            int connDirIncoming = ConnDirFlag.ConnDirAll;
            int connDirOutgoing = ConnDirFlag.ConnDirAll;
            int horizontal = 0;
            int vertical = 0;
            // where are the incoming ports?
            int left = 0;
            int right = 0;
            int up = 0;
            int down = 0;

            if (direction == Direction.RIGHT) {
                connDirIncoming = ConnDirFlag.ConnDirLeft;
                connDirOutgoing = ConnDirFlag.ConnDirRight;
                horizontal = 1;
                left = 1;
            } else if (direction == Direction.LEFT) {
                connDirIncoming = ConnDirFlag.ConnDirRight;
                connDirOutgoing = ConnDirFlag.ConnDirLeft;
                horizontal = 1;
                right = 1;
            } else if (direction == Direction.UP) {
                connDirIncoming = ConnDirFlag.ConnDirDown;
                connDirOutgoing = ConnDirFlag.ConnDirUp;
                vertical = 1;
                down = 1;
            } else if (direction == Direction.DOWN) {
                connDirIncoming = ConnDirFlag.ConnDirUp;
                connDirOutgoing = ConnDirFlag.ConnDirDown;
                vertical = 1;
                up = 1;
            }

            // create the pins
            // incoming
            if (portLessIncomingEdges > 0) {
                double incSpacing = 1 / (double) (portLessIncomingEdges + 1);
                for (int i = 0; i < portLessIncomingEdges; i++) {
                    ShapeConnectionPin pin =
                            new ShapeConnectionPin(sr, PIN_INCOMING, vertical
                                    * ((i + 1) * incSpacing) + right, horizontal
                                    * ((i + 1) * incSpacing) + down, 0, connDirIncoming);
                    pin.setExclusive(true);
                }
            }

            // outgoing
            if (portLessOutgoingEdges > 0) {
                double outSpacing = 1 / (double) (portLessOutgoingEdges + 1);
                for (int i = 0; i < portLessOutgoingEdges; i++) {
                    ShapeConnectionPin pin =
                            new ShapeConnectionPin(sr, PIN_OUTGOING, vertical
                                    * ((i + 1) * outSpacing) + left, horizontal
                                    * ((i + 1) * outSpacing) + up, 0, connDirOutgoing);
                    pin.setExclusive(true);
                }
            }
        }
    }

    private void libavoidPort(final ElkPort port, final int portId, final int nodeId,
            final ElkNode compoundNode) {

        // put to map
        portIdMap.put(portId, port);

        // gather information
        PortSide side = port.getProperty(CoreOptions.PORT_SIDE);
        if (side == null) {
            side = ElkUtil.calcPortSide(port, direction);
        }

        // parent's margins
        ElkMargin margin =
                port.getParent().getProperty(AvoidOptions.MARGINS);
        if (compoundNode != null) {
            margin = new ElkMargin();
        }

        // get center point of port
        double centerX = port.getX() + port.getWidth() / 2 + margin.left;
        double centerY = port.getY() + port.getHeight() / 2 + margin.top;

        // for compound nodes we have to mirror the port sides
        if (compoundNode != null) {
            side = side.opposed();
        }

        // create the port
        ShapeRef sr = idShapeRefMap.get(nodeId);

        // get the bounding box of the node
        AvoidBox box = sr.polygon().offsetBoundingBox(0);
        // calculate width and height
        double width = box.getMax().getX() - box.getMin().getX();
        double height = box.getMax().getY() - box.getMin().getY();
        // determine the pin's positions relative on the respective side
        double relX = centerX / width;
        double relY = centerY / height;

        ShapeConnectionPin pin = null;
        if (side == PortSide.NORTH) {
            pin =
                    new ShapeConnectionPin(sr, portId, relX, adaptagrams.getATTACH_POS_TOP(), 0d,
                            ConnDirFlag.ConnDirUp);
        } else if (side == PortSide.EAST) {
            pin =
                    new ShapeConnectionPin(sr, portId, adaptagrams.getATTACH_POS_RIGHT(), relY, 0d,
                            ConnDirFlag.ConnDirRight);
        } else if (side == PortSide.SOUTH) {
            pin =
                    new ShapeConnectionPin(sr, portId, relX, adaptagrams.getATTACH_POS_BOTTOM(),
                            0d, ConnDirFlag.ConnDirDown);
        } else { // (side == PORT_SIDE_WEST) {
            pin =
                    new ShapeConnectionPin(sr, portId, adaptagrams.getATTACH_POS_LEFT(), relY, 0d,
                            ConnDirFlag.ConnDirLeft);
        }

        pin.setExclusive(false);
    }

    private void transformNode(final ElkNode node) {

        // turn this into an option
        // atm ignore unconnected components
        if (node.getOutgoingEdges().isEmpty() && node.getIncomingEdges().isEmpty()) {
            return;
        }

        // get information about port-less incoming and outgoing edges
        int portLessIncomingEdges =
                Iterables.size(Iterables.filter(node.getIncomingEdges(), new Predicate<ElkEdge>() {
                    public boolean apply(final ElkEdge edge) {
                        return edge.getTargets() == null 
                            || edge.getTargets().isEmpty() 
                            || !(edge.getTargets().get(0) instanceof ElkPort);
                    }
                }));
        int portLessOutgoingEdges =
                Iterables.size(Iterables.filter(node.getOutgoingEdges(), new Predicate<ElkEdge>() {
                    public boolean apply(final ElkEdge edge) {
                        return edge.getSources() == null 
                            || edge.getSources().isEmpty() 
                            || !(edge.getSources().get(0) instanceof ElkPort);
                    }
                }));

        // convert the bounds
        libavoidNode(node, nodeIdCounter, node.getX(), node.getY(), node.getWidth(),
                node.getHeight(), portLessIncomingEdges, portLessOutgoingEdges);

        // transfer all ports
        for (ElkPort port : node.getPorts()) {
            libavoidPort(port, portIdCounter, nodeIdCounter, null);
            portIdCounter++;
        }

        nodeIdCounter++;
    }

    private void transformEdge(final ElkEdge edge) {
        // assign an id
        edgeIdMap.put(edgeIdCounter, edge);

        ElkNode srcNode = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
        ElkNode tgtNode = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
        ElkPort srcPort = ElkGraphUtil.connectableShapeToPort(edge.getSources().get(0));
        ElkPort tgtPort = ElkGraphUtil.connectableShapeToPort(edge.getTargets().get(0));
        // convert the edge
        Integer srcId = nodeIdMap.inverse().get(srcNode);
        Integer tgtId = nodeIdMap.inverse().get(tgtNode);

        Integer srcPortId = portIdMap.inverse().get(srcPort);
        Integer tgtPortId = portIdMap.inverse().get(tgtPort);

        // hierarchical port's libavoid nodes are not stored in the mapping
        if (srcPortId != null && srcId == null) {
            srcId = determineHierarchicalNodeId(srcPort);
        }
        if (tgtPortId != null && tgtId == null) {
            tgtId = determineHierarchicalNodeId(tgtPort);
        }

        // create the edge
        ShapeRef src = idShapeRefMap.get(srcId);
        ShapeRef tgt = idShapeRefMap.get(tgtId);

        // determine the pin locations for this edge
        int srcPin = PIN_ARBITRARY;
        int tgtPin = PIN_ARBITRARY;

        // determine the type of the edge, ie, if it involves ports
        if (srcPortId != null && tgtPortId != null) {
            srcPin = srcPortId;
            tgtPin = tgtPortId;
        } else if (srcPortId != null) {
            srcPin = srcPortId;
            if (direction != Direction.UNDEFINED) {
                tgtPin = PIN_INCOMING;
            }
        } else if (tgtPortId != null) {
            if (direction != Direction.UNDEFINED) {
                srcPin = PIN_OUTGOING;
            }
            tgtPin = tgtPortId;
        } else {
            if (direction != Direction.UNDEFINED) {
                tgtPin = PIN_INCOMING;
                srcPin = PIN_OUTGOING;
            }
        }

        ConnEnd srcPt = new ConnEnd(src, srcPin);
        ConnEnd tgtPt = new ConnEnd(tgt, tgtPin);

        ConnRef connRef = new ConnRef(router, srcPt, tgtPt, edgeIdCounter);
        connRef.setRoutingType(edgeRouting == EdgeRouting.ORTHOGONAL ? ConnType.ConnType_Orthogonal
                : ConnType.ConnType_PolyLine);

        idConnRefMap.put(edgeIdCounter, connRef);
        connRefEdgeMap.put(connRef, edge);

        edgeIdCounter++;
    }

    private int determineHierarchicalNodeId(final ElkPort port) {
        PortSide ps = ElkUtil.calcPortSide(port, direction);
        int nodeId = 0;
        switch (ps) {
        case NORTH:
            nodeId = NODE_COMPOUND_NORTH;
            break;
        case EAST:
            nodeId = NODE_COMPOUND_EAST;
            break;
        case SOUTH:
            nodeId = NODE_COMPOUND_SOUTH;
            break;
        default: // WEST
            nodeId = NODE_COMPOUND_WEST;
            break;
        }
        return nodeId;
    }

    /*-----------------------------------------------------
     *  Layout Application
     * -----------------------------------------------------
     */
    /**
     * Apply the positions back to the original elements.
     * 
     * @param root
     *            the original parent node
     */
    protected void applyLayout(final ElkNode root) {

        for (ConnRef cr : idConnRefMap.values()) {

            // Be sure to use #displayRoute() here and not route(), as the
            // second method only contains the "raw" route, eg, without any
            // nudging done.
            // Remark: don't be confused by the polygon type here
            // in c++ polyline is just a typedef of polygon
            Polygon route = cr.displayRoute();

            // get the associated edge
            ElkEdge edge = connRefEdgeMap.get(cr);
            ElkPort srcPort = ElkGraphUtil.connectableShapeToPort(edge.getSources().get(0));
            ElkPort tgtPort = ElkGraphUtil.connectableShapeToPort(edge.getTargets().get(0));
            
            // clear the old bend points
            ElkEdgeSection edgeSection = ElkGraphUtil.firstEdgeSection(edge, true, true);
            
            // determine some offsets
            // in libavoid the pins are placed on the border of the rectangle
            // which includes margins. Here, offsets are added to the endpoints
            // that correlate to the port's specified side.
            KVector srcOffset = calculatePortOffset(srcPort);
            KVector tgtOffset = calculatePortOffset(tgtPort);

            AvoidPoints pts = route.getPs();
            Point lastPoint = null;
            KVectorChain vc = new KVectorChain();
            // transfer libavoid's results to the edges
            for (int i = 0; i < pts.size(); ++i) {
                Point pi = pts.get(i);
                if (i == 0) {
                    // first point is the source point
                    if (srcPort != null) {
                        KVector v = toKPointWPortSize(srcPort, pts.get(i), srcOffset);
                        vc.add(v);
                    } else {
                        vc.add(pi.getX() + srcOffset.x, pi.getY() + srcOffset.y);
                    }
                } else if (i == pts.size() - 1) {
                    // last point is the target point
                    if (tgtPort != null) {
                        KVector v = toKPointWPortSize(tgtPort, pts.get(i), tgtOffset);
                        vc.add(v);
                    } else {
                        vc.add(pi.getX() + tgtOffset.x, pi.getY() + tgtOffset.y);
                    }
                } else {
                    Point current = pts.get(i);
                    
                    // FIXME libavoid produces duplicate points ...
                    // seems to be a weird issue on windows with float precision
                    // SUPPRESS CHECKSTYLE NEXT 4 EmptyBlock 
                    if (Math.abs(current.getX() - lastPoint.getX())
                            // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber 
                            < 0.001d && Math.abs(current.getY() 
                                    - lastPoint.getY()) < 0.001d) {
                        // duplicate
                    } else {
                        // rest are bend points
                        vc.add(pi.getX(), pi.getY());
                    }
                    
                }
                
                lastPoint = pts.get(i);
            }
            
            // finally copy the points to the edge section
            ElkUtil.applyVectorChain(vc, edgeSection);
        }
    }

    /**
     * Considers the size of the port to adjust the actual anchor point.
     */
    private KVector toKPointWPortSize(final ElkPort port, final Point pnt, final KVector offset) {
        KVector v = new KVector(pnt.getX() + offset.x, pnt.getX() + offset.y);
        PortSide side = port.getProperty(CoreOptions.PORT_SIDE);
        switch (side) {
        case WEST:
            v.x -= port.getWidth();
            break;
        case EAST:
            v.x += port.getWidth();
            break;
        case NORTH:
            v.y -= port.getHeight();
            break;
        case SOUTH:
            v.y += port.getHeight();
            break;
        default:
            // nothing to adapt
        }

        return v;
    }

    /**
     * 
     * @param p
     * @return if {@code p} is null, a (0, 0) offset is returned.
     */
    private KVector calculatePortOffset(final ElkPort p) {
        KVector offset = new KVector();
        if (p != null) {
            ElkMargin margins = p.getParent().getProperty(AvoidOptions.MARGINS);
            PortSide side = p.getProperty(CoreOptions.PORT_SIDE);

            switch (side) {
            case WEST:
                offset.add(margins.left, 0);
                break;
            case EAST:
                offset.add(-margins.right, 0);
                break;
            case NORTH:
                offset.add(0, margins.top);
                break;
            case SOUTH:
                offset.add(0, -margins.bottom);
                break;
            default:
                // leave it where it is
            }
        }
        return offset;
    }

    /**
     * Calculates and sets the junction points for each edge of the graph.
     * 
     * @param graph
     *            the graph.
     */
    private void calculateJunctionPoints(final ElkNode graph) {
        for (ElkNode n : graph.getChildren()) {
            for (ElkEdge edge : n.getOutgoingEdges()) {
                KVectorChain junctionPoints = ElkUtil.determineJunctionPoints(edge);
                edge.setProperty(CoreOptions.JUNCTION_POINTS, junctionPoints);
            }
        }
    }

}
