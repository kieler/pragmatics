package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import java.util.List

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all bundles in the given list of bundles.
 */
class BundleOverviewSynthesis extends AbstractSubSynthesis<List<Bundle>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleBundleSynthesis simpleBundleSynthesis
    
    override transform(List<Bundle> bundles) {
        return #[
            createNode => [
                configureOverviewLayout
                addOverviewRendering("Bundles")
                val filteredBundles = SynthesisUtils.filteredBundles(bundles, usedContext)
                children += filteredBundles.flatMap[ simpleBundleSynthesis.transform(it) ]
                initiallyCollapse
            ]
        ]
    }
    
}