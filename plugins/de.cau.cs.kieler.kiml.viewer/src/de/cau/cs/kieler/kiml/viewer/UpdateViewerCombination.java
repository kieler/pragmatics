/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.viewer;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;
import de.cau.cs.kieler.kiml.ui.layout.LayoutEffect;
import de.cau.cs.kieler.kiml.ui.triggers.LayoutGraphTriggerState;

/**
 * Combination for updating the KIML Viewer when layout actions are performed.
 * 
 * @author soh
 */
public class UpdateViewerCombination extends AbstractCombination {

    /**
     * Update the viewer.
     * 
     * @param effectTrigger
     *            the layout effect trigger
     * @param layoutGraphTrigger
     *            the layout graph trigger
     */
    public void execute(final EffectTriggerState<LayoutEffect> effectTrigger,
            final LayoutGraphTriggerState layoutGraphTrigger) {
        if (effectTrigger.getSequenceNumber() > layoutGraphTrigger
                .getSequenceNumber()) {
            LayoutEffect layoutEffect = effectTrigger.getEffect();
            schedule(new UpdateViewerEffect(layoutEffect.getManager()
                    .getLayoutGraph(), UpdateViewerEffect.POST_LAYOUT));
        } else {
            KNode graph = layoutGraphTrigger.getLayoutGraph();
            String state = layoutGraphTrigger.getState();
            schedule(new UpdateViewerEffect(graph, state));
        }
    }
}
