package de.scheidtbachmann.osgimodel.visualization.actions

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.actions.SynthesizingAction
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.LineStyle
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.Bundle
import java.util.ArrayList
import org.eclipse.elk.graph.properties.MapPropertyHolder

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
        val bundle = context.getDomainElement(bundleNode) as Bundle

        // The KNode containing the bundle in which the used bundles should be added as well.
        val containingNode = bundleNode.eContainer as KNode
        
        bundle.requiredBundles.forEach[ requiredBundle |
            // Only add this bundle to the context if it has not been added before.
            val oldBundleNode = containingNode.children.findFirst[ alreadySynthesizedNodes |
                context.getDomainElement(alreadySynthesizedNodes) === requiredBundle
            ]
            if (oldBundleNode === null) {
                val newBundleContainer = LightDiagramServices.translateModel(
                    requiredBundle,
                    context.viewContext,
                    new MapPropertyHolder => [
                        setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, "de.scheidtbachmann.osgimodel.visualization.BundleSynthesis")
                    ]
                )
                val newBundleNodes = new ArrayList<KNode>
                newBundleNodes.addAll(newBundleContainer.children)
                newBundleNodes.forEach[ newBundleNode |
                    // If the new bundle node is in fact the representation of the required bundle, look for its
                    // used by bundle port and connect an edge to that.
//                        if (context.getDomainElement(newBundleNode) === requiredBundle) {
                        connectRequiredEdge(bundleNode, newBundleNode)
//                        }
                containingNode.children += newBundleNodes
                ]
            } else {
                // Otherwise, just connect the new edge.
                connectRequiredEdge(bundleNode, oldBundleNode)
            }
        ]
        if (bundle.usedByBundle.empty) {
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
        
        val edge = createEdge(sourceBundleNode, requiredBundleNode) => [
            addPolyline => [
                addHeadArrowDecorator => [
                    lineWidth = 1
                    background = "lack".color
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