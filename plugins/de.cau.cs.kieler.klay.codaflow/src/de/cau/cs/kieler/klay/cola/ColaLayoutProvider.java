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

import java.util.Arrays;
import java.util.Stack;

import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.TestConvergence;
import org.adaptagrams.UnsatisfiableConstraintInfoPtrs;
import org.adaptagrams.Unsigneds;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;
import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.kiml.util.nodespacing.NodeMarginCalculator;
import de.cau.cs.kieler.klay.cola.graphimport.HierarchicalKGraphImporter;
import de.cau.cs.kieler.klay.cola.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.IdealEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.processors.PortConstraintProcessor;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties;
import de.cau.cs.kieler.klay.cola.util.DebugTestConvergence;
import de.cau.cs.kieler.klay.cola.util.MinMaxTestConvergence;

/**
 * Basic constrained force-based layout using adaptagrams {@link ConstrainedFDLayout}.
 * 
 * @author uru
 */
public class ColaLayoutProvider extends AbstractLayoutProvider {

    private CGraph graph;

    /**
     * Remember the created layouters in order to free the c++ elements after the layout has been
     * applied back to the original graph.
     */
    private Stack<ConstrainedFDLayout> layouters;

    /*
     * Debug
     */
    private boolean debug = false;
    private MinMaxTestConvergence testConvergence;
    private String debugPrefix;

    /**
     * Main entry point of the layout provider.
     * 
     * {@inheritDoc}
     */
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Cola Layout", 1);

        // handle some properties
        KLayoutData rootLayout = parentNode.getData(KLayoutData.class);
        debug = rootLayout.getProperty(LayoutOptions.DEBUG_MODE);
        
        if (debug) {
            // Internal convergence test that outputs debug information.
            testConvergence = new DebugTestConvergence("cola");

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

        layouters = new Stack<ConstrainedFDLayout>();

        // calculate margins
        calculateMarginsAndSizes(parentNode);

        rootLayout.setProperty(CGraphProperties.MARGIN_INCLUDES_SPACING, true);
        
        // execute layout algorithm
        IGraphImporter<KNode, CGraph> importer;
        if (!rootLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            importer = new KGraphImporter();
        } else {
            importer = new HierarchicalKGraphImporter();
        }
        graph = importer.importGraph(parentNode);

        // apply some processors
        
        // CARE ideal edge length processor has a dependency on 
        // port constraint process (to get correct lengths for the ports)
        new PortConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new IdealEdgeLengthProcessor().process(graph, progressMonitor.subTask(1));

        if (debug) {
            System.out.println("Cola Ideal Edge Lengths: " + Arrays.toString(graph.idealEdgeLengths));
            System.out.println(graph);
        }

        // FIXME for the moment fix the issue where the edge lengths do not allow 0
        float borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);
        for (int i = 0; i < graph.idealEdgeLengths.length; ++i) {
            if (graph.idealEdgeLengths[i] == 0) {
                graph.idealEdgeLengths[i] = borderSpacing;
            }
        }

        int cap = Integer.MAX_VALUE;
        
        // first run w/o overlap prevention
        IKielerProgressMonitor spm = progressMonitor.subTask(1);
        spm.begin("Untangling Layout", 1);
        runLayout(false, "", 1, 5, cap, spm);
        spm.done();

        // generate the flow constraints
        new DirectionConstraintProcessor().process(graph, progressMonitor.subTask(1));
        spm = progressMonitor.subTask(1);
        spm.begin("Flow Constrained Layout", 1);
        runLayout(false, "_flow", 1, 1, cap, spm);
        spm.done();

        // then run some with overlap prevention
        spm = progressMonitor.subTask(1);
        spm.begin("Overlap Preventing Layout", 1);
        runLayout(true, "", 1, 1, cap, spm);
        spm.done();

        // FIXME adaptagrams - atm still have to compute the bounding rects of clusters
        graph.rootCluster.computeBoundingRect(graph.nodes);

