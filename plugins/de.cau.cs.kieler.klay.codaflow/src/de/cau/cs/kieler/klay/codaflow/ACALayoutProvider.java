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
package de.cau.cs.kieler.klay.codaflow;

import java.util.ArrayList;
import java.util.Arrays;

import org.adaptagrams.ACAEdgeOffsets;
import org.adaptagrams.ACALayout;
import org.adaptagrams.ACAOverlapPrevention;
import org.adaptagrams.ACASepFlag;
import org.adaptagrams.ACASepFlagsStruct;
import org.adaptagrams.Bools;
import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.DoublePair;
import org.adaptagrams.IntIntMap;
import org.adaptagrams.TestConvergence;
import org.adaptagrams.Unsigneds;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;
import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
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
import de.cau.cs.kieler.klay.codaflow.graphimport.HierarchicalKGraphImporter;
import de.cau.cs.kieler.klay.codaflow.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.codaflow.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.codaflow.processors.FlowConstraintProcessor;
import de.cau.cs.kieler.klay.codaflow.processors.IdealEdgeLengthProcessor;
import de.cau.cs.kieler.klay.codaflow.processors.PortConstraintProcessor;
import de.cau.cs.kieler.klay.codaflow.properties.CodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.properties.InternalColaProperties;
import de.cau.cs.kieler.klay.codaflow.util.ACADebugTestConvergence;
import de.cau.cs.kieler.klay.codaflow.util.MinMaxTestConvergence;

/**
 * 
 * @author uru
 */
public class ACALayoutProvider extends AbstractLayoutProvider {


    private CGraph graph;
    private ACALayout aca;
    

    /*
     * Debug
     */
    private boolean debug = false;
    private MinMaxTestConvergence testConvergence;
    private String debugPrefix;

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("ACA Layout", 1);

        final KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        debug = parentLayout.getProperty(LayoutOptions.DEBUG_MODE);
        boolean layoutHierarchy = parentLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY);

        if (debug) {
            // Internal convergence test that outputs debug information.
            testConvergence = new ACADebugTestConvergence("aca");

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
            testConvergence = new MinMaxTestConvergence();
        }

        // margins for the nodes (include the labels etc)
        calculateMarginsAndSizes(parentNode);
        
        parentLayout.setProperty(CGraphProperties.MARGIN_INCLUDES_SPACING, true);

        // execute layout algorithm
        IGraphImporter<KNode, CGraph> importer;
        if (layoutHierarchy) {
            importer = new HierarchicalKGraphImporter();
        } else {
            importer = new KGraphImporter();
        }
        graph = importer.importGraph(parentNode);
        graph.init();

        // execute some processors
        new FlowConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new PortConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new IdealEdgeLengthProcessor().process(graph, progressMonitor.subTask(1));


        if (debug) {
            System.out.println("ACA Ideal Edge Lengths: " + Arrays.toString(graph.idealEdgeLengths));
        }
        
        
        // assemble ACA object
        aca = new ACALayout(graph.nodes, graph.edges, graph.constraints, 1, true,
                        graph.getIdealEdgeLengths(), testConvergence);
        aca.setClusterHierarchy(graph.rootCluster);

        
        if (debug) {
            ACADebugTestConvergence debugConvergence = (ACADebugTestConvergence) testConvergence;
            debugConvergence.setLayouter(aca);
            debugConvergence.setNamePrefix(debugPrefix + "aca");
        }

        // favor long edges when aligning?
        aca.favourLongEdges(parentLayout.getProperty(CodaflowProperties.ACA_FAVOR_LONG_EDGES));
        
        // tell aca to ignore some edges, e.g. edges connecting dummy port nodes to parent nodes,
        // or edges to/from external ports
        // furthermore, any dummy port node is ignored during overlap calculation in aca
        generateIgnoredEdgesAndNodes();

        // tell aca which nodes ports belong to. aca uses this information to check valid alignments
        // based on the nodes instead of the ports
        // FIXME remove
        generatePortNodeMapping();
        
