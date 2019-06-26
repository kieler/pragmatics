package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all service interfaces in the given list of service interfaces.
 * 
 * @author nre
 */
class ServiceInterfaceOverviewSynthesis extends AbstractSubSynthesis<ServiceInterfaceOverviewContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleServiceInterfaceSynthesis simpleServiceInterfaceSynthesis
    @Inject ServiceInterfaceSynthesis serviceInterfaceSynthesis
    
    override transform(ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        return #[
            createNode => [
                configureBoxLayout
                associateWith(serviceInterfaceOverviewContext)
                addOverviewRendering("Service Interfaces")
                children += serviceInterfaceOverviewContext.collapsedServiceInterfaceContexts.flatMap[
                    return simpleServiceInterfaceSynthesis.transform(it)
                ]
                children += serviceInterfaceOverviewContext.detailedServiceInterfaceContexts.flatMap[
                    return serviceInterfaceSynthesis.transform(it)
                ]
                initiallyCollapse
            ]
        ]
    }
}