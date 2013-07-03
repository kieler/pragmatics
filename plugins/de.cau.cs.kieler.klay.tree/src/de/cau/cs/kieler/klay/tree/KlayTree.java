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
package de.cau.cs.kieler.klay.tree;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.erouting.EdgeRouter;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.porder.OrderBalance;
import de.cau.cs.kieler.klay.tree.pplacing.NodePlacer;
import de.cau.cs.kieler.klay.tree.ptreeing.Treeing;

/**
 * Implement a layouter for trees. The T layouter uses the algorithm from
 * "A Node-Positioning Algorithm for General Trees, John Q.Walker II" to layout trees. To do this it
 * uses four phases plus a pree processing to build a corresponding data structure. The first phase
 * "treeing" transforms the given graph to into a tree if necessary. To do this edges which destroy
 * the tree property will be removed and stored to be restored in the post processing. In the second
 * phase "orderNodes" the nodes of each level are seperated into leaves and inner nodes. And then
 * whitespace in the level is filled with leaves. The third phase "placeNodes" uses the algorithm
 * first mentioned from John Q.Walker II to compute the actual position of the nodes. The last phase
 * routeEdges set the positions for the edges corresponding to the positions of the nodes.
 * 
 * Each phase uses intermediate processors for small computations on the graph. The corresponding
 * processor are defined in each phase. Some are defined multiple times, but they are invoked only
 * once.
 * 
 * The processors can determine roots of components and fan outs or level neighbors of nodes.
 * 
 * @author sor
 * @author sgu
 */
public final class KlayTree {

    // /////////////////////////////////////////////////////////////////////////////
    // Variables

    /** phase 1: treeing module. */
    private ILayoutPhase treeing;
    /** phase 2: order module. */
    private ILayoutPhase orderNodes;
    /** phase 3: arrange module. */
    private ILayoutPhase placeNodes;
    /** phase 4: route module. */
    private ILayoutPhase edgeRouter;

    /** intermediate layout processor configuration. */
    private IntermediateProcessingConfiguration intermediateProcessingConfiguration = new IntermediateProcessingConfiguration();

    /** collection of instantiated intermediate modules. */
    private Map<LayoutProcessorStrategy, ILayoutProcessor> intermediateLayoutProcessorCache = new HashMap<LayoutProcessorStrategy, ILayoutProcessor>();

    /** list of layout processors that compose the current algorithm. */
    private List<ILayoutProcessor> algorithm = new LinkedList<ILayoutProcessor>();

    // /////////////////////////////////////////////////////////////////////////////
    // Regular Layout

