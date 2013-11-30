/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.service;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.DefaultFactory;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataFactoryImpl;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataPackageImpl;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kwebs.server.RemoteServiceException;
import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.server.layout.ServerGraphFormatsService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.service.filter.LayoutFilter;
import de.cau.cs.kieler.kwebs.server.service.filter.LayoutFilterData;
import de.cau.cs.kieler.kwebs.server.service.filter.LayoutFilters;
import de.cau.cs.kieler.kwebs.server.util.Graphs;

/**
 * This abstract base class provides the implementation of the layout functionality. Web service
 * architecture specific service implementations may sub class this class in order to use the provided
 * layout.
 *
 * @kieler.design 2011-08-25 reviewed by ckru, msp, mri
 * @author swe
 * @author msp
 */
public abstract class AbstractService {

    //////////
    
    /** The layout engine used. */
    private static RecursiveGraphLayoutEngine layoutEngine
        = new RecursiveGraphLayoutEngine();
    
    //ToDo: Make filter chains configurable declatively
    /** */
    private final LayoutFilters preFilters
        = new LayoutFilters();

    /** */
    private final LayoutFilters postFilters
        = new LayoutFilters();
    
    //////////
    
    /**
     * Protected constructor. Initializes the layout data services.
     */
    protected AbstractService() {
        LayoutDataService.setInstanceFactory(new DefaultFactory<LayoutDataService>(
                ServerLayoutDataService.class));
        GraphFormatsService.setInstanceFactory(new DefaultFactory<GraphFormatsService>(
                ServerGraphFormatsService.class));
        initFilters();
    }

    /** */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.server.configuration";

    /** */
    private static final String ELEMENT_PREFILTERS
        = "preFilters";

    /** */
    private static final String ELEMENT_POSTFILTERS
        = "postFilters";

    /** */
    private static final String ELEMENT_LAYOUTFILTER
        = "layoutFilter";

    /** */
    private static final String ATTRIBUTE_DESCRIPTION
        = "description";

    /** */
    private static final String ATTRIBUTE_PRIORITY
        = "priority";

    /** */
    private static final String ATTRIBUTE_IMPLEMENTATION
        = "implementation";

    /**
     * 
     */
    private void initFilters() {
        
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        
        if (registry == null) {
            return;
        }
        
        for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSIONPOINT_ID)) {
            
            if (
                element.getName().equals(ELEMENT_PREFILTERS)
                ||
                element.getName().equals(ELEMENT_POSTFILTERS)
            ) {
        
                for (IConfigurationElement filter : element.getChildren(ELEMENT_LAYOUTFILTER)) {
                    
                    try {
                        
                        final int priority 
                            = Integer.parseInt(filter.getAttribute(ATTRIBUTE_PRIORITY));
                        final String description 
                            = filter.getAttribute(ATTRIBUTE_DESCRIPTION);
                        final LayoutFilter instance 
                            = (LayoutFilter) filter.createExecutableExtension(ATTRIBUTE_IMPLEMENTATION);
                        
                        instance.setPriority(priority);
                        instance.setDescription(description);
                        
                        if (element.getName().equals(ELEMENT_PREFILTERS)) {
                            
                            Logger.log(
                                Severity.INFO, 
                                "Added pre processing filter '" 
                                + instance.getClass().getSimpleName() 
                                + "'."
                            );
                            
                            preFilters.addSegment(instance);
                            
                        } else {

                            Logger.log(
                                Severity.INFO, 
                                "Added post processing filter '" 
                                + instance.getClass().getSimpleName() 
                                + "'."
                            );
                            
                            postFilters.addSegment(instance);
                            
                        }
                        
                    } catch (CoreException e) {
                        Logger.log(Severity.CRITICAL, "Could not create filter.", e);
                    }
                    
                }
                
            } 
            
        }
        
//        final Comparator<LayoutFilter> comparator = new Comparator<LayoutFilter>() {
//            public int compare(final LayoutFilter filter1, final LayoutFilter filter2) {
//                return filter1.getPriority() - filter2.getPriority();
//            }
//        };
        
