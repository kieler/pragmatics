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

import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataFactoryImpl;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataPackageImpl;
import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.kstatistics.KStatistics;
import de.cau.cs.kieler.kwebs.kstatistics.KStatisticsFactory;
import de.cau.cs.kieler.kwebs.kstatistics.LayoutType;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationData;
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
        = new RecursiveGraphLayoutEngine(null);

    /** Cached instance of server side layout data service. */
    private static ServerLayoutDataService dataService;

    /** Cached instance of the factory for layout statistics instances. */
    private KStatisticsFactory statisticsFactory
        = KStatisticsFactory.eINSTANCE;
    
    /**
     * Protected constructor. Initialized the layout data services.
     */
    protected AbstractService() {
        ServerLayoutDataService.create();
        dataService = ServerLayoutDataService.getInstance();
    }
    
    /**
     * Base implementation of layout functionality.
     * 
     * @param serializedGraph
     *            the graph to do layout on in serial representation
     * @param format
     *            the format of the serial graph {@see Formats}
     * @param options
     *            the optional layout options
     * @return the graph on which the layout was done in the same format as used for the source graph 
     */
    protected final String layout(final String serializedGraph, final String format, 
        final List<GraphLayoutOption> options) {
        // Parameter testing
        if (serializedGraph == null) {
            throw new IllegalArgumentException("No graph given");
        }
        if (!Formats.isSupportedFormat(format)) {
            throw new IllegalArgumentException("Format not supported");
        }
        Logger.log(Severity.DEBUG, "Starting layout");
        IGraphTransformer<?> transformer = dataService.getTransformer(format);
        if (transformer == null) {
            throw new IllegalStateException("Transformer could not be acquired");
        }
        String serializedResult = layout(serializedGraph, transformer, options);
        Logger.log(Severity.DEBUG, "Finished layout");
        return serializedResult;
    }
    
    /**
     * Perform layout using a given graph transformer.
     * 
     * @param serializedGraph
     *            the graph to do layout on in serial representation
     * @param transformer
     *            a graph transformer
     * @param options
     *            the optional layout options
     * @return the graph on which the layout was done in the same format as used for the source graph 
     */
    private <T> String layout(final String serializedGraph, final IGraphTransformer<T> transformer, 
        final List<GraphLayoutOption> options) { 
        double operationStarted = System.nanoTime();
        KStatistics statistics = statisticsFactory.createKStatistics();
        statistics.setType(LayoutType.REMOTE_LAYOUT);
        // Get the graph instances of which the layout is to be calculated
        T graph = transformer.deserialize(serializedGraph);
        // Derive the layout structures of the graph instances
        TransformationData<T> transData = new TransformationData<T>();
        transData.setSourceGraph(graph);
        transformer.deriveLayout(transData);
        // Parse the transmitted layout options and annotate the layout structure
        if (options != null) {
            for (KNode layout : transData.getLayoutGraphs()) {
                annotateGraph(layout, options);
            }
        }    
        // Actually do the layout on the structure
        double layoutStarted = System.nanoTime();
        for (KNode layout : transData.getLayoutGraphs()) {
            layoutEngine.layout(layout, new BasicProgressMonitor());
        }
        double layoutFinished = System.nanoTime();
        // Apply the calculated layout back to the graph instance
        transformer.applyLayout(transData);
        // Calculate statistical values and annotate graph if it is a KGraph instance.
        // The serialization process can not be included.
        double operationFinished = System.nanoTime();
        if (graph instanceof KNode) {
            // Graph related statistics
            int nodes = 0;
            int ports = 0;
            int labels = 0;
            int edges = 0;
            for (KNode layout : transData.getLayoutGraphs()) {
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
            statistics.setBytes(serializedGraph.length());
            statistics.setNodes(nodes);
            statistics.setPorts(ports);
            statistics.setLabels(labels);
            statistics.setEdges(edges);
            // Execution time related statistics
            double layoutTime = layoutFinished - layoutStarted;
            statistics.setTimeLayout(layoutTime);
            statistics.setTimeRemoteSupplemental(operationFinished - operationStarted - layoutTime);
            ((KNode) graph).getData().add(statistics);
        }
        // Create and return the resulting graph in serialized form
        String serializedResult = transformer.serialize(transData.getSourceGraph());
        return serializedResult;
    }
    
    /**
     * Annotate the graph with the given layout options.
     * 
     * @param layout a layout graph
     * @param options a list of layout options
     */
    private void annotateGraph(final KNode layout, final List<GraphLayoutOption> options) {
        LayoutOptionData<?> layoutOption = null;        
        for (GraphLayoutOption option : options) {
            layoutOption = dataService.getOptionData(option.getId());
            // Is the option identified only by it's suffix?
            if (layoutOption == null) {
                layoutOption = dataService.getMatchingOptionData(option.getId());
            }
            // Fail silent on invalid option declarations
            if (layoutOption != null) {
                Object layoutOptionValue = layoutOption.parseValue(option.getValue());
                if (layoutOptionValue != null) {
                    if (layoutOption.hasTarget(Target.PARENTS)) {
                        Logger.log(
                            Severity.DEBUG, 
                            "Setting layout option (PARENTS, " 
                            + layoutOptionValue.toString() 
                            + ")"
                        );
                        annotateGraphParents(layout, layoutOption, layoutOptionValue);
                    }                        
                    if (layoutOption.hasTarget(Target.NODES)) {
                        Logger.log(
                            Severity.DEBUG, 
                            "Setting layout option (NODES, " 
                            + layoutOptionValue.toString() 
                            + ")"
                        );
                        annotateGraphNodes(layout, layoutOption, layoutOptionValue);
                    }
                    if (layoutOption.hasTarget(Target.EDGES)) {
                        Logger.log(
                            Severity.DEBUG, 
                            "Setting layout option (EDGES, " 
                            + layoutOptionValue.toString() 
                            + ")"
                        );
                        annotateGraphEdges(layout, layoutOption, layoutOptionValue);
                    }
                    if (layoutOption.hasTarget(Target.PORTS)) {
                        Logger.log(
                            Severity.DEBUG, 
                            "Setting layout option (PORTS, " 
                            + layoutOptionValue.toString() 
                            + ")"
                        );
                        annotateGraphPorts(layout, layoutOption, layoutOptionValue);
                    }
                    if (layoutOption.hasTarget(Target.LABELS)) {
                        Logger.log(
                            Severity.DEBUG, 
                            "Setting layout option (LABELS, " 
                            + layoutOptionValue.toString() 
                            + ")"
                        );
                        annotateGraphLabels(layout, layoutOption, layoutOptionValue);
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
