package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Product
import java.util.Map
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link Product}s.
 * 
 * @author nre
 */
class ProductContext implements IVisualizationContext<Product> {
    
    /**
     * The product to get the data from when visualizing this context.
     */
    Product product
    
    /**
     * The parent visualization context.
     */
    IOverviewVisualizationContext<Product> parent
    
    /**
     * The context for the bundle overview shown in detailed products.
     */
    @Accessors
    BundleOverviewContext bundleOverviewContext
    
    /**
     * The context for the service component overview shown in detailed products.
     */
    @Accessors
    ServiceComponentOverviewContext serviceComponentOverviewContext
    
    private new() {}
    
    new(Product product, IOverviewVisualizationContext<Product> parent) {
        this.parent = parent
        this.product = product
    }
    
    override getChildContexts() {
        return #[bundleOverviewContext, serviceComponentOverviewContext]
    }
    
    override getModelElement() {
       return product
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<Product>
    }
    
    override IOverviewVisualizationContext<Product> getParentVisualizationContext() {
        return parent
    }
    
    override initializeChildVisualizationContexts() {
        // Any bundle could be referenced in more than one feature, so make sure they are included only once.
        val allBundles = product.features.flatMap [ bundles ].toSet.toList
        bundleOverviewContext = new BundleOverviewContext(allBundles, this)
        val allServiceComponents = allBundles.flatMap [ serviceComponents ].toList
        serviceComponentOverviewContext = new ServiceComponentOverviewContext(allServiceComponents, this, true)
    }
    
    override deepCopy(Map<IVisualizationContext<?>, IVisualizationContext<?>> seenContexts) {
        val alreadyCloned = seenContexts.get(this)
        if (alreadyCloned !== null) {
            return alreadyCloned as ProductContext
        }
        
        val copy = new ProductContext
        if (bundleOverviewContext !== null) {
            copy.bundleOverviewContext = bundleOverviewContext.deepCopy(seenContexts) as BundleOverviewContext
            copy.bundleOverviewContext.parentVisualizationContext = copy
        }
        if (serviceComponentOverviewContext !== null) {
            copy.serviceComponentOverviewContext = serviceComponentOverviewContext.deepCopy(seenContexts)
                as ServiceComponentOverviewContext
            copy.serviceComponentOverviewContext.parentVisualizationContext = copy
        }
        copy.product = product
        
        seenContexts.put(this, copy)
        return copy
    }
    
}