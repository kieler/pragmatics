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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TGraphBuilder;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
@SuppressWarnings("unused")
public class TreeLayoutProvider extends AbstractLayoutProvider {

    // /////////////////////////////////////////////////////////////////////////////
    // Variables

    /** the layout algorithm used for this layout. */
    private KlayTree klayTree = new KlayTree();

    // /////////////////////////////////////////////////////////////////////////////
    // Regular Layout

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode kgraph, IKielerProgressMonitor progressMonitor) {

        IGraphImporter<KNode> graphImporter = new KGraphImporter();
        TGraph tGraph = graphImporter.importGraph(kgraph);
        
//        TGraph tGraph = TGraphBuilder.createTGraphFromKGraph(kgraph);

        tGraph = klayTree.doLayout(tGraph, progressMonitor);

        // apply the layout results to the original graph
        graphImporter.applyLayout(tGraph);
    }

}
