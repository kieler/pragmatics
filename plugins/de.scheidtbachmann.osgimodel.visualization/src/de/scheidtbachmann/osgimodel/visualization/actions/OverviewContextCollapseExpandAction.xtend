package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import org.eclipse.emf.ecore.EObject

/**
 * An action that collapses or expands an overview by setting its {@link IOverviewVisualizationContext} that way.
 * 
 * @author nre
 */
class OverviewContextCollapseExpandAction extends AbstractVisualizationContextChangingAction {
    /**
     * This action's ID.
     */
    public static val String ID = OverviewContextCollapseExpandAction.name 
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        if (!(modelVisualizationContext instanceof IOverviewVisualizationContext)) {
            throw new IllegalArgumentException("This action can only be called on a IOverviewVisualizationContext. "
                + "Was called on " + modelVisualizationContext.class)
        }
        // Just invert the expanded state.
        val c = modelVisualizationContext as IOverviewVisualizationContext<M>
        c.expanded = !c.expanded
        
        // Also, toggle the expansion state in the viewer.
        actionContext.getActiveViewer().toggleExpansion(actionContext.getKNode());
        
        return null
    }
    
}