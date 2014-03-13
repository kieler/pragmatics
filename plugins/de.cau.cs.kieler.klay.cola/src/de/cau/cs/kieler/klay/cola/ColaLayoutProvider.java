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

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
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
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.NonUniformEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.processors.PortConstraintProcessor;

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
        Rectangle.setXBorder(spacing);
        Rectangle.setYBorder(spacing);

        borderSpacing = rootLayout.getProperty(LayoutOptions.BORDER_SPACING);

        // calculate margins
        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.calculateNodeMargins(adapter);

        // execute layout algorithm
        KGraphImporter importer = new KGraphImporter();
        graph = importer.importGraph(parentNode);

        BasicProgressMonitor bpm = new BasicProgressMonitor();
        new DirectionConstraintProcessor().process(graph, bpm);
        new PortConstraintProcessor().process(graph, bpm);
        new NonUniformEdgeLengthProcessor().process(graph, bpm);

        System.out.println(parentNode);
        System.out.println(Arrays.toString(graph.idealEdgeLengths));

        // for the moment fix the issue where the edgelengths do not allow 0
        for (int i = 0; i < graph.idealEdgeLengths.length; ++i) {
            if (graph.idealEdgeLengths[i] == 0) {
                graph.idealEdgeLengths[i] = borderSpacing;
            }
        }

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 50, false,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());

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

        algo.makeFeasible();

        for (int i = 0; i < runs; i++) {
            algo.runOnce();

            algo.outputInstanceToSVG("out99overlap" + i + ".svg");

            // System.out.println(i);
        }

        /*
         * End
         */

        // apply the calculated layout back to the kgrap
        importer.applyLayout(graph);

        // cleanup c++ objects
        algo.freeAssociatedObjects();

    }


}
