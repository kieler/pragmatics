package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductOverviewContext

import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all products in the given list of products.
 * 
 * @author nre
 */
class ProductOverviewSynthesis extends AbstractSubSynthesis<ProductOverviewContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleProductSynthesis simpleProductSynthesis
    @Inject ProductSynthesis productSynthesis
    
    override transform(ProductOverviewContext productOverviewContext) {
        return #[
            createNode => [
                associateWith(productOverviewContext)
                configureBoxLayout
                addOverviewRendering("Products")
                
                val filteredCollapsedProducts = SynthesisUtils.filteredBasicOsgiObjectContexts(
                    productOverviewContext.collapsedElements, usedContext)
                children += filteredCollapsedProducts.flatMap[
                    return simpleProductSynthesis.transform(it as ProductContext)
                ]
                val filteredDetailedProducts = SynthesisUtils.filteredBasicOsgiObjectContexts(
                    productOverviewContext.detailedElements, usedContext)
                children += filteredDetailedProducts.flatMap[
                    return productSynthesis.transform(it as ProductContext)
                ]
            ]
        ]
    }
}