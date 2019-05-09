package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.Product
import org.eclipse.elk.graph.properties.MapPropertyHolder

class ReferencedSynthesisExpandAction extends CollapseExpandAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.ReferencedSynthesisExpandAction" 
    
    override execute(ActionContext context) {
        if (context.KNode.children.empty) {
            // If the clicked node has no children yet, invoke the synthesis for Bundles and add them to the node.
            val modelElement = context.getDomainElement(context.KNode)
            val requestedSynthesis = switch modelElement {
                case modelElement instanceof Bundle: "de.scheidtbachmann.osgimodel.visualization.BundleSynthesis"
                case modelElement instanceof Feature: "de.scheidtbachmann.osgimodel.visualization.FeatureSynthesis" // TODO: implement these syntheses
                case modelElement instanceof Product: "de.scheidtbachmann.osgimodel.visualization.ProductSynthesis"
            }
            if (requestedSynthesis !== null) {
                val diagram = LightDiagramServices.translateModel(
                    modelElement,
                    context.viewContext,
                    new MapPropertyHolder => [
                        setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, requestedSynthesis)
                    ]
                )
                context.KNode.children += diagram.children
            }
        }
        super.execute(context)
    }
}