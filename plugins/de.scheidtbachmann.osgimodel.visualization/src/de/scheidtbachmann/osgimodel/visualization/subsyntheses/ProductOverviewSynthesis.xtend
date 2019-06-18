package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ProductOverviewContext

import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all products in the given list of products.
 */
class ProductOverviewSynthesis extends AbstractSubSynthesis<ProductOverviewContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleProductSynthesis simpleProductSynthesis
    @Inject ProductSynthesis productSynthesis
    
    override transform(ProductOverviewContext productOverviewContext) {
        return #[
            createNode => [
                configureBoxLayout
                addOverviewRendering("Products")
                children += productOverviewContext.collapsedProductContexts.flatMap[
                    return simpleProductSynthesis.transform(it)
                ]
                children += productOverviewContext.detailedProductContexts.flatMap[
                    return productSynthesis.transform(it)
                ]
            ]
        ]
    }
}