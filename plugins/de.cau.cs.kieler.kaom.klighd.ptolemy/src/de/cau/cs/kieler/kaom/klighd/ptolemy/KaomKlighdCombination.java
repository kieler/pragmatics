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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.kaom.klighd.ptolemy;

import java.util.List;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;

/**
 * Combination to display a kaom model in a klight view.
 * 
 * @author ckru
 *
 */
public class KaomKlighdCombination extends AbstractCombination {

    /**
     * {@inheritDoc}
     */
    public void execute(SelectionState triggerState) {
        List<Object> selection = triggerState.getSelectedObjects();
        if ((selection != null && !selection.isEmpty())) {// && selection.forall[typeof(EcoreDomainNavigatorItem).isInstance(it)]) {
            if (selection.get(0) instanceof Entity) {
                KlighdUpdateDiagramEffect e = new KlighdUpdateDiagramEffect(triggerState.getWorkbenchPart().getTitle(), selection.get(0));
                e.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy.ID);
                this.schedule(e);
            }

        }
        
    }
    
}
