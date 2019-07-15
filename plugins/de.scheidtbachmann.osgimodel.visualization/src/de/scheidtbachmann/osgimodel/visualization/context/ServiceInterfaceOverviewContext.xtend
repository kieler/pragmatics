package de.scheidtbachmann.osgimodel.visualization.context

import com.google.common.collect.ImmutableList
import de.scheidtbachmann.osgimodel.ServiceInterface
import java.util.LinkedList
import java.util.List
import java.util.Map
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
     * All referenced bundles shown
     */
    @Accessors
    List<BundleContext> referencedBundleContexts
    
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
    
    /**
     * boolean value storing the current value for the {@link IOverviewVisualizationContext#isExpanded} method.
     */
    boolean expanded
    
    private new() {}
    
    new(List<ServiceInterface> serviceInterfaces, IVisualizationContext<?> parent) {
        this.parent = parent
        this.serviceInterfaces = serviceInterfaces
        detailedServiceInterfaceContexts = new LinkedList
        collapsedServiceInterfaceContexts = new LinkedList
        implementingServiceComponentContexts = new LinkedList
        referencedBundleContexts = new LinkedList
        implementedInterfaceEdges = new LinkedList
        expanded = false
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return ImmutableList.copyOf(detailedServiceInterfaceContexts + collapsedServiceInterfaceContexts) // TODO: + implementingServiceComponentContexts? referencedBundleContexts?
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
    
    override isExpanded() {
        return expanded
    }
    
    override setExpanded(boolean newExpanded) {
        this.expanded = newExpanded
    }
    
    override deepCopy(Map<IVisualizationContext<?>, IVisualizationContext<?>> seenContexts) {
        val alreadyCloned = seenContexts.get(this)
        if (alreadyCloned !== null) {
            return alreadyCloned as ServiceInterfaceOverviewContext
        }
        
        val copy = new ServiceInterfaceOverviewContext
        
        copy.detailedServiceInterfaceContexts = new LinkedList
        detailedServiceInterfaceContexts.forEach[
            val newServiceInterfaceContext = deepCopy(seenContexts) as ServiceInterfaceContext
            newServiceInterfaceContext.parentVisualizationContext = copy
            copy.detailedServiceInterfaceContexts.add(newServiceInterfaceContext)
        ]
        copy.collapsedServiceInterfaceContexts = new LinkedList
        collapsedServiceInterfaceContexts.forEach [
            val newServiceInterfaceContext = deepCopy(seenContexts) as ServiceInterfaceContext
            newServiceInterfaceContext.parentVisualizationContext = copy
            copy.collapsedServiceInterfaceContexts.add(newServiceInterfaceContext)
        ]
        copy.implementingServiceComponentContexts = new LinkedList
        implementingServiceComponentContexts.forEach [
            val newServiceComponentContext = deepCopy(seenContexts) as ServiceComponentContext
            newServiceComponentContext.parentVisualizationContext = copy
            copy.implementingServiceComponentContexts.add(newServiceComponentContext)
        ]
        copy.referencedBundleContexts = new LinkedList
        referencedBundleContexts.forEach [
            val newBundleContext = deepCopy(seenContexts) as BundleContext
            newBundleContext.parentVisualizationContext = copy
            copy.referencedBundleContexts.add(newBundleContext)
        ]
        copy.implementedInterfaceEdges = new LinkedList
        implementedInterfaceEdges.forEach[
            copy.implementedInterfaceEdges.add(key.deepCopy(seenContexts) as ServiceComponentContext
                -> value.deepCopy(seenContexts) as ServiceInterfaceContext)
        ]
        
        copy.serviceInterfaces = serviceInterfaces.clone
        
        copy.expanded = isExpanded
        
        seenContexts.put(this, copy)
        return copy
    }
    
}