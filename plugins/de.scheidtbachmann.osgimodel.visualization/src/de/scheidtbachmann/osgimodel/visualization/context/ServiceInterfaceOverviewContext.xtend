package de.scheidtbachmann.osgimodel.visualization.context

import com.google.common.collect.ImmutableList
import de.scheidtbachmann.osgimodel.ServiceInterface
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link ServiceInterface} overviews.
 * 
 * @author nre
 */
class ServiceInterfaceOverviewContext implements IOverviewVisualizationContext<ServiceInterface> {
    
    /**
     * All service interfaces that should be drawn in their detailed form.
     */
    List<ServiceInterfaceContext> detailedServiceInterfaceContexts
    
    /**
     * All service interfaces that should be drawn in their collapsed, non-detailed form.
     */
    List<ServiceInterfaceContext> collapsedServiceInterfaceContexts
    
    /**
     * All service components implementing some service interface in this overview that are added later by actions to 
     * this context.
     */
    @Accessors
    List<ServiceComponentContext> implementingServiceComponentContexts
    
    /**
     * All connections for the implementing service components that should be drawn.
     * The pairs should be viewed that the first component implements the second interface.
     */
    @Accessors
    List<Pair<ServiceComponentContext, ServiceInterfaceContext>> implementedInterfaceEdges
    
    /**
     * The service interfaces shown in this overview.
     */
    List<ServiceInterface> serviceInterfaces
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext<?> parent
    
    private new() {}
    
    new(List<ServiceInterface> serviceInterfaces, IVisualizationContext<?> parent) {
        this.parent = parent
        this.serviceInterfaces = serviceInterfaces
        detailedServiceInterfaceContexts = new LinkedList
        collapsedServiceInterfaceContexts = new LinkedList
        implementingServiceComponentContexts = new LinkedList
        implementedInterfaceEdges = new LinkedList
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return ImmutableList.copyOf(detailedServiceInterfaceContexts + collapsedServiceInterfaceContexts) // TODO: + implementingServiceComponentContexts?
    }
    
    override getModelElement() {
        return serviceInterfaces
    }
    
    override getDetailedElements() {
        return detailedServiceInterfaceContexts
    }
    
    override getCollapsedElements() {
        return collapsedServiceInterfaceContexts
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent
    }
    
    override initializeChildVisualizationContexts() {
        serviceInterfaces.forEach[
            collapsedServiceInterfaceContexts += new ServiceInterfaceContext(it, this)
        ]
    }
    
    override deepCopy() {
        val copy = new ServiceInterfaceOverviewContext
        // Remember the cloned service interface / -component contexts as they are used multiple times.
        val clonedServiceComponentContexts = new HashMap<ServiceComponentContext, ServiceComponentContext>
        val clonedServiceInterfaceContexts = new HashMap<ServiceInterfaceContext, ServiceInterfaceContext>
        
        copy.detailedServiceInterfaceContexts = new LinkedList
        detailedServiceInterfaceContexts.forEach[
            val newServiceInterfaceContext = deepCopy as ServiceInterfaceContext
            clonedServiceInterfaceContexts.put(it, newServiceInterfaceContext)
            newServiceInterfaceContext.parentVisualizationContext = copy
            copy.detailedServiceInterfaceContexts.add(newServiceInterfaceContext)
        ]
        copy.collapsedServiceInterfaceContexts = new LinkedList
        collapsedServiceInterfaceContexts.forEach [
            val newServiceInterfaceContext = deepCopy as ServiceInterfaceContext
            clonedServiceInterfaceContexts.put(it, newServiceInterfaceContext)
            newServiceInterfaceContext.parentVisualizationContext = copy
            copy.collapsedServiceInterfaceContexts.add(newServiceInterfaceContext)
        ]
        copy.implementingServiceComponentContexts = new LinkedList
        implementingServiceComponentContexts.forEach [
            val newServiceComponentContext = deepCopy as ServiceComponentContext
            clonedServiceComponentContexts.put(it, newServiceComponentContext)
            newServiceComponentContext.parentVisualizationContext = copy
            copy.implementingServiceComponentContexts.add(newServiceComponentContext)
        ]
        copy.implementedInterfaceEdges = new LinkedList
        implementedInterfaceEdges.forEach[
            copy.implementedInterfaceEdges.add(clonedServiceComponentContexts.get(key) -> clonedServiceInterfaceContexts.get(value))
        ]
        
        copy.serviceInterfaces = serviceInterfaces.clone
        
        return copy
    }
    
}