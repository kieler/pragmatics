/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Collector object that holds context information during a run of GrAna. When multiple analyses are
 * executed consecutively, later analyses may depend on information gathered or calculated by
 * earlier analyses. The {@link AnalysisContext} is passed to the
 * {@link IAnalysis#doAnalysis(de.cau.cs.kieler.core.kgraph.KNode, AnalysisContext, 
 * de.cau.cs.kieler.core.alg.IKielerProgressMonitor)}
 * method and can be used to access such information.
 * 
 * The results map may additionally contain information about can cancelled or failed 
 * analyses (see {@link AnalysisFailed}.
 * 
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
public class AnalysisContext {

    /**
     * A map containing analysis instances that have been executed.
     */
    private Map<Class<?>, Object> analysisInstances = Maps.newHashMap();

    /**
     * A map containing the results of previously executed analyses.
     * Make it a linked hash set to preserve insertion order.
     */
    private Map<String, Object> resultsMap = Maps.newLinkedHashMap();

    /**
     * @param instance
     *            the instance of an executed analysis to add.
     * @param <T>
     *            type of the added analysis
     * @return See javadoc for {@link Map#put(Object, Object)}
     */
    @SuppressWarnings("unchecked")
    public <T extends IAnalysis> T putFinishedAnalysis(final T instance) {
        return (T) analysisInstances.put(instance.getClass(), instance);
    }

    /**
     * @param clazz
     *            the class of the {@link IAnalysis}
     * @param <T>
     *            type of the requested analysis
     * @return either the the previously created instance of the requested analysis or {@code null}.
     */
    @SuppressWarnings("unchecked")
    public <T extends IAnalysis> T getAnalysisInstance(final Class<T> clazz) {
        return (T) analysisInstances.get(clazz);
    }

    /**
     * @param id
     *            the id of the analysis for which to add a result.
     * @param result
     *            the object containing the result, usually this is an array.
     * @return see {@link Map#put(Object, Object)}.
     */
    public Object putResult(final String id, final Object result) {
        return resultsMap.put(id, result);
    }

    /**
     * @param id
     *            the id of the requested analysis's result.
     * @return either the stored result or {@code null}.
     */
    public Object getResult(final String id) {
        return resultsMap.get(id);
    }

    /**
     * @return the resultsMap
     */
    public Map<String, Object> getResults() {
        return resultsMap;
    }
    
    /**
     * @return the analysisInstances
     */
    public Map<Class<?>, Object> getAnalysisInstances() {
        return analysisInstances;
    }
}
