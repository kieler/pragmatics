/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.info.views;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.kiml.smart.MetaLayout;

/**
 * Content provider for the smart layout view.
 *
 * @author msp
 */
public class SmartLayoutContentProvider implements ITreeContentProvider {

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof List<?>) {
            return ((List<?>) inputElement).toArray();
        }
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasChildren(final Object element) {
        return element instanceof MetaLayout;
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof MetaLayout) {
            
        }
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(final Object element) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
