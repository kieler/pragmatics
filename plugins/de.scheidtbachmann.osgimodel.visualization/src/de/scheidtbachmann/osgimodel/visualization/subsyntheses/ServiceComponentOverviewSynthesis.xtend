package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentOverviewContext
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all service components in the given list of service components.
 * 
 * @author nre
 */
class ServiceComponentOverviewSynthesis extends AbstractSubSynthesis<ServiceComponentOverviewContext, KNode> {
    @Inject extension KEdgeExtensions
    @Inject extension KNodeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension OsgiStyles
    @Inject SimpleServiceComponentSynthesis simpleServiceComponentSynthesis
    @Inject ServiceComponentSynthesis serviceComponentSynthesis
    
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceComponentOverviewContext serviceComponentOverviewContext) {
        return #[
            createNode => [
                associateWith(serviceComponentOverviewContext)
                data += createKIdentifier => [ it.id = serviceComponentOverviewContext.hashCode.toString ]
                if (serviceComponentOverviewContext.expanded) {
                    initiallyExpand
                } else {
                    initiallyCollapse
                }
                setLayoutOption(it, CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
                setLayoutOption(it, CoreOptions::DIRECTION, Direction.DOWN)
                addOverviewRendering("ServiceComponents")
                
                // remove the padding of the invisible container.
                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0))
                
                // Add all simple service component renderings in a first subgraph (top)
                val collapsedOverviewNode = transformCollapsedServiceComponentsOverview(serviceComponentOverviewContext)
                children += collapsedOverviewNode
                
                // Add all detailed service component renderings and their connections in a second subgraph (bottom)
                val detailedOverviewNode = transformDetailedServiceComponentsOverview(serviceComponentOverviewContext)
                children += detailedOverviewNode
                
                // Put an invisible edge between the collapsed and detailed overviews to guarantee the simple renderings
                // are above the detailed ones.
                collapsedOverviewNode.outgoingEdges += createEdge => [
                    addPolyline => [
                        invisible = true
                    ]
                    target = detailedOverviewNode
                ]
            ]
        ]
    }
    
    /**
     * The top part of the service component overview rendering containing all collapsed service component renderings in a box layout.
     * 
     * @param serviceComponentOverviewContext The overview context for all service components in this subsynthesis.
     */
    private def KNode transformCollapsedServiceComponentsOverview(
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        val filteredCollapsedServiceComponentContexts = serviceComponentOverviewContext.collapsedElements
        createNode => [
            associateWith(serviceComponentOverviewContext)
            configureBoxLayout
            addInvisibleContainerRendering
            
            filteredCollapsedServiceComponentContexts.sortBy [
                // The string to sort by. Either the shortened ID or the name.
                modelElement.name
            ].forEach [ collapsedServiceComponentContext, index |
                children += simpleServiceComponentSynthesis.transform(collapsedServiceComponentContext as ServiceComponentContext , -index)
            ]
        ]
    }
    
    /**
     * The bottom part of the service component overview rendering containing all detailed service component renderings and their
     * connections in a layered layout.
     * 
     * @param serviceComponentOverviewContext The overview context for all service components in this subsynthesis.
     */
    private def KNode transformDetailedServiceComponentsOverview(
        ServiceComponentOverviewContext serviceComponentOverviewContext) {
        val filteredDetailedServiceComponentContexts = serviceComponentOverviewContext.detailedElements
        createNode => [
            associateWith(serviceComponentOverviewContext)
            configureOverviewLayout
            addInvisibleContainerRendering
            
            children += filteredDetailedServiceComponentContexts.flatMap [
                return serviceComponentSynthesis.transform(it as ServiceComponentContext)
            ]
        ]
    }
    
}