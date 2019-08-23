package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.scheidtbachmann.osgimodel.visualization.context.ReferencedInterfaceEdgeConnection
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import java.util.List

/**
 * A class for common parts of syntheses that are collected in this helper class to avoid duplicate code.
 * Calls to the static methods of this class need to provide the extension instances they use, as this helper class
 * can not be instanciated.
 */
final class OsgiCommonSynthesis {
    
    /** Private constructor. This can not be instanciated. */
    private new() {}
    
    /**
     * Adds edges between components and their implemented interfaces. Assumes that the nodes for the components and
     * interfaces have already been generated previously.
     * 
     * @param implementedInterfaceEdges A list of all component->interface pairs for whose nodes edges should be added.
     * @param edgeExtensions The KEdgeExtensions from the synthesis using this method.
     * @param nodeExtensions The KNodeExtensions from the synthesis using this method.
     * @param styles The OsgiStyles extension from the synthesis using this method.
     */
    def static addImplementedInterfaceEdges(
        List<Pair<ServiceComponentContext, ServiceInterfaceContext>> implementedInterfaceEdges,
        extension KEdgeExtensions edgeExtensions, extension KNodeExtensions nodeExtensions,
        extension OsgiStyles styles) {
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
    }
    
    /**
     * Adds edges between components and their referenced interfaces. Assumes that the nodes for the components and
     * interfaces have already been generated previously.
     * 
     * @param referencedInterfaceEdges A list of all components and interfaces for whose nodes edges should be
     * generated and the reference relation they stand in.
     * @param edgeExtensions The KEdgeExtensions from the synthesis using this method.
     * @param nodeExtensions The KNodeExtensions from the synthesis using this method.
     * @param styles The OsgiStyles extension from the synthesis using this method.
     */
    def static addReferencedInterfaceEdges(List<ReferencedInterfaceEdgeConnection> referencedInterfaceEdges,
        extension KEdgeExtensions edgeExtensions, extension KNodeExtensions nodeExtensions,
        extension OsgiStyles styles, ViewContext usedContext) {
        referencedInterfaceEdges.forEach [
            // Connects the service component and -interface via an arrow.
            val component = it.serviceComponentContext
            val interface = it.serviceInterfaceContext
            val reference = it.reference
            val componentNode = component.node
            val interfaceNode = interface.node
            val componentPort = componentNode.ports.findFirst [
                data.filter(KIdentifier).head?.id === "referencedServiceInterfaces"
            ]
            val interfacePort = interfaceNode.ports.findFirst [
                data.filter(KIdentifier).head?.id === "referencingServiceComponents"
            ]
            
            val edge = createEdge(component, interface, reference) => [
                addReferencedInterfaceEdgeRendering(reference, usedContext)
                source = interfaceNode
                target = componentNode
                sourcePort = interfacePort
                targetPort = componentPort
            ]
            interfaceNode.outgoingEdges += edge
        ]
    }
    
}