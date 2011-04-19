/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Inserts dummy nodes to cope with northern and southern ports.
 * 
 * <p>For each node with northern or southern ports, dummy nodes are inserted in the node's
 * layer above or below the node. (for northern and southern ports, respectively) Assume
 * that a node has several northern ports. First, the ports are assembled in left-to-right
 * order according to their position. Then, the ports are partitioned into ports with
 * only incoming edges (in ports), ports with only outgoing edges (out ports) and ports
 * with both, incoming and outgoing edges (in/out ports). Note that this does not rely on
 * port type information.</p>
 * 
 * <p>In and out ports are now matched up left to right and right to left, respectively, as
 * long as the out port is right of the in port. In the example below, ports P1 and P6
 * will be matched, as will be ports P2 and P5. Ports P3 and P4 will not be matched since
 * P3 is an out port and not right of P4.</p>
 * 
 * <p>For each pair of matched ports, a dummy node is then inserted that is shared by both
 * ports. This leads to edges of both ports sharing the same y coordinate, which is fine
 * as long as the out port is right of the in port. When this is no longer the case,
 * ports cannot share a dummy node anymore. (the horizontal edge segments would overlap
 * and thus introduce ambiguity) Thus, P4 and P3 each get their own dummy node.</p>
 * 
 * <p>Once in and out ports are processed, in/out ports are given their own dummy node.</p>
 * 
 * <pre>
 *                      ------------------------------
 *                      |
 * ---------------------+----
 *                      |   |
 * ------------------   |   |   ----------------------
 *                  |   |   |   |
 * --------------   |   |   |   |   ------------------
 *              |   |   |   |   |   |
 *              P1  P2  P3  P4  P5  P6
 * </pre>
 * 
 * <p>Self-loops are a special case that is handled party by this processor. For this to
 * work, the {@link SelfLoopProcessor} must have been executed prior to this processor's
 * execution. Then, this processor correctly processes all kinds of self-loops involving
 * northern or southern ports as follows: (due to the {@link SelfLoopProcessor}, only the
 * cases detailled below must be handled)</p>
 * 
 * <ul>
 *   <li>West-north, west-south, north-east and south-east self-loops are handled just
 *     like every other connection.</li>
 *   <li>North-north and south-south self-loops are handled by creating a dummy node just
 *     for that self-loop.</li>
 *   <li>North-south self-loops are handled by adding a northern and a southern dummy,
 *     both having just one port via which they are connected. At the moment, the port
 *     is created on the eastern side of the dummy node, but that could be handled more
 *     clerverly later.</li>
 * </ul>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; nodes have fixed port sides.</dd>
 *   <dt>Postcondition:</dt><dd>dummy nodes have been inserted for edges connected to
 *     ports on north and south sides, with layout groups and node successor constraints
 *     set.</dd>
 *   <dt>Slots:</dt><dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link PortOrderProcessor},
 *     {@link SelfLoopProcessor}</dd>
 * </dl>
 * 
 * @see NorthSouthPortPostprocessor
 * @see PortOrderProcessor
 * @see SelfLoopProcessor
 * @author cds
 */
public class NorthSouthPortPreprocessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Odd port side processing", 1);
        
        int pointer;
        List<LNode> northDummyNodes = new LinkedList<LNode>();
        List<LNode> southDummyNodes = new LinkedList<LNode>();
        
        // Iterate through the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // The pointer indicates the index of the current node while northern ports are
            // processed, and the index of the most recently inserted dummy while south ports
            // are processed
            pointer = -1;
            
            // Iterate through the nodes (use an array to avoid concurrent modification
            // exceptions)
            LNode[] nodeArray = layer.getNodes().toArray(new LNode[0]);
            for (LNode node : nodeArray) {
                pointer++;
                
                // We only care about non-dummy nodes with fixed port sides
                if (!node.getProperty(Properties.NODE_TYPE).equals(Properties.NodeType.NORMAL)
                        && node.getProperty(Properties.PORT_CONS).isSideFixed()) {
                    
                    continue;
                }
                
                // Nodes form their own layout unit
                node.setProperty(Properties.LAYER_LAYOUT_UNIT, node);
                
                // Clear the lists of northern and southern dummy nodes
                northDummyNodes.clear();
                southDummyNodes.clear();
                
                // Prepare a list of ports on the northern side, sorted from left
                // to right (when viewed in the diagram); create the appropriate
                // dummy nodes and assign them to the layer
                List<LPort> portList = new LinkedList<LPort>();
                for (LPort port : node.getPorts(PortSide.NORTH)) {
                    portList.add(port);
                }

                createDummyNodes(portList, northDummyNodes, southDummyNodes);
                
                int insertPoint = pointer;
                LNode successor = node;
                for (LNode dummy : northDummyNodes) {
                    dummy.setLayer(insertPoint, layer);
                    pointer++;
                    
                    // The dummy nodes form a layout unit identified by the node they
                    // were created from. In addition, the order of the dummy nodes must
                    // be fixed.
                    dummy.setProperty(Properties.LAYER_LAYOUT_UNIT, node);
                    dummy.setProperty(
                            Properties.LAYER_NODE_SUCCESSOR_CONSTRAINT,
                            successor);
                    
                    successor = dummy;
                }
                
                // Do the same for ports on the southern side; the list of ports must
                // be built in reversed order, since ports on the southern side are
                // listed from right to left
                portList.clear();
                for (LPort port : node.getPorts(PortSide.SOUTH)) {
                    portList.add(0, port);
                }
                
                createDummyNodes(portList, southDummyNodes, null);
                
                LNode predecessor = node;
                for (LNode dummy : southDummyNodes) {
                    dummy.setLayer(++pointer, layer);
                    
                    // The dummy nodes form a layout unit identified by the node they
                    // were created from. In addition, the order of the dummy nodes must
                    // be fixed.
                    dummy.setProperty(Properties.LAYER_LAYOUT_UNIT, node);
                    predecessor.setProperty(
                            Properties.LAYER_NODE_SUCCESSOR_CONSTRAINT,
                            dummy);
                    
                    predecessor = dummy;
                }
            }
        }
    }
    
    /**
     * Returns a list of dummy nodes for the given ports. The list of ports must be
     * sorted by position from left to right.
     * 
     * @param ports the list of ports to create dummy nodes for.
     * @param dummyNodes all created dummy nodes are added to this list.
     * @param opposingSideDummyNodes all dummy nodes created due to north-south self-loops
     *                               for the southern side are placed in this list. When
     *                               called for southern ports, this may be {@code null}.
     */
    private void createDummyNodes(final List<LPort> ports, final List<LNode> dummyNodes,
            final List<LNode> opposingSideDummyNodes) {
        
        // We'll assemble lists of ports with only incoming, ports with only outgoing
        // and ports with both, incoming and outgoing edges
        List<LPort> inPorts = new ArrayList<LPort>(ports.size());
        List<LPort> outPorts = new ArrayList<LPort>(ports.size());
        List<LPort> inOutPorts = new ArrayList<LPort>(ports.size());
        List<LEdge> sameSideSelfLoopEdges = new ArrayList<LEdge>(ports.size());
        List<LEdge> northSouthSelfLoopEdges = new ArrayList<LEdge>(ports.size());
        
        for (LPort port : ports) {
            boolean in = false;
            boolean out = false;
            
            // This is completely independent of ports being marked as INPUT or OUTPUT
            // ports to stay clear of problems related to cycle breaking. (INPUT ports
            // may have outgoing edges, OUTPUT ports may have incoming edges)
            for (LEdge edge : port.getEdges()) {
                // Check for self loops we'd be interested in
                if (edge.getSource().getNode() == edge.getTarget().getNode()) {
                    // Only do this for the source port
                    if (edge.getSource() == port) {
                        // Check which sides the ports are on
                        if (port.getSide() == edge.getTarget().getSide()) {
                            // Same side
                            sameSideSelfLoopEdges.add(edge);
                            continue;
                            
                        } else if (port.getSide() == PortSide.NORTH
                                && edge.getTarget().getSide() == PortSide.SOUTH) {
                            
                            // North->South self-loop. Due to the SelfLoopProcessor, a
                            // South->North self-loop cannot happen
                            northSouthSelfLoopEdges.add(edge);
                            continue;
                        }
                    }
                }
                
                // Once we get here, we haven't found a self-loop that would require
                // additional measures
                if (edge.getSource() == port) {
                    out = true;
                } else {
                    in = true;
                }
            }
            
            if (in && out) {
                inOutPorts.add(port);
            } else if (in) {
                inPorts.add(port);
            } else if (out) {
                outPorts.add(port);
            }
        }
        
        // First, create the dummy nodes that handle north->south self-loops. For now,
        // we always route north->south self-loops east to the node. This could later
        // change, though.
        for (LEdge edge : northSouthSelfLoopEdges) {
            createDummyNode(edge, dummyNodes, opposingSideDummyNodes, PortSide.EAST);
        }
        
        // Second, create the dummy nodes that handle same-side self-loops
        for (LEdge edge : sameSideSelfLoopEdges) {
            createDummyNode(edge, dummyNodes);
        }
        
        // Iterate through the lists of input and output ports while both lists still
        // have elements and while output ports are still located right of input ports.
        // While this is true, input and output ports may share the same dummy node
        int inPortsIndex = 0;
        int outPortsIndex = outPorts.size() - 1;
        
        while (inPortsIndex < inPorts.size() && outPortsIndex >= 0) {
            LPort inPort = inPorts.get(inPortsIndex);
            LPort outPort = outPorts.get(outPortsIndex);
            
            // If the out port is not right of the in port, they cannot share the same
            // dummy node anymore
            if (ports.indexOf(outPort) < ports.indexOf(inPort)) {
                break;
            }
            
            // Otherwise, create a dummy node for them
            createDummyNode(inPort, outPort, dummyNodes);
            
            inPortsIndex++;
            outPortsIndex--;
        }
        
        // Give the rest of input and output ports their dummy nodes
        while (inPortsIndex < inPorts.size()) {
            createDummyNode(inPorts.get(inPortsIndex), null, dummyNodes);
            inPortsIndex++;
        }
        
        while (outPortsIndex >= 0) {
            createDummyNode(null, outPorts.get(outPortsIndex), dummyNodes);
            outPortsIndex--;
        }
        
        // in / out ports get their own dummy nodes
        for (LPort inOutPort : inOutPorts) {
            createDummyNode(inOutPort, inOutPort, dummyNodes);
        }
    }
    
    /**
     * Creates a dummy node for the given ports. Edges going into {@code inPort} are
     * rerouted to the dummy node's input port. Edges leaving the {@code outPort} are
     * rerouted to the dummy node's output port. Both arguments may refer to the same
     * port. The dummy's port have their {@code ORIGIN} property set to the port whose
     * edges have been rerouted to them.
     * 
     * @param inPort the input port whose edges to reroute. May be {@code null}.
     * @param outPort the output port whose edges to reroute. May be {@code null}.
     * @param dummyNodes list the created dummy node should be added to.
     */
    private void createDummyNode(final LPort inPort, final LPort outPort,
            final List<LNode> dummyNodes) {
        
        LNode dummy = new LNode();
        dummy.setProperty(Properties.NODE_TYPE, Properties.NodeType.NORTH_SOUTH_PORT);
        dummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        // Input port
        if (inPort != null) {
            LPort dummyInputPort = new LPort();
            dummyInputPort.setProperty(Properties.ORIGIN, inPort);
            dummyInputPort.setType(PortType.INPUT);
            dummyInputPort.setSide(PortSide.WEST);
            dummyInputPort.setNode(dummy);
            
            // Reroute edges
            LEdge[] edgeArray = inPort.getEdges().toArray(new LEdge[0]);
            for (LEdge edge : edgeArray) {
                if (edge.getTarget() == inPort) {
                    edge.setTarget(dummyInputPort);
                }
            }
        }
        
        // Output port
        if (outPort != null) {
            LPort dummyOutputPort = new LPort();
            dummyOutputPort.setProperty(Properties.ORIGIN, outPort);
            dummyOutputPort.setType(PortType.OUTPUT);
            dummyOutputPort.setSide(PortSide.EAST);
            dummyOutputPort.setNode(dummy);
            
            // Reroute edges
            LEdge[] edgeArray = outPort.getEdges().toArray(new LEdge[0]);
            for (LEdge edge : edgeArray) {
                if (edge.getSource() == outPort) {
                    edge.setSource(dummyOutputPort);
                }
            }
        }
        
        dummyNodes.add(dummy);
    }
    
    /**
     * Creates a dummy node for the given non-north-south self-loop edge. The dummy node's
     * {@code ORIGIN} property is set to the edge. The dummy node has two ports, one for each
     * port the node was connected to. Their {@code ORIGIN} property is set accordingly.
     * 
     * @param selfLoop the self-loop edge.
     * @param dummyNodes list the created dummy node should be added to.
     */
    private void createDummyNode(final LEdge selfLoop, final List<LNode> dummyNodes) {
        LNode dummy = new LNode();
        dummy.setProperty(Properties.NODE_TYPE, Properties.NodeType.NORTH_SOUTH_PORT);
        dummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        dummy.setProperty(Properties.ORIGIN, selfLoop);
        
        // Input port
        LPort dummyInputPort = new LPort();
        dummyInputPort.setProperty(Properties.ORIGIN, selfLoop.getTarget());
        dummyInputPort.setType(PortType.INPUT);
        dummyInputPort.setSide(PortSide.WEST);
        dummyInputPort.setNode(dummy);
        
        // Output port
        LPort dummyOutputPort = new LPort();
        dummyOutputPort.setProperty(Properties.ORIGIN, selfLoop.getSource());
        dummyOutputPort.setType(PortType.OUTPUT);
        dummyOutputPort.setSide(PortSide.EAST);
        dummyOutputPort.setNode(dummy);
        
        // Disconnect the edge
        selfLoop.setSource(null);
        selfLoop.setTarget(null);
        
        dummyNodes.add(dummy);
    }
    
    /**
     * Creates two dummy nodes for the given north-south self-loop edge. Each dummy node has
     * only one port, on the specified side of the node. Their {@code ORIGIN} property is set
     * accordingly.
     * 
     * @param selfLoop the self-loop edge.
     * @param northDummyNodes list the created northern dummy node should be added to.
     * @param southDummyNodes list the created southern dummy node should be added to.
     * @param portSide on which sides on the dummy nodes the self-loop ports should be
     *                 placed.
     */
    private void createDummyNode(final LEdge selfLoop, final List<LNode> northDummyNodes,
            final List<LNode> southDummyNodes, final PortSide portSide) {
        
        // North dummy
        LNode northDummy = new LNode();
        northDummy.setProperty(Properties.NODE_TYPE, Properties.NodeType.NORTH_SOUTH_PORT);
        northDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        LPort northDummyOutputPort = new LPort();
        northDummyOutputPort.setProperty(Properties.ORIGIN, selfLoop.getSource());
        northDummyOutputPort.setType(PortType.OUTPUT);
        northDummyOutputPort.setSide(portSide);
        northDummyOutputPort.setNode(northDummy);
        
        // South dummy
        LNode southDummy = new LNode();
        southDummy.setProperty(Properties.NODE_TYPE, Properties.NodeType.NORTH_SOUTH_PORT);
        southDummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        LPort southDummyInputPort = new LPort();
        southDummyInputPort.setProperty(Properties.ORIGIN, selfLoop.getTarget());
        southDummyInputPort.setType(PortType.INPUT);
        southDummyInputPort.setSide(portSide);
        southDummyInputPort.setNode(southDummy);
        
        // Reroute the edge
        selfLoop.setSource(northDummyOutputPort);
        selfLoop.setTarget(southDummyInputPort);
        
        northDummyNodes.add(0, northDummy);
        southDummyNodes.add(southDummy);
    }
    
}
