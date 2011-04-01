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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.ArrayList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.model.triggers.DiagramTrigger.DiagramState;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.util.RandomLayoutProvider;

/**
 * Add a button that triggers a Random layout onto the whole diagram once.
 * 
 * @author haf
 * 
 */
public class RandomLayoutCombination extends AbstractCombination {

    private static final String RANDOM_BUTTON = "de.cau.cs.kieler.core.kivi.randomLayout";

    private static final ArrayList<String> EDITOR_IDS = Lists.newArrayList(
            "de.cau.cs.kieler.synccharts.diagram.part.SyncchartsDiagramEditorID",
            "de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorID");

    /**
     * Setup Buttons in the Constructor.
     */
    public RandomLayoutCombination() {
        ImageDescriptor iconRandom = KimlUiPlugin.imageDescriptorFromPlugin(KimlUiPlugin.PLUGIN_ID,
                "icons/menu16/random-arrange.gif");

        KiviMenuContributionService.INSTANCE.addToolbarButton(this, RANDOM_BUTTON, "RandomLayout",
                "Do a random layout on the current diagram. "
                        + "Invalidates all positions but keeps the original layout options.",
                iconRandom, SWT.PUSH, null, EDITOR_IDS.toArray(new String[2]));
    }

    /**
     * Execute this Combination and react on the input triggers.
     * 
     * @param button
     *            react on the random layout button
     * @param diagram
     *            react on the current diagram
     */
    public void execute(final ButtonState button, final DiagramState diagram) {
        dontUndo();
        if (this.getTriggerState() instanceof ButtonState
                && button.getButtonId().equals(RANDOM_BUTTON)) {
            LayoutEffect layout = new LayoutEffect(diagram.getDiagramPart(), null, false, true,
                    true, true);
            TreeIterator<?> iterator = diagram.getSemanticModel().eAllContents();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if (object instanceof EObject) {
                    layout.setOption((EObject) object, LayoutOptions.ALGORITHM,
                            RandomLayoutProvider.ID);
                }
            }
            this.schedule(layout);
        } else {
            doNothing();
        }
    }
}
