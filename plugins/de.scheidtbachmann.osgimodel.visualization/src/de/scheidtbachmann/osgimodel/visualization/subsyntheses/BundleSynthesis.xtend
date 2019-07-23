package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import java.util.EnumSet
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide
import org.eclipse.elk.core.options.SizeConstraint

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Bundle} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class BundleSynthesis extends AbstractSubSynthesis<BundleContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension KPortExtensions
    @Inject extension OsgiStyles
    @Inject ServiceComponentOverviewSynthesis serviceComponentOverviewSynthesis
    extension KGraphFactory = KGraphFactory.eINSTANCE
        
    override transform(BundleContext bc) {
        val bundle = bc.modelElement
        return #[
            bc.createNode() => [
                addLayoutParam(CoreOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
                associateWith(bc)
                data += createKIdentifier => [ it.id = bc.hashCode.toString ]
                addBundleRendering(bundle, bc.parentVisualizationContext instanceof BundleOverviewContext, usedContext)
                
                // Only show any connection ports if this bundle is shown in a bundle overview.
                if (bc.parentVisualizationContext instanceof BundleOverviewContext) {
                    // The ports that show the connection to the usedBy / required bundles with actions to add them to
                    // the view.
                    val filteredUsedByBundles = SynthesisUtils.filteredElements(bundle.usedByBundle,
                        bc.parentVisualizationContext as IOverviewVisualizationContext<Bundle>, usedContext)
                    if (!filteredUsedByBundles.empty) {
                        ports += createPort(bc, "usedByBundles") => [
                            associateWith(bc)
                            // Identifier helps for connecting to this port later.
                            data += createKIdentifier => [ it.id = "usedByBundles" ]
                            // Used by bundles are always shown and expanded to the west against the drawing direction.
                            addLayoutParam(CoreOptions::PORT_SIDE, PortSide::WEST)
                            addUsedByBundlesPortRendering(filteredUsedByBundles.size, bc.allRequiringBundlesShown)
                            width = 12
                            height = 12
                        ]
                    }
                    val filteredRequiredBundles = SynthesisUtils.filteredElements(bundle.requiredBundles,
                        bc.parentVisualizationContext as IOverviewVisualizationContext<Bundle>, usedContext)
                    if (!filteredRequiredBundles.empty) {
                        ports += createPort(bc, "requiredBundles") => [
                            associateWith(bc)
                            data += createKIdentifier => [ it.id = "requiredBundles" ]
                            // Required bundles are always shown and expanded to the east with the drawing direction.
                            addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                            addRequiredBundlesPortRendering(filteredRequiredBundles.size, bc.allRequiredBundlesShown)
                            width = 12
                            height = 12
                        ]
                    }
                    // Port for connection of imported packages.
                    val importedPackages = bundle.importedPackages
                    if (!importedPackages.empty) {
                        ports += createPort(bc, "importedPackages") => [
                            associateWith(bc)
                            data += createKIdentifier => [ it.id = "importedPackages" ]
                            // Bundles supporting used packages are always shown and expanded to the east with the drawing direction.
                            addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                            addUsedPackagesPortRendering(bc.allUsedPackagesShown)
                            width = 12
                            height = 12
                        ]
                    }
                }
                
                // Show a service component overview of all service components provided by this bundle.
                // Only show this, if the option for it says so and if the context is available.
                if (usedContext.getOptionValue(OsgiOptions.BUNDLE_SHOW_SERVICE_COMPONENTS) === true
                    && bc.serviceComponentOverviewContext !== null) {
                    setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
                    val componentOverviewNodes = serviceComponentOverviewSynthesis.transform(
                        bc.serviceComponentOverviewContext)
                    children += componentOverviewNodes
                }
            ]
        ]
    }
    
}