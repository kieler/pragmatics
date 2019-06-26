package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import java.util.List
import org.eclipse.emf.ecore.EObject

/**
 * Util class that contains some static methods commonly used in the OSGi synthesis for modifying the visualization
 * context.
 * 
 * @author nre
 */
class ContextUtils {
    
    /**
     * Returns true if {@code childContext} is a child visualization context contained in the {@code rootContext} or the
     * contexts are equal.
     * Uses the {@link IVisualizationContext#getParentVisualizationContext()} to find out if it is contained.
     * 
     * @param rootContext The root context that may contain the child context.
     * @param childContext The child context that should be checked if it is contained in the rootContext.
     * @return If {@code rootContext} is a parent of {@code childContext}.
     */
    def static boolean isChildContextOrEqual(IVisualizationContext<?> rootContext, IVisualizationContext<?> childContext) {
        if (rootContext === childContext) {
            return true
        }
        var currentContext = childContext
        while(currentContext !== null) {
            if (currentContext === rootContext) {
                return true
            }
            currentContext = currentContext.parentVisualizationContext
        }
        return false
    }
    
    /**
     * Makes the {@code collapsedContext} that is a collapsed visualization context within {@code overviewContext}
     * so it is detailed and handles the initialization of the child visualization contexts of that now detailed
     * context.
     * 
     * @param overviewContext The parent overview context that contains the collapsed context in its collapsedElements
     * field.
     * @param collapsedContext The context that is now a collapsed element in the parent overview and should be put in
     * the detailed elements as well as be initialized for its child contexts.
     */
    def static <M extends EObject> void makeDetailed(IOverviewVisualizationContext<M> overviewContext, IVisualizationContext<M> collapsedContext) {
        // this element was previously collapsed, so put it in the detailed list now and initialize its child
        // visualization contexts.
        overviewContext.collapsedElements.remove(collapsedContext)
        // Only this cast will allow to add the context. We know this adding is type-safe, as the collapsed- and
        // the detailed elements list are always of the same type. If they are not, the collapsed/detailed state
        // here would not make any sense.
        (overviewContext.detailedElements as List<IVisualizationContext<M>>).add(collapsedContext)
        collapsedContext.initializeChildVisualizationContexts
    }
    
    /**
     * Collapses the {@code expandedContext} that is an expanded visualization context within {@code overviewContext}
     * so it is collapsed after this method.
     * Also removes any edges connected to the now collapsed element.
     * 
     * @param overviewContext The parent overview context that contains the expanded context in its detailedElements
     * field.
     * @param detailedContext The context that is now a detailed element in the parent overview and should be put in
     * the collapsed elements.
     */
    def static <M extends EObject> void collapse(IOverviewVisualizationContext<M> overviewContext, IVisualizationContext<M> detailedContext) {
        // this element was previously detailed, so put it in the collapsed list now.
        overviewContext.detailedElements.remove(detailedContext)
        // Only this cast will allow to add the context. We know this adding is type-safe, as the collapsed- and
        // the detailed elements list are always of the same type. If they are not, the collapsed/detailed state
        // here would not make any sense.
        (overviewContext.collapsedElements as List<IVisualizationContext<M>>).add(detailedContext)
        
        // Remove all edges incident to the now collapsed context.
        removeEdges(overviewContext, detailedContext)
    }
    
    /**
     * Removes all edges incident to the context.
     * 
     * @param overviewContext The overview context containing the edge
     * @param context The context of that all edges should be removed.
     */
    def dispatch static void removeEdges(BundleOverviewContext overviewContext, BundleContext context) {
        val requiredBundlesView = overviewContext.requiredBundleEdges.clone
        requiredBundlesView.forEach [
            if (key === context || value === context) {
                overviewContext.requiredBundleEdges.remove(it)
                key.allRequiredBundlesShown = false
                value.allRequiringBundlesShown = false
            }
        ]
        val usedPackagesView = overviewContext.usedPackagesEdges.clone
        usedPackagesView.forEach [
            if (sourceBundleContext === context || targetBundleContext === context) {
                overviewContext.usedPackagesEdges.remove(it)
                sourceBundleContext.allUsedPackagesShown = false
            }
        ]
    }
    
    /**
     * Removes all edges incident to the context.
     * 
     * @param overviewContext The overview context containing the edge
     * @param context The context of that all edges should be removed.
     */
    def dispatch static void removeEdges(ProductOverviewContext overviewContext, ProductContext context) {
        // There are no edges in product overview contexts. So do nothing.
    }
    
    /**
     * Removes all edges incident to the context.
     * 
     * @param overviewContext The overview context containing the edge
     * @param context The context of that all edges should be removed.
     */
    def dispatch static void removeEdges(ServiceInterfaceOverviewContext overviewContext, ServiceInterfaceContext context) {
        // There are no edges in service interface overview contexts. So do nothing.
    }
    
