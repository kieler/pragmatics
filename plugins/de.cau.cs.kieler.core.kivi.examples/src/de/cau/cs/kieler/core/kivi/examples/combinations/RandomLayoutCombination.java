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

import java.util.ArrayList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.examples.KiViExamplesPlugin;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.model.trigger.DiagramTrigger.DiagramState;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.LayoutEffect;
import de.cau.cs.kieler.kiml.util.RandomLayoutProvider;

/**
 * Add a button that triggers a Random layout onto the whole diagram once.
 * 
 * @author haf
 * 
 */
public class RandomLayoutCombination extends AbstractCombination {

    private final String RANDOM_BUTTON = "de.cau.cs.kieler.core.kivi.randomLayout";

    private static final ArrayList<String> editorIDs = Lists.newArrayList("de.cau.cs.kieler.synccharts.diagram.part.SyncchartsDiagramEditorID",
    "de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorID");
    
    public RandomLayoutCombination() {
        ImageDescriptor iconRandom = KiViExamplesPlugin.imageDescriptorFromPlugin(
                KiViExamplesPlugin.PLUGIN_ID, "icons/random-arrange.gif");

        KiviMenuContributionService.INSTANCE.addToolbarButton(this, RANDOM_BUTTON,
                "RandomLayout", "Do a random layout on the current diagram. Invalidates all positions but keeps the original layout options.", iconRandom,
                SWT.PUSH, null, editorIDs.toArray(new String[2]));
    }

    public void execute(ButtonState button, DiagramState diagram) {
        dontUndo();
        if (this.getTriggerState() instanceof ButtonState
                && button.getButtonId().equals(RANDOM_BUTTON)) {
            LayoutEffect layout = new LayoutEffect(diagram.getDiagramPart(), null, true, false);
            TreeIterator iterator = diagram.getSemanticModel().eAllContents();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if (object instanceof EObject) {
                    layout.setOption((EObject) object, LayoutOptions.LAYOUTER_HINT,
                            RandomLayoutProvider.ID);
                }
            }
            this.schedule(layout);
        }else{
            doNothing();
        }
    }
}
