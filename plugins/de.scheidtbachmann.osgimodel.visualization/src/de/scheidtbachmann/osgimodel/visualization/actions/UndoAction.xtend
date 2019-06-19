package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties

/**
 * Undoes the last action performed on the view model.
 */
class UndoAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.UndoAction"
    
    override execute(ActionContext context) {
        // Just decrement the current index pointing towards the current visualization by one.
        var index = context.viewContext.getProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX).intValue
        index = Math.max(index - 1, 0)
        context.viewContext.setProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX, Integer.valueOf(index))
        
        return ActionResult.createResult(true).doSynthesis
    }
    
}