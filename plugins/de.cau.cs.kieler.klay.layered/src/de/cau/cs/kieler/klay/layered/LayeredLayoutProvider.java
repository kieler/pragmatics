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

import java.util.List;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;

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

    // /////////////////////////////////////////////////////////////////////////////
    // Variables

    /** the layout algorithm. */
    private KlayLayered klayLayered = new KlayLayered();

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode kgraph, final IKielerProgressMonitor progressMonitor) {
        // Create the hash code counter used to create all graph elements; this is used to ensure
        // that all hash codes are unique, but predictable independently of the object instances.
        HashCodeCounter hashCodeCounter = new HashCodeCounter();

        KShapeLayout sourceShapeLayout = kgraph.getData(KShapeLayout.class);
        IGraphImporter<KNode> graphImporter;

        // Check if hierarchy handling for a compound graph is requested, choose importer accordingly
        boolean isCompound = sourceShapeLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY);
        if (isCompound) {
            graphImporter = new CompoundKGraphImporter(hashCodeCounter);
        } else {
            graphImporter = new KGraphImporter(hashCodeCounter);
        }

        // import the graph
        LGraph layeredGraph = graphImporter.importGraph(kgraph);

        // apply layout
        LGraph result = klayLayered.doLayout(layeredGraph, progressMonitor);

        // apply the layout results to the original graph
        graphImporter.applyLayout(result);
    }
    
    /**
     * Does a layout on the given graph, but only to the point where the given phase or processor was
     * executed. If connected components processing was active, the returned list will contain one
     * layered graph for each connected component; if the processing was not active, the list will only
     * contain one layered graph. Either way, the layered graphs are in the state they were in after
     * execution of the given phase finished.
     * 
     * <p>If the given phase does not exist in the algorithm's configuration or is {@code null}, the
     * returned result is the connected components just prior to the execution of the first phase.</p>
     * 
     * <p><strong>Note:</strong> This method does not apply the layout back to the original kgraph!</p>
     * 
     * @param kgraph the graph to layout.
     * @param progressMonitor a progress monitor to show progress information in.
     * @param phase the phase or processor to stop after.
     * @return list of connected components after the execution of the given phase.
     */
    public List<LGraph> doLayoutTest(final KNode kgraph,
            final IKielerProgressMonitor progressMonitor,
            final Class<? extends ILayoutProcessor> phase) {
        // Create the hash code counter used to create all graph elements; this is used to ensure
        // that all hash codes are unique, but predictable independently of the object instances.
        HashCodeCounter hashCodeCounter = new HashCodeCounter();

        KShapeLayout sourceShapeLayout = kgraph.getData(KShapeLayout.class);
        IGraphImporter<KNode> graphImporter;

        // Check if hierarchy handling for a compound graph is requested, choose importer
        // accordingly
        boolean isCompound = sourceShapeLayout.getProperty(LayoutOptions.LAYOUT_HIERARCHY);
        if (isCompound) {
            graphImporter = new CompoundKGraphImporter(hashCodeCounter);
        } else {
            graphImporter = new KGraphImporter(hashCodeCounter);
        }

        LGraph layeredGraph = graphImporter.importGraph(kgraph);

        // apply test layout
        return klayLayered.doLayoutTest(layeredGraph, progressMonitor, phase);
    }

}
