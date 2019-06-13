package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import java.util.List

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all features in the given list of features.
 */
class FeatureOverviewSynthesis extends AbstractSubSynthesis<List<Feature>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleFeatureSynthesis simpleFeatureSynthesis
    
    override transform(List<Feature> features) {
        return #[
            createNode => [
                configureOverviewLayout
                addOverviewRendering("Features")
                children += features.flatMap[ simpleFeatureSynthesis.transform(it)]
                initiallyCollapse
            ]
        ]
    }
}