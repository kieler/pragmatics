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
package de.cau.cs.kieler.kiml.grana.handlers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;

/**
 * The base class for handlers that perform an analysis of any kind.
 * 
 * @author mri
 */
public abstract class AbstractAnalysisHandler extends AbstractHandler {
    
    /** the name for the last analyses preference. */
    public static final String LAST_ANALYSES_PREFERENCE = "lastAnalysesPreference"; 
   
    /**
     * Returns the last selected analyses from the preference store.
     * 
     * @return the last selected analyses
     */
    protected List<AbstractInfoAnalysis> getLastAnalysesSelection() {
        List<AbstractInfoAnalysis> result =
                new LinkedList<AbstractInfoAnalysis>();
        // get the preference store
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        // get the serialized analyses ids from the preference store
        String analysesIdsSerialized =
                preferenceStore.getString(LAST_ANALYSES_PREFERENCE);
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
     * Sets the last selected analyses in the preference store.
     * 
     * @param analyses
     *            the analyses
     */
    protected void setLastAnalysesSelection(
            final List<AbstractInfoAnalysis> analyses) {
        // get the preference store
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        // serialize the analyses ids
        String analysesIdsSerialized = "";
        for (AbstractInfoAnalysis analysis : analyses) {
            analysesIdsSerialized += analysis.getId() + ";";
        }
        preferenceStore.setValue(LAST_ANALYSES_PREFERENCE,
                analysesIdsSerialized);
    }
}
