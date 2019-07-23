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
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction
import org.eclipse.elk.core.options.HierarchyHandling

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
    @Inject BundleSynthesis bundleSynthesis
    @Inject ServiceComponentSynthesis serviceComponentSynthesis
    @Inject ServiceInterfaceSynthesis serviceInterfaceSynthesis
    @Inject SimpleServiceInterfaceSynthesis simpleServiceInterfaceSynthesis
    
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        return #[
            createNode => [
                associateWith(serviceInterfaceOverviewContext)
                data += createKIdentifier => [ it.id = serviceInterfaceOverviewContext.hashCode.toString ]
                if (serviceInterfaceOverviewContext.expanded) {
                    initiallyExpand
                } else {
                    initiallyCollapse
                }
                DiagramSyntheses.setLayoutOption(it, CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
                DiagramSyntheses.setLayoutOption(it, CoreOptions::DIRECTION, Direction.DOWN)
                addOverviewRendering("Service Interfaces")
                
                // remove the padding of the invisible container.
                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0))
                
                // Add all simple service interface renderings in a first subgraph (top)
                val collapsedOverviewNode = transformCollapsedServiceInterfacesOverview(serviceInterfaceOverviewContext)
                children += collapsedOverviewNode
                
                // Add all detailed service interface renderings and their connections in a second subgraph (bottom)
                val detailedOverviewNode = transformDetailedServiceInterfacesOverview(serviceInterfaceOverviewContext)
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
     * @param serviceInterfaceOverviewContext The overview context for all service interfaces in this subsynthesis.
     */
    private def KNode transformCollapsedServiceInterfacesOverview(
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        val filteredCollapsedServiceInterfaceContexts = SynthesisUtils.filteredServiceInterfaceContexts(
            serviceInterfaceOverviewContext.collapsedElements, usedContext)
        createNode => [
            associateWith(serviceInterfaceOverviewContext)
            configureBoxLayout
            addInvisibleContainerRendering
            
            filteredCollapsedServiceInterfaceContexts.sortBy [
                SynthesisUtils.getId(modelElement.name, usedContext)
            ].forEach [ serviceInterfaceContext, index |
                children += simpleServiceInterfaceSynthesis.transform(
                    serviceInterfaceContext as ServiceInterfaceContext, -index)
            ]
        ]
    }
    
    /**
     * The bottom part of the service interface overview rendering containing all detailed service interface renderings
     * and their connections in a layered layout.
     * 
     * @param serviceInterfaceOverviewContext The overview context for all service interfaces in this subsynthesis.
     */
    private def KNode transformDetailedServiceInterfacesOverview(
        ServiceInterfaceOverviewContext serviceInterfaceOverviewContext) {
        createNode => [
            associateWith(serviceInterfaceOverviewContext)
            configureOverviewLayout
            setLayoutOption(LayeredMetaDataProvider::SPACING_NODE_NODE_BETWEEN_LAYERS, 30.0)
            addInvisibleContainerRendering
            
            // All service interfaces.
            val filteredDetailedServiceInterfaceContexts = SynthesisUtils.filteredServiceInterfaceContexts(
                serviceInterfaceOverviewContext.detailedElements, usedContext)
            children += filteredDetailedServiceInterfaceContexts.flatMap [
                return serviceInterfaceSynthesis.transform(it as ServiceInterfaceContext)
            ]
            
            // Add all implementing service component edges.
            var List<Pair<ServiceComponentContext, ServiceInterfaceContext>>  implementedInterfaceEdges
            switch (usedContext.getProperty(OsgiSynthesisProperties.CURRENT_SERVICE_COMPONENT_VISUALIZATION_MODE)) {
                case PLAIN: {
                    // All service components.
                    val filteredImplementingServiceComponentContexts = serviceInterfaceOverviewContext
                        .implementingServiceComponentContexts
                    children += filteredImplementingServiceComponentContexts.flatMap [
                        return serviceComponentSynthesis.transform(it as ServiceComponentContext)
                    ]
                    implementedInterfaceEdges = serviceInterfaceOverviewContext.implementedInterfaceEdgesPlain
                }
                case IN_BUNDLES: {
                    setLayoutOption(CoreOptions::HIERARCHY_HANDLING, HierarchyHandling.INCLUDE_CHILDREN)
                    // All bundles containing the service components.
                    val filteredBundleContexts = serviceInterfaceOverviewContext.referencedBundleContexts
                    children += filteredBundleContexts.flatMap [
                        return bundleSynthesis.transform(it as BundleContext)
                    ]
                    implementedInterfaceEdges = serviceInterfaceOverviewContext.implementedInterfaceEdgesInBundles
                }
            }
            
            implementedInterfaceEdges.forEach [
                // Connects the service component and -interface via an arrow in UML style,
                // so [component] ---implements---|> [interface]
                val component = key
                val interface = value
                val componentNode = component.node
                val interfaceNode = interface.node
                val componentPort = componentNode.ports.findFirst [
                    data.filter(KIdentifier).head?.id === "implementedServiceInterfaces"
                ]
                val interfacePort = interfaceNode.ports.findFirst [
                    data.filter(KIdentifier).head?.id === "implementingServiceComponents"
                ]
                
                val edge = createEdge(component, interface) => [
                    addImplementingComponentEdgeRendering
                    sourcePort = componentPort
                    targetPort = interfacePort
                    source = componentNode
                    target = interfaceNode
                ]
                componentNode.outgoingEdges += edge
            ]
        ]
    }
    
}