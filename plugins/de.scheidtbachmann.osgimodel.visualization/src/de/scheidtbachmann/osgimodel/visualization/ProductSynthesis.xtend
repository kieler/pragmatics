package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.BundleOverviewSynthesis
import java.util.EnumSet
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Product} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ProductSynthesis extends AbstractDiagramSynthesis<Product> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject BundleOverviewSynthesis bundleOverviewSynthesis
    
    override transform(Product p) {
        return p.createNode => [
            associateWith(p)
            children += createNode() => [
                associateWith(p)
                val rendering = addProductRendering(p, usedContext)
//                val renderingSize = estimateSize(rendering, new Bounds(0,0))
                setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
//                setLayoutOption(CoreOptions::NODE_SIZE_MINIMUM, new KVector(renderingSize.x, renderingSize.y))
//                setLayoutOption(CoreOptions::EXPAND_NODES, true);
                
                // Show a bundle overview of all bundles within this product.
                val overviewBundleNodes = bundleOverviewSynthesis.transform(p.features.flatMap[bundles].toList)
                overviewBundleNodes.forEach [ associateWith(p) ]
                children += overviewBundleNodes
//                children += createNode
            ]
        ]
    }
    
}