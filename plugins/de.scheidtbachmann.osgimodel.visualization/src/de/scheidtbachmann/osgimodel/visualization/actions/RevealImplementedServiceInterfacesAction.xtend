package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.ServiceComponent
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext
import org.eclipse.emf.ecore.EObject

/**
 * Puts the service components implementing this service interface next to this service interface and connects them 
 * with an edge from this service interface's 'implementingServiceComponents' port to the new service component's
 * 'implementedServiceInterfaces' port.
 * 
 * @author nre
 */
class RevealImplementedServiceInterfacesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = RevealImplementedServiceInterfacesAction.name
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // The ServiceComponentContext element for the element that was clicked on.
        val serviceComponentContext = modelVisualizationContext as ServiceComponentContext
        
        // The ServiceComponent itself from the context.
        val serviceComponent = serviceComponentContext.modelElement
        
        // The overview context this service component is shown in.
        val overviewContext = serviceComponentContext.parentVisualizationContext
            as IOverviewVisualizationContext<?>
        
        // Handle this action differently, depending on in which overview context stack this is contained in.
        if (overviewContext instanceof ServiceInterfaceOverviewContext) {
            // Revealed component in an interface context in PLAIN view. Also update the corresponding IN_BUNDLES view.
            revealInInterfaceOverview(serviceComponent, overviewContext as ServiceInterfaceOverviewContext)
            
        } else if (overviewContext instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext instanceof BundleContext
            && overviewContext.parentVisualizationContext.parentVisualizationContext
                instanceof ServiceInterfaceOverviewContext) {
            // Revealed component in an interface context in IN_BUNDLES view. Also update the corresponding PLAIN view.
            revealInInterfaceOverview(serviceComponent,
                overviewContext.parentVisualizationContext.parentVisualizationContext
                as ServiceInterfaceOverviewContext)
            
        } else if (overviewContext instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext instanceof BundleContext
            && overviewContext.parentVisualizationContext.parentVisualizationContext instanceof BundleOverviewContext) {
            // Component in an independent bundle context. Only available as a plain view.
            revealInIndependentBundle(serviceComponentContext, overviewContext as ServiceComponentOverviewContext)
            
        } else if (overviewContext instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext instanceof ProductContext) {
            // Component defined inside a product in PLAIN view. Also update the corresponding IN_BUNDLES view.
            revealInProduct(serviceComponent, overviewContext as ServiceComponentOverviewContext)
            
        } else if (overviewContext instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext instanceof BundleContext
            && overviewContext.parentVisualizationContext.parentVisualizationContext
                instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext.parentVisualizationContext.parentVisualizationContext
                instanceof ProductContext) {
            // Component defined inside a product in IN_BUNDLES view. Also update the corresponding PLAIN view.
            revealInProduct(serviceComponent, overviewContext.parentVisualizationContext.parentVisualizationContext
                as ServiceComponentOverviewContext)
            
        } else {
            throw new IllegalStateException("Hierarchy of this service component is unknown and is not able to "
                + "reveal its implemented service interfaces.")
        }
        
        return null
    }
    
    private static def void revealInInterfaceOverview(ServiceComponent serviceComponent, 
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        // The service interfaces that are yet collapsed need to be expanded first.
        serviceComponent.serviceInterfaces.forEach [ serviceInterface |
            var implementedServiceInterfaceContext = serviceInterfaceOverviewContext
                .collapsedElements.findFirst [
                    return modelElement === serviceInterface
                ]
            if (implementedServiceInterfaceContext !== null) {
                ContextUtils.makeDetailed(serviceInterfaceOverviewContext, implementedServiceInterfaceContext)
            }
        ]
        
        // ----- Find/put the service component in the context for the PLAIN view -----
        var serviceComponentContextPlain = serviceInterfaceOverviewContext.implementingServiceComponentContexts
            .findFirst [ return it.modelElement === serviceComponent ]
        if (serviceComponentContextPlain === null) {
            serviceComponentContextPlain = new ServiceComponentContext(serviceComponent,
                serviceInterfaceOverviewContext)
            serviceInterfaceOverviewContext.implementingServiceComponentContexts.add(serviceComponentContextPlain)
        }
        // Make this variable final.
        val serviceComponentContextPlain_ = serviceComponentContextPlain
        
        // ----- Find/put the service component and the bundle in the context for the IN_BUNDLES view -----
        
        // Find the bundle context that should be containing the dual view on this service component.
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
        
        val serviceComponentContextInBundle = serviceComponentOverviewContext
            .childContexts.findFirst [
                return it.modelElement === serviceComponent
            ] as ServiceComponentContext
        ContextUtils.makeDetailed(serviceComponentOverviewContext, serviceComponentContextInBundle)
        
        // Add all connections for both views.
        serviceComponent.serviceInterfaces.forEach [ serviceInterface |
            val implementedServiceInterfaceContext = serviceInterfaceOverviewContext.detailedElements.findFirst [
                return modelElement === serviceInterface
            ] as ServiceInterfaceContext
            ContextUtils.addImplementingServiceComponentEdgePlain(implementedServiceInterfaceContext,
                serviceComponentContextPlain_)
            ContextUtils.addImplementingServiceComponentEdgeInBundle(implementedServiceInterfaceContext,
                serviceComponentContextInBundle)
        ]
    }
    
    private static def void revealInIndependentBundle(ServiceComponentContext serviceComponentContext,
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        val serviceComponent = serviceComponentContext.modelElement
        
        // Find/put the contexts of the implemented interfaces in the overview
        serviceComponent.serviceInterfaces.forEach [ serviceInterface |
            var serviceInterfaceContext = serviceComponentOverviewContext.implementedServiceInterfaceContexts.findFirst [
                modelElement === serviceInterface
            ]
            // Create a new context if it is not yet in the view
            if (serviceInterfaceContext === null) {
                serviceInterfaceContext = new ServiceInterfaceContext(serviceInterface, serviceComponentOverviewContext)
                serviceComponentOverviewContext.implementedServiceInterfaceContexts.add(serviceInterfaceContext)
            }
            // Add the edges for all implemented interfaces.
            ContextUtils.addImplementingServiceComponentEdgePlain(serviceInterfaceContext, serviceComponentContext)
        ]
    }
    
    private static def void revealInProduct(ServiceComponent serviceComponent, 
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        // The service interfaces that are not in the view need to be added first.
        serviceComponent.serviceInterfaces.forEach [ serviceInterface |
            var implementedServiceInterfaceContext = serviceComponentOverviewContext
                .implementedServiceInterfaceContexts.findFirst [
                    return modelElement === serviceInterface
                ]
            if (implementedServiceInterfaceContext === null) {
                serviceComponentOverviewContext.implementedServiceInterfaceContexts.add(
                    new ServiceInterfaceContext(serviceInterface, serviceComponentOverviewContext))
            }
        ]
        
        // ----- Find the service component in the context for the PLAIN view and expand it if necessary. -----
        val serviceComponentContextPlain = serviceComponentOverviewContext.childContexts
            .findFirst [ return modelElement === serviceComponent ]
        ContextUtils.makeDetailed(serviceComponentOverviewContext, serviceComponentContextPlain)
        
        // ----- Find/put the service component and the bundle in the context for the IN_BUNDLES view -----
        
        // Find the bundle context that should be containing the dual view on this service component.
        var referencedBundleContext = serviceComponentOverviewContext.referencedBundleContexts.findFirst [
            return it.modelElement === serviceComponent.bundle
        ]
        // Create a new bundle context for that bundle if it is not yet in the view.
        if (referencedBundleContext === null) {
            referencedBundleContext = new BundleContext(serviceComponent.bundle, serviceComponentOverviewContext)
            serviceComponentOverviewContext.referencedBundleContexts.add(referencedBundleContext)
        }
        val innerServiceComponentOverviewContext = referencedBundleContext.serviceComponentOverviewContext
        innerServiceComponentOverviewContext.expanded = true
        
        val serviceComponentContextInBundle = innerServiceComponentOverviewContext.childContexts.findFirst [
            return it.modelElement === serviceComponent
        ] as ServiceComponentContext
        ContextUtils.makeDetailed(innerServiceComponentOverviewContext, serviceComponentContextInBundle)
        
        // Add all connections for both views.
        serviceComponent.serviceInterfaces.forEach [ serviceInterface |
            val implementedServiceInterfaceContext = serviceComponentOverviewContext
                .implementedServiceInterfaceContexts.findFirst [
                    return modelElement === serviceInterface
                ] as ServiceInterfaceContext
            ContextUtils.addImplementingServiceComponentEdgePlain(implementedServiceInterfaceContext,
                serviceComponentContextPlain as ServiceComponentContext)
            ContextUtils.addImplementingServiceComponentEdgeInBundle(implementedServiceInterfaceContext,
                serviceComponentContextInBundle)
        ]
    }
    
}