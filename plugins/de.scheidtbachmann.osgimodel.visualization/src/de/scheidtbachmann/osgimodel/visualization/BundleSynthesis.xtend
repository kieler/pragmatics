package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import org.eclipse.elk.core.options.CoreOptions
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
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    @Inject extension OsgiStyles
    extension KRenderingFactory = KRenderingFactory.eINSTANCE
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    // TODO: add option to leave out / shorten description texts
    
    override transform(Bundle b) {
        return b.createNode.associateWith(b) => [
            // This is only the top-level node of this synthesis without 
            children += createNode() => [
                addLayoutParam(CoreOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
                associateWith(b)
                addBundleRendering(b)
                ports += createPort(b, "usedByBundles") => [
                    data += createKIdentifier => [ it.id = 'usedByBundles' ]
                    addLayoutParam(CoreOptions::PORT_SIDE, PortSide::WEST)
                    addUsedByBundlesPortRendering
                    width = 8
                    height = 8
                ]
                ports += createPort(b, "requiredBundles") => [
                    data += createKIdentifier => [ it.id = 'requiredBundles' ]
                    addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                    addRequiredBundlesPortRendering
                    width = 8
                    height = 8
                ]
            ]
        ]
    }
}