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

import org.adaptagrams.ACALayout;
import org.adaptagrams.PreIteration;
import org.adaptagrams.TestConvergence;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;

/**
 * @author uru
 * 
 */
public class ACALayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("ACA Layout", 1);

        parentNode.getData(KShapeLayout.class).setProperty(ColaProperties.PORT_DUMMIES, false);

        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();

        KGraphImporter importer = new KGraphImporter();
        final CGraph graph = importer.importGraph(parentNode);

        // run ACA
        ACALayout aca = new ACALayout(graph.nodes, graph.edges, graph.constraints, 40, true);

        aca.createAlignments();

        aca.layout();

        System.out.println("Done");
        
        importer.applyLayout(graph);

        progressMonitor.done();
    }

}
