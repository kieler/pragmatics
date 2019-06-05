package de.scheidtbachmann.osgimodel.visualization.actions

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.actions.SynthesizingAction
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles

/**
 * Closes the clicked element and removes it and all its connecting edges from the view model.
 */
class CloseAction extends SynthesizingAction {
    @Inject extension OsgiStyles
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.CloseAction"
    
    override execute(ActionContext context) {
        // The node that should be removed.
        val node = context.KNode
        
        // TODO: filter these edges / ports
        val ports = node.incomingEdges.map [ sourcePort ] + node.outgoingEdges.map [ targetPort ]
        ports.forEach [ unHighlightAllShown ]
        
        // The util function does exactly what this action wants to achieve.
        KGraphUtil.removeElement(node)
        
        return ActionResult.createResult(true)
    }
}