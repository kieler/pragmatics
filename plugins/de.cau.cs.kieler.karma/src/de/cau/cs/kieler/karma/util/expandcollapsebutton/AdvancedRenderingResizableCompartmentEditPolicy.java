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
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.karma.util.expandcollapsebutton;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;

/**
 * 
 * @author ckru
 * 
 */
public class AdvancedRenderingResizableCompartmentEditPolicy extends ResizableCompartmentEditPolicy {

    private AdvancedRenderingCompartmentCollapseHandle collapseHandle;

    private IFigure collapseFigure = null;

    private IFigure expandFigure = null;

    private Locator collapseExpandLocator = null;

    @Override
    protected List createCollapseHandles() {
        IGraphicalEditPart part = (IGraphicalEditPart) getHost();

        List collapseHandles = new ArrayList();
        collapseHandle = new AdvancedRenderingCompartmentCollapseHandle(part, collapseFigure,
                expandFigure, collapseExpandLocator);
        collapseHandles.add(collapseHandle);
        return collapseHandles;
    }

    /**
     * set custom collapse figure.
     * @param figure the new custom figure
     */
    public void setCollapseFigure(final IFigure figure) {
        this.collapseFigure = figure;
    }

    /**
     * set custom expand figure.
     * @param figure the new custom figure
     */
    public void setExpandFigure(final IFigure figure) {
        this.expandFigure = figure;
    }

    /**
     * set custom collapse/expand locator.
     * @param locator the new custom figure
     */
    public void setCollapseExpandLocator(final Locator locator) {
        this.collapseExpandLocator = locator;
    }

}
