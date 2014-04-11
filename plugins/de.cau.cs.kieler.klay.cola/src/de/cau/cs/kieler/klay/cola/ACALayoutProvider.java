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
package de.cau.cs.kieler.klay.cola;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adaptagrams.ACAEdgeOffsets;
import org.adaptagrams.ACALayout;
import org.adaptagrams.ACAOverlapPrevention;
import org.adaptagrams.ACASepFlag;
import org.adaptagrams.ACASepFlagsStruct;
import org.adaptagrams.Bools;
import org.adaptagrams.DoublePair;
import org.adaptagrams.TestConvergence;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;
import de.cau.cs.kieler.klay.cola.graphimport.HierarchicalKGraphImporter;
import de.cau.cs.kieler.klay.cola.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.NonUniformEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.processors.PortConstraintProcessor;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties;
import de.cau.cs.kieler.klay.cola.util.DebugTestConvergence;

/**
 * @author uru
 * 
 */
public class ACALayoutProvider extends AbstractLayoutProvider {

    private boolean layoutHierarchy = false;
    
    private  ACALayout aca;

    /*
     * Debug
     */
    private boolean debug = false;
    private TestConvergence testConvergence;
    private String debugPrefix;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("ACA Layout", 1);
        
        final KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        debug = parentLayout.getProperty(LayoutOptions.DEBUG_MODE);
        layoutHierarchy = parentLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY);
        
        if (debug) {
            // Internal convergence test that outputs debug information.
            testConvergence = new DebugTestConvergence("aca");

            // store hierarchical debug output separately
            debugPrefix = "";
            if (parentNode.getParent() != null) {
                debugPrefix +=
                        EcoreUtil.getURI(parentNode.getParent()) + "-"
                                + EcoreUtil.getURI(parentNode) + "-";
                // make sure it's a valid file path
                debugPrefix = debugPrefix.replace("/", "").replace("#", "");
            }
        } else {
            // use default convergence test
            testConvergence = new TestConvergence();
        }
        
        // ACA _never_ uses port dummies
        //parentLayout.setProperty(ColaProperties.PORT_DUMMIES, false);

        calculateMarginsAndSizes(parentNode);

        // execute layout algorithm
        IGraphImporter<KNode> importer;
        if (layoutHierarchy) {
            importer = new HierarchicalKGraphImporter();
        } else {
            importer = new KGraphImporter();
        }
        CGraph graph = importer.importGraph(parentNode);
        graph.init();

        
        // check if we have existing edge lengths
        // Map<Object, Double> precalculatedEdgeLengths =
        // graph.getProperty(new Property<Map<Object, Double>>("mama"));
        // System.out.println("PRECALC" + precalculatedEdgeLengths);

        // execute some processors
        new DirectionConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new PortConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new NonUniformEdgeLengthProcessor().process(graph, progressMonitor.subTask(1));

        // run ACA
        System.out.println(graph.nodes.size());
        System.out.println(graph.edges.size());
        System.out.println(graph.constraints.size());
        double idealEdgeLength = parentLayout.getProperty(ColaProperties.IDEAL_EDGE_LENGTHS);
        System.out.println("EDGE LENGTH" + idealEdgeLength);
        aca =
                new ACALayout(graph.nodes, graph.edges, graph.constraints, 1, true,
                        graph.getIdealEdgeLengths());
        aca.setClusterHierarchy(graph.rootCluster);
        
//        if (debug) {
//            DebugTestConvergence debugConvergence = (DebugTestConvergence) testConvergence;
//            debugConvergence.setLayouter(aca.getFDLayout());
//            debugConvergence.setNamePrefix(debugPrefix + (true ? "overlap" : "non_overlap"));
//        }
        
        
        // we have to tell aca to ignore edges from dummy port nodes to their parents
        Bools bools = new Bools(graph.edges.size());
        for (int i = 0; i < bools.size(); ++i) {
            bools.set(i, false);
        }
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                bools.set(p.cEdgeIndex, true);
            }
        }
        aca.ignoreEdges(bools);
        
        // add flags that restrict edges to being aligned horizontally
        ACASepFlagsStruct struct = new ACASepFlagsStruct();
        ArrayList<CEdge> edges = Lists.newArrayListWithCapacity((int) graph.edges.size());
        for (int i = 0; i < graph.edges.size(); i++) {
            edges.add(null);
        }
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                edges.set(e.cIndex, e);
            }
        }
        for (CPort p : graph.getExternalPorts()) {

        }
        for (int i = 0; i < graph.edges.size(); ++i) {
            CEdge edge = edges.get(i);
            System.out.println("edge " + edge);
            if (edge == null) {
                // edges to dummy ports, no alignment for them
                struct.addFlag(ACASepFlag.ACANOSEP);
            } else {
                
                // TODO properly here
                if ((edge.getSourcePort() != null && edge.getTargetPort() != null)
                        && (edge.getSourcePort().side != PortSide.EAST || edge.getTargetPort().side != PortSide.WEST)) {
                    struct.addFlag(ACASepFlag.ACANOSEP);
                } else {
                    struct.addFlag(ACASepFlag.ACAEAST);
                }
            }
        }
        aca.setAllowedSeparations(struct);

        aca.overlapPrevention(ACAOverlapPrevention.ACAOPWITHOFFSETS);

        // for each edge two offsets can be passed that correlate to the
        // position of a possible src/tgt port relative to the node's center
        ACAEdgeOffsets edgeOffsets = new ACAEdgeOffsets(graph.edges.size());
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                KEdge kedge = (KEdge) e.getProperty(InternalColaProperties.ORIGIN);
                KPort srcPort = kedge.getSourcePort();
                KPort tgtPort = kedge.getTargetPort();

                // calculate the offset for this edge
                DoublePair st = new DoublePair(0, 0);
                if (srcPort != null) {
                    st.setFirst(-calculatePortOffset(n, srcPort));
                }
                if (tgtPort != null) {
                    st.setSecond(-calculatePortOffset(e.getTarget(), tgtPort));
                }
                edgeOffsets.set(e.cIndex, st);

            }
        }
//        aca.setAlignmentOffsetsForCompassDirection(ACASepFlag.ACAEAST, edgeOffsets);


        // execute ACA
        if (debug) {
            int i = 0;
            while (aca.createOneAlignment()) {
                aca.getFDLayout().outputInstanceToSVG("aca_output_" + (i++));
            }
        } else {
            aca.createAlignments();
        }
        
        //aca.getFDLayout().outputInstanceToSVG("aca_post_alignment");

        // apply the layout back
        importer.applyLayout(graph);

        System.gc();
        System.gc();
        System.gc();
        progressMonitor.done();
    }

    /**
     * Calculates the offset of the passed port relative to the parent.
     */
    private double calculatePortOffset(final CNode n, final KPort p) {

        Margins margins = n.getMargins();
        double nodeHeight = n.getRectSizeRaw().y;

        // TODO not sure about the validity of selecting the port's pos and size
        KShapeLayout portLayout = p.getData(KShapeLayout.class);
        double dy =
                -(nodeHeight / 2f) + margins.top + portLayout.getYpos()
                        + (portLayout.getHeight() / 2);

        return dy;
    }

    private void calculateMarginsAndSizes(final KNode parent) {
        KGraphAdapter adapter = KGraphAdapters.adapt(parent);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);

        // KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).excludePorts().process();
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();

        if (parent.getData(KLayoutData.class).getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            for (KNode child : parent.getChildren()) {
                calculateMarginsAndSizes(child);
            }
        }
    }
}
