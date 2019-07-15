package de.scheidtbachmann.osgimodel.visualization.context

import com.google.common.collect.ImmutableList
import de.scheidtbachmann.osgimodel.ServiceComponent
import java.util.LinkedList
import java.util.List

/**
 * Context for the OSGi synthesis that contains information about {@link ServiceComponent} overviews.
 * 
 * @author nre
 */
class ServiceComponentOverviewContext implements IOverviewVisualizationContext<ServiceComponent> {
    
    /**
     * The service component contexts for all service components in their collapsed form.
     */
    List<ServiceComponentContext> collapsedServiceComponentContexts
    
    /**
     * The service component contexts for all service components in their detailed form.
     */
    List<ServiceComponentContext> detailedServiceComponentContexts
    
    /**
     * The service components displayed in this context.
     */
    List<ServiceComponent> serviceComponents
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext<?> parent
    
    /**
     * boolean value storing the current value for the {@link IOverviewVisualizationContext#isExpanded} method.
     */
    boolean expanded
    
    private new() {}
    
    new(List<ServiceComponent> serviceComponents, IVisualizationContext<?> parent) {
        this.parent = parent
        this.serviceComponents = serviceComponents
        collapsedServiceComponentContexts = new LinkedList
        detailedServiceComponentContexts = new LinkedList
        expanded = false
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return ImmutableList.copyOf(detailedServiceComponentContexts + collapsedServiceComponentContexts)
    }
    
    override getModelElement() {
        return serviceComponents
    }
    
    override getDetailedElements() {
        return detailedServiceComponentContexts
    }
    
    override getCollapsedElements() {
        return collapsedServiceComponentContexts
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent
    }
    
    override initializeChildVisualizationContexts() {
        serviceComponents.forEach [
            collapsedServiceComponentContexts += new ServiceComponentContext(it, this)
        ]
    }
    
    override isExpanded() {
        return expanded
    }
    
    override setExpanded(boolean newExpanded) {
        this.expanded = newExpanded
    }
    
    override deepCopy() {
        val copy = new ServiceComponentOverviewContext
        copy.collapsedServiceComponentContexts = new LinkedList
        collapsedServiceComponentContexts.forEach [
            val newServiceComponentContext = deepCopy as ServiceComponentContext
            newServiceComponentContext.parentVisualizationContext = copy
            copy.collapsedServiceComponentContexts.add(newServiceComponentContext)
        ]
        copy.detailedServiceComponentContexts = new LinkedList
        detailedServiceComponentContexts.forEach [
            val newServiceComponentContext = deepCopy as ServiceComponentContext
            newServiceComponentContext.parentVisualizationContext = copy
            copy.detailedServiceComponentContexts.add(newServiceComponentContext)
        ]
        
        copy.serviceComponents = serviceComponents.clone
        
        copy.expanded = isExpanded
        
        return copy
    }
    
}