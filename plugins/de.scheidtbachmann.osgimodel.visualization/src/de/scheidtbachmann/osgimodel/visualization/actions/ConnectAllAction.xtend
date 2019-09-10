package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.KlighdDataManager
import de.scheidtbachmann.osgimodel.visualization.context.BundleCategoryContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.FeatureContext
import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.PackageObjectContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceComponentContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceContext
import org.eclipse.emf.ecore.EObject

/**
 * Calls the corresponding reveal*Actions for all elements visualized in this overview.
 * 
 * @author nre
 */
class ConnectAllAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = ConnectAllAction.name
    
    override <M extends EObject> changeVisualization(IVisualizationContext<M> modelVisualizationContext,
        ActionContext actionContext) {
        val revealImplementedServiceInterfacesAction = KlighdDataManager.getInstance()
            .getActionById(RevealImplementedServiceInterfacesAction.ID) as RevealImplementedServiceInterfacesAction
        val revealImplementingServiceComponentsAction = KlighdDataManager.getInstance()
            .getActionById(RevealImplementingServiceComponentsAction.ID) as RevealImplementingServiceComponentsAction
        val revealReferencedServiceInterfacesAction = KlighdDataManager.getInstance()
            .getActionById(RevealReferencedServiceInterfacesAction.ID) as RevealReferencedServiceInterfacesAction
        val revealReferencingServiceComponentsAction = KlighdDataManager.getInstance()
            .getActionById(RevealReferencingServiceComponentsAction.ID) as RevealReferencingServiceComponentsAction
        val revealRequiredBundlesAction = KlighdDataManager.getInstance()
            .getActionById(RevealRequiredBundlesAction.ID) as RevealRequiredBundlesAction
        val revealUsedByBundlesAction = KlighdDataManager.getInstance()
            .getActionById(RevealUsedByBundlesAction.ID) as RevealUsedByBundlesAction
        val revealUsedPackagesAction = KlighdDataManager.getInstance()
            .getActionById(RevealUsedPackagesAction.ID) as RevealUsedPackagesAction
        
        if (!(modelVisualizationContext instanceof IOverviewVisualizationContext)) {
            throw new IllegalStateException("The ConnectAllAction is only callable on IOverviewVisualizationContexts,"
                + " but was called on a " + modelVisualizationContext.class)
        }
        val ovc = modelVisualizationContext as IOverviewVisualizationContext<? extends EObject>
        
        // Expand all context not that are not yet in their detailed form.
        val collapsedContexts = ovc.collapsedElements.clone
        collapsedContexts.forEach [ collapsed |
            ContextUtils.makeDetailed(ovc as IOverviewVisualizationContext<EObject>,
                collapsed as IVisualizationContext<EObject>)
        ]
        
        // For each child context, call actions on all their connections.
        // Also remember all elements added later so that for them all connections can be shown as well // TODO: needed?
        ovc.childContexts.clone.forEach [ childContext |
            switch (childContext) {
                BundleCategoryContext: {
                    // Do nothing.
                }
                BundleContext: {
                    if (ovc instanceof BundleOverviewContext) {
                        // Connect all required and requiring bundles and the required packages.
                        revealRequiredBundlesAction.changeVisualization(childContext, actionContext)
                        revealUsedByBundlesAction  .changeVisualization(childContext, actionContext)
                        revealUsedPackagesAction   .changeVisualization(childContext, actionContext)
                    }
                }
                FeatureContext: {
                    // Do nothing.
                }
                PackageObjectContext: {
                    // Do nothing.
                }
                ProductContext: {
                    // Do nothing.
                }
                ServiceComponentContext: {
                    // Connect all implemented and referenced service interfaces.
                    revealImplementedServiceInterfacesAction.changeVisualization(childContext, actionContext)
                    revealReferencedServiceInterfacesAction .changeVisualization(childContext, actionContext)
                }
                ServiceInterfaceContext: {
                    // Connect all implementing and referencing service components.
                    revealImplementingServiceComponentsAction.changeVisualization(childContext, actionContext)
                    revealReferencingServiceComponentsAction .changeVisualization(childContext, actionContext)
                }
                default: {
                    throw new IllegalStateException("Unknown child context. " + childContext.class)
                }
            }
        ]
        return null
    }

    
}