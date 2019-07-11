package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext
import org.eclipse.emf.ecore.EObject

/**
 * An action that removes an element's context from an {@link IOverviewVisualizationContext}.
 * 
 * @author nre
 */
class ContextRemoveAction extends AbstractVisualizationContextChangingAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.ContextRemoveAction" 
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // This action will always be performed on a child visualization context of a IOverviewVisualizationContext.
        val overviewVisContext = modelVisualizationContext.parentVisualizationContext
        if (!(overviewVisContext instanceof IOverviewVisualizationContext)) {
            throw new IllegalStateException("This action is performed on an element that is not inside an overview " +
                "visualization!")
        }
        val ovc = overviewVisContext as IOverviewVisualizationContext<M>
        
        // Service component in a service interface context:
        if (modelVisualizationContext instanceof ServiceComponentContext
            && ovc instanceof ServiceInterfaceOverviewContext) {
            (ovc as ServiceInterfaceOverviewContext).implementingServiceComponentContexts
                .remove(modelVisualizationContext)
            ContextUtils.removeEdges(ovc, modelVisualizationContext)
        } else {
            throw new IllegalArgumentException("ContextRemoveAction does not support removing "
                + modelVisualizationContext.class + " from " + ovc.class + " yet.")
        }
        return null
    }
    
}