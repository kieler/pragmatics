/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisCategory;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;

/**
 * This singleton class is responsible for reading the evolution extensions.
 *
 * @author bdu
 *
 */
public final class EvolutionServices {
    /** Identifier of the extension point for evolution data. */
    private static final String EXTP_ID_EVOLUTION_DATA = "de.cau.cs.kieler.kiml.evol.evolutionData";

    /** Identifier of the element "data". */
    private static final String ELEMENT_DATA = "data";

    /** Identifier of the analysis category "metric". */
    private static final String METRICS_CATEGORY = "de.cau.cs.kieler.kiml.evol.metricCategory";

    /**
     * The shared instance.
     */
    private static EvolutionServices instance;

    /**
     * Builds the extension data. This method must be called exactly once before
     * the extension data can be accessed.
     */
    public static synchronized void createExtensionData() {
        assert instance == null : "EvolutionServices data already loaded.";

        // create instance of the evolution data holder class
        instance = new EvolutionServices();

        // build data for the extension points
        instance.loadEvolutionDataExtensions();
        instance.loadLayoutMetricsExtensions();
    }

    /**
     * Returns the singleton instance of this class.
     *
     * {@link #createExtensionData()} must be called before this method.
     *
     * @return the singleton instance
     */
    public static EvolutionServices getInstance() {
        assert instance != null;
        return instance;
    }

    /**
     * The default constructor is hidden to prevent others from instantiating
     * this class.
     */
    private EvolutionServices() {
        // nothing
    }

    /** Set of registered evolution data. */
    private HashMap<String, IConfigurationElement> evolutionDataMap;

    /** Set of registered layout metrics. */
    private HashMap<String, IConfigurationElement> layoutMetricsMap;

    /** Set of the cached metrics. */
    private Set<AbstractInfoAnalysis> cachedMetrics;

    /**
     *
     * Returns the evolution data element with the given ID. The method
     * {@link #createExtensionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @param id
     *            the ID of the evolution data element
     * @return the evolution data with the given ID, or {@code null} if none is
     *         found.
     */
    public IConfigurationElement getEvolutionData(final String id) {
        assert this.evolutionDataMap != null;
        IConfigurationElement result = this.evolutionDataMap.get(id);
        return result;
    }

    /**
     * Returns a copy of the registered evolution data IDs.
     * {@code createEvolutionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @return a copy of the registered evolution data IDs.
     */
    public Set<String> getEvolutionDataIds() {
        assert this.evolutionDataMap != null;
        return Collections.unmodifiableSet(this.evolutionDataMap.keySet());
    }

    /**
     * Returns the layout metric element with the given ID. The method
     * {@link #createExtensionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @param id
     *            the ID of the layout metric element
     * @return the layout metric element with the given ID, or {@code null} if
     *         none is found.
     */
    public IConfigurationElement getLayoutMetric(final String id) {
        assert this.layoutMetricsMap != null;
        IConfigurationElement result = this.layoutMetricsMap.get(id);
        return result;
    }

    /**
     * Returns a copy of the registered layout metrics IDs.
     * {@link #createExtensionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @return a copy of the registered layout metrics IDs.
     */
    public Set<String> getLayoutMetricsIds() {
        assert this.layoutMetricsMap != null;
        return Collections.unmodifiableSet(this.layoutMetricsMap.keySet());
    }

    /**
     * Returns the unmodifiable set of layout metrics.
     * {@link #createExtensionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @return the set of layout metrics
     */
    public Set<AbstractInfoAnalysis> getLayoutMetrics() {
        assert this.cachedMetrics != null;
        return Collections.unmodifiableSet(this.cachedMetrics);
    }

    /**
     * Loads and registers all evolution data entries from the extension point.
     */
    private void loadEvolutionDataExtensions() {
        this.evolutionDataMap = new HashMap<String, IConfigurationElement>();
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        EXTP_ID_EVOLUTION_DATA);

        for (final IConfigurationElement element : extensions) {
            if (ELEMENT_DATA.equals(element.getName())) {
                String option = element.getAttribute("option");
                this.evolutionDataMap.put(option, element);
            }
        }
    }

    /**
     * Loads and registers all metrics from the extension point.
     */
    private void loadLayoutMetricsExtensions() {
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        AnalysisServices.EXTP_ID_ANALYSIS_PROVIDERS);

        // get the metrics via the respective analysis category
        AnalysisCategory metricsCategory =
                AnalysisServices.getInstance().getCategoryById(METRICS_CATEGORY);
        List<AbstractInfoAnalysis> metrics = metricsCategory.getAnalyses();
        assert metrics != null;
        List<String> metricIds = new ArrayList<String>(metrics.size());

        for (final AbstractInfoAnalysis metric : metrics) {
            metricIds.add(metric.getId());
        }

        this.layoutMetricsMap = new HashMap<String, IConfigurationElement>();

        // Store the configuration elements of the metrics.
        for (final IConfigurationElement element : extensions) {
            if (AnalysisServices.ELEMENT_ANALYSIS_PROVIDER.equals(element.getName())) {
                String id = element.getAttribute("id");
                if (metricIds.contains(id)) {
                    this.layoutMetricsMap.put(id, element);
                }
            }
        }

        // We have the metrics here, so we cache them additionally
        // to storing their configuration element.
        this.cachedMetrics = new HashSet<AbstractInfoAnalysis>(metrics);
    }
}
