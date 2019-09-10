package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.ServiceComponent
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext

/**
 * Puts the service interfaces referenced by this service component next to this service component and connects them 
 * with an edge from this service component's 'referencedServiceComponents' port to the new service interface.
 * 
 * @author nre
 */
class RevealReferencedServiceInterfacesAction extends AbstractRevealServiceInterfacesAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = RevealReferencedServiceInterfacesAction.name
    
    override protected void revealInInterfaceOverview(ServiceComponent serviceComponent, 
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        // The service interfaces that are yet collapsed need to be expanded first.
        serviceComponent.reference.forEach [ reference |
            val serviceInterface = reference.serviceInterface
            var referencedServiceInterfaceContext = serviceInterfaceOverviewContext.collapsedElements.findFirst [
                return modelElement === serviceInterface
            ]
            if (referencedServiceInterfaceContext !== null) {
                ContextUtils.makeDetailed(serviceInterfaceOverviewContext, referencedServiceInterfaceContext)
            }
        ]
        
        // ----- Find/put the service component in the context for the PLAIN view ----
        var serviceComponentContextPlain = serviceInterfaceOverviewContext
            .implementingOrReferencingServiceComponentContexts.findFirst [
            return it.modelElement === serviceComponent
        ]
        if (serviceComponentContextPlain === null) {
            serviceComponentContextPlain = new ServiceComponentContext(serviceComponent,
                serviceInterfaceOverviewContext)
            serviceInterfaceOverviewContext.implementingOrReferencingServiceComponentContexts
                .add(serviceComponentContextPlain)
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
        
        val serviceComponentContextInBundle = serviceComponentOverviewContext.childContexts.findFirst [
            return it.modelElement === serviceComponent
        ] as ServiceComponentContext
        ContextUtils.makeDetailed(serviceComponentOverviewContext, serviceComponentContextInBundle)
        
        // Add all connections for both views.
        serviceComponent.reference.forEach [ reference |
            val serviceInterface = reference.serviceInterface
            val referencedServiceInterfaceContext = serviceInterfaceOverviewContext.detailedElements.findFirst [
                return modelElement === serviceInterface
            ] as ServiceInterfaceContext
            ContextUtils.addReferencedServiceInterfaceEdgePlain(serviceComponentContextPlain_,
                referencedServiceInterfaceContext, reference)
            ContextUtils.addReferencedServiceInterfaceEdgeInBundle(serviceComponentContextInBundle,
                referencedServiceInterfaceContext, reference)
        ]
    }
    
    override protected void revealInIndependentBundle(ServiceComponentContext serviceComponentContext,
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        val serviceComponent = serviceComponentContext.modelElement
        
        // Find/put the contexts of the referenced interfaces in the overview.
        serviceComponent.reference.forEach [ reference |
            val serviceInterface = reference.serviceInterface
            var serviceInterfaceContext = serviceComponentOverviewContext
                .implementedOrReferencedServiceInterfaceContexts.findFirst [
                modelElement === serviceInterface
            ]
            // Create a new context if it is not yet in the view.
            if (serviceInterfaceContext === null) {
                serviceInterfaceContext = new ServiceInterfaceContext(serviceInterface, serviceComponentOverviewContext)
                serviceComponentOverviewContext.implementedOrReferencedServiceInterfaceContexts
                    .add(serviceInterfaceContext)
            }
            // Add the edges for all referenced interfaces.
            ContextUtils.addReferencedServiceInterfaceEdgePlain(serviceComponentContext, serviceInterfaceContext,
                reference)
        ]
    }
    
    override protected void revealInProduct(ServiceComponent serviceComponent, 
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        // The service interfaces that are not in the view need to be added first.
        serviceComponent.reference.forEach [ reference |
            val serviceInterface = reference.serviceInterface
            var referencedServiceInterfaceContext = serviceComponentOverviewContext
                .implementedOrReferencedServiceInterfaceContexts.findFirst [
                return modelElement === serviceInterface
            ]
            if (referencedServiceInterfaceContext === null) {
                serviceComponentOverviewContext.implementedOrReferencedServiceInterfaceContexts.add(
                    new ServiceInterfaceContext(serviceInterface, serviceComponentOverviewContext))
            }
        ]
        
        // ----- Find the service component in the context for the PLAIN view and expand it if necessary. -----
        val serviceComponentContextPlain = serviceComponentOverviewContext.childContexts
            .findFirst [ return modelElement === serviceComponent ] as IVisualizationContext<ServiceComponent>
        ContextUtils.makeDetailed(serviceComponentOverviewContext, serviceComponentContextPlain)
        
        // ----- Find/put the service component and the bundle in the context for the IN_BUNDLES view. -----
        
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
        serviceComponent.reference.forEach [ reference |
            val serviceInterface = reference.serviceInterface
            val referencedServiceInterfaceContext = serviceComponentOverviewContext
                .implementedOrReferencedServiceInterfaceContexts.findFirst [
                return modelElement === serviceInterface
            ] as ServiceInterfaceContext
            ContextUtils.addReferencedServiceInterfaceEdgePlain(serviceComponentContextPlain as ServiceComponentContext,
                referencedServiceInterfaceContext, reference)
            ContextUtils.addReferencedServiceInterfaceEdgeInBundle(serviceComponentContextInBundle,
                referencedServiceInterfaceContext, reference)
        ]
    }
    
}