//        preFilters.sortSegments(comparator);
//        postFilters.sortSegments(comparator);
        
    }
    
    //////////
    
    /**
     * Base implementation of layout functionality.
     *
     * @param serializedGraph
     *            the graph to do layout on in serial representation
     * @param informat
     *            the format of the serial input graph
     * @param outformat
     *            the format of the serial output graph
     * @param options
     *            the optional layout options
     * @return the graph on which the layout was done in the same format as used for the source graph
     */
    protected final String layout(final String serializedGraph, final String informat,
            final String outformat, final List<GraphLayoutOption> options) {
        // Parameter testing
        if (serializedGraph == null) {
            throw new IllegalArgumentException("No input graph to process.");
        }
        if (informat == null) {
            throw new IllegalArgumentException("No input graph format was specified.");
        }

        Logger.log(Severity.DEBUG, "Starting layout");
        GraphFormatData informatData = GraphFormatsService.getInstance()
                .getFormatDataBySuffix(informat);
        if (informatData == null) {
            throw new IllegalArgumentException("Graph format \"" + informat + "\" is unknown.");
        }
        String serializedResult;
        if (outformat == null || outformat.equals(informat)) {
            serializedResult = layout(serializedGraph, informatData.getHandler(), null, options);
        } else {
            GraphFormatData outformatData = GraphFormatsService.getInstance()
                    .getFormatDataBySuffix(outformat);
            if (outformatData == null) {
                throw new IllegalArgumentException("Graph format \"" + outformat + "\" is unknown.");
            }
            serializedResult = layout(serializedGraph, informatData.getHandler(),
                    outformatData.getHandler(), options);
        }

        Logger.log(Severity.DEBUG, "Finished layout");
        return serializedResult;
    }

    /** factor for nanoseconds. */
    private static final double NANO_FACT = 1e9;

    /**
     * Perform layout using a given graph transformer.
     *
     * @param <I>
     *            object type for the input graph format
     * @param <O>
     *            object type for the output graph format
     * @param serializedGraph
     *            the graph to do layout on in serial representation
     * @param inhandler
     *            an input graph transformation handler
     * @param outhandler
     *            an output graph transformation handler, or {@code null}
     * @param options
     *            the optional layout options
     * @return the graph on which the layout was done in the same format as used for the source graph
     */
    private <I, O> String layout(final String serializedGraph,
            final IGraphFormatHandler<I> inhandler, final IGraphFormatHandler<O> outhandler,
            final List<GraphLayoutOption> options) {
        
        // Start measuring the total time of the operation
        double operationStarted = System.nanoTime();

        // Get the graph instances of which the layout is to be calculated
        TransformationData<I, KNode> inTransData = new TransformationData<I, KNode>();
        if (options != null) {
            annotateTransData(inTransData, options);
        }
        inhandler.deserialize(serializedGraph, inTransData);
        
        // The input was empty, so return an empty graph
        if (inTransData.getSourceGraph() == null) {
            return "";
        }

        // Derive the layout structures of the graph instances
        inhandler.getImporter().transform(inTransData);
        Iterator<String> messageIter = inTransData.getMessages().iterator();
        while (messageIter.hasNext()) {
            Logger.log(messageIter.next());
            messageIter.remove();
        }

        List<KNode> graphs = inTransData.getTargetGraphs();

        // Create filter DTO
        LayoutFilterData filterData = new LayoutFilterData(graphs, options);
        
        // Apply request pre processing filters
        if (!preFilters.apply(filterData)) {
            throw new RemoteServiceException("Request did not pass pre-filters.");
        }
        
        // Parse the transmitted layout options and annotate the layout structure
        if (options != null) {
            for (KNode layout : graphs) {
                annotateGraph(layout, options);
            }
        }

        // Actually do the layout on the structure
        double layoutTime = 0;
        if (!inTransData.getProperty(LayoutOptions.NO_LAYOUT)) {
            for (KNode layout : graphs) {
                IKielerProgressMonitor layoutMonitor = new BasicProgressMonitor();
                layoutEngine.layout(layout, layoutMonitor);
                layoutTime += layoutMonitor.getExecutionTime() * NANO_FACT;
            }
        }

        // Calculate statistical values and annotate graph if it is a KGraph instance.
        // The serialization process can not be included.
        if (inTransData.getSourceGraph() instanceof KNode) {
            double supplementalTime = System.nanoTime() - operationStarted - layoutTime;
            annotateStatistics((KNode) inTransData.getSourceGraph(), inTransData.getTargetGraphs(),
                    serializedGraph.length(), layoutTime, supplementalTime);
        }

        String serializedResult;
        if (outhandler == null) {
            if (!inTransData.getProperty(LayoutOptions.NO_LAYOUT)) {
                // Apply the calculated layout back to the graph instance
                inhandler.getImporter().transferLayout(inTransData);
            }

            // Serialize the resulting graph
            TransformationData<KNode, I> outTransData = new TransformationData<KNode, I>();
            annotateTransData(outTransData, options);
            outTransData.getTargetGraphs().add(inTransData.getSourceGraph());
            serializedResult = inhandler.serialize(outTransData);

        } else {
            StringBuilder outGraphBuilder = new StringBuilder();
            for (KNode layoutGraph : inTransData.getTargetGraphs()) {
                // Transform the graph to the output format
                TransformationData<KNode, O> outTransData = new TransformationData<KNode, O>();
                annotateTransData(outTransData, options);
                outTransData.setSourceGraph(layoutGraph);
                outhandler.getExporter().transform(outTransData);
                messageIter = outTransData.getMessages().iterator();
                while (messageIter.hasNext()) {
                    Logger.log(messageIter.next());
                    messageIter.remove();
                }

                // Serialize the resulting graphs
                outGraphBuilder.append(outhandler.serialize(outTransData));

            }
            serializedResult = outGraphBuilder.toString();
        }
        
        //ToDo: Set statistics in filter dto
        
        // Apply request post processing filters
        if (!postFilters.apply(filterData)) {
            throw new RemoteServiceException("Request did not pass post-filters.");
        }
        
        return serializedResult;
        
    }

    /**
     * Create statistics for the layout process and annotate the graph.
     *
     * @param sourceGraph the source graph to annotate
     * @param layoutGraphs the generated layout graphs
     * @param serializedSize the size of the serialized graph
     * @param layoutTime the time taken for layout
     * @param supplementalTime the time taken for supplemental operations
     */
    private void annotateStatistics(final KNode sourceGraph, final List<KNode> layoutGraphs,
            final int serializedSize, final double layoutTime, final double supplementalTime) {
        // Graph related statistics
        int nodes = 0;
        int ports = 0;
        int labels = 0;
        int edges = 0;
        for (KNode layout : layoutGraphs) {
            for (KGraphElement element : Graphs.getAllElementsOfType(layout, KGraphElement.class)) {
                if (element instanceof KNode) {
                    nodes++;
                } else if (element instanceof KPort) {
                    ports++;
                } else if (element instanceof KLabel) {
                    labels++;
                } else if (element instanceof KEdge) {
                    edges++;
                }
            }
        }
        Statistics statistics = new Statistics();
        statistics.setBytes(serializedSize);
        statistics.setNodes(nodes);
        statistics.setPorts(ports);
        statistics.setLabels(labels);
        statistics.setEdges(edges);
        // Execution time related statistics
        statistics.setTimeLayout(layoutTime);
        statistics.setTimeRemoteSupplemental(supplementalTime);
        KIdentifier identifier = sourceGraph.getData(KIdentifier.class);
        if (identifier == null) {
            identifier = KLayoutDataFactoryImpl.eINSTANCE.createKIdentifier();
            sourceGraph.getData().add(identifier);
        }
        identifier.setProperty(Statistics.STATISTICS, statistics.toString());
    }

    /**
     * Annotate transformation data with the given layout options. This is done so the user
     * can control how graphs are imported and exported.
     *
     * @param transData a transformation data instance
     * @param options a list of layout options
     */
    @SuppressWarnings("unchecked")
    private void annotateTransData(final TransformationData<?, ?> transData,
            final List<GraphLayoutOption> options) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        for (GraphLayoutOption option : options) {
            LayoutOptionData<?> optionData = dataService.getOptionDataBySuffix(option.getId());
            if (optionData != null) {
                Object optionValue = optionData.parseValue(option.getValue());
                if (optionValue != null) {
                    transData.setProperty((LayoutOptionData<Object>) optionData, optionValue);
                }
            }
        }
    }

    /**
     * Annotate the graph with the given layout options.
     *
     * @param graph a layout graph
     * @param options a list of layout options
     */
    private void annotateGraph(final KNode graph, final List<GraphLayoutOption> options) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        for (GraphLayoutOption option : options) {
            LayoutOptionData<?> optionData = dataService.getOptionDataBySuffix(option.getId());
            // Fail silent on invalid option declarations
            if (optionData != null) {
                Object optionValue = optionData.parseValue(option.getValue());
                if (optionValue != null) {
                    if (optionData.getTargets().isEmpty()
                            || optionData.getTargets().contains(Target.PARENTS)) {
                        // special support for algorithm ids and layout type ids
                        if (optionData.equals(LayoutOptions.ALGORITHM)) {
                            LayoutAlgorithmData algoData = dataService.getAlgorithmDataBySuffix(
                                    (String) optionValue);
                            if (algoData != null) {
                                optionValue = algoData.getId();
                            } else {
                                LayoutTypeData typeData = dataService.getTypeDataBySuffix(
                                        (String) optionValue);
                                if (typeData != null) {
                                    optionValue = typeData.getId();
                                }
                            }
                        }
                        // annotate all parent nodes of the graph
                        annotateGraphElement(graph, KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(),
                                optionData, optionValue);
                        for (KNode node : Graphs.getAllElementsOfType(graph, KNode.class)) {
                            if (node.getChildren().size() > 0) {
                                annotateGraphElement(node,
                                        KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(),
                                        optionData, optionValue);
                            }
                        }
                    }
                    if (optionData.getTargets().isEmpty()
                            || optionData.getTargets().contains(Target.NODES)) {
                        // annotate all child nodes of the graph
                        for (KNode node : Graphs.getAllElementsOfType(graph, KNode.class)) {
                            annotateGraphElement(node,
                                    KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(),
                                    optionData, optionValue);
                        }
                    }
                    if (optionData.getTargets().isEmpty()
                            || optionData.getTargets().contains(Target.EDGES)) {
                        // annotate all edges of the graph
                        for (KEdge edge : Graphs.getAllElementsOfType(graph, KEdge.class)) {
                            annotateGraphElement(edge,
                                    KLayoutDataPackageImpl.eINSTANCE.getKEdgeLayout(),
                                    optionData, optionValue);
                        }
                    }
                    if (optionData.getTargets().isEmpty()
                            || optionData.getTargets().contains(Target.PORTS)) {
                        // annotate all ports of the graph
                        for (KPort port : Graphs.getAllElementsOfType(graph, KPort.class)) {
                            annotateGraphElement(port,
                                    KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(),
                                    optionData, optionValue);
                        }
                    }
                    if (optionData.getTargets().isEmpty()
                            || optionData.getTargets().contains(Target.LABELS)) {
                        // annotate all labels of the graph
                        for (KLabel label : Graphs.getAllElementsOfType(graph, KLabel.class)) {
                            annotateGraphElement(label,
                                    KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(),
                                    optionData, optionValue);
                        }
                    }
                }
            }
        }

    }

    /**
     * This method annotates graph elements with layout options. If an option
     * is already set it will be preserved and not overwritten so a global option
     * does not clear user specific settings.
     *
     * @param element
     *            the element of which the layout shall be annotated
     * @param type
     *            the type of layout data of the element
     * @param layoutOption
     *            the option to annotate
     * @param layoutOptionValue
     *            the value of the option
     */
    @SuppressWarnings("unchecked")
    private void annotateGraphElement(final KGraphElement element,
        final EClass type, final IProperty<?> layoutOption,
        final Object layoutOptionValue) {
        // Illegal type declarations are silently ignored
        if (!type.equals(KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout())
            && !type.equals(KLayoutDataPackageImpl.eINSTANCE.getKEdgeLayout())) {
            return;
        }
        KGraphData layout = element.getData(type);
        if (layout == null) {
            layout = (KGraphData) KLayoutDataFactoryImpl.eINSTANCE.create(type);
            element.getData().add(layout);
        }
        // Do not overwrite element specific option if already set
        EMap<IProperty<?>, Object> properties = layout.getProperties();
        if (!properties.containsKey(layoutOption)) {
            layout.setProperty((LayoutOptionData<Object>) layoutOption, layoutOptionValue);
        }
    }

}
