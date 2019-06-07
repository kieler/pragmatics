package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction
import org.eclipse.elk.core.options.EdgeRouting
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Bundle} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class BundleSynthesis extends AbstractDiagramSynthesis<Bundle> {
    @Inject extension KNodeExtensions
    @Inject extension KPortExtensions
    @Inject extension KRenderingExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
        
    override transform(Bundle b) {
        // The top level node that is not shown and will be replaced by KLighD.
        return b.createNode => [
            associateWith(b)
            // The Node that contains this bundle and also may contain all additional required and used by bundles.
            children += createNode => [
                addInvisibleContainerRendering
                associateWith(b)
                configureBundleLayout
                children += createNode() => [
                    addLayoutParam(CoreOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
                    associateWith(b)
                    addBundleRendering(b, usedContext)
                    
                    // The ports that show the connection to the usedBy / required bundles with actions to add them to
                    // the view.
                    val filteredUsedByBundles = SynthesisUtils.filteredBundles(b.usedByBundle, usedContext)
                    if (!filteredUsedByBundles.empty) {
                        ports += createPort(b, "usedByBundles") => [
                            associateWith(b)
                            // Identifier helps for connecting to this port later.
                            data += createKIdentifier => [ it.id = "usedByBundles" ]
                            // Used by bundles are always shown and expanded to the west against the drawing direction.
                            addLayoutParam(CoreOptions::PORT_SIDE, PortSide::WEST)
                            addUsedByBundlesPortRendering(filteredUsedByBundles.size)
                            width = 12
                            height = 12
                        ]
                    }
                    val filteredRequiredBundles = SynthesisUtils.filteredBundles(b.requiredBundles, usedContext)
                    if (!filteredRequiredBundles.empty) {
                        ports += createPort(b, "requiredBundles") => [
                            associateWith(b)
                            data += createKIdentifier => [ it.id = "requiredBundles" ]
                            // Required bundles are always shown and expanded to the east with the drawing direction.
                            addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                            addRequiredBundlesPortRendering(filteredRequiredBundles.size)
                            width = 12
                            height = 12
                        ]
                    }
                    val importedPackages = b.importedPackages
                    if (!importedPackages.empty) {
                        ports += createPort(b, "importedPackages") => [
                            associateWith(b)
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
        ]
    }
    
    /**
     * Configures the layout on the bundle view for the top level node that shows the connection between required and
     * used by bundles.
     */
    private def configureBundleLayout(KNode node) {
        node => [
            setLayoutOption(CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
            setLayoutOption(CoreOptions::DIRECTION, Direction.RIGHT)
            setLayoutOption(CoreOptions::EDGE_ROUTING, EdgeRouting.POLYLINE)
        ]
    }
}