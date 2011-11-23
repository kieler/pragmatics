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
package de.cau.cs.kieler.ksbase.ui.kivi;

import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.EObjectSelectionState;

/**
 * 
 * This combination while active will update the kieler toolbar every
 * time the selection in the editor is changed.
 * 
 * @author ckru
 * 
 */
public class UpdateVisibilityCombination extends AbstractCombination {

    /**
     * {@inheritDoc}
     */
    public void execute(final EObjectSelectionState selection) {
        if (selection.getWorkbenchPart() instanceof IEditorPart) {
            UpdateVisibilityEffect effect = new UpdateVisibilityEffect(
                    (IEditorPart) selection.getWorkbenchPart());
            effect.schedule();
            // update((IEditorPart) selection.getWorkbenchPart());
        }

    }

}
