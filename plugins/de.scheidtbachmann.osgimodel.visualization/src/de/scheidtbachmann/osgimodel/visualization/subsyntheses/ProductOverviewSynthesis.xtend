package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import java.util.List

import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all products in the given list of products.
 */
class ProductOverviewSynthesis extends AbstractSubSynthesis<List<Product>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleProductSynthesis simpleProductSynthesis
    
    override transform(List<Product> products) {
        return #[
            createNode => [
                configureOverviewLayout
                addOverviewRendering("Products")
                children += products.flatMap[ simpleProductSynthesis.transform(it) ]
            ]
        ]
    }
}