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

/**
 * This class is responsible for reading the evaluation data extensions.
 *
 * @author bdu
 *
 */
public final class EvolutionDataUtil {
    /** identifier of the extension point for evolution data. */
    public static final String EXTP_ID_EVOLUTION_DATA = "de.cau.cs.kieler.kiml.evol.evolutionData";
    private static final Object ELEMENT_DATA = "data";

    /**
     * Builds the evolution data.
     */
    public static void createEvolutionData() {
        // create instance of the evolution data holder class
        instance = new EvolutionDataUtil();
        // build evolution data for the extension point
        instance.loadEvolutionDataExtensions();
    }

    /**
     * The shared instance.
     */
    private static EvolutionDataUtil instance;

    /**
     *
     * Returns the evolution data with the given id.
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
     *
     * @return a copy of the registered elements.
     */
    public Set<String> getRegisteredElements() {
        Assert.isNotNull(this.evolutionDataMap);
        return Collections.unmodifiableSet(this.evolutionDataMap.keySet());
    }

    /**
     *
     * @return the singleton instance
     */
    public static EvolutionDataUtil getDefault() {
        return EvolutionDataUtil.instance;
    }

    private HashMap<String, IConfigurationElement> evolutionDataMap;

    /** set of registered evolution data. */
    /**
     * Loads and registers all evolution data entries from the extension point.
     */
    private void loadEvolutionDataExtensions() {
        // TODO: implement
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
     * The default constructor is hidden to prevent others from instantiating
     * this class.
     */
    private EvolutionDataUtil() {
        // nothing
    }
}
