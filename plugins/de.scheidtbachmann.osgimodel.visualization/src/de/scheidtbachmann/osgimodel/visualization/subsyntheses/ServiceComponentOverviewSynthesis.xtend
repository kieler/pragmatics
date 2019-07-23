package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties.ServiceComponentVisualizationMode
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import java.util.EnumSet
import java.util.List
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction
import org.eclipse.elk.core.options.HierarchyHandling
import org.eclipse.elk.core.options.SizeConstraint

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
    @Inject BundleSynthesis bundleSynthesis
    @Inject ServiceComponentSynthesis serviceComponentSynthesis
    @Inject ServiceInterfaceSynthesis serviceInterfaceSynthesis
    @Inject SimpleServiceComponentSynthesis simpleServiceComponentSynthesis
    
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
                setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
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
            
            val currentVisualizationMode = usedContext.getProperty(OsgiSynthesisProperties.CURRENT_SERVICE_COMPONENT_VISUALIZATION_MODE)
            // All service components.
            if (!serviceComponentOverviewContext.allowInBundleConnections || currentVisualizationMode.equals(
                ServiceComponentVisualizationMode.PLAIN)) {
                filteredCollapsedServiceComponentContexts.sortBy [
                    // The string to sort by. Either the shortened ID or the name.
                    modelElement.name
                ].forEach [ collapsedServiceComponentContext, index |
                    children += simpleServiceComponentSynthesis.transform(collapsedServiceComponentContext as ServiceComponentContext , -index)
                ]
            }
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
        createNode => [
            associateWith(serviceComponentOverviewContext)
            configureOverviewLayout
            addInvisibleContainerRendering
            
            val currentVisualizationMode = usedContext.getProperty(OsgiSynthesisProperties.CURRENT_SERVICE_COMPONENT_VISUALIZATION_MODE)
            // All service components.
            if (!serviceComponentOverviewContext.allowInBundleConnections || currentVisualizationMode.equals(
                ServiceComponentVisualizationMode.PLAIN)) {
                val filteredDetailedServiceComponentContexts = serviceComponentOverviewContext.detailedElements
                children += filteredDetailedServiceComponentContexts.flatMap [
                    return serviceComponentSynthesis.transform(it as ServiceComponentContext)
                ]
            }
            
            // All service interfaces.
            val filteredImplementedServiceInterfaceContexts = serviceComponentOverviewContext
                .implementedServiceInterfaceContexts
            children += filteredImplementedServiceInterfaceContexts.flatMap [
                return serviceInterfaceSynthesis.transform(it as ServiceInterfaceContext)
            ]
            
            // Add all implementing service component edges.
            var List<Pair<ServiceComponentContext, ServiceInterfaceContext>>  implementedInterfaceEdges
            if (serviceComponentOverviewContext.allowInBundleConnections && currentVisualizationMode.equals(
                ServiceComponentVisualizationMode.IN_BUNDLES)) {
                setLayoutOption(CoreOptions::HIERARCHY_HANDLING, HierarchyHandling.INCLUDE_CHILDREN)
                // All bundles cotaining the service components.
                val filteredBundleContexts = serviceComponentOverviewContext.referencedBundleContexts
                children += filteredBundleContexts.flatMap [
                    return bundleSynthesis.transform(it as BundleContext)
                ]
                implementedInterfaceEdges = serviceComponentOverviewContext.implementedInterfaceEdgesInBundles
            } else {
                implementedInterfaceEdges = serviceComponentOverviewContext.implementedInterfaceEdgesPlain
            }
            
            implementedInterfaceEdges.forEach [
                // Connects the service component and -interface via an arrow in UML style,
                // so [component] ---implements--|> [interface]
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