    /**
     * Adds a required bundle edge to the parent bundle overview context of the two given contexts.
     * The direction of the edge indicates that the bundle of the {@code requiringContext} requires the bundle of the
     * {@code requiredContext}.
     * [requiring] ---requires---> [required]
     * 
     * @param requiringContext The bundle context with the bundle requiring the other bundle.
     * @param requiredContext The bundle context with the bundle required by the other bundle.
     */
    def static void addRequiredBundleEdge(BundleContext requiringContext, BundleContext requiredContext) {
        val parentContext = requiringContext.parentVisualizationContext as BundleOverviewContext
        if (requiredContext.parentVisualizationContext !== parentContext) {
            throw new IllegalArgumentException("The requiring and the required context both have to have the same " +
                "parent context!")
        }
        // Only if this edge does not exist yet, add it to the list of required bundle edges.
        if (!parentContext.requiredBundleEdges.exists [ key === requiringContext && value === requiredContext ]) {
            parentContext.requiredBundleEdges += requiringContext -> requiredContext
            
            // Check for both the requiring bundle and the required bundle if all connections are now shown in the 
            // parent context. If they are, remember it in the corresponding bundle context.
            // Requiring context:
            if (requiringContext.modelElement.requiredBundles.forall [ requiredBundle |
                !parentContext.modelElement.contains(requiredBundle) ||
                parentContext.requiredBundleEdges.exists [ key === requiringContext && value.modelElement === requiredBundle ]
            ]) {
                requiringContext.allRequiredBundlesShown = true
            }
            // Required context:
            if (requiredContext.modelElement.usedByBundle.forall [ requiringBundle |
                !parentContext.modelElement.contains(requiringBundle) ||
                parentContext.requiredBundleEdges.exists [ key.modelElement === requiringBundle && value === requiredContext ]
            ]) {
                requiredContext.allRequiringBundlesShown = true
            }
        }
    }
    
    /**
     * Removes a required bundle edge of the parent bundle overview context of the two given contexts.
     * 
     * @param requiringContext The bundle context with the bundle requiring the other bundle.
     * @param requiredContext The bundle context with the bundle required by the other bundle.
     */
    def static removeRequiredBundleEdge(BundleContext requiringContext, BundleContext requiredContext) {
        val parentContext = requiringContext.parentVisualizationContext as BundleOverviewContext
        if (requiredContext.parentVisualizationContext !== parentContext) {
            throw new IllegalArgumentException("The requiring and the required context both have to have the same " +
                "parent context!")
        }
        parentContext.requiredBundleEdges.removeIf [ key === requiringContext && value === requiredContext ]
        
        // Mark for both the requiring bundle and the required bundle that not all connections are shown in the 
        // parent context anymore. Remember that in the corresponding bundle context.
        requiringContext.allRequiredBundlesShown = false
        requiredContext.allRequiringBundlesShown = false
    }
    
    /**
     * Adds a new edge to the parent bundle context of the source- and target bundle context that indicates which
     * packages are used by a bundle and by which bundle they are provided in the given product context.
     * 
     * @param sourceBundleContext The bundle context where the edge starts.
     * @param usedPackages The packages required by the source bundle and provided by the target bundle.
     * @param product The product in that this relation holds.
     * @param targetBundleContext The bundle context where the edge ends.
     */
    def static void addUsedPackagesEdge(BundleContext sourceBundleContext, List<PackageObject> usedPackages,
        Product product, BundleContext targetBundleContext) {
        val parentContext = sourceBundleContext.parentVisualizationContext as BundleOverviewContext
        if (targetBundleContext.parentVisualizationContext !== parentContext) {
            throw new IllegalArgumentException("The source and target bundle contexts both have to have the same " +
                "parent context!")
        }
        // Only if this edge does not exist yet, add it to the list of used packages edges.
        if (!parentContext.usedPackagesEdges.exists [
            it.sourceBundleContext === sourceBundleContext &&
            it.product === product &&
            it.targetBundleContext === targetBundleContext
        ]) {
            parentContext.usedPackagesEdges += new UsedPackagesEdgeConnection(sourceBundleContext, usedPackages,
                product, targetBundleContext)
        }
    }
    
    /**
     * Determines whether all edges are displayed that could be connected to the clicked port.
     * 
     * @param clickedContext The context for that should be checked, if all element representations are connected to it.
     * @param possiblyConnectedContexts The list of elements that could be connected.
     * @param overviewContext The parent context in which the connection status should be checked.
     * @param isSource If the {@code clickedContext} should be checked if it is the source or the target in the
     * potential connection.
     */
    def static boolean allConnected(IVisualizationContext<Bundle> clickedContext, List<? extends IVisualizationContext<Bundle>> possiblyConnectedContexts,
        BundleOverviewContext overviewContext, boolean isSource) {
        val conntectedContexts = if (isSource) {
            overviewContext.requiredBundleEdges.filter [ requiredBundleEdge |
                requiredBundleEdge.key === clickedContext
            ].map[it.value].toList
        } else {
            overviewContext.requiredBundleEdges.filter [ requiredBundleEdge |
                requiredBundleEdge.value === clickedContext
            ].map[it.key].toList
        }
        
        return possiblyConnectedContexts.size === conntectedContexts.size &&
            possiblyConnectedContexts.containsAll(conntectedContexts)
    }
    
    /**
     * Determines whether the {@code project} is the root model this {@code context} comes from.
     * 
     * @param context The visualization context in question.
     * @param project The potential root model.
     * @return If the context comes from the project.
     */
    def static boolean isRootModel(IVisualizationContext<?> context, OsgiProject project) {
        var IVisualizationContext<?> currentContext = context
        while(currentContext.parentVisualizationContext !== null) {
            currentContext = currentContext.parentVisualizationContext
        }
        return currentContext.modelElement === project
    }
    
    
}