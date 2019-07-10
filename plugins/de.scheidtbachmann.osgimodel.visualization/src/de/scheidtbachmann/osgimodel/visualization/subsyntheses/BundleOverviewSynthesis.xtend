package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.SimpleTextType
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all bundles in the given list of bundles.
 * 
 * @author nre
 */
class BundleOverviewSynthesis extends AbstractSubSynthesis<BundleOverviewContext, KNode> {
    @Inject extension KEdgeExtensions
    @Inject extension KNodeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension OsgiStyles
    @Inject SimpleBundleSynthesis simpleBundleSynthesis
    @Inject BundleSynthesis bundleSynthesis
    
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(BundleOverviewContext bundleOverviewContext) {
        return #[
            createNode => [
                associateWith(bundleOverviewContext)
                data += createKIdentifier => [ it.id = bundleOverviewContext.hashCode.toString ]
                if (bundleOverviewContext.expanded) {
                    initiallyExpand
                } else {
                    initiallyCollapse
                }
                setLayoutOption(it, CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
                setLayoutOption(it, CoreOptions::DIRECTION, Direction.DOWN)
                addOverviewRendering("Bundles")
                
                // remove the padding of the invisible container.
                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0))
                
                // Add all simple bundle renderings in a first subgraph (top)
                val collapsedOverviewNode = transformCollapsedBundlesOverview(bundleOverviewContext)
                children += collapsedOverviewNode
                
                // Add all detailed bundle renderings and their connections in a second subgraph (bottom)
                val detailedOverviewNode = transformDetailedBundlesOverview(bundleOverviewContext)
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
     * The top part of the bundle overview rendering containing all collapsed bundle renderings in a box layout.
     * 
     * @param bundleOverviewContext The overview context for all bundles in this subsynthesis.
     */
    private def KNode transformCollapsedBundlesOverview(BundleOverviewContext bundleOverviewContext) {
        val filteredCollapsedBundleContexts = SynthesisUtils.filteredBasicOsgiObjectContexts(
            bundleOverviewContext.collapsedElements, usedContext)
        createNode => [
            associateWith(bundleOverviewContext)
            configureBoxLayout
            addInvisibleContainerRendering
            
            filteredCollapsedBundleContexts.sortBy [
                // The string to sort by. Either the shortened ID or the name.
                switch usedContext.getOptionValue(SIMPLE_TEXT) {
                    case SimpleTextType.Id: {
                        SynthesisUtils.getId(modelElement.uniqueId, usedContext)
                    }
                    case SimpleTextType.Name: {
                        modelElement.descriptiveName
                    }
                } ?: ""
            ].forEach [ collapsedBundleContext, index |
                children += simpleBundleSynthesis.transform(collapsedBundleContext as BundleContext, -index)
            ]
        ]
    }
    
    /**
     * The bottom part of the bundle overview rendering containing all detailed bundle renderings and their connections
     * in a layered layout.
     * 
     * @param bundleOverviewContext The overview context for all bundles in this subsynthesis.
     */
    private def KNode transformDetailedBundlesOverview(BundleOverviewContext bundleOverviewContext) {
        val filteredDetailedBundleContexts = SynthesisUtils.filteredBasicOsgiObjectContexts(
            bundleOverviewContext.detailedElements, usedContext)
        createNode => [
            associateWith(bundleOverviewContext)
            configureOverviewLayout
            addInvisibleContainerRendering
            
            children += filteredDetailedBundleContexts.flatMap [
                return bundleSynthesis.transform(it as BundleContext)
            ]
            
            // Add all required bundle edges.
            bundleOverviewContext.requiredBundleEdges.forEach [
                // Connects the {@code sourceBundleNode} and the {@code usedByBundleNode} via an arrow in UML style,
                // so [usedByBundleNode] ----- uses -----> [sourceBundleNode]
                val requiring = key
                val required = value
                val requiringNode = requiring.node
                val requiredNode = required.node
                val requiringPort = requiringNode.ports.findFirst [
                    data.filter(KIdentifier).head?.id === "requiredBundles"
                ]
                val requiredPort = requiredNode.ports.findFirst [
                    data.filter(KIdentifier).head?.id === "usedByBundles"
                ]
                
                val edge = createEdge(requiring, required) => [
                    addRequiredBundleEdgeRendering
                    sourcePort = requiringPort
                    targetPort = requiredPort
                    source = requiringNode
                    target = requiredNode
                ]
                requiringNode.outgoingEdges += edge
            ]
            
            // Add all used packages edges.
            bundleOverviewContext.usedPackagesEdges.forEach [ connection |
                val sourceBundleNode = connection.sourceBundleContext.node
                val sourceBundlePort = sourceBundleNode.ports.findFirst [
                    data.filter(KIdentifier).head?.id === "importedPackages"
                ]
                val targetBundleNode = connection.targetBundleContext.node
                
                val edge = createEdge(sourceBundleNode, targetBundleNode, connection.usedPackages, connection.product)
                    => [
                    addInternalUsedPackagesBundleEdgeRendering(connection.usedPackages, connection.product, usedContext)
                    sourcePort = sourceBundlePort
                    source = sourceBundleNode
                    target = targetBundleNode
//                    data += createKIdentifier => [ 
//                        it.id = "usedPackageBy:" + connection.product.uniqueId  + "," + 
//                            connection.sourceBundleContext.bundle.uniqueId + "," + 
//                            connection.targetBundleContext.bundle.uniqueId
//                    ]
                ]
                sourceBundleNode.outgoingEdges += edge
            ]
        ]
    }
    
}