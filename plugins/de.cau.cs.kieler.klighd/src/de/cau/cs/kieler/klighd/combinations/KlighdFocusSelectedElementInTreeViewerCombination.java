/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.combinations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.klighd.effects.KlighdFocusInTreeViewerEffect;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState;

/**
 * A combination that realizes the focus of elements selected in KLighD views in related tree viewers.
 * 
 * @author chsch
 */
public class KlighdFocusSelectedElementInTreeViewerCombination extends AbstractCombination {
    
    /**
     * THE execute method!
     * 
     * 
     * @param state the trigger state the combination is sensitive to
     */
    public void execute(final KlighdSelectionState state) {
        TreeViewer viewer = null;
        if (state.getViewContext().getSourceWorkbenchPartViewer() instanceof TreeViewer) {
            viewer = (TreeViewer) state.getViewContext().getSourceWorkbenchPartViewer();
        } else {
            // in this case there is nothing to show the selected element in
            return;
        }
        if (!state.getSelectedEModelElements().isEmpty()) {
            // take just the 1st element for the moment - should be improved sometimes...
            EObject me = state.getSelectedEModelElements().get(0);
            if (me != null) {
                this.schedule(new KlighdFocusInTreeViewerEffect(me, viewer));
            }
        }
    }

}
