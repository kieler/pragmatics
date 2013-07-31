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
package de.cau.cs.kieler.klay.tree;

import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.GenTestGraph;

/**
 * Layout provider to connect the tree layouter to the Eclipse based layout services and orchestrate
 * the pre layout processing .
 * 
 * @author sor
 * @author sgu
 */
public class TreeLayoutProvider extends AbstractLayoutProvider {

    // /////////////////////////////////////////////////////////////////////////////
    // Variables

    /** the layout algorithm used for this layout. */
    private KlayTree klayTree = new KlayTree();
    /** connected components processor. */
    private ComponentsProcessor componentsProcessor = new ComponentsProcessor();

    // /////////////////////////////////////////////////////////////////////////////
    // Regular Layout

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode kgraph, IKielerProgressMonitor progressMonitor) {

        if (kgraph.getData(KShapeLayout.class) != null
                && kgraph.getData(KShapeLayout.class).getProperty(LayoutOptions.DEBUG_MODE)) {
            KShapeLayout graphLayout = kgraph.getData(KShapeLayout.class);
            int graphs = graphLayout.getProperty(Properties.TEST_GRAPHS);
            int nodes = graphLayout.getProperty(Properties.TEST_NODES);

            GenTestGraph testGenerator = new GenTestGraph();

            for (int i = 0; i < graphs; i++) {

                KNode testGraph = testGenerator.generateKgraph(nodes);

                process(testGraph, progressMonitor);

                testGenerator.exportLayoutGraph(testGraph);

            }
        }
        process(kgraph, progressMonitor);
    }

    /**
     * @param kgraph
     */
    private void process(final KNode kgraph, IKielerProgressMonitor progressMonitor) {
        // build tGraph
        IGraphImporter<KNode> graphImporter = new KGraphImporter();
        TGraph tGraph = graphImporter.importGraph(kgraph);

        // split the input graph into components
        List<TGraph> components = componentsProcessor.split(tGraph);

        // perform the actual layout on the components
        for (TGraph comp : components) {
            klayTree.doLayout(comp, progressMonitor.subTask(1.0f / components.size()));
        }

        // pack the components back into one graph
        tGraph = componentsProcessor.pack(components);

        // apply the layout results to the original graph
        graphImporter.applyLayout(tGraph);
    }

}
