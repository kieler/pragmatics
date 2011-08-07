/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.service;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
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
    private RecursiveGraphLayoutEngine layoutEngine
        = new RecursiveGraphLayoutEngine(null);

    /** Mapping of format identifiers {@see Formats} to transformer instances. */
    private Hashtable<String, IGraphTransformer> transformers 
        = new Hashtable<String, IGraphTransformer>();

    /** */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.server.configuration";

    /** */
    private static final String ELEMENT_TRANSFORMER
        = "transformer";

    /** */
    private static final String ATTRIBUTE_SUPPORTEDFORMAT
        = "supportedFormat";

    /** */
    private static final String ATTRIBUTE_IMPLEMENTATION
        = "implementation";

    /**
     * 
     */
    protected AbstractService() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSIONPOINT_ID)) {
            if (element.getName().equals(ELEMENT_TRANSFORMER)) {
                String format = element.getAttribute(ATTRIBUTE_SUPPORTEDFORMAT);
                String implementation = element.getAttribute(ATTRIBUTE_IMPLEMENTATION);
                if (Formats.isSupportedFormat(format)) {
                    if (!transformers.containsKey(format)) {
                        try {
                            Bundle contributor 
                                = Platform.getBundle(element.getContributor().getName());
                            IGraphTransformer transformer 
                                = (IGraphTransformer)
                                      (contributor.loadClass(implementation).newInstance());
                            transformers.put(format, transformer);                                
                        } catch (Exception e) {
                                //FIXME handle
                        }
                    }
                }
            }
        }
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
        //FIXME monitor timeout
        IKielerProgressMonitor monitor = new BasicProgressMonitor();
        IGraphTransformer transformer = transformers.get(format);
        if (transformer == null) {
            throw new IllegalStateException("Transformer could not be acquired");
        }
        monitor.begin("", 1);
        // Get the graph instance of which the layout is to be calculated
        Object graph = transformer.deserialize(serializedGraph);
        // Derive the structure of the graph instance
        KNode layout = transformer.deriveLayout(graph);
        // Parse the transmitted layout options and annotate
        // the layout structure
        if (options != null) {
            LayoutDataService dataService = LayoutDataService.getInstance();
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
                            annotateGraphParentsWithOption(layout, layoutOption, layoutOptionValue);
                        }                        
                        if (layoutOption.hasTarget(Target.NODES)) {
                            Logger.log(
                                Severity.DEBUG, 
                                "Setting layout option (NODES, " 
                                + layoutOptionValue.toString() 
                                + ")"
                            );
                            annotateGraphNodesWithOption(layout, layoutOption, layoutOptionValue);
                        }
                        if (layoutOption.hasTarget(Target.EDGES)) {
                            Logger.log(
                                Severity.DEBUG, 
                                "Setting layout option (EDGES, " 
                                + layoutOptionValue.toString() 
                                + ")"
                            );
                            annotateGraphEdgesWithOption(layout, layoutOption, layoutOptionValue);
                        }
                        if (layoutOption.hasTarget(Target.PORTS)) {
                            Logger.log(
                                Severity.DEBUG, 
                                "Setting layout option (PORTS, " 
                                + layoutOptionValue.toString() 
                                + ")"
                            );
                            annotateGraphPortsWithOption(layout, layoutOption, layoutOptionValue);
                        }
                        if (layoutOption.hasTarget(Target.LABELS)) {
                            Logger.log(
                                Severity.DEBUG, 
                                "Setting layout option (LABELS, " 
                                + layoutOptionValue.toString() 
                                + ")"
                            );
                            annotateGraphLabelsWithOption(layout, layoutOption, layoutOptionValue);
                        }
                    }
                }
            }
        }        
        // Actually do the layout on the structure
        layoutEngine.layout(layout, monitor);
        // Apply the calculated layout back to the graph instance
        transformer.applyLayout(graph, layout);
        // Create and return the resulting graph in serialized form
        String serializedResult = transformer.serialize(graph);
        monitor.done();
        Logger.log(Severity.DEBUG, "Finished layout");
        return serializedResult;
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
    private void annotateGraphParentsWithOption(final KNode annotateNode, 
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
    private void annotateGraphNodesWithOption(final KNode annotateNode, 
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
    private void annotateGraphEdgesWithOption(final KNode annotateNode, 
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
    private void annotateGraphPortsWithOption(final KNode annotateNode, 
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
    private void annotateGraphLabelsWithOption(final KNode annotateNode, 
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
