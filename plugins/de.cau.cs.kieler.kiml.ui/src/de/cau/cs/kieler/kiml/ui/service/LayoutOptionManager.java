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
package de.cau.cs.kieler.kiml.ui.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;

/**
 * The main class for configuration of KGraph instances.
 * 
 * @author msp
 */
public class LayoutOptionManager {

    /** internal cache of semantic layout configurations. */
    private Map<EClass, List<ILayoutConfig>> semanticConfigMap = Maps.newHashMap();
    
    /** the default layout configuration. */
    private DefaultLayoutConfig defaultLayoutConfig = new DefaultLayoutConfig();

    /**
     * Configure the layout graph in the given layout mapping.
     * 
     * @param layoutMapping a layout mapping
     * @param progressMonitor a progress monitor
     */
    public void configure(final LayoutMapping<?> layoutMapping,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Layout configuration", 1);
        
        // create basic layout configuration
        CompoundLayoutConfig clc = new CompoundLayoutConfig();
        clc.add(defaultLayoutConfig);
        clc.addAll(LayoutInfoService.getInstance().getActiveConfigs());
        clc.addAll(layoutMapping.getLayoutConfigs());

        // configure the layout graph recursively
        KNode layoutGraph = layoutMapping.getLayoutGraph();
        recursiveConf(layoutGraph, layoutMapping, clc);
        
        progressMonitor.done();
    }

    /**
     * Create a layout configuration that can be used to access all actual values of layout options.
     * 
     * @param domainElement
     *            a domain model element
     * @param extraConfigs
     *            optional additional layout configurations to include
     * @return a complete layout configuration
     */
    public IMutableLayoutConfig createConfig(final EObject domainElement,
            final ILayoutConfig... extraConfigs) {
        CompoundLayoutConfig clc = new CompoundLayoutConfig();
        clc.add(defaultLayoutConfig);
        clc.addAll(LayoutInfoService.getInstance().getActiveConfigs());
        clc.addAll(getSemanticConfigs(domainElement));
        for (ILayoutConfig conf : extraConfigs) {
            clc.add(conf);
        }
        return clc;
    }

    /**
     * Configure all elements contained in the given node.
     * 
     * @param node
     *            a node from the layout graph
     * @param layoutMapping
     *            a layout mapping
     * @param config
     *            the basic layout configuration
     */
    private void recursiveConf(final KNode node, final LayoutMapping<?> layoutMapping,
            final CompoundLayoutConfig config) {
        // configure the node and its label
        configure(node, layoutMapping, config);
        for (KLabel label : node.getLabels()) {
            configure(label, layoutMapping, config);
        }

        // configure ports
        for (KPort port : node.getPorts()) {
            configure(port, layoutMapping, config);
            for (KLabel label : port.getLabels()) {
                configure(label, layoutMapping, config);
            }
        }

        // configure outgoing edges
        for (KEdge edge : node.getOutgoingEdges()) {
            configure(edge, layoutMapping, config);
            for (KLabel label : edge.getLabels()) {
                configure(label, layoutMapping, config);
            }
        }

        // configure child nodes
        for (KNode child : node.getChildren()) {
            recursiveConf(child, layoutMapping, config);
        }
    }

    /**
     * Configure a graph element.
     * 
     * @param graphElement
     *            a graph element
     * @param layoutMapping
     *            a layout mapping
     * @param config
     *            the basic layout configuration
     */
    private void configure(final KGraphElement graphElement, final LayoutMapping<?> layoutMapping,
            final CompoundLayoutConfig config) {
        // create a layout context for the current graph element
        LayoutContext context = new LayoutContext();
        context.setProperty(LayoutContext.GRAPH_ELEM, graphElement);
        Object diagramPart = layoutMapping.getGraphMap().get(graphElement);
        context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
        EObject modelElement = (EObject) layoutMapping.getAdapterFactory().getAdapter(
                diagramPart, EObject.class);
        context.setProperty(LayoutContext.DOMAIN_MODEL, modelElement);
        IWorkbenchPart workbenchPart = layoutMapping.getProperty(IWorkbenchPart.class);
        context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
        
        // add semantic configurations from the extension point
        List<ILayoutConfig> semanticConfigs = getSemanticConfigs(modelElement);
        config.addAll(semanticConfigs);

        // enrich the layout context using the basic configuration
        config.enrich(context);

        // clear the previous configuration
        KGraphData graphData = graphElement.getData(
                graphElement instanceof KEdge ? KEdgeLayout.class : KShapeLayout.class);
        graphData.getProperties().clear();
        
        // transfer the options from the layout configuration
        config.transferValues(graphData, context);

        // remove the semantic layout configurations again
        config.removeAll(semanticConfigs);
    }

    /**
     * Get a list of semantic layout configurations for the given model element, using a cached
     * value if available.
     * 
     * @param modelElement
     *            a domain model element
     * @return the list of semantic layout configurations
     */
    private List<ILayoutConfig> getSemanticConfigs(final EObject modelElement) {
        if (modelElement == null) {
            return Collections.emptyList();
        }
        EClass modelClass = modelElement.eClass();
        List<ILayoutConfig> configs = semanticConfigMap.get(modelClass);
        if (configs == null) {
            configs = EclipseLayoutInfoService.getInstance().getSemanticConfigs(modelClass);
            semanticConfigMap.put(modelClass, configs);
        }
        return configs;
    }

}
