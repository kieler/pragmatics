package de.scheidtbachmann.osgimodel.visualization.actions

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.actions.SynthesizingAction
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.LineStyle
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

/**
 * Reveals and synthesizes the required bundles of any bundle into the KNode surrounding the Bundle node this action
 * is performed on and connects the new bundles with a connecting edge from the new bundle's 'usedByBundles' port to
 * this bundle's 'requiredBundles' port. 
 */
class RevealRequiredBundlesAction extends SynthesizingAction {
    @Inject extension KColorExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KRenderingExtensions
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealRequiredBundlesAction"
    
    override execute(ActionContext context) {
        // TODO: On the second call of this action maybe remove all required bundles again.
        // The KPort that was clicked on.
        val clickedPort = context.KGraphElement as KPort

        // The KNode representing the Bundle that should add the used bundles.
        val bundleNode = clickedPort.eContainer as KNode

        // The Bundle element itself that was clicked on.
//        val bundle = context.getDomainElement(bundleNode) as Bundle
        val bundle = SynthesisUtils.getDomainElement(context) as Bundle

        // The KNode containing the bundle in which the used bundles should be added as well.
        val containingNode = bundleNode.eContainer as KNode
        
        val filteredRequiredBundles = SynthesisUtils.filteredBundles(bundle.requiredBundles, context.viewContext).toList
        
        val bundleNodes = GenericRevealActionUtil.revealElements(filteredRequiredBundles, context, containingNode)
        bundleNodes.forEach [ requiredBundleNode |
            connectRequiredEdge(bundleNode, requiredBundleNode)
        ]
        
        // Change the background color of the clicked node to indicate that all its children are now shown.
        clickedPort.data.filter(KRendering).forEach [
            background = "OliveDrab".color
            selectionBackground = "OliveDrab".color
        ]
        
        if (filteredRequiredBundles.empty) {
            return ActionResult.createResult(false)
        } else {
            return ActionResult.createResult(true)
        }
    }
    
    /**
     * Connects the {@code sourceBundleNode} and the {@code requiredBundleNode} via an arrow in UML style, so
     * [sourceBundleNode] ----- uses -----> [requiredBundleNode]
     */
    def connectRequiredEdge(KNode sourceBundleNode, KNode requiredBundleNode) {
        val sourceBundlePort = sourceBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "requiredBundles" ]
        val targetBundlePort = requiredBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "usedByBundles" ]
        // Do not add this edge, if it is already there.
        if (sourceBundlePort.node.outgoingEdges.findFirst[edge |
                edge.target === targetBundlePort.node
            ] !== null) {
            return
        }
        
        val edge = createEdge(sourceBundleNode, requiredBundleNode) => [
            addPolyline => [
                addHeadArrowDecorator => [
                    lineWidth = 1
                    background = "black".color
                    foreground = "black".color
                ]
                lineStyle = LineStyle.DASH
            ]
            sourcePort = sourceBundlePort
            targetPort = targetBundlePort
            source = sourceBundleNode
            target = requiredBundleNode
        ]
        sourceBundleNode.outgoingEdges += edge
    }
}