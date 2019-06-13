package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.core.options.CoreOptions

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a product that provides functionality to be expanded, when the specific 
 * synthesis for products is called.
 */
class SimpleProductSynthesis extends AbstractSubSynthesis<Product, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(Product p) {
        return #[
            p.createNode() => [
                associateWith(p)
                initiallyCollapse
                val label = p.descriptiveName
                setLayoutOption(CoreOptions::PRIORITY, SynthesisUtils.priorityOf(label))
                addProductInOverviewRendering(p, label)
            ]
        ]
    }
    
}