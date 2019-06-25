package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.SimpleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import org.eclipse.elk.core.options.CoreOptions

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a product that provides functionality to be expanded, when the specific 
 * synthesis for products is called.
 * 
 * @author nre
 */
class SimpleProductSynthesis extends AbstractSubSynthesis<ProductContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(ProductContext pc) {
        val product = pc.product
        return #[
            product.createNode() => [
                associateWith(pc)
                val label = switch usedContext.getOptionValue(SIMPLE_TEXT) {
                    case SimpleTextType.Id: {
                        SynthesisUtils.getId(product.uniqueId, usedContext)
                    }
                    case SimpleTextType.Name: {
                        product.descriptiveName
                    }
                } ?: ""
                setLayoutOption(CoreOptions::PRIORITY, SynthesisUtils.priorityOf(label))
                addProductInOverviewRendering(product, label)
            ]
        ]
    }
    
}