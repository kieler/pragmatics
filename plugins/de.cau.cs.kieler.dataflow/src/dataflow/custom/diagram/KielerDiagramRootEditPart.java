package dataflow.custom.diagram;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;

/**
 * Custom RootEditPart with support of BorderItemRectilinearRouter to better
 * support connections between border items.
 * 
 * This RootEditPart must be provided by the Diagram Editor's EditPartProvider.
 * 
 * @author haf
 *
 */
public class KielerDiagramRootEditPart extends RenderedDiagramRootEditPart {

	
	public KielerDiagramRootEditPart(MeasurementUnit mu) {
		super(mu);
	}
	
    /** Overrides the default createPrintableLayersMethod to introduce a custom
     *  ConnectionLayer that uses the BorderItemRectilinearRouter to support
     *  a better routing of connections between border items (ports).
     * @see org.eclipse.gef.ui.parts.FreeformGraphicalRootEditPart#createPrintableLayers()
     */
	@Override
    protected LayeredPane createPrintableLayers() {
    	FreeformLayeredPane layeredPane = new FreeformLayeredPane();
              
    	layeredPane.add(new BorderItemsAwareFreeFormLayer(), PRIMARY_LAYER);
    	layeredPane.add(new KielerConnectionLayerEx(), CONNECTION_LAYER);
		layeredPane.add(new FreeformLayer(), DECORATION_PRINTABLE_LAYER);

        return layeredPane;        
    }

	
}
