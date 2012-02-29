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

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.options.GraphFeatures;
import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.smart.SmartLayoutService.SmartRuleData;

/**
 * A meta layout provides a layout option mapping for graph elements.
 *
 * @author msp
 */
public class MetaLayout {
    
    /** the parent node of the graph. */
    private KNode graph;
    /** the configuration mapping. */
    private Map<IProperty<?>, Object> config;
    /** the analysis cache. */
    private Map<String, Object> analysisCache;
    /** set of recognized graph features. */
    private final EnumSet<GraphFeatures> graphFeatures = EnumSet.noneOf(GraphFeatures.class);
    /** the smart layout rule results. */
    private Map<SmartRuleData, Double> resultMap;
    /** the time when the meta layout instance was created. */
    private long timestamp = System.currentTimeMillis();
    
    /**
     * Return the graph for this meta layout.
     * 
     * @return the graph
     */
    public KNode getGraph() {
        return graph;
    }
    
    /**
     * Set the graph for this meta layout.
     * 
     * @param thegraph the graph
     */
    public void setGraph(final KNode thegraph) {
        this.graph = thegraph;
    }
    
    /**
     * Returns the layout configuration.
     * 
     * @return the layout configuration
     */
    public Map<IProperty<?>, Object> getConfig() {
        if (config == null) {
            config = Maps.newHashMap();
        }
        return config;
    }
    
    /**
     * Returns the map of smart layout rule results.
     * 
     * @return the smart layout results
     */
    public Map<SmartRuleData, Double> getResults() {
        if (resultMap == null) {
            resultMap = Maps.newHashMap();
        }
        return resultMap;
    }
    
    /**
     * Perform the graph analysis with given identifier.
     * 
     * @param <T> result type inferred from the context
     * @param analysisId the analysis identifier
     * @return the result of the graph analysis
     */
    @SuppressWarnings("unchecked")
    public <T> T analyze(final String analysisId) {
        if (analysisCache == null) {
            analysisCache = Maps.newHashMap();
        }
        Object result = AnalysisService.getInstance().analyze(graph, analysisId,
                new BasicProgressMonitor(0), analysisCache);
        if (result instanceof AnalysisFailed) {
            AnalysisFailed fail = (AnalysisFailed) result;
            if (fail.getException() == null) {
                throw new RuntimeException("Failed to perform graph analysis.");
            } else {
                throw new WrappedException(fail.getException());
            }
        }
        return (T) result;
    }
    
    /**
     * Perform the graph analysis with given identifier and fetch the result of given index.
     * 
     * @param <T> result type inferred from the context
     * @param analysisId the analysis identifier
     * @param index array index of the relevant result
     * @return the result of the graph analysis
     */
    @SuppressWarnings("unchecked")
    public <T> T analyze(final String analysisId, final int index) {
        Object[] resultArray = analyze(analysisId);
        return (T) resultArray[index];
    }
    
    /**
     * Returns the timestamp.
     * 
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * Returns the set of recognized graph features.
     * 
     * @return the graph features
     */
    public EnumSet<GraphFeatures> getGraphFeatures() {
        return graphFeatures;
    }

}
