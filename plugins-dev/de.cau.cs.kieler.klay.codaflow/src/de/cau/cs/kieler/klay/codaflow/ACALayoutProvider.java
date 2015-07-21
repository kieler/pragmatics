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
package de.cau.cs.kieler.klay.codaflow;

import java.util.ArrayList;
import java.util.Arrays;

import org.adaptagrams.ACAOverlapPrevention;
import org.adaptagrams.ACASepFlag;
import org.adaptagrams.ACASepFlagsStruct;
import org.adaptagrams.Bools;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;
import de.cau.cs.kieler.adaptagrams.layouter.KACALayouter;
import de.cau.cs.kieler.adaptagrams.layouter.KConstrainedFDLayouter;
import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.klay.codaflow.graphimport.HierarchicalKGraphImporter;
import de.cau.cs.kieler.klay.codaflow.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.codaflow.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.codaflow.processors.FlowConstraintProcessor;
import de.cau.cs.kieler.klay.codaflow.processors.IdealEdgeLengthProcessor;
import de.cau.cs.kieler.klay.codaflow.processors.PortConstraintProcessor;
import de.cau.cs.kieler.klay.codaflow.properties.CodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.properties.InternalCodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.util.ACADebugTestConvergence;
import de.cau.cs.kieler.klay.codaflow.util.MinMaxTestConvergence;

/**
 * Uses {@link org.adaptagrams.adaptagrams adaptagrams}' {@link org.adaptagrams.ACALayout ACALayout}
 * to greedily generate alignment constraints producing a grid-like layout were as many edges as
 * possible are axis-aligned.
 * 
 * @author uru
 */
public class ACALayoutProvider extends AbstractLayoutProvider {

    private CGraph graph;

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
        progressMonitor.begin("CoDaFlow ACA Layout", 1);

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

        parentLayout.setProperty(CGraphProperties.INCLUDE_SPACING_IN_MARGIN, true);

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

        // tell aca to ignore some edges, e.g. edges connecting dummy port nodes to parent nodes,
        // or edges to/from external ports
        // furthermore, any dummy port node is ignored during overlap calculation in aca
        Bools ignoreEdges = generateIgnoredEdgesAndNodes();

        // tell aca which nodes ports belong to. aca uses this information to check valid alignments
        // based on the nodes instead of the ports
        // generatePortNodeMapping();
        // aca.overlapPrevention(ACAOverlapPrevention.ACAOPWITHOFFSETS);

        // we only allow aca to create certain alignments, basically horizontal ones for
        // WEST/EAST edges and vertical ones for SOUTH/NORTH edges
        ACASepFlagsStruct allowedDirections = generateAllowedSeparations();

        // specify offsets for each edge that relate to the port positions to which
        // the edge is connected
        // generateEdgeOffsets();

        // first configure ACA
        KACALayouter acaL =
                new KACALayouter()
                        .withIgnoreEdges(ignoreEdges)
                        .withAllowedDirections(allowedDirections)
                        .withOverlapPrevention(ACAOverlapPrevention.ACAOPWITHOFFSETS)
                        .withFavorLongEdges(
                                parentLayout.getProperty(CodaflowProperties.ACA_FAVOR_LONG_EDGES));

        // then configure underlying constrained layout
        acaL.withCGraph(graph)
            .withIdealEdgeLength(1)
            .withIndividualEdgeLengths()
            .withRemoveOverlaps()
            .withRemoveOverlapsYFirst()
            .withConvergenceTest(testConvergence);
        

        // prepare the layouter, needs to be accessible for debug
        acaL.prepare();
        
        if (debug) {
            System.out.println("ACA Ideal Edge Lengths: " + Arrays.toString(graph.idealEdgeLengths));

            ACADebugTestConvergence debugConvergence = (ACADebugTestConvergence) testConvergence;
            debugConvergence.setLayouter(acaL.getACALayouter());
            debugConvergence.setNamePrefix(debugPrefix + "aca");
        }

        // FIXME adaptagrams - atm still have to compute the bounding rects of clusters
        graph.rootCluster.computeBoundingRect(graph.nodes);

        if (debug) {
            acaL.getACALayouter().outputInstanceToSVG("aca_original_the_one_before");
        }

        IKielerProgressMonitor spm = progressMonitor.subTask(1);
        spm.begin("ACA Alignment Generation", 1);
        // execute ACA
        if (debug) {
            int i = 0;
            while (acaL.getACALayouter().createOneAlignment()) {
                acaL.getACALayouter().outputInstanceToSVG("aca_output_" + (i++));
            }
        } else {
            acaL.run();
        }
        spm.done();

        if (debug) {
            acaL.getACALayouter().outputInstanceToSVG("aca_original_the_one_after");
        }

        // FIXME adaptagrams - atm still have to compute the bounding rects of clusters
        graph.rootCluster.computeBoundingRect(graph.nodes);

        // remember which edges were aligned by aca
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                boolean isAligned = acaL.getACALayouter().edgeIsAlignedConstTime(e.cIndex);
                e.setProperty(InternalCodaflowProperties.ACA_EDGE_ALIGNED, isAligned);
            }
        }
        
        // post compaction
        if (parentLayout.getProperty(CodaflowProperties.ACA_POST_COMPACTION)) {
            spm = progressMonitor.subTask(1);
            spm.begin("ACA Post Compaction", 1);
            
            // SUPPRESS CHECKSTYLE NEXT 5 MagicNumber
            new KConstrainedFDLayouter()
                .withCGraph(graph)
                .withIdealEdgeLength(10)
                .withRemoveOverlaps()
                .withRemoveOverlapsYFirst()
                .run();
            
            spm.done();
        }

        // apply the layout back
        importer.applyLayout(graph);

        progressMonitor.done();
    }

    private ACASepFlagsStruct generateAllowedSeparations() {
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
                } else if ((edge.getSourcePort() != null && edge.getTargetPort() != null)
                        && (edge.getSourcePort().side != PortSide.EAST 
                        || edge.getTargetPort().side != PortSide.WEST)) {
                    // edges with north-south or inverted ports should not be aligned
                    struct.addFlag(ACASepFlag.ACANOSEP);
                } else {
                    struct.addFlag(ACASepFlag.ACAEAST);
                }
            }
        }

        return struct;
    }
    private Bools generateIgnoredEdgesAndNodes() {
        // we have to tell aca to ignore edges from dummy port nodes to their parents
        Bools bools = new Bools(graph.edges.size());
        // first accept all edges
        for (int i = 0; i < bools.size(); ++i) {
            bools.set(i, false);
        }

        // now deny certain edges
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                // the 'dummy' edge to the node's center
                bools.set(p.cEdgeIndex, true);
            }
        }
        
        // all edges connected to external ports
        // for (CPort p : graph.getExternalPorts()) {
        // for (CEdge e : p.getConnectedEdges()) {
        // bools.set(e.cIndex, true);
        // }
        // }

        // the user decides for cross hierarchy edges
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                if (e.crossHierarchy
                        && !graph.getProperty(CodaflowProperties.ACA_ALIGN_CROSS_HIERARCHY_EDGES)) {
                    bools.set(e.cIndex, true);
                }
            }
        }

        return bools;
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
