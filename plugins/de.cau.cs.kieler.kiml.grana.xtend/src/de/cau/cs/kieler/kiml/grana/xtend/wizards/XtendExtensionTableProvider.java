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

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.core.model.transformation.AbstractTransformation;

/**
 * Provider class for the xtend analyses table.
 * 
 * @author mri
 */
public class XtendExtensionTableProvider extends LabelProvider implements
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
        if (element instanceof AbstractTransformation) {
            AbstractTransformation transformation =
                    (AbstractTransformation) element;
            switch (columnIndex) {
            case 0:
                return transformation.getTransformation();
            case 1:
                String paramString = "";
                boolean first = true;
                for (String parameter : transformation.getParameterList()
                        .get(0)) {
                    if (first) {
                        first = false;
                    } else {
                        paramString += ", ";
                    }
                    paramString += parameter;
                }
                return paramString;
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
        if (inputElement instanceof List) {
            @SuppressWarnings("rawtypes")
            List list = (List) inputElement;
            return list.toArray();
        }
        return null;
    }
}
