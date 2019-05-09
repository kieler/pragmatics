package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.IAction.ActionContext

class RevealRequiredBundlesAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealRequiredBundlesAction"
    
    override execute(ActionContext context) {
        // TODO: this.
        return ActionResult.createResult(true)
    }
    
}