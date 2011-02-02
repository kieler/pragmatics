/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.core.kivi.examples.combinations;

import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.examples.KiViExamplesPlugin;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.core.model.CoreModelPlugin;
import de.cau.cs.kieler.core.model.effects.CompartmentCollapseExpandEffect;
import de.cau.cs.kieler.core.model.effects.FocusContextEffect;
import de.cau.cs.kieler.core.model.trigger.DiagramTrigger.DiagramState;
import de.cau.cs.kieler.core.model.trigger.ModelChangeTrigger.ActiveEditorState;
import de.cau.cs.kieler.kiml.ui.layout.LayoutEffect;

/**
 * @author haf
 * 
 */
public class ManualFocusCombination extends AbstractCombination {

    private final static String selectionFocusButtonId = "de.cau.cs.kieler.core.kivi.selectionFocus";
    private final static String focusPlusButtonId = "de.cau.cs.kieler.core.kivi.focusPlus";
    private final static String focusMinusButtonId = "de.cau.cs.kieler.core.kivi.focusMinus";

    /*
     * Add editor ID here to enable this button also for other editors.
     */
    private final static String[] editorIDs = { 
        "de.cau.cs.kieler.synccharts.diagram.part.SyncchartsDiagramEditorID",
        "de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorID"};

    private static final int DEFAULT_ZOOM_LEVEL = 1;
    private int zoomLevel = DEFAULT_ZOOM_LEVEL;

    /**
     * Default Constructor defining some Buttons.
     */
    public ManualFocusCombination() {
        ImageDescriptor iconFC = KiViExamplesPlugin.imageDescriptorFromPlugin(
                KiViExamplesPlugin.PLUGIN_ID, "icons/focusContext.png");
        ImageDescriptor iconPlus = KiViExamplesPlugin.imageDescriptorFromPlugin(
                KiViExamplesPlugin.PLUGIN_ID, "icons/focusContextPlus.png");
        ImageDescriptor iconMinus = KiViExamplesPlugin.imageDescriptorFromPlugin(
                KiViExamplesPlugin.PLUGIN_ID, "icons/focusContextMinus.png");

        KiviMenuContributionService.INSTANCE.addToolbarButton(this, selectionFocusButtonId,
                "focusSelect", "Focus selected model objects and do a semantic zooming.", iconFC,
                SWT.CHECK, null, editorIDs);

        KiviMenuContributionService.INSTANCE.addToolbarButton(this, focusPlusButtonId, "focusPlus",
                "Increase Focus/Context zoom level.", iconPlus, SWT.PUSH, null, editorIDs);

        KiviMenuContributionService.INSTANCE.addToolbarButton(this, focusMinusButtonId,
                "focusMinus", "Decrease Focus/Context zoom level.", iconMinus, SWT.PUSH, null,
                editorIDs);
    }

    /**
     * Main Combination logic.
     * 
     * @param button
     *            listens to ButtonTriggers
     * @param selection
     *            listens to a SelectionTrigger
     * @param diagram
     *            listens to a DiagramTrigger
     */
    public void execute(final ButtonState button, final SelectionState selection,
            final DiagramState diagram) {
        if (this.getTriggerState() instanceof ButtonState) {
            if (button.getButtonId().equals(focusPlusButtonId)) {
                zoomLevel++;
            } else if (button.getButtonId().equals(focusMinusButtonId)) {
                zoomLevel--;
            }
        }
        if (button.isPushedIn(selectionFocusButtonId)) {
            List<EObject> focus = selection.getSelectedEObjects();
            // if nothing is selected, use the model root as the focus
            if (focus.isEmpty()) {
                focus.add(diagram.getSemanticModel());
            }

            FocusContextEffect focusEffect = new FocusContextEffect(diagram.getDiagramPart());
            focusEffect.addFocus(focus, zoomLevel);

            for (IEffect effect : focusEffect.getPrimitiveEffects()) {
                this.schedule(effect);
            }
            
            this.schedule(new LayoutEffect(diagram.getDiagramPart(), null, true));
            
        } else {
            // reset zoom level if not active
            zoomLevel = DEFAULT_ZOOM_LEVEL;
        }
    }

}
