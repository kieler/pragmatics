package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.SimpleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductOverviewContext
import java.util.EnumSet
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
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
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ProductOverviewContext productOverviewContext) {
        return #[
            createNode => [
                associateWith(productOverviewContext)
                data += createKIdentifier => [ it.id = productOverviewContext.hashCode.toString ]
                if (productOverviewContext.expanded) {
                    initiallyExpand
                } else {
                    initiallyCollapse
                }
                configureBoxLayout
                setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
                addOverviewRendering("Products", productOverviewContext.overviewText)
                
                val filteredCollapsedProducts = SynthesisUtils.filteredElementContexts(
                    productOverviewContext.collapsedElements, usedContext)
                val indexOffset = filteredCollapsedProducts.size
                filteredCollapsedProducts.sortBy [ sortBy ].forEach [ collapsedProductContext, index |
                    children += simpleProductSynthesis.transform(collapsedProductContext as ProductContext, -index)
                ]
                
                val filteredDetailedProducts = SynthesisUtils.filteredElementContexts(
                    productOverviewContext.detailedElements, usedContext)
                filteredDetailedProducts.sortBy [ sortBy ].forEach [ detailedProductContext, index |
                    children += productSynthesis.transform(detailedProductContext as ProductContext, -index
                        - indexOffset)
                ]
            ]
        ]
    }
    
    /**
     * The string to sort by. Either the shortened ID or the name.
     */
    def sortBy(IVisualizationContext<Product> it) {
        switch usedContext.getOptionValue(SIMPLE_TEXT) {
            case SimpleTextType.Id: {
                SynthesisUtils.getId(modelElement.uniqueId, usedContext)
            }
            case SimpleTextType.Name: {
                modelElement.descriptiveName
            }
        } ?: ""
    }
    
}