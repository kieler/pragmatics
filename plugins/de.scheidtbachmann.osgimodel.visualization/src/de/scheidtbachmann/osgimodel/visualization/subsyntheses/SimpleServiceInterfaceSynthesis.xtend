package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext

/**
 * Transformation of a simple view of a service interface that provides functionality to be expanded, when the
 * specific synthesis for service interfaces is called.
 * 
 * @author nre
 */
class SimpleServiceInterfaceSynthesis extends AbstractSubSynthesis<ServiceInterfaceContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(ServiceInterfaceContext s) {
        val serviceInterface = s.modelElement
        return #[
            s.createNode() => [
                associateWith(s)
                // The 'name' attribute of service interfaces really are their ID.
                addServiceInterfaceInOverviewRendering(serviceInterface, SynthesisUtils.getId(serviceInterface.name, usedContext))
            ]
        ]
    }
    
}