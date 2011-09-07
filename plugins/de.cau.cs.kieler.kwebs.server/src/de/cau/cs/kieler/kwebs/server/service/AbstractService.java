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

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.formats.Formats;
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
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public abstract class AbstractService {

    /** The layout engine used. */
    private static RecursiveGraphLayoutEngine layoutEngine
        = new RecursiveGraphLayoutEngine(null);

    /** Cached instance of server side layout data service. */
    private static ServerLayoutDataService dataService;
    
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
        // Get the graph instances of which the layout is to be calculated
        T graph = transformer.deserialize(serializedGraph);
        // Derive the layout structures of the graph instances
        TransformationData<T> transData = new TransformationData<T>(graph);
        transformer.deriveLayout(transData);
        // Parse the transmitted layout options and annotate the layout structure
        if (options != null) {
            for (KNode layout : transData.getLayoutGraphs()) {
                annotateGraph(layout, options);
            }
        }        
        // Actually do the layout on the structure
        for (KNode layout : transData.getLayoutGraphs()) {
            layoutEngine.layout(layout, new BasicProgressMonitor());
        }
        // Apply the calculated layout back to the graph instance
        transformer.applyLayout(transData);
        // Create and return the resulting graph in serialized form
        String serializedResult = transformer.serialize(graph);
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
                node.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
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
            node.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
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
            edge.getData(KEdgeLayout.class).setProperty(layoutOption, layoutOptionValue);
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
            port.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
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
            label.getData(KShapeLayout.class).setProperty(layoutOption, layoutOptionValue);
        }
    }

}
