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
package de.cau.cs.kieler.klay.layered;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas.DrawingMode;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.impl.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.impl.NaiveSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.impl.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.impl.SplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;
import de.cau.cs.kieler.klay.layered.options.LayeredEdgeRouting;

/**
 * Layout provider to connect the layered layouter to the Eclipse based layout services.
 * 
 * @author msp
 */
public class LayeredLayoutProvider extends AbstractLayoutProvider {

    /** default value for object spacing. */
    public static final float DEF_SPACING = 20.0f;

    /** phase 1: cycle breaking module. */
    private ICycleBreaker cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayerer layerer = new LongestPathLayerer();
    /** phase 3: crossing minimization module. */
    private ICrossingMinimizer crossingMinimizer = new LayerSweepCrossingMinimizer();
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter = new PolylineEdgeRouter();

    /** the DebugCanvas to use for debug-drawings. **/
    private DebugCanvas debugCanvas;

    public static int MINIMAL_EDGE_ANGLE = 0;

    /** Option to choose edgerouter. */
    public static final String KLAY_EDGE_ROUTING = "de.cau.cs.kieler.klay.layered."
            + "options.LayeredEdgeRouting";

    /** option to choose if debug info is shown. */
    public static final String KLAY_DEBUG_INFO = "de.cau.cs.kieler.klay.layered."
            + "options.LayeredDebugInfo";

    /** option defines the minimal angle a shortedge may have. */
    public static final String KLAY_MINIMAL_EDGE_ANGLE = "de.cau.cs.kieler.klay.layered."
            + "options.MinimalAngle";
    
    /** option to choose if debug info is shown. */
    public static final String KLAY_STRAIGHT_EDGES = 
        "de.cau.cs.kieler.klay.layered.options.LayeredStraightEdges";

    /** constructor registering the new enum option. */
    public LayeredLayoutProvider() {
        LayoutOptions.registerEnum(KLAY_EDGE_ROUTING, LayeredEdgeRouting.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Layered layout", 1);
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        float spacing = LayoutOptions.getFloat(parentLayout, LayoutOptions.MIN_SPACING);
        if (Float.isNaN(spacing) || spacing < 0) {
            spacing = DEF_SPACING;
        }

        // check which EdgeRouter to use
        LayeredEdgeRouting router = LayoutOptions.getEnum(parentLayout, LayeredEdgeRouting.class);
        switch (router) {
        case POLYLINE:
            if (!(edgeRouter instanceof PolylineEdgeRouter)) {
                edgeRouter = new PolylineEdgeRouter();
            }
            break;
        case NAIVE_SPLINES:
            if (!(edgeRouter instanceof NaiveSplineEdgeRouter)) {
                edgeRouter = new NaiveSplineEdgeRouter();
            }
            break;
        case BOX_SPLINES:
            if (!(edgeRouter instanceof SplineEdgeRouter)) {
                edgeRouter = new SplineEdgeRouter();
            }
        }

        // get minimal edge angle
        MINIMAL_EDGE_ANGLE = LayoutOptions.getInt(parentLayout, KLAY_MINIMAL_EDGE_ANGLE);
        
        // debug mode
        Boolean debug = LayoutOptions.getBoolean(parentLayout, KLAY_DEBUG_INFO);
        if (debug) {
            // get debug canvas and clear
            debugCanvas = new DebugCanvas(layoutNode, DrawingMode.IMMEDIATE);
            float borderspacing = LayoutOptions
                    .getFloat(parentLayout, LayoutOptions.BORDER_SPACING);
            if (Float.isNaN(borderspacing) || borderspacing < 0) {
                borderspacing = DEF_SPACING;
            }
            debugCanvas.setCustomXOffset(borderspacing);
            debugCanvas.setCustomYOffset(borderspacing);
            debugCanvas.clear();
        } else {
            if (debugCanvas != null) {
                debugCanvas.clear();
            }
            debugCanvas = null;
        }

        // transform the input graph
        List<LNode> nodes = transformGraph(layoutNode);
        // create an empty layered graph
        LayeredGraph layeredGraph = new LayeredGraph();
        // Add information for LinearSegmentsNodePlacer
        Boolean straightEdges = LayoutOptions.getBoolean(parentLayout, KLAY_STRAIGHT_EDGES);
        layeredGraph.setProperty(Properties.STRAIGHT_EDGES, straightEdges);
        // perform the actual layout
        doLayout(layeredGraph, nodes, progressMonitor.subTask(1), spacing);
        // apply the layout results to the original graph
        applyLayout(layoutNode, layeredGraph);

        progressMonitor.done();
    }

    /**
     * Perform the five phases of the layered layouter.
     * 
     * @param layeredGraph
     *            an initially empty layered graph
     * @param nodes
     *            a list of nodes
     * @param themonitor
     *            a progress monitor, or {@code null}
     * @param spacing
     *            spacing for layout
     */
    public void doLayout(final LayeredGraph layeredGraph, final List<LNode> nodes,
            final IKielerProgressMonitor themonitor, final float spacing) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Layered layout phases", 1 + 1 + 1 + 1 + 1);

        // phase 1: cycle breaking
        cycleBreaker.reset(monitor.subTask(1));
        cycleBreaker.breakCycles(nodes);
        // phase 2: layering
        layerer.reset(monitor.subTask(1));
        layerer.layer(nodes, layeredGraph);
        layeredGraph.splitEdges();
        // phase 3: crossing minimization
        crossingMinimizer.reset(monitor.subTask(1));
        crossingMinimizer.minimizeCrossings(layeredGraph);
        // phase 4: node placement
        nodePlacer.reset(monitor.subTask(1));
        nodePlacer.setSpacing(spacing);
        nodePlacer.placeNodes(layeredGraph);
        // phase 5: edge routing
        edgeRouter.reset(monitor.subTask(1));
        edgeRouter.setSpacing(spacing);
        if (edgeRouter instanceof SplineEdgeRouter) {
            ((SplineEdgeRouter) edgeRouter).routeEdges(layeredGraph, debugCanvas);
        } else {
            edgeRouter.routeEdges(layeredGraph);
        }

        monitor.done();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (LayoutOptions.MIN_SPACING.equals(optionId)) {
            return DEF_SPACING;
        } else if (LayoutOptions.BORDER_SPACING.equals(optionId)) {
            return DEF_SPACING;
        } else if (KLAY_MINIMAL_EDGE_ANGLE.equals(optionId)) {
            return 0;
        } else {
            return super.getDefault(optionId);
        }
    }

