package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.visualization.OsgiDiagramSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import java.util.ArrayList
import org.eclipse.elk.graph.properties.MapPropertyHolder

/**
 * Undoes the last action performed on the view model.
 */
class UndoAction implements IAction {
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.UndoAction"
    
    override execute(ActionContext context) {
//        // If nothing is focused, this action does nothing.
//        if(context.viewContext.getProperty(OsgiSynthesisProperties.FOCUSED_ELEMENTS).empty) {
//            return ActionResult.createResult(false)
//        }
//        
//        // Put an empty list as the main element stack in the view context to reset the main element.
//        context.viewContext.setProperty(OsgiSynthesisProperties.FOCUSED_ELEMENTS, new ArrayList<Object>)
//        
//        // Get the root OsgiProject.
//        val rootNode = context.viewContext.viewModel
//        val osgiProject = context.viewContext.inputModel
//        if (!(osgiProject instanceof OsgiProject)) {
//            throw new IllegalStateException("The model not associated to an osgi project!")
//        }
//        
//        // Call the synthesis of the root osgi project again and put its result as the new view model.
//        val newRoot = LightDiagramServices.translateModel(
//            osgiProject,
//            context.viewContext,
//            new MapPropertyHolder => [
//                setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, "de.scheidtbachmann.osgimodel.visualization.OsgiDiagramSynthesis")
//            ]
//        )
//        
//        // Replace the root node's children.
//        rootNode.children.removeIf [ true ]
//        rootNode.children += newRoot.children
        
        return ActionResult.createResult(true)
    }
    
}