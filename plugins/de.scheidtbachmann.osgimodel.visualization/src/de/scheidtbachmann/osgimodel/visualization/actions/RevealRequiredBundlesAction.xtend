package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext

/**
 * Reveals and synthesizes the required bundles of any bundle into the KNode surrounding the Bundle node this action
 * is performed on and connects the new bundles with a connecting edge from the new bundle's 'usedByBundles' port to
 * this bundle's 'requiredBundles' port. 
 * If all required bundles are already shown, this action reverses its functionality and removes all bundles again.
 * 
 * @author nre
 */
class RevealRequiredBundlesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealRequiredBundlesAction"
    
    override protected <M> changeVisualization(IVisualizationContext<M> modelVisualizationContext, ActionContext actionContext) {
        // The BundleContext element for the element that was clicked on.
        val bundleContext = modelVisualizationContext as BundleContext
        
        // The bundle itself from the context.
        val bundle = bundleContext.bundle
        
        // The bundle overview context this bundle is shown in.
        val bundleOverviewContext = bundleContext.parent as BundleOverviewContext
        
        // The required bundles that are currently not yet in their detailed view need to be put in that state first.
        val collapsedRequiredBundleContexts = bundleOverviewContext.collapsedBundleContexts.filter [
            bundle.requiredBundles.contains(it.bundle)
        ].toList
        collapsedRequiredBundleContexts.forEach [
            ContextUtils.makeDetailed(bundleOverviewContext, it)
        ]
        
        // The bundle contexts in the overview that the requiredBundle connection can connect to.
        // Use the detailed bundle contexts only, as they are all made detailed above.
        val requiredBundleContexts = bundleOverviewContext.detailedBundleContexts.filter [
            bundle.requiredBundles.contains(it.bundle)
        ].toList
        
        // If all bundles are already connected, remove them all. Otherwise, connect them all.
        if (ContextUtils.allConnected(bundleContext, requiredBundleContexts, bundleOverviewContext, true)) {
            requiredBundleContexts.forEach [ requiredBundleContext |
                ContextUtils.removeRequiredBundleEdge(bundleContext, requiredBundleContext)
            ]
        } else {
            requiredBundleContexts.forEach [ requiredBundleContext |
                ContextUtils.addRequiredBundleEdge(bundleContext, requiredBundleContext)
            ]
        }
    }
    
}