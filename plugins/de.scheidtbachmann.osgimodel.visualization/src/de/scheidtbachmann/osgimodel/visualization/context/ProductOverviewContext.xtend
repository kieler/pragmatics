package de.scheidtbachmann.osgimodel.visualization.context

import com.google.common.collect.ImmutableList
import de.scheidtbachmann.osgimodel.Product
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.LinkedList

/**
 * Context for the OSGi synthesis that contains information about {@link Product} overviews.
 * 
 * @author nre
 */
@Accessors
class ProductOverviewContext implements IOverviewVisualizationContext<Product> {
    
    /**
     * All products that should be drawn in their detailed form.
     */
    List<ProductContext> detailedProductContexts
    
    /**
     * All products that should be drawn in their collapsed, non-detailed form.
     */
    List<ProductContext> collapsedProductContexts
    
    /**
     * The products shown in this overview.
     */
    List<Product> products
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext<?> parent
    
    private new() {}
    
    new(List<Product> products, IVisualizationContext<?> parent) {
        this.parent = parent
        this.products = products
        detailedProductContexts = new LinkedList
        collapsedProductContexts = new LinkedList
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return ImmutableList.copyOf(detailedProductContexts + collapsedProductContexts)
    }
    
    override getModelElement() {
        return ImmutableList.copyOf(detailedProductContexts.map[product] + collapsedProductContexts.map[product])
    }
    
    override getDetailedElements() {
        return detailedProductContexts
    }
    
    override getCollapsedElements() {
        return collapsedProductContexts
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override initializeChildVisualizationContexts() {
        products.forEach[
            collapsedProductContexts += new ProductContext(it, this)
        ]
    }
    
    override deepCopy() {
        val copy = new ProductOverviewContext
        copy.detailedProductContexts = new LinkedList
        detailedProductContexts.forEach[
            val newProductContext = deepCopy as ProductContext
            newProductContext.parent = copy
            copy.detailedProductContexts.add(newProductContext)
        ]
        copy.collapsedProductContexts = new LinkedList
        collapsedProductContexts.forEach [
            val newProductContext = deepCopy as ProductContext
            newProductContext.parent = copy
            copy.collapsedProductContexts.add(newProductContext)
        ]
        copy.products = products.clone
        
        return copy
    }
    
}