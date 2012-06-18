package de.cau.cs.kieler.kaom.karma.ptolemy.krendering;

import java.util.List;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect;

public class KaomKlighdCombination extends AbstractCombination {

    public void execute(SelectionState triggerState) {
        List<Object> selection = triggerState.getSelectedObjects();
        if ((selection != null && !selection.isEmpty())) {// && selection.forall[typeof(EcoreDomainNavigatorItem).isInstance(it)]) {
            if (selection.get(0) instanceof Entity) {
                this.schedule(new KlighdUpdateDiagramEffect(selection.get(0)));
            }

        }
        
    }
    
}
