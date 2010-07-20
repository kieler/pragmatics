/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphiti;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;

/**
 * @author atr
 *
 */
public class GraphitiLayoutInspector implements ILayoutInspector {

    IPictogramElementEditPart focusEditPart;
    
  
    
    public GraphitiLayoutInspector(IPictogramElementEditPart editPart) {
        if (editPart == null) {
            throw new NullPointerException("editPart");
        }
        focusEditPart = editPart;
    }
   
    
    /**
     * {@inheritDoc}
     */
    public void initOptions() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public List<LayoutOptionData> getOptionData() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public KOption getKOption(LayoutOptionData optionData, boolean create) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void removeKOption(LayoutOptionData optionData) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public LayoutProviderData getFocusLayouterData() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public EditPart getContainerPart() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutProviderData getContainerLayouterData() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void removeAllKOptions() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(LayoutOptionData optionData) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setDefault(LayoutOptionData optionData, Object value) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public EditPart getFocusPart() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public EObject getFocusModel() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IFigure getDrawingLayer() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public TransactionalEditingDomain getEditingDomain() {
        // TODO Auto-generated method stub
        return null;
    }

}
