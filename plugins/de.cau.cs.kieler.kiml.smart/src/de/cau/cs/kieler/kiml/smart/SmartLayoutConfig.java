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

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.GraphFeature;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.analyses.CompoundEdgeAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeLabelCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.MultiEdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.PortCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.SelfLoopAnalysis;
import de.cau.cs.kieler.kiml.smart.SmartLayoutService.SmartRuleData;

/**
 * Smart layout!
 *
 * @author msp
 */
public class SmartLayoutConfig implements ILayoutConfig {

    /** the priority for the smart layout configuration. */
    public static final int PRIORITY = 14;
    /** the suitability threshold at which rules are applied. */
    public static final double SUITABILITY_THRESHOLD = 0.6;
    /** the priority factor for calculation of bias values. */
    public static final double PRIORITY_BIAS = 0.01;

    /** property for activation of the smart layout config. */
    public static final Property<Boolean> ACTIVATION = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.smart", false);
    /** property for the configuration map. */
    public static final IProperty<MetaLayout> META_LAYOUT
            = new Property<MetaLayout>("smartLayout.metaLayout");
    
    /** the time interval for cache age check. */
    private static final long CACHE_CHECK_INTERVAL = 6000;
    /** the maximal time for configurations to stay in the cache. */
    private static final long CACHE_MAX_AGE = 10000;
    
    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    /** the meta layout cache for performance optimization. */
    private final Map<Object, MetaLayout> metaLayoutCache = Maps.newHashMap();
    /** the last time when the cache was checked for old information. */
    private long lastCheckTime = System.currentTimeMillis();
    
    /**
     * Returns the currently cached meta layout data.
     * 
     * @return the cached meta layout data
     */
    public Map<Object, MetaLayout> getMetaLayoutCache() {
        return metaLayoutCache;
    }
    
    /**
     * Provide a meta layout for the given context.
     * 
     * @param context a layout context
     * @return a meta layout
     */
    public MetaLayout provideMetaLayout(final LayoutContext context) {
        // look for meta layout property in the context
        MetaLayout metaLayout = context.getProperty(META_LAYOUT);

        if (metaLayout == null) {
            // no meta layout present yet - generate one
            KGraphElement graphElement = context.getProperty(LayoutContext.GRAPH_ELEM);
            if (graphElement instanceof KNode) {
                KNode node = (KNode) graphElement;
                if (!node.getChildren().isEmpty()) {
                    long currentTime = System.currentTimeMillis();
                    
                    // check the meta layout cache for old information
                    if (currentTime - lastCheckTime >= CACHE_CHECK_INTERVAL) {
                        @SuppressWarnings("unchecked")
                        Map.Entry<Object, MetaLayout>[] entries = metaLayoutCache.entrySet().toArray(
                                new Map.Entry[metaLayoutCache.size()]);
                        for (Map.Entry<Object, MetaLayout> entry : entries) {
                            if (currentTime - entry.getValue().getTimestamp() > CACHE_MAX_AGE) {
                                metaLayoutCache.remove(entry.getKey());
                            }
                        }
                        lastCheckTime = currentTime;
                    }
                    
                    // look into the meta layout cache
                    metaLayout = metaLayoutCache.get(context.getProperty(LayoutContext.DIAGRAM_PART));
                    if (metaLayout != null) {
                        context.setProperty(META_LAYOUT, metaLayout);
                    } else {
                        
                        // perform smart layout on the parent node
                        metaLayout = smartLayout(node);
                        
                        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
                        if (diagramPart != null) {
                            metaLayoutCache.put(diagramPart, metaLayout);
                        }
                        context.setProperty(META_LAYOUT, metaLayout);
                    }
                }
            }
        }
        return metaLayout;
    }
    
    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        MetaLayout metaLayout = provideMetaLayout(context);
        if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
            // provide content algorithm hint option
            if (context.getProperty(DefaultLayoutConfig.CONTENT_HINT) == null && metaLayout != null) {
                String algorithm = (String) metaLayout.getConfig().get(LayoutOptions.ALGORITHM);
                if (algorithm != null) {
                    context.setProperty(DefaultLayoutConfig.CONTENT_HINT, algorithm);
                }
            }
            
