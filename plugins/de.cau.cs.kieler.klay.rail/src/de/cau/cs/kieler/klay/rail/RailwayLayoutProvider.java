/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.rail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeJoiner;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeSplitter;
import de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.rail.impl.RailwayEdgeRouter;
import de.cau.cs.kieler.klay.rail.impl.RailwayNetworkSimplexLayerer;
import de.cau.cs.kieler.klay.rail.impl.RailwayNodePlacer;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

/**
 * 
 * Provider class for railway layout.
 * 
 * @author jjc
 */
public class RailwayLayoutProvider extends AbstractLayoutProvider {

    /** phase 1: cycle breaking module. */
    private ILayoutPhase cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayoutPhase layerer = new RailwayNetworkSimplexLayerer();
    /** phase 3: node placement module. */
    private ILayoutPhase nodePlacer = new RailwayNodePlacer();
    /** phase 4: Edge routing module. */
    private ILayoutPhase edgeRouter = new RailwayEdgeRouter();

    /**
     * Proper layering module. Since the phases used in this algorithm do not have any
     * dependencies, this is hardcoded into the layout provider.
     */
    private ILayoutProcessor edgeSplitter = new LongEdgeSplitter();
    private ILayoutProcessor reversedEdgeRestorer = new ReversedEdgeRestorer();
    private ILayoutProcessor edgeJoiner = new LongEdgeJoiner();

    private static final int SWITCH_PORTS = 3;

    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Railway layout", 1);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // transform the input graph
        KRailGraphImporter graphImporter = new KRailGraphImporter();
        LayeredGraph layeredGraph = graphImporter.importGraph(layoutNode);

        setOptions(layeredGraph, layoutNode, parentLayout);

        // perform the actual layout
        layout(layeredGraph, progressMonitor.subTask(1));
        // apply the layout results to the original graph
        graphImporter.applyLayout(layeredGraph);

        progressMonitor.done();
    }

    /**
     * Set layout options for the layered graph.
     * 
     * @param layeredGraph
     *            a new layered graph
     * @param parent
     *            the original parent node
     * @param parentLayout
     *            the layout data for the parent node
     */
    private void setOptions(final LayeredGraph layeredGraph, final KNode parent,
            final KShapeLayout parentLayout) {
        // set object spacing option
        float objSpacing = parentLayout.getProperty(LayoutOptions.SPACING);
        if (objSpacing >= 0) {
            layeredGraph.setProperty(Properties.OBJ_SPACING, objSpacing);
        }

        // set border spacing option
        float borSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borSpacing >= 0) {
            layeredGraph.setProperty(Properties.BOR_SPACING, borSpacing);
        }

        layeredGraph.setProperty(Properties.RANDOM, new Random(1));
    }

    /**
     * Perform the five phases of the layered layouter.
     * 
     * @param layeredGraph
     *            the graph to lay out.
     * @param themonitor
     *            a progress monitor, or {@code null}
     */
    public void layout(final LayeredGraph layeredGraph, final IKielerProgressMonitor themonitor) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Layered layout phases", 1 + 1 + 1 + 1 + 1);
        List<LNode> nodes = layeredGraph.getLayerlessNodes();

        System.out.println("Validating ...");
        validateRailwayGraph(nodes);
        System.out.println("Redirecting ...");
        redirectEdges(nodes);
        System.out.println("Ready to start layout.");

        // phase 1: cycle breaking
        cycleBreaker.reset(monitor.subTask(1));
        cycleBreaker.process(layeredGraph);
        // phase 2: layering
        layerer.reset(monitor.subTask(1));
        layerer.process(layeredGraph);
        // intermediate phase: edge splitting
        edgeSplitter.reset(monitor.subTask(1));
        edgeSplitter.process(layeredGraph);
        // phase 3: node placement
        nodePlacer.reset(monitor.subTask(1));
        nodePlacer.process(layeredGraph);
        // subphase: arrange ports
        System.out.println("Arranging ports ...");
        arrangePorts(nodes);
        // phase 4: edge routing
        edgeRouter.reset(monitor.subTask(1));
        edgeRouter.process(layeredGraph);
        
