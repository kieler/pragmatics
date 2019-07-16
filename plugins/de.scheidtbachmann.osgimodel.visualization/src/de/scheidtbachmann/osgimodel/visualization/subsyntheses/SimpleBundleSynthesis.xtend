package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.SimpleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import org.eclipse.elk.core.options.CoreOptions

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a bundle that provides functionality to be expanded, when the specific 
 * synthesis for bundles is called.
 * 
 * @author nre
 */
class SimpleBundleSynthesis extends AbstractSubSynthesis<BundleContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(BundleContext bc) {
        transform(bc, 0)
    }
    
    def transform(BundleContext bc, int priority) {
        val bundle = bc.modelElement
        return #[
            bc.createNode() => [
                associateWith(bc)
                data += createKIdentifier => [ it.id = bc.hashCode.toString ]
                val label = switch usedContext.getOptionValue(SIMPLE_TEXT) {
                    case SimpleTextType.Id: {
                        SynthesisUtils.getId(bundle.uniqueId, usedContext)
                    }
                    case SimpleTextType.Name: {
                        bundle.descriptiveName
                    }
                } ?: ""
                setLayoutOption(CoreOptions::PRIORITY, priority)
                addBundleInOverviewRendering(bundle, label)
            ]
        ]
    }
    
}