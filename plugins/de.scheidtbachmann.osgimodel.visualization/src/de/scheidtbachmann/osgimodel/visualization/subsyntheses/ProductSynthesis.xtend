package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import java.util.EnumSet
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Product} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ProductSynthesis extends AbstractSubSynthesis<ProductContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject BundleOverviewSynthesis bundleOverviewSynthesis
    
    override transform(ProductContext pc) {
        val product = pc.product
        return #[
            pc.createNode() => [
                associateWith(pc)
                val rendering = addProductRendering(product, usedContext)
//                val renderingSize = estimateSize(rendering, new Bounds(0,0))
                setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
//                setLayoutOption(CoreOptions::NODE_SIZE_MINIMUM, new KVector(renderingSize.x, renderingSize.y))
//                setLayoutOption(CoreOptions::EXPAND_NODES, true);
                
                // Show a bundle overview of all bundles within this product.
                val overviewBundleNodes = bundleOverviewSynthesis.transform(pc.bundleOverviewContext)
                children += overviewBundleNodes
//                children += createNode
            ]
        ]
    }
    
}