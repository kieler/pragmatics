package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext

/**
 * Focuses the element this action is issued on and replaces the view model with the focused element.
 * 
 * @author nre
 */
class FocusAction extends AbstractVisualizationContextChangingAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.FocusAction"
    
    override protected <M> changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // Changing the focus element is exactly what returning a non-null visualization context to the
        // AbstractVisualizationContextChangingAction does, so just return the given context.
        return modelVisualizationContext
    }
    
}