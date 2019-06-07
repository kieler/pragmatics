package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.OsgiDiagramSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import org.eclipse.elk.graph.properties.MapPropertyHolder

/**
 * Removes the focus of the top focused element for the {@link OsgiDiagramSynthesis} and gives it to the second element
 * into the focus stack.
 */
class UnfocusAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.UnfocusAction"
    
    override execute(ActionContext context) {
        // Remove the last list element to reset the main element stack in the view context to the last focused element.
        val focusedElements = context.viewContext.getProperty(OsgiSynthesisProperties.FOCUSED_ELEMENTS)
        
        // If there is no focus, there is nothing to do here.
        if (focusedElements.empty) {
            return ActionResult.createResult(false)
        }
        // If there is only one element in the focus stack currently, this behaves just as the {@link UnfocusAllAction}.
        if (focusedElements.size === 1) {
            val action = KlighdDataManager.getInstance().getActionById(UnfocusAllAction::ID)
            return action.execute(context)
        }
        // Otherwise, remove the top element and give focus to the second element from the top.
        focusedElements.remove(focusedElements.last)
        
        val newFocusedElement = focusedElements.last
        
        // Find out the required synthesis for the new focused element.
        val requiredSynthesis = SynthesisUtils.requiredSynthesis(newFocusedElement)
        
        // Call a synthesis for the new focused element
        val newRoot = LightDiagramServices.translateModel(
            newFocusedElement,
            context.viewContext,
            new MapPropertyHolder => [
                setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, requiredSynthesis)
            ]
        )
        
        // Replace the root node's children.
        val rootNode = context.viewContext.viewModel
        rootNode.children.removeIf [ true ]
        rootNode.children += newRoot.children
        
        return ActionResult.createResult(true)
    }
    
}