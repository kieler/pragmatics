package de.scheidtbachmann.osgimodel.visualization.context

import com.google.common.collect.ImmutableList
import de.scheidtbachmann.osgimodel.Bundle
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Context for the OSGi synthesis that contains information about {@link Bundle} overviews.
 */
@Accessors
class BundleOverviewContext implements IOverviewVisualizationContext {
    
    /**
     * All connections for the required bundles hierarchy that should be drawn.
     * The pairs should be viewed that the first bundle requires the second.
     */
    List<Pair<BundleContext, BundleContext>> requiredBundleEdges
    
    /**
     * All connections for the required packages that should be drawn.
     */
    List<UsedPackageEdgeConnection> usedPackagesEdges
    
    /**
     * The bundle contexts for all bundles in their collapsed form.
     */
    List<BundleContext> collapsedBundleContexts
    
    /**
     * The bundle contexts for all bundles in their detailed form.
     */
    List<BundleContext> detailedBundleContexts
    
    /**
     * The bundles displayed in this context.
     */
    List<Bundle> bundles
    
    /**
     * The parent visualization context.
     */
    IVisualizationContext parent
    
    private new() {}
    
    new(List<Bundle> bundles, IVisualizationContext parent) {
        this.parent = parent
        this.bundles = bundles
        requiredBundleEdges = new LinkedList
        usedPackagesEdges = new LinkedList
        collapsedBundleContexts = new LinkedList
        detailedBundleContexts = new LinkedList
        initializeChildVisualizationContexts
    }
    
    override getChildContexts() {
        return ImmutableList.copyOf(detailedBundleContexts + collapsedBundleContexts)
    }
    
    override getModelElement() {
        return ImmutableList.copyOf(detailedBundleContexts.map[bundle] + collapsedBundleContexts.map[bundle])
    }
    
    override getDetailedElements() {
        return detailedBundleContexts
    }
    
    override getCollapsedElements() {
        return collapsedBundleContexts
    }
    
    override getParentVisualizationContext() {
        return parent
    }
    
    override initializeChildVisualizationContexts() {
        bundles.forEach[
            collapsedBundleContexts += new BundleContext(it, this)
        ]
    }
    
    override deepCopy() {
        val copy = new BundleOverviewContext
        // remember the cloned bundle contexts, as they may be used multiple times.
        val clonedBundleContexts = new HashMap<BundleContext, BundleContext>
        copy.usedPackagesEdges = usedPackagesEdges.clone
        copy.collapsedBundleContexts = new LinkedList
        collapsedBundleContexts.forEach[
            val newBundleContext = deepCopy as BundleContext
            clonedBundleContexts.put(it, newBundleContext)
            newBundleContext.parent = copy
            copy.collapsedBundleContexts.add(newBundleContext)
        ]
        copy.detailedBundleContexts = new LinkedList
        detailedBundleContexts.forEach[
            val newBundleContext = deepCopy as BundleContext
            clonedBundleContexts.put(it, newBundleContext)
            newBundleContext.parent = copy
            copy.detailedBundleContexts.add(newBundleContext)
        ]
        copy.requiredBundleEdges = new LinkedList<Pair<BundleContext, BundleContext>>
        requiredBundleEdges.forEach[
            copy.requiredBundleEdges.add(clonedBundleContexts.get(key) -> clonedBundleContexts.get(value))
        ]
        copy.bundles = bundles.clone
        
        return copy
    }
    
}