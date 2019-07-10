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
     * The context for the feature overview.
     */
    @Accessors
    FeatureOverviewContext featureOverviewContext
    
    /**
     * The context for the imported package overview.
     */
    @Accessors
    PackageObjectOverviewContext importedPackageOverviewContext
    
    /**
     * The context for the bundle category overview.
     */
    @Accessors
    BundleCategoryOverviewContext bundleCategoryOverviewContext
    
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
        return #[bundleOverviewContext, productOverviewContext, serviceInterfaceOverviewContext, featureOverviewContext,
            importedPackageOverviewContext, bundleCategoryOverviewContext]
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
        productOverviewContext.expanded = true
        bundleOverviewContext = new BundleOverviewContext(project.bundles, this)
        serviceInterfaceOverviewContext = new ServiceInterfaceOverviewContext(project.serviceInterfaces, this)
        featureOverviewContext = new FeatureOverviewContext(project.features, this)
        importedPackageOverviewContext = new PackageObjectOverviewContext(project.importedPackages, this)
        bundleCategoryOverviewContext = new BundleCategoryOverviewContext(project.bundleCategories, this)
    }
    
    override deepCopy() {
        val copy = new OsgiProjectContext
        copy.bundleOverviewContext = bundleOverviewContext.deepCopy as BundleOverviewContext
        copy.bundleOverviewContext.parentVisualizationContext = copy
        copy.productOverviewContext = productOverviewContext.deepCopy as ProductOverviewContext
        copy.productOverviewContext.parentVisualizationContext = copy
        copy.serviceInterfaceOverviewContext = serviceInterfaceOverviewContext.deepCopy
            as ServiceInterfaceOverviewContext
        copy.serviceInterfaceOverviewContext.parentVisualizationContext = copy
        copy.featureOverviewContext = featureOverviewContext.deepCopy as FeatureOverviewContext
        copy.featureOverviewContext.parentVisualizationContext = copy
        copy.importedPackageOverviewContext = importedPackageOverviewContext.deepCopy as PackageObjectOverviewContext
        copy.importedPackageOverviewContext.parentVisualizationContext = copy
        copy.bundleCategoryOverviewContext = bundleCategoryOverviewContext.deepCopy as BundleCategoryOverviewContext
        copy.bundleCategoryOverviewContext.parentVisualizationContext = copy
        
        
        copy.project = project
        
        return copy
    }
    
}