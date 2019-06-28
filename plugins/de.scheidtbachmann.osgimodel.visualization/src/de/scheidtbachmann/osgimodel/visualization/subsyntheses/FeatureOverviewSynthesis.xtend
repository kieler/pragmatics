package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.SimpleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import java.util.List

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all features in the given list of features.
 * 
 * @author nre
 */
class FeatureOverviewSynthesis extends AbstractSubSynthesis<List<Feature>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleFeatureSynthesis simpleFeatureSynthesis
    
    override transform(List<Feature> features) {
        return #[
            createNode => [
                configureBoxLayout
                associateWith(features)
                addOverviewRendering("Features")
                features.sortBy [
                    // The string to sort by. Either the shortened ID or the name.
                    switch usedContext.getOptionValue(SIMPLE_TEXT) {
                        case SimpleTextType.Id: {
                            SynthesisUtils.getId(uniqueId, usedContext)
                        }
                        case SimpleTextType.Name: {
                            descriptiveName
                        }
                    } ?: ""
                ].forEach [ feature, index |
                    children += simpleFeatureSynthesis.transform(feature, -index)
                ]
                initiallyCollapse
            ]
        ]
    }
}