/*
    Proposal by CDS:
        reversedEdgeRestorer.reset(monitor.subTask(1));
        reversedEdgeRestorer.process(layeredGraph);
        
        edgeJoiner.reset(monitor.subTask(1));
        edgeJoiner.process(layeredGraph);
*/
        swapBackSwappedEdges();
        monitor.done();
    }

    /**
     * Validates the graph against the requirements of a railway graph. This has to be executed
     * before any processing or layouting is done. When this was executed, we may often access the
     * first member of some lists, because we know then that they contain only one element.
     * 
     * @param thenodes
     *            A list of nodes to validate
     */
    public void validateRailwayGraph(final List<LNode> thenodes) {
        int foundEntryNodes = 0;
        for (LNode lNode : thenodes) {
            if (lNode.getProperty(Properties.ENTRY_POINT).booleanValue()) {
                foundEntryNodes++;
            }
            if (lNode.getProperty(Properties.NODE_TYPE).equals(NodeType.BREACH_OR_CLOSE)) {
                if (lNode.getPorts().size() != 1) {
                    System.out.println("A breach or close may only have one port.");
                    throw new IllegalArgumentException("A breach or close may only have one port.");
                }
            }
            if (lNode.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)
                    || lNode.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_RIGHT)) {
                if (lNode.getPorts().size() != SWITCH_PORTS) {
                    System.out.println("A switch has to have exactly " + SWITCH_PORTS + " ports.");
                    throw new IllegalArgumentException("A switch has to have exactly "
                            + SWITCH_PORTS + " ports.");
                }
            }
            for (LPort lPort : lNode.getPorts()) {
                if (lPort.getDegree() != 1) {
                    System.out.println("Each port may only have one edge");
                    throw new IllegalArgumentException("Each port may only have one edge");
                }
            }
            // TODO: circle detection here or in redirection?
        }
        if (foundEntryNodes != 1) {
            throw new IllegalArgumentException("Currently the graph needs exactly one entry point.");
        }
    }

    /**
     * Method to arrange the ports of all nodes according to their turning angle.
     * 
     * @param thenodes
     *            A list containing all nodes
     */
    private void arrangePorts(final List<LNode> thenodes) {
        // CHECKSTYLEOFF MagicNumber
        // The numbers in the switch cases represent turning angle multipliers, would be annoying to
        // introduce them as constants
        for (LNode node : thenodes) {
            double nodeX = node.getSize().x;
            double nodeY = node.getSize().y;
            KPort kPort = (KPort) node.getPorts().get(0).getProperty(Properties.ORIGIN);
            KShapeLayout portLayout = kPort.getData(KShapeLayout.class);
            double portWidth = portLayout.getWidth();
            double portHeight = portLayout.getHeight();
            if (node.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)) {
                for (LPort port : node.getPorts()) {
                    switch (node.getProperty(Properties.SWITCH_ROTATION)) {
                    case 0:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = nodeX * (3.0 / 4.0);
                            port.getPosition().y = 0;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = nodeX;
                            port.getPosition().y = nodeY / 2.0;
                        } else {
                            port.getPosition().x = 0;
                            port.getPosition().y = nodeY / 2.0;
                        }
                        break;
                    case 2:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = 0;
                            port.getPosition().y = nodeY / 2.0;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = nodeX * (1.0 / 4.0);
                            port.getPosition().y = 0;
                        } else {
                            port.getPosition().x = nodeX * (3.0 / 4.0);
                            port.getPosition().y = nodeY;
                        }
                        break;
                    case 3:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = nodeX * (1.0 / 4.0);
                            port.getPosition().y = nodeY;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = 0.0;
                            port.getPosition().y = nodeY / 2.0;
                        } else {
                            port.getPosition().x = nodeX;
                            port.getPosition().y = nodeY / 2.0;
                        }
                        break;
                    case 5:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = nodeX;
                            port.getPosition().y = nodeY / 2.0;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = nodeX * (3.0 / 4.0);
                            port.getPosition().y = nodeY;
                        } else {
                            port.getPosition().x = nodeX * (1.0 / 4.0);
                            port.getPosition().y = 0;
                        }
                        break;
                    }
                    if (port.getPosition().x == 0) {
                        port.getPosition().x -= portWidth;
                    } else if (port.getPosition().x < nodeX) {
                        port.getPosition().x -= portWidth / 2;
                    }
                    if (port.getPosition().y == 0) {
                        port.getPosition().y -= portHeight;
                    } else if (port.getPosition().y < nodeY) {
                        port.getPosition().y -= portHeight / 2;
                    }
                }
            } else if (node.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_RIGHT)) {
                for (LPort port : node.getPorts()) {
                    switch (node.getProperty(Properties.SWITCH_ROTATION)) {
                    case 0:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = nodeX * (3.0 / 4.0);
                            port.getPosition().y = nodeY;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = nodeX;
                            port.getPosition().y = nodeY / 2.0;
                        } else {
                            port.getPosition().x = 0;
                            port.getPosition().y = nodeY / 2.0;
                        }
                        break;
                    case 1:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = nodeX;
                            port.getPosition().y = nodeY / 2.0;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = nodeX * (3.0 / 4.0);
                            port.getPosition().y = 0;
                        } else {
                            port.getPosition().x = nodeX * (1.0 / 4.0);
                            port.getPosition().y = nodeY;
                        }
                        break;
                    case 3:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = nodeX * (1.0 / 4.0);
                            port.getPosition().y = 0;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = 0;
                            port.getPosition().y = nodeY / 2.0;
                        } else {
                            port.getPosition().x = nodeX;
                            port.getPosition().y = nodeY / 2.0;
                        }
                        break;
                    case 4:
                        if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                            port.getPosition().x = 0;
                            port.getPosition().y = nodeY / 2.0;
                        } else if (port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT)) {
                            port.getPosition().x = nodeX * (1.0 / 4.0);
                            port.getPosition().y = nodeY;
                        } else {
                            port.getPosition().x = nodeX * (3.0 / 4.0);
                            port.getPosition().y = 0;
                        }
                        break;
                    }
                    if (port.getPosition().x == 0) {
                        port.getPosition().x -= portWidth;
                    } else if (port.getPosition().x < nodeX) {
                        port.getPosition().x -= portWidth / 2;
                    }
                    if (port.getPosition().y == 0) {
                        port.getPosition().y -= portHeight;
                    } else if (port.getPosition().y < nodeY) {
                        port.getPosition().y -= portHeight / 2;
                    }
                }
            } else {
                node.getPorts().get(0).getPosition().y = nodeY / 2 - (portHeight / 2);
            }
        }
        // CHECKSTYLEON MagicNumber
    }

    /**
     * Method to correct the direction of all edges. Edges have to go out from the entry point, this
     * method will apply this to given graph.
     * 
     * @param thenodes
     *            A list of nodes to process
     */
    private void redirectEdges(final List<LNode> thenodes) {
        swappedEdges.clear();
        LPort entryPort = new LPort();
        HashMap<LPort, Boolean> visited = new HashMap<LPort, Boolean>();
        Queue<LPort> queue = new LinkedList<LPort>();
        for (LNode lNode : thenodes) {
            if (lNode.getProperty(Properties.ENTRY_POINT).booleanValue()) {
                // can do this because validation was executed earlier
                LNode entryNode = lNode;
                entryPort = entryNode.getPorts().get(0);
                if (!entryPort.getIncomingEdges().isEmpty()) {
                    // entry port has to be output since all edges
                    // are directed right bound coming from here
                    LEdge edge = entryPort.getIncomingEdges().get(0);
                    edge.reverse(false);
                    swappedEdges.add(edge);
                }
            }
            for (LPort lPort : lNode.getPorts()) {
                visited.put(lPort, false);
            }
        }
        queue.add(entryPort);
        visited.put(entryPort, true);
        while (!queue.isEmpty()) {
            LPort currentPort = queue.poll();
            LPort currentTarget = currentPort.getOutgoingEdges().get(0).getTarget();
            visited.put(currentTarget, true);
            LNode nextNode = currentTarget.getNode();
            for (LPort lPort : nextNode.getPorts()) {
                if (!visited.get(lPort)) {
                    if (lPort.getIncomingEdges().isEmpty()) {
                        LEdge edge = lPort.getIncomingEdges().get(0);
                        edge.reverse(false);
                        swappedEdges.add(edge);
                    }
                    queue.add(lPort);
                    visited.put(lPort, true);
                }
            }
        }
    }

    /** Holds all swapped edges for later reversing. */
    private List<LEdge> swappedEdges = new LinkedList<LEdge>();

    /**
     * Undo the swapping on all edges that were swapped.
     */
    private void swapBackSwappedEdges() {
        Iterator<LEdge> iter = swappedEdges.iterator();
        while (iter.hasNext()) {
            LEdge edge = iter.next();
            iter.remove();
            edge.reverse(false);
        }
    }
}
