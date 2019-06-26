package de.scheidtbachmann.osgimodel.visualization.context

import com.google.common.collect.ImmutableList
import de.scheidtbachmann.osgimodel.ServiceInterface
import java.util.LinkedList
import java.util.List

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
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return ImmutableList.copyOf(detailedServiceInterfaceContexts + collapsedServiceInterfaceContexts)
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
        copy.detailedServiceInterfaceContexts = new LinkedList
        detailedServiceInterfaceContexts.forEach[
            val newServiceInterfaceContext = deepCopy as ServiceInterfaceContext
            newServiceInterfaceContext.parentVisualizationContext = copy
            copy.detailedServiceInterfaceContexts.add(newServiceInterfaceContext)
        ]
        copy.collapsedServiceInterfaceContexts = new LinkedList
        collapsedServiceInterfaceContexts.forEach [
            val newServiceInterfaceContext = deepCopy as ServiceInterfaceContext
            newServiceInterfaceContext.parentVisualizationContext = copy
            copy.collapsedServiceInterfaceContexts.add(newServiceInterfaceContext)
        ]
        copy.serviceInterfaces = serviceInterfaces.clone
        
        return copy
    }
    
}