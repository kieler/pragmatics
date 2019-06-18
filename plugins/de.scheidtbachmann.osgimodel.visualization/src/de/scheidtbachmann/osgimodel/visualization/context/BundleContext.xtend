package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import org.eclipse.xtend.lib.annotations.Accessors
import de.scheidtbachmann.osgimodel.Bundle

/**
 * Context for the OSGi synthesis that contains information about {@link Bundle}s.
 */
@Accessors
class BundleContext implements IVisualizationContext {
    /**
     * Indicates if all required bundles are shown in this context.
     */
    boolean allRequiredBundlesShown
    
    /**
     * Indicates if all bundles that require this bundle are shown in this context.
     */
    boolean allRequiringBundlesShown
    
    /**
     * The bundle to get the data from when visualizing this context.
     */
    Bundle bundle
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext parent
    
    private new() {}
    
    new(Bundle bundle, IVisualizationContext parent) {
        this.parent = parent
        this.bundle = bundle
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return bundle
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override initializeChildVisualizationContexts() {
        // Nothing to do yet.
    }
    
    override deepCopy() {
        val clone = new BundleContext
        clone.allRequiredBundlesShown = allRequiredBundlesShown
        clone.allRequiringBundlesShown = allRequiringBundlesShown
        clone.bundle = bundle
        clone.parent = null
        
        return clone
    }
    
}