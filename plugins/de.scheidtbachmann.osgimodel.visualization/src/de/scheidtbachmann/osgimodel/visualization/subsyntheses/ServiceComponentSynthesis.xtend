package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.ServiceComponent
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentOverviewContext
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link ServiceComponent} views.
 * 
 * @author nre
 */
class ServiceComponentSynthesis extends AbstractSubSynthesis<ServiceComponentContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension KPortExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceComponentContext sc) {
        val serviceComponent = sc.modelElement
        return #[
            sc.createNode() => [
                addLayoutParam(CoreOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
                associateWith(sc)
                data += createKIdentifier => [ it.id = sc.hashCode.toString ]
                addServiceComponentRendering(serviceComponent,
                    sc.parentVisualizationContext instanceof ServiceComponentOverviewContext, usedContext)
                
                // The ports that show the connection to the service interfaces this service component implements with
                // actions to add them to the view.
                val interfaces = serviceComponent.serviceInterfaces
                if (!interfaces.empty) {
                    ports += createPort(sc, "implementedServiceInterfaces") => [
                        associateWith(sc)
                        // Identifier helps for connecting to this port later.
                        data += createKIdentifier => [ it.id = "implementedServiceInterfaces" ]
                        // Implemented interfaces are always shown and expanded to the east with the drawing
                        // direction.
                        addLayoutParam(CoreOptions::PORT_SIDE, PortSide::EAST)
                        
                        val boolean allImplementingComponentsShown = switch (usedContext.getProperty(
                            OsgiSynthesisProperties.CURRENT_SERVICE_COMPONENT_VISUALIZATION_MODE)) {
                            case PLAIN: {
                                sc.allImplementedInterfacesShownPlain
                            }
                            case IN_BUNDLES: {
                                sc.allImplementedInterfacesShownInBundles
                            }
                        }
                            
                        addImplementedServiceInterfacesPortRendering(interfaces.size,
                            allImplementingComponentsShown)
                        width = 12
                        height = 12
                    ]
                }
            ]
        ]
    }
    
}