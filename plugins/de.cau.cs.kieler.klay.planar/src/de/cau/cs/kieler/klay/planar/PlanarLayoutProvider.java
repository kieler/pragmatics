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
package de.cau.cs.kieler.klay.planar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.planar.p1planar.BoyerMyrvoldPlanarSubgraphBuilder;
import de.cau.cs.kieler.klay.planar.p1planar.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.p1planar.LRPlanarSubgraphBuilder;
import de.cau.cs.kieler.klay.planar.p1planar.PlanarityTestStrategy;
import de.cau.cs.kieler.klay.planar.p2ortho.TamassiaOrthogonalizer;
import de.cau.cs.kieler.klay.planar.p3compact.GiottoCompactor;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Layout provider for an orthogonal layout.
 * 
 * @author ocl
 * @author pdo
 * @author pkl
 */
public class PlanarLayoutProvider extends AbstractLayoutProvider {

    // ======================== Variables ============================
    /** Graph factory. */
    private PGraphFactory factory = new PGraphFactory();

    /** phase 1: algorithm for planar testing and building a subgraph. */
    private ILayoutPhase subgraphBuilder = new BoyerMyrvoldPlanarSubgraphBuilder();

    /** phase 2: algorithm for inserting edges that are removed in the step before. */
    private ILayoutPhase edgeInserter = new EdgeInsertionPlanarization();

    /** phase 3: algorithm for orthogonalization. */
    private ILayoutPhase orthogonalizer = new TamassiaOrthogonalizer();

    /** phase 4: algorithm for compaction. */
    private ILayoutPhase compactor = new GiottoCompactor();

    /** connected components processor. */
    private ComponentsProcessor componentsProcessor = new ComponentsProcessor();
    /** intermediate layout processor strategy. */
    private IntermediateProcessingStrategy intermediateProcessingStrategy = new IntermediateProcessingStrategy();
    /** collection of instantiated intermediate modules. */
    private Map<IntermediateLayoutProcessor, ILayoutProcessor> intermediateLayoutProcessorCache = new HashMap<IntermediateLayoutProcessor, ILayoutProcessor>();

    /** list of layout processors that compose the current algorithm. */
    private List<ILayoutProcessor> algorithm = new LinkedList<ILayoutProcessor>();

    /**
     * Returns an intermediate processing strategy with processors not tied to specific phases.
     * 
     * @param graph
     *            the layered graph to be processed. The strategy may vary depending on certain
     *            properties of the graph.
     * @return intermediate processing strategy. May be {@code null}.
     */
    private IntermediateProcessingStrategy getIntermediateProcessingStrategy(final PGraph graph) {
        // TODO think about graphproperties Set<GraphProperties> graphProperties =
        // graph.getProperty(Properties.GRAPH_PROPERTIES);
        return null;
    }

    /**
     * Returns a list of layout processor instances for the given intermediate layout processing
     * slot.
     * 
     * @param slotIndex
     *            the slot index. One of the constants defined in
     *            {@link IntermediateProcessingStrategy}.
     * @return list of layout processors.
     */
    private List<ILayoutProcessor> getIntermediateProcessorList(final int slotIndex) {
        // fetch the set of layout processors configured for the given slot
        Set<IntermediateLayoutProcessor> processors = intermediateProcessingStrategy
                .getProcessors(slotIndex);
        List<ILayoutProcessor> result = new ArrayList<ILayoutProcessor>(processors.size());

        // iterate through the layout processors and add them to the result list
        for (IntermediateLayoutProcessor processor : processors) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode kgraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Orthogonal Layout", 1);

        // KGraph -> PGraph conversion
        PGraph pgraph = this.factory.createGraphFromKGraph(kgraph);

        // update the modules
        updateModules(pgraph);

        // if (pgraph.getProperty(LayoutOptions.DEBUG_MODE)) {
        // Util.clearTmpDir();
        // Util.storeGraph(pgraph, 50, false);
        // }
        // split the input graph into components
        // TODO work with the components processor!
        // List<PGraph> components = componentsProcessor.split(pgraph);

        // if (pgraph.getProperty(LayoutOptions.DEBUG_MODE)) {
        // Util.storeGraph(pgraph, 51, false);
        // }
        // perform the actual layout
        // for (PGraph comp : components) {
        layout(pgraph, null);
        // }
        // layout(components.get(1), progressMonitor.subTask(1.0f / components.size()));

        // pack the components back into one graph
        // pgraph = componentsProcessor.pack(components);

        // TODO do that step
        // apply the layout results to the original graph
        // this.factory.applyLayout(pgraph);

        progressMonitor.done();
    }

