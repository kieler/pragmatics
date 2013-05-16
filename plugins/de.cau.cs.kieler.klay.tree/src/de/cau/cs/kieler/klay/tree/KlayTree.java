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
import java.util.Random;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * 
 * @author sor
 * @author sgu
 */
public final class KlayTree {

    // /////////////////////////////////////////////////////////////////////////////
    // Variables

    //TODO build actual phase
    /** phase 1: test module. */
    private ILayoutPhase testPhase;

    /** intermediate layout processor configuration. */
    private IntermediateProcessingConfiguration intermediateProcessingConfiguration 
                                         = new IntermediateProcessingConfiguration();

    /** collection of instantiated intermediate modules. */
    private Map<LayoutProcessorStrategy, ILayoutProcessor> intermediateLayoutProcessorCache 
                 = new HashMap<LayoutProcessorStrategy, ILayoutProcessor>();
    
    /** list of layout processors that compose the current algorithm. */
    private List<ILayoutProcessor> algorithm = new LinkedList<ILayoutProcessor>();

    /** index of the processor that is to be executed next during a layout test. */
    private int currentLayoutTestStep = 0;


    // /////////////////////////////////////////////////////////////////////////////
    // Regular Layout
    
    /**
     * Does a layout on the given graph.
     * 
     * @param tgraph the graph to layout.
     * @param progressMonitor a progress monitor to show progress information in.
     * @return tree graph with layout applied.
     */
    public TGraph doLayout(final TGraph tgraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Tree layout", 1);

        // set special properties for the tree graph
        setOptions(tgraph);

        // update the modules depending on user options
        updateModules(tgraph);

        // do layout
        // maybe split the graph into multiple components and combine them after the layout
        
        layout(tgraph, progressMonitor.subTask(1.0f));

        progressMonitor.done();
        
        return tgraph;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Options and Modules Management

    /**
     * Set special layout options for the tree graph.
     * 
     * @param tGraph
     *            a new tree graph
     */
    private void setOptions(final TGraph tGraph) {
        // set the random number generator based on the random seed option
        Integer randomSeed = tGraph.getProperty(LayoutOptions.RANDOM_SEED);
        if (randomSeed != null) {
            int val = randomSeed;
            if (val == 0) {
                tGraph.setProperty(Properties.RANDOM, new Random());
            } else {
                tGraph.setProperty(Properties.RANDOM, new Random(val));
            }
        } else {
            tGraph.setProperty(Properties.RANDOM, new Random(1));
        }
    }

    /**
     * Update the modules depending on user options.
     * 
     * @param graph
     *            the graph to be laid out.
     */
    private void updateModules(final TGraph graph) {
        // check which cycle breaking strategy to use
       
        
        // check which crossing minimization strategy to use
        
        
        // check which node placement strategy to use
        

        // check which edge router to use
        

        // update intermediate processor configuration
        intermediateProcessingConfiguration.clear();
        intermediateProcessingConfiguration
                .addAll(testPhase.getIntermediateProcessingConfiguration(graph));

        // construct the list of processors that make up the algorithm
        algorithm.clear();
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_1));
        algorithm.add(testPhase);
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

    /**
     * Returns an intermediate processing configuration with processors not tied to specific phases.
     * 
     * @param graph
     *            the tree graph to be processed. The configuration may vary depending on certain
     *            properties of the graph.
     * @return intermediate processing configuration. May be {@code null}.
     */
    private IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final TGraph graph) {
        
        // get graph properties
        
        // Basic configuration
        IntermediateProcessingConfiguration configuration = new IntermediateProcessingConfiguration();
        
        // graph transformations for unusual layout directions
        switch (graph.getProperty(LayoutOptions.DIRECTION)) {
        case LEFT:
            // ADD LEFT_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
            break;
        case RIGHT:
            // ADD RIGHT_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
            break;
        case DOWN:
         // ADD DOWN_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
            break;
        case UP:
         // ADD UP_DIR_POSTPROCESSOR to IntermediateProcessingConfiguration
            break;
        default:
            // This is either RIGHT or UNDEFINED, which is just mapped to RIGHT. Either way, we don't
            // need any processors here
            break;
        }

        // Additional dependencies

        return configuration;
    }
    

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
                processor.process(graph, monitor.subTask(1));
            }
        monitor.done();
    }

    /**
     * Executes the given layout processor on the given list of graphs.
     * 
     * @param graphs the list of graphs to be laid out.
     * @param monitor a progress monitor.
     * @param processor processor to execute.
     */
    private void layoutTest(final List<TGraph> graphs, final IKielerProgressMonitor monitor,
            final ILayoutProcessor processor) {
        
        monitor.begin("Layout", graphs.size());
        
        // invoke the layout processor on each of the given graphs
        for (TGraph graph : graphs) {
            if (monitor.isCanceled()) {
                return;
            }
            
            processor.process(graph, monitor.subTask(1));
        }

        monitor.done();
    }
    
    // /////////////////////////////////////////////////////////////////////////////
    // Processing Configuration Constants

    

}
