package de.cau.cs.kieler.klighd.xtext;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.xtext.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;

public class UpdateXtextModelKLighDCombination extends AbstractCombination {
    
    public void execute(XtextModelChangeState state) {
        StatusManager.getManager().addLoggedStatus(
                new Status(IStatus.INFO, KLighDXtextPlugin.PLUGIN_ID, state.getResource().getURI()
                        .toPlatformString(false)));
        System.out.println(state.getResource().getURI().toPlatformString(false));
        this.schedule(new KlighdDiagramEffect(state.getResource().getURI().toPlatformString(false), state
                .getResource().getContents().get(0)));
    }

}
