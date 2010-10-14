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
package de.cau.cs.kieler.kiml.ui.layout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.CombinationParameter;
import de.cau.cs.kieler.core.kivi.triggers.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Performs automatic layout when the button or key combination is pressed.
 * 
 * @author mmu
 * 
 */
public class LayoutCombination extends AbstractCombination {

    private static final String ZOOM_TO_FIT = LayoutCombination.class.getCanonicalName()
            + ".zoomToFit";
    private static final String PROGRESS_BAR = LayoutCombination.class.getCanonicalName()
            + ".progressBar";

    private static final CombinationParameter[] PARAMETERS = new CombinationParameter[] {
            new CombinationParameter(ZOOM_TO_FIT, getPreferenceStore(), "Zoom to Fit",
                    "Perform zoom to fit with automatic layout.", true,
                    CombinationParameter.BOOLEAN_TYPE),
            new CombinationParameter(PROGRESS_BAR, getPreferenceStore(), "Progress Bar",
                    "Display a progress bar while performing automatic layout.", false,
                    CombinationParameter.BOOLEAN_TYPE) };

    /** the id of the layout command. */
    private static final String ID = "de.cau.cs.kieler.kiml.ui.command.layout";
    /** parameter identifier for the scope of automatic layout. */
    public static final String PARAM_LAYOUT_SCOPE = "de.cau.cs.kieler.kiml.ui.layoutScope";
    /** value for diagram scope. */
    public static final String VAL_DIAGRAM = "diagram";
    /** value for selection scope. */
    public static final String VAL_SELECTION = "selection";

    /**
     * Listen to view management buttons and the current selection.
     * 
     * @param button
     *            the trigger state for view management buttons
     * @param selection
     *            the trigger state for the current selection
     */
    public void execute(final ButtonState button, final SelectionState selection) {
        dontUndo();
        if (button.getSequenceNumber() < selection.getSequenceNumber()) {
            return; // the selection has changed, only layout when the button was pushed
        }
        if (!ID.equals(button.getButtonId())) {
            return; // sort out other buttons
        }

        // check parameter for layout scope, default is diagram scope
        Object layoutScope = button.getParameters().get(PARAM_LAYOUT_SCOPE);
        if (layoutScope instanceof String && layoutScope.equals(VAL_SELECTION)) {
            for (EObject selected : selection.getSelectedEObjects()) {
                schedule(new LayoutEffect(button.getEditor(), selected, zoom(), progressBar()));
            }
        } else {
            schedule(new LayoutEffect(button.getEditor(), null, zoom(), progressBar()));
        }
    }

    /**
     * Get the parameters for the layout combination.
     * 
     * @return array of parameters
     */
    public static CombinationParameter[] getParameters() {
        return PARAMETERS;
    }

    private boolean zoom() {
        return getPreferenceStore().getBoolean(ZOOM_TO_FIT);
    }

    private boolean progressBar() {
        return getPreferenceStore().getBoolean(PROGRESS_BAR);
    }

    private static IPreferenceStore getPreferenceStore() {
        return KimlUiPlugin.getDefault().getPreferenceStore();
    }
}
