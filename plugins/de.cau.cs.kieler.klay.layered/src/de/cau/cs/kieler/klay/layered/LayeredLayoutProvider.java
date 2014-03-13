/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;
import de.cau.cs.kieler.klay.layered.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.layered.graphimport.KGraphImporter;

/**
 * Layout provider to connect the layered layouter to the Eclipse based layout services.
 * 
 * @see KlayLayered
 * 
 * @author msp
 * @author cds
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating proposed yellow by msp
 */
public final class LayeredLayoutProvider extends AbstractLayoutProvider {
    
    ///////////////////////////////////////////////////////////////////////////////
    // Variables

    /** the layout algorithm used for regular layout runs. */
    private final KlayLayered klayLayered = new KlayLayered();


    ///////////////////////////////////////////////////////////////////////////////
    // Regular Layout

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode kgraph, final IKielerProgressMonitor progressMonitor) {
        // Create the hash code counter used to create all graph elements; this is used to ensure
        // that all hash codes are unique, but predictable independently of the object instances.
        HashCodeCounter hashCodeCounter = new HashCodeCounter();

        // Import the graph
        IGraphImporter<KNode> graphImporter = new KGraphImporter(hashCodeCounter);
        LGraph layeredGraph = graphImporter.importGraph(kgraph);

        // Check if hierarchy handling for a compound graph is requested
        KShapeLayout kgraphLayout = kgraph.getData(KShapeLayout.class);
        if (kgraphLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {

            // Layout for all hierarchy levels is requested
            klayLayered.doCompoundLayout(layeredGraph, progressMonitor);
            
        } else {
            
            // Only the top-level graph is processed
            klayLayered.doLayout(layeredGraph, progressMonitor);
        }
        
        if (!progressMonitor.isCanceled()) {
            // Apply the layout results to the original graph
            graphImporter.applyLayout(layeredGraph);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // Layout Testing
    
    /**
     * Import the given KGraph and return a test execution state prepared for a test run with the
     * resulting {@link LGraph}. The layout test run methods can immediately be called on the
     * returned object.
     * 
     * <p><strong>Note:</strong> This method does not apply the layout back to the original KGraph!</p>
     * 
     * @param kgraph the KGraph to be used for the layout test run.
     * @return an initialized test execution state
     */
    public KlayLayered.TestExecutionState startLayoutTest(final KNode kgraph) {
        // Create the hash code counter used to create all graph elements; this is used to ensure
        // that all hash codes are unique, but predictable independently of the object instances.
        HashCodeCounter hashCodeCounter = new HashCodeCounter();

        IGraphImporter<KNode> graphImporter = new KGraphImporter(hashCodeCounter);

        LGraph layeredGraph = graphImporter.importGraph(kgraph);
        
        // Prepare a layout test and return the test execution state
        return klayLayered.prepareLayoutTest(layeredGraph);
    }
    
    /**
     * Return the layered layout algorithm.
     * 
     * @return the layout algorithm
     */
    public KlayLayered getLayoutAlgorithm() {
        return klayLayered;
    }

}
