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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.IGraphImporter;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.impl.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.impl.SimpleSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;
import de.cau.cs.kieler.klay.rail.options.NodeType;

/**
 * 
 * Provider class for railway layout.
 * 
 * @author jjc
 * 
 */
public class RailwayLayoutProvider extends AbstractLayoutProvider {

    // Strategies for the 5 phases of layered layout, assuming this
    // will use layered layout
    // This is still open, though

    /** phase 1: cycle breaking module. */
    private ICycleBreaker cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayerer layerer = new NetworkSimplexLayerer();
    /** phase 3: crossing minimization module. */
    private ICrossingMinimizer crossingMinimizer = new LayerSweepCrossingMinimizer();
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter = new SimpleSplineEdgeRouter();

    private static final int SWITCH_PORTS = 3;

    /**
     * Initialize default values for options.
     */
    public RailwayLayoutProvider() {
        setProperty(LayoutOptions.BORDER_SPACING, Properties.DEF_BORDER);
        setProperty(LayoutOptions.OBJ_SPACING, Properties.DEF_SPACING);
    }

    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Railway layout", 1);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // transform the input graph
        IGraphImporter graphImporter = new KRailGraphImporter(layoutNode);
        LayeredGraph layeredGraph = graphImporter.getGraph();

        setOptions(layeredGraph, layoutNode, parentLayout);

        // perform the actual layout
        layout(graphImporter, progressMonitor.subTask(1));
        // apply the layout results to the original graph
        graphImporter.applyLayout();

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
        float objSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (objSpacing >= 0) {
            layeredGraph.setProperty(Properties.OBJ_SPACING, objSpacing);
        }

