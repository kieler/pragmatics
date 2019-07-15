package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import org.eclipse.elk.core.options.CoreOptions

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Transformation of a simple view of a service component that provides functionality to be expanded, when the
 * specific synthesis for service components is called.
 * 
 * @author nre
 */
class SimpleServiceComponentSynthesis extends AbstractSubSynthesis<ServiceComponentContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceComponentContext scc) {
        transform(scc, 0)
    }
    
    def transform(ServiceComponentContext scc, int priority) {
        val serviceComponent = scc.modelElement
        return #[
            scc.createNode() => [
                associateWith(scc)
                data += createKIdentifier => [ it.id = scc.hashCode.toString ]
                // The 'name' attribute of service components really are their ID.
                val label = SynthesisUtils.getId(serviceComponent.name, usedContext)
                setLayoutOption(CoreOptions::PRIORITY, priority)
                addServiceComponentInOverviewRendering(serviceComponent, label)
            ]
        ]
    }
    
}