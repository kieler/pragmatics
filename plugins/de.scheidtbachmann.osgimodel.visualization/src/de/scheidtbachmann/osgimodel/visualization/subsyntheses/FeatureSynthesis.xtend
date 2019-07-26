package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.FeatureContext
import de.scheidtbachmann.osgimodel.visualization.context.FeatureOverviewContext
import java.util.EnumSet
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.SizeConstraint

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Feature} views.
 * 
 * @author nre
 */
class FeatureSynthesis extends AbstractSubSynthesis<FeatureContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject BundleOverviewSynthesis bundleOverviewSynthesis
    extension KGraphFactory = KGraphFactory.eINSTANCE
        
    override transform(FeatureContext fc) {
        val feature = fc.modelElement
        return #[
            fc.createNode() => [
                addLayoutParam(CoreOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
                associateWith(fc)
                data += createKIdentifier => [ it.id = fc.hashCode.toString ]
                addFeatureRendering(feature, fc.parentVisualizationContext instanceof FeatureOverviewContext, usedContext)
                
                // Show a bundle overview of all bundles provided by this feature.
                if (fc.bundleOverviewContext !== null) {
                    setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
                    val bundleOverviewNodes = bundleOverviewSynthesis.transform(
                        fc.bundleOverviewContext)
                    children += bundleOverviewNodes
                }
            ]
        ]
    }
    
}