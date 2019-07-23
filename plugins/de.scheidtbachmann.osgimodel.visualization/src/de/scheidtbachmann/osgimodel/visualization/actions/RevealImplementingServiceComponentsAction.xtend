package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
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
 * Puts the service components implementing this service interface next to this service interface and connects them with
 * an edge from the new service component's 'implementedServiceInterfaces' to this service interface's
 * 'implementingServiceComponents' port.
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
        
        // The overview context this service interface is shown in.
        val overviewContext = serviceInterfaceContext.parentVisualizationContext
            as IOverviewVisualizationContext<?>
        
        // Handle this action differently, depending on in which overview context stack this is contained in.
        if (overviewContext instanceof ServiceInterfaceOverviewContext) {
            // Interface in its default interface overview. Component to that may be in PLAIN or IN_BUNDLES view.
            revealInInterfaceOverview(serviceInterfaceContext, overviewContext as ServiceInterfaceOverviewContext)
            
        } else if (overviewContext instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext instanceof BundleContext) {
            // Interface defined inside an independent bundle context. Only available as a plain view.
            revealInIndependentBundle(serviceInterfaceContext, overviewContext as ServiceComponentOverviewContext)
            
        } else if (overviewContext instanceof ServiceComponentOverviewContext
            && overviewContext.parentVisualizationContext instanceof ProductContext) {
            // Interface defined inside a product. Component to that may be in PLAIN or IN_BUNDLES view.
            revealInProduct(serviceInterfaceContext, overviewContext as ServiceComponentOverviewContext, actionContext)
        }
        
        return null
    }
    
    private static def void revealInInterfaceOverview(ServiceInterfaceContext serviceInterfaceContext,
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        val serviceInterface = serviceInterfaceContext.modelElement
        // ----- Put the service components in the context for the PLAIN view -----
        
        // The service components that are not already represented in the overview need to be put in first.
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
            ContextUtils.addImplementingServiceComponentEdgePlain(serviceInterfaceContext,
                implementingServiceComponentContext)
        ]
        
        // ----- Put the service components and the bundles in the context for the IN_BUNDLES view -----
        
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
            ContextUtils.addImplementingServiceComponentEdgeInBundle(serviceInterfaceContext,
                implementingServiceComponentContext as ServiceComponentContext)
        ]
    }
    
    private static def void revealInIndependentBundle(ServiceInterfaceContext serviceInterfaceContext,
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        val serviceInterface = serviceInterfaceContext.modelElement
        
        // Expand the contexts of the implementing components in the overview
        serviceInterface.serviceComponent.forEach [ serviceComponent |
            // First, make it detailed if it is not already.
            val collapsedServiceComponentContext = serviceComponentOverviewContext.collapsedElements.findFirst [
                modelElement === serviceComponent
            ]
            if (collapsedServiceComponentContext !== null) {
                ContextUtils.makeDetailed(serviceComponentOverviewContext, collapsedServiceComponentContext)
            }
            // This is the context of the now detailed component.
            val serviceComponentContext = serviceComponentOverviewContext.detailedElements.findFirst [
                modelElement === serviceComponent
            ] as ServiceComponentContext
            // If the context is null, the component is not defined by this bundle and should therefore not be shown.
            if (serviceComponentContext !== null) {
                ContextUtils.addImplementingServiceComponentEdgePlain(serviceInterfaceContext, serviceComponentContext)
            }
        ]
    }
    
    private static def void revealInProduct(ServiceInterfaceContext serviceInterfaceContext,
        ServiceComponentOverviewContext serviceComponentOverviewContext, ActionContext actionContext) {
        val serviceInterface = serviceInterfaceContext.modelElement
        val filteredServiceComponents = SynthesisUtils.filteredServiceComponents(serviceInterface.serviceComponent,
            serviceComponentOverviewContext)
        
        filteredServiceComponents.forEach [ serviceComponent |
            // ----- Expand the service components in the context for the PLAIN view -----
            var implementingServiceComponentContextPlain = serviceComponentOverviewContext
                .childContexts.findFirst [
                    return it.modelElement === serviceComponent
                ]
            ContextUtils.makeDetailed(serviceComponentOverviewContext, implementingServiceComponentContextPlain)
            ContextUtils.addImplementingServiceComponentEdgePlain(serviceInterfaceContext,
                implementingServiceComponentContextPlain as ServiceComponentContext)
        
            // ----- Put the service components and the bundles in the context for the IN_BUNDLES view -----
        
            // Find the bundle context that should be containing a view on this service component.
            var referencedBundleContext = serviceComponentOverviewContext.referencedBundleContexts.findFirst [
                return it.modelElement === serviceComponent.bundle
            ]
            // Create a new bundle context for that bundle if it is not yet in the view.
            if (referencedBundleContext === null) {
                referencedBundleContext = new BundleContext(serviceComponent.bundle, serviceComponentOverviewContext)
                serviceComponentOverviewContext.referencedBundleContexts.add(referencedBundleContext)
            }
            
            val innerServiceComponentOverviewContext = referencedBundleContext.serviceComponentOverviewContext
            if (!innerServiceComponentOverviewContext.expanded) {
                innerServiceComponentOverviewContext.expanded = true
                val nodes = actionContext.activeViewer.viewContext.getTargetElements(innerServiceComponentOverviewContext)
                // TODO: search the component node relevant in this context.
                // FIXME: The tracer is broken for this kind of synthesis. Only if that is fixed this manual expansion
                // will work.
                actionContext.activeViewer.toggleExpansion(nodes.head)
//                actionContext.KNode.setProperty(KlighdProperties.EXPAND, true)
            }
            var implementingServiceComponentContextInBundles = innerServiceComponentOverviewContext
                .childContexts.findFirst [
                    return it.modelElement === serviceComponent
                ]
            // Create a new service component context for the component if it is not already in the bundle view.
            if (implementingServiceComponentContextInBundles === null) {
                throw new IllegalStateException("The bundle context does not contain all its service components as "
                    + "own contexts!")
            }
            ContextUtils.makeDetailed(innerServiceComponentOverviewContext,
                implementingServiceComponentContextInBundles)
            ContextUtils.addImplementingServiceComponentEdgeInBundle(serviceInterfaceContext,
                implementingServiceComponentContextInBundles as ServiceComponentContext)
        ]
    }
    
}