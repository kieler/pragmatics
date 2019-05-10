package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties

class FocusAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.FocusAction"
    
    override execute(ActionContext context) {
        // The Bundle element itself that was clicked on.
        val element = context.getDomainElement(context.KNode)
        
        // Put the bundle as the main element in the view context.
        context.viewContext.setProperty(OsgiSynthesisProperties.MAIN_ELEMENT, element)
        
        return ActionResult.createResult(true)
    }
    
}