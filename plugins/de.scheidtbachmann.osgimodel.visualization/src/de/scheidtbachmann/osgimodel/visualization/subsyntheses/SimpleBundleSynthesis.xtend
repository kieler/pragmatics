package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.BundleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.core.options.CoreOptions

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a bundle that provides functionality to be expanded, when the specific 
 * synthesis for bundles is called.
 */
class SimpleBundleSynthesis extends AbstractSubSynthesis<Bundle, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(Bundle b) {
        return #[
            b.createNode() => [
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
        ]
    }
    
}