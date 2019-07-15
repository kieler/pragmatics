package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Bundle
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link Bundle}s.
 * 
 * @author nre
 */
class BundleContext implements IVisualizationContext<Bundle> {
    /**
     * Indicates if all required bundles and their connections are shown in this parent's context.
     */
    @Accessors
    boolean allRequiredBundlesShown
    
    /**
     * Indicates if all bundles that require this bundle and their connections are shown in this parent's context.
     */
    @Accessors
    boolean allRequiringBundlesShown
    
    /**
     * Indicated if all used packages resp. the bundles providing the packages and their connections are shown in this
     * parent's context.
     */
    @Accessors
    boolean allUsedPackagesShown
    
    /**
     * The bundle to get the data from when visualizing this context.
     */
    Bundle bundle
    
    /**
     * The parent visualization context.
     */
    IOverviewVisualizationContext<?> parent
    
    /**
     * The context for the service component overview shown in detailed bundles.
     */
    @Accessors
    ServiceComponentOverviewContext serviceComponentOverviewContext
    
    private new() {}
    
    new(Bundle bundle, IOverviewVisualizationContext<?> parent) {
        this.parent = parent
        this.bundle = bundle
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return #[serviceComponentOverviewContext]
    }
    
    override getModelElement() {
       return bundle
    }
    
    override IOverviewVisualizationContext<?> getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<Bundle>
    }
    
    override initializeChildVisualizationContexts() {
        serviceComponentOverviewContext = new ServiceComponentOverviewContext(bundle.serviceComponents, this)
    }
    
    override deepCopy() {
        val clone = new BundleContext
        if (serviceComponentOverviewContext !== null) {
            clone.serviceComponentOverviewContext = serviceComponentOverviewContext.deepCopy
                as ServiceComponentOverviewContext
            clone.serviceComponentOverviewContext.parentVisualizationContext = clone
        }
        clone.allRequiredBundlesShown = allRequiredBundlesShown
        clone.allRequiringBundlesShown = allRequiringBundlesShown
        clone.allUsedPackagesShown = allUsedPackagesShown
        clone.bundle = bundle
        clone.parent = null
        
        return clone
    }
    
}