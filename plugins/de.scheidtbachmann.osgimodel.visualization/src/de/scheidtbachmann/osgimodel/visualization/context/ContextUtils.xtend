package de.scheidtbachmann.osgimodel.visualization.context

import java.util.List

/**
 * Util class that contains some static methods commonly used in the OSGi synthesis for modifying the visualization
 * context.
 * 
 * @author nre
 */
class ContextUtils {
    
    /**
     * Returns true if {@code childContext} is a child visualization context contained in the {@code rootContext}.
     * Uses the {@link IVisualizationContext#getParentVisualizationContext()} to find out if it is contained.
     * 
     * @param rootContext The root context that may contain the child context.
     * @param childContext The child context that should be checked if it is contained in the rootContext.
     * @return If {@code rootContext} is a parent of {@code childContext}.
     */
    def static boolean isChildContext(IVisualizationContext rootContext, IVisualizationContext childContext) {
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
    def static void makeDetailed(IOverviewVisualizationContext overviewContext, IVisualizationContext collapsedContext) {
        // this element was previously collapsed, so put it in the detailed list now and initialize its child
        // visualization contexts.
        overviewContext.collapsedElements.remove(collapsedContext)
        // Only this cast will allow to add the context. We know this adding is type-safe, as the collapsed- and
        // the detailed elements list are always of the same type. If they are not, the collapsed/detailed state
        // here would not make any sense.
        (overviewContext.detailedElements as List<IVisualizationContext>).add(collapsedContext)
        collapsedContext.initializeChildVisualizationContexts
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
        }
    }
    
}