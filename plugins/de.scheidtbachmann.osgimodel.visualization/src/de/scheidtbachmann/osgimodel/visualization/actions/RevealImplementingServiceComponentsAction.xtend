package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext
import java.util.ArrayList
import org.eclipse.emf.ecore.EObject

/**
 * Puts the service components implementing this service interface next to this service interface and connects them with
 * an edge from the new service component to this service interface's 'implementingServiceComponents' port.
 * 
 * @author nre
 */
class RevealImplementingServiceComponentsAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealImplementingServiceComponentsAction"
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // The ServiceInterfaceContext element for the element that was clicked on.
        val serviceInterfaceContext = modelVisualizationContext as ServiceInterfaceContext
        
        // The ServiceInterface itself from the context.
        val serviceInterface = serviceInterfaceContext.modelElement
        
        // The service interface overview context this service interface is shown in.
        val serviceInterfaceOverviewContext = serviceInterfaceContext.parentVisualizationContext as ServiceInterfaceOverviewContext
        
        // The service components that are not already represented in the overview need to be put in first.
        // Collect all contexts for the service components implementing this interface.
        val implementingServiceComponentContexts = new ArrayList<ServiceComponentContext>
        serviceInterface.serviceComponent.forEach [ serviceComponent |
            var implementingServiceComponentContext = serviceInterfaceOverviewContext.implementingServiceComponentContexts.findFirst [
                return it.modelElement === serviceComponent
            ]
            if (implementingServiceComponentContext === null) {
                implementingServiceComponentContext = new ServiceComponentContext(serviceComponent, serviceInterfaceOverviewContext)
                serviceInterfaceOverviewContext.implementingServiceComponentContexts.add(implementingServiceComponentContext)
            }
            implementingServiceComponentContexts.add(implementingServiceComponentContext)
        ]
        
        implementingServiceComponentContexts.forEach [ implementingServiceComponentContext |
            ContextUtils.addImplementingServiceComponentEdge(serviceInterfaceContext, implementingServiceComponentContext)
        ]
        
        return null
    }
    
}