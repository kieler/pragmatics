package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.core.options.CoreOptions

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a packages that provides functionality to be expanded, when the specific
 * synthesis for packages is called.
 * 
 * @author nre
 */
class SimplePackageObjectSynthesis extends AbstractSubSynthesis<PackageObject, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(PackageObject p) {
        transform(p, 0)
    }
    
    def transform(PackageObject p, int priority) {
        return #[
            p.createNode() => [
                associateWith(p)
                val label = p.uniqueId
                setLayoutOption(CoreOptions::PRIORITY, priority)
                addGenericRendering(SynthesisUtils.getId(label, usedContext))
            ]
        ]
    }
    
}