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

import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;

/**
 * @author uru
 * 
 */
public class CompactingLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {

        if (parentNode.getParent() != null) {
            return;
        }

        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.calculateNodeMargins(adapter);

        parentNode.getData(KShapeLayout.class).setProperty(KGraphImporter.PORT_DUMMIES, false);
        
        KGraphImporter importer = new KGraphImporter();
        CGraph graph = importer.importGraph(parentNode);

        for (CNode tgt : graph.getChildren()) {

            if (tgt.getIncomingEdges().isEmpty()) {

                // fix at its position

            } else {

                for (CEdge e : tgt.getIncomingEdges()) {

                    CNode src = e.getSource();

                    Margins srcMargin = src.getProperty(LayoutOptions.MARGINS);
                    Margins tgtMargin = tgt.getProperty(LayoutOptions.MARGINS);

                    double gy = tgt.getPos().y - src.getPos().y;
                    if (src.getPos().y < tgt.getPos().y) {
                        gy += (srcMargin.bottom + tgtMargin.top) / 2f;
                    } else {
                        gy += (srcMargin.top + tgtMargin.bottom) / 2f;
                    }

                    // fix the y distance
                    SeparationConstraint scY =
                            new SeparationConstraint(Dim.YDIM, src.cIndex, tgt.cIndex, gy, true);
                    graph.constraints.add(scY);

                    double g =
                            src.getSize().x / 2f + tgt.getSize().x / 2f
                                    + (srcMargin.left + srcMargin.right) / 2f
                                    + (tgtMargin.left + tgtMargin.right) / 2f;

                    if (src.getPos().x < tgt.getPos().x) {
                        SeparationConstraint scX =
                                new SeparationConstraint(Dim.XDIM, src.cIndex, tgt.cIndex, g + 20,
                                        false);
                        graph.constraints.add(scX);
                    } else {
                        SeparationConstraint scX =
                                new SeparationConstraint(Dim.XDIM, tgt.cIndex, src.cIndex, g + 20,
                                        false);
                        graph.constraints.add(scX);
                    }

                }

            }

        }

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 20, false);
        algo.setConstraints(graph.constraints);
        algo.makeFeasible();
        algo.run();

        algo.outputInstanceToSVG("compact_pre");

        // algo = new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 20, true);
        // algo.setConstraints(graph.constraints);
        // algo.makeFeasible();
        // algo.run();
        //
        // algo.outputInstanceToSVG("compact_after");

        importer.applyLayout(graph);

    }
}
