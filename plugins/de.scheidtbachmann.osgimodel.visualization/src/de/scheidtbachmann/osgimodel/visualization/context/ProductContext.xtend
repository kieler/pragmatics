package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Product
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link Product}s.
 * 
 * @author nre
 */
@Accessors
class ProductContext implements IVisualizationContext<Product> {
    
    /**
     * The product to get the data from when visualizing this context.
     */
    Product product
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext<List<Product>> parent
    
    /**
     * The context for the bundle overview shown in detailed products.
     */
    BundleOverviewContext bundleOverviewContext
    
    private new() {}
    
    new(Product product, IVisualizationContext<List<Product>> parent) {
        this.parent = parent
        this.product = product
    }
    
    override getChildContexts() {
        return #[bundleOverviewContext]
    }
    
    override getModelElement() {
       return product
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override initializeChildVisualizationContexts() {
        bundleOverviewContext = new BundleOverviewContext(product.features.flatMap[bundles].toSet.toList, this)
    }
    
    override deepCopy() {
        val copy = new ProductContext
        if (bundleOverviewContext !== null) {
            copy.bundleOverviewContext = bundleOverviewContext.deepCopy as BundleOverviewContext
            copy.bundleOverviewContext.parent = copy
        }
        copy.product = product
        
        return copy
    }
    
}