    /**
     * @param graph
     */
    private void updateModules(final PGraph graph) {
        if (graph.getProperty(Properties.PLANAR_TESTING_ALGORITHM) == PlanarityTestStrategy.BOYER_MYRVOLD_ALGORITHM) {
            if (!(this.subgraphBuilder instanceof BoyerMyrvoldPlanarSubgraphBuilder)) {
                this.subgraphBuilder = new BoyerMyrvoldPlanarSubgraphBuilder();
            }
        } else {
            if (!(this.subgraphBuilder instanceof LRPlanarSubgraphBuilder)) {
                this.subgraphBuilder = new LRPlanarSubgraphBuilder();
            }
        }

        // update intermediate processor strategy
        intermediateProcessingStrategy.clear();
        intermediateProcessingStrategy
                .addAll(subgraphBuilder.getIntermediateProcessingStrategy(graph))
                .addAll(edgeInserter.getIntermediateProcessingStrategy(graph))
                .addAll(orthogonalizer.getIntermediateProcessingStrategy(graph));
        // TODO implement compactor
        // .addAll(compactor.getIntermediateProcessingStrategy(graph));
        // .addAll(this.getIntermediateProcessingStrategy(graph))

        // construct the list of processors that make up the algorithm
        algorithm.clear();
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_1));
        algorithm.add(subgraphBuilder);
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_2));
        algorithm.add(edgeInserter);
        algorithm
                .addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_3));
        algorithm.add(orthogonalizer);
        // TODO implement compactor
        // algorithm
        // .addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_4));
        // algorithm.add(compactor);
        // algorithm
        // .addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.AFTER_PHASE_4));
    }

    // ======================================= Layout ===============================

    /**
     * Perform the four phases of the topology-shape-metrics layouter.
     * 
     * @param graph
     *            the graph that is to be laid out
     * @param themonitor
     *            a progress monitor, or {@code null}
     */
    public void layout(final PGraph graph, final IKielerProgressMonitor themonitor) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Component Layout", algorithm.size());

        if (graph.getProperty(LayoutOptions.DEBUG_MODE)) {
            Util.clearTmpDir();
            // Debug Mode!
            // Prints the algorithm configuration and outputs the whole graph to a file
            // before each slot execution

            System.out.println("KLay planar uses the following " + algorithm.size() + " modules:");
            for (int i = 0; i < algorithm.size(); i++) {
                System.out.println("   Slot " + String.format("%1$02d", i) + ": "
                        + algorithm.get(i).getClass().getName());
            }

            // invoke each layout processor
            int slotIndex = 0;
            System.out.println("graph:\n" + graph.toString());
            for (ILayoutProcessor processor : algorithm) {
                if (monitor.isCanceled()) {
                    return;
                }
                processor.reset(monitor.subTask(1));
                processor.process(graph);
                // graph debug output
                Util.storeGraph(graph, slotIndex++, false);

            }

        } else {
            // invoke each layout processor
            for (ILayoutProcessor processor : algorithm) {
                if (monitor.isCanceled()) {
                    return;
                }
                processor.reset(monitor.subTask(1));
                processor.process(graph);
            }
        }
        monitor.done();
    }

}