        // set border spacing option
        float borSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borSpacing >= 0) {
            layeredGraph.setProperty(Properties.BOR_SPACING, borSpacing);
        }

    }

    /**
     * Perform the five phases of the layered layouter.
     * 
     * @param importer
     *            the graph importer
     * @param themonitor
     *            a progress monitor, or {@code null}
     */
    public void layout(final IGraphImporter importer, final IKielerProgressMonitor themonitor) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Layered layout phases", 1 + 1 + 1 + 1);
        LayeredGraph layeredGraph = importer.getGraph();
        List<LNode> nodes = importer.getImportedNodes();

        System.out.println("Validating ...");
        validateRailwayGraph(nodes);
        System.out.println("Redirecting ...");
        redirectEdges(nodes);
        System.out.println("Preprocessing ...");
        preprocess(nodes);
        System.out.println("Ready to start layout.");

        // phase 1: cycle breaking
        cycleBreaker.reset(monitor.subTask(1));
        cycleBreaker.breakCycles(nodes);
        // phase 2: layering
        layerer.reset(monitor.subTask(1));
        layerer.layer(nodes, layeredGraph);
        layeredGraph.splitEdges();
        // phase 3: crossing minimization
        // crossingMinimizer.reset(monitor.subTask(1));
        // crossingMinimizer.minimizeCrossings(layeredGraph);
        // phase 4: node placement
        nodePlacer.reset(monitor.subTask(1));
        nodePlacer.placeNodes(layeredGraph);
        // phase 5: edge routing
        edgeRouter.reset(monitor.subTask(1));
        edgeRouter.routeEdges(layeredGraph);

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
                if (lPort.getEdges().size() != 1) {
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
     * Method to correct the direction of all edges. Edges have to go out from the entry point, this
     * method will apply this to given graph.
     * 
     * @param thenodes
     *            A list of nodes to process
     */
    private void redirectEdges(final List<LNode> thenodes) {
        LPort entryPort = new LPort();
        HashMap<LPort, Boolean> visited = new HashMap<LPort, Boolean>();
        Queue<LPort> queue = new LinkedList<LPort>();
        for (LNode lNode : thenodes) {
            if (lNode.getProperty(Properties.ENTRY_POINT).booleanValue()) {
                // can do this because validation was executed earlier
                LNode entryNode = lNode;
                entryPort = entryNode.getPorts().get(0);
                if (entryPort.getType().equals(PortType.INPUT)) {
                    // entry port has to be output since all edges
                    // are directed right bound coming from here
                    swapPorts(entryPort.getEdges().get(0));
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
            LPort currentTarget = currentPort.getEdges().get(0).getTarget();
            visited.put(currentTarget, true);
            LNode nextNode = currentTarget.getNode();
            for (LPort lPort : nextNode.getPorts()) {
                if (!visited.get(lPort)) {
                    if (lPort.getType().equals(PortType.INPUT)) {
                        swapPorts(lPort.getEdges().get(0));
                    }
                    queue.add(lPort);
                    visited.put(lPort, true);
                }
            }
        }
    }

    /**
     * Method to apply general conventions of the railway layout to nodes. These are, for example,
     * the port positions on a node.
     * 
     * @param thenodes
     *            A list of nodes to process
     */
    private void preprocess(final List<LNode> thenodes) {
        for (LNode lNode : thenodes) {
            if (lNode.getProperty(Properties.ENTRY_POINT).booleanValue()) {
                List<LPort> ports = lNode.getPorts();
                // can do this because validation was done earlier
                LPort port = ports.get(0);
                port.setSide(PortSide.EAST);
                port.getPos().x = port.getNode().getSize().x;
                port.getPos().y = port.getNode().getSize().y / 5;
            } else if (lNode.getProperty(Properties.NODE_TYPE).equals(NodeType.BREACH_OR_CLOSE)) {
                List<LPort> ports = lNode.getPorts();
                // same as above
                LPort port = ports.get(0);
                if (port.getType().equals(PortType.INPUT)) {
                    port.setSide(PortSide.WEST);
                    port.getPos().x = 0;
                } else if (port.getType().equals(PortType.OUTPUT)) {
                    port.setSide(PortSide.EAST);
                    port.getPos().x = port.getNode().getSize().x;
                } else {
                    throw new IllegalArgumentException(
                            "Railway layout doesn't allow undefined ports.");
                }
                port.getPos().y = port.getNode().getSize().y / 5;
            } else if (lNode.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)) {
                List<LPort> ports = lNode.getPorts();
                // same as above
                int inputPorts = 0;
                int outputPorts = 0;
                for (LPort lPort : ports) {
                    if (lPort.getType().equals(PortType.INPUT)) {
                        inputPorts++;
                    } else if (lPort.getType().equals(PortType.OUTPUT)) {
                        outputPorts++;
                    } else {
                        throw new IllegalArgumentException(
                                "Railway layout doesn't allow undefined ports.");
                    }
                }
                boolean flipped = (inputPorts == 2);
                int flipOffset = 0;
                for (LPort lPort : ports) {
                    if (lPort.getType().equals(PortType.INPUT)) {
                        lPort.setSide(PortSide.WEST);
                        lPort.getPos().x = 0;
                        if (flipped) {
                            lPort.getPos().y = lPort.getNode().getSize().y / (7 - flipOffset);
                            flipOffset = 3;
                        } else {
                            lPort.getPos().y = lPort.getNode().getSize().y / 5;
                        }
                    } else  {
                        lPort.setSide(PortSide.EAST);
                        lPort.getPos().x = lPort.getNode().getSize().x;
                        if (flipped) {
                            lPort.getPos().y = lPort.getNode().getSize().y / 5;
                        } else {
                            lPort.getPos().y = lPort.getNode().getSize().y / (6 + flipOffset);
                            flipOffset = 3;
                        }
                    }
                }
            }
            // TODO: handling switches
        }
    }

    /**
     * Swaps the direction of an edges and changes the port types while doing so.
     * 
     * @param theedge
     *            The edge to use.
     */
    private void swapPorts(final LEdge theedge) {
        LPort swap = theedge.getSource();
        swap.setType(PortType.INPUT);
        theedge.setSource(theedge.getTarget());
        theedge.setTarget(swap);
        theedge.getSource().setType(PortType.OUTPUT);
    }

}
