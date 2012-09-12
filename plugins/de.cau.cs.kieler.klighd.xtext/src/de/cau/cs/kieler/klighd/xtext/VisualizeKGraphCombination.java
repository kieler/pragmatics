/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext;

import de.cau.cs.kieler.core.model.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState;
// SUPPRESS CHECKSTYLE PREVIOUS LineLength

/**
 * A combination for initializing/refreshing of KLighD views of textually formulated KGraph models.
 * Views initiated by this combination are shared with {@link VisualizeChosenElementCombination}
 * since they use the compute the (secondary) view ids the same way.
 * 
 * @author chsch
 */
public class VisualizeKGraphCombination extends UpdateXtextModelKLighDCombination {

    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param state
     *            A {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     */
    public void execute(final XtextModelChangeState state) {
        // I chose the language name as the filter criterion as e.g. the resource provided by the
        //  trigger state won't by available for 'CLOSE' event firings.
        if (!state.getEditor().getLanguageName().equals("de.cau.cs.kieler.core.kgraph.text.KGraph")) {
            return;
        }
        super.execute(state);
    }
}
