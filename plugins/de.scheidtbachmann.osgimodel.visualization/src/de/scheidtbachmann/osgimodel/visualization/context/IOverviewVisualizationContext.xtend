package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import java.util.List

/**
 * Interface for a visualization context for overviews that simply shows a list of elements in collapsed or detailed
 * fashion individually.
 */
interface IOverviewVisualizationContext extends IVisualizationContext {
    
    /**
     * The visualization contexts of the detailed elements.
     */
    def List<? extends IVisualizationContext> getDetailedElements()
    
    /**
     * The visualization contexts of the collapsed elements.
     */
    def List<? extends IVisualizationContext> getCollapsedElements()
    
}