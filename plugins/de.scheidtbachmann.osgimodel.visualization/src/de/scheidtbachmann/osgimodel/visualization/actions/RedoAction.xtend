package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties

/**
 * Redoes the last action that was undone on the view model.
 * 
 * @author nre
 */
class RedoAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RedoAction"
    
    override execute(ActionContext context) {
        // Just increment the current index pointing towards the current visualization by one.
        var index = context.viewContext.getProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX).intValue
        val visualizationContexts = context.viewContext.getProperty(OsgiSynthesisProperties.VISUALIZATION_CONTEXTS)
        index = Math.min(index + 1, visualizationContexts.size - 1)
        context.viewContext.setProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX, Integer.valueOf(index))
        
        return ActionResult.createResult(true).doSynthesis
    }
    
}