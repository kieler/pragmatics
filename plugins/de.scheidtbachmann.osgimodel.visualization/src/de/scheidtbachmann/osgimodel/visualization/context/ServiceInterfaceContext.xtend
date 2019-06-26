package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.ServiceInterface
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link ServiceInterface}s.
 * 
 * @author nre
 */
@Accessors
class ServiceInterfaceContext implements IVisualizationContext<ServiceInterface> {
    
    /**
     * The service interface to get the data from when visualizing this context.
     */
    ServiceInterface serviceInterface
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext<List<ServiceInterface>> parent
    
    private new() {}
    
    new(ServiceInterface serviceInterface, IVisualizationContext<List<ServiceInterface>> parent) {
        this.parent = parent
        this.serviceInterface = serviceInterface
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return serviceInterface
    }
    
    override getParentVisualizationContext() {
        return parent
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