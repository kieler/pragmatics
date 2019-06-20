package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.ServiceInterface
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import java.util.List

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all service interfaces in the given list of service interfaces.
 * 
 * @author nre
 */
class ServiceInterfaceOverviewSynthesis extends AbstractSubSynthesis<List<ServiceInterface>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimpleServiceInterfaceSynthesis simpleServiceInterfaceSynthesis
    
    override transform(List<ServiceInterface> serviceInterfaces) {
        return #[
            createNode => [
                configureBoxLayout
                addOverviewRendering("Service Interfaces")
                children += serviceInterfaces.flatMap[ simpleServiceInterfaceSynthesis.transform(it) ]
                initiallyCollapse
            ]
        ]
    }
}