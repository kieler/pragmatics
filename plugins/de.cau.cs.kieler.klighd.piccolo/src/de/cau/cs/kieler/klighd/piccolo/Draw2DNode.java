/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.ui.statushandlers.StatusManager;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A Piccolo node for displaying Draw2D figures.
 *
 * @author msp
 */
public class Draw2DNode extends PNode {

    /** the serial version UID. */
    private static final long serialVersionUID = -1948310925725969628L;
    
    /** the figure that is displayed by this node. */
    private IFigure figure;
    
    /**
     * Create a Draw2D node with the given figure.
     * 
     * @param figure a Draw2D figure
     */
    public Draw2DNode(final IFigure figure) {
        this.figure = figure;
        org.eclipse.draw2d.geometry.Rectangle bounds = figure.getBounds();
        this.setBounds(bounds.preciseX(), bounds.preciseY(), bounds.preciseWidth(),
                bounds.preciseHeight());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        try {
            GraphicsAdapter graphics = new GraphicsAdapter((SWTGraphics2D) paintContext.getGraphics());
            figure.paint(graphics);
        } catch (Throwable throwable) {
            IStatus status = new Status(IStatus.ERROR, "de.cau.cs.kieler.skad",
                    "Error while drawing the selected diagram.", throwable);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            throwable.printStackTrace();
        }
    }

}
