package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Feature
import java.util.Map

/**
 * Context for the OSGi synthesis that contains information about {@link Feature}s.
 * 
 * @author nre
 */
class FeatureContext implements IVisualizationContext<Feature> {
    
    /**
     * The feature to get the data from when visualizing this context.
     */
    Feature feature
    
    /**
     * The parent visualization context.
     */
    IOverviewVisualizationContext<Feature> parent
    
    private new() {}
    
    new(Feature feature, IOverviewVisualizationContext<Feature> parent) {
        this.parent = parent
        this.feature = feature
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return feature
    }
    
    override IOverviewVisualizationContext<Feature> getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<Feature>
    }
    
    override initializeChildVisualizationContexts() {
        // Nothing to do yet.
    }
    
    override deepCopy(Map<IVisualizationContext<?>, IVisualizationContext<?>> seenContexts) {
        val alreadyCloned = seenContexts.get(this)
        if (alreadyCloned !== null) {
            return alreadyCloned as FeatureContext
        }
        
        val clone = new FeatureContext
        clone.feature = feature
        clone.parent = null
        
        seenContexts.put(this, clone)
        return clone
    }
    
}