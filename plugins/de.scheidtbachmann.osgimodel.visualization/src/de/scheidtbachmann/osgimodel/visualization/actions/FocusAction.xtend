package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.graph.properties.MapPropertyHolder

/**
 * Focuses the element this action is issued on and replaces the view model with a newly synthesized version of the
 * focused element. Uses the {@link OsgiSynthesisProperties#FOCUSED_ELEMENTS} property on the top level element in the
 * view context to persist the focused state.
 */
class FocusAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.FocusAction"
    
    override execute(ActionContext context) {
        // The Bundle element itself that was clicked on.
        val element = SynthesisUtils.getDomainElement(context)
//        val element = context.getDomainElement(context.KNode)
        
        // Push the object to be the main element in the view context.
        val focusedElementStack = context.viewContext.getProperty(OsgiSynthesisProperties.FOCUSED_ELEMENTS)
        focusedElementStack.add(element)
        
        // Synthesize the now top level element in the focusedStack and put it as the root element in the view.
        val requiredSynthesis = SynthesisUtils.requiredSynthesis(element)
        val newRoot = LightDiagramServices.translateModel(
            element,
            context.viewContext,
            new MapPropertyHolder => [
                setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, requiredSynthesis)
            ]
        )
        
        // Find the previous root node
        val rootNode = context.viewContext.getViewModel()
        
        // Replace the root node's children.
        rootNode.children.removeIf [ true ]
        rootNode.children += newRoot.children
        
        return ActionResult.createResult(true)
    }
    
}