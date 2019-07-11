package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import org.eclipse.emf.ecore.EObject

/**
 * Expands the bundles used by any bundle and connects the bundles with an edge from the new bundle's
 * 'requiredBundles' port to this bundle's 'usedByBundles' port. 
 * If all used by bundles are already shown, this action reverses its functionality and removes all bundles again.
 * 
 * @author nre
 */
class RevealUsedByBundlesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = RevealUsedByBundlesAction.name
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // The BundleContext element for the element that was clicked on.
        val bundleContext = modelVisualizationContext as BundleContext
        
        // The bundle itself from the context.
        val bundle = bundleContext.modelElement
        
        // The bundle overview context this bundle is shown in.
        val bundleOverviewContext = bundleContext.parentVisualizationContext as BundleOverviewContext
        
        // The used by bundles that are currently not yet in their detailed view need to be put in that state first.
        val collapsedUsedByBundleContexts = bundleOverviewContext.collapsedElements.filter [
            bundle.usedByBundle.contains(it.modelElement)
        ].toList
        collapsedUsedByBundleContexts.forEach [
            ContextUtils.makeDetailed(bundleOverviewContext, it)
        ]
        
        // The bundle contexts in the overview that the usedByBundle connection can connect to.
        // Use the detailed bundle contexts only, as they are all made detailed above.
        val usedByBundleContexts = bundleOverviewContext.detailedElements.filter [
            bundle.usedByBundle.contains(it.modelElement)
        ].toList
        
        // If all bundles are already connected, remove them all. Otherwise, connect them all.
        if (ContextUtils.allConnected(bundleContext, usedByBundleContexts, bundleOverviewContext, false)) {
            usedByBundleContexts.forEach [ usedByBundleContext |
                ContextUtils.removeRequiredBundleEdge(usedByBundleContext as BundleContext, bundleContext)
            ]
        } else {
            usedByBundleContexts.forEach [ usedByBundleContext |
                ContextUtils.addRequiredBundleEdge(usedByBundleContext as BundleContext, bundleContext)
            ]
        }
        return null
    }
    
}