            // provide container algorithm hint option
            Object containerDiagramPart = context.getProperty(LayoutContext.CONTAINER_DIAGRAM_PART);
            if (context.getProperty(DefaultLayoutConfig.CONTAINER_HINT) == null
                    && containerDiagramPart != null) {
                MetaLayout containerMetaLayout = metaLayoutCache.get(containerDiagramPart);
                if (containerMetaLayout != null) {
                    String containerAlgorithm = (String) containerMetaLayout.getConfig()
                            .get(LayoutOptions.ALGORITHM);
                    if (containerAlgorithm != null) {
                        context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, containerAlgorithm);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        MetaLayout metaLayout = provideMetaLayout(context);
        if (metaLayout != null) {
            return metaLayout.getConfig().get(optionData);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        MetaLayout metaLayout = provideMetaLayout(context);
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
        Collection<SmartRuleData> smartRules = SmartLayoutService.getInstance().getSmartRules();
        MetaLayout metaLayout = new MetaLayout();
        metaLayout.setGraph(node);

        // determine the specific features of the graph
        EnumSet<GraphFeature> graphFeatures = calcGraphFeatures(metaLayout);
        boolean fullHierarchy = graphFeatures.contains(GraphFeature.COMPOUND)
                || graphFeatures.contains(GraphFeature.CLUSTERS);
        node.getData(KShapeLayout.class).setProperty(AnalysisOptions.ANALYZE_HIERARCHY, fullHierarchy);

        Map<SmartRuleData, Double> results = metaLayout.getResults();
        double maxValue = 0;
        ISmartRule bestRule = null;
        for (SmartRuleData ruleData : smartRules) {
            try {
                double val = ruleData.getRule().suitability(metaLayout);
                results.put(ruleData, val);
                // consider the rule priorities by adding a corresponding bias value
                val += PRIORITY_BIAS * ruleData.getPriority();
                if (val > maxValue) {
                    maxValue = val;
                    bestRule = ruleData.getRule();
                }
            } catch (Exception exception) {
                // the smart layout rule failed, so put a negative value
                results.put(ruleData, -1.0);
            }
        }
        
        if (maxValue >= SUITABILITY_THRESHOLD) {
            // apply the meta layout of the most suitable rule
            bestRule.applyMetaLayout(metaLayout);
            // activate hierarchy layout for compound graphs and cluster graphs
            metaLayout.getConfig().put(LayoutOptions.LAYOUT_HIERARCHY, fullHierarchy);
        }
        
        return metaLayout;
    }
    
    /**
     * Calculate the specific features of a graph.
     * 
     * @param metaLayout the meta layout instance containing the graph
     */
    private EnumSet<GraphFeature> calcGraphFeatures(final MetaLayout metaLayout) {
        EnumSet<GraphFeature> graphFeatures = metaLayout.getGraphFeatures();
        
        // determine self-loops
        int selfLoops = metaLayout.analyze(SelfLoopAnalysis.ID);
        if (selfLoops > 0) {
            graphFeatures.add(GraphFeature.SELF_LOOPS);
        }
        
        // determine multi-edges
        int multiEdges = metaLayout.analyze(MultiEdgeCountAnalysis.ID);
        if (multiEdges > 0) {
            graphFeatures.add(GraphFeature.MULTI_EDGES);
        }
        
        // determine edge labels
        int edgeLabels = metaLayout.analyze(EdgeLabelCountAnalysis.ID);
        if (edgeLabels > 0) {
            graphFeatures.add(GraphFeature.EDGE_LABELS);
        }
        
        // determine ports
        int ports = metaLayout.analyze(PortCountAnalysis.ID);
        if (ports > 0) {
            graphFeatures.add(GraphFeature.PORTS);
        }
        
        // determine compound edges
        Object[] compoundEdgeResult = metaLayout.analyze(CompoundEdgeAnalysis.ID);
        if ((Integer) compoundEdgeResult[0] > 0) {
            if ((Integer) compoundEdgeResult[2] == 0) {
                graphFeatures.add(GraphFeature.CLUSTERS);
            } else {
                graphFeatures.add(GraphFeature.COMPOUND);
            }
        }
        
        return graphFeatures;
    }
    
    private static final int SUPP_FEATURE_PRIO_SCALE = 7;
    
    /**
     * Determine a penalty value for the features that are not supported by a layout algorithm
     * of given type. The maximal penalty equals the total number of defined graph features.
     * 
     * @param metaLayout meta layout instance
     * @param typeId the layout type identifier
     * @return a penalty for the missing features
     */
    public static double missingFeaturesFromType(final MetaLayout metaLayout, final String typeId) {
        LayoutTypeData typeData = LayoutDataService.getInstance().getTypeData(typeId);
        EnumSet<GraphFeature> graphFeatures = metaLayout.getGraphFeatures();
        double penalty = GraphFeature.values().length;
        for (LayoutAlgorithmData algorithmData : typeData.getLayouters()) {
            double p = 0;
            for (GraphFeature feature : graphFeatures) {
                int prio = algorithmData.getSupportedPriority(feature);
                if (prio <= -SUPP_FEATURE_PRIO_SCALE) {
                    p++;
                } else if (prio < 0) {
                    p += (double) -prio / SUPP_FEATURE_PRIO_SCALE;
                }
            }
            if (p < penalty) {
                penalty = p;
            }
        }
        return penalty;
    }
    
    /**
     * Determine the layout algorithm of given type that supports the highest number of graph
     * features of the processed graph. If multiple algorithms have the same number of supported
     * features, the one with highest registered priority for general diagrams is selected.
     * 
     * @param metaLayout meta layout instance
     * @param typeId the layout type identifier
     * @return the most feasible layout algorithm data
     */
    public static LayoutAlgorithmData mostFeasibleAlgorithm(final MetaLayout metaLayout,
            final String typeId) {
        LayoutTypeData typeData = LayoutDataService.getInstance().getTypeData(typeId);
        EnumSet<GraphFeature> graphFeatures = metaLayout.getGraphFeatures();
        int featureSupport = 0;
        LayoutAlgorithmData bestAlgo = null;
        for (LayoutAlgorithmData algorithmData : typeData.getLayouters()) {
            int s = 0;
            for (GraphFeature feature : graphFeatures) {
                s += Math.max(algorithmData.getSupportedPriority(feature), -SUPP_FEATURE_PRIO_SCALE);
            }
            if (s > featureSupport) {
                featureSupport = s;
                bestAlgo = algorithmData;
            } else if (s == featureSupport) {
                int bestPrio = bestAlgo == null ? 0 : bestAlgo.getSupportedPriority(
                        LayoutDataService.DIAGRAM_TYPE_GENERAL);
                int currentPrio = algorithmData.getSupportedPriority(
                        LayoutDataService.DIAGRAM_TYPE_GENERAL);
                if (bestAlgo == null || currentPrio > bestPrio) {
                    bestAlgo = algorithmData;
                }
            }
        }
        return bestAlgo;
    }

}
