package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.scheidtbachmann.osgimodel.visualization.OsgiDiagramSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties

/**
 * Removes the focus of any element for the {@link OsgiDiagramSynthesis}.
 */
class UnfocusAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.UnfocusAction"
    
    override execute(ActionContext context) {
        // Put null as the main element in the view context to reset the main element.
        context.viewContext.setProperty(OsgiSynthesisProperties.MAIN_ELEMENT, null)
        
        return ActionResult.createResult(true)
    }
    
}