package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.graph.properties.MapPropertyHolder

/**
 * A {@link CollapseExpandAction} that calls the synthesis of the expanded object on first expansion and adds the new
 * synthesized nodes as the children of the expanded node.
 * 
 * The expanded rendering of the node should really not contain much at all, as the dynamically synthesized node should
 * contain all content of the element.
 */
class ReferencedSynthesisExpandAction extends CollapseExpandAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.ReferencedSynthesisExpandAction" 
    
    override execute(ActionContext context) {
        if (context.KNode.children.empty) {
            // If the clicked node has no children yet, invoke the synthesis for Bundles and add them to the node.
//            val modelElement = context.getDomainElement(context.KNode)
            val modelElement = SynthesisUtils.getDomainElement(context)
            val requiredSynthesis = SynthesisUtils.requiredSynthesis(modelElement)
            if (requiredSynthesis !== null) {
                val diagram = LightDiagramServices.translateModel(
                    modelElement,
                    context.viewContext,
                    new MapPropertyHolder => [
                        setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, requiredSynthesis)
                    ]
                )
                context.KNode.children += diagram.children
            }
        }
        super.execute(context)
    }
}