//        aca.overlapPrevention(ACAOverlapPrevention.ACAOPWITHOFFSETS);
        
        // we only allow aca to create certain alignments, basically horizontal ones for 
        // WEST/EAST edges and vertical ones for SOUTH/NORTH edges
        generateAllowedSeparations();
        
        // specify offsets for each edge that relate to the port positions to which
        // the edge is connected
        generateEdgeOffsets();
        
        // FIXME adaptagrams - atm still have to compute the bounding rects of clusters
        graph.rootCluster.computeBoundingRect(graph.nodes);
        
        aca.outputInstanceToSVG("aca_original_the_one_before");

        // FIXME usually I wanna generate exemptions first, but the fd layout is not yet available
        //aca.removeOverlaps();
        
        generateOverlapIgnores(graph, aca);

        
        IKielerProgressMonitor spm = progressMonitor.subTask(1);
        spm.begin("ACA Alignment Generation", 1);
        // execute ACA
        if (debug) {
            int i = 0;
            while (aca.createOneAlignment()) {
                aca.outputInstanceToSVG("aca_output_" + (i++));
            }
        } else {
            aca.createAlignments();
        }
        spm.done();
        
        aca.outputInstanceToSVG("aca_original_the_one");

        // aca.getFDLayout().outputInstanceToSVG("aca_post_alignment");
        // FIXME adaptagrams - atm still have to compute the bounding rects of clusters
        graph.rootCluster.computeBoundingRect(graph.nodes);

        
        
        
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                if (aca.edgeIsAligned(e.cIndex)) {
                    e.setProperty(InternalColaProperties.ACA_EDGE_ALIGNED, true);
                }
            }
        }
        
//        ConstrainedFDLayout fd2 =
//                new ConstrainedFDLayout(graph.nodes, graph.edges, 1, true, graph.getIdealEdgeLengths());
//        fd2.setClusterHierarchy(graph.rootCluster);
//        fd2.setConstraints(graph.constraints);
//        fd2.setM_doYAxisFirst(true);
//        fd2.run();
        
