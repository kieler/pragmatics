package de.cau.cs.kieler.graphs.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.ui.util.EditorUtils;

public class DrawDebugInfoHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        IEditorPart editor = EditorUtils.getLastActiveEditor();

        if (editor instanceof DiagramEditor) {
            /*
             * <ul> <li>GEF layers: in LayerConstants <li>FEEDBACK_LAYER:
             * transparent for user interactions, but does not get zoomed
             * <li>GRID_LAYER: draws grid, only visible when grid enabled
             * <li>PRIMARY_LAYER: main layer that contains all nodes (possibly
             * nested) <li>CONNECTION_LAYER: topmost layer that contains all
             * edges. drawings here will make user interaction impossible
             * 
             * <li>GMF layers: in DiagramRootEditPart
             * <li>DECORATION_UNPRINTABLE_LAYER: nomen est omen, gets zoomed
             * <li>PAGE_BREAKS_LAYER: gets zoomed, transpaent for user
             * interaction </ul>
             */
            IFigure layer = ((DiagramEditor) editor).getDiagramEditPart().getLayer(
                    DiagramRootEditPart.PAGE_BREAKS_LAYER);

            // draw some exising figure class, e.g. a Rectangle
            Rectangle bounds = new Rectangle(100, 100, 100, 100);
            RectangleFigure rect = new RectangleFigure();
            rect.setFill(false);
            rect.setForegroundColor(ColorConstants.red);
            rect.setBounds(bounds);
            layer.add(rect);

            // draw some custom stuff in low level drawing methods
            CustomDebugFigure debug = new CustomDebugFigure();
            layer.add(debug);
        }

        return null;
    }

    /**
     * Temp Figure that draws some arbitrary debug stuff.
     * 
     * @author haf
     * 
     */
    class CustomDebugFigure extends Shape {
        public CustomDebugFigure() {
            // you have to set the bounding box of the figure in any case
            this.setBounds(new Rectangle(0, 0, 400, 400));
        }

        @Override
        protected void fillShape(Graphics graphics) {
            // TODO Auto-generated method stub
        }

        @Override
        protected void outlineShape(Graphics graphics) {
            graphics.setForegroundColor(ColorConstants.blue);
            graphics.drawLine(10, 10, 300, 300);
            graphics.drawText("Hello World!", 50, 50);
        }
    }

}
