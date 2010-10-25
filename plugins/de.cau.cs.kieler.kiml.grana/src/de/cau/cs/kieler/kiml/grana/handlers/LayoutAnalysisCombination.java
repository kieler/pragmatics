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

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.LayoutEffect;

/**
 * A view management combination that performs graph analysis after layout.
 * 
 * @author msp
 */
public class LayoutAnalysisCombination extends AbstractCombination {

    /**
     * Execute this combination with an effect trigger state.
     * 
     * @param layoutState the trigger state of the last layout effect
     */
    public void execute(final EffectTriggerState<LayoutEffect> layoutState) {
        DiagramLayoutManager manager = layoutState.getEffect().getManager();
        if (manager != null) {
            final List<AbstractInfoAnalysis> analyses = getLastAnalysesSelection();
            KNode parentNode = manager.getLayoutGraph();
            schedule(new AnalysisEffect(parentNode, analyses, false));
        }
    }
    
    /**
     * Returns the last selected analyses from the preference store.
     * 
     * @return the last selected analyses
     */
    private List<AbstractInfoAnalysis> getLastAnalysesSelection() {
        List<AbstractInfoAnalysis> result =
                new LinkedList<AbstractInfoAnalysis>();
        // get the preference store
        IPreferenceStore preferenceStore =
                GranaPlugin.getDefault().getPreferenceStore();
        // get the serialized analyses ids from the preference store
        String analysesIdsSerialized =
                preferenceStore.getString(AbstractAnalysisHandler.LAST_ANALYSES_PREFERENCE);
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
    
}
