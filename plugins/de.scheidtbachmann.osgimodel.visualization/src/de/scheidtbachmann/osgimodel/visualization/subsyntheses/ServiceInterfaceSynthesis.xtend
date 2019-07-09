package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.ServiceInterface
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.PortSide

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link ServiceInterface} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ServiceInterfaceSynthesis extends AbstractSubSynthesis<ServiceInterfaceContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension KPortExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceInterfaceContext sic) {
        val serviceInterface = sic.modelElement
        return #[
            sic.createNode() => [
                associateWith(sic)
                data += createKIdentifier => [ it.id = sic.hashCode.toString ]
                addServiceInterfaceRendering(serviceInterface, usedContext)
                
                // The ports that show the connection to the service components this service interface with actions to
                // add them to the view.
                val components = serviceInterface.serviceComponent
                if (!components.empty) {
                    ports += createPort(sic, "implementingServiceComponents") => [
                        associateWith(sic)
                        // Identifier helps for connecting to this port later.
                        data += createKIdentifier => [ it.id = "implementingServiceComponents" ]
                        // Implementing components are always shown and expanded to the east with the drawing direction.
                        addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                        addImplementingServiceComponentsPortRendering(components.size, sic.allImplementingComponentsShown)
                        width = 12
                        height = 12
                    ]
                }
            ]
        ]
    }
    
}