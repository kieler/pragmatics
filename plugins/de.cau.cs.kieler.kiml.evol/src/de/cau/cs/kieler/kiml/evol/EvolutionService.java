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

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kiml.grana.AnalysisServices;

/**
 * This class is responsible for reading the evolution extensions.
 *
 * @author bdu
 *
 */
public final class EvolutionService {
    /** identifier of the extension point for evolution data. */
    private static final String EXTP_ID_EVOLUTION_DATA = "de.cau.cs.kieler.kiml.evol.evolutionData";

    private static final String ELEMENT_DATA = "data";

    /**
     * The shared instance.
     */
    private static EvolutionService instance;

    /**
     * Builds the extension data.
     */
    public static void createExtensionData() {
        // create instance of the evolution data holder class
        instance = new EvolutionService();
        // build data for the extension points
        instance.loadEvolutionDataExtensions();
        instance.loadLayoutMetricsExtensions();
    }

    /**
     *
     * {@code createExtensionData()} must be called before this method.
     *
     * @return the singleton instance
     */
    public static EvolutionService getInstance() {
        Assert.isNotNull(instance);
        return instance;
    }

    /**
     * The default constructor is hidden to prevent others from instantiating
     * this class.
     */
    private EvolutionService() {
        // nothing
    }

    /** set of registered evolution data. */
    private HashMap<String, IConfigurationElement> evolutionDataMap;
    /** set of registered layout metrics. */
    private HashMap<String, IConfigurationElement> layoutMetricsMap;

    /**
     *
     * Returns the evolution data element with the given id.
     * {@code createEvolutionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @param id
     *            the id of the evolution data element
     * @return the evolution data with the given id, or {@code null} if none is
     *         found.
     */
    public IConfigurationElement getEvolutionData(final String id) {
        Assert.isNotNull(this.evolutionDataMap);
        final IConfigurationElement result = this.evolutionDataMap.get(id);
        return result;
    }

    /**
     * Returns the layout metric element with the given id. The method
     * {@code createEvolutionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @param id
     *            the id of the layout metric element
     * @return the layout metric element with the given id, or {@code null} if
     *         none is found.
     */
    public IConfigurationElement getLayoutMetric(final String id) {
        Assert.isNotNull(this.layoutMetricsMap);
        final IConfigurationElement result = this.layoutMetricsMap.get(id);
        return result;
    }

    /**
     * Returns a copy of the registered evolution data ids.
     * {@code createEvolutionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @return a copy of the registered evolution data ids.
     */
    public Set<String> getEvolutionDataIds() {
        Assert.isNotNull(this.evolutionDataMap);
        return Collections.unmodifiableSet(this.evolutionDataMap.keySet());
    }

    /**
     * Returns a copy of the registered layout metrics ids.
     * {@code createEvolutionData()} must be called before this method,
     * otherwise an exception is thrown.
     *
     * @return a copy of the registered layout metrics ids.
     */
    public Set<String> getLayoutMetricsIds() {
        Assert.isNotNull(this.layoutMetricsMap);
        return Collections.unmodifiableSet(this.layoutMetricsMap.keySet());
    }

    /**
     * Loads and registers all evolution data entries from the extension point.
     */
    private void loadEvolutionDataExtensions() {
        this.evolutionDataMap = new HashMap<String, IConfigurationElement>();
        final IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
EXTP_ID_EVOLUTION_DATA);

        for (final IConfigurationElement element : extensions) {
            if (ELEMENT_DATA.equals(element.getName())) {
                final String option = element.getAttribute("option");
                this.evolutionDataMap.put(option, element);
            }
        }
    }

    /**
     * Loads and registers all metrics from the extension point.
     */
    private void loadLayoutMetricsExtensions() {
        this.layoutMetricsMap = new HashMap<String, IConfigurationElement>();
        final IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        AnalysisServices.EXTP_ID_ANALYSIS_PROVIDERS);
        for (final IConfigurationElement element : extensions) {
            System.out.println(element.getName());
            if (AnalysisServices.ELEMENT_ANALYSIS_PROVIDER.equals(element.getName())) {
                final String id = element.getAttribute("id");
                // XXX ugly hack to get rid of the non-normalized analyses
                if (id.toLowerCase().contains("metric")) {
                    this.layoutMetricsMap.put(id, element);
                }
            }
        }
    }
}