    /**
     * Transform the given KGraph to a layered graph.
     * 
     * @param layoutNode
     *            parent node of the KGraph
     * @return a list of nodes for a layered graph
     */
    private List<LNode> transformGraph(final KNode layoutNode) {
        List<LNode> layeredNodes = new LinkedList<LNode>();

        // transform nodes and ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        for (KNode child : layoutNode.getChildren()) {
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(child);
            LNode newNode = new LNode(child, child.getLabel().getText());
            newNode.getSize().x = nodeLayout.getWidth();
            newNode.getSize().y = nodeLayout.getHeight();
            layeredNodes.add(newNode);
            elemMap.put(child, newNode);
            for (KPort kport : child.getPorts()) {
                PortType type = PortType.UNDEFINED;
                int outBalance = 0;
                for (KEdge edge : kport.getEdges()) {
                    if (edge.getSourcePort() == kport) {
                        outBalance++;
                    }
                    if (edge.getTargetPort() == kport) {
                        outBalance--;
                    }
                }
                if (outBalance > 0) {
                    type = PortType.OUTPUT;
                } else if (outBalance < 0) {
                    type = PortType.INPUT;
                }
                LPort newPort = new LPort(type, kport, kport.getLabel().getText());
                newPort.setNode(newNode);
                elemMap.put(kport, newPort);
            }
        }

