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
package de.cau.cs.kieler.klighd.combinations

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kivi.AbstractCombination
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect
import static extension org.eclipse.emf.ecore.util.EcoreUtil.*

/**
 * 
 */
class KlighdCropDiagramCombination extends AbstractCombination {

    /**
     * THE 'execute' method.
     * 
     * @param selection the selection state the combination is sensitive to
     */
    def void execute(KlighdSelectionTrigger$KlighdSelectionState selection) {
        if (selection.getSelections().size() > 1) {
            val KNode root = copy(getRootContainer(selection.selectedDiagramElements.head) as KNode);
            root.getChildren().clear();
            root.getChildren() += copyAll(selection.selectedDiagramElements.filter(typeof(KNode)).toList);
            this.schedule(new KlighdUpdateDiagramEffect(selection.getViewId()+"_1", root));
        }
    }
}