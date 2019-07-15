package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.ServiceComponent
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentOverviewContext

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link ServiceComponent} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ServiceComponentSynthesis extends AbstractSubSynthesis<ServiceComponentContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceComponentContext sc) {
        val serviceComponent = sc.modelElement
        return #[
            sc.createNode() => [
                associateWith(sc)
                data += createKIdentifier => [ it.id = sc.hashCode.toString ]
                addServiceComponentRendering(serviceComponent,
                    sc.parentVisualizationContext instanceof ServiceComponentOverviewContext, usedContext)
            ]
        ]
    }
    
}