package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.SimpleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.core.options.CoreOptions

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a feature that provides functionality to be expanded, when the specific 
 * synthesis for features is called.
 * 
 * @author nre
 */
class SimpleFeatureSynthesis extends AbstractSubSynthesis<Feature, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(Feature f) {
        return #[
            f.createNode() => [
                associateWith(f)
                val label = switch usedContext.getOptionValue(SIMPLE_TEXT) {
                    case SimpleTextType.Id: {
                        SynthesisUtils.getId(f.uniqueId, usedContext)
                    }
                    case SimpleTextType.Name: {
                        f.descriptiveName
                    }
                } ?: ""
                setLayoutOption(CoreOptions::PRIORITY, SynthesisUtils.priorityOf(label))
                addGenericRendering(label)
            ]
        ]
    }
    
}