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

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.util.GranaUtil;
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
     * @param layoutState
     *            the trigger state of the last layout effect
     */
    public void execute(final EffectTriggerState<LayoutEffect> layoutState) {
        DiagramLayoutManager manager = layoutState.getEffect().getManager();
        if (manager != null) {
            final List<AbstractInfoAnalysis> analyses =
                    GranaUtil.getLastAnalysesSelection();
            KNode parentNode = manager.getLayoutGraph();
            schedule(new AnalysisEffect(parentNode, analyses, false));
        }
    }
}
