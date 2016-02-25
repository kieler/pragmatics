/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.util;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.grana.GranaPlugin;

/**
 * A utility class for grana handlers.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public final class GranaUtil {

    /** the name for the last analyses preference. */
    private static final String LAST_ANALYSES_PREFERENCE = "lastAnalysesPreference";

    /**
     * Make this class not instantiable.
     */
    private GranaUtil() {
        // do nothing
    }

    /**
     * Returns the last analyses selection from the preference store.
     * 
     * @return the last analyses selection
     */
    public static List<AnalysisData> getLastAnalysesSelection() {
        return getAnalysesSelection(LAST_ANALYSES_PREFERENCE);
    }

    /**
     * Returns an analysis selection from the preference store.
     * 
     * @param preferenceKey
     *            the key of the preference which stores the analysis selection
     * @return the last selected analyses
     */
    public static List<AnalysisData> getAnalysesSelection(final String preferenceKey) {
        List<AnalysisData> result = new LinkedList<AnalysisData>();
        IPreferenceStore preferenceStore = GranaPlugin.getDefault().getPreferenceStore();
        // get the serialized analyses ids from the preference store
        String analysesIdsSerialized = preferenceStore.getString(preferenceKey);
        String[] analysesIds = analysesIdsSerialized.split(";");
        for (String analysisId : analysesIds) {
            AnalysisData analysis = AnalysisService.getInstance().getAnalysis(analysisId.trim());
            if (analysis != null) {
                result.add(analysis);
            }
        }
        return result;
    }
    
    /**
     * Returns a layout configuration from the preference store.
     * 
     * @param preferenceKey
     *            the key of the preference which stores the layout configuration
     * @return the last used layout configuration
     */
    public static List<Pair<LayoutOptionData, Object>> getConfiguration(
            final String preferenceKey) {
        List<Pair<LayoutOptionData, Object>> result
                = new LinkedList<Pair<LayoutOptionData, Object>>();
        IPreferenceStore preferenceStore = GranaPlugin.getDefault().getPreferenceStore();
        // get the serialized analyses ids from the preference store
        String optionsSerialized = preferenceStore.getString(preferenceKey);
        String[] options = optionsSerialized.split(";");
        for (String option : options) {
            String[] keyAndValue = option.split("=");
            if (keyAndValue.length == 2) {
                LayoutOptionData optionData = LayoutMetaDataService.getInstance()
                .getOptionData(keyAndValue[0]);
                if (optionData != null) {
                    Object value = optionData.parseValue(keyAndValue[1]);
                    if (value != null) {
                        result.add(new Pair<LayoutOptionData, Object>(optionData, value));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Sets the last analyses selection in the preference store.
     * 
     * @param analyses
     *            the last analyses selection
     */
    public static void setLastAnalysesSelection(final List<AnalysisData> analyses) {
        setAnalysesSelection(LAST_ANALYSES_PREFERENCE, analyses);
    }

    /**
     * Sets an analysis selection in the preference store.
     * 
     * @param preferenceKey
     *            the key of the preference which stores the given analysis selection
     * @param analyses
     *            the analyses
     */
    public static void setAnalysesSelection(final String preferenceKey,
            final List<AnalysisData> analyses) {
        IPreferenceStore preferenceStore = GranaPlugin.getDefault().getPreferenceStore();
        // serialize the analyses ids
        StringBuilder analysesIdsSerialized = new StringBuilder();
        for (AnalysisData analysis : analyses) {
            analysesIdsSerialized.append(analysis.getId()).append(";");
        }
        preferenceStore.setValue(preferenceKey, analysesIdsSerialized.toString());
    }
    
    /**
     * Sets a layout configuration in the preference store.
     * 
     * @param preferenceKey
     *            the key of the preference which stores the given layout configuration
     * @param layoutConfig
     *            the configuration
     */
    public static void setConfiguration(final String preferenceKey,
            final List<Pair<IProperty<?>, Object>> layoutConfig) {
        IPreferenceStore preferenceStore = GranaPlugin.getDefault().getPreferenceStore();
        // serialize the layout options
        StringBuilder optionsSerialized = new StringBuilder();
        for (Pair<IProperty<?>, Object> entry : layoutConfig) {
            optionsSerialized.append(entry.getFirst().getId()).append("=")
                    .append(entry.getSecond().toString()).append(";");
        }
        preferenceStore.setValue(preferenceKey, optionsSerialized.toString());
    }
    
}