        // ConstrainedFDLayout fd =
        // new ConstrainedFDLayout(graph.nodes, graph.edges, 10, true);
        // WhitparentLayout.getProperty(LayoutOptions.SPACING)
        // fd.setClusterHierarchy(graph.rootCluster);
        // fd.setConstraints(graph.constraints);
        // fd.run();
        
        
        // apply the calculated layout back to the kgrap
        importer.applyLayout(graph);

        // free c++ object after the last layouter finished
        // FIXME this should still leak the earlier layouter instances ...
        layouters.pop().freeAssociatedObjects();
        layouters.clear();
        
        progressMonitor.done();
    }

    private ConstrainedFDLayout runLayout(final boolean overlap, final String dbgString, 
            final double edgelength, final int minIts, final int maxIts, IKielerProgressMonitor pm) {

        // create a new layouter instance
        ConstrainedFDLayout algo =
                // edge length multiplier is 1
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), edgelength, overlap,
                        graph.getIdealEdgeLengths(), testConvergence);

        UnsatisfiableConstraintInfoPtrs uciX = new UnsatisfiableConstraintInfoPtrs();
        UnsatisfiableConstraintInfoPtrs uciY = new UnsatisfiableConstraintInfoPtrs();
                
        algo.setUnsatisfiableConstraintInfo(uciX, uciY);
        
        algo.setM_doYAxisFirst(true);
        
        generateOverlapIgnores(graph, algo);
        
        // remember for later disposal
        layouters.add(algo);
        
        testConvergence.minIterations = minIts;
        testConvergence.maxIterations = maxIts;
        if (debug) {
            DebugTestConvergence debugConvergence = (DebugTestConvergence) testConvergence;
            debugConvergence.setLayouter(algo);
            debugConvergence.setNamePrefix(debugPrefix + (overlap ? "overlap" : "non_overlap")
                    + dbgString);
        }

        // set constraints and clusters
        algo.setConstraints(graph.getConstraints());
        algo.setClusterHierarchy(graph.rootCluster);

        algo.outputInstanceToSVG("colapre_" + (overlap ? "overlap" : "non_overlap" + dbgString));
        
        algo.makeFeasible();
        
        algo.outputInstanceToSVG("colapremf_" + (overlap ? "overlap" : "non_overlap" + dbgString));

        IKielerProgressMonitor ipm = pm.subTask(1);
        ipm.begin("Run", 1);
        algo.run();
        ipm.done();
        
        for (int i = 0; i < uciX.size(); i++) {
            System.out.println("X " + uciX.get(i));
        }
        
        for (int i = 0; i < uciY.size(); i++) {
            System.out.println("Y" + uciY.get(i));
        }
        
        return algo;
    }

    
    private void generateOverlapIgnores(final CGraph graph, final ConstrainedFDLayout alg) {

        for (CNode n : graph.getChildren()) {

            Unsigneds portsGroup = new Unsigneds();
            
            // add the ports to be ignored
            for (CPort p : n.getPorts()) {
                portsGroup.add(p.cIndex);
            }
            
            // add the node as "head" of the group
            portsGroup.add(n.cIndex);

            alg.addGroupOfNonOverlapExemptRectangles(portsGroup);
            // System.out.println("Added group: " + n + " " + portsGroup);
        }

    }
    
    
    private void calculateMarginsAndSizes(final KNode parent) {
        KGraphAdapter adapter = KGraphAdapters.adapt(parent);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        NodeMarginCalculator mc = KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter);
        //if (parent.getData(KShapeLayout.class).getProperty(ColaProperties.PORT_DUMMIES)) {
            //mc.excludePorts();
        //}
        mc.process();

        if (parent.getData(KLayoutData.class).getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            for (KNode child : parent.getChildren()) {
                calculateMarginsAndSizes(child);
            }
        }
    }

}
