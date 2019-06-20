package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import java.util.List

/**
 * Interface for a visualization context for overviews that simply shows a list of elements in collapsed or detailed
 * fashion individually.
 * 
 * @param <M> The model element class of the child contexts of this context.
 * 
 * @author nre
 */
interface IOverviewVisualizationContext<M> extends IVisualizationContext<List<M>> {
    
    /**
     * The visualization contexts of the detailed elements.
     */
    def List<? extends IVisualizationContext<M>> getDetailedElements()
    
    /**
     * The visualization contexts of the collapsed elements.
     */
    def List<? extends IVisualizationContext<M>> getCollapsedElements()
    
    override List<? extends IVisualizationContext<M>> getChildContexts()
    
}