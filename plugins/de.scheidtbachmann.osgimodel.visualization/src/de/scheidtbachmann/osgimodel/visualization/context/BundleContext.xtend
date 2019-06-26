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
    IOverviewVisualizationContext<Bundle> parent
    
    private new() {}
    
    new(Bundle bundle, IOverviewVisualizationContext<Bundle> parent) {
        this.parent = parent
        this.bundle = bundle
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return bundle
    }
    
    override IOverviewVisualizationContext<Bundle> getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<Bundle>
    }
    
    override initializeChildVisualizationContexts() {
        // Nothing to do yet.
    }
    
    override deepCopy() {
        val clone = new BundleContext
        clone.allRequiredBundlesShown = allRequiredBundlesShown
        clone.allRequiringBundlesShown = allRequiringBundlesShown
        clone.allUsedPackagesShown = allUsedPackagesShown
        clone.bundle = bundle
        clone.parent = null
        
        return clone
    }
    
}