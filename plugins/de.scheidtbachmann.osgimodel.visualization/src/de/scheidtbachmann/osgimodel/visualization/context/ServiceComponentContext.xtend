package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.ServiceComponent
import java.util.Map

/**
 * Context for the OSGi synthesis that contains information about {@link ServiceComponent}s.
 * 
 * @author nre
 */
class ServiceComponentContext implements IVisualizationContext<ServiceComponent> {
    
    /**
     * The service component to get the data from when visualizing this context.
     */
    ServiceComponent serviceComponent
    
    /**
     * The parent visualization context.
     */
    IOverviewVisualizationContext<?> parent
    
    private new() {}
    
    new(ServiceComponent serviceComponent, IOverviewVisualizationContext<?> parent) {
        this.parent = parent
        this.serviceComponent = serviceComponent
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return serviceComponent
    }
    
    override IOverviewVisualizationContext<?> getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<ServiceComponent>
    }
    
    override initializeChildVisualizationContexts() {
        // Nothing to do yet.
    }
    
    override deepCopy(Map<IVisualizationContext<?>, IVisualizationContext<?>> seenContexts) {
        val alreadyCloned = seenContexts.get(this)
        if (alreadyCloned !== null) {
            return alreadyCloned as ServiceComponentContext
        }
        
        val copy = new ServiceComponentContext
        copy.serviceComponent = serviceComponent
        copy.parent = null
        
        seenContexts.put(this, copy)
        return copy
    }
    
}