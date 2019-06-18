package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Product
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link Product}s.
 */
@Accessors
class ProductContext implements IVisualizationContext {
    
    /**
     * The product to get the data from when visualizing this context.
     */
    Product product
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext parent
    
    /**
     * The context for the bundle overview shown in detailed products.
     */
    BundleOverviewContext bundleOverviewContext
    
    private new() {}
    
    new(Product product, IVisualizationContext parent) {
        this.parent = parent
        this.product = product
    }
    
    override getChildContexts() {
        return #[]
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
        copy.product = product
        
        return copy
    }
    
}