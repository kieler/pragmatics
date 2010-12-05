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
package de.cau.cs.kieler.kiml.grana.util;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;

/**
 * A utility class for grana handlers.
 * 
 * @author mri
 */
public final class GranaUtil {

    /** the name for the last analyses preference. */
    private static final String LAST_ANALYSES_PREFERENCE =
            "lastAnalysesPreference";

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
    public static List<AbstractInfoAnalysis> getLastAnalysesSelection() {
        return getAnalysesSelection(LAST_ANALYSES_PREFERENCE);
    }

    /**
     * Returns an analysis selection from the preference store.
     * 
     * @param preferenceKey
     *            the key of the preference which stores the given analysis
     *            selection
     * @return the last selected analyses
     */
    public static List<AbstractInfoAnalysis> getAnalysesSelection(
            final String preferenceKey) {
        List<AbstractInfoAnalysis> result =
                new LinkedList<AbstractInfoAnalysis>();
        // get the preference store
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        // get the serialized analyses ids from the preference store
        String analysesIdsSerialized = preferenceStore.getString(preferenceKey);
        String[] analysesIds = analysesIdsSerialized.split(";");
        for (String analysisId : analysesIds) {
            AbstractInfoAnalysis analysis =
                    AnalysisServices.getInstance().getAnalysisById(
                            analysisId.trim());
            if (analysis != null) {
                result.add(analysis);
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
    public static void setLastAnalysesSelection(
            final List<AbstractInfoAnalysis> analyses) {
        setAnalysesSelection(LAST_ANALYSES_PREFERENCE, analyses);
    }

    /**
     * Sets an analysis selection in the preference store.
     * 
     * @param preferenceKey
     *            the key of the preference which stores the given analysis
     *            selection
     * @param analyses
     *            the analyses
     */
    public static void setAnalysesSelection(final String preferenceKey,
            final List<AbstractInfoAnalysis> analyses) {
        // get the preference store
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        // serialize the analyses ids
        String analysesIdsSerialized = "";
        for (AbstractInfoAnalysis analysis : analyses) {
            analysesIdsSerialized += analysis.getId() + ";";
        }
        preferenceStore.setValue(preferenceKey, analysesIdsSerialized);
    }
}
