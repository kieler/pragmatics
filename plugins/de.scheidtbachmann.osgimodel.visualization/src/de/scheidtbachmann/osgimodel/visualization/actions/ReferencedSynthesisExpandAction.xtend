package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.actions.CollapseExpandAction
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext

/**
 * A {@link CollapseExpandAction} that calls the synthesis of the expanded object on first expansion and adds the new
 * synthesized nodes as the children of the expanded node.
 * 
 * The expanded rendering of the node should really not contain much at all, as the dynamically synthesized node should
 * contain all content of the element.
 */
class ReferencedSynthesisExpandAction extends AbstractVisualizationContextChangingAction { // TODO: rename this action.
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.ReferencedSynthesisExpandAction" 
    
    override protected changeVisualization(IVisualizationContext modelVisualizationContext, ActionContext actionContext) {
        // This action will always be performed on a child visualization context of a IOverviewVisualizationContext.
        val overviewVisContext = modelVisualizationContext.parentVisualizationContext
        if (!(overviewVisContext instanceof IOverviewVisualizationContext)) {
            throw new IllegalStateException("This action is performed on an element that is not inside an overview " +
                "visualization!")
        }
        val ovc = (overviewVisContext as IOverviewVisualizationContext)
        
        if (ovc.collapsedElements.contains(modelVisualizationContext)) {
            ContextUtils.makeDetailed(ovc, modelVisualizationContext)
        } else if (ovc.detailedElements.contains(modelVisualizationContext)) {
            // TODO: this.
        } else { // This error should not be reachable.
            throw new IllegalStateException("Bug in code detected. This context has to be either contained within " +
                "the collapsed or the contained elements")
        }
    }
    
}