        // transform edges
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
                if (kedge.getTarget().getParent() == child.getParent()) {
                    LNode sourceNode = (LNode) elemMap.get(child);
                    LPort sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
                    LNode targetNode = (LNode) elemMap.get(kedge.getTarget());
                    LPort targetPort = (LPort) elemMap.get(kedge.getTargetPort());
                    if (sourcePort == null) {
                        sourcePort = new LPort(PortType.OUTPUT);
                        sourcePort.setNode(sourceNode);
                    }
                    if (targetPort == null) {
                        targetPort = new LPort(PortType.INPUT);
                        targetPort.setNode(targetNode);
                    }
                    LEdge newEdge = new LEdge(kedge);
                    newEdge.setSource(sourcePort);
                    newEdge.setTarget(targetPort);
                    for (KLabel label : kedge.getLabels()) {
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                        Coord labelSize = new Coord(labelLayout.getWidth(), labelLayout.getHeight());
                        LLabel newLabel = new LLabel(label, label.getText());
                        newLabel.getSize().add(labelSize);
                        newEdge.getLabels().add(newLabel);
                    }
                    // set properties of the new edge
                    int priority = LayoutOptions.getInt(edgeLayout, LayoutOptions.PRIORITY);
                    if (priority > 0) {
                        newEdge.setProperty(Properties.PRIORITY, Integer.valueOf(priority));
                    }
                } else {
                    LayoutOptions.setBoolean(edgeLayout, LayoutOptions.NO_LAYOUT, true);
                }
            }
        }

        return layeredNodes;
    }

    /**
     * Apply the computed layout of a layered graph to the original graph.
     * 
     * @param parentNode
     *            parent node of the original graph
     * @param layeredGraph
     *            a layered graph
     */
    private void applyLayout(final KNode parentNode, final LayeredGraph layeredGraph) {
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        float borderSpacing = LayoutOptions.getFloat(parentLayout, LayoutOptions.BORDER_SPACING);
        if (Float.isNaN(borderSpacing) || borderSpacing < 0) {
            borderSpacing = DEF_SPACING;
        }
        Coord offset = new Coord(borderSpacing + layeredGraph.getOffset().x, borderSpacing
                + layeredGraph.getOffset().y);

        // process the nodes
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                if (lnode.getOrigin() instanceof KNode) {
                    KNode knode = (KNode) lnode.getOrigin();
                    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
                    nodeLayout.setXpos(lnode.getPos().x + offset.x);
                    nodeLayout.setYpos(lnode.getPos().y + offset.y);
                }
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        edge.id = -1;
                    }
                }
            }
        }

        // collect all parts of each long edge
        Map<KEdge, List<LEdge>> edgeMap = new HashMap<KEdge, List<LEdge>>();
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    for (LEdge ledge : port.getEdges()) {
                        if (ledge.id < 0 && ledge.getOrigin() instanceof KEdge) {
                            KEdge kedge = (KEdge) ledge.getOrigin();
                            List<LEdge> edgeList = edgeMap.get(kedge);
                            if (edgeList == null) {
                                edgeList = new LinkedList<LEdge>();
                                edgeMap.put(kedge, edgeList);
                            }
                            edgeList.add(ledge);
                            // apply layout to labels
                            for (LLabel label : ledge.getLabels()) {
                                KLabel klabel = (KLabel) label.getOrigin();
                                KShapeLayout klabelLayout = KimlLayoutUtil.getShapeLayout(klabel);
                                Coord labelPos = new Coord(ledge.getSource().getPos().x, ledge
                                        .getSource().getPos().y);
                                labelPos.add(ledge.getSource().getNode().getPos());
                                labelPos.add(label.getPos());
                                klabelLayout.setXpos(labelPos.x + offset.x);
                                klabelLayout.setYpos(labelPos.y + offset.y);
                            }
                        }
                    }
                }
            }
        }
        // process the edges
        for (Map.Entry<KEdge, List<LEdge>> edgeEntry : edgeMap.entrySet()) {
            KEdge kedge = edgeEntry.getKey();
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
            List<LEdge> edgeList = edgeEntry.getValue();
            // set source and target points, considering direction of the edge
            LEdge firstEdge = edgeList.get(0);
            LPort sourcePort = firstEdge.getSource();
            sourcePort.getPos().add(sourcePort.getNode().getPos());
            LEdge lastEdge = edgeList.get(edgeList.size() - 1);
            LPort targetPort = lastEdge.getTarget();
            targetPort.getPos().add(targetPort.getNode().getPos());
            if (firstEdge.isReversed()) {
                applyLayout(edgeLayout.getSourcePoint(), targetPort.getPos(), offset);
                applyLayout(edgeLayout.getTargetPoint(), sourcePort.getPos(), offset);
            } else {
                applyLayout(edgeLayout.getSourcePoint(), sourcePort.getPos(), offset);
                applyLayout(edgeLayout.getTargetPoint(), targetPort.getPos(), offset);
            }
            // set bend points, considering direction of the edge
            List<KPoint> bendPoints = edgeLayout.getBendPoints();
            bendPoints.clear();
            for (LEdge ledge : edgeList) {
                for (Coord lpoint : ledge.getBendPoints()) {
                    KPoint newPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    applyLayout(newPoint, lpoint, offset);
                    if (ledge.isReversed()) {
                        bendPoints.add(0, newPoint);
                    } else {
                        bendPoints.add(newPoint);
                    }
                }
            }
        }

        // set up the parent node
        parentLayout.setWidth(layeredGraph.getSize().x + 2 * borderSpacing);
        parentLayout.setHeight(layeredGraph.getSize().y + 2 * borderSpacing);
        LayoutOptions.setBoolean(parentLayout, LayoutOptions.FIXED_SIZE, true);
    }

    /**
     * Apply the given point coordinates to the {@code KPoint}.
     * 
     * @param kpoint
     *            point where coordinates are written
     * @param lpoint
     *            point from which coordinates are read
     */
    private void applyLayout(final KPoint kpoint, final Coord lpoint, final Coord offset) {
        kpoint.setX(lpoint.x + offset.x);
        kpoint.setY(lpoint.y + offset.y);
    }

}
