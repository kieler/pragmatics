/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola;

import java.util.Arrays;

import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Rectangle;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graphimport.HierarchicalKGraphImporter;
import de.cau.cs.kieler.klay.cola.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.LibtopologyProcessor;
import de.cau.cs.kieler.klay.cola.processors.NonUniformEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.processors.PortConstraintProcessor;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;

/**
 * 
 * @author uru
 */
public class ColaLayoutProvider extends AbstractLayoutProvider {

    private float spacing;
    private float borderSpacing;

    private CGraph graph;

    /**
     * Main entry point of the layout provider.
     * 
     * {@inheritDoc}
     */
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {

        System.out.println("Called layout "
                + parentNode.getData(KShapeLayout.class)
                        .getProperty(LayoutOptions.LAYOUT_HIERARCHY));

        // handle some properties
        KLayoutData rootLayout = parentNode.getData(KLayoutData.class);

        // spacing
        spacing = rootLayout.getProperty(LayoutOptions.SPACING);
        //Rectangle.setXBorder(spacing);
        //Rectangle.setYBorder(spacing);

        borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);

        // calculate margins
        calculateMarginsAndSizes(parentNode);

        // execute layout algorithm
        IGraphImporter<KNode> importer;
        if (!rootLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            importer = new KGraphImporter();
        } else {
            importer = new HierarchicalKGraphImporter();
        }
        graph = importer.importGraph(parentNode);

        new DirectionConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new PortConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new NonUniformEdgeLengthProcessor().process(graph, progressMonitor.subTask(1));

        // System.out.println(parentNode);
        System.out.println(Arrays.toString(graph.idealEdgeLengths));

        // for the moment fix the issue where the edge lengths do not allow 0
        for (int i = 0; i < graph.idealEdgeLengths.length; ++i) {
            if (graph.idealEdgeLengths[i] == 0) {
                graph.idealEdgeLengths[i] = borderSpacing + 40;
            }
        }

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 50, false,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());
        algo.setClusterHierarchy(graph.rootCluster);

        // run some w/o overlap
        algo.makeFeasible();

        int runs = 10;

        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out" + i + ".svg");

            // System.out.println(i);
        }

        // do some with overlap
        algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 1, true,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());
        algo.setClusterHierarchy(graph.rootCluster);

        algo.makeFeasible();

        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out99overlap" + i + ".svg");

            // System.out.println(i);
        }

        graph.rootCluster.computeBoundingRect(graph.nodes);

        /*
         * End
         */

        // apply the calculated layout back to the kgrap
        importer.applyLayout(graph);

        if (graph.getProperty(ColaProperties.LIBTOPOLOGY)) {
            System.out.println("running topology");
            new LibtopologyProcessor().process(graph, progressMonitor.subTask(1));
        }

        // cleanup c++ objects
        algo.freeAssociatedObjects();

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
