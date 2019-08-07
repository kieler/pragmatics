package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction
import de.scheidtbachmann.osgimodel.visualization.OsgiSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import java.util.HashMap
import org.eclipse.emf.ecore.EObject

/**
 * An abstract action that allows to change the {@link IVisualizationContext} for a model so that the old one is still
 * remembered for undo/redo functionality.
 * An action implementing this class should not override the {@code execute} method, but only the
 * {@code changeVisualization} method to modify the visualization context that will be used during the next synthesis.
 * This action will cause KLighD to issue a new synthesis of the base model that will consider the new changed
 * visualization context.
 * 
 * @author nre
 */
abstract class AbstractVisualizationContextChangingAction implements IAction {
    
    final override execute(ActionContext context) {
        val visualizationContexts = context.viewContext.getProperty(OsgiSynthesisProperties.VISUALIZATION_CONTEXTS)
        val index = context.viewContext.getProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX).intValue
        
        val currentVisualizationContext = visualizationContexts.get(index)
        
        // Make a deep-copy of the current context and store it as the action that can be undone next.
        // To copy the current context, copy from the parent root context ...
        var rootVisualizationContext = currentVisualizationContext
        while (rootVisualizationContext.parentVisualizationContext !== null) {
            rootVisualizationContext = rootVisualizationContext.parentVisualizationContext
        }
        val copiedContexts = new HashMap
        rootVisualizationContext.deepCopy(copiedContexts)
        
        // ... and take the copied reference from that.
        val copiedVisualizationContext = copiedContexts.get(currentVisualizationContext)
        // Remove this visualization context and all after that, a redo after an action is not possible anymore.
        while (visualizationContexts.size > index) {
            visualizationContexts.remove(index)
        }
        // Put the copy at the old index.
        visualizationContexts.add(index, copiedVisualizationContext)
        
        // The visualization context of the element that this action is performed on.
        val modelVisualizationContext = SynthesisUtils.getDomainElement(context) as IVisualizationContext<? extends EObject>
        if (!ContextUtils.isChildContextOrEqual(currentVisualizationContext, modelVisualizationContext)) {
            throw new IllegalStateException("This action is performed on an element that is not currently in the " +
                "currently displayed visualization context:" + this.class
                + "\nSomething during the clone process by the undo action probably went wrong.")
        }
        
        // Change the visualization.
        var newContext = changeVisualization(modelVisualizationContext, context)
        if (newContext === null) {
            newContext = currentVisualizationContext
        }
        
        // Put the old context, that will be updated below at the at index + 1 and remember that new index as the
        // current index.
        visualizationContexts.add(index + 1, newContext)
        context.viewContext.setProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX, index + 1)
        
        return ActionResult.createResult(true).doSynthesis
    }
    
    /**
     * Modifies the visualization context towards a state that represents what this action should perform.
     * 
     * @param modelVisualizationContext The visualization context of the element that this action is performed on.
     * @return The new main visualization context that will be used for the next synthesis or {@code null} if the
     * visualization focus does not change.
     */
    protected abstract def <M extends EObject> IVisualizationContext<?> changeVisualization(IVisualizationContext<M> modelVisualizationContext,
        ActionContext actionContext)
    
}