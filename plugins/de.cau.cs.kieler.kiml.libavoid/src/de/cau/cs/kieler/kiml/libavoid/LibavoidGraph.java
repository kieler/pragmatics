/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.libavoid;

import java.io.File;
import java.util.Map;

import org.adaptagrams.AvoidPoints;
import org.adaptagrams.AvoidRectangle;
import org.adaptagrams.Box;
import org.adaptagrams.ConnDirFlag;
import org.adaptagrams.ConnEnd;
import org.adaptagrams.ConnRef;
import org.adaptagrams.ConnType;
import org.adaptagrams.Point;
import org.adaptagrams.Polygon;
import org.adaptagrams.Router;
import org.adaptagrams.RouterFlag;
import org.adaptagrams.ShapeConnectionPin;
import org.adaptagrams.ShapeRef;
import org.adaptagrams.adaptagrams;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

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
    protected BiMap<Integer, KNode> nodeIdMap = HashBiMap.create();
    /** Maps ports to their ids. */
    protected BiMap<Integer, KPort> portIdMap = HashBiMap.create();
    /** Maps edges to their ids. */
    protected BiMap<Integer, KEdge> edgeIdMap = HashBiMap.create();

    /** Maps ids to the respective shapes. */
    protected Map<Integer, ShapeRef> idShapeRefMap = Maps.newHashMap();
    /** Maps ids to the respective connection refs . */
    protected BiMap<Integer, ConnRef> idConnRefMap = HashBiMap.create();

    private Map<ConnRef, KEdge> connRefEdgeMap = Maps.newHashMap();

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

    private KNode parentNode;

    /**
     * Initializes a new libavoid router with the properties of the root node.
     * 
     * @param parentNode
     *            the root KNode
     */
    public LibavoidGraph(final KNode parentNode) {

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
        KShapeLayout layout = parentNode.getData(KShapeLayout.class);
        edgeRouting = layout.getProperty(LayoutOptions.EDGE_ROUTING);
        int rf = RouterFlag.PolyLineRouting;
        if (edgeRouting == EdgeRouting.ORTHOGONAL) {
            rf = RouterFlag.OrthogonalRouting;
        }
        direction = layout.getProperty(LayoutOptions.DIRECTION);

        // create the router object
        router = new Router(rf);

        // layout options
        LibavoidProperties.transferOptions(router, layout);
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
    public BiMap<Integer, KNode> getNodeIdMap() {
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
    private void transformGraph(final KNode root) {

        // add boundaries if this node is a compound node
        if (root.getParent() != null) {
            transformHierarchicalParent(root);
        } else {
            // create 4 dummy nodes, as the libavoid process expects gap-less node
            // ids starting from 1.
            transformHierarchicalParentDummy(root);
        }

        // nodes
        for (KNode node : root.getChildren()) {
            transformNode(node);
        }

        // edges
        for (KNode node : root.getChildren()) {
            // all edges between nodes within the root node
            for (KEdge edge : node.getOutgoingEdges()) {
                if (edge.getSource().getParent().equals(edge.getTarget().getParent())) {
                    transformEdge(edge);
                }
            }
        }
        // AND, in case of a compound node,
        // all edges between hierarchical ports and nodes within the root node
        for (KPort p : root.getPorts()) {
            for (KEdge e : p.getEdges()) {
                KNode srcParent = e.getSource().getParent();
                KNode tgtParent = e.getTarget().getParent();
                if ((srcParent.equals(root) || tgtParent.equals(root))) {
                    transformEdge(e);
                }
            }
        }

    }

    /**
     * Create 4 nodes that "surround", hence restrict, the child area. This way it is guaranteed
     * that no edge is routed outside its compound node.
     */
    private void transformHierarchicalParent(final KNode parent) {

        KShapeLayout shape = parent.getData(KShapeLayout.class);
        KInsets insets = shape.getInsets();
        float borderSpacing = shape.getProperty(LayoutOptions.BORDER_SPACING);

        // offset each side by the shape buffer distance to let edges route properly
        float bufferDistance = shape.getProperty(LibavoidProperties.SHAPE_BUFFER_DISTANCE);
        // top
        libavoidNode(parent, NODE_COMPOUND_NORTH, 0, 0 - SURROUNDING_RECT_SIZE - bufferDistance,
                shape.getWidth(), SURROUNDING_RECT_SIZE + insets.getTop(),
                // edges
                0, 0);
        // right
        libavoidNode(parent, NODE_COMPOUND_EAST,
                0 + shape.getWidth() + bufferDistance - insets.getRight(), 0,
                SURROUNDING_RECT_SIZE, shape.getHeight(), 0, 0);
        // bottom
        libavoidNode(parent, NODE_COMPOUND_SOUTH, 0, 0 + shape.getHeight() + bufferDistance
                - insets.getBottom(), shape.getWidth(), SURROUNDING_RECT_SIZE, 0, 0);
        // left
        libavoidNode(parent, NODE_COMPOUND_WEST, 0 - bufferDistance - SURROUNDING_RECT_SIZE, 0,
                SURROUNDING_RECT_SIZE + insets.getLeft() + borderSpacing, shape.getHeight(), 0, 0);

        // convert the ports of the compound node itself
        for (KPort port : parent.getPorts()) {
            int nodeId = determineHierarchicalNodeId(port);
            libavoidPort(port, portIdCounter, nodeId, parent);
            portIdCounter++;
        }
    }

    /**
     * For internal representation only, no semantic meaning.
     */
    private void transformHierarchicalParentDummy(final KNode root) {
        // 4 dummies
        libavoidNode(root, NODE_COMPOUND_NORTH, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_EAST, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_SOUTH, 0, 0, 0, 0, 0, 0);
        libavoidNode(root, NODE_COMPOUND_WEST, 0, 0, 0, 0, 0, 0);
    }

    private void libavoidNode(final KNode node, final int id, final float xPos, final float yPos,
            final float width, final float height, final int portLessIncomingEdges,
            final int portLessOutgoingEdges) {

        // get margins
        Margins margin = node.getData(KShapeLayout.class).getProperty(LayoutOptions.MARGINS);

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

    private void libavoidPort(final KPort port, final int portId, final int nodeId,
            final KNode compoundNode) {

        // put to map
        portIdMap.put(portId, port);

        // gather information
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        PortSide side = portLayout.getProperty(LayoutOptions.PORT_SIDE);
        if (side == null) {
            side = KimlUtil.calcPortSide(port, direction);
        }

        // parents margins
        Margins margin =
                port.getNode().getData(KShapeLayout.class).getProperty(LayoutOptions.MARGINS);
        if (compoundNode != null) {
            margin = new Margins();
        }

        // get center point of port
        double centerX = portLayout.getXpos() + portLayout.getWidth() / 2 + margin.left;
        double centerY = portLayout.getYpos() + portLayout.getHeight() / 2 + margin.top;

        // for compound nodes we have to mirror the port sides
        if (compoundNode != null) {
            side = side.opposed();
        }

        // create the port
        ShapeRef sr = idShapeRefMap.get(nodeId);

        // get the bounding box of the node
        Box box = sr.polygon().offsetBoundingBox(0);
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

    private void transformNode(final KNode node) {

        // turn this into an option
        // atm ignore unconnected components
        if (node.getOutgoingEdges().isEmpty() && node.getIncomingEdges().isEmpty()) {
            return;
        }

        // get information about port-less incoming and outgoing edges
        int portLessIncomingEdges =
                Iterables.size(Iterables.filter(node.getIncomingEdges(), new Predicate<KEdge>() {
                    public boolean apply(final KEdge edge) {
                        return edge.getTargetPort() == null;
                    }
                }));
        int portLessOutgoingEdges =
                Iterables.size(Iterables.filter(node.getOutgoingEdges(), new Predicate<KEdge>() {
                    public boolean apply(final KEdge edge) {
                        return edge.getSourcePort() == null;
                    }
                }));

        // convert the bounds
        KShapeLayout shape = node.getData(KShapeLayout.class);

        libavoidNode(node, nodeIdCounter, shape.getXpos(), shape.getYpos(), shape.getWidth(),
                shape.getHeight(), portLessIncomingEdges, portLessOutgoingEdges);

        // transfer port constraints
        // TODO unsupported yet?
        // PortConstraints pc = shape.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        // sb.append("NODEOPTION " + nodeIdCounter + " " + pc);
        // sb.append("\n");

        // transfer all ports
        for (KPort port : node.getPorts()) {
            libavoidPort(port, portIdCounter, nodeIdCounter, null);
            portIdCounter++;
        }

        nodeIdCounter++;
    }

    private void transformEdge(final KEdge edge) {
        // assign an id
        edgeIdMap.put(edgeIdCounter, edge);

        // convert the edge
        Integer srcId = nodeIdMap.inverse().get(edge.getSource());
        Integer tgtId = nodeIdMap.inverse().get(edge.getTarget());

        Integer srcPort = portIdMap.inverse().get(edge.getSourcePort());
        Integer tgtPort = portIdMap.inverse().get(edge.getTargetPort());

        // hierarchical port's libavoid nodes are not stored in the mapping
        if (srcPort != null && srcId == null) {
            srcId = determineHierarchicalNodeId(edge.getSourcePort());
        }
        if (tgtPort != null && tgtId == null) {
            tgtId = determineHierarchicalNodeId(edge.getTargetPort());
        }

        // create the edge
        ShapeRef src = idShapeRefMap.get(srcId);
        ShapeRef tgt = idShapeRefMap.get(tgtId);

        // determine the pin locations for this edge
        int srcPin = PIN_ARBITRARY;
        int tgtPin = PIN_ARBITRARY;

        // determine the type of the edge, ie, if it involves ports
        if (srcPort != null && tgtPort != null) {
            srcPin = srcPort;
            tgtPin = tgtPort;
        } else if (srcPort != null) {
            srcPin = srcPort;
            if (direction != Direction.UNDEFINED) {
                tgtPin = PIN_INCOMING;
            }
        } else if (tgtPort != null) {
            if (direction != Direction.UNDEFINED) {
                srcPin = PIN_OUTGOING;
            }
            tgtPin = tgtPort;
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

    private int determineHierarchicalNodeId(final KPort port) {
        PortSide ps = KimlUtil.calcPortSide(port, direction);
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
    protected void applyLayout(final KNode root) {

        for (ConnRef cr : idConnRefMap.values()) {

            // Be sure to use #displayRoute() here and not route(), as the
            // second method only contains the "raw" route, eg, without any
            // nudging done.
            // Remark: don't be confused by the polygon type here
            // in c++ polyline is just a typedef of polygon
            Polygon route = cr.displayRoute();

            // get the associated edge
            KEdge edge = connRefEdgeMap.get(cr);
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            // clear the old bend points
            edgeLayout.getBendPoints().clear();

            // determine some offsets
            // in libavoid the pins are placed on the border of the rectangle
            // which includes margins. Here, offsets are added to the endpoints
            // that correlate to the port's specified side.
            KVector srcOffset = calculatePortOffset(edge.getSourcePort());
            KVector tgtOffset = calculatePortOffset(edge.getTargetPort());

            AvoidPoints pts = route.getPs();
            // transfer libavoid's results to the edges
            for (int i = 0; i < pts.size(); ++i) {
                if (i == 0) {
                    // first point is the source point
                    if (edge.getSourcePort() != null) {
                        edgeLayout.setSourcePoint(toKPointWPortSize(edge.getSourcePort(), pts.get(i),
                                srcOffset));
                    } else {
                        edgeLayout.setSourcePoint(toKPoint(pts.get(i), srcOffset));
                    }
                } else if (i == pts.size() - 1) {
                    // last point is the target point
                    if (edge.getTargetPort() != null) {
                        edgeLayout.setTargetPoint(toKPointWPortSize(edge.getTargetPort(), pts.get(i),
                                tgtOffset));
                    } else {
                        edgeLayout.setTargetPoint(toKPoint(pts.get(i), tgtOffset));
                    }
                } else {
                    // rest are bend points
                    edgeLayout.getBendPoints().add(toKPoint(pts.get(i)));
                }
            }
        }
    }

    private KPoint toKPoint(final Point p) {
        return toKPoint(p, null);
    }

    private KPoint toKPoint(final Point p, final KVector offset) {
        KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        if (offset != null) {
            kpoint.setPos((float) (p.getX() + offset.x), (float) (p.getY() + offset.y));
        } else {
            kpoint.setPos((float) p.getX(), (float) p.getY());
        }
        return kpoint;
    }

    /**
     * Considers the size of the port to adjust the actual anchor point.
     */
    private KPoint toKPointWPortSize(final KPort port, final Point pnt, final KVector offset) {
        KPoint kpoint = toKPoint(pnt, offset);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        PortSide side = portLayout.getProperty(LayoutOptions.PORT_SIDE);
        switch (side) {
        case WEST:
            kpoint.setX((float) (kpoint.getX() - portLayout.getWidth()));
            return kpoint;
        case EAST:
            kpoint.setX((float) (kpoint.getX() + portLayout.getWidth()));
            return kpoint;
        case NORTH:
            kpoint.setY((float) (kpoint.getY() - portLayout.getHeight()));
            return kpoint;
        case SOUTH:
            kpoint.setY((float) (kpoint.getY() + portLayout.getHeight()));
            return kpoint;
        default:
            // nothing to adapt
        }

        return kpoint;
    }

    /**
     * 
     * @param p
     * @return if {@code p} is null, a (0, 0) offset is returned.
     */
    private KVector calculatePortOffset(final KPort p) {
        KVector offset = new KVector();
        if (p != null) {
            KShapeLayout portLayout = p.getData(KShapeLayout.class);
            Margins margins =
                    p.getNode().getData(KLayoutData.class).getProperty(LayoutOptions.MARGINS);
            PortSide side = portLayout.getProperty(LayoutOptions.PORT_SIDE);

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
    private void calculateJunctionPoints(final KNode graph) {
        for (KNode n : graph.getChildren()) {
            for (KEdge edge : n.getOutgoingEdges()) {
                KVectorChain junctionPoints = KimlUtil.determineJunctionPoints(edge);
                edge.getData(KLayoutData.class).setProperty(LayoutOptions.JUNCTION_POINTS,
                        junctionPoints);
            }
        }
    }

}
