package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction
import org.eclipse.elk.core.options.EdgeRouting

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all service interfaces in the given list of service interfaces.
 * 
 * @author nre
 */
class ServiceInterfaceOverviewSynthesis extends AbstractSubSynthesis<ServiceInterfaceOverviewContext, KNode> {
    @Inject extension KEdgeExtensions
    @Inject extension KNodeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension OsgiStyles
    @Inject SimpleServiceInterfaceSynthesis simpleServiceInterfaceSynthesis
    @Inject ServiceInterfaceSynthesis serviceInterfaceSynthesis
    @Inject ServiceComponentSynthesis serviceComponentSynthesis
    
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        return #[
            createNode => [
                associateWith(serviceInterfaceOverviewContext)
                data += createKIdentifier => [ it.id = serviceInterfaceOverviewContext.hashCode.toString ]
                initiallyCollapse
                DiagramSyntheses.setLayoutOption(it, CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
                DiagramSyntheses.setLayoutOption(it, CoreOptions::DIRECTION, Direction.DOWN)
                addOverviewRendering("Service Interfaces")
                
                // remove the padding of the invisible container.
                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0))
                
                // Add all simple service interface renderings in a first subgraph (top)
                val filteredCollapsedServiceInterfaceContexts = SynthesisUtils.filteredServiceInterfaceContexts(
                    serviceInterfaceOverviewContext.collapsedElements, usedContext)
                val collapsedOverviewNode = transformCollapsedServiceInterfacesOverview(
                    filteredCollapsedServiceInterfaceContexts as Iterable<ServiceInterfaceContext>,
                    serviceInterfaceOverviewContext)
                children += collapsedOverviewNode
                
                // Add all detailed service interface renderings and their connections in a second subgraph (bottom)
                val filteredDetailedServiceInterfaceContexts = SynthesisUtils.filteredServiceInterfaceContexts(
                    serviceInterfaceOverviewContext.detailedElements, usedContext)
                val detailedOverviewNode = transformDetailedServiceInterfacesOverview(
                    filteredDetailedServiceInterfaceContexts as Iterable<ServiceInterfaceContext>,
                    serviceInterfaceOverviewContext)
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
     * The top part of the service interface overview rendering containing all collapsed service interface renderings in
     * a box layout.
     * 
     * @param filteredCollapsedServiceInterfaceContexts The service interface contexts for all collapsed service
     * Interfaces that are allowed to be shown by the option filters.
     * @param serviceInterfaceOverviewContext The overview context for all service interfaces in this subsynthesis.
     */
    private def KNode transformCollapsedServiceInterfacesOverview(
        Iterable<ServiceInterfaceContext> filteredCollapsedServiceInterfaceContexts,
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        createNode => [
            associateWith(serviceInterfaceOverviewContext)
            configureBoxLayout
            addInvisibleContainerRendering
            
            filteredCollapsedServiceInterfaceContexts.sortBy [
                SynthesisUtils.getId(modelElement.name, usedContext)
            ]
            filteredCollapsedServiceInterfaceContexts.forEach [ serviceInterfaceContext, index |
                children += simpleServiceInterfaceSynthesis.transform(serviceInterfaceContext, index)
            ]
        ]
    }
    
    /**
     * The bottom part of the service interface overview rendering containing all detailed service interface renderings
     * and their connections in a layered layout.
     * 
     * @param filteredDetailedServiceInterfaceContexts The service interface contexts for all detailed service
     * interfaces that are allowed to be shown by the option filters.
     * @param serviceInterfaceOverviewContext The overview context for all service interfaces in this subsynthesis.
     */
    private def KNode transformDetailedServiceInterfacesOverview(
        Iterable<ServiceInterfaceContext> filteredDetailedServiceInterfaceContexts,
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        createNode => [
            associateWith(serviceInterfaceOverviewContext)
            configureServiceInterfaceOverviewLayout
            addInvisibleContainerRendering
            
            // All service interfaces.
            children += filteredDetailedServiceInterfaceContexts.flatMap [
                return serviceInterfaceSynthesis.transform(it)
            ]
            
            // All service components.
            val serviceComponents = serviceInterfaceOverviewContext.implementingServiceComponentContexts
            children += serviceComponents.flatMap [
                return serviceComponentSynthesis.transform(it)
            ]
            
            // Add all implementing service component edges.
            serviceInterfaceOverviewContext.implementedInterfaceEdges.forEach [
                // Connects the service component and -interface via an arrow in UML style,
                // so [component] ---implements---|> [interface]
                val component = key
                val interface = value
                val componentNode = component.node
                val interfaceNode = interface.node
                val interfacePort = interfaceNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "implementingServiceComponents" ]
                
                val edge = createEdge(component, interface) => [
                    addImplementingComponentEdgeRendering
                    targetPort = interfacePort
                    source = componentNode
                    target = interfaceNode
                ]
                componentNode.outgoingEdges += edge
            ]
        ]
    }
    
    /**
     * Configures the layout on the service interface view for the top level node that shows the connections between
     * service interfaces and their implementing service component.
     * 
     * @param node The node containing the connected nodes.
     */
    private def configureServiceInterfaceOverviewLayout(KNode node) {
        node => [
            setLayoutOption(CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
            setLayoutOption(CoreOptions::DIRECTION, Direction.RIGHT)
            setLayoutOption(CoreOptions::EDGE_ROUTING, EdgeRouting.POLYLINE)
            setLayoutOption(LayeredMetaDataProvider::SPACING_NODE_NODE_BETWEEN_LAYERS, 30.0)
        ]
    }
    
}