    /**
     * Does a layout on the given graph.
     * 
     * @param tgraph
     *            the graph to layout.
     * @param progressMonitor
     *            a progress monitor to show progress information in.
     * @return tree graph with layout applied.
     */
    public TGraph doLayout(final TGraph tgraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Tree layout", 1);

        // set up the phases and processors depending on user options
        updateModules(tgraph);

        // do layout for each component
        layout(tgraph, progressMonitor.subTask(1.0f));

        progressMonitor.done();

        return tgraph;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Options and Modules Management

    /**
     * Update the modules depending on user options.
     * 
     * @param graph
     *            the graph to be laid out.
     */
    private void updateModules(final TGraph graph) {

        // build a tree
        if (treeing == null) {
            treeing = new Treeing();
        }

        // order nodes
        if (orderNodes == null) {
            orderNodes = new OrderBalance();
        }

        // set node placement strategy to use
        // arrange nodes
        if (placeNodes == null) {
            placeNodes = new NodePlacer();
        }

        // set node placement strategy to use
        // arrange nodes
        if (edgeRouter == null) {
            edgeRouter = new EdgeRouter();
        }

        // update intermediate processor configuration
        intermediateProcessingConfiguration.clear();
        intermediateProcessingConfiguration
                .addAll(treeing.getIntermediateProcessingConfiguration(graph))
                .addAll(orderNodes.getIntermediateProcessingConfiguration(graph))
                .addAll(placeNodes.getIntermediateProcessingConfiguration(graph))
                .addAll(edgeRouter.getIntermediateProcessingConfiguration(graph));
        // TODO add intermediate processing configuration for block direction
        // .addAll(this.getIntermediateProcessingConfiguration(graph));

        // construct the list of processors that make up the algorithm
        algorithm.clear();
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_1));
        algorithm.add(treeing);
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_2));
        algorithm.add(orderNodes);
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_3));
        algorithm.add(placeNodes);
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_4));
        algorithm.add(edgeRouter);
    }

    /**
     * Returns a list of layout processor instances for the given intermediate layout processing
     * slot.
     * 
     * @param slotIndex
     *            the slot index. One of the constants defined in
     *            {@link IntermediateProcessingConfiguration}.
     * @return list of layout processors.
     */
    private List<ILayoutProcessor> getIntermediateProcessorList(final int slotIndex) {
        // fetch the set of layout processors configured for the given slot
        EnumSet<LayoutProcessorStrategy> processors = intermediateProcessingConfiguration
                .getProcessors(slotIndex);
        List<ILayoutProcessor> result = new ArrayList<ILayoutProcessor>(processors.size());

        // iterate through the layout processors and add them to the result list; the EnumSet
        // guarantees that we iterate over the processors in the order in which they occur in
        // the LayoutProcessorStrategy, thereby satisfying all of their runtime order
        // dependencies without having to sort them in any way
        for (LayoutProcessorStrategy processor : processors) {
            // check if an instance of the given layout processor is already in the cache
            ILayoutProcessor processorImpl = intermediateLayoutProcessorCache.get(processor);

            if (processorImpl == null) {
                // It's not in the cache, so create it and put it in the cache
                processorImpl = processor.create();
                intermediateLayoutProcessorCache.put(processor, processorImpl);
            }

            // add the layout processor to the list of processors for this slot
            result.add(processorImpl);
        }

        return result;
    }

    // TODO add this for intermediate processing configuration for block direction
    // /**
    // * Returns an intermediate processing configuration with processors not tied to specific
    // phases.
    // *
    // * @param graph
    // * the tree graph to be processed. The configuration may vary depending on certain
    // * properties of the graph.
    // * @return intermediate processing configuration. May be {@code null}.
    // */
    // private IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
    // final TGraph graph) {
    //
    // // get graph properties
    //
    // // Basic configuration
    // IntermediateProcessingConfiguration configuration = new
    // IntermediateProcessingConfiguration();
    //
    // // graph transformations for unusual layout directions
    // switch (graph.getProperty(LayoutOptions.DIRECTION)) {
    // case LEFT:
    // // ADD LEFT_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
    // break;
    // case RIGHT:
    // // ADD RIGHT_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
    // break;
    // case DOWN:
    // // ADD DOWN_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
    // break;
    // case UP:
    // // ADD UP_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
    // break;
    // default:
    // // This is either RIGHT or UNDEFINED, which is just mapped to RIGHT. Either way, we
    // // don't
    // // need any processors here
    // break;
    // }
    //
    // // Additional dependencies
    //
    // return configuration;
    // }

    // /////////////////////////////////////////////////////////////////////////////
    // Layout

    /**
     * Perform the phases of the tree layouter.
     * 
     * @param graph
     *            the graph that is to be laid out
     * @param themonitor
     *            a progress monitor, or {@code null}
     */
    private void layout(final TGraph graph, final IKielerProgressMonitor themonitor) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Layout", algorithm.size());

        if (graph.getProperty(LayoutOptions.DEBUG_MODE)) {
            // Debug Mode!
            // Prints the algorithm configuration and outputs the whole graph to a file
            // before each slot execution

            System.out.println("KLay Tree uses the following " + algorithm.size() + " modules:");
            for (int i = 0; i < algorithm.size(); i++) {
                System.out.println("   Slot " + String.format("%1$02d", i) + ": "
                        + algorithm.get(i).getClass().getName());
            }
        }
        // invoke each layout processor
        for (ILayoutProcessor processor : algorithm) {
            if (monitor.isCanceled()) {
                return;
            }
            processor.process(graph, monitor.subTask(2));
        }
        monitor.done();
    }
}
