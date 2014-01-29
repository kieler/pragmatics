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
package de.cau.cs.kieler.kiml.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutConfigService;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * The main class for configuration of KGraph instances. Configuration means the annotation of
 * graph elements with layout options for selecting layout algorithms and setting parameters
 * for their execution. This is done through <em>layout configurators</em>, which are executed
 * as part of a {@link CompoundLayoutConfig}.
 * The {@link #configure(LayoutMapping, IKielerProgressMonitor) configure} method should be
 * called before a graph layout engine is executed for a layout graph.
 * 
 * @author msp
 * @see de.cau.cs.kieler.kiml.options.LayoutOptions
 * @see de.cau.cs.kieler.kiml.config.ILayoutConfig
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2012-07-05 review KI-18 by cmot, sgu
 */
public class LayoutOptionManager {

    /** internal cache of semantic layout configurations. */
    private final Map<Class<?>, List<ILayoutConfig>> semanticConfigMap = Maps.newHashMap();
    
    /** the default layout configuration. */
    private final DefaultLayoutConfig defaultLayoutConfig = new DefaultLayoutConfig();

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
        clc.addAll(ExtensionLayoutConfigService.getInstance().getActiveConfigs());
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
     *            a domain model element, or {@code null}
     * @param extraConfigs
     *            optional additional layout configurations to include
     * @return a complete layout configuration
     */
    public IMutableLayoutConfig createConfig(final Object domainElement,
            final ILayoutConfig... extraConfigs) {
        CompoundLayoutConfig clc = new CompoundLayoutConfig();
        clc.add(defaultLayoutConfig);
        clc.addAll(ExtensionLayoutConfigService.getInstance().getActiveConfigs());
        clc.addAll(getSemanticConfigs(domainElement));
        for (ILayoutConfig conf : extraConfigs) {
            clc.add(conf);
        }
        return clc;
    }
    
    /**
     * Retrieve a global option value from the given configurator.
     * 
     * @param option a property that defines a layout option
     * @param config the layout configurator for getting the option value
     * @return the global option value stored in the configurator, or the default value
     * @param <T> the type of the given option
     */
    @SuppressWarnings("unchecked")
    public <T> T getGlobalValue(final IProperty<T> option, final ILayoutConfig config) {
        if (config != null) {
            LayoutOptionData optionData = LayoutDataService.getInstance().getOptionData(
                    option.getId());
            if (optionData != null) {
                Object value = config.getValue(optionData, LayoutContext.global());
                if (value != null) {
                    return (T) value;
                }
            }
        }
        return option.getDefault();
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
        KLayoutData layoutData = graphElement.getData(KLayoutData.class);
        layoutData.getProperties().clear();
        
        // transfer the options from the layout configuration
        transferValues(layoutData, config, context);

        // remove the semantic layout configurations again
        config.removeAll(semanticConfigs);
    }
    
    /**
     * Transfer all layout options affected by the given configurator to the layout data instance.
     * 
     * @param layoutData a layout data instance of a graph element
     * @param config a layout configurator
     * @param context the context under which to fetch the options
     */
    @SuppressWarnings("unchecked")
    public void transferValues(final KLayoutData layoutData, final ILayoutConfig config,
            final LayoutContext context) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        Collection<IProperty<?>> options = config.getAffectedOptions(context);
        for (IProperty<?> option : options) {
            Object value = null;
            if (option instanceof LayoutOptionData) {
                value = config.getValue((LayoutOptionData) option, context);
            } else {
                LayoutOptionData optionData = dataService.getOptionData(option.getId());
                if (optionData != null) {
                    value = config.getValue(optionData, context);
                }
            }
            if (value != null) {
                layoutData.setProperty((IProperty<Object>) option, value);
            }
        }
    }

    /**
     * Get a list of semantic layout configurations for the given model element, using a cached
     * value if available.
     * 
     * @param modelElement
     *            a domain model element
     * @return the list of semantic layout configurations, or an empty list if the model element
     *          is {@code null}
     */
    private List<ILayoutConfig> getSemanticConfigs(final Object modelElement) {
        if (modelElement == null) {
            return Collections.emptyList();
        }
        Class<?> clazz = modelElement.getClass();
        List<ILayoutConfig> configs = semanticConfigMap.get(clazz);
        if (configs == null) {
            if (modelElement instanceof EObject) {
                configs = LayoutConfigService.getInstance().getSemanticConfigs(
                        ((EObject) modelElement).eClass());
            } else {
                configs = LayoutConfigService.getInstance().getSemanticConfigs(clazz);
            }
            semanticConfigMap.put(clazz, configs);
        }
        return configs;
    }

}
