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
package de.cau.cs.kieler.kiml.grana.xtend.wizards;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.kiml.service.grana.AbstractInfoAnalysis;

/**
 * Provider class for the analyses table.
 * 
 * @author mri
 */
public class AnalysisTableProvider extends LabelProvider implements
        ITableLabelProvider, IStructuredContentProvider {

    /**
     * {@inheritDoc}
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        // no images
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getColumnText(final Object element, final int columnIndex) {
        if (element instanceof AbstractInfoAnalysis) {
            AbstractInfoAnalysis analysis = (AbstractInfoAnalysis) element;
            switch (columnIndex) {
            case 0:
                return analysis.getName();
            case 1:
                return analysis.getDescription();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput) {
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof Collection) {
            @SuppressWarnings("rawtypes")
            Collection collection = (Collection) inputElement;
            return collection.toArray();
        }
        return null;
    }
}
