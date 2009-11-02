/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file LICENSE.txt or see http://www.eclipse.org/legal/ for the
 * license text.
 */
package de.cau.cs.kieler.kiml.viewer.views;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * A LayoutGraphCanvas drawing the graph in a highlighted color and
 * able to retrieve the GMF Editor. Is used to compare the GMF Layout
 * with the LayoutGraph Layout by creating an transparent image of this canvas and 
 * overlaying it over the GMF Editor.
 * 
 * @see TransparentShell
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public class GmfDebugCanvas extends LayoutGraphCanvas {

    private FigureCanvas canvas;
    
    /**
     * @param parent
     */
    public GmfDebugCanvas(final Composite parent) {
        super(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintControl(final PaintEvent event) {
        GC graphics = event.gc;
        super.paintControl(event, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintLayoutGraph(final GC graphics, final Point size) {
        super.paintLayoutGraph(graphics, size,3);
        
    }

    /**
     * Returns the currently active DiagramDocumentEditor.
     * This is supposed to be the GMF Editor. If there is none, it
     * returns null.
     * @return Currently active DiagramDocumentEditor or null
     */
    public DiagramDocumentEditor getGmfEditor() {
        IEditorPart editor = PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage()
        .getActiveEditor();
        if (editor instanceof DiagramDocumentEditor) {  
            DiagramDocumentEditor deditor = (DiagramDocumentEditor) editor;
            return deditor;
        }
        return null;
    }
    

//    public void setGmfCanvas() {
//        try {
//            this.setLayoutGraph(null);
//            DiagramDocumentEditor deditor = getGmfEditor();
//            if(deditor != null){
//                
//                IFigure gmfDiagram = deditor.getDiagramEditPart().getFigure();
//                IFigure layer = deditor.getDiagramEditPart().getLayer("Connection Layer");
//                IFigure viewport = deditor.getDiagramEditPart().getViewport();
//                
//                
//                IFigure root = new Figure();
//                root.setOpaque(false);
//                root.setBounds(layer.getBounds());
//                root.add(layer);
//                layer.setParent(root);
//                lws.setContents(layer);
//                
//                
//            }
//        } catch (NullPointerException e) {/* nothing */
//        }
//}

    
    
}
