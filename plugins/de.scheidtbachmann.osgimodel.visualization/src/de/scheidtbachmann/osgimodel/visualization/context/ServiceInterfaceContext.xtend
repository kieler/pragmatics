package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.ServiceInterface

/**
 * Context for the OSGi synthesis that contains information about {@link ServiceInterface}s.
 * 
 * @author nre
 */
class ServiceInterfaceContext implements IVisualizationContext<ServiceInterface> {
    
    /**
     * The service interface to get the data from when visualizing this context.
     */
    ServiceInterface serviceInterface
    
    /**
     * The parent visualization context.
     */
    IOverviewVisualizationContext<ServiceInterface> parent
    
    private new() {}
    
    new(ServiceInterface serviceInterface, IOverviewVisualizationContext<ServiceInterface> parent) {
        this.parent = parent
        this.serviceInterface = serviceInterface
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return serviceInterface
    }
    
    override IOverviewVisualizationContext<ServiceInterface> getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<ServiceInterface>
    }
    
    override initializeChildVisualizationContexts() {
        // Nothing to do yet.
    }
    
    override deepCopy() {
        val copy = new ServiceInterfaceContext
        copy.serviceInterface = serviceInterface
        
        return copy
    }
    
}