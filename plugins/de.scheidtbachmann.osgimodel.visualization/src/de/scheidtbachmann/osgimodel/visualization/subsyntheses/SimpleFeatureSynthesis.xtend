package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles

/**
 * Transformation of a simple view of a feature that provides functionality to be expanded, when the specific 
 * synthesis for features is called.
 */
class SimpleFeatureSynthesis extends AbstractSubSynthesis<Feature, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(Feature f) {
        return #[
            f.createNode() => [
                associateWith(f)
                addGenericRendering(f.descriptiveName)
            ]
        ]
    }
    
}