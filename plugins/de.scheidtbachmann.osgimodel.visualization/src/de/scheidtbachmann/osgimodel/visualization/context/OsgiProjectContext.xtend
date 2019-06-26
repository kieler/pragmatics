package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.OsgiProject
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about the root project overview.
 * 
 * @author nre
 */
class OsgiProjectContext implements IVisualizationContext<OsgiProject> {
    
    /**
     * The context for the bundle overview.
     */
    @Accessors
    BundleOverviewContext bundleOverviewContext
    
    /**
     * The context for the product overview.
     */
    @Accessors
    ProductOverviewContext productOverviewContext
    
    /**
     * The context for the service interface overview.
     */
    @Accessors
    ServiceInterfaceOverviewContext serviceInterfaceOverviewContext
    
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
        return #[bundleOverviewContext, productOverviewContext, serviceInterfaceOverviewContext]
            as List<? extends IVisualizationContext<? extends EObject>>
    }
    
    override getModelElement() {
        return project
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent
    }
    
    override initializeChildVisualizationContexts() {
        productOverviewContext = new ProductOverviewContext(project.products, this)
        bundleOverviewContext = new BundleOverviewContext(project.bundles, this)
        serviceInterfaceOverviewContext = new ServiceInterfaceOverviewContext(project.serviceInterfaces, this)
    }
    
    override deepCopy() {
        val copy = new OsgiProjectContext
        copy.bundleOverviewContext = bundleOverviewContext.deepCopy as BundleOverviewContext
        copy.bundleOverviewContext.parentVisualizationContext = copy
        copy.productOverviewContext = productOverviewContext.deepCopy as ProductOverviewContext
        copy.productOverviewContext.parentVisualizationContext = copy
        copy.serviceInterfaceOverviewContext = serviceInterfaceOverviewContext.deepCopy as ServiceInterfaceOverviewContext
        copy.serviceInterfaceOverviewContext.parentVisualizationContext = copy
        copy.project = project
        
        return copy
    }
    
}