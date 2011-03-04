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
package de.cau.cs.kieler.klighd.piccolo.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.klighd.piccolo.PiccoloViewer;
import edu.umd.cs.piccolo.PNode;

/**
 * A view part for light-weight piccolo diagrams.
 *
 * @author msp
 */
public class PiccoloViewPart extends ViewPart {

    /** the viewer that is shown in this view part. */
    private PiccoloViewer viewer;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        viewer = new PiccoloViewer(parent);
        
        ShowDiagramAction showDiagramAction = new ShowDiagramAction(this, getSite().getPage());
        getViewSite().getActionBars().getToolBarManager().add(showDiagramAction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
    
    /**
     * Show the given Piccolo node in the viewer.
     * 
     * @param piccoloNode a Piccolo node
     */
    public void showNode(final PNode piccoloNode) {
        viewer.setInput(piccoloNode);
    }

}
