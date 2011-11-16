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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
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
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataFactoryImpl;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataPackageImpl;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.TransformationService;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.Statistics;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Graphs;

/**
 * This abstract base class provides the implementation of the layout functionality. Web service 
 * architecture specific service implementations may sub class this class in order to use the provided
 * layout.
 *
 * @kieler.rating  2011-08-25 proposed yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author  swe
 */
public abstract class AbstractService {

    /** The layout engine used. */
    private static RecursiveGraphLayoutEngine layoutEngine
        = new RecursiveGraphLayoutEngine();

    /**
     * Protected constructor. Initialized the layout data services.
     */
    protected AbstractService() {
        ServerLayoutDataService.create();
    }
    
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
            throw new IllegalArgumentException("No graph given");
        }
        Logger.log(Severity.DEBUG, "Starting layout");
        GraphFormatData informatData = TransformationService.getInstance()
                .getFormatDataBySuffix(informat);
        if (informatData == null) {
            throw new IllegalArgumentException("Graph format \"" + informat + "\" is unknown.");
        }
        String serializedResult;
        if (outformat == null || outformat.equals(informat)) {
            serializedResult = layout(serializedGraph, informatData.getHandler(), null, options);
        } else {
            GraphFormatData outformatData = TransformationService.getInstance()
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
            final ITransformationHandler<I> inhandler, final ITransformationHandler<O> outhandler,
            final List<GraphLayoutOption> options) {
        // Start measuring the total time of the operation
        double operationStarted = System.nanoTime();
        
        // Get the graph instances of which the layout is to be calculated
        TransformationData<I, KNode> inTransData = new TransformationData<I, KNode>();
        annotateTransData(inTransData, options);
        inhandler.deserialize(serializedGraph, inTransData);
        if (inTransData.getSourceGraph() == null) {
            // The input was empty, so return an empty graph
            return "";
        }
        
        // Derive the layout structures of the graph instances
        inhandler.getImporter().transform(inTransData);
        Iterator<String> messageIter = inTransData.getMessages().iterator();
        while (messageIter.hasNext()) {
            Logger.log(messageIter.next());
            messageIter.remove();
        }
        
        // Parse the transmitted layout options and annotate the layout structure
        if (options != null) {
            for (KNode layout : inTransData.getTargetGraphs()) {
                annotateGraph(layout, options);
            }
        }
        
        // Actually do the layout on the structure
        double layoutTime = 0;
        for (KNode layout : inTransData.getTargetGraphs()) {
            IKielerProgressMonitor layoutMonitor = new BasicProgressMonitor();
            layoutEngine.layout(layout, layoutMonitor);
            layoutTime += layoutMonitor.getExecutionTime() * NANO_FACT;
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
            serializedResult = inhandler.serialize(inTransData.getSourceGraph());
            
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
                for (O outgraph : outTransData.getTargetGraphs()) {
                    outGraphBuilder.append(outhandler.serialize(outgraph));
                }
                
            }
            serializedResult = outGraphBuilder.toString();
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
            List<KGraphElement> elements = Graphs.getAllElementsOfType(layout, KGraphElement.class);
            for (KGraphElement element : elements) {
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
        KIdentifier identifier = sourceGraph.getData(KIdentifier.class);
        if (identifier == null) {
            identifier = KLayoutDataFactoryImpl.eINSTANCE.createKIdentifier();
            sourceGraph.getData().add(identifier);
        }    
        identifier.setProperty(Statistics.STATISTICS, statistics);
    }
    
    /**
     * Annotate transformation data with the given layout options.
     * 
     * @param transData a transformation data instance
     * @param options a list of layout options
     */
    private void annotateTransData(final TransformationData<?, ?> transData,
            final List<GraphLayoutOption> options) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        for (GraphLayoutOption option : options) {
            LayoutOptionData<?> optionData = dataService.getOptionDataBySuffix(option.getId());
            if (optionData != null) {
                Object optionValue = optionData.parseValue(option.getValue());
                if (optionValue != null) {
                    transData.setProperty(optionData, optionValue);
                }
            }
        }
    }
    
    /**
     * Annotate the graph with the given layout options.
     * 
     * @param layout a layout graph
     * @param options a list of layout options
     */
    private void annotateGraph(final KNode layout, final List<GraphLayoutOption> options) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        for (GraphLayoutOption option : options) {
            LayoutOptionData<?> optionData = dataService.getOptionDataBySuffix(option.getId());
            // Fail silent on invalid option declarations
            if (optionData != null) {
                Object optionValue = optionData.parseValue(option.getValue());
                if (optionValue != null) {
                    if (optionData.hasTarget(Target.PARENTS)) {
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
                        Logger.log(Severity.DEBUG, "Setting layout option (PARENTS, " 
                            + optionValue.toString() + ")");
                        annotateGraphParents(layout, optionData, optionValue);
                    }
                    if (optionData.hasTarget(Target.NODES)) {
                        Logger.log(Severity.DEBUG, "Setting layout option (NODES, " 
                            + optionValue.toString() + ")");
                        annotateGraphNodes(layout, optionData, optionValue);
                    }
                    if (optionData.hasTarget(Target.EDGES)) {
                        Logger.log(Severity.DEBUG, "Setting layout option (EDGES, " 
                            + optionValue.toString() + ")");
                        annotateGraphEdges(layout, optionData, optionValue);
                    }
                    if (optionData.hasTarget(Target.PORTS)) {
                        Logger.log(Severity.DEBUG, "Setting layout option (PORTS, " 
                            + optionValue.toString() + ")");
                        annotateGraphPorts(layout, optionData, optionValue);
                    }
                    if (optionData.hasTarget(Target.LABELS)) {
                        Logger.log(Severity.DEBUG, "Setting layout option (LABELS, " 
                            + optionValue.toString() + ")");
                        annotateGraphLabels(layout, optionData, optionValue);
                    }
                }
            }
        }

    }

    /**
     * Annotates the parent nodes of a given graph with the given layout option.
     * 
     * @param annotateNode
     *            the root node of the graph of which the parents are to be annotated
     * @param layoutOption
     *            the layout option to annotate the parents with
     * @param layoutOptionValue
     *            the value for the layout option
     */
    private void annotateGraphParents(final KNode annotateNode, 
        final IProperty<?> layoutOption, final Object layoutOptionValue) {
        if (annotateNode == null || layoutOption == null || layoutOptionValue == null) {
            return;
        }
        List<KNode> nodes = (List<KNode>) Graphs.getAllElementsOfType(annotateNode, KNode.class);
        for (KNode node : nodes) {
            if (node != null && node.getChildren().size() > 0) {
                annotateGraphElement(
                    node, 
                    KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(), 
                    layoutOption, 
                    layoutOptionValue
                );
                //node.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
            }
        }
    }

    /**
     * Annotates the nodes of a given graph with the given layout option.
     * 
     * @param annotateNode
     *            the root node of the graph of which the parents are to be annotated
     * @param layoutOption
     *            the layout option to annotate the parents with
     * @param layoutOptionValue
     *            the value for the layout option
     */
    private void annotateGraphNodes(final KNode annotateNode, 
        final IProperty<?> layoutOption, final Object layoutOptionValue) {
        if (annotateNode == null || layoutOption == null || layoutOptionValue == null) {
            return;
        }
        List<KNode> nodes = (List<KNode>) Graphs.getAllElementsOfType(annotateNode, KNode.class);
        for (KNode node : nodes) {
            annotateGraphElement(
                node, 
                KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(), 
                layoutOption, 
                layoutOptionValue
            );
            //node.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
        }
    }

    /**
     * Annotates the edges of a given graph with the given layout option.
     * 
     * @param annotateNode
     *            the root node of the graph of which the parents are to be annotated
     * @param layoutOption
     *            the layout option to annotate the parents with
     * @param layoutOptionValue
     *            the value for the layout option
     */
    private void annotateGraphEdges(final KNode annotateNode, 
        final IProperty<?> layoutOption, final Object layoutOptionValue) {
        if (annotateNode == null || layoutOption == null || layoutOptionValue == null) {
            return;
        }
        List<KEdge> edges = (List<KEdge>) Graphs.getAllElementsOfType(annotateNode, KEdge.class);
        for (KEdge edge : edges) {
            annotateGraphElement(
                edge, 
                KLayoutDataPackageImpl.eINSTANCE.getKEdgeLayout(), 
                layoutOption, 
                layoutOptionValue
            );
            //edge.getData(KEdgeLayout.class).setProperty(layoutOption, layoutOptionValue);
        }
    }

    /**
     * Annotates the ports of a given graph with the given layout option.
     * 
     * @param annotateNode
     *            the root node of the graph of which the parents are to be annotated
     * @param layoutOption
     *            the layout option to annotate the parents with
     * @param layoutOptionValue
     *            the value for the layout option
     */
    private void annotateGraphPorts(final KNode annotateNode, 
        final IProperty<?> layoutOption, final Object layoutOptionValue) {
        if (annotateNode == null || layoutOption == null || layoutOptionValue == null) {
            return;
        }
        List<KPort> ports = (List<KPort>) Graphs.getAllElementsOfType(annotateNode, KPort.class);
        for (KPort port : ports) {
            annotateGraphElement(
                port, 
                KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(), 
                layoutOption, 
                layoutOptionValue
            );
            //port.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
        }
    }

    /**
     * Annotates the labels of a given graph with the given layout option.
     * 
     * @param annotateNode
     *            the root node of the graph of which the parents are to be annotated
     * @param layoutOption
     *            the layout option to annotate the parents with
     * @param layoutOptionValue
     *            the value for the layout option
     */
    private void annotateGraphLabels(final KNode annotateNode, 
        final IProperty<?> layoutOption, final Object layoutOptionValue) {
        if (annotateNode == null || layoutOption == null || layoutOptionValue == null) {
            return;
        }
        List<KLabel> labels = (List<KLabel>) Graphs.getAllElementsOfType(annotateNode, KLabel.class);
        for (KLabel label : labels) {
            annotateGraphElement(
                label, 
                KLayoutDataPackageImpl.eINSTANCE.getKShapeLayout(), 
                layoutOption, 
                layoutOptionValue
            );
            //label.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
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
            layout.setProperty(layoutOption, layoutOptionValue);
        }
    }
    
}
