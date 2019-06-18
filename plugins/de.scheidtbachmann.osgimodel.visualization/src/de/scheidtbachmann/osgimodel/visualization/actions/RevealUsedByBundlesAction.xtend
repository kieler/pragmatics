package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext

/**
 * Reveals and synthesizes the bundles used by any bundle into the KNode surrounding the Bundle node this action
 * is performed on and connects the new bundles with a connecting edge from this bundle's 'requiredBundles' port to
 * the new bundle's 'usedByBundles' port.
 * If all used by bundles are already shown, this action reverses its functionality and removes all bundles again.
 */
class RevealUsedByBundlesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedByBundlesAction"
    
    override protected changeVisualization(IVisualizationContext modelVisualizationContext) {
        // TODO: remove items if all are are already shown
        // The BundleContext element for the element that was clicked on.
        val bundleContext = modelVisualizationContext as BundleContext
        
        // The bundle itself from the context.
        val bundle = bundleContext.bundle
        
        // The bundle overview context this bundle is shown in.
        val bundleOverviewContext = bundleContext.parent as BundleOverviewContext
        
        // The used by bundles that are currently not yet in their detailed view need to be put in that state first.
        val collapsedUsedByBundleContexts = bundleOverviewContext.collapsedBundleContexts.filter [
            bundle.usedByBundle.contains(it.bundle)
        ].toList
        collapsedUsedByBundleContexts.forEach [
            ContextUtils.makeDetailed(bundleOverviewContext, it)
        ]
        
        // The bundle contexts in the overview that the usedByBundle connection can connect to.
        // Use the detailed bundle contexts only, as they are all made detailed above.
        val usedByBundleContexts = bundleOverviewContext.detailedBundleContexts.filter [
            bundle.usedByBundle.contains(it.bundle)
        ]
        
        usedByBundleContexts.forEach [ usedByBundleContext |
            ContextUtils.addRequiredBundleEdge(usedByBundleContext, bundleContext)
        ]

        // The KNode containing the bundle in which the used bundles should be added as well.
//        val containingNode = bundleNode.eContainer as KNode
        
//        val filteredUsedByBundles = SynthesisUtils.filteredBundles(bundle.usedByBundle, context.viewContext).toList
//        
//        // If all bundles are already connected, remove them all. Otherwise, connect them all.
//        if (GenericRevealActionUtil.allConnected(filteredUsedByBundles, bundleNode, false, context, containingNode)) {
//            // TODO: filter these edges by some new Identifier so only the required bundle edges are removed.
//            val edgesToRemove = bundleNode.incomingEdges.immutableCopy
//            edgesToRemove.forEach [ edge |
//                val targetNode = edge.source
//                // Remove the edge.
//                KGraphUtil.removeElement(edge)
//                // TODO: filter these edges as well.
//                if (targetNode.incomingEdges.empty && targetNode.outgoingEdges.empty) {
//                    // If the target node now is not connected anymore, remove it.
//                    if (!containingNode.children.contains(targetNode)) {
//                        throw new IllegalStateException("The target of the given bundle node is not contained in the " +
//                            "containingNode and cannot be removed!")
//                    }
//                    KGraphUtil.removeElement(targetNode)
//                } else {
//                    // If the target node is still connected, at least its connections to usedByBundles is not complete
//                    // anymore, so revert the port color of that node to its default.
//                    val connectedPort = targetNode.ports.findFirst [
//                        it.data.filter(KIdentifier)?.head?.id === "requiredBundles"
//                    ]
//                    if (connectedPort !== null) {
//                        connectedPort.unHighlightAllShown
//                    }
//                }
//            ]
//            // Revert the background color of the clicked node to indicate that not all of its children are shown
//            // anymore.
//            clickedPort.unHighlightAllShown
//        } else {
//            val bundleNodes = GenericRevealActionUtil.revealElements(filteredUsedByBundles, context, containingNode)
//            bundleNodes.forEach [ usedByBundle, usedByBundleNode |
//                connectUsedByEdge(bundleNode, usedByBundleNode, usedByBundle, context)
//            ]
//            
//            // Change the background color of the clicked node to indicate that all its children are now shown.
//            clickedPort.highlightAllShown
//        }
//        
//        if (filteredUsedByBundles.empty) {
//            return ActionResult.createResult(false)
//        } else {
//            return ActionResult.createResult(true)
//        }
    }
    
//    /**
//     * Connects the {@code sourceBundleNode} and the {@code usedByBundleNode} via an arrow in UML style, so
//     * [usedByBundleNode] ----- uses -----> [sourceBundleNode]
//     */
//    def connectUsedByEdge(KNode sourceBundleNode, KNode usedByBundleNode, Bundle usedByBundle, ActionContext context) {
//        val sourceBundlePort = usedByBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "requiredBundles" ]
//        val targetBundlePort = sourceBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "usedByBundles" ]
//        // Do not add this edge, if it is already there.
//        if (sourceBundlePort.node.outgoingEdges.findFirst[edge |
//                edge.target === targetBundlePort.node
//            ] !== null) {
//            return
//        }
//        
//        val edge = createEdge(usedByBundleNode, sourceBundleNode) => [
//            addRequiredBundleEdgeRendering
//            sourcePort = sourceBundlePort
//            targetPort = targetBundlePort
//            source = usedByBundleNode
//            target = sourceBundleNode
//        ]
//        usedByBundleNode.outgoingEdges += edge
//        
//        val filteredRequiredBundles = SynthesisUtils.filteredBundles(usedByBundle.requiredBundles, context.viewContext).toList
//        if (GenericRevealActionUtil.allConnected(filteredRequiredBundles, usedByBundleNode, true, context,
//            sourceBundleNode.parent)) {
//            sourceBundlePort.highlightAllShown
//        }
//    }
    
}