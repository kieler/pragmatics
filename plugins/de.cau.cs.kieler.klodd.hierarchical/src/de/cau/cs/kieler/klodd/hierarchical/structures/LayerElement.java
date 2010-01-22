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
package de.cau.cs.kieler.klodd.hierarchical.structures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * A layer element representing a node or a long edge in the layered graph.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayerElement {

    /** value to use if the rank is undefined. */
    public static final int UNDEF_RANK = -1;

    /** rank of this element inside the containing layer. */
    private int rank = UNDEF_RANK;
    /** linear segment containing this layer element. */
    private LinearSegment linearSegment = null;
    /** the number of routing slot ranks on north side. */
    private int northRanks = 0;
    /** the number of routing slot ranks on east side. */
    private int eastRanks = 0;
    /** the number of routing slot ranks on south side. */
    private int southRanks = 0;
    /** the number of routing slot ranks on west side. */
    private int westRanks = 0;
    /** the element object. */
    private KGraphElement elemObj;
    /** the containing layer. */
    private Layer layer;
    /** the corresponding node in the acyclic KIELER graph. */
    private KSlimNode kNode;
    /** port constraints for this layer element. */
    private PortConstraints portConstraints;
    /** number of rank numbers consumed by this layer element. */
    private int rankWidth;
    /** the new position that is determined for this layer element. */
    private KPoint position;
    /** offset to be added to the determined position. */
    private KPoint posOffset;
    /** the width of the contained object. */
    private float realWidth;
    /** the height of the contained object. */
    private float realHeight;
    /** the total crosswise dimension. */
    private float totalCrosswiseDim = -1.0f;
    /** the list of incoming layer connections. */
    private List<LayerConnection> incoming = new LinkedList<LayerConnection>();
    /** the list of outgoing layer connections. */
    private List<LayerConnection> outgoing = new LinkedList<LayerConnection>();
    /** the list of element loop. */
    private List<ElementLoop> loops = new LinkedList<ElementLoop>();
    /** map of ports to port ranks. */
    private Map<KPort, Integer> portRanks = null;
    /** ports on the north side. */
    private KPort[] northPorts;
    /** ports on the east side. */
    private KPort[] eastPorts;
    /** ports on the south side. */
    private KPort[] southPorts;
    /** ports on the west side. */
    private KPort[] westPorts;

    /**
     * Creates a layer element in an existing layer.
     * 
     * @param obj the element object
     * @param thelayer the containing layer
     * @param thekNode the corresponding node in the acyclic KIELER graph
     */
    public LayerElement(final KGraphElement obj, final Layer thelayer, final KSlimNode thekNode) {
        this.elemObj = obj;
        this.layer = thelayer;
        this.kNode = thekNode;
        this.position = KLayoutDataFactory.eINSTANCE.createKPoint();
        this.posOffset = KLayoutDataFactory.eINSTANCE.createKPoint();

        if (obj instanceof KNode) {
            // the layer element is a layout node
            KNode node = (KNode) obj;
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(node);
            portConstraints = LayoutOptions.getEnum(nodeLayout, PortConstraints.class);
            rankWidth = Math.max(node.getPorts().size(), 1);
            realWidth = nodeLayout.getWidth();
            realHeight = nodeLayout.getHeight();

            // create port lists
            List<KPort> northPortList = new LinkedList<KPort>();
            List<KPort> eastPortList = new LinkedList<KPort>();
            List<KPort> southPortList = new LinkedList<KPort>();
            List<KPort> westPortList = new LinkedList<KPort>();
            for (KPort port : node.getPorts()) {
                PortSide portSide = LayoutOptions.getEnum(KimlLayoutUtil.getShapeLayout(port),
                        PortSide.class);
                switch (portSide) {
                case NORTH:
                    northPortList.add(port);
                    break;
                case EAST:
                    eastPortList.add(port);
                    break;
                case SOUTH:
                    southPortList.add(port);
                    break;
                case WEST:
                    westPortList.add(port);
                }
            }
            KPort[] dummyArray = new KPort[0];
            northPorts = northPortList.toArray(dummyArray);
            eastPorts = eastPortList.toArray(dummyArray);
            southPorts = southPortList.toArray(dummyArray);
            westPorts = westPortList.toArray(dummyArray);
            if (portConstraints == PortConstraints.FIXED_POS
                    || portConstraints == PortConstraints.FIXED_ORDER) {
                Comparator<KPort> portComparator = new Comparator<KPort>() {
                    public int compare(final KPort port1, final KPort port2) {
                        KShapeLayout layout1 = KimlLayoutUtil.getShapeLayout(port1);
                        KShapeLayout layout2 = KimlLayoutUtil.getShapeLayout(port2);
                        int rank1 = LayoutOptions.getInt(layout1, LayoutOptions.PORT_RANK);
                        int rank2 = LayoutOptions.getInt(layout2, LayoutOptions.PORT_RANK);
                        return rank1 - rank2;
                    }
                };
                Arrays.sort(northPorts, portComparator);
                Arrays.sort(eastPorts, portComparator);
                Arrays.sort(southPorts, portComparator);
                Arrays.sort(westPorts, portComparator);
            }
        } else if (obj instanceof KPort) {
            // the layer element is a port
            KPort port = (KPort) obj;
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(port);
            portConstraints = PortConstraints.FIXED_POS;
            rankWidth = 1;
            realWidth = shapeLayout.getWidth();
            realHeight = shapeLayout.getHeight();
        } else if (obj instanceof KEdge) {
            // the layer element is a long edge
            portConstraints = PortConstraints.FIXED_POS;
            rankWidth = 1;
            realWidth = 0.0f;
            realHeight = 0.0f;
        } else {
            throw new IllegalArgumentException("Unknown object type received.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (elemObj instanceof KNode) {
            KNode node = (KNode) elemObj;
            String label = node.getLabel().getText();
            if (label != null && label.length() > 0) {
                return label;
            } else {
                return "node:" + rank;
            }
        } else if (elemObj instanceof KPort) {
            KPort port = (KPort) elemObj;
            return "port:" + port.getLabel().getText();
        } else if (elemObj instanceof KEdge) {
            KEdge edge = (KEdge) elemObj;
            KNode source = edge.getSource();
            KNode target = edge.getTarget();
            if (source != null && target != null) {
                return "(" + source.getLabel().getText() + ") > (" + target.getLabel().getText() + ")";
            } else if (target != null) {
                return "(" + edge.getSourcePort().getLabel().getText() + ") > ("
                        + target.getLabel().getText() + ")";
            } else if (source != null) {
                return "(" + source.getLabel().getText() + ") > ("
                        + edge.getTargetPort().getLabel().getText() + ")";
            } else {
                return "(" + edge.getSourcePort().getLabel().getText() + ") > ("
                        + edge.getTargetPort().getLabel().getText() + ")";
            }
        } else {
            return "element:" + rank;
        }
    }

    /**
     * Get the containing layer.
     * 
     * @return the layer
     */
    public Layer getLayer() {
        return layer;
    }

    /**
     * Set a new layer for this element. The element is added to the new layer.
     * 
     * @param newLayer the new layer
     */
    public void setLayer(final Layer newLayer) {
        this.layer = newLayer;
        newLayer.getElements().add(this);
    }

    /**
     * Gets the outgoing edges of the contained object.
     * 
     * @return the list of outgoing edges, or null if the object is an edge
     */
    public List<KSlimEdge> getOutgoingEdges() {
        if (kNode != null) {
            LinkedList<KSlimEdge> nonLoopEdges = new LinkedList<KSlimEdge>();
            for (KSlimNode.IncEntry edgeEntry : kNode.getIncidence()) {
                // ignore loops over a single node
                if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT
                        && edgeEntry.getEdge().getSource().getId()
                        != edgeEntry.getEdge().getTarget().getId()) {
                    nonLoopEdges.add(edgeEntry.getEdge());
                }
            }
            return nonLoopEdges;
        } else {
            return null;
        }
    }

    /**
     * Adds a new cross-layer connection with given target.
     * 
     * @param edge the edge between the two layout nodes
     * @param target target layer element
     */
    public void addOutgoing(final KEdge edge, final LayerElement target) {
        addOutgoing(edge, target, null, null);
    }

    /**
     * Adds a new cross-layer connection with given target.
     * 
     * @param edge the edge between the two layout nodes
     * @param target target layer element
     * @param sourcePort the source port
     * @param targetPort the target port
     */
    public void addOutgoing(final KEdge edge, final LayerElement target, final KPort sourcePort,
            final KPort targetPort) {
        LayerConnection connection = new LayerConnection(edge, this, sourcePort, target, targetPort);
        this.outgoing.add(connection);
        target.incoming.add(connection);
    }

    /**
     * Applies the layout of this layer element to the contained object and
     * updates position information according to given offset values.
     * 
     * @param offset offset to be added to this element's position
     * @param insets insets of the containing parent layout node
     */
    public void applyLayout(final KPoint offset, final KInsets insets) {
        position.setX(position.getX() + offset.getX());
        position.setY(position.getY() + offset.getY());
        if (elemObj instanceof KNode) {
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(elemObj);
            shapeLayout.setXpos(position.getX() + posOffset.getX());
            shapeLayout.setYpos(position.getY() + posOffset.getY());
        } else if (elemObj instanceof KPort) {
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(elemObj);
            switch (LayoutOptions.getEnum(shapeLayout, PortSide.class)) {
            case NORTH:
                if (layer.getLayeredGraph().getExternalPortConstraints() == PortConstraints.FIXED_POS) {
                    shapeLayout.setXpos(position.getX());
                } else {
                    shapeLayout.setXpos(position.getX() + insets.getLeft());
                }
                shapeLayout.setYpos(position.getY());
                break;
            case EAST:
                shapeLayout.setXpos(position.getX() + insets.getLeft() + insets.getRight());
                if (layer.getLayeredGraph().getExternalPortConstraints() == PortConstraints.FIXED_POS) {
                    shapeLayout.setYpos(position.getY());
                } else {
                    shapeLayout.setYpos(position.getY() + insets.getTop());
                }
                break;
            case SOUTH:
                if (layer.getLayeredGraph().getExternalPortConstraints() == PortConstraints.FIXED_POS) {
                    shapeLayout.setXpos(position.getX());
                } else {
                    shapeLayout.setXpos(position.getX() + insets.getLeft());
                }
                shapeLayout.setYpos(position.getY() + insets.getTop() + insets.getBottom());
                break;
            case WEST:
                shapeLayout.setXpos(position.getX());
                if (layer.getLayeredGraph().getExternalPortConstraints() == PortConstraints.FIXED_POS) {
                    shapeLayout.setYpos(position.getY());
                } else {
                    shapeLayout.setYpos(position.getY() + insets.getTop());
                }
                break;
            }
        }
    }

    /**
     * Gets the port constraints for this layer element.
     * 
     * @return the port constraints
     */
    public PortConstraints getPortConstraints() {
        return portConstraints;
    }

    /**
     * Gets the rank width of this layer element.
     * 
     * @return the rank width
     */
    public int getRankWidth() {
        return rankWidth;
    }

    /**
     * Gets the list of incoming layer connections.
     * 
     * @return the incoming connections
     */
    public List<LayerConnection> getIncomingConnections() {
        return incoming;
    }

    /**
     * Gets the list of outgoing connections.
     * 
     * @return the outgoing connections
     */
    public List<LayerConnection> getOutgoingConnections() {
        return outgoing;
    }

    /**
     * Gets the loops of this layer element.
     * 
     * @return list of element loops
     */
    public List<ElementLoop> getLoops() {
        return loops;
    }

    /**
     * Returns the width of this layer element.
     * 
     * @return the width
     */
    public float getRealWidth() {
        return realWidth;
    }

    /**
     * Returns the height of this layer element.
     * 
     * @return the height
     */
    public float getRealHeight() {
        return realHeight;
    }

    /**
     * Sets the crosswise position for this layer element, considering all edges
     * that are routed before this element.
     * 
     * @param pos new crosswise position
     * @param minDist minimal distance for routed edges
     */
    public void setCrosswisePos(final float pos, final float minDist) {
        LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();
        if (layoutDirection == LayoutDirection.DOWN) {
            position.setX(pos + westRanks * minDist);
        } else {
            position.setY(pos + northRanks * minDist);
        }
    }

    /**
     * Gets the total crosswise dimension of this layer element with routed
     * edges.
     * 
     * @param minDist minimal distance for routed edges
     * @return total crosswise dimension with routed edges
     */
    public float getTotalCrosswiseDim(final float minDist) {
        if (totalCrosswiseDim < 0.0f) {
            LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();
            if (layoutDirection == LayoutDirection.DOWN) {
                totalCrosswiseDim = (westRanks + eastRanks) * minDist + realWidth;
            } else {
                totalCrosswiseDim = (northRanks + southRanks) * minDist + realHeight;
            }
        }
        return totalCrosswiseDim;
    }

    /**
     * Gets the current position of this layer element.
     * 
     * @return the currently set position
     */
    public KPoint getPosition() {
        return position;
    }

    /**
     * Gets the current position offset of the contained node, or {@code null}
     * if the contained object is not a node.
     * 
     * @return the position offset
     */
    public KPoint getPosOffset() {
        return posOffset;
    }

    /**
     * Gets the object contained in this layer element.
     * 
     * @return the element object
     */
    public KGraphElement getElemObj() {
        return elemObj;
    }

    /**
     * Gets the KIELER node associated with this layer element.
     * 
     * @return the KIELER node
     */
    public KSlimNode getKNode() {
        return kNode;
    }

    /**
     * Clears the internal map of port ranks.
     */
    public void clearPortRanks() {
        portRanks = null;
    }

    /**
     * Gets the number of edges that are routed in front of this element.
     * 
     * @return number of edges in front
     */
    public int getEdgesFront() {
        LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();
        return layoutDirection == LayoutDirection.DOWN ? northRanks : westRanks;
    }

    /**
     * Gets the number of edges that are routed in the back of this element.
     * 
     * @return number of edges in the back
     */
    public int getEdgesBack() {
        LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();
        return layoutDirection == LayoutDirection.DOWN ? southRanks : eastRanks;
    }

    /**
     * Gets the port rank for a given port.
     * 
     * @param port port for which the rank shall be obtained
     * @param forward if true, ranks are determined for a forward layer sweep,
     *            else for a backwards layer sweep
     * @return port rank, or 0 if {@code port == null}
     */
    public int getPortRank(final KPort port, final boolean forward) {
        if (port != null) {
            if (portRanks == null) {
                calcPortRanks(forward);
            }
            return portRanks.get(port).intValue();
        } else {
            return 0;
        }
    }

    /**
     * Determines the rank of each port of a layout node.
     * 
     * @param forward if true, ranks are determined for a forward layer sweep,
     *            else for a backwards layer sweep
     */
    private void calcPortRanks(final boolean forward) {
        LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();
        portRanks = new HashMap<KPort, Integer>();

        if (elemObj instanceof KNode) {
            // sort all ports by their relative position
            KPort[] portArray = ((KNode) elemObj).getPorts().toArray(new KPort[0]);
            Arrays.sort(portArray, new KimlLayoutUtil.PortComparator(forward, layoutDirection));
            // set the ranks in the newly sorted list
            for (int i = 0; i < portArray.length; i++) {
                portRanks.put(portArray[i], Integer.valueOf(i));
            }
        } else if (elemObj instanceof KPort) {
            portRanks.put((KPort) elemObj, Integer.valueOf(0));
        }
    }

    /**
     * Gets a list of combined element and port ranks for all incoming or for
     * all outgoing connections.
     * 
     * @param forward if true, only incoming connections are considered, else
     *            only outgoing connections are considered
     * @return list of ranks for the specified connections
     */
    public List<Integer> getConnectionRanks(final boolean forward) {
        List<Integer> connectionRanks = new LinkedList<Integer>();
        if (forward) {
            if (elemObj instanceof KEdge && linearSegment.hasPreceding(this)) {
                // only the preceding element of the linear segment may be
                // considered
                for (LayerConnection connection : incoming) {
                    LayerElement source = connection.getSourceElement();
                    if (source.elemObj instanceof KEdge) {
                        connectionRanks.add(Integer.valueOf(source.rank));
                        break;
                    }
                }
            } else {
                for (LayerConnection connection : incoming) {
                    if (connection.getSourcePort() != null) {
                        // the source is a node with ports or a port
                        connectionRanks.add(Integer.valueOf(connection.getSourceElement().getPortRank(
                                connection.getSourcePort(), true)
                                + connection.getSourceElement().rank));
                    } else {
                        // the source is a node without ports or an edge
                        connectionRanks.add(Integer.valueOf(connection.getSourceElement().rank));
                    }
                }
            }
        } else {
            if (elemObj instanceof KEdge && linearSegment.hasFollowing(this)) {
                // only the following element of the linear segment may be
                // considered
                for (LayerConnection connection : outgoing) {
                    LayerElement target = connection.getTargetElement();
                    if (target.elemObj instanceof KEdge) {
                        connectionRanks.add(Integer.valueOf(target.rank));
                        break;
                    }
                }
            } else {
                for (LayerConnection connection : outgoing) {
                    if (connection.getTargetPort() != null) {
                        // the target is a node with ports or a port
                        connectionRanks.add(Integer.valueOf(connection.getTargetElement().getPortRank(
                                connection.getTargetPort(), false)
                                + connection.getTargetElement().rank));
                    } else {
                        // the target is a node without ports or an edge
                        connectionRanks.add(Integer.valueOf(connection.getTargetElement().rank));
                    }
                }
            }
        }
        return connectionRanks;
    }

    /**
     * Gets a list of combined element and port ranks of connections sorted by
     * the port to which the connection is attached. This only works if the
     * element object is a layout node. The method is expected to give at least
     * an empty list for each port in the contained node group.
     * 
     * @param forward if true, only incoming connections are considered, else
     *            only outgoing connections are considered
     * @return list of connection ranks for each port of this layout node, or
     *         null if the contained object is not a layout node
     */
    public Map<KPort, List<Integer>> getConnectionRanksByPort(final boolean forward) {
        if (elemObj instanceof KNode) {
            KNode node = (KNode) elemObj;
            Map<KPort, List<Integer>> connectionRankMap = new LinkedHashMap<KPort, List<Integer>>();
            for (KPort port : node.getPorts()) {
                connectionRankMap.put(port, new LinkedList<Integer>());
            }

            if (forward) {
                for (LayerConnection connection : incoming) {
                    KPort targetPort = connection.getTargetPort();
                    if (targetPort != null) {
                        List<Integer> portList = connectionRankMap.get(targetPort);
                        if (connection.getSourcePort() != null) {
                            // the source is a node or a port
                            portList.add(Integer.valueOf(connection.getSourceElement().getPortRank(
                                    connection.getSourcePort(), forward)
                                    + connection.getSourceElement().rank));
                        } else {
                            // the source is an edge
                            portList.add(Integer.valueOf(connection.getSourceElement().rank));
                        }
                    }
                }
            } else {
                for (LayerConnection connection : outgoing) {
                    KPort sourcePort = connection.getSourcePort();
                    if (sourcePort != null) {
                        List<Integer> portList = connectionRankMap.get(sourcePort);
                        if (connection.getTargetPort() != null) {
                            // the target is a node or a port
                            portList.add(Integer.valueOf(connection.getTargetElement().getPortRank(
                                    connection.getTargetPort(), forward)
                                    + connection.getTargetElement().rank));
                        } else {
                            // the target is an edge
                            portList.add(Integer.valueOf(connection.getTargetElement().rank));
                        }
                    }
                }
            }

            return connectionRankMap;
        } else {
            return null;
        }
    }

    /**
     * Comparator used to sort ports using a list of abstract ranks.
     */
    private static class DirectedPortComparator implements Comparator<KPort>, Serializable {
        /** the serial version UID. */
        private static final long serialVersionUID = 8667388280121765908L;
        /** abstract ranks for some ports. */
        private Map<KPort, Double> abstractPortRanks;
        private boolean forward, definedFirst;

        /**
         * Creates a port comparator for a single list of abstract ranks.
         * 
         * @param theabstractPortRanks abstract ranks for some ports
         * @param theforward indicates whether to use the ranks in forward
         *            direction
         * @param thedefinedFirst indicates whether to put ports with defined
         *            abstract rank before ports with undefined abstract rank
         */
        DirectedPortComparator(final Map<KPort, Double> theabstractPortRanks,
                final boolean theforward, final boolean thedefinedFirst) {
            this.abstractPortRanks = theabstractPortRanks;
            this.forward = theforward;
            this.definedFirst = thedefinedFirst;
        }

        /**
         * {@inheritDoc}
         */
        public int compare(final KPort port1, final KPort port2) {
            Double arank1 = abstractPortRanks.get(port1);
            Double arank2 = abstractPortRanks.get(port2);
            if (arank1 == null && arank2 == null) {
                int rank1 = LayoutOptions.getInt(KimlLayoutUtil.getShapeLayout(port1),
                        LayoutOptions.PORT_RANK);
                int rank2 = LayoutOptions.getInt(KimlLayoutUtil.getShapeLayout(port2),
                        LayoutOptions.PORT_RANK);
                return rank1 - rank2;
            } else if (arank1 == null) {
                return definedFirst ? 1 : -1;
            } else if (arank2 == null) {
                return definedFirst ? -1 : 1;
            } else {
                return forward ? arank1.compareTo(arank2) : arank2.compareTo(arank1);
            }
        }
    }

    /**
     * Sorts the ports on each side of the related node according to the given
     * abstract ranks.
     * 
     * @param abstractPortRanks abstract port ranks
     * @param isoutgoing if true, the abstract ranks are assumed to be for
     *            outgoing connections, else for incoming connections
     */
    public void sortPorts(final Map<KPort, Double> abstractPortRanks, final boolean isoutgoing) {
        boolean vertical = layer.getLayeredGraph().getLayoutDirection() == LayoutDirection.DOWN;
        Arrays.sort(northPorts, new DirectedPortComparator(abstractPortRanks, vertical ? !isoutgoing
                : isoutgoing, !isoutgoing));
        Arrays.sort(eastPorts, new DirectedPortComparator(abstractPortRanks, vertical ? !isoutgoing
                : isoutgoing, vertical ? !isoutgoing : isoutgoing));
        Arrays.sort(southPorts, new DirectedPortComparator(abstractPortRanks, vertical ? !isoutgoing
                : isoutgoing, vertical ? !isoutgoing : isoutgoing));
        Arrays.sort(westPorts, new DirectedPortComparator(abstractPortRanks, vertical ? !isoutgoing
                : isoutgoing, isoutgoing));
        assignPortRanks();
    }

    /**
     * Comparator used to sort ports using two lists of abstract ranks.
     */
    private static class SymmetricPortComparator implements Comparator<KPort>, Serializable {
        /** the serial version UID. */
        private static final long serialVersionUID = -2258577968632647502L;
        private Map<KPort, Double> abstractPortRanks1, abstractPortRanks2;
        private boolean list1First, firstForward;

        SymmetricPortComparator(final Map<KPort, Double> theabstractPortRanks1,
                final Map<KPort, Double> theabstractPortRanks2, final boolean thelist1First,
                final boolean thefirstForward) {
            this.abstractPortRanks1 = theabstractPortRanks1;
            this.abstractPortRanks2 = theabstractPortRanks2;
            this.list1First = thelist1First;
            this.firstForward = thefirstForward;
        }

        /**
         * {@inheritDoc}
         */
        public int compare(final KPort port1, final KPort port2) {
            Double a1rank1 = abstractPortRanks1.get(port1);
            Double a2rank1 = abstractPortRanks2.get(port1);
            Double a1rank2 = abstractPortRanks1.get(port2);
            Double a2rank2 = abstractPortRanks2.get(port2);
            if (a1rank1 != null && a1rank2 != null) {
                return list1First && firstForward || !list1First && !firstForward ? a1rank1
                        .compareTo(a1rank2) : a1rank2.compareTo(a1rank1);
            } else if (a1rank1 != null && a2rank2 != null) {
                return list1First ? -1 : 1;
            } else if (a2rank1 != null && a1rank2 != null) {
                return list1First ? 1 : -1;
            } else if (a2rank1 != null && a2rank2 != null) {
                return list1First && !firstForward || !list1First && firstForward ? a2rank1
                        .compareTo(a2rank2) : a2rank2.compareTo(a2rank1);
            } else if (a1rank1 != null || a2rank1 != null) {
                return 1;
            } else if (a1rank2 != null || a2rank2 != null) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Sorts the ports on each side of the related node according to the given
     * abstract ranks.
     * 
     * @param outgoingPortRanks ranks for ports with outgoing connections
     * @param incomingPortRanks ranks for ports with incoming connections
     */
    public void sortPorts(final Map<KPort, Double> outgoingPortRanks,
            final Map<KPort, Double> incomingPortRanks) {
        boolean vertical = layer.getLayeredGraph().getLayoutDirection() == LayoutDirection.DOWN;
        Arrays.sort(northPorts, new SymmetricPortComparator(outgoingPortRanks, incomingPortRanks, false,
                vertical));
        Arrays.sort(eastPorts, new SymmetricPortComparator(outgoingPortRanks, incomingPortRanks,
                !vertical, true));
        Arrays.sort(southPorts, new SymmetricPortComparator(outgoingPortRanks, incomingPortRanks,
                !vertical, true));
        Arrays.sort(westPorts, new SymmetricPortComparator(outgoingPortRanks, incomingPortRanks, true,
                !vertical));
        assignPortRanks();
    }

    /**
     * Assigns ranks to the ports of the related node according to the order of
     * ports in the port lists.
     */
    private void assignPortRanks() {
        int portrank = 0;
        for (KPort port : northPorts) {
            LayoutOptions.setInt(KimlLayoutUtil.getShapeLayout(port), LayoutOptions.PORT_RANK,
                    portrank++);
        }
        for (KPort port : eastPorts) {
            LayoutOptions.setInt(KimlLayoutUtil.getShapeLayout(port), LayoutOptions.PORT_RANK,
                    portrank++);
        }
        for (KPort port : southPorts) {
            LayoutOptions.setInt(KimlLayoutUtil.getShapeLayout(port), LayoutOptions.PORT_RANK,
                    portrank++);
        }
        for (KPort port : westPorts) {
            LayoutOptions.setInt(KimlLayoutUtil.getShapeLayout(port), LayoutOptions.PORT_RANK,
                    portrank++);
        }
    }

    /**
     * Determines placements for the ports of the related node, based on the
     * internally stored order. This order should be compatible to the assigned
     * rank of each port.
     */
    public void placePorts() {
        if (elemObj instanceof KNode) {
            KNode node = (KNode) elemObj;
            float width = realWidth;
            float height = realHeight;
            if (portConstraints != PortConstraints.FIXED_POS) {
                placePorts(northPorts, 0.0f, 0.0f, false, true, true, width);
                placePorts(eastPorts, width, 0.0f, true, true, false, height);
                placePorts(southPorts, width, height, false, false, false, width);
                placePorts(westPorts, 0.0f, height, true, false, true, height);
            }

            // determine new bounds for the contained node
            float minX = 0.0f, minY = 0.0f, maxX = width, maxY = height;
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                minX = Math.min(minX, portLayout.getXpos());
                minY = Math.min(minY, portLayout.getYpos());
                maxX = Math.max(maxX, portLayout.getXpos() + portLayout.getWidth());
                maxY = Math.max(maxY, portLayout.getYpos() + portLayout.getHeight());
            }
            posOffset.setX(-minX);
            posOffset.setY(-minY);
            realWidth = maxX;
            realHeight = maxY;
        }
    }

    /**
     * Determines placements for the given ports, equally distributing them on a
     * horizontal or vertical line.
     * 
     * @param ports ports to place
     * @param startX horizontal offset for placement
     * @param startY vertical offset for placement
     * @param vertical indicates whether ports shall be placed vertically
     * @param forward indicates whether ports shall be placed in positive
     *            direction from the starting point
     * @param subPortDim indicates whether port dimension shall be subtracted
     *            from each position
     * @param length length of the line on which ports shall be placed
     */
    private static void placePorts(final KPort[] ports, final float startX, final float startY,
            final boolean vertical, final boolean forward, final boolean subPortDim,
            final float length) {
        float pos = vertical ? startY : startX;
        float incr = length / (ports.length + 1);
        if (!forward) {
            incr = -incr;
        }
        for (KPort port : ports) {
            pos += incr;
            KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
            portLayout.setXpos(vertical ? startX - (subPortDim ? portLayout.getWidth() : 0) : pos);
            portLayout.setYpos(vertical ? pos : startY - (subPortDim ? portLayout.getHeight() : 0));
        }
    }

    /**
     * Sets the rank.
     *
     * @param therank the rank to set
     */
    public void setRank(final int therank) {
        this.rank = therank;
    }

    /**
     * Returns the rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the linear segment.
     *
     * @param thelinearSegment the linear segment to set
     */
    public void setLinearSegment(final LinearSegment thelinearSegment) {
        this.linearSegment = thelinearSegment;
    }

    /**
     * Returns the linear segment.
     *
     * @return the linear segment
     */
    public LinearSegment getLinearSegment() {
        return linearSegment;
    }

    /**
     * Sets the ranks for a specific side.
     *
     * @param ranks the ranks to set
     * @param side the side for which the ranks are set
     */
    public void setRanks(final int ranks, final PortSide side) {
        switch (side) {
        case NORTH:
            this.northRanks = ranks;
            break;
        case EAST:
            this.eastRanks = ranks;
            break;
        case SOUTH:
            this.southRanks = ranks;
            break;
        case WEST:
            this.westRanks = ranks;
            break;
        }
    }

    /**
     * Returns the ranks for a specific side.
     *
     * @param side the side for which ranks are returned
     * @return the ranks for the given side
     */
    public int getRanks(final PortSide side) {
        switch (side) {
        case NORTH:
            return northRanks;
        case EAST:
            return eastRanks;
        case SOUTH:
            return southRanks;
        case WEST:
            return westRanks;
        }
        return 0;
    }

}
