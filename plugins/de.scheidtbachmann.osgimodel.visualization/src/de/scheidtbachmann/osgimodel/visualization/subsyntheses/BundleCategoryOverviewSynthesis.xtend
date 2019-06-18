package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.BundleCategory
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import java.util.List

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all bundle categories in the given list of bundle categories.
 */
class BundleCategoryOverviewSynthesis extends AbstractSubSynthesis<List<BundleCategory>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleBundleCategorySynthesis simpleBundleCategorySynthesis
    
    override transform(List<BundleCategory> features) {
        return #[
            createNode => [
                configureBoxLayout
                addOverviewRendering("Bundle Categories")
                children += features.flatMap[ simpleBundleCategorySynthesis.transform(it)]
                initiallyCollapse
            ]
        ]
    }
}