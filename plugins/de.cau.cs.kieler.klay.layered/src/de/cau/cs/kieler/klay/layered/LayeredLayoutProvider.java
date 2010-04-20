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
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.impl.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.impl.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;

/**
 * Layout provider to connect the layered layouter to the Eclipse based layout
 * services.
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Layered layout", 1);
        
        // transform the input graph
        List<LNode> nodes = transformGraph(layoutNode);
        // create an empty layered graph and perform the actual layout
        LayeredGraph layeredGraph = new LayeredGraph(layoutNode);
        doLayout(layeredGraph, nodes, progressMonitor.subTask(1));
        // apply the layout results to the original graph
        applyLayout(layoutNode, layeredGraph);

        progressMonitor.done();
    }
    
    /**
     * Perform the five phases of the layered layouter.
     * 
     * @param layeredGraph an initially empty layered graph
     * @param nodes a list of nodes
     * @param themonitor a progress monitor, or {@code null}
     */
    public void doLayout(final LayeredGraph layeredGraph, final List<LNode> nodes,
            final IKielerProgressMonitor themonitor) {
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
        // phase 3: crossing minimization
        crossingMinimizer.reset(monitor.subTask(1));
        crossingMinimizer.minimizeCrossings(layeredGraph);
        // phase 4: node placement
        nodePlacer.reset(monitor.subTask(1));
        nodePlacer.placeNodes(layeredGraph);
        // phase 5: edge routing
        edgeRouter.reset(monitor.subTask(1));
        edgeRouter.routeEdges(layeredGraph);
        
        monitor.done();
    }
    
    /**
     * Transform the given KGraph to a layered graph.
     * 
     * @param layoutNode parent node of the KGraph
     * @return a list of nodes for a layered graph
     */
    private List<LNode> transformGraph(final KNode layoutNode) {
        List<LNode> layeredNodes = new LinkedList<LNode>();
        
        // transform nodes and ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        for (KNode child : layoutNode.getChildren()) {
            LNode newNode = new LNode(child, child.getLabel().getText());
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
                }
            }
        }
        
        return layeredNodes;
    }
    
    /**
     * Apply the computed layout of a layered graph to the original graph.
     * 
     * @param parentNode parent node of the original graph
     * @param layeredGraph a layered graph
     */
    private void applyLayout(final KNode parentNode, final LayeredGraph layeredGraph) {
        // process nodes
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                if (lnode.getOrigin() instanceof KNode) {
                    KNode knode = (KNode) lnode.getOrigin();
                    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
                    nodeLayout.setXpos(lnode.getPos().x);
                    nodeLayout.setYpos(lnode.getPos().y);
                }
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        edge.id = -1;
                    }
                }
            }
        }
        
        // process edges
        Map<KEdge, List<LEdge>> edgeMap = new HashMap<KEdge, List<LEdge>>();
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    for (LEdge ledge : port.getEdges()) {
                        if (ledge.id < 0 && ledge.getOrigin() instanceof KEdge) {
                            KEdge kedge = (KEdge) ledge.getOrigin();
                            collectEdge(kedge, ledge, edgeMap);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Collect all pieces of an long edge and puts them into the edge map.
     * 
     * @param kedge an original KEdge
     * @param theledge an edge in the layered graph
     * @param edgeMap the edge map
     */
    private void collectEdge(final KEdge kedge, final LEdge theledge,
            final Map<KEdge, List<LEdge>> edgeMap) {
        LEdge ledge = theledge;
        do {
            List<LEdge> edgeList = edgeMap.get(kedge);
            if (edgeList == null) {
                edgeList = new LinkedList<LEdge>();
                edgeMap.put(kedge, edgeList);
            }
            ledge = null;
            LNode targetNode = ledge.getTarget().getNode();
            for (LPort port : targetNode.getPorts(PortType.OUTPUT)) {
                for (LEdge nextEdge : port.getEdges()) {
                    if (nextEdge.getOrigin() == kedge) {
                        ledge = nextEdge;
                        break;
                    }
                }
                if (ledge != null) {
                    break;
                }
            }
        } while (ledge != null);
    }

}
