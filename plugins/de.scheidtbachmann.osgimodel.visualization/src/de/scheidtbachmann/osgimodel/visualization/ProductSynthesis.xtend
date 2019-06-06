package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.microlayout.Bounds
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.BundleTextType
import java.util.EnumSet
import java.util.List
import org.eclipse.elk.core.math.KVector
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint

import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.*
import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Product} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ProductSynthesis extends AbstractDiagramSynthesis<Product> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
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
                children += transformBundleOverview(p.features.flatMap[bundles].toList).associateWith(p)
//                children += createNode
            ]
        ]
    }
    
    
    
    
    // TODO: Remove all the following code and put it in a sub-synthesis that this and the osgi synthesis can access.
    
    /**
     * Transformation as an overview of all bundles in the given list of bundles.
     * 
     * @param bundles The list of bundles to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformBundleOverview(List<Bundle> bundles) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Bundles")
            val filteredBundles = SynthesisUtils.filteredBundles(bundles, usedContext)
            children += filteredBundles.map[ transform ]
            initiallyCollapse
        ]
    }
    
    /**
     * Transformation of a simple view of a bundle that provides functionality to be expanded, when the specific 
     * synthesis for bundles is called.
     * 
     * @param b The bundle that should be transformed into a simple bundle rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(Bundle b) {
        return b.createNode() => [
            associateWith(b)
            initiallyCollapse
            val label = switch usedContext.getOptionValue(BUNDLE_TEXT) {
                case BundleTextType.Id: {
                    SynthesisUtils.getId(b.uniqueId, usedContext)
                }
                case BundleTextType.Name: {
                    b.descriptiveName
                }
            } ?: ""
            setLayoutOption(CoreOptions::PRIORITY, SynthesisUtils.priorityOf(label))
            addBundleInOverviewRendering(b, label)
        ]
    }
    
    /**
     * Configures the layout of any overview node. Configures the box layout algorithm of elk.
     */
    private def void configureOverviewLayout(KNode node) {
        node => [
            setLayoutOption(CoreOptions::ALGORITHM, "org.eclipse.elk.box")
//            setLayoutOption(CoreOptions::EXPAND_NODES, true) // TODO: why does this not work on bundles?
        ]
    }
    
}