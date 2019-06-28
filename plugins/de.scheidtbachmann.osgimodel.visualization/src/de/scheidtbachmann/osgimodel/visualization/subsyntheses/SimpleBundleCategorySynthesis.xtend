package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.BundleCategory
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.core.options.CoreOptions

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

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
                val label = b.categoryName
                setLayoutOption(CoreOptions::PRIORITY, SynthesisUtils.priorityOf(label))
                addGenericRendering(label)
            ]
        ]
    }
    
}