//        fd2.outputInstanceToSVG("aca_overlap_on");
        
        if (parentLayout.getProperty(CodaflowProperties.ACA_POST_COMPACTION)) {
            spm = progressMonitor.subTask(1);
            spm.begin("ACA Post Compaction", 1);
            ConstrainedFDLayout fd =
                    new ConstrainedFDLayout(graph.nodes, graph.edges, 10, true);
            // WhitparentLayout.getProperty(LayoutOptions.SPACING)
            fd.setClusterHierarchy(graph.rootCluster);
            fd.setConstraints(graph.constraints);
            fd.setM_doYAxisFirst(true);
            fd.run();
            spm.done();
        }
        
        
        // apply the layout back
        importer.applyLayout(graph);

        progressMonitor.done();
    }

    private void generateAllowedSeparations() {
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
            for (CEdge e : p.getConnectedEdges()) {
                edges.set(e.cIndex, e);
            }
        }
        for (int i = 0; i < graph.edges.size(); ++i) {
            CEdge edge = edges.get(i);
            if (edge == null) {
                // edges to dummy ports, no alignment for them
                struct.addFlag(ACASepFlag.ACAEAST);
            } else {

                // external ports can be aligned into either west or east
                if (edge.getTargetPort() != null && edge.getTargetPort().isExternal()
                        || edge.getSourcePort() != null && edge.getSourcePort().isExternal()) {
                    struct.addFlag(ACASepFlag.ACAEASTWEST);
                }
                
                // TODO properly here
                else if ((edge.getSourcePort() != null && edge.getTargetPort() != null)
                        && (edge.getSourcePort().side != PortSide.EAST 
                        || edge.getTargetPort().side != PortSide.WEST)) {
                    struct.addFlag(ACASepFlag.ACANOSEP);
                } else {
                    struct.addFlag(ACASepFlag.ACAEAST);
                }
            }
        }
        aca.setAllowedDirections(struct);
        
        aca.overlapPrevention(ACAOverlapPrevention.ACAOPWITHOFFSETS);
    }
    
    private void generateEdgeOffsets() {
        // for each edge two offsets can be passed that correlate to the
        // position of a possible src/tgt port relative to the node's center
        ACAEdgeOffsets edgeOffsets = new ACAEdgeOffsets(graph.edges.size());
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) { 
                KEdge kedge = (KEdge) e.getProperty(CGraphProperties.ORIGIN);
                KPort srcPort = kedge.getSourcePort();
                KPort tgtPort = kedge.getTargetPort();

                // FIXME clean this, we dont have to differ between the cases!
                // for cross hierarchy edges we have to extract the ports from the cedge 
                if (e.crossHierarchy) {
                    if (e.getSourcePort() != null) {
                        srcPort = (KPort) e.getSourcePort().getProperty(CGraphProperties.ORIGIN);
                    }
                    
                    if (e.getTargetPort() != null) { 
                        tgtPort = (KPort) e.getTargetPort().getProperty(CGraphProperties.ORIGIN);
                    }
                }
                
                // calculate the offset for this edge
                DoublePair st = new DoublePair(0, 0);
                if (srcPort != null) {
                    st.setFirst(-calculatePortOffset(n, srcPort));
                }
                if (tgtPort != null) {
                    st.setSecond(-calculatePortOffset(e.getTarget(), tgtPort));
                }
                
//                System.out.println(e.getSource() + " " + e.getTarget());
//                System.out.println("\t" + srcPort + " " + srcPort.getData(KShapeLayout.class) + " " + e.getSourcePort().getPos());
//                System.out.println("\t" + tgtPort + " " + tgtPort.getData(KShapeLayout.class) + " " + e.getTargetPort().getPos());
//                System.out.println(st);
                edgeOffsets.set(e.cIndex, st);

            }
        }
        
        // add the external thingys
        for (CPort p : graph.getExternalPorts()) {
            for (CEdge e : p.getConnectedEdges()) {
                
                KEdge kedge = (KEdge) e.getProperty(CGraphProperties.ORIGIN);
                KPort srcPort = kedge.getSourcePort();
                KPort tgtPort = kedge.getTargetPort();
                
                // calculate the offset for this edge
                DoublePair st = new DoublePair(0, 0);
                if (e.getSource() != null) {
                    st.setFirst(-calculatePortOffset(e.getSource(), srcPort));
                }
                if (e.getTarget() != null) {
                    st.setSecond(-calculatePortOffset(e.getTarget(), tgtPort));
                }
                // System.out.println("adding offset for external port " + st);
                edgeOffsets.set(e.cIndex, st);
            }
        }
        
        
        aca.overlapPrevention(ACAOverlapPrevention.ACAOPWITHOFFSETS);
        aca.setAlignmentOffsetsForCompassDirection(ACASepFlag.ACAEAST, edgeOffsets);
    }
    
    /**
     * Calculates the offset of the passed port relative to the parent.
     * 
     * FIXME .. do not use the KPort here, but the CPort, is that possible?
     * Note, to do this we require FIXED_POS ports!
     */
    private double calculatePortOffset(final CNode n, final KPort p) {

        Margins margins = n.getMargins();
        double nodeHeight = n.getRectSizeRaw().y;
        
        // TODO not sure about the validity of selecting the port's pos and size
        KShapeLayout portLayout = p.getData(KShapeLayout.class);
        // System.out.println(n.getProperty(LayoutOptions.PORT_CONSTRAINTS) + " " + portLayout);
        double dy =
                -(nodeHeight / 2f) + margins.top + portLayout.getYpos()
                        + (portLayout.getHeight() / 2);

        return dy;
    }
    

    private void generateIgnoredEdgesAndNodes() {
        // we have to tell aca to ignore edges from dummy port nodes to their parents
        Bools bools = new Bools(graph.edges.size());
        // first accept all edges
        for (int i = 0; i < bools.size(); ++i) {
            bools.set(i, false);
        }
        // deny certain edges
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                // the 'dummy' edge to the node's center
                bools.set(p.cEdgeIndex, true);
            }
        }
        // all edges connected to external ports
        for (CPort p : graph.getExternalPorts()) {
            for (CEdge e : p.getConnectedEdges()) {
                // TODO do we wanna ignore these?
                // bools.set(e.cIndex, true);
            }
        }
        
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                if (e.crossHierarchy
                        && !graph.getProperty(CodaflowProperties.ACA_ALIGN_CROSS_HIERARCHY_EDGES)) {
                    bools.set(e.cIndex, true);
                }
            }
        }
        
        // configure aca
        aca.ignoreEdges(bools);
        
        
        // ignore port dummies when determining overlaps in aca
        Bools boolNodes = new Bools(graph.getLastNodeIndex());
        for (int i = 0; i < boolNodes.size(); ++i) {
            boolNodes.set(i, false);
        }
        // also ignore all dummy port nodes
        for (CNode node : graph.getChildren()) {
            for (CPort p : node.getPorts()) {
                boolNodes.set(p.cIndex, true);
            }
        }
        aca.ignoreNodesForOPWithOffsets(boolNodes);
    }
    
    private void generatePortNodeMapping() {
        IntIntMap map = new IntIntMap();
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                map.set(p.cIndex, n.cIndex);
            }
        }
        aca.setNodeAliases(map);
    }
    
    private void generateOverlapIgnores(final CGraph graph, final ACALayout alg) {

        for (CNode n : graph.getChildren()) {

            Unsigneds portsGroup = new Unsigneds();
            
            // add the ports to be ignored
            for (CPort p : n.getPorts()) {
                portsGroup.add(p.cIndex);
            } 
            
            // add the node as "head" of the group
            portsGroup.add(n.cIndex);

            alg.addGroupOfNonOverlapExemptRectangles(portsGroup);
        }

    }

    private void calculateMarginsAndSizes(final KNode parent) {
        KGraphAdapter adapter = KGraphAdapters.adapt(parent);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);

//        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).excludePorts().process();
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();

        if (parent.getData(KLayoutData.class).getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            for (KNode child : parent.getChildren()) {
                calculateMarginsAndSizes(child);
            }
        }
    }
}
