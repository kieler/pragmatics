package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles

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
        return #[
            p.createNode() => [
                associateWith(p)
                addGenericRendering(p.uniqueId)
            ]
        ]
    }
    
}