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
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide

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
    extension KGraphFactory = KGraphFactory.eINSTANCE
        
    override transform(BundleContext bc) {
        val bundle = bc.bundle
        // The top level node that is not shown and will be replaced by KLighD.
        return #[
            bc.createNode() => [
                addLayoutParam(CoreOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
                associateWith(bc)
                addBundleRendering(bundle, usedContext)
                
                // The ports that show the connection to the usedBy / required bundles with actions to add them to
                // the view.
                val filteredUsedByBundles = SynthesisUtils.filteredBundles(bundle.usedByBundle, usedContext)
                if (!filteredUsedByBundles.empty) {
                    ports += createPort(bc, "usedByBundles") => [
                        associateWith(bc)
                        // Identifier helps for connecting to this port later.
                        data += createKIdentifier => [ it.id = "usedByBundles" ]
                        // Used by bundles are always shown and expanded to the west against the drawing direction.
                        addLayoutParam(CoreOptions::PORT_SIDE, PortSide::WEST)
                        addUsedByBundlesPortRendering(filteredUsedByBundles.size)
                        width = 12
                        height = 12
                    ]
                }
                val filteredRequiredBundles = SynthesisUtils.filteredBundles(bundle.requiredBundles, usedContext)
                if (!filteredRequiredBundles.empty) {
                    ports += createPort(bc, "requiredBundles") => [
                        associateWith(bc)
                        data += createKIdentifier => [ it.id = "requiredBundles" ]
                        // Required bundles are always shown and expanded to the east with the drawing direction.
                        addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                        addRequiredBundlesPortRendering(filteredRequiredBundles.size)
                        width = 12
                        height = 12
                    ]
                }
                val importedPackages = bundle.importedPackages
                if (!importedPackages.empty) {
                    ports += createPort(bc, "importedPackages") => [
                        associateWith(bc)
                        data += createKIdentifier => [ it.id = "importedPackages" ]
                        // Bundles supporting used packages are always shown and expanded to the east with the drawing direction.
                        addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                        addUsedPackagesPortRendering
                        width = 12
                        height = 12
                    ]
                }
            ]
        ]
    }
    
}