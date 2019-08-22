package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductOverviewContext
import java.util.EnumSet
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Product} views.
 * 
 * @author nre
 */
class ProductSynthesis extends AbstractSubSynthesis<ProductContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject FeatureOverviewSynthesis featureOverviewSynthesis
    @Inject BundleOverviewSynthesis bundleOverviewSynthesis
    @Inject ServiceComponentOverviewSynthesis serviceComponentOverviewSynthesis
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ProductContext pc) {
        val product = pc.modelElement
        return #[
            pc.createNode() => [
                associateWith(pc)
                data += createKIdentifier => [ it.id = pc.hashCode.toString ]
                setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
                
                // Show a feature overview of all features within this product.
                val overviewFeatureNodes = featureOverviewSynthesis.transform(pc.featureOverviewContext)
                children += overviewFeatureNodes
                
                // Show a bundle overview of all bundles within this product.
                val overviewBundleNodes = bundleOverviewSynthesis.transform(pc.bundleOverviewContext)
                children += overviewBundleNodes
                
                // Show a service component overview of all service components defined by bundles of this product.
                val overviewServiceComponentNodes = serviceComponentOverviewSynthesis.transform(
                    pc.serviceComponentOverviewContext)
                children += overviewServiceComponentNodes
                
                // Add the rendering.
                val hasChildren = !children.empty
                addProductRendering(product,
                    pc.parentVisualizationContext instanceof ProductOverviewContext, hasChildren, usedContext)
            ]
        ]
    }
    
}