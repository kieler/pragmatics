/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.smart;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.smart.rules.LayeredRule;

/**
 * Smart layout!
 *
 * @author msp
 */
public class SmartLayoutConfig implements ILayoutConfig {

    /** the priority for the smart layout configuration. */
    public static final int PRIORITY = 100;
    
    /** the time interval for cache age check. */
    private static final long CACHE_CHECK_INTERVAL = 20000;
    /** the maximal time for configurations to stay in the cache. */
    private static final long CACHE_MAX_AGE = 90000;

    /** property for the configuration map. */
    public static final IProperty<MetaLayout> META_LAYOUT
            = new Property<MetaLayout>("smartLayout.metaLayout");
    
    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    /** the meta layout cache for performance optimization. */
    private Map<Object, MetaLayout> metaLayoutCache = Maps.newHashMap();
    /** the last time when the cache was checked for old information. */
    private long lastCheckTime = System.currentTimeMillis();
    /** list of smart layout rules. */
    private List<ISmartRule> smartRules = Lists.newLinkedList();
    
    /**
     * Create a smart layout configuration and initialize rules in according priority.
     */
    public SmartLayoutConfig() {
        smartRules.add(new LayeredRule());
    }
    
    /**
     * Provide a meta layout for the given context.
     * 
     * @param context a layout context
     * @return a meta layout
     */
    public MetaLayout provideMetaLayout(final LayoutContext context) {
        long currentTime = System.currentTimeMillis();
        // look for meta layout property in the context
        MetaLayout metaLayout = context.getProperty(META_LAYOUT);
        if (metaLayout == null) {
            // look in the meta layout cache
            metaLayout = metaLayoutCache.get(context.getProperty(LayoutContext.DIAGRAM_PART));
            if (metaLayout != null) {
                context.setProperty(META_LAYOUT, metaLayout);
            }
        }
        
        // check the meta layout cache for old information
        if (currentTime - lastCheckTime >= CACHE_CHECK_INTERVAL) {
            @SuppressWarnings("unchecked")
            Map.Entry<Object, MetaLayout>[] entries = metaLayoutCache.entrySet().toArray(
                    new Map.Entry[metaLayoutCache.size()]);
            for (Map.Entry<Object, MetaLayout> entry : entries) {
                if (entry.getValue().getTimestamp() - currentTime > CACHE_MAX_AGE) {
                    metaLayoutCache.remove(entry.getKey());
                }
            }
            lastCheckTime = currentTime;
        }

        if (metaLayout == null) {
            // no meta layout present yet - generate one
            KGraphElement graphElement = context.getProperty(LayoutContext.GRAPH_ELEM);
            if (graphElement instanceof KNode) {
                KNode node = (KNode) graphElement;
                if (!node.getChildren().isEmpty()) {
                    
                    // perform smart layout on the parent node
                    metaLayout = smartLayout(node);
                    
                    Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
                    if (diagramPart != null) {
                        metaLayoutCache.put(diagramPart, metaLayout);
                    }
                    context.setProperty(META_LAYOUT, metaLayout);
                }
            }
        } else {
            metaLayout.updateTimestamp();
        }
        return metaLayout;
    }
    
    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        if (context.getProperty(META_LAYOUT) == null) {
            provideMetaLayout(context);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        MetaLayout metaLayout = context.getProperty(META_LAYOUT);
        if (metaLayout == null) {
            metaLayout = provideMetaLayout(context);
        }
        if (metaLayout != null) {
            return metaLayout.getConfig().get(optionData);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        MetaLayout metaLayout = context.getProperty(META_LAYOUT);
        if (metaLayout == null) {
            metaLayout = provideMetaLayout(context);
        }
        if (metaLayout != null) {
            for (Map.Entry<IProperty<?>, Object> entry : metaLayout.getConfig().entrySet()) {
                graphData.setProperty(entry.getKey(), entry.getValue());
            }
        }
    }
    
    /**
     * Perform smart layout on the given parent node.
     * 
     * @param node a parent node
     * @return a meta layout instance
     */
    private MetaLayout smartLayout(final KNode node) {
        MetaLayout metaLayout = new MetaLayout();
        for (ISmartRule rule : smartRules) {
            if (rule.isSuitable(node)) {
                rule.applyMetaLayout(metaLayout);
                break;
            }
        }
        return metaLayout;
    }

}
