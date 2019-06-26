package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Product
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
    
    private new() {}
    
    new(Product product, IOverviewVisualizationContext<Product> parent) {
        this.parent = parent
        this.product = product
    }
    
    override getChildContexts() {
        return #[bundleOverviewContext]
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
        bundleOverviewContext = new BundleOverviewContext(product.features.flatMap[bundles].toSet.toList, this)
    }
    
    override deepCopy() {
        val copy = new ProductContext
        if (bundleOverviewContext !== null) {
            copy.bundleOverviewContext = bundleOverviewContext.deepCopy as BundleOverviewContext
            copy.bundleOverviewContext.parentVisualizationContext = copy
        }
        copy.product = product
        
        return copy
    }
    
}