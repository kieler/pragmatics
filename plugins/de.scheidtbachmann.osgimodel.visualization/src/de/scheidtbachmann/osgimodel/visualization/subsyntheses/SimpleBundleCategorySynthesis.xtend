package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.BundleCategory
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles

/**
 * Transformation of a simple view of a bundle category that provides functionality to be expanded, when the
 * specific synthesis for bundle categories is called.
 * 
 * @author nre
 */
class SimpleBundleCategorySynthesis extends AbstractSubSynthesis<BundleCategory, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(BundleCategory b) {
        return #[
            b.createNode() => [
                associateWith(b)
                addGenericRendering(b.categoryName)
            ]
        ]
    }
    
}