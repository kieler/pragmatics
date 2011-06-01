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

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.IViewerProvider;
import edu.umd.cs.piccolo.PNode;

/**
 * A viewer provider for Piccolo nodes or lists of Piccolo nodes.
 * 
 * @author mri
 */
public class PiccoloViewerProvider implements IViewerProvider {

    /**
     * {@inheritDoc}
     */
    public Viewer createViewer(final Composite parent) {
        return new PiccoloViewer(parent);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isModelSupported(final Object model) {
        if (model instanceof PNode) {
            return true;
        }
        if (model instanceof List) {
            List<?> list = (List<?>) model;
            if (list.size() > 0) {
                Object first = list.get(0);
                return first instanceof PNode;
            }
        }
        return false;
    }

}
