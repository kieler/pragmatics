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
package de.cau.cs.kieler.klighd.xtext;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.xtext.effects.XtextEditorHighlightEffect;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState.SelectionElement;

/**
 * @author chsch
 */
public class HighlightChosenElementInXtextEditorCombination extends AbstractCombination {

    /**
     * @param state triggerState
     */
    public void execute(final KlighdSelectionState state) {
        if (!state.getSelections().isEmpty()) {
            SelectionElement se = state.getSelections().get(0);
            final Object me = se.getModelElement();
            if (me == null || !(me instanceof EObject)) {
                return;
            }
            this.schedule(new XtextEditorHighlightEffect((EObject) me));
        }
    }
}
