package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext

/**
 * Reveals and synthesizes the required bundles of any bundle into the KNode surrounding the Bundle node this action
 * is performed on and connects the new bundles with a connecting edge from the new bundle's 'usedByBundles' port to
 * this bundle's 'requiredBundles' port. 
 * If all required bundles are already shown, this action reverses its functionality and removes all bundles again.
 * 
 * @author nre
 */
class RevealRequiredBundlesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealRequiredBundlesAction"
    
    override protected changeVisualization(IVisualizationContext modelVisualizationContext, ActionContext actionContext) {
        // TODO: remove items if all are are already shown
        // The BundleContext element for the element that was clicked on.
        val bundleContext = modelVisualizationContext as BundleContext
        
        // The bundle itself from the context.
        val bundle = bundleContext.bundle
        
        // The bundle overview context this bundle is shown in.
        val bundleOverviewContext = bundleContext.parent as BundleOverviewContext
        
        // The required bundles that are currently not yet in their detailed view need to be put in that state first.
        val collapsedRequiredBundleContexts = bundleOverviewContext.collapsedBundleContexts.filter [
            bundle.requiredBundles.contains(it.bundle)
        ].toList
        collapsedRequiredBundleContexts.forEach [
            ContextUtils.makeDetailed(bundleOverviewContext, it)
        ]
        
        // The bundle contexts in the overview that the requiredBundle connection can connect to.
        // Use the detailed bundle contexts only, as they are all made detailed above.
        val requiredBundleContexts = bundleOverviewContext.detailedBundleContexts.filter [
            bundle.requiredBundles.contains(it.bundle)
        ]
        
        requiredBundleContexts.forEach [ requiredBundleContext |
            ContextUtils.addRequiredBundleEdge(bundleContext, requiredBundleContext)
        ]

        // The KNode containing the bundle in which the used bundles should be added as well.
//        val containingNode = bundleNode.eContainer as KNode
//        
//        val filteredRequiredBundles = SynthesisUtils.filteredBundles(bundle.requiredBundles, context.viewContext).toList
//        
//        // If all bundles are already connected, remove them all. Otherwise, connect them all.
//        if (GenericRevealActionUtil.allConnected(filteredRequiredBundles, bundleNode, true, context, containingNode)) {
//            // TODO: filter these edges by some new Identifier so only the required bundle edges are removed.
//            val edgesToRemove = bundleNode.outgoingEdges.immutableCopy
//            edgesToRemove.forEach [ edge |
//                val targetNode = edge.target
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
//                        it.data.filter(KIdentifier)?.head?.id === "usedByBundles"
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
//            val bundleNodes = GenericRevealActionUtil.revealElements(filteredRequiredBundles, context, containingNode)
//            bundleNodes.forEach [ requiredBundle, requiredBundleNode |
//                connectRequiredEdge(bundleNode, requiredBundleNode, requiredBundle, context)
//            ]
//            
//            // Change the background color of the clicked node to indicate that all its children are now shown.
//            clickedPort.highlightAllShown
//        }
        
//        if (filteredRequiredBundles.empty) {
//            return ActionResult.createResult(false)
//        } else {
//            return ActionResult.createResult(true)
//        }
    }
    
//    /**
//     * Connects the {@code sourceBundleNode} and the {@code requiredBundleNode} via an arrow in UML style, so
//     * [sourceBundleNode] ----- uses -----> [requiredBundleNode]
//     */
//    def connectRequiredEdge(KNode sourceBundleNode, KNode requiredBundleNode, Bundle requiredBundle, ActionContext context) {
//        val sourceBundlePort = sourceBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "requiredBundles" ]
//        val targetBundlePort = requiredBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "usedByBundles" ]
//        // Do not add this edge, if it is already there.
//        if (sourceBundlePort.node.outgoingEdges.findFirst[edge |
//                edge.target === targetBundlePort.node
//            ] !== null) {
//            return
//        }
//        
//        val edge = createEdge(sourceBundleNode, requiredBundleNode) => [
//            addRequiredBundleEdgeRendering
//            sourcePort = sourceBundlePort
//            targetPort = targetBundlePort
//            source = sourceBundleNode
//            target = requiredBundleNode
//        ]
//        sourceBundleNode.outgoingEdges += edge
//        
//        val filteredUsedByBundles = SynthesisUtils.filteredBundles(requiredBundle.usedByBundle, context.viewContext).toList
//        if (GenericRevealActionUtil.allConnected(filteredUsedByBundles, requiredBundleNode, false, context,
//            sourceBundleNode.parent)) {
//            targetBundlePort.highlightAllShown
//        }
//    }
}