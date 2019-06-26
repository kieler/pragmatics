package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link ServiceInterface} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ServiceInterfaceSynthesis extends AbstractSubSynthesis<ServiceInterfaceContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(ServiceInterfaceContext si) {
        val serviceInterface = si.serviceInterface
        return #[
            si.createNode() => [
                associateWith(si)
                addServiceInterfaceRendering(serviceInterface, usedContext)
            ]
        ]
    }
    
}