package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.PackageObject

/**
 * Context for the OSGi synthesis that contains information about {@link PackageObject}s.
 * 
 * @author nre
 */
class PackageObjectContext implements IVisualizationContext<PackageObject> {
    
    /**
     * The package object to get the data from when visualizing this context.
     */
    PackageObject packageObject
    
    /**
     * The parent visualization context.
     */
    IOverviewVisualizationContext<PackageObject> parent
    
    private new() {}
    
    new(PackageObject packageObject, IOverviewVisualizationContext<PackageObject> parent) {
        this.parent = parent
        this.packageObject = packageObject
    }
    
    override getChildContexts() {
        return #[]
    }
    
    override getModelElement() {
       return packageObject
    }
    
    override IOverviewVisualizationContext<PackageObject> getParentVisualizationContext() {
        return parent
    }
    
    override setParentVisualizationContext(IVisualizationContext<?> parent) {
        this.parent = parent as IOverviewVisualizationContext<PackageObject>
    }
    
    override initializeChildVisualizationContexts() {
        // Nothing to do yet.
    }
    
    override deepCopy() {
        val clone = new PackageObjectContext
        clone.packageObject = packageObject
        clone.parent = null
        
        return clone
    }
    
}