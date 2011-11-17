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
package de.cau.cs.kieler.kiml.ui.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.CombinationParameter;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.DiagramSelectionState;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Performs automatic layout when the button or key combination is pressed.
 * 
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author mmu
 */
public class LayoutCombination extends AbstractCombination {

    /** parameter id for animation. */
    public static final String ANIMATE = "de.cau.cs.kieler.kiml.animate";
    /** parameter id for zoom to fit. */
    public static final String ZOOM_TO_FIT = "de.cau.cs.kieler.kiml.zoomToFit";
    /** parameter id for progress bar. */
    public static final String PROGRESS_BAR = "de.cau.cs.kieler.kiml.progressBar";
    
    /** parameter array for this combination. */
    private static final CombinationParameter<?>[] PARAMETERS = new CombinationParameter[] {
            new CombinationParameter<Boolean>(ANIMATE, getPreferenceStore(), "Animate",
                    "Animates the automatic layout of a graph.", true),
            new CombinationParameter<Boolean>(ZOOM_TO_FIT, getPreferenceStore(), "Zoom to Fit",
                    "Perform zoom to fit with automatic layout.", false),
            new CombinationParameter<Boolean>(PROGRESS_BAR, getPreferenceStore(), "Progress Bar",
                    "Display a progress bar while performing automatic layout.", false)
    };

    /** the id of the layout command. */
    private static final String COMMAND_ID = "de.cau.cs.kieler.kiml.ui.command.layout";
    /** parameter identifier for the scope of automatic layout. */
    public static final String PARAM_LAYOUT_SCOPE = "de.cau.cs.kieler.kiml.ui.layoutScope";
    /** parameter value for diagram scope. */
    public static final String VAL_DIAGRAM = "diagram";
    /** parameter value for selection scope. */
    public static final String VAL_SELECTION = "selection";

    /**
     * Return the preference store for the KIML UI plugin.
     * 
     * @return the preference store
     */
    private static IPreferenceStore getPreferenceStore() {
        return KimlUiPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Get the parameters for the layout combination.
     * 
     * @return array of parameters
     */
    public static CombinationParameter<?>[] getParameters() {
        return PARAMETERS;
    }

    /**
     * Listen to view management buttons and the current selection.
     * 
     * @param button
     *            the trigger state for view management buttons
     * @param selection
     *            the trigger state for the current selection
     */
    public void execute(final ButtonState button, final DiagramSelectionState selection) {
        if (button == latestState() && COMMAND_ID.equals(button.getButtonId())) {
            IPreferenceStore preferenceStore = getPreferenceStore();
            boolean animate = preferenceStore.getBoolean(ANIMATE);
            boolean zoom = preferenceStore.getBoolean(ZOOM_TO_FIT);
            boolean progressBar = preferenceStore.getBoolean(PROGRESS_BAR);
            // check parameter for layout scope, default is diagram scope
            Object layoutScope = button.getParameters().get(PARAM_LAYOUT_SCOPE);
            if (layoutScope instanceof String && layoutScope.equals(VAL_SELECTION)) {
                for (EObject selected : selection.getSelectedEObjects()) {
                    // merging of multiple selected objects is done by the layout effect
                    schedule(new LayoutEffect(button.getEditor(), selected, zoom, progressBar,
                            false, animate));
                }
            } else {
                schedule(new LayoutEffect(button.getEditor(), null, zoom, progressBar, false,
                        animate));
            }
        }
    }

}
