package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
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
    public static val String ID = RevealImplementingServiceComponentsAction.name
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // The ServiceInterfaceContext element for the element that was clicked on.
        val serviceInterfaceContext = modelVisualizationContext as ServiceInterfaceContext
        
        // The ServiceInterface itself from the context.
        val serviceInterface = serviceInterfaceContext.modelElement
        
        // The service interface overview context this service interface is shown in.
        val serviceInterfaceOverviewContext = serviceInterfaceContext.parentVisualizationContext
            as ServiceInterfaceOverviewContext
        
        // ----- Put the service components in the context for the PLAIN view -----
        
        // The service components that are not already represented in the overview need to be put in first.
        // Collect all contexts for the service components implementing this interface.
        val implementingServiceComponentContextsPlain = new ArrayList<ServiceComponentContext>
        serviceInterface.serviceComponent.forEach [ serviceComponent |
            var implementingServiceComponentContext = serviceInterfaceOverviewContext
                .implementingServiceComponentContexts.findFirst [
                    return it.modelElement === serviceComponent
                ]
            if (implementingServiceComponentContext === null) {
                implementingServiceComponentContext = new ServiceComponentContext(serviceComponent,
                    serviceInterfaceOverviewContext)
                serviceInterfaceOverviewContext.implementingServiceComponentContexts
                    .add(implementingServiceComponentContext)
            }
            implementingServiceComponentContextsPlain.add(implementingServiceComponentContext)
        ]
        
        implementingServiceComponentContextsPlain.forEach [ implementingServiceComponentContext |
            ContextUtils.addImplementingServiceComponentEdgePlain(serviceInterfaceContext,
                implementingServiceComponentContext)
        ]
        
        // ----- Put the service components and the bundles in the context for the IN_BUNDLES view -----
        
        val implementingServiceComponentContextsInBundles = new ArrayList<ServiceComponentContext>
        serviceInterface.serviceComponent.forEach [ serviceComponent |
            // Find the bundle context that should be containing a view on this service component.
            var referencedBundleContext = serviceInterfaceOverviewContext.referencedBundleContexts.findFirst [
                return it.modelElement === serviceComponent.bundle
            ]
            // Create a new bundle context for that bundle if it is not yet in the view.
            if (referencedBundleContext === null) {
                referencedBundleContext = new BundleContext(serviceComponent.bundle, serviceInterfaceOverviewContext)
                serviceInterfaceOverviewContext.referencedBundleContexts.add(referencedBundleContext)
            }
            
            val serviceComponentOverviewContext = referencedBundleContext.serviceComponentOverviewContext
            serviceComponentOverviewContext.expanded = true
            var implementingServiceComponentContext = serviceComponentOverviewContext
                .childContexts.findFirst [
                    return it.modelElement === serviceComponent
                ]
            // Create a new service component context for the component if it is not already in the bundle view.
            if (implementingServiceComponentContext === null) {
                throw new IllegalStateException("The bundle context does not contain all its service components as "
                    + "own contexts!")
            }
            ContextUtils.makeDetailed(serviceComponentOverviewContext,
                implementingServiceComponentContext)
            implementingServiceComponentContextsInBundles.add(implementingServiceComponentContext
                as ServiceComponentContext)
        ]
        
        implementingServiceComponentContextsInBundles.forEach [ implementingServiceComponentContext |
            ContextUtils.addImplementingServiceComponentEdgeInBundle(serviceInterfaceContext,
                implementingServiceComponentContext)
        ]
        
        
        return null
    }
    
}