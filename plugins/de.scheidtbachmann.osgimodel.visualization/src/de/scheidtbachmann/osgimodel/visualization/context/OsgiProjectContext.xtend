package de.scheidtbachmann.osgimodel.visualization.context

import org.eclipse.xtend.lib.annotations.Accessors
import de.scheidtbachmann.osgimodel.OsgiProject

/**
 * Context for the OSGi synthesis that contains information about the root project overview.
 * 
 * @author nre
 */
@Accessors
class OsgiProjectContext implements IVisualizationContext<OsgiProject> {
    
    /**
     * The context for the bundle overview.
     */
    BundleOverviewContext bundleOverviewContext
    
    /**
     * The context for the product overview.
     */
    ProductOverviewContext productOverviewContext
    
    /**
     * The OSGi project model to get the data from when visualizing this context.
     */
    OsgiProject project
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext<?> parent
    
    private new() {}
    
    new(OsgiProject project, IVisualizationContext<?> parent) {
        this.parent = parent
        this.project = project
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return #[bundleOverviewContext, productOverviewContext]
    }
    
    override getModelElement() {
        return project
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override initializeChildVisualizationContexts() {
        productOverviewContext = new ProductOverviewContext(project.products, this)
        bundleOverviewContext = new BundleOverviewContext(project.bundles, this)
    }
    
    override deepCopy() {
        val copy = new OsgiProjectContext
        copy.bundleOverviewContext = bundleOverviewContext.deepCopy as BundleOverviewContext
        copy.bundleOverviewContext.parent = copy
        copy.productOverviewContext = productOverviewContext.deepCopy as ProductOverviewContext
        copy.productOverviewContext.parent = copy
        copy.project = project
        
        return copy
    }
    
}