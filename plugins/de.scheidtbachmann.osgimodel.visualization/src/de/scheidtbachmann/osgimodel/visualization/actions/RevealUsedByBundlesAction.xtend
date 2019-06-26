package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import org.eclipse.emf.ecore.EObject

/**
 * Reveals and synthesizes the bundles used by any bundle into the KNode surrounding the Bundle node this action
 * is performed on and connects the new bundles with a connecting edge from this bundle's 'requiredBundles' port to
 * the new bundle's 'usedByBundles' port.
 * If all used by bundles are already shown, this action reverses its functionality and removes all bundles again.
 * 
 * @author nre
 */
class RevealUsedByBundlesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedByBundlesAction"
    
    override protected <M extends EObject> IVisualizationContext<?>
    changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // The BundleContext element for the element that was clicked on.
        val bundleContext = modelVisualizationContext as BundleContext
        
        // The bundle itself from the context.
        val bundle = bundleContext.bundle
        
        // The bundle overview context this bundle is shown in.
        val bundleOverviewContext = bundleContext.parent as BundleOverviewContext
        
        // The used by bundles that are currently not yet in their detailed view need to be put in that state first.
        val collapsedUsedByBundleContexts = bundleOverviewContext.collapsedBundleContexts.filter [
            bundle.usedByBundle.contains(it.bundle)
        ].toList
        collapsedUsedByBundleContexts.forEach [
            ContextUtils.makeDetailed(bundleOverviewContext, it)
        ]
        
        // The bundle contexts in the overview that the usedByBundle connection can connect to.
        // Use the detailed bundle contexts only, as they are all made detailed above.
        val usedByBundleContexts = bundleOverviewContext.detailedBundleContexts.filter [
            bundle.usedByBundle.contains(it.bundle)
        ].toList
        
        // If all bundles are already connected, remove them all. Otherwise, connect them all.
        if (ContextUtils.allConnected(bundleContext, usedByBundleContexts, bundleOverviewContext, false)) {
            usedByBundleContexts.forEach [ usedByBundleContext |
                ContextUtils.removeRequiredBundleEdge(usedByBundleContext, bundleContext)
            ]
        } else {
            usedByBundleContexts.forEach [ usedByBundleContext |
                ContextUtils.addRequiredBundleEdge(usedByBundleContext, bundleContext)
            ]
        }
        